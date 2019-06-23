# これは何？

いわゆるマスタデータ(このコードでは性別(`Sex`))を自アプリケーションにキャッシュして、DBからの読み取り負荷を軽減する手法の検討。

# 実行方法

    mvn spring-boot:run

結果は標準出力される。

# 挙動説明

`Person` と `Sex` は many-to-one の関係で、`Sex`は更新されないと考えて良いデータである。
したがって、 `SEX`テーブルへは初回`SELECT`時に全レコードを取得しメモリにキャッシュしておく(`SexRepositoryImpl#cache`)。

# 実装説明

`Person` は `Sex` の `ID` を持っているので、Javaオブジェクトに変換する際にこの`ID`を`Sex`オブジェクトに変換する必要がある。
これを実現しているのが `SexTypeHandler`。

これを、 `Person`の `resultMap` で使用している(`PersonMapper.xml`)。
`SexTypeHandler`をグローバルに適用してしまうと(`mybatis-config.xml`コメントアウト箇所)、`SEX`テーブル = `Sex`エンティティマッピング時にも `ID` から `Sex` オブジェクトを取得しようとして無限に再帰する。

あとで気づいたが、公式リファレンス

- [設定 > typeHandlers > Handling Enums](http://www.mybatis.org/mybatis-3/ja/configuration.html#Handling_Enums)

に今回の要望を実現する方法が示唆されている。