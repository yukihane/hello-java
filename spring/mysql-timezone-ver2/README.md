## 使い方

ビルドする:

```bash
docker compose build
```

必要に応じて docker-compose.yml の環境変数の値を書き換える。

例:

- [`spring.jpa.properties.hibernate.jdbc.time_zone`](https://vladmihalcea.com/time-zones-java-web-application/) を設定したいのであれば `HIBERNATE_TIMEZONE` に設定する
- [`preserveInstants` 等の設定](https://dev.mysql.com/doc/connector-j/en/connector-j-time-instants.html) をしたいのであれば `SPRING_DATASOURCE_URL` の値を書き換える
- タイムゾーン設定は `TZ` (mysql, spring それぞれのコンテナーに個別に設定できる)

mysqlサーバーを起動する:

```bash
docker compose up mysql -d
```

直後はmysqlの起動処理が終わっていないので少し待ってからspring bootを実行する:

```bash
docker compose up spring-app
```

spring-app コンテナーはバッチ処理が終われば終了する(ので `-d` オプションは付けていない)。

spring bootの処理が終わったら永続化されたデータを確認する。

```bash
docker compose exec -it mysql /bin/bash
```

でコンテナーに入り、

```bash
mysql -umyuser -pmypassword mydatabase
```

```sql
select * from my_entities;
```

でデータを確認できる。

docker-compose.yml 内の環境変数設定を変えながら

```bash
docker compose up spring-app
```

を実行すればそれぞれの設定でどういう結果になるか比較できる。

## 終了方法

```bash
docker compose down --volumes
```

## リンク

- MySQL Connector/J Developer Guide > Preserving Time Instants
  - https://dev.mysql.com/doc/connector-j/en/connector-j-reference.html
- java.time パッケージサマリー
  - https://docs.oracle.com/javase/jp/21/docs/api/java.base/java/time/package-summary.html
- JDBC4.3 spec
  - https://jcp.org/aboutJava/communityprocess/mrel/jsr221/index3.html
