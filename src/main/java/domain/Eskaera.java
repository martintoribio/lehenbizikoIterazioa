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

@XmlAccessorType(XmlAccessType.FIELD)
@Entity
public class Eskaera implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@XmlID
	@Id 
	private String idEskaera;
	private String produktuIzena;
	private String kategoria;
	private String egoera;

	
	@ManyToOne(cascade=CascadeType.PERSIST)
	private User user;
	
	@OneToMany(cascade=CascadeType.PERSIST)
	private Eskaintza eskaintza;
	
	
	public Eskaera() {
		super();
	}

	public Eskaera(String idEskaera, String produktuIzena, String kategoria, String egoera, User user) {
		this.idEskaera = idEskaera;
		this.produktuIzena = produktuIzena;
		this.kategoria = kategoria;
		this.egoera = egoera;
		this.user = user;
		
	}
	
	public String getIdEskaera() {
		return idEskaera;
	}

	public String getProduktuIzena() {
		return produktuIzena;
	}

	public String getKategoria() {
		return kategoria;
	}
	
	public String getEgoera() {
		return egoera;
	}

	
	
	public String toString(){
		return idEskaera+";"+produktuIzena+ ";" + kategoria + ";" + egoera;
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

