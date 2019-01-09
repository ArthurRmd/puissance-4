import MG2D.*;
import MG2D.geometrie.*;
import MG2D.geometrie.Point;

import java.awt.*;

public class InterfaceGraphique {

    private Cercle[][] jeu;
    private Fenetre f ;
    private Fenetre f1;
    private Fenetre fMenu;
    private Clavier clavier;
    private Clavier clavier1;
    private Clavier clavier2;

    private Font police;
    private Font petitePolice;
    private Font moyennePolice;
    private Font grandePolice;
    private Texte texte;








    public InterfaceGraphique (Plateau parti, boolean jeuAvecMenuDemarrer) {


        boolean continuer = false;
        int menu = 0;

        police = new Font("Calibri", Font.BOLD, 40);
        petitePolice = new Font("Calibri", Font.BOLD, 20);
        moyennePolice = new Font("Calibri", Font.BOLD, 25);
        grandePolice = new Font("Calibri", Font.BOLD, 33);


       if ( jeuAvecMenuDemarrer) {

            f1 = new Fenetre("Puissance 4", 800, 450);
            clavier1 = f1.getClavier();
            Texte texte2 = new Texte();


            texte = new Texte(Couleur.NOIR, "Bienvenue dans le Puissance 4", police, new Point(f1.getMilieu().getX(), 400));
            f1.ajouter(texte);

            Texte texteJouer = new Texte(Couleur.NOIR, "Jouer", police, new Point(f1.getMilieu().getX(), 250));
            f1.ajouter(texteJouer);

            Texte texteTuto = new Texte(Couleur.NOIR, "Tutoriel", police, new Point(f1.getMilieu().getX(), 150));
            f1.ajouter(texteTuto);

            Texte texteBasDePage = new Texte(Couleur.NOIR, "Flèche Haut / Bas pour sélectionner, puis Entrée ", petitePolice, new Point(f1.getMilieu().getX(), 25));
            f1.ajouter(texteBasDePage);

            Cercle cercleChoix = new Cercle(Couleur.NOIR, new Point(250, 250), 13, true);
            f1.ajouter(cercleChoix);


            while (!continuer) {

                try {
                    Thread.sleep(30);
                } catch (Exception e) { }


                if (clavier1.getHautTape() && cercleChoix.getO().getY() == 150) {

                    f1.supprimer(cercleChoix);
                    cercleChoix = new Cercle(Couleur.NOIR, new Point(250, 250), 13, true);
                    f1.ajouter(cercleChoix);
                    f1.rafraichir();

                }


                if (clavier1.getBasTape() && cercleChoix.getO().getY() == 250) {

                    f1.supprimer(cercleChoix);
                    cercleChoix = new Cercle(Couleur.NOIR, new Point(250, 150), 13, true);
                    f1.ajouter(cercleChoix);
                    f1.rafraichir();

                }


                if (clavier1.getEntreeTape()) {

                    if (cercleChoix.getO().getY() == 250) {

                        menu = 1;
                        continuer = true;

                    }


                    if (cercleChoix.getO().getY() == 150) {

                        menu = 2;
                        f1.fermer();
                        fMenu = new Fenetre("Tutoriel ", 850, 400);
                        clavier2 = fMenu.getClavier();

                        Texte texteTutorielTitre = new Texte(Couleur.NOIR, "Tutoriel", police, new Point(fMenu.getMilieu().getX(), 350));
                        fMenu.ajouter(texteTutorielTitre);

                        Texte texteTutorielRegle1 = new Texte(Couleur.NOIR, "Toucher la flèche Gauche / Droite pour changer ", moyennePolice, new Point(fMenu.getMilieu().getX(), 250));
                        fMenu.ajouter(texteTutorielRegle1);

                        Texte texteTutorielRegle1Suite = new Texte(Couleur.NOIR, " la position du pion", moyennePolice, new Point(fMenu.getMilieu().getX(), 210));
                        fMenu.ajouter(texteTutorielRegle1Suite);

                        Texte texteTutorielRegle2 = new Texte(Couleur.NOIR, "Touche Espace pour faire tomber le pion", moyennePolice, new Point(fMenu.getMilieu().getX(), 130));
                        fMenu.ajouter(texteTutorielRegle2);

                        texte2 = new Texte(Couleur.NOIR, "Appuyer sur la touche Espace pour revenir en arrière et lancer une partie", petitePolice, new Point(fMenu.getMilieu().getX(), 25));
                        fMenu.ajouter(texte2);

                    }

                }


                if (menu == 2) {

                    if (clavier2.getEspaceTape()) {

                        menu = 0;
                        fMenu.supprimer(texte);
                        fMenu.fermer();

                        f1 = new Fenetre("Puissance 4", 800, 450);

                        f1.ajouter(texte);
                        f1.ajouter(texteJouer);

                        f1.ajouter(texteTuto);

                        f1.ajouter(texteBasDePage);

                        f1.ajouter(cercleChoix);
                        clavier = f1.getClavier();

                       continuer = true;

                    }

                }

            }


            f1.fermer();

        }


            /* ----------------- Fenetre avec Puissance 4 ------------------ */

            f = new Fenetre("Puissance 4", 700, 800);
            clavier = f.getClavier();
            initPlateauGraphique(parti);

            police = new Font("Calibri", Font.BOLD, 55);
            texte = new Texte(Couleur.NOIR, "", police, new Point(f.getMilieu().getX(), 760));
            f.ajouter(texte);

            //System.exit(0);
            //f.fermer();


    }



