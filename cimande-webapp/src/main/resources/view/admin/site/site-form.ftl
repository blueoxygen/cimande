<html>
	<head>
		<title><@s.text name="page.admin.site.title" /></title>
	</head>
	<body>
		<content tag="header"><@s.text name="page.admin.site.header" /></content>
		<content tag="script">
		<script type="text/javascript">
		</script>
		</content>

		<@s.actionerror theme="bootstrap"/>
		<@s.actionmessage theme="bootstrap"/>
		<div class="row">
			<div class="col-md-8">
				<div class="box box-primary">
					<div class="box-header">
						<h3 class="box-title"><@s.text name="label.admin.site.title" /></h3>
					</div>
					
					<div class="box-body">
						<@s.form theme="bootstrap">
							<@s.hidden key="label.admin.site.id" name="site.id" readonly="true" />
							<@s.textfield key="label.admin.site.name" name="site.name" />
							<@s.textfield key="label.admin.site.description" name="site.description" />
							<@s.textfield key="label.admin.site.virtualhost" name="site.virtualHost" />
							
							<@s.submit cssClass="btn btn-primary col-md-3" value="%{getText('button.main.save')}" />
						</@s.form>
					</div>
				</div>
			</div>
			<div class="col-md-4">
				<div class="box box-success">
					<div class="box-header">
						<h3 class="box-title"><@s.text name="label.admin.site.logo" /></h3>
					</div>
					
					<div class="box-body">
						<@s.form theme="bootstrap" enctype="multipart/form-data">
							<div class="row">
								<img src="<@s.url value="/admin/sites/${site.name!}/logo" />" class="img-thumbnail col-md-6 col-md-offset-3" alt="Site Logo">
							</div>
							<@s.hidden name="site.id" />
							<@s.hidden name="edit" value="logo" />
							<@s.file key="button.main.browse" name="logo" />
							
							<@s.submit cssClass="btn btn-primary col-md-3" value="%{getText('button.main.upload')}" />
						</@s.form>
					</div>
				</div>
				<#if site.logInformation.activeFlag == 0>
				<div class="box box-success">
					<div class="box-body">
						<@s.form theme="bootstrap" action="${request.contextPath}${request.servletPath}/status">
							<@s.hidden name="id" value="%{site.id}" />
							<@s.hidden name="status" value="1" />
							<@s.submit cssClass="btn btn-success btn-lg col-md-12" key="label.admin.site.enable" />
						</@s.form>
					</div>
				</div>
				<#elseif site.logInformation.activeFlag == 1>
				<div class="box box-danger">
					<div class="box-body">
						<@s.form theme="bootstrap" action="${request.contextPath}${request.servletPath}/status">
							<@s.hidden name="id" value="%{site.id}" />
							<@s.hidden name="status" value="0" />
							<@s.submit cssClass="btn btn-danger btn-lg col-md-12" key="label.admin.site.disable" />
						</@s.form>
					</div>
				</div>
				</#if>
			</div>
		</div>
	</body>
</html>