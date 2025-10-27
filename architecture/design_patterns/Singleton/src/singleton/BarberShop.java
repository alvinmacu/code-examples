package singleton;
/**
 * @author amartinezc
 * @category Creacionales
 * @since 30/09/2014
 * @version 1.0
 */

/**
 * Clase BarberShop, la cual nos proporcionará las funciones: 
 * 	-cutHair
 *  -getCost
 *  -payService
 */
public class BarberShop {
	/**
	 * Variable que almacena la instancia.
	 */
	private static BarberShop oBarberShop;
	private String sName;
	private int iCost;
	
	/**
	 * El constructor debe ser priavdo, esto para asegurar que no se puede crear una instancia fuera de esta clase.
	 */
	private BarberShop() {
		this.sName = "Alvin BarberShop";
		this.iCost = 100;
	}
	
	public static BarberShop getInstance() {
		if( oBarberShop ==  null ) {
			oBarberShop = new BarberShop();
		}
		
		return oBarberShop;
	}
	
	public String toString() {
		return getsName() + ". \nPrecio por corte: "+ getCost();
	}
	
	public void cutHair( Cliente oCliente ) {
		System.out.println( "Cliente atendido: " + oCliente.getsName() );
		
		String sMsj = "El cliente ";
		if( true == oCliente.getIsReady() ) {
			sMsj = "ha sido atendido.";
			oCliente.setIsReady( false );
		} else {
			sMsj = "no pueder ser atendido.";
			oCliente.setIsReady( true );
		}
		System.out.println( sMsj );
	}
	
	public String getsName() {
		return sName;
	}
	
	public int getCost() {
		return iCost;
	}
}
