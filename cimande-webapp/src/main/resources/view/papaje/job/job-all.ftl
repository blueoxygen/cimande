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
						$http.post('<@s.url value="/backend/jobs/" />' + id + '/apply').success(function() {
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
		<div class="row" >
			<div class="col-md-12">
				<#list jobs.content as job>
				<div class="box box-info">
					<div class="box-header" ng-controller="job">
<#-- 						<h3 class="box-title"><a href="<@s.url value="/backend/jobs/${job.id!}/view" />">${job.name!}</a></h3> -->
						<h3 class="box-title">${job.name!}</h3>
						<div class="box-tools pull-right">
                            <a href="" class="btn btn-default btn-sm" ng-bootbox-confirm="Are you sure you want to apply this job ?" ng-bootbox-confirm-action="confirm('${job.id!0}')"><i class="fa fa-check"></i> Apply</a>
                        </div>
					</div>
					<div class="box-body">
						<p>${job.description!}</p>
					</div>
					<div class="box-footer">
					</div>
				</div>
				</#list>
			</div>
		</div>
	</body>
</html>