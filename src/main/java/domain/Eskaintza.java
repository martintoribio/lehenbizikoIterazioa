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
public class Eskaintza implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@XmlID
	@Id 
	private String erantzunMezua;
	private float prezioa;
	
	@ManyToOne(cascade=CascadeType.PERSIST)
	private User user;
	
	public Eskaintza() {
		super();
	}

	public Eskaintza(String erantzunMezua, float prezioa, User user) {
		this.erantzunMezua = erantzunMezua;
		this.prezioa = prezioa;
		this.user = user;
		
	}
	
	
	public String getErantzunMezua() {
		return erantzunMezua;
	}

	public float getPrezioa() {
		return prezioa;
	}
	

	
	
	public String toString(){
		return erantzunMezua+";"+prezioa;
	}
	
	
}

