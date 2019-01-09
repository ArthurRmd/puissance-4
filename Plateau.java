
import MG2D.*;
import MG2D.geometrie.*;

public class Plateau {

    private Ligne[] tableau;







            // Constructeur par Défaut //

    public Plateau() {

        tableau = new Ligne[1];
        tableau[0] = new Ligne();
    }


    public  Plateau ( int nb_colonne , int nb_ligne) {

        tableau = new Ligne[nb_ligne];
        for ( int i=0 ; i< nb_ligne; i++)  {
            tableau[i] = new Ligne(nb_colonne);
        }
    }



            // Accesseur  //


    public Case getXY ( int x , int y ) {

        return tableau[y].getX(x);
    }


    public void ajoutPion ( int num_col , int num_joueur) {


        boolean vide = false;
        Case temp = new Case();
        int i = (tableau.length) - 1;


        while ( i >= 0 && vide == false) {

            temp = this.getXY(num_col, i);

            if ( temp.getContenu() == 0) {

                vide = true;
                i++;

            }

            i--;
        }


        if ( i >= 0) {

            tableau[i].setX(num_col, num_joueur);

        }
        else{

            System.out.println("Erreur, colonne pleine");

        }

    }


    public boolean colonneVide ( int numColonne) {

        boolean vide = false;


        if ( ( this.getXY(numColonne, 0).getContenu() ) == 0) {
            return true;

        }
        else{
            return false;
        }



    }


    public String toString() {

        int taille1 = tableau.length;
        int taille2 = tableau[1].getTaille();
        int temp = 0;
        String str = "";


        for ( int i=0 ; i < taille1 ; i++) {

            for ( int j=0 ; j < taille2 ; j++) {

                str += ("|");
                str += (tableau[i].getX(j).getContenu());

            }

            str += ("|\n");

        }

        return str;
    }

    public int tailleLargeur () {
        return tableau[0].getTaille();
    }

    public int tailleHauteur () {
        return tableau.length;
    }


    public boolean gagner (Plateau parti){

        boolean gagner = false;
        int i=0;
        int j=0;
        Ligne ligneVerti = new Ligne(parti.tailleHauteur());



        // ----------------------- Verification horizontale --------------------------

        for ( i=0 ; i< parti.tailleHauteur(); i++) {

            if ( tableau[i].rechercheValeursContigues(4) ) {

                System.out.println("Gagner horizontale");
                gagner = true;

            }
        }




        // ------------------------ Verif Vertical  ------------------------------

        for (i=0 ; i< parti.tailleLargeur() ; i++ ) {

            for ( j=0 ; j< parti.tailleHauteur()  ; j++) {

                ligneVerti.setX(j , (parti.getXY(i, j).getContenu()));

            }

            if ( ligneVerti.rechercheValeursContigues(4)) {

                System.out.println("Gagner verticale ");
                gagner = true;

            }


        }


        /*---------------------Verif Diagonale ----------------------------------- */



        // Permet de savoir de la longueur max de la diagonal

        int longueurMaxDiagonal = 0;


        if (tailleLargeur() > tailleHauteur()) {

            longueurMaxDiagonal = ( tailleLargeur() ) -1;

        }


        else {

            longueurMaxDiagonal = ( tailleHauteur() ) -1;

        }


        int valeurMini = 4;
        int compteur = 3;
        int compteurCase = 3;
        int y =0;
        int z= 0;


        Ligne ligneDiagonale = new Ligne(longueurMaxDiagonal);
        ligneDiagonale.initNombre(0);


        int nombreDiagonale = (tailleLargeur()-3) + (tailleHauteur() -4);


        j=0;

        for (i=0 ; i < nombreDiagonale  ; i++) {

            ligneDiagonale.initNombre(0);


            for ( y=0 ; y < valeurMini ; y++) {

                ligneDiagonale.setX(y, (parti.getXY( compteurCase - y, ( ( ( tailleHauteur() -1) ) - y ) - z ).getContenu()));

            }


            compteur++;

            if ( compteur < tailleLargeur()  -1 ) {

                compteurCase++;
                valeurMini++;

            }


            else if (compteur == ( tailleLargeur() - 1 ) ) {

                compteurCase++;

            }


            else {

                z++;
                valeurMini--;

            }



            if ( ligneDiagonale.rechercheValeursContigues(4)) {

                gagner = true;

            }
        }






        /*---------------------Verif Diagonale 2 ---------------*/





         valeurMini = 4;
         compteur = 3;
         compteurCase = 3;
         y =0;
         z= 0;


        Ligne ligneDiagonale2 = new Ligne(longueurMaxDiagonal);

        ligneDiagonale2.initNombre(0);



        j=0;

        for (i=0 ; i < nombreDiagonale  ; i++) {

            ligneDiagonale.initNombre(0);


            for ( y=0 ; y < valeurMini ; y++) {

                ligneDiagonale2.setX(y, (parti.getXY(  y + z ,   compteurCase - y ).getContenu()));

            }


            compteur++;

            if ( compteur < tailleLargeur()  -1 ) {

                compteurCase++;
                valeurMini++;

            }


            else if (compteur == ( tailleLargeur() - 1 ) ) {

                compteurCase++;
                compteurCase--;
                z++;

            }


            else {

                z++;
                valeurMini--;

            }


            if ( ligneDiagonale2.rechercheValeursContigues(4)) {


                gagner = true;

            }
        }





        // ------------------- FIN -----------------------------



            return gagner;
    }

    public int changeJoueur ( int numJoueur) {


        if ( numJoueur != 1 && numJoueur != 2) {

            numJoueur = 1;

        }


        if ( numJoueur == 1) {

            numJoueur = 2;

        }


        else {

            numJoueur = 1;

        }

        return  numJoueur;
    }


    public int numJoueurVainqueur (int nbTour , int numJoueurDepart) {

     int numJoueurVainqueur = 0;


     if ( nbTour == 42 ) {

         numJoueurVainqueur = 3;

     }


     else {

         numJoueurVainqueur = (nbTour + numJoueurDepart) % 2;

     }


     return numJoueurVainqueur;

    }





}
