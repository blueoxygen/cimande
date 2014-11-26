<html>
	<head>
		<title><@s.text name="page.backend.category.title" /></title>
	</head>
	<body>
		<content tag="header"><@s.text name="page.backend.category.header" /></content>
		
		<@s.actionerror theme="bootstrap"/>
		<@s.actionmessage theme="bootstrap"/>
		<div class="row">
			<div class="col-md-9">
				<div class="box box-primary">
					<div class="box-header">
						<h3 class="box-title"><@s.text name="label.backend.category.title" /></h3>
					</div>
					
					<div class="box-body">
						<@s.form theme="bootstrap">
							<@s.hidden key="label.backend.category.id" name="category.id" readonly="true" />
							<@s.textfield key="label.backend.category.name" name="category.name" />
							<@s.textfield key="label.backend.category.description" name="category.description" />
							<@s.submit cssClass="btn btn-primary col-md-3" value="%{getText('button.main.save')}" />
						</@s.form>
					</div>
				</div>
			</div>
			<div class="col-md-3">
				<#if category.logInformation.activeFlag == 0>
				<div class="box box-success">
					<div class="box-body">
						<@s.form theme="bootstrap" action="${request.contextPath}${request.servletPath}/status">
							<@s.hidden name="id" value="%{category.id}" />
							<@s.hidden name="status" value="1" />
							<@s.submit cssClass="btn btn-success btn-lg col-md-12" key="label.backend.category.enable" />
						</@s.form>
					</div>
				</div>
				<#elseif category.logInformation.activeFlag == 1>
				<div class="box box-danger">
					<div class="box-body">
						<@s.form theme="bootstrap" action="${request.contextPath}${request.servletPath}/status">
							<@s.hidden name="id" value="%{category.id}" />
							<@s.hidden name="status" value="0" />
							<@s.submit cssClass="btn btn-danger btn-lg col-md-12" key="label.backend.category.disable" />
						</@s.form>
					</div>
				</div>
				</#if>
			</div>
		</div>
	</body>
</html>