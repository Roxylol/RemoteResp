package test;

import java.sql.*;


public class testConvertToXML
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
            System.out.println("<customers>");
                while(rset.next()) {
                	System.out.println("<customer>");
                	for(int i = 1; i <= count; i++) {
                        System.out.print("<"+rsmd.getColumnLabel(i)+">");
                        System.out.print(rset.getString(rsmd.getColumnLabel(i)) );
                        System.out.print("</"+rsmd.getColumnLabel(i)+">");
                    System.out.print("\n");
                }
                	System.out.println("</customer>");
            }
                System.out.println("</customers>");
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

    }

}
