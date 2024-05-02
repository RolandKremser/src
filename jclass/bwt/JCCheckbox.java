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
// RCSID -- $RCSfile: JCCheckbox.java $ $Revision: 2.11 $ $Date: 2000/11/09 20:09:28 $ $Locker: $  Sitraka Inc.

package jclass.bwt;
import jclass.util.*;
import java.applet.*;
import java.awt.*;
import java.net.URL;

/**
 * A component that is used for representing 2 or more states. It consists
 * of an indicator and a label.
 * It contains two functions for building groups of checkboxes:
 * <ul>
 * <li><a href=#makeGroup>makeGroup</a> takes an array of labels and 
 * an array of values and creates a group
 * of radio buttons with associated values for each button.
 * <li><a href=#makeCollection>makeCollection</a> takes the same parameters 
 * and produces an array of individual check boxes
 * </ul><p>
 *
 * <strong>Behavior</strong><br>
 * <ul>
 * <li>When the checkbox is pressed, it will be displayed in its next state.
 * <li>If the checkbox is pressed and the mouse is dragged outside the button,
 * the checkbox will be displayed in its previous state, ie the activation
 * is temporarily cancelled until the mouse is dragged within the checkbox.
 * <li>If the mouse button is pressed and then released within the checkbox,
 * the checkbox is cycled to its next state, and an event is generated.
 * <li>If the space key is hit, the checkbox will cycle to its next 
 * state. This is equivalent to clicking it.
 * </ul><p>
 *
 * <strong>Properties</strong><p>
 * <table border>
 * <tr><th>Name</th>    <th>Method</th></tr><tr><td>
 * Background           </td><td><a href=jclass.bwt.JCComponent.html#setBackground>setBackground</a></td></tr><tr><td>
 * DoubleBuffer         </td><td><a href=jclass.bwt.JCComponent.html#setDoubleBuffer>setDoubleBuffer</a></td></tr><tr><td>
 * Font                 </td><td><a href=jclass.bwt.JCComponent.html#setFont>setFont</a></td></tr><tr><td>
 * Foreground           </td><td><a href=jclass.bwt.JCComponent.html#setForeground>setForeground</a></td></tr><tr><td>
 * HighlightColor       </td><td><a href=jclass.bwt.JCComponent.html#setHighlightColor>setHighlightColor</a></td></tr><tr><td>
 * HighlightThickness   </td><td><a href=jclass.bwt.JCComponent.html#setHighlightThickness>setHighlightThickness</a></td></tr><tr><td>
 * Indicator            </td><td><a href=#setIndicator>setIndicator</a></td></tr><tr><td>
 * IndicatorImageList   </td><td><a href=#setIndicatorImageList>setIndicatorImageList</a></td></tr><tr><td>
 * Insets               </td><td><a href=jclass.bwt.JCComponent.html#setInsets>setInsets</a></td></tr><tr><td>
 * Label                </td><td><a href=jclass.bwt.JCLabel.html#setLabel>setLabel</a></td></tr><tr><td>
 * NumStates            </td><td><a href=#setNumStates>setNumStates</a></td></tr><tr><td>
 * PreferredSize        </td><td><a href=jclass.bwt.JCComponent.html#setPreferredSize>setPreferredSize</a></td></tr><tr><td>
 * SelectColor          </td><td><a href=#setSelectColor>setSelectColor</a></td></tr><tr><td>
 * ShadowThickness  </td><td><a href=jclass.bwt.JCComponent.html#setShadowThickness>setShadowThickness</a></td></tr><tr><td>
 * State                </td><td><a href=#setState>setState</a></td></tr><tr><td>
 * Traversable          </td><td><a href=jclass.bwt.JCComponent.html#setTraversable>setTraversable</a></td></tr><tr><td>
 * UnselectColor        </td><td><a href=#setUnselectColor>setUnselectColor</a></td></tr><tr><td>
 * UserData             </td><td><a href=jclass.bwt.JCComponent.html#setUserData>setUserData</a></td></tr>
 * </table><p>
 *
 * <strong>Events</strong><p>
 * <table border><tr>
 * <th>Class</th> <th>Listener</th>   <th>Description</th></tr><tr><td>
 * JCItemEvent      </td><td><a href=#addItemListener>addItemListener</a></td> <td>Posted when the button's state changes</td></tr>
 * </table>
 */
