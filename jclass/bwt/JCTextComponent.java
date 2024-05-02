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
// RCSID -- $RCSfile: JCTextComponent.java $ $Revision: 2.86 $ $Date: 2000/11/09 20:11:17 $ $Locker: $  Sitraka Inc.

package jclass.bwt;
import jclass.util.JCEnvironment;
import jclass.util.JCVector;
import jclass.util.JClassInfo;
import java.awt.*;
import java.applet.Applet;

/* JDK110_START */
import java.io.Serializable;
import java.io.InputStream;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.*;
import java.util.Locale;
import java.util.ResourceBundle;
import jclass.bwt.resources.*;
import jclass.cell.*;
/* JDK110_END */

/* SWING11_START
import com.sun.java.swing.JPopupMenu;
import com.sun.java.swing.JMenuItem;
import com.sun.java.swing.FocusManager;
 SWING11_END */
/* SWING12_START */
import javax.swing.JPopupMenu;
import javax.swing.JMenuItem;
import javax.swing.FocusManager;
/* SWING12_END */

/** An abstract class used as the superclass for JCTextArea and JCTextField.
 * <p>
 * <strong>Properties</strong><p>
 * <table border>
 * <tr><th>Name</th>    <th>Method</th></tr><tr><td>
 * Alignment           </td><td><a href=#setAlignment>setAlignment</a></td></tr><tr><td>
 * Background          </td><td><a href=jclass.bwt.JCComponent.html#setBackground>setBackground</a></td></tr><tr><td>
 * CaretPosition       </td><td><a href=#setCaretPosition>setCaretPosition</a></td></tr><tr><td>
 * Changed             </td><td><a href=#setChanged>setChanged</a></td></tr><tr><td>
 * Columns             </td><td><a href=#setColumns>setColumns</a></td></tr><tr><td>
 * CursorPosition      </td><td><a href=#setCursorPosition>setCursorPosition</a></td></tr><tr><td>
 * DoubleBuffer        </td><td><a href=jclass.bwt.JCComponent.html#setDoubleBuffer>setDoubleBuffer</a></td></tr><tr><td>
 * Editable            </td><td><a href=#setEditable>setEditable</a></td></tr><tr><td>
 * Font                </td><td><a href=jclass.bwt.JCComponent.html#setFont>setFont</a></td></tr><tr><td>
 * Foreground          </td><td><a href=jclass.bwt.JCComponent.html#setForeground>setForeground</a></td></tr><tr><td>
 * HighlightColor      </td><td><a href=jclass.bwt.JCComponent.html#setHighlightColor>setHighlightColor</a></td></tr><tr><td>
 * HighlightThickness  </td><td><a href=jclass.bwt.JCComponent.html#setHighlightThickness>setHighlightThickness</a></td></tr><tr><td>
 * Insets              </td><td><a href=jclass.bwt.JCComponent.html#setInsets>setInsets</a></td></tr><tr><td>
 * MaximumLength       </td><td><a href=#setMaximumLength>setMaximumLength</a></td></tr><tr><td>
 * Overstrike          </td><td><a href=#setOverstrike>setOverstrike</a></td></tr><tr><td>
 * PreferredSize       </td><td><a href=jclass.bwt.JCComponent.html#setPreferredSize>setPreferredSize</a></td></tr><tr><td>
 * SelectedBackground  </td><td><a href=#setSelectedBackground>setSelectedBackground</a></td></tr><tr><td>
 * SelectedForeground  </td><td><a href=#setSelectedForeground>setSelectedForeground</a></td></tr><tr><td>
 * SelectionEnd        </td><td><a href=#setSelectionEnd>setSelectionEnd</a></td></tr><tr><td>
 * SelectionList       </td><td><a href=#setSelectionList>setSelectionList</a></td></tr><tr><td>
 * SelectionStart      </td><td><a href=#setSelectionStart>setSelectionStart</a></td></tr><tr><td>
 * ShadowThickness  </td><td><a href=jclass.bwt.JCComponent.html#setShadowThickness>setShadowThickness</a></td></tr><tr><td>
 * ShowCursorPosition  </td><td><a href=#setShowCursorPosition>setShowCursorPosition</a></td></tr><tr><td>
 * StringCase          </td><td><a href=#setStringCase>setStringCase</a></td></tr><tr><td>
 * Text                </td><td><a href=#setText>setText</a></td></tr><tr><td>
 * Traversable         </td><td><a href=jclass.bwt.JCComponent.html#setTraversable>setTraversable</a></td></tr><tr><td>
 * UserData            </td><td><a href=jclass.bwt.JCComponent.html#setUserData>setUserData</a></td></tr>
 * </table><p>
 *
 * <strong>Events</strong><p>
 * <table border><tr>
 * <th>Class</th> <th>Listener</th>   <th>Description</th></tr><tr><td>
 * JCTextEvent   </td><td><a href=#addTextListener>addTextListener</a></td> <td>Posted when the value changes</td></tr>
 * JCTextCursorEvent  </td><td><a href=#addTextCursorListener>addTextCursorListener</a></td> <td>Posted when the cursor is moved</td></tr><tr><td>
 * </table>
 * @see JCTextArea
 * @see JCTextField
 */
public abstract class JCTextComponent extends JCComponent
    implements JCTextInterface, Runnable
/* JDK110_START */
                , CellEditor, CellRenderer
