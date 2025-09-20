import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Texture {

	
	private static BufferedImage [] images = new BufferedImage[100];
	private static BufferedImage image;
	
	
	
	public static void paint(
			int screenX, 
			int screenY,
			int picId,
			Graphics g,
			double alpha
			) {
		
		if (alpha == 0) {
			paint(screenX, screenY, picId, g);
		}
		else {
			Graphics2D g2 = (Graphics2D) g;
			
			AffineTransform at = new AffineTransform();
			at.translate(screenX, screenY);
			at.rotate(alpha,
					images[picId].getWidth()/2,
					images[picId].getHeight()/2);
			
			g2.drawImage(images[picId], at, null);
		}
		
	}
	
	
	public static void paint(
			int screenX, 
			int screenY,
			int picId,
			Graphics g
			) {
		
		g.drawImage(images[picId], 
				screenX, 
				screenY, 
				null);
		
	}
	
	public static boolean load(String filename) {
		
		File f = new File(filename);
		
		try {
			image = ImageIO.read(f);
			return true;
		} catch (IOException e) {
			return false;
		}
		
	}
	
	public static void crop(int row, int col, int picId) {
		
		images [picId] = image.getSubimage(
				col * Block.SIZE,
				row * Block.SIZE,
				Block.SIZE, 
				Block.SIZE);
		
	}
	
	
}
