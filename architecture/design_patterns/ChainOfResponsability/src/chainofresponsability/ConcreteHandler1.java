package chainofresponsability;

// "ConcreteHandler1" 
public class ConcreteHandler1 extends Handler { 
	
	public void HandleRequest( int request ) {
		
		if (request >= 0 && request < 10) {
			System.out.println("{0} handled request {1}", this.GetType().Name, request);
			} else if (successor != null) {
				successor.HandleRequest(request);    
				}  
		}
	}