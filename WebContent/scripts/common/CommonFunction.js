/*获取url参数*/
function getUrlParam(name)
{
     var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
     var r = window.location.search.substr(1).match(reg);
     if(r!=null)return  unescape(r[2]); return null;
}
/*转化时间戳为日期格式*/
function formatDate(pDate) {
	var year=1900+pDate.getYear();
	var month=pDate.getMonth()+1;
	var date=pDate.getDate();
	return year+"-"+month+"-"+date;
}