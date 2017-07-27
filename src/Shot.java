import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

public class Shot extends GameObject {

	protected Shot() throws SlickException {
		super(null, Game.player.pos_x, Game.player.pos_y-(Game.player.getImage().getHeight()/2), null);
		this.img = new Image("img/shot.png");
		
		this.hitbox = new Rectangle(this.pos_x - this.img.getWidth() / 2,
				this.pos_y - this.img.getHeight() / 2,
				this.img.getWidth(),
				this.img.getHeight());	
	}
	
	public void update(){
		move(5);
	}
	
	private void move(int speed){
		this.pos_y-=speed;
		this.hitbox.setY(this.hitbox.getY()-speed);
	}
	
	

}
