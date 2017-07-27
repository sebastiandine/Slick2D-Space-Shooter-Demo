import java.awt.Font;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;

public class Game extends BasicGame{

	private static String gameTitle = "Game";

	private static EnemyContainer enemy_container;
	private static AppGameContainer game;
	
	public static Image background_img;
	
	public static Player player;
	public static GameContainer game_container;
	
	public static int score;
	
	public static TrueTypeFont big_message_font;
	public static TrueTypeFont info_text_font;


	public Game() throws SlickException {
		super(gameTitle);
	}

	@Override
	public void init(GameContainer container) throws SlickException {	
		/*NOTE: The background needs to be initialized first, because several other
		 * 		calculations of player and enemies, references the size of the background-image.
		 */
		
		/* Init Background */
		background_img = new Image("img/background.jpg");
		game.setDisplayMode(background_img.getWidth(), background_img.getHeight(), false);
		
		/* Init Player, Score and Enemies */
		game_container = container;
		player = new Player();
		score = 0;
		enemy_container = new EnemyContainer();	

		
		/* Init Message Fonts */
		Font font = new Font("Chalkduster", Font.BOLD, 50);
		big_message_font = new TrueTypeFont(font, true);
		font = new Font("Chalkduster", Font.BOLD, 20);
		info_text_font = new TrueTypeFont(font, true);
	}

	@Override
	public void update(GameContainer container, int delta) throws SlickException {
		
		if(!player.got_hit){						/* As long as the player doesn´t get hit, the normal 
													   update-routine will run */
			player.update();
			enemy_container.update();
			
			for(Shot i : player.getShotList()){
				i.update();
			}
		}
		
		
	}
	
	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException {
		background_img.draw(0, 0);
		
		/* Normal Game-flow */
		if(!player.got_hit){
			info_text_font.drawString(10, 10,"Score: "+score, Color.orange);
			player.render(false);
			enemy_container.render(false);
			
			for(Shot i : player.getShotList()){
				i.render(false);
			}
		}
		/* Game Over Screen */
		else {
			big_message_font.drawString(background_img.getWidth()/4, background_img.getHeight()/3, "GAME OVER!", Color.orange );
			info_text_font.drawString(background_img.getWidth()/4, background_img.getHeight()/2,"Press Space to Continue", Color.orange);
			
			if(game_container.getInput().isKeyPressed(Input.KEY_SPACE)){
				init(game_container);
			}
		}
		
	}
	
	void start(){
		try {
			game = new AppGameContainer(this);
			//game.setDisplayMode(600, 600, false); /* static display mode. */
													/* if the game-size should be adjusted to the 
													 * background image, it has to be done in the 
													 * init-routine.
													 */
			game.setTargetFrameRate(60);
			game.setVSync(true);
			game.setShowFPS(false);
			game.start();
			
			
		} catch (SlickException e) {
			e.printStackTrace();
		}
		
	}
}
