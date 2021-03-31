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
		BigDecimal[] coinDenominations = { BigDecimal.valueOf(0.05), BigDecimal.valueOf(0.10), BigDecimal.valueOf(0.25),
				BigDecimal.valueOf(1.0), BigDecimal.valueOf(2.0) };
		int maxWeight = 23000;
		int scaleSensitivity = 10;
		station = new SelfCheckoutStation(currency, noteDenominations, coinDenominations, maxWeight, scaleSensitivity);

		//load the dispensers
		for(int i = 0; i < station.banknoteDispensers.size(); i++) {
			Banknote banknoteToLoad = new Banknote(noteDenominations[i],currency);
			for(int j = 0; j < 100; j++) {				
				station.banknoteDispensers.get(noteDenominations[i]).load(banknoteToLoad);
			}
		}
		
		for(int i = 0; i < station.coinDispensers.size(); i++) {
			Coin coinToLoad = new Coin(coinDenominations[i], currency);
			for(int j = 0; j < 100; j++) {
				station.coinDispensers.get(coinDenominations[i]).load(coinToLoad);
			}			
		}
			
		// creates a banknote and a coin
		int value = 5;
		banknote = new Banknote(value, currency);
		coin = new Coin(coinDenominations[0], currency);
		
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

	@Test //Testing the fringe case when user pays exact amount
	public void testCalculateChangeNoChange() {
		double totalOwed = 5.00;
		payWithBanknote.pay(banknote);
		GiveChange giveChange = new GiveChange(station, currency, totalOwed, payWithBanknote, payWithCoin);

		giveChange.calculateChange();
		
		double expected = 0.00;
		
		assertEquals(expected, giveChange.getChange(),0.00);
	}
	
	@Test //Testing normal use
	public void testCalculateChange() {
		double totalOwed = 4.25;
		payWithBanknote.pay(banknote);
		GiveChange giveChange = new GiveChange(station, currency, totalOwed, payWithBanknote, payWithCoin);

		giveChange.calculateChange();
		
		double expected = 0.75;
		
		assertEquals(expected, giveChange.getChange(),0.00);
	}


	@Test //Testing if exception is thrown if there is a dangling banknote in the slot
	public void testDispenseDangling() {
		try {
			double totalOwed = 1.00;
			GiveChange giveChange = new GiveChange(station, currency, totalOwed, payWithBanknote, payWithCoin);
			
			Banknote tester = new Banknote(5, giveChange.getCurrency());
			giveChange.getStation().banknoteOutput.emit(tester);
			giveChange.dispense();
			fail("Should have thrown exception"); //fail because an exception should have been thrown and thus should not have reached this line
		} catch (Exception e) {
			assertTrue("Simulation Exception thrown because of a dangling banknote in the banknote slot", e instanceof SimulationException);
		}
	}
	
	@Test //Testing if exception is thrown if banknote slot is disabled
	public void testDispenseBankenoteSlotDisabled() {
		try {
			double totalOwed = 1.00;
			GiveChange giveChange = new GiveChange(station, currency, totalOwed, payWithBanknote, payWithCoin);
			giveChange.getStation().banknoteOutput.disable();
			giveChange.dispense();
			fail("Should have thrown exception"); //fail because an exception should have been thrown and thus should not have reached this line
		} catch (Exception e) {
			assertTrue("Simulation Exception thrown because banknote ouput slot is disabled", e instanceof SimulationException);
		}
	}
	
	@Test //Testing if exception is thrown if coin tray is disabled
	public void testDispenseCoinTrayDisabled() {
		try {
			double totalOwed = 1.00;
			GiveChange giveChange = new GiveChange(station, currency, totalOwed, payWithBanknote, payWithCoin);
			giveChange.getStation().coinTray.disable();
			giveChange.dispense();
			fail("Should have thrown exception"); //fail because an exception should have been thrown and thus should not have reached this line
		} catch (Exception e) {
			assertTrue("Simulation Exception thrown because coin tray is disabled", e instanceof SimulationException);
		}
	}
	
	@Test //Testing if exception is thrown if coin tray and banknote ouput is disabled
	public void testDispenseBothDisabled() {
		try {
			double totalOwed = 1.00;
			
			banknote = new Banknote(10, currency);
			payWithBanknote.pay(banknote);
			coin = new Coin(BigDecimal.valueOf(2.0), currency);
			payWithCoin.pay(coin);
			
			GiveChange giveChange = new GiveChange(station, currency, totalOwed, payWithBanknote, payWithCoin);
			giveChange.getStation().coinTray.disable();
			giveChange.getStation().banknoteOutput.disable();
			
			giveChange.dispense();
			fail("Should have thrown exception"); //fail because an exception should have been thrown and thus should not have reached this line
		} catch (Exception e) {
			//fails if exception thrown because this is a valid station
			assertTrue("Simulation Exception thrown because coin tray and banknote slot are disabled", e instanceof SimulationException);
		}
	}
	
	@Test //Testing if exception is thrown if a needed banknote dispenser is disabled
	public void testDispenseBanknoteDispenderDisabled() {
		try {
			double totalOwed = 1.00;
			
			banknote = new Banknote(10, currency);
			payWithBanknote.pay(banknote);
			coin = new Coin(BigDecimal.valueOf(2.0), currency);
			payWithCoin.pay(coin);
			
			GiveChange giveChange = new GiveChange(station, currency, totalOwed, payWithBanknote, payWithCoin);
			giveChange.getStation().banknoteDispensers.get(10).disable();
			
			giveChange.dispense();
			fail("Should have thrown exception"); //fail because an exception should have been thrown and thus should not have reached this line
		} catch (Exception e) {
			//fails if exception thrown because this is a valid station
			assertTrue("Simulation Exception thrown because banknote dispenser is disabled", e instanceof SimulationException);
		}
	}
	
	@Test //Testing if exception is thrown if a needed banknote dispenser is disabled
	public void testDispenseCoinDispenderDisabled() {
		try {
			double totalOwed = 1.00;
			
			banknote = new Banknote(10, currency);
			payWithBanknote.pay(banknote);
			coin = new Coin(BigDecimal.valueOf(2.0), currency);
			payWithCoin.pay(coin);
			
			GiveChange giveChange = new GiveChange(station, currency, totalOwed, payWithBanknote, payWithCoin);
			giveChange.getStation().coinDispensers.get(BigDecimal.valueOf(1.0)).disable();
			
			giveChange.dispense();
			fail("Should have thrown exception"); //fail because an exception should have been thrown and thus should not have reached this line
		} catch (Exception e) {
			//fails if exception thrown because this is a valid station
			assertTrue("Simulation Exception thrown because coin dispenser is disabled", e instanceof SimulationException);
		}
	}
	
	@Test //Testing system when a needed denomination is empty
	public void testDispenseBanknoteEmpty() {
		try {			
			double totalOwed = 2.00;
			
			banknote = new Banknote(10, currency);
			payWithBanknote.pay(banknote);
			coin = new Coin(BigDecimal.valueOf(2.0), currency);
			payWithCoin.pay(coin);
			
			
			GiveChange giveChange = new GiveChange(station, currency, totalOwed, payWithBanknote, payWithCoin);
			station.banknoteOutput.removeDanglingBanknote();
			giveChange.dispense();
			
			//if dispense worked correctly, change variable should be updated to not owe the user
			double expected = 0.00;
			
			assertEquals(expected, giveChange.getChange(),0.00);
		} catch (Exception e) {
			//fails if exception thrown because this is a valid station
			
			fail("Exception thrown for valid code with");
		}
		
	}
	
	@Test //Testing normal function - banknote only change
	public void testDispenseBanknoteOnly() {
		try {			
			double totalOwed = 2.00;
			
			banknote = new Banknote(20, currency);
			payWithBanknote.pay(banknote);
			coin = new Coin(BigDecimal.valueOf(2.0), currency);
			payWithCoin.pay(coin);
			
			
			GiveChange giveChange = new GiveChange(station, currency, totalOwed, payWithBanknote, payWithCoin);
			station.banknoteOutput.removeDanglingBanknote();
			giveChange.dispense();
			
			//if dispense worked correctly, change variable should be updated to not owe the user
			double expected = 0.00;
			
			assertEquals(expected, giveChange.getChange(),0.00);
		} catch (Exception e) {
			//fails if exception thrown because this is a valid station
			
			fail("Exception thrown for valid code with");
		}
		
	}
	
	@Test //Testing normal function - coin only change
	public void testDispenseCoinOnly() {
		try {			
			double totalOwed = 1.05;
			
			payWithBanknote.pay(banknote);
			payWithCoin.pay(coin);
			
			GiveChange giveChange = new GiveChange(station, currency, totalOwed, payWithBanknote, payWithCoin);
			giveChange.dispense();
			
			//if dispense worked correctly, change variable should be updated to not owe the user
			double expected = 0.00;
			
			assertEquals(expected, giveChange.getChange(),0.00);
		} catch (Exception e) {
			//fails if exception thrown because this is a valid station
			fail("Exception thrown for valid code");
		}
		
	}
	
	@Test //Testing normal function - banknote and coin only change
	public void testDispense() {
		try {			
			double totalOwed = 2.05;
			
			banknote = new Banknote(10, currency);
			payWithBanknote.pay(banknote);
			coin = new Coin(BigDecimal.valueOf(2.0), currency);
			payWithCoin.pay(coin);
			
			
			GiveChange giveChange = new GiveChange(station, currency, totalOwed, payWithBanknote, payWithCoin);
			station.banknoteOutput.removeDanglingBanknote();
			giveChange.dispense();
			
			//if dispense worked correctly, change variable should be updated to not owe the user
			double expected = 0.00;
			
			assertEquals(expected, giveChange.getChange(),0.00);
		} catch (Exception e) {
			//fails if exception thrown because this is a valid station
			
			fail("Exception thrown for valid code with :" + e);
		}
	}







}
