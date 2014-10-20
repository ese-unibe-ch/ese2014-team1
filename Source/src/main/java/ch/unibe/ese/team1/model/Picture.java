package ch.unibe.ese.team1.model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * This class is the abstract super-entity of all entities which represent
 * pictures.
 */
@MappedSuperclass
public abstract class Picture {

	@Id
	@GeneratedValue
	private long id;

	@Column(nullable = false)
	private String filePath;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
}
