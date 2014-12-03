package ch.unibe.ese.team1.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Represents a picture that is linked to an ad and therefore is linked to an
 * ad.
 */
@Entity
public class AdPicture extends Picture {

	@JsonIgnore
	@ManyToOne
	private Ad ad;

	public Ad getAd() {
		return ad;
	}

	public void setAd(Ad ad) {
		this.ad = ad;
	}
}
