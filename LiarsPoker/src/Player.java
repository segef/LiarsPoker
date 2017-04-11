public class Player {
	
	private String name;
	private boolean dealed, blind, lose, mark;
	private int numberOfCards;
	protected Card[] Cards = new Card[2];
	private Player nextPlayer;
	protected Player prevPlayer;
	public static String message="";
	
	public Player(String name, int numberOfCards){
		this.name = name;
		this.numberOfCards = numberOfCards;
		this.nextPlayer = null;
		this.prevPlayer = null;
		dealed = false;
		blind = false;
		lose = false;
	}
	
	public Player(String name){
		this.name = name;
		this.numberOfCards = 2;
	}
	
	public Guess guess(Guess prev, String act){
		Guess actuall = new Guess(this);
		actuall.setheight(act);
		if(prev.getheight()<actuall.getheight()){
			return actuall;
		}else{
			return null;
		}
	}
	
	public Guess firstGuess(String act){
		Guess actuall = new Guess(this);
		actuall.setheight(act);
		return actuall;
	}
	
	public Player openCards(Guess prev){
		String[] temp = prev.getSortedGuesses()[prev.getheight()].split(" ");
		int[] tmp = new int[3];
		tmp[0] = Integer.parseInt(temp[0]);
		tmp[1] = Integer.parseInt(temp[1]);
		tmp[2] = Integer.parseInt(temp[2]);
		int[] guessNums = prev.getPossibleGuesses()[tmp[0]];
		String[] types = prev.getPossibleTypes();
		
		if(tmp[0] == 2 || tmp[0] == 5 || tmp[0] == 8){
			int expectedX = guessNums[0];
			int expectedY = guessNums[1];
			int realX = CardsOnTable().numOfCard(types[tmp[1]]);
			int realY = CardsOnTable().numOfCard(types[tmp[2]]);
			int numOf2 = CardsOnTable().numOfCard("Two");
			int x = 0;
			int y = 0;
			
			if(expectedX>realX){
				x = expectedX - realX;
			}
			if(expectedY>realY){
				y = expectedY - realY;
			}
			if(x+y > numOf2){
				setMessage(prevPlayer.incNumberOfCards());
				if(prevPlayer.lose){
					setMark();
					return this;
				}
				prevPlayer.setMark();
				return this;
			}else{
				prevPlayer.setMessage(this.incNumberOfCards());
				if(lose){
					prevPlayer.setMark();
					return prevPlayer;
				}
				setMark();
				return prevPlayer;
			}
		}else{
			int expectedX = guessNums[0];
			int realX = CardsOnTable().numOfCard(types[tmp[1]]);
			int numOf2 = CardsOnTable().numOfCard("Two");
			int x = 0;
			
			if(expectedX>realX){
				x = expectedX - realX;
			}
			if(x > numOf2){
				setMessage(prevPlayer.incNumberOfCards());
				if(prevPlayer.lose){
					setMark();
					return this;
				}
				prevPlayer.setMark();
				return this;
			}else{
				prevPlayer.setMessage(this.incNumberOfCards());
				if(lose){
					prevPlayer.setMark();
					return prevPlayer;
				}
				setMark();
				return prevPlayer;
			}
		}
	}
	
	
	
	public Player getMarkedPlayer(){
		Player x = this;
		do{
			if(x.getMark() == true){
				break;
			}
			x = x.getNextPlayer();
		}while(x != this);
		return x;
	}
	
	private void setMessage(String s){
		message = s;
	}
	
	public boolean getMark(){
		return mark;
	}
	
	public String getName(){
		return name;
	}
	
	public int getNumberOfCards(){
		return numberOfCards;
	}
	
	public Card[] getCards(){
		return Cards;
	}
	
	public Player getNextPlayer(){
		return nextPlayer;
	}
	
	public Player getPrevPlayer(){
		return prevPlayer;
	}
	
	public boolean getdealed(){
		return dealed;
	}
	
	public boolean getlost(){
		return lose;
	}
	
	public boolean getBlind(){
		return blind;
	}
	
	protected String setBlind(){
		blind = true;
		return (name + " is blind!\n");
	}
	
	protected void setMark(){
		mark = true;
	}
	
	protected void unsetMark(){
		mark = false;
	}

	protected void unsetMarks(){
		Player x = this;
		do{
			x.unsetMark();
			x = x.getNextPlayer();
		}while(x!=this);
	}	
	
	protected void setNextPlayer(Player next){
		nextPlayer = next;
		next.setPrevPlayer2(this);
	}
	protected void setPrevPlayer2(Player prev){
		prevPlayer = prev;
	}
	
	
	protected void setPrevPlayer(Player prev){
		prevPlayer = prev;
		prev.setNextPlayer2(this);		
	}
	protected void setNextPlayer2(Player next){
		nextPlayer = next;
	}
	
	public void deletePlayer(){
		if(nextPlayer != prevPlayer){
			if(nextPlayer == prevPlayer.prevPlayer){
				prevPlayer.setNextPlayer(nextPlayer);
				nextPlayer.setNextPlayer(prevPlayer);
			}else{
				prevPlayer.setNextPlayer(nextPlayer);
			}
		}else{
//			nextPlayer.nextPlayer = null;
//			nextPlayer.prevPlayer = null;
//			nextPlayer = null;
//			prevPlayer = null;
		}
	}
	
	protected String incNumberOfCards(){
		if(numberOfCards <= 4 && !blind){
			numberOfCards++ ;
			return "";
		}else if(numberOfCards == 5 && !blind){
			numberOfCards = 0;
			return setBlind();
		}else{
			String ret = setlost();
			deletePlayer();
			return ret;
		}
	}
	
	protected void setCards(Card[] cards){
		Cards = cards;
	}
	
	protected void setdealed(){
		dealed = true;
	}
		
	protected void setNotdealed(){
		dealed = false;
	}
	
	protected String setlost(){
		lose = true;
		return (name + " has eliminated\n");
	}
	
	public Deck CardsOnTable(){
		Card[] cards;
		int sum = 0;
		int counter = 0;
		int counter2 = 0;
		Player x = this;
		do{
			sum = sum + x.getNumberOfCards();
			x = x.getNextPlayer();
		}while(x != this);
		Player y = this;
		cards = new Card[sum];
		do{
			while(counter2<y.getNumberOfCards() && counter<sum){
				cards[counter] = y.getCards()[counter2];
				counter++;
				counter2++;
			}
			counter2 = 0;
			y = y.getNextPlayer();
		}while(y != this);
		return new Deck(cards);
		
}
	
	public String toString(){
		String ret = "";
		if(!blind){
			for(int i = 0; i<Cards.length-1 ; i++){
				ret = ret + Cards[i] + "\n";
			}
			ret = ret + Cards[Cards.length-1];
			
		}
		return ret;
	}
	
}
