package objectpool;

import java.util.Enumeration;
import java.util.Hashtable;

//ObjectPool Class
public abstract class ObjectPool {  
	private long expirationTime;
	private Hashtable locked, unlocked;  
	public ObjectPool() {    
		expirationTime = 30000; // 30 seconds
		locked = new Hashtable();    
		unlocked = new Hashtable();  
		}  
	//Se usa Object en lugar de T, ya que la versión actual no soporta genéricos
	protected abstract Object create();
	public abstract boolean validate(Object o);  
	public abstract void expire(Object o);
	public synchronized Object checkOut() {    
		long now = System.currentTimeMillis();    
		Object t;    
		if (unlocked.size() > 0) {      
			Enumeration e = unlocked.keys();
			while (e.hasMoreElements()) {        
				t = e.nextElement();        
				//if ((now - unlocked.get(t)) > expirationTime) {
				if ((now - unlocked.get(t).hashCode()) > expirationTime) {
					// object has expired          
					unlocked.remove(t);
					expire(t);          
					t = null;        
				} else {          
					if (validate(t)) {            
						unlocked.remove(t);
						locked.put(t, now);
						return (t);          
					} else {            
						// object failed validation            
						unlocked.remove(t);
						expire(t);            
						t = null;          
						}        
					}      
			}
		}    
		// no objects available, create a new one    
		t = create();    
		locked.put(t, now);    
		return (t);
	}  
	public synchronized void checkIn(Object t) {    
		locked.remove(t);    
		unlocked.put(t, System.currentTimeMillis());
		}
	}