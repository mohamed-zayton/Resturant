
package resturant;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.swing.JOptionPane;

public class DB {
    public static Connection connect(){
        Connection connection = null;
        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            connection = DriverManager.getConnection("jdbc:ucanaccess://C:\\Users\\EGYPT-LAPTOP\\Documents\\NetBeansProjects\\Resturant\\Resturant.accdb");
        } catch (Exception e) {

        }
        return connection;
    }
    public static void main(String[] args) {
        connect();
    }
    
    public static int countM(String col){
        Connection connection = connect();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select count("+col+") from Meal");
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return  Integer.parseInt(resultSet.getString(1));
            }
        } catch (Exception e) {
          
        }finally{
            try {
                connection.close();
            } catch (Exception e) {
                
            }
        }
        return 0 ;
    }
     public static int countD(String col){
        Connection connection = connect();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select count("+col+") from Drinks");
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return  Integer.parseInt(resultSet.getString(1));
            }
        } catch (Exception e) {
          
        }finally{
            try {
                connection.close();
            } catch (Exception e) {
                
            }
        }
        return 0 ;
    }
    public static boolean insertM(String table ,int id ,String name ,String type ,int price){
        Connection connection = connect();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("insert into Meal  Values('"+id+"','"+name+"','"+type+"','"+price+"')");
            return preparedStatement.execute();
        } catch (Exception e) {
        
            if (table.equals("Drinks")) {
                JOptionPane.showMessageDialog(null, "name or number of Drink is exicted");
            }
           
        }
        
        return true;
    }
     public static boolean insertD(String table ,int id ,String name ,String type ,int price){
        Connection connection = connect();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("insert into Drinks  Values('"+id+"','"+name+"','"+type+"','"+price+"')");
            return preparedStatement.execute();
        } catch (Exception e) {
        
            if (table.equals("Meal")) {
                JOptionPane.showMessageDialog(null, "name or number of Meal is exicted");
            }
           
        }
        return true;
    }
        public static ObservableList<Drinks> getDrinks(){
            Connection connection = connect();
            ObservableList<Drinks> list = FXCollections.observableArrayList();
            try {
                PreparedStatement preparedStatement = connection.prepareStatement("select * from Drinks");
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {             
                    list.add(new Drinks(resultSet.getInt("num") ,resultSet.getString("namef") ,resultSet .getString("type") ,resultSet.getInt("price")));
                }
            } catch (Exception e) {
                System.out.println(e);
            }finally{
                try {
                    connection.close();
                } catch (Exception e) {
                }
                return list;
            }
        }
         public static ObservableList<Meal> getMeal(){
            Connection connection = connect();
            ObservableList<Meal> list = FXCollections.observableArrayList();
            try {
                PreparedStatement preparedStatement = connection.prepareStatement("select * from Meal ");
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {                    
                    list.add(new Meal(resultSet.getInt("num") ,resultSet.getString("namef") ,resultSet .getString("type") ,resultSet.getInt("price")));
                }
            } catch (Exception e) {
            }finally{
                try {
                    connection.close();
                } catch (Exception e) {
                }
                return list;
            }
        }
         public static boolean updateM(String where,String name ,String type, int price){
         
             Connection connection = connect();
             String sql;
             try {
                    sql = "Update Meal set namef = '"+name+"' ,type = '"+type+"', price = '"+price+"' " + where  ;              
                 PreparedStatement preparedStatement = connection.prepareStatement(sql);
                 return !preparedStatement.execute();
             } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
             
             }
             
             return false;
         }
               public static boolean updateD(String where,String name ,String type, int price){
         
             Connection connection = connect();
             String sql;
             try {
                    sql = "Update Meal set namef = '"+name+"' ,type = '"+type+"', price = '"+price+"' " + where  ;              
                 PreparedStatement preparedStatement = connection.prepareStatement(sql);
                 return !preparedStatement.execute();
             } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
             
             }
             
             return false;
         }
               
         public static boolean delete(String table,String where){
             Connection connection = connect();
             
             try {
                  PreparedStatement preparedStatement = connection.prepareStatement("delete from '"+table+"' where" + where);
             } catch (Exception e) {
             }finally{
                 try {
                     connection.close();
                 } catch (Exception e) {
                 }
             }
             
             return false;
         }

}
