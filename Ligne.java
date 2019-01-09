import MG2D.*;
import MG2D.geometrie.*;

class Ligne {

    private Case[] tableau;



    //  Constructeur par défaut //

    public Ligne() {
        tableau = new Case[1];
        tableau[0] = new Case();
    }





    public Ligne( int n ) {
        tableau = new Case[n];
        for (int i=0 ; i<n ; i++) {
            tableau[i] = new Case();
        }
    }




    public Ligne ( String chaine ) {
        int longueur = chaine.length();
        char carac;
        int chiffre = 0;
        tableau = new Case[longueur];


        for (int i=0 ; i<longueur ; i++) {
            carac = chaine.charAt(i);

            //chiffre = Integer.valueOf(carac).intValue();
            tableau[i] = new Case();
            tableau[i].setContenu(carac-'0');
        }
    }




    public Ligne ( Ligne A ) {
        int longueur = A.tableau.length;
        tableau = new Case[longueur];
        for (int i=0; i<longueur; i++ ) {
            tableau[i] = new Case();
            tableau[i].setContenu(A.tableau[i].getContenu());
        }
    }





    //  Accesseur  //

    public Case getX ( int indice ) {

        return tableau[indice];
    }


    public void setX (int position , int valeur ) {

        if ( valeur <= 9 && valeur >= 0) {
            tableau[position].setContenu(valeur);
        }
        else {
            tableau[position].setContenu(0);
        }

    }







    // Autre Méthode



    public void setLigne ( String ValInit) {

        int taille_tableau= this.tableau.length;
        int taille_chaine = ValInit.length();
        char carac;
        int chiffre;

        if ( taille_tableau == taille_chaine){

            for (int i=0 ; i<taille_chaine ; i++) {
                carac = ValInit.charAt(i);
                // chiffre = Integer.valueOf(carac).intValue();
                tableau[i].setContenu(carac - '0');
                System.out.println(" Valeur -> " + carac );



                tableau[i] = new Case();
                tableau[i].setContenu(carac-'0');
            }

        }
        else {
            System.out.println("Erreur , la taille de la ligne ne correspond pas a la longueur de la chaine");
        }

    }

    public int getTaille ( ) {
        int nb = this.tableau.length;
        return nb;
    }


    public  boolean rechercheValeursContigues ( int n) {


        int stock = this.tableau[0].getContenu();
        int nb_max = 1;
        int nb = 1;




        for (int i=1 ; i < this.tableau.length  ; i++  )  {

            if ( this.tableau[i].getContenu() == stock && this.tableau[i].getContenu() != 0) {
                nb += 1;
            }

            else {
                stock = this.tableau[i].getContenu();

                if ( nb > nb_max ) {
                    nb_max = nb;
                }

                nb = 1;
            }

            if ( nb_max >= n ) {
                return true;
            }


        }
        if(nb>=n)
            return true;

        return false;
    }



    public String toString () {

        int longueur = this.tableau.length;
        String chaine = new String();
        String chaine_temp = new String();
        int temp;

        for (int i=0 ; i< longueur ; i++ ) {
            temp = this.tableau[i].getContenu();
            chaine_temp = String.valueOf(temp);
            chaine = chaine + chaine_temp;
        }
        return chaine;
    }


    public  void initNombre (int nb) {

        int longeurLigne = this.getTaille();

        for (int i=0 ; i <longeurLigne ; i++) {

            this.setX(i,nb);
        }



    }


}
