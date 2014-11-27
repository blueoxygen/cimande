package org.blueoxygen.cimande.site;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.blueoxygen.cimande.core.CimandeJpaPersistence;
import org.meruvian.yama.core.commons.JpaFileInfo;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "cimande_site")
public class Site extends CimandeJpaPersistence {
	private String name;
	private String description;
	private String title;
	private String urlBranding;
	private String siteUrl;
	private String adminEmail;
	private String notifyEmail;
	private String notifyFrom;
	private String notifyMessage;
	private int notifyFlag = 0;
	private Site master;
	private List<Site> sites = new ArrayList<Site>();
	private String virtualHost;
	private String path;
	private int level = 0;
	private JpaFileInfo fileInfo;

	@Column(unique = true)
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "url_branding")
	public String getUrlBranding() {
		return urlBranding;
	}
	
	public void setUrlBranding(String urlBranding) {
		this.urlBranding = urlBranding;
	}

	@Column(name = "site_url")
	public String getSiteUrl() {
		return siteUrl;
	}
	
	public void setSiteUrl(String siteUrl) {
		this.siteUrl = siteUrl;
	}

	@Column(name = "admin_email")
	public String getAdminEmail() {
		return adminEmail;
	}
	
	public void setAdminEmail(String adminEmail) {
		this.adminEmail = adminEmail;
	}

	@Column(name = "notify_email")
	public String getNotifyEmail() {
		return notifyEmail;
	}
	
	public void setNotifyEmail(String notifyEmail) {
		this.notifyEmail = notifyEmail;
	}

	@Column(name = "notify_from")
	public String getNotifyFrom() {
		return notifyFrom;
	}
	
	public void setNotifyFrom(String notifyFrom) {
		this.notifyFrom = notifyFrom;
	}

	@Column(name = "notify_message")
	public String getNotifyMessage() {
		return notifyMessage;
	}
	
	public void setNotifyMessage(String notifyMessage) {
		this.notifyMessage = notifyMessage;
	}

	@Column(name = "notify_flag")
	public int getNotifyFlag() {
		return notifyFlag;
	}
	
	public void setNotifyFlag(int notifyFlag) {
		this.notifyFlag = notifyFlag;
	}

	@JsonBackReference
	@org.codehaus.jackson.annotate.JsonBackReference
	@ManyToOne
	@JoinColumn(name = "master")
	public Site getMaster() {
		return master;
	}
	
	public void setMaster(Site master) {
		this.master = master;
	}

	@JsonIgnore
	@org.codehaus.jackson.annotate.JsonBackReference
	@OneToMany(mappedBy = "master", fetch = FetchType.LAZY)
	public List<Site> getSites() {
		return sites;
	}

	public void setSites(List<Site> sites) {
		this.sites = sites;
	}

	@Column(name = "virtual_host", unique = true)
	public String getVirtualHost() {
		return virtualHost;
	}
	
	public void setVirtualHost(String virtualHost) {
		this.virtualHost = virtualHost;
	}
	
	public String getPath() {
		return path;
	}
	
	public void setPath(String path) {
		this.path = path;
	}
	
	public int getLevel() {
		return level;
	}
	
	public void setLevel(int level) {
		this.level = level;
	}

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "file_info_id")
	public JpaFileInfo getFileInfo() {
		return fileInfo;
	}

	public void setFileInfo(JpaFileInfo fileInfo) {
		this.fileInfo = fileInfo;
	}
	
}
