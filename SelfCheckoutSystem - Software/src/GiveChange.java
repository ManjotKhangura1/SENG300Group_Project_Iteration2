import org.lsmr.selfcheckout.devices.SelfCheckoutStation;
import org.lsmr.selfcheckout.devices.SimulationException;

public class GiveChange {
	public SelfCheckoutStation station;
	public PayWithBanknote payWithBanknote;
	public PayWithCoin payWithCoin;
	public double totalOwed;
	public double totalPaid;

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
		setTotalPaid(payWithBanknote.getTotalPaid());
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
		setPayWithCoin(payWithCoin);
		setTotalPaid(payWithCoin.getcoinsPaid());
	}

	/**
	 * Give Change constructor that gives change if banknotes and coins were used to pay
	 * @param station station The SelfCheckoutStation that is currently being used
	 * @param total The total cost of the transaction
	 * @param payWithBanknote The PayWithBanknote instance used to pay
	 * @param payWithCoin The PayWithCoin instance used to pay
	 */
	public GiveChange(SelfCheckoutStation station, double total, PayWithBanknote payWithBanknote, PayWithCoin payWithCoin) {
		double sum;
		
		setStation(station);
		setTotalOwed(totalOwed);
		setPayWithBanknote(payWithBanknote);
		setPayWithCoin(payWithCoin);
	
		//grabbing the total paid by banknote and coins and adding them together
		sum = payWithBanknote.getTotalPaid() + payWithCoin.getcoinsPaid();
		setTotalPaid(sum);
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
	 * Gets payWithCoin instance 
	 * @return payWithCoin the payWithCoin instance
	 */
	public PayWithCoin getPayWithCoin() {
		return payWithCoin;
	}

	/**
	 * Sets the payWithCoin to given payWithCoin after checking if given is valid
	 * @param payWithCoin given payWithCoin
	 * @throws SimulationException if a null payWithCoin is given
	 */
	private void setPayWithCoin(PayWithCoin payWithCoin) {
		if (payWithCoin == null) {			
			throw new SimulationException(new NullPointerException("payWithCoin is null"));
		}
		this.payWithCoin = payWithCoin;
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
	
	/**
	 * Gets the total owed from the transaction 
	 * @return totalOwed the amount owed
	 */
	public double getTotalPaid() {
		return totalPaid;
	}

	/**
	 * Sets the totalPaid to given totalPaid after checking if given is valid
	 * @param totalPaid the given totalPaid
	 * @throws SimulationException if totalPaid is less than 0.00
	 */
	private void setTotalPaid(double totalPaid) {
		//throws exception if totalOwed is less than 0.00
		if (totalPaid < 0.00) {
			throw new SimulationException(
					new IllegalArgumentException("totalPaid cannot be less than nothing"));
		}
		this.totalPaid = totalPaid;
	}
	

}
