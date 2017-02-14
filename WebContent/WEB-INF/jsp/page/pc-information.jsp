<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>个人中心--有鱼</title>
		<link rel="shortcut icon" href="/images/sharing.ico" type="image/x-icon">
        <link rel="stylesheet" href="/Thesis/styles/bootstrap.min.css" />
        <link rel="stylesheet" href="/Thesis/styles/plugins/buttons.css" />
        <link rel="stylesheet" href="/Thesis/styles/plugins/fileinput.css" />
        <link rel="stylesheet" href="/Thesis/styles/plugins/bootstrapValidator.min.css" />
        <link rel="stylesheet" href="/Thesis/styles/common/pesonal-center.css" />
        <script type="text/javascript" src="/Thesis/scripts/jquery-2.2.3.min.js" ></script>
        <script type="text/javascript" src="/Thesis/scripts/bootstrap.min.js" ></script>
        <script type="text/javascript" src="/Thesis/scripts/plugins/fileinput.min.js" ></script>
        <script type="text/javascript" src="/Thesis/scripts/plugins/fileinput-locale-zh.js" ></script>
        <script type="text/javascript" src="/Thesis/scripts/plugins/bootstrapValidator.min.js"></script>
	</head>
	<body>
		<div class="container m-body">
    	<div class="row">
    		<!--左部分-->
    		<div class="col-sm-2">
    			<div class="panel-left">
    				<div class="l-userInfo">
    					<div class="avatar">
    						<a href="#"><img src="/Thesis/pictureServicer/avatars/${sessionScope.currentUser.avatar}" class="img-circle img-thumbnail" alt="头像" width="100" height="100"></a>
								<div class="icon-camera"><img src="/Thesis/images/camera.svg"/></div>
    					</div>
    					<p class="nickname text-center">${sessionScope.currentUser.userName}</p>
						<p class="text-center">未认证</p>
    				</div>
    				<div class="menu">		
							<a href="/Thesis/pc/user/info.do" class="menu-item active">个人信息</a>
							<a href="/Thesis/pc/goods/manage.do" class="menu-item ">商品管理</a>
							<a href="#" class="menu-item ">求购管理</a>
							<a href="#" class="menu-item ">收藏夹</a>
							<a href="#" class="menu-item ">消息中心</a>
							<a href="#" class="menu-item ">认证</a>
    				</div>
    			</div>
    		</div>	
    		<!--右部分-->
    		<div class="col-sm-10">
    			<div class="panel-right">
    				<div class="r-userInfo">
	    					<form id="infoForm" class="userInfo-form required-validate">
	    						<div class="item">
	    							<label class="r-label f-left">账号</label>
	    							<div class="content f-left">${sessionScope.currentUser.userAccount}</div>
	    						</div>
	    						<div class="item">
	    							<label class="r-label f-left">昵称</label>
	    							<div class="form-group content f-left">
									    <input class="form-control r-input" name="userName" value="${sessionScope.currentUser.userName}" autocomplete="off" maxlength="20" placeholder="昵称"/>
									</div>
									<div id="msg-username" class="validate-msg f-left"></div>
	    						</div>
	    						<div class="item">
	    							<label class="r-label f-left">手机号码</label>
	    							<div class="form-group content f-left">
									    <input class="form-control r-input" name="telephoneNumber" value="${sessionScope.currentUser.telephoneNumber}" autocomplete="off" maxlength="11" placeholder="手机号码（必填）"/>
									</div>
									<div id="msg-tel" class="validate-msg f-left"></div>
	    						</div>
	    						<div class="item">
	    							<label class="r-label f-left">QQ</label>
	    							<div class="form-group content f-left">
									    <input class="form-control r-input" name="socialAccount" value="${sessionScope.currentUser.socialAccount}" autocomplete="off" maxlength="12" placeholder="QQ（可选）"/>
									</div>
									<div id="msg-sa" class="validate-msg f-left"></div>
	    						</div>
	    						<div class="item profile">
	    							<label class="r-label f-left">个人简介</label>
	    							<div class="form-group content f-left">
									    <textarea class="form-control input-lg r-textarea" id="goods-description" name="profile" rows="3" cols="60" maxlength="200" autocomplete="off"> ${sessionScope.currentUser.profile}</textarea>
									    <!--<span id="words-num">已输入0个字，还能输入200个字</span>-->
									</div>
	    						</div>
	    						<div class="item">
	    							<label class="r-label f-left"></label>
	    							<div class="content f-left"><button id="btn-updateInfo" class="button button-glow button-rounded button-raised button-action button-large">保存</button></div>
	    							<div class="msg-result f-left"></div>
	    						</div>
	    					</form>
	    				</div>   				
    			</div>
    		</div>    		
    	</div>   	
    </div>
    
    <script type="text/javascript" src="/Thesis/scripts/common/pc-info.js"></script>
	</body>
</html>