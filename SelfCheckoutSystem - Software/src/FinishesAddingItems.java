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
	public FinishesAddingItems(SelfCheckoutStation station, ScanItem scan) {
		scan.handheld = null;
		scan.main = null;
		
		coin = new PayWithCoin(station);
		banknote = new PayWithBanknote(station);
		//debit = new PayWithDebit(station);
		//credit = new PayWithCredit(station);
		
		
		printer = station.printer;
		this.scan = scan;
		printerListener();
	}
	  
	/**
	 * Prompts a check for membership
	 */
	public void checkForMembership() {
		
	}
	/**
	 * starts the paying process
	 */
	public void pay() {
		
	}
	
	/**
	 * prints the user receipt
	 */
	public void printRecipt() {
		
		String str = scan.getTotalList().toString(); // the list of items
		for(int i = 0; i < str.length(); i++) {
				printer.print(str.charAt(i)); //one character at a time
		}
		
		//could also print out more like price and weight here
		
		printer.cutPaper();
		printer.removeReceipt();
	}
	
	/**
	 * creating instance of the printer listener
	 */
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
