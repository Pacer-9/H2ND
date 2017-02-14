//商品墙模板
function HtmlItem(items){
	var len = items.length;
	var picUrl = "";
	var html = "";
	for(var i=0;i<len;i++){
		if(items[i].pictures != ""){
			picUrl = "pictureServicer/goods/"+items[i].pictures[0].pictureUrl;
		}
		else{
			picUrl = "images/goods-1.jpg";
		}
		html +="<div class='goods-msg'>";
		html +="<a href='html/goods-detail.html?itemId="+items[i].id+"' target='_blank'><img src='"+picUrl+"' class='img-rounded goods-img' alt='没有图片哟'/></a>";
		html +="<div><div class='goods-title'><a href='html/goods-detail.html?itemId="+items[i].id+"' target='_blank' title="+items[i].item_title+">"
			   +"<span>"+items[i].item_title+"</span>"
			   +"</a></div>";
		html +="<span class='goods-price'>￥ "+items[i].price+"</span>"
			   +"<span class='goods-focus'>关注数  "+items[i].focus_number+"</span>";
		html +="</div></div>";
	}
	$("#index-content").html(html);
}
$(document).ready(function(){
	//加载首页
	$.getJSON("/Thesis/item/index.do",function(data){
		var items = data.allItems;
		var categorys = data.categorys;
		//var picDir = data.picDir;
		HtmlItem(items);
		/*加载商品类型*/
		var cate_html = "<li class='selected'><a name='-1' href='#'>所有商品</a></li>";
		for (var i = 1; i < categorys.length-1; i++) {
			cate_html += "<li><a name='"+categorys[i].id+"' href='#'>"+categorys[i].categoryName+"</a></li>"
		}
		cate_html += "<li><a name='"+categorys[0].id+"' href='#'>"+categorys[0].categoryName+"</a></li>"
		$("#categorybar").html(cate_html);
	});
	//加载选择的分类商品
	$(document).on("click","#categorybar a",function(){
		$("#sortbar li").not(".selected").find("img").attr("name","DESC")
		$("#sortbar a img").css("display","none");
		$("#categorybar li").removeClass("selected");
		$(this).parent().addClass("selected");
		var title = "H2ND-"+$(this).text();
		var cateid = $(this).attr("name"); 
		var url = "/Thesis/item/category/"+cateid+".do";
		$.getJSON(url,function(data){
			var items = data.subItems;
			//var picDir = data.picDir;
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
			//var picDir = data.picDir;
			HtmlItem(items);
			$(document).attr("title",title);
		});
	});
	//排序
	$(document).on("click","#sortbar li",function(){
		$("#sortbar li").removeClass("selected");
		$(this).addClass("selected");
		$("#sortbar li").not(".selected").find("img").attr("name","DESC")
		$("#sortbar a img").css("display","none");
		$(this).find("img").css("display","inline");
		var flag = $(this).find("img").attr("name");
		var categoryID = $("#categorybar li.selected a").attr("name");
		var sortOrder = "ASC";
		var sortType = $(this).find("a").text();
		var url = "/Thesis/item/category/"+categoryID;
		if(flag=="ASC"){
			$(this).find("img").attr("name","DESC");
			$(this).find("img").attr("src","images/DESC.svg");
			sortOrder = "DESC";
		}else if(flag=="DESC"){
			$(this).find("img").attr("name","ASC");
			$(this).find("img").attr("src","images/ASC.svg");
			sortOrder = "ASC";
		}
		url += "/order/"+sortOrder+"/"+sortType+".do";
		$.getJSON(url,function(data){
			var items = data.sortItems;
			//var picDir = data.picDir;
			HtmlItem(items);
		});
	});
	/*登录*/
	$("#login").click(function(){
		$.getJSON("/Thesis/user/login.do",function(data){
			alert("ok");
		});
	});
});
