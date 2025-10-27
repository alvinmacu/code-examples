package command;

//"ConcreteCommand"
public class ConcreteCommand extends Command {  
	// Constructor   
	public ConcreteCommand(Receiver receiver) { 
		super (receiver);   
		}  
	
	@Override
	public void Execute() {    
		receiver.Action();  
		System.out.println( "ConcreteCommand Save()" );
		}
	}