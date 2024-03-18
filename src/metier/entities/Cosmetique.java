package metier.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "COSMETIQUE")
public class Cosmetique implements Serializable {
	@Id
	@Column(name = "IDCOSMETIQUE")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idCosmetique;
	@Column(name = "NOMCOSMETIQUE")
	private String nomCosmetique;
	private double prix;

	public Cosmetique() {
		super();
	}

	public Cosmetique(String nomCosmetique, double prix) {
		super();
		this.nomCosmetique = nomCosmetique;
		this.prix = prix;
	}

	public Long getIdCosmetique() {
		return idCosmetique;
	}

	public void setIdCosmetique(Long id) {
		this.idCosmetique = id;
	}

	public String getNomCosmetique() {
		return nomCosmetique;
	}

	public void setNomCosmetique(String nom) {
		this.nomCosmetique = nom;
	}

	public double getPrix() {
		return prix;
	}

	public void setPrix(double prix) {
		this.prix = prix;
	}

}