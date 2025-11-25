# ネストしたJSONをマッピングできるかの検証

次のようなTS型定義から生成されるJSONについて、KotlinでJacksonを使ってKotlinクラスにマッピングするサンプルコード。
プロジェクト管理はGradle, JVMは17。

```typescript
type Root = { name: string };

type TypeA = Root & { type: "type-a" };
type TypeB = Root & { type: "type-b" };
type TypeC = Root & { type: "type-c"; additional: "string" };

type TypeAChild1 = TypeA & { skip: true };
type TypeAChild2 = TypeA & { skip: false; additional: string };

type TypeBChild1 = TypeB & { skip: true };
type TypeBChild2 = TypeB & { skip: false; additional: string };
```

## 結論

2段階の `@JsonTypeInfo` はJacksonではサポートされていませんでした。

- https://github.com/FasterXML/jackson-databind/issues/4542
