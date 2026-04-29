package businessLogic;
import java.io.File;
import java.util.Date;
import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebService;

import dataAccess.DataAccess;
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

import java.awt.image.BufferedImage;
import java.awt.Image;
import javax.imageio.ImageIO;
import java.io.IOException;


/**
 * It implements the business logic as a web service.
 */
@WebService(endpointInterface = "businessLogic.BLFacade")
public class BLFacadeImplementation  implements BLFacade {
	 private static final int baseSize = 160;

		private static final String basePath="src/main/resources/images/";
	DataAccess dbManager;

	public BLFacadeImplementation()  {		
		System.out.println("Creating BLFacadeImplementation instance");
		dbManager=new DataAccess();		
	}
	
    public BLFacadeImplementation(DataAccess da)  {
		System.out.println("Creating BLFacadeImplementation instance with DataAccess parameter");
		dbManager=da;		
	}
    

	/**
	 * {@inheritDoc}
	 */
   @WebMethod
	public Sale createSale(String title, String description, String kategoria, int status, float price, Date pubDate, String userEmail, File file) throws  FileNotUploadedException, MustBeLaterThanTodayException, SaleAlreadyExistException {
		dbManager.open();
		Sale product=dbManager.createSale(title, description, kategoria, status, price, pubDate, userEmail, file);		
		dbManager.close();
		return product;
   };
	
   /**
    * {@inheritDoc}
    */
	@WebMethod 
	public List<Sale> getSales(String desc){
		dbManager.open();
		List<Sale>  rides=dbManager.getSales(desc);
		dbManager.close();
		return rides;
	}
	
	/**
	    * {@inheritDoc}
	    */
		@WebMethod 
		public List<Sale> getPublishedSales(String desc, Date pubDate) {
			dbManager.open();
			List<Sale>  rides=dbManager.getPublishedSales(desc,pubDate);
			dbManager.close();
			return rides;
		}
	/**
	    * {@inheritDoc}
	    */
	@WebMethod public BufferedImage getFile(String fileName) {
		return dbManager.getFile(fileName);
	}

    
	public void close() {
		DataAccess dB4oManager=new DataAccess();
		dB4oManager.close();

	}

	/**
	 * {@inheritDoc}
	 */
    @WebMethod	
	 public void initializeBD(){
    	dbManager.open();
		dbManager.initializeDB();
		dbManager.close();
	}
    /**
	 * {@inheritDoc}
	 */
    @WebMethod public Image downloadImage(String imageName) {
        File image = new File(basePath+imageName);
        try {
            return ImageIO.read(image);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    @WebMethod public User isLogin(String email, String password) {
		dbManager.open();
		User b = dbManager.isLogin(email, password);
		dbManager.close();
		return b;
	}
    
    @WebMethod public Arduraduna isLoginArd(String email, String password) {
		dbManager.open();
		Arduraduna b = dbManager.isLoginArd(email, password);
		dbManager.close();
		return b;
	}
    
    @WebMethod public boolean isRegister(String email, String password, String tIzena, String tZenb, int PIN) {
		dbManager.open();
		boolean b = dbManager.isRegister(email, password, tIzena, tZenb, PIN);
		dbManager.close();
		return b;
	}
    @WebMethod public Sale buy(Sale s, String email) throws NahikoDirurikEzException{
		dbManager.open();
		Sale sale = dbManager.buy(s, email);
		dbManager.close();
		return sale;
	}

    @WebMethod 
    public boolean addFavorite(String email, Sale sale) {
        dbManager.open();
        boolean success = dbManager.addFavorite(email, sale);
        dbManager.close();
        return success;
    }

    @WebMethod 
    public List<Sale> getFavorites(String email) {
        dbManager.open();
        List<Sale> favorites = dbManager.getFavorites(email);
        dbManager.close();
        return favorites;
    }
    @WebMethod 
    public List<Sale> getBoughtSales(String email){
    	dbManager.open();
    	List<Sale> boughtSales = dbManager.getBoughtSales(email);
    	dbManager.close();
    	return boughtSales;
    }
    
    @WebMethod 
    public float getSaldoa(String email){
    	dbManager.open();
    	float saldoa = dbManager.getSaldoa(email);
    	dbManager.close();
    	return saldoa;
    }
    
    @WebMethod
    public List<Mugimendua> getMugimenduak(String email){
    	dbManager.open();
    	List<Mugimendua> mugimenduak = dbManager.getMugimenduak(email);
    	dbManager.close();
    	return mugimenduak;
    	
    }
    
    @WebMethod 
    public void diruaAtera(String email, float diruKop, int PIN) throws NahikoDirurikEzException, TxartelOkerraException {
    	dbManager.open();
    	dbManager.diruaAtera(email, diruKop, PIN);
    	dbManager.close();
    }
    @WebMethod 
    public void diruaGehitu(String email, float diruKop, int PIN) throws TxartelOkerraException{
        dbManager.open();
        dbManager.diruaGehitu(email, diruKop, PIN);
        dbManager.close();
    }
    
    @WebMethod
    public Salaketa sortuSalaketa(String email, String titulua, String deskribapena, int saleNumber) {
    	dbManager.open();
    	Salaketa salaketa = dbManager.sortuSalaketa(email, titulua, deskribapena, saleNumber);
    	dbManager.close();
    	return salaketa;
    }
    
    @WebMethod
    public List<Salaketa> getSalaketak(String email) {
    	dbManager.open();
    	List<Salaketa> salaketak = dbManager.getSalaketak(email);
    	dbManager.close();
    	return salaketak;
    }
    
    @WebMethod
    public List<Salaketa> getAztertzekoSalaketak() {
    	dbManager.open();
    	List<Salaketa> salaketak = dbManager.getAztertzekoSalaketak();
    	dbManager.close();
    	return salaketak;
    }
    
    @WebMethod
    public boolean salaketaOnartu(Integer idSalaketa) {
    	dbManager.open();
    	boolean b = dbManager.salaketaOnartu(idSalaketa);
    	dbManager.close();
    	return b;
    }
    
    @WebMethod
    public boolean salaketaEzeztatu(Integer idSalaketa) {
    	dbManager.open();
    	boolean b = dbManager.salaketaEzeztatu(idSalaketa);
    	dbManager.close();
    	return b;
    }
    @WebMethod
    public Erreklamazioa sortuErreklamazioa(String email, String titulua, String deskribapena, int saleNumber) {
    	dbManager.open();
    	Erreklamazioa erreklamazio = dbManager.sortuErreklamazioa(email, titulua, deskribapena, saleNumber);
    	dbManager.close();
    	return erreklamazio;
    }
    
    @WebMethod
    public List<Erreklamazioa> getErreklamazioak(String email) {
    	dbManager.open();
    	List<Erreklamazioa> erreklamazioak = dbManager.getErreklamazioak(email);
    	dbManager.close();
    	return erreklamazioak;
    }
    
    @WebMethod
    public boolean erreklamazioaOnartu(Integer idErreklam) {
    	dbManager.open();
    	boolean b = dbManager.erreklamazioaOnartu(idErreklam);
    	dbManager.close();
    	return b;
    }
    
    @WebMethod
    public void erreklamazioaEzeztatu(Integer idErreklam) {
    	dbManager.open();
    	dbManager.erreklamazioaEzeztatu(idErreklam);
    	dbManager.close();
    }
	@WebMethod 
	public List<Erreklamazioa> getAztertzekoErreklamazioak(){
		dbManager.open();
		List<Erreklamazioa> erreklamazioak = dbManager.getAztertzekoErreklamazioak();
		dbManager.close();
		return erreklamazioak;
	}

}

