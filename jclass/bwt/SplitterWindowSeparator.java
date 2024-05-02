/*****************************************************************************
 *
 * (c) Copyright 1997-2000, SITRAKA INC.  
 * ALL RIGHTS RESERVED 
 *
 *  THIS SOFTWARE IS FURNISHED UNDER A LICENSE AND MAY BE USED
 * AND COPIED ONLY IN ACCORDANCE WITH THE TERMS OF SUCH LICENSE AND
 * WITH THE INCLUSION OF THE ABOVE COPYRIGHT NOTICE.  THIS SOFTWARE OR
 * ANY OTHER COPIES THEREOF MAY NOT BE PROVIDED OR OTHERWISE MADE
 * AVAILABLE TO ANY OTHER PERSON.  NO TITLE TO AND OWNERSHIP OF THE
 * SOFTWARE IS HEREBY TRANSFERRED.
 *
 *  THE INFORMATION IN THIS SOFTWARE IS SUBJECT TO CHANGE WITHOUT
 * NOTICE AND SHOULD NOT BE CONSTRUED AS A COMMITMENT BY SITRAKA INC.
 * OR ITS THIRD PARTY SUPPLIERS.
 *
 *  SITRAKA INC. AND ITS THIRD PARTY SUPPLIERS, ASSUME NO RESPONSIBILITY 
 * FOR THE USE OR INABILITY TO USE ANY OF ITS SOFTWARE.   THIS SOFTWARE IS 
 * PROVIDED "AS IS" WITHOUT WARRANTY OF ANY KIND, AND SITRAKA INC. EXPRESSLY 
 * DISCLAIMS ALL IMPLIED WARRANTIES, INCLUDING BUT NOT LIMITED TO THE 
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE.
 *
 * Notice:  Notwithstanding any other lease or license that may pertain to,
 * or accompany the delivery of, this computer software, the rights of the
 * Government regarding its use, reproduction and disclosure are as set
 * forth in Section 52.227-19 of the FARS Computer Software-Restricted
 * Rights clause.
 *
 * (c) Copyright 1997-2000 Sitraka Inc.  Unpublished - all
 * rights reserved under the Copyright laws of the United States.
 *
 * RESTRICTED RIGHTS NOTICE:  Use, duplication, or disclosure by the
 * Government is subject to the restrictions as set forth in subparagraph
 * (c)(1)(ii) of the Rights in Technical Data and Computer Software clause
 * at DFARS 52.227-7013.
 *
 * Sitraka Inc.
 * 260 King Street East
 * Toronto, Ontario
 * Canada M5A 1K3
 * (416) 594-1026
 * (416) 594-1919 (FAX)
 *
 * RESTRICTED RIGHTS LEGEND:  This computer software is submitted with
 * "restricted rights."  Use, duplication or disclosure is subject to the
 * restrictions as set forth in NASA FAR SUP 18-52.227-79 (April 1985)
 * "Commercial Computer Software- Restricted Rights (April 1985)."  Sitraka
 * Inc., 300-260 King Street E., Toronto, Ontario, Canada M5A 1K3.  If
 * the contract contains the Clause at 18-52.227-74 "Rights in Data General"
 * then the "Alternate III" clause applies.
 *
 * (c) Copyright 1997-2000 Sitraka Inc.
 * ALL RIGHTS RESERVED
 *
 *****************************************************************************/
// RCSID -- $RCSfile: SplitterWindowSeparator.java $ $Revision: 2.13 $ $Date: 2000/11/09 20:11:48 $ $Locker: $  Sitraka Inc.

package jclass.bwt;
import jclass.util.*;
import java.applet.*;
import java.awt.*;
/* JDK110_START */
import java.awt.event.*;
/* JDK110_END */

/** A private class used by JCSplitterWindow for its separator.
 * @see JCSplitterWindow
 */
