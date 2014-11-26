<html>
	<head>
		<title><@s.text name="page.admin.modulefunction.title" /></title>
	</head>
	<body>
		<content tag="header"><@s.text name="page.admin.modulefunction.header" /></content>
		<content tag="script">
			<script src="<@s.url value="/scripts/yama/cimande-popup.js" />" type="text/javascript"></script>
		</content>
		
		<@s.actionerror theme="bootstrap"/>
		<@s.actionmessage theme="bootstrap"/>
		<div class="row">
			<div class="col-md-9">
				<div class="box box-primary">
					<div class="box-header">
						<h3 class="box-title"><@s.text name="label.admin.modulefunction.title" /></h3>
					</div>
					
					<div class="box-body">
						<@s.form theme="bootstrap">
							<@s.hidden key="label.admin.modulefunction.id" name="moduleFunction.id" readonly="true" />
							<@s.textfield key="label.admin.modulefunction.name" name="moduleFunction.name" />
							<@s.textfield key="label.admin.modulefunction.description" name="moduleFunction.description" />
							<@s.textfield key="label.admin.modulefunction.moduleurl" name="moduleFunction.moduleUrl" />
							
							<div class="form-group">
								<label class="control-label" for=""><@s.text name="label.admin.modulefunction.name" /></label>
								<div class="input-group">
									<@s.hidden id="master-id" name="moduleFunction.master.id" />
							      	<input type="text" value="<#if moduleFunction.master??>${moduleFunction.master.name!'None'}</#if>" id="master-name" name="moduleFunction.master.name" readonly="readonly" class="form-control">
							      	<span class="input-group-btn">
										<#if moduleFunction.id??>
											<#assign masterUrl = moduleFunction.id + '/master/' />
										<#else>
											<#assign masterUrl = ''/>
										</#if>
							        	<button class="btn btn-default openpopup" type="button" title="Master Module" object-name="moduleFunctions|description" field-target="master-id|master-name" href="<@s.url value="/admin/modulefunctions/" />${masterUrl}"><@s.text name="button.main.choose" /></button>
							      	</span>
							    </div>
						    </div>
							<@s.submit cssClass="btn btn-primary col-md-3" value="%{getText('button.main.save')}" />
						</@s.form>
					</div>
				</div>
			</div>
			<div class="col-md-3">
				<#if moduleFunction.logInformation.activeFlag == 0>
				<div class="box box-success">
					<div class="box-body">
						<@s.form theme="bootstrap" action="${request.contextPath}${request.servletPath}/status">
							<@s.hidden name="id" value="%{moduleFunction.id}" />
							<@s.hidden name="status" value="1" />
							<@s.submit cssClass="btn btn-success btn-lg col-md-12" key="label.admin.modulefunction.enable" />
						</@s.form>
					</div>
				</div>
				<#elseif moduleFunction.logInformation.activeFlag == 1>
				<div class="box box-danger">
					<div class="box-body">
						<@s.form theme="bootstrap" action="${request.contextPath}${request.servletPath}/status">
							<@s.hidden name="id" value="%{moduleFunction.id}" />
							<@s.hidden name="status" value="0" />
							<@s.submit cssClass="btn btn-danger btn-lg col-md-12" key="label.admin.modulefunction.disable" />
						</@s.form>
					</div>
				</div>
				</#if>
			</div>
		</div>
	</body>
</html>