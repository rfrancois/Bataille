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
		// Joueur gagnant ou joueur précédent
		Joueur joueurGagnant = null;
		// Cartes à gagner
		ArrayList<Carte> cartes = new ArrayList<Carte>();
		// Joueur ayant une aglité au cours d'une partie
		ArrayList<Joueur> joueursEgaux = new ArrayList<Joueur>();
		// Liste de joueurs
		ArrayList<Joueur> joueurs = new ArrayList<Joueur>();
		
		while(!Joueur.vainceurExiste()) {
			
			// Si il y a eu une égalité dans la partie précédente
			// ne faire jouer que les joueurs présents dans cette égalité
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
				// Si il n'y a pas de joueur actuellement gagnant, soit pas de joueur précédent
				if(joueurGagnant != null) {
					// Si c'est supérieur au joueur actuellement gagnant
					if(joueur.comparer(joueurGagnant)) {
						// Ne pas enlever de carte puisqu'on l'a déjà enlevé
						if(joueursEgaux.isEmpty()) cartes.add(joueurGagnant.enleverCarte());
						joueurGagnant = joueur;
					}
					// Si c'est égale
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
					// Si il n'y à aucun joueur gagnant, c'est donc qu'on est sur le premier tour de boucle
					// Le joueur gagnant devient le joueur du premier tour de boucle
					joueurGagnant = joueur;
				}
			}
			
			// Si la liste des joueurs égaux est pleine
			if(!joueursEgaux.isEmpty()) {
				// Enlever une carte à tous les joueurs présent dans la liste
				for(Joueur joueur : joueursEgaux) {
					// Ne pas enlever sa carte au joueur s'il ne lui en reste plus qu'une
					if(!joueur.quitteOuDouble()) {
						cartes.add(joueur.enleverCarte());
					}
				}
				System.out.println("Egalité !");
			}
			// Sinon faire gagner le joueur avec la carte la plus forte
			else {
				joueurGagnant.nouvellesCartes(cartes);
				System.out.println("Gagnant : " + joueurGagnant);
				cartes = new ArrayList<Carte>();
			}
			System.out.println("\nAppuyez sur Entrée pour continuer...");
			joueurGagnant = null;
			enter.nextLine();
		}
		
		System.out.println(Joueur.getVainceur() + " a gagné la partie !");
		
		
	}

}
