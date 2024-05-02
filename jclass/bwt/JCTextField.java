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
import jclass.util.JCVector;
import java.awt.*;
import java.applet.Applet;
/* JDK110_START */
import java.awt.event.*;
import jclass.cell.*;
/* JDK110_END */

/**
 * JCTextField is a component that allows the editing of a single line of text.<p>
 * <p>
 * <strong>Properties</strong><p>
 * <table border>
 * <tr><th>Name</th>    <th>Method</th></tr><tr><td>
 * Alignment           </td><td><a href=jclass.bwt.JCTextComponent.html#setAlignment>setAlignment</a></td></tr><tr><td>
 * Background          </td><td><a href=jclass.bwt.JCComponent.html#setBackground>setBackground</a></td></tr><tr><td>
 * CaretPosition       </td><td><a href=jclass.bwt.JCTextComponent.html#setCaretPosition>setCaretPosition</a></td></tr><tr><td>
 * Changed             </td><td><a href=jclass.bwt.JCTextComponent.html#setChanged>setChanged</a></td></tr><tr><td>
 * Columns             </td><td><a href=jclass.bwt.JCTextComponent.html#setColumns>setColumns</a></td></tr><tr><td>
 * CursorPosition      </td><td><a href=jclass.bwt.JCTextComponent.html#setCursorPosition>setCursorPosition</a></td></tr><tr><td>
 * DoubleBuffer        </td><td><a href=jclass.bwt.JCComponent.html#setDoubleBuffer>setDoubleBuffer</a></td></tr><tr><td>
 * EchoChar            </td><td><a href=#setEchoChar>setEchoChar</a></td></tr><tr><td>
 * Editable            </td><td><a href=jclass.bwt.JCTextComponent.html#setEditable>setEditable</a></td></tr><tr><td>
 * Font                </td><td><a href=jclass.bwt.JCComponent.html#setFont>setFont</a></td></tr><tr><td>
 * Foreground          </td><td><a href=jclass.bwt.JCComponent.html#setForeground>setForeground</a></td></tr><tr><td>
 * HighlightColor      </td><td><a href=jclass.bwt.JCComponent.html#setHighlightColor>setHighlightColor</a></td></tr><tr><td>
 * HighlightThickness  </td><td><a href=jclass.bwt.JCComponent.html#setHighlightThickness>setHighlightThickness</a></td></tr><tr><td>
 * Insets              </td><td><a href=jclass.bwt.JCComponent.html#setInsets>setInsets</a></td></tr><tr><td>
 * MaximumLength       </td><td><a href=jclass.bwt.JCTextComponent.html#setMaximumLength>setMaximumLength</a></td></tr><tr><td>
 * Overstrike          </td><td><a href=jclass.bwt.JCTextComponent.html#setOverstrike>setOverstrike</a></td></tr><tr><td>
 * PreferredSize       </td><td><a href=jclass.bwt.JCComponent.html#setPreferredSize>setPreferredSize</a></td></tr><tr><td>
 * SelectedBackground  </td><td><a href=jclass.bwt.JCTextComponent.html#setSelectedBackground>setSelectedBackground</a></td></tr><tr><td>
 * SelectedForeground  </td><td><a href=jclass.bwt.JCTextComponent.html#setSelectedForeground>setSelectedForeground</a></td></tr><tr><td>
 * SelectionEnd        </td><td><a href=jclass.bwt.JCTextComponent.html#setSelectionEnd>setSelectionEnd</a></td></tr><tr><td>
 * SelectionList       </td><td><a href=jclass.bwt.JCTextComponent.html#setSelectionList>setSelectionList</a></td></tr><tr><td>
 * SelectionStart      </td><td><a href=jclass.bwt.JCTextComponent.html#setSelectionStart>setSelectionStart</a></td></tr><tr><td>
 * ShadowThickness  </td><td><a href=jclass.bwt.JCComponent.html#setShadowThickness>setShadowThickness</a></td></tr><tr><td>
 * ShowCursorPosition  </td><td><a href=jclass.bwt.JCTextComponent.html#setShowCursorPosition>setShowCursorPosition</a></td></tr><tr><td>
 * StringCase          </td><td><a href=jclass.bwt.JCTextComponent.html#setStringCase>setStringCase</a></td></tr><tr><td>
 * Text                </td><td><a href=jclass.bwt.JCTextComponent.html#setText>setText</a></td></tr><tr><td>
 * Traversable         </td><td><a href=jclass.bwt.JCComponent.html#setTraversable>setTraversable</a></td></tr><tr><td>
 * UserData            </td><td><a href=jclass.bwt.JCComponent.html#setUserData>setUserData</a></td></tr>
 * </table><p>
 *
 * <strong>Events</strong><p>
 * <table border><tr>
 * <th>Class</th> <th>Listener</th>   <th>Description</th></tr><tr><td>
 * JCActionEvent    </td><td><a href=#addActionListener>addActionListener</a></td> <td>Posted when the ENTER key is hit</td></tr><tr><td>
 * JCTextCursorEvent  </td><td><a href=jclass.bwt.JCTextComponent.html#addTextCursorListener>addTextCursorListener</a></td> <td>Posted when the cursor is moved</td></tr><tr><td>
 * JCTextEvent   </td><td><a href=jclass.bwt.JCTextComponent.html#addTextListener>addTextListener</a></td> <td>Posted when the value changes</td></tr>
 * </table>
 */
