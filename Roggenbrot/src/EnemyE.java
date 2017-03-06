import java.awt.Image;
public class EnemyE extends Enemy{

	public EnemyE(int xpos, int ypos) {//EnemyE constructor
		super(xpos, ypos);//call Enemy constructor
		health = 150;//current health initialized at 150
		giveDamage=15;//player takes 15 damage from EnemyE
		Image imageE = DisplayImage.getEnemyEImage();//get the image of EnemyE
		setImage(imageE);//set the image of EnemyE
	}
}
