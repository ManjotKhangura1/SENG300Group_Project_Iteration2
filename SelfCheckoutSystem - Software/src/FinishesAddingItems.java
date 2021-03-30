import org.lsmr.selfcheckout.devices.*;
import org.lsmr.selfcheckout.devices.listeners.*;

import java.util.ArrayList;

import org.lsmr.selfcheckout.*;


public class FinishesAddingItems {
	ReceiptPrinter printer;
	ScanItem scan;
	boolean isEnabled;
	
	PayWithCoin coin;
	PayWithBanknote banknote;
	PayWithDebit debit;
	//PayWithCredit credit;
	
	private double finalPrice;
	private ArrayList<String> finalList;

	/**
	 * Constructor to finish transaction
	 * @param SelfCheckoutStation 
	 * @param ScanItem
	 */
	public FinishesAddingItems(SelfCheckoutStation station, ScanItem scan, BaggingArea bags) {
		scan.handheld.disable();
		scan.main.disable();
		bags.baggingArea.disable();
		
		finalPrice = scan.getTotalPrice();
		finalList = scan.getTotalList();
		
		coin = new PayWithCoin(station);
		banknote = new PayWithBanknote(station);
		debit = new PayWithDebit(station);
		//credit = new PayWithCredit(station);
	}
	 
	/*
	 * here we would implement the initialization of the pay class which we will make next iteration
	 */
	
	/**
	 * @return the final price
	 */
	public double getPrice() {
		return finalPrice;
	}
	
	/**
	 * @return the final list of items
	 */
	public ArrayList<String> getList(){
		return finalList;
	}
}
