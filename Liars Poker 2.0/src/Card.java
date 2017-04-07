
public class Card {
	private String value, type;
	private int dominance;
	private String[] types = {"Spades","Hearts","Clubs","Diamonds"};
	private String[] values = {"Two","Three","Four","Five","Six","Seven","Eight","Nine","Ten","Jack","Queen","King","Ace"};
	
	
	public Card(int type, int value){
		this.value = values[value];
		this.type = types[type];
		dominance = value;
	}
	
	public int getDominance(){
		return dominance;
	}
	
	public String getValue(){
		return value;
	}
	
	public String getType(){
		return type;
	}
	
	public String toString(){
		return value + " of " + type;
	}
	
}
