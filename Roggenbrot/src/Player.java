import java.awt.Image;


public class Player extends Character{//Player has Character characteristics

	int takingDamage=0;//takeDamage variable
	static int health = 150;//initialize health
	public static int enemyTakesDamage = 5;//damage taken by enemy
	public static boolean powerUpA = false;//all powerups off
	public static boolean powerUpB = false;
	public static boolean powerUpC = false;
	public static boolean powerUpD = false;

	public Player(int xpos, int ypos)//Player constructor
	{
		super(xpos, ypos, null);//call Character constructor
		Image image = DisplayImage.getCrossImage();//get initial image
		setImage(image);//set the initial image

	}
	public void setMovingNorth(boolean movingNorth)
	{
		if(takingDamage == 0)//if the player isnt taking damage
		{
			this.movingNorth=movingNorth;//move north
			if(movingNorth)
				movingSouth=false;//stop moving south
		}
	}
	public void setMovingSouth(boolean movingSouth)
	{
		if(takingDamage == 0)//if the player isnt taking damage
		{
			this.movingSouth=movingSouth;//move south
			if(movingSouth)
				movingNorth=false;//stop moving north
		}
	}
	public void setMovingWest(boolean movingWest)
	{
		if(takingDamage == 0)//if the player isnt taking damage
		{
			this.movingWest=movingWest;//move west
			if(movingWest)
				movingEast=false;//stop moving east
		}
	}
	public void setMovingEast(boolean movingEast)
	{
		if(takingDamage == 0)//if the player isnt taking damage
		{
			this.movingEast=movingEast;//move east
			if(movingEast)
				movingWest=false;//stop moving west
		}
	}

	public void exec()
	{
		super.exec();
		if(Map.getArray()[(ypos+15)/30][(xpos+15)/30]=='!')//if the player reaches a part of the map denoted as ! in the csv file
		{
			//reset position and go to the next level
			xpos=30;
			ypos=30;

			Map.loadNextMap();
		}
		if(Map.getArray()[(ypos+15)/30][(xpos+15)/30]=='(')//if denoted as a C in the csv
		{
			//set powerUpA to true
			powerUpA=true;
			//increase maxRadius
			Projectile.maxRadius=600;
			//change the image of the Player
			setImage(DisplayImage.getPlayerImage());

			if(powerUpA && powerUpB)//if both powerUpA and powerUpB
			{
				Projectile.speedP=40;//change the speed
				setImage(DisplayImage.getLauncherImage());//change the image
			}
		}
		if(Map.getArray()[(ypos+15)/30][(xpos+15)/30]=='B')//if denoted as B in the csv
		{
			powerUpC=true;//set powerUpC to true
			Projectile.maxRadius=800;//increase the max radius
			setImage(DisplayImage.getLauncherImage());//set the player image

		}
		if(Map.getArray()[(ypos+15)/30][(xpos+15)/30]=='M')//if denoted as M in the csv
		{
			powerUpD=true;//powerUpD is true
			Projectile.maxRadius=1000;//increase the radius
			setImage(DisplayImage.getWaterImage());//change the image
			enemyTakesDamage=60;//enemy takes more damage
			Projectile.speedP=60;//speed of the projectile increases
		}
		if(Map.getArray()[(ypos+15)/30][(xpos+15)/30]==')')//if denoted as ) in the csv
		{
			powerUpB=true;//powerUpB is true

			if(powerUpA && powerUpB)//if both A and B
			{
				Projectile.speedP=40;//speed is changed
				setImage(DisplayImage.getLauncherImage());//change image
			}
		}
		if(Map.getArray()[(ypos+15)/30][(xpos+15)/30]=='%')//if denoted as % in the csv
		{
			if(!Player.powerUpD)//if powerUpD is false
				health-=5;//take damage

		}
		if(Map.getArray()[(ypos+15)/30][(xpos+15)/30]=='#')//if # on csv
		{
			health-=15;//lose health

		}
		if(takingDamage != 0)//if takingDamage does not equal 0
		{
			takingDamage=takingDamage+1;//add one
			if (takingDamage==0)//if it does equal 0
			{
				movingEast=false;//set all moving to false
				movingNorth=false;
				movingSouth=false;
				movingWest=false;
			}
		}
	}
	public void takeDamage(int damage, Enemy enemy, int enemyX, int enemyY)//take damage for Enemy
	{
		takingDamage=1;//reinitialize takingDamage to 1
		if(enemyX<=Frame.player.getX())//if the enemy is to the left of the player
		{
			movingEast=true;//move east and not west
			movingWest=false;
		}
		else//otherwise
		{
			movingEast=false;//move west and not east
			movingWest=true;
		}
		if(enemyY<=Frame.player.getY())//if the enemy is above the player
		{
			movingSouth=true;//move south and not north
			movingNorth=false;
		}
		else//otherwise
		{
			movingSouth=false;//move north and not south
			movingNorth=true;
		}
		health = health-damage;//player's health decreases
		if(health<=0)//if the player's health is less than 0 make it 0
			health=0;
		System.out.println("Your current health is " + health);//print the player's health to console

	}

