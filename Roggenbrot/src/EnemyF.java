import java.awt.Image;
public class EnemyF extends Enemy{//EnemyF extends the properties of Enemy

	public EnemyF(int xpos, int ypos) {//EnemyF constructor
		super(xpos, ypos);//call the enemy constructor
		health = 170;//current health initialized at 170
		giveDamage=5;//player takes 5 damage from EnemyF
		Image imageE = DisplayImage.getEnemyFImage();//get the image of EnemyF
		setImage(imageE);//set the image of EnemyF
	}
}
