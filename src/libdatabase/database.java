
package libdatabase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Pablo
 */
public class database {
    
    
    
    public void conectarse(Connection co, String ip, String nombre_base, String user, String password){
         try {
            Class.forName("com.mysql.jdbc.Driver");
            co = DriverManager.getConnection("jdbc:mysql://"+ip+"/"+nombre_base+"?user="+user+"&password="+password);
        } catch (ClassNotFoundException | SQLException e) {
            JOptionPane.showMessageDialog(null, "Error");
        }
    }
    
     public void insert(Connection co,String nombre_tabla, String [] campos){
      campos = new String[4];
      for(int i = 0; i<5;i++){
          campos[i]=JOptionPane.showInputDialog("Escribe el nombre del campo"+" "+i+" "+"que quieres agregar");
      }
      String SQL = "INSERT INTO "+nombre_tabla+"("+campos[0]+","+campos[1]+", "+campos[2]+", "+campos[3]+", "+campos[4]+") VALUES(?, ?, ?, ?, ?)";
        try {
            PreparedStatement pst = co.prepareStatement(SQL);
            pst.setString(1, campos[0]);
            pst.setString(2,campos[1]);
            pst.setString(3,campos[2]);
            pst.setString(4,campos[3]);
            pst.setString(5,campos[4]);
            int n = pst.executeUpdate();
            if (n>0){
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error");
        }
}
     public ResultSet consult(Connection co, String tabla) {
        ResultSet rs=null;
       
        try {
            Statement s = co.createStatement();
           rs = s.executeQuery("select * from "+tabla+";");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return rs;
    }
     
      public  void delete(Connection co, String tabla, String campo) throws SQLException{
      
       
       String SQL = "DELETE from "+tabla+" where "+ campo+" ="+ JOptionPane.showInputDialog("¿Qué  desea eliminar?\n")+";";
        
            PreparedStatement pst = co.prepareStatement(SQL);
            pst.execute();
}
}
