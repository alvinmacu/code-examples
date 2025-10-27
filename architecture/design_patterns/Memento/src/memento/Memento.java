package memento;

// The 'Memento' 
public class Memento  {    
	private String _state;
	
	// Constructor    
	public Memento(String state) {      
		this._state = state;    
		}   
	
	// Gets or sets state    
	public String getState() {      
		return this._state; 
	}
}