	public void takeCDamage(int damage, EnemyC enemyC, int enemyX, int enemyY)
	{
		takingDamage=1;//reinitialize takingDamage to 1
		if(enemyX<=Frame.player.getX())//if the enemy is to the left of the player
		{
			movingEast=true;//move east and not west
			movingWest=false;
		}
		else//otherwise
		{
			movingEast=false;//move west and not east
			movingWest=true;
		}
		if(enemyY<=Frame.player.getY())//if the enemy is above the player
		{
			movingSouth=true;//move south and not north
			movingNorth=false;
		}
		else//otherwise
		{
			movingSouth=false;//move north and not south
			movingNorth=true;
		}
		health = health-damage;//player's health decreases
		if(health<=0)//if the player's health is less than 0 make it 0
			health=0;
		System.out.println("Your current health is " + health);//print the player's health to console

	}

	public void takeDamageRA(int damage, RotateEnemyA slow, int enemyX, int enemyY)
	{
		takingDamage=1;//reinitialize takingDamage to 1
		if(enemyX<=Frame.player.getX())//if the enemy is to the left of the player
		{
			movingEast=true;//move east and not west
			movingWest=false;
		}
		else//otherwise
		{
			movingEast=false;//move west and not east
			movingWest=true;
		}
		if(enemyY<=Frame.player.getY())//if the enemy is above the player
		{
			movingSouth=true;//move south and not north
			movingNorth=false;
		}
		else//otherwise
		{
			movingSouth=false;//move north and not south
			movingNorth=true;
		}
		health = health-damage;//player's health decreases
		if(health<=0)//if the player's health is less than 0 make it 0
			health=0;
		System.out.println("Your current health is " + health);//print the player's health to console

	}

	public void takeDamageBoss(int damage, Boss boss, int enemyX, int enemyY)
	{
		takingDamage=1;//reinitialize takingDamage to 1
		if(enemyX<=Frame.player.getX())//if the enemy is to the left of the player
		{
			movingEast=true;//move east and not west
			movingWest=false;
		}
		else//otherwise
		{
			movingEast=false;//move west and not east
			movingWest=true;
		}
		if(enemyY<=Frame.player.getY())//if the enemy is above the player
		{
			movingSouth=true;//move south and not north
			movingNorth=false;
		}
		else//otherwise
		{
			movingSouth=false;//move north and not south
			movingNorth=true;
		}
		health = health-damage;//player's health decreases
		if(health<=0)//if the player's health is less than 0 make it 0
			health=0;
		System.out.println("Your current health is " + health);//print the player's health to console
	}
	public void takeDamageRB(int giveDamage, RotateEnemyB rotateEnemyB,
			int xpos, int ypos) {
		// TODO Auto-generated method stub
		takingDamage=1;//reinitialize takingDamage to 1
		if(xpos<=Frame.player.getX())//if the enemy is to the left of the player
		{
			movingEast=true;//move east and not west
			movingWest=false;
		}
		else//otherwise
		{
			movingEast=false;//move west and not east
			movingWest=true;
		}
		if(ypos<=Frame.player.getY())//if the enemy is above the player
		{
			movingSouth=true;//move south and not north
			movingNorth=false;
		}
		else//otherwise
		{
			movingSouth=false;//move north and not south
			movingNorth=true;
		}
		health = health-giveDamage;//player's health decreases
		if(health<=0)//if the player's health is less than 0 make it 0
			health=0;
		System.out.println("Your current health is " + health);//print the player's health to console
	}

}
