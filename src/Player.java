import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

public class Player extends GameObject {
	
	private List<Shot> shotList = new ArrayList<Shot>();
	
	public boolean got_hit;

	protected Player() throws SlickException {		
		super(null, (Game.background_img.getWidth()/2), 600, null);
		
		got_hit = false;	//flat wich indicates, that the player got hit by an enemy
		
		/* Image and Hitbox will be created after calling super-constructor,
		 * because creating the hitbox, needs further computing
		 */
		this.img = new Image("img/fighter.png");

		
		this.hitbox = new Rectangle(this.pos_x - this.img.getWidth() / 2,
									 this.pos_y - this.img.getHeight() / 2,
									 this.img.getWidth(),
									 this.img.getHeight());	
	}
	
	
	/**
	 * Update-Routine. This has to be called once per frame.
	 * @throws SlickException
	 */
	public void update() throws SlickException{
		move(3);
		shot_out_of_view_check();
	}
	
	/**
	 * Handle all Player actions - move and shoot.
	 * 
	 * @throws SlickException
	 */
	private void move(int speed) throws SlickException{

		if(Game.game_container.getInput().isKeyDown(Input.KEY_W)){
			this.pos_y-=speed;
			this.hitbox.setY(this.hitbox.getY()-speed);
		}
		if(Game.game_container.getInput().isKeyDown(Input.KEY_S)){
			this.pos_y+=speed;
			this.hitbox.setY(this.hitbox.getY()+speed);
		}
		if(Game.game_container.getInput().isKeyDown(Input.KEY_A)){
			this.pos_x-=speed;
			this.hitbox.setX(this.hitbox.getX()-speed);
		}
		if(Game.game_container.getInput().isKeyDown(Input.KEY_D)){
			this.pos_x+=speed;
			this.hitbox.setX(this.hitbox.getX()+speed);
		}
		
		if(Game.game_container.getInput().isMousePressed(Input.MOUSE_LEFT_BUTTON)){
			this.shotList.add(new Shot());
		}
	}
	
	/**
	 * This method deletes shots from the shot-list {@link #shotList}, if they are out of view, which means,
	 * the reached the top of the window.
	 * The garbage collector will notice, that the element is not referenced anymore and will delete it.
	 */
	private void shot_out_of_view_check(){
		
		for(int i=0; i < shotList.size(); i++){
			if(shotList.get(i).getHitbox().getCenterY() < 0){
				shotList.remove(i);
				if(i > 0) i -= 1;		/* i has to be decreased to avoid an IndexOutOfBounds Exception */
				
				/* return, if the shot-list has no more entry, to avoid an IndexOutOfBounds exception */
				if(shotList.size() == 0){
					return;
				}
			}
		}
	}
	
	public List<Shot> getShotList(){
		return shotList;
	}
	
	public Image getImage(){
		return this.img;
	}
}
