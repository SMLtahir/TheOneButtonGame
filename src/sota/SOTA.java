package sota;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.Random;

public class SOTA extends Canvas implements Runnable{
	
	private static final long serialVersionUID = 1L;
	
	public static final int WIDTH = 640, HEIGHT = 3* WIDTH/4 ;
	private Thread thread;
	private boolean isRunning = false;
	
	public Scoreboard board;
	public ScreenState state;
	private Screen screen;
	private Controller controller;
	
	// Game Constructor
	public SOTA() {
		// Initialize controller, screen and window and add keyboard and mouse listeners
		controller = new Controller(this);
		this.addKeyListener(new KeyboardInput(controller));
		
		screen = new Screen(this, controller);
		this.addMouseListener(screen);
		
		new Window(WIDTH, HEIGHT, "SOTA 1.0", this);
		board = new Scoreboard();
		state = ScreenState.WelcomeScreen;
		
		if(state == ScreenState.Play) {
			// Floor and ceiling
			controller.addAgent(new Floor(0, HEIGHT-100, TYPE.Floor, SOTA.WIDTH, 100));
			controller.addAgent(new Ceiling(0, 62, TYPE.Ceiling, SOTA.WIDTH, 40));
			
			// Player
			controller.addAgent(new UserAgent(controller, 0, HEIGHT-132, TYPE.Player));
			
			// Obstacle for Level 1
			controller.addAgent(new Obstacle(SOTA.WIDTH/2, HEIGHT-132, TYPE.Obstacle, 32, 32));
		}
		
	}
	
	public static void main(String[] args) {
		// Create instance of the game
		new SOTA();
	}

	public synchronized void start() {
		// Taken from online source
		thread = new Thread(this);
		thread.start();
		isRunning = true;
	}
	
	public synchronized void stop() {
		// Taken from online source
		try {
			thread.join();
			isRunning = false;
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void run() {
		// Method to run thread
		
		// Request automatic focus to the game window
		this.requestFocus();
		
		// This is a popular game loop taken from online source
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000/ amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		
		while(isRunning) {
			long now = System.nanoTime();
			delta += (now - lastTime)/ns;
			lastTime = now;
			while(delta >= 1) {
				tick();
				delta--;
			}
			if(isRunning)
				render();
			
			if(System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
			}
		}
		stop();
	}
	
	private void tick() {
		controller.tick();
		if(state == ScreenState.Play) {
			board.tick();
		} else {
			screen.tick();
		}
	}
	
	private void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		
		g.setColor(Color.BLACK);
		g.fillRect(0,0,WIDTH,HEIGHT);
		
		controller.render(g);
		
		// Losing condition reached
		if(Scoreboard.LIFE <= 0) {
			controller.agents.clear();
			state = ScreenState.Lose;
		}
		
		if(state == ScreenState.Play) {
			board.render(g);
		} else {
			screen.render(g);
		}
		
		g.dispose();
		bs.show();
	}
	
	public static int clampToMinMax(int val, int min, int max) {
		if(val <= min) { return min;}
		else if(val >= max) { return max;}
		return val;
	}
	
}
