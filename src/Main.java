import org.newdawn.slick.SlickException;

public class Main{

	
	public static void main(String[] args) {
		
		Game game;
		
		try {
			game = new Game();
			game.start();
		} catch (SlickException e) {
			e.printStackTrace();
		}
		

	}

}
