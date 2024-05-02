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
 * Suite 300
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
// RCSID -- $RCSfile: JCTextArea.java $ $Revision: 2.15 $ $Date: 2000/11/09 20:11:14 $ $Locker: $  Sitraka Inc.

package jclass.bwt;
import java.awt.*;
/* JDK110_START */
import java.awt.event.KeyListener;
import java.awt.event.FocusListener;
/* JDK110_END */
import java.applet.*;
import java.io.*;

/**
 * A JCTextArea component is a multi-line area that displays text.<p>
 *
 * <strong>Properties</strong><p>
 * <table border>
 * <tr><th>Name</th>    <th>Method</th></tr><tr><td>
 * Alignment                 </td><td><a href=#setAlignment>setAlignment</a></td></tr><tr><td>
 * Background                </td><td><a href=java.awt.Component.html#setBackground>setBackground</a></td></tr><tr><td>
 * CaretPosition             </td><td><a href=#setCaretPosition>setCaretPosition</a></td></tr><tr><td>
 * Columns                   </td><td><a href=#setColumns>setColumns</a></td></tr><tr><td>
 * CursorPosition            </td><td><a href=#setCursorPosition>setCursorPosition</a></td></tr><tr><td>
 * Editable                  </td><td><a href=#setEditable>setEditable</a></td></tr><tr><td>
 * Font                      </td><td><a href=jclass.bwt.JCContainer.html#setFont>setFont</a></td></tr><tr><td>
 * Foreground                </td><td><a href=java.awt.Component.html#setForeground>setForeground</a></td></tr><tr><td>
 * Insets                    </td><td><a href=jclass.bwt.JCContainer.html#setInsets>setInsets</a></td></tr><tr><td>
 * MaximumLength       </td><td><a href=jclass.bwt.JCTextComponent.html#setMaximumLength>setMaximumLength</a></td></tr><tr><td>
 * Multiline                 </td><td><a href=#setMultiline>setMultiline</a></td></tr><tr><td>
 * Overstrike                </td><td><a href=#setOverstrike>setOverstrike</a></td></tr><tr><td>
 * PreferredSize             </td><td><a href=jclass.bwt.JCContainer.html#setPreferredSize>setPreferredSize</a></td></tr><tr><td>
 * Rows                      </td><td><a href=#setRows>setRows</a></td></tr><tr><td>
 * ScrollbarDisplay          </td><td><a href=jclass.bwt.JCScrolledWindow.html#setScrollbarDisplay>setScrollbarDisplay</a></td></tr><tr><td>
 * ScrollbarOffset           </td><td><a href=jclass.bwt.JCScrolledWindow.html#setScrollbarOffset>setScrollbarOffset</a></td></tr><tr><td>
 * SelectedBackground        </td><td><a href=#setSelectedBackground>setSelectedBackground</a></td></tr><tr><td>
 * SelectedForeground        </td><td><a href=#setSelectedForeground>setSelectedForeground</a></td></tr><tr><td>
 * SelectionEnd              </td><td><a href=#setSelectionEnd>setSelectionEnd</a></td></tr><tr><td>
 * SelectionList             </td><td><a href=#setSelectionList>setSelectionList</a></td></tr><tr><td>
 * SelectionStart            </td><td><a href=#setSelectionStart>setSelectionStart</a></td></tr><tr><td>
 * ShadowThickness  </td><td><a href=#setShadowThickness>setShadowThickness</a></td></tr><tr><td>
 * ShowCursorPosition        </td><td><a href=#setShowCursorPosition>setShowCursorPosition</a></td></tr><tr><td>
 * StringCase          </td><td><a #setStringCase>setStringCase</a></td></tr><tr><td>
 * Text                      </td><td><a href=#setText>setText</a></td></tr><tr><td>
 * TopCharacter              </td><td><a href=#setTopCharacter>setTopCharacter</a></td></tr><tr><td>
 * TopRow                    </td><td><a href=#setTopRow>setTopRow</a></td></tr><tr><td>
 * Traversable         </td><td><a href=#setTraversable>setTraversable</a></td></tr><tr><td>
 * UserData                  </td><td><a href=jclass.bwt.JCContainer.html#setUserData>setUserData</a></td></tr>
 * </table><p>
 *
 * <strong>Events</strong><p>
 * <table border><tr>
 * <th>Class</th> <th>Listener</th>   <th>Description</th></tr><tr><td>
 * JCTextEvent   </td><td><a href=#addTextListener>addTextListener</a></td> <td>Posted when the value changes</td></tr>
 * JCTextCursorEvent  </td><td><a href=#addTextCursorListener>addTextCursorListener</a></td> <td>Posted when the cursor is moved</td></tr><tr><td>
 * </table>
 */