class SplitterWindowSeparator extends JCSeparator 
/* JDK110_START */
implements MouseListener, MouseMotionListener
/* JDK110_END */
{

boolean dragging = false;
JCSplitterWindow window;
Component child1, child2;
int dx, dy, pos;
static final int SIZE = 4;		// width/height

SplitterWindowSeparator(JCSplitterWindow window) {
	// set the orientation of the separator to be opposite to that
	// of the splitter window
	super((window.dir == BWTEnum.HORIZONTAL) ? 
		BWTEnum.VERTICAL : BWTEnum.HORIZONTAL, window.applet, window.getName());
	this.window = window;
	traversable = JCEnvironment.getJavaVersion() < 110;	// JDK 1.0.2 bug
	border_style = jclass.base.Border.CONTROL_OUT;
	border = 1;
	/* JDK110_START */
	enableEvents(AWTEvent.MOUSE_MOTION_EVENT_MASK);
	/* JDK110_END */
}

void setSplitterWindow(JCSplitterWindow window) {
	dir = (window.dir == BWTEnum.HORIZONTAL) ? 
		BWTEnum.VERTICAL : BWTEnum.HORIZONTAL;
}

/** Draws a shadow centered horizontally or vertically, depending on
 * the orientation.
 */
public void paint(Graphics gc) {
	gc.setColor(getBackground());
	gc.fillRect(0, 0, size().width, size().height);
	//drawHighlightAndShadow(gc);

	gc.setColor(Color.white);
	if (dir == VERTICAL) 
		gc.drawLine(0, 0, 0, size().height);
	else
		gc.drawLine(0, 0, size().width, 0);

	gc.setColor(Color.black);
	if (dir == VERTICAL) 
		gc.drawLine(size().width, 0, size().width, size().height);
	else
		gc.drawLine(0, size().height, size().width, size().height);
	gc.setColor(getBackground());
}

protected int preferredWidth() {
	return (dir == VERTICAL) ? SIZE : 100;
}

protected int preferredHeight() {
	return (dir == HORIZONTAL) ? SIZE : 100;
}

public boolean mouseEnter(Event ev, int x, int y) {
	return mouseMove(ev, x, y);
}

public boolean mouseMove(Event ev, int x, int y) {
	if (!dragging && getDrawingArea().inside(x,y))
		setCursor(dir == BWTEnum.VERTICAL ? 
				  Frame.W_RESIZE_CURSOR : Frame.N_RESIZE_CURSOR);
	else
		setCursor(Frame.DEFAULT_CURSOR);
	return true;
}

/** Tracks the cursor. */
public boolean mouseExit(Event ev, int x, int y) {
	if (!dragging) 
		setCursor(Frame.DEFAULT_CURSOR);
	return true;
}

/** Begins movement of the separator if mouse is inside the shadow area. */
public boolean mouseDown(Event ev, int x, int y) {
	if (!getDrawingArea().inside(x,y))
		return true;
	pos = window.getComponent(this);
	if (pos <= 0 || pos == window.countComponents()-1) return false;

	child1 = window.getComponents()[pos-1];
	child2 = window.getComponents()[pos+1];

	/* JDK110_START */
	// Add listeners to children, due to JDK 1.1.5 bug which causes mouseDrag
	// events to be passed to children instead of seperator
	addMouseMotionListeners(this, child1);
	addMouseMotionListeners(this, child2);
	addMouseListeners(this, child1);
	addMouseListeners(this, child2);
	/* JDK110_END */

	dragging = true;
	dx = dy = 0;
	drawLine(false, ev);
	return true;
}

/* JDK110_START */
Event translate(MouseEvent mouse_ev) {
	Event ev = new Event(this, mouse_ev.getWhen(), mouse_ev.getID(), 
				  mouse_ev.getX(), mouse_ev.getY(), 0, mouse_ev.getModifiers());
	Point source_loc = ((Component)mouse_ev.getSource()).getLocationOnScreen();
	Point loc = getLocationOnScreen();
	ev.translate(source_loc.x - loc.x, source_loc.y - loc.y);
	return ev;
}

public void mouseDragged(MouseEvent mouse_ev) {
	Event ev = translate(mouse_ev);
	mouseDrag(ev, ev.x, ev.y);
}

public void mouseReleased(MouseEvent mouse_ev) {
	Event ev = translate(mouse_ev);
	mouseUp(ev, ev.x, ev.y);
}

public void mouseClicked(MouseEvent e) {}
public void mousePressed(MouseEvent e) {}
public void mouseEntered(MouseEvent e) {}
public void mouseExited(MouseEvent e) {}
public void mouseMoved(MouseEvent ev) {}

static void 
addMouseListeners(MouseListener listener, Component parent) {
	if (parent != listener)
		parent.addMouseListener(listener);
	if (parent instanceof Container) {
		Container c = (Container) parent;
		for (int i=0; i < c.countComponents(); i++)
			addMouseListeners(listener, c.getComponent(i));
	}
}

static void 
addMouseMotionListeners(MouseMotionListener listener, Component parent) {
	if (parent != listener)
		parent.addMouseMotionListener(listener);
	if (parent instanceof Container) {
		Container c = (Container) parent;
		for (int i=0; i < c.countComponents(); i++)
			addMouseMotionListeners(listener, c.getComponent(i));
	}
}

static void 
removeMouseListeners(MouseListener listener, Component parent) {
	if (parent != listener)
		parent.removeMouseListener(listener);
	if (parent instanceof Container) {
		Container c = (Container) parent;
		for (int i=0; i < c.countComponents(); i++)
			removeMouseListeners(listener, c.getComponent(i));
	}
}

static void 
removeMouseMotionListeners(MouseMotionListener listener, Component parent) {
	if (parent != listener)
		parent.removeMouseMotionListener(listener);
	if (parent instanceof Container) {
		Container c = (Container) parent;
		for (int i=0; i < c.countComponents(); i++)
			removeMouseMotionListeners(listener, c.getComponent(i));
	}
}
/* JDK110_END */

static long last_time;

/** Moves the separator. */
public boolean mouseDrag(Event ev, int x, int y) {
	if (!dragging) return false;
	boolean in_browser = 
		JCEnvironment.getBrowser(this) != JCEnvironment.BROWSER_INTERPRETER;

	// Filter the events if in a slow browser
	if (in_browser && ev.when - last_time < 50) return true;
	last_time = ev.when;

	int dx = (dir == BWTEnum.VERTICAL) ? ev.x : 0,
		dy = (dir == BWTEnum.VERTICAL) ? 0 : ev.y;

	// Check that one of the children would not be resized too small
	if ((dx < 0 && child1.size().width + dx < window.min_child_size)
		|| (dx > 0 && child2.size().width - dx < window.min_child_size)
		|| (dy < 0 && child1.size().height + dy < window.min_child_size)
		|| (dy > 0 && child2.size().height - dy < window.min_child_size)) {
		return true;
	}

	this.dx = dx; this.dy = dy;
	drawLine(true, null);
	drawLine(false, ev);
	return true;
}

/** Ends movement of the separator. */
public boolean mouseUp(Event ev, int x, int y) {
	setCursor(Frame.DEFAULT_CURSOR);
	if (!dragging) return false;
	drawLine(true, null);

	if (dx != 0 || dy != 0) {
		window.resized = true;	// Must be set before children are resized
		child1.resize(child1.size().width + dx, child1.size().height + dy);
		move(location().x + dx, location().y + dy);

		x = (dx == 0) ? child2.location().x : location().x + size().width + 1;
		y = (dy == 0) ? child2.location().y : location().y + size().height + 1;
		JCComponent.setBounds(child2, x, y, child2.size().width - dx, 
							child2.size().height - dy);
	}

/* JDK110_START */
	removeMouseMotionListeners(this, child1);
	removeMouseMotionListeners(this, child2);
	removeMouseListeners(this, child1);
	removeMouseListeners(this, child2);
/* JDK110_END */

	dragging = false;
	updateParent();
	return true;
}

void drawLine(boolean clear, Event ev) {
	drawLine(clear, ev, window);
}

private static int x_save = BWTEnum.NOVALUE, y_save =  BWTEnum.NOVALUE;
private static boolean version102 = JCEnvironment.getJavaVersion() <= 102;

/**
 * Draw temporary dashed line in all of parent's children while dragging.
 * @param clear If true, the previously-drawn line is erased (and the 
 * remaining arguments are ignored).
 */
void drawLine(boolean clear, Event ev, Container parent) {
	// Drawing the line again at the same position erases it
	if (!clear && parent == window) {
		x_save = ev.x + location().x;
		y_save = ev.y + location().y;
	}
	else if (x_save == BWTEnum.NOVALUE) 
		return;

	for (int i=0; i < parent.countComponents(); i++) {
		Component child = parent.getComponents()[i];
		if (BWTUtil.instanceOf(child, "Scrollbar"))
			continue;
		if (child instanceof Container)
			drawLine(clear, ev, (Container) child);
		if (version102 && !(child instanceof Canvas))
			continue;

		Point p = BWTUtil.translateToParent(window, child, 0, 0);
		Graphics gc = child.getGraphics();
		if (gc == null) continue;
		gc.setXORMode(child.getBackground());
	
		if (dir == BWTEnum.HORIZONTAL) {
			int y = y_save - p.y, w = child.size().width;
			for (int k=0; k < SIZE; k++)
				gc.drawLine(0, y+k, w, y+k);
		}
		else {
			int x = x_save - p.x, h = child.size().height;
			for (int k=0; k < SIZE; k++)
				gc.drawLine(x+k, 0, x+k, h);
		}

		gc.dispose();
	}
}

}
