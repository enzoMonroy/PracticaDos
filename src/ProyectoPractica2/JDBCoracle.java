
package ProyectoPractica2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
/**
 *
 * @author Enzo
 */
public class JDBCoracle {
    
    private static final String DATABASE_URL = "jdbc:oracle:thin:@localhost:1521:XE";
            //"jdbc:oracle:thin:@localhost:1521:XE/PRACTICA2?useSSL=false";
    private static final String DATABASE_USERNAME = "root";
    private static final String DATABASE_PASSWORD = "";
    private static final String SELECT_QUERY = "SELECT * FROM users WHERE username = ? AND password = ?";
    
    public boolean validar(String username, String password){
        
        try{
            Connection connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
            
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_QUERY);
            preparedStatement.setString(1, username);
            preparedStatement.setString(1, password);
            
            System.out.println(preparedStatement);
            
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                return true;
            }
        }catch(SQLException e ){
            
            printSQLException(e);
        }
        return false;
    }
    public static void printSQLException(SQLException ex){
        for(Throwable e: ex){
            if(ex instanceof SQLException){
                e.printStackTrace(System.err);
                System.err.println("SQL estado: "+ ((SQLException)e).getSQLState());
                System.err.println("Error codigo: "+ ((SQLException)e).getErrorCode());
                System.err.println("Mensaje: "+ ex.getMessage());
                Throwable t = ex.getCause();
                while(t != null){
                    System.err.println("Causa: "+ t);
                    t = t.getCause();
                }
                        
            }
        }
    
       
}
     
}