import { getDate } from "./date";
import { sum } from "./sum";

document.addEventListener("DOMContentLoaded", (event) => {
  const elm = document.getElementById("client");
  if (elm) {
    elm.innerText = getDate().toString();
  }

  // startsWith が利用できることの確認(IEだと本来利用できない)
  // https://developer.mozilla.org/ja/docs/Web/JavaScript/Reference/Global_Objects/String/startsWith#%E4%BE%8B
  let str = "To be, or not to be, that is the question.";

  console.log(str.startsWith("To be")); // true
  console.log(str.startsWith("not to be")); // false
  console.log(str.startsWith("not to be", 10)); // true

  console.log(sum(1, 2));
});
