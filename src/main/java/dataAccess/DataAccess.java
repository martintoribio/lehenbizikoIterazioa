package dataAccess;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;
import javax.jws.WebMethod;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import configuration.ConfigXML;
import configuration.UtilDate;
import domain.User;
import domain.Arduraduna;
import domain.Mugimendua;
import domain.Salaketa;
import domain.Sale;
import domain.Txartela;
import domain.User;
import exceptions.FileNotUploadedException;
import exceptions.MustBeLaterThanTodayException;
import exceptions.NahikoDirurikEzException;
import exceptions.SaleAlreadyExistException;
import exceptions.TxartelOkerraException;

/**
 * It implements the data access to the objectDb database
 */
public class DataAccess {
	private EntityManager db;
	private EntityManagerFactory emf;
	private static final int baseSize = 160;

	private static final String basePath = "src/main/resources/images/";

	ConfigXML c = ConfigXML.getInstance();

	public DataAccess() {
		if (c.isDatabaseInitialized()) {
			String fileName = c.getDbFilename();

			File fileToDelete = new File(fileName);
			if (fileToDelete.delete()) {
				File fileToDeleteTemp = new File(fileName + "$");
				fileToDeleteTemp.delete();
				System.out.println("File deleted");
			} else {
				System.out.println("Operation failed");
			}
		}
		open();
		if (c.isDatabaseInitialized())
			initializeDB();
		System.out.println("DataAccess created => isDatabaseLocal: " + c.isDatabaseLocal() + " isDatabaseInitialized: "
				+ c.isDatabaseInitialized());

		close();

	}

	public DataAccess(EntityManager db) {
		this.db = db;
	}