    private void initPlateauGraphique(Plateau parti) {


        int x = 0;
        int y = 0;

        jeu = new Cercle[7][6];


        for (int i=0 ; i<7 ; i++){

            for (int j=0; j<6 ; j++ ){

                x = 50 + ( i*100);
                y = 50 + ( j*100);

                jeu[i][j] = new Cercle( Couleur.GRIS_FONCE, new Point( x , y ),  50 , true);
                f.ajouter(jeu[i][j]);

                parti.getXY(i,j).setContenu(0);

            }
        }



    }



    public void refreshPion (Plateau parti){

        jeu = new Cercle[7][7];

        int taille_largeur = (parti.tailleLargeur());
        int taille_hauteur = (parti.tailleHauteur());

        int largeur = (parti.tailleLargeur()); //7
        int hauteur = (parti.tailleHauteur()); // 6

        int temp = 0;

        int x = 0;
        int y = 0;

        jeu = new Cercle[taille_largeur][taille_hauteur];


        for (int i=0 ; i<6 ; i++){

            for (int j=0; j<7 ; j++ ){

                temp = parti.getXY( j , i ).getContenu();

                x = 50 + ( -1* ( ( i-5) *100)) ;
                y = 50 + ( j*100);

                if ( temp != 0 ) {

                    if ( temp == 1 ) {

                        jeu[j][i] = new Cercle(Couleur.JAUNE, new Point(y, x), 50, true);
                        f.ajouter(jeu[j][i]);

                    }


                    if ( temp == 2 ) {

                        jeu[j][i] = new Cercle(Couleur.ROUGE, new Point(y, x), 50, true);
                        f.ajouter(jeu[j][i]);

                    }


                }

            }

        }


    }



    public int selectionPion ( int numJoueur , Plateau parti , int position) {

        f.rafraichir();
        Boolean toucheDroite = false;
        Boolean toucheGauche = false;

        int x = 50 + ( position *100);
        int y = 75 + ( 6*100);

        Couleur joueurCouleur = new Couleur(Couleur.JAUNE);


        if ( numJoueur == 1) {

            joueurCouleur = Couleur.JAUNE;

        }


        else if ( numJoueur == 2) {

            joueurCouleur = Couleur.ROUGE;

        }


        Cercle pion = new Cercle(joueurCouleur, new Point(x, y), 50, true);
        f.ajouter(pion);
        Boolean continuer = true;



        while (continuer) {

            toucheDroite = false;
            toucheGauche = false;

            try {
                Thread.sleep(30);
            } catch(Exception e) {}




            /* ------------------ Touche Gauche  -----------------*/

            if ( clavier.getGaucheTape()) {
                toucheGauche = true;
                position--;
            }

            if ( position < 0 ) {
                position = 6;
            }


            if( toucheGauche == true && position <= 6){

                f.supprimer(pion);
                x = 50 + ( position *100);
                y = 75 + ( 6*100);
                pion = new Cercle(joueurCouleur, new Point(x, y), 50, true);
                f.ajouter(pion);
            }




            /* ------------------ Touche droite  ------------------- */


                if ( clavier.getDroiteTape()) {
                    toucheDroite = true;
                    position++;
                }


                if( toucheDroite == true && position <= 6){

                        f.supprimer(pion);
                        x = 50 + ( position *100);
                        y = 75 + ( 6*100);
                        pion = new Cercle(joueurCouleur, new Point(x, y), 50, true);
                        f.ajouter(pion);
                }


                if( toucheDroite == true && position  > 6){

                    position = 0;
                    f.supprimer(pion);
                    x = 50 + ( position *100);
                    y = 75 + ( 6*100);
                    pion = new Cercle(joueurCouleur, new Point(x, y), 50, true);
                    f.ajouter(pion);
                }



            if ( !parti.colonneVide(position)) {

                Texture croix = new Texture( "img/cross.png" , new Point( (x - ( pion.getRayon())), (y- ( pion.getRayon()))), 100 , 100);
                f.ajouter(croix);
                f.rafraichir();

                }

            /* -------------------- Touche Espace -------------------*/

            if ( clavier.getEspaceTape() ) {

                continuer = false;

            }


            f.rafraichir();

        }



        f.supprimer(pion);
        return position;


    }



