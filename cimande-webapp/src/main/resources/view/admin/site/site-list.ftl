<html>
	<head>
		<title><@s.text name="page.admin.site.title" /></title>
	</head>
	<body>
		<content tag="header"><@s.text name="page.admin.site.header" /></content>
		<content tag="script">
		<script type="text/javascript" src="<@s.url value="/scripts/jquery/simple-pagination.js" />"></script>
		<script type="text/javascript">
		$(function() {
			$('#pagination').pagination({
				pages: ${sites.totalPages},
				currentPage: ${sites.number},
				hrefTextPrefix: '?q=${q!}&max=${max!}&page='
			});
		});
		
		function site($scope, $http) {
			$scope.confirm = function(id) {
				$http.post('<@s.url value="/admin/sites/" />' + id + '/delete').success(function() {
					location.reload();
				});
			}
		}
		</script>
		</content>
		<div class="row">
			<div class="col-md-6">
				<a href="<@s.url value="/admin/sites/-/edit" />" class="btn btn-default col-md-3"><@s.text name="button.main.add" /></a>
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
										<th><@s.text name="label.admin.site.name" /></th>
										<th><@s.text name="label.admin.site.description" /></th>
										<th><@s.text name="label.admin.site.virtualhost" /></th>
										<th><@s.text name="label.admin.site.action" /></th>
									</tr>
								</thead>
								<tbody>
									<#list sites.content as site>
									<tr>
										<td><a href="<@s.url value="/admin/sites/${site.name!}/edit" />">${site.name!}</a></td>
										<td>${site.description!}</td>
										<td>${site.virtualHost!}</td>
										<td>
											<#assign status = site.logInformation.activeFlag />
											<#if status == 0>
											<span class="glyphicon glyphicon-unchecked"></span>
											<#elseif status == 1>
											<span class="glyphicon glyphicon-check"></span>
											</#if>
											&nbsp;&nbsp;&nbsp;
											<a href="" title="<@s.text name="label.site.action.delete" />" ng-bootbox-confirm="<@s.text name="message.site.delete.confirm" />" ng-bootbox-confirm-action="confirm('${site.id!}')">
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
								<div class="pagination alert pull-right">Found ${sites.numberOfElements} entries</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</body>
</html>