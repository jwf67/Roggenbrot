import java.awt.Image;
public class EnemyD extends Enemy{//EnemyD extends the properties of Enemy

	public EnemyD(int xpos, int ypos) {//EnemyD constructor
		super(xpos, ypos);//call Enemy constructor
		health = 120;//current health initialized at 120
		giveDamage=10;//player takes 10 damage from EnemyD
		Image imageE = DisplayImage.getEnemyDImage();//get the image of EnemyD
		setImage(imageE);//set the image of EnemyD
	}

}
