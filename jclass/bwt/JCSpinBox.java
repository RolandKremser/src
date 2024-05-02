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
// RCSID -- $RCSfile: JCSpinBox.java $ $Revision: 2.23 $ $Date: 2000/11/09 20:11:04 $ $Locker: $  Sitraka Inc.

package jclass.bwt;
import jclass.util.JCVector;
import jclass.base.*;
import java.awt.*;
import java.applet.*;
/* JDK110_START */
import java.awt.event.*;
/* JDK110_END */

class SpinArrowButton extends JCArrowButton {

JCSpinBox spin;

public SpinArrowButton(int orientation, JCSpinBox spin) {
	super(orientation);
	this.spin = spin;
}

public boolean mouseUp(Event ev, int x, int y) {
	boolean status = super.mouseUp(ev, x, y);
	// In a rare case that when mouse click is too fast and
	// the current arrow button does not receive mouseUp event,
	// we have to make sure both arrow buttons receive it to
	// disarm themselves.
	JCArrowButton ab = null;
	ab = this.equals(spin.decr_arrow) ?  spin.incr_arrow : spin.decr_arrow;
	if (ab != null && ab.isArmed()) {
		ab.mouseUp(ev, x, y);
	} 
	return status;
}

}

class SpinField extends JCTextField {

JCSpinBox spin;

SpinField(JCSpinBox spin) { 
	super(); this.spin = spin; 
}

/**
 * Overridden to make sure that the highlight is drawn in the field's
 * background color instead of the parent
 */
protected void drawHighlight(Graphics gc, boolean on) {
	if (highlight == 0) return;

	// Check that highlight needs to be drawn
	if (!intersects(paint_rect, highlight))
		return;
	int x=0, y=0, w=size().width, h=size().height;
	Color c = on ? highlight_color : getBackground();
	if (c == null) c = Color.black;
	gc.setColor(c);
	for (int i=0; i < highlight; i++, x++, y++, w-=2, h-=2) {
 		gc.drawRect(x, y, w-1, h-1);
	}
}

public boolean keyDown(Event ev, int key) {
	if (key == Event.UP || key == Event.DOWN) {
		spin.setTextAction(key == Event.UP ? JCSpinBox.INCREMENT : JCSpinBox.DECREMENT);
		return true;
	}
	return super.keyDown(ev, key);
}

/** Inserts a character at the current cursor position. 
 * @return false if the insertion was disallowed by the spinbox
 * @see JCSpinBox#validateKey
 */
protected boolean insertChar(Event ev, int key) {
	if (spin.validateKey((char)key)) return super.insertChar(ev, key);
	return false;
}

}	// SpinField

