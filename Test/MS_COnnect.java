package Test;

import java.applet.Applet;
import java.awt.HeadlessException;

public class MS_COnnect extends Applet
{
  /**
	 * 
	 */
	private static final long serialVersionUID = -5837977357439548725L;

public MS_COnnect() throws HeadlessException
  {
  }

  private java.sql.Connection  con = null;
     private final String url = "jdbc:microsoft:sqlserver://";
     private final String serverName= "192.1.1.254";
     private final String portNumber = "1403";
     private final String databaseName= "AU";
     private final String userName = "dbaau";
     private final String password = "sql";
     // Informs the driver to use server a side-cursor,
     // which permits more than one active statement
     // on a connection.
     private final String selectMethod = "cursor";

     // Constructor

     private String getConnectionUrl(){
          return url+serverName+":"+portNumber+";database="+databaseName+";selectMethod="+selectMethod+";";
     }

     private java.sql.Connection getConnection(){
          try{
               Class.forName("com.microsoft.jdbc.sqlserver.SQLServerDriver");
               con = java.sql.DriverManager.getConnection(getConnectionUrl(),userName,password);
               if(con!=null) System.out.println("Connection Successful!");
          }catch(Exception e){
               e.printStackTrace();
               System.out.println("Error Trace in getConnection() : " + e.getMessage());
         }
          return con;
      }

     /*
          Display the driver properties, database details
     */

     public void displayDbProperties(){
          java.sql.DatabaseMetaData dm = null;
          java.sql.ResultSet rs = null;
          try{
               con= this.getConnection();
               if(con!=null){
                    dm = con.getMetaData();
                    System.out.println("Driver Information");
                    System.out.println("\tDriver Name: "+ dm.getDriverName());
                    System.out.println("\tDriver Version: "+ dm.getDriverVersion ());
                    System.out.println("\nDatabase Information ");
                    System.out.println("\tDatabase Name: "+ dm.getDatabaseProductName());
                    System.out.println("\tDatabase Version: "+ dm.getDatabaseProductVersion());
                    System.out.println("Avalilable Catalogs ");
                    rs = dm.getCatalogs();
                    while(rs.next()){
                         System.out.println("\tcatalog: "+ rs.getString(1));
                    }
                    rs.close();
                    rs = null;
                    closeConnection();
               }else System.out.println("Error: No active Connection");
          }catch(Exception e){
               e.printStackTrace();
          }
          dm=null;
     }

     private void closeConnection(){
          try{
               if(con!=null)
                    con.close();
               con=null;
          }catch(Exception e){
               e.printStackTrace();
          }
     }
     public void start()
     {
          displayDbProperties();
    }


}