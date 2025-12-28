package models;
public class ManutAR extends Manu implements AR{
    public ManutAR(String prenom, String nom, int age, String date, int heures) {
        super(prenom, nom, age, date, heures);
    }

    @Override
    public double calculerSalaire() {
        return super.calculerSalaire() + PRIME;
    }
}