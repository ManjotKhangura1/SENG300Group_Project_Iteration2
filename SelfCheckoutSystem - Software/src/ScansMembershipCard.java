import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import org.lsmr.selfcheckout.Card;
import org.lsmr.selfcheckout.Card.CardData;
import org.lsmr.selfcheckout.devices.AbstractDevice;
import org.lsmr.selfcheckout.devices.CardReader;
import org.lsmr.selfcheckout.devices.SelfCheckoutStation;
import org.lsmr.selfcheckout.devices.listeners.AbstractDeviceListener;
import org.lsmr.selfcheckout.devices.listeners.CardReaderListener;
import org.lsmr.selfcheckout.products.BarcodedProduct;

public class ScansMembershipCard { //through card reader

	public SelfCheckoutStation aSelfCheckoutStation;
	
	
	//initially card data is not read
	public boolean cardDataIsRead=false;
	
	public CardData data;
	
	Card validCard1 = new Card("Membership", "1234567", "A Name", null, null, false, false);
	
	Card validCard2 = new Card("Membership", "2345678", "A Name", null, null, false, false);
	//creating a database with valid card numbers
	public  HashMap<String, Card> validMembershipData= new HashMap<>();

	

	//read the card using class CardReader which makes use of CardReaderListener
	//different ways to read card: tap, swipe, insert
	//SelfCheckoutstation has a cardReader so use everything off that
	//all functions in CardReader basically just read the card using different methods & return the data
	
	public CardReaderListener crl = new CardReaderListener() {
		
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
			cardDataIsRead=true;
			
		}	  
	  };
	  
	  
	
	//constructor for use case
	//passing instance of self checkout station because all operations are done through it
	public ScansMembershipCard(SelfCheckoutStation station) {
		
		aSelfCheckoutStation= station;
		
		//registering card reader and listener
		aSelfCheckoutStation.cardReader.register(crl);
		
		//initializing database
		 validMembershipData.put("1234567", validCard1);
		 validMembershipData.put("2345678", validCard2);
		
	}
	
	
	
	//tap membership card and the data will be read and returned
	public CardData tapMembershipCard(Card aMembershipCard) {
		if(validMembershipData.containsKey(aMembershipCard)) {
			try {
				data= aSelfCheckoutStation.cardReader.tap(aMembershipCard);
				
				cardDataIsRead=true;
				
				if(cardDataIsRead==true) {
					return data;
				}
					
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return null;
	}
	//swipe membership card and the data will be read and returned
	public CardData swipeMembershipCard(Card aMembershipCard, BufferedImage aSignature) {
		
		try {
			data=aSelfCheckoutStation.cardReader.swipe(aMembershipCard, aSignature);
			
			if(cardDataIsRead==true) {
				return data;
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
			return null;
	}
	
	//insert membership card and the data will be read and returned
	public CardData insertMembershipCard(Card aMembershipCard, String aPin) {
	
		try {
			data=aSelfCheckoutStation.cardReader.insert(aMembershipCard, aPin);
			
			if(cardDataIsRead==true) {
				
				aSelfCheckoutStation.cardReader.remove();
				
				return data;
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;	
}
	  
}
