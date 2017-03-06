import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import javax.imageio.ImageIO;


public class Map {
	private static Image image;//image variable to stre map
	public static int index = 1;//store the current level default 1
	private static char array[][];//store 2d array of map
	static int levels = 10;//total number of levels
	
	public static void loadFirstMap()//loads the first map
	{
		index = 1;//index of first map is 1
		readMap(index);//calls readMap which will get the map of index
	}
	public static void loadNextMap()//loads the next map
	{
		
		if(index<=levels-1)//if the level is valid
		{
		
		index++;//add 1 to index
		readMap(index);//read the level of index
		}
		else
			{

			System.out.println("You win!");//console prints you win
			index=1;//reset index
			Frame.count=0;//reset count
			readMap(index);//get the first level
			Player.health=150;//reset player stats
			Player.powerUpA=false;
			Player.powerUpB=false;
			Player.powerUpC=false;
			Player.powerUpD=false;
			Projectile.speedP=20;
			Projectile.maxRadius=400;
			
			
			}
	}
	public static void setDefaultState(){
		Frame.count = 0;
		Player.health=150;
		Player.powerUpA=false;
		Player.powerUpB=false;
		Player.powerUpC=false;
		Player.powerUpD=false;
		Projectile.speedP=20;
		Projectile.maxRadius=400;
		Frame.player.setImage(DisplayImage.getCrossImage());//default image
	}
	//all enemies die when reaching new level
	public static void clearEnemies(){
		Frame.enemies.clear();
		Frame.projectiles.clear();
		Frame.bosses.clear();
		Frame.rotateAs.clear();
		Frame.rotateBs.clear();
	}
	
	private static void readMap(int index)

	{	
		Frame.player.xpos=30;//set the position of the player to position [1][1] in the array
		Frame.player.ypos=30;
		
		//if the player is on the first level
		if(index == 1){
			setDefaultState();	
		}
		//upon new level, delete enemies
		clearEnemies();
		//get all the necessary map pictures and csv files for the framework of the levels
		String imageFilePath = "Maps/L" + index + ".png";
		String courseFilePath = "Maps/TextMapFighter" + index + ".csv";
		array = new char[28][48];//new array to store csv symbols
		try {//check if the image and csv file are valid
			Scanner scan = new Scanner (new File(courseFilePath));
			image = ImageIO.read(new File(imageFilePath));
			for(int i = 0; i<28; i++)//go through all the elements (rows)
			{
				Scanner columnScanner = new Scanner(scan.nextLine());
				columnScanner.useDelimiter(",");//using a comma delimeter only get the other symbols of the csv file
				
				for(int j = 0; j<48; j++)//go through all the elements (columns)
				{
					//System.out.println(courseFilePath + " " + i + " " + j);
					char symbol = columnScanner.next().charAt(0);
					switch(symbol){
						case '^': Frame.enemies.add(new Enemy(j*30, i*30));
							array[i][j]='*';
							break;
						case '$': Frame.bosses.add(new Boss(j*30, i*30));//add a boss
							break;
						case 'O': Frame.enemies.add(new EnemyC(j*30, i*30));//add an enemy C
						case 'D': Frame.enemies.add(new EnemyD(j*30, i*30));//add an enemy D
						case 'T': Frame.rotateAs.add(new RotateEnemyA(j*30, i*30));//add a rotate enemy A
						case 'E': Frame.enemies.add(new EnemyE(j*30, i*30));//add an emeny E
						case 'F': Frame.enemies.add(new EnemyF(j*30, i*30));// add an enemy F
						case 'G': Frame.rotateBs.add(new RotateEnemyB(j*30, i*30));//add a rotate enemy B
						default: array[i][j]=symbol;
					}
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static char[][] getArray()//get the array of the level
	{
		return array;
	}
	public static Image getImage()//get the image of the level
	{
		return image;
	}
}
//this was 5 years ago