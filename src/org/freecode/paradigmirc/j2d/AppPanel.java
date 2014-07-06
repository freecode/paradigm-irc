package org.freecode.paradigmirc.j2d;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.ArrayList;

/**
 * Created by mlaux on 7/5/14.
 */
public class AppPanel extends JPanel {
	private static final int DEFAULT_WIDTH = 1000;
	private static final int DEFAULT_HEIGHT = 600;

	private List<UiPanel> panels = new ArrayList<UiPanel>();

	public AppPanel() {
		setDoubleBuffered(true);
		setPreferredSize(new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT));
		panels.add(new TitlePanel());
	}

	public void paintComponent(Graphics _g) {
		Graphics2D g = (Graphics2D) _g;
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g.setColor(Colors.LIGHT_BACKGROUND);
		g.fillRect(0, 0, getWidth(), getHeight());

		for(UiPanel panel : panels) {
			int params = panel.getLayoutParams();
			int w = panel.getPreferredWidth();
			int h = panel.getPreferredHeight();
			int x = 0, y = 0;

			if((params & LayoutParams.FILL_HORIZONTAL) != 0) {
				w = getWidth();
			}
			if((params & LayoutParams.FILL_VERTICAL) != 0) {
				h = getHeight();
			}

			if((params & LayoutParams.ANCHOR_TOP) != 0) {
				y = 0;
			}
			if((params & LayoutParams.ANCHOR_LEFT) != 0) {
				x = 0;
			}
			if((params & LayoutParams.ANCHOR_BOTTOM) != 0) {
				y = getHeight() - h;
			}
			if((params & LayoutParams.ANCHOR_RIGHT) != 0) {
				x = getWidth() - w;
			}
			panel.draw(g, x, y, w, h);
		}
	}
}
