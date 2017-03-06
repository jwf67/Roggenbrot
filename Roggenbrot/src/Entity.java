import java.awt.Image;
import java.awt.Graphics;
import javax.swing.JComponent;
import javax.swing.JFrame;


public class Entity
{
	protected int xpos=30;//default x and y positions
	protected int ypos=30;
	private Image image;//image variable
	protected int SPEED=10;//default speed
	protected boolean movingNorth=false;//entity isn't moving
	protected boolean movingSouth=false;
	protected boolean movingWest=false;
	protected boolean movingEast=false;

	public int getX()//get the x position of the entity
	{
		return xpos;
	}

	public int getY()//get the y position of the entity
	{
		return ypos;
	}
	public void setImage(Image image)//set the image of the entity
	{
		this.image=image;
	}
	public Image getImage()//get the image of the entity
	{

		return image;
	}

	public Entity(int xpos, int ypos, Image image)//Entity constructor
	{
		this.xpos = xpos;//set the x position, y position, and image
		this.ypos = ypos;
		this.image = image;
	}

	public boolean isValid(int xpos, int ypos)//determines if the position is valid for the entity to move to
	{
		char[][] array = Map.getArray();//get the current map array

		xpos = xpos/30;//where the entity would be in the array
		ypos = ypos/30;

		if(xpos>=0 && ypos>=0 && ypos<array.length && xpos < array[ypos].length && array[ypos][xpos] != '@')//if the entity is in a valid spot
		{
			return true;//then it's able to move there
		}

		return false;//otherwise it cant
	}
	public void paint(Graphics g){
		g.drawRect(xpos-10,ypos,10,10);
	}
	public void exec()//execute method
	{
		int localSpeed = SPEED;//create a local speed variable using SPEED

		if((movingNorth || movingSouth) && (movingWest || movingEast))//if the entity is moving diagonally
		{
			localSpeed=(int) (localSpeed/(Math.sqrt(2)));//divide the speed by sqrt(2) so it moves at the same speed as something that would horizontally or vertically
		}
		if(movingNorth)//if it's moving north
		{
			if(isValid(xpos, ypos-localSpeed) && isValid(xpos+29, ypos-localSpeed) )//if it can move to that spot
			{
				ypos-=localSpeed;//change the y position
			}
			else
				ypos=(ypos/30)*30;//otherwise it remains unchanged
		}
		if(movingSouth)//if it's moving south
		{
			if(isValid(xpos, ypos+localSpeed+29) && isValid(xpos+29, ypos+localSpeed+29))//if it can move to that spot
			{
				ypos+=localSpeed;//change the y position
			}
			else
				ypos=(int)Math.round(ypos/30.0)*30;//otherwise dont change the position

		}
		if(movingWest)//if it's moving west
		{
			if(isValid(xpos-localSpeed, ypos) && isValid(xpos-localSpeed, ypos+29))//if it can move to that spot
			{
				xpos-=localSpeed;//change the x position
			}
			else
				xpos=(xpos/30)*30;//otherwise dont change the position
		}
		if(movingEast)//if it's moving east
		{
			if(isValid(xpos+localSpeed+29, ypos) && isValid(xpos+localSpeed+29, ypos+29))//if it can move to that spot
			{
				xpos+=localSpeed;//change the x position
			}
			else
				xpos=(int)Math.round(xpos/30.0)*30;//otherwise dont change the position
		}
		
	}
}