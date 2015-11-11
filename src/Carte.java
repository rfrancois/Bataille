
public class Carte {

	private static String[] familles = {"Pique", "Carreau", "Coeur", "Trefle"};
	private static String[] valeurs = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "Valet", "Dame", "Roi", "As"};
	private int famille;
	private int valeur;
	
	public Carte(int famille, int valeur) {
		this.famille = famille;
		this.valeur = valeur;
	}
	
	public int getFamille() {
		return famille;
	}

	public int getValeur() {
		return valeur;
	}

	@Override
	public String toString() {
		return valeurs[valeur] + " de " + familles[famille];
	}

	
}
