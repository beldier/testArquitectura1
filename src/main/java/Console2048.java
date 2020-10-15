import java.util.Scanner;

class Console2048 {

    private G2048 game;
	private Scanner scanner = new Scanner(System.in);
    public Console2048(G2048 game) {
	this.game = game;
    }

    public void play() {

		while (true) {
			printService("Please use the following letters to play W,A,S,D");
			String command = readService();
			if(validateCommand(command)){
				this.play(command);

				if (game.winGame()) {
					printService("You win !!!!! :)");
					break;
				}

				if (game.lostGame())  {
					printService("You lost !!! :(");
					break;
				}

			}
			else{
				printService("Please use only the indicated letters");
			}
			printService(game.toString());
		}

    }
	private void play(String order){
		switch (order){
			case "W":this.game.moveUp();break;
			case "S":this.game.moveDown();break;
			case "A":this.game.moveLeft();break;
			case "D":this.game.moveRight();break;
			default: throw new UnsupportedOperationException("Order not recognized");
		}
	}
	private boolean validateCommand(String command)
	{
		command = command.toUpperCase();
		String[] commands = {"A","S","D","W"};
		for(String c: commands)
			if(c.equals(command))
				return true;
		return false;
	}
	private String readService(){

    	String input= scanner.next();
    	return input;
	}
	private void printService(String message){
    	System.out.println(message);
	}
    
}
