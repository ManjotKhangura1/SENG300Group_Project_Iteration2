import org.lsmr.selfcheckout.devices.*;
import org.lsmr.selfcheckout.devices.listeners.*;
import org.lsmr.selfcheckout.*;


public class FinishesAddingItems {
	ReceiptPrinter printer;
	ScanItem scan;
	boolean isEnabled;
	
	PayWithCoin coin;
	PayWithBanknote banknote;
	PayWithDebit debit;
	//PayWithCredit credit;
	
	

	/**
	 * Constructor to finish transaction
	 * @param SelfCheckoutStation 
	 * @param ScanItem
	 */
	public FinishesAddingItems(ScanItem scan, BaggingArea bags) {
		scan.handheld.disable();
		scan.main.disable();
		bags.baggingArea.disable();
		
	}
	  
}
