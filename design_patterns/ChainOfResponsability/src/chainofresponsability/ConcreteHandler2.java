package chainofresponsability;

//"ConcreteHandler2" 
public class ConcreteHandler2 extends Handler {
	
	@override
	public void HandleRequest( int request ) {
		if (request >= 10 && request < 20)    {     
			System.out.println("{0} handled request {1}",  this.GetType().Name, request);    
			}    else if (successor != null)    {   
				successor.HandleRequest(request);    
				}  
		}
	}