fetch("http://localhost:8080/secure/hello", {
  redirect: "follow",
}).then((response) => console.log(response.status));
