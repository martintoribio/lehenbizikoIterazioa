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
public class Salaketa implements Serializable {
	
	private static final long serialVersionUID = 1L;
	@XmlID
	@Id 
	@XmlJavaTypeAdapter(IntegerAdapter.class)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer idSalaketa;
	
	private String deskribapena;
	private String egoera;
	
	@XmlIDREF
	@ManyToOne
	private User user;
	
	@XmlIDREF
	@ManyToOne
	private Sale sale;
	
	
	public Salaketa() {
		super();
	}
	public Salaketa(String deskribapena, String egoera, User user, Sale sale) {
		this.deskribapena=deskribapena;
		this.egoera=egoera;
		this.user = user;
		this.sale = sale;
	}
	public String getDeskribapena() {
		return deskribapena;
	}
	public void setDeskribapena(String deskribapena) {
		this.deskribapena=deskribapena;
	}
	public String getEgoera() {
		return egoera;
	}
	public void setEgoera(String egoera) {
		this.egoera=egoera;
	}
	
	public Sale getSale() {
		return sale;
	}
	public User getUser() {
		return user;
	}
	
	public String toString() {
		return deskribapena+";"+egoera+";";
	}
	
	
	@Override
	public boolean equals(Object obj) {
	    if (this == obj)
	        return true;
	    if (obj == null)
	        return false;
	    if (getClass() != obj.getClass())
	        return false;
	    Salaketa other = (Salaketa) obj;
	    if (!this.deskribapena.equals(other.deskribapena))
	        return false;
	    return true;
	}
}
