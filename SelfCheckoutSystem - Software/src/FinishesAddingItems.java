import java.util.ArrayList;

import org.lsmr.selfcheckout.devices.*;
import org.lsmr.selfcheckout.devices.listeners.*;
import org.lsmr.selfcheckout.*;


public class FinishesAddingItems {
	ReceiptPrinter printer;
	ScanItem scan;
	boolean isEnabled;

	/**
	 * Constructor to finish transaction
	 * @param SelfCheckoutStation 
	 * @param ScanItem
	 */
	public FinishesAddingItems(SelfCheckoutStation station, ScanItem scan) {
		scan.handheld = null;
		scan.main = null;
		
		printer = station.printer;
		this.scan = scan;
	}
	  
	/**
	 * Prompts a check for membership
	 */
	public void checkForMembership() {
		
	}
	
	/**
	 * prints the user receipt
	 */
	public void printRecipt() {
		
		String str = scan.getTotalList().toString();
		for(int i = 0; i < str.length(); i++) {
				printer.print(str.charAt(i));
		}
		
		printer.cutPaper();
		printer.removeReceipt();
	}
	
	private void printerListener() {
		printer.register(new ReceiptPrinterListener() {

			@Override
			public void enabled(AbstractDevice<? extends AbstractDeviceListener> device) {
				isEnabled = true;
			}

			@Override
			public void disabled(AbstractDevice<? extends AbstractDeviceListener> device) {
				isEnabled = false;
			}

			@Override
			public void outOfPaper(ReceiptPrinter printer) {}

			@Override
			public void outOfInk(ReceiptPrinter printer) {}

			@Override
			public void paperAdded(ReceiptPrinter printer) {}

			@Override
			public void inkAdded(ReceiptPrinter printer) {}
			
		});
	}
}
