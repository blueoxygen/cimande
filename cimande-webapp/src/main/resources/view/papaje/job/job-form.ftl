<html>
	<head>
		<title><@s.text name="page.backend.job.title" /></title>
	</head>
	<body>
		<content tag="header"><@s.text name="page.backend.job.header" /></content>
		<content tag="script">
		<script type="text/javascript">
		</script>
		</content>

		<@s.actionerror theme="bootstrap"/>
		<@s.actionmessage theme="bootstrap"/>
		<div class="row">
			<div class="col-md-9">
				<div class="box box-primary">
					<div class="box-header">
						<h3 class="box-title"><@s.text name="label.backend.job.title" /></h3>
					</div>
					
					<div class="box-body">
						<@s.form theme="bootstrap">
							<@s.hidden key="label.backend.job.id" name="job.id" readonly="true" />
							<@s.hidden key="label.backend.job.id" name="job.company.id" readonly="true" />
							<@s.textfield key="label.backend.job.code" name="job.code" />
							<@s.textfield key="label.backend.job.name" name="job.name" />
							<@s.textfield key="label.backend.job.function" name="job.function" />
							<@s.textfield key="label.backend.job.sallary" name="job.sallary" />
							<@s.textarea key="label.backend.job.description" name="job.description" />
							<@s.textarea key="label.backend.job.requirement" name="job.requirement" />
							<@s.textarea key="label.backend.job.personalspec" name="job.personalSpec" />
							<@s.textarea key="label.backend.job.notes" name="job.notes" />
							
							<@s.submit cssClass="btn btn-primary col-md-3" value="%{getText('button.main.save')}" />
						</@s.form>
					</div>
				</div>
			</div>
			<div class="col-md-3">
				<#if job.logInformation.activeFlag == 0>
				<div class="box box-success">
					<div class="box-body">
						<@s.form theme="bootstrap" action="${request.contextPath}${request.servletPath}/status">
							<@s.hidden name="id" value="%{job.id}" />
							<@s.hidden name="status" value="1" />
							<@s.submit cssClass="btn btn-success btn-lg col-md-12" key="label.backend.job.enable" />
						</@s.form>
					</div>
				</div>
				<#elseif job.logInformation.activeFlag == 1>
				<div class="box box-danger">
					<div class="box-body">
						<@s.form theme="bootstrap" action="${request.contextPath}${request.servletPath}/status">
							<@s.hidden name="id" value="%{job.id}" />
							<@s.hidden name="status" value="0" />
							<@s.submit cssClass="btn btn-danger btn-lg col-md-12" key="label.backend.job.disable" />
						</@s.form>
					</div>
				</div>
				</#if>
			</div>
		</div>
	</body>
</html>