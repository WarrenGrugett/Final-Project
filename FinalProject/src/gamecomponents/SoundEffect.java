package gamecomponents;
import java.io.*;
import javax.sound.sampled.*;

/**
 * 
 * @author Leo
 *
 */
public class SoundEffect {
	private SourceDataLine line = null;
	private byte[] audioBytes;
	private int numBytes;

	/**
	 * Takes in the filename of a sound byte and initializes an AudioInputStream
	 * 
	 * @param fileName
	 *            of Sound byte
	 */
	public SoundEffect(String fileName) {
		try {
			File soundFile = new File(fileName);
			AudioInputStream audioInputStream = null;
			try {
				audioInputStream = AudioSystem.getAudioInputStream(soundFile);
			} catch (Exception ex) {
			}
			AudioFormat audioFormat = audioInputStream.getFormat();
			DataLine.Info info = new DataLine.Info(SourceDataLine.class, audioFormat);
			try {
				line = (SourceDataLine) AudioSystem.getLine(info);
				line.open(audioFormat);
			} catch (LineUnavailableException ex) {
				System.out.println("lol");
			}
			line.start();
			audioBytes = new byte[(int) soundFile.length()];
			try {
				numBytes = audioInputStream.read(audioBytes, 0, audioBytes.length);
			} catch (IOException ex) {
			}
		} catch (Exception e) {

		}
	}

	/**
	 * Plays the soundByte
	 */
	public void play() {
		line.write(audioBytes, 0, numBytes);
	}
}