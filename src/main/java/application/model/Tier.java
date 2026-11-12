package application.model;

public class Tier {
    private String lettre;
    private String couleur;

    public Tier(String lettre, String Couleur){
        /**
         * Constructeur de la classe Tier, qui représente une tier d'une tierlist.
         * @param lettre : la lettre de la tier en majuscule
         * @param Couleur : la couleur de la tier au format hexadécimal
         */

        this.lettre = lettre;
        this.couleur = Couleur;
    }

    public void setLetter(String Titre){
        this.lettre = Titre;
    }

    public void setCouleur(String Couleur){
        this.couleur = Couleur;
    }

    public String getLettre(){
        return lettre;
    }

    public String getCouleur(){
        return couleur;
    }
}
