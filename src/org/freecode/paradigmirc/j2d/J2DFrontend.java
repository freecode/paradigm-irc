package org.freecode.paradigmirc.j2d;

import javax.swing.*;
import javax.swing.border.LineBorder;

/**
 * Created by mlaux on 7/5/14.
 */
public class J2DFrontend {
	public static final String APP_NAME = "paradigm irc";

	public static final String RESOURCES_PATH = "resources/";

	private static void startUI() {
		AppPanel panel = new AppPanel();

		JFrame frame = new JFrame(APP_NAME);
		frame.setUndecorated(true);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		ResizeAdapter ra = new ResizeAdapter(frame);
		frame.getRootPane().addMouseListener(ra);
		frame.getRootPane().addMouseMotionListener(ra);
		frame.getRootPane().setBorder(new LineBorder(Colors.BORDER, 2));

		frame.add(panel);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				startUI();
			}
		});
	}
}
