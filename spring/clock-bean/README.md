# Clock をインジェクションするサンプルコード

## 説明

`build.gradle.kts` で `user.timezone` を `UTC` に設定して実行しています。

システムデフォルトのタイムゾーン(上記の通り `UTC`)を利用した場合と、カスタム `Clock` (`Asia/Tokyo` に設定)を利用した場合で現在時刻の取得に差が出ることを確認しています。

## How to run 

```bash
./gradlew bootRun
```

access http://localhost:8080 .
