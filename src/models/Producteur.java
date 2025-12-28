package models;
public class Producteur extends Employe {
    protected int unites;

    public Producteur(String prenom, String nom, int age, String date, int unites) {
        super(prenom, nom, age, date);
        this.unites = unites;
    }

    @Override
    public double calculerSalaire() {
        return unites * 5;
    }

    @Override
    public String getTitre() {
        return "Le technicien";
    }
}