thymeleafの `th:field` で `input type="date"` な項目と、 `LocalDate` オブジェクトをバインドしたい。

が、やってみると input の value には `yyyy/mm/dd` 形式の文字列が設定され、初期状態で日付が表示されない。

本来、 value には [`yyyy-mm-dd` 形式の文字列を設定する必要がある](https://developer.mozilla.org/ja/docs/Web/HTML/Element/input/date)が、どうすれば良いのだろうか。