public class JCCheckbox extends JCLabel implements JCItemSelectable {

public static final int INDICATOR_FILL	= BWTEnum.INDICATOR_FILL;
public static final int INDICATOR_CHECK	= BWTEnum.INDICATOR_CHECK;
public static final int INDICATOR_CIRCLE = BWTEnum.INDICATOR_CIRCLE;
public static final int INDICATOR_CROSS	= BWTEnum.INDICATOR_CROSS;
public static final int INDICATOR_DIAMOND = BWTEnum.INDICATOR_DIAMOND;
public static final int INDICATOR_IMAGE = BWTEnum.INDICATOR_IMAGE;

public static final int OFF = BWTEnum.OFF;
public static final int ON = BWTEnum.ON;
public static final int INDETERMINATE = BWTEnum.INDETERMINATE;

int					indicator = INDICATOR_CROSS;
Image[]				image_list;
URL[]				image_url_list;
Color				select_color = Color.white, unselect_color = Color.white;
JCCheckboxGroup 	group;
boolean				armed = false;
int					state = OFF;
int					num_states = 2;
int 				old_state;	// current state
int					new_state;	// new state if clickAction is called

private static final String base = "checkbox";
private static int nameCounter = 0;

/** Separation between the indicator and the label. */
protected int		label_offset = 5;

/** Calculated indicator size. */
protected int		ind_height, ind_width;

/** Calculated indicator position. */
protected int		ind_x, ind_y;

/** Whether setIndicator() has been called. */
protected boolean 	indicator_set = false;

/** List of JCItemEvent listeners */
protected JCVector  itemListeners = new JCVector(0);

/** Default indicator size. */
public final static int INDICATOR_SIZE = 13;

/** Creates an empty checkbox. No parameters are read from an HTML file. */
public JCCheckbox() {
	this(null, null, null);
}

/** Creates a checkbox with the specified label.
 * No parameters are read from an HTML file.
 */
public JCCheckbox(Object label) {
	this(label, null, null);
}

/** Creates a checkbox which reads parameters from the applet's HTML file.
 * @param applet the applet whose PARAM tags are to be read
 * @param name if this is not null, only parameters preceded by this name are read
 * @see java.applet.Applet#getParameter
 */
public JCCheckbox(Object label, Applet applet, String name) {
	super(label, applet, name);
	if (name == null) 
		setName(base + nameCounter++);
	alignment = MIDDLELEFT;
	traversable = true;
	highlight = 2;
	if (getClass().getName().equals("jclass.bwt.JCCheckbox"))
		 getParameters(applet);
	/* JDK110_START */
	enableEvents(JCAWTEvent.MOUSE_MOTION_EVENT_MASK);
	/* JDK110_END */
}

/**
 * Creates a button as part of a CheckboxGroup.
 * @param label the checkbox's label
 * @param group the "owner" of the box. This can be null.
 * @param value the userdata associated with the checkbox. This can be
 * retrieved by calling getUserDataInt(). If the checkbox is currently
 * selected, calling the group's getValue() method will return this value.
 * @see JCCheckboxGroup#getValue
 * @see jclass.base.BaseComponent#getUserDataInt
 */
public JCCheckbox(Object label, JCCheckboxGroup group, int value) {
    this(label, null, null);
	this.group = group;
	if (group != null)
		group.add(this);
	setUserData(new Integer(value));
}

/**
 * Creates a button with the specified label, set to the
 * specified state, and in the specified check box group.
 * @param label the label on the Checkbox
 * @param state is the initial state of this Checkbox
 * @param group the CheckboxGroup this Checkbox is in
 * @see #setState
 */
public JCCheckbox(Object label, int state, JCCheckboxGroup group) {
    this(label, null, null);
	this.group = group;
	if (group != null)
		group.add(this);
	setState(state);
}

/** Reads the parameter values from the HTML page using the component's applet.
 * The values will override those previously set.
 */
protected void getParameters() {
	super.getParameters();
	CheckboxConverter.getParams(this);
}

/**
 * This static function creates a group of buttons.
 * @param labels a list of labels for each checkbox
 * @param values a list of values for each checkbox (may be null)
 * @param radio_behavior if true, setting one checkbox will deselect any other
 * @return a JCCheckboxGroup containing the check boxes
 * @see JCCheckboxGroup#setRadioBehavior
 */
public static JCCheckboxGroup makeGroup(Object labels[],
										int values[], boolean radio_behavior) {
    if (labels == null)
		return null;
	if (values == null)
		values = new int[labels.length];

    JCCheckboxGroup group = new JCCheckboxGroup();
    for (int i = 0; i < Math.min(labels.length, values.length); i++) 
        group.add(new JCCheckbox(labels[i], group, values[i]));
	group.setRadioBehavior(radio_behavior);
    return group;
}

/** Gets the Indicator value.
 * @see #setIndicator
 */
public int getIndicator() { return indicator; }

/** Sets the indicator style:
<pre>
INDICATOR_FILL     Indicator is drawn, and its shadows are switched
INDICATOR_CHECK    Checkmark 
INDICATOR_CIRCLE   Circle 
INDICATOR_CROSS    X (default)
INDICATOR_DIAMOND  Diamond
INDICATOR_IMAGE    Image specified by <a href=#setImageList>ImageList</a>
</pre><p>
* <strong>HTML param name/value:</strong> "Indicator"/enum
* @exception IllegalArgumentException If an invalid value is set
* @see #setState
* @see jclass.util.JCConverter#toEnum
*/
public synchronized void setIndicator(int v) {
	CheckboxConverter.checkIndicator(v);
	indicator = v;
	indicator_set = true;
	layout();
	repaint();
}

/** Gets the IndicatorImageList value.
 * @see #setIndicatorImageList
 */
public Image[] getIndicatorImageList() { return image_list; }

/** Sets a list of indicator images to be used if Indicator is INDICATOR_IMAGE.
 * If this property is not set, no indicator is displayed.
 * The position of the image within the list is specified by the State value.
 * <strong>HTML param name/value:</strong> "IndicatorImageList"/comma-separated list of images<p>
 * @see jclass.util.JCConverter#toImageList
 * @see #setIndicator
 * @see #setState
 */
public synchronized void setIndicatorImageList(Image[] v) {
	image_list = v;
	repaint();
}

/** Gets the IndicatorImageList value.
 * @see #setIndicatorImageList
 */
public URL[] getIndicatorImageURLList() { return image_url_list; }

/** Sets a list of indicator images to be used if Indicator is INDICATOR_IMAGE.
 * If this property is not set, no indicator is displayed.
 * The position of the image within the list is specified by the State value.
 * <strong>HTML param name/value:</strong> "IndicatorImageList"/comma-separated list of images<p>
 * @see jclass.util.JCConverter#toImageList
 * @see #setIndicator
 * @see #setState
 */
public synchronized void setIndicatorImageURLList(URL[] list) {
	image_url_list = list;
	if (list == null) return;
	image_list = new Image[list.length];
	for (int i=0; i < list.length; i++)
		image_list[i] = getToolkit().getImage(list[i]);
	repaint();
}

/** Gets the NumStates value.
 * @see #setNumStates
 */
public int getNumStates() { return num_states; }

/** Sets the maximum number of states that can be set (default: 2). By default, 
 * the button can be set to OFF and ON.<p>
 * <strong>HTML param name/value:</strong> "NumStates"/int
 */
public synchronized void setNumStates(int v) {
	num_states = v;
}

/** Gets the SelectColor value.
 * @see #setSelectColor
 */
public Color getSelectColor() { return select_color; }

/** The color of the indicator background when the button is set
 * (default: Color.white). If set to null, the indicator will be filled
 * with the current background color.<p>
 * This value only applies to FILL and DIAMOND indicators. All other
 * indicators are filled with a white background.<p>
 * <strong>HTML param name/value:</strong> "SelectColor"/Color<p>
 * @see #setIndicator
 * @see jclass.util.JCConverter#toColor
 */
public synchronized void setSelectColor(Color v) {
	select_color = v;
	if (state == ON) 
		repaint();
}

/** Gets the UnselectColor value.
 * @see #setUnselectColor
 */
public Color getUnselectColor() { return unselect_color; }

/** The color of the indicator background when the button is not set
 * (default: Color.white). If set to null, the indicator will be filled
 * with the current background color.<p>
 * This value only applies to FILL and DIAMOND indicators. All other
 * indicators are filled with a white background.<p>
 * <strong>HTML param name/value:</strong> "UnselectColor"/Color<p>
 * @see #setIndicator
 * @see jclass.util.JCConverter#toColor
 */
public synchronized void setUnselectColor(Color v) {
	unselect_color = v;
	if (state == OFF) 
		repaint();
	repaint();
}

/** Gets the current state.
 * @see #setState
 */
public int getState() { return state; }

/** Sets the button's state. For convenience,
 * the following ints are provided: OFF (0), ON (1) and INDETERMINATE (2).
 * The number of states is specified by NumStates.
 * As the user clicks on the button, the value 
 * of State increments through the states, and then back to 0 (OFF).
 * The button's clickAction() method is not called, and thus no events
 * are posted.<p>
 * <strong>HTML param name/value:</strong> "State"/int, "ON", or "OFF"<p>
 * @see #setNumStates
 * @see #getState
 * @see jclass.util.JCConverter#toInt
 */
public void setState(int state) {
	if (group != null && state == ON) {
			group.setCurrent(this);
	}

	synchronized (this) {
		// Prevent the button from being unset if group's radio behavior is set
		if (group != null && group.radio_behavior && group.getCurrent() == this) {
			state = ON;
		}
		this.state = state;
		Graphics gc = getGraphics();
/* SWING_START */
		clipGCToAncestors(gc);
/* SWING_END */
		drawIndicator(gc);
	}
}

/** Sets the button's state, optionally specifying that the button's
 * events are to be posted.
 * @param notify if true the button's clickAction() method is called
 * @see #clickAction
 */
public void setState(int state, boolean notify) {
	if (notify) {
		new_state = state;
		clickAction(null);
	} else {
		setState(state);
	}
}

/**
 * Returns the checkbox group.
 * @see #setCheckboxGroup
 */
public JCCheckboxGroup getCheckboxGroup() { return group; }

/**
 * Sets this button to the specified group.
 * The previous group's current button is unset.
 */
public void setCheckboxGroup(JCCheckboxGroup g) {
	if (group != null) 
	    group.setCurrent(null);
	group = g;
	group.manage(this);
}

/** Set the indicator size. */
protected void setIndicatorSize() {
	ind_height = ind_width = 0;
	if (indicator == INDICATOR_IMAGE && image_list != null) {
		for (int i=0; i < image_list.length; i++)
			if (image_list[i] != null) {
				ind_width = Math.max(ind_width, image_list[i].getWidth(null));
				ind_height = Math.max(ind_height, image_list[i].getHeight(null));
			}
	}
	if (ind_width == 0) ind_width = INDICATOR_SIZE;
	if (ind_height == 0) ind_height = INDICATOR_SIZE;
}

/**
 * Returns an array (length 1) containing the selected index (0),
 * or null if the checkbox is not set.
 * @see JCItemSelectable
 */
public int[] getSelectedIndexes() {
	if (state != OFF) {
		int[] indexes = new int[1];
		indexes[0] = 0;
		return indexes;
	}
	return null;
}

/**
 * Returns the an array (length 1) containing the checkbox
 * label, or null if the checkbox is not set.
 * @see JCItemSelectable
 */
public Object[] getSelectedObjects() {
	if (state != OFF) {
		Object[] items = new Object[1];
		items[0] = label;
		return items;
	}
	return null;
}

/** Calculates indicator size. */
public void addNotify() {
	super.addNotify();
	setIndicatorSize();
}

/** Lays out the label's internal elements. */
public synchronized void layout() {
	if (!isDisplayable())
		return;
	setIndicatorSize();
	int w = label_width, h = label_height;
	label_width = w + ind_width + label_offset;
	label_height = Math.max(h, ind_height);
	super.layout();
	ind_x = label_rect.x;
	ind_y = label_rect.y;

	// Center the indicator and label relative to each other
	if (ind_height < h) 
		ind_y = label_rect.y + (h - ind_height) / 2;

	label_rect.x += ind_width + label_offset;
	label_width = w;
	label_height = h;
}

/** Draws or clears the highlight rectangle (called by paint).
 * @param on if true, draws the highlight rectangle and a dashed rectangle
 * around the label; otherwise clears the rect
 */
protected void drawHighlight(Graphics gc, boolean on) {
	int w = size().width, h = size().height;

	Color c = on ? highlight_color : getBackground();
	if (c == null) c = getForeground();
	gc.setColor(c);
	if (on)
		BWTUtil.drawDashedRect(gc, 0, 0, w-1, h-1);
	else
		gc.drawRect(0, 0, w-1, h-1);
}

/**
 * Draws indicator and label
 */
protected void paintComponent(Graphics gc) {
	if (needs_layout) layout();
	drawValue(gc, label);
	drawIndicator(gc);
}

/** Draws the indicator. */
protected void drawIndicator(Graphics gc) {
	if (isShowing() && isDisplayable()) 
		Indicator.draw(this, gc);
}

protected int preferredWidth() {
	return label_width + ind_width + label_offset;
}

protected int preferredHeight() {
	return Math.max(label_height, ind_height);
}

/**
 * Adds the specified item listener to receive item events from this checkbox.
 * @see JCItemEvent
 */ 
public void addItemListener(JCItemListener l) {
	itemListeners.addUnique(l);
}

/**
 * Removes the specified item listener so it no longer receives
 * item events from this checkbox.
 */ 
public void removeItemListener(JCItemListener l) {
	itemListeners.removeElement(l);
}

/** Temporarily changes the state.
 * The state is not set permanently until clickAction() is called,
 * since the user may release the mouse button while the cursor is outside
 * the checkbox.
 * @see #clickAction
 */
public void armAction(Event ev) {
	armed = true;
	old_state = state;
	state = (state + 1) % num_states;
	new_state = state;
	Graphics gc = getGraphics();
/* SWING_START */
	clipGCToAncestors(gc);
/* SWING_END */
	drawIndicator(gc);
}

/** Changes the button's state and posts an JCItemEvent. 
 * @see #setState
 * @see #addItemListener
 */
public void clickAction(Event ev) {
	setState(new_state);
	if (ev == null)
		ev = new Event(this, 0, null);

	int reason = (state == OFF) 
		? JCItemEvent.DESELECTED : JCItemEvent.SELECTED;
	JCItemEvent item_ev = new JCItemEvent(this, JCItemEvent.ITEM_STATE_CHANGED, label, reason);
	for (int i=0; i < itemListeners.size(); i++) 
		((JCItemListener)itemListeners.elementAt(i)).itemStateChanged(item_ev);
}

/** If the mouse was pressed and is moved outside the button,
 * the state is reset to its original value and the indicator is redrawn.
 */
public boolean mouseExit(Event ev, int x, int y) {
    if (armed) {
		state = old_state;
		repaint();
	}
	return armed;
}

/** If the mouse was pressed and is moved inside the button,
 * the state is reset to its original value and the indicator is redrawn.
 */
public boolean mouseEnter(Event ev, int x, int y) {
    if (armed) {
		state = new_state;
		repaint();
	}
	return armed;
}

/** Calls armAction if btn1 was pressed.
 * @see #armAction
 */
public boolean mouseDown(Event ev, int x, int y) {
	// Ignore if user just made a non-Unix combobox selection (bug 2048)
	if (ev.when - popdown_event_timestamp < 50 
			&& JCEnvironment.getOS() != JCEnvironment.OS_UNIX)
		return true;
	if (BWTUtil.getMouseButton(ev) != 1) return false;
	if (armed) return true;
	if (!isEnabled()) return false;
	super.mouseDown(ev, x, y);
	armAction(ev);
	return true;
}

/** If the cursor is within the button, calls clickAction
 * @see #clickAction
 */
public boolean mouseUp(Event ev, int x, int y) {
	if (inside(x, y)) {
		clickAction(ev);
		armed = false;
	}
	else if (armed) {
		armed = false;
		repaint();
	}
	return true;
}

/** If key is space, calls armAction, clickAction
 * @see #armAction
 * @see #clickAction
 */
public boolean keyDown(Event ev, int key) {
	super.keyDown(ev, key);
	if ((char)ev.key == ' ') {
		armAction(ev);
		clickAction(ev);
		armed = false;
		getToolkit().sync();
		return true;
	}
	return false;
}

}
