package singleton;

public class Cliente {
	private String sName;
	private boolean bIsReady;
	
	private Cliente() {
		
	}
	
	public Cliente( String sName ) { 
		this.sName = sName;
	}
	
	public String getsName() {
		return sName;
	}
	
	public boolean getIsReady() {
		return bIsReady;
	} 
	
	public void setIsReady( boolean bStatus ) {
		bIsReady = bStatus;
	}
}
