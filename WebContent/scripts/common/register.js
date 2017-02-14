$(document).on('ready', function() {
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
						 userAccount:{
							validators:{
								notEmpty:{
									message:'账号不能为空'									
								},
								stringLength: {
			                         min: 6,
			                         max: 16,
			                         message: '帐号须由6-16个字符组成'
								},
								regexp:{
									regexp:/^[0-9a-zA-Z_]+$/,
									message:"请用字母、数字或下划线命名"
								},
			                    remote: {
			                        type: 'POST',
			                        url: '/Thesis/user/isExisted.do',
			                        message: '账号已存在',
			                        delay: 1000
			                    },
							}
						},
						password:{
							validators:{
								notEmpty:{
									message:'密码不能为空'									
								},
								stringLength: {
			                         min: 6,
			                         max: 16,
			                         message: '密码须由6-16个字符组成，区分大小写'
								},
								different: {
			                        field: 'userAccount',
			                        message: '密码与注册账号不能相同'
			                    }
							}
						},
						confirmPassword:{
							validators:{
								notEmpty:{
									message:'请确认密码'									
								},
								identical: {
			                        field: 'password',
			                        message: '密码不一致'
			                    }
							}
						},
						telephoneNumber:{
							validators:{
								notEmpty:{
									message:'手机号码不能为空（留下手机号码方便买家联系）'									
								},
								regexp:{
									regexp:/^1\d{0,10}$/,
									message:'请输入正确的手机号码'
								}
							} 
						},
					}
				});
});