$("#searchButton").click(function() {
	var data = $("#queryText").val();
	alert("hello")
	$.ajax({
		type : "POST",
		url : "Controller.jsp",
		data : {
			submit : "search",
			searchString : data
		},
	});
})
$("#spellCheck").click(function() {
	var data = $("#queryText").val();
	checkSpelling(data);
});
function checkSpelling(word) {
	alert(word)
	var str = "";
	BootstrapDialog.show({
        message: $('<div></div>').load('/Computing-Project/JSP/spellCheck.jsp?word=' + word)
    });
}
