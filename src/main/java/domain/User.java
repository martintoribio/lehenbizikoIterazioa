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
public class User implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@XmlID
	@Id 
	private String email;
	
	private String pasahitza;
	
	@OneToOne(cascade=CascadeType.PERSIST)
	private Txartela txartela;
	
	private float saldoa;
	
	@XmlIDREF
	@OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.PERSIST)
	private List<Sale> sales=new ArrayList<Sale>();
	@XmlIDREF
	@ManyToMany(fetch=FetchType.EAGER, cascade=CascadeType.PERSIST)
	private List<Sale> favorites = new ArrayList<Sale>();
	@XmlIDREF
	@OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.PERSIST)
	private List<Sale> boughtSales = new ArrayList<Sale>();
	@XmlIDREF
	@OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.PERSIST)
	private List<Mugimendua> mugimenduak = new ArrayList<Mugimendua>();
	@XmlIDREF
	@OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.PERSIST)
	private List<Salaketa> salaketak = new ArrayList<Salaketa>();
	@XmlIDREF
	@OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.PERSIST)
	private List<Erreklamazioa> erreklamazioak = new ArrayList<Erreklamazioa>();
	
	
	public User() {
		super();
	}

	public User(String email, String pasahitza, Txartela txartela) {
		this.email = email;
		this.pasahitza=pasahitza;
		this.txartela = txartela;
		saldoa = 50;
	}
	
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPasahitza() {
		return pasahitza;
	}
	
	public void setPasahitza(String pasahitza) {
		this.pasahitza = pasahitza;
	}
	
	
	public String toString(){
		return email+";"+sales;
	}
	
	/**
	 * This method creates/adds a sale to a seller
	 * 
	 * @param title of the sale
	 * @param description of the sale
	 * @param status 
	 * @param selling price
	 * @param publicationDate
	 * @return Sale
	 */
	
	


	public Sale addSale(String title, String description, int status, float price,  Date pubDate, File file)  {
		
		Sale sale=new Sale(title, description, status, price,  pubDate, file, this);
        sales.add(sale);
        return sale;
	}
	/**
	 * This method checks if the ride already exists for that driver
	 * 
	 * @param from the origin location 
	 * @param to the destination location 
	 * @param date the date of the ride 
	 * @return true if the ride exists and false in other case
	 */
	public boolean doesSaleExist(String title)  {	
		for (Sale s:sales)
			if ( s.getTitle().compareTo(title)==0 )
			 return true;
		return false;
	}
		
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (email != other.email)
			return false;
		return true;
	}

	public List<Sale> getFavorites() {
	    return favorites;
	}

	public void addFavorite(Sale sale) {
	    if (!favorites.contains(sale)) {
	        favorites.add(sale);
	    }
	}
	
	public List<Sale> getBoughtSales() {
		return boughtSales;
	}
	
	public void addBought(Sale s) {
		boughtSales.add(s);
	}
	
	public Txartela getTxartela() {
		return txartela;
	}
	
	public float getSaldoa() {
		return saldoa;
	}
	
	public void setSaldoa(float saldoBerria) {
		this.saldoa = saldoBerria;
	}
	
	public void diruaGehitu(float diruKop) {
		this.saldoa+=diruKop;
	}
	
	public boolean diruaKendu(float diruKop) {
		if (saldoa-diruKop>=0) {
			this.saldoa-=diruKop;
			return true;
		} else {
			return false;
		}
	}
	
	public void addMugimendua(Mugimendua mugi) {
		mugimenduak.add(mugi);
	}
	
	public List<Mugimendua> getMugimenduak() {
		return mugimenduak;
	}
	
}
