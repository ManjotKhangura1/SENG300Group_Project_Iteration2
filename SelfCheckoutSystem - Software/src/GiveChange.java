import org.lsmr.selfcheckout.devices.SelfCheckoutStation;
import org.lsmr.selfcheckout.devices.SimulationException;

public class GiveChange {
	public SelfCheckoutStation station;
	public PayWithBanknote payWithBanknote;
	public double totalOwed;

	/**
	 * Give Change constructor that gives change if only banknotes were used to pay
	 * @param station The SelfCheckoutStation that is currently being used
	 * @param total The total cost of the transaction
	 * @param payWithBanknote The PayWithBanknote instance used to pay
	 */
	public GiveChange(SelfCheckoutStation station, double totalOwed, PayWithBanknote payWithBanknote) {
		setStation(station);
		setTotalOwed(totalOwed);
		setPayWithBanknote(payWithBanknote);

	}

	/**
	 * Give Change constructor that gives change if only coins were used to pay
	 * @param station station The SelfCheckoutStation that is currently being used
	 * @param total The total cost of the transaction
	 * @param payWithCoin The PayWithCoin instance used to pay
	 */
	public GiveChange(SelfCheckoutStation station, double total, PayWithCoin payWithCoin) {
		setStation(station);
		setTotalOwed(totalOwed);
	}

	/**
	 * Give Change constructor that gives change if only banknotes were used to pay
	 * @param station station The SelfCheckoutStation that is currently being used
	 * @param total The total cost of the transaction
	 * @param payWithBanknote The PayWithBanknote instance used to pay
	 * @param payWithCoin The PayWithCoin instance used to pay
	 */
	public GiveChange(SelfCheckoutStation station, double total, PayWithBanknote payWithBanknote, PayWithCoin payWithCoin) {
		setStation(station);
		setTotalOwed(totalOwed);
		setPayWithBanknote(payWithBanknote);
	}

	/**
	 * Gets selfCheckoutstation
	 * @return station selfCheckoutStation
	 */
	public SelfCheckoutStation getStation() {
		return station;
	}
	
	/**
	 * Sets the station to given selfCheckoutStation after checking if given is valid
	 * @param station The given selfCheckoutStation
	 * @throws SimulationException if a null station is given
	 */
	private void setStation(SelfCheckoutStation station) {
		//throws and exception if station given is null
		if (station == null) {
			throw new SimulationException(new NullPointerException("station is null"));
		}
		this.station = station;
	}
	
	/**
	 * Gets payWithBankNote instance 
	 * @return payWithBanknote the payWithBanknote instance
	 */
	public PayWithBanknote getPayWithBanknote() {
		return payWithBanknote;
	}

	/**
	 * Sets the payWithBankote to given payWithBanknote after checking if given is valid
	 * @param payWithBanknote given payWithBanknote
	 * @throws SimulationException if a null payWithBanknote is given
	 */
	private void setPayWithBanknote(PayWithBanknote payWithBanknote) {
		if (payWithBanknote == null) {			
			throw new SimulationException(new NullPointerException("payWithBanknote is null"));
		}
		this.payWithBanknote = payWithBanknote;
	}

	/**
	 * Gets the total owed from the transaction 
	 * @return totalOwed the amount owed
	 */
	public double getTotalOwed() {
		return totalOwed;
	}

	/**
	 * Sets the totalOwed to given totalOwed after checking if given is valid
	 * @param totalOwed the given totalOwed
	 * @throws SimulationException if totalOwed is less than 0.00
	 */
	private void setTotalOwed(double totalOwed) {
		//throws exception if totalOwed is less than 0.00
		if (totalOwed < 0.00) {
			throw new SimulationException(
					new IllegalArgumentException("totalOwed cannot be less than nothing"));
		}
		this.totalOwed = totalOwed;
	}
	

}
