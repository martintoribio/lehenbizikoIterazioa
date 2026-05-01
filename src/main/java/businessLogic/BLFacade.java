package businessLogic;

import java.io.File;
import java.util.Date;
import java.util.List;

import domain.Arduraduna;
import domain.Mugimendua;
import domain.Salaketa;
import domain.Erreklamazioa;
import domain.Sale;
import domain.User;
import domain.Txartela;
import exceptions.FileNotUploadedException;
import exceptions.MustBeLaterThanTodayException;
import exceptions.SaleAlreadyExistException;
import exceptions.TxartelOkerraException;
import exceptions.NahikoDirurikEzException;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.awt.image.BufferedImage;
import java.awt.Image;

import gui.*;
/**
 * Interface that specifies the business logic.
 */
@WebService
public interface BLFacade  {
	  

	/**
	 * This method creates/adds a product to an user
	 * 
	 * @param title of the product
	 * @param description of the product
	 * @param status 
	 * @param selling price
	 * @param category of a product
	 * @param publicationDate
	 * @return Sale
	 */
   @WebMethod
	public Sale createSale(String title, String description, String kategoria, int status, float price, Date pubDate, String userEmail, File file) throws  FileNotUploadedException, MustBeLaterThanTodayException, SaleAlreadyExistException;
	
	
	/**
	 * This method retrieves the products that contain desc
	 * 
	 * @param desc the text to search
	 * @return collection of sales that contain desc 
	 */
	@WebMethod public List<Sale> getSales(String desc);
	
	/**
	 * 	 * This method retrieves the products that contain a desc text in a title and the publicationDate today or before
	 * 
	 * @param desc the text to search
	 * @param pubDate the date  of the publication date
	 * @return collection of sales that contain desc and published before pubDate
	 */
	@WebMethod public List<Sale> getPublishedSales(String desc, Date pubDate);

	
	/**
	 * This method calls the data access to initialize the database with some users and products.
	 * It is only invoked  when the option "initialize" is declared in the tag dataBaseOpenMode of resources/config.xml file
	 */	
	@WebMethod public void initializeBD();
	
		
	@WebMethod public Image downloadImage(String imageName);
	
	@WebMethod public User isLogin(String login, String password);
	@WebMethod public Arduraduna isLoginArd(String login, String password);
	@WebMethod public boolean isRegister(String login, String password, String tIzena, String tZenb, int PIN);
	@WebMethod public Sale buy(Sale s, String email) throws NahikoDirurikEzException;
	@WebMethod public List<Sale> getBoughtSales(String email);
	@WebMethod public List<Mugimendua> getMugimenduak(String email);
	@WebMethod public boolean addFavorite(String email, Sale sale);
	@WebMethod public List<Sale> getFavorites(String email);
	@WebMethod public float getSaldoa(String email);
	@WebMethod public void diruaAtera(String email, float diruKop, int pin) throws NahikoDirurikEzException, TxartelOkerraException;
	@WebMethod public void diruaGehitu(String email, float diruKop, int pin) throws TxartelOkerraException;
	@WebMethod public Salaketa sortuSalaketa(String email, String titulua, String deskribapena, int saleNumber); 
	@WebMethod public List<Salaketa> getSalaketak(String email);
	@WebMethod public List<Salaketa> getAztertzekoSalaketak ();
	@WebMethod public boolean salaketaOnartu(Integer idSalaketa);
	@WebMethod public boolean salaketaEzeztatu(Integer idSalaketa);	
	@WebMethod public Erreklamazioa sortuErreklamazioa(String email, String titulua, String deskribapena, int saleNumber); 
	@WebMethod public List<Erreklamazioa> getErreklamazioak(String email);
	@WebMethod public boolean erreklamazioaOnartu(Integer idErreklam);
	@WebMethod public void erreklamazioaEzeztatu(Integer idErreklam);
	@WebMethod public List<Erreklamazioa> getAztertzekoErreklamazioak();
	@WebMethod public List<String> kategoriakAldatu(String email, List<String> kategoriak);
}

