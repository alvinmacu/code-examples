package SAIInterface;

public class SAIRemote implements SAICRUD{

	@Override
	public void initConfiguration() {
		System.out.println( "initConfiguration Remote" );
		
	}

	@Override
	public void initDBConnection() {
		System.out.println( "initDBConnection Remote" );
		
	}

}
