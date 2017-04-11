

public class Guess{
	
	
	private static String[] sortedGuesses = new String[480];
	private static int[][][] guessVals = new int[10][12][12];
	private static int[][] possibleGuesses = {{2},{3},{3,2},{4},{5},{5,3},{6},{7},{7,5},{8}};
	private static String[] possibleTypes = {"Three","Four","Five","Six","Seven","Eight","Nine","Ten","Jack","Queen","King","Ace"};
	private int height;
	private Player p;
	
	public Guess( Player p){
		this.p = p;
		arrange();
	}
	
	private void arrange(){
		int i =0;
		for(int x=0;x<10;x++){
			for(int y=0;y<12;y++){
				for(int z=0;z<12;z++){
					if(x==2 || x==5 || x==8){
						if(y!=z){
							sortedGuesses[i] = x+" "+y+" "+z;
							i++;
						}
					}else{
						if(z==0){
							sortedGuesses[i] = x+" "+y+" "+z;
							i++;
						}
					}
				}
			}
		}
		for(int a=0; a<480; a++){
			String[] temp = sortedGuesses[a].split(" ");
			guessVals[Integer.parseInt(temp[0])][Integer.parseInt(temp[1])][Integer.parseInt(temp[2])] = a;
		}
	}
	
	
	public void setheight(String s){
		String[] temp = s.split(" ");
		height = guessVals[Integer.parseInt(temp[0])][Integer.parseInt(temp[1])][Integer.parseInt(temp[2])];
	}
	public void setheight(int a){
		height = a;
	}
	
	public int getheight(){
		return height;
	}
	
	public Player getPlayer(){
		return p;
	}
	
	public String[] getSortedGuesses(){
		return sortedGuesses;
	}
	
	public int[][][] getGuessVals(){
		return guessVals;
	}
	
	public int[][] getPossibleGuesses(){
		return possibleGuesses;
	}
	
	public String[] getPossibleTypes(){
		return possibleTypes;
	}
	
 	public String toString(){
 		String ret = "";
 		String[] temp = sortedGuesses[height].split(" ");
 		int[] tmp = new int[3];
		tmp[0] = Integer.parseInt(temp[0]);
		tmp[1] = Integer.parseInt(temp[1]);
		tmp[2] = Integer.parseInt(temp[2]);
		int[] guessNums = possibleGuesses[(tmp[0])];
		if(tmp[0] == 2 || tmp[0] == 5 || tmp[0] == 8){
			ret = guessNums[0] +" "+ possibleTypes[tmp[1]]+"'s and "+guessNums[1]+" "+possibleTypes[tmp[2]]+"'s";
		}else{
			ret = guessNums[0] +" "+ possibleTypes[tmp[1]]+"'s"; 
		}
		
		return ret;
 	}

}

