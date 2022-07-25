package utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DataBaseConnection {

	private static String dbConnectionUrl;
	private static String dbUserName;
	private static String dbPassword;
	
	public String getDBresult(String sqlQuery) throws SQLException {
		
		//dbConnectionUrl = "jdbc:oracle:thin:@tmolexa07np-iowvn-scan.netsxtkeu.vcnvrtwo.oraclevcn.com:1521/TTMO10H1_PRIM.netsxtkeu.vcnvrtwo.oraclevcn.com";
		
		  dbConnectionUrl ="jdbc:oracle:thin:@tmolexa07np-iowvn-scan.netsxtkeu.vcnvrtwo.oraclevcn.com:1521/TTMO10H1_PRIM.netsxtkeu.vcnvrtwo.oraclevcn.com";
		  dbUserName = "TMOREAD"; 
		  dbPassword="Tmoread123";
		 
	
		Connection connection = null;
		ResultSet rs;
		String resultValue = "";
		//Driver Connection
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//Class.forName("oracle.jdbc.pool.OracleDataSource");
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//establishing Connection to DB 
		try {
			System.out.println("connection");
			connection = DriverManager.getConnection(dbConnectionUrl, dbUserName, dbPassword);
			//String query= "";
			if(connection!=null)
			{
				System.out.println("Connection established successfully");
			}
			else
			{
				System.out.println("Connection Failed");
			}
			
		} catch(SQLException e) {
			System.out.println(e);
		}
		//Executing the SQL Query to the connection
		/*String sqlQuery="select acc.ACC_SAC_NBR \r\n"
				+ "from ofsllprd.accounts acc where  acc.ACC_PRD_PRODUCT='LOAN' and acc.acc_status_cd_tmo in ('Active', 'Active-Shipped')\r\n"
				+ "and acc.ACC_STATUS_CD = 'ACTIVE' Fetch next 10 rows only"; */
		Statement stmt = connection.createStatement();
		rs = stmt.executeQuery(sqlQuery);
		while(rs.next()) {
			resultValue = rs.getString(1).toString();
			System.out.println(resultValue);
			}
		
		//Query returning single row and iterating each column and printing
		connection.close();
		return resultValue;			
	}
	
	public boolean multipleDBResult() throws SQLException {
		
		//dbConnectionUrl = "jdbc:oracle:thin:@tmolexa07np-iowvn-scan.netsxtkeu.vcnvrtwo.oraclevcn.com:1521/TTMO10H1_PRIM.netsxtkeu.vcnvrtwo.oraclevcn.com";
		  dbConnectionUrl = "jdbc:oracle:thin:@tmolexa07np-iowvn-scan.netsxtkeu.vcnvrtwo.oraclevcn.com:1521/TTMO10H1_PRIM.netsxtkeu.vcnvrtwo.oraclevcn.com";
		dbUserName = "TMOREAD";
		dbPassword="Tmoread123";
		
		Connection connection = null;
		ResultSet rs;
		String resultValue = "";
		//Driver Connection
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//Class.forName("oracle.jdbc.pool.OracleDataSource");
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//establishing Connection to DB 
		try {
			System.out.println("connection");
			connection = DriverManager.getConnection(dbConnectionUrl, dbUserName, dbPassword);
			//String query= "";
			if(connection!=null)
			{
				System.out.println("Connection established successfully");
			}
			else
			{
				System.out.println("Connection Failed");
			}
			
		} catch(SQLException e) {
			System.out.println(e);
		}
		//Executing the SQL Query to the connection
		String sqlQuery="select acc.ACC_SAC_NBR, acc.ACC_AGREEMENT_NBR_TMO \r\n"
				+ "from ofsllprd.accounts acc where  acc.ACC_PRD_PRODUCT='LOAN' and acc.acc_status_cd_tmo in ('Active', 'Active-Shipped')\r\n"
				+ "and acc.ACC_STATUS_CD = 'ACTIVE' Fetch next 10 rows only";
		Statement stmt = connection.createStatement();
		rs = stmt.executeQuery(sqlQuery);
		
		ResultSetMetaData rsmd = rs.getMetaData();
		
		List<String> dbRecords = new ArrayList<String>();
		boolean recordFound = false;
		try {
			{
			while(rs.next()) {
				for(int i=1;i<=rsmd.getColumnCount();i++) {
					dbRecords.add(rs.getString(i));
					}							
				}
			for(int j=0; j<dbRecords.size();j++) {
				String dbValue = dbRecords.get(j);
				if(dbValue!=null) {
				System.out.println(dbValue);
				recordFound = true;
				}
			}
			}
		}catch(SQLException e) {
			e.printStackTrace();
			
		}
			
		//Query returning single row and iterating each column and printing
		connection.close();
		return recordFound;		
	}
}
