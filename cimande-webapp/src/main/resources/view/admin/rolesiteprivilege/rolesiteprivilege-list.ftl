<html>
	<head>
		<title><@s.text name="page.admin.rolesiteprivilege.title" /></title>
	</head>
	<body>
		<content tag="header"><@s.text name="page.admin.rolesiteprivilege.header" /></content>
		<content tag="script">
		<script type="text/javascript" src="<@s.url value="/scripts/jquery/simple-pagination.js" />"></script>
		<script src="<@s.url value="/scripts/yama/cimande-popup.js" />" type="text/javascript"></script>
		<script type="text/javascript">
			$(function() {
				$('#pagination').pagination({
					pages: ${roleSitePrivileges.totalPages},
					currentPage: ${roleSitePrivileges.number},
					hrefTextPrefix: '?q=${q!}&max=${max!}&page='
				});
				$('#popup').modal('false');
			});
			
			function modulefunction($scope, $http) {
				$scope.confirm = function(id) {
					$http.post('<@s.url value="/admin/rolesiteprivilege/" />' + id + '/delete' ).success(function() {
						location.reload();
					});
				}
			}
		</script>
		</content>
		<div class="row">
			<div class="col-md-6">
				<@s.form theme="bootstrap" method="post">
					<@s.hidden name="roleSite.id"/>
					<@s.textfield key="label.admin.rolesiteprivilege.role.name" name="roleSite.role.name" readonly="true"/>
					<@s.textfield key="label.admin.rolesiteprivilege.site.name" name="roleSite.site.name" readonly="true"/>
					<div class="form-group">
						<label class="control-label" for=""><@s.text name="label.admin.rolesiteprivilege.modulefunction.name" /></label>
						<div class="input-group">
							<input type="hidden" id="moduleFunction-id" name="moduleFunctionId" />
					      	<input type="text" id="moduleFunction-name" name="moduleFunction.name" readonly="readonly" class="form-control">
					      	<span class="input-group-btn">
					        	<button class="btn btn-default openpopup" type="button" title="Module Function" object-name="moduleFunctions|name" field-target="moduleFunction-id|moduleFunction-name" href="<@s.url value="/admin/rolesiteprivilege/${roleSite.id}/modulefunction" />"><@s.text name="button.main.choose" /></button>
					      	</span>
					    </div>
				    </div>
					<@s.submit cssClass="btn btn-primary col-md-3" value="%{getText('button.main.save')}" />
				</@s.form>
			</div>
		</div>
		<br>
		<div class="row" ng-controller="modulefunction">
			<div class="col-md-12">
				<div class="box">
					<div class="box-header">
					</div>
					<div class="box-body">
						<div class="table-responsive">
							<table class="table">
								<thead>
									<tr>
										<th><@s.text name="label.admin.rolesiteprivilege.modulefunction.name" /></th>
										<th><@s.text name="label.admin.rolesiteprivilege.action" /></th>
									</tr>
								</thead>
								<tbody>
									<#list roleSitePrivileges.content as rp>
									<tr>
										<td>${rp.moduleFunction.name!}</td>
										<td>
											<a href="" title="<@s.text name="label.rolesiteprivilege.action.delete" />" ng-bootbox-confirm="<@s.text name="message.rolesiteprivilege.delete.confirm" />" ng-bootbox-confirm-action="confirm('${rp.id!}')">
												<i class="fa fa-trash"></i>
											</a>
										</td>
									</tr>
									</#list>
								</tbody>
							</table>
						</div>
					</div>
					<div class="box-footer">
						<div class="row">
							<div class="col-md-6">
								<div id="pagination"></div>
							</div>
							<div class="col-md-6">
								<div class="pagination alert pull-right">Found ${roleSitePrivileges.numberOfElements} entries</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</body>
</html>