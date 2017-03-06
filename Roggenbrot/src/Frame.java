import java.awt.Image;
import java.util.Scanner;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.LinkedList;
import javax.sound.midi.*;

import javax.swing.JFrame;
import javax.swing.Timer;

public class Frame extends JFrame implements ActionListener, KeyListener, MouseListener
{ 
	//create player and linkedlists for enemies, projectiles, bosses
	public static Player player;
	public static LinkedList<Enemy> enemies, enemiesToRemove;
	public static LinkedList<Projectile> projectiles,projectilesToRemove;
	public static LinkedList<Boss> bosses, bossesToRemove;
	public static LinkedList<RotateEnemyA> rotateAs, rotateAsToRemove;
	public static LinkedList<RotateEnemyB> rotateBs, rotateBsToRemove;
	public static int count = 0;
	private Timer timer;//create timer
	Scanner scan = new Scanner(System.in);

	private RoggenbrotPanel Rogg;

	public static void main(String[] args) 
	{
		DisplayImage.loadImages();//load all of the images
		new Frame();//create a new frame
	}

	public Frame()
	{
		//initialize player and linkedlists for enemies, projectiles, bosses
		player = new Player(30, 30);
		enemies = new LinkedList<Enemy>();
		projectiles = new LinkedList<Projectile>();
		projectilesToRemove = new LinkedList<Projectile>();
		enemiesToRemove = new LinkedList<Enemy>();
		bosses = new LinkedList<Boss>();
		bossesToRemove = new LinkedList<Boss>();
		rotateAs = new LinkedList<RotateEnemyA>();
		rotateAsToRemove = new LinkedList<RotateEnemyA>();
		rotateBs = new LinkedList<RotateEnemyB>();
		rotateBsToRemove = new LinkedList<RotateEnemyB>();
		//initialize a new timer
		timer = new Timer(33, this);

		Map.loadFirstMap();//load first map
		Rogg = new RoggenbrotPanel(player, enemies, projectiles, bosses, rotateAs, rotateBs);//create a new RoggenbrotPanel
		Rogg.addMouseListener(this);//add a new MouseListener
		setContentPane(Rogg);
		addKeyListener(this);//add a key lsitener
		//addMouseListener(this);
		pack();
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		timer.start();//start the timer
	}

	public void actionPerformed(ActionEvent evt)
	{

		player.exec();
		//execute exec() methods for every existing entity
		for(Enemy enemy: enemies)
		{
			enemy.exec();
		}
		for(Projectile projectile: projectiles)
		{
			projectile.exec();
		}
		for(Projectile projectile: projectilesToRemove)
		{
			projectiles.remove(projectile);
		}
		for(Enemy enemy: enemiesToRemove)
		{
			enemies.remove(enemy);
		}
		for(Boss boss: bosses)
		{
			boss.exec();
		}
		for(Boss boss: bossesToRemove)
		{
			bosses.remove(boss);
		}
		
		for(RotateEnemyA rotateA: rotateAs)
		{
			rotateA.exec();
		}
		for(RotateEnemyA rotateA: rotateAsToRemove)
		{
			rotateAs.remove(rotateA);
		}
		for(RotateEnemyB rotateB: rotateBs)
		{
			rotateB.exec();
		}
		for(RotateEnemyB rotateB: rotateBsToRemove)
		{
			rotateBs.remove(rotateB);
		}
		//remove all entities supposed to be removed
		enemiesToRemove.clear();
		bossesToRemove.clear();
		rotateAsToRemove.clear();
		rotateBsToRemove.clear();
		repaint();
		if(player.health <= 0)//if player's health is 0 or less than 0
		{
			System.out.println("You lost... resetting game.");//you lose the game
			player.health = 150;//reset health to 150
			Map.loadFirstMap();//go back to the first level
		}

	}

	@Override
	public void keyPressed(KeyEvent e) {//checks which key is pressed
		if(e.getKeyCode()==KeyEvent.VK_G)//if G is pressed
		{
			char[][] array = Map.getArray();
			for(int i = 0; i<28; i++)
			{

				for(int j = 0; j<48; j++)
				{
					if(player.getX()/30==j && player.getY()/30 == i)
					{
						System.out.print('x');
					}
					else
						System.out.print(array[i][j]);
				}
				System.out.println();
			}

		}
		if(e.getKeyCode()==KeyEvent.VK_W)//if W is pressed
		{
			player.setMovingNorth(true);//move north
		}
		if(e.getKeyCode()==KeyEvent.VK_S)//if S is pressed
		{
			player.setMovingSouth(true);//move south
		}
		if(e.getKeyCode()==KeyEvent.VK_A)//if A is pressed
		{
			player.setMovingWest(true);//move west
		}
		if(e.getKeyCode()==KeyEvent.VK_D)//if D is pressed
		{
			player.setMovingEast(true);//move east
		}
		if(e.getKeyCode()==KeyEvent.VK_Z)//if Z is pressed
		{
			System.out.println("Your Current Health: " + player.health);//shows current health in console
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		if(e.getKeyCode()==KeyEvent.VK_W)//if W is released
		{
			player.setMovingNorth(false);//player stops moving north
		}
		if(e.getKeyCode()==KeyEvent.VK_S)//if S is released
		{
			player.setMovingSouth(false);//player stops moving south
		}
		if(e.getKeyCode()==KeyEvent.VK_A)//if A is released
		{
			player.setMovingWest(false);//player stops moving west
		}
		if(e.getKeyCode()==KeyEvent.VK_D)//if D is released
		{
			player.setMovingEast(false);//player stops moving east
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {//when the mouse is released
		int x = e.getX();//get the x and y positions of where clicked
		int y = e.getY();
		x=(int)(x/Rogg.scaleX);//get the scaled values if screen size changes
		y=(int)(y/Rogg.scaleY);
		int width = x - player.getX()+15;//width and height of area clicked - player stored
		int height = y - player.getY()+15;
		double angle;//variable for angle

		if(width==0){//if width = 0
			if(height>0)//if clicked straight from above
				angle=Math.PI/2;//shoot up
			else if(height<0)//if clicked directly below
				angle=-Math.PI/2;//shoot down
			else //if clicked directly on
				angle = 0;}//angle is 0
		else{//otherwise
			angle = Math.atan(height*1.0/width);//angle calculation
			if(width<0)//if width is less than 0
			{
				angle+=Math.PI;//add pi to the angle
			}
		}

		//System.out.println(player.getX()+15 + " " + (player.getY()+15) + " " + x + " " + y);
		//System.out.println(angle*180/Math.PI);
		Image image;//inage variable

		if(player.powerUpA)//if the player got powerup A
		{
			image=DisplayImage.getPlayerImage();//get a new player image
			if(player.powerUpB)//if the player got powerup B
				image=image.getScaledInstance(20, 20, Image.SCALE_FAST);//create scaled version of image
			else if(player.powerUpC)
				image = image.getScaledInstance(30, 30, Image.SCALE_FAST);//create scaled version of image
			else
				image=image.getScaledInstance(10, 10, Image.SCALE_FAST);//create scaled version of image
		}
		else
			image=DisplayImage.getBulletImage();//get the bullet image
		projectiles.add(new Projectile(player.getX()+10, player.getY()+10, angle, image));//add a new projectile
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}