package test;

import SAIInterface.SAILocal;
import SAIInterface.SAIRemote;
import SAIManager.SAIManager;

public class Tester {
	public static void main( String...args ) {
		SAIManager oSAI = new SAIManager();
		//String ConnType = "LOCAL";
		String ConnType = "REMOTE";
		
		if( ConnType == "LOCAL" ) {
			oSAI.setConfigModule( new SAILocal() );
		} else {
			oSAI.setConfigModule( new SAIRemote() );
		}
		oSAI.initConfiguration();
	}
}
