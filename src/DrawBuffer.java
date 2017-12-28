import java.awt.Graphics;

import javax.swing.JPanel;

public class DrawBuffer extends JPanel {
	private byte[] buffer = {0};
	public void paintComponent(Graphics g){
		for(int i = 0; i < buffer.length; i++){
			if(i > 0){
				g.drawLine(i-1, 127 + buffer[i-1], i, 127 + buffer[i]);
			}
		}
		
	}
	
	public void setBuffer(byte[] entry){
		buffer = entry;
	}
	
}
