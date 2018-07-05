package HexSlider;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;

/**
 * Generates the main GUI of the program
 */
public class Communication extends JFrame {

	private static final long serialVersionUID = 1375138048172866876L;
	private Camera camera;
	private JTextArea textarea_message;
	private JButton button_send;

	/**
	 * Constructs the present class
	 */
	public Communication() {
		super("Matthew Damon on Mars");

		setLayout(new BorderLayout());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Camera
		initialiseComponents();

		add(camera, BorderLayout.NORTH);
		add(textarea_message, BorderLayout.CENTER);
		add(button_send, BorderLayout.SOUTH);

		pack();
		setResizable(false);
		setVisible(true);

	}


	// Initialises all needed UI components
	private void initialiseComponents(){
		camera = new Camera(this);

		// Text Area
		textarea_message = new JTextArea(20, 40);

		textarea_message.setMaximumSize(new Dimension(800, 230));

		// Button
		button_send = new JButton("Send");
		button_send.addActionListener(new ActionListener() {

			/**
			 * Retrieves the content of textarea_message and processes it to
			 * then pass to the JSpinner
			 */
			@Override
			public void actionPerformed(ActionEvent e) {
				sendMessage(textarea_message.getText());
				camera.startSliderThread();

				textarea_message.setText("");
			}
		});


	}



	/**
	 * Sends a message to be added to the queue used for outputting messages
	 *
	 * @param msg
	 */
	public void sendMessage(String msg) {
		if (msg != null) {
			camera.addToQueue(msg);
		}
	}

}
