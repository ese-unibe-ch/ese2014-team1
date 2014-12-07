package ch.unibe.ese.team1.model;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Represents a picture that is linked to an ad and therefore is linked to an
 * ad.
 */
@Entity
public class AdPicture extends Picture {

}
