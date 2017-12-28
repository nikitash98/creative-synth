import java.awt.Graphics;

import javax.swing.JPanel;

public class DrawBuffer extends JPanel {
	private byte[] buffer = {0};
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		int themid = this.getSize().height/2;
		g.drawLine(0, themid, this.getSize().width, themid);
		
		g.drawLine(0, themid + 127, this.getSize().width, themid + 127);
		g.drawLine(0, themid - 127, this.getSize().width, themid - 127);

		for(int i = 0; i < buffer.length && i < this.getSize().width; i++){
			if(i > 0){
				g.drawLine(i-1, this.getSize().height/2 + buffer[i-1], i, this.getSize().height/2 + buffer[i]);
			}
		}
		
	}
	
	public void setBuffer(byte[] entry){
		buffer = entry;
	}
	
}
