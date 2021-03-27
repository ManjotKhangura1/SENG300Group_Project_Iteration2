import java.util.ArrayList;

import org.lsmr.selfcheckout.devices.*;
import org.lsmr.selfcheckout.devices.listeners.*;
import org.lsmr.selfcheckout.*;


public class FinishesAddingItems {

	/**
	 * Constructor to finish adding items
	 * @param SelfCheckoutStation 
	 * @param ScanItem
	 */
	public FinishesAddingItems(SelfCheckoutStation station, ScanItem scan) {
		scan.handheld = null;
		scan.main = null;
		
	}
	
	/**
	 * Prompts a check for membership
	 */
	public void checkForMembership() {
		
	}
}
