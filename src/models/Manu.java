package models;
public class Manu extends Employe {
    protected int heures;

    public Manu(String prenom, String nom, int age, String date, int heures) {
        super(prenom, nom, age, date);
        this.heures = heures;
    }

    @Override
    public double calculerSalaire() {
        return heures * 650;
    }

    @Override
    public String getTitre() {
        return "Le manut.";
    }
}