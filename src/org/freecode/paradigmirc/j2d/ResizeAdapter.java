package org.freecode.paradigmirc.j2d;

import javax.swing.*;
import javax.swing.event.MouseInputAdapter;
import java.awt.*;
import java.awt.event.MouseEvent;

/**
* Created by mlaux on 7/5/14.
*/
class ResizeAdapter extends MouseInputAdapter {
	private static final int HOTSPOT_SIZE = 15;

	private static final int DRAG_NONE = 0;
	private static final int DRAG_E = 1;
	private static final int DRAG_SE = 2;
	private static final int DRAG_S = 3;
	private static final int DRAG_SW = 4;
	private static final int DRAG_W = 5;
	private static final int DRAG_NW = 6;
	private static final int DRAG_N = 7;
	private static final int DRAG_NE = 8;

	private static final Cursor[] CURSORS = {
		new Cursor(Cursor.DEFAULT_CURSOR),
		new Cursor(Cursor.E_RESIZE_CURSOR),
		new Cursor(Cursor.SE_RESIZE_CURSOR),
		new Cursor(Cursor.S_RESIZE_CURSOR),
		new Cursor(Cursor.SW_RESIZE_CURSOR),
		new Cursor(Cursor.W_RESIZE_CURSOR),
		new Cursor(Cursor.NW_RESIZE_CURSOR),
		new Cursor(Cursor.N_RESIZE_CURSOR),
		new Cursor(Cursor.NE_RESIZE_CURSOR)
	};

	private JFrame frame;
	private int dragMode;
	private int frameX;
	private int frameY;
	private int frameW;
	private int frameH;

	public ResizeAdapter(JFrame frame) { this.frame = frame; }

	@Override
	public void mousePressed(MouseEvent e) {
		super.mousePressed(e);
		frameX = frame.getX();
		frameY = frame.getY();
		frameW = frame.getWidth();
		frameH = frame.getHeight();
		updateCursor(e);
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		int newLeft = frameX;
		int newTop = frameY;
		int newRight = newLeft + frameW;
		int newBottom = newTop + frameH;

		switch(dragMode) {
			case DRAG_E:
				newRight = e.getXOnScreen();
				break;
			case DRAG_SE:
				newRight = e.getXOnScreen();
				newBottom = e.getYOnScreen();
				break;
			case DRAG_S:
				newBottom = e.getYOnScreen();
				break;
			case DRAG_SW:
				newLeft = e.getXOnScreen();
				newBottom = e.getYOnScreen();
				break;
			case DRAG_W:
				newLeft = e.getXOnScreen();
				break;
			case DRAG_NW:
				newLeft = e.getXOnScreen();
				newTop = e.getYOnScreen();
				break;
			case DRAG_N:
				newTop = e.getYOnScreen();
				break;
			case DRAG_NE:
				newRight = e.getXOnScreen();
				newTop = e.getYOnScreen();
				break;
		}

		frameX = newLeft;
		frameY = newTop;
		frameW = newRight - newLeft;
		frameH = newBottom - newTop;
		frame.setBounds(frameX, frameY, frameW, frameH);
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		dragMode = DRAG_NONE;
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		updateCursor(e);
	}

	private void updateCursor(MouseEvent e) {
		int newMode = getDragMode(e.getX(), e.getY());
		if(frame.getRootPane().getCursor().getType() != CURSORS[newMode].getType()) {
			frame.getRootPane().setCursor(CURSORS[newMode]);
		}
		dragMode = newMode;
	}

	private int getDragMode(int x, int y) {
		if(x > frameW - HOTSPOT_SIZE && x < frameW + HOTSPOT_SIZE) {
			// right edge
			if(y > frameH - HOTSPOT_SIZE && y < frameH + HOTSPOT_SIZE) {
				return DRAG_SE;
			} else if(y > -HOTSPOT_SIZE && y < HOTSPOT_SIZE) {
				return DRAG_NE;
			} else {
				return DRAG_E;
			}
		} else if(x > -HOTSPOT_SIZE && x < HOTSPOT_SIZE) {
			// left edge
			if(y > frameH - HOTSPOT_SIZE && y < frameH + HOTSPOT_SIZE) {
				return DRAG_SW;
			} else if(y > -HOTSPOT_SIZE && y < HOTSPOT_SIZE) {
				return DRAG_NW;
			} else {
				return DRAG_W;
			}
		} else {
			// middle, check top and bottom
			if(y > frameH - HOTSPOT_SIZE && y < frameH + HOTSPOT_SIZE) {
				return DRAG_S;
			} else if(y > -HOTSPOT_SIZE && y < HOTSPOT_SIZE) {
				return DRAG_N;
			}
		}
		return DRAG_NONE;
	}
}
