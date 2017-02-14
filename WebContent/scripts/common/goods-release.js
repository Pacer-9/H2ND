$(document).on('ready', function() {
		/*加载当前用户的部分信息和下拉框加载商品类型*/
		$.getJSON("/Thesis/release/init.do",function(data){
			var tel = data.tel;
			var s_account = data.s_account;
			var categorys = data.categorys;
			var cate_html = "";
			for (var i = 1; i < categorys.length-1; i++) {
				cate_html += "<option value='"+categorys[i].id+"'>"+categorys[i].categoryName+"</option>";
			}
			cate_html += "<option value='"+categorys[0].id+"'>"+categorys[0].categoryName+"</option>";
			$("#goods-category").append(cate_html);
			$("#owner-tel").val(tel);
			$("#owner-social-account").val(s_account);
		});
 		/*上传图片设置*/
        $("#picture-upload").fileinput({
            language: 'zh',
            //uploadUrl: '/Thesis/picture/upload.do',
            //showClose:false,
            showUpload: false,
            allowedPreviewTypes : [ 'image' ],
            allowedFileExtensions: ['jpg', 'jpeg', 'png'],
            maxFileSize:1024,
            maxFileCount:3
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
							validators:{
								notEmpty:{
									message:'手机号码不能为空!（留下手机号码方便买家联系）'									
								},
								regexp:{
									regexp:/^1\d{0,10}$/,
									message:'请输入正确的手机号码!'
								}
							} 
						},
						socialAccount:{
							validators:{
								stringLength: {
                         min: 1,
                         max: 30,
                         message: '最多为30个字!'
                }
							}
						}
					}
				});
});