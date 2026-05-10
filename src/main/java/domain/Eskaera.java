package domain;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlAccessorType(XmlAccessType.FIELD)
@Entity
public class Eskaera implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@XmlID
	@Id 
	@XmlJavaTypeAdapter(IntegerAdapter.class)
	@GeneratedValue
	private Integer idEskaera;
	private String produktuIzena;
	private boolean bought;
	private String kategoria;

	
	@ManyToOne(cascade=CascadeType.PERSIST)
	private User user;
	
	@OneToMany(cascade=CascadeType.PERSIST)
	private List<Eskaintza> eskaintzak = new ArrayList<Eskaintza>();
	
	
	public Eskaera() {
		super();
	}

	public Eskaera(String produktuIzena, String kategoria, User user) {
		this.produktuIzena = produktuIzena;
		this.bought = false;
		this.kategoria = kategoria;
		this.user = user;
	}
	
	public Integer getIdEskaera() {
		return idEskaera;
	}

	public String getProduktuIzena() {
		return produktuIzena;
	}

	public boolean isBought() {
		return bought;
	}
	
	public void setBought() {
		this.bought=true;
	}
	
	public String getKategoria() {
		return kategoria;
	}

	public Eskaintza addEskaintza(String erantzunMezua, float prezioa, User user) {
		Eskaintza eskaintza = new Eskaintza(erantzunMezua, prezioa, this, user);
		eskaintzak.add(eskaintza);
		return eskaintza;
	}
	
	public String toString(){
		return idEskaera+";"+produktuIzena+ ";" + bought + ";" + kategoria;
	}
	
	
		
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Eskaera other = (Eskaera) obj;
		if (!this.idEskaera.equals(other.idEskaera))
			return false;
		return true;
	}
	
	
	
	
}

