import { getDate } from "./date";

document.addEventListener("DOMContentLoaded", (event) => {
  const elm = document.getElementById("client");
  if (elm) {
    elm.innerText = getDate().toString();
  }
});
