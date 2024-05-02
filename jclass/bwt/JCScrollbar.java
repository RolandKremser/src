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
// RCSID -- $RCSfile: JCScrollbar.java $ $Revision: 2.15 $ $Date: 2000/11/09 20:10:57 $ $Locker: $  Sitraka Inc.

package jclass.bwt;
import jclass.util.*;
import java.awt.*;
import java.applet.*;

/** 
 * The JCScrollbar component allows the user to view data that is too large to
 * be displayed all at once. Scrollbars are usually located inside a 
 * <a href=jclass.bwt.JCScrolledWindow.html>JCScrolledWindow</a>
 * and adjacent to the component that contains the data to be 
 * viewed. When the user interacts with the scrollbar, the data within the other
 * component scrolls.<p>
 * 
 * <strong>Behavior</strong><br>
 * The scrollbar consists of two JCArrowButtons at either end, and a rectangular 
 * area called a thumb within it. The data is scrolled by clicking a button,
 * clicking to either side of the thumb, or dragging the thumb. Clicking
 * to either side of the thumb with the CONTROL key pressed will cause the
 * thumb to move to the beginning or end.
 * Hitting PAGE-UP or PAGE-DOWN will scroll up
 * or down by a page. Hitting HOME or END will scroll to the beginning or end.<p>
 * 
 * <strong>Properties</strong><p>
 * <table border>
 * <tr><th>Name</th>    <th>Method</th></tr><tr><td>
 * Background          </td><td><a href=java.awt.Component.html#setBackground>setBackground</a></td></tr><tr><td>
 * BlockIncrement             </td><td><a href=#setBlockIncrement>setBlockIncrement</a></td></tr><tr><td>
 * FilterTime          </td><td><a href=#setFilterTime>setFilterTime</a></td></tr><tr><td>
 * Foreground          </td><td><a href=java.awt.Component.html#setForeground>setForeground</a></td></tr><tr><td>
 * Maximum             </td><td><a href=#setMaximum>setMaximum</a></td></tr><tr><td>
 * Minimum             </td><td><a href=#setMinimum>setMinimum</a></td></tr><tr><td>
 * Orientation         </td><td><a href=#setOrientation>setOrientation</a></td></tr><tr><td>
 * PreferredSize       </td><td><a href=jclass.bwt.JCContainer.html#setPreferredSize>setPreferredSize</a></td></tr><tr><td>
 * UnitIncrement             </td><td><a href=#setUnitIncrement>setUnitIncrement</a></td></tr><tr><td>
 * UserData            </td><td><a href=jclass.bwt.JCContainer.html#setUserData>setUserData</a></td></tr><tr><td>
 * Value               </td><td><a href=#setValue>setValue</a></td></tr><tr><td>
 * VisibleAmount       </td><td><a href=#setVisibleAmount>setVisibleAmount</a></td></tr>
 * </table><p>
 *
 * <strong>Events</strong><p>
 * <table border><tr>
 * <th>Class</th> <th>Listener</th>   <th>Description</th></tr><tr><td>
 * JCAdjustmentEvent    </td><td><a href=#addAdjustmentListener>addAdjustmentListener</a></td> <td>Posted when the value changes</td></tr>
 * </table>
 */
