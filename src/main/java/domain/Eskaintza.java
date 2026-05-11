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
public class Eskaintza implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@XmlID
	@Id 
	@XmlJavaTypeAdapter(IntegerAdapter.class)
	@GeneratedValue
	private Integer idEskaintza;
	private String erantzunMezua;
	private float prezioa;
	private String egoera;
	
	@ManyToOne(cascade=CascadeType.PERSIST)
	private User user;
	
	@ManyToOne(cascade=CascadeType.PERSIST)
	private Eskaera eskaera;
	
	public Eskaintza() {
		super();
	}

	public Eskaintza(String erantzunMezua, float prezioa, Eskaera eskaera, User user) {
		this.erantzunMezua = erantzunMezua;
		this.prezioa = prezioa;
		this.user = user;
		this.eskaera = eskaera;
		this.egoera = "Ikusi gabe";
	}
	
	public Integer getIdEskaintza() {
		return idEskaintza;
	}
	
	public String getErantzunMezua() {
		return erantzunMezua;
	}

	public float getPrezioa() {
		return prezioa;
	}
	

	public Eskaera getEskaera() {
		return eskaera;
	}
	
	public User getEroslea() {
		return user;
	}
	
	public void setOnartua() {
		this.egoera = "onartua";
	}
	
	public void setEzeztatua() {
		this.egoera = "ezeztatua";
	}
	
	public String getEgoera() {
		return this.egoera;
	}
	
	public String toString(){
		return erantzunMezua+";"+prezioa;
	}
	
	
}

