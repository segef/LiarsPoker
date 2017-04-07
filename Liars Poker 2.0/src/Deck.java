import java.util.Random;

public class Deck{
	private Player[] Players;
	private Card[] deck = new Card[52];
	
	public Deck(Card[] deck, Player[] Players){
		this.deck = deck;
		this.Players = Players;
	}
	
	public Deck(Card[] deck){
		this.deck = deck;
	}
	
	public Deck(Player[] Players){
		this.Players = Players;
	}
	

	public void initialize(){
		for(int i=0;i<4;i++){
			for(int j=0;j<13;j++){
				deck[13*i + j] = new Card(i,j);
			}
		}		
	}
	
	public Card[] getDeck(){
		return deck;
	}
	
	public String deal(){
		for(Player p : Players){
			Card[] pCards = new Card[p.getNumberOfCards()];
			for(int i = 0 ; i<pCards.length ; i++){
				pCards[i] = deck[i];
			}
			p.setCards(pCards);
			Card[] newDeck = new Card[deck.length - pCards.length];
			for(int i = 0; i<newDeck.length ; i++){
				newDeck[i] = deck[i + pCards.length];
			}
			deck = newDeck;
			p.setdealed();
		}
		return "The Deck is being dealed...";
	}
	
	public String shuffle(){
		Random ran = new Random();
		for(int i = 0; i<deck.length ; i++){
			int rand = ran.nextInt(deck.length);
			Card a = deck[i];
			deck[i] = deck[rand];
			deck[rand] = a;			
		}
		return "The Deck is being shuffled...";
	}
	
	public int numOfCard(String s){
		int ret = 0;
		for(int i=0; i<deck.length; i++){
			if(deck[i].getValue().equals(s)){
				ret++;
			}
		}
		return ret;
	}
	
	public String toString(){
		String ret = "";
		for(int i = 0; i<deck.length ; i++){
			ret = ret + deck[i].toString() + "\n";
		}
		return ret;
	}
	
}
