/**
 * 
 */
function go_detail(pseq) {
	var theform = document.getElementById("prod_form");
	
	theform.action = "admin_product_detail?pseq=" + pseq;
	theform.method = "GET";
	theform.submit();
}