	/**
	 * This method initializes the database with some products and users. This
	 * method is invoked by the business logic (constructor of
	 * BLFacadeImplementation) when the option "initialize" is declared in the tag
	 * dataBaseOpenMode of resources/config.xml file
	 */
	public void initializeDB() {

		db.getTransaction().begin();

		try {

			// Create users
			User user1 = new User("user1@gmail.com", "aurrera", null);
			User user2 = new User("user2@gmail.com", "aurrera", null);
			User user3 = new User("user3@gmail.com", "aurrera", null);

			// Create admins
			Arduraduna admin1 = new Arduraduna("i32546789","1234");
			Arduraduna admin2 = new Arduraduna("j12345678","1234");
			Arduraduna admin3 = new Arduraduna("m09876543","1234");
			Arduraduna admin4 = new Arduraduna("d10293847","1234");
			
			// Create products
			Date today = UtilDate.trim(new Date());

			user1.addSale("futbol baloia", "oso polita, gutxi erabilita", 2, 10, today, null);
			user1.addSale("salomon mendiko botak", "44 zenbakia, 3 ateraldi", 2, 20, today, null);
			user1.addSale("samsung 42\" telebista", "berria, erabili gabe", 1, 175, today, null);

			user2.addSale("imac 27", "7 urte, dena ondo dabil", 1, 200, today, null);
			user2.addSale("iphone 17", "oso gutxi erabilita", 2, 400, today, null);
			user2.addSale("orbea mendiko bizikleta", "29\" 10 urte, mantenua behar du", 3, 225, today, null);
			user2.addSale("polar kilor erlojua", "Vantage M, ondo dago", 3, 30, today, null);

			user3.addSale("sukaldeko mahaia", "1.8*0.8, 4 aulkiekin. Prezio finkoa", 3, 45, today, null);

			db.persist(user1);
			db.persist(user2);
			db.persist(user3);

			db.persist(admin1);
			db.persist(admin2);
			db.persist(admin3);
			db.persist(admin4);
			
			db.getTransaction().commit();
			System.out.println("Db initialized");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * This method creates/adds a product to an user
	 * 
	 * @param title           of the product
	 * @param description     of the product
	 * @param status
	 * @param selling         price
	 * @param category        of a product
	 * @param publicationDate
	 * @return Product
	 * @throws SaleAlreadyExistException if the same product already exists for the
	 *                                   user
	 */
	public Sale createSale(String title, String description, int status, float price, Date pubDate, String userEmail,
			File file) throws FileNotUploadedException, MustBeLaterThanTodayException, SaleAlreadyExistException {

		System.out.println(">> DataAccess: createProduct=> title= " + title + " user=" + userEmail);
		try {

			if (pubDate.before(UtilDate.trim(new Date()))) {
				throw new MustBeLaterThanTodayException(
						ResourceBundle.getBundle("Etiquetas").getString("DataAccess.ErrorSaleMustBeLaterThanToday"));
			}
			if (file == null)
				throw new FileNotUploadedException(
						ResourceBundle.getBundle("Etiquetas").getString("DataAccess.ErrorFileNotUploadedException"));

			db.getTransaction().begin();

			User user = db.find(User.class, userEmail);
			if (user.doesSaleExist(title)) {
				db.getTransaction().commit();
				throw new SaleAlreadyExistException(
						ResourceBundle.getBundle("Etiquetas").getString("DataAccess.SaleAlreadyExist"));
			}

			Sale sale = user.addSale(title, description, status, price, pubDate, file);
			// next instruction can be obviated

			db.persist(user);
			db.getTransaction().commit();
			System.out.println("sale stored " + sale + " " + user);

			System.out.println("hasta aqui");

			return sale;
		} catch (NullPointerException e) {
			e.printStackTrace();
			// TODO Auto-generated catch block
			db.getTransaction().commit();
			return null;
		}

	}

	/**
	 * This method retrieves all the products that contain a desc text in a title
	 * 
	 * @param desc the text to search
	 * @return collection of products that contain desc in a title
	 */
	public List<Sale> getSales(String desc) {
		System.out.println(">> DataAccess: getProducts=> from= " + desc);

		List<Sale> res = new ArrayList<Sale>();
		TypedQuery<Sale> query = db.createQuery("SELECT s FROM Sale s WHERE s.title LIKE ?1", Sale.class);
		query.setParameter(1, "%" + desc + "%");

		List<Sale> sales = query.getResultList();
		for (Sale sale : sales) {
			res.add(sale);
		}
		return res;
	}

	/**
	 * This method retrieves the products that contain a desc text in a title and
	 * the publicationDate today or before
	 * 
	 * @param desc the text to search
	 * @return collection of products that contain desc in a title
	 */
	public List<Sale> getPublishedSales(String desc, Date pubDate) {
		System.out.println(">> DataAccess: getProducts=> from= " + desc);

		List<Sale> res = new ArrayList<Sale>();
		TypedQuery<Sale> query = db.createQuery("SELECT s FROM Sale s WHERE s.title LIKE ?1 AND s.pubDate <=?2",
				Sale.class);
		query.setParameter(1, "%" + desc + "%");
		query.setParameter(2, pubDate);

		List<Sale> sales = query.getResultList();
		for (Sale sale : sales) {
			res.add(sale);
		}
		return res;
	}

	public void open() {

		String fileName = c.getDbFilename();
		if (c.isDatabaseLocal()) {
			emf = Persistence.createEntityManagerFactory("objectdb:" + fileName);
			db = emf.createEntityManager();
		} else {
			Map<String, String> properties = new HashMap<String, String>();
			properties.put("javax.persistence.jdbc.user", c.getUser());
			properties.put("javax.persistence.jdbc.password", c.getPassword());

			emf = Persistence.createEntityManagerFactory(
					"objectdb://" + c.getDatabaseNode() + ":" + c.getDatabasePort() + "/" + fileName, properties);
			db = emf.createEntityManager();
		}
		System.out.println("DataAccess opened => isDatabaseLocal: " + c.isDatabaseLocal());

	}

	public BufferedImage getFile(String fileName) {
		File file = new File(basePath + fileName);
		BufferedImage targetImg = null;
		try {
			targetImg = rescale(ImageIO.read(file));
		} catch (IOException ex) {
			// Logger.getLogger(MainAppFrame.class.getName()).log(Level.SEVERE, null, ex);
		}
		return targetImg;

	}

	public BufferedImage rescale(BufferedImage originalImage) {
		System.out.println("rescale " + originalImage);
		BufferedImage resizedImage = new BufferedImage(baseSize, baseSize, BufferedImage.TYPE_INT_RGB);
		Graphics2D g = resizedImage.createGraphics();
		g.drawImage(originalImage, 0, 0, baseSize, baseSize, null);
		g.dispose();
		return resizedImage;
	}

	@WebMethod
	public Arduraduna isLoginArd(String email, String password) {

		TypedQuery<Arduraduna> query = db.createQuery("SELECT a FROM Arduraduna a WHERE a.email =?1 AND a.pasahitza=?2",Arduraduna.class);
		query.setParameter(1, email);
		query.setParameter(2, password);
		if (!query.getResultList().isEmpty()) {
			return query.getResultList().get(0);
		}
		return null;
	}
	
	@WebMethod
	public User isLogin(String email, String password) {

		TypedQuery<User> query = db.createQuery("SELECT u FROM User u WHERE u.email =?1 AND u.pasahitza=?2",
				User.class);
		query.setParameter(1, email);
		query.setParameter(2, password);
		if (!query.getResultList().isEmpty()) {
			return query.getResultList().get(0);
		}
		return null;
	}

	@WebMethod
	public boolean isRegister(String email, String password, String tIzena, String tZenb, int PIN) {

		TypedQuery<User> query = db.createQuery("SELECT u FROM User u WHERE u.email =:email", User.class);
		query.setParameter("email", email);
		TypedQuery<Txartela> query2 = db.createQuery("SELECT t FROM Txartela t WHERE t.tZenb=:tZenb", Txartela.class);
		query2.setParameter("tZenb", tZenb);
		if (query.getResultList().isEmpty() && query2.getResultList().isEmpty()) {
			db.getTransaction().begin();
			Txartela txartela = new Txartela(tIzena, tZenb, PIN);
			User u = new User(email, password, txartela);
			db.persist(u);
			db.getTransaction().commit();
			return true;
		}
		return false;
	}

	@WebMethod
	public Sale buy(Sale s, String email) throws NahikoDirurikEzException {
		try {
			db.getTransaction().begin();
			// Ez da begiratu behar sale erosita dagoen, querySales-en bakarrik erosita ez
			// dauden sale-ak erakusten direlako
			Sale sale = db.find(Sale.class, s.getSaleNumber());
			User user = db.find(User.class, email);
			float saldoa = user.getSaldoa();
			float prezioa = sale.getPrice();
			if (saldoa-prezioa>=0) {
				sale.setBought(true);
				user.diruaKendu(prezioa);
				user.addBought(sale);
				String title = sale.getTitle();
				Mugimendua mugi = new Mugimendua("Erosketa: " + title, prezioa*(-1), user);
				user.addMugimendua(mugi);
				db.persist(mugi);
				db.getTransaction().commit();
				return sale;
			} else {
				throw new NahikoDirurikEzException(
						ResourceBundle.getBundle("Etiquetas").getString("DataAccess.NahikoDirurikEzException"));
			}

		} catch (NahikoDirurikEzException e) {
			db.getTransaction().rollback();
			throw e;
		} catch (Exception e) {
			db.getTransaction().rollback();
			return null;
		}
	}

	public boolean addFavorite(String email, Sale sale) {
		db.getTransaction().begin();
		try {
			User user = db.find(User.class, email);

			Sale managedSale = db.find(Sale.class, sale.getSaleNumber());
			if (managedSale != null) {
				user.addFavorite(managedSale);
				db.persist(user);
				db.getTransaction().commit();
				return true;
			}
		} catch (Exception e) {
			System.out.println("Errorea DataAccess.addFavorite-n: " + e.getMessage());
			e.printStackTrace();
		}

		if (db.getTransaction().isActive()) {
			db.getTransaction().rollback();
		}
		return false;
	}

	public List<Sale> getFavorites(String email) {
		User user = db.find(User.class, email);
		if (user != null) {
			return new ArrayList<Sale>(user.getFavorites());
		}
		return new ArrayList<Sale>();
	}

	public List<Sale> getBoughtSales(String email) {
		User user = db.find(User.class, email);
		if (user != null) {
			return new ArrayList<Sale>(user.getBoughtSales());
		}
		return new ArrayList<Sale>();
	}

	public float getSaldoa(String email) {
		User user = db.find(User.class, email);
		if (user != null) {
			float saldoa = user.getSaldoa();
			return saldoa;
		}
		return 0;
	}

	public void diruaAtera(String email, float diruKop, int PIN) throws NahikoDirurikEzException, TxartelOkerraException{
		db.getTransaction().begin();
		User user = db.find(User.class, email);
		Txartela t = user.getTxartela();
		boolean b1 = t.egiaztatuTxartela(PIN);
		if (b1) {
			boolean b2 = user.diruaKendu(diruKop);
			if (b2) {
				Mugimendua m = new Mugimendua("ATERA", diruKop*(-1), user);
				db.persist(m);
				user.addMugimendua(m);
				db.getTransaction().commit();
			} else {
				db.getTransaction().rollback();
				throw new NahikoDirurikEzException(ResourceBundle.getBundle("Etiquetas").getString("WalletGUI.NoMoney"));
			}
		} else {
			throw new TxartelOkerraException(ResourceBundle.getBundle("Etiquetas").getString("WalletGUI.PinError"));
		}
		
		
	}
	
	public List<Mugimendua> getMugimenduak(String email){
		User user = db.find(User.class, email);
		List<Mugimendua> mugimenduak = user.getMugimenduak();
		return mugimenduak;
		
	}
	

	public void diruaGehitu(String email, float diruKop, int PIN) throws TxartelOkerraException{
		db.getTransaction().begin();
		User user = db.find(User.class, email);
		Txartela t = user.getTxartela();
		boolean b = t.egiaztatuTxartela(PIN);
		if (b) {
			user.diruaGehitu(diruKop);
			Mugimendua m = new Mugimendua("GEHITU", diruKop, user);
			db.persist(m);
			user.addMugimendua(m);
			db.getTransaction().commit();
		} else {
			throw new TxartelOkerraException(ResourceBundle.getBundle("Etiquetas").getString("WalletGUI.PinError"));
		}
		
	}
	
	public Salaketa sortuSalaketa(String email, String deskribapena, int saleNumber) {
		db.getTransaction().begin();
		Sale sale = db.find(Sale.class, saleNumber);
		User user = db.find(User.class, email);
		if (sale!=null && user!=null) {
			Salaketa salaketa = new Salaketa(deskribapena, "aztertzeko", user, sale);
			user.addSalaketa(salaketa);
			db.persist(salaketa);
			db.getTransaction().commit();
			return salaketa;
		} else {
			db.getTransaction().rollback();
			return null;
		}
	}
	
	public List<Salaketa> getSalaketak(String email) {
		TypedQuery <Salaketa> query = db.createQuery("SELECT s FROM Salaketa s WHERE s.user.email=:email", Salaketa.class);
		query.setParameter("email", email);
		List<Salaketa> salaketak = query.getResultList();
		if (!salaketak.isEmpty()) {
			return salaketak;
		} else {
			return new ArrayList<Salaketa>();
		}
	}
	
	public List<Salaketa> getAztertzekoSalaketak() {
		TypedQuery <Salaketa> query = db.createQuery("SELECT s FROM Salaketa s WHERE s.egoera='aztertzeko'", Salaketa.class);	
		List<Salaketa> salaketak = query.getResultList();
		if(!salaketak.isEmpty()) {
			return salaketak;
		} else {
			return new ArrayList<Salaketa>();
		}
	}
	
	public boolean salaketaOnartu(Integer idSalaketa) {
		
		Salaketa salaketa = db.find(Salaketa.class, idSalaketa);
		salaketa.setEgoera("onartua");
		Sale sale = salaketa.getSale();
		if (sale!=null) {
			db.getTransaction().begin();
			db.remove(sale);
			db.getTransaction().commit();
			return true;
		} else {
			return false;
		}

	}
	
	public boolean salaketaEzeztatu(Integer idSalaketa) {
		Salaketa salaketa = db.find(Salaketa.class, idSalaketa);
		salaketa.setEgoera("ezeztatua");
		return true;
	}
	
	public void close() {
		db.close();
		System.out.println("DataAcess closed");
	}

	
}
