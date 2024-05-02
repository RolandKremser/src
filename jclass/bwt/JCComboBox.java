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
// RCSID -- $RCSfile: JCComboBox.java $ $Revision: 2.96 $ $Date: 2000/11/09 20:10:25 $ $Locker: $  Sitraka Inc.

package jclass.bwt;

import jclass.util.*;
import jclass.base.*;
import java.awt.*;
import java.applet.*;
/* JDK110_START */
import java.io.Serializable;
import java.awt.event.*;
import jclass.cell.*;
/* JDK110_END */
/* SWING11_START
import com.sun.java.swing.JWindow;
import com.sun.java.swing.JDialog;
import com.sun.java.swing.JComponent;
import com.sun.java.swing.SwingUtilities;
 SWING11_END */
/* SWING12_START */
import javax.swing.JWindow;
import javax.swing.JDialog;
import javax.swing.JComponent;
import javax.swing.SwingUtilities;
/* SWING12_END */

class ComboField extends JCTextField {

JCComboBox combo;

ComboField(JCComboBox combo) {
	super(); this.combo = combo;
}

protected void drawHighlight(Graphics gc, boolean on) {
	if (!isEnabled()) on = false;
	Color old_highlight = highlight_color;
	if (!on && combo.style == BWTEnum.COMBOBOX_SIMPLE) {
		highlight_color = combo.getBackground();
		on = true;
	}
	else if (!on) {
		highlight_color = getBackground();
		on = true;
	}
	super.drawHighlight(gc, on);
	highlight_color = old_highlight;
}

/** Show the list if the user clicks in an uneditable field. */
public boolean mouseDown(Event ev, int x, int y) {
  //Bug 9511: added check for the combo box being enabled to prevent it from
  //droping down if it is disabled.
	if (combo.style != BWTEnum.COMBOBOX_SIMPLE && combo.isEnabled() && !isEditable()) {
		combo.showListAction(null);

		// This line was added to prevent any responses from mouse click events
		// when clicked within the text portion on the drop down ComboBox
		combo.hidePopup();
		return true;
	}
	return super.mouseDown(ev, x, y);
}

/** Bug: 8518. This method was added in order to set the cursor position to the
 *	beginning of the string initially. Sets the caret position to zero (beginning).
 */
public void setText(String s) {
	super.setText(s);
	setCaretPosition(0);
}

/** Pass the keyDown event to the combobox to allow autoSearch.
 */
public boolean keyDown(Event ev, int key) {
	if (ev.key != Event.DOWN && ev.key != Event.UP) {
		// Only call super when key is not up/down arrow to avoid focus lost.
		super.keyDown(ev, key);
	}
	return combo.keyDown(ev, key);
}

/** It is the same as JCTextComponent's insertChar except that
 * rather than beeping for non-editable field, it does nothing.
 */
protected boolean insertChar(Event ev, int key) {
	return combo.isEditable() ? super.insertChar(ev, key) : true;
}
}	// ComboField

class ComboDialog extends
/* AWT_START 
Dialog {
 AWT_END */
/* SWING_START */
JDialog {
/* SWING_END */

JCComboBox combo;

ComboDialog(JCComboBox combo, Frame parent) {
	super(parent, false);
	this.combo = combo;
}

/** Calls hide if a WINDOW_DESTROY event is passed. */
public boolean handleEvent(Event ev) {
	if (ev.id == Event.WINDOW_DESTROY) {
		combo.hidePopup();
		return true;
	}
	return super.handleEvent(ev);
}
}	// ComboDialog

class ComboListComponent extends JCListComponent {

ComboList combo_list;
private static final boolean TRACE = false;

ComboListComponent(ComboList list) {
	super();
	combo_list = list;
/* JDK110_START */
	enableEvents(AWTEvent.MOUSE_MOTION_EVENT_MASK);
/* JDK110_END */
}

/**
 * Moves selection up or down if arrow key hit; posts an JCActionEvent if ENTER
 * is hit; otherwise selects item which starts with the character.
 * Hides the popup if ESC hit.
 */
public boolean keyDown(Event ev, int key) {
	if (key == BWTEnum.ESC) {
		combo_list.box.hidePopup();
		return true;
	}
	return super.keyDown(ev, key);
}

public boolean lostFocus(Event ev, Object what) {
/* JDK110_START */
	combo_list.box.last_to_lose_focus = combo_list.box;
	long dt = System.currentTimeMillis() - combo_list.box.list_poppedup_time;
	if (TRACE) {
		System.out.println("ComboListComponent.lostFocus()");
		System.out.println("\tdt: " + dt +
					" combo_list.box.list_poppedup: " + combo_list.box.list_poppedup +
					" combo_list.exited: " + combo_list.exited);
	}
	if (
		(combo_list.box.list_poppedup && !combo_list.exited) ||
		// In VisualAge and JDK11, when the arrow button of the
		// JCComboBox is pressed, actionPerformed is called and
		// then the focus is set back to the arrow button from
		// the popup list.  It is why lostFocus() is called
		// here.  Check the event time difference.  If it is small (the
		// number is derived from trial and error),
		// the list should stay up and has the focus.
		(JCEnvironment.isVisualAge() && dt >= 0 && dt < 600)
	) {
		// Request the focus back to the list component
		// so lostFocus() will be called again.
		this.requestFocus();
	}

	// This was commented out in order for lostFocus() not to be called
	// again
	/*
	else {
		combo_list.lostFocus();
	}
	/*

/* JDK110_END */
/* JDK102_START
	combo_list.lostFocus();
 JDK102_END */
	return super.lostFocus(ev, what);
}

/** Selects a row in a non-SIMPLE combobox
 * as the user moves the mouse over the list.
 */
public boolean mouseMove(Event ev, int x, int y) {
	if (combo_list.box.style != JCComboBox.COMBOBOX_SIMPLE) {
		int row = eventToRow(ev, true);
		if (row != BWTEnum.NOTFOUND && row != getSelectedIndex()) {
			select(row, false);
			// Ensure that an event is sent if user clicks the row
			did_selection = false;
		}
	}
	return super.mouseMove(ev, x, y);
}

public boolean mouseUp(Event ev, int x, int y) {
	boolean ret = super.mouseUp(ev, x, y);
	combo_list.box.setTextAction(combo_list.box.getSelectedItem());
	combo_list.box.hidePopup();
	return ret;
}

/**
 * In the case of the ComboBox, we do not want to clip the highlight around
 * the list if the style is drop down.
 */
/* SWING_START */
protected void clipGCToAncestors(Graphics gc) {
	if (combo_list.box.style != BWTEnum.COMBOBOX_DROPDOWN) {
		super.clipGCToAncestors(gc);
	}
}
/* SWING_END */

}	// ComboListComponent


/**
 *	In JDK11, mouse events are not passed from the current component to
 *	its container.  Since we are interested in the mouse
 *	motion of a popup list (JCList), i.e. including JCListComponent, JCScrollbar
 * 	and the JCArrowButtons in the scrollbar, we need to a listener class to
 * 	to listen to mouse and mouse motion events for all these components.
 */
/* JDK110_START */
class ComboListMouseListener extends MouseAdapter implements MouseMotionListener {

ComboList list;

public ComboListMouseListener(ComboList list) {
	super();
	this.list = list;
}

public void mouseEntered(MouseEvent e) {
	Event ev = new Event(this, e.getWhen(), e.getID(),
				 e.getX(), e.getY(), 0, e.getModifiers());
	// Determine if cursor is moved outside the JCList.
	list.mouseEnter(ev, e.getX(), e.getY());
}
public void mouseExited(MouseEvent e) {
	Event ev = new Event(this, e.getWhen(), e.getID(),
				 e.getX(), e.getY(), 0, e.getModifiers());
	// Determine if cursor is moved outside the JCList.
	list.mouseExit(ev, e.getX(), e.getY());
}
/* JDK110_END */
/** When mouse click on an item the popup list,
 * 	show the selected item in the text field and
 * 	hide the popup list.
 */
/* JDK110_START */
public void mouseReleased(MouseEvent e) {
	if (e.getComponent() == list.getList()) {
		Event ev = new Event(this, e.getWhen(), e.getID(),
					 e.getX(), e.getY(), 0, e.getModifiers());
		list.mouseUp(ev, e.getX(), e.getY());
	}
}

public void mouseDragged(MouseEvent e) {}
public void mouseMoved(MouseEvent e) {
	Event ev = new Event(this, e.getWhen(), e.getID(),
				 e.getX(), e.getY(), 0, e.getModifiers());
	// Determine if cursor is moved outside the JCList.
	list.mouseMove(ev, e.getX(), e.getY());
}

}	// ComboListMouseListener
/* JDK110_END */

