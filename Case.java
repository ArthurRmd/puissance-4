import MG2D.*;
import MG2D.geometrie.*;

class Case  {



    private int contenu ;


    //  Constructeur par défaut  //

    public Case  () {
        contenu = 0;
        //System.out.println( "   La variable contenu a été initialisé à 0  ");
    }



    public Case ( int A) {

        if ( A <= 9 && A >= 0) {
            contenu = A ;
            System.out.println( "La variable contenu a été initialisé à " + A);
        }
        else {
            System.out.println(" Erreur , la valeur n'est pas comprise entre 0 et 9");
            this.contenu = 0;
            System.out.println( "La variable contenu a été initialisé à 0");
        }
    }


    public Case ( Case A)  {

        A.contenu = this.contenu;
    }



    // Accesseur   //


    public int getContenu() {
        return contenu;
    }

    public void setContenu(int A) {
        this.contenu = A;
    }




    //  Autre méthode //


    public boolean correct() {

        if ( this.contenu <= 9 && this.contenu >= 0) {
            return true;
        }
        else {
            return false;
        }
    }


    public boolean equals(Case A) {

        if ( this.contenu == A.contenu) {
            return true;
        }
        else {
            return false;
        }

    }


    public String toString() {

        String  ligne =  new String();

        if ( this.contenu == 0){
            ligne = " ";
        }
        else {
            ligne = String.valueOf(this.contenu);
        }

        return ligne;
    }




}