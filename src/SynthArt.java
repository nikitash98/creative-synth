import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSlider;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import java.awt.event.ActionListener;
import java.util.Vector;
import java.awt.event.ActionEvent;
public class SynthArt {
	protected static final int SAMPLING_RATE = 44100; //Sampling rate of audio

	public static void main(String[] args) throws LineUnavailableException{
		Vector<SoundGenerator> soundgens = new Vector<SoundGenerator>();
		String[] options = {"Sin", "Saw", "Square", "Noise"};
		JComboBox soundlist = new JComboBox(options);
		
		final AudioFormat af = new AudioFormat(SAMPLING_RATE, 8, 1, true, true); // Get audio format
		SourceDataLine line = AudioSystem.getSourceDataLine(af);
		
		JFrame frame = new JFrame("The Synth!");
		JSlider length = new JSlider(JSlider.HORIZONTAL, 0, 2, 1);

	    JButton addnew = new JButton("Add");
	    addnew.addActionListener(new ActionListener(){
	    	public void actionPerformed(ActionEvent e){
	    		int selectedInd = soundlist.getSelectedIndex();
	    		if(selectedInd == 0){
		    		soundgens.add(new SinGenerator());
	    		} else if(selectedInd == 1){
	    			soundgens.add(new SawGenerator());
	    		} else if(selectedInd == 2){
	    			soundgens.add(new SquareGenerator());
	    		} else if(selectedInd == 3){
	    			soundgens.add(new NoiseGenerator());
	    		}
	    		JPanel singen = soundgens.lastElement().drawPanel();
	    		frame.add(singen);
	    		frame.revalidate();
	    		frame.repaint();
	    	}
	    });
	    
	    
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
				System.out.println(length.getValue());	
				byte[] entry = soundgens.lastElement().createBuffer(1);
				line.write(entry, 0, entry.length);
				line.drain();
				line.close();

			}
		});
		
		length.setMajorTickSpacing(100);
		length.setPaintTicks(true);
		frame.getContentPane().setLayout(new FlowLayout());
		frame.add(addnew);
		frame.add(soundlist);
		frame.add(length);
		frame.add(Play);
		frame.pack();
		frame.setSize(400,400);
		frame.setVisible(true);
	}

	
}
