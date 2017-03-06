import java.awt.Image;


public class Projectile extends Entity//projectile has characteristics of entity
{
	//default values for player projectiles
	public final int DEFAULTSPEED = 20;
	public final int DEFAULTRADIUS = 400;
	
	public static int speedP;//speed of projectile
	double angle;//angle of launch
	public static int maxRadius;//length of maximum movement
	int initX;//x position
	int initY;//y position

	public Projectile(int xpos, int ypos, double angle, Image image)
	{
		
		super(xpos, ypos, image);//call entity constructor
		this.angle = angle;//set the angle
		initX=xpos;//set the x position
		initY=ypos;//set the y position
		speedP = DEFAULTSPEED;//set to defaults
		maxRadius = DEFAULTRADIUS;
	}

	public void exec()
	{
		char array[][]=Map.getArray();//get the 2d array of the current map
		xpos+=(speedP*Math.cos(angle));//change the x position based on the desired launch angle
		ypos+=(speedP*Math.sin(angle));//change the y position based on the desited launch angle
		if(Math.sqrt(Math.pow(xpos-initX, 2)+Math.pow(ypos-initY, 2))>=maxRadius)//if the projectile moves out of maxRadius
				Frame.projectilesToRemove.add(this);//the projectile will be removed
		else if ((ypos+5)/30>=28||(xpos+5)/30>=48||array[(ypos+5)/30][(xpos+5)/30]=='@' || array[(ypos+5)/30][(xpos+5)/30]=='#' || (ypos+5)/30<=0||(xpos+5)/30<=0)
			//otherwise if it has the same position as places that cant be moved on or lava
		{
			Frame.projectilesToRemove.add(this);//the projectile will be removed
		}
	}
}