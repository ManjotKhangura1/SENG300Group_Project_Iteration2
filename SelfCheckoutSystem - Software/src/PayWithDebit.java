import java.awt.image.BufferedImage;
import java.io.IOException;
import java.math.BigDecimal;

import org.lsmr.selfcheckout.Card;
import org.lsmr.selfcheckout.Card.CardData;
import org.lsmr.selfcheckout.devices.AbstractDevice;
import org.lsmr.selfcheckout.devices.CardReader;
import org.lsmr.selfcheckout.devices.SelfCheckoutStation;
import org.lsmr.selfcheckout.devices.listeners.AbstractDeviceListener;
import org.lsmr.selfcheckout.devices.listeners.CardReaderListener;
import org.lsmr.selfcheckout.external.CardIssuer;



public class PayWithDebit {

	private SelfCheckoutStation checkoutStation;
	public CardIssuer cardIssuer;
	
	/* Constructors */
	public PayWithDebit(SelfCheckoutStation station) {
		this.checkoutStation = station;
	}
	
	//pay with swipe
	public void PayWithSwipe(Card card, CardIssuer issuer,  BufferedImage signature, BigDecimal amount) {
		try {
			CardData data = this.checkoutStation.cardReader.swipe(card, signature);
			HandleDebitHold(data, issuer, amount);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	//pay with tap
	public void PayWithTap(Card card, CardIssuer issuer, BigDecimal amount) {
		try {
			CardData data = this.checkoutStation.cardReader.tap(card);
			HandleDebitHold(data, issuer, amount);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void PayWithInsert(Card card, CardIssuer issuer, BigDecimal amount, String pin) {
		try {
			CardData data = this.checkoutStation.cardReader.insert(card, pin);
			HandleDebitHold(data, issuer, amount);
			this.checkoutStation.cardReader.remove();
		} catch (IOException e) {
			this.checkoutStation.cardReader.remove(); 	// Either way if you succeed or fail you have to remove the card
			e.printStackTrace();
		}
	}
	
	/**
	 * Handles the debit transactions for your Card Issuer
	 * 2 cases : 
	 * 	a) If HoldNumber returns -1 then the transaction has failed, and you need to release the hold
	 * 	b) If HoldNumber returns anything else, then take that hold number and call PostTransaction, which will post & release the hold
	 * @param data Card data to be used
	 * @param issuer Issuer for the card
	 * @param amount Amount that you're looking to spend
	 */
	private void HandleDebitHold(CardData data, CardIssuer issuer, BigDecimal amount) {
		int holdNumber = issuer.authorizeHold(data.getNumber(), amount);
		if (holdNumber != -1) {
			issuer.postTransaction(data.getNumber(), holdNumber, amount);
		} else {
			issuer.releaseHold(data.getNumber(), holdNumber);
		}
	}
}
