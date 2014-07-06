package org.freecode.paradigmirc.j2d;

import java.awt.*;

/**
 * Created by mlaux on 7/6/14.
 */
public interface UiPanel {
	public int getLayoutParams();

	public int getPreferredWidth();

	public int getPreferredHeight();

	public void draw(Graphics2D g, int x, int y, int w, int h);
}
