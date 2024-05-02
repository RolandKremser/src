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
// RCSID -- $RCSfile: JCButton.java $ $Revision: 2.16 $ $Date: 2000/11/09 20:09:26 $ $Locker: $  Sitraka Inc.

package jclass.bwt;
import jclass.util.*;
import java.applet.*;
import java.awt.*;

/**
 * A component that displays a button with multiple lines of read-only text and/or images.<p>
 * <strong>Behavior</strong><br>
 * <ul>
 * <li>When the button is pressed, its shadows are reversed to display a "pushed"
 * appearance.
 * <li>If the button is pressed and the mouse is dragged outside the button,
 * the button will be displayed as normal, ie the press is cancelled.
 * <li>If the mouse is pressed and then released within the button,
 * the button is activated (ie an event is generated).
 * <li>If the RETURN or space key is hit, the button is momentarily displayed
 * as pressed. This is equivalent to clicking it.
 * </ul><p>
 *
 * <strong>Properties</strong><p>
 * <table border>
 * <tr><th>Name</th>    <th>Method</th></tr><tr><td>
 * Alignment           </td><td><a href=jclass.bwt.JCLabel.html#setAlignment>setAlignment</a></td></tr><tr><td>
 * ArmLabel            </td><td><a href=#setArmLabel>setArmLabel</a></td></tr><tr><td>
 * Background          </td><td><a href=jclass.bwt.JCComponent.html#setBackground>setBackground</a></td></tr><tr><td>
 * DoubleBuffer        </td><td><a href=jclass.bwt.JCComponent.html#setDoubleBuffer>setDoubleBuffer</a></td></tr><tr><td>
 * Font                </td><td><a href=jclass.bwt.JCComponent.html#setFont>setFont</a></td></tr><tr><td>
 * Foreground          </td><td><a href=jclass.bwt.JCComponent.html#setForeground>setForeground</a></td></tr><tr><td>
 * HighlightColor      </td><td><a href=jclass.bwt.JCComponent.html#setHighlightColor>setHighlightColor</a></td></tr><tr><td>
 * HighlightThickness  </td><td><a href=jclass.bwt.JCComponent.html#setHighlightThickness>setHighlightThickness</a></td></tr><tr><td>
 * Insets              </td><td><a href=jclass.bwt.JCComponent.html#setInsets>setInsets</a></td></tr><tr><td>
 * Label               </td><td><a href=jclass.bwt.JCLabel.html#setLabel>setLabel</a></td></tr><tr><td>
 * PreferredSize       </td><td><a href=jclass.bwt.JCComponent.html#setPreferredSize>setPreferredSize</a></td></tr><tr><td>
 * ShadowThickness  </td><td><a href=jclass.bwt.JCComponent.html#setShadowThickness>setShadowThickness</a></td></tr><tr><td>
 * Traversable         </td><td><a href=jclass.bwt.JCComponent.html#setTraversable>setTraversable</a></td></tr><tr><td>
 * UserData            </td><td><a href=jclass.bwt.JCComponent.html#setUserData>setUserData</a></td></tr>
 * </table><p>
 *
 * <strong>Events</strong><p>
 * <table border><tr>
 * <th>Class</th> <th>Listener</th>   <th>Description</th></tr><tr><td>
 * JCActionEvent    </td><td><a href=#addActionListener>addActionListener</a></td> <td>Posted when the button is pressed and then released</td></tr><tr><td>
 * JCButtonEvent  </td><td><a href=#addButtonListener>addButtonListener</a></td> <td>Posted when the button is pressed and released</td></tr>
 * </table>
 */
