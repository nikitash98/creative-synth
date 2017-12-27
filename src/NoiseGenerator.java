import java.util.Random;

public class NoiseGenerator extends SoundGenerator {
	NoiseGenerator(){
		type = "Noise";
	}
	public byte [] createBuffer(int length){
		int samples = (int)((length * SAMPLING_RATE));
		byte [] output = new byte [samples];
		Random rand = new Random();
		for(int i = 0; i < output.length; i++){
			output[i] = (byte) (rand.nextInt(this.strength * 2) - this.strength);
		}
		return output;
	}
	public byte [] createBuffer(byte[] entry){
		Random rand = new Random();
		for(int i = 0; i < entry.length; i++){
			entry[i] = (byte)(rand.nextInt(strength * 2) - strength + entry[i]);
		}
		return entry;
	}
}
