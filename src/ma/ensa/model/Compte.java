package ma.ensa.model;

import org.springframework.stereotype.Component;

@Component("Compte")
public class Compte {
	private double solde;

	public double getSolde() {
		return solde;
	}

	public void setSolde(double solde) {
		this.solde = solde;
	}

	public void debiter(double mt) {
		try {
			Thread.sleep(954);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		this.solde -= mt;
	}

	public void approvisionner(double mt) {
		try {
			Thread.sleep(415);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		this.solde += mt;
	}

}
