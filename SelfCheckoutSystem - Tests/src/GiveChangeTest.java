import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.Currency;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.lsmr.selfcheckout.Banknote;
import org.lsmr.selfcheckout.Coin;
import org.lsmr.selfcheckout.devices.SelfCheckoutStation;
import org.lsmr.selfcheckout.devices.SimulationException;

public class GiveChangeTest {
	SelfCheckoutStation station;
	Currency currency;
	PayWithBanknote payWithBanknote;
	PayWithCoin payWithCoin;
	Banknote banknote;
	Coin coin;

	@Before
	public void setUp() throws Exception {
		// Creates a self checkout station
		currency = Currency.getInstance("CAD");
		int[] noteDenominations = { 5, 10, 20, 50, 100 };
		BigDecimal[] coinDenomonations = { BigDecimal.valueOf(0.05), BigDecimal.valueOf(0.10), BigDecimal.valueOf(0.25),
				BigDecimal.valueOf(1.0), BigDecimal.valueOf(2.0) };
		int maxWeight = 23000;
		int scaleSensitivity = 10;
		station = new SelfCheckoutStation(currency, noteDenominations, coinDenomonations, maxWeight, scaleSensitivity);

		// creates a banknote and a coin
		int value = 5;
		banknote = new Banknote(value, currency);
		coin = new Coin(coinDenomonations[0], currency);
		
		//creates a payWithCoin and a payWithBanknote
		payWithBanknote = new PayWithBanknote(station);
		payWithCoin = new PayWithCoin(station);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test //Tests if the proper exception is thrown when constructor is called with a null station
	public void testBanknoteConstructorNullStation() {
		try {
			double totalOwed = 0.01;
			GiveChange giveChange = new GiveChange(null, currency, totalOwed, payWithBanknote);
			fail("Should have thrown exception"); //fail because an exception should have been thrown and thus should not have reached this line
		} catch (Exception e) {
			assertTrue("Simulation Exception thrown because null station provided", e instanceof SimulationException);
		}
	}
	
	@Test //Tests if the proper exception is thrown when constructor is called with a null currency
	public void testBanknoteConstructorNullCurrency() {
		try {
			double totalOwed = 0.01;
			GiveChange giveChange = new GiveChange(station, null, totalOwed, payWithBanknote);
			fail("Should have thrown exception"); //fail because an exception should have been thrown and thus should not have reached this line
		} catch (Exception e) {
			assertTrue("Simulation Exception thrown because null currency provided", e instanceof SimulationException);
		}
	}
	
	@Test //Tests if the proper exception is thrown when constructor is called with a negative totalOwed
	public void testBanknoteConstructorNegativeTotalOwed() {
		try {
			double totalOwed = -0.01;
			GiveChange giveChange = new GiveChange(station, currency, totalOwed, payWithBanknote);
			fail("Should have thrown exception"); //fail because an exception should have been thrown and thus should not have reached this line
		} catch (Exception e) {
			assertTrue("Simulation Exception thrown because negative totalOwed provided", e instanceof SimulationException);
		}
	}

	@Test
	public void testGiveChangeSelfCheckoutStationCurrencyDoublePayWithCoin() {
		fail("Not yet implemented");
	}

	@Test
	public void testGiveChangeSelfCheckoutStationCurrencyDoublePayWithBanknotePayWithCoin() {
		fail("Not yet implemented");
	}

	@Test
	public void testCalculateChange() {
		fail("Not yet implemented");
	}

	@Test
	public void testDispense() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetStation() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetCurrency() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetPayWithBanknote() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetPayWithCoin() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetTotalOwed() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetTotalPaid() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetChange() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetBanknoteSlotEnabled() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetCoinTrayEnabled() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetBanknoteRemoved() {
		fail("Not yet implemented");
	}

}
