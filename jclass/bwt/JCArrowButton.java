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

package jclass.bwt;
import jclass.util.*;
import java.applet.*;
import java.awt.*;

/**
 * <strong>Properties</strong><p>
 * <table border>
 * <tr><th>Name</th>    <th>Method</th></tr><tr><td>
 * ArrowSize           </td><td><a href=#setArrowSize>setArrowSize</a></td></tr><tr><td>
 * Background          </td><td><a href=jclass.bwt.JCComponent.html#setBackground>setBackground</a></td></tr><tr><td>
 * DoubleBuffer        </td><td><a href=jclass.bwt.JCComponent.html#setDoubleBuffer>setDoubleBuffer</a></td></tr><tr><td>
 * HighlightThickness  </td><td><a href=jclass.bwt.JCComponent.html#setHighlightThickness>setHighlightThickness</a></td></tr><tr><td>
 * InitialRepeatDelay  </td><td><a href=#setInitialRepeatDelay>setInitialRepeatDelay</a></td></tr><tr><td>
 * Insets              </td><td><a href=jclass.bwt.JCComponent.html#setInsets>setInsets</a></td></tr><tr><td>
 * Orientation         </td><td><a href=#setOrientation>setOrientation</a></td></tr><tr><td>
 * PreferredSize       </td><td><a href=jclass.bwt.JCComponent.html#setPreferredSize>setPreferredSize</a></td></tr><tr><td>
 * ShadowThickness  </td><td><a href=jclass.bwt.JCComponent.html#setShadowThickness>setShadowThickness</a></td></tr><tr><td>
 * Traversable         </td><td><a href=jclass.bwt.JCComponent.html#setTraversable>setTraversable</a></td></tr><tr><td>
 * UserData            </td><td><a href=jclass.bwt.JCComponent.html#setUserData>setUserData</a></td></tr>
 * </table><p>
 * The ShadowThickess is set to 1 and the HighlightThickness is set to 0.<p>
 *
 * <strong>Events</strong><p>
 * <table border><tr>
 * <th>Class</th> <th>Listener</th>   <th>Description</th></tr><tr><td>
 * JCActionEvent    </td><td><a href=jclass.bwt.JCButton.html#addActionListener>addActionListener</a></td> <td>Posted when the button is pressed and then released</td></tr><tr><td>
 * JCButtonEvent  </td><td><a href=jclass.bwt.JCButton.html#addButtonListener>addButtonListener</a></td> <td>Posted when the button is pressed and released</td></tr>
 */
