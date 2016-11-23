package ma.ensa.main;

import ma.ensa.model.Compte;

public class Application {
	public static void main(String[] args){
		Compte c = new Compte();
		c.setSolde(1000);
		c.debiter(500);
	}
}
