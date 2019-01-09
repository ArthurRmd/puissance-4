import MG2D.*;
import MG2D.geometrie.*;



public class Jeu {
    Jeu game;
    public Plateau parti;
    private InterfaceGraphique graphique;



    public Jeu( boolean jeuAvecMenuDemarrer ) {


        parti = new Plateau(7, 6);
        graphique = new InterfaceGraphique(parti , jeuAvecMenuDemarrer);
        graphique.refreshPion(parti);

        int numJoueur = (((int) (Math.random()*100)) % 2 ) + 1;    // Renvoi un nombre aléatoire 1 ou 2 : Permet de choisir aléatoirement un joueur
        int numJoueurDepart = numJoueur;
        int numColonne = 3;
        int nbTour = 0;
        int nbTourMax = (parti.tailleHauteur()) * ( parti.tailleLargeur());
        boolean partieTerminé = false;
        boolean partieEgalite = false;


        graphique.textPartie("Bonne chance");



        while ( !partieTerminé) {


            numColonne = graphique.selectionPion(numJoueur , parti , numColonne);


            if ( parti.colonneVide(numColonne ) && nbTour <= nbTourMax) {

                parti.ajoutPion(numColonne, numJoueur);
                graphique.refreshPion(parti);
                nbTour ++;
                numJoueur = parti.changeJoueur( numJoueur);


                if ( nbTour == nbTourMax) {

                    graphique.textPartie("Fin Partie");
                    partieTerminé = true;
                    partieEgalite = true;
            }

                if (parti.gagner(parti)) {

                    graphique.textPartie("Fin Partie");
                    partieTerminé = true;
                    graphique.interfaceFinJeu(parti.numJoueurVainqueur(nbTour, numJoueurDepart));

                }


                if ( partieEgalite) {

                    graphique.afficheLigneCroix(parti);
                    graphique.interfaceFinJeu(parti.numJoueurVainqueur(nbTour, numJoueurDepart));
                    partieTerminé = true;


                }

            }
        }




    }


































































}