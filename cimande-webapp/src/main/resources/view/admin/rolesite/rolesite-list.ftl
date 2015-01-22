<html>
	<head>
		<title><@s.text name="page.admin.rolesite.title" /></title>
	</head>
	<body>
		<content tag="header"><@s.text name="page.admin.rolesite.header" /></content>
		<content tag="script">
		<script type="text/javascript" src="<@s.url value="/scripts/jquery/simple-pagination.js" />"></script>
		<script src="<@s.url value="/scripts/yama/cimande-popup.js" />" type="text/javascript"></script>
		<script type="text/javascript">
			$(function() {
				$('#pagination').pagination({
					pages: ${roleSites.totalPages},
					currentPage: ${roleSites.number},
					hrefTextPrefix: '?q=${q!}&max=${max!}&page='
				});
				$('#popup').modal('false');
			});
			
			function site($scope, $http) {
				$scope.confirm = function(id) {
					$http.post('<@s.url value="/admin/rolesite/" />' + id + '/delete' ).success(function() {
						location.reload();
					});
				}
			}
		</script>
		</content>
		<div class="row">
			<div class="col-md-6">
				<@s.form theme="bootstrap" method="post">
					<@s.hidden name="role.id"/>
					<@s.textfield key="label.admin.rolesite.role.name" name="role.name" readonly="true"/>
					<div class="form-group">
						<label class="control-label" for=""><@s.text name="label.admin.rolesite.site.name" /></label>
						<div class="input-group">
							<input type="hidden" id="site-id" name="siteId" />
					      	<input type="text" id="site-name" name="site.name" readonly="readonly" class="form-control">
					      	<span class="input-group-btn">
					        	<button class="btn btn-default openpopup" type="button" title="Site Function" object-name="sites|name" field-target="site-id|site-name" href="<@s.url value="/admin/rolesite/${role.id}/site" />"><@s.text name="button.main.choose" /></button>
					      	</span>
					    </div>
				    </div>
					<@s.submit cssClass="btn btn-primary col-md-3" value="%{getText('button.main.save')}" />
				</@s.form>
			</div>
		</div>
		<br>
		<div class="row" ng-controller="site">
			<div class="col-md-12">
				<div class="box">
					<div class="box-header">
					</div>
					<div class="box-body">
						<div class="table-responsive">
							<table class="table">
								<thead>
									<tr>
										<th><@s.text name="label.admin.rolesite.site.name" /></th>
										<th><@s.text name="label.admin.rolesite.action" /></th>
									</tr>
								</thead>
								<tbody>
									<#list roleSites.content as us>
									<tr>
										<td>${us.site.name!}</td>
										<td>
											<a href="" title="<@s.text name="label.rolesite.action.delete" />" ng-bootbox-confirm="<@s.text name="message.rolesite.delete.confirm" />" ng-bootbox-confirm-action="confirm('${us.id!}')">
												<i class="fa fa-trash"></i>
											</a>
											&nbsp;&nbsp;&nbsp;
											<a href="<@s.url value="/admin/rolesiteprivilege/${us.id!}/edit" />"><i class="fa fa-plus-square"></i></a>
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
								<div class="pagination alert pull-right">Found ${roleSites.numberOfElements} entries</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</body>
</html>