package ch.unibe.ese.team1.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;

/** A visit for a flat, has a time window. */
@Entity
public class Visit {

	@Id
	@GeneratedValue
	private long id;
	
	@ManyToOne
	private Ad ad;
	
	@ManyToOne
	private User advertiser;

	@ManyToOne
	private User searcher;
	
	@JsonFormat(pattern = "HH:mm, dd.MM.yyyy")
	@Temporal(TemporalType.TIMESTAMP)
	private Date startTimestamp;
	
	@JsonFormat(pattern = "HH:mm, dd.MM.yyyy")
	@Temporal(TemporalType.TIMESTAMP)
	private Date endTimestamp;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	public Ad getAd() {
		return ad;
	}

	public void setAd(Ad ad) {
		this.ad = ad;
	}

	public User getAdvertiser() {
		return advertiser;
	}

	public void setAdvertiser(User advertiser) {
		this.advertiser = advertiser;
	}

	public User getSearcher() {
		return searcher;
	}

	public void setSearcher(User searcher) {
		this.searcher = searcher;
	}

	public Date getStartTimestamp() {
		return startTimestamp;
	}

	public void setStartTimestamp(Date startTimestamp) {
		this.startTimestamp = startTimestamp;
	}

	public Date getEndTimestamp() {
		return endTimestamp;
	}

	public void setEndTimestamp(Date endTimestamp) {
		this.endTimestamp = endTimestamp;
	}
	
}