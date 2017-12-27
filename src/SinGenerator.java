import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;

import javax.swing.event.ChangeListener;

public class SinGenerator extends SoundGenerator {
	SinGenerator(){
		type = "Sin";
	}
	//Creates a byte array of sin wave sound
	public byte[] createBuffer(int length) {
	       int samples = (int)((length * SAMPLING_RATE));
	       byte[] output = new byte[samples];
	       double period = (double)SAMPLING_RATE / frequency;
	       for (int i = 0; i < output.length; i++) {
	    	   output[i] = (byte)(strength * Math.sin(2 * Math.PI * i / (period)));
	       }

	       return output;
	}
	//Creates a byte array of sin wave sound
	public byte[] createBuffer(byte[] thing) {
	       double period = (double)SAMPLING_RATE / frequency;
	       for (int i = 0; i < thing.length; i++) {
	    	   thing[i] = (byte)(thing[i] + strength * Math.sin(2 * Math.PI * i / (period)));
	       }

	       return thing;
	}

}
