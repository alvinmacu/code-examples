package memento;

// The 'Originator' class  
public class Originator  {    
	private String _state;
	
	// Property    
	public String getState() {
		return _state; 
		}
	
	public void setState( String value ) {
		_state = value;
		System.out.println("State = " + _state);
		}    
	
	// Creates memento     
	public Memento CreateMemento() {
		return (new Memento(_state));
		}    
	
	// Restores original state    
	public void setMemento(Memento memento) {
		System.out.println("Restoring state...");      
		_state = memento.getState();    
		System.out.println( "Estado restaurado: " + _state );
	}  
}
