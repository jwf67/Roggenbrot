import java.awt.Image;


public class Enemy extends Character{//hEnemy has the properties of character
	protected int health = 30;//initialize health
	protected int giveDamage = 5;//deals 5 damage to player
	public Enemy(int xpos, int ypos) {//Enemy constructor
		super(xpos, ypos, null);//call character's constructor
		Image imageE = DisplayImage.getEnemyBImage();//get the image of Enemy
		setImage(imageE);//set the image of Enemy
		SPEED=5;//set the speed of the enemy to 5
	}
	@Override
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
		
		for(Enemy enemy: Frame.enemies)//for each enemy
		{
			if(this!=enemy)
			{
				//get positions
				int playerX=enemy.xpos;
				int playerY=enemy.ypos;
				//get player and enemy heights and widths
				int playerHeight=30,playerWidth=30,enemyWidth=30,enemyHeight=30;
				//collision detection
				//determines whether there are common points between the player and the enemy
				//if there is the enemy will move in the opposite direction
				if(((xpos<=playerX && playerX<=xpos+enemyWidth) || (xpos<=playerX+playerWidth && playerX+playerWidth<=xpos+enemyWidth)) && ((ypos<=playerY && playerY<=ypos+enemyHeight) || (ypos<=playerY+playerHeight && playerY+playerHeight<=ypos+enemyHeight)))
				{
					if(xpos+30>enemy.xpos && xpos+30<enemy.xpos+30)
					{
						movingWest=true;
						movingEast=false;
						//xpos=enemy.xpos-30;
					}
					if(xpos<enemy.xpos+30 && xpos>enemy.xpos)
					{
						movingWest=false;
						movingEast=true;
						//xpos=enemy.xpos+30;
					}
					if(ypos+30>enemy.ypos && ypos+30<enemy.ypos+30)
					{
						movingNorth=true;
						movingSouth=false;
						//ypos=enemy.ypos-30;
					}
					if(ypos<enemy.ypos+30 && ypos>enemy.ypos)
					{
						movingNorth=false;
						movingSouth=true;
						//ypos=enemy.ypos+30;
					}
					super.exec();
				}
			}
		}
		
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
					Player.enemyTakesDamage = 25;//enemy takes 25 damage per hit with powerup B
				if(Player.powerUpC)//if powerUpC is true
					Player.enemyTakesDamage = 35;//enemy takes 35 damage per hit with powerup C 
				if(Player.powerUpD)//if powerUpD is true
					Player.enemyTakesDamage = 50;//enemy takes 50 damage per hit with powerup D
				health-=Player.enemyTakesDamage;//set health of the boss as current health minus the damage the enemy should
				Frame.projectilesToRemove.add(projectile);//remove the projectile
				if(health<0)//if the enemy's health is less than 0
					health=0;//set the health to 0
			}

		}
			
		if(((xpos<=playerX && playerX<=xpos+enemyWidth) || (xpos<=playerX+playerWidth && playerX+playerWidth<=xpos+enemyWidth)) && ((ypos<=playerY && playerY<=ypos+enemyHeight) || (ypos<=playerY+playerHeight && playerY+playerHeight<=ypos+enemyHeight))){
			//if the player and enemy collide
			Frame.player.takeDamage(giveDamage, this, xpos, ypos);//player takes damage
		}
		if(health<=0){//if the health of the enemy is less than or equal to 0
			Frame.enemiesToRemove.add(this);//remove the enemy from the list of enemies
		}
	}
}
