package sota;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;

public class Floor extends GameAgent{

	private static final long serialVersionUID = 1L;
	private int WIDTH;
	private int HEIGHT;
	
	public Floor(int x, int y, TYPE type, int width, int height) {

		super(x, y, type);
		this.WIDTH = width;
		this.HEIGHT = height;
	}

	@Override
	public void tick() {

		// No tick() method for floor
	}

	@Override
	public void render(Graphics g) {

		g.setColor(Color.DARK_GRAY);
		g.fillRect(x, y, WIDTH, HEIGHT);
		paint(g);
	}

	@Override
	public Rectangle getBounds() {

		return new Rectangle(x, y, WIDTH, HEIGHT);
	}
	
	@Override
	public void paint(Graphics g) {
	    Graphics2D g2 = (Graphics2D) g;

	    /*URL url = Floor.class.getResource("src/sota/floor.jpg");
	    ImageIcon icon = new ImageIcon(url);
	    JFrame frame = new JFrame();
        frame.add(new JLabel(icon));
//        frame.pack();
*/	    Image img1 = Toolkit.getDefaultToolkit().getImage("resources/floor.jpg");
	    g2.drawImage(img1, x, y, this);
	    g2.finalize();
	}
	
}
