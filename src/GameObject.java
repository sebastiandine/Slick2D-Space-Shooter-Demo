import org.newdawn.slick.Color;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Shape;

public abstract class GameObject {
	
	protected Image img;
	protected int pos_x;
	protected int pos_y;
	protected int speed_x;
	protected int speed_y;
	protected Shape hitbox;
	
	/**
	 * Constructor for GameObject
	 * 
	 * @param img	single image of the GameObject, can be null
	 * @param gamecontainer GameContainer of the whole game, cannot be null
	 * @param pos_x centered (horizontal) x-position of the GameObject
	 * @param pos_y centered (horizontal) y-position of the GameObject
	 * @param hitbox single hitbox for hit-detection, can be null if no hit-detection is needed
	 */
	protected GameObject(Image img, int pos_x, int pos_y, Shape hitbox){
		this.img = img;
		this.pos_x = pos_x;
		this.pos_y = pos_y;
		this.hitbox = hitbox;
	}
	
	
	/**
	 * Render-Routine. This has to be called once per frame.
	 * Renders image on screen at specified position.
	 * If a hitbox for the target GameObject exists, an can be selected visible with the parameter draw_hitbox.
	 * @param draw_hitbox render hitbox, if exists
	 */
	public void render(boolean draw_hitbox){
		if (this.img != null)
		{
			this.img.drawCentered(this.pos_x, this.pos_y);
		}
		if(draw_hitbox == true && this.hitbox != null){
			Game.game_container.getGraphics().setColor(Color.pink);
			Game.game_container.getGraphics().draw(this.hitbox);
		}
	}
	
	
	public Shape getHitbox(){
		return this.hitbox;
	}
	
	

}
