package memento;

//The 'Caretaker' 
public class  Caretaker  {
	private Memento _memento;    
	
	// Gets or sets memento    
	public void setMemento( Memento value ) { 
		_memento = value; 
		}      
	public Memento getMemento () { 
		return _memento; }    
	}  