public class JCArrowButton extends JCButton implements Runnable {

public static final int UP = BWTEnum.UP;
public static final int DOWN = BWTEnum.DOWN;
public static final int LEFT = BWTEnum.LEFT;
public static final int RIGHT = BWTEnum.RIGHT;

int orientation = DOWN;
Dimension arrow_size;
int initial_delay = BWTEnum.MAXINT;
transient Thread thread;
transient boolean thread_stop = false;
transient int thread_counter = 0;
int tn = 0;
Color border_color;				// 1-pixel border color
Color right_border_color;		// 1-pixel right-bottom border color

private static final String base = "arrowbutton";
private static int nameCounter = 0;
private static final boolean TRACE = false;

/** Creates a DOWN button. No parameters are read from an HTML file.
 * @see #setOrientation
 */
public JCArrowButton() {
	this(DOWN, null, null); 
}

/** Creates a button with the specified orientation.
 * No parameters are read from an HTML file. 
 * @param orientation UP, DOWN, LEFT, or RIGHT.
 * @exception IllegalArgumentException If an invalid orientation is set
 */
public JCArrowButton(int orientation) {
	this(orientation, null, null); 
}

/** Creates a button which reads parameters from the applet's HTML file.
 * @param orientation UP, DOWN, LEFT, or RIGHT.
 * @param applet the applet whose PARAM tags are to be read
 * @param name if this is not null, only parameters preceded by this name are read
 * @exception IllegalArgumentException If an invalid value is set
 * @see java.applet.Applet#getParameter
 */
public JCArrowButton(int orientation, Applet applet, String name) {
	super(null, applet, name);
	if (name == null)
		setName(base + nameCounter++);
	highlight = 0;
    border = 1;
	if (applet != null && getClass().getName().equals("jclass.bwt.JCArrowButton"))
		 getParameters(applet);
	setOrientation(orientation);
	if (JCEnvironment.getJavaVersion() >= 110)
		traversable = false;
	double_buffer = false;
	border_style = jclass.base.Border.OUT;
}

/** Reads the parameter values from the HTML page using the component's applet.
 * The values will override those previously set.
 */
protected void getParameters() {
	super.getParameters();
	ArrowButtonConverter.getParams(this);
}

/**
 * Sets the background color of the component.<p>
 * <strong>HTML param name/value:</strong> "Background"/Color
 * @see jclass.util.JCConverter#toColor
 */
public void setBackground(Color c) {
	super.setBackground(c);
}

/** Gets the direction of the arrow.
 * @see #setOrientation
 */
public int getOrientation() { return orientation; }

/** Sets the arrow's direction: UP, DOWN (default), LEFT, or RIGHT. 
 * @exception IllegalArgumentException If an invalid value is set
 */
public synchronized void setOrientation(int v) {
	ArrowButtonConverter.checkOrientation(v);
	orientation = v;
	if (v == UP || v == DOWN) {
		insets = new Insets(3,2,3,3);
		arrow_size = new Dimension(7,4);
	}
	else {
		arrow_size = new Dimension(4,7);
		insets = new Insets(2,3,3,3);
	}
	repaint();
}

/** Gets the arrow's size.
 * @see #setArrowSize
 */
public Dimension getArrowSize() { return arrow_size; }

/** Sets the arrow's size (default: 7x4 (UP/DOWN), 4x7 (LEFT/RIGHT)).
 */
public synchronized void setArrowSize(Dimension v) { 
	arrow_size = v; 
	repaint();
}

/** Gets the InitialRepeatDelay value.
 * @see #setInitialRepeatDelay
 */
public int getInitialRepeatDelay() { return initial_delay; }

/** Sets the time (in ms) to wait before sending continuous button press
 * events while the button is pressed and held down.
 * If set to BWTEnum.MAXINT (default),
 * events are not sent after the button is pressed.
 */
public void setInitialRepeatDelay(int v) { initial_delay = v; }

/** Returns ArrowSize width. */
protected int preferredWidth() { return arrow_size.width; }

/** Returns ArrowSize height. */
protected int preferredHeight() { return arrow_size.height; }

/** Don't draw highlight rectangle. */
protected void drawHighlight(Graphics gc, boolean on) {}

protected void drawBorder(Graphics gc) {
	super.drawBorder(gc);
/* SWING_START */
	if (getBorder() == null) {
/* SWING_END */
		if (border_color != null) {
			gc.setColor(border_color);
			gc.drawRect(0, 0, size().width-1, size().height-1);
		}
		if (right_border_color != null) 
			jclass.base.Border.drawBottomLines(gc, 1, 0, 0, size().width, 
											size().height, right_border_color);
/* SWING_START */
	}
/* SWING_END */
}

/**
 * An internal method used to handle repaint events.
 */
protected void paintComponent(Graphics gc) {

	int[] x = new int[3], y = new int[3];
	Rectangle rect = getDrawingArea();

	switch (orientation) {
	case UP:
		x[0] = arrow_size.width/2;
		y[0] = 0;
		x[1] = 0;
		y[1] = arrow_size.height-1;
		x[2] = arrow_size.width-1;
		y[2] = arrow_size.height-1;
		break;
	case DOWN:
		x[0] = arrow_size.width/2;
		y[0] = arrow_size.height-1;
		x[1] = 0;
		x[2] = arrow_size.width-1;
		y[1] = y[2] = 0;
		break;
	case LEFT:
		y[0] = arrow_size.height/2;
		x[0] = 0;
		y[1] = 0;
		y[2] = arrow_size.height-1;
		x[1] = x[2] = arrow_size.width-1;
		break;
	case RIGHT:
		x[0] = arrow_size.width-1;
		y[0] = arrow_size.height/2;
		x[1] = x[2] = 0;
		y[1] = 0;
		y[2] = arrow_size.height-1;
		break;
	}

	int x_offset = (rect.width - arrow_size.width)/2,
		y_offset = (rect.height - arrow_size.height)/2;
	for (int i=0; i < 3; i++) {
		x[i] += rect.x + x_offset;
		y[i] += rect.y + y_offset;
	}

	if (armed)
		gc.translate(arm_offset, arm_offset);

	if (!isEnabled())
		gc.setColor(Color.lightGray.darker());

	/*
	 * NOTE: Graphics.fillPolygon() should be used, but it is broken
	 */

	if (orientation == UP || orientation == DOWN) {
		int x1 = x[0], x2 = x[0], y1 = y[0];
		double dx1 = (y[1] != y[0]) ? (double)(x[1]-x[0])/(y[1]-y[0]) : 0;
		double dx2 = (y[2] != y[0]) ? (double)(x[2]-x[0])/(y[2]-y[0]) : 0;
		while (true) {
			gc.drawLine(x1, y1, x2, y1);
			if (y[1] > y[0]) {
				y1++;
				if (y1 > y[1]) break;
			}
			else {
				y1--;
				if (y1 < y[1]) break;
			}
			x1 = (int) ((y1-y[0]) * dx1 + x[0]);
			x2 = (int) ((y1-y[0]) * dx2 + x[0]);
		}
	}
	else {
		int y1 = y[0], y2 = y[0], x1 = x[0];
		double dy1 = (x[1] != x[0]) ? (double)(y[1]-y[0])/(x[1]-x[0]) : 0;
		double dy2 = (x[2] != x[0]) ? (double)(y[2]-y[0])/(x[2]-x[0]) : 0;
		while (true) {
			gc.drawLine(x1, y1, x1, y2);
			if (x[1] > x[0]) {
				x1++;
				if (x1 > x[1]) break;
			}
			else {
				x1--;
				if (x1 < x[1]) break;
			}
			y1 = (int) ((x1-x[0]) * dy1 + y[0]);
			y2 = (int) ((x1-x[0]) * dy2 + y[0]);
		}
	}
	gc.translate(-arm_offset, -arm_offset);
}

public void getDrawingArea(Rectangle rect) {
	int k = highlight + border;
	JCComponent.setBounds(rect, k, k, 
			  Math.max(0, size().width - 2*k),
			  Math.max(0, size().height - 2*k));
}

/** Disables the component. */
public synchronized void disable() {
	if (isEnabled() && thread != null) {
		if (thread.equals(Thread.currentThread())) {
			// if this thread is the spawned-off thread, don't call disarmAction to
			// wait for itself to die.  Instead, the spawned off thread tells 
			// itself to drop dead gracefully.
			thread_stop = true;
			super.disarmAction(null);
		} else {
			// Only call disarmAction if the current thread is not the spawned-off thread.
			disarmAction(null);
		}
	}
	super.disable();
}

/** Displays the button as pressed in. If the InitialRepeatDelay has been set,
 * a timer is started.
 * @see #setInitialRepeatDelay
 */
public void armAction(Event ev) {
	super.armAction(ev);
	synchronized (this) {
		if (initial_delay != BWTEnum.MAXINT) {
			if (thread != null && !thread.equals(Thread.currentThread())) {
				// There is already a thread.  Stop that one first.
				thread_stop = true;
				try {
					// The thread may be stopped after we set thread_stop.
					while (thread != null && thread.isAlive()) {
						thread.join(1000);
					}
				} catch (Exception e) { }
				thread = null;
			}
			if (thread == null) {
				thread = new Thread(this, getName() + "-created-thread" + thread_counter++);
				// Don't set priority in netscape because netscape shows security exception
				// even if you catch it.  The exception is caused by Thread.checkAccess()
				if (JCEnvironment.getBrowser(this) != JCEnvironment.BROWSER_NETSCAPE) {
					thread.setPriority(Thread.MIN_PRIORITY);
				}
				thread_stop = false;
				thread.start();
			}
		}
	}
}

/** Displays the button normally. If the InitialRepeatDelay has been set,
 * the timer is stopped.
 * @see #setInitialRepeatDelay
 */
public void disarmAction(Event ev) {
	synchronized (this) {
		if (thread != null) {
			// Don't stop the thread here.  Instead, set the 'thread' variable to
			// null.  It will be checked by the running thread and exit the
			// thread gracefully rather than in the middle of an operation by 
			// the receiver of the mouse events.  Also, the "thread" can 
			// call and wait for completion of clickAction(null), 
			// which in turn call this disarmAction.
			// This deadlock problem is why AWT Event Queue thread cannot 
			// join() the "thread"
			if (TRACE) {
				System.out.println(Thread.currentThread().getName() + 
					" is disarming and is stopping " + thread.getName());
			}
			thread_stop = true;
			thread = null;
		}
	}
	super.disarmAction(ev);
}

public boolean mouseDrag(Event ev, int x, int y) { 
	return super.mouseDrag(ev, x, y);
}

public boolean mouseMove(Event ev, int x, int y) { return true; }

/**
 * Calls armAction if btn1 was pressed.
 * @see #armAction
 */
/* JDK110_START */
// In JDK102, doing clickAction() let the arrow button gets the focus and 
// combo box list disappears.
public boolean mouseDown(Event ev, int x, int y) {
	if (BWTUtil.getMouseButton(ev) != 1) return false;
	if (armed) return true;
	if (!inside(x, y)) return false;
	if (!isEnabled()) return false;
	super.mouseDown(ev, x, y);
	clickAction(ev);
	return true;
}
/* JDK110_END */

/** If btn1 was pressed and the button is armed, calls disarmAction, clickAction.
 * @see jclass.bwt.JCButton#clickAction
 * @see jclass.bwt.JCButton#disarmAction
 */
public boolean mouseUp(Event ev, int x, int y) {
	if (BWTUtil.getMouseButton(ev) != 1) return false;

	if (TRACE) {
		System.out.println(Thread.currentThread().getName() + 
			" mouseUp arrowbutton: " + this.getName() + 
			"\t inside: " + inside(x,y) + " armed: " + armed);
	}
	
	if (armed) {
		disarmAction(ev);		// order is important
/* JDK102_START 
		// clickAction() should be fired from mouseDown.  It is called from here due to a JDK102 bug.
		clickAction(ev);
 JDK102_END */
	}
	if (TRACE) {
		System.out.println(Thread.currentThread().getName() + 
			" leaving mouseUp arrowbutton: " + this.getName() + 
			"\t inside: " + inside(x,y) + " armed: " + armed);
	}
	return !BWTUtil.instanceOf(getParent(), "JCScrollbar");
}

/**
 * Sends action events if the user continues to press the button.
 */
public void run() {
	int sleeplen = initial_delay;
	int this_tn = tn;
	tn++;
	if (TRACE) {
		System.out.println("thread: " + Thread.currentThread().getName() + 
			" is created and starts running...");
	}
	while (true) {
		long t = System.currentTimeMillis();
		try {
			Thread.sleep(sleeplen);
		} catch (Exception e) { }
		// End thread if it was marked for death during execution.
		if (thread == null || thread_stop || !Thread.currentThread().isAlive()) {
			if (TRACE) {
				System.out.println("thread: " + Thread.currentThread().getName() + 
					" is exiting...");
			}
			return;
		}

		// Sanity check - Netscape bug
		if (isEnabled() && (System.currentTimeMillis() - t >= sleeplen)) {
			if (TRACE) {
				System.out.println("thread: " + Thread.currentThread().getName() + 
					" is calling clickAction()...");
			}

			// Commented out 'clickAction(null)' so that no extra clicks will be
			// generated 
			//clickAction(null);

		}

		// Save the sleep if the thread was marked for death during the
		// click action.
		if (thread == null || thread_stop) {
			if (TRACE) {
				System.out.println("thread: " + Thread.currentThread().getName() + 
					" is exiting...");
			}
			return;
		}

		sleeplen = 50;
	}
}

/**
 * This method should be called when the component is no longer needed.
 * It ensures that the component will be garbage collected.
 */
public void dispose() {
	super.dispose();
	if (thread != null) {
		thread.stop();
		thread = null;
	}
}

}
