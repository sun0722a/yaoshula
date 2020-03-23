function doFirst() {
  nowPage = document.getElementById("nowPage");
  pageForm = document.getElementById("pageForm");
  searchForm = document.getElementById("searchForm");
  // nowPage = document.getElementById("nowPage");

  searchForm.addEventListener("change", function() {
    searchForm.submit();
  });
  nowPage.addEventListener("select", changea);
}
function changea() {
  alert("change");
  // pageForm.submit();
}
window.addEventListener("load", doFirst);
