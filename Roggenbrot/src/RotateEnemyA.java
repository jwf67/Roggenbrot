import java.awt.Image;


public class RotateEnemyA extends Character{//has the properties of Character
	protected int health = 300;//initialize health at 300
	public int iHealth = 300;//initialize initial health at 300
	protected int giveDamage = 1;//deals one damage to player
	private static Image imageA = DisplayImage.getTankAImage();//initialize imageA
	public RotateEnemyA(int xpos, int ypos) {//RotateEnemyA constructor
		super(xpos, ypos, null);//call the character constructor
		//depending on the direction the enemy is moving load the appropriate image
		if(movingWest && movingNorth)
			imageA = DisplayImage.getTankHImage();
		else if(movingWest && movingSouth)
			imageA = DisplayImage.getTankFImage();
		else 
			imageA = DisplayImage.getTankGImage();
		if(movingEast && movingNorth)
			imageA = DisplayImage.getTankBImage();
		else if(movingEast && movingSouth)
			imageA = DisplayImage.getTankDImage();
		else 
			imageA = DisplayImage.getTankCImage();
		if(movingWest && movingNorth)
			imageA = DisplayImage.getTankHImage();
		else if(movingWest && movingSouth)
			imageA = DisplayImage.getTankFImage();
		else 
			imageA = DisplayImage.getTankGImage();
		if(movingNorth && !movingEast && !movingWest)
			imageA = DisplayImage.getTankAImage();
		if(movingSouth && !movingEast && !movingWest)
			imageA = DisplayImage.getTankEImage();
		setImage(imageA);//set the image as appropriate image
		SPEED=5;//set the speed of the enemy to 5
	}
	public void exec()
	{
		if(Frame.player.getX()+SPEED < getX() )//if the player is to the left of the enemy
		{
			movingWest=true;//enemy moves west and not east
			movingEast=false;
		}
		else if(Frame.player.getX()-SPEED > getX() )//if the player is to the right of the enemy
		{
			movingEast=true;//enemy moves east and not west
			movingWest=false;
		}
		else//otherwise
		{
			movingWest=false;//enemy doesnt move
			movingEast=false;

		}
		if(Frame.player.getY()+SPEED < getY())//if the player is above the enemy
		{
			movingNorth=true;//boss moves north not south
			movingSouth=false;
		}
		else if(Frame.player.getY()-SPEED > getY() )//if the player is below the enemy
		{
			movingSouth=true;//enemy moves south not north
			movingNorth=false;
		}
		else//otherwise
		{
			movingNorth=false;//enemy doesnt move
			movingSouth=false;
		}
		
		super.exec();
		for(RotateEnemyA rotateA: Frame.rotateAs)//for every enemy
		{
			if(this!=rotateA)
			{
				//get positions
				int playerX=rotateA.xpos;
				int playerY=rotateA.ypos;
				//initialize the player and enemy heights and widths
				int playerHeight=30,playerWidth=30,enemyWidth=30,enemyHeight=30;
				//collision detection
				//determines whether there are common points between the player and the enemy
				//if there is the enemy will move in the opposite direction
				if(((xpos<=playerX && playerX<=xpos+enemyWidth) || (xpos<=playerX+playerWidth && playerX+playerWidth<=xpos+enemyWidth)) && ((ypos<=playerY && playerY<=ypos+enemyHeight) || (ypos<=playerY+playerHeight && playerY+playerHeight<=ypos+enemyHeight)))
				{
					if(xpos+30>rotateA.xpos && xpos+30<rotateA.xpos+30)
					{
						movingWest=true;
						movingEast=false;
						//xpos=enemy.xpos-30;
					}
					if(xpos<rotateA.xpos+30 && xpos>rotateA.xpos)
					{
						movingWest=false;
						movingEast=true;
						//xpos=enemy.xpos+30;
					}
					if(ypos+30>rotateA.ypos && ypos+30<rotateA.ypos+30)
					{
						movingNorth=true;
						movingSouth=false;
						//ypos=enemy.ypos-30;
					}
					if(ypos<rotateA.ypos+30 && ypos>rotateA.ypos)
					{
						movingNorth=false;
						movingSouth=true;
						//ypos=enemy.ypos+30;
					}
					super.exec();
				}
			}
		}
		//depending on appropriate movement, get the right image 
		if(movingWest && movingNorth)
		{
			imageA = DisplayImage.getTankHImage();
		}
		else if(movingWest && movingSouth)
		{
			imageA = DisplayImage.getTankFImage();
		}
		else if(movingWest && !movingSouth && !movingNorth)
		{
			imageA = DisplayImage.getTankGImage();
		}
		if(movingEast && movingNorth)
		{
			imageA = DisplayImage.getTankBImage();
		}
		else if(movingEast && movingSouth)
		{
			imageA = DisplayImage.getTankDImage();
		}
		else if(movingEast && !movingNorth && !movingSouth)
		{
			imageA = DisplayImage.getTankCImage();
		}
		if(movingNorth && !movingEast && !movingWest)
		{
			imageA = DisplayImage.getTankAImage();
		}
		if(movingSouth && !movingEast && !movingWest)
		{
			imageA = DisplayImage.getTankEImage();
		}
		setImage(imageA);//set the image
		int playerX=Frame.player.getX();//get player's x position
		int playerY=Frame.player.getY();//get player's y position
		int playerWidth = 30;//player's width
		int playerHeight = 30;//player's height
		int projectileWidth=10;//width of projectile
		int projectileHeight = 10;//height of projectile
		int projectileXWidth=20;//upgraded width of projectile
		int projectileXHeight = 20;//upgraded height o projectile
		int enemyWidth = 30;//enemy width
		int enemyHeight = 30;//enemy height
		
		for(Projectile projectile: Frame.projectiles){//for each projectile in projectiles
			int projectileX = projectile.getX();//get the x and y positions
			int projectileY = projectile.getY();
			
			if(((xpos<=projectileX && projectileX<=xpos+enemyWidth) || (xpos<=projectileX+projectileXWidth && projectileX+projectileXWidth<=xpos+enemyWidth)) && ((ypos<=projectileY && projectileY<=ypos+enemyHeight) || (ypos<=projectileY+projectileXHeight && projectileY+projectileXHeight<=ypos+enemyHeight))){
				//if the projectile collides with the enemy	
				Player.enemyTakesDamage = 5;//enemy takes 5 damage per hit with no powerups
				if(Player.powerUpA)//if powerUpA is true
				Player.enemyTakesDamage = 15;//enemy takes 15 damage per hit with powerup A
				if(Player.powerUpB)//if powerUpB is true
					Player.enemyTakesDamage = 20;//enemy takes 20 damage per hit with powerup B
				if(Player.powerUpC)//if powerUpC is true
					Player.enemyTakesDamage = 30;//enemy takes 30 damage per hit with powerup C 
				if(Player.powerUpD)//if powerUpD is true
					Player.enemyTakesDamage = 45;//enemy takes 45 damage per hit with powerup D
				health-=Player.enemyTakesDamage;//set health of the boss as current health minus the damage the enemy should
				Frame.projectilesToRemove.add(projectile);//remove the projectile
				if(health<0)//if the enemy's health is less than 0
					health=0;//set the health to 0
			}

		}
		if(((xpos<=playerX && playerX<=xpos+enemyWidth) || (xpos<=playerX+playerWidth && playerX+playerWidth<=xpos+enemyWidth)) && ((ypos<=playerY && playerY<=ypos+enemyHeight) || (ypos<=playerY+playerHeight && playerY+playerHeight<=ypos+enemyHeight)))
		{
			//if the player and enemy collide
			Frame.player.takeDamageRA(giveDamage, this, xpos, ypos);
		}
		if(health<=0){//if the health of the enemy is less than or equal to 0
			Frame.rotateAsToRemove.add(this);//remove the enemy from the list of enemies
		}
	}
	public int getHealth() {
		return health;//get the health
	}
	public int getIHealth() {
		return iHealth;//get the initial health
	}
}
