<html>
	<head>
		<title><@s.text name="page.admin.modulefunction.title" /></title>
	</head>
	<body>
		<content tag="header"><@s.text name="page.admin.modulefunction.header" /></content>
		<content tag="script">
		<script type="text/javascript" src="<@s.url value="/scripts/jquery/simple-pagination.js" />"></script>
		<script type="text/javascript">
		$(function() {
			$('#pagination').pagination({
				pages: ${moduleFunctions.totalPages},
				currentPage: ${moduleFunctions.number},
				hrefTextPrefix: '?q=${q!}&max=${max!}&page='
			});
		});
		
		function modulefunction($scope, $http) {
			$scope.confirm = function(id) {
				$http.post('<@s.url value="/admin/modulefunctions/" />' + id + '/delete').success(function() {
					location.reload();
				});
			}
		}
		</script>
		</content>
		<div class="row">
			<div class="col-md-6">
				<a href="<@s.url value="/admin/modulefunctions/-/edit" />" class="btn btn-default col-md-3"><@s.text name="button.main.add" /></a>
			</div>
			<div class="col-md-6">
				<@s.form theme="bootstrap" method="GET">
					<div class="form-group col-md-10">
						<input name="q" value="${q!}" type="text" class="form-control" placeholder="<@s.text name="button.main.search" />...">
					</div>
					<@s.submit cssClass="btn btn-success col-md-2" value="%{getText('button.main.search')}" />
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
										<th><@s.text name="label.admin.modulefunction.name" /></th>
										<th><@s.text name="label.admin.modulefunction.description" /></th>
										<th><@s.text name="label.admin.modulefunction.moduleurl" /></th>
										<th><@s.text name="label.admin.modulefunction.action" /></th>
									</tr>
								</thead>
								<tbody>
									<#list moduleFunctions.content as md>
									<tr>
										<td><a href="<@s.url value="/admin/modulefunctions/${md.id!}/edit" />">${md.name!}</a></td>
										<td>${md.description!}</td>
										<td>${md.moduleUrl!}</td>
										<td>
											<#assign status = md.logInformation.activeFlag />
											<#if status == 0>
											<span class="glyphicon glyphicon-unchecked"></span>
											<#elseif status == 1>
											<span class="glyphicon glyphicon-check"></span>
											</#if>
											&nbsp;&nbsp;&nbsp;
											<a href="" title="<@s.text name="label.modulefunction.action.delete" />" ng-bootbox-confirm="<@s.text name="message.modulefunction.delete.confirm" />" ng-bootbox-confirm-action="confirm('${md.id!}')">
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
								<div class="pagination alert pull-right">Found ${moduleFunctions.numberOfElements} entries</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</body>
</html>