package org.blueoxygen.modules.papaje.company;

import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.blueoxygen.modules.papaje.company.Company;
import org.springframework.data.domain.Page;

@Path("/admin/companies")
@Produces(MediaType.APPLICATION_JSON)
public interface CompanyService {
	@GET
	@Path("/{id}")
	Company getCompanyById(@PathParam("id") String id);
	
	@GET
	Page<? extends Company> findCompanyByKeyword(@QueryParam("q") @DefaultValue("") String keyword, 
			@QueryParam("max") @DefaultValue("10") int max,
			@QueryParam("page") @DefaultValue("0") int page);

	@DELETE
	@Path("/{name}")
	boolean removeCompany(@PathParam("id") String id);

	@POST
	Company saveCompany(Company company);
}
