# 動機

CDIで言うところの `CDI.current().getBeans(...)` とか `CDI.current().select(...)`とかそんな感じのことをやりたかった。

# 参考リファレンス

ドキュメントでは、やりたいことが書いてあるように見えたのはこのセクション

- [5.10.1. Using AspectJ to Dependency Inject Domain Objects with Spring](https://docs.spring.io/spring/docs/5.1.7.RELEASE/spring-framework-reference/core.html#aop-atconfigurable) - Spring Framework Core リファレンス

だったので、この記述に従って実装してみることにした。

# 環境

- Spring Boot 2.1.5
- Java11
- Lombok使用

# ゴール

次のようなコードを動かしたい。最終的なインジェクションのやり方はともかく、 `new MyPojo()` で生成したインスタンス(つまり非Springコンポーネント)で、Springコンポーネントである `MyComponent` を使いたい。

[d7436a7cd8f4d2ef49707c21b488bdadd9e5fac1](https://github.com/yukihane/hello-java/blob/d7436a7cd8f4d2ef49707c21b488bdadd9e5fac1/spring/aspectj/src/main/java/com/github/yukihane/spring/aspectj/AspectjApplication.java):

    @SpringBootApplication
    public class AspectjApplication implements CommandLineRunner {

        public static void main(final String[] args) {
            SpringApplication.run(AspectjApplication.class, args);
        }

        @Override
        public void run(final String... args) throws Exception {
            System.out.println(new MyPojo().getText());
        }

        public static class MyPojo {
            @Autowired
            private MyComponent myComponent;

            @Getter
            @Setter
            private String greetingText = "Hello, ";

            public String getText() {
                return getGreetingText() + myComponent.getName();
            }
        }

        @Component
        public static class MyComponent {
            public String getName() {
                return this.getClass().getSimpleName();
            }
        }
    }

なお、このまま実行すると、 `MyPojo`クラスの`myComponent`は `null` のままなので、NPEが発生する。

# 作業ログ

結果のソース:

- https://github.com/yukihane/hello-java/tree/master/spring/aspectj

## `@EnableSpringConfigured` `@Configurable` 付与

前述 [Spring Framework リファレンス](https://docs.spring.io/spring/docs/5.1.7.RELEASE/spring-framework-reference/core.html#aop-atconfigurable)に記載されている通り、アノテーションを付与した。

[494691a12b1ae303f3d51caa08b83ccb85923b9e](https://github.com/yukihane/hello-java/commit/494691a12b1ae303f3d51caa08b83ccb85923b9e#diff-59f621b35bac66c30e783ac3af61dc70):

```
+@EnableSpringConfigured
 @SpringBootApplication
 public class AspectjApplication implements CommandLineRunner {
 
@@ -20,6 +23,7 @@ public class AspectjApplication implements CommandLineRunner {
         System.out.println(new MyPojo().getText());
     }
 
+    @Configurable
     public static class MyPojo {
         @Autowired
         private MyComponent myComponent;
```

## 依存関係追加

同じくリファレンスに記載されている通り `pom.xml` へ `spring-aspects` を依存関係に追加した。
[a0b2455bb5b4e5d0937899de20180a482475a023](https://github.com/yukihane/hello-java/commit/a0b2455bb5b4e5d0937899de20180a482475a023#diff-59f621b35bac66c30e783ac3af61dc70):

```
+    <dependency>
+      <groupId>org.springframework</groupId>
+      <artifactId>spring-aspects</artifactId>
+    </dependency>
```

## AspectJアノテーションプロセッシング

おそらく上で追加したアノテーションをコンパイル時に何かするのだろう、と探したところ [aspectj-maven-plugin](https://www.mojohaus.org/aspectj-maven-plugin/usage.html)というものがあったのでusageの通り `pom.xml` へ追記した。

[fc552678d14a5c01f7ea33b6df09453823456510](https://github.com/yukihane/hello-java/commit/fc552678d14a5c01f7ea33b6df09453823456510#diff-59f621b35bac66c30e783ac3af61dc70):

```
   <build>
     <plugins>
+      <plugin>
+        <groupId>org.codehaus.mojo</groupId>
+        <artifactId>aspectj-maven-plugin</artifactId>
+        <version>1.11</version>
+        <executions>
+          <execution>
+            <goals>
+              <goal>compile</goal>       <!-- use this goal to weave all your main classes -->
+              <goal>test-compile</goal>  <!-- use this goal to weave all your test classes -->
+            </goals>
+          </execution>
+        </executions>
+      </plugin>
       <plugin>
         <groupId>org.springframework.boot</groupId>
         <artifactId>spring-boot-maven-plugin</artifactId>
```

この状態でコンパイルを実行すると次のエラーが発生した:

    [ERROR] Failed to execute goal org.codehaus.mojo:aspectj-maven-plugin:1.11:compile (default) on project aspectj: Execution default of goal org.codehaus.mojo:aspectj-maven-plugin:1.11:compile failed: Plugin org.codehaus.mojo:aspectj-maven-plugin:1.11 or one of its dependencies could not be resolved: Could not find artifact com.sun:tools:jar:11.0.2 at specified path /home/yuki/.sdkman/candidates/java/11.0.2-open/../lib/tools.jar

## aspectj-maven-plugin の Java11対応

当pluginのGitHub Issues/PR のページを見てみると、Java11に対して未対応のようだった。
対応したfork versionを作成されている方がいたのでこれを用いることとした。

- [Adding support for JDK 11 and fixing integration test #45](https://github.com/mojohaus/aspectj-maven-plugin/pull/45#issuecomment-445393068)

また、AJC(AspectJ Compilerの略か？)にターゲットJavaバージョンを明示する必要があったので(デフォルトだと1.4だと解釈するようだ)、その対応も行った。

[21a76a79d9..3f0abe043](https://github.com/yukihane/hello-java/compare/fc552678d14a5c01f7ea33b6df09453823456510...3f0abe04351bf6094e70eae58fcf31ec5cd19c70):

```
   <build>
     <plugins>
       <plugin>
-        <groupId>org.codehaus.mojo</groupId>
+        <groupId>com.nickwongdev</groupId>
         <artifactId>aspectj-maven-plugin</artifactId>
-        <version>1.11</version>
+        <version>1.12.1</version>
         <executions>
           <execution>
             <goals>
@@ -61,6 +61,11 @@
             </goals>
           </execution>
         </executions>
+        <configuration>
+          <source>${java.version}</source>
+          <target>${java.version}</target>
+          <complianceLevel>${java.version}</complianceLevel>
+        </configuration>
       </plugin>
       <plugin>
         <groupId>org.springframework.boot</groupId>
```

## Lombokとの組み合わせ対応: .javaに対するAspectJ適用スキップ

ここまでの状態でコンパイルを実行すると次のエラーとなる:

```
[ERROR] Failed to execute goal com.nickwongdev:aspectj-maven-plugin:1.12.1:compile (default) on project aspectj: AJC compiler errors:
[ERROR] error at return getGreetingText() + myComponent.getName();
[ERROR]        ^^
[ERROR] /home/yuki/Documents/repos/java/hello-java/spring/aspectj/src/main/java/com/github/yukihane/spring/aspectj/AspectjApplication.java:36:0::0 The method getGreetingText() is undefined for the type AspectjApplication.MyPojo
```

ここで指摘されている `getGreetingText()` メソッドは、Lombokの `@Getter` アノテーションによって生成されるはずのメソッドだ。 AJC compilerはこれが見えないと言っている。
当然だ。`.java`上には存在しない。

ググると対策が提示されていた。

- [Lombok and AspectJ - Stack Overflow](https://stackoverflow.com/a/52869545/4506703)

ただしなぜこれで上手く行くのか説明は無いので自分なりの解釈をここに書いておく。

AspectJがweavingを行い得るタイミングは3つあるらしい。このうち今回関係しているのは、コンパイル時の話なので、最初の2つだ。
[Chapter 5. Load-Time Weaving - The AspectJ Development Environment Guide](https://www.eclipse.org/aspectj/doc/released/devguide/ltw.html):
> 
- Compile-time weaving is the simplest approach. When you have the source code for an application, ajc will compile from source and produce woven class files as output. The invocation of the weaver is integral to the ajc compilation process. The aspects themselves may be in source or binary form. If the aspects are required for the affected classes to compile, then you must weave at compile-time. Aspects are required, e.g., when they add members to a class and other classes being compiled reference the added members.
- Post-compile weaving (also sometimes called binary weaving) is used to weave existing class files and JAR files. As with compile-time weaving, the aspects used for weaving may be in source or binary form, and may themselves be woven by aspects.
- Load-time weaving (LTW) is simply binary weaving defered until the point that a class loader loads a class file and defines the class to the JVM. To support this, one or more "weaving class loaders", either provided explicitly by the run-time environment or enabled through a "weaving agent" are required.

リンク先Stack Overflowの回答で行っているのは、compile-time weavingをスキップすることでLombokがgetterを生成する前の`.java`ファイルをAJCが見ることを回避し、ただしスキップしたが`.class`に対するpost-compile weavingは強制している、ということなのだろう。

差分は長い割に回答リンク先と変わらないので記載省略。
リンク: [4ca1f64cc4174bb3c41932f6a08b3997779a782a](https://github.com/yukihane/hello-java/commit/4ca1f64cc4174bb3c41932f6a08b3997779a782a#diff-59f621b35bac66c30e783ac3af61dc70)。

## weaving時の問題対処

さて、AspectJ適用をコンパイル後に先送りしてしまったのでここからは`mvn clean compile`でなく`mvn clean process-classes`を実行する必要がある。
早速実行してみると新しいエラーが出る。

```
[ERROR] Failed to execute goal com.nickwongdev:aspectj-maven-plugin:1.12.1:compile (default-compile) on project aspectj: AJC compiler errors:
[ERROR] error can't determine superclass of missing type org.springframework.transaction.interceptor.TransactionAspectSupport
[ERROR] when batch building BuildConfig[null] #Files=0 AopXmls=#0
[ERROR]  [Xlint:cantFindType]
```

これもググったら回答があった:

- [IDEA 10.5.2 Aspectj compiler - can't determine superclass of missing type org.springframework.transaction.interceptor.TransactionAspectSupport
Ask Question - Stack Overflow](https://stackoverflow.com/a/7352398/4506703)

ただし最もupvotedされている回答は何を言っているのかさっぱり理解できない。

何にせよ今回トランザクションに関わることは行っていないし、所詮はlintのメッセージなので[Xlintオプション](https://www.mojohaus.org/aspectj-maven-plugin/compile-mojo.html#Xlint)でエラーレベルを下げて放置することにした。

[62ff366f3755cfd882b15bf6b2b8a4b49807b065](https://github.com/yukihane/hello-java/commit/62ff366f3755cfd882b15bf6b2b8a4b49807b065#diff-59f621b35bac66c30e783ac3af61dc70):

```
               <weaveDirectories>
                 <weaveDirectory>${project.build.directory}/classes</weaveDirectory>
               </weaveDirectories>
+              <Xlint>warning</Xlint>
             </configuration>
```

## 警告メッセージ対応

ここまでで `mvn clean process-classes` は正常終了するようになった。ただし、いくつかのwarningが残っているのでそれらを対処した。

### couldn't find aspectjrt.jar on classpath

`aspectjrt`を依存関係に追加した。

[5311573ab6b8dd45cf921e9d234e9e4fdd51e3a7](https://github.com/yukihane/hello-java/commit/5311573ab6b8dd45cf921e9d234e9e4fdd51e3a7#diff-59f621b35bac66c30e783ac3af61dc70):

```
       <groupId>org.springframework</groupId>
       <artifactId>spring-aspects</artifactId>
     </dependency>
+    <dependency>
+      <groupId>org.aspectj</groupId>
+      <artifactId>aspectjrt</artifactId>
+    </dependency>
 
     <dependency>
       <groupId>org.springframework.boot</groupId>
```

### bad version number found in /home/yuki/.m2/repository/org/aspectj/aspectjrt/1.9.4/aspectjrt-1.9.4.jar expected 1.9.2 found 1.9.4

`aspectj-maven-plugin` が利用すべきバージョンを明示した。

[e63eb7ce811d4081eded526d1310bcea33532c09](https://github.com/yukihane/hello-java/commit/e63eb7ce811d4081eded526d1310bcea33532c09#diff-59f621b35bac66c30e783ac3af61dc70):

```
             </aspectLibrary>
           </aspectLibraries>
         </configuration>
+        <dependencies>
+          <dependency>
+            <groupId>org.aspectj</groupId>
+            <artifactId>aspectjtools</artifactId>
+            <version>1.9.4</version>
+          </dependency>
+        </dependencies>
       </plugin>
       <plugin>
         <groupId>org.springframework.boot</groupId>
```

# 実行

    mvn clean spring-boot:run

で所望の結果が得られた:

    Hello, MyComponent

結果的に、ソースコードは当初の想定通りで、`@Autowired`にコンポーネントがインジェクションされるような形で扱えた。

# おまけ

## Eclipse IDE の対応

`pom.xml` に `aspectj-maven-plugin`(forkでなくオリジナルの方)を設定するとm2eプラグインだったりAJDTだったり？をインストールしてくれようとするのだが、AJDTのリンクが死んでいるようでインストール全体が失敗する。
[AspectJプロジェクトページ](https://www.eclipse.org/aspectj/)からのAJDTリンクも死んでいる。

結果、Eclipse IDEが実行するコンパイルではweavingされない(ので別途`mvn process-classes`を実行する必要がある)。

## 顧客が本当に欲しかったもの

```

@Component
public class ApplicationContextProvider implements ApplicationContextAware {

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    private static class Holder {
        private static final Holder SINGLETON = new Holder();

        private ApplicationContext applicationContext;
    }

    @Override
    public void setApplicationContext(final ApplicationContext applicationContext) throws BeansException {
        Holder.SINGLETON.applicationContext = applicationContext;
    }

    public ApplicationContext getApplicationContext() {
        return Holder.SINGLETON.applicationContext;
    }
}
```

