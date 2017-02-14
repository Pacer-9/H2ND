$(document).on("ready",function(){	
	/*更新用户信息*/
	$(document).on("click","#btn-updateInfo",function(){
		$.ajax({
			'url':"/Thesis/pc/user/info/update.do",
			'dataType':"JSON",
			'method':"POST",
			'data':$("#infoForm").serialize(),
			'success':function(result){
				$(".l-userInfo .nickname").text(result.nickname);
				$(".msg-result").text(result.msg);
			}
		});
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
						container:'#msg-tel',
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
						container:'#msg-sa',
						validators:{
							stringLength: {
			                     min: 1,
			                     max: 30,
			                     message: '最多为30个字'
							}
						}
					},
					userName:{
						container:'#msg-username',
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