public class JCTextField extends JCTextComponent {

char 		echo_char;
String 		actionCommand;

/** List of JCActionEvent listeners. */
protected JCVector actionListeners = new JCVector(0);

private static final String base = "textfield";
private static int nameCounter = 0;

/**
 * Creates an empty, editable field with 20 columns.
 */
public JCTextField() {
	this("", 20);
}

/**
 * Creates an editable field with the specified text and 20 columns.
 */
public JCTextField(String text) {
	this(text, 20);
}

/**
 * Creates an editable field with the specified text and number of columns.
 */
public JCTextField(String text, int cols) {
	this(text, null, null);
	columns = cols;
}

/** Creates a text field which reads parameters from the applet's HTML file.
 * @param applet the applet whose PARAM tags are to be read
 * @param name if this is not null, only parameters preceded by this name are read
 * @see java.applet.Applet#getParameter
 * @see jclass.util.JCString
 */
public JCTextField(String text, Applet applet, String name) {
	super(applet, name);
	setInsets(new Insets(2,2,2,2));
	if (name == null)
		setName(base + nameCounter++);
	setText(text);
}

/**
 * Sets the text of this component to the specified text.<p>
 * <strong>HTML param name/value:</strong> "Text"/string
 * @see #getText
 */
public void setText(String text) {
	if (getText().equals(text)) {
		return;
	}
	super.setText(text);
}

/** Reads the parameter values from the HTML page using the component's applet.
 * The values will override those previously set.
 */
protected void getParameters() {
	super.getParameters();
}

/**
 * Returns the character to be used for echoing, or 0 if no char has been set.
 * @see #setEchoChar
 */
public char getEchoChar() { return echo_char; }

/**
 * Sets the echo character. This is useful
 * for fields where the user input shouldn't be echoed to the screen,
 * as in the case of a password.
 * @param c the echo character, or 0 if the echo char is not to be set
 */
public void setEchoChar(char c) { 
	echo_char = c;
	repaint();
}

/**
 * Returns a string whose first char is set to the char to be used for echoing,
 * or null if no echo char has been set.
 * @see #setEchoChar
 */
public String getEchoCharString() { 
	if (echo_char == 0) return null;
	char[] tmp = { echo_char };
	return new String(tmp);
}

/**
 * Sets the echo character. 
 * @param s the echo string (only the first character is used), 
 * or null if the echo char is not to be set
 */
public void setEchoCharString(String s) {
	echo_char = (s != null && s.length() > 0) ? s.charAt(0) : 0;
}

/**
 * Returns true if a character has been set for echoing.
 * @see #setEchoChar
 * @see #getEchoChar
 */
public boolean echoCharIsSet() { return echo_char != 0; }

/** Gets the text which will be displayed.
 * @see #setEchoChar
 */
char[] getOutputChars() {
	if (echo_char == 0) return getTextChars();
	char[] a = new char[num_char];
	for (int i=0; i < num_char; i++)
		a[i] = echo_char;
	return a;
}

/** Gets the text which will be displayed.
 * @see #setEchoChar
 */
protected String getOutputText() {
	return new String(getOutputChars(), 0, num_char);
}

/** Gets the x co-ordinate for a character position, ignoring scrolling. */
public int positionToX(int pos) {
	if (!isDisplayable()) return 0;
	getDrawingArea(rect);
	pos = Math.max(0, Math.min(pos, num_char));
	int offset = 0;
	char[] text = getOutputChars();

	switch (alignment) {
	case BWTEnum.RIGHT:
		return rect.x + rect.width - fm.charsWidth(text, pos, num_char-pos);
	case BWTEnum.CENTER:
		offset = (rect.width - fm.charsWidth(text, 0, num_char)) / 2;
	}
	return rect.x + fm.charsWidth(text, 0, pos) + offset;
}

/**
 * Scrolls the text if necessary to ensure that the position is visible.
 * @param pos the number of characters from the beginning of the text buffer.
 */
public void showPosition(int pos) {
	if (!isDisplayable()) return;
	int x = positionToX(pos), old_origin = horiz_origin;
	getDrawingArea(rect);
	if (rect.width == 0 || rect.height == 0) return;

	if (x < horiz_origin + rect.x)
		horiz_origin = x - rect.x - 2;
	else if (x > horiz_origin + rect.x + rect.width)
		horiz_origin = x - (rect.x + rect.width) + 5;
	if (old_origin != horiz_origin)
		repaint();
	cursor_pos = pos;
}

/**
 * Sets the command name of the action event fired by this field.
 * By default this will be a 0-length string.
 */
public void setActionCommand(String command) { actionCommand = command; }

/**
 * Returns the command name of the action event fired by this field.
 * @see #setActionCommand
 */
public String getActionCommand() {
	return (actionCommand == null ? "" : actionCommand);
}

/**
 * Adds the specified action listener to receive action events.
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

final int drawLine(Graphics gc, String string, int x, int y) {
	gc.drawString(string, x, y);
	return fm.stringWidth(string);
}

/**
 * Returns the minimum size needed for the specified number of columns.
 */
public Dimension getMinimumSize(int columns) {
	return (isDisplayable()) ? 
			new Dimension(columns * fm.charWidth('N'), preferredHeight()) :
/* AWT_START 
			super.minimumSize();
 AWT_END */
/* SWING_START */
		    super.getMinimumSize();
/* SWING_END */
}

/**
 * Paints the text.
 */
protected void paintComponent(Graphics gc) {	
	paintComponent(gc, horiz_origin, 
				   getOutputText(), getDrawingArea(), alignment, fm,
				   isEnabled(), getForeground(), getBackground(),
				   selected_fg, selected_bg,
				   select_start, select_end);
	blinkCursor(true);
}

/**
 * Paints the text; this method can be invoked to draw a text area
 * wherever one wants
 */
protected void paintComponent(Graphics gc, int horizontal_offset,
							  String text, Rectangle rect,
							  int alignment, FontMetrics fm, boolean enabled,
							  Color foreground, Color background,
							  Color selected_foreground,
							  Color selected_background,
							  int select_start, int select_end) {
	gc.translate(-horizontal_offset, 0);

	int x = rect.x;
	switch (alignment) {
	case BWTEnum.CENTER:
		x += (rect.width - fm.stringWidth(text)) / 2;
		break;
	case BWTEnum.RIGHT:
		x += rect.width - fm.stringWidth(text);
	}

	if (select_start >= text.length()) {
		select_start = text.length();
	}
	if (select_end >= text.length()) {
		select_end = text.length();
	}


	// y
	int h = fm.getHeight();
	int	line_space = h - fm.getAscent();
	int y = rect.y + h - line_space;

	if (!isEnabled()) {
		Color old_color = gc.getColor();
		gc.setColor(Color.lightGray.darker().darker());
		gc.drawString(text, x, y);
		gc.setColor(old_color);
	}
	else if (select_start != select_end) {
		if (select_start > 0)
			x += drawLine(gc, text.substring(0, select_start), x, y);
		int select_x = positionToX(select_start);
		int select_w = fm.charsWidth(getOutputChars(),
									 select_start, select_end-select_start);
		gc.setColor(selected_background != null
					? selected_background : foreground);
		// The height of the selected region is the minimum of the size
		// of the component and the height of the font
		gc.fillRect(select_x, 0, select_w,
					Math.min(size().height, fm.getHeight() + fm.getDescent() + fm.getAscent()));

		gc.setColor(selected_foreground != null
					? selected_foreground : background);
		x += drawLine(gc, text.substring(select_start, select_end), x, y);

		gc.setColor(getForeground());
		drawLine(gc, text.substring(select_end), x, y);
	}

	else {
		gc.drawString(text, x, y);
	}
	gc.translate(horizontal_offset, 0);
}

/**
 * Converts an x coordinate into a text position. The y value is ignored.
 */
public int pointToPosition(int x, int y) {
	getDrawingArea(rect);
	return pointToPosition(x, y, getOutputChars(), num_char, fm, rect, horiz_origin);
}

/**
 * Converts an x coordinate into a text position. The y value is ignored.
 */
public int pointToPosition(int x, int y, char text[], FontMetrics fm,
						   Rectangle rect, int horizontal_offset) {
	int num_char = text.length;
	return pointToPosition(x, y, text, num_char, fm, rect, horizontal_offset);
}

/**
 * Converts an x coordinate into a text position. The y value is ignored.
 * Use this method iff the array you are passing as "text" is possibly
 * longer than the actual number of characters inside it.
 */
protected int pointToPosition(int x, int y, char text[], int num_char, FontMetrics fm,
						   Rectangle rect, int horizontal_offset) {
	int widths[] = fm.getWidths(), width = size().width;

	switch (alignment) {
	case BWTEnum.CENTER:
		x -= rect.x + (rect.width - (fm.charsWidth(text, 0, num_char))) / 2;
		break;
	case BWTEnum.RIGHT:
		x -= rect.x + rect.width - fm.charsWidth(text, 0, num_char) - horizontal_offset;
		break;
	default:
	    x -= rect.x - horizontal_offset;
	    break;
	}

	/* if overstrike mode is off, clicking on the first half of the character
	 * should set the cursor to the beginning of that character. If the other
     * half of the character was clicked on, the cursor should be set to the
     * next cursor position.
     */
	for (int i = 0; i < num_char; i++) {
	    int w;
		if (text[i] < 256) {
			w = widths[text[i]];
		}
		else {
			w = fm.charWidth(text[i]);
		}
		if (overstrike == true) {
			if (x < w) { 
				return i;
			}
		}
		else {
			if (x < w/2) {
				return i;
			}
		}
	    x -= w;
	}
	return num_char;
}

/** Posts an JCActionEvent.
 * @see #addActionListener
 * @see JCActionEvent
 */
protected void postActionEvent(Event ev) {
	String cmd = getActionCommand();
	if (ev == null)
		ev = new Event(this, 0, cmd);

	JCActionEvent a = new JCActionEvent(this, ev.id, cmd, ev.modifiers);
	for (int i=0; i < actionListeners.size(); i++) 
		((JCActionListener)actionListeners.elementAt(i)).actionPerformed(a);
/* JDK110_START */
	cell_editor_support.fireStopEditing(new CellEditorEvent(a));
/* JDK110_END */
}

/** Processes keyDown events.
<pre>
CTRL/A		scrollHome
CTRL/D		deleteCurrentChar
CTRL/E		scrollEnd
CTRL/O		toggleOverstrike
CTRL/C      copyToClipboard
CTRL/X      cutToClipboard
CTRL/V      pasteFromClipboard
HOME        scrollHome
END         scrollEnd
RIGHT		moveForwardChar
LEFT		moveBackwardChar
BS			deletePreviousChar
DELETE		deleteCurrentChar
ENTER		postActionEvent
any other	insertChar
</pre>
*/
public boolean keyDown(Event ev, int key) {
	if (key == BWTEnum.ENTER) {
		postActionEvent(ev);
		return true;
	}
	return super.keyDown(ev, key);
}

