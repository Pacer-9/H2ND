$(document).ready(function(){

/*小图片切换，大图片显示相应图片*/
$(".goods-picture-small img").mouseover(function(){
	  $(".goods-picture-small img").css("border","none");/*初始化所有小图边框*/
	  $(this).css("border","3px solid #EC971F");/*被选中小图边框变橙色*/
		var name = $(this).attr("src");/*小图图片地址*/
		$(".goods-picture-large img").attr("src",name);/*把大图转为相应小图*/
	  $(".magnify-large").css("background","url('" + name + "') center no-repeat");/*放大镜也转为相应小图*/
  });

});