function htmlModule(item,stmt,seq){
	var html = "";
	var dropClass = "dropdown";
	var isDropdown = "icon-dropdown.svg";
	var collapse_stmt = "";
	var picUrl = "default.jpg";
	var ownerQQ = item.socialAccount; 
	var goodsDesc = item.item_description;
	var lessTime = Math.ceil(90-((new Date().getTime()-item.public_date)/1000/3600/24));	
	var btnHtml = '<div class="m-btn"><a class="btn-offsale" href="#modal-offsale" data-toggle="modal" data-id="'+item.id+'"><img src="/Thesis/images/goods-offSale.svg" alt="icon" height="30" width="30"/>下架</a></div>'+
				  '<div class="m-btn"><a class="btn-edit" href="#modal-edit" data-toggle="modal" data-id="'+item.id+'"><img src="/Thesis/images/goods-edit.svg" alt="icon" height="30" width="30"/>编辑</a>';
	var msgHtml = "距离自动下架还有"+lessTime+"天";
	if(seq==1){
		dropClass = "";
		isDropdown = "icon-dropup.svg"
		collapse_stmt = "in";
	}
	if(item.pictures!=""){
		picUrl = item.pictures[0].pictureUrl;
	}
	if(ownerQQ==""){
		ownerQQ = "null";
	}
	if(goodsDesc==""||goodsDesc==null){
		goodsDesc = "卖家什么也没说。";
	}
	if(stmt==2){
		msgHtml = "于"+formatDate(new Date(item.off_date))+"完成交易";
		btnHtml = '<div class="m-btn"><a class="btn-delete" href="#modal-delete" data-toggle="modal" data-id="'+item.id+'"><img src="/Thesis/images/goods-delete.svg" alt="icon" height="30" width="30"/>删除</a>';
	}else if(stmt==3){
		msgHtml = "于"+formatDate(new Date(item.off_date))+"下架";
		btnHtml = '<div class="m-btn"><a class="btn-resale" href="#" data-id="'+item.id+'"><img src="/Thesis/images/goods-upSale.svg" alt="icon" height="30" width="30"/>上架</a></div>'+
		  		  '<div class="m-btn"><a class="btn-delete" href="#modal-delete" data-toggle="modal" data-id="'+item.id+'"><img src="/Thesis/images/goods-delete.svg" alt="icon" height="30" width="30"/>删除</a>';
	}
	html += '<div class="panel panel-success">';
	html += '<div class="panel-heading">'+
			'<a class="a-collapse" href="#content-'+stmt+'-'+seq+'" data-toggle="collapse" data-parent="#accordion-'+stmt+'">'+
			'<h3 class="panel-title"><span class="badge">'+item.category.categoryName+'</span>'+item.item_title+
			'<div class="icon-drop f-right"><img class="'+dropClass+'" src="/Thesis/images/'+isDropdown+'" alt="icon" height="15px" width="15px"/>'+
			'</div></h3></a>'+'</div>';
	html += '<div id="content-'+stmt+'-'+seq+'" class="panel-body collapse '+collapse_stmt+'">'+
			'<div class="content-img f-left"><img src="/Thesis/pictureServicer/goods/'+picUrl+'" alt="封面图片" height="100" width="100"/></div>'+
			'<div class="content-message f-left">'+
			'<div class="content-description"><div class="description-icon f-left"><img src="/Thesis/images/description.svg" title="商品描述" alt="图标" height="50" width="50"/></div>'+
    		'<div class="description-words f-left"><span>'+goodsDesc+'</span></div>'+
    		'</div>'+
			'<div class="content-items">'+
    		'<span title="价格" class="content-item"><img src="/Thesis/images/price.svg"/ >'+item.price+'</span>'+
	    	'<span title="交易地点" class="content-item"><img src="/Thesis/images/address.svg"/>'+item.location+'</span>'+
	    	'<span title="手机号码" class="content-item"><img src="/Thesis/images/telephone.svg"/>'+item.telephoneNumber+'</span>'+
	    	'<span title="QQ" class="content-item"><img src="/Thesis/images/QQ.svg"/>'+ownerQQ+'</span>'+
	    	'<span title="关注数" class="content-item"><img src="/Thesis/images/focus.svg"/>'+item.focus_number+'</span>'+
	    	'<span title="上架日期" class="content-item"><img src="/Thesis/images/date.svg"/>'+formatDate(new Date(item.public_date))+'</span>'+
	    	'</div></div>'+
			'<div class="content-btn f-right">'+btnHtml+'</div></div>'+ 
	    	'<div class="content-tips f-left"><span>'+msgHtml+'</span></div>'+
			'</div>';
	html += '</div>';
	return html;
}
$(document).on("ready",function(){
		//加载商品
    	$.post("/Thesis/pc/goods/manage/load.do",function(data){
    			var notTraded = data.notTraded;
    			var traded = data.traded;
    			var offsale = data.offsale;
    			var html1 = "";
    			var html2 = "";
    			var html3 = "";
    			//上架中 
    			if(notTraded==""){
    				$("#home .content-nonempty").hide();
    				$("#home .content-empty").show();
    			}else{
    				for (var i = 0; i < notTraded.length; i++) {
						html1 += htmlModule(notTraded[i], "1", i+1);
					}
    				$("#home .content-empty").hide();
    				$("#home .content-nonempty").html(html1);
    				$("#home .content-nonempty").show();
    				
    			}
    			//已交易 
    			if(traded==""){
    				$("#menu1 .content-nonempty").hide();
    				$("#menu1 .content-empty").show();
    			}else{
    				for (var i = 0; i < traded.length; i++) {
						html2 += htmlModule(traded[i], "2", i+1);
					}
    				$("#menu1 .content-empty").hide();
    				$("#menu1 .content-nonempty").html(html2);
    				$("#menu1 .content-nonempty").show();
    			}
    			//已下架 
    			if(offsale==""){
    				$("#menu2 .content-nonempty").hide();
    				$("#menu2 .content-empty").show();
    			}else{
    				for (var i = 0; i < offsale.length; i++) {
						html3 += htmlModule(offsale[i], "3", i+1);
					}
    				$("#menu2 .content-empty").hide();
    				$("#menu2 .content-nonempty").html(html3);
    				$("#menu2 .content-nonempty").show();
    			}
    	});
    	//模态框设置“ESC”键可用
    	$('.modal').modal({
    		keyboard: true
    	});
    	//下拉文本设置
    	$(document).on("click",".a-collapse",function(){
    		var imgEle = $(this).find("img");
    		var isDropdown = imgEle.hasClass("dropdown");
    		$(".a-collapse img").attr("src","/Thesis/images/icon-dropdown.svg").addClass("dropdown");
    		if(isDropdown){
    			imgEle.attr("src","/Thesis/images/icon-dropup.svg");
    			imgEle.removeClass("dropdown");
    		}else{
    			imgEle.attr("src","/Thesis/images/icon-dropdown.svg");
    			imgEle.addClass("dropdown");
    		}
    		
    	});
    	//下架商品
    	$(document).on("click",".btn-offsale",function(){
    		var itemId = $(this).attr("data-id");
    		$("#modal-offsale #offsale-confirm").attr("data-id",itemId);
    	});
    	$(document).on("click","#offsale-confirm",function(){
    		var itemId = $(this).attr("data-id");
    		$.post("/Thesis/pc/goods/manage/offsale.do",{"itemId":itemId},function(data){
    			alert(data.msg);
    			location.reload();
    		});
    	});
    	//编辑商品
    	$(document).on("click",".btn-edit",function(){
    		var itemId = $(this).attr("data-id");
    		/*$("#modal-edit #edit-confirm").val(itemId);*/
    		$.post("/Thesis/pc/goods/manage/edit/load.do",{"itemId":itemId},function(data){
    			var categorys = data.categorys;
    			var item = data.item;
    			var cate_html = "";
    			for (var i = 0; i < categorys.length; i++) {
    				var isSelected = "";
    				if(item.category.categoryName == categorys[i].categoryName){
    					isSelected = 'selected="selected"';
    				}
    				cate_html += '<option value="'+categorys[i].id+'" '+isSelected+'>'+categorys[i].categoryName+'</option>';
				}
    			$("#modal-edit #goods-category").html(cate_html);
    			$("#modal-edit #goods-title").val(item.item_title);
    			$("#modal-edit #goods-price").val(item.price);
    			$("#modal-edit #goods-trans-address").val(item.location);
    			$("#modal-edit #owner-tel").val(item.telephoneNumber);
    			$("#modal-edit #owner-social-account").val(item.socialAccount);
    			$("#modal-edit #goods-description").val(item.item_description);
    			$("#modal-edit #goods-id").val(item.id);
    			var inputNums = $("#modal-edit #goods-description").val().length;
        		var leaveNums = 200-inputNums;
        		var html = "已输入"+inputNums+"个字，还能输入"+leaveNums+"个字";
        		$("#words-num").html(html);
    		});
    	});
    	$(document).on("click","#edit-confirm",function(){
    		$.ajax({
    				'url':"/Thesis/pc/goods/manage/edit/save.do",
    				'method':"POST",
    				'dataType':"JSON",
    				'data':$("#editForm").serialize(),
    				'success':function(data){
    					location.reload();
    				}
    				   					
    		})
    	});
    	//重新上架
    	$(document).on("click",".btn-resale",function(){
    		var itemId = $(this).attr("data-id");
    		$.post("/Thesis/pc/goods/manage/resale.do",{"itemId":itemId},function(data){
    			alert(data.msg);
    			location.reload();
    		});
    	});
    	//删除商品
    	$(document).on("click",".btn-delete",function(){
    		var itemId = $(this).attr("data-id");
    		$("#modal-delete #delete-confirm").attr("data-id",itemId);
    	});
    	$(document).on("click","#delete-confirm",function(){
    		var itemId = $(this).attr("data-id");
    		$.post("/Thesis/pc/goods/manage/delete.do",{"itemId":itemId},function(data){
    			alert(data.msg);
    			location.reload();
    		});
    	});
    	/*文本框输入字数计数*/
        $("#goods-description").on("keyup",function(){
        		var inputNums = $(this).val().length;
        		var leaveNums = 200-inputNums;
        		var html = "已输入"+inputNums+"个字，还能输入"+leaveNums+"个字";
        		$("#words-num").html(html);
        });
        /*表单验证*/
        $("form").bootstrapValidator({
        			//通用错误提示
    				message:'输入有误，请重新输入！',
    				feedbackIcons:{
    					valid:'glyphicon glyphicon-ok',
    					invalid:'glyphicon glyphicon-remove',
    					validating:'glyphicon glyphicon-refresh'
    				},
    				fields:{
    					//fields start
    					item_title:{
    						validators:{
    							notEmpty:{
    								message:'标题不能为空!（好的标题能吸引更多的目光哦）'									
    							},
    							stringLength: {
    		                         min: 1,
    		                         max: 20,
    		                         message: '最多为20个字!'
    							}
    						}
    					},
    					price:{
    						validators:{
    							notEmpty:{
    								message:'价格不能为空!（真的要免费吗？）'									
    							},
    							'regexp':{
    								'regexp': /^((0|[1-9]{1}\d{0,5}){1}(\.\d{1})?)$/,
    								message:'请输入数字，小数点保留一位!支持最大金额为999999.9元'
    							},
    						}
    					},
    					location:{
    						validators:{
    							notEmpty:{
    								message:'交易地点不能为空!'									
    							},
    							stringLength: {
                         min: 1,
                         max: 15,
                         message: '最多为15个字!'
                }
    						}
    					},
    					categoryId:{
    						validators:{
    							//下拉框第一个option的value=""
    							notEmpty:{
    								message:"请为商品选择一个分类!"
    							}
    						}
    					},
    					telephoneNumber:{
    						//container:'#msg-tel',
    						validators:{
    							notEmpty:{
    								message:'手机号码不能为空!（留下手机号码方便买家联系）'									
    							},
    							regexp:{
    								regexp:/^1\d{10}$/,
    								message:'请输入正确的手机号码!'
    							}
    						} 
    					},
    					socialAccount:{
    						//container:'#msg-sa',
    						validators:{
    							stringLength: {
    			                     min: 1,
    			                     max: 30,
    			                     message: '最多为30个字'
    							}
    						}
    					},
    					userName:{
    						//container:'#msg-username',
    						validators:{
    							notEmpty:{
    								message:'昵称不能为空'									
    							},
    							stringLength: {
    			                     min: 1,
    			                     max: 15,
    			                     message: '最多为15个字'
    							}
    						}
    					}
    					// fields end
    					
    				}
    			});
    });