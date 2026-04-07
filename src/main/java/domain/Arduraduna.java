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
public class Arduraduna implements Serializable {
	private static final long serialVersionUID = 1L;
	@XmlID
	@Id 
	private String email;
	private String pasahitza;
	
	public Arduraduna(String email, String pasahitza) {
		this.email = email;
		this.pasahitza = pasahitza;
		
	}
	
	public Arduraduna() {
		super();
	}
	
	public String getEmail() {
		return this.email;
	}
	
	public String getPasahitza() {
		return this.pasahitza;
	}
}
