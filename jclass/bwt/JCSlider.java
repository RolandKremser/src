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
// RCSID -- $RCSfile: JCSlider.java $ $Revision: 2.11 $ $Date: 2000/11/09 20:11:03 $ $Locker: $  Sitraka Inc.

package jclass.bwt;
import jclass.util.*;
import java.applet.*;
import java.awt.*;

class SliderThumb extends ScrollbarThumb {

JCSlider slider;

SliderThumb(JCSlider slider) {
	super(slider);
	highlight = 0;
	this.slider = slider;
}

public void move(int x, int y) {
	if (slider.dir == JCSlider.HORIZONTAL)
		x += slider.trough_offset;
	else
		y += slider.trough_offset;
	super.move(x, y);
}

protected void drawShadow(Graphics gc) {
	int w = size().width, h = size().height;
	gc.setColor(BWTUtil.brighter(getBackground()));
	if (slider.dir == JCSlider.HORIZONTAL) {
		// Top
		gc.drawLine(0, 0, w, 0);
		// Left
		gc.drawLine(0, 0, 0, h-6);
		gc.drawLine(0, h-6, w/2, h-1);

		// Right
		gc.setColor(BWTUtil.darker(getBackground()));
		gc.drawLine(w-2, 1, w-2, h-6);
		gc.drawLine(w-2, h-6, w/2, h-2);

		gc.setColor(Color.black);
		gc.drawLine(w-1, 0, w-1, h-6);
		gc.drawLine(w-1, h-6, w/2-1, h);
	}
	else {
		// Left
		gc.drawLine(0, 0, 0, h);
		// Top
		gc.drawLine(0, 0, w-6, 0);
		gc.drawLine(w-6, 0, w-1, h/2);

		// Bottom
		gc.setColor(BWTUtil.darker(getBackground()));
		gc.drawLine(1, h-2, w-6, h-2);
		gc.drawLine(w-6, h-2, w-2, h/2);

		gc.setColor(Color.black);
		gc.drawLine(0, h-1, w-6, h-1);
		gc.drawLine(w-6, h-1, w, h/2-1);
	}
}

}

/** A JCSlider is a subclass of JCScrollbar which is used by an application
 * to indicate a value from within a range of values. It allows the user to
 * input or modify a value from the same range.
 * <p>
 * A Slider has an elongated rectangular region similar to a
 * ScrollBar. A thumb inside this region indicates the
 * current value along it. The user can also modify the
 * slider's value by moving the slider within the rectangular
 * region of the Scale. A slider can also include labels
 * located at either end.<p>
 *
 * <strong>Properties</strong><p>
 * <table border>
 * <tr><th>Name</th>    <th>Method</th></tr><tr><td>
 * AutoTick            </td><td><a href=#setAutoTick>setAutoTick</a></td></tr><tr><td>
 * Background          </td><td><a href=java.awt.Component.html#setBackground>setBackground</a></td></tr><tr><td>
 * BlockIncrement             </td><td><a href=#setBlockIncrement>setBlockIncrement</a></td></tr><tr><td>
 * Foreground          </td><td><a href=java.awt.Component.html#setForeground>setForeground</a></td></tr><tr><td>
 * Maximum             </td><td><a href=#setMaximum>setMaximum</a></td></tr><tr><td>
 * Minimum             </td><td><a href=#setMinimum>setMinimum</a></td></tr><tr><td>
 * MaximumLabel        </td><td><a href=#setMaximumLabel>setMaximumLabel</a></td></tr><tr><td>
 * MinimumLabel        </td><td><a href=#setMinimumLabel>setMinimumLabel</a></td></tr><tr><td>
 * NumTicks            </td><td><a href=#setNumTicks>setNumTicks</a></td></tr><tr><td>
 * Orientation         </td><td><a href=#setOrientation>setOrientation</a></td></tr><tr><td>
 * PreferredSize       </td><td><a href=jclass.bwt.JCContainer.html#setPreferredSize>setPreferredSize</a></td></tr><tr><td>
 * ThumbSize           </td><td><a href=#setThumbSize>setThumbSize</a></td></tr>
 * UnitIncrement             </td><td><a href=#setUnitIncrement>setBlockIncrement</a></td></tr><tr><td>
 * UserData            </td><td><a href=jclass.bwt.JCContainer.html#setUserData>setUserData</a></td></tr><tr><td>
 * Value               </td><td><a href=#setValue>setValue</a></td></tr>
 * </table><p>
 *
 * <strong>Events</strong><p>
 * <table border><tr>
 * <th>Class</th> <th>Listener</th>   <th>Description</th></tr><tr><td>
 * JCAdjustmentEvent    </td><td><a href=#addAdjustmentListener>addAdjustmentListener</a></td> <td>Posted when the value changes</td></tr>
 * </table>
 */
