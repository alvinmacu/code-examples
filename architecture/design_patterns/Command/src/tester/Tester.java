package tester;

import command.Command;
import command.ConcreteCommand;
import command.Invoker;
import command.Receiver;

public class Tester {

	public static void main( String...args ) {
		// Create receiver, command, and invoker     
		Receiver receiver = new Receiver();    
		Command command = new ConcreteCommand(receiver);
		Invoker invoker = new Invoker();    

		// Set and execute command     
		invoker.SetCommand(command);    
		invoker.ExecuteCommand();

		// Wait for user     
		System.out.println( "Termina el main()" );
	}
	
}


