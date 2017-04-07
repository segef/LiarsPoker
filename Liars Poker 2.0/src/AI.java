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
		if(prev.getheight()<479){
			int prevHeight = prev.getheight();
			Guess act = new Guess(this);
			act.setheight(prevHeight+1);
			return act;
		}else{
			return null;
		}
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
	
	public Guess firstGuess(){
		Random ran = new Random();
		int newHeight = ran.nextInt(480);
		Guess act = new Guess(this);
		act.setheight(newHeight);
		return act;
	}
	
	

	

}
