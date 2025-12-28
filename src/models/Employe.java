package models; 

public abstract class Employe {
    protected int id; 
    protected String nom;
    protected String prenom;
    protected int age;
    protected String date;

    public Employe(String prenom, String nom, int age, String date) {
        this.nom = nom;
        this.prenom = prenom;
        this.age = age;
        this.date = date;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNomSeul() { return nom; }
    public int getAge() { return age; }
    public String getDate() { return date; }

    public abstract double calculerSalaire();

    public String getTitre() { return "L'employé"; }

    public String getNom() {
        return getTitre() + " " + prenom + " " + nom;
    }
}