class ComboList extends JCList {

JCComboBox box;
boolean exited = true;
boolean bg_set = false;
boolean fg_set = false;
boolean fn_set = false;

ComboList(JCComboBox box) {
	super();
	this.box = box;
	exited = false;
	setList(new ComboListComponent(this));
/* JDK110_START */
	// Add mouse and mouse motion listeners for all the components
	// in JCList.  Thus, mouse events received by components can
	// be passed back to JCList.
	JCScrollbar vert_sb = getVertScrollbar();
	JCScrollbar horiz_sb = getHorizScrollbar();
	ComboListMouseListener ma = new ComboListMouseListener(this);
	addMouseMotionListener(ma);
	getList().addMouseMotionListener(ma);
	vert_sb.addMouseMotionListener(ma);
	vert_sb.incr_arrow.addMouseMotionListener(ma);
	vert_sb.decr_arrow.addMouseMotionListener(ma);
	horiz_sb.addMouseMotionListener(ma);
	horiz_sb.incr_arrow.addMouseMotionListener(ma);
	horiz_sb.decr_arrow.addMouseMotionListener(ma);
	addMouseListener(ma);
	getList().addMouseListener(ma);
	vert_sb.addMouseListener(ma);
	vert_sb.incr_arrow.addMouseListener(ma);
	vert_sb.decr_arrow.addMouseListener(ma);
	horiz_sb.addMouseListener(ma);
	horiz_sb.incr_arrow.addMouseListener(ma);
	horiz_sb.decr_arrow.addMouseListener(ma);

   if (JCEnvironment.isVisualAge()) {
       setSmartScroll(false);
   }

/* JDK110_END */
}

public void setBackground(Color c) {
	super.setBackground(c);
	bg_set = true;
}

public void setForeground(Color c) {
	super.setForeground(c);
	fg_set = true;
}

public void setFont(Font f) {
	super.setFont(f);
	fn_set = true;
}

void lostFocus() {
	if (box.style != JCComboBox.COMBOBOX_SIMPLE && exited) {
		box.hidePopup();
	}
}

public boolean mouseMove(Event ev, int x, int y) {
	exited = !inside(x, y);
	return super.mouseMove(ev, x, y);
}

public boolean mouseEnter(Event ev, int x, int y) {
	exited = false;
	return super.mouseEnter(ev, x, y);
}

public boolean mouseExit(Event ev, int x, int y) {
	exited = true;
	return super.mouseExit(ev, x, y);
}

}	// ComboList


/**
 * JCComboBox provides the capabilities of a JCTextField and a JCList component.
 * It allows users to perform operations like typing and pasting information,
 * and it also provides a list of possible choices that the user can select from
 * to complete the TextField entry.
 * <p>
 * Note that on MS-Windows, AWT currently uses a Windows ComboBox as the peer for
 * its Choice component.
 * JCComboBox provides this functionality on all platforms.
 * <p>
 * The list may be displayed at all times, or may only drop down when the user
 * presses an arrow button next to the TextField (a "drop-down" ComboBox).
 * <p>
 * <strong>Behavior</strong><br>
 * If the arrow button is pressed and released, the list pops
 * up and stays up until the user clicks an item inside it, hits RETURN,
 * clicks outside the list, or presses ESCAPE. If an item is clicked or RETURN
 * is hit, the item is copied to the text field.<p>
 *
 * <strong>Properties</strong><p>
 * <table border>
 * <tr><th>Name</th>    <th>Method</th></tr><tr><td>
 * Alignment           	</td><td><a href=jclass.bwt.JCTextComponent.html#setAlignment>setAlignment</a></td></tr><tr><td>
 * Background          	</td><td><a href=java.awt.Component.html#setBackground>setBackground</a></td></tr><tr><td>
 * Changed              </td><td><a href=jclass.bwt.JCTextComponent.html#setChanged>setChanged</a></td></tr><tr><td>
 * Columns             	</td><td><a href=jclass.bwt.JCTextComponent.html#setColumns>setColumns</a></td></tr><tr><td>
 * CursorPosition      	</td><td><a href=jclass.bwt.JCTextComponent.html#setCursorPosition>setCursorPosition</a></td></tr><tr><td>
 * Editable            	</td><td><a href=jclass.bwt.JCTextComponent.html#setEditable>setEditable</a></td></tr><tr><td>
 * Font                	</td><td><a href=jclass.bwt.JCContainer.html#setFont>setFont</a></td></tr><tr><td>
 * Foreground          	</td><td><a href=java.awt.Component.html#setForeground>setForeground</a></td></tr><tr><td>
 * HighlightColor      	</td><td><a href=jclass.bwt.JCComponent.html#setHighlightColor>setHighlightColor</a></td></tr><tr><td>
 * HighlightThickness  	</td><td><a href=jclass.bwt.JCComponent.html#setHighlightThickness>setHighlightThickness</a></td></tr><tr><td>
 * Items               	</td><td><a href=#setItems>setItems</a></td></tr><tr><td>
 * Insets              	</td><td><a href=jclass.bwt.JCContainer.html#setInsets>setInsets</a></td></tr><tr><td>
 * MaximumLength       	</td><td><a href=jclass.bwt.JCTextComponent.html#setMaximumLength>setMaximumLength</a></td></tr><tr><td>
 * Overstrike          	</td><td><a href=jclass.bwt.JCTextComponent.html#setOverstrike>setOverstrike</a></td></tr><tr><td>
 * PreferredSize       	</td><td><a href=jclass.bwt.JCContainer.html#setPreferredSize>setPreferredSize</a></td></tr><tr><td>
 * SelectedBackground  	</td><td><a href=jclass.bwt.JCTextComponent.html#setSelectedBackground>setSelectedBackground</a></td></tr><tr><td>
 * SelectedForeground  	</td><td><a href=jclass.bwt.JCTextComponent.html#setSelectedForeground>setSelectedForeground</a></td></tr><tr><td>
 * SelectionEnd        	</td><td><a href=jclass.bwt.JCTextComponent.html#setSelectionEnd>setSelectionEnd</a></td></tr><tr><td>
 * SelectionList       	</td><td><a href=jclass.bwt.JCTextComponent.html#setSelectionList>setSelectionList</a></td></tr><tr><td>
 * SelectionStart      	</td><td><a href=jclass.bwt.JCTextComponent.html#setSelectionStart>setSelectionStart</a></td></tr><tr><td>
 * ShadowThickness  	</td><td><a href=jclass.bwt.JCComponent.html#setShadowThickness>setShadowThickness</a></td></tr><tr><td>
 * ShowCursorPosition  	</td><td><a href=jclass.bwt.JCTextComponent.html#setShowCursorPosition>setShowCursorPosition</a></td></tr><tr><td>
 * StringCase          	</td><td><a href=jclass.bwt.JCTextComponent.html#setStringCase>setStringCase</a></td></tr><tr><td>
 * Style          		</td><td><a href=#setStyle>setStyle</a></td></tr><tr><td>
 * Text          		</td><td><a href=#setText>setText</a></td></tr><tr><td>
 * UserData            	</td><td><a href=jclass.bwt.JCContainer.html#setUserData>setUserData</a></td></tr><tr><td>
 * VisibleRows         	</td><td><a href=#setVisibleRows>setVisibleRows</a></td></tr><tr><td>
 * WrapAroundSearch     </td><td><a href=#setWrapAroundSearch>setWrapAroundSearch</a></td></tr>
 * </table><p>
 *
 * <strong>Events</strong><p>
 * <table border><tr>
 * <th>Class</th> <th>Listener</th>   <th>Description</th></tr><tr><td>
 * JCActionEvent    </td><td><a href=#addActionListener>addActionListener</a></td> <td>Called when the ENTER key is hit</td></tr><tr><td>
 * JCItemEvent      </td><td><a href=#addItemListener>addItemListener</a></td> <td>Called after the user selects an item</td></tr><tr><td>
 * JCComboBoxEvent    </td><td><a href=#addComboBoxListener>addComboBoxListener</a></td> <td>Called before the list is displayed, and after the user has made a selection</td></tr><tr><td>
 * JCTextEvent   </td><td><a href=jclass.bwt.JCTextComponent.html#addTextListener>addTextListener</a></td> <td>Posted when the value changes</td></tr><tr><td>
 * JCTextCursorEvent  </td><td><a href=jclass.bwt.JCTextComponent.html#addTextCursorListener>addTextCursorListener</a></td> <td>Posted when the cursor is moved</td></tr>
 * </table>
 */
public class JCComboBox extends JCContainer
    implements JCListListener, JCActionListener, JCItemSelectable,
               JCChoiceInterface, JCTextInterface, JCTextManagerInterface
/* JDK110_START */
                , CellEditor, CellRenderer
