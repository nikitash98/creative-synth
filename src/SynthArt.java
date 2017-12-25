import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSlider;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
public class SynthArt {
	protected static final int SAMPLING_RATE = 44100; //Sampling rate of audio

	public static void main(String[] args) throws LineUnavailableException{
		
		final AudioFormat af = new AudioFormat(SAMPLING_RATE, 8, 1, true, true); // Get audio format
		SourceDataLine line = AudioSystem.getSourceDataLine(af);
		JFrame frame = new JFrame("The Synth!");
		JSlider frequency = new JSlider(JSlider.HORIZONTAL, 0, 1000, 500);
		JPanel singen = new SinGenerator();
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
				byte[] entry = BufferSource.createSinWaveBuffer(frequency.getValue(), 1);
				entry = BufferSource.addNoise(entry, 2);
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
		frame.add(singen);
		frame.pack();
		frame.setSize(400,400);
		frame.setVisible(true);
	}

	
}
