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
public class Notifikazioa implements Serializable {
	
	private static final long serialVersionUID = 1L;
	@XmlID
	@Id 
	@XmlJavaTypeAdapter(IntegerAdapter.class)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer idNotif;
	
	private String kategoria;
	private String mezua;
	
	@XmlIDREF
	@ManyToOne
	private User user;
	
	
	public Notifikazioa() {
		super();
	}

	public Notifikazioa(String kategoria, String mezua, User user) {
		this.kategoria = kategoria;
		this.mezua = mezua;
		this.user = user;
	}

	public Integer getIdNotifikazioa() {
		return idNotif;
	}
	
	public String getKategoria() {
		return kategoria;
	}
	
	public String getMezua() {
		return mezua;
	}

	public void setMezua(String mezua) {
		this.mezua = mezua;
	}

	public User getUser() {
		return user;
	}
	
	public String toString() {
		return mezua + ";" + kategoria + ";";
	}
	
	@Override
	public boolean equals(Object obj) {
	    if (this == obj)
	        return true;
	    if (obj == null)
	        return false;
	    if (getClass() != obj.getClass())
	        return false;
	    Notifikazioa other = (Notifikazioa) obj;
	    if (!this.idNotif.equals(other.idNotif))
	        return false;
	    return true;
	}
}
