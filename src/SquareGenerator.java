import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;

public class SquareGenerator extends SoundGenerator {


	//Creates a byte array of square wave sound
	public byte [] createBuffer(int length){
		int samples = (int)((length * SAMPLING_RATE));
		byte [] output = new byte [samples];
		double period = (double)SAMPLING_RATE/frequency;
		int flipper = 1;
		int checker = 0;

		for(int i = 0; i < output.length; i++){
			if(checker > period/2){
				flipper *= -1;
				checker = 0;
			} 
			checker++;
			output[i] = (byte)(this.strength * flipper);
		}
		return output;
	}
}
