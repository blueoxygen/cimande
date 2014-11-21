<!DOCTYPE html>
<html ng-app="yama">
	<head>
		<meta charset="UTF-8">
		<title>${title!} - <@s.text name="page.main.title" /></title>
		<base href="<@s.url value="/" />" />
		<meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport'>
		<!-- bootstrap 3.0.2 -->
		<link href="<@s.url value="/webjars/bootstrap/3.1.1/css/bootstrap.min.css" />" rel="stylesheet" type="text/css" />
		<!-- font Awesome -->
		<link href="<@s.url value="/webjars/font-awesome/4.1.0/css/font-awesome.min.css" />" rel="stylesheet" type="text/css" />
		<!-- Theme style -->
		<link href="<@s.url value="/styles/adminlte/adminlte.css" />" rel="stylesheet" type="text/css" />

		<link rel="stylesheet" type="text/css" href="<@s.url value="/styles/bootstrap/datepicker.css" />" />
		<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
		<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
		<!--[if lt IE 9]>
		  <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
		  <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
		<![endif]-->
		
		${head!}
	</head>
	<body class="skin-blue">
		<!-- header logo: style can be found in header.less -->
		<#include "nav/top.ftl" />
		<div class="wrapper row-offcanvas row-offcanvas-left">
			<!-- Left side column. contains the logo and sidebar -->
			<#include "nav/side.ftl" />
		
			<!-- Right side column. Contains the navbar and content of the page -->
			<aside class="right-side">
				<!-- Content Header (Page header) -->
				<section class="content-header">
					<h1>
						${page.getProperty('page.header')!}
						<small>${page.getProperty('page.headerDetail')!}</small>
					</h1>
					<ol class="breadcrumb">
						${page.getProperty('page.breadcrumb')!}
						<#--
						<li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>
						<li class="active">Dashboard</li>
						-->
					</ol>
				</section>

				<!-- Main content -->
				<section class="content">
					<!-- top row -->
					<div class="row">
						<div class="col-xs-12 connectedSortable">
						<#if request.getParameter("registrationSuccess")??>
						<div class="row">
							<div class="col-md-12">
								<div class="alert alert-success alert-dismissable">
									<button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
									Registration success! Now complete your profile
								</div>
							</div>
						</div>
						</#if>
						${body}
						
							<div class="modal fade" id="popup-dialog">
							  <div class="modal-dialog">
							    <div class="modal-content">
							      <div class="modal-header">
							        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
							        <h4 class="modal-title" id="popup-header">Modal title</h4>
							      </div>
							      <div class="modal-body">
									<form method="get" class="form-inline" id="popup-search" style="width: 100%">
										<div class="input-group">
											<input type="text" name="q" value="" placeholder="Search..." class="form-control">
									      	<span class="input-group-btn">
												<button value="Search" class="btn btn-default">
													&nbsp;<i class="fa fa-search"></i>&nbsp;
												</button>
											</span>
									    </div>
									</form>
									<div id="popup-data"></div>
							      </div>
							      <div class="modal-footer">
							        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
							        <button type="button" class="btn btn-primary">Save changes</button>
							      </div>
							    </div>
							  </div>
							</div>
						</div><!-- /.col -->
					</div>
					<!-- /.row -->
				</section><!-- /.content -->
				<section class="footer">
					<div class="navbar-static-bottom">
						<div class="container">
							<div class="row">
								<hr>
								<div class="col-xs-9">
									<p class="text-muted"><@s.text name="page.main.footer" /></p>
								</div>
								<div class="col-xs-3">
									<p class="text-muted"><@s.text name="page.main.header" /> v<@s.text name="page.main.version" /></p>
								</div>
							</div>
						</div>
					</div>
				</section>
			</aside><!-- /.right-side -->
		</div><!-- ./wrapper -->
		<!-- add new calendar event modal -->


		<!-- jQuery 2.0.2 -->
		<script src="<@s.url value="/webjars/jquery/1.11.0/jquery.min.js" />"></script>
		<!-- Bootstrap -->
		<script src="<@s.url value="/webjars/bootstrap/3.1.1/js/bootstrap.min.js" />" type="text/javascript"></script>
		<!-- AdminLTE App -->
		<script src="<@s.url value="/scripts/adminlte/app.js" />" type="text/javascript"></script>
		<script type="text/javascript" src="<@s.url value="/scripts/bootstrap/bootstrap-datepicker.js" />"></script>
		<!-- AngularJs -->
		<script src="<@s.url value="/webjars/angularjs/1.2.16/angular.min.js" />" type="text/javascript"></script>
		
		<script src="<@s.url value="/webjars/bootbox/4.3.0/bootbox.js" />" type="text/javascript"></script>
		
		<script src="<@s.url value="/webjars/angular-ui-bootstrap/0.11.2/ui-bootstrap-tpls.min.js" />" type="text/javascript"></script>
		
		<script src="<@s.url value="/scripts/angular/ngBootbox.min.js" />" type="text/javascript"></script>
		
		<script src="<@s.url value="/scripts/yama/app-main.js" />" type="text/javascript"></script>
		<script type="text/javascript">
		$(function(){
			$('#date').datepicker({
				format : 'dd-mm-yyyy',
				endDate : new Date(),
				autoclose : true
			});
		});
		</script>
		${page.getProperty('page.script')!}
	</body>
</html>