package HexSlider;

import java.util.Hashtable;
import java.util.LinkedList;

import javax.swing.JLabel;
import javax.swing.JSlider;

/**
 * This class creates a police man that holds a collection of evidence boxes
 */
public class Camera extends JSlider {

	private static final long serialVersionUID = 6977756596221274590L;
	private LinkedList<String> output;
	private Communication comm;
	private boolean threadStarted;

	public Camera(Communication c) {
		super(0, 15, 0);
		comm = c;
		threadStarted = false;
		output = new LinkedList<>();
		Hashtable<Integer, JLabel> table = new Hashtable<>();
		for(int i=0; i<16; ++i) {
				if(i>=10) {

					char character = (char)(i+55);
					String string = String.valueOf(character);
					table.put(new Integer(i), new JLabel(string));
					continue;
				}
				table.put(new Integer(i), new JLabel(String.valueOf(i)));
				
			}

		setPaintTicks(true);
		setLabelTable(table);
		setPaintLabels(true);
	}

	/**
	 * Adds individual letters of a string to a queue in the hex form
	 *
	 * @param s
	 */
	public void addToQueue(String s) {
		for (int i = 0; i < s.length(); i++) {
			String sub = s.substring(i, i + 1);
			if (sub.equals(" ")) {
				output.add("2");
				output.add("0");
				continue;
			} else if (sub.equals("\n")) {
				output.add("0");
				output.add("A");
				continue;
			}

			String letter = Start.toHex(sub);
			output.add(letter.substring(0, 1));
			output.add(letter.substring(1, 2));
		}

	}

	/**
	 * Converts a hex letter to a slider position
	 *
	 * @param s
	 * @return the numeric variable that is then assigned to the JSpinner
	 */
	public int toSliderPosition(String s) {
		int number = ((int)letter.charAt(0))-55;

		return number;
		// switch (s) {
		// case "1":
		// 	return 1;
		// case "2":
		// 	return 2;
		// case "3":
		// 	return 3;
		// case "4":
		// 	return 4;
		// case "5":
		// 	return 5;
		// case "6":
		// 	return 6;
		// case "7":
		// 	return 7;
		// case "8":
		// 	return 8;
		// case "9":
		// 	return 9;
		// case "A":
		// 	return 10;
		// case "B":
		// 	return 11;
		// case "C":
		// 	return 12;
		// case "D":
		// 	return 13;
		// case "E":
		// 	return 14;
		// case "F":
		// 	return 15;
		// }
		// return 0;
	}

	/**
	 * Moves the slider to the requested position
	 *
	 * @param s
	 */
	public void displayOnSlider(String s) {
		setValue(toSliderPosition(s));
	}

	/**
	 * Returns the queue containing letters to be outputted
	 *
	 * @return
	 */
	public LinkedList<String> getOutput() {
		return output;
	}

	/**
	 * Returns true if the slider thread started
	 *
	 * @return
	 */
	public boolean hasSliderThreadStarted() {
		return threadStarted;
	}

	/**
	 * Starts the slider thread
	 */
	public void startSliderThread() {
		if (threadStarted == false) {
			threadStarted = true;
			new SliderThread(this);
		}
	}

	/**
	 * Sets the title for the JFrame containing the slider
	 *
	 * @param s
	 */
	public void setFrameTitle(String s) {
		comm.setTitle(s);
	}

}
