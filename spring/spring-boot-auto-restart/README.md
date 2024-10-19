Kotlin & Gradle のプロジェクトで、 Thymeleaf テンプレートや css ファイルを編集すると、DevToolsの機能でリスタートされてしまう。

本来、これらの編集ではリスタートされないのでは？という疑問を調査。

- https://docs.spring.io/spring-boot/how-to/hotswapping.html
- https://docs.spring.io/spring-boot/reference/using/devtools.html


## Maven + Kotlin

thymeleaf(html), css ともリスタートされない。

## Gradle + Kotlin

リスタートされてしまう。

issueがあった:
- [ Devtools always triggers restart if build with gradle #20136](https://github.com/spring-projects/spring-boot/issues/20136)
    - [Delegated build process always triggers Spring-Boot devtools restart #12220](https://github.com/gradle/gradle/issues/12220)
