import org.lsmr.selfcheckout.Card;
import org.lsmr.selfcheckout.Card.CardData;
import org.lsmr.selfcheckout.devices.AbstractDevice;
import org.lsmr.selfcheckout.devices.CardReader;
import org.lsmr.selfcheckout.devices.SelfCheckoutStation;
import org.lsmr.selfcheckout.devices.listeners.AbstractDeviceListener;
import org.lsmr.selfcheckout.devices.listeners.CardReaderListener;

public class ScansMembershipCard { //through card reader

	public SelfCheckoutStation aSelfCheckoutStation;
	public Card aMembershipCard;
	public boolean cardIsInserted; 
	//just use this variable from CR class using . instead of creating this
	
	public ScansMembershipCard(SelfCheckoutStation station, Card aCard) {
		aSelfCheckoutStation= station;
		aMembershipCard= aCard;
		//do the registering here with CSL
	}
	
	public void tapMembershipCard(Card aCard) {

		
	}
	
	public void swipeMembershipCard() {
		

		
	}
	
	public void insertMembershipCard() {
	
}
	

	//read the card using class CardReader which makes use of CRListener
	//different ways to read card: tap, swipe, insert
	//SCS has a cardReader so use everything off that
	//all functions in CR basically just read the card & return the data
	
	  private CardReaderListener csl = new CardReaderListener() {

		@Override
		public void enabled(AbstractDevice<? extends AbstractDeviceListener> device) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void disabled(AbstractDevice<? extends AbstractDeviceListener> device) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void cardInserted(CardReader reader) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void cardRemoved(CardReader reader) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void cardTapped(CardReader reader) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void cardSwiped(CardReader reader) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void cardDataRead(CardReader reader, CardData data) {
			// TODO Auto-generated method stub
			
		}
		  
	  };
	
}

