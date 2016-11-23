package ma.ensa.model;

public class Compte {
	private double solde;

	public double getSolde() {
		return solde;
	}

	public void setSolde(double solde) {
		this.solde = solde;
	}

	public void debiter(double mt) {
		this.solde -= mt;
	}

	public void approvisionner(double mt) {
		this.solde += mt;
	}

}
