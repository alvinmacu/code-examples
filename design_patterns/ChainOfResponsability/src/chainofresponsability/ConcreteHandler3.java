package chainofresponsability;

//"ConcreteHandler3" 
public class ConcreteHandler3 extends Handler {
	
	@override
	public void HandleRequest( int request ) {
		
		if (request >= 20 && request < 30) {
			System.out.println("{0} handled request {1}", this.getType().Name, request);    
			}
		else if (successor != null) {
			successor.HandleRequest(request);    
			}  
		}
	}