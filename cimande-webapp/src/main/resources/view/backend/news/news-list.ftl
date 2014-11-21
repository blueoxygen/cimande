<html>
	<head>
		<title>News</title>
	</head>
	<body>
		<content tag="header">News</content>
		<content tag="headerDetail">News List</content>
		<content tag="script">
		<script type="text/javascript" src="<@s.url value="/scripts/jquery/simple-pagination.js" />"></script>
		<script type="text/javascript">
		$(function() {
			$('#pagination').pagination({
				pages: ${newss.totalPages},
				currentPage: ${newss.number},
				hrefTextPrefix: '?q=${q!}&max=${max!}&page='
			});
		});
		</script>
		</content>
		<div class="row">
			<div class="col-md-6">
				<a href="<@s.url value="/backend/news/-/edit" />" class="btn btn-default col-md-3"><@s.text name="button.main.add" /></a>
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
		<div class="row">
			<div class="col-md-12">
				<div class="box">
					<div class="box-header">
					</div>
					<div class="box-body">
						<div class="table-responsive">
							<table class="table">
								<thead>
									<tr>
										<th><@s.text name="label.backend.news.titlee" /></th>
										<th><@s.text name="label.backend.news.content" /></th>
										<th><@s.text name="label.backend.news.enabled" /></th>
									</tr>
								</thead>
								<tbody>
									<#list newss.content as n>
									<tr>
										<td><a href="<@s.url value="/backend/news/${(n.id!'')?upper_case}/edit" />">${(n.title!"")}</a></td>
										<td>${n.content!}</td>
										<td>
											<#assign status = n.logInformation.activeFlag />
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
								<div class="pagination alert pull-right">Found ${newss.numberOfElements} entries</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</body>
</html>