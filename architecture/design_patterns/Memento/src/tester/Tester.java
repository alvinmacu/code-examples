package tester;

import memento.Caretaker;
import memento.Originator;

public class Tester {

	public static void main( String...args ) {
		 Originator o = new Originator();      
		 o.setState( "On" );

		 // Store internal state      
		 Caretaker c = new Caretaker();      
		 c.setMemento( o.CreateMemento() );
		 
		 // Continue changing originator20.
		 System.out.println( "Se cambia a: " );
		 o.setState ( "Off" );
		 
		 
		 // Restore saved state      
		 o.setMemento( c.getMemento() );
		 
		 System.out.println( "Termina test de Memento." );
	}
	
}
