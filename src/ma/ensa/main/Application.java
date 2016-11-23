package ma.ensa.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import ma.ensa.model.Client;

public class Application {
	public static void main(String[] args){
		ApplicationContext context = new ClassPathXmlApplicationContext("ma/ensa/model/SpringContext.xml");
		Client cl = (Client) context.getBean("Client");
		cl.setNom("Younes NAJA");
		cl.getCp().setSolde(1000);
		cl.getCp().debiter(500);
	}
}
