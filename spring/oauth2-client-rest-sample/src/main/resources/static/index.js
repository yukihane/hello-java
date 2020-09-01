let myfunc = (event) => {
  console.log("hello, world");
  fetch("/api/books")
    .then((response) => response.json())
    .then((data) => console.log(data));
};
