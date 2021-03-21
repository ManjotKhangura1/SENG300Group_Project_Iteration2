import java.util.ArrayList;

import org.lsmr.selfcheckout.devices.*;
import org.lsmr.selfcheckout.devices.listeners.*;
import org.lsmr.selfcheckout.*;

public class ScanItem {
	//private double totPrice = 0;
	private double totWeight = 0;
	private ArrayList<String> totList;
	private BarcodeScanner main;
	private BarcodeScanner handheld;
	private boolean isEnabled = false;

	/**
	 * scans items
	 * @param BarcodedItem item
	 * @throws SimulationException if SelfCheckoutStation is null
	 */
	public ScanItem(SelfCheckoutStation station) {
		if(station == null) throw new SimulationException(new NullPointerException("station is null"));
		
		main = station.mainScanner;
		main.enable();
		handheld = station.handheldScanner;
		handheld.enable();
		totList = new ArrayList<String>();
		
		scannerListener();
	}
	
	/**
	 * Scans a barcoded item from the main scanner
	 * @param a Barcoded item
	 * @throws SimulationException if barcodedItem is null
	 */
	public void scanFromMain(BarcodedItem item) {
		if(item == null) throw new SimulationException(new NullPointerException("item is null"));
		
		main.scan(item);
		updateWeight(item.getWeight());
	}
	
	/**
	 * Scans a barcoded item from the handheld scanner
	 * @param a Barcoded item
	 * @throws SimulationException if barcodedItem is null
	 */
	public void scanFromHandheld(BarcodedItem item) {
		if(item == null) throw new SimulationException(new NullPointerException("item is null"));
		
		handheld.scan(item);
		updateWeight(item.getWeight());
		
	}
	
	/**
	 * adds the last scanned item to the total weight 
	 * 
	 * this is not an ideal way to update weight, it should be updated through the listener but the listener does not handle 
	 * barcodedItem only barcode 
	 * 
	 * @param double
	 */
	private void updateWeight(double d) {
		totWeight += d;
	}	
	
	/**
	 * adds the last scanned items price to the total price
	 * 
	 * at the moment there is no price attached to barcodedItem, but this is here for future implementation
	 * this is not an ideal way to update price it would be better handled through the listener
	 * @param double
	 * 
	 * private void updatePrice(double p){
	 * 	totPrice += p;
	 * }
	 */
	
	/**
	 * returns the total weight of scanned items
	 * @return total weight
	 */
	public double getTotalWeight() {
		return totWeight;
	}
	
	/**
	 * returns the list of names of scanned items
	 * @return total list
	 */
	public ArrayList<String> getTotalList() {
		return totList;
	}
	
	/**
	 * returns the total price of scanned items
	 * @return total price
	 * 
	 * public double getTotalPrice(){
	 * 	return totPrice;
	 * }
	 */
	
	public boolean getIsEnabled() {
		return isEnabled;
	}
	
	
	/**
	 * create an instance of the listener 
	 */
	private void scannerListener() {
		main.register(new BarcodeScannerListener() {

			@Override
			public void enabled(AbstractDevice<? extends AbstractDeviceListener> device) {
				isEnabled = true;
			}

			@Override
			public void disabled(AbstractDevice<? extends AbstractDeviceListener> device) {
				isEnabled = false;
			}

			@Override
			public void barcodeScanned(BarcodeScanner barcodeScanner, Barcode barcode) {
				totList.add(barcode.toString());
			}
			
		});
		handheld.register(new BarcodeScannerListener() {

			@Override
			public void enabled(AbstractDevice<? extends AbstractDeviceListener> device) {
				isEnabled = true;
			}

			@Override
			public void disabled(AbstractDevice<? extends AbstractDeviceListener> device) {
				isEnabled = false;
			}

			@Override
			public void barcodeScanned(BarcodeScanner barcodeScanner, Barcode barcode) {
				totList.add(barcode.toString());
			}
			
		});
	}
}
