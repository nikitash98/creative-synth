import java.util.Random;

public class NoiseGenerator extends SoundGenerator {
	public byte [] createBuffer(int length){
		int samples = (int)((length * SAMPLING_RATE));
		byte [] output = new byte [samples];
		Random rand = new Random();
		for(int i = 0; i < output.length; i++){
			output[i] = (byte) (rand.nextInt(254) - 127);
		}
		return output;
	}
	public static byte [] addNoise(byte[] entry, int strength){
		Random rand = new Random();
		for(int i = 0; i < entry.length; i++){
			entry[i] = (byte)(rand.nextInt(strength * 2) - strength + entry[i]);
		}
		return entry;
	}
}
