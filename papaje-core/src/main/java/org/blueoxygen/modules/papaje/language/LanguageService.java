package org.blueoxygen.modules.papaje.language;

import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.springframework.data.domain.Page;

@Path("/admin/languages")
@Produces(MediaType.APPLICATION_JSON)
public interface LanguageService {
	@GET
	@Path("/{id}")
	Language getLanguageById(@PathParam("id") String id);
	
	@GET
	Page<? extends Language> findLanguageByKeyword(@QueryParam("q") @DefaultValue("") String keyword, 
			@QueryParam("max") @DefaultValue("10") int max,
			@QueryParam("page") @DefaultValue("0") int page);

	@DELETE
	@Path("/{name}")
	boolean removeLanguage(@PathParam("id") String id);

	@POST
	Language saveLanguage(Language language);

}