    public void textPartie ( String phrase ) {

        texte.setTexte(phrase);
        f.rafraichir();

    }


    public void afficheLigneCroix (Plateau parti) {


        int i;
        int x ;
        int y = 75 + ( 6*100);


        for ( i=0 ; i < parti.tailleLargeur() ; i++) {

             x = 50 + ( i *100);
            Texture croix = new Texture( "img/cross.png" , new Point( x - 50,  (y- 50)), 100 , 100);
            f.ajouter(croix);
            f.rafraichir();

        }

    }


    public void interfaceFinJeu (int numJoueurVainqueur) {


        boolean continuer = false;
        String texteVainqueur = "";
        Fenetre finJeu = new Fenetre("Vainqueur", 800, 400);
        Clavier clavierFinJeu = finJeu.getClavier();
        int menu = 1;

        /* ------------- Selection Texte ----------------*/

        if ( numJoueurVainqueur == 0 ) {

            texteVainqueur = "Victoire Jaune"  ;

        }


        else if ( numJoueurVainqueur == 1) {

            texteVainqueur = "Victoire Rouge";

        }

        else if ( numJoueurVainqueur == 3) {

            texteVainqueur = "Egalité";

        }




        Texte texteTutorielTitre = new Texte(Couleur.NOIR, texteVainqueur , police , new Point( finJeu.getMilieu().getX() , 350));
        finJeu.ajouter(texteTutorielTitre);

        Texte texteTutorielRegle1 = new Texte(Couleur.NOIR, "Rejouer " , grandePolice , new Point( finJeu.getMilieu().getX() , 200));
        finJeu.ajouter(texteTutorielRegle1);

        Texte texteTutorielRegle1Suite = new Texte(Couleur.NOIR, "Quitter" , grandePolice , new Point( finJeu.getMilieu().getX() , 125));
        finJeu.ajouter(texteTutorielRegle1Suite);

        Texte texteBasDePage = new Texte(Couleur.NOIR, "Flèche Haut / Bas pour sélectionner, puis Entrée" , petitePolice , new Point( finJeu.getMilieu().getX() , 25));
        finJeu.ajouter(texteBasDePage);

        Cercle cercleChoix =  new Cercle( Couleur.NOIR, new Point( 275, 200 ),  13, true);
        finJeu.ajouter(cercleChoix);



        while (!continuer) {

            try {
                Thread.sleep(30);
            } catch (Exception e) { }


            if (clavierFinJeu.getHautTape() && cercleChoix.getO().getY() == 125) {

                finJeu.supprimer(cercleChoix);
                cercleChoix = new Cercle(Couleur.NOIR, new Point(275, 200), 13, true);
                finJeu.ajouter(cercleChoix);
                finJeu.rafraichir();
                menu = 1;

            }

            if (clavierFinJeu.getBasTape() && cercleChoix.getO().getY() == 200) {

                finJeu.supprimer(cercleChoix);
                cercleChoix = new Cercle(Couleur.NOIR, new Point(275, 125), 13, true);
                finJeu.ajouter(cercleChoix);
                finJeu.rafraichir();
                menu = 2;

            }


            if ( clavierFinJeu.getEntreeTape() ) {

                if ( menu == 1) {

                    finJeu.fermer();
                    f.fermer();
                    Jeu nouvellePartie = new Jeu(false);

                }


                if ( menu == 2) {

                    f.fermer();
                    finJeu.fermer();
                    System.exit(0);

                }

            }


        }


    }






}
