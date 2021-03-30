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
	public void testConstructorNullStation() {
		try {
			double totalOwed = 0.01;
			GiveChange giveChange = new GiveChange(null, currency, totalOwed, payWithBanknote, payWithCoin);
			fail("Should have thrown exception"); //fail because an exception should have been thrown and thus should not have reached this line
		} catch (Exception e) {
			assertTrue("Simulation Exception thrown because null station provided", e instanceof SimulationException);
		}
	}
	
	@Test //Tests if the proper exception is thrown when constructor is called with a null currency
	public void testConstructorNullCurrency() {
		try {
			double totalOwed = 0.01;
			GiveChange giveChange = new GiveChange(station, null, totalOwed, payWithBanknote, payWithCoin);
			fail("Should have thrown exception"); //fail because an exception should have been thrown and thus should not have reached this line
		} catch (Exception e) {
			assertTrue("Simulation Exception thrown because null currency provided", e instanceof SimulationException);
		}
	}
	
	@Test //Tests if the proper exception is thrown when constructor is called with a negative totalOwed
	public void testConstructorNegativeTotalOwed() {
		try {
			double totalOwed = -0.01;
			GiveChange giveChange = new GiveChange(station, currency, totalOwed, payWithBanknote, payWithCoin);
			fail("Should have thrown exception"); //fail because an exception should have been thrown and thus should not have reached this line
		} catch (Exception e) {
			assertTrue("Simulation Exception thrown because negative totalOwed provided", e instanceof SimulationException);
		}
	}
	
	@Test //Tests if the proper exception is thrown when constructor is called with a null PayWithBanknote
	public void testConstructorNullPayWithBanknote() {
		try {
			double totalOwed = 0.01;
			payWithBanknote = null;
			GiveChange giveChange = new GiveChange(station, currency, totalOwed, payWithBanknote, payWithCoin);
			fail("Should have thrown exception"); //fail because an exception should have been thrown and thus should not have reached this line
		} catch (Exception e) {
			assertTrue("Simulation Exception thrown because null payWithBanknote provided", e instanceof SimulationException);
		}
	}
	
	@Test //Tests if the proper exception is thrown when constructor is called with a null PayWithCoin
	public void testConstructorNullPayWithBankCoin() {
		try {
			double totalOwed = 0.01;
			payWithCoin = null;
			GiveChange giveChange = new GiveChange(station, currency, totalOwed, payWithBanknote, payWithCoin);
			fail("Should have thrown exception"); //fail because an exception should have been thrown and thus should not have reached this line
		} catch (Exception e) {
			assertTrue("Simulation Exception thrown because null payWithCoin provided", e instanceof SimulationException);
		}
	}

	@Test //Testing normal use
	public void testConstructor() {
		try {
			double totalOwed = 0.01;
			GiveChange giveChange = new GiveChange(station, currency, totalOwed, payWithBanknote, payWithCoin);
			assertNotNull(giveChange);
		} catch (Exception e) {
			//fails if exception thrown because this is a valid station
			fail("Exception thrown for valid code");
		}
	}

	@Test
	public void testCalculateChangeNoChange() {
		double totalOwed = 5.00;
		payWithBanknote.pay(banknote);
		GiveChange giveChange = new GiveChange(station, currency, totalOwed, payWithBanknote, payWithCoin);
		
		giveChange.calculateChange();
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
