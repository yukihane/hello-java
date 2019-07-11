# これは何？

kazuki43zooさんの認可サーバ/リソースサーバ/クライアント実装を参考に作成したサンプル。
- https://qiita.com/kazuki43zoo/items/9cc00f0c92c7b0e1e529
- https://github.com/kazuki43zoo/spring-security-oauth-demo

その他参考リンク:

- [30.3.3 Authorization Server](https://docs.spring.io/spring-boot/docs/2.1.6.RELEASE/reference/html/boot-features-security.html#_authorization_server) - Spring Boot reference
- [OAuth 2 Developers Guide](https://projects.spring.io/spring-security-oauth/docs/oauth2.html)
- [OAuth2 Autoconfig](https://docs.spring.io/spring-security-oauth2-boot/docs/2.1.6.RELEASE/reference/html5/) - spring-security-oauth2-boot 2.1.6 refernce
- [OAuth2 Boot](https://docs.spring.io/spring-security-oauth2-boot/docs/current-SNAPSHOT/reference/html5/) - spring-security-oauth2-boot current-SNAPSHOT reference

# 実行方法

1. `oauth2-server` と `oauth2-client` を実行。(STS上で実行するのが簡単)
   - http://localhost:8080 がサーバ、 http://localhost:8081/client がクライアント。
1. ブラウザで http://localhost:8081/client/tasks にアクセスする
1. Basic認証を求められるので id:pw を yamada:pwyamada あるいは suzuki:pwsuzuki で入力
1. 認証が通ると認可画面になるので認可。
1. リソースが表示される。

# 要調査TODO

- 認可コード、アクセストークン、リフレッシュトークン、クライントデータ等をDBに永続化する
  - http://terasolunaorg.github.io/guideline/5.5.1.RELEASE/ja/Security/OAuth.html
  - https://stackoverflow.com/a/45698964/4506703
- OAuth認証時、複数の認証方法を選べるようにする(認証エンドポイントを複数作れるようにする)
- 直接ログイン時の認証とOAuthの認証を別方式にする
