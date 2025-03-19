# MapStructをSpring, Kotlin, Gradleな環境で利用する

## これは何？

以前、MapStruct Spring Extensions を Spring, Java, Maven な環境で試してみました。

- [Mapstruct Spring Extensions を試してみる ](https://yukihane.github.io/blog/202110/18/mapstruct-spring-extensions/)
    - https://github.com/yukihane/hello-java/tree/main/spring/mapstruct-spring-extensions-example

今回はKotlin, Gradle な環境で同様のコードを動かしてみます。

## 動作環境

- Java 21
- Spring Boot 3.4.3
- [MapStruct](https://mapstruct.org/) 1.6.3
- [MapStruct Spring Extensions](https://mapstruct.org/documentation/spring-extensions/reference/html/) 1.1.3

それぞれ、これを記載した時点の最新安定板です。

## 本リポジトリーのコードの確認方法

冒頭にリンクした実装と同じですので、確認方法も同じです。

```bash
./gradlew bootRun
```

あるいはIDEからアプリケーションを起動し、次のリクエストを投げればMapStructでオブジェクトの詰め替えを行う処理が走ります。

```bash
curl --location --request POST 'http://localhost:8080/' \
--header 'Content-Type: application/json' \
--data-raw '{
    "name": "my car",
    "wheel": {
        "size": 100
    },
    "pedal": {
        "size": 20
    }
}'
``````

## MapStruct設定方法

### 1. Spring Extensions を利用しない通常の利用方法

([今回の変更差分](https://github.com/yukihane/hello-java/compare/b746ab...ed7fdf))

公式のサンプル実装は以下にありますのでこれが参考になります:

- https://github.com/mapstruct/mapstruct-examples/tree/main/mapstruct-kotlin-gradle

build.gradle.kts に次のように書き加えます。

1. [`kapt` プラグイン](https://kotlinlang.org/docs/kapt.html#use-in-gradle) を有効化する
2. [MapStructを依存関係に追加する](https://mapstruct.org/documentation/installation/#gradle)
    - このとき、Kotlinでは公式マニュアルの通り `annotationProcessor` を使用するのではなく `kapt` を使用する必要がある
3. `kapt.arguments`
   の設定で[MapStructのオプション](https://mapstruct.org/documentation/stable/reference/html/#configuration-options)
   を設定する
    - Springで利用する場合は `mapstruct.defaultComponentModel` を `spring` にする

### 2. Spring の `Converter` として実装する

([今回の変更差分](https://github.com/yukihane/hello-java/compare/ed7fdf...1a9a33))

上記で利用した通常の `@Mapper` インターフェースに次の変更を加えます:

1. `org.springframework.core.convert.converter.Converter` を継承する
2. (option)変換関数の名称を `Converter` に合うようにリネームする。今回のサンプルではあらかじめ適合するようにしています

変換時には直接 `HogeMapper` を利用するのでなく、 `ConversionService` をインジェクションすれば良くなります。

### 3. MapStruct Spring Extensions を利用する

([今回の変更差分](https://github.com/yukihane/hello-java/compare/1a9a33...bc11db))

1. MapStruct Spring Extensions を利用するよう [
   `build.gradle.kts` を変更します](https://mapstruct.org/documentation/spring-extensions/reference/html/#_gradle)
2. `MapperSpringConfig` (名前は何でもよい) インターフェースを作成し、 `@SpringMapperConfig` と
   `@MapperConfig(componentModel = MappingConstants.ComponentModel.SPRING)` を付与します
    - `@SpringMapperConfig` を付けたクラスと同じパッケージに次で利用する `ConversionServiceAdapter` が生成されます
    - `@MapperConfig(componentModel = MappingConstants.ComponentModel.SPRING)`
      は[マニュアル](https://mapstruct.org/documentation/spring-extensions/reference/html/#mappersAsConvertersCustomNames)
      で付与しているので同じようにしているのですが、何に影響しているのかわかっていないです
3. Mapper の `uses` 値を `ConversionServiceAdapter::class` に置き換えます
