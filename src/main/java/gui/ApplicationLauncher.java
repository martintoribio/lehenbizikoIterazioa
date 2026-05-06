package gui;

import java.awt.Color;
import java.net.URL;
import java.util.Locale;

import javax.swing.UIManager;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import configuration.ConfigXML;
import dataAccess.DataAccess;
import businessLogic.BLFacade;
import businessLogic.BLFacadeImplementation;


public class ApplicationLauncher {

	public static void main(String[] args) {

		try {
			UIManager.setLookAndFeel(new com.formdev.flatlaf.FlatLightLaf());
			java.io.InputStream is = ApplicationLauncher.class.getResourceAsStream("/wallapop-theme.properties");
			if (is != null) {
				java.util.Properties props = new java.util.Properties();
				props.load(is);
				java.util.Map<String, String> map = new java.util.HashMap<>();
				for (String key : props.stringPropertyNames()) {
				    map.put(key, props.getProperty(key));
				}
				com.formdev.flatlaf.FlatLaf.setGlobalExtraDefaults(map);
			}
		} catch (Exception e) {
			System.out.println("Theme charging error: " + e.toString());
		}

		ConfigXML c = ConfigXML.getInstance();
		Locale.setDefault(new Locale(c.getLocale()));

		MainGUI a = new MainGUI();
		a.setVisible(true);

		try {
			BLFacade appFacadeInterface;

			if (c.isBusinessLogicLocal()) {
				DataAccess da = new DataAccess();
				appFacadeInterface = new BLFacadeImplementation(da);
			} else {
				String serviceName = "http://" + c.getBusinessLogicNode() + ":" + c.getBusinessLogicPort() + "/ws/" + c.getBusinessLogicName() + "?wsdl";
				URL url = new URL(serviceName);
				QName qname = new QName("http://businessLogic/", "BLFacadeImplementationService");
				Service service = Service.create(url, qname);
				appFacadeInterface = service.getPort(BLFacade.class);
			}

			MainGUI.setBussinessLogic(appFacadeInterface);

		} catch (Exception e) {
			a.jLabelTitle.setText("Error: " + e.toString());
			a.jLabelTitle.setForeground(Color.RED);
			System.out.println("Error in ApplicationLauncher: " + e.toString());
		}
	}
}
