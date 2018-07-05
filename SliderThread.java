package HexSlider;

import java.util.LinkedList;

/**
 * The class serves to run a thread which is needed to change the value of the
 * slider and at the same time preventing the GUI from freezing.
 */
public class SliderThread implements Runnable {
	private Camera camera;
	private LinkedList<String> output;
	private Thread thread;

	public SliderThread(Camera camera) {
		thread = new Thread(this, "Slider movement thread");
		camera = this.camera;
		output = camera.getOutput();
		thread.start();
	}

	/**
	 * It controls the functioning of the slider creating visible interactivity
	 * between the user and the program
	 */
	@Override
	public void run() {
		try {
			while (true) {
				if (!output.isEmpty()) {
					camera.setFrameTitle("Sending...");
					camera.displayOnSlider(output.pop());
				} else {
					camera.setFrameTitle("Matthew Damon on Mars");
				}
				Thread.sleep(1000);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
