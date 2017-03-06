import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;


public class DisplayImage {//stores all the images in variables and lets the other classes use the images for display

	//initialize the image variables
	private static Image playerImage;
	private static Image crossbowImage;
	private static Image bulletImage;
	private static Image waterImage;
	private static Image enemyBImage;
	private static Image enemyCImage;
	private static Image launcherImage;
	private static Image bossImage;
	private static Image TankEnemyAImage;
	private static Image TankEnemyBImage;
	private static Image TankEnemyCImage;
	private static Image TankEnemyDImage;
	private static Image TankEnemyEImage;
	private static Image TankEnemyFImage;
	private static Image TankEnemyGImage;
	private static Image TankEnemyHImage;
	private static Image enemyDImage;
	private static Image enemyEImage;
	private static Image enemyFImage;
	private static Image enemyGAImage;
	private static Image enemyGBImage;
	private static Image enemyGCImage;
	private static Image enemyGDImage;
	private static Image enemyGEImage;
	private static Image enemyGFImage;
	private static Image enemyGGImage;
	private static Image enemyGHImage;
	
	public static void loadImages()
	{
		//initialize all the images
		String playerPic = "Maps/roggPic.png";
		String crossbow = "Maps/roggPic2.png";
		String bulletpic = "Maps/bullet.png";
		String enemyBPic = "Maps/enemyB.png";
		String enemyCPic = "Maps/enemyC.png";
		String launchPic = "Maps/roggPicRocketLauncher.png";
		String bossPic = "Maps/RC_Cola_12_oz_Can2.png";
		String tankPicA = "Maps/tankA.png";
		String tankPicB = "Maps/tankB.png";
		String tankPicC = "Maps/tankC.png";
		String tankPicD = "Maps/tankD.png";
		String tankPicE = "Maps/tankE.png";
		String tankPicF = "Maps/tankF.png";
		String tankPicG = "Maps/tankG.png";
		String tankPicH = "Maps/tankH.png";
		String enemyDPic = "Maps/enemyD.png";
		String enemyEPic = "Maps/enemyE.png";
		String enemyFPic = "Maps/enemyF.png";
		String enemyGAPic = "Maps/enemyGA.png";
		String enemyGBPic = "Maps/enemyGB.png";
		String enemyGCPic = "Maps/enemyGC.png";
		String enemyGDPic = "Maps/enemyGD.png";
		String enemyGEPic = "Maps/enemyGE.png";
		String enemyGFPic = "Maps/enemyGF.png";
		String enemyGGPic = "Maps/enemyGG.png";
		String enemyGHPic = "Maps/enemyGH.png";
		String waterPic = "Maps/roggPicWater.png";
		try {//make sure the images exist
			playerImage = ImageIO.read(new File(playerPic));
			crossbowImage = ImageIO.read(new File(crossbow));
			bulletImage = ImageIO.read(new File(bulletpic));
			enemyBImage = ImageIO.read(new File(enemyBPic));
			enemyCImage = ImageIO.read(new File(enemyCPic));
			launcherImage = ImageIO.read(new File(launchPic));
			bossImage = ImageIO.read(new File(bossPic));
			enemyDImage = ImageIO.read(new File(enemyDPic));
			TankEnemyAImage= ImageIO.read(new File(tankPicA));
			TankEnemyBImage= ImageIO.read(new File(tankPicB));
			TankEnemyCImage= ImageIO.read(new File(tankPicC));
			TankEnemyDImage= ImageIO.read(new File(tankPicD));
			TankEnemyEImage= ImageIO.read(new File(tankPicE));
			TankEnemyFImage= ImageIO.read(new File(tankPicF));
			TankEnemyGImage= ImageIO.read(new File(tankPicG));
			TankEnemyHImage= ImageIO.read(new File(tankPicH));
			enemyEImage = ImageIO.read(new File(enemyEPic));
			enemyFImage = ImageIO.read(new File(enemyFPic));
			enemyGAImage = ImageIO.read(new File(enemyGAPic));
			enemyGBImage = ImageIO.read(new File(enemyGBPic));
			enemyGCImage = ImageIO.read(new File(enemyGCPic));
			enemyGDImage = ImageIO.read(new File(enemyGDPic));
			enemyGEImage = ImageIO.read(new File(enemyGEPic));
			enemyGFImage = ImageIO.read(new File(enemyGFPic));
			enemyGGImage = ImageIO.read(new File(enemyGGPic));
			enemyGHImage = ImageIO.read(new File(enemyGHPic));
			waterImage = ImageIO.read(new File(waterPic));
			
		} catch (IOException e) {//catch exceptions
			e.printStackTrace();
		}
	}
	
	//get methods for all the images. each method returns the image
	public static Image getBulletImage(){
		return bulletImage;
	}
	
	public static Image getPlayerImage(){
		return playerImage;
	}
	
	public static Image getEnemyBImage(){
		return enemyBImage;
	}
	
	public static Image getEnemyCImage(){
		return enemyCImage;
	}
	
	public static Image getCrossImage(){
		return crossbowImage;
	}
	
	public static Image getLauncherImage(){
		return launcherImage;
	}
	
	public static Image getBossImage(){
		return bossImage;
	}
	
	public static Image getEnemyDImage(){
		return enemyDImage;
	}
	
	public static Image getTankAImage(){
		return TankEnemyAImage;
	}
	
	public static Image getTankBImage(){
		return TankEnemyBImage;
	}
	
	public static Image getTankCImage(){
		return TankEnemyCImage;
	}
	
	public static Image getTankDImage(){
		return TankEnemyDImage;
	}
	
	public static Image getTankEImage(){
		return TankEnemyEImage;
	}
	
	public static Image getTankFImage(){
		return TankEnemyFImage;
	}
	
	public static Image getTankGImage(){
		return TankEnemyGImage;
	}
	
	public static Image getTankHImage(){
		return TankEnemyHImage;
	}
	
	public static Image getEnemyEImage(){
		return enemyEImage;
	}
	
	public static Image getEnemyFImage() {
		return enemyFImage;
	}
	
	public static Image getEnemyGAImage() {
		return enemyGAImage;
	}
	
	public static Image getEnemyGBImage() {
		return enemyGBImage;
	}
	
	public static Image getEnemyGCImage() {
		return enemyGCImage;
	}
	
	public static Image getEnemyGDImage() {
		return enemyGDImage;
	}
	
	public static Image getEnemyGEImage() {
		return enemyGEImage;
	}
	
	public static Image getEnemyGFImage() {
		return enemyGFImage;
	}
	
	public static Image getEnemyGGImage() {
		return enemyGGImage;
	}
	
	public static Image getEnemyGHImage() {
		return enemyGHImage;
	}
	
	public static Image getWaterImage() {
		return waterImage;
	}
}

