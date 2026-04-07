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
public class Erreklamazioa implements Serializable {
	
	private static final long serialVersionUID = 1L;
	@XmlID
	@Id 
	@XmlJavaTypeAdapter(IntegerAdapter.class)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer idErreklam;
	
	private String deskribapena;
	private String egoera;
	
	@XmlIDREF
	@ManyToOne
	private User user;
	
	@XmlIDREF
	@ManyToOne
	private Sale sale;
	
	
	public Erreklamazioa() {
		super();
	}

	public Erreklamazioa(String deskribapena, String egoera, User user, Sale sale) {
		this.deskribapena = deskribapena;
		this.egoera = egoera;
	}

	public String getDeskribapena() {
		return deskribapena;
	}

	public void setDeskribapena(String deskribapena) {
		this.deskribapena = deskribapena;
	}

	public String getEgoera() {
		return egoera;
	}

	public void setEgoera(String egoera) {
		this.egoera = egoera;
	}

	public String toString() {
		return deskribapena + ";" + egoera + ";";
	}
	
	@Override
	public boolean equals(Object obj) {
	    if (this == obj)
	        return true;
	    if (obj == null)
	        return false;
	    if (getClass() != obj.getClass())
	        return false;
	    Erreklamazioa other = (Erreklamazioa) obj;
	    if (!this.deskribapena.equals(other.deskribapena))
	        return false;
	    return true;
	}
}
