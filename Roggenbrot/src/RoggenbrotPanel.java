import java.awt.*;
import java.util.LinkedList;
import javax.swing.JComponent;


public class RoggenbrotPanel extends JComponent
{ 
	private Player player;//player variable
	//linked lists for the enemies, projectiles, and bosses
	private LinkedList<Enemy> enemies;
	private LinkedList<Projectile> projectiles;
	private LinkedList<Boss> bosses;
	//store the default size of the window
	public final int WIDTH=1440, HEIGHT=840;
	//store the scale of the window
	public double scaleX=1.0, scaleY=1.0;
	//linked lists for the other enemies
	private LinkedList<RotateEnemyA> rotateAs;
	private LinkedList<RotateEnemyB> rotateBs;

	//create a new RoggenbrotPanel
	public RoggenbrotPanel(Player player, LinkedList<Enemy> enemies, LinkedList<Projectile> projectiles, LinkedList<Boss> bosses, LinkedList<RotateEnemyA> rotateAs, LinkedList<RotateEnemyB> rotateBs)
	{
		super();
		setPreferredSize(new Dimension (WIDTH,HEIGHT));
		//if no map is loaded the application background will be black
		setBackground(Color.BLACK);
		//set the values of the types of enemies and the player
		this.player=player;
		this.projectiles=projectiles;
		this.enemies=enemies;
		this.bosses=bosses;
		this.rotateAs=rotateAs;
		this.rotateBs=rotateBs;
	}

	public void paint(Graphics gUnsized) {

		super.paint(gUnsized);//calls the super class
		Graphics2D g=(Graphics2D) gUnsized;//intitializes a Graphics2D variable as gUnsized
		scaleX=1.0*getWidth()/WIDTH;//calculates the scale of the window for resizing
		scaleY=1.0*getHeight()/HEIGHT;
		g.scale(scaleX, scaleY);//scale everything wth movement from the window
		g.drawImage(Map.getImage(), 0, 0, 1440, 840, null);//draw the map image on default
		g.drawImage(player.getImage(), player.getX(), player.getY(), 30, 30, null);//draw player on default
		for(Projectile projectile: projectiles)//draw the projectiles
		{
				g.drawImage(projectile.getImage(), projectile.getX(), projectile.getY(), projectile.getImage().getWidth(null), projectile.getImage().getHeight(null), null);
			
		}
		for(Enemy enemy: enemies)//for each enemy
		{
			g.drawImage(enemy.getImage(), enemy.getX(), enemy.getY(), enemy.getImage().getWidth(null), enemy.getImage().getHeight(null), null);//draw the enemy
			//draw a health bar
			g.setColor(Color.red);
			g.drawLine(enemy.xpos, enemy.ypos-1, enemy.xpos+enemy.health, enemy.ypos-1);
		}
		for(Boss boss: bosses)//for each boss
		{
			g.drawImage(boss.getImage(), boss.getX(), boss.getY(), boss.getImage().getWidth(null), boss.getImage().getHeight(null), null);//draw the bosse
			//draw a health bar
			g.setColor(Color.orange);
			g.drawLine(boss.xpos, boss.ypos-1, boss.xpos+(30*(boss.getHealth()/250)), boss.ypos-1);
			}
		for(RotateEnemyA rotateA: rotateAs)//for each enemy
		{
			g.drawImage(rotateA.getImage(), rotateA.getX(), rotateA.getY(), rotateA.getImage().getWidth(null), rotateA.getImage().getHeight(null), null);//draw it
			//draw a health bar
			g.setColor(Color.blue);
			g.drawLine(rotateA.xpos, rotateA.ypos-1, rotateA.xpos+(30*(rotateA.getHealth()/rotateA.getIHealth())), rotateA.ypos-1);
		}
		for(RotateEnemyB rotateB: rotateBs)//for each enemy
		{
			g.drawImage(rotateB.getImage(), rotateB.getX(), rotateB.getY(), rotateB.getImage().getWidth(null), rotateB.getImage().getHeight(null), null);//draw it
		}
	}

}