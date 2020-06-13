# はじめに

[ちょっと待って！そのUserDetails、本当に必要ですか？](https://qiita.com/yukihane/items/865db9e7279cf0e588a7)で書いたことの繰り返しなんですけども。

Spring Securityで認証機能やってみた系のエントリは高確率で[`UserDetailsService`](https://docs.spring.io/spring-security/site/docs/5.3.3.RELEASE/api/org/springframework/security/core/userdetails/UserDetailsService.html), [`UserDetails`](https://docs.spring.io/spring-security/site/docs/5.3.3.RELEASE/api/org/springframework/security/core/userdetails/UserDetails.html)使って実装してると思うんだけど、そんなん使って実装してみても重要なところなんも理解できなかったでしょ？
(暗黙のデフォルト設定がゴイゴイ入っているので、そのデフォルト設定の仕組みを理解しないことには肝心の認証機能について入っていけないでしょ？)

自分は[10. Authentication](https://docs.spring.io/spring-security/site/docs/5.3.3.RELEASE/reference/html5/#servlet-authentication)章の冒頭で列挙されているような要素を理解することがまず優先すべきことだと思ってる。もうちょっと絞て具体的に言うと

- `AuthenticationProvider`(及びそれを取りまとめる `AuthenticationManager`): 具体的に認証処理を実装するところ
- `Filter`: リクエストをフックして認証処理を行うようにする
- `WebSecurityConfig`: 上記のフィルタやら認証プロバイダやらを使うようにする設定

の3点を理解するのが最初の一歩目だと信じてるんですねこれ。

ところが(すっとこ)どっこい(しょ)、`UserDetailsService`使うと実際に認証処理を行うところである`AuthenticationProvider`からしてどこにいっちゃってるのかわかんなくなる。
いやいやさすがにそこ外したらいかんでしょ、というのが私が懸念するところです。

なのでここで`UserDetailsService`を使わない「やってみた」記事をぶちかまそうというのが主旨です。

ちなみに、タイトルでは「一発目に使うのは止めよう」と言っていますが、個人的には二発目以降も別に要らんと思ってます。
ただ[公式ドキュメントでえらい推されてる](https://docs.spring.io/spring-security/site/docs/5.1.5.RELEASE/reference/htmlsingle/#tech-userdetailsservice)んで若干弱気(…と思って最新版のドキュメント見たらこの辺の記述無くなって、やや推し力は弱まっている感じも受けたけど(それでもまだドキュメント内を"userdetails"で検索すると248箇所もヒットするんだけどね))。
`UserDetailsService`使えばこんな便利なんだぜ！みたいなことがあるのなら教えて欲しいんだぜ。

更にちなむと、公式ドキュメント中で最も簡潔に`UserDetailsService`について説明されているのは"[What is a UserDetailsService and do I need one?](https://docs.spring.io/spring-security/site/docs/5.3.3.RELEASE/reference/html5/#appendix-faq-what-is-userdetailservice)"節。そんな長くないので全文引っ張ってくると:

> `UserDetailsService` is a DAO interface for loading data that is specific to a user account. It has no other function other to load that data for use by other components within the framework. It is not responsible for authenticating the user. Authenticating a user with a username/password combination is most commonly performed by the `DaoAuthenticationProvider`, which is injected with a `UserDetailsService` to allow it to load the password (and other data) for a user in order to compare it with the submitted value. Note that if you are using LDAP, this approach may not work.

> If you want to customize the authentication process then you should implement `AuthenticationProvider` yourself. See this blog article for an example integrating Spring Security authentication with Google App Engine.

というわけで、前述の`UserDetailsService`に隠されてしまった`AuthenticationProvider`実装というのは`DaoAuthenticationProvider`のことなんですけれども、`UserDetailsService`使って「やってみた」人、その点理解できてました？

そして本記事の主旨としては、このドキュメントの文章を借りれば、"implement AuthenticationProvider yourself"をちゃんと「やってみた」しとこうよ、ということになるんだわさ。

# コードへのリンク

https://github.com/yukihane/hello-java/tree/master/spring/springboot-auth-example-202006

以降の文章中では、そのタイミングでの実装コードのハッシュも記しています。

# 「やってみた」してみよう！

## ところで、そもそも何を作ろうとしているの？

以下ののblogで実装している機能(の途中まで)をパクらせてもらいます。

- [Implementing JWT Authentication on Spring Boot APIs - Aauth0 Blog](https://auth0.com/blog/implementing-jwt-authentication-on-spring-boot/)

(以降、こちらのblogエントリのことを「参考元」と呼称します。)


次のような機能を提供するWeb APIを実装します:

- ユーザ登録
- 登録したユーザの認証(ログイン)
- ログインしたユーザのみが取得できるリソース提供

あんま参考元タイトルに"JWT"という単語が入ってますがそこはあんま関係ないです。`AuthenticationProvider`自作するためのネタになっているだけです。

あと、参考元あるんやったらそっち見た方が良いんじゃないの？という疑問については:

- 参考元は `UserDetailsService` 使っているのに対しこちらは使っていない、というのが最も大きな違いです
- 参考元は一気にコードがドバっと出てくるので、実際に作る順番がわかりづらいかな、と思いました(のでこちらでは順番考えて説明しています)
- あとは、勘違いしそうな型の使い回しをなるべくやめたり、誤解しそうな箇所だと思う点について細かな修正を行っています


## ベースプロジェクトの作成と基本設定追加

それじゃ早速やっていきましょう。

### ベースプロジェクト作成

https://start.spring.io/ でベースを作成しよう。ちなみにJav11の想定で、今回利用するSpringBootのバージョンはリリースされたてほやほやの2.3.1だ！(このサンプル書いてる途中にリリースされたよ！)

利用するdependenciesは次の通り:

- 使う使わないに関わらず取り敢えずぶち込んどけ系: **DevTools**, **Lombok**, **Spring Configuration Processor**
- スタンダードなウェブアプリ作るので: **Spring Web**
- Spring Securityをやってみたするので当然: **Spring Security**
- DBにユーザ登録してそのデータで認証処理するので: **Spring Data JPA**, **H2 Database**

ベースプロジェクトの作成が終わったら、ダウンロード&展開して[Spring Tools 4 for Eclipse](https://spring.io/tools)を起動してプロジェクトをインポートしよう。

続いて色々基本的な設定を加えていくよ。

### 依存ライブラリの追加

参考元に書いてある通り、今回のサンプルでは次のライブラリが追加で必要なので`pom.xml`の`dependencies`に追加します:

```xml:pom.xml
    <dependency>
      <groupId>javax.xml.bind</groupId>
      <artifactId>jaxb-api</artifactId>
    </dependency>
    <dependency>
      <groupId>com.auth0</groupId>
      <artifactId>java-jwt</artifactId>
      <version>3.10.3</version>
    </dependency>
```

### SQL ログ出力

インメモリDB使うのでホンマにDBに入ってんの？とか気になると思うのでSQLをログ出力するようにしときます。

```properties:src/main/resources/application.properties
logging.level.org.hibernate.SQL=DEBUG
logging.level.org.hibernate.type.descriptor.sql.BasicBinder=TRACE
```

### UserDetailsService の auto-configuration を無効化

今回の目玉、`UserDetailsService`を使わない、を確実にするために`UserDetailsServiceAutoConfiguration`をdisableします。デフォルトだと良い感じに設定されちゃってるので、使ってないつもりで使ってた、みたいなことが(SpringBootあるある)。

```java:src/main/java/com/example/springbootauthexample202006/SpringbootAuthExample202006Application.java
package com.example.springbootauthexample202006;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;

@SpringBootApplication(exclude = UserDetailsServiceAutoConfiguration.class)
public class SpringbootAuthExample202006Application {
...
```

###  websecurityconfg の auto-configuration を無効化

[この件](https://qiita.com/yukihane/items/cdb7f348da9b32b2ff4d)です。未設定の状態だとBoot君がよしなに設定してくれちゃうんで取り敢えず次の設定をぶち込んでおきます:

```java:src/main/java/com/example/springbootauthexample202006/security/MyWebSecurityConfig.java
package com.example.springbootauthexample202006.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class MyWebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        http.csrf().disable()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }
}
```

いや空設定ちゃうんかい！`csrf`とか何やねんそれ！というツッコミについては、今回のスコープから外れるのでパス。
画面じゃないWeb APIなんでこれで良いんですぅ。

ここまでのコード: [31312e8c94530bb6f6272d0b9c6c9607a83939ec](https://github.com/yukihane/hello-java/tree/31312e8c94530bb6f6272d0b9c6c9607a83939ec/spring/springboot-auth-example-202006)

## ユーザエンティティの作成

さてベースを設定し終わったので実装に入りましょう。

何から作るのが自然かと聞かれたときユーザのエンティティから作ると答えるのは別に変じゃなかろうもん。
ログインIDとかパスワードを保持するところ、最初に欲しいよね？

```java
package com.example.springbootauthexample202006.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Version;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class ApplicationUser {

    @Id
    @GeneratedValue
    private Long id;

    @Version
    private int version;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    public ApplicationUser(final String username, final String password) {
        this.username = username;
        this.password = password;
    }
}
```

はい。特筆すべきところが無い普通のJPA Entityクラスです。もうちょっとそれっぽくemailとかの項目有っても良いんじゃないかとも一瞬考えましたが面倒なのでやめました。

ところでいきなり余談に入るんだけれど(なので読み飛ばしてOK)、`UserDetailsService`を使う場合、`UserDetails`は上記のようなユーザエンティティに実装すべきでしょうか？

自分は、

- 一般的には**ユーザエンティティは`UserDetails`を実装する必要はない**(し、実装しちゃうと理解の妨げになるので、少なくともやってみたコードでは実装すべきではない)
    - (`UserDetails`って [`Authentication#getPrincipal()`](https://docs.spring.io/spring-security/site/docs/5.3.3.RELEASE/api/org/springframework/security/core/Authentication.html#getPrincipal--) で取得できるようになる情報っしょ？principal = ユーザ なわけなく[なくなくなくなくなくない](https://www.youtube.com/watch?v=BhWq-ceJa5k)？)

派なんだけれども、巷にあふれるやってみたコードでは実装しちゃってるコードがどちゃくそ多い。
…書いてて気になってきたのでちょっとQiita内の記事で見てみよう…というわけで根気が途切れるまで新着順で検索してみた:

- ユーザエンティティに`UserDetails`をimplements **する** 派
    - [SpringSecurity(securityConfig)によるログイン機能実装](https://qiita.com/zakioka_pirori/items/f07a768353ac789528dd)
    - [spring boot security + DB認証を試した時のポイント](https://qiita.com/teradatk/items/1e09f0ed4a29d2699504)
    - [SpringBoot(Kotlin)とFreemarkerでログインするサンプル作った](https://qiita.com/renoinn/items/a957431018aa033768ce)
    - [SpringBoot + Spring Securityで認証を行う](https://qiita.com/t-iguchi/items/9d12ab0b260e286ba18c)
- ユーザエンティティに`UserDetails`をimplements **しない** 派
    - [Visual Studio CodeによるSpring5 MVC Webアプリ開発　Spring Security使用編 1/3【準備編】](https://qiita.com/t_skri/items/10760c93b1788207215f)
    - [Spring-Securityの新規登録とログイン（JPA）](https://qiita.com/velphedia/items/5700e701a6350d4dc782)
    - [Spring SecurityでDB認証&BCryptでハッシュ化](https://qiita.com/1412azkz/items/62a14fd5beae96b326a0)
    - [Spring Security と Spring Bootで最小機能のデモアプリケーションを作成する](https://qiita.com/rubytomato@github/items/8eee9e3fa86c89dd305c)
    - [SpringSecurityで認証機能を実装③](https://qiita.com/YJ2222/items/3047949cb6018d2453dc)
- その他: `UserDetails`にユーザエンティティを所有させる派
    - [Spring Security データベースの認証](https://qiita.com/Tomohiro1993/items/c41931ef63cc36c48543)
    - [Spring Security ログイン認証のDBアクセス処理を実装](https://qiita.com/huge-book-storage/items/69485cd8a7b3bc589d4a)
    - [Spring bootで、パスワードをハッシュ化して会員登録 & Spring securityを使って、ログイン機能実装。](https://qiita.com/yamateion/items/c1affc4fa8defa814a41)
    - [SpringSecurityで認証作ってみた　その１フォーム認証](https://qiita.com/shibafu/items/18609b4100994a62dc71)
    - [Spring Security データベースの認証](https://qiita.com/TomohiroSaito/items/dee4f22a3cf94edff7df)
    - [Spring Security with Spring Boot 2.0で簡単なRest APIを実装する](https://qiita.com/rubytomato@github/items/6c6318c948398fa62275)
    - [SpringSecurityでDBログイン認証処理を実装してみた(MyBatis使用)](https://qiita.com/Hyuga-Tsukui/items/8c7e3d9201d07d2be089)

(あんま時間かけて見てないので分類間違い御免)

…どちゃくそ多いという程ではなかった。でも第3の派閥を見つけてしまったよ…

ちなみに[公式リファレンス](https://docs.spring.io/spring-security/site/docs/5.3.3.RELEASE/reference/html5/)では`UserDetails`についての指針は特に無いし、公式ガイド([1](https://spring.io/guides/gs/securing-web/),[2](https://spring.io/guides/tutorials/spring-security-and-angular-js/))のサンプルコード含めてもインメモリで`UserDetails`オブジェクト作ってる例ばっかりでそれを実際にはどこからどうやって取得すべきなのかが推測できないものばっかり。うーんこ💩の。

## サインアップ機能(ユーザ登録機能)

さあさ続きましてはさっきの`ApplicationUser`の永続化でございます。
まだSpring Security関係ないのでサクッと行きましょう。

コントローラと、コントローラがつこてる`ApplicationUser`リポジトリを実装。

```java:src/main/java/com/example/springbootauthexample202006/user/UserController.java
package com.example.springbootauthexample202006.user;

import java.util.List;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Slf4j
public class UserController {

    @Data
    public static class UserForm {
        private String username;
        private String password;
    }

    private final ApplicationUserRepository applicationUserRepository;

    @PostMapping("/sign-up")
    public void signUp(@RequestBody final UserForm form) {
        final ApplicationUser user = new ApplicationUser(
            form.getUsername(),
            form.getPassword());

        final ApplicationUser saved = applicationUserRepository.save(user);

        log.info("User sign-upped: {}", saved);
    }

    @GetMapping("")
    public List<ApplicationUser> users() {
        return applicationUserRepository.findAll();
    }
}
```

```java:src/main/java/com/example/springbootauthexample202006/user/ApplicationUserRepository.java
package com.example.springbootauthexample202006.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplicationUserRepository extends JpaRepository<ApplicationUser, Long> {
}
```

ここまでのコード: [916d7bed6d26787b73091725a662a39051130f04](https://github.com/yukihane/hello-java/tree/916d7bed6d26787b73091725a662a39051130f04/spring/springboot-auth-example-202006)

ここまで実装できたら実際に動かしてみよう。`curl`を使って次を実行だ:

```bash
curl -H "Content-Type: application/json" -X POST -d '{
    "username": "yamada",
    "password": "password"
}' http://localhost:8080/users/sign-up
```

これで`yamada`君が登録された。ログにそれっぽい出力があるはずだ。あるいは、

```bash
curl http://localhost:8080/users
```

で登録ユーザ一覧が見られる。


### パスワードのハッシュ化

(今回の流れで出すには少し細かい話なのかなと思ったのだけれど、)

```bash
curl http://localhost:8080/users
```

を見て気づいたであろうか。そう！！誰も！！パスワードをハッシュ化していないのである！！

というわけでハッシュ化しましょう。

```java:src/main/java/com/example/springbootauthexample202006/user/UserController.java
    @PostMapping("/sign-up")
    public void signUp(@RequestBody final UserForm form) {

        final PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

        final ApplicationUser user = new ApplicationUser(
            form.getUsername(),
            passwordEncoder.encode(form.getPassword()));

        final ApplicationUser saved = applicationUserRepository.save(user);

        log.info("User sign-upped: {}", saved);
    }
```

ここまでのコード: [ec6b8045d0b007c1c6dd3eb58b31bd8b117ee362](https://github.com/yukihane/hello-java/tree/ec6b8045d0b007c1c6dd3eb58b31bd8b117ee362/spring/springboot-auth-example-202006)

もう一度上に書いた`curl`コマンドを実行してみよう。今度は生パスワードでなくハッシュ化されたパスワードがDBに保存されたはずだ。

ちなみにこの`PasswordEncoder`、巷のやってみた記事では次のようにBean化しているものが多い。

```java
@Configuration
public class MyConfig {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
...
```

だけどなァ、これをするとなァ、[Spring Securityのグローバルのデフォルト設定が置き換わ](https://docs.spring.io/spring-security/site/docs/5.3.3.RELEASE/reference/html5/#authentication-password-storage-configuration)っちまうんだよなァ。

わかっててやってるなら良いんだけど、何の説明もなしにいきなり書くなら参考元のように`BCryptPasswordEncoder`をBean化するのが無難じゃなかろかいな。

```java
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
```

## セキュリティ設定

ﾋｬﾊｯｰ!ついにSpring Securityの時間だぜ！

取り敢えず原則認証受けてないとアクセスできないように設定しよう。
ただし、上で実装したサインアップエンドポイントだけは例外だ。誰でもアクセスできなくちゃあならない。
(さもなくば、服を買いに行くための服が無い状態だ。)

```java:src/main/java/com/example/springbootauthexample202006/security/MyWebSecurityConfig.java
http.authorizeRequests()
    .antMatchers(HttpMethod.POST, "/users/sign-up")
    .permitAll()
    .anyRequest().authenticated();
```

ここもまあハマりポイントとかいろいろ有ったりすると思うんだけど涙をのんで今回は詳しい話をパス！

ここまでのコード: [2692a5c1fc141d412777d3e9126c6f4f99727d87](https://github.com/yukihane/hello-java/tree/2692a5c1fc141d412777d3e9126c6f4f99727d87/spring/springboot-auth-example-202006)

さて上記セキュリティ設定が済んだらもう一度上の`curl`コマンドを実行してみよう。

```bash
curl -H "Content-Type: application/json" -X POST -d '{
    "username": "tanaka",
    "password": "password"
}' http://localhost:8080/users/sign-up
```

ふむ、ユーザ登録は登録できているように見える。

```bash
curl http://localhost:8080/users
```

ん？`403`に変わったぞ？となったら正解だ。自由にアクセスできないようにセキュリティ設定したんだからな！

## 認証の実現

### 認証フィルタ

さあそろそろヤマ場だ。
認証フィルタは冒頭「はじめに」で書いた通りリクエストをフックして認証処理を行わせるところだ。

今回、敢えて自作するサンプルを選んだわけだけれども、そういう場合でも1から作るみたいなことはあんまりないと思う。
一番よくあるのは今回みたいに `UsernamePasswordAuthenticationFilter` を継承してカスタマイズする、みたいなものなんじゃなかろうか。

```src/main/java/com/example/springbootauthexample202006/security/JWTAuthenticationFilter.java
package com.example.springbootauthexample202006.security;

import static com.auth0.jwt.algorithms.Algorithm.HMAC512;

import com.auth0.jwt.JWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.Date;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private static final String SECRET = "SecretKeyToGenJWTs";
    private static final long EXPIRATION_TIME = 864_000_000; // 10 days
    private static final String TOKEN_PREFIX = "Bearer ";
    private static final String HEADER_STRING = "Authorization";

    private final ObjectMapper objectMpper = new ObjectMapper();

    public JWTAuthenticationFilter(final AuthenticationManager authenticationManager) {
        super();
        setAuthenticationManager(authenticationManager);
    }

    @Override
    public Authentication attemptAuthentication(final HttpServletRequest req,
        final HttpServletResponse res) throws AuthenticationException {
        try {
            final LoginForm form = objectMpper.readValue(req.getInputStream(), LoginForm.class);

            final UsernamePasswordAuthenticationToken creds = new UsernamePasswordAuthenticationToken(
                form.getUsername(),
                form.getPassword());

            return getAuthenticationManager().authenticate(creds);
        } catch (final IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void successfulAuthentication(final HttpServletRequest req,
        final HttpServletResponse res,
        final FilterChain chain,
        final Authentication auth) throws IOException, ServletException {

        final String token = JWT.create()
            .withSubject(auth
                .getName())
            .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
            .sign(HMAC512(SECRET.getBytes()));
        res.addHeader(HEADER_STRING, TOKEN_PREFIX + token);
    }
}
```

ここまでのコード: [2d84598819a0574f188b57c930a06c23ff7c2db7](https://github.com/yukihane/hello-java/tree/2d84598819a0574f188b57c930a06c23ff7c2db7/spring/springboot-auth-example-202006)

長い割に重要なポイントは2つだけなんだけど、

- `getAuthenticationManager().authenticate(creds);` としてるのが認証プロバイダ(※次節で実装)に認証処理を委譲しているところ。フィルタがやってるのはその認証プロバイダが認証を行うのに必要な情報の抽出。
- (このコード上には現れていなくて、[親クラスがやっている](https://github.com/spring-projects/spring-security/blob/5.3.3.RELEASE/web/src/main/java/org/springframework/security/web/authentication/UsernamePasswordAuthenticationFilter.java#L63)ことなんだけれど、)このフィルタが適用される、つまり認証処理が行われるのは `/login` に対する `POST` 。

というわけで、次は委譲先、認証プロバイダの実装だ。

### 認証プロバイダ

入力されたユーザ名とパスワードがDBデータと一致してるか確認する、これが！これこそが！みんなの思い描く認証だ！

`UserDetailsSevice`使ったときのモヤモヤが晴れるだろう！この素直な実装！

```java:src/main/java/com/example/springbootauthexample202006/security/ApplicationUserAuthenticationProvider.java
package com.example.springbootauthexample202006.security;

import com.example.springbootauthexample202006.user.ApplicationUser;
import com.example.springbootauthexample202006.user.ApplicationUserRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;

@RequiredArgsConstructor
public class ApplicationUserAuthenticationProvider implements AuthenticationProvider {

    private final PasswordEncoder passwordEncoder;
    private final ApplicationUserRepository applicationUserRepository;

    @Override
    public Authentication authenticate(final Authentication authentication) throws AuthenticationException {
        final UsernamePasswordAuthenticationToken auth = (UsernamePasswordAuthenticationToken) authentication;
        final String username = (String) auth.getPrincipal();
        final String password = (String) auth.getCredentials();

        final Optional<ApplicationUser> user = applicationUserRepository.findByUsername(username);

        final Optional<ApplicationUserAuthentication> result = user.map(u -> {
            if (passwordEncoder.matches(password, u.getPassword())) {
                return new ApplicationUserAuthentication(username);
            } else {
                return null;
            }
        });

        return result.orElseThrow(() -> new BadCredentialsException("illegal username or password"));
    }

    @Override
    public boolean supports(final Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }

}
```

ここまでのコード: [a9478f869c84248cb7dcddff8d192878e0388810](https://github.com/yukihane/hello-java/tree/a9478f869c84248cb7dcddff8d192878e0388810/spring/springboot-auth-example-202006)

### 認証処理の利用設定

さあ、認証の実装は行ったので、後はこの実装を使うように設定変更するだけだ。

```java:src/main/java/com/example/springbootauthexample202006/security/MyWebSecurityConfig.java
@Configuration
@RequiredArgsConstructor
public class MyWebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final ApplicationUserRepository applicationUserRepository;

    @Override
    protected void configure(final HttpSecurity http) throws Exception {
...

        final PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        final AuthenticationProvider provider = new ApplicationUserAuthenticationProvider(passwordEncoder,
            applicationUserRepository);
        final AuthenticationManager manager = new ProviderManager(Arrays.asList(provider));

        http.addFilter(new JWTAuthenticationFilter(manager));
    }
}
```

ここまでのコード: [973f5f7a33b84ffbc2f9a069c0a9bd0b9393395c](https://github.com/yukihane/hello-java/tree/973f5f7a33b84ffbc2f9a069c0a9bd0b9393395c/spring/springboot-auth-example-202006)

`http.addFilter()`で使用するフィルタを登録する、ってのがこのコードの本質。
そしてフィルタが利用する認証プロバイダ(を管理する認証マネージャ)をコンストラクタで指定してるってわけ。イージーだね！

当然だけど`PasswordEncorder`はsign-upでユーザ登録したときのものと同じものを使わないと検証できないよ！

んで本題と関係ないけどJavaわかってる感出すために`Arrays.asList()`じゃなくてJava9で導入された`List.of()`つこたろ、ってやったら[流れるようにバグ踏んだ](https://qiita.com/yukihane/items/d7bde522bdb286a18a21)(#8689])ので皆もイキるときは気をつけよう。

もいっこあんまり関係ない話をすると、FilterをBean化すると[ちょっと困ったことになったりもした](https://qiita.com/yukihane/items/3fd4ae02043fb4d99d3c)。

前述の`PasswordEncoder`もそうだけど、よくわからんけど他人のコードコピペしてBean化しました！ってやると予期しない範囲まで波及してしまうという、これもSpring Bootあるあるだね！

閑話休題。あとここで言っとくべきことは、フィルタの適用順って重要、ってことなんだけど、今回のサンプルではもう1個フィルタ追加するのでそんときに説明します。

さあさあ！ついに認証処理を通るリクエストが投げられるようになりましたよ！サインアップしてログインしてみよう！

```bash
curl -H "Content-Type: application/json" -X POST -d '{
    "username": "suzuki",
    "password": "password"
}' http://localhost:8080/users/sign-up
```

```bash
curl -i -H "Content-Type: application/json" -X POST -d '{
    "username": "suzuki",
    "password": "password"
}' http://localhost:8080/login
```

そうするとログイン成功してこんな感じのヘッダが付いて返ってくるはず。[Bearerトークン](https://ja.wikipedia.org/wiki/Bearer%E3%83%88%E3%83%BC%E3%82%AF%E3%83%B3)てやつだね！
(今回説明した事の本質からは逸れてるのであんまり触れないけど、これは `JWTAuthenticationFilter`が認証が正常に終了した後に`successfulAuthentication`で生成してるので気になる人はそこを見てね！)

```
Authorization: Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJzdXp1a2kiLCJleHAiOjE1OTI4OTM3MDN9.ul4oibmjgOMZPoyqu6NqMENIRmoQ92Ht8WsDFr9UupsUo_FeJH4pCwzAa8RP3XNPojYxaJjjq6u91HKJuraz1g
```

次はこのトークンを使えば保護されたリソースへアクセスできる、ようにする実装だ。

## 認可フィルタの実装と適用

### 認可フィルタ

上で登場したBearerトークンの使い方を先に書いとくと、

```
curl -H "Authorization: Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJzdXp1a2kiLCJleHAiOjE1OTI4OTM3MDN9.ul4oibmjgOMZPoyqu6NqMENIRmoQ92Ht8WsDFr9UupsUo_FeJH4pCwzAa8RP3XNPojYxaJjjq6u91HKJuraz1g" \
http://localhost:8080/users
```

みたいにヘッダにつけて保護されたリソースを要求すると、サーバは、「おうおう、あんたなら見せてやれるよ」って言ってくれるわけね。

ただ現時点ではそんな実装してないので上のリクエスト投げても敢え無く`403`になるわけなのよ。それを何とかするのが2つめのフィルタ。

```java
package com.example.springbootauthexample202006.security;

import static com.example.springbootauthexample202006.security.SecurityConstants.HEADER_STRING;
import static com.example.springbootauthexample202006.security.SecurityConstants.SECRET;
import static com.example.springbootauthexample202006.security.SecurityConstants.TOKEN_PREFIX;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

public class JWTAuthorizationFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(final HttpServletRequest req,
        final HttpServletResponse res,
        final FilterChain chain) throws IOException, ServletException {
        final String header = req.getHeader(HEADER_STRING);

        if (header == null || !header.startsWith(TOKEN_PREFIX)) {
            chain.doFilter(req, res);
            return;
        }

        final ApplicationUserAuthentication authentication = getAuthentication(req);

        SecurityContextHolder.getContext().setAuthentication(authentication);
        chain.doFilter(req, res);
    }

    private ApplicationUserAuthentication getAuthentication(final HttpServletRequest request) {
        final String token = request.getHeader(HEADER_STRING);
        if (token != null) {
            // parse the token.
            final String username = JWT.require(Algorithm.HMAC512(SECRET.getBytes()))
                .build()
                .verify(token.replace(TOKEN_PREFIX, ""))
                .getSubject();

            if (username != null) {
                return new ApplicationUserAuthentication(username);
            }
            return null;
        }
        return null;
    }
}
```

ここまでのコード: [90c26b3dcf6e3ba52651f1ad00e9c8c52b0fd35a](https://github.com/yukihane/hello-java/tree/90c26b3dcf6e3ba52651f1ad00e9c8c52b0fd35a/spring/springboot-auth-example-202006)

ヘッダに設定されているBearerトークンをデコードして、その結果から得られる情報をもとに `Authentication`を生成し`SecurityContextHolder.getContext().setAuthentication()`でセキュリティコンテキストへセットする、というのが日本語での簡単な説明。

ここでセットされた`Authentiction`の[`isAuthenticated()`が`true`](https://github.com/spring-projects/spring-security/blob/5.3.3.RELEASE/core/src/main/java/org/springframework/security/core/Authentication.java#L103-L122)なので、SpringBoot君は保護されたリソースへのアクセスを許してくれる。

### 認可フィルタの利用設定

フィルタの登録。基本は1つめのフィルタと同じだね。


```java:src/main/java/com/example/springbootauthexample202006/security/MyWebSecurityConfig.java
@Configuration
@RequiredArgsConstructor
public class MyWebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(final HttpSecurity http) throws Exception {
...
        http.addFilterAfter(new JWTAuthorizationFilter(), JWTAuthenticationFilter.class);
    }
}
```

ここまでのコード: [881cf366e10ba61162470936e267cab6930a2e57](https://github.com/yukihane/hello-java/tree/881cf366e10ba61162470936e267cab6930a2e57/spring/springboot-auth-example-202006)

んで前に触れたフィルタの適用順の話。
フィルタが適用される順番はもちろん重要で、例えば今回のフィルタを例にとると、 http://localhost:8080/login にアクセスしたとき、`JWTAuthorizationFilter`(Bearerトークンのデコード)より`JWTAuthenticationFilter`(Bearerトークンの生成)を優先してほしいわけですよ。
だってログインしようとしてるんだからBearerトークン持ってるはずないじゃん。
なのにBearerトークン要求されたらこれまた服を買いに行くための服以下略じゃないですか！

で、そのフィルタの順番なんですが、基本これ。

- [Table 2. Standard Filter Aliases and Ordering - 18.3.1. Adding in Your Own Filters](https://docs.spring.io/spring-security/site/docs/5.3.3.RELEASE/reference/html5/#ns-custom-filters)

この票に登場するクラス、それを継承したクラスは、`addFilter()`この表の順序に割り当てられる。
例えば`JWTAuthenticationFilter`は`UsernamePasswordAuthenticationFilter`を継承して作ってるので
```
http.addFilter(new JWTAuthenticationFilter(manager));
```
とすると`UsernamePasswordAuthenticationFilter`のところに自動で割り当たる。

一方で、 `JWTAuthorizationFilter`はこの表に登場しない `OncePerRequestFilter` を継承して作っているので(※参考元コードとは異なります)、順序を明示的に教えてあげる必要がある。
なので、`JWTAuthenticationFilter`の後にしてくれ
```
http.addFilterAfter(new JWTAuthorizationFilter(), JWTAuthenticationFilter.class);
```
ってやってるわけ。

# 完成

んじゃ実行してみましょうよ。

```bash
curl -H "Content-Type: application/json" -X POST -d '{
    "username": "ito",
    "password": "password"
}' http://localhost:8080/users/sign-up
```

```bash
curl -i -H "Content-Type: application/json" -X POST -d '{
    "username": "ito",
    "password": "password"
}' http://localhost:8080/login
```

```bash
curl -i -H "Authorization: Bearer <loginで取得したトークン文字列>" \
http://localhost:8080/users
```

いかがでしたか？
