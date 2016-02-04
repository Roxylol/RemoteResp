package test;

import java.sql.*;

import org.json.simple.JSONArray;


public class testConvertToJSON
{
    

    private static final String url = "jdbc:sqlserver://localhost:1433;DatabaseName=java";
    private static final String user = "sa";
    private static final String password = "sa123456";
    private static final String SELECT = "select * from customer";

    public static void main(String[] args)
    {

        try (Connection conn = DriverManager.getConnection(url, user, password);
                PreparedStatement stmt = conn.prepareStatement(SELECT);
                ResultSet rset = stmt.executeQuery();)
        {
            ResultSetMetaData rsmd = rset.getMetaData();
            int count = rsmd.getColumnCount();
            System.out.print("{");
            System.out.print("\"customers\":"+"[");
            
                while(rset.next()) {
//                	System.out.print("[");
                	System.out.print("{");
                	for(int i = 1; i <= count; i++) {
                		
                        System.out.print("\""+rsmd.getColumnLabel(i)+"\":");
                        if(i==count){
                        	System.out.print("\""+rset.getString(rsmd.getColumnLabel(i))+"\"" );
                        }else{
                        	System.out.print("\""+rset.getString(rsmd.getColumnLabel(i))+"\""+"," );
                        }
                        
                }
                	if(rset.next()){
                		System.out.print("}"+",");
                	}else{
                		System.out.print("}"+"]");
                	}

            }
                System.out.print("}");
                System.out.println();
            
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

    }

}
