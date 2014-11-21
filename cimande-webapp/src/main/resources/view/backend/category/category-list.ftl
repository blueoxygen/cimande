<html>
	<head>
		<title>Category</title>
	</head>
	<body>
		<content tag="header">Category</content>
		<content tag="headerDetail">Category List</content>
		<content tag="script">
		<script type="text/javascript" src="<@s.url value="/scripts/jquery/simple-pagination.js" />"></script>
		<script type="text/javascript">
		$(function() {
			$('#pagination').pagination({
				pages: ${categories.totalPages},
				currentPage: ${categories.number},
				hrefTextPrefix: '?q=${q!}&max=${max!}&page='
			});
		});
		</script>
		</content>
		<div class="row">
			<div class="col-md-6">
				<a href="<@s.url value="/backend/category/-/edit" />" class="btn btn-default col-md-3"><@s.text name="button.main.add" /></a>
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
										<th><@s.text name="label.backend.category.name" /></th>
										<th><@s.text name="label.backend.category.description" /></th>
										<th><@s.text name="label.backend.category.enabled" /></th>
									</tr>
								</thead>
								<tbody>
									<#list categories.content as c>
									<tr>
										<td><a href="<@s.url value="/backend/category/${(c.id!'')?upper_case}/edit" />">${(c.name!"")}</a></td>
										<td>${c.description!}</td>
										<td>
											<#assign status = c.logInformation.activeFlag />
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
								<div class="pagination alert pull-right">Found ${categories.numberOfElements} entries</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</body>
</html>