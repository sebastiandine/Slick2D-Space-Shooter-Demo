import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

public class Enemy extends GameObject {

	
	public Enemy(int pos_x, int pos_y) throws SlickException {
		super(null, pos_x, pos_y, null);
		
		/* Image and Hitbox will be created after calling super-constructor,
		 * because creating the hitbox, needs further computing
		 */
		this.img = new Image("img/ufo.png");
		
		this.hitbox = new Rectangle(this.pos_x - this.img.getWidth() / 2,
									this.pos_y - this.img.getHeight() / 2,
									this.img.getWidth(),
									this.img.getHeight());	
	}
	
	/**
	 * Update-Routine. Has to be called once per frame.
	 * @throws SlickException
	 */
	public void update() throws SlickException{
		move(1);				//at the moment the speed is hard coded, but the game could be extended with 
								//various speeds, or rising speed per time, to increase the difficulty.
		collision_check();
		
	}

	
	/**
	 * Move of the enemy. All enemies will strictly move down, according to their specified speed.
	 * 
	 * @param speed Speed of down-movement
	 */
	private void move(int speed){
		this.pos_y+=speed;
		this.hitbox.setY(this.hitbox.getY()+speed);
	}
	
	/**
	 * If an {link Enemy}-object collides with the {@link Player}-object, the game is over.
	 */
	private void collision_check(){
		if(this.hitbox.intersects(Game.player.getHitbox())){
			Game.player.got_hit = true;
		}
	}	

}
