import java.awt.Canvas;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.util.Arrays;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JRadioButton;
import javax.swing.JSlider;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Random;
public class SynthArt {
	protected static final int SAMPLING_RATE = 44100; //Sampling rate of audio
	protected static final int SAMPLE_SIZE = 2; //Sample size of audio
	public static void main(String[] args) throws LineUnavailableException{
		
		final AudioFormat af = new AudioFormat(SAMPLING_RATE, 8, 1, true, true); // Get audio format
		SourceDataLine line = AudioSystem.getSourceDataLine(af);
		boolean forwardNotBack = true;
		JFrame frame = new JFrame("The Synth!");
		JSlider frequency = new JSlider(JSlider.HORIZONTAL, 0, 1000, 500);
		
		JRadioButton sinButton = new JRadioButton("Sin");
	    sinButton.setSelected(true);
	    JRadioButton sawButton = new JRadioButton("Saw");

	    JRadioButton squareButton = new JRadioButton("Square");

	    JRadioButton noiseButton = new JRadioButton("Noise");
	    
	    ButtonGroup group = new ButtonGroup();
	    group.add(sinButton);
	    group.add(sawButton);
	    group.add(noiseButton);
	    group.add(squareButton);
	    
	    
		JButton Play = new JButton("PLAY");
		Play.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e){
				try {
					line.open(af, SAMPLING_RATE);
				} catch (LineUnavailableException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				line.start();
				System.out.println(frequency.getValue());
				byte[] entry = createSinWaveBuffer(frequency.getValue(), 1);
				//byte[] entry = createNoiseWaveBuffer( 5);
				line.write(entry, 0, entry.length);
				line.drain();
				line.close();

			}
		});
		frequency.setMajorTickSpacing(100);
		frequency.setPaintTicks(true);
		frame.getContentPane().setLayout(new FlowLayout());
		
		 frame.getContentPane().add(sinButton);
		 frame.getContentPane().add(sawButton);
		 frame.getContentPane().add(noiseButton);
		 frame.getContentPane().add(squareButton);
		frame.add(frequency);
		frame.add(Play);
		frame.pack();
		frame.setSize(400,400);
		frame.setVisible(true);
		
		
		
	}
	//Creates a byte array of sin wave sound
	private static byte[] createSinWaveBuffer(double freq, int length) {
	       int samples = (int)((length * SAMPLING_RATE));
	       byte[] output = new byte[samples];
	           //
	       double period = (double)SAMPLING_RATE / freq;
	       for (int i = 0; i < output.length; i++) {
	    	   output[i] = (byte)(127f * Math.sin(2 * Math.PI * i / (period)));
	       }

	       return output;
	}
	//Creates a byte array of square wave sound
	private static byte [] createSquareWaveBuffer(double freq, int length){
		int samples = (int)((length * SAMPLING_RATE));
		byte [] output = new byte [samples];
		double period = (double)SAMPLING_RATE/freq;
		int flipper = 1;
		int checker = 0;

		for(int i = 0; i < output.length; i++){
			if(checker > period/2){
				flipper *= -1;
				checker = 0;
			} 
			checker++;
			output[i] = (byte)(127f * flipper);
		}
		return output;
	}
	//Creates an array of saw wave sound
	private static byte [] createSawWaveBuffer(double freq, int length){
		int samples = (int)((length * SAMPLING_RATE));
		byte [] output = new byte [samples];
		double period = (double)SAMPLING_RATE/freq;
		double slope = (127 * 2)/period;
		int mover = 0;

		for(int i = 0; i < output.length; i++){
			if(slope * mover == 127f){
				mover = 0;
			} 
			
			output[i] = (byte)(slope * mover + -127f);
			mover++;
		}
		return output;
	}
	private static byte [] createNoiseWaveBuffer(int length){
		int samples = (int)((length * SAMPLING_RATE));
		byte [] output = new byte [samples];
		Random rand = new Random();
		for(int i = 0; i < output.length; i++){
			output[i] = (byte) (rand.nextInt(254) - 127);
		}
		return output;
	}
	
}
