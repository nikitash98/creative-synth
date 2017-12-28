import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSlider;
import java.util.Iterator;
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
		frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.PAGE_AXIS));
		JSlider length = new JSlider(JSlider.HORIZONTAL, 0, 2, 1);
		DrawBuffer canvas = new DrawBuffer();

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
	    		
	    		soundgens.lastElement().drawPanel(frame);
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
				byte[] entry = new byte[SAMPLING_RATE * length.getValue()];
				//Deletion Script//
				for (int x = soundgens.size()-1; x >= 0; x--){
					if(soundgens.elementAt(x).deleted == 1){
						soundgens.remove(x);
					}
				}
				Iterator<SoundGenerator> it = soundgens.iterator();

				while(it.hasNext()){
					entry = it.next().createBuffer(entry);
				}
				canvas.setBuffer(entry);
				frame.revalidate();
				frame.repaint();
				line.write(entry, 0, entry.length);
				line.drain();
				line.close();

			}
		});
		length.setMajorTickSpacing(100);
		length.setPaintTicks(true);
		frame.getContentPane().add(addnew);
		frame.getContentPane().add(soundlist);
		frame.getContentPane().add(length);
		frame.getContentPane().add(Play);
		canvas.setPreferredSize(new Dimension(300, 300));
		frame.getContentPane().add(canvas);

		frame.pack();
		frame.setSize(400,400);
		frame.setVisible(true);
	}

	
}
