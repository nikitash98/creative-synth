import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public abstract class SoundGenerator {
	int frequency;
	int strength;
	protected static final int SAMPLING_RATE = 44100; //Sampling rate of audio
	protected static final int SAMPLE_SIZE = 2; //Sample size of audio

	
	public JPanel drawPanel(){
		JPanel ret = new JPanel();
		ret.setLayout(new BoxLayout(ret, BoxLayout.PAGE_AXIS));
		JLabel label = new JLabel("Sin Generator!");
		JLabel freqlab = new JLabel("Frequency");
		JSlider frequency = new JSlider(JSlider.HORIZONTAL, 0, 1000, 500);
		frequency.setMajorTickSpacing(100);
		frequency.setPaintTicks(true);
		JLabel strlab = new JLabel("Strength");
		JSlider strength = new JSlider(JSlider.HORIZONTAL, 0, 127, 56);
		strength.setMajorTickSpacing(30);
		strength.setPaintTicks(true);
		setFrequency(frequency.getValue());
		setStrength(strength.getValue());
		frequency.addChangeListener(new ChangeListener(){
			@Override
			public void stateChanged(ChangeEvent e) {
				setFrequency(frequency.getValue());
			}
		});
		strength.addChangeListener(new ChangeListener(){

			@Override
			public void stateChanged(ChangeEvent e) {
				setStrength(strength.getValue());
			}
			
		});
		ret.add(label);
		ret.add(freqlab);
		ret.add(frequency);
		ret.add(strlab);
		ret.add(strength);
		return ret;
	}
	public abstract byte[] createBuffer(int j);
	public void setFrequency(int j){
		this.frequency = j;
	}
	public void setStrength(int j){
		this.strength = j;
	}
}
