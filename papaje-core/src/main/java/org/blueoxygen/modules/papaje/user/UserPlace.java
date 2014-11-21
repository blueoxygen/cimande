/**
 * 
 */
package org.blueoxygen.modules.papaje.user;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.blueoxygen.modules.papaje.location.Location;
import org.meruvian.yama.core.DefaultJpaPersistence;
import org.meruvian.yama.core.commons.JpaAddress;

/**
 * @author dianw
 * 
 */
@Entity
@Table(name = "papaje_user_place")
public class UserPlace extends DefaultJpaPersistence {
	public enum PlaceType {
		HOMETOWN, CURRENT, OTHER
	}

	private String name;
	private PersonalInfo personalInfo;
	private JpaAddress address = new JpaAddress();
	private PlaceType placeType = PlaceType.OTHER;
	private Date when;
	private String story;
	private Location city;
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	@ManyToOne
	@JoinColumn(name = "personal_info_id", nullable = false)
	public PersonalInfo getPersonalInfo() {
		return personalInfo;
	}

	public void setPersonalInfo(PersonalInfo personalInfo) {
		this.personalInfo = personalInfo;
	}

	@Embedded
	public JpaAddress getAddress() {
		return address;
	}

	public void setAddress(JpaAddress address) {
		this.address = address;
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "place_type", nullable = false)
	public PlaceType getPlaceType() {
		return placeType;
	}

	public void setPlaceType(PlaceType placeType) {
		this.placeType = placeType;
	}

	public Date getWhen() {
		return when;
	}

	public void setWhen(Date when) {
		this.when = when;
	}

	@Column(length = 500)
	public String getStory() {
		return story;
	}

	public void setStory(String story) {
		this.story = story;
	}

	@ManyToOne
	@JoinColumn(name = "city_id")
	public Location getCity() {
		return city;
	}
	
	public void setCity(Location city) {
		this.city = city;
	}
}
