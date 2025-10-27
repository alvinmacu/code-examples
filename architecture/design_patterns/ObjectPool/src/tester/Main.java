package tester;

import java.sql.Connection;

import objectpool.JDBCConnectionPool;


public class Main {
	public static void main(String...args  ) {
		// Do something...
		
		
		//Create the ConnectionPool
		JDBCConnectionPool oJDBCConnectionPool = new JDBCConnectionPool( "org.hsqldb.jdbcDriver",
																			"jdbc:hsqldb://localhost/mydb",
																				"sa",
																					"secret" );
		//Get a connection
		Connection con = ( Connection ) oJDBCConnectionPool.checkOut();
		//Use the connection
		
		//Return the connection
		oJDBCConnectionPool.checkIn( con );
	}
}
