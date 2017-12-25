import java.util.Random;

public class BufferSource {
	
	protected static final int SAMPLING_RATE = 44100; //Sampling rate of audio
	protected static final int SAMPLE_SIZE = 2; //Sample size of audio

	//Creates a byte array of sin wave sound
	public static byte[] createSinWaveBuffer(double freq, int length) {
	       int samples = (int)((length * SAMPLING_RATE));
	       byte[] output = new byte[samples];
	       double period = (double)SAMPLING_RATE / freq;
	       for (int i = 0; i < output.length; i++) {
	    	   output[i] = (byte)(127f * Math.sin(2 * Math.PI * i / (period)));
	       }

	       return output;
	}
	//Creates a byte array of square wave sound
	public static byte [] createSquareWaveBuffer(double freq, int length){
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
	public static byte [] createSawWaveBuffer(double freq, int length){
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
	public static byte [] createNoiseWaveBuffer(int length){
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
