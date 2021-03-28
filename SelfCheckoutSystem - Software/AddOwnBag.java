import org.lsmr.selfcheckout.Item;
import org.lsmr.selfcheckout.devices.AbstractDevice;
import org.lsmr.selfcheckout.devices.ElectronicScale;
import org.lsmr.selfcheckout.devices.OverloadException;
import org.lsmr.selfcheckout.devices.SelfCheckoutStation;
import org.lsmr.selfcheckout.devices.listeners.AbstractDeviceListener;
import org.lsmr.selfcheckout.devices.listeners.ElectronicScaleListener;

public class AddOwnBag {
	private ElectronicScale baggingArea;
	private boolean isPlaced = false;
	private double initialWeight;
	private double finalWeight;
	private double bagWeight;
	private Bag bag;
	
	public AddOwnBag(SelfCheckoutStation selfCheckout) throws OverloadException {
		this.baggingArea = selfCheckout.baggingArea;
		selfCheckout.baggingArea.register(esl);
		this.initialWeight = baggingArea.getCurrentWeight();
		
	}
	
	private ElectronicScaleListener esl = new ElectronicScaleListener() {

		@Override
		public void enabled(AbstractDevice<? extends AbstractDeviceListener> device) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void disabled(AbstractDevice<? extends AbstractDeviceListener> device) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void weightChanged(ElectronicScale scale, double weightInGrams) {
			// TODO Auto-generated method stub
			isPlaced = true;
		}

		@Override
		public void overload(ElectronicScale scale) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void outOfOverload(ElectronicScale scale) {
			// TODO Auto-generated method stub
			
		}
		
	};
	
	//main method
	//once the listener announces that weight has changed, the weight of the bag is then calculated and added to the bagging area
	public void AddBag() throws OverloadException {
		if(isPlaced) {
			finalWeight =  baggingArea.getCurrentWeight();
			if(finalWeight - initialWeight >= 0) {
				bagWeight = finalWeight - initialWeight;
				bag = new Bag(bagWeight);
				baggingArea.add(bag);
			}
		}
	}
	

}

class Bag extends Item{

	protected Bag(double weightInGrams) {
		super(weightInGrams);
		// TODO Auto-generated constructor stub
	}

}
