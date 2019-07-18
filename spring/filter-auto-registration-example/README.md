# 説明

1. 認証不要なエンドポイント `/api/public` と認証必要な `/api/private` を作成。 [875e3e6](https://github.com/yukihane/hello-java/commit/875e3e6)
1. 空のウェブセキュリティコンフィグを作成して全てのエンドポイントで認証不要に([参考](https://qiita.com/yukihane/items/cdb7f348da9b32b2ff4d))。 [74c1e02](https://github.com/yukihane/hello-java/commit/74c1e02)
1. `/api/private` へのアクセスは認証が必要であるように設定。 [39ece39](https://github.com/yukihane/hello-java/commit/39ece39)
1. 認証フィルタを実装。 [217ce8a](https://github.com/yukihane/hello-java/commit/217ce8a)
   - この時点でフィルタが有効化され、かつ、本来認証不要な `/api/public` まで認証が必要になってしまう。
1. 認証フィルタを適用。 [6c9ed46](https://github.com/yukihane/hello-java/commit/6c9ed46)
   - 前述の通り既に(意図せず)フィルタが有効化されており、この設定では特に何も変化がない
1. 自動登録されたフィルタを無効化する。 [6f0c001](https://github.com/yukihane/hello-java/commit/6f0c001)
   - 参考: [Disable Registration of a Servlet or Filter](https://docs.spring.io/spring-boot/docs/2.1.6.RELEASE/reference/html/howto-embedded-web-servers.html#howto-disable-registration-of-a-servlet-or-filter)
   - なお、 `MyHeaderAuthenticationFilter` の `@Component` を除去し Spring Bean としないことで自動登録対象から外す、という別の対応策もある。

# 参考

- [78.10 Add a Servlet, Filter, or Listener to an Application](https://docs.spring.io/spring-boot/docs/2.1.6.RELEASE/reference/html/howto-embedded-web-servers.html#howto-add-a-servlet-filter-or-listener)
   > As [described earlier](https://docs.spring.io/spring-boot/docs/2.1.6.RELEASE/reference/html/howto-embedded-web-servers.html#howto-add-a-servlet-filter-or-listener-as-spring-bean), any `Servlet` or `Filter` beans are registered with the servlet container automatically.
- [Introduce a mechanism to disable existing filters/servlets beans #2173](https://github.com/spring-projects/spring-boot/issues/2173)
  - このissueが起源で[自動登録の無効化方法がリファレンスに追記されている](https://github.com/spring-projects/spring-boot/commit/0ef3de4d82acc32a3f44d872e852d94feb8cd5da)