public class JCSlider extends JCContainer
implements JCAdjustable {

public static final int	HORIZONTAL = BWTEnum.HORIZONTAL;
public static final int	VERTICAL   = BWTEnum.VERTICAL;

/** Scrollbar's thumb. */
protected ScrollbarThumb thumb;

int	value;	// current value
int	min, max = 100;
int	dir = BWTEnum.NOVALUE;	// orientation
int trough_offset = 0;

/** List of JCAdjustmentEvent listeners. */
protected JCVector adjustmentListeners = new JCVector(0);

int line_incr = 10;	// line increment
int page_incr = 10;	// page increment
long filter_time = 0, last_time = 0;	// filter times

int trough_size = 0;		// trough size (total size - buttons)
int slider_size = 0;		// current slider size
int visible;				// slider size in user units
int major, minor;			// sizes

/***
 * Starting point (in pixels) for a drag operation.  Used to
 * determine if mouse drag operation is in progress.
 */
private int dragStart = BWTEnum.NOVALUE;

/**
 * Value of scrollbar at start of mouse drag operation.
 */
private int dragValueStart = BWTEnum.NOVALUE;

int thumb_major = 21, thumb_minor = 11;
int THUMB_SIZE = Math.min(thumb_major, thumb_minor);
int HALF_THUMB_SIZE = (int)Math.ceil(THUMB_SIZE/2.);

final static int TICK_SIZE = 3;

int num_ticks = 0;
boolean auto_tick = true;
JCLabel min_label, max_label;

private static final String base = "slider";
private static int nameCounter = 0;

/** Creates a horizontal slider. No parameters are read from an HTML file */
public JCSlider() {
	this(HORIZONTAL, null, null);
}

/**
 * Constructs a slider with the specified orientation.
 * @param dir either HORIZONTAL or VERTICAL
 * @exception IllegalArgumentException when an illegal orientation is given.
 */
public JCSlider(int dir) {
	this(dir, null, null);
}

/**
 * Constructs a slider with an initial orientation,
 * value, and minimum and maximum values.
 * @param dir either HORIZONTAL or VERTICAL
 * @param value the scrollbar's value
 * @param min the min value
 * @param max the max value
 */
public JCSlider(int dir, int value, int min, int max) {
	this(dir, null, null);
	setValues(value, min, max);
}

/** Creates a slider which reads parameters from the applet's HTML file.
 * @param dir either HORIZONTAL or VERTICAL
 * @param applet the applet whose PARAM tags are to be read
 * @param name if this is not null, only parameters preceded by this name are read
 * @see java.applet.Applet#getParameter
 */
public JCSlider(int dir, Applet applet, String name) {
	super(applet, name);
	if (name == null)
		setName(base + nameCounter++);
	setLayout(null);
	setOrientation(dir);
	double_buffer = true;
	add(thumb = new SliderThumb(this));
	if (getClass().getName().equals("jclass.bwt.JCSlider"))
		 getParameters(applet);
	/* JDK110_START */
	enableEvents(AWTEvent.MOUSE_MOTION_EVENT_MASK);
	/* JDK110_END */
}

/** Reads the parameter values from the HTML page using the component's applet.
 * The values will override those previously set.
 */
protected void getParameters() {
	super.getParameters();
	SliderConverter.getParams(this);
}

/**
 * Sets the values.
 * @param value the slider's value
 * The page increment this is also set to this value.
 * @param min the minimum value of the slider
 * @param max the maximum value of the slider
 */
public void setValues(int value, int min, int max) {
	int old_page = 0;
	synchronized (this) {
		old_page = page_incr;
		this.min = min;
		this.max = max;
		visible = 0;

		if (max < min) max = min;
		visible = Math.min(max-min, visible);
		value = Math.min(Math.max(min, value), max-visible);
		this.value = value;
		page_incr = this.visible = visible;
	}
	layout();
	synchronized (this) {
		page_incr = old_page;
	}
}

/**
 * Gets the orientation.
 */
public int getOrientation() { return dir; }

/**
 * Sets the orientation. This must be called before the scrollbar is visible
 * (ie before it is added to its parent).
 * @param dir either HORIZONTAL or VERTICAL
 * @exception IllegalArgumentException If an invalid value is set
 */
public synchronized void setOrientation(int dir) {
	ScrollbarConverter.checkOrientation(dir);
	if (this.dir == dir) return;
	this.dir = dir;
}

/**
 * Gets the current value. This will be between the minimum value and the
 * maximum value minus the slider size.
 * @see #getMinimum
 * @see #getMaximum
 */
public int getValue() { return value; }

protected int check(int value) {
	return Math.min(Math.max(min, value), max-visible);
}

/**
 * Sets the value.
 * @param v the new value. If this value is
 * below the current minimum or above the current maximum, it becomes the
 * new one of those values, respectively.
 * @see #getValue
 */
public void setValue(int v) {
	boolean move_thumb = false;
	synchronized (this) {
		v = check(v);
		if (v != value) {
			value = v;
			move_thumb = true;
		}
	}
	if (move_thumb)
		moveThumb();
}

/** Sets the value.
 * @param notify if true, an JCAdjustmentEvent is posted.
 * @see #addAdjustmentListener
 */
public void setValue(int value, boolean notify) {
	setValue(value, Event.SCROLL_ABSOLUTE, notify);
}

void setValue(int v, int type, boolean notify) {
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
		v = check(v);
		// create "thisvalue" and use it instead of "this.value" to avoid very
		// strange problem in Symantec JIT
		int thisvalue = this.value;
		if (v == thisvalue)
			return;
		value = v;
		al = (JCVector) adjustmentListeners.clone();
	}
	moveThumb();
	if (!notify || al.size() == 0)
		return;

	JCAdjustmentEvent adj_ev = new JCAdjustmentEvent(this,
						 JCAdjustmentEvent.ADJUSTMENT_VALUE_CHANGED, type, value);
	for (int i=0; i < al.size(); i++)
		((JCAdjustmentListener)al.elementAt(i)).adjustmentValueChanged(adj_ev);
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
	setValues(value, v, max);
}

