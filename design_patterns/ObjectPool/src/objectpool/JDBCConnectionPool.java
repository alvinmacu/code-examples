/**
 * 
 */
package objectpool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author amartinezc
 *
 */
public class JDBCConnectionPool extends ObjectPool {

	private String dsn, usr, pwd;
	public JDBCConnectionPool( String driver, String dsn, String usr, String pwd ) {
		super();
		try {
			Class.forName( driver ).newInstance();
		} catch( Exception e ) {
			e.printStackTrace();
		}
		this.dsn = dsn;
		this.usr = usr;
		this.pwd = pwd;
	}
	
	@Override
	protected Object create() {
		try {
			return (DriverManager.getConnection( dsn, usr, pwd ));
		} catch( SQLException e ) { 
			e.printStackTrace();
			return ( null );
		}
	}

	@Override
	public void expire( Object  o) {
		try {
			((Connection)o).close();
		} catch( SQLException e ) {
			e.printStackTrace();
		} 
	}

	@Override
	public boolean validate( Object o) {
		try {
			return ( !((Connection)o).isClosed() );
		} catch( SQLException e ) {
			e.printStackTrace();
			return ( false );
		}
	}
}
