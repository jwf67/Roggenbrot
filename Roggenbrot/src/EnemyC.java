import java.awt.Image;


public class EnemyC extends Enemy{//EnemyC extends the properties of Enemy

	public EnemyC(int xpos, int ypos) {//EnemyC constructor
		super(xpos, ypos);//call Enemy constructor
		health=100;//current health initialized at 100
		giveDamage=10;//player takes 10 damage from EnemyC
		Image imageE = DisplayImage.getEnemyCImage();//get the image of EnemyC
		setImage(imageE);//set the image of EnemyC
	}
}