public class JCTextArea extends JCScrolledWindow
implements JCTextAreaInterface {

public static final int LEFT = BWTEnum.LEFT;
public static final int CENTER = BWTEnum.CENTER;
public static final int RIGHT = BWTEnum.RIGHT;

public static final int CASE_AS_IS = BWTEnum.CASE_AS_IS;
public static final int CASE_LOWER = BWTEnum.CASE_LOWER;
public static final int CASE_UPPER = BWTEnum.CASE_UPPER;

JCTextAreaComponent text;

private static final String base = "textarea";
private static int nameCounter = 0;

/**
 * Creates an empty JCTextArea with 5 rows and 20 columns.
 * No parameters are read from an HTML file.
 */
public JCTextArea() {
	this(null, 5, 20);
}

/**
 * Creates a new JCTextArea with the specified text and 5 rows by 20 columns.
 * No parameters are read from an HTML file.
 */
public JCTextArea(String text) {
	this(text, 5, 20);
}

/**
 * Creates a new JCTextArea with the specified text and number of rows and columns.
 * No parameters are read from an HTML file.
 */
public JCTextArea(String text, int rows, int cols) {
	this(text, rows, cols, DISPLAY_ALWAYS);
}

/**
 * Constructs a new TextArea with the specified text and number
 * of rows, columns, and scrollbar visibility.
 * @param sb_display the visibility of scrollbars
 * @see JCScrolledWindow#setScrollbarDisplay
 */
public JCTextArea(String text, int rows, int columns, int sb_display) {
	super(null, base + nameCounter++);
	this.text = new JCTextAreaComponent(text, rows, columns);
	this.sb_display = sb_display;
	viewport = this.text;
	this.text.scrolled_window = this;
}

/** Creates a TextArea which reads parameters from the applet's HTML file.
 * @param applet the applet whose PARAM tags are to be read
 * @param name if this is not null, only parameters preceded by this name are read
 * @see java.applet.Applet#getParameter
 * @see jclass.util.JCString
 */
public JCTextArea(String text, Applet applet, String name) {
	super(applet, name);
	if (name == null)
		setName(base + nameCounter++);
	sb_display = DISPLAY_ALWAYS;
	setTextComponent(new JCTextAreaComponent(text, applet, name));
	if (getClass().getName().equals("jclass.bwt.JCTextArea"))
		 getParameters(applet);
}

/**
 * Creates an editable field with the text from the specified file.
 * @param applet if not null, its documentBase() is used to construct a complete filename if necessary.
 * @param file the file to be read. If an http protocol is not specified
 * (a ":" is not present), the current working directory is prepended to
 * the file name
 * @param rows,cols the number of rows and columns to display
 * @see #readFile
 * @exception IOException If an I/O error has occurred.
 */
public JCTextArea(Applet applet, String file, int rows, int cols)
throws IOException {
	this("", rows, cols);
	readFile(applet, file);
	text.setCursorPosition(0);
}

/** Gets the internal text area. */
public JCTextAreaComponent getTextComponent() { return text; }

/** Replaces the current internal text component. */
public void setTextComponent(JCTextAreaComponent text) {
/* AWT_START 
	// We don't need to do this under SWING since JComponent tracks the current Applet
	text.setApplet(applet);
 AWT_END */
	this.text = text;
	viewport = text;
	text.scrolled_window = this;
	if (isDisplayable())
		validate();
}

/** Specifies that java.awt.SystemColor colors are to be used for new components.
 * If false (default), or if running under JDK 1.0, the parent's colors are used.
 * @see java.awt.SystemColor
 */
public static void useSystemColor(boolean v) {
	JCTextAreaComponent.useSystemColors(v);
}

/**
 * Sets the font of the component.<p>
 * <strong>HTML param name/value:</strong> "Font"/font
 * @see jclass.util.JCConverter#toFont
 */
public synchronized void setFont(Font f) {
	super.setFont(f);
	text.setFont(f);
}

/** Sets the work area width to be the text's virtual width. */
protected int getViewportWidth() {
	return text.getWidestRow() +
		2*(text.getBorderThickness() + text.getHighlightThickness()) +
		text.getInsets().left + text.getInsets().right;
}

/** Sets the work area height to be the text area's virtual height. */
protected int getViewportHeight() {
	int last_row = text.countRows() - 1;
	if (last_row < 0)
		return super.getViewportHeight();
	return text.getRowPosition(last_row) + text.getRowHeight(last_row) +
		2*(text.getBorderThickness() + text.getHighlightThickness()) +
		text.getInsets().top + text.getInsets().bottom;
}

protected void setHorizScrollbarValues(int value, int visible,
									   int min, int max) {
	getHorizScrollbar().setValues(text.getHorizOrigin(), visible, 0, max);
}

protected void setVertScrollbarValues(
							   int value, int visible, int min, int max) {
	getVertScrollbar().setValues(text.getTopRow(), text.getVisibleRows(), 0, text.countRows());
	getVertScrollbar().setUnitIncrement(1);
}

protected void
scrollVertical(JCScrollableInterface scrollable, JCAdjustmentEvent ev, int value) {
	super.scrollVertical(scrollable, ev, value*text.fm.getHeight());
}

/** Scrolls the window vertically to the specified row. */
public void scrollVertical(int row) {
	super.scrollVertical(row/text.fm.getHeight());
}

/**********************
 * JCTextInterface methods
 **********************/

/**
 * Returns the minimum size needed for the specified number of columns.
 */
public Dimension getMinimumSize(int columns) {
	return getMinimumSize(text.rows, columns);
}

/**
 * Returns the minimum size needed for the specified number of rows and columns.
 */
public Dimension getMinimumSize(int rows, int columns) {
	Dimension s = text.getMinimumSize(rows, columns);
	int sb = BWTEnum.SB_SIZE + sb_offset;

	switch (sb_display) {
	case DISPLAY_ALWAYS:
		return new Dimension(s.width + sb, s.height + sb);
	case DISPLAY_VERTICAL_ONLY:
		return new Dimension(s.width + sb, s.height);
	case DISPLAY_HORIZONTAL_ONLY:
		return new Dimension(s.width, s.height + sb);
	}
	return s;
}

/** Returns true if the user has changed the value since the last
 * programmatic setting.
 */
public boolean getChanged() { return text.getChanged(); }

/** Emits a beep. */
public void beep() { text.beep(); }

/**
 * Add key listener to the TextAreaComponent
 * Since the JCTextArea itself never receives focus it is necessary
 * to be able to set it
 * @see removeKeyListener
 */
/* JDK110_START */
public void addKeyListener(KeyListener l) {
	text.addKeyListener(l);
}
/* JDK110_END */

/**
 * Remove focus listener to the TextAreaComponent
 * @see addKeyListener
 */
/* JDK110_START */
public void removeKeyListener(KeyListener l) {
	text.removeKeyListener(l);
}
/* JDK110_END */

/**
 * Add focus listener to the TextAreaComponent child of TextArea
 * Since the JCTextArea itself never receives focus it is necessary
 * to be able to set it
 */
/* JDK110_START */
public void addFocusListener(FocusListener l) {
	if (text != null)
		text.addFocusListener(l);
}
/* JDK110_END */

/**
 * Remove focus listener to the TextAreaComponent child of TextArea
 * @see addFocusListener
 */
/* JDK110_START */
public void removeFocusListener(FocusListener l) {
	if (text != null)
		text.removeFocusListener(l);
}
/* JDK110_END */


/**
 * Adds the specified listener to receive text events.
 * @see JCTextEvent
 * @see JCTextListener
 */
public void addTextListener(JCTextListener l) { text.addTextListener(l); }

/**
 * Removes the specified text listener so it no longer receives text events.
 */
public void removeTextListener(JCTextListener l) { text.removeTextListener(l); }

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

/**
 * Reads data from a file and appends it to the textarea.
 * @param applet if not null, its documentBase() is used to construct a complete filename if necessary.
 * @param file the file to be read. If an http protocol is not specified
 * (a ":" is not present), the current working directory is prepended to
 * the file name
 * @exception IOException If an I/O error has occurred.
 */
public void readFile(Applet applet, String file) throws IOException {
	text.readFile(applet, file);
}

/**
 * Reads data from a file stream and appends it to the textarea.
 * @exception IOException If an I/O error has occurred.
 */
public void readFile(InputStream stream) throws IOException {
	text.readFile(stream);
}

/**
 * Sets the text of this component to the specified text.
 * @see #getText
 */
public void setText(String t) { text.setText(t); }

/**
 * Returns the text contained in this component.
 * @see #setText
 */
public String getText() { return text.getText(); }

/**
 * Returns the selected text contained in this component.
 * @see #setText
 */
public String getSelectedText() { return text.getSelectedText(); }

/** Gets the MaximumLength value.
 * @see #setMaximumLength
 */
public int getMaximumLength() { return text.getMaximumLength(); }

/** Sets the maximum number of characters which may be entered by the user
 * (default: BWTEnum.MAXINT). If the user attempts to enter more characters,
 * the values will be ignored and the bell will be sounded.
 * This value is ignored for values set programmatically.<p>
 * <strong>HTML param name/value:</strong> "MaximumLength"/int
 * @see #setText
 */
public void setMaximumLength(int v) { text.setMaximumLength(v); }

/** Gets the StringCase value.
 * @see #setStringCase
 */
public int getStringCase() { return text.getStringCase(); }

/** Sets the case of text entered by the user or set programmatically:
<pre>
    CASE_AS_IS       No conversion (default)
    CASE_LOWER       Convert to lower case
    CASE_UPPER       Convert to upper case
</pre><p>
 * <strong>HTML param name/value:</strong> "StringCase"/enum
 * @exception IllegalArgumentException If an invalid value is set
 */
public void setStringCase(int v) { text.setStringCase(v); }

/**
 * Returns the boolean indicating whether this component is editable or not.
 * @see #setEditable
 */
public boolean isEditable() { return text.isEditable(); }

/**
 * Returns the boolean indicating whether this component is editable or not.
 * @see #setEditable
 */
public boolean getEditable() { return text.getEditable(); }

/**
 * Determines whether this component is editable.
 * If ShowCursorPosition is set to true and Editable
 * is set to false, the cursor will still appear in non-editable
 * cells.
 * @see #isEditable
 */
public void setEditable(boolean t) { text.setEditable(t); }

/**
 * Returns the selected text's start position.
 */
public int getSelectionStart() { return text.getSelectionStart(); }

/**
 * Returns the selected text's end position.
 */
public int getSelectionEnd() { return text.getSelectionEnd(); }

/**
 * Gets the selected text's start position.
 */
public void setSelectionStart(int pos) { text.setSelectionStart(pos); }

/**
 * Gets the selected text's end position.
 */
public void setSelectionEnd(int pos) { text.setSelectionEnd(pos); }

/**
 * Selects the text found between the specified start and end locations.
 */
public void select(int start, int end) { text.select(start, end); }

/**
 * Selects all the text in the component.
 */
public void selectAll() { text.selectAll(); }

/**
 * Inserts text at the specified position.
 * @see #setText
 * @see #replaceRange
 */
public void insert(String str, int pos) { text.insert(str, pos); }

/**
 * Appends text to the end. The cursor is moved to the end of the text.
 * @see #insert
 */
public void append(String str) { text.append(str); }

/**
 * Appends a row of text. The cursor is moved to the end of the text.
 */
public void appendRow(String str) { text.appendRow(str); }

/**
 * Replaces the existing text between two positions with the specified text.
 * @see #insert
 */
public void replaceRange(String str, int start, int end) {
	text.replaceRange(str, start, end);
}

/** Gets the current SelectionList.
 * @see #setSelectionList
 */
public int[] getSelectionList() { return text.getSelectionList(); }

/** Sets a list of values used for multi-click. As the mouse is clicked in
 * rapid succession, each click selects the next type in the list.
 * Valid values (in order of the default list):
 * <pre>
 * SELECT_POSITION     Selects the current pointer position
 * SELECT_WORD         Selects the current word
 * SELECT_LINE         Selects the current line
 * SELECT_ALL          Selects all the text
 * </pre>
 * Default list: { SELECT_POSITION, SELECT_WORD, SELECT_LINE, SELECT_ALL }
 */
public void setSelectionList(int[] list) { text.setSelectionList(list); }

/** If true (default), the user can break text into multiple lines by
 * hitting Enter.
 */
public void setMultiline(boolean v) { text.setMultiline(v); }

/** Gets the Multiline value.
 * @see #setMultiline
 */
public boolean getMultiline() { return text.getMultiline(); }

/**
 * Returns the number of rows.
 * @see #setRows
 */
public int getRows() { return text.getRows(); }

/**
 * Returns the number of columns.
 * @see #setColumns
 */
public int getColumns() { return text.getColumns(); }

/**
 * Sets the number of rows. The component will attempt to resize itself
 * to display this number of lines in the current font.
 * @exception IllegalArgumentException If the value is less than 0.
 */
public void setRows(int v) { text.setRows(v); }

/**
 * Sets the number of columns. The component will attempt to resize itself
 * to display this number of N's in the current font.
 * @exception IllegalArgumentException If the value is less than 0.
 */
public void setColumns(int v) { text.setColumns(v); }

/**
 * Gets the SelectedBackground value.
 * @see #setSelectedBackground
 */
public Color getSelectedBackground() { return text.getSelectedBackground(); }

/**
 * Sets the background color of selected text (default: blue).<p>
 * <strong>HTML param name/value:</strong> "SelectedBackground"/color
 * @see jclass.util.JCConverter#toColor
 */
public void setSelectedBackground(Color v) { text.setSelectedBackground(v); }

/**
 * Gets the SelectedForeground value.
 * @see #setSelectedForeground
 */
public Color getSelectedForeground() { return text.getSelectedForeground(); }

/**
 * Sets the foreground color of selected text (default: background color).<p>
 * <strong>HTML param name/value:</strong> "SelectedForeground"/color
 * @see jclass.util.JCConverter#toColor
 */
public void setSelectedForeground(Color v) { text.setSelectedForeground(v); }

/**
 * Maps a physical position to the corresponding character position.
 */
public int pointToPosition(int x, int y) { return text.pointToPosition(x, y); }

/**
 * Scrolls the text if necessary to ensure that the position is visible.
 * @param pos the number of characters from the beginning of the text buffer.
 */
public void showPosition(int pos) { text.showPosition(pos); }

/** Gets the current top-most visible row.
 * @see #setTopRow
 */
public int getTopRow() { return text.getTopRow(); }

/** Sets the top-most visible row.
 */
public void setTopRow(int row) { text.setTopRow(row); }

/** Gets the position of the leftmost character on the first line currently
 * displayed, or 0 if the component is empty.
 * @see #setTopCharacter
 */
public int getTopCharacter() { return text.getTopCharacter(); }

/** Sets the position of the leftmost character on the first line currently
 * displayed. This is the number of characters from the beginning of the
 * text buffer. The first character position is 0.
 * @see #setTopRow
 */
public void setTopCharacter(int pos) { text.setTopCharacter(pos); }

/** Gets the position of the cursor.
 * @see #setCursorPosition
 */
public int getCursorPosition() { return text.getCursorPosition(); }

/**
 * Sets the position of the cursor.
 */
public void setCursorPosition(int pos) { text.setCursorPosition(pos); }

/** If false (default), characters typed by the user are inserted into
 * the current text.
 */
public void setOverstrike(boolean v) { text.setOverstrike(v); }

/** Gets the Overstrike value.
 * @see #setOverstrike
 */
public boolean getOverstrike() { return text.getOverstrike(); }

/** Gets the position of the last character in the text.
 * This represents the position that text appended to the component would be
 * placed after.
 */
public int getLastPosition() { return text.getLastPosition(); }

/** Gets the ShowCursorPosition value.
 * @see #setShowCursorPosition
 */
public boolean getShowCursorPosition() { return text.getShowCursorPosition(); }

/**
 * If set to true (default), the cursor position will be indicated with
 * a vertical I-beam. The cursor will still appear in cells with Editable
 * set to false.
 */
public void setShowCursorPosition(boolean v) { text.setShowCursorPosition(v); }

/** Gets the field's alignment.
 * @see #setAlignment
 */
public int getAlignment() { return text.getAlignment(); }

/** Sets the position of the text:<br>
LEFT (default), CENTER, or RIGHT.
 * <strong>HTML param name/value:</strong> "Alignment"/enum
 * @exception IllegalArgumentException If an invalid value is set
 */
public void setAlignment(int v) { text.setAlignment(v); }

/**
 * Gets the position of the cursor (provided for JDK 1.1 compatibility).
 * @see #getCursorPosition
 */
public int getCaretPosition() { return text.getCursorPosition(); }

/**
 * Sets the position of the cursor (provided for JDK 1.1 compatibility).
 * @see #setCursorPosition
 */
public void setCaretPosition(int pos) { text.setCursorPosition(pos); }

public boolean gotFocus(Event ev, Object what) {
	text.requestFocus();
	return super.gotFocus(ev, what);
}

/** Gets the shadow's thickness.<p>
 * <strong>HTML param name/value:</strong> "ShadowThickness"/int
 * @see #setShadowThickness
 */
public int getShadowThickness() { return text.getBorderThickness(); }

/** Sets the shadow's thickness (default: 2) */
public void setShadowThickness(int v) { text.setBorderThickness(v); }

/** Gets the value of the Traversable property.
 * @see setTraversable
 */
public boolean isTraversable() { return text.isTraversable(); }

/** Sets whether the component can accept focus (default: true).<p>
 * <strong>HTML param name/value:</strong> "Traversable"/boolean
 * @see jclass.util.JCConverter#toBoolean
 */
public void setTraversable(boolean v) { text.setTraversable(v); }


/** writeObject and readObject deal with the problem when a TextArea
 * is deserialized but the scrolled_window of the JCTextComponent is
 * null.  This causes display problems if the component can scroll.
 * These methods restore text.scrolled_window on deserialization.
 */

/* JDK110_START */
private void readObject(java.io.ObjectInputStream in) throws
java.io.IOException {
    try {
        in.defaultReadObject();
    }
    catch (Exception e) {
        e.printStackTrace(System.out);
    }
}
/* JDK110_END */

/* JDK110_START */
private void writeObject(java.io.ObjectOutputStream out) throws
java.io.IOException {
    try {
        out.defaultWriteObject();
    }
    catch (Exception e) {
        e.printStackTrace(System.out);
    }
}
/* JDK110_END */

}
