<html>
	<head>
		<title><@s.text name="page.backend.company.title" /></title>
	</head>
	<body>
		<content tag="header"><@s.text name="page.backend.company.header" /></content>
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
						<h3 class="box-title"><@s.text name="label.backend.company.title" /></h3>
					</div>
					
					<div class="box-body">
						<@s.form theme="bootstrap">
							<@s.hidden key="label.backend.company.id" name="company.id" readonly="true" />
							<@s.textfield key="label.backend.company.name" name="company.name" />
							<@s.textarea key="label.backend.company.description" name="company.description" />
							<@s.textfield key="label.backend.company.address.street1" name="company.address.street1" />
							<@s.textfield key="label.backend.company.address.street2" name="company.address.street2" />
							<@s.textfield key="label.backend.company.address.city" name="company.address.city" />
							<@s.textfield key="label.backend.company.address.state" name="company.address.state" />
							<@s.textfield key="label.backend.company.address.zip" name="company.address.zip" />
							<@s.textfield key="label.backend.company.phone1" name="company.phone1" />
							<@s.textfield key="label.backend.company.website" name="company.website" />
							<@s.textfield key="label.backend.company.email" name="company.email" />
							
							<@s.submit cssClass="btn btn-primary col-md-3" value="%{getText('button.main.save')}" />
						</@s.form>
					</div>
				</div>
			</div>
			<div class="col-md-3">
			</div>
		</div>
	</body>
</html>