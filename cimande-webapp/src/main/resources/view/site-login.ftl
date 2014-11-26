<html>
	<head>
		<meta charset="UTF-8">
		<title><@s.text name="page.login.title" /></title>
	</head>
	<body>
		<div class="row">
			<div class="col-md-2"></div>
			<div class="col-md-8">
				<div class="row">
					<div class="form-box" id="login-box">
						<div class="header bg-gray">
							<img src="<@s.url value="/images/flat_meruvian_200px.png" />" />
						</div>
						<div class="col-md-12 bg-gray">
							<@s.form id="loginForm" action="${request.contextPath}${request.servletPath}/do" method="post" theme="bootstrap">
								<div class="body bg-gray row">
									<div class="col-sm-5 bg-gray text-center" style="height: 240px;">
										</br></br>
										<span><@s.text name="label.login.social" /></span>
										</br></br>
										<a href="<@s.url value="/login/social/mervpolis/auth" />">
											<span class="fa-stack fa-3x">
						                        <i class="fa fa-circle fa-stack-2x text-primary"></i>
						                        <img class="fa fa-stack-1x fa-inverse" style="width: 1.96em; height: 1.96em;" src="<@s.url value="/images/midas_96px.png" />"/>
						                    </span>
										</a>
										<a href="<@s.url value="/login/social/facebook/auth" />">
											<span class="fa-stack fa-3x">
						                        <i class="fa fa-circle fa-stack-2x"></i>
						                        <i class="fa fa-facebook fa-stack-1x fa-inverse"></i>
						                    </span>
										</a>
										<a href="<@s.url value="/login/social/google/auth" />">
											<span class="fa-stack fa-3x">
						                        <i class="fa fa-circle fa-stack-2x" style="color : #f56954"></i>
						                        <i class="fa fa-google-plus fa-stack-1x fa-inverse"></i>
						                    </span>
										</a>
									</div>
									<div class="col-sm-2 hidden-xs parent" style="height: 240px;">
										<h4 class="child">OR</h4>
									</div>
									<div class="col-sm-2 visible-xs text-center">
										<h4>OR</h4>
									</div>
									<div class="col-sm-5 bg-gray">
										<div class="form-group">
											<label class="control-label"><@s.text name="label.login.username" /></label>
											<div class="input-group">
												<span class="input-group-addon"><i class="fa fa-user"></i></span>
						                        <input id="username" name="username" type="text" class="form-control">  
<!-- 						                        <span class="input-group-addon">@${sitename!}</span> -->
				                    		</div>
										</div>
										<div class="form-group">
											<label class="control-label"><@s.text name="label.login.password" /></label>
											<div class="input-group">
												<span class="input-group-addon"><i class="fa fa-lock"></i></span>
						                        <input id="password" name="password" type=password class="form-control">  
				                    		</div>
										</div>
										<div class="form-group">
											<input type="checkbox" name="_spring_security_remember_me"/> <@s.text name="label.login.rememberme" />
										</div>
										<button type="submit" class="btn bg-blue btn-block"><@s.text name="button.main.login" /></button>  
										<a href="<@s.url value="/register" />" class="text-center"><@s.text name="label.login.register" /></a>
									</div>
								</div>
								<div class="footer bg-gray">
								</div>
							</@s.form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</body>
</html>