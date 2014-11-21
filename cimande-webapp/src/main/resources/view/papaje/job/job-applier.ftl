<html>
	<head>
		<title><@s.text name="page.backend.job.applier.title" /></title>
	</head>
	<body>
		<content tag="header"><@s.text name="page.backend.job.applier.header" /></content>
		<content tag="script">
		<script type="text/javascript" src="<@s.url value="/scripts/jquery/simple-pagination.js" />"></script>
		<script type="text/javascript">
			$(function() {
				$('#pagination').pagination({
					pages: ${employees.totalPages},
					currentPage: ${employees.number},
					hrefTextPrefix: '?q=${q!}&max=${max!}&page='
				});
				$('#popup').modal('false');
			});
			
			function site($scope, $http) {
				$scope.confirm = function(id) {
					$http.post('<@s.url value="/admin/rolesite/" />' + id + '/delete').success(function() {
						location.reload();
					});
				}
			}
		</script>
		</content>
		<div class="row">
			<div class="col-md-6">
				<@s.form theme="bootstrap" method="get">
					<@s.hidden name="job.id"/>
					<@s.textfield key="label.backend.job.name" name="job.name" readonly="true"/>
					<@s.textarea key="label.backend.job.description" name="job.description" readonly="true"/>
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
										<th><@s.text name="label.backend.job.applier.name" /></th>
										<th><@s.text name="label.backend.job.applier.personalPage" /></th>
										<th><@s.text name="label.backend.job.action" /></th>
									</tr>
								</thead>
								<tbody>
									<#list employees.content as e>
									<tr>
										<td>${e.name!}</td>
										<td>${e.personalPage!}</td>
										<td>
											<#--
											<a href="" title="<@s.text name="label.rolesite.action.delete" />" ng-bootbox-confirm="<@s.text name="message.rolesite.delete.confirm" />" ng-bootbox-confirm-action="confirm('${us.id!}')">
												<i class="fa fa-trash"></i>
											</a>
											-->
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
								<div class="pagination alert pull-right">Found ${employees.numberOfElements} entries</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</body>
</html>