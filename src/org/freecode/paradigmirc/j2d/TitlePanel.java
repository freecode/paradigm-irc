package org.freecode.paradigmirc.j2d;

import java.awt.*;

/**
 * Created by mlaux on 7/6/14.
 */
public class TitlePanel implements UiPanel {
	@Override
	public int getLayoutParams() {
		return LayoutParams.ANCHOR_TOP | LayoutParams.FILL_HORIZONTAL;
	}

	@Override
	public int getPreferredWidth() {
		return -1;
	}

	@Override
	public int getPreferredHeight() {
		return 50;
	}

	@Override
	public void draw(Graphics2D g, int x, int y, int w, int h) {
		FontMetrics fm = g.getFontMetrics();
		g.setColor(Colors.BACKGROUND);
		g.fillRect(x, y, w, h - 3);
		g.setColor(Colors.SELECTED_TAB);
		g.fillRect(x, y + h - 3, w, 3);

		g.setColor(Colors.TITLE);
		int width = fm.stringWidth(J2DFrontend.APP_NAME);
		g.drawString(J2DFrontend.APP_NAME, x + w / 2 - width / 2, y + h / 2);
	}
}
