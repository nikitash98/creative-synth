import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;

import javax.swing.event.ChangeListener;

public class SinGenerator extends SoundGenerator {

	//Creates a byte array of sin wave sound
	public byte[] createBuffer(int length) {
	       int samples = (int)((length * SAMPLING_RATE));
	       byte[] output = new byte[samples];
	       double period = (double)SAMPLING_RATE / frequency;
	       for (int i = 0; i < output.length; i++) {
	    	   output[i] = (byte)(127f * Math.sin(2 * Math.PI * i / (period)));
	       }

	       return output;
	}

}
