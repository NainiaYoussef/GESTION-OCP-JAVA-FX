package models;

public abstract class working extends Employe {
    protected double chiffreAffaire;

    public working(String prenom, String nom, int age, String date, double chiffreAffaire) {
        super(prenom, nom, age, date);
        this.chiffreAffaire = chiffreAffaire;
    }
}