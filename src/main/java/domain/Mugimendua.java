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
public class Mugimendua implements Serializable {
	
	@Id
	@GeneratedValue
	private Integer id;
	private String deskribapena;
	private float diruAld;

	public Mugimendua() {
		super();
	}

	public Mugimendua(String deskribapena, float diruAld) {
		this.deskribapena = deskribapena;
		this.diruAld = diruAld;
	}

	public String getDeskribapena() {
		return deskribapena;
	}

	public void setDeskribapena(String deskribapena) {
		this.deskribapena = deskribapena;
	}

	public float getDiruAld() {
		return diruAld;
	}

	public void setDiruAld(int diruAld) {
		this.diruAld = diruAld;
	}

	public String toString() {
		return deskribapena + ";" + diruAld + ";";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Mugimendua other = (Mugimendua) obj;
		if (!this.deskribapena.equals(other.deskribapena))
			return false;
		return true;
	}
}
