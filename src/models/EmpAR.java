package models;
public class EmpAR extends Producteur implements AR {
    public EmpAR(String prenom, String nom, int age, String date, int unites) {
        super(prenom, nom, age, date, unites);
    }

    @Override
    public double calculerSalaire() {
        return super.calculerSalaire() + PRIME;
    }
}