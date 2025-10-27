package SAIManager;

import SAIInterface.SAICRUD;

public class SAIManager {
	SAICRUD oCRUD;
	
	public void setConfigModule( SAICRUD oCRUD ) {
		this.oCRUD = oCRUD;		
	}
	
	public void initConfiguration(){
		this.oCRUD.initDBConnection();
		this.oCRUD.initConfiguration();
	}
}
