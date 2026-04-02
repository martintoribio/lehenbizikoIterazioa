package domain;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;
import javax.persistence.ManyToMany;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlIDREF;

@XmlAccessorType(XmlAccessType.FIELD)
@Entity
public class Txartela implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@XmlID
	@Id 
	private String tZenb;
	private String tIzena;
	private int PIN;
	
	public Txartela() {
		super();
	}

	public Txartela(String tIzena, String tZenb, int PIN) {
		this.tIzena = tIzena;
		this.tZenb = tZenb;
		this.PIN = PIN;
	}
	
	
	public String getTIzena() {
		return tIzena;
	}

	public String getTZenb() {
		return tZenb;
	}

	public int getPIN() {
		return PIN;
	}
	

	
	
	public String toString(){
		return tIzena+";"+tZenb+ ";" + PIN;
	}
	
	
		
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Txartela other = (Txartela) obj;
		if (!this.tIzena.equals(other.tIzena))
			return false;
		return true;
	}
	
	public boolean isSameTxartela(String tIzena, String tZenb, int PIN) {
		return (this.tIzena.equals(tIzena) && this.tZenb.equals(tZenb) && this.PIN==PIN);
	}
	
	
	
}