public class JCButton extends JCLabel {

/** Amount label is draw offset when the button is armed. */
Object 				arm_label;
boolean				armed = false;
protected int		old_shadowtype;
String 				actionCommand;
boolean				inside_button = false;
boolean				is_action_button = false;

private static final boolean TRACE = false;

/** List of action listeners */
protected JCVector  actionListeners = new JCVector(0);

/** List of JCButtonEvent listeners */
protected JCVector  buttonListeners = new JCVector(0);
    
private static final String base = "button";
private static int nameCounter = 0;

/** Amount that the label is drawn offset when the button is pressed. */
protected int		arm_offset = 1;

/** Creates an empty button. No parameters are read from an HTML file. */
public JCButton() {
	this(null, null, null);
}

/** Creates a button with the specified label.
 * No parameters are read from an HTML file.
 */
public JCButton(Object label) {
	this(label, null, null);
}

/** Creates a button with a label constructed from a String and an Image.
 * The label's name is set to the string.
 * @param layout the relative position of the string with respect to the image:
 * BWTEnum.STRING_LEFT, STRING_RIGHT, STRING_TOP or STRING_BOTTOM
 */
public JCButton(String s, Image image, int layout) {
	this(new JCString(s, image, layout), null, null);
	setName(s);
}

/** Creates a button with a label constructed from a String and an Image file. 
 * @param applet the applet that is loading the image.  If the applet
 * is in a browser, Applet.getImage() is used in order to take
 * advantage of Applet.getDocumentBase()
 * @param image the file containing the image to be loaded; if an http protocol
 * is not specified (a ":" is not present), the current document base or
 * working directory is prepended to the file name
 * @param layout the relative position of the string with respect to the image:
 * BWTEnum.STRING_LEFT, STRING_RIGHT, STRING_TOP or STRING_BOTTOM
 */
public JCButton(String s, String image, Applet applet, int layout) {
	this(null, null, null);
	setLabel(new JCString(s, conv.toImage(applet, image), layout));
	insets = new Insets(2,5,2,5);
}

/** Creates a button which reads parameters from the applet's HTML file.
 * @param label the button's label
 * @param applet the applet whose PARAM tags are to be read
 * @param name if this is not null, only parameters preceded by this name are read
 * @see JCLabel#setLabel
 * @see java.applet.Applet#getParameter
 */
public JCButton(Object label, Applet applet, String name) {
	super(label, applet, name);
	if (name == null) setName(base + nameCounter++);
	border_style = jclass.base.Border.CONTROL_OUT;
	traversable = true;
	highlight = 1;
	border = 2;
	if (label == null || label instanceof String)
		insets = new Insets(0,5,0,5);
	else
		insets = new Insets(2,5,2,5);
	if (getClass().getName().equals("jclass.bwt.JCButton"))
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
	ButtonConverter.getParams(this);
}

/**
 * Returns whether the button is considered an actionButton 
 * @see #setIsActionButton
 * @see JCContainer#setActionButton
 */
public boolean getIsActionButton() { return is_action_button; }

/**
 * Sets whether the button is considered a actionButton or not.
 * When set the highlight rectangle is always rendered.  Note that the
 * highlightThickness of the component must be >0 for this to show.
 * @see JCContainer#setActionButton
 * @see jclass.base.BaseComponent#setHighlightThickness
 */
public void setIsActionButton(boolean v) {
	if (v != is_action_button) {
		is_action_button = v;
		repaint();
	}
}

/**
 * Gets the button's ArmLabel.
 * @see #setArmLabel
 */
public Object getArmLabel() { return arm_label; }

/**
 * Sets the label that is displayed when the button is pressed.
 * This may be a String, JCString, or any object (in which case the object's
 * toString() method is called to obtain a string).
 * If this value is not set, the displayed label is not changed.<p>
 * <strong>HTML param name/value:</strong> "ArmLabel"/string<p>
 * @see jclass.util.JCString
 * @see jclass.util.JCConverter#toJCString
 */
public void setArmLabel(Object v) {
	arm_label = v;
	if (armed) {
		layout();
		repaint();
	}
}

/**
 * Sets the command name of the action event fired by this button.
 * By default this will be set to the label of the button.
 */
public void setActionCommand(String command) { actionCommand = command; }

/**
 * Returns the command name of the action event fired by this button.
 * @see #setActionCommand
 */
public String getActionCommand() {
	return (actionCommand == null ? ""+label : actionCommand);
}

/**
 * Adds the specified action listener to receive action events from this button.
 * @see JCActionEvent
 */ 
public void addActionListener(JCActionListener l) {
	actionListeners.addUnique(l);
}

/**
 * Removes the specified action listener so it no longer receives
 * action events from this button.
 * @see #addActionListener
 */ 
public void removeActionListener(JCActionListener l) {
	actionListeners.removeElement(l);
}

/**
 * Adds the specified JCButtonEvent listener to receive arm/disarm events.
 * @see JCButtonEvent
 */ 
public void addButtonListener(JCButtonListener l) {
	buttonListeners.addUnique(l);
}

/**
 * Removes the specified listener so it no longer receives
 * JCButtonEvents from this button.
 * @see #addButtonListener
 */ 
public void removeButtonListener(JCButtonListener l) {
	buttonListeners.removeElement(l);
}

/** Displays the button as armed (reversed top and bottom shadow colors),
 * and posts a JCButtonEvent.
 * @see #addButtonListener
 */
public void armAction(Event ev) {
	old_shadowtype = border_style;
	border_style = BWTEnum.SHADOW_IN;
	armed = true;
	JCButtonEvent e = (buttonListeners.size() > 0) ? new JCButtonEvent(ev) : null;

	for (int i=0; i < buttonListeners.size(); i++) 
		((JCButtonListener)buttonListeners.elementAt(i)).buttonArmBegin(e);
	Dimension size = size();
	paintImmediately(0, 0, size.width, size.height);
	border_style = old_shadowtype;
	for (int i=0; i < buttonListeners.size(); i++) 
		((JCButtonListener)buttonListeners.elementAt(i)).buttonArmEnd(e);
}

/** Paints the button with its normal appearance,
 * and posts a JCButtonEvent
 * @see @addButtonListener
 */
public void disarmAction(Event ev) {
	JCButtonEvent e = (buttonListeners.size() > 0) ? new JCButtonEvent(ev) : null;
	for (int i=0; i < buttonListeners.size(); i++) 
		((JCButtonListener)buttonListeners.elementAt(i)).buttonDisarmBegin(e);

	if (armed && arm_label != null)
		layout(label);
	armed = false;
	border_style = old_shadowtype;

	Dimension size = size();
	paintImmediately(0, 0, size.width, size.height);

	for (int i=0; i < buttonListeners.size(); i++) 
		((JCButtonListener)buttonListeners.elementAt(i)).buttonDisarmEnd(e);
}

/** Posts an JCActionEvent.
 * @see #addActionListener
 * @see JCActionEvent
 */
public void clickAction(Event ev) {
	String cmd = getActionCommand();
	if (ev == null)
		ev = new Event(this, 0, cmd);

	JCActionEvent a = new JCActionEvent(this, ev.id, cmd, ev.modifiers);
	for (int i=0; i < actionListeners.size(); i++) 
		((JCActionListener)actionListeners.elementAt(i)).actionPerformed(a);
}

/** Lays out the label's internal elements. */
public synchronized void layout() {
	super.layout();
	if (!isDisplayable())
		return;

	/* Shift the rect up by 1 pixel (the button's size was increased to
	 * allow the label to be displayed offset when the button is armed).
	 */
	if (BWTUtil.isRight(alignment))
		label_rect.x -= arm_offset;

	else if (BWTUtil.isCenter(alignment))
		label_rect.x -= arm_offset/2;

	if (BWTUtil.isMiddle(alignment))
		label_rect.y -= arm_offset/2;

	else if (BWTUtil.isBottom(alignment))
		label_rect.y -= arm_offset;
}

/** Draws the button's label or arm label (if it is pressed).
 */
protected void drawValue(Graphics gc, Object value) {
	if (armed && arm_label != null) {
		layout(arm_label);
		gc.translate(arm_offset, arm_offset);
		super.drawValue(gc, arm_label);
	}
	else {
		if (armed)
			gc.translate(arm_offset, arm_offset);
		super.drawValue(gc, value);
	}
	if (armed)
		gc.translate(-arm_offset, -arm_offset);
}

/**
 * Draws or clears the highlight rectangle (called by paint).
 * @param on if true, draws the highlight rectangle and a dashed rectangle
 * around the label; otherwise clears the rect
 */
protected void drawHighlight(Graphics gc, boolean on) {
	super.drawHighlight(gc, on);
	int x = 4, y = 4, w = size().width, h = size().height;

	Color c = on ? highlight_color : getBackground();
	if (c == null) c = getForeground();
	gc.setColor(c);
	if (on) {
		// draw dashed focus line
		BWTUtil.drawDashedRect(gc, x, y, w-2*x-1, h-2*y-1);
	}
	else {
		// erase dashed focus line
		gc.drawRect(x, y, w-2*x-1, h-2*y-1);
	}

	if (is_action_button && highlight > 0) {
		// if this is the defaultButton and we have room to draw the highlight
		x = 0;
		y = 0;
		w = size().width;
		h = size().height;
		gc.setColor(getHighlightColor() != null
					? getHighlightColor() : Color.black);
		for (int i = 0; i < highlight; i++, x++, y++, w -= 2, h -= 2) {
			gc.drawRect(x, y, w - 1, h - 1);
		}
	}
}

/** Indicates if the button is armed. */
public boolean isArmed() {
	return armed;
}

/** If the mouse was pressed and is moved back inside the button,
 * armAction is called.
 * @see #armAction
 */
public boolean mouseEnter(Event ev, int x, int y) {
	if (TRACE) {
		System.out.println(Thread.currentThread().getName() + 
			" mouseEnter button: " + this.getName() + 
			"\tisEnabled: " + isEnabled() + " armed: " + armed);
	}
	inside_button = true;
	if (!isEnabled()) return false;

	if (!armed) {
		return false;
	}
	armAction(ev);
	return true;
}

/** If the mouse was pressed and is moved outside the button,
 * disarmAction is called.
 * @see #disarmAction
 */
public boolean mouseExit(Event ev, int x, int y) {
	inside_button = false;
	if (!armed) {
		return false;
	}
	disarmAction(ev);
	armed = true;
	return true;
}

public boolean mouseMove(Event ev, int x, int y) { return true; }

/**
 *	Calls mouseEnter if cursor is dragged back in the button.  
 *  Calls mouseExit if cursor is moved out of the button.
 * 	It is necessary because VMs has different implementation on the sequence
 *	of mouse events.
 */
public boolean mouseDrag(Event ev, int x, int y) { 

	// if the button is already inside, don't call mouse enter.
	// if the button is already outside, don't call mouse exit. 
	if (inside(x,y) && !inside_button) {
		mouseEnter(ev, x, y);
	} else if (!inside(x,y) && inside_button) {
		mouseExit(ev, x, y);
	}
	return true; 
}

/** Calls armAction if btn1 was pressed.
 * @see #armAction
 */
public boolean mouseDown(Event ev, int x, int y) {
	if (BWTUtil.getMouseButton(ev) != 1) return false;
	if (armed) return true;
	if (!inside(x, y)) return false;
	if (!isEnabled()) return false;
	super.mouseDown(ev, x, y);
	armAction(ev);
	return true;
}

/** If the cursor is within the button, calls disarmAction, clickAction.
 * @see #clickAction
 * @see #disarmAction
 */
public boolean mouseUp(Event ev, int x, int y) {
	if (BWTUtil.getMouseButton(ev) != 1) return false;
	if (TRACE) {
		System.out.println(Thread.currentThread().getName() + 
			" mouseUp button: " + this.getName() + 
			"\t inside: " + inside(x,y) + " armed: " + armed);
	}
	if (inside(x, y) && armed) {
		disarmAction(ev);		// order is important
		clickAction(ev);
	}
	else if (armed) {
		armed = false;
		repaint();
	}
	if (TRACE) {
		System.out.println(Thread.currentThread().getName() + 
			" leaving mouseUp button: " + this.getName() + 
			"\t inside: " + inside(x,y) + " armed: " + armed);
	}
	return true;
}

/** If key is space or RETURN, calls armAction, disarmAction, clickAction.
 * @see #armAction
 * @see #clickAction
 * @see #disarmAction
 */
public boolean keyDown(Event ev, int key) {
	super.keyDown(ev, key);
	if (ev.key == 10 || (char)ev.key == ' ') {
		armAction(ev);
		getToolkit().sync();
		try {
			Thread.sleep(50);
		} catch(Exception e) {}
		disarmAction(ev);		// order is important
		clickAction(ev);
		getToolkit().sync();
		return true;
	}
	return false;
}

/** Returns the label or arm label's width, whichever is larger.
 * 1 pixel is added to allow for the label's being drawn offset when
 * the button is armed.
 */
protected int preferredWidth() {
	int w = Math.max(label_width+arm_offset,
					 BWTUtil.getWidth(arm_label, this)+arm_offset);
	return Math.max(20, w);
}

/** Returns the label or arm label's height, whichever is larger.
 * 1 pixel is added to allow for the label's being drawn offset when
 * the button is armed.
 */
protected int preferredHeight() {
	int h = Math.max(label_height+arm_offset,
					 BWTUtil.getHeight(arm_label, this)+arm_offset);
	return Math.max(20, h);
}

}
