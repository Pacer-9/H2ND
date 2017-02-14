$(document).ready(function(){
	var url = "/Thesis/item/detail/itemId/"+getUrlParam("itemId")+".do";
	$.getJSON(url,function(data){
		var item = data.item;
		var user = data.owner;
		var pictures = item.pictures;
		var title = "H2ND-"+item.item_title;
		var pic_html = "";
		if(pictures==""){
			pic_html += '<li><img id="small-picture-1" src="../images/goods-1.jpg" alt="空白" /></li>';
			$(".goods-picture-small ul").html(pic_html);
			$(".goods-picture-large img").attr("src","../images/goods-1.jpg");
		}
		else{
			/*商品图片*/		
			for (var i = 0; i < pictures.length; i++) {
				var picUrl = "../pictureServicer/goods/"+pictures[i].pictureUrl
				pic_html += '<li><img id="small-picture-1" src="'+picUrl+'" alt="空白" /></li>';
			}
			$(".goods-picture-small ul").html(pic_html);
			$(".goods-picture-large img").attr("src","../pictureServicer/goods/"+pictures[0].pictureUrl);
		}
		/*商品信息*/
		$("#goods-name span").append(item.item_title);
		$("#goods-price span").append(item.price);
		$("#user-name span").append(user.userName);
		$("#tel span").append(item.telephoneNumber);
		$("#social-account span").append(item.socialAccount);
		$("#trans-address span").append(item.location);
		$("#authentication span").append("未认证");
		$("#publish-date span").append(formatDate(new Date(item.public_date)));
		
		/*商品描述*/
		$("#description p").html(item.item_description);
		
		/*网页标题*/
		$(document).attr("title",title);
	})
	/*tab转换*/
	$(".nav-tabs a").click(function(){
	        $(this).tab('show');
	});
	/*轮播图初始化*/
	$('#myCarousel').carousel();
	/*小图片切换，大图片显示相应图片*/
	$(document).on("mouseover",".goods-picture-small img",function(){
		  $(".goods-picture-small img").css("border","none");/*初始化所有小图边框*/
		  $(this).css("border","3px solid #EC971F");/*被选中小图边框变橙色*/
			var name = $(this).attr("src");/*小图图片地址*/
			$(".goods-picture-large img").attr("src",name);/*把大图转为相应小图*/
		  $(".magnify-large").css("background","url('" + name + "') center no-repeat");/*放大镜也转为相应小图*/
	 });
});