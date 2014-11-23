package ch.unibe.ese.team1.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
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

	@ManyToMany
	private List<User> searchers;
	
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

	public List<User> getSearchers() {
		return searchers;
	}

	public void setSearchers(List<User> searchers) {
		this.searchers = searchers;
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