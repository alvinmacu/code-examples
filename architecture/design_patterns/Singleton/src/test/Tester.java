package test;

import singleton.BarberShop;
import singleton.Cliente;

public class Tester {
	public static void main( String ...args ) {
		BarberShop oBarberShop = BarberShop.getInstance();
		System.out.println( oBarberShop.toString() );
		
		Cliente oCliente = new Cliente( "Andrés" );
		
		oBarberShop.cutHair( oCliente );
		oBarberShop.cutHair( oCliente );
	}
}
