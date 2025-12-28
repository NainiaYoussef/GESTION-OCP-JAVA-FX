package models;
public class Vendeur extends working {
    public Vendeur(String prenom, String nom, int age, String date, double chiffreAffaire) {
        super(prenom, nom, age, date, chiffreAffaire);
    }

    @Override
    public double calculerSalaire() {
        return (0.2 * chiffreAffaire) + 4000;
    }

    @Override
    public String getTitre() {
        return "Le vendeur";
    }
}