const addRowButton = document.getElementById("addRowButton");
addRowButton.addEventListener("click", function () {
  const template = /** @type {HTMLTemplateElement} */ (
    document.getElementById("familyRow")
  );
  const clone = template.content.cloneNode(true);
  const familyTbody = document.querySelector("#familyTable tbody");
  familyTbody.appendChild(clone);
  calcIndex();
});

// イベントデリゲーションを使用して削除ボタンのクリックを処理
const familyTable = document.getElementById("familyTable");
familyTable.addEventListener("click", (event) => {
  const target = /** @type {Element} */ (event.target);

  // 削除ボタンまたはその子要素がクリックされた場合
  if (target.matches('[data-role="remove"], [data-role="remove"] *')) {
    // ボタンの親行（<tr>）を取得して削除
    const row = target.closest("tr");
    if (row) {
      row.remove();
      calcIndex();
    }
  }
});

function calcIndex() {
  const familyTbody = document.querySelector("#familyTable tbody");
  const rows = familyTbody.querySelectorAll("tr");
  rows.forEach((row, index) => {
    const inputs = row.querySelectorAll("input");
    inputs.forEach((input) => {
      const name = input.name;
      const newName = name.replace(/\d+/, index.toString());
      input.name = newName;

      const id = input.id;
      const newId = id.replace(/\d+/, index.toString());
      input.id = newId;
    });

    const labels = row.querySelectorAll("label");
    labels.forEach((label) => {
      const forAttr = label.getAttribute("for");
      if (forAttr) {
        const newFor = forAttr.replace(/\d+/, index.toString());
        label.setAttribute("for", newFor);
      }
    });
  });
}