/* JDK110_END */
{

private static final boolean TRACE = false;

public static final int LEFT = BWTEnum.LEFT;
public static final int CENTER = BWTEnum.CENTER;
public static final int RIGHT = BWTEnum.RIGHT;

public static final int CASE_AS_IS = BWTEnum.CASE_AS_IS;
public static final int CASE_LOWER = BWTEnum.CASE_LOWER;
public static final int CASE_UPPER = BWTEnum.CASE_UPPER;

public static final int SELECT_NONE = 0;
public static final int SELECT_CHAR = BWTEnum.SELECT_CHAR;
public static final int SELECT_WORD = BWTEnum.SELECT_WORD;
public static final int SELECT_LINE = BWTEnum.SELECT_LINE;
public static final int SELECT_PARAGRAPH = BWTEnum.SELECT_PARAGRAPH;
public static final int SELECT_ALL = BWTEnum.SELECT_ALL;

/* JDK110_START */
protected ResourceBundle li = null; // Our LocaleInfo bundle
/* JDK110_END */

/* AWT110_START 
protected PopupMenu popup_menu = null;
 AWT110_END */
/* SWING_START */
protected JPopupMenu popup_menu = null;
protected boolean is_cell_editor = false;
/* SWING_END */

// Cursor blinking
transient Thread cursor_thread;
int cursor_pos;						 // cursor position
boolean cursor_state = false;
boolean cursor_visible = true;
boolean paint_cursor = true;
boolean	display_cursor = true;	     // Paint cursor?
boolean	show_cursor = true;  		 // need to show the cursor position?
int disabled_repaint_count = 0;
int action_count = 0;				// startAction - endAction pairs
boolean needs_repaint = false;
boolean do_repaint = true;
Window window;						// parent window
static boolean dialog_bug = false;

int num_char;		// number of characters
int max_length = BWTEnum.MAXINT;
int columns = 20;
char text[] = new char[4];

int alignment = LEFT;
transient boolean dispose = false;

// Selections
int select_start, select_end;	// selection end points
int select_anchor;				// mouseDown position
int selection_type;				// current selection type

/** Amount by which the text has been scrolled. */
protected int horiz_origin, vert_origin;

/** List of JCTextCursorEvent listeners. */
protected JCVector cursorListeners = new JCVector(0);

/** List of TextEvent/JCTextEvent listeners. */
protected JCVector valueListeners = new JCVector(0);

/** List of CellEditorListeners */
/* JDK110_START */
protected CellEditorSupport cell_editor_support = new CellEditorSupport();
/* JDK110_END */

boolean editable = true;
boolean overstrike = false;
boolean changed = false;
int string_case = CASE_AS_IS;
Color selected_fg, selected_bg;
transient FontMetrics fm;
transient Event last_event;

/** Cursor's blink rate (ms) */
protected int cursor_blink_rate = 500;

protected boolean ignore_lose_focus = false;
protected boolean ignore_select_extensions = false;

Rectangle rect = new Rectangle();

// Array to cycle for selections
int[] select_list = {
	SELECT_CHAR, SELECT_WORD, SELECT_LINE, SELECT_ALL
};

// Clipboard used in JDK 102 and in JDK 1.1 when we are not permitted access
// to the system clipboard
static String clipboard;

/** Creates an empty component. */
public JCTextComponent() {
	this(null, null);
}

/** Creates a component which reads parameters from the applet's HTML file.
 * @param applet the applet whose PARAM tags are to be read
 * @param name if this is not null, only parameters preceded by this name are read
 * @see java.applet.Applet#getParameter
 */
public JCTextComponent(Applet applet, String name) {
	super(applet, name);

	if (getClass().getName().equals("jclass.bwt.JCTextComponent"))
		 getParameters(applet);
	border_style = jclass.base.Border.CONTROL_IN;
/* JDK110_START */
	if (use_system_colors) {
		setBackground(SystemColor.text);
		setForeground(SystemColor.textText);
		selected_bg = SystemColor.textHighlight;
		selected_fg = SystemColor.textHighlightText;
	}
	if (TRACE) {
		System.out.println("Locale = " + Locale.getDefault());
	}
	li = ResourceBundle.getBundle("jclass.bwt.resources.LocaleInfo",
								  Locale.getDefault());

/* JDK110_END */
}

/** Reads the parameter values from the HTML page using the component's applet.
 * The values will override those previously set.
 */
protected void getParameters() {
	super.getParameters();
	TextComponentConverter.getParams(this);
}

public void setFont(Font f) {
	if (isDisplayable())
		fm = getToolkit().getFontMetrics(f);
	super.setFont(f);
	repaint();
}

/** Gets the field's alignment.
 * @see #setAlignment
 */
public int getAlignment() { return alignment; }

/** Sets the position of the text:<p>
 * LEFT (default), CENTER, or RIGHT.
 * <strong>HTML param name/value:</strong> "Alignment"/enum
 * @exception IllegalArgumentException If an invalid value is set
 */
public void setAlignment(int v) {
	LabelConverter.checkAlignment(v);
	alignment = v;
	repaint();
}

/** Returns true if the user has changed the value since the last
 * programmatic setting.
 */
public boolean getChanged() { return changed; }

/** Resets the Changed flag.
 * @see #getChanged
 */
public void setChanged(boolean v) { changed = v; }

/** Gets the position of the last character in the text.
 * This represents the position that text appended to the component would be
 * placed after.
 */
public int getLastPosition() { return num_char; }

/**
 * Returns the start of the selection.
 * @see #setSelectionStart
 */
public int getSelectionStart() { return select_start; }

/**
 * Sets the selection start to the specified position.  The new
 * starting point is constrained to be before or at the current
 * selection end.
 */
public synchronized void setSelectionStart(int v) {
	select(v, getSelectionEnd());
}

/**
 * isPositionSelected - is the specified position within the selected range
 */
public boolean isPositionSelected(int pos) {
	if (pos >= select_start && pos < select_end) {
		return(true);
	}
	return(false);
}

/**
 * Returns the end of the selection.
 * @see #setSelectionEnd
 */
public int getSelectionEnd() { return select_end; }

/**
 * Sets the selection end to the specified position.  The new
 * end point is constrained to be at or after the current
 * selection start.
 */
public synchronized void setSelectionEnd(int v) {
	select(getSelectionStart(), v);
}

/**
 * Gets the value of the text.
 * @see #setText
 */
public synchronized String getText() { return new String(text, 0, num_char); }

/**
 * Returns the internal text characters.
 */
char[] getTextChars() { return text; }

/**
 * Sets the text of this component to the specified text.<p>
 * <strong>HTML param name/value:</strong> "Text"/string
 * @see #getText
 */
public void setText(String text) {
	if (text == null)
	    text = "";
	synchronized (this) {
		horiz_origin = vert_origin = 0;
		replaceRange(text, 0, num_char);
		changed = false;
	}
}

/** Gets the current number of characters. */
public int getNumChar() { return num_char; }

/** Gets the MaximumLength value.
 * @see #setMaximumLength
 */
public int getMaximumLength() { return max_length; }

/** Sets the maximum number of characters which may be entered by the user
 * (default: BWTEnum.MAXINT). If the user attempts to enter more characters,
 * the values will be ignored and the bell will be sounded.
 * This value is ignored for values set programmatically.<p>
 * <strong>HTML param name/value:</strong> "MaximumLength"/int
 * @see #setText
 */
public void setMaximumLength(int v) { max_length = v; }

/**
 * Deletes the current selection.
 * @return false if the replacement was disallowed
 * @see #addTextListener
 */
protected boolean deleteSelection() {
	return deleteRange(select_start, select_end);
}

/**
 * Deletes the text between the specified positions.
 * @return false if the replacement was disallowed
 * @see #addTextListener
 */
protected boolean deleteRange(int start, int end) {
	if (start >= end) return true;
	String s = "";
	boolean ok = replaceRangeInternal(s, start, end);
	if (ok == true) {
		// make sure that a paste count as modifying the text
		changed = true;
	}
	return ok;
}

/**
 * Cancels the current selection.
 */
protected void cancelSelection() {
	if (select_start >= select_end) return;
	int old_start = select_start, old_end = select_end;
	select_start = select_end = cursor_pos;
	repaintPositions(old_start, old_end);
}

/**
 * Inserts text at the specified position.
 * @see #setText
 * @see #replaceRange
 */
public synchronized void insert(String text, int pos) {
	replaceRange(text, pos, pos);
}

/**
 * Appends text to the end. The cursor is moved to the end of the text.
 * @see #insert
 */
public void append(String text) {
	// select causes scrollbar to repaint and acquires AWT Tree lock.
	// Call it outside the monitor to avoid deadlock.
	select(num_char);
	replaceRange(text, num_char, num_char);
}

/** Gets the StringCase value.
 * @see #setStringCase
 */
public int getStringCase() { return string_case; }

/** Sets the case of text entered by the user or set programmatically:
<pre>
    CASE_AS_IS       No conversion (default)
    CASE_LOWER       Convert to lower case
    CASE_UPPER       Convert to upper case
</pre><p>
 * <strong>HTML param name/value:</strong> "StringCase"/enum
 * @exception IllegalArgumentException If an invalid value is set
 */
public void setStringCase(int v) {
	TextComponentConverter.checkStringCase(v);
	if (v != string_case) {
		string_case = v;
		if (v != CASE_AS_IS)
			replaceRange(getText(), 0, num_char);
	}
}

/**
 * Replaces text from the indicated start to end positions (inclusive) with the
 * new text specified. The text is first converted according to the StringCase
 * value. The JCTextCursorEvent and JCTextEvent events are then posted.
 * The cursor is moved to the end position.
 * @param text if null, the current text will be deleted
 * @see #insert
 * @see #setStringCase
 * @see #addTextCursorListener
 * @see #addTextListener
 */
public void replaceRange(String str, int start, int end) {
	replaceRangeInternal(str, start, end);
}

boolean replaceRangeInternal(String str, int start, int end) {
	startAction(null);
	if (str == null) str = "";
	if (string_case == CASE_LOWER)
		str = str.toLowerCase();
	else if (string_case == CASE_UPPER)
		str = str.toUpperCase();
	boolean s = replace(str, start, end);
	endAction();
	return s;
}

/**
 * Updates the internal text buffer.  If start == end then we are just
 * inserting the new text
 * @param str text to add
 * @param start start position of text to replace
 * @param end end position of text to replace
 */
protected void replaceTextInternal(String str, int start, int end) {
	// We declare do_repaint locally so that we can safely retain the intended
	// value of the object variable after we return from the following
	// synchronised block
	boolean do_repaint = false;

	synchronized (this) {
		if (end - start > 0) {
			//
			// Delete text delimited by start and end parameters
			//
			System.arraycopy(text, end, text, start, num_char - end);
			num_char -= (end - start);
		}

		int strlen = str != null ? str.length() : 0;
		if (strlen > 0) {
			//
			// Insert the text specified by "str"
			//
			if (num_char + strlen + start >= text.length) {
				// Expand text array to contain new text
				char newtext[] = new char[Math.max(num_char * 2,
										  num_char + strlen + start + 1)];
				System.arraycopy(text, 0, newtext, 0, num_char);
				text = newtext;
			}
			if (num_char > start) {
				// Make room for the text we want to insert by moving
				// as existing character that are past the insertion point
				// by an amount equal to the length of the text being
				// inserted
				System.arraycopy(text, start, text,
								 start + strlen, num_char - start);
			}
			// Copy the new string into the text array
			str.getChars(0, strlen, text, start);

			// Collapse the selected region to a point after the newly
			// inserted text
			start += strlen;
			select_start = select_end = start;
			num_char += strlen;

			// Store do_repaint and use it outside the synchronized block.
			do_repaint = this.do_repaint;
		}
	}

	if (do_repaint) {
		// generally this code block is not executed by subclasses that
		// override this method since they set "this.do_repaint" to false
		// before invoking their super
		if (isDisplayable()) {
			Rectangle draw_rect = new Rectangle(positionToPoint(start, null));
			draw_rect.add(positionToPoint(end, null));
			if (alignment != LEFT)
				draw_rect.x = getDrawingArea().x;
			paintImmediately(draw_rect.x - horiz_origin,
							 draw_rect.y - vert_origin,
							 size().width - (draw_rect.x - horiz_origin),
							 draw_rect.height + fm.getHeight());
		}
	}

}

protected void updateScrollbars() {}

/**
 * Deletes the text from start to end and
 * then inserts, at start, the text that was passed.
 * The JCTextEvent events are then posted.
 * @return false if the replacement was disallowed
 * @see #addTextListener
 */
protected boolean replace(String text, int start, int end) {

	int strlen = text != null ? text.length() : 0;

	// Check that an interactive insertion will not be too long
	if (last_event != null && max_length < BWTEnum.MAXINT) {
		if (num_char + strlen - (end - start) > max_length) {
			beep();
			return false;
		}
	}

	JCTextEvent ev = null;
	Rectangle draw_rect = null;

	if (valueListeners.size() > 0) {
		//
		// Call text chanaged listeners with begin event
		//
		ev = new JCTextEvent(this, start, end, text);
		if (last_event != null
			&& (last_event.key == BWTEnum.BS
				|| last_event.key == BWTEnum.DELETE)) {
			ev.is_deletion = true;
		}
		else {
			// in overstrike mode, entering text is not considered a deletion
			// even though it is really deleting a character and
			// then inserting a new character. Therefore, we can't just
			// check if end is greater than start.
			ev.is_deletion = end-start > 0 && strlen < end-start;
		}

		for (int i=0; i < valueListeners.size(); i++) {
			((JCTextListener)valueListeners.elementAt(i)).textValueChangeBegin(ev);
		}
		if (!ev.doit) {
			// cancel change if one of the invoked listener has requested it
			return false;
		}

		// reset the params as per listener specification
		start = ev.start;
		end = ev.end;
		text = ev.text;
	}

	if (last_event == null) {
		changed = false;
	}

	if (text == null) {
		// set text to an empty string to make sure we don't get
		// a null pointer exception
		text = "";
	}

	if (isDisplayable()) {
		draw_rect = new Rectangle(positionToPoint(start, null));
		draw_rect.add(positionToPoint(end, null));
		if (alignment != LEFT) {
			draw_rect.x = getDrawingArea().x;
		}
	}

	replaceTextInternal(text, start, end);

	setCursorPosition(start + strlen);

	// Clear current selection
	select_start = select_end = cursor_pos;
	if (isDisplayable()) {
		draw_rect.add(positionToPoint(cursor_pos, null));
		paintImmediately(draw_rect.x - horiz_origin,
						 draw_rect.y - vert_origin,
						 size().width - (draw_rect.x - horiz_origin),
						 draw_rect.height + fm.getHeight());
	}

	//
	// post changed event
	//
	if (ev != null) {
		for (int i=0; i < valueListeners.size(); i++) {
			((JCTextListener)valueListeners.elementAt(i)).textValueChangeEnd(ev);
		}
	}
	return true;
}

/** Resets the position to the range [0,num_char] */
protected int checkCursorPosition(int pos) {
	return (pos < 0 ? 0 : (pos > num_char ? num_char : pos));
}

/** Gets the position of the cursor.
 * @see #setCursorPosition
 */
public int getCursorPosition() { return cursor_pos; }

/**
 * Sets the position of the cursor, and posts a JCTextCursorEvent event.<p>
 * <strong>HTML param name/value:</strong> "CursorPosition"/int
 * @exception IllegalArgumentException If the value is less than 0.
 * @see #addTextCursorListener
 */
public void setCursorPosition(int pos) {
	if (TRACE) {
		System.out.println("setCursorPosition(" + pos + ")");
	}
	if (pos < 0)
	    throw new IllegalArgumentException("cursor position less than zero.");

	JCTextCursorEvent ev = null;

	// Called programmatically
	if (last_event == null)
		startAction(null);

	if (cursor_pos == pos) {
		endAction();
		return;
	}

	synchronized (this) {
		pos = checkCursorPosition(pos);

		if (cursorListeners.size() > 0) {
			ev = new JCTextCursorEvent(this, cursor_pos, pos);
			for (int i=0; i < cursorListeners.size(); i++)
				((JCTextCursorListener)cursorListeners.elementAt(i)).textCursorMoveBegin(ev);
			if (!ev.doit)
				return;
			pos = checkCursorPosition(ev.new_pos);
		}
		if (last_x >= 0 && last_y >= 0) {
			paintImmediately(0, 0,size().width, size().height);
		}
		cursor_pos = pos;
	}

	// If called by application, set selection point to cursor position
	if (last_event == null) {
		// select can't be called within a monitor.  Otherwise,
		// deadlock occurs because endAction() acquires AWT Tree lock.
		select(cursor_pos);
	}

	endAction();
	if (ev != null) {
		for (int i=0; i < cursorListeners.size(); i++)
			((JCTextCursorListener)cursorListeners.elementAt(i)).textCursorMoveEnd(ev);
	}
}

/**
 * Gets the position of the cursor (provided for JDK 1.1 compatibility).
 * @see #getCursorPosition
 */
public int getCaretPosition() { return getCursorPosition(); }

/**
 * Sets the position of the cursor (provided for JDK 1.1 compatibility).
 * @see #setCursorPosition
 */
public void setCaretPosition(int pos) { setCursorPosition(pos); }

/** Gets the ShowCursorPosition value.
 * @see #setShowCursorPosition
 */
public boolean getShowCursorPosition() { return display_cursor; }

/**
 * If set to true (default), the cursor position will be indicated with
 * a vertical I-beam.<p>
 * <strong>HTML param name/value:</strong> "ShowCursorPosition"/boolean
 */
public void
setShowCursorPosition(boolean v) {
	display_cursor = v;
	if (show_cursor)
		showPosition(cursor_pos);
}

/** If false (default), characters typed by the user are inserted into
 * the current text.<p>
 * <strong>HTML param name/value:</strong> "Overstrike"/boolean
 */
public void setOverstrike(boolean v) { overstrike = v; }

/** Gets the Overstrike value.
 * @see #setOverstrike
 */
public boolean getOverstrike() { return overstrike; }

/**
 * Get the selected part of the text.
 * @see #select
 */
public synchronized String getSelectedText() {
	return new String(text, select_start, select_end - select_start);
}

/**
 * Selects all the text, and sets the cursor at the last character.
 */
public void selectAll() {
	if (TRACE) {
		System.out.println("selectAll");
	}
	select(0, num_char);
}

/**
 * Sets the current selection, and sets the cursor at the end of the selection.
 * If start == end, then the selection is cancelled.
 */
public void select(int start, int end) {
	synchronized (this) {
		select(start, end, 0);
	}
	// setCursorPosition changes selection if last_event == null we fake out
	// a last_event
	last_event = new Event(null, 0, null);
	// setCursorPosition requires AWT Tree Lock.  Call it outside the monitor to avoid deadlock.
	setCursorPosition(start);
}

void select(int pos) { select(pos, pos); }

/**
 * Determines whether a character is a word delimiter.
 * @return false if char is a letter, number, '$' or '_'
 */
protected boolean isWordDelimiter(char ch) {
	// return !Character.isJavaIdentifierPart(ch);	//	JDK 1.1
	return !Character.isJavaLetterOrDigit(ch);
}

/**
 * Determines whether a character is a line delimiter.
 * @return true if char is a newline or return
 */
protected boolean isLineDelimiter(char ch) {
	return (ch == '\n') || (ch == '\r');
}

/**
 * Selects part of the text.
 * @param type SELECT_CHAR, SELECT_WORD, SELECT_LINE, or SELECT_ALL
 */
protected void select(int start, int end, int type) {
	if (TRACE) {
		BWTUtil.trace();
		System.out.println("select(" + start + ", " + end + ", " + type + ")");
	}
	if (start > end) {
		int t = start;
	    start = end;
	    end = t;
	}
	start = Math.max(0, Math.min(start, num_char));
	end = Math.max(0, Math.min(end, num_char));
	int old_start = start, old_end = end;

	switch (type) {
	case 0:
	case SELECT_CHAR:
		break;
	case SELECT_WORD:
		for (; start > 0 && !isWordDelimiter(text[start-1]); start--) ;
		for (; end < num_char && !isWordDelimiter(text[end]); end++) ;

		// If there is only 1 word on line and it is already selected, select all
		if (start == old_start && end == old_end
				&& getSelectionType(type+1) == SELECT_LINE) {
			start = 0;
			end = num_char;
		}
		break;
	case SELECT_LINE:
		for (; start > 0 && !isLineDelimiter(text[start-1]); start--) ;
		for (; end < num_char && !isLineDelimiter(text[end]); end++) ;
		break;
	case SELECT_ALL:
	    start = 0;
	    end = num_char;
	}

	if (start == select_start && end == select_end) {
		return;
	}

	old_start = select_start;
	old_end = select_end;
	select_start = start;
	select_end = end;

	// No overlap
	if (end < old_start || start > old_end) {
	    repaintPositions(old_start, old_end);
	    repaintPositions(select_start, select_end);
	}
	else {
		repaintPositions(old_start, start);
		repaintPositions(old_end, end);
	}
}

/** Gets the x co-ordinate for a character position, ignoring scrolling. */
public abstract int positionToX(int pos);

/** Gets the y co-ordinate for a character position, ignoring scrolling. */
public int positionToY(int pos) { return getDrawingArea().y; }

/**
 * Adds the specified listener to receive text events.
 * @see JCTextEvent
 */
public void addTextListener(JCTextListener l) {
	valueListeners.addUnique(l);
}

/**
 * Removes the specified text listener so it no longer receives text events.
 */
public void removeTextListener(JCTextListener l) {
	valueListeners.removeElement(l);
}

/**
 * Adds the specified JCTextCursorEvent listener to receive cursor movement events.
 * @see JCTextCursorEvent
 */
public void addTextCursorListener(JCTextCursorListener l) {
	cursorListeners.addUnique(l);
}

/**
 * Removes the specified listener so it no longer receives JCTextCursorEvents.
 * @see #addTextCursorListener
 */
public void removeTextCursorListener(JCTextCursorListener l) {
	cursorListeners.removeElement(l);
}

boolean last_state = false;
boolean last_overstrike = false;
int last_x = BWTEnum.NOVALUE, last_y;
int last_cursor_width;

/** Draws an I-beam. */
void paintCursor(boolean state, Graphics gc, int x, int y) {
	int w, h = fm.getHeight();
	y += h-1;

	// Dialog and Borland JBuilder bug - Graphics.setXORMode() doesn't work
	if (!state && (JCEnvironment.isJBuilder() || dialog_bug)) {
		repaint(new Rectangle(x, y-h+1, 1, h));
	}
	else if (dialog_bug) {
		gc.setPaintMode();
		gc.setColor(getForeground());
		gc.drawLine(x, y-h+1, x, y);
	}
	else {
		gc.drawLine(x, y-h+1, x, y);
	}
}

/** Paints or erases the cursor, depending on the value of state. */
protected void paintCursor(boolean state) {
	if (!isShowing() || !paint_cursor || !has_focus) return;
	getDrawingArea(rect);
	int x = last_x, y = last_y;

	// Note: SystemColor has the alpha bit set to 00, which causes a bug in setXORMode.
	//       Fix it by changing the alpha bit to FF.
	// check for background alpah bit
	if (getBackground().getRGB() > 0) {
		Color b = new Color(getBackground().getRed(),
							getBackground().getGreen(),
							getBackground().getBlue());
		setBackground(b);
	}
	// check for foreground alpha bit
	if (getForeground().getRGB() > 0) {
		Color f = new Color(getForeground().getRed(),
							getForeground().getGreen(),
							getForeground().getBlue());
		setForeground(f);
	}

	if (isShowing() && display_cursor && state != last_state) {
		Graphics gc;
		try {
			gc = getGraphics();
		} catch (Exception e) { return; }
		if (gc == null) return;
/* SWING_START */
		clipGCToAncestors(gc);
/* SWING_END */

		synchronized (LOCK) {
			if (state) {
				x = positionToX(cursor_pos);
				y = positionToY(cursor_pos);
			}

		int sh = border + highlight;
		gc.clipRect(sh, sh, size().width-2*sh, size().height-2*sh);
		gc.translate(-horiz_origin, -vert_origin);
		gc.setXORMode(getBackground());
		if (!state && last_overstrike != overstrike) {
			boolean o = overstrike;
			overstrike = last_overstrike;
			paintCursor(state, gc, x, y);
			overstrike = o;
		}
		else {
			paintCursor(state, gc, x, y);
		}
		gc.dispose();
		}
	}
	last_state = state;
	if (state) {
		last_x = x; last_y = y;
	}
	last_overstrike = overstrike;
}

/** Blinks the cursor. */
public void run() {
	paintCursor(true);
	while (true) {
		try {
			Thread.sleep(cursor_blink_rate);
		} catch (Throwable e) { return; }

		if (!Thread.currentThread().isAlive() || cursor_thread == null)
			return;
		cursor_visible = !cursor_visible;
		paintCursor(cursor_visible);
	}
}

int leftMargin() { return getDrawingArea().x; }
int rightMargin() { return insets.right + 2*(highlight+border); }

/** If set to true (default), the user can edit the text.<p>
 * <strong>HTML param name/value:</strong> "Editable"/boolean
 */
public void setEditable(boolean v) {
	if (editable == v) return;

	synchronized (this) {
		editable = v;
		if (editable) traversable = true;
	}
	repaint();
}

/**
 * Gets the SelectedBackground value.
 * @see #setSelectedBackground
 */
public Color getSelectedBackground() {
	return selected_bg != null ? selected_bg : getForeground();
}

/**
 * Sets the background color of selected text (default: blue).<p>
 * <strong>HTML param name/value:</strong> "SelectedBackground"/color
 * @see jclass.util.JCConverter#toColor
 */
public void setSelectedBackground(Color v) { selected_bg = v; }

/**
 * Gets the SelectedForeground value.
 * @see #setSelectedForeground
 */
public Color getSelectedForeground() {
	return selected_fg != null ? selected_fg : getBackground();
}

/**
 * Sets the foreground color of selected text (default: background color).<p>
 * <strong>HTML param name/value:</strong> "SelectedForeground"/color
 * @see jclass.util.JCConverter#toColor
 */
public void setSelectedForeground(Color v) { selected_fg = v; }

/** Gets the number of columns.
 * @see #setColumns
 */
public int getColumns() { return columns; }

/** Sets the number of columns.<p>
 * <strong>HTML param name/value:</strong> "Columns"/int
 * @exception IllegalArgumentException If the value is less than 0.
 */
public void setColumns(int v) {
	if (v < 0)
	    throw new IllegalArgumentException("columns less than zero.");
	columns = v;
}

/** Gets the current SelectionList.
 * @see #setSelectionList
 */
public int[] getSelectionList() { return select_list; }

/** Sets a list of values used for multi-click. As the mouse is clicked in
 * rapid succession, each click selects the next type in the list.
 * Valid values (in order of the default list):
 * <pre>
 * SELECT_CHAR         Selects the current pointer position
 * SELECT_WORD         Selects the current word
 * SELECT_LINE         Selects the current line
 * SELECT_ALL          Selects all the text
 * </pre>
 * Default list: { SELECT_CHAR, SELECT_WORD, SELECT_LINE, SELECT_ALL }
 */
public void setSelectionList(int[] list) { select_list = list; }

/** Gets the Editable value.
 * @see #setEditable
 */
public boolean getEditable() { return editable; }

/** Gets the Editable value.
 * @see #setEditable
 */
public boolean isEditable() { return editable; }

/**
 * Scrolls the text if necessary to ensure that the position is visible.
 * @param pos the number of characters from the beginning of the text buffer.
 */
public abstract void showPosition(int pos);

private void setFm() {
	fm = (getFont() != null) ? getToolkit().getFontMetrics(getFont()) : null;
}

/** Returns the height of 1 row. */
protected int preferredHeight() {
	setFm();
	return (fm != null) ? fm.getHeight() : 30;
}

/** Returns the width of an 'N' times the specified number of columns. */
protected int preferredWidth() {
	return (fm != null) ? columns * fm.charWidth('N') : columns * 10;
}

public void addNotify() {

	if (!isDisplayable()) {
		super.addNotify();
		setFm();
	}
	window = BWTUtil.getWindow(this);
	if (window instanceof Dialog) {
		dialog_bug = true;
	}

/* JDK110_START */
	enableEvents(JCAWTEvent.MOUSE_MOTION_EVENT_MASK);
/* JDK110_END */
}


boolean first = true;

/* AWT_START 
public void reshape(int x, int y, int width, int height) {
 AWT_END */
/* SWING_START */
public void setBounds(int x, int y, int width, int height) {
/* SWING_END */

	synchronized (this) {
		if (x == location().x && y == location().y
			&& width == size().width && height == size().height)
			return;
	}

/* AWT_START 
	super.reshape(x, y, width, height);
 AWT_END */
/* SWING_START */
	super.setBounds(x, y, width, height);
/* SWING_END */

	synchronized (this) {
		if (isDisplayable()) {
			if (first && show_cursor)
				showPosition(cursor_pos);
			first = false;
		}
	}
}

public void repaint() {
	if (!isFrozen()) {
		super.repaint();
		needs_repaint = false;
	}
	else
		needs_repaint = true;
}

void setSelectedBg(Graphics gc) {
	gc.setColor(selected_bg != null ? selected_bg : getForeground());
}

void setSelectedFg(Graphics gc) {
	gc.setColor(selected_fg != null ? selected_fg : getBackground());
}

/**
 * Converts an x coordinate into a text position. The y value is ignored.
 */
abstract public int pointToPosition(int x, int y);

/**
 * Gets the physical location of a position, ignoring scrolling.
 * @param p point (returned if not null)
 * @return the location, or null if p is not null
 */
protected Point positionToPoint(int pos, Point p) {
	if (p != null) {
		p.x = positionToX(pos); p.y = positionToY(pos);
		return null;
	}
	return new Point(positionToX(pos), positionToY(pos));
}

private static Point p1 = new Point(0,0), p2 = new Point(0,0);

/** Draws the text between 2 positions. */
protected void repaintPositions(int start, int end) {
	if (start == end) return;
	synchronized (this) {
		positionToPoint(start, p1);
		positionToPoint(end, p2);
	}
	repaintPositions(p1, p2);
}

/** Draws the text between 2 points. */
protected void repaintPositions(Point p1, Point p2) {
	int x = 0, y = 0;
	synchronized (this) {
		if (p1.equals(p2)) return;
		x = Math.min(p1.x, p2.x);
		y = Math.min(p1.y, p2.y);
	}
	paintImmediately(x - horiz_origin, y,
					 size().width - (x - horiz_origin),
					 Math.max(p1.y, p2.y)-y+fm.getHeight());
}

/** Emits a beep. For JDK 1.0.2 running in a browser it also flashes it. */
public void beep() {
/* JDK102_START
	System.out.print(BWTEnum.BELL);
	System.out.flush();
	if (!BWTUtil.inBrowser(this)) return;

	Color fg = getForeground(), bg = getBackground();
	setForeground(bg);
	setBackground(fg);

	getToolkit().sync();
	try {
		Thread.sleep(250);
	} catch(Exception e) {}

	setBackground(bg);
	setForeground(fg);
 JDK102_END */
/* JDK110_START */
	getToolkit().beep();
/* JDK110_END */
}

/** If the component is editable, inserts the clipboard selection
 * at the current cursor position.
 * @return false if the component is not editable
 * @see #readClipboard
 */
public boolean pasteFromClipboard(Event ev) {
	if (!editable) return false;
	String s = readClipboard();
	if (s == null) {
		// nothing to insert
		return false;
	}

	boolean pasted = true;

	if (overstrike
		&& (max_length - cursor_pos) > s.length()) {
		// we are in overstrike mode and the number of characters we are
		// adding does not exceed the number left from the cursor position
		// to the end of the maximum allowed number of characters
		deleteSelection();
		replaceRange(s, cursor_pos, cursor_pos+s.length());
	}
	else if ((num_char + s.length()) <= max_length) {
		// we are not in overstrike mode and the number of characters to
		// insert when added to the number currently in the field does
		// not exceed the specified maximum number of characters
		deleteSelection();
		insert(s, cursor_pos);
	}
	else {
		// the max length of the field does not allow this paste to
		// occur
		pasted = false;
	}
	if (pasted == true) {
		changed = true;
	}
	return pasted;
}

/** Writes to the clipboard. */
protected synchronized void writeClipboard(String v) {
/* JDK102_START
	clipboard = v;
 JDK102_END */
/* JDK110_START */
	// Get system clipboard
	if (TRACE) {
		System.out.println("Writing to clipboard");
	}
	try {
		Clipboard c = getToolkit().getSystemClipboard();
		// Create transferable string
		StringSelection ss = new StringSelection(getText().substring(select_start, select_end));
		// Put it to the clipboard
		c.setContents(ss, ss);
	}
	catch (Exception e) {
		// Can't use system clipboard - use internal clipboard
		clipboard = v;
	}
/* JDK110_END */
}

/* JDK110_START */
protected static String getStringFromTransferable(Transferable t) {
	if (t == null) {
		if (TRACE) {
			System.out.println("getStringFromTransferable: returning null b/c Transferable was null");
		}
		return(null);
	}

	String s = null;

	if (t.isDataFlavorSupported(DataFlavor.stringFlavor)) {
		// Handle preferred java text passing machanism
		if (TRACE) {
			System.out.println("getStringFromTransferable: processing stringFlavor");
		}
		try {
			s = (String) t.getTransferData(DataFlavor.stringFlavor);
		}
		catch (Exception e) {
			s = null;
		}
	}
	else if (t.isDataFlavorSupported(DataFlavor.plainTextFlavor)) {
		// Handle secondary java text passing machansm
		// Note that as of JDK 1.2 beta 2 this stuff still does not work
		// properly
		if (TRACE) {
			System.out.println("getStringFromTransferable: processing plainTextFlavor");
		}
		try {
			InputStream io;
			io = (InputStream) t.getTransferData(DataFlavor.plainTextFlavor);
			int num_bytes = io.available();
			if (num_bytes > 0) {
				// there are bytes in the sream to read
				byte bytes[] = new byte[num_bytes];
				if (num_bytes == io.read(bytes)) {
					// all the bytes were successfully read in
					s = new String(bytes, 0, bytes.length, "unicode");
				}
			}
		}
		catch (Exception e) {
			s = null;
		}
	}

	return(s);
}
/* JDK110_END */

/** Gets the clipboard contents. */
protected String readClipboard() {
	String s = null;

/* JDK102_START
	s = clipboard;
 JDK102_END */
/* JDK110_START */
	// Get system clipboard
	try {
		Clipboard c = getToolkit().getSystemClipboard();
		Transferable t = c.getContents(this);
		if (t != null) {
			s = getStringFromTransferable(t);
		}
	}
	catch (Exception e) {
		// Can't use system clipboard - use internal clipboard
		s = clipboard;
	}
/* JDK110_END */
	return s;
}

/** If text is currently selected, copies the selection to the clipboard
 * and deletes it from the component.
 * @see #writeClipboard
 */
public void cutToClipboard(Event ev) {
	if (select_start < select_end) {
		copyToClipboard(ev);
		deleteSelection();
	}
}

/** If text is currently selected, copies the selection to the clipboard.
 * @see #writeClipboard
 */
public void copyToClipboard(Event ev) {
	if (select_start < select_end) {
		writeClipboard(getText().substring(select_start, select_end));
	}
}

/** Gets the selection type corresponding to the index.
 * @see #setSelectionList
 */
protected int getSelectionType(int v) {
	int k = Math.max(0, Math.min(select_list.length-1, v-1));
	return select_list[k];
}

/** Starts a programmatic or interactive action.
 * It erases the cursor and saves the event.
 */
void startAction(Event ev) {
	synchronized (this) {
		action_count++;
		if (action_count > 1) return;
		last_event = ev;
	}

	paintCursor(false);
	paint_cursor = false;
	blinkCursor(false);
	freeze();
}

/** Ends the current programmatic or interactive action.
 * It restores the cursor, updates the scrollbars (if any) and scrolls the
 * display if necessary to show the cursor.
 */
void endAction() {
	synchronized (this) {
		if (action_count > 0) {
			action_count--;
			if (action_count > 0) return;
		}
		paint_cursor = true;
		last_event = null;
		unfreeze();
	}

	if (needs_repaint) {
		repaint();
	}

	// updateScrollbars(), showPosition() causes scrollbar to repaint and acquires AWT Tree lock.
	// Call it outside the monitor to avoid deadlock.
	updateScrollbars();

	if (show_cursor)
		showPosition(cursor_pos);
	paintCursor(true);
	blinkCursor(true);
}

/** Starts the selection. */
protected void selectStart(Event ev) {
	startAction(ev);
	int pos = pointToPosition(ev.x, ev.y);
	select_anchor = pos;
	selection_type = getSelectionType(ev.clickCount);
	select(pos, pos, selection_type);
	setCursorPosition(pos);
	endAction();
}

/** Extends the current selection. */
protected void selectExtend(Event ev) {
	if (ignore_select_extensions) {
		return;
	}
	startAction(ev);
	int pos = pointToPosition(ev.x, ev.y);
	select(select_anchor, pos, selection_type);
	setCursorPosition(pos);
	endAction();
}

/** Ends the current selection.
 */
protected void selectEnd(Event ev) {
	startAction(ev);
	setCursorPosition(select_start);
	endAction();
}

// Windows bug - if MB2 pressed, mouseUp gets MB1
boolean pasting = false;

/** Inserts the current clipboard contents at the event's position,
 * after first deleting any selected text.
 * @see #pasteFromClipboard
 */
protected void paste(Event ev) {
	startAction(ev);
	setCursorPosition(pointToPosition(ev.x, ev.y));
	pasting = true;
	boolean s = pasteFromClipboard(ev);
	endAction();
	if (!s) beep();
	else changed = true;
}

protected void freeze() { disabled_repaint_count++; }
protected void unfreeze() {
	if (disabled_repaint_count > 0) disabled_repaint_count--;
}
protected boolean isFrozen() { return disabled_repaint_count != 0; }

/** Positions the cursor, or if MB2 was pressed, pastes the current clipboard
 * contents.
 * @see #pasteFromClipboard
 * @see #selectStart
 */
public boolean mouseDown(Event ev, int x, int y) {
	super.mouseDown(ev, x, y);
	if (BWTUtil.getMouseButton(ev) == 2) {
		paste(ev);
		return true;
	}
/* JDK110_START */
	else if (BWTUtil.getMouseButton(ev) == 3) {
		// indicates that Right mouse button was depressed
		createPopup(this, x, y);
	}
/* JDK110_END */
	else if (BWTUtil.getMouseButton(ev) != 1)
		return false;
	else if (ev.shiftDown())
		selectExtend(ev);
	else
		selectStart(ev);
	return true;
}

/** Selects the text.
 * @see #selectExtend
 */
public boolean mouseDrag(Event ev, int x, int y) {
	if (pasting) return true;
	selectExtend(ev);
	return true;
}

/** Ends text selection.
 * @see #selectEnd
 */
public boolean mouseUp(Event ev, int x, int y) {
	// clear this flag if it was set
	if (TRACE) {
		System.out.println("mouseUp()");
	}
	ignore_select_extensions = false;

	if (BWTUtil.getMouseButton(ev) == 3) {
		return false;
	}
	if (!pasting) {
		selectEnd(ev);
	}
	pasting = false;
	return true;
}

/** Scrolls to the first line. */
protected void scrollHome(Event ev) {
	if (ev.shiftDown()) {
		if (select_start != select_end && cursor_pos > select_start)
			select(0, select_start, SELECT_CHAR);
		else
			select(0, select_end, SELECT_CHAR);
	}
	else
		select(0);
	setCursorPosition(0);
}

/** Scrolls to the last line. */
protected void scrollEnd(Event ev) {
	if (ev.shiftDown()) {
		if (select_start != select_end && cursor_pos < select_end) {
			select(select_end, num_char, SELECT_CHAR);
		}
		else {
			select(select_start, num_char, SELECT_CHAR);
		}
		setCursorPosition(select_start);
	}
	else {
		select(num_char);
		setCursorPosition(num_char);
	}
}

/** Scrolls to the end of the current line. */
protected void scrollLineEnd(Event ev) {
	scrollEnd(ev);
}

/** Scrolls to the beginning of the current line. */
protected void scrollLineBegin(Event ev) {
	scrollHome(ev);
}

/** Moves the cursor forward one character.
 * If the Shift key is pressed, the selection is extended.
 */
protected void moveForwardChar(Event ev) {
	if (cursor_pos == num_char) return;
	startAction(ev);
	if (ev.shiftDown()) {
		int start = select_start, end = select_end, pos;
		if (cursor_pos < end)
			pos = start = Math.min(num_char, start+1);
		else
			pos = end = Math.min(num_char, end+1);
		setCursorPosition(pos);
		select(start, end, SELECT_NONE);
	}
	else {
		select(cursor_pos+1);
	}
	endAction();
}

/** Moves the cursor backward one character.
 * If the Shift key is pressed, the selection is extended.
 */
protected void moveBackwardChar(Event ev) {
	if (cursor_pos == 0) return;
	startAction(ev);
	if (ev.shiftDown()) {
		int start = select_start, end = select_end, pos;
		if (cursor_pos > start)
			pos = end = Math.max(0, end-1);
		else
			pos = start = Math.max(0, start-1);
		select(start, end, SELECT_NONE);
		setCursorPosition(pos);
	}
	else {
		select(cursor_pos-1);
	}
	endAction();
}

/** Inserts a character at the current cursor position.
 * @return false if the component is not editable
 */
protected boolean insertChar(Event ev, int key) {

	if (!editable) return false;
	startAction(ev);
	char[] tmp = { (char)key };
	int start = cursor_pos, end = cursor_pos;

	if (select_end > select_start) {
		start = select_start;
		end = select_end;
	}

	if (overstrike && start == end && end < num_char)
		end++;

	if (replaceRangeInternal(new String(tmp), start, end))
		changed = true;
	endAction();
	return true;
}

/** Deletes the current selection; or a character if no text is selected.
 * @param current true if the current char should be deleted; otherwise the
 * previous char is deleted
 * @return false if the component is not editable
 */
protected boolean deleteChar(Event ev, boolean current) {
	if (!editable) return false;
	boolean ok = false;

	startAction(ev);
	if (select_start < select_end)
		ok = deleteRange(select_start, select_end);
	else if (!current && cursor_pos > 0)
		ok = deleteRange(cursor_pos-1, cursor_pos);
	else if (current && cursor_pos < num_char)
		ok = deleteRange(cursor_pos, cursor_pos+1);
	endAction();
	return true;
}

/** Deletes the character before the current cursor position.
 * @return false if the component is not editable
 */
protected boolean deletePreviousChar(Event ev) {
	return deleteChar(ev, false);
}

/** Deletes the character at the current cursor position.
 * @return false if the component is not editable
 */
protected boolean deleteCurrentChar(Event ev) {
	return deleteChar(ev, true);
}

/** Toggles the value of the overstrike flag. */
protected void toggleOverstrike(Event ev) {
	overstrike = !overstrike;
}

/** Processes keyDown events.
<pre>
CTRL/A      scrollHome
CTRL/D      deleteCurrentChar
CTRL/E      scrollEnd
CTRL/O      toggleOverstrike
CTRL/C      copyToClipboard
CTRL/X      cutToClipboard
CTRL/V      pasteFromClipboard
HOME        scrollHome
END         scrollEnd
RIGHT       moveForwardChar
LEFT        moveBackwardChar
BS          deletePreviousChar
DELETE      deleteCurrentChar
any other   insertChar
</pre>
*/
/* JDK102_START
public boolean keyDown(Event ev, int key) {
	boolean no_error = true, handled = true;
	last_event = ev;

	if (ev.controlDown()) {
		int key2 = key + (int)'A' - 1;
		if (key2 == (int)'A') scrollLineBegin(ev);
		else if (key2 == (int)'C') copyToClipboard(ev);
		else if (key2 == (int)'D') no_error = deleteCurrentChar(ev);
		else if (key2 == (int)'E') scrollLineEnd(ev);
		else if (key2 == (int)'H') no_error = deletePreviousChar(ev);
		else if (key2 == (int)'O') toggleOverstrike(ev);
		else if (key2 == (int)'X') cutToClipboard(ev);
		else if (key2 == (int)'V') no_error = pasteFromClipboard(ev);
		else if (key == Event.HOME) {
		    paintCursor(false);
            scrollHome(ev);
		}
		else if (key == Event.END) {
		    paintCursor(false);
		    scrollEnd(ev);
        }
		else handled = false;
	}

	else {
		if (key == Event.RIGHT) moveForwardChar(ev);
		else if (key == Event.LEFT) moveBackwardChar(ev);

		else if (key == BWTEnum.BS) no_error = deletePreviousChar(ev);
		else if (key == BWTEnum.DELETE) no_error = deleteCurrentChar(ev);

		else if (key == Event.HOME) {
		    paintCursor(false);
		    scrollLineBegin(ev);
        }
		else if (key == Event.END) {
		    paintCursor(false);
		    scrollLineEnd(ev);
        }
		else if (key == BWTEnum.TAB) handled = false;
		else if (key == BWTEnum.ESC) no_error = true;

		// printable character
		else if (ev.id != Event.KEY_ACTION)
			no_error = insertChar(ev, key);
		else
			handled = false;
	}

	if (!no_error)
		beep();
	last_event = null;
	return handled ? true : super.keyDown(ev, key);
}
 JDK102_END */

/* SWING_START */
//
// In Swing, we have to disable the focus manager in order
// to get the key processing we want
//
public void processKeyEvent(KeyEvent e) {
	if (is_cell_editor == false) {
		super.processKeyEvent(e);
		return;
	}
	FocusManager fm = FocusManager.getCurrentManager();
	FocusManager.disableSwingFocusManager();
	super.processKeyEvent(e);
	fm.setCurrentManager(fm);
}
/* SWING_END */
/* JDK110_START */
public boolean keyDown(Event ev, int key) {
	boolean no_error = true, handled = true;
	last_event = ev;

	if (TRACE) {
		System.out.println("keyDown(" + ev + ", " + key + ")");
	}
	if (ev.controlDown()) {
		if (cmpKeyStroke(LocaleInfo.HOMEKEY, key)) {
			scrollLineBegin(ev);
		}
		else if (cmpKeyStroke(LocaleInfo.COPYKEY, key)) {
			copyToClipboard(ev);
		}
		else if (cmpKeyStroke(LocaleInfo.DELETEKEY, key)) {
			no_error = deleteCurrentChar(ev);
		}
		else if (cmpKeyStroke(LocaleInfo.ENDKEY, key)) {
			scrollLineEnd(ev);
		}
		else if (cmpKeyStroke(LocaleInfo.BSKEY, key)) {
			no_error = deletePreviousChar(ev);
		}
		else if (cmpKeyStroke(LocaleInfo.OVRSTKKEY, key)) {
			toggleOverstrike(ev);
		}
		else if (cmpKeyStroke(LocaleInfo.CUTKEY, key)) {
			cutToClipboard(ev);
		}
		else if (cmpKeyStroke(LocaleInfo.PASTEKEY, key)) {
			no_error = pasteFromClipboard(ev);
		}
		else if (key == Event.HOME) {
			paintCursor(false);
			scrollHome(ev);
		}
		else if (key == Event.END) {
			paintCursor(false);
			scrollEnd(ev);
		}
		else handled = false;
	}
	else {
		if (key == Event.RIGHT) moveForwardChar(ev);
		else if (key == Event.LEFT) moveBackwardChar(ev);

		else if (key == BWTEnum.BS) no_error = deletePreviousChar(ev);
		else if (key == BWTEnum.DELETE) no_error = deleteCurrentChar(ev);

		else if (key == Event.HOME) {
			paintCursor(false);
			scrollLineBegin(ev);
		}
		else if (key == Event.END) {
			paintCursor(false);
			scrollLineEnd(ev);
		}
		else if (key == BWTEnum.TAB) handled = false;
		else if (key == Event.ESCAPE) {
			no_error = true;
			cell_editor_support.fireCancelEditing(new CellEditorEvent(new AWTEvent(this, 0) {}));
		}
		// printable character
		else if (ev.id != Event.KEY_ACTION)
			no_error = insertChar(ev, key);
		else
			handled = false;
	}

	if (!no_error)
		beep();
	last_event = null;
	return handled ? true : super.keyDown(ev, key);
}
/* JDK110_END */
/* SWING_START */
private static final JMenuItem createMenuItem(String s) {
	return new JMenuItem(s);
}
/* SWING_END */
/* AWT110_START 
private static final MenuItem createMenuItem(String s) {
	return new MenuItem(s);
}
 AWT110_END */
/* JDK110_START */
protected void createPopup(Component comp, int x, int y) {
	if (popup_menu != null) {
		remove(popup_menu);
	}
/* JDK110_END */
/* SWING_START */
	popup_menu = new JPopupMenu();
	JMenuItem item;
/* SWING_END */
/* AWT110_START 
	popup_menu = new PopupMenu();
	MenuItem item;
 AWT110_END */
/* JDK110_START */

	boolean all_selected = false,
		no_selection = false;

	if (TRACE) {
		System.out.println("   select_start = " + select_start);
		System.out.println("   select_end = " + select_end);
		System.out.println("   num_char = " + num_char);
	}
	if ((select_start == 0 && (select_end + 1) >= num_char)
		&& num_char != 0) {
		all_selected = true;
	}
	if (select_start == select_end || num_char == 0) {
		no_selection = true;
	}

	// Cut
	item = createMenuItem(li.getString(LocaleInfo.CUT));
	if (no_selection || !isEditable()) {
		item.setEnabled(false);
	}
	else {
		item.addActionListener(new Cut());
	}
	popup_menu.add(item);

	// Copy
	item = createMenuItem(li.getString(LocaleInfo.COPY));
	if (no_selection) {
		item.setEnabled(false);
	}
	else {
		item.addActionListener(new Copy());
	}
	popup_menu.add(item);

	// Paste
	item = createMenuItem(li.getString(LocaleInfo.PASTE));
	if (!isEditable()) {
		item.setEnabled(false);
	}
	else {
		item.addActionListener(new Paste());
	}
	popup_menu.add(item);

	// Delete
	item = createMenuItem(li.getString(LocaleInfo.DELETE));
	if (no_selection || !isEditable()) {
		item.setEnabled(false);
	}
	else {
		item.addActionListener(new Delete());
	}
	popup_menu.add(item);

	// Add separator
	popup_menu.addSeparator();

	// Select All
	item = createMenuItem(li.getString(LocaleInfo.SELECTALL));
	if (all_selected || num_char == 0) {
		item.setEnabled(false);
	}
	else {
		item.addActionListener(new SelectAll());
	}
	popup_menu.add(item);

	add(popup_menu);

	popup_menu.show(comp, x, y);
}

/* JDK110_END */

/**
 * Inner class to listen for and Action and then perform a "cut"
 * on the TextComponet it belongs to
 */
/* JDK110_START */
public class Cut implements ActionListener, Serializable {
	public void actionPerformed(ActionEvent e) {
		cutToClipboard(null);
	}
}
/* JDK110_END */

/**
 * Inner class to listen for and Action and then perform a "copy"
 * on the TextComponet it belongs to
 */
/* JDK110_START */
public class Copy implements ActionListener, Serializable {
	public void actionPerformed(ActionEvent e) {
		copyToClipboard(null);
	}
}
/* JDK110_END */

/**
 * Inner class to listen for and Action and then perform a "paste"
 * on the TextComponet it belongs to
 */
/* JDK110_START */
public class Paste implements ActionListener, Serializable {
	public void actionPerformed(ActionEvent e) {
		pasteFromClipboard(null);
	}
}
/* JDK110_END */

/**
 * Inner class to listen for and Action and then perform a "delete"
 * on the TextComponet it belongs to
 */
/* JDK110_START */
public class Delete implements ActionListener, Serializable {
	public void actionPerformed(ActionEvent e) {
		deleteSelection();
	}
}
/* JDK110_END */

/**
 * Inner class to listen for and Action and then perform a "SelectAll"
 * on the TextComponet it belongs to
 */
/* JDK110_START */
public class SelectAll implements ActionListener, Serializable {
	public void actionPerformed(ActionEvent e) {
		selectAll();
	}
}

protected boolean cmpKeyStroke(String modkey, int key) {
	Integer cmpkey = (Integer) li.getObject(modkey);
	if (TRACE) {
		System.out.println("cmpKeyStroke(" + modkey + ", " + key + ")");
		System.out.println("             cmpkey = " + cmpkey);
	}
	if (cmpkey == null) {
		return(false);
	}
	if (cmpkey.intValue() == key) {
		return(true);
	}
	return(false);
}

/* JDK110_END */


/**
 * Enables cursor blinking. No action is taken if the component
 * does not have focus, is not editable, or if the cursor is already blinking.
 * This method is called by gotFocus and lostFocus.
 * @param on if true, start cursor blinking; otherwise disable it and
 * don't display the cursor
 */
protected void blinkCursor(boolean on) {

   	if (!has_focus || !display_cursor || !isEnabled() || dispose) return;

	if (on) {
		if (cursor_thread != null && cursor_thread.isAlive()) return;
		cursor_thread = new Thread(this);
		// In case cursor_thread is changed and started or stopped by other thread.
		if (cursor_thread != null && !cursor_thread.isAlive()) {
			cursor_thread.start();
		}
	}
	else if (cursor_thread != null && cursor_thread.isAlive()) {
		try {
			cursor_thread.yield();
			cursor_thread.stop();
			paintCursor(false);
			cursor_thread = null;
		} catch (Exception e) {}
	}
}

/** Starts the cursor blinking if the component is editable.
 * @see #blinkCursor
 */
public boolean gotFocus102(Event ev, Object what) {
	boolean s = super.gotFocus102(ev, what);
	setFm();
	if (s) {
		blinkCursor(true);
	}
	return s;
}

/** Stops the cursor blinking.
 * @see #blinkCursor
 */
public boolean lostFocus102(Event ev, Object what) {
	blinkCursor(false);
	repaint();
	/*
	Note: JCTextField should not cancel selection when it loses focus
	if (ignore_lose_focus == false) {
		// this should only be ignored if the field is being used as
		// a cell editor
		cancelSelection();
	}
	*/
	return super.lostFocus102(ev, what);
}

/** Disables the component and stops the cursor blinking. */
public synchronized void disable() {
	if (isEnabled()) {
		blinkCursor(false);
		super.disable();
	}
}

		//
		// jclass.cell.CellEditor Implementation
		//
/* JDK110_START */
public void initialize(InitialEvent ev, CellInfo info, Object o) {
	containedInitialize(ev, info, o);
	setInsets(info.getMarginInsets());
	setShadowThickness(info.getBorderInsets().top);
	if (info.isEditable()) {
		setEditable(true);
	}
	else {
		setEditable(false);
	}
}

// Lets the text component handle a specific subset of initialization
// when used inside a combo or spin box editor
public void containedInitialize(InitialEvent ev, CellInfo info, Object o) {
/* JDK110_END */
/* SWING_START */
	is_cell_editor = true;
/* SWING_END */
/* JDK110_START */
	if (TRACE) {
		System.out.println("containedInitialize()");
	}
	// since lose focus event come in _after_ this is called it is
	// necessary to ignore any lose focus events when acting as
	// a cell editor
	ignore_lose_focus = true;

  // Originaly this :
	// String s = (String) o;
  // However this causes a problem is o is not a String.
  // changed to use a toString call.
  String s;
  if(o instanceof String) {
      s = (String) o;
  }
  else {
      s = o.toString();
  }

	setHighlightThickness(0);

	// we only support uniform border thickness so we randomly use the
	// top border thickness
	setAlignment(info.getHorizontalAlignment());
	setBackground(info.getBackground());
	setForeground(info.getForeground());
	setSelectedBackground(info.getSelectedBackground());
	setSelectedForeground(info.getSelectedForeground());
	setText(s);
	if (ev.getEventType() == ev.MOUSE) {
		if (TRACE) {
			System.out.println("\ttrying to ignore selections");
		}
		// this flag is cleared by the first mouse up
		// we set it to avoid mous jiggles causing the
		// selectAll() to be less than all
		ignore_select_extensions = true;
	}
	if (info.getSelectAll() == true) {
		selectAll();
	}
	else {
		if (ev.getEventType() == ev.MOUSE) {
			// position the cursor if we have a click event
			setCursorPosition(ev, info, s);
		}
	}

	if (ev.getEventType() == ev.KEY) {
		insertChar(null, ev.getKey());
	}
}
/* JDK110_END */

/**
 * support function for the above initialize() method to be implemented
 * in the sub-class
 */
/* JDK110_START */
abstract public void setCursorPosition(InitialEvent ev,
										  CellInfo info, String s);
/* JDK110_END */

/* JDK110_START */
// For internal use only
public Component getComponent() {
	return this;
}

// For internal use only
public Object getCellEditorValue() {
	return getText();
}

// For internal use only
public boolean stopCellEditing() {
	return true;
}

// For internal use only
public boolean isModified() {
	return getChanged();
}

// For internal use only
public void cancelCellEditing() {
	return;
}

// to be provided by subclass
abstract public KeyModifier[] getReservedKeys();

// For internal use only
public void addCellEditorListener(CellEditorListener l) {
	cell_editor_support.addCellEditorListener(l);
}

// For internal use only
public void removeCellEditorListener(CellEditorListener l) {
	cell_editor_support.removeCellEditorListener(l);
}

// For internal use only
public CellEditorSupport getCellEditorSupport() {
	return cell_editor_support;
}

		//
		// jclass.cell.CellRenderer Implementation Extensions
		//

abstract protected void paintComponent(Graphics gc, int horizontal_offset,
							  String text, Rectangle rect,
							  int alignment, FontMetrics fm, boolean enabled,
							  Color foreground, Color background,
							  Color selected_foreground,
							  Color selected_background,
							  int select_start, int select_end);

public void draw(Graphics gc, CellInfo info, Object o, boolean selected) {
	gc.setFont(info.getFont());
	gc.setColor(selected ? info.getSelectedForeground() : info.getForeground());
	Rectangle cell_size = info.getDrawingArea();
	Dimension ps = getPreferredSize(info, o);
	int va = info.getVerticalAlignment();
	int trans_y = 0;
	if (va == info.CENTER) {
		trans_y = (cell_size.height - ps.height) / 2;
	}
	else if (va == info.BOTTOM) {
		trans_y = cell_size.height - ps.height;
	}

	// translate the gc down to handle vertical alignment properly
	if (trans_y != 0) {
		gc.translate(0, trans_y);
	}

	paintComponent(gc, 0, (String) o, info.getDrawingArea(),
				   info.getHorizontalAlignment(),
				   info.getFontMetrics(), true,
				   info.getForeground(), info.getBackground(),
				   info.getSelectedForeground(), info.getSelectedBackground(),
				   0, 0);
	if (trans_y != 0) {
		gc.translate(0, -trans_y);
	}

    // arrows are drawn using the current foreground color
	if ((ps.width > cell_size.width || ps.height > cell_size.height) &&
		info.getClipHints() != CellInfo.SHOW_NONE) {
		jclass.cell.Utilities.drawClipArrows(gc, info, ps,
								 jclass.cell.Utilities.DEFAULT_CLIP_ARROW_SIZE,
								 true);
	}
}

abstract public Dimension getPreferredSize(CellInfo cellInfo, Object o);

/* JDK110_END */

/**
 * This method should be called when the component is no longer needed.
 * It ensures that the component will be garbage collected.
 */
public void dispose() {
	dispose = true;
	window = null;
	super.dispose();

	if (cursor_thread != null) {
		cursor_thread.stop();
		cursor_thread = null;
	}
}

}
