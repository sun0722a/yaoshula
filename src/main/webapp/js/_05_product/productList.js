function doFirst() {
	nowPage = document.getElementById("nowPage");
	pageForm = document.getElementById("pageForm");
	searchForm = document.getElementById("searchForm");

	searchForm.addEventListener("change", function() {
		searchForm.submit();
	});
	nowPage.addEventListener("change", function() {
		 pageForm.submit();
	});
}
window.addEventListener("load", doFirst);
