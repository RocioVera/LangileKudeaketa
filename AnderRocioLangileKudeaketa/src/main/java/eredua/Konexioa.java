package eredua;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Konexioa {
	private String  maquina="localhost/elorrieta_errekamari";
    private String  usuario="root";
    private String  clave="";
    private int puerto= 3306;
    private String  servidor="127.0.0.1";
    private static Connection konexioa  = null;
    private String baseDatos="elorrieta_errekamari";
    //KONSTRUKTOREA
  
    public Konexioa(){
       
    		
    	
    	this.servidor="jdbc:mysql://"+this.maquina+":"+ this.puerto+"/"+baseDatos;

        //Registrar el driver
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println("ERROREA DRIVERRA INSTALATZEAN");
            System.exit(0); //exekuzioa gelditu
        }
 
        //Establecer la conexión con el servidor
        try {
            konexioa = DriverManager.getConnection("jdbc:mysql://"+this.maquina,this.usuario, this.clave);
        } catch (SQLException e) {
            System.err.println("ERRORREA ZERBITZARIRA KONEKTATZEKO");
            System.exit(0); //exekuzioa gelditu
        }
        System.out.println("Conectado a "+baseDatos);
    }
 
/**
 * 
 * @return conexion
 */
    
    public static Connection getKonexioa() {
        return konexioa;
    }
    public static void itxikonexioa() {
    	 try {
			konexioa.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
