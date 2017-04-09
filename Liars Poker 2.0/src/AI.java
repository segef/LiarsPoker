import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Random;

public class AI extends Player{
	Guess guessed;
	
	public AI(String name, int numberOfCards) {
		super(name, numberOfCards);
		// TODO Auto-generated constructor stub
	}
	public AI(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}
	public Guess guess(Guess prev){
		return mathematicalGuess(prev);
	}
	public Guess randomGuess(Guess prev){
		Random ran = new Random();
		int p = ran.nextInt(5);
		if(prev.getheight()<479 && p!=0){
			int prevHeight = prev.getheight();
			int newHeight = ran.nextInt(480-prevHeight-1)+prevHeight+1;
			Guess act = new Guess(this);
			act.setheight(newHeight);
			return act;
		}else{
			return null;
		}
	}
	
	public Guess basicGuess(Guess prev){
		Random ran = new Random();
		float poss = calculate(prev);
		int x = prev.getheight();
		if(poss < 0.5){
			return null;
		}else{
			Guess act;
			while(true){
				int newHeight = ran.nextInt(480-x)+x;
				act = new Guess(this);
				act.setheight(newHeight);
				if(calculate(act)>0.15){
					break;
				}
			}
			return act;
		}
	}
	
	public Guess mathematicalGuess(Guess prev){
		int preHeight = prev.getheight();
		
		if(!getBlind()){
			Guess[] impGuesses = logicalGuesses();
			
			int x = -1;
			float poss = 0;
			float tmpPoss;
			for(int i = 0; i<impGuesses.length; i++){
				if(impGuesses[i].getheight()>preHeight){
					tmpPoss = calculate(impGuesses[i]);
					if(tmpPoss > poss && tmpPoss > 0.40){
						poss = tmpPoss;
						x = i;
					}
				}
			}

			if(x == -1){
				return null;
			}else{
				return impGuesses[x];
			}
		}else{
			if(calculate(prev) < 0.3){
				return null;
			}else{
				if(preHeight<280){
					Random ran = new Random();
					Guess ret = new Guess(this);
					ret.setheight(preHeight+ran.nextInt(10));
					return ret;
				}else{
					Guess ret = new Guess(this);
					ret.setheight(preHeight+1);
					return ret;
				}
			}
		}
	}
	
	public Guess firstGuess(){
//		Random ran = new Random();
//		Guess act;
//		while(true){
//			int newHeight = ran.nextInt(12);
//			act = new Guess(this);
//			act.setheight(newHeight);
//			if(!getBlind() && calculate(act)>0.25){
//				break;
//			}else if(calculate(act)>0.05){
//				break;
//			}
//		}
//		return act;
		
		if(!getBlind()){
			Guess[] impGuesses = logicalGuesses();
			
			int x = 0;
			float poss = 0;
			float tmpPoss;
			for(int i = 0; i<impGuesses.length; i++){
					tmpPoss = calculate(impGuesses[i]);
					if(tmpPoss > poss){
						poss = tmpPoss;
						x = i;
					}
				
			}
			
			return impGuesses[x];
			
		}else{
			Random ran = new Random();
			Guess ret = new Guess(this);
			int number = CardsOnTable().length();
			if(number > 13){
				ret.setheight(ran.nextInt(24));
			}else{
				ret.setheight(ran.nextInt(12));
			}
			return ret;
		}
	}
//	
//	public static BigInteger fac(BigInteger n){
//		if(n.equals(new BigInteger("0"))){
//			return new BigInteger("1");
//		}else{
//			return n.multiply(fac(n.subtract(new BigInteger("1"))));
//		}
//	}
//	
//	public static BigInteger comb(BigInteger n, BigInteger m){
//		return fac(n).divide(fac(m).multiply(fac(n.subtract(m))));
//	}
	
	private Guess[] logicalGuesses(){
		ArrayList<Integer> tmp = new ArrayList<Integer>();
		for(int i = 0; i < getNumberOfCards() ; i++){
			if(!tmp.contains((Integer)((getCards()[i]).getDominance())-1) && getCards()[i].getDominance() != 0){
				tmp.add((Integer)((getCards()[i]).getDominance()-1));
			}
		}
		
		int[] hand = new int[tmp.size()];
		int mark = 0;
		for(Integer i : tmp){
			hand[mark] = (int)i;
			mark++;
		}
		
		int numOfGuesses = 7*hand.length + 3*hand.length*(hand.length-1);
		
		Guess[] ret = new Guess[numOfGuesses];
		int val = 0;
		
		int[] one = {0,1,3,4,6,7,9};
		int[] two = {2,5,8};
		
		for(int i = 0 ; i < 7 ; i++){
			for(int j = 0 ; j < hand.length ; j++){
				ret[val] = new Guess(this);
				ret[val].setheight(one[i]+" "+hand[j]+" "+"0");
				val++;
			}
		}
		
		for(int i = 0 ; i < 3 ; i++){
			for(int x = 0; x < hand.length; x++){
				for(int y = 0; y < hand.length ; y++){
					if(x != y){
						ret[val] = new Guess(this);
						ret[val].setheight(two[i]+" "+hand[x]+" "+hand[y]);
						val++;
					}
				}
			}
		}
		return ret;
	}
	
	private float calculate(Guess prev){
		int num = 100;
		int val1 = 0;
		int val2 = 0;
		int n = CardsOnTable().length()-getNumberOfCards();
		Deck tmpDeck;
		
		System.out.println(prev);
		
		for(int i=0; i<num; i++){
			
			tmpDeck = new Deck(n, getCards());
			System.out.println(tmpDeck+"\n"+i);
			
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
				int realX = tmpDeck.numOfCard(types[tmp[1]]);
				int realY = tmpDeck.numOfCard(types[tmp[2]]);
				int numOf2 = tmpDeck.numOfCard("Two");
				int x = 0;
				int y = 0;
				
				if(expectedX>realX){
					x = expectedX - realX;
				}
				if(expectedY>realY){
					y = expectedY - realY;
				}
				if(x+y > numOf2){
					val2++;
				}else{
					val1++;
				}
			}else{
				int expectedX = guessNums[0];
				int realX = tmpDeck.numOfCard(types[tmp[1]]);
				int numOf2 = tmpDeck.numOfCard("Two");
				int x = 0;
				
				if(expectedX>realX){
					x = expectedX - realX;
				}
				if(x > numOf2){
					val2++;
				}else{
					val1++;
				}
			}
			System.out.println(val1);
		}
		System.out.println((float)((float)val1/(float)num));
		return (float)val1/(float)num;
	}
	

	

}
