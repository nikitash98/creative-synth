import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;

public class SawGenerator extends SoundGenerator {

	//Creates a byte array of sin wave sound
	//Creates an array of saw wave sound
	public byte [] createBuffer( int length){
		int samples = (int)((length * SAMPLING_RATE));
		byte [] output = new byte [samples];
		double period = (double)SAMPLING_RATE/frequency;
		double slope = (this.strength)/period;
		int mover = 0;

		for(int i = 0; i < output.length; i++){
			if(slope * mover >= this.strength){
				mover = 0;
			} 
			
			output[i] = (byte)(slope * mover + -this.strength);
			mover++;
		}
		return output;
	}
}
