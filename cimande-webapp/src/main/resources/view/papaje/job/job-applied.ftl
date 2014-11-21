<html>
	<head>
		<title><@s.text name="page.backend.job.title" /></title>
	</head>
	<body>
		<content tag="header"><@s.text name="page.backend.job.header" /></content>
		<content tag="script">
			<script type="text/javascript" src="<@s.url value="/scripts/jquery/simple-pagination.js" />"></script>
			<script type="text/javascript">
				$(function() {
					$('#pagination').pagination({
						pages: ${jobs.totalPages},
						currentPage: ${jobs.number},
						hrefTextPrefix: '?q=${q!}&max=${max!}&page='
					});
				});
				
				function job($scope, $http) {
					$scope.confirm = function(id) {
						$http.post('<@s.url value="/backend/jobs/" />' + id + '/delete').success(function() {
							location.reload();
						});
					}
				}
			</script>
		</content>
		<div class="row">
			<div class="col-md-6">
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
		<div class="row" ng-controller="job">
			<div class="col-md-12">
				<div class="box">
					<div class="box-header">
					</div>
					<div class="box-body">
						<div class="table-responsive">
							<table class="table">
								<thead>
									<tr>
										<th><@s.text name="label.backend.job.name" /></th>
										<th><@s.text name="label.backend.job.description" /></th>
										<th><@s.text name="label.backend.job.action" /></th>
									</tr>
								</thead>
								<tbody>
									<#list jobs.content as job>
									<tr>
										<td>
											<#-- <a href="<@s.url value="/backend/jobs/${job.id!}/view" />"> -->
												${job.name!}
											<#-- </a> -->
										</td>
										<td>${job.description!}</td>
										<td>
											<#assign status = job.logInformation.activeFlag />
											<#if status == 0>
											<span class="glyphicon glyphicon-unchecked"></span>
											<#elseif status == 1>
											<span class="glyphicon glyphicon-check"></span>
											</#if>
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
								<div class="pagination alert pull-right">Found ${jobs.numberOfElements} entries</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</body>
</html>