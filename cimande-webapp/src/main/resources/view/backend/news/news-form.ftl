<html>
	<head>
		<title><@s.text name="page.backend.news.title" /></title>
	</head>
	<body>
		<content tag="header"><@s.text name="page.backend.news.header" /></content>
		<content tag="script">
		<script type="text/javascript">
		$(function() {
			var categories = [];
			<#if categories??>
			<#list categories.content as c>
				categories.push('${(c.id!"")}');
			</#list>
			</#if>
			
			for (c in categories) {
				$('select[name="news.category.id"] option[value="' + categories[c] + '"]').attr('selected', true);
			}
		});
		</script>
		</content>

		<@s.actionerror theme="bootstrap"/>
		<@s.actionmessage theme="bootstrap"/>
		<div class="row">
			<div class="col-md-9">
				<div class="box box-primary">
					<div class="box-header">
						<h3 class="box-title"><@s.text name="label.backend.news.title" /></h3>
					</div>
					
					<div class="box-body">
						<@s.form theme="bootstrap">
							<@s.hidden name="news.id" />
							<@s.textfield key="label.backend.news.titlee" name="news.title" value="${(news.title!'')?upper_case}" />
							<@s.textarea rows="2" key="label.backend.news.content" name="news.content" />
							<@s.textarea rows="2" key="label.backend.news.description" name="news.description" />
							<div class="form-group ">
								<label for="categories"><@s.text name="label.backend.news.categories" /></label>
								<div class="controls">
									<select name="news.category.id" class="form-control" multiple="true">
										<#list categories.content as c>
										<option value="${(c.id!"")}">${(c.name!"")?upper_case}</option>
										</#list>
									</select>
								</div>
							</div>
							<@s.checkbox id="isForce" key="label.backend.news.is_force" name="news.force" />							
							<div class="form-group" id="forceDate" style="display: none;">
								<label class="control-label col-md-2" for="date">Force Date</label>
								<div class="controls">
									<input name="news.forceDate" value="<#if news.forceDate??>${(news.forceDate!'')?string("dd-MM-yyyy")}</#if>" type="text" class="form-control" id="date" readonly="readonly"/>
								</div>
							</div>
							
							<div class="form-group ">
								<div class="col-md-4">
									<@s.submit cssClass="btn btn-primary col-md-4" value="%{getText('button.main.save')}" />
								</div>
							</div>
						</@s.form>
					</div>
				</div>
			</div>
			<div class="col-md-3">
				<#if news.logInformation.activeFlag == 0>
				<div class="box box-success">
					<div class="box-body">
						<@s.form theme="bootstrap" action="${request.contextPath}${request.servletPath}/status">
							<@s.hidden name="id" value="%{news.id}" />
							<@s.hidden name="status" value="1" />
							<@s.submit cssClass="btn btn-success btn-lg col-md-12" key="label.backend.news.enable" />
						</@s.form>
					</div>
				</div>
				<#elseif news.logInformation.activeFlag == 1>
				<div class="box box-danger">
					<div class="box-body">
						<@s.form theme="bootstrap" action="${request.contextPath}${request.servletPath}/status">
							<@s.hidden name="id" value="%{news.id}" />
							<@s.hidden name="status" value="0" />
							<@s.submit cssClass="btn btn-danger btn-lg col-md-12" key="label.backend.news.disable" />
						</@s.form>
					</div>
				</div>
				</#if>
			</div>
		</div>
		<script type="text/javascript">
			$(function(){
				if($("#isForce").is(':checked')) {
					$('#forceDate').show();
				}
				$("#isForce").click(function(){
					if($(this).is(':checked')) {
						$('#forceDate').show();
					} else {
						$('#forceDate').hide();
					}
				});
			});
		</script>
	</body>
</html>