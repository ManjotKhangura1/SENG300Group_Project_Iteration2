package src;

import org.lsmr.selfcheckout.Item;

import org.lsmr.selfcheckout.devices.*;
import org.lsmr.selfcheckout.devices.listeners.AbstractDeviceListener;
import org.lsmr.selfcheckout.devices.listeners.ElectronicScaleListener;


public class FailBagging {

	private ElectronicScale baggingArea;
	private SelfCheckoutStation selfCheckout;
	private Item item;
	private double itemWeight = 0;
	private boolean lock;
	
	public FailBagging(SelfCheckoutStation selfCheckout)
	{
		this.baggingArea = selfCheckout.baggingArea;
		this.selfCheckout = selfCheckout;
		this.baggingArea.register(createBaggingListener());
	}

	public boolean isLock() {
		return lock;
	}
	
	private ElectronicScaleListener createBaggingListener()
	{
		return new ElectronicScaleListener() {

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
				if (itemWeight == weightInGrams)
				{
					if (isLock())
					{
						unlockScan();
					}
				}
				
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
	}
	
	
	public void lockScan(Item item)
	{
		lock = true;
		itemWeight = item.getWeight();
		selfCheckout.mainScanner.disable();
		selfCheckout.handheldScanner.disable();
	}
	
	private void unlockScan()
	{
		lock = false;
		itemWeight = 0;
		selfCheckout.mainScanner.enable();
		selfCheckout.handheldScanner.enable();
	}
	
	public void adminOverride()
	{
		if (isLock())
		{
			unlockScan();
		}
	}
	
	
}
