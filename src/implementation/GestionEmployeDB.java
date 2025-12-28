package implementation;

import connection.DBConnection;
import models.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GestionEmployeDB {

    public static void addEmployee(Employe e, String type, double valeurParametre, boolean risque) {
        String sql = "INSERT INTO Employe (nom, age, date_entree, type_employe, valeur_salaire_base, salaire_net, a_risque) VALUES (?, ?, ?, ?, ?, ?, ?)";
        
        try (PreparedStatement stmt = DBConnection.getConnection().prepareStatement(sql)) {
            stmt.setString(1, e.getNomSeul()); 
            stmt.setInt(2, e.getAge());
            stmt.setString(3, e.getDate());
            stmt.setString(4, type);
            stmt.setDouble(5, valeurParametre); 
            stmt.setDouble(6, e.calculerSalaire());
            stmt.setBoolean(7, risque);
            
            stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static List<Employe> getAllEmployees() {
        List<Employe> list = new ArrayList<>();
        String sql = "SELECT * FROM Employe";

        try (Statement stmt = DBConnection.getConnection().createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String nom = rs.getString("nom");
                int age = rs.getInt("age");
                String date = rs.getString("date_entree");
                String type = rs.getString("type_employe");
                double val = rs.getDouble("valeur_salaire_base");
                boolean risque = rs.getBoolean("a_risque");

                Employe e = null;
                switch (type) {
                    case "Vendeur": e = new Vendeur("", nom, age, date, val); break;
                    case "Representant": e = new Representant("", nom, age, date, val); break;
                    case "Producteur": 
                        if(risque) e = new EmpAR("", nom, age, date, (int)val);
                        else e = new Producteur("", nom, age, date, (int)val); 
                        break;
                    case "Manutentionnaire": 
                        if(risque) e = new ManutAR("", nom, age, date, (int)val);
                        else e = new Manu("", nom, age, date, (int)val);
                        break;
                }

                if (e != null) {
                    e.setId(id);
                    list.add(e);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }
    
    public static void deleteEmployee(int id) {
        try {
            DBConnection.getConnection().createStatement().executeUpdate("DELETE FROM Employe WHERE id=" + id);
        } catch (SQLException e) { e.printStackTrace(); }
    }
    
    public static double getMoyenneSalaire() {
        try {
            ResultSet rs = DBConnection.getConnection().createStatement().executeQuery("SELECT AVG(salaire_net) FROM Employe");
            if(rs.next()) return rs.getDouble(1);
        } catch (SQLException e) { e.printStackTrace(); }
        return 0.0;
    }
}