/**
 * JCSpinBox provides the capabilities of an JCTextField and 2 JCArrowButtons.
 * The user can choose from a range of numeric values.<p>
 * By default, the text field is editable. If the user attempts to enter an
 * invalid value, it is disallowed.<p>
 * 
 * <strong>Properties</strong><p>
 * <table border>
 * <tr><th>Name</th>    <th>Method</th></tr><tr><td>
 * AutoArrowDisable    	</td><td><a href=#setPosition>setAutoArrowDisable</a></td></tr><tr><td>
 * Alignment           	</td><td><a href=jclass.bwt.JCTextComponent.html#setAlignment>setAlignment</a></td></tr><tr><td>
 * Background          	</td><td><a href=java.awt.Component.html#setBackground>setBackground</a></td></tr><tr><td>
 * Changed              </td><td><a href=jclass.bwt.JCTextComponent.html#setChanged>setChanged</a></td></tr><tr><td>
 * Columns             	</td><td><a href=jclass.bwt.JCTextComponent.html#setColumns>setColumns</a></td></tr><tr><td>
 * CursorPosition      	</td><td><a href=jclass.bwt.JCTextComponent.html#setCursorPosition>setCursorPosition</a></td></tr><tr><td>
 * DecimalPlaces       	</td><td><a href=#setDecimalPlaces>setDecimalPlaces</a></td></tr><tr><td>
 * DoubleBuffer        	</td><td><a href=jclass.bwt.JCContainer.html#setDoubleBuffer>setDoubleBuffer</a></td></tr><tr><td>
 * Font                	</td><td><a href=jclass.bwt.JCContainer.html#setFont>setFont</a></td></tr><tr><td>
 * Foreground          	</td><td><a href=java.awt.Component.html#setForeground>setForeground</a></td></tr><tr><td>
 * Editable            	</td><td><a href=jclass.bwt.JCTextComponent.html#setEditable>setEditable</a></td></tr><tr><td>
 * HighlightColor      	</td><td><a href=jclass.bwt.JCComponent.html#setHighlightColor>setHighlightColor</a></td></tr><tr><td>
 * HighlightThickness  	</td><td><a href=jclass.bwt.JCComponent.html#setHighlightThickness>setHighlightThickness</a></td></tr><tr><td>
 * Increment           	</td><td><a href=#setIncrement>setIncrement</a></td></tr><tr><td>
 * Insets              	</td><td><a href=jclass.bwt.JCContainer.html#setInsets>setInsets</a></td></tr><tr><td>
 * Maximum             	</td><td><a href=#setMaximum>setMaximum</a></td></tr><tr><td>
 * MaximumLength       	</td><td><a href=jclass.bwt.JCTextComponent.html#setMaximumLength>setMaximumLength</a></td></tr><tr><td>
 * Minimum             	</td><td><a href=#setMinimum>setMinimum</a></td></tr><tr><td>
 * Overstrike          	</td><td><a href=jclass.bwt.JCTextComponent.html#setOverstrike>setOverstrike</a></td></tr><tr><td>
 * Position            	</td><td><a href=#setPosition>setPosition</a></td></tr><tr><td>
 * PreferredSize       	</td><td><a href=jclass.bwt.JCContainer.html#setPreferredSize>setPreferredSize</a></td></tr><tr><td>
 * SelectedBackground  	</td><td><a href=jclass.bwt.JCTextComponent.html#setSelectedBackground>setSelectedBackground</a></td></tr><tr><td>
 * SelectedForeground  	</td><td><a href=jclass.bwt.JCTextComponent.html#setSelectedForeground>setSelectedForeground</a></td></tr><tr><td>
 * SelectionEnd        	</td><td><a href=jclass.bwt.JCTextComponent.html#setSelectionEnd>setSelectionEnd</a></td></tr><tr><td>
 * SelectionList       	</td><td><a href=jclass.bwt.JCTextComponent.html#setSelectionList>setSelectionList</a></td></tr><tr><td>
 * SelectionStart      	</td><td><a href=jclass.bwt.JCTextComponent.html#setSelectionStart>setSelectionStart</a></td></tr><tr><td>
 * ShadowThickness  	</td><td><a href=jclass.bwt.JCComponent.html#setShadowThickness>setShadowThickness</a></td></tr><tr><td>
 * ShowCursorPosition  	</td><td><a href=jclass.bwt.JCTextComponent.html#setShowCursorPosition>setShowCursorPosition</a></td></tr><tr><td>
 * StringCase          	</td><td><a href=jclass.bwt.JCTextComponent.html#setStringCase>setStringCase</a></td></tr><tr><td>
 * Text                	</td><td><a href=#setText>setText</a></td></tr><tr><td>
 * UserData         	</td><td><a href=jclass.bwt.JCContainer.html#setUserData>setUserData</a></td></tr>
 * </table><p>
 *
 * <strong>Events</strong><p>
 * <table border><tr>
 * <th>Class</th> <th>Listener</th>   <th>Description</th></tr><tr><td>
 * JCSpinBoxEvent    </td><td><a href=#addSpinBoxListener>addSpinBoxListener</a></td> <td>Posted when the user changes the value</td></tr>
 * JCTextEvent   </td><td><a href=jclass.bwt.JCTextComponent.html#addTextListener>addTextListener</a></td> <td>Posted when the value changes</td></tr>
 * JCTextCursorEvent  </td><td><a href=jclass.bwt.JCTextComponent.html#addTextCursorListener>addTextCursorListener</a></td> <td>Posted when the cursor is moved</td></tr><tr><td>
 * </table>
 */
