import java.util.ArrayList;
import java.util.Scanner;


public class Bataille {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		new Joueur("Romain");
		new Joueur("Test");
		new Joueur("Trol");
		
		
		Pioche pioche = new Pioche();
				
		pioche.distribuerCartes(Joueur.getJoueurs());
		
		Scanner enter = new Scanner(System.in);
		// Joueur gagnant ou joueur pr�c�dent
		Joueur joueurGagnant = null;
		// Cartes � gagner
		ArrayList<Carte> cartes = new ArrayList<Carte>();
		// Joueur ayant une aglit� au cours d'une partie
		ArrayList<Joueur> joueursEgaux = new ArrayList<Joueur>();
		// Liste de joueurs
		ArrayList<Joueur> joueurs = new ArrayList<Joueur>();
		
		while(!Joueur.vainceurExiste()) {
			
			// Si il y a eu une �galit� dans la partie pr�c�dente
			// ne faire jouer que les joueurs pr�sents dans cette �galit�
			if(!joueursEgaux.isEmpty()) {
				joueurs = joueursEgaux;
				joueursEgaux = new ArrayList<Joueur>();
			}
			// Sinon appliquer la liste normale
			else {
				joueurs = Joueur.getJoueurs();
			}
			for(Joueur joueur: joueurs) {
				System.out.println(joueur.devoilerCarte() + " pour " + joueur.toString());
				// Si il n'y a pas de joueur actuellement gagnant, soit pas de joueur pr�c�dent
				if(joueurGagnant != null) {
					// Si c'est sup�rieur au joueur actuellement gagnant
					if(joueur.comparer(joueurGagnant)) {
						// Ne pas enlever de carte puisqu'on l'a d�j� enlev�
						if(joueursEgaux.isEmpty()) cartes.add(joueurGagnant.enleverCarte());
						joueurGagnant = joueur;
					}
					// Si c'est �gale
					else if(joueur.egales(joueurGagnant)) {
						// On sauvegarde les deux joueurs sur le premier tour de boucle
						// Pour les autres tours, on n'en sauvegarde qu'un seul
						if(!joueursEgaux.contains(joueurGagnant)) {
							joueursEgaux.add(joueurGagnant);
							// Ne pas enlever sa carte au joueur s'il ne lui en reste plus qu'une
							if(!joueurGagnant.quitteOuDouble()) cartes.add(joueurGagnant.enleverCarte());
						}
						joueursEgaux.add(joueur);
						// Ne pas enlever sa carte au joueur s'il ne lui en reste plus qu'une
						if(!joueur.quitteOuDouble()) cartes.add(joueur.enleverCarte());
					}
					// Else prevJoueur reste comme il est
					else {
						// Ne pas enelver de carte si la liste existe
						if(joueursEgaux.isEmpty()) cartes.add(joueur.enleverCarte());
					}
				}
				else {
					// Si il n'y � aucun joueur gagnant, c'est donc qu'on est sur le premier tour de boucle
					// Le joueur gagnant devient le joueur du premier tour de boucle
					joueurGagnant = joueur;
				}
			}
			
			// Si la liste des joueurs �gaux est pleine
			if(!joueursEgaux.isEmpty()) {
				// Enlever une carte � tous les joueurs pr�sent dans la liste
				for(Joueur joueur : joueursEgaux) {
					// Ne pas enlever sa carte au joueur s'il ne lui en reste plus qu'une
					if(!joueur.quitteOuDouble()) {
						cartes.add(joueur.enleverCarte());
					}
				}
				System.out.println("Egalit� !");
			}
			// Sinon faire gagner le joueur avec la carte la plus forte
			else {
				joueurGagnant.nouvellesCartes(cartes);
				System.out.println("Gagnant : " + joueurGagnant);
				cartes = new ArrayList<Carte>();
			}
			System.out.println("\nAppuyez sur Entr�e pour continuer...");
			joueurGagnant = null;
			enter.nextLine();
		}
		
		System.out.println(Joueur.getVainceur() + " a gagn� la partie !");
		
		
	}

}
