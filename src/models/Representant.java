package models;
public class Representant extends working {
    public Representant(String prenom, String nom, int age, String date, double chiffreAffaire) {
        super(prenom, nom, age, date, chiffreAffaire);
    }

    @Override
    public double calculerSalaire() {
        return (0.2 * chiffreAffaire) + 8000;
    }

    @Override
    public String getTitre() {
        return "Le représentant";
    }
}