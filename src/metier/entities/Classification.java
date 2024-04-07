package metier.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "CLASSIFICATION")
public class Classification implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idClas;
	private String nomClas;

	@Temporal(TemporalType.DATE)
	private Date dateCreation;

	@OneToMany(mappedBy = "classification")
	private List<Cosmetique> cosmetiques;

	public Classification() {
		super();
	}

	public Classification(String nomCat, Date dateCreation) {
		super();
		this.nomClas = nomCat;
		this.dateCreation = dateCreation;
	}

	public Long getIdClas() {
		return idClas;
	}

	public void setIdClas(Long idCat) {
		this.idClas = idCat;
	}

	public String getNomClas() {
		return nomClas;
	}

	public void setNomClas(String nomCat) {
		this.nomClas = nomCat;
	}

	public Date getDateCreation() {
		return dateCreation;
	}

	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}

	public List<Cosmetique> getCosmetiques() {
		return cosmetiques;
	}

	public void setCosmetiques(List<Cosmetique> cosmetiques) {
		this.cosmetiques = cosmetiques;
	}
}