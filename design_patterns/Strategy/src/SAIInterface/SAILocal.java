package SAIInterface;

public class SAILocal implements SAICRUD{

	@Override
	public void initConfiguration() {
		System.out.println( "initConfiguration Local" );
		
	}

	@Override
	public void initDBConnection() {
		System.out.println( "initDBConnection Local" );
		
	}

}
