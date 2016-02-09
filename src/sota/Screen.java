package sota;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Screen extends MouseAdapter {
	
	private SOTA game;
	private Controller controller;
	
	public Screen(SOTA g, Controller c) {
		this.game = g;
		this.controller = c;
	}

	public void mousePressed(MouseEvent e) {
		int mouseX = e.getX();
		int mouseY = e.getY();
		
		// Check if on StartGame button
		if(game.state == ScreenState.WelcomeScreen && isWithinRegion(mouseX, mouseY, 210, 160, 220, 80)) {
			// Start Game
			game.state = ScreenState.Levels;
			return;		// So that the user has to click again on the next screen
		}
		
		if(game.state == ScreenState.Levels && isWithinRegion(mouseX, mouseY, 210, 160, 220, 240)) {
			// Select difficulty level
			if(isWithinRegion(mouseX, mouseY, 210, 160, 220, 80)) {
				UserAgent.velX = 3;
			} else if(isWithinRegion(mouseX, mouseY, 210, 240, 220, 80)) {
				UserAgent.velX = 4;
			} else if(isWithinRegion(mouseX, mouseY, 210, 320, 220, 80)) {
				UserAgent.velX = 5;
			}
			
			// Start Game
			game.state = ScreenState.Play;
			startGame();
			return;
		}
		
		// Check if on HowToPlay button
		if(game.state == ScreenState.WelcomeScreen && isWithinRegion(mouseX, mouseY, 210, 240, 220, 80)) {
			// Open HowToPlay instructions
			game.state = ScreenState.HowToPlay;
			return;
		}
		
		// Check if on ExitGame button
		if(game.state == ScreenState.WelcomeScreen && isWithinRegion(mouseX, mouseY, 210, 320, 220, 80)) {
			// Exit Game
			System.exit(0);
		}
		
		// Check if on BackToHome button
		if(game.state == ScreenState.HowToPlay || game.state == ScreenState.Levels 
				&& isWithinRegion(mouseX, mouseY, 0, SOTA.HEIGHT - 120, 150, 80)) {
			// Back to Welcome Screen
			game.state = ScreenState.WelcomeScreen;
		}
	}

	private void startGame() {

		// Floor and ceiling
		controller.addAgent(new Floor(0, SOTA.HEIGHT-100, TYPE.Floor, SOTA.WIDTH, 100));
		controller.addAgent(new Ceiling(0, 62, TYPE.Ceiling, SOTA.WIDTH, 40));
		
		// Player
		controller.addAgent(new UserAgent(controller, 0, SOTA.HEIGHT-132, TYPE.Player));
		
		// Obstacle for Level 1
		controller.addAgent(new Obstacle(SOTA.WIDTH/2, SOTA.HEIGHT-132, TYPE.Obstacle, 32, 32));
	}

	public void render(Graphics g) {
		// Render screen based on GameState
		if(game.state == ScreenState.WelcomeScreen) {
			loadWelcomeScreen(g);
		} else if(game.state == ScreenState.Levels) {
			loadLevelsScreen(g);
		} else if(game.state == ScreenState.HowToPlay) {
			loadHowToPlayScreen(g);
		} else if(game.state == ScreenState.Win) {
			loadWinScreen(g);
		} else if(game.board.LIFE == 0 || game.state == ScreenState.Lose) {
			loadLoseScreen(g);
		}	
	}
	
	private void loadLevelsScreen(Graphics g) {

		Font f = new Font("Garamond", 1, 30);
		
		g.setFont(f);
		g.setColor(Color.CYAN);
		g.drawString("Choose your level of difficulty", 80, 70);
		
		g.setColor(Color.BLUE);
		g.drawRect(210, 160, 220, 80);
		g.drawString("Easy", 260, 210);
		
		g.setColor(Color.BLUE);
		g.drawRect(210, 240, 220, 80);
		g.drawString("Medium", 260, 290);
		
		g.setColor(Color.BLUE);
		g.drawRect(210, 320, 220, 80);
		g.drawString("Hard", 260, 375);
		
		//Back To Homescreen button
		g.setColor(Color.BLUE);
		g.drawRect(0, SOTA.HEIGHT - 120, 150, 80);
		g.drawString("<- Home", 10, SOTA.HEIGHT - 70);
		
	}

	private void loadLoseScreen(Graphics g) {

		Font f = new Font("Garamond", 1, 30);
		
		g.setFont(f);
		g.setColor(Color.CYAN);
		g.drawString("Haha! You lost at a One-Button Game!!", 50, 90);
		
		// Print score
		g.setColor(Color.BLUE);
		g.drawString("Your score: "+ game.board.SCORE, 220, 200);
		
	}

	private void loadWelcomeScreen(Graphics g) {

		Font f = new Font("Garamond", 1, 30);
		Font f1 = new Font("Garamond", 1, 20);
		
		g.setFont(f);
		g.setColor(Color.CYAN);
		g.drawString("Welcome to the game of SOTA v1.0", 80, 70);
		
		g.setColor(Color.BLUE);
		g.drawRect(210, 160, 220, 80);
		g.drawString("Start Game", 240, 210);
		
		g.setColor(Color.BLUE);
		g.drawRect(210, 240, 220, 80);
		g.drawString("How to Play", 240, 290);
		
		g.setColor(Color.BLUE);
		g.drawRect(210, 320, 220, 80);
		g.drawString("Exit Game", 240, 375);
		

		g.setFont(f1);
		g.drawString("- T.M. Sousa", 3*SOTA.WIDTH/4, 9*SOTA.HEIGHT/10);
	}

	private void loadHowToPlayScreen(Graphics g) {

		Font f = new Font("Garamond", 1, 30);
		
		g.setFont(f);
		g.setColor(Color.CYAN);
		g.drawString("How To Play", 230, 60);
		
		// Instructions
		g.setColor(Color.BLUE);
		g.drawString("Press the Space key to jump.", 80, 110);
		g.drawString("Red obstacles are dangerous", 80, 140);
		g.drawString("Avoid them by jumping over them.", 80, 170);
		g.drawString("Your Health decreases every time", 80, 200);
		g.drawString("you hit an obstacle.", 80, 230);
		g.drawString("Your score increases as you ", 80, 260);
		g.drawString("reach further in the game.", 80, 290);
		g.drawString("Press Escape to exit.", 80, 320);
		g.drawString("Press r to restart the level.", 80, 350);
		
		// Back To Homescreen button
		g.setColor(Color.BLUE);
		g.drawRect(0, SOTA.HEIGHT - 120, 150, 80);
		g.drawString("<- Home", 10, SOTA.HEIGHT - 70);
		
	}

	private void loadWinScreen(Graphics g) {

		Font f = new Font("Garamond", 1, 30);
		
		g.setFont(f);
		g.setColor(Color.CYAN);
		g.drawString("You Win!!", 240, 90);
		
		// Print score
		g.setColor(Color.BLUE);
		g.drawString("Your score: "+ game.board.SCORE, 230, 200);
	}

	public void tick() {
		// Purposely kept blank
	}
	
	public boolean isWithinRegion(int mouseX, int mouseY, int regionX, int regionY, int regionWidth, int regionHeight) {
		if(mouseX > regionX && mouseX < regionX + regionWidth && 
				mouseY > regionY && mouseY < regionY + regionHeight) {
			return true;
		}
		return false;
	}
}