public class JCSpinBox extends JCContainer implements JCTextInterface, JCActionListener {

protected SpinField 		text;
protected SpinArrowButton 	decr_arrow, incr_arrow;
protected int				position = 0;
protected int				min = 0, max = BWTEnum.MAXINT;
protected int				incr = 1;
protected int				decimals = 0;
protected Object 			value;						// current value
protected boolean			auto_arrow_disable = true;

/** The container's shadow thickness. */
protected int 		border = 2;

protected final static int NONE = 0;
protected final static int INCREMENT = 1;
protected final static int DECREMENT = -1;

final static int BEGIN = 0;
final static int END = 1;

private int pow = (int) Math.pow(10., (double)decimals);
private char decimal_point_char;

/** List of JCSpinBoxEvent listeners */
protected JCVector listeners = new JCVector(0);
    
private static final String base = "spinbox";
private static int nameCounter = 0;

/** Creates an empty SpinBox. No parameters are read from an HTML file. */
public JCSpinBox() {
	this(null, null); 
}

/** Creates an empty SpinBox with the specified number of columns.
 * No parameters are read from an HTML file. 
 */
public JCSpinBox(int cols) {
	this(null, null);
	text.setColumns(cols);
}

/** Creates a SpinBox which reads parameters from the applet's HTML file.
 * @param applet the applet whose PARAM tags are to be read
 * @param name if this is not null, only parameters preceded by this name are read
 * @see java.applet.Applet#getParameter
 */
public JCSpinBox(Applet applet, String name) {
	super(applet, name);
	if (name == null)
		setName(base + nameCounter++);
	if (getClass().getName().equals("jclass.bwt.JCSpinBox"))
		 getParameters(applet);

	setLayout(null);
	add(text = new SpinField(this));
	add(incr_arrow = new SpinArrowButton(BWTEnum.UP, this));
	add(decr_arrow = new SpinArrowButton(BWTEnum.DOWN, this));
	incr_arrow.initial_delay = decr_arrow.initial_delay = 250;
	text.addActionListener(this);
/* JDK110_START */
    incr_arrow.setTraversable(false);
	decr_arrow.setTraversable(false);
/* JDK110_END */
	text.setBorderThickness(0);

	incr_arrow.addActionListener(this);
	decr_arrow.addActionListener(this);

	initTextValue();
}

/** Reads the parameter values from the HTML page using the component's applet.
 * The values will override those previously set.
 */
protected void getParameters() {
	super.getParameters();
	SpinBoxConverter.getParams(this);
}

/** Gets the component to be used as the data entry field. */
public JCTextField getTextField() { return text; }

/** Gets the decrement (down) button. */
public JCArrowButton getDecrementArrow() { return decr_arrow; }

/** Gets the increment (up) button. */
public JCArrowButton getIncrementArrow() { return incr_arrow; }

/** Gets the AutoArrowDisable value.
 * @see #setAutoArrowDisable
 */
public boolean getAutoArrowDisable() { return auto_arrow_disable; }

/** If true (default), the increment or decrement arrow button is disabled 
 * when the end of the range is reached. If set to false, the buttons are
 * always enabled.<p>
 * <strong>HTML param name/value:</strong> "AutoArrowDisable"/boolean
 */
public void setAutoArrowDisable(boolean v) { 
	auto_arrow_disable = v; 
	enableArrowButtons();
}

/** Gets the DecimalPlaces value.
 * @see #setDecimalPlaces
 */
public int getDecimalPlaces() { return decimals; }

/** Sets the number of decimal points used when displaying the value
 * (default: 0).<p>
 * <strong>HTML param name/value:</strong> "DecimalPlaces"/int
 */
public void setDecimalPlaces(int v) {
	decimals = v;
	pow = (int) Math.pow(10., (double)v);
	setTextValue(value);
}

/** Gets the Increment value.
 * @see #setIncrement
 */
public int getIncrement() { return incr; }

/** Sets the amount by which to increment or decrement the value (default: 1).<p>
 * <strong>HTML param name/value:</strong> "Increment"/int
 */
public void setIncrement(int v) {
	incr = v; 
}

/** Gets the Minimum value.
 * @see #setMinimum
 */
public int getMinimum() { return min; }

/** Sets the lowest possible value (default: 0).<p>
 * <strong>HTML param name/value:</strong> "Minimum"/int
 */
public void setMinimum(int v) {
	min = v; 
	validate(value);
}

/** Gets the Maximum value.
 * @see #setMaximum
 */
public int getMaximum() { return max; }

/** Sets the highest possible value (default: BWTEnum.MAXINT).<p>
 * <strong>HTML param name/value:</strong> "Maximum"/int
 */
public void setMaximum(int v) {
	max = v;
	validate(value);
}

/** Gets the Position value.
 * @see #setPosition
 */
public int getPosition() { return position; }

/** Sets the position of the currently displayed item (default: 0)
 * (the position in the range of possible values).
 * <strong>HTML param name/value:</strong> "Position"/int
 */
public void setPosition(int v) {
	position = v;
	value = new Integer((position - min) / incr + min);
	setTextValue(value);
	enableArrowButtons();
}

/** Gets the current value as an int. */
public int getIntValue() {
	if (value instanceof Integer)
		return ((Integer)value).intValue();
	return Integer.parseInt(text.getText()); 
}

/** Sets the current value as an int. */
public void setIntValue(int v) {
	value = new Integer(v);
	setTextValue(value);
	enableArrowButtons();
}

/** Gets the current value as a string. */
public String getValue() { return text.getText(); }

/**
 * Sets the text field's value to the specified string.<p>
 * <strong>HTML param name/value:</strong> "Text"/string
 */
public synchronized void setText(String s) { 
	if (s == null || s.length() == 0) 
		setIntValue(min);
	else {
		setTextValue(s);
		enableArrowButtons();
	}
}

/**
 * Gets the value of the text.
 * @see #setText
 */
public synchronized String getText() { return text.getText(); }

/**
 * Adds the specified JCSpinBoxEvent listener to receive value change events.
 * @see JCSpinBoxEvent
 */ 
public void addSpinBoxListener(JCSpinBoxListener l) {
	listeners.addUnique(l);
}

/**
 * Removes the specified listener so it no longer receives JCSpinBoxEvents.
 * @see #addSpinBoxListener
 */ 
public void removeSpinBoxListener(JCSpinBoxListener l) {
	listeners.removeElement(l);
}

/** Returns the sum of the text field's and arrow button's preferred widths. */
protected int preferredWidth() {
	return JCComponent.getPreferredSize(text).width + 
		JCComponent.getPreferredSize(incr_arrow).width +
		2*border;
}

/** Returns the text field's preferred height. */
protected int preferredHeight() {
	return 2*border + JCComponent.getPreferredSize(text).height;
}

/** Positions the text field and arrow buttons. */
public synchronized void layout() {
	int text_height = JCComponent.getPreferredSize(text).height,
		btn_height = text_height/2,
		btn_width = JCComponent.getPreferredSize(incr_arrow).width, 
		btn_x = size().width - btn_width - border;
	int r = text_height % 2;
	JCComponent.setBounds(text, border, border, 
				 size().width - btn_width - 2*border, btn_height*2);
	JCComponent.setBounds(incr_arrow, btn_x, border, btn_width, btn_height);
	JCComponent.setBounds(decr_arrow, btn_x, btn_height+border, btn_width, 
						  btn_height+r);
	enableArrowButtons();
}

/** Sets background of the Spin field */
public void setBackground(Color background) {
	super.setBackground(background);
	decr_arrow.setBackground(background);
	incr_arrow.setBackground(background);
}

/** Sets foreground color of the Spin field */
public void setForeground(Color foreground) {
	super.setForeground(foreground);
	decr_arrow.setForeground(foreground);
	incr_arrow.setForeground(foreground);
}

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

/** Draws the shadows. */
public void paintInterior(Graphics gc) {
	gc.clearRect(0, 0, size().width, size().height);

/* SWING_START */
	if (swing_border != null) {
		swing_border.paintBorder(this, gc, 0, 0, size().width, size().height);
	}
	else {
/* SWING_END */
		Border.draw(gc, Border.IN, border, 0, 0,
					size().width, size().height,
					getBackground(), getForeground());
/* SWING_START */
	}
/* SWING_END */
}

/**
 * Add key listener to the TextComponent 
 * Since the JCSpinBox itself never receives focus it is necessary
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
 * Since the JCSpinBox itself never receives focus it is necessary
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
// Implement JCTextInterface
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
 * programmatic setting.
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
public int getShadowThickness() { return text.getBorderThickness(); }

/** Sets the shadow's thickness (default: 2) */
public void setShadowThickness(int v) {
	text.setBorderThickness(v);
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



/** Posts a JCSpinBoxEvent
 * @return the posted event, or null if there are no listeners
 * @see #addSpinBoxListener
 * @see JCSpinBoxEvent
 */
protected JCSpinBoxEvent
postSpinBoxEvent(int stage, Object value) {
	if (listeners.size() == 0) 
		return null;

	JCSpinBoxEvent spin_ev = new JCSpinBoxEvent(this, position, value);

	for (int i=0; i < listeners.size(); i++) {
		JCSpinBoxListener l = (JCSpinBoxListener) listeners.elementAt(i);
		if (stage == BEGIN) 
			l.spinBoxChangeBegin(spin_ev);
		else
			l.spinBoxChangeEnd(spin_ev);
	}
	return spin_ev;
}

/** Gets the decimal point char. */
protected char getDecimalPointChar() {
	if (decimal_point_char != 0) return decimal_point_char;
	return (decimal_point_char = new Double(5.5).toString().charAt(1));
}

/** Gets the value to be displayed
 * @param dir INCREMENT, DECREMENT or NONE
 */
protected Object calcValue(int dir) {
	int inc = (dir == NONE) ? 0 : (dir == INCREMENT ? incr : -incr);
	if (value == null) value = new Integer(min);
	int v = ((Integer)value).intValue() + inc;
	v = Math.max(Math.min(v, max), min);
	position = (v - min) / incr;
	return new Integer(v);
}

/** Sets the text field's initial value. */
protected void initTextValue() {
	value = new Integer(min);
	position = 0;
	setTextValue(value);
}

/** Sets the text field's value. */
protected void setTextValue(Object value) {
	String v = (value != null) ? value.toString() : null;
	if (decimals > 0) {
		v = new Double(((Integer)value).doubleValue() / pow).toString();
		int p = v.indexOf(getDecimalPointChar());
		if (p == -1) {
			v += getDecimalPointChar();
			p = v.length() - 1;
		}
		for (int i=v.length()-p-1; i < decimals; i++)
			v += '0';
	}

	text.setText(v);
	postSpinBoxEvent(END, value);
}

/** Sets the text field's value after an arrow key is hit.
 * @param dir INCREMENT, DECREMENT or NONE
 */
public void setTextAction(int dir) {

	// If the user has changed the text's value, validate it
	if (text.getChanged()) {
		if (!validate(text.getText())) {
			text.beep();
			return;
		}
	}

	Object new_value = calcValue(dir);
	JCSpinBoxEvent spin_ev = postSpinBoxEvent(BEGIN, new_value);
	if (spin_ev != null) {
		if (!spin_ev.doit) return;
		if (new_value != spin_ev.value)
			if (!validate(spin_ev.value)) return;
		new_value = spin_ev.value;
	}

	value = new_value;
	setTextValue(value);
	enableArrowButtons();
}

/** Disables the arrow buttons if the end of the range is reached.
 * @see #setAutoArrowDisable
 */
protected void enableArrowButtons() {
	if (!auto_arrow_disable) {
		incr_arrow.enable(true);
		decr_arrow.enable(true);
	}
	else {
		int v = ((Integer)value).intValue();
		incr_arrow.enable(v < max);
		decr_arrow.enable(v > min);
	}
}

/** Validates a key typed into the text field.
 * @return false if the key is invalid
 */
protected boolean validateKey(char key) {
	if (decimals == 0) {
		return Character.isDigit(key) || 
			(key == '-' && text.getText().indexOf('-') == -1);
	}
	return Character.isDigit(key) || 
			(key == '.' && text.getText().indexOf('.') == -1) ||
			(key == '-' && text.getText().indexOf('-') == -1);
}

/** Validates the text field's value after the user has changed it.
 * If the value is valid, the position is determined.
 * @see #setPosition
 * @return false if the value is invalid
 */
protected boolean validate(Object value) {
	boolean valid = false;
	char dec_pt = getDecimalPointChar();

	if (value instanceof String) {
		String s = (String) value;
		int v;
		try {
			if (decimals == 0) {
				v = Integer.parseInt(s);
			}
			else {
				if (s.indexOf(dec_pt) == 0)
					s += '.';
				double dbl_v = Double.valueOf(s).doubleValue();
				v = (int) Math.ceil(dbl_v*pow);
			}
			if (valid = (v >= min && v <= max)) {
				position = (v - min) / incr;
				this.value = new Integer(v);
			}
		} catch (Exception e) { valid = false; }
	}
	else if (value instanceof Integer) {
		int v = ((Integer)value).intValue();
		if (valid = (v >= min && v <= max)) 
			position = (v - min) / incr;
	}
	else if (value instanceof Number) {
		double v = ((Double)value).doubleValue();
		if (valid = (v >= min && v <= max)) 
			position = ((int)v - min) / incr;
	}
	return valid;
}

/** 
 * If an arrow button was pressed, calls setTextAction.<br>
 * If the user changed the text field's value, calls validate.
 * @see #setTextAction
 * @see #validate
 */
public void actionPerformed(JCActionEvent ev) {
	if (ev.getSource() == incr_arrow) 
		setTextAction(INCREMENT);

	else if (ev.getSource() == decr_arrow) 
		setTextAction(DECREMENT);

	else if (ev.getSource() == text && text.getEditable()) 
		setTextAction(NONE);
}

/** 
 * Sets the layout manager for this container.  This method is
 * overridden to prevent the layout mgr from being set.
 */
public final void setLayout(LayoutManager mgr) {}

	//
	// java.awt.im.InputMethodRequests implementation
	//

}
