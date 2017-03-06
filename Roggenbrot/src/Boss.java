import java.awt.Image;

public class Boss extends Character{//boss extends the properties of character
	private int health = 250;//current health variable initialized
	public Boss(int xpos, int ypos) {//Boss constructor
		super(xpos, ypos, null);//call character's constructor
		Image imageE = DisplayImage.getBossImage();
		setImage(imageE);//set the image of the boss
		SPEED=10;//set the speed of the boss to 10
	}
	@Override
	public void exec()
	{
		if(Frame.player.getX()+SPEED<getX())//if the player is to the left of the boss
		{
			movingWest=true;//boss moves west and not east
			movingEast=false;
		}
		else if(Frame.player.getX()-SPEED>getX())//if the player is to the right of the boss
		{
			movingEast=true;//boss moves east and not west
			movingWest=false;
		}
		else//otherwise
		{
			movingWest=false;//boss doesnt move
			movingEast=false;

		}
		if(Frame.player.getY()+SPEED<getY())//if the player is above the boss
		{
			movingNorth=true;//boss moves north not south
			movingSouth=false;
		}
		else if(Frame.player.getY()-SPEED>getY())//if the player is below the boss
		{
			movingSouth=true;//boss moves south not north
			movingNorth=false;
		}
		else//otherwise
		{
			movingNorth=false;//boss doesnt move
			movingSouth=false;
		}

		for(Boss boss: Frame.bosses)//for each boss in the linked list (existing bosses)
		{
			if(this!=boss)
			{
				int playerX=boss.xpos;
				int playerY=boss.ypos;
				int playerHeight=30,playerWidth=30,enemyWidth=30,enemyHeight=30;
				if(((xpos<=playerX && playerX<=xpos+enemyWidth) || (xpos<=playerX+playerWidth && playerX+playerWidth<=xpos+enemyWidth)) && ((ypos<=playerY && playerY<=ypos+enemyHeight) || (ypos<=playerY+playerHeight && playerY+playerHeight<=ypos+enemyHeight)))
				{
					if(xpos+30>boss.xpos && xpos+30<boss.xpos+30)
					{
						movingWest=true;
						movingEast=false;
						//xpos=enemy.xpos-30;
					}
					if(xpos<boss.xpos+30 && xpos>boss.xpos)
					{
						movingWest=false;
						movingEast=true;
						//xpos=enemy.xpos+30;
					}
					if(ypos+30>boss.ypos && ypos+30<boss.ypos+30)
					{
						movingNorth=true;
						movingSouth=false;
						//ypos=enemy.ypos-30;
					}
					if(ypos<boss.ypos+30 && ypos>boss.ypos)
					{
						movingNorth=false;
						movingSouth=true;
						//ypos=enemy.ypos+30;
					}
					super.exec();//call character's exec() method
				}
			}
		}
		super.exec();//call character's exec() method
		
		int playerX=Frame.player.getX();//get player's x position
		int playerY=Frame.player.getY();//get player's y position
		int playerWidth = 30;//player's width
		int playerHeight = 30;//player's height
		int projectileXWidth=20;//upgraded width of projectile
		int projectileXHeight = 20;//upgraded height o projectile
		int enemyWidth = 30;//boss width
		int enemyHeight = 62;//boss height
		
		for(Projectile projectile: Frame.projectiles){//for each projectile in projectiles
			int projectileX = projectile.getX();//get the x and y positions
			int projectileY = projectile.getY();

			if(((xpos<=projectileX && projectileX<=xpos+enemyWidth) || (xpos<=projectileX+projectileXWidth && projectileX+projectileXWidth<=xpos+enemyWidth)) && ((ypos<=projectileY && projectileY<=ypos+enemyHeight) || (ypos<=projectileY+projectileXHeight && projectileY+projectileXHeight<=ypos+enemyHeight))){
				//if the projectile collides with the boss
				Player.enemyTakesDamage = 5;//boss takes 5 damage per hit with no powerups
				if(Player.powerUpA)//if powerUpA is true
				Player.enemyTakesDamage = 10;//boss takes 10 damage per hit with powerup A
				if(Player.powerUpB)//if powerUpB is true
					Player.enemyTakesDamage = 15;//boss takes 15 damage per hit with powerup B
				if(Player.powerUpC)//if powerUpC is true
					Player.enemyTakesDamage = 30;//boss takes 30 damage per hit with powerup C
				if(Player.powerUpD)//if powerUpD is true
					Player.enemyTakesDamage = 40;//boss takes 40 damage per hit with powerup D
				setHealth(getHealth() - Player.enemyTakesDamage);//set health of the boss as current health minus the damage the enemy should take
				Frame.projectilesToRemove.add(projectile);//remove the projectile
				if(getHealth()<0)//if the boss's health is less than 0
					setHealth(0);//set the health to 0
			}
		}
		
		if(didCollide(playerX, playerY, playerWidth, playerHeight, enemyWidth, enemyHeight)){
			int giveDamage = 15;//player should take 15 damage
			Frame.player.takeDamageBoss(giveDamage, this, xpos, ypos);//player takes damage from the boss
		}
		
		didDie();
	}
	
	public int getHealth() {//get the current health
		return health;
	}
	
	public void setHealth(int health) {//set the health
		this.health = health;
	}
	
	public boolean didCollide(int playerX, int playerY, int playerWidth, int playerHeight, int enemyWidth, int enemyHeight){
		if(((xpos<=playerX && playerX<=xpos+enemyWidth) || (xpos<=playerX+playerWidth && playerX+playerWidth<=xpos+enemyWidth)) && ((ypos<=playerY && playerY<=ypos+enemyHeight) || (ypos<=playerY+playerHeight && playerY+playerHeight<=ypos+enemyHeight))){
			//if the player and boss collide
			return true;
		} else {
			return false;		
		}
	}
	
	//if the boss has less than or equal to 0 health it should be removed
	public void didDie(){
		if(health <= 0)
			Frame.bossesToRemove.add(this);
	}
}