	//
	// define preferredSize for jclass.cell.CellRenderer interface
	//
/* JDK110_START */
public Dimension getPreferredSize(jclass.cell.CellInfo cellInfo, Object o) {
	FontMetrics fm = cellInfo.getFontMetrics();
	String s = (String) o;
	return new Dimension(fm.stringWidth(s), fm.getHeight());
}
/* JDK110_END */

	//
	// Define setCursorPosition used by superclasses
	//

/**
 * Calculates and sets the Cursor Position for the
 * jclass.cell.CellEditor.initialize() method inplemented in the super class
 * @see JCTextComponent#initialize
 */
/* JDK110_START */
public void setCursorPosition(jclass.cell.InitialEvent ev,
								 jclass.cell.CellInfo info, String s){
	// position the cursor if we have a click event	
	char text[] = new char[s.length()];
	s.getChars(0, s.length(), text, 0);
	int pos = pointToPosition(ev.getX(), ev.getY(),
							  text, num_char, info.getFontMetrics(),
							  info.getDrawingArea(), 0);
	setCursorPosition(pos);
}

	//
	// Defines traversal keys for that we use that the cell editor should not
	// override
	//

KeyModifier [] key_modifiers = {
	new KeyModifier(KeyEvent.VK_RIGHT, KeyModifier.ALL),
	new KeyModifier(KeyEvent.VK_LEFT,  KeyModifier.ALL)
};

public KeyModifier[] getReservedKeys() {
	return key_modifiers;
}
/* JDK110_END */

}
