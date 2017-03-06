import java.awt.Image;


public class RotateEnemyB extends Character{//has the properties of Character

	protected int health = 200;//initialize health at 200
	protected int giveDamage = 2;//deals 2 damage upon
	private static Image imageA = DisplayImage.getEnemyGAImage();//get the image of the rotating image
	public RotateEnemyB(int xpos, int ypos) {//RotateEnemyB constructor
		super(xpos, ypos, null);//call the character constructor
		//depending on the direction the enemy is moving load the appropriate image
		if(movingWest && movingNorth)
			imageA = DisplayImage.getEnemyGHImage();
		else if(movingWest && movingSouth)
			imageA = DisplayImage.getEnemyGFImage();
		else 
			imageA = DisplayImage.getEnemyGGImage();
		if(movingEast && movingNorth)
			imageA = DisplayImage.getEnemyGBImage();
		else if(movingEast && movingSouth)
			imageA = DisplayImage.getEnemyGDImage();
		else 
			imageA = DisplayImage.getEnemyGCImage();
		if(movingWest && movingNorth)
			imageA = DisplayImage.getEnemyGHImage();
		else if(movingWest && movingSouth)
			imageA = DisplayImage.getEnemyGFImage();
		else 
			imageA = DisplayImage.getTankGImage();
		if(movingNorth && !movingEast && !movingWest)
			imageA = DisplayImage.getEnemyGAImage();
		if(movingSouth && !movingEast && !movingWest)
			imageA = DisplayImage.getEnemyGEImage();
		setImage(imageA);//set the appropriate image
		SPEED = 5;//set the speed of the enemy to 5
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
		for(RotateEnemyB rotateB: Frame.rotateBs)//for every enemy
		{
			if(this!=rotateB)
			{
				//get positions
				int playerX=rotateB.xpos;
				int playerY=rotateB.ypos;
				//initialize the player and enemy heights and widths
				int playerHeight=30,playerWidth=30,enemyWidth=30,enemyHeight=30;
				//collision detection
				//determines whether there are common points between the player and the enemy
				//if there is the enemy will move in the opposite direction
				if(((xpos<=playerX && playerX<=xpos+enemyWidth) || (xpos<=playerX+playerWidth && playerX+playerWidth<=xpos+enemyWidth)) && ((ypos<=playerY && playerY<=ypos+enemyHeight) || (ypos<=playerY+playerHeight && playerY+playerHeight<=ypos+enemyHeight)))
				{
					if(xpos+30>rotateB.xpos && xpos+30<rotateB.xpos+30)
					{
						movingWest=true;
						movingEast=false;
						//xpos=enemy.xpos-30;
					}
					if(xpos<rotateB.xpos+30 && xpos>rotateB.xpos)
					{
						movingWest=false;
						movingEast=true;
						//xpos=enemy.xpos+30;
					}
					if(ypos+30>rotateB.ypos && ypos+30<rotateB.ypos+30)
					{
						movingNorth=true;
						movingSouth=false;
						//ypos=enemy.ypos-30;
					}
					if(ypos<rotateB.ypos+30 && ypos>rotateB.ypos)
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
			imageA = DisplayImage.getEnemyGHImage();
		}
		else if(movingWest && movingSouth)
		{
			imageA = DisplayImage.getEnemyGFImage();
		}
		else if(movingWest && !movingSouth && !movingNorth)
		{
			imageA = DisplayImage.getEnemyGGImage();
		}
		if(movingEast && movingNorth)
		{
			imageA = DisplayImage.getEnemyGBImage();
		}
		else if(movingEast && movingSouth)
		{
			imageA = DisplayImage.getEnemyGDImage();
		}
		else if(movingEast && !movingNorth && !movingSouth)
		{
			imageA = DisplayImage.getEnemyGCImage();
		}
		if(movingNorth && !movingEast && !movingWest)
		{
			imageA = DisplayImage.getEnemyGAImage();
		}
		if(movingSouth && !movingEast && !movingWest)
		{
			imageA = DisplayImage.getEnemyGEImage();
		}
		setImage(imageA);//set the image of the enemy
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
					Player.enemyTakesDamage = 50;//enemy takes 50 damage per hit with powerup D
				health-=Player.enemyTakesDamage;//set health of the boss as current health minus the damage the enemy should
				Frame.projectilesToRemove.add(projectile);//remove the projectile
				if(health<0)//if the enemy's health is less than 0
					health=0;//set the health to 0
			}

		}
		if(((xpos<=playerX && playerX<=xpos+enemyWidth) || (xpos<=playerX+playerWidth && playerX+playerWidth<=xpos+enemyWidth)) && ((ypos<=playerY && playerY<=ypos+enemyHeight) || (ypos<=playerY+playerHeight && playerY+playerHeight<=ypos+enemyHeight)))
		{
			//if the player and enemy collide
			Frame.player.takeDamageRB(giveDamage, this, xpos, ypos);
		}
		if(health<=0){//if the health of the enemy is less than or equal to 0
			Frame.rotateBsToRemove.add(this);//remove the enemy from the list of enemies
		}
	}
	public int getHealth() {
		return health;
	}

}
