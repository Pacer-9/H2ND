//商品墙模板
function HtmlItem(items){
	var len = items.length;
	var html = "";
	for(var i=0;i<len;i++){
		html +="<div class='goods-msg'>";
		html +="<a href='#'><img src='images/goods.jpg' class='img-rounded goods-img' alt='没有图片哟'/></a>";
		html +="<div><div class='goods-title'><a href='#' title="+items[i].item_title+">"
			   +"<span>"+items[i].item_title+"</span>"
			   +"</a></div>";
		html +="<span class='goods-price'>￥ "+items[i].price+"</span>"
			   +"<span class='goods-focus'>关注数  "+items[i].focus_number+"</span>";
		html +="</div></div>";
	}
	$("#index-content").html(html);
}
$(document).ready(function(){
	//加载所有商品
	$.getJSON("/Thesis/item/index.do",function(data){
		var items = data.allItems;
		HtmlItem(items);
	});
	//加载选择的分类商品
	$("#categorybar a").click(function(){
		$("#sortbar li").not(".selected").find("img").attr("name","DESC")
		$("#sortbar a img").css("display","none");
		$("#categorybar li").removeClass("selected");
		$(this).parent().addClass("selected");
		var title = "H2ND-"+$(this).text();
		var cateid = $(this).attr("name"); 
		var url = "/Thesis/item/category/"+cateid+".do";
		$.getJSON(url,function(data){
			var items = data.subItems;
			HtmlItem(items);
			$(document).attr("title",title);
		});
	});
	//加载搜索的商品
	$("#search-btn").click(function(){
		var keyword = $("#search-frame").val();
		var title = "H2ND-搜索商品'"+keyword+"'";
		var url = "/Thesis/item/search/"+keyword+".do";
		$.getJSON(url,function(data){
			var items = data.searchItems;
			HtmlItem(items);
			$(document).attr("title",title);
		});
	});
	//排序
	$("#sortbar li").click(function(){
		$("#sortbar li").removeClass("selected");
		$(this).addClass("selected");
		$("#sortbar li").not(".selected").find("img").attr("name","DESC")
		$("#sortbar a img").css("display","none");
		$(this).find("img").css("display","inline");
		var flag = $(this).find("img").attr("name");
		var categoryID = $("#categorybar li.selected a").attr("name");
		var sortOrder = "ASC";
		var sortType = $(this).find("a").text();
		var url = "/Thesis/item/order/"+categoryID;
		if(flag=="ASC"){
			$(this).find("img").attr("name","DESC");
			$(this).find("img").attr("src","images/DESC.svg");
			sortOrder = "DESC";
		}else if(flag=="DESC"){
			$(this).find("img").attr("name","ASC");
			$(this).find("img").attr("src","images/ASC.svg");
			sortOrder = "ASC";
		}
		url += "/"+sortOrder+"/"+sortType+".do";
		$.getJSON(url,function(data){
			var items = data.sortItems;
			HtmlItem(items);
		});
	});
});
