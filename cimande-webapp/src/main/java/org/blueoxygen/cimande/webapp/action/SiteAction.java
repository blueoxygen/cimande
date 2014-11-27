package org.blueoxygen.cimande.webapp.action;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.blueoxygen.cimande.security.SessionCredentials;
import org.blueoxygen.cimande.site.Site;
import org.blueoxygen.cimande.site.SiteManager;
import org.meruvian.inca.struts2.rest.ActionResult;
import org.meruvian.inca.struts2.rest.annotation.Action;
import org.meruvian.inca.struts2.rest.annotation.Param;
import org.meruvian.inca.struts2.rest.annotation.Result;
import org.meruvian.inca.struts2.rest.annotation.Results;
import org.meruvian.inca.struts2.rest.annotation.Action.HttpMethod;
import org.meruvian.inca.struts2.rest.annotation.ActionParam;
import org.meruvian.yama.core.commons.DefaultFileInfo;
import org.meruvian.yama.core.commons.FileInfo;
import org.meruvian.yama.core.commons.FileInfoManager;
import org.meruvian.yama.core.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.opensymphony.xwork2.ActionSupport;

@Action(name = "/admin/sites")
public class SiteAction extends ActionSupport {
	@Inject
	private SiteManager siteManager;
	
	@Inject
	private FileInfoManager fileInfoManager;
	
	private InputStream picture;

	public InputStream getPicture() {
		return picture;
	}
	
	@Action(method = HttpMethod.GET)
	public ActionResult siteList(@ActionParam("q") String q, @ActionParam("max") int max, @ActionParam("page") int page) {
		max = max == 0 ? 10 : max;
		
		Page<? extends Site> sites = siteManager.findSiteForName(q, new PageRequest(page, max));
		
		return new ActionResult("freemarker", "/view/admin/site/site-list.ftl").addToModel("sites", sites);
	}
	
	@Action(name = "/{sitename}/edit", method = HttpMethod.GET)
	public ActionResult siteForm(@ActionParam("sitename") String sitename) {
		Site site = new Site();
		
		if (!StringUtils.equalsIgnoreCase(sitename, "-"))
			site = siteManager.findSiteByName(sitename);
		
		return new ActionResult("freemarker", "/view/admin/site/site-form.ftl").addToModel("site", site);
	}
	
	@Action(name = "/{sitename}/edit", method = HttpMethod.POST)
	public ActionResult updateSite(@ActionParam("sitename") String sitename, @ActionParam("site") Site site,
			@ActionParam("logo") File file, @ActionParam("logoFileName") String fileName, 
			@ActionParam("edit") String edit) throws IOException {
		
		if (StringUtils.equalsIgnoreCase(edit, "logo")) {
			if(StringUtils.isNotBlank(site.getId())) {
				validateImage(file);
				
				if (hasFieldErrors())
					return new ActionResult("freemarker", "/view/admin/site/site-form.ftl");
				
				DefaultFileInfo fileInfo = new DefaultFileInfo();
				fileInfo.setOriginalName(fileName);
				fileInfo.setDataBlob(new FileInputStream(file));
				fileInfo.setPath("logo");
				
				FileInfo f = fileInfoManager.saveFileInfo(fileInfo);
				siteManager.setSiteLogo(site, f);
				site = siteManager.findSiteById(site.getId());
			}
		} else {
			validateSite(site);
			
			if (hasFieldErrors())
				return new ActionResult("freemarker", "/view/admin/site/site-form.ftl");
			
			site = siteManager.saveSite(site);
		}
		
		return new ActionResult("redirect", StringUtils.join("/admin/sites/", StringUtils.defaultIfBlank(site.getName(), "-"), "/edit?success"));
	}
	
	@Action(name = "/{sitename}/edit/status", method = HttpMethod.POST)
	public ActionResult updateRoleStatus(@ActionParam("id") final String id, @ActionParam("status") int status) {
		Site site = siteManager.findSiteById(id);
		site = siteManager.updateStatus(site, status);
		
		String redirectUri = "/admin/sites/" + site.getName() + "/edit?success";
		
		return new ActionResult("redirect", redirectUri);
	}
	

	@Action(name = "/{sitename}/logo")
	@Results({
		@Result(name = "no-logo", type = "redirect", location = "/images/no-profile.jpg"),
		@Result(name = "logo", type = "stream", 
			params = { 
				@Param(name = "contentType", value = "image/jpg"),
				@Param(name = "inputName", value = "picture")
			})
	})
	public String getPhoto(@ActionParam("sitename") String sitename) throws IOException {
		Site site = siteManager.findSiteByName(sitename);

		FileInfo fileInfo = site.getFileInfo();
		if (fileInfo == null || StringUtils.isBlank(fileInfo.getId())) {
			return "no-logo";
		}
		
		fileInfo = fileInfoManager.getFileInfoById(fileInfo.getId());
		picture = fileInfo.getDataBlob();
		
		return "logo";
	}
	
	private void validateImage(File file) {
		if (file != null) {
			try {
				BufferedImage image = ImageIO.read(file);
				if (image != null) return;
			} catch (IOException e) {
			}
		}
		
		addFieldError("profilePicture", getText("message.profile.picture.notvalid"));
	}
	
	private void validateSite(Site site) {
		if (StringUtils.isBlank(site.getName())) {
			addFieldError("site.name", getText("message.site.name.notempty"));
		}
	}
}
