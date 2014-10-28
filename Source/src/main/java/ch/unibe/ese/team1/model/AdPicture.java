package ch.unibe.ese.team1.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

/**
 * Represents a picture that is linked to an ad and therefore is linked to an
 * ad.
 */
@Entity
public class AdPicture extends Picture {

	@ManyToOne
	private Ad ad;

	public Ad getAd() {
		return ad;
	}

	public void setAd(Ad ad) {
		this.ad = ad;
	}
}