/**
 * Sets the maximum value (default: 100).
 * @see #setValues
 */
public void setMaximum(int v) {
	setValues(value, min, v);
}

/**
 * Gets the page increment.
 */
public int getBlockIncrement() { return page_incr; }

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

/** @return true if the user is currently dragging the scrollbar's thumb */
public boolean dragging() {
	return (dragStart != BWTEnum.NOVALUE);
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
	return true;
}

/**
 * If an arrow button is pressed, the slider will move by one line.<p>
 * If the trough is clicked with the CTRL key down, the slider will move to the beginning or end.<p>
 * If the trough is clicked, the slider will move by one page.
 */
public boolean mouseDown(Event ev, int x, int y) {

	// Check whether slider can move
	if (slider_size == trough_size || ev.target != this) return true;

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
	if (slider_size == trough_size) return true;

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
	// Check whether slider can move
	if (slider_size == trough_size) return true;

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

/** Moves the slider. */
protected void moveThumb() {
	int start;
	int x = 0, y = 0;

	synchronized (this) {
		if (!isDisplayable()) return;
		start =
			Math.max(0, Math.min(toPixels(value-min), trough_size-slider_size));

		if (dir == HORIZONTAL)
			x = start;
		else
			y = start;
	}
	thumb.move(x, y);
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

/**
 * Converts from scrollbar scale to screen pixels.
 */
protected int toPixels(int value) {
	if (max == min) return 0;
	else if (value >= max-min-visible) return trough_size;
	long v = ((long) trough_size * (long) value) / (long) (max-min);
	return (v > 0) ? (int) v : 0;
}

/**
 * Converts from screen pixels to scrollbar scale.
 */
protected int toValue(int value) {
	if (trough_size == 0) return 0;
	return (int) (((long) value * (long) (max-min)) / (long) trough_size);
}

public synchronized void setBackground(Color c) {
	thumb.setBackground(c);
	super.setBackground(c);
}

/**
 * Sets the layout manager for this container.  This method is
 * overridden to prevent the layout mgr from being set.
 */
public final void setLayout(LayoutManager mgr) {}

/**
 * Gets the maximum value.
 */
public int getMaximum() { return max-visible; }

/** Gets the thumb's size.
 * @see #setThumbSize
 */
public Dimension getThumbSize() {
	return (dir == HORIZONTAL) ?
		new Dimension(thumb_minor, thumb_major) :
		new Dimension(thumb_major, thumb_minor);
}

/** Sets the thumb's size (default: 21x11 for a vertical slider,
 * 11x21 for a horizontal slider).
 */
public void setThumbSize(Dimension d) {
	thumb_minor = (dir == HORIZONTAL) ? d.width : d.height;
	thumb_major = (dir == HORIZONTAL) ? d.height : d.width;
	layout();
}

/**
 * Sets the line increment (default: BlockIncrement value).
 * This is the amount that the thumb moves when the user presses an arrow key.
 * @see #setBlockIncrement
 */
public void setUnitIncrement(int v) { line_incr = v; }

/**
 * Gets the line increment.
 */
public int getUnitIncrement() {	return line_incr; }

/**
 * Returns the thumb size, in user units
 * @see #setVisibleAmount
 */
public int getVisibleAmount() { return visible; }

/** Sets the thumb size, in user units.
 */
public void setVisibleAmount(int v) {
	setValues(value, min, max);
}

/**
 * Sets the page increment. This is the amount that the thumb will move
 * when the user clicks to either side of it (default: 10).
 */
public void setBlockIncrement(int v) { page_incr = line_incr = v; }

/** Gets the AutoTick value.
 * @see #setAutoTick
 */
public boolean getAutoTick() { return auto_tick; }

/** If true (default), a tick mark is drawn at each page increment.
 * This value is ignored if NumTicks is non-zero.
 * @see #setNumTicks
 */
public void setAutoTick(boolean v) {
	auto_tick = v;
	repaint();
}

/** Returns the NumTicks value.
 * @see #setNumTicks
 */
public int getNumTicks() { return num_ticks; }

/** Sets the number of tick marks to be displayed (default: 0).
 * If this value is non-zero, it overrides the AutoTick value.
 * @see #setAutoTick
 */
public void setNumTicks(int v) {
	num_ticks = v;
	repaint();
}

/** Gets the MinimumLabel value. This will be null if no label has been set.
 * @see #setMinimumLabel
 */
public JCLabel getMinimumLabel() { return min_label; }

/** Sets the label to be displayed at the slider's minimum end
 * (the top or left, depending on the orientation).
 */
public void setMinimumLabel(JCLabel l) {
	if (min_label != null)
		remove(min_label);
	min_label = l;
	layout();
	repaint();
}

/** Gets the MinimumLabel value. This will be null if no label has been set.
 * @see #setMinimumLabel
 */
public String getMinimumLabelString() {
	return min_label != null ? min_label.getText() : null;
}

/** Sets the label to be displayed at the slider's minimum end
 * (the top or left, depending on the orientation).
 */
public void setMinimumLabelString(String l) { setMinimumLabel(new JCLabel(l)); }

/** Gets the MaximumLabel value. This will be null if no label has been set.
 * @see #setMaximumLabel
 */
public String getMaximumLabelString() {
	return max_label != null ? max_label.getText() : null;
}

/** Sets the label to be displayed at the slider's maximum end
 * (the bottom or right, depending on the orientation).
 */
public void setMaximumLabelString(String l) { setMaximumLabel(new JCLabel(l)); }

/** Gets the MaximumLabel value. This will be null if no label has been set.
 * @see #setMaximumLabel
 */
public JCLabel getMaximumLabel() { return max_label; }

/** Sets the label to be displayed at the slider's maximum end
 * (The bottom or right, depending on the orientation).
 */
public void setMaximumLabel(JCLabel l) {
	if (max_label != null)
		remove(max_label);
	max_label = l;
	layout();
	repaint();
}

protected int preferredWidth() {
	int min_w = 0, max_w = 0;
	if (min_label != null) min_w =
							   JCComponent.getPreferredSize(min_label).width;
	if (max_label != null) max_w =
							   JCComponent.getPreferredSize(max_label).width;

	if (dir == HORIZONTAL)
		return min_w + max_w + 200;
	return Math.max(Math.max(min_w, max_w),
		   thumb_major + ((num_ticks > 0 || auto_tick) ? TICK_SIZE + 1 : 0));
}

protected int preferredHeight() {
	int min_h = 0, max_h = 0;
	if (min_label != null) min_h =
							   JCComponent.getPreferredSize(min_label).height;
	if (max_label != null) max_h =
							   JCComponent.getPreferredSize(max_label).height;

	if (dir == VERTICAL)
		return min_h + max_h + 200;
	return Math.max(Math.max(min_h, max_h),
		   thumb_major + ((num_ticks > 0 || auto_tick) ? TICK_SIZE + 1 : 0));
}

/** Disallows resizing the slider. */
protected void resizeThumb(int w, int h) {
	if (dir == HORIZONTAL)
		thumb.resize(thumb_minor, thumb_major);
	else
		thumb.resize(thumb_major, thumb_minor);
}

public void layout() {
	if (!isDisplayable()) return;

	int x, y, label_size;

	synchronized (this) {
		x = JCComponent.getInsets(this).left;
		y = JCComponent.getInsets(this).top;
		label_size = 0;
		trough_offset = (dir == HORIZONTAL) ? x : y;
	}

	if (min_label != null) {
		add(min_label);
		int w = JCComponent.getPreferredSize(min_label).width,
			h = JCComponent.getPreferredSize(min_label).height;
		JCComponent.setBounds(min_label, x, y, w, h);
		synchronized (this) {
			if (dir == HORIZONTAL) {
				trough_offset = x + w;
				label_size += w;
			} else {
				trough_offset = y + h;
				label_size += h;
			}
		}
	}
	if (max_label != null) {
		add(max_label);
		int w = JCComponent.getPreferredSize(max_label).width,
			h = JCComponent.getPreferredSize(max_label).height;
		int reshape_x,
			reshape_y;
		synchronized (this) {
			if (dir == HORIZONTAL) {
				reshape_x = major-JCComponent.getInsets(this).right-w;
				reshape_y = y;
				label_size += w;
			}
			else {
				reshape_x = x;
				reshape_y = major-JCComponent.getInsets(this).bottom-h;
				label_size += h;
			}
		}
		JCComponent.setBounds(max_label, reshape_x, reshape_y, w, h);
	}

	synchronized (this) {
		int arrow_size = Math.max(minor, BWTEnum.SB_SIZE);
		trough_size = major;

		slider_size = thumb_minor;
	}
	if (dir == HORIZONTAL)
		resizeThumb(slider_size, minor);
	else
		resizeThumb(minor, slider_size);
	moveThumb();

	synchronized (this) {
		trough_size = Math.max(5, major-label_size);
		max -= visible;
		max += (visible = toValue(THUMB_SIZE));
	}
	moveThumb();
}

/** Draws the tick marks. */
protected void drawTicks(Graphics g, int num_ticks) {
	g.setColor(getForeground());
	double interval = (num_ticks > 1) ?
		(trough_size-THUMB_SIZE)/(num_ticks-1.0) : 0;
	int last_pos = trough_offset + trough_size - HALF_THUMB_SIZE;

	if (dir == HORIZONTAL) {
		double x = trough_offset + THUMB_SIZE/2.0;
		int y = thumb.bounds().y + thumb_major + 1;
		for (int i=0; i < num_ticks-1; i++, x += interval)
			g.drawLine((int)x, y, (int)x, y+3);

		// Ensure that position of last tick is not rounded off
		if (num_ticks > 1)
			g.drawLine(last_pos, y, last_pos, y+3);
	}
	else {
		int x = thumb.bounds().x + thumb_major + 1;
		double y = trough_offset + THUMB_SIZE/2.0;
		for (int i=0; i < num_ticks-1; i++, y += interval)
			g.drawLine(x, (int)y, x+3, (int)y);

		// Ensure that position of last tick is not rounded off
		if (num_ticks > 1)
			g.drawLine(x, last_pos, x+3, last_pos);
	}
}

/** Draws the trough and thumb. */
public void paintInterior(Graphics g) {
	if (dir == HORIZONTAL) {
		int x = trough_offset, y = thumb_major/2 - 2;

		// Trough
		g.setColor(BWTUtil.darker(getBackground()));
		g.drawLine(x, y, x+trough_size-1, y);
		g.drawLine(x, y, x, y+3);
		y++;
		g.setColor(Color.black);
		g.drawLine(x+1, y, x+trough_size-2, y);
		y++;
		g.setColor(getBackground().brighter().darker());
		g.drawLine(x+1, y, x+trough_size-1, y);
		g.drawLine(x+trough_size-1, y-1, x+trough_size-1, y);
		y++;
		g.setColor(BWTUtil.brighter(g.getColor()));
		g.drawLine(x, y, x+trough_size, y);
		g.drawLine(x+trough_size-1, y-3, x+trough_size-1, y);
	}
	else {
		int y = trough_offset, x = thumb_major/2 - 2;

		// Trough
		g.setColor(BWTUtil.darker(getBackground()));
		g.drawLine(x, y, x, y+trough_size-1);
		g.drawLine(x, y, x+3, y);
		x++;
		g.setColor(Color.black);
		g.drawLine(x, y+1, x, y+trough_size-2);
		x++;
		g.setColor(getBackground().brighter().darker());
		g.drawLine(x, y+1, x, y+trough_size-1);
		g.drawLine(x-1, y+trough_size-1, x, y+trough_size-1);
		x++;
		g.setColor(BWTUtil.brighter(g.getColor()));
		g.drawLine(x, y, x, y+trough_size);
		g.drawLine(x-3, y+trough_size-1, x, y+trough_size-1);
	}
	int ticks = (num_ticks > 0) ? num_ticks :
		((auto_tick && page_incr > 0) ? (max-visible-min)/page_incr + 1 : 0);
	if (ticks > 0)
		drawTicks(g, ticks);
	paintThumb(g);
}

}