public class JCScrollbar 
extends JCContainer implements JCAdjustable, JCActionListener {

public static final int	HORIZONTAL = BWTEnum.HORIZONTAL;
public static final int	VERTICAL   = BWTEnum.VERTICAL;

/** Parent window. */
transient protected JCScrolledWindow scrolled_window = null;

/** Down/right and up/left arrow buttons. */
protected JCArrowButton incr_arrow, decr_arrow;

/** Scrollbar's thumb. */
protected ScrollbarThumb thumb;
static final int MIN_SLIDERSIZE = 6;	// Minimum slider size

private static final String base = "scrollbar";
private static int nameCounter = 0;

int	value;	// current value
int	min, max = 100;
int	dir = BWTEnum.NOVALUE;	// orientation

/** List of JCAdjustmentEvent listeners. */
protected JCVector adjustmentListeners = new JCVector(0);

int line_incr = 10;						// line increment
int page_incr = 10;						// page increment
long filter_time = 0, last_time = 0;	// filter times
int visible;				// slider size in user units
int major, minor;			// sizes

int trough_size = 0;		// trough size (total size - buttons)
int slider_size = 0;		// current slider size
transient Image disabled_image;

// Starting point (in pixels) for a drag operation.  Used to
// determine if mouse drag operation is in progress.
private int dragStart = BWTEnum.NOVALUE;

// Value of scrollbar at start of mouse drag operation.
private int dragValueStart = BWTEnum.NOVALUE;

/**
 * Constructs a vertical scrollbar.
 */
public JCScrollbar() {
	this(VERTICAL);
}

/**
 * Constructs a scrollbar with the specified orientation.
 * @param dir either HORIZONTAL or VERTICAL
 */
public JCScrollbar(int dir) {
	this(dir, null, null);
}

/** Creates a scrollbar which reads parameters from the applet's HTML file.
 * @param dir either HORIZONTAL or VERTICAL
 * @param applet the applet whose PARAM tags are to be read
 * @param name if this is not null, only parameters preceded by this name are read
 * @see java.applet.Applet#getParameter
 */
public JCScrollbar(int dir, Applet applet, String name) {
	super(applet, name);
	if (name == null)
		setName(base + nameCounter++);
	setLayout(null);
	setOrientation(dir);
	add(thumb = new ScrollbarThumb(this));
	double_buffer = true;
	/* JDK110_START */
	enableEvents(AWTEvent.MOUSE_MOTION_EVENT_MASK);
	/* JDK110_END */
	if (getClass().getName().equals("jclass.bwt.JCScrollbar"))
		 getParameters(applet);
}

/**
 * Constructs a scrollbar with an initial orientation,
 * value, page size, and minumum and max values.
 * @param dir either HORIZONTAL or VERTICAL
 * @param value the scrollbar's value
 * @param visible the size of the visible portion of the
 * scrollable area. The scrollbar will use this value when paging up
 * or down by a page.
 * @param min the min value
 * @param max the max value
 */
public JCScrollbar(int dir, int value, int visible, int min, int max) {
	this(dir);
	setValues(value, visible, min, max);
}

/** Reads the parameter values from the HTML page using the component's applet.
 * The values will override those previously set.
 */
protected void getParameters() {
	super.getParameters();
	ScrollbarConverter.getParams(this);
}

/** Gets a parameter for this container. */
public void getParameters(Applet applet) {
	getParameters();
}

String getParam(String param) {
	return conv.getParam(applet, this, getName(), param);
}

// Disable keyboard traversal
public boolean isFocusTraversable() { return false; }

/* SWING11_START 
public void setBorder(com.sun.java.swing.border.Border border) {
 SWING11_END */
/* SWING12_START */
public void setBorder(javax.swing.border.Border border) {
/* SWING12_END */
/* SWING_START */
	swing_border = border;
	setChildrenBorder(border);
}
/* SWING_END */

/**
 * Gets the orientation.
 */
public int getOrientation() { return dir; }

/**
 * Sets the orientation. This must be called before the scollbar is visible 
 * (ie before it is added to its parent).
 * @param dir either HORIZONTAL or VERTICAL
 * @exception IllegalArgumentException If an invalid value is set
 */
public synchronized void setOrientation(int dir) { 
	ScrollbarConverter.checkOrientation(dir);
	if (this.dir == dir) return;
	this.dir = dir;

	int orient = (dir == HORIZONTAL) ? BWTEnum.LEFT : BWTEnum.UP;
	add(decr_arrow = new JCArrowButton(orient, applet, getName()));
	decr_arrow.addActionListener(this);
	orient = (dir == HORIZONTAL) ? BWTEnum.RIGHT : BWTEnum.DOWN;
	add(incr_arrow = new JCArrowButton(orient, applet, getName()));
	incr_arrow.addActionListener(this);
	incr_arrow.setBorderThickness(2);
	decr_arrow.setBorderThickness(2);
	incr_arrow.border_color = decr_arrow.border_color = Color.black;
	incr_arrow.initial_delay = decr_arrow.initial_delay = 250;
}

/**
 * Gets the current value. This will be between the minimum value and the
 * maximum value minus the slider size.
 * @see #getMinimum
 * @see #getMaximum
 */
public int getValue() { return value; }

int check(int value) {
	return Math.min(Math.max(min, value), max-visible);
}

/**
 * Sets the value.
 * @param value the new value. If this value is
 * below the current minimum or above the current maximum, it becomes the
 * new one of those values, respectively.
 * @see #getValue
 */
public void setValue(int value) {
	synchronized (this) {
		value = check(value);
		if (value != this.value) {
			this.value = value;
		}
	}
	moveThumb();
}

/** Sets the value.
 * @param notify if true, an JCAdjustmentEvent is posted.
 * @see #addAdjustmentListener
 */
public void setValue(int value, boolean notify) {
	setValue(value, Event.SCROLL_ABSOLUTE, notify);
}

void setValue(int value, int type, boolean notify) {
	switch (type) {
	case Event.SCROLL_LINE_UP:
		type = JCAdjustmentEvent.UNIT_DECREMENT;
		break;
	case Event.SCROLL_LINE_DOWN:
		type = JCAdjustmentEvent.UNIT_INCREMENT;
		break;
	case Event.SCROLL_PAGE_UP:
		type = JCAdjustmentEvent.BLOCK_DECREMENT;
		break;
	case Event.SCROLL_PAGE_DOWN:
		type = JCAdjustmentEvent.BLOCK_INCREMENT;
		break;
	case Event.SCROLL_ABSOLUTE:
		type = JCAdjustmentEvent.TRACK;
		break;
	}

	JCVector al = null;
	synchronized (this) {
		value = check(value);
		// create "thisvalue" and use it instead of "this.value" to avoid very
		// strange problem in Symantec JIT
		int thisvalue = this.value;
		if (value == thisvalue) 
			return;
		this.value = value;

		// Clone the listeners to avoid timing race
		al = (JCVector) adjustmentListeners.clone();
	}
	moveThumb();

	if (!notify || al.size() == 0) 
		return;

	JCAdjustmentEvent adj_ev = new JCAdjustmentEvent(this, 
					 JCAdjustmentEvent.ADJUSTMENT_VALUE_CHANGED, type, value);

	for (int i=0; i < al.size(); i++) 
		((JCAdjustmentListener)al.elementAt(i))
			.adjustmentValueChanged(adj_ev);
}

/**
 * Gets the minimum value.
 * @see #setMinimum
 */
public int getMinimum() { return min; }

/**
 * Sets the minimum value.
 * @see #setValues
 */
public void setMinimum(int v) { 
	setValues(value, visible, v, max);
}

/**
 * Gets the maximum value.
 * @see #setMaximum
 */
public int getMaximum() { return max; }

/**
 * Sets the maximum value (default: 100).
 * @see #setValues
 */
public void setMaximum(int v) { 
	setValues(value, visible, min, v);
}

/**
 * Returns the slider size, in user units
 * @see #setVisibleAmount
 */
public int getVisibleAmount() { return visible; }

/**
 * @deprecated
 * @see #getVisibleAmount
 */
public int getVisible() { return visible; }

/** Sets the slider size, in user units.
 * @see #setValues
 */
public void setVisibleAmount(int v) {
	setValues(value, v, min, max);
}

/**
 * Sets the line increment (default: 10). This is the amount that is
 * scrolled up or down when the user clicks the arrow buttons.
 */
public void setUnitIncrement(int v) { line_incr = v; }

/**
 * Gets the line increment.
 */
public int getUnitIncrement() {	return line_incr; }

/**
 * Sets the page increment. This is the amount that will
 * be scrolled up or down when the user clicks above/below the slider
 * (default: slider size).
 */
public void setBlockIncrement(int v) { page_incr = v; }

/**
 * Gets the page increment.
 */
public int getBlockIncrement() { return page_incr; }

/**
 * Sets the values.
 * @param value the scrollbar's value
 * @param visible the amount visible per page (the slider size).
 * The page increment is also set to this value.
 * @param min the minimum value of the scrollbar
 * @param max the maximum value of the scrollbar
 * @see #setBlockIncrement
 */
public void setValues(int value, int visible, int min, int max) {
	boolean do_layout = false;
	synchronized (this) {
		if (max < min) max = min;
		visible = Math.min(max-min, visible);
		value = Math.min(Math.max(min, value), max-visible);
		if (this.value != value || this.visible != visible 
			|| this.min != min || this.max != max) {
			this.min = min;
			this.max = max;
			this.value = value;
			page_incr = this.visible = visible;
			do_layout = true;
		}
	}
	if (do_layout) layout();
}

/** @return true if slider is currently at min or max value */
public boolean atEnd() {
	return (value == 0 || value >= max - visible);
}

boolean dragging() { return (dragStart != BWTEnum.NOVALUE); }

/** Gets the FilterTime value.
 * @see #setFilterTime
 */
public long getFilterTime() { return filter_time; }

/** If 2 mouse drag events are received within this interval,
 * the 2nd is not reported (ie no events are posted) (default: 0 ms).
 * To specify that no events are to be reported until the user releases the 
 * mouse, set this value to BWTEnum.MAXINT.<p>
 * <strong>HTML param name/value:</strong> "FilterTime"/int
 */
public void setFilterTime(long t) { filter_time = t; }

/** Returns BWTEnum.SB_SIZE if this is a vertical scrollbar; 100 otherwise. */
protected int preferredWidth() {
	return (getOrientation() == VERTICAL) ? BWTEnum.SB_SIZE : 100;
}

/** Returns BWTEnum.SB_SIZE if this is a horizontal scrollbar; 100 otherwise. */
protected int preferredHeight() {
	return (getOrientation() == HORIZONTAL) ? BWTEnum.SB_SIZE : 100;
}

/**
 * Adds the specified adjustment listener to receive adjustment events.
 * @see JCAdjustmentEvent
 */ 
public void addAdjustmentListener(JCAdjustmentListener l) {
	adjustmentListeners.addUnique(l);
}

/**
 * Removes the specified adjustment listener so that it no longer
 * receives adjustment events.
 */ 
public void removeAdjustmentListener(JCAdjustmentListener l) {
	adjustmentListeners.removeElement(l);
}

/** Scrolls by a line if an arrow key was pressed. */
public void actionPerformed(JCActionEvent ev) {
	if (ev.getSource() == decr_arrow) 
		setValue(value-line_incr, Event.SCROLL_LINE_UP, true);
	else if (ev.getSource() == incr_arrow) 
		setValue(value+line_incr, Event.SCROLL_LINE_DOWN, true);
}

/**
 * Resets the scrollbar state after some other operation.
 */
public boolean mouseUp(Event ev, int x, int y) {
	// Ensure that this event is handled.
	if (dragging()) {
		int v = value;
		value = BWTEnum.NOVALUE;
		setValue(v, Event.SCROLL_ABSOLUTE, true);
	}

	dragStart = dragValueStart = BWTEnum.NOVALUE;

/* JDK102_START 
	// JDK 1.0.2 sets focus on Containers - 
	// transfer it back to the scrolledWindow's child
	if (scrolled_window != null) 
		scrolled_window.getViewport().requestFocus();
 JDK102_END */
/* JDK110_START */
	if (scrolled_window != null && JCEnvironment.getJavaVersion() < 110) {
		scrolled_window.getViewport().requestFocus();
	}
/* JDK110_END */

	return true;
}

/**
 * If an arrow button is pressed, the slider will move by one line.<p>
 * If the trough is clicked with the CTRL key down, the slider will move to the beginning or end.<p>
 * If the trough is clicked, the slider will move by one page.
 */
public boolean mouseDown(Event ev, int x, int y) {
	if (ev.target != this) return true;

	// AWT bug - event sometimes sent twice
	if (ev.when - last_time < 20) return true;
	last_time = ev.when;

	int spot = (dir == HORIZONTAL) ? x : y,
		start = (dir == HORIZONTAL) ? thumb.location().x : thumb.location().y;

	// Check whether thumb was clicked
	if (spot >= start && spot <= start + slider_size) {
		dragStart = spot;
		dragValueStart = value;
		return true;
	}

	if (ev.controlDown()) {
		if (spot < start)
			setValue(min, Event.SCROLL_ABSOLUTE, true);
		else
			setValue(max-visible, Event.SCROLL_ABSOLUTE, true);
		return true;
	}

	if (spot < start) 
		setValue(value-page_incr, Event.SCROLL_PAGE_UP, true);
	else 
		setValue(value+page_incr, Event.SCROLL_PAGE_DOWN, true);

	return true;
}

/**
 * Handles a mouse drag event in the slider.
 */
public boolean mouseDrag(Event ev, int x, int y) {
	if (!dragging()) return true;

	int spot = (dir == HORIZONTAL) ? ev.x : ev.y;
	boolean notify = (ev.when - last_time > filter_time);
	if (notify)
		last_time = ev.when;
	setValue(dragValueStart+toValue(spot-dragStart), notify);
	last_time = ev.when;
	return true;
}

/**
 * Moves the slider if HOME, END, PGUP, PGDN or an arrow key is hit.
 */
public boolean keyDown(Event ev, int key) {
	if (key == Event.PGUP) {
		setValue(value-page_incr, Event.SCROLL_PAGE_UP, true);
		return true;
	}
	else if (key == Event.PGDN) {
		setValue(value+page_incr, Event.SCROLL_PAGE_DOWN, true);
		return true;
	}
	else if (key == Event.HOME) {
		setValue(min, Event.SCROLL_ABSOLUTE, true);
		return true;
	}
	else if (key == Event.END) {
		setValue(max-slider_size, Event.SCROLL_ABSOLUTE, true);
		return true;
	}
	else if (key == Event.UP || key == Event.LEFT) {
		setValue(value-line_incr, Event.SCROLL_LINE_UP, true);
		return true;
	}
	else if (key == Event.DOWN || key == Event.RIGHT) {
		setValue(value+line_incr, Event.SCROLL_LINE_DOWN, true);
		return true;
	}
	else
		return super.keyDown(ev, key);
}

/* AWT_START 
public void reshape(int x, int y, int w, int h) {
 AWT_END */
/* SWING_START */
public void setBounds(int x, int y, int w, int h) {
/* SWING_END */

	synchronized (this) {
		major = (dir == HORIZONTAL) ? w : h;
		minor = (dir == HORIZONTAL) ? h : w;
	}
/* AWT_START 
	super.reshape(x, y, w, h);
 AWT_END */
/* SWING_START */
	super.setBounds(x, y, w, h);
/* SWING_END */
	
}

/**
 * Converts the slider size (in user units) to pixels.
 * @param v slider size in user units ("visible" arg passed to setValues)
 * @return slider size in pixels
 * @see #setValues
 */
int cvtThumbSizeToPixels(int v) {
	int size = 0;
	if (max > min) {
		size = (trough_size * v) / (max-min);
		if (size < 0) size = 0;
	}
	size = Math.min(trough_size, Math.max(size, MIN_SLIDERSIZE-2));
	if (min >= max)
		size = trough_size;
	return size;
}

/** Moves the thumb. */
private void moveThumb() {
	int start;
	int x = 0, y = 0;
	synchronized (this) {
		if (!isDisplayable()) return;
		start =
			Math.max(0, Math.min(toPixels(value-min), trough_size-slider_size));

		if (dir == HORIZONTAL) 
			x = start + decr_arrow.size().width;
		else 
			y = start + decr_arrow.size().height;
	}
	thumb.move(x, y);
}

/** Positions the thumb and arrow buttons. */
public void layout() {
	if (!isDisplayable()) return;
	int arrow_size = 0;

	synchronized (this) {
		arrow_size = Math.max(minor, BWTEnum.SB_SIZE);
		
		// if the 2 arrows are bigger than the actual size of the scrollbar,
		// resize the arrows so they both fit.
		if ((arrow_size * 2) > major) {
			arrow_size = major/2;
		}
		
		trough_size = Math.max(MIN_SLIDERSIZE, major) - 2*arrow_size;

		slider_size = cvtThumbSizeToPixels(visible);
	}

	if (dir == HORIZONTAL)
		thumb.resize(slider_size, minor);
	else
		thumb.resize(minor, slider_size);

	if (dir == HORIZONTAL) {
		JCComponent.setBounds(decr_arrow, 0, 0, arrow_size, minor);
		JCComponent.setBounds(incr_arrow, major-arrow_size, 0, 
							  arrow_size, minor);
	}
	else {
		JCComponent.setBounds(decr_arrow, 0, 0, minor, arrow_size);
		JCComponent.setBounds(incr_arrow, 0, major-arrow_size, 
							  minor, arrow_size);
	}
	moveThumb();

	// Hide thumb and disable scrollbar if no scrolling is possible
	thumb.show(!(slider_size == trough_size));
}

/** Paints the thumb. */
protected void paintThumb(Graphics gc) {
	if (!thumb.isVisible()) return;
	Point p = BWTUtil.translateToParent(this, thumb, 0, 0);
	Dimension size = thumb.size();
	if (!getPaintRect().intersects(new Rectangle(p, size))) 
		return;

	thumb.setDoubleBuffer(false);
	Point loc = thumb.location();
	gc.setColor(getBackground());
	Graphics new_gc = gc.create(loc.x, loc.y, size.width, size.height);
	thumb.paint(new_gc);
	new_gc.dispose();
	thumb.setDoubleBuffer(true);
}

/** Draws a 1-pixel black border. */
public void paintInterior(Graphics gc) {
	if (!decr_arrow.getBackground().equals(getBackground())) {
		decr_arrow.setBackground(getBackground());
		incr_arrow.setBackground(getBackground());
	}
	if (isEnabled()) {
		gc.setColor(BWTUtil.brighter(getBackground()));
		gc.fillRect(0, 0, size().width, size().height);
	}
	else {
		if (disabled_image == null)
			disabled_image = createDisabledImage();
		BWTUtil.wallPaper(this, gc, disabled_image);
	}
/* SWING_START */
	if (swing_border == null) {
/* SWING_END */
		gc.setColor(Color.black);
		gc.drawRect(0, 0, size().width-1, size().height-1);
/* SWING_START */
	}
/* SWING_END */
	paintThumb(gc);
}

/**
 * Converts from scrollbar scale to screen pixels.
 */
private int toPixels(int value) {
	if (max == min) return 0;
	else if (value >= max-min-visible) return trough_size;
	int v = (trough_size * value) / (max-min);
	return (v > 0) ? v : 0; 
}

/**
 * Converts from screen pixels to scrollbar scale.
 */
private int toValue(int value) {
	if (trough_size == 0) return 0;
	return (value * (max-min)) / trough_size;
}

public synchronized void setForeground(Color c) {
	incr_arrow.setForeground(c);
	decr_arrow.setForeground(c);
	super.setForeground(c);
}

public synchronized void setBackground(Color c) {
	new_bg = !isDisplayable() || c == null || !c.equals(getBackground());
	thumb.setBackground(c);
	super.setBackground(c);
}

transient private JCImageCreator creator;
transient private boolean new_bg = true;

private final String[] disabled_pixels = {
"wbwbwbwbwbwbwbwb",
"bwbwbwbwbwbwbwbw",
"wbwbwbwbwbwbwbwb",
"bwbwbwbwbwbwbwbw",
"wbwbwbwbwbwbwbwb",
"bwbwbwbwbwbwbwbw",
"wbwbwbwbwbwbwbwb",
"bwbwbwbwbwbwbwbw",
"wbwbwbwbwbwbwbwb",
"bwbwbwbwbwbwbwbw",
};

private Image createDisabledImage() {
	if (!new_bg && disabled_image != null) return disabled_image;
	if (creator == null) 
		creator = new JCImageCreator(this);
	Color bg = getBackground(), fg = getForeground();
	creator.setColor('b', !bg.equals(Color.white) ? bg : Color.lightGray);
	creator.setColor('w', Color.white);
	new_bg = false;
	creator.setSize(disabled_pixels[0].length(), disabled_pixels.length);
	return creator.create(disabled_pixels);
}

/** 
 * Sets the layout manager for this container.  This method is
 * overridden to prevent the layout mgr from being set.
 */
public final void setLayout(LayoutManager mgr) {}

}
