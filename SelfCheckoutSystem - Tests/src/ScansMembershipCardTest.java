import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Currency;

import org.junit.Before;
import org.junit.Test;
import org.lsmr.selfcheckout.Card;
import org.lsmr.selfcheckout.Coin;
import org.lsmr.selfcheckout.Item;
import org.lsmr.selfcheckout.devices.OverloadException;
import org.lsmr.selfcheckout.devices.SelfCheckoutStation;
import org.lsmr.selfcheckout.devices.SimulationException;

public class ScansMembershipCardTest {
	
	Currency currency= Currency.getInstance("CAD");
	int[] bankNoteDenominations= {5,10,20,50,100};
	BigDecimal[] coinDenominations= {new BigDecimal(0.01), new BigDecimal(0.05),
			 new BigDecimal(0.1),new BigDecimal(0.25), new BigDecimal(0.50)};
	int scaleMaximumWeight= 1000;
	int scaleSensitivity=1;
	
	SelfCheckoutStation scs = new SelfCheckoutStation(currency, bankNoteDenominations,
			coinDenominations, scaleMaximumWeight, scaleSensitivity);
	
	ScansMembershipCard scanMembership = new ScansMembershipCard(scs);
		
	@Test
	public void testTapMembershipCard_Valid() {
		
		
		Card validCard = new Card("Membership", "1234567", "A Name", null, null, true, false);
		
		scanMembership.tapMembershipCard(validCard);
		
		boolean actual= scanMembership.cardDataIsRead;
		boolean expected= true;
		
	}
	
	@Test
	public void testTapMembershipCard_Invalid() {
		
		
		Card validCard = new Card("Membership", "asdkjsdfjhs", "askjd", null, null, true, false);
		
		scanMembership.tapMembershipCard(validCard);
		
		boolean actual= scanMembership.cardDataIsRead;
		boolean expected= false;
		
	}
	
}
