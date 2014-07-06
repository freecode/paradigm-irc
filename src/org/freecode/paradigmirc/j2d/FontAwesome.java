package org.freecode.paradigmirc.j2d;

import java.awt.*;
import java.io.File;

/**
 * Created by mlaux on 7/6/14.
 */
public class FontAwesome {
	public static final char ARROW_UP = '\uf077';
	public static final char ARROW_DOWN = '\uf078';
	public static final char EXIT = '\uf00d';

	private static final String FILENAME = "fontawesome.ttf";

	private static Font font;

	public static Font getFont() {
		if(font == null) {
			try {
				font = Font.createFont(Font.TRUETYPE_FONT, new File(J2DFrontend.RESOURCES_PATH + FILENAME));
				font = font.deriveFont(12.0f);
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		return font;
	}
}
