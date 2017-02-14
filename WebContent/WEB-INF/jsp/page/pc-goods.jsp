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
    				<div class="goodsManage-nav">
	    				<ul class="nav nav-pills">
						    <li class="active"><a data-toggle="pill" href="#home">上架中<!-- <span class="badge">0</span> --></a></li>
						    <li><a data-toggle="pill" href="#menu1">已交易</a></li>
						    <li><a data-toggle="pill" href="#menu2">已下架</a></li>
						</ul>
					</div>
					<div class="tab-content goodsManage-content">
					    <div id="home" class="tab-pane fade in active">
					      <div class="content-empty">
					      	<img class="icon-empty" src="/Thesis/images/empty-goods.svg" alt="图片" />
					      	<p class="p-empty">您的货架上空着呢</p>
					      	<div class="a-publish"><a href="#">+发布商品</a></div>      	
					      </div>
					      <div id="accordion-1" class="content-nonempty"></div>
					    </div>  
					    <div id="menu1" class="tab-pane fade">
					      <div class="content-empty">
					      	<img class="icon-empty" src="/Thesis/images/empty-traded.svg" alt="图片" />
					      	<p class="p-empty">没有已交易的商品呢</p>
					      </div>
					      <div id="accordion-2" class="content-nonempty"></div>
					    </div>
					    <div id="menu2" class="tab-pane fade">
					      <div class="content-empty">
					      	<img class="icon-empty" src="/Thesis/images/empty-expired.svg" alt="图片" />
					      	<p class="p-empty">没有下架的商品呢</p>
					      </div>
					      <div id="accordion-3" class="content-nonempty"></div>
					    </div>
				    </div>  				
    			</div>
    		</div>    		
    	</div>   	
    </div>
    <!--
    	模态框（Modal）
    	下架提示
    -->
		<div id="modal-offsale" class="modal fade modal-tips" role="dialog" data-show="false">
				<div class="modal-dialog">						
						<!-- Modal content-->
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal">&times;</button>
						  <h4 class="modal-title">下架提示</h4>
						</div>
						<div class="modal-body">
							<h4>该商品下架后可在“已下架”页面找回，确定要下架吗？</h4>
						</div>
						<div class="modal-footer">
							<button id="offsale-confirm" type="button" class="btn btn-default" data-id="">确定</button>
							<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
						</div>
				  </div>						
		    </div>
		</div>	    				
	    				<!--
                  模态框（Modal）
    	编辑商品
    -->
    <div id="modal-edit" class="modal fade" role="dialog" data-show="false">
		  <div class="modal-dialog">
		    <!-- Modal content-->
		    <div class="modal-content">
		      <div class="modal-header">
		        <button type="button" class="close" data-dismiss="modal" >&times;</button>
		        <h4 class="modal-title">编辑商品</h4>
		      </div>
		      <div class="modal-body">
		        <form id="editForm" class="form-horizontal required-validate">
		        			  <div class="form-group">
							    <label for="goods-title" class="control-label col-sm-2">商品标题</label>
							    <div class="col-sm-8">
							    	<input type="text" class="form-control" id="goods-title" name="item_title" placeholder="请输入商品标题" autocomplete="off" autofocus>
							  	</div>
							  </div>
							  <div class="form-group">	
							  	<label for="goods-price" class="control-label col-sm-2">商品价格</label>
								  <div class="col-sm-8">
								  	<input type="text" class="form-control" id="goods-price" name="price" placeholder="请输入合适的价格" maxlength="8" autocomplete="off">
							  	</div>
							  </div>
							  <div class="form-group">
							    <label for="goods-trans-address" class="control-label col-sm-2">交易地点</label>
							    <div class="col-sm-8">
							    	<input type="text" class="form-control" id="goods-trans-address" name="location" placeholder="请输入交易地点" autocomplete="off">
							  	</div>
							  </div>	
							  <div class="form-group">
							    <label for="goods-category" class="control-label col-sm-2">商品分类</label>
							    <div class="col-sm-8">
							    	<select class="form-control" id="goods-category" name="categoryId" autocomplete="off">
							    													
									</select><br>
									</div>	
							  </div>
							  <div class="form-group">
							    <label for="owner-tel" class="control-label col-sm-2">手机号码</label>
							    <div class="col-sm-8">
							    	<input type="tel" class="form-control" id="owner-tel" name="telephoneNumber" placeholder="请留下手机号码" maxlength="11" autocomplete="off">
							  	</div>
							  </div>
							  <div class="form-group">
							    <label for="owner-social-account" class="control-label col-sm-2">社交账号</label>
							    <div class="col-sm-8">
							    	<input type="text" class="form-control" id="owner-social-account" name="socialAccount" placeholder="输入QQ、微信等联系方式" maxlength="30" autocomplete="off">
							  	</div>
							  </div>
							  <div class="form-group">
							    <label for="goods-description" class="control-label col-sm-2">商品详情</label>
							    <div class="col-sm-8">
								    <textarea class="form-control" id="goods-description" name="description" rows="4" placeholder="介绍一下您的宝贝吧（最多200字）" maxlength="200" autocomplete="off"></textarea>
								    <span id="words-num">已输入0个字，还能输入200个字</span>
								</div>
							  </div>
							  <input id="goods-id" type="hidden" name="itemId" />
							  <div class="modal-footer">
						      	<button id="edit-confirm" type="button" class="btn btn-default" data-id="">保存</button>
						        <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
						      </div>
		        </form>
		      </div>
		      <!-- <div class="modal-footer">
		      	<button id="edit-confirm" type="button" class="btn btn-default" data-id="">保存</button>
		        <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
		      </div> -->
		    </div>
		  </div>
		</div>
		<!--
    	模态框（Modal）
    	删除提示
    -->
		<div id="modal-delete" class="modal fade modal-tips" role="dialog" data-show="false">
				<div class="modal-dialog">						
						<!-- Modal content-->
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal">&times;</button>
						  <h4 class="modal-title">删除提示</h4>
						</div>
						<div class="modal-body">
							<h4>该商品删除后无法找回，确定要删除吗？</h4>
						</div>
						<div class="modal-footer">
							<button id="delete-confirm" type="button" class="btn btn-default" data-id="">确定</button>
							<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
						</div>
				  </div>						
		    </div>
		</div>
	<script type="text/javascript" src="/Thesis/scripts/common/CommonFunction.js" ></script>	
   <!--  <script type="text/javascript" src="/Thesis/scripts/common/personal-center.js"></script> -->
    <script type="text/javascript" src="/Thesis/scripts/common/pc-goods.js"></script>
	</body>
</html>