/**
 * 
 */
package org.blueoxygen.modules.papaje.location;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.meruvian.yama.core.DefaultJpaPersistence;

/**
 * @author dianw
 * 
 */
@Entity
@Table(name = "papaje_location")
public class Location extends DefaultJpaPersistence {
	public enum LocationType {
		COUNTRY, STATE, CITY, SUBDISTRICT, OTHER
	}

	private String name;
	private LocationType locationType = LocationType.OTHER;
	private double latitude = 0.0;
	private double longitude = 0.0;
	private Location parent;

	@Column(nullable = false)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "location_type")
	public LocationType getLocationType() {
		return locationType;
	}

	public void setLocationType(LocationType locationType) {
		this.locationType = locationType;
	}

	@Column(nullable = false)
	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	@Column(nullable = false)
	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	@ManyToOne
	@JoinColumn(name = "parent_id")
	public Location getParent() {
		return parent;
	}

	public void setParent(Location parent) {
		this.parent = parent;
	}

}
