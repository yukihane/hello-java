- `EntityGraph` は子のフィルターを行うためのわけではない、らしい
  - が、下のページではその用途で使えている
    - https://qiita.com/sis-yoshiday/items/5117789d2af9ab616729
  - よく見ると、上のページで用いているのは Spring Data JPA の `EntityGraph`, 冒頭の話は JPA の `EntityGraph` のこと
- Spring Data JPA のものと、JPA のもの、それぞれの挙動を見てみよう

結果:

Spring Data JPA が関与しない、JPA(Hibernate)単独で実行してみてもそうなった。

eager を指定すると join fetch になるのは(JPA の仕様ではないので)Hibernate 独自の挙動のように思われる。
