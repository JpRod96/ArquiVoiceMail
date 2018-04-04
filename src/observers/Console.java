package observers;
import main.*;

public class Console implements Observer{
	
	private Observable observable;
	public Console(Observable observable) {
		this.observable=observable;
		observable.addObserver(this);
	}
	
	public void update() {
		
	}
}
