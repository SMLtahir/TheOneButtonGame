package sota;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyboardInput extends KeyAdapter{
	
	private Controller controller;
	
	// Constructor
	public KeyboardInput(Controller c) {
		this.controller = c;
	}
	
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		
		// Exit Game
		if(key == KeyEvent.VK_ESCAPE) {
			System.exit(0);
		}
		
		for(int i=0; i< controller.agents.size(); i++) {
			GameAgent agent = controller.agents.get(i);
			
			if(agent.type == TYPE.Player) {
				// Key events for player
//				System.out.println(o.getY());
				// Respawn
				if(key == KeyEvent.VK_R) {
					UserAgent u = (UserAgent) agent;
					u.respawn();
				}
				
				// Jump
				if(key == KeyEvent.VK_SPACE && agent.getY() == 348) { 
					for(int j=0;j<10;j++) {
						agent.setVy(agent.getVy() - 1);
					}
					
				}
			}
		}
		
	}
	
	public void keyReleased(KeyEvent e) {
		
		int key = e.getKeyCode();
		
		for(int i=0; i< controller.agents.size(); i++) {
			GameAgent a = controller.agents.get(i);
			
			if(a.type == TYPE.Player) {
				// Key events for userAgent
				
				if(key == KeyEvent.VK_SPACE) { 
					a.setVy(3);
				}
			}
		}
		
	}
	
}