/* JDK110_END */
{
public static final int COMBOBOX_SIMPLE	= BWTEnum.COMBOBOX_SIMPLE;
public static final int COMBOBOX_DROPDOWN = BWTEnum.COMBOBOX_DROPDOWN;
public static final int COMBOBOX_DROPDOWN_LIST = BWTEnum.COMBOBOX_DROPDOWN_LIST;

ComboField text;
ComboList list;
JCArrowButton button;
int style = COMBOBOX_DROPDOWN;

long list_popdown_time;		// Time when ComboBox list is popped down.
long list_poppedup_time;		// Time when ComboBox list is popped up.

protected boolean arrow_key_spinning_allowed = true;

/** Set to true when non-COMBOBOX_SIMPLE list is displayed. */
protected boolean list_poppedup = false;
boolean using_window = false;
transient protected Window list_window;

transient protected Component list_child;
transient JCListEvent last_list_event;	// event passed to listItemSelectEnd

protected int border = 2; // shadow/border thickness

/** List of JCItemEvent listeners */
protected JCVector itemListeners = new JCVector(0);

/** List of JCActionEvent listeners */
protected JCVector actionListeners = new JCVector(0);

/** List of JCComboBoxEvent listeners */
protected JCVector listeners = new JCVector(0);

private static final String base = "combobox";
private static int nameCounter = 0;
private static final boolean TRACE = false;

protected final static int BEGIN = 0;
protected final static int END = 1;

protected static JCComboBox last_to_lose_focus = null;

/* JDK110_START */
protected Object getLastToLoseFocus() { return last_to_lose_focus; }

private class ComboWindowListener extends WindowAdapter
implements Serializable {

	public void windowActivated(WindowEvent e) {
		if (style != COMBOBOX_SIMPLE) {
			// Sometimes when a dialog is destroyed the combo inside it
			// never gets the lost focus event; in at least one case it gets
			// activated before it goes down so we put a hidePopup() in here
			// to catch this case
            if (!(JCEnvironment.isVisualAge()))
    			hidePopup();
		}
	}

	public void windowDeiconified(WindowEvent e) {
		if (style != COMBOBOX_SIMPLE) {
			if (JCComboBox.this == getLastToLoseFocus()) {
				// we popup and the hide the list box as a work around to
				// iconification problems if the popup is popped up
				list_poppedup_time = System.currentTimeMillis();
				showListAction(null);
				hidePopup();
			}
		}
	}
	
	// Added a window handler event to set "last_to_lose_focus"
	// to null. Note:- If window is closed it does not necessary 
	// mean that it is disposed.
	public void windowClosed (WindowEvent e) {
		last_to_lose_focus = null;
	}
	
};
/* JDK110_END */

final static int DEFAULT_VISIBLE_ROWS = 5;
boolean visible_rows_set = false;

/** Creates an empty ComboBox. No parameters are read from an HTML file. */
public JCComboBox() {
	this(null, null);
}

/** Creates an empty ComboBox with the specified list items. */
public JCComboBox(JCVector items) {
	this(items, null, null);
}

/** Creates a comboBox with the specified items and name.
 * No parameters are read from an HTML file.
 */
public JCComboBox(String[] items, String name) {
	this(new JCVector(items), null, name);
}

/** Creates a comboBox which reads parameters from the applet's HTML file.
 * @param applet the applet whose PARAM tags are to be read
 * @param name if this is not null, only parameters preceded by this name are read
 * @see java.applet.Applet#getParameter
 */
public JCComboBox(JCVector items, Applet applet, String name) {
	super(applet, name);
	if (name == null)
		setName(base + nameCounter++);
	setLayout(null);
	text = new ComboField(this);
	text.addActionListener(this);
	list = new ComboList(this);
	list.setAutoSelect(true);
	list.setVisibleRows(5);
	button = new JCArrowButton(BWTEnum.DOWN);
	button.setBorderThickness(2);
	button.right_border_color = Color.black;
	button.addActionListener(this);
	list.setItems(items);
	list.addActionListener(this);
	list.addItemListener(this);
	setStyle(style);
	checkButton();
}

/** Reads the parameter values from the HTML page using the component's applet.
 * The values will override those previously set.
 */
protected void getParameters() {
	super.getParameters();
	ComboBoxConverter.getParams(this);
}

/** Gets the component to be used as the data entry field. */
public JCTextField getTextField() { return text; }

/**
 * Gets the component to be used as the data entry field as an object which
 * supports the JCTextInterface
 * @see getTextField
 */
public JCTextInterface getTextComponent() { return text; }

/** Gets the list component. */
public JCList getList() { return list; }

/** Gets the button component. */
public JCArrowButton getButton() { return button; }

/**
 * Sets the text field's value to the specified string.<p>
 * <strong>HTML param name/value:</strong> "Text"/string
 */
public synchronized void setText(String s) { text.setText(s); }

/**
 * Gets the value of the text.
 * @see #setText
 */
public synchronized String getText() { return text.getText(); }


/** Sets the list's values as a list of Strings. */
public void setItems(String[] items) { list.setItems(items); checkButton();}

/** Gets the list's values as a list of Strings.
 * @see #setItems
 */
public synchronized String[] getItems() { return list.getItemsStrings(); }

/** Gets the Style value.
 * @see #setStyle
 */
public int getStyle() { return style; }

/** Sets the style, which specifies whether the list is always displayed and
 * whether the text field is editable. If the list is hidden, it can be displayed
 * by pressing the arrow button or hitting the down or up arrow key.
 * The value must be one of the following values:
 * <pre>
 * COMBOBOX_SIMPLE           list is always displayed, text field is editable
 * COMBOBOX_DROPDOWN         list is hidden, text field is editable (default)
 * COMBOBOX_DROPDOWN_LIST    list is hidden, text field is uneditable
 * </pre>
 * @exception IllegalArgumentException If an invalid value is set
 */
public void setStyle(int v) {
	ComboBoxConverter.checkStyle(v);

	// Style may be changed from DROPDOWN while the dialog/window is still shown.
	// Make sure the dialog/window is hidden and free.
	if (style == COMBOBOX_DROPDOWN) {
		hidePopup();
		list_window = null;
	}

	// In case the style was set to DROPDOWN, the list is hidden,
	// and must be made visible again.
	if (v != COMBOBOX_DROPDOWN)
		list.show();
	style = v;
	text.setEditable(style != COMBOBOX_DROPDOWN_LIST);
	text.setShowCursorPosition(style != COMBOBOX_DROPDOWN_LIST);
	if (style == COMBOBOX_SIMPLE) {
		setInsets(new Insets(0,0,0,0));
		text.setBorderThickness(border);
		list.getList().spacing = 0;
		list.getList().setBorderThickness(border);
	}
	else {
		setInsets(new Insets(border, border, border, border));
		text.setBorderThickness(0);
		list.getList().spacing = -1;
/* JDK110_START */
		list.getList().setBorderThickness(0);
/* JDK110_END */
	}
	updateParent();
}

/**
 * Returns the number of items in the list.
 * @see #getItem
 */
public int getItemCount() { return list.countItems(); }

/**
 * Returns the item at the specified index in the list.
 * @see #getItemCount
 */
public Object getItem(int index) { return list.getItem(index); }

/**
 * Adds an item to the list.
 * @exception NullPointerException If the item's value is null.
*/
public void add(String item) { list.addItem(item); checkButton(); }

/**
 * Removes the first occurrence of an item from the list.
 * @exception IllegalArgumentException If the item doesn't exist
 */
public void remove(String item) { list.remove(item); checkButton(); }

/**
 * Removes all items from the list.
 * @see #remove
 */
public void removeAll() { list.clear(); checkButton(); }

/**
 * Returns a String representation of the current choice.
 * @see #getSelectedIndex
 */
public String getSelectedItem() {
	Object v;
	synchronized (list) {
		v = list.getSelectedItem();
	}
	return v != null ? v.toString() : "";
}

/**
 * Returns the index of the currently selected item.
 * @see #getSelectedItem
 */
public int getSelectedIndex() { return list.getSelectedIndex(); }

/**
 * Selects the item with the specified postion.
 * @exception IllegalArgumentException If the choice item position is invalid.
 * @see #getSelectedItem
 * @see #getSelectedIndex
 */
public synchronized void select(int pos) { list.select(pos); }

/**
 * Selects the specified item.
 * @see #getSelectedItem
 * @see #getSelectedIndex
 */
public synchronized void select(Object item) { list.select(item); }

/**
 * Returns an array (length 1) containing the currently selected item.
 * @see JCItemSelectable
 * @see #getSelectedIndex
 */
public Object[] getSelectedObjects() { return list.getSelectedObjects(); }

/**
 * Returns an array (length 1) containing the currently selected item.
 * @see JCItemSelectable
 */
public synchronized int[] getSelectedIndexes() { return list.getSelectedIndexes(); }

/** Sets the number of visible rows; if set to 0 (default), the list
 * will attempt to resize itself so that all its items are visible.<p>
 * <strong>HTML param name/value:</strong> "VisibleRows"/int
 * @see #setItems
 */
public void setVisibleRows(int v) {
	visible_rows_set = true;
	list.setVisibleRows(v);
}

/**
 * Gets the WrapAroundSearch value.
 * @see #setWrapAroundSearch
 */
public boolean getWrapAroundSearch() { return list.getWrapAroundSearch(); }

/**
 * Sets the WrapAroundSearch value.
 * If true (default), search by key entered will wrap around to the
 * beginning/end of the list to find the next/previous item.
 */
public void setWrapAroundSearch(boolean v) {
	list.setWrapAroundSearch(v);
}

/**
 * Adds the specified JCComboBoxEvent listener to receive events.
 * @see JCComboBoxEvent
 */
public void addComboBoxListener(JCComboBoxListener l) {
	listeners.addUnique(l);
}

/**
 * Removes the specified listener so it no longer receives JCComboBoxEvents.
 * @see #addComboBoxListener
 */
public void removeComboBoxListener(JCComboBoxListener l) {
	listeners.removeElement(l);
}

/**
 * Adds the specified action listener to receive JCTextField action events.
 * @see JCActionEvent
 */
public void addActionListener(JCActionListener l) {
	actionListeners.addUnique(l);
}

/**
 * Removes the specified action listener so it no longer receives action events.
 * @see #addActionListener
 */
public void removeActionListener(JCActionListener l) {
	actionListeners.removeElement(l);
}

/**
 * Adds the specified item listener to receive item selection events.
 * @see JCItemEvent
 * @see JCItemListener
 */
public void addItemListener(JCItemListener l) {
	itemListeners.addUnique(l);
}

/**
 * Removes the specified item listener so it no longer receives item events.
 */
public void removeItemListener(JCItemListener l) {
	itemListeners.removeElement(l);
}

protected int preferredWidth() {
	int w = JCComponent.getPreferredSize(text).width +
		    JCComponent.getPreferredSize(button).width;
	// the size of the border is compensated for by the size of the button
	return (style != COMBOBOX_SIMPLE) ? w : Math.max(w, JCComponent.getPreferredSize(list).width);
}

protected int preferredHeight() {
	int text_h = JCComponent.getPreferredSize(text).height;
	// the size of the border is compensated for by the size of the button
	return (style != COMBOBOX_SIMPLE) ?
		Math.max(text_h, JCComponent.getPreferredSize(button).height)
		: text_h + JCComponent.getPreferredSize(list).height;
}

/** Sets the list's colors and font to the same as the textfield's,
 * if they have not been set.
 */
protected void setListProperties() {
	if (!list.bg_set) {
		list.getList().setBackground(text.getBackground());
		list.bg_set = false;
	}
	if (!list.fg_set) {
		list.getList().setForeground(text.getForeground());
		list.fg_set = false;
	}
	if (!list.fn_set) {
		list.getList().setFont(text.getFont());
		list.fn_set = false;
	}
}

/* JDK110_START */
WindowListener windowListener = null;
/* JDK110_END */
public void addNotify() {
	// Bug 6149: Comment this out because a simple combo box does not contain a
	// button. Added the "text" and "button", specific to each type of combo box.
	//add(text);
	//add(button);
	if (style == COMBOBOX_SIMPLE) {
		// Add only text here because a simple combo box does not contain a button
		// therefore no "add(button);" is needed.
		add(text);
		add(list);
		setListProperties();
	}
	// Added an "else" clause to add text and button to a drop-down combo box.
	else {
		add(text);
		add(button);
	}	
	super.addNotify();
	
/* JDK110_START */
	// We need to add a window listener so that we can pop down as is
	// necessary when the parent window iconifyies/closes/ etc

    // in VisualAge BWTUtil.getWindow(this) returns null during design time. -ahm
	Window parentWindow = BWTUtil.getWindow(this);
	if (parentWindow != null){
/* JDK110_END */
/* AWT110_START 
	    parentWindow.addWindowListener(windowListener = new ComboWindowListener());
 AWT110_END */
/* SWING_START */
		// if using plugin in IE4, do not add the window listener
		if (!text.getFrame().getClass().getName().equals(
										 "sun.beans.ole.OleEmbeddedFrame")) {
			parentWindow.addWindowListener(windowListener = new ComboWindowListener());
		}
/* SWING_END */
/* JDK110_START */
	}
/* JDK110_END */
}

public void removeNotify() {
/* SWING_START */
	// we don't get a loseFocus event in Swing so wee need to make sure the
	// popup is hidden when we are removed
	hidePopup();
/* SWING_END */
/* JDK110_START */

    // in VisualAge BWTUtil.getWindow(this) returns null during design time. -ahm
	Window parentWindow = BWTUtil.getWindow(this);
	if (parentWindow != null && windowListener != null) {
	    parentWindow.removeWindowListener(windowListener);
	}
/* JDK110_END */
	super.removeNotify();
}

public void requestFocus() {
	// A container should never have focus so be pass this request on to
	// our first traversable child; if we have no children then we do nothing
	if (text != null) {
		text.requestFocus();
	}
}

/** Sets background of the ComboBox */
public void setBackground(Color background) {
	super.setBackground(background);
	button.setBackground(background);
}

/** Sets foreground color of the ComboBox */
public void setForeground(Color foreground) {
	super.setForeground(foreground);
	button.setForeground(foreground);
}

public void layout() {
	int btn_width = 0, text_height = 0, btn_height = 0;
	int h_margin = 0, v_margin = 0;
	int width = 0, height = 0;
	int local_style;

	synchronized (this) {
		btn_width = JCComponent.getPreferredSize(button).width;
		text_height = JCComponent.getPreferredSize(text).height;
		btn_height = text_height;
		h_margin = JCComponent.getInsets(this).left +
			       JCComponent.getInsets(this).right;
		v_margin = JCComponent.getInsets(this).top +
			       JCComponent.getInsets(this).bottom;
		width = Math.max(0, size().width - h_margin);
		height = Math.max(0, size().height - v_margin);

		local_style = style;
	}

	if (local_style == COMBOBOX_SIMPLE) {
		btn_width = btn_height = 0;
		remove(button);
		int w = Math.max(width, JCComponent.getPreferredSize(list).width);
		int h = Math.max(0, height - text_height);
		int x = Math.min(0, w - width);
		JCComponent.setBounds(list, x + JCComponent.getInsets(this).left,
							  text_height + JCComponent.getInsets(this).top,
							  w, h);
		add(list);
		JCComponent.setBounds(text, JCComponent.getInsets(this).left,
							  JCComponent.getInsets(this).top,
							  width - btn_width, text_height);
	}
	else {
		remove(list);
		int h = Math.min(height, text_height);
		JCComponent.setBounds(button,
							  size().width - btn_width -
							  JCComponent.getInsets(this).right,
							  JCComponent.getInsets(this).top, btn_width, h);
		add(button);
		JCComponent.setBounds(text, JCComponent.getInsets(this).left,
							  JCComponent.getInsets(this).top,
							  width - btn_width, h);
	}
}

protected Frame createPopupWindow(Component child) {

	Frame parent_frame = text.getFrame();
	String class_name = parent_frame.getClass().getName();

	// check to see if the java plug-in technology is being used.
	// When using Activator, the popup ends up in the top right corner
	// outside the browser.
	if (class_name.equals("sun.beans.ole.OleEmbeddedFrame") ||
		    class_name.equals("sun.plugin.navig.win32.PluginFrame")) {
/* AWT_START 
		using_window = false;
 AWT_END */
/* SWING_START */
		using_window = true;
/* SWING_END */

	}

	// In Visual Cafe in a bean property editor, the popup does not appear
	// in the right position. Therefore, we do not use the window.
	else if (class_name.equals("sun.awt.windows.WEmbeddedFrame") &&
			 JCEnvironment.isVisualCafe() == true) {
		using_window = false;
	}
	else if (JCEnvironment.isBrowserNetscape(this) &&
			 JCEnvironment.getOS() == JCEnvironment.OS_UNIX) {
		// bug in Netscape under Solaris where popup does not appear
		using_window = true;
	}
	else if (JCEnvironment.getAppletContext(applet) == null) {
		// We are running as an application
		using_window = true;
	}
	else if (JCEnvironment.isBrowserAppletViewer(this)) {
		// We are running in a browser
		using_window = true;
	}
/* JDK102_START
	else if (JCEnvironment.isBrowserExplorer(this)) {
		// For IE4 and JDK102, using frame cannot show popup list.  Thus,
		// window must be used.
		using_window = true;
	}
 JDK102_END */
	else if (JCEnvironment.isBrowserVisualAge(this)) {
		// We are running in VisualAge
		using_window = true;
	}
	else if (JCEnvironment.isBrowserVisualCafe(this)) {
		// We are running in Visual Cafe
		using_window = true;
	}
    else if (JCEnvironment.isBrowserNetscape(this) || JCEnvironment.isBrowserExplorer(this)) {
        // We are running Netscape or Explorer.
/* AWT_START 
        // Under AWT we don't use a separate window
		 using_window = false;
		
		// Bug: PR 5301 states when the drop down list from the combo box is 
		// dropped down and the frame is resized, the list maximizes to the size
		// of the applet. This does a loop to check and see if the main parent is
		// derived from window and sets using_window to true.
		Container parentContainer = this.getParent();
		while (parentContainer != null) {
			if (parentContainer instanceof Window) {
				String containerClassName =  parentContainer.getClass().getName();
				
				if (containerClassName.toLowerCase().indexOf("netscape") < 0)
					using_window = true;
						
				break;
			}
			
			parentContainer = parentContainer.getParent();
		}	
		
 AWT_END */
/* SWING_START */
        // Under swing use a separate window
		using_window = true;
/* SWING_END */

    }
	else {
		using_window = false;
	}

	// We need to know if this is in a model dialog so that we can hack around
	// the problem where reusing a model dialog renders the popup shell "modal"
	// disabled
	boolean is_modal = false;
	Window window = BWTUtil.getWindow(this);
	if (window instanceof Dialog) {
		is_modal = ((Dialog)window).isModal();
	}

	if (!using_window) {
/* JDK102_START
		parent_frame.add("Center", child);
 JDK102_END */
/* JDK110_START */
		parent_frame.add(child, "Center", 0);
/* JDK110_END */
		// ensure that the child has the right background color
		child.setBackground(this.getBackground());
		child.addNotify();
		list_child = child;
	}
	else if (list_window == null || is_modal) {
		// always recreate the popup shell if there is a modal dialog
		// as a parent of the combobox
		if (JCEnvironment.getOS() == JCEnvironment.OS_UNIX) {
			// UNIX jdk does not support a Window ppear so we create
			// and use a Framed Dialog instead
			list_window = new ComboDialog(this, parent_frame);
		}
		else {
/* AWT_START 
			list_window = new Window(parent_frame);
 AWT_END */
/* SWING_START */
			list_window = new JWindow(parent_frame);
/* SWING_END */
		}
/* AWT_START 
		list_window.add("Center", child);
 AWT_END */
/* SWING_START */
		if (JCEnvironment.getOS() == JCEnvironment.OS_UNIX) {
			((JDialog)list_window).getContentPane().add("Center", child);
		}
		else {
			((JWindow)list_window).getContentPane().add("Center", child);
		}
		if (swing_border != null && (child instanceof JComponent)) {
		    ((JComponent)child).setBorder(swing_border);
		}
/* SWING_END */
		list_window.pack();

/* JDK110_START */
		if ((JCEnvironment.getJavaVersion() >= 116) &&
			    !JCEnvironment.isVisualAge() ) {
			list_window.addWindowListener(new ComboPopupWindowListener());
		}
/* JDK110_END */
	}

	return parent_frame;
}


/* JDK110_START */
private class ComboPopupWindowListener extends WindowAdapter
implements Serializable {

	public void windowDeactivated(WindowEvent e) {
		hidePopup();
	}
};
/* JDK110_END */

protected void positionPopupWindow(Component child, Frame parent_frame,
								  int x, int width, int height) {

	int y;
	boolean using_plugin = false;

	// We must include the height of the dialog title bar for
	// UNIX or the applet warning label under Internet Explorer.
	if ((JCEnvironment.getOS() == JCEnvironment.OS_UNIX)) {
		if (JCEnvironment.isBrowserNetscape(this)) {
			height += 50;
		}
		else {
			height += 25;
		}

	}
/* SWING_START */
	// using plugin with browsers causes a warning message to appear
	// because we are popping up a window. We must take the height of the
	// message into account.
	String class_name = parent_frame.getClass().getName();
	if (class_name.equals("sun.beans.ole.OleEmbeddedFrame") ||
		    class_name.equals("sun.plugin.navig.win32.PluginFrame")) {
		using_plugin = true;
		height += 25;
	}
/* SWING_END */

	Window parent = BWTUtil.getWindow(text);
	Point p = BWTUtil.translateToParent(parent, this,
									x, text.location().y + text.size().height);
	Container c = (parent instanceof Frame) ? null : parent_frame;
	if (TRACE) {
		System.out.println("parent: " + parent);
		System.out.println("c: " + c);
		System.out.println("position relative to parent: x: " + p.x + " y: " + p.y);
        System.out.println("using plugin " + using_plugin);
	}

	// For IE4 and JDK102, because a window is used, we have to translate
	// the position.
	// For IE4 and JDK11, don't translate to parent if parent is null.
	// Otherwise, it seems to use the screen as the Container
	// c (the parent of parent) and results a point which puts
	// the popup list far off.
	if (using_window || !(JCEnvironment.isBrowserExplorer(this) &&
					  c == null)) {
		p = BWTUtil.translateToParent(c, parent, p.x, p.y);
	}

/* SWING_START */
    if(using_window && (JCEnvironment.isBrowserNetscape(this) || JCEnvironment.isBrowserExplorer(this))) {
        SwingUtilities.convertPointToScreen(p,parent);
    }
/* SWING_END */

	// Ensure that list is fully visible. If there is not enough room to
	// show the list underneath the text field, it will pop the list above
	// the text field.
	Dimension screen = getToolkit().getScreenSize();
	if (!using_window) screen = parent.size();
	p.x = Math.max(0, Math.min(p.x, screen.width - width));
	if (p.y + height > screen.height) {
		p.y = p.y - text.size().height - height;
	}
	if (TRACE) {
		System.out.println("Final position x: " + p.x + " y: " + p.y);
	}

	if (!using_window) {
		JCComponent.setBounds(child, p.x, p.y, width, height);
	}
	else {
/* SWING_START */
		if ((using_plugin == true) || JCEnvironment.isBrowserNetscape(this) || JCEnvironment.isBrowserExplorer(this)) {
			Point p2 = new Point(p.x, p.y);
			SwingUtilities.convertPointFromScreen(p2, this);
			JCComponent.setBounds(list_window, p.x - p2.x, p.y - p2.y +
								  text.size().height +
								  JCComponent.getInsets(this).top,
								  width, height);
		}
		else {
/* SWING_END */
/* JDK110_START */
		    if (JCEnvironment.getOS() == JCEnvironment.OS_UNIX &&
				JCEnvironment.isBrowserNetscape(this)) {
				Point p2 = new Point(p.x, p.y);
				translatePointFromScreen(p2, this);
				JCComponent.setBounds(list_window, p.x - p2.x, p.y - p2.y +
									  text.size().height +
									  JCComponent.getInsets(this).top,
									  width, height);
			}
			else {
/* JDK110_END */
				JCComponent.setBounds(list_window, p.x, p.y, width, height);
/* JDK110_START */
			}
/* JDK110_END */
/* SWING_START */
		}
/* SWING_END */

/* JDK102_START
		   child.resize(width, height);
 JDK102_END */
/* JDK110_START */
		child.setSize(width, height);
/* JDK110_END */
		child.show();
		list_window.show();

		// This line was added to request the focus back to the list component
		list_window.requestFocus();
	}
}

/**
 * This method converts a point from the screen coordinate system to the
 * specified component's coordinate space.
 */
/* JDK110_START */
protected void translatePointFromScreen(Point p, Component c) {
    Rectangle rect;
    int x, y;

    do {
        if (c instanceof java.applet.Applet) {
            Point pp = c.getLocationOnScreen();
            x = pp.x;
            y = pp.y;
        }
		else {
            rect = c.getBounds();
            x = rect.x;
            y = rect.y;
        }

        p.x -= x;
        p.y -= y;

        if (c instanceof java.awt.Window || c instanceof java.applet.Applet) {
            break;
		}
        c = c.getParent();
    } while(c != null);
}
/* JDK110_END */

/**
 * Displays the list if it is not visible. A JCComboBoxEvent is first posted.
 */
public void showListAction(JCActionEvent ev) {
	if (style == COMBOBOX_SIMPLE) return;
	if (list_poppedup) return;

	JCComboBoxEvent combo_ev = postComboBoxEvent(BEGIN, null);

	int row = list.find(text.getText());
	if (row >= 0)
		list.select(row, false);
	else
		list.deselectAll();

	// call routine to do the popup window creation
	Frame parent_frame = createPopupWindow(list);

	setListProperties();

	// Position the list below the text field
	int w = Math.max(size().width, JCComponent.getPreferredSize(list).width);

	// If VisibleRows has not been set, set it to the range [1,5]
	if (!visible_rows_set) {
		int rows = list.getItems().size();
		if (rows == 0)
			rows = 1;
		else if (rows > DEFAULT_VISIBLE_ROWS)
			rows = DEFAULT_VISIBLE_ROWS;
		list.setVisibleRows(rows);
	}
	int h = JCComponent.getPreferredSize(list).height;
	int x = Math.min(0, w - size().width);

	positionPopupWindow(list, parent_frame, x, w, h);

	list.exited = true;
	list.makeVisible(list.getSelectedIndex());
	// the following "list.show()" exists for the sake of Netscape Navigator
	// the show is also done in the "positionPopupWindow" wich is sufficient
	// for the JDK

	// in Swing, the popup never seems to get focus when it's popped up.
	// The toFront method forces it to get focus

/* SWING_START */
	if (list_window != null) {
		list_window.toFront();
	}
	else {
		parent_frame.toFront();
	}
/* SWING_END */

	list.show();
	list.getList().requestFocus();
	list_poppedup = true;
}

/**
 * This routine should never be called.  It hides the popup list and is used
 * internaly by the various components that make up the combobox
 */
public void hidePopup() {

	if (!list_poppedup) return;

	// Record the event timestamp when when the list is popped down.
	JCComponent.popdown_event_timestamp = JCComponent.mouse_down_event_timestamp;

	// Record the time when list is shown
	list_popdown_time = System.currentTimeMillis();
	list_poppedup = false;
	if (style == COMBOBOX_SIMPLE)
		return;
	else if (using_window && list_window != null)
		list_window.hide();
	else if (!using_window)
		list_child.hide();

	//
	// Make sure the selection in the list matches that in the text field
	//
	int row = list.find(text.getText());
	if (row >= 0)
		list.select(row, false);
	else
		list.deselectAll();

	listPoppedDown();
}

/**
 * Sets the focus if the list was selected interactively.
 */
protected void listPoppedDown() {
	// when not using window, the popup will not popdown after the first time
	// until the text field gets focus. For this case, we force the text field
	// to get focus every time the popup is hidden
	if (last_list_event != null && last_list_event.event != null ||
		    !using_window) {
		text.requestFocus();
	}
}

/**
 * Copies the item to the text field, after first posting a JCComboBoxEvent
 * to allow the application to disallow the selection or change its value.
 * After the value is copied, an JCItemEvent is posted.
 * @see #addComboBoxListener
 * @see #addItemListener
 */
protected void setTextAction(String value) {
	JCComboBoxEvent combo_ev = postComboBoxEvent(END, value);
	if (combo_ev != null)
		value = combo_ev.value != null ? combo_ev.value.toString() : null;

	text.setText(value);
	// don't do a hide list here since we really need to do it on mouseUp
	// so we need to trigger this selectively at a high level
	//hidePopup();

	// Post the event to the JCItemListeners, after changing the target to this
	if (itemListeners.size() == 0) return;
	JCItemEvent item_ev = new JCItemEvent(this, JCItemEvent.ITEM_STATE_CHANGED,
									  value, JCItemEvent.SELECTED);
	for (int i=0; i < itemListeners.size(); i++) {
		JCItemListener l = (JCItemListener) itemListeners.elementAt(i);
		l.itemStateChanged(item_ev);
	}
}

/** Posts a JCComboBoxEvent
 * @return the posted event, or null if there are no listeners
 * @see #addComboBoxListener
 * @see JCComboBoxEvent
 */
protected JCComboBoxEvent postComboBoxEvent(int stage, Object value) {
	if (listeners.size() == 0)
		return null;

	JCComboBoxEvent combo_ev = new JCComboBoxEvent(this, value);

	for (int i=0; i < listeners.size(); i++) {
		JCComboBoxListener l = (JCComboBoxListener) listeners.elementAt(i);
		if (stage == BEGIN)
			l.comboBoxListDisplayBegin(combo_ev);
		else
			l.comboBoxListDisplayEnd(combo_ev);
	}
	return combo_ev;
}

/** JCItemListener method - no-op */
public void itemStateChanged(JCItemEvent ev) {}

/**
 * Only support single character search.
 * Always wrap around even though there is an unexposed WrapAroundSearch property.
 * If ComboBox is editable, search is triggered only if one character
 * is entered in the text field.
 */
protected int getNextAutoSearchIndex(Event ev, int key) {
	int row = BWTEnum.NOTFOUND;
	int selected_index = getSelectedIndex();
	JCListComponent l = list.getList();
	int num_items = l.getItems().size();
	char search_char = (ev.key == Event.DOWN) ? ' ' : (char) key;

	if (num_items < 1) return BWTEnum.NOTFOUND;

	// If editable, only one character can be entered to do search.
	if (isEditable() && ev.key != Event.DOWN) {
		if (!getText().equalsIgnoreCase(
				String.valueOf(search_char)))
			return BWTEnum.NOTFOUND;
	}


	if (isEditable() && getText().length() == 1) {
		// Use the only key entered to start the search
		search_char = getText().charAt(0);
	}

	if (search_char == ' ') {
		// Go to the next item.
		row = Math.max(0, selected_index+1);
		if (getWrapAroundSearch() && row >= num_items) {
			row = 0;
		}
		else if (row >= num_items) {
			// make sure the row doesn't go out of bounds
			row = selected_index;
		}
		return row;
	}


	if (selected_index == BWTEnum.NOTFOUND ||
		(last_key != key && ev.key != Event.DOWN) ||
		(selected_index != BWTEnum.NOTFOUND &&
		!getSelectedItem().equalsIgnoreCase(String.valueOf(search_char))
		)) {
		// If no selected item or start a new search by a new character,
		// or selected item does not start with the seach char,
		// search from the top
		row = l.findItem(search_char);
	}
	else {
		// Starting at selected item, find next item.
		if (selected_index+1 < num_items) {
			row = l.findNextItem(search_char, selected_index+1);
		}
		if (getWrapAroundSearch() && row == BWTEnum.NOTFOUND) {
			// wrap around and start search at the beginning
			row = l.findItem(search_char);
		}
	}

	return row;
}

/**
 * Only support single character search.
 * Always wrap around even though there is an unexposed
 * WrapAroundSearch property.
 * If ComboBox is editable, search is triggered only if one character
 * is entered in the text field.
 */
protected int getPrevAutoSearchIndex(Event ev, int key) {
	int row = BWTEnum.NOTFOUND;
	int selected_index = getSelectedIndex();
	JCListComponent l = list.getList();
	int num_items = l.getItems().size();
	char search_char = (ev.key == Event.UP) ? ' ' : (char) key;

	if (num_items < 1)
		return BWTEnum.NOTFOUND;

	// If editable, only one character can be entered to do search.
	if (isEditable() && ev.key != Event.UP) {
		if (!getText().equalsIgnoreCase(String.valueOf(search_char)))
			return BWTEnum.NOTFOUND;
	}

	if (isEditable() && getText().length() == 1) {
		// Use the only key entered to start the search
		search_char = getText().charAt(0);
	}

	if (search_char == ' ') {
		// Go to the previous item.
		row = selected_index-1;
		if (getWrapAroundSearch() && row < 0) {
			row = num_items-1;
		}
		else if (row < 0) {
			// make sure the row is not negative
			row = selected_index;
		}
		return row;
	}

	if (selected_index == BWTEnum.NOTFOUND
		|| (last_key != key && ev.key != Event.UP)
		|| (selected_index != BWTEnum.NOTFOUND
			&& !getSelectedItem().equalsIgnoreCase(String.valueOf(search_char)))) {
		// If no selected item or start a new search by a new character,
		// or selected item does not start with the seach char,
		// search from the bottom
		row = l.findPrevItem(search_char, num_items-1);
	}
	else {
		// Have selection, find previous item.
		if (selected_index-1 < num_items) {
			row = l.findPrevItem(search_char, selected_index-1);
		}
		if (getWrapAroundSearch() && row == BWTEnum.NOTFOUND) {
			// wrap around and start search at the end
			row = l.findPrevItem(search_char, num_items-1);
		}
	}

	return row;
}

transient int last_key = (int) ' ';

/**
 * Is spinning of the combo value with the up and down cursor keys allowed
 */
public boolean isArrowKeySpinningAllowed() {
	return arrow_key_spinning_allowed;
}

/**
 * Is spinning of the combo value with the up and down cursor keys allowed
 */
public void setArrowKeySpinningAllowed(boolean spin) {
	arrow_key_spinning_allowed = spin;
}

/**
 * Display the list if CTRL-DOWN pressed.
 * If it is editable and there is only one character is in the text field,
 * or UP or DOWN key is entered, do list search.
 * If it is not editable, entered key triggers list search.
 */
public boolean keyDown(Event ev, int key) {
	if (ev.target == text) {
		int row = BWTEnum.NOTFOUND;
		if (ev.controlDown() && ev.key == Event.DOWN) {
			showListAction(new JCActionEvent(text, ev.id, null, ev.modifiers));
			return true;
		}
		else if (ev.key == Event.DOWN) {
			if (arrow_key_spinning_allowed) {
				row = getNextAutoSearchIndex(ev, key);
			}
		}
		else if (ev.key == Event.UP) {
			if (arrow_key_spinning_allowed) {
				row = getPrevAutoSearchIndex(ev, key);
			}
		}
		else {
			row = getNextAutoSearchIndex(ev, key);
			// Update last entered key.  Arrow keys do not count.
			last_key = key;
		}
		if (row != BWTEnum.NOTFOUND) {
			list.deselectAll();
			list.makeVisible(row);
			if (!isEditable() || (ev.key == Event.DOWN || ev.key == Event.UP)) {
				// Change text value and make selection if the combo box is non-editable,
				// or up/down arrow is pressed.
				list.select(row);
				list.getList().setFocus(row);
			}
		}
	}
	return super.keyDown(ev, key);
}

/** If the arrow button was clicked, calls showListAction to display the list.<br>
 * If the list is displayed and the RETURN key was hit,
 * calls setTextAction to copy the selected item to the text field.
 * @see #showListAction
 * @see #setTextAction
 */
public void actionPerformed(JCActionEvent ev) {
  if(isEnabled() == false) {
      return;
  }

	if (ev.getSource() == button) {
		long dt = System.currentTimeMillis() - list_popdown_time;
		// lostFocus() of the popup list may hide the list before actionPerformed
		// is called.  Check the event time difference.  If it is small and
		// the list is hidden, don't do anything.
		if (!list_poppedup && dt >= 0 && dt < 150) {
			return;
		}

		if (list_poppedup) {
			hidePopup();
		} else {
			list_poppedup_time = System.currentTimeMillis();
			showListAction(ev);
		}
	}
	else if (ev.getSource() == list) {
		// Selected an item.  Hide the list.
		setTextAction(getSelectedItem());
		hidePopup();
	}

	// Post text field's event to listeners
	else if (ev.getSource() == text && actionListeners.size() > 0) {
		JCActionEvent a =
			new JCActionEvent(this, JCActionEvent.ACTION_PERFORMED, null);
		for (int i=0; i < actionListeners.size(); i++)
			((JCActionListener)actionListeners.elementAt(i)).actionPerformed(a);
	}
}

/** JCListListener method - no-op */
public void listItemSelectBegin(JCListEvent ev) {}

/** If the user clicked an item,
 * calls setTextAction to copy the item to the text field.
 * @see #setTextAction
 */
public void listItemSelectEnd(JCListEvent ev) {
	last_list_event = ev;
	if (ev.getStateChange() != JCItemEvent.SELECTED)
		return;

	if (ev.event == null || ev.event.id == Event.MOUSE_DOWN)
		setTextAction(getSelectedItem());
}

/**
 * Sets the layout manager for this container.  This method is
 * overridden to prevent the layout mgr from being set.
 */
public final void setLayout(LayoutManager mgr) {}

/** Overrides setBorder so the children get the border */
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
 * Draws the shadows and title.
 */
public void paintInterior(Graphics gc) {
	if (style == COMBOBOX_SIMPLE || border == 0) return;
	Color bg = getBackground();

/* SWING_START */
	if (swing_border != null) {
		swing_border.paintBorder(this, gc, 0, 0, size().width, size().height);
	}
	else {
/* SWING_END */
		Border.draw(gc, Border.IN, 1, 0, 0,
					size().width, size().height,
					bg == Color.lightGray ? Color.white : Border.brighter(bg),
					BWTUtil.darker(bg), null);
		if (border > 1) {
			Border.draw(gc, Border.IN, 1, 1, 1,
						size().width-2, size().height-2,
						bg.brighter().darker(), Color.black, null);
		}
/* SWING_START */
	}
/* SWING_END */
}

/**
 * Add key listener to the TextComponent
 * Since the JCComboBox itself never receives focus it is necessary
 * to be able to set it
 * @see removeKeyListener
 */
/* JDK110_START */
public void addKeyListener(KeyListener l) {
	if (text != null) {
		text.addKeyListener(l);
	}
}
/* JDK110_END */

/**
 * Remove focus listener to the TextComponent
 * @see addKeyListener
 */
/* JDK110_START */
public void removeKeyListener(KeyListener l) {
	if (text != null) {
		text.removeKeyListener(l);
	}
}
/* JDK110_END */

/**
 * Add focus listener to the TextComponent
 * Since the JCComboBox itself never receives focus it is necessary
 * to be able to set it
 * @see removeFocusListener
 */
/* JDK110_START */
public void addFocusListener(FocusListener l) {
	if (text != null) {
		text.addFocusListener(l);
	}
}
/* JDK110_END */

/**
 * Remove focus listener to the TextComponent
 * @see addFocusListener
 */
/* JDK110_START */
public void removeFocusListener(FocusListener l) {
	if (text != null) {
		text.removeFocusListener(l);
	}
}
/* JDK110_END */


///////////////////////////////////////////
//
// JCTextInterface
//

/**
 * Returns the selected text contained in this component.
 * @see #setText
 */
public String getSelectedText() {
	return text.getSelectedText();
}

/**
 * Returns the boolean indicating whether this component is editable or not.
 * @see #setEditable
 */
public boolean isEditable() {
	return text.isEditable();
}

/**
 * Returns the boolean indicating whether this component is editable or not.
 * @see #setEditable
 */
public boolean getEditable() {
	return text.getEditable();
}

/**
 * Determines whether this component is editable.
 * @see #isEditable
 */
public void setEditable(boolean t) {
	text.setEditable(t);
}

/**
 * Returns the selected text's start position.
 */
public int getSelectionStart() {
	return text.getSelectionStart();
}

/**
 * Returns the selected text's end position.
 */
public int getSelectionEnd() {
	return text.getSelectionEnd();
}

/**
 * Sets the selected text's start position.
 */
public void setSelectionStart(int pos) {
	text.setSelectionStart(pos);
}

/**
 * Sets the selected text's end position.
 */
public void setSelectionEnd(int pos) {
	text.setSelectionEnd(pos);
}

/**
 * Selects the text found between the specified start and end locations.
 */
public void select(int start, int end) {
	text.select(start, end);
}

/**
 * Selects all the text in the component.
 */
public void selectAll() {
	text.selectAll();
}

/**
 * Inserts text at the specified position.
 * @see #setText
 * @see #replaceRange
 */
public void insert(String str, int pos) {
	text.insert(str, pos);
}

/**
 * Appends text to the end.
 * @see #insert
 */
public void append(String str) {
	text.append(str);
}

/**
 * Replaces text between two positions with the specified text.
 */
public void replaceRange(String str, int start, int end) {
	text.replaceRange(str, start, end);
}

/**
 * Returns the number of columns.
 */
public int getColumns() {
	return text.getColumns();
}

/**
 * Sets the number of columns.
 */
public void setColumns(int v) {
	text.setColumns(v);
}

/** Gets the field's alignment.
 * @see #setAlignment
 */
public int getAlignment() {
	return text.getAlignment();
}

/** Sets the position of the text:<br>
BWTEnum.LEFT (default), CENTER, or RIGHT.
 */
public void setAlignment(int v) {
	text.setAlignment(v);
}

/** Gets the MaximumLength value.
 * @see #setMaximumLength
 */
public int getMaximumLength() {
	return text.getMaximumLength();
}

/** Sets the maximum number of characters which may be entered by the user
 * (default: BWTEnum.MAXINT). If the user attempts to enter more characters,
 * the values will be ignored and the bell will be sounded.
 * This value is ignored for values set programmatically.
 */
public void setMaximumLength(int v) {
	text.setMaximumLength(v);
}

/** Gets the StringCase value.
 * @see #setStringCase
 */
public int getStringCase() {
	return text.getStringCase();
}

/** Sets the case of text entered by the user or set programmatically:
<pre>
    BWTEnum.CASE_AS_IS       No conversion (default)
    BWTEnum.CASE_LOWER       Convert to lower case
    BWTEnum.CASE_UPPER       Convert to upper case
</pre>
 */
public void setStringCase(int v) {
	text.setStringCase(v);
}

/** Emits a beep. */
public void beep() {
	text.beep();
}

/** Returns true if the user has changed the value since the last
 * 	programmatic setting.
 *	@see #setChanged
 */
public boolean getChanged() {
	return text.getChanged();
}

/** Resets the Changed flag.
 * @see jclass.bwt.JCTextComponent#setChanged
 */
public void setChanged(boolean v) {
	text.setChanged(v);
}

/**
 * Returns the minimum size needed for the specified number of columns.
 */
public Dimension getMinimumSize(int columns) {
	return text.getMinimumSize(columns);
}

/** Gets the current SelectionList.
 * @see #setSelectionList
 */
public int[] getSelectionList() {
	return text.getSelectionList();
}

/** Sets a list of values used for multi-click. As the mouse is clicked in
 * rapid succession, each click selects the next type in the list.
 * Valid BWTEnum values (in order of the default list):
 * <pre>
 * SELECT_POSITION     Selects the current pointer position
 * SELECT_WORD         Selects the current word
 * SELECT_LINE         Selects the current line
 * SELECT_PARAGRAPH    Selects the current paragraph
 * SELECT_ALL          Selects all the text
 * </pre>
 * Default list: { BWTEnum.SELECT_POSITION, SELECT_WORD, SELECT_LINE, SELECT_ALL }
 */
public void setSelectionList(int[] list) {
	text.setSelectionList(list);
}

/**
 * Gets the SelectedBackground value.
 * @see #setSelectedBackground
 */
public Color getSelectedBackground() {
	return text.getSelectedBackground();
}

/**
 * Sets the background color of selected text (default: blue).<p>
 * <strong>HTML param name/value:</strong> "SelectedBackground"/color
 * @see jclass.util.JCConverter#toColor
 */
public void setSelectedBackground(Color v) {
	text.setSelectedBackground(v);
}

/**
 * Gets the SelectedForeground value.
 * @see #setSelectedForeground
 */
public Color getSelectedForeground() {
	return text.getSelectedForeground();
}

/**
 * Sets the foreground color of selected text (default: background color).<p>
 * <strong>HTML param name/value:</strong> "SelectedForeground"/color
 * @see jclass.util.JCConverter#toColor
 */
public void setSelectedForeground(Color v) {
	text.setSelectedForeground(v);
}

/**
 * Maps a physical position to the corresponding character position.
 */
public int pointToPosition(int x, int y) {
	return text.pointToPosition(x, y);
}

/**
 * Scrolls the text if necessary to ensure that the position is visible.
 * @param pos the number of characters from the beginning of the text buffer.
 */
public void showPosition(int pos) {
	text.showPosition(pos);
}

/** Gets the position of the cursor.
 * @see #setCursorPosition
 */
public int getCursorPosition() {
	return text.getCursorPosition();
}

/**
 * Sets the position of the cursor.
 */
public void setCursorPosition(int pos) {
	text.setCursorPosition(pos);
}

/** If false (default), characters typed by the user are inserted into
 * the current text.
 */
public void setOverstrike(boolean v) {
	text.setOverstrike(v);
}

/** Gets the Overstrike value.
 * @see #setOverstrike
 */
public boolean getOverstrike() {
	return text.getOverstrike();
}

/** Gets the position of the last character in the text.
 * This represents the position that text appended to the component would be
 * placed after.
 */
public int getLastPosition() {
	return text.getLastPosition();
}

/** Gets the ShowCursorPosition value.
 * @see #setShowCursorPosition
 */
public boolean getShowCursorPosition() {
	return text.getShowCursorPosition();
}

/**
 * If set to true (default), the cursor position will be indicated with
 * a vertical I-beam.
 */
public void setShowCursorPosition(boolean v) {
	text.setShowCursorPosition(v);
}

/**
 * Adds the specified listener to receive text events.
 * @see JCTextEvent
 */
public void addTextListener(JCTextListener l) {
	text.addTextListener(l);
}

/**
 * Removes the specified text listener so it no longer receives text events.
 */
public void removeTextListener(JCTextListener l) {
	text.removeTextListener(l);
}

/**
 * Adds the specified JCTextCursorEvent listener to receive cursor movement events.
 * @see JCTextCursorEvent
 */
public void addTextCursorListener(JCTextCursorListener l) {
	text.addTextCursorListener(l);
}

/**
 * Removes the specified listener so it no longer receives JCTextCursorEvents.
 * @see #addTextCursorListener
 */
public void removeTextCursorListener(JCTextCursorListener l) {
	text.removeTextCursorListener(l);
}


/** Gets the shadow's thickness.<p>
 * <strong>HTML param name/value:</strong> "ShadowThickness"/int
 * @see #setShadowThickness
 */
public int getShadowThickness() { return border; }

/** Sets the shadow's thickness (default: 2) */
public void setShadowThickness(int v) {
	border = v;
	if (style == COMBOBOX_SIMPLE) {
		text.setBorderThickness(v);
		setInsets(new Insets(0, 0, 0, 0));
	}
	else {
		text.setBorderThickness(0);
		border = v;
		setInsets(new Insets(border, border, border, border));
	}
}

/** Gets the thickness of the highlight rectangle.
 * @see #setHighlightThickness
 */
public int getHighlightThickness() { return text.getHighlightThickness(); }

/** Sets the thickness of the rectangle drawn when the component has focus (default: 2).<p>
 * <strong>HTML param name/value:</strong> "HighlightThickness"/int
 * @see jclass.util.JCConverter#toInt
 */
public void setHighlightThickness(int v) {
	text.setHighlightThickness(v);
}

/** Gets the color of the highlight rectangle.
 * @see #setHighlightColor
 */
public Color getHighlightColor() {
	return text.getHighlightColor();
}

/** Sets the color of the rectangle drawn when the component has focus
 * (default: black).<p>
 * <strong>HTML param name/value:</strong> "HighlightColor"/Color
 * @see jclass.util.JCConverter#toColor
 */
public void setHighlightColor(Color v) {
	text.setHighlightColor(v);
}

/**
 * Called anytime the number of items are changed to check whether or not
 * to disable/enable the arrow button.  The arrow button is disabled if there
 * are no items to display <p>
 * If bypassing this components setItems to change the number of items in
 * the list subcomponent then one must manually call this method.
 */
public void checkButton() {
	if (button == null) {
		return;
	}
	if (list.countItems() > 0) {
		if (button.isEnabled() == false) {
			button.setEnabled(true);
		}
	}
	else if (button.isEnabled() == true) {
		button.setEnabled(false);
	}
}


		//
		// jclass.cell.CellEditor Implementation
		//

/* JDK110_START */
public void initialize(InitialEvent ev, CellInfo info, Object o) {
	hidePopup();
	String s = (String) o;
	setHighlightThickness(0);
	text.setHighlightThickness(0);
	// we only support uniform border thickness so we randomly use the
	// top border thickness
	setShadowThickness(info.getBorderInsets().top);
	text.setInsets(info.getMarginInsets());
	setAlignment(info.getHorizontalAlignment());
	setFont(info.getFont());
	setBackground(info.getBackground());
	setForeground(info.getForeground());
	setSelectedBackground(info.getSelectedBackground());
	setSelectedForeground(info.getSelectedForeground());
	// let the text components handle a specific subset of initialization
	text.containedInitialize(ev, info, o);
}
/* JDK110_END */

/**
 * support function for the above initialize() method to be implemented
 * in the sub-class
 */

/* JDK110_START */
public Component getComponent() {
	return this;
}

public Object getCellEditorValue() {
	return text.getText();
}

public boolean stopCellEditing() {
//	System.out.println("stopCellEditing");
	hidePopup();
	return text.stopCellEditing();
}

public boolean isModified() {
	return text.isModified();
}

public void cancelCellEditing() {
//	System.out.println("stopCellEditing");
	hidePopup();
	text.cancelCellEditing();
}

public KeyModifier[] getReservedKeys() {
	return jclass.cell.Utilities.addKey(text.getReservedKeys(),
							  new KeyModifier(KeyEvent.VK_DOWN,
											  Event.CTRL_MASK, true));
}

public void addCellEditorListener(CellEditorListener l) {
	// this is okay to leave as a pass through since this is not a
	// BEAN listener
	text.addCellEditorListener(l);
}

public void removeCellEditorListener(CellEditorListener l) {
	// this is okay to leave as a pass through since this is not a
	// BEAN listener
	text.removeCellEditorListener(l);
}

		//
		// jclass.cell.CellRenderer Implementation Extensions
		//

public void draw(Graphics gc, CellInfo info, Object o, boolean selected) {
	text.draw(gc, info, o, selected);
}

public Dimension getPreferredSize(CellInfo cellInfo, Object o) {
	return text.getPreferredSize(cellInfo, o);
}


/* JDK110_END */

	//
	// java.awt.im.InputMethodRequests implementation
	//

}
