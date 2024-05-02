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
import jclass.util.JCFile;
import java.awt.*;
import java.applet.Applet;
import java.io.*;
/* JDK110_START */
import java.awt.event.*;
import jclass.cell.*;
/* JDK110_END */

/** A private class used by JCTextArea.
 * @see JCTextArea
 */
public class JCTextAreaComponent extends JCTextComponent
     implements JCScrollableInterface {

public static final boolean TRACE = false;

int rows = 5;
boolean multiline = true;
/** Parent window. */
transient protected JCScrolledWindow scrolled_window = null;

protected boolean update_scrollbars = true;
int top_row;	// row number of 1st visible line
int top_char;
int bottom_row = BWTEnum.NOVALUE;
int widest_row = BWTEnum.NOVALUE;
int original_bottom_margin;

transient JCVector lines;
private static final String base = "text";
private static int nameCounter = 0;

/** The number of visible lines. */
protected int visible_rows;

protected boolean post_text_event_on_set_text = true;

Rectangle draw_rect = new Rectangle();

/**
 * Creates an empty, editable field with 5 rows and 20 columns.
 */
public JCTextAreaComponent() {
	this("", 5, 20);
}

/**
 * Creates an editable field with the specified text and 5 rows and 20 columns.
 */
public JCTextAreaComponent(String text) {
	this(text, 5, 20);
}

/**
 * Creates an editable field with the specified text and number of columns.
 */
public JCTextAreaComponent(String text, int rows, int cols) {
	this(text, null, null);
	this.rows = rows;
	columns = cols;
}

/** Creates a TextArea which reads parameters from the applet's HTML file.
 * @param applet the applet whose PARAM tags are to be read
 * @param name if this is not null, only parameters preceded by this name are read
 * @see java.applet.Applet#getParameter
 * @see jclass.util.JCString
 */
public JCTextAreaComponent(String text, Applet applet, String name) {
	super(applet, name);
	setInsets(new Insets(2,2,2,2));
	original_bottom_margin = 2;
	lines = new JCVector();
	if (name == null)
		setName(base + nameCounter++);
	setText(text);
	setCursorPosition(0);
}

/**
 * Creates an editable 5-row by 20-column field with the text
 * from the specified file.
 * @param applet if not null, its documentBase() is used to construct a complete filename if necessary.
 * @param file the file to be read. If an http protocol is not specified
 * (a ":" is not present), the current working directory is prepended to
 * the file name
 * @param rows,cols the number of rows and columns to display
 * @see #readFile
 * @exception IOException If an I/O error has occurred.
 */
public JCTextAreaComponent(Applet applet, String file, int rows, int cols)
throws IOException {
	this("", rows, cols);
	readFile(JCFile.createURL(applet, file).openStream());
	setCursorPosition(0);
}

/** Reads the parameter values from the HTML page using the component's applet.
 * The values will override those previously set.
 */
protected void getParameters() {
	super.getParameters();
	TextAreaConverter.getParams(this);
}

/**
 * Reads data from a file and appends it to the field.
 * @param applet if not null, its documentBase() is used to construct a complete filename if necessary.
 * @param file the file to be read. If an http protocol is not specified
 * (a ":" is not present), the current working directory is prepended to
 * the file name
 * @exception IOException If an I/O error has occurred.
 */
public void readFile(Applet applet, String file) throws IOException {
	readFile(JCFile.createURL(applet, file).openStream());
}

/**
 * Reads data from a file stream and appends it to the textarea.
 * @exception IOException If an I/O error has occurred.
 */
public void readFile(InputStream stream) throws IOException {
	DataInputStream data = new DataInputStream(new BufferedInputStream(stream));
	for (String s = data.readLine(); s != null; s = data.readLine())
		appendRow(s);
}

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
		do_repaint = false;
		if (post_text_event_on_set_text) {
			// calling this makes sure that the TextChanged events
			// are posted
			super.replaceRangeInternal(text, 0, num_char);
		}
		else {
			super.replaceTextInternal(text, 0, num_char);
		}
		do_repaint = true;
		if (isDisplayable())
			buildLineTable();
	}
	// select causes scrollbar to repaint and acquires AWT Tree lock.
	// Call it outside the monitor to avoid deadlock.
	select(num_char);
	repaint();
	changed = false;
}

/**
 * Older versions of JCTextArea contained a bug whereby JCTextEvents
 * were not thrown when calling setText().  This has been fixed as of
 * JClass 3.5.1.  This property is provided as a backwards compatibility
 * option for those existing applications that rely on the old
 * incorrect behaviour of setText()
 * @deprecated This should not be used except for backward compatibily
 * with versions <= 3.5.0
 */
public void setPostTextEventOnSetText(boolean v) {
	post_text_event_on_set_text = v;
}

/**
 * Appends text to the end. The cursor is moved to the end of the text.
 * @see jclass.bwt.JCTextArea.html#insert
 */
public void append(String text) {
	if (text == null) text = "";
	if (text.indexOf('\n') == -1)
		super.append(text);
	else
		setText(getText() + text);
}

/**
 * Appends a row of text. The cursor is moved to the end of the text.
 */
public void appendRow(String text) { append(text+'\n'); }

/** Sets the spacing between the text and the edge of the component.
 */
public void setInsets(Insets v) {
	startAction(null);
	insets = v;
	original_bottom_margin = insets.bottom;
	adjustInsets();
	repaint();
	endAction();
}

/** Gets the current top-most visible row.
 * @see #setTopRow
 */
public int getTopRow() { return top_row; }

/** Sets the top-most visible row.
 */
public void setTopRow(int row) {
	int new_top_row = Math.min(lines.size() - visible_rows, Math.max(0, row));
	if (new_top_row == top_row) return;
	top_row = new_top_row;
	vert_origin = BWTEnum.NOVALUE;
	repaint();
	updateScrollbars();
}

/** Gets the position of the leftmost character on the first line currently
 * displayed, or 0 if the component is empty.
 * @see #setTopCharacter
 */
public int getTopCharacter() { return top_char; }

/** Sets the position of the leftmost character on the first line currently
 * displayed. This is the number of characters from the beginning of the
 * text buffer. The first character position is 0.
 * @see #setTopRow
 */
public void setTopCharacter(int pos) {
	if (top_char == pos)
		return;
	top_char = pos;
	setTopRow(positionToRow(pos));
}

/** If true (default), the user can break text into multiple lines by
 * hitting ENTER.<p>
 * <strong>HTML param name/value:</strong> "Multiline"/boolean
 */
public void setMultiline(boolean v) { multiline = v; }

/** Gets the Multiline value.
 * @see #setMultiline
 */
public boolean getMultiline() { return multiline; }

/** Gets the number of rows.
 * @see #setRows
 */
public int getRows() { return rows; }

/** Sets the number of rows.<p>
 * <strong>HTML param name/value:</strong> "Rows"/int
 * @exception IllegalArgumentException If the value is less than 0.
 */
public void setRows(int r) {
	if (rows < 0)
	    throw new IllegalArgumentException("rows less than zero.");
	rows = r;
}

/** Returns the height of the specified number of rows. */
protected int preferredHeight() { return rows * fm.getHeight(); }

/**
 * Returns the minimum size needed for the specified number of columns.
 */
public Dimension getMinimumSize(int columns) {
	return getMinimumSize(rows, columns);
}

/**
 * Returns the minimum size needed for the specified number of rows and columns.
 */
public Dimension getMinimumSize(int rows, int columns) {
	synchronized (LOCK) {
	    return (isDisplayable()) ?
			new Dimension(columns * fm.charWidth('N'),
						  rows * fm.getHeight()) :
/* AWT_START 
			super.minimumSize();
 AWT_END */
/* SWING_START */
			super.getMinimumSize();
/* SWING_END */
	}
}

/*
 * Returns the maximum number of lines that will fit in a given height.
 * @param height height to fit lines into (pixels)
 */
protected int
maxLines(int height) {
	if (!multiline || scrolled_window == null)
		return (int) Math.ceil((double) height/fm.getHeight());
	return height / fm.getHeight();
}

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

	if (width <= 0 || height <= 0) return;

	synchronized (this) {
		adjustInsets();
		visible_rows = maxLines(getDrawingArea().height);
		bottom_row = Math.max(top_row, Math.min(top_row+visible_rows-1, countRows()-1));
	}
	endAction();

	// MS-Windows bug
	repaint();
}

public void addNotify() {
	super.addNotify();
	buildLineTable();
}

/**
 * Adjusts the bottom inset so a partial line is not displayed.
 */
void adjustInsets() {
	if (!isDisplayable()) return;
	insets.bottom = original_bottom_margin;
	visible_rows = maxLines(getDrawingArea().height);
	insets.bottom += getDrawingArea().height - visible_rows*fm.getHeight();
}

protected void updateScrollbars() {
	if (update_scrollbars && scrolled_window != null)
		scrolled_window.layout();
}

/** Scans the text and adds it to the line table. */
protected void buildLineTable() {
	int num_lines = BWTUtil.countChar(text, num_char, '\n', 0, num_char);

	synchronized (this) {
		if (lines == null)
			lines = new JCVector(num_lines);
		else {
			lines.removeAllElements();
			lines.ensureCapacity(num_lines);
		}
	}

	synchronized (lines) {
		int start = 0, pos, row = 0;
		while ((pos = BWTUtil.indexOf(text, num_char, '\n', start)) != -1) {
			lines.addElement(new TextLine(this, row++, text, start, pos-start));
			start = pos + 1;
		}
		lines.addElement(new TextLine(this, row, text, start, num_char-start));
	}

	findWidestRow();
}

/**
 * Updates the internal text buffer and the line table entry
 * @see JCTextComponent#replaceTextInternal
 */
protected void replaceTextInternal(String str, int start, int end) {
	boolean end_action = false;
	int start_row = 0, end_row = 0;
	int num_lines = 0;
	int first = 0;

	if (TRACE) {
		System.out.println("JCTextAreaComponent.replaceTextInternal(\""
						   + str + "\", " + start + ", " + end + ")");
	}
	synchronized (this) {
	synchronized (lines) {
		// set do_repaint flag to false to prevent the following call to
		// the super method from doing a repaint
		do_repaint = false;
		// Calling super method takes care of deleting old text and inserting
		// the new text comtained in "str"
		super.replaceTextInternal(str, start, end);
		do_repaint = true;
		// This checks to see that the peer exists to determine if we need
		// to the rest of the stuff or not.  Note that this will not be
		// compatible with "peerless model"
		if (!isDisplayable()) return;

		// remove cursor
		startAction(null);

		//
		// Now we need to map the changes into the multiline model we use
		// for efficient display
		//

		start_row = positionToRow(start);
		end_row = positionToRow(end);
		num_lines = BWTUtil.countChar(str, '\n', 0, str.length()) + 1;
		first = getFirstPosition(start_row);

		// Simple case - replacement of one line of text in a single row
		if (end_row == start_row && num_lines == 1) {
			if (TRACE) {
				System.out.println("\thandling simple 1 line case");
			}
			int prev_width = getWidth(start_row);
			getLine(start_row).replace(str, start - first, end - first);

			if ((start_row == widest_row && getWidth(start_row) < prev_width)
				|| (start_row != widest_row && getWidth(start_row) > prev_width))
				checkWidestRow(start_row);
			end_action = true;
		}
	}
	}

	if (end_action) {
		endAction();
		return;
	}

	synchronized (this) {
		// Remove lines between start and end rows
		if (end > start) {
			if (TRACE) {
				System.out.println("\tremoving text");
			}
			String start_text = getText(start_row).substring(0, start-first),
				end_text = getText(end_row).substring(end - getFirstPosition(end_row));
			removeLines(start_row+1, end_row);
			getLine(start_row).setText(start_text+end_text);
			updateLineTable(start_row);
		}

		if (num_lines == 1 || str.length() == 0) {
			getLine(start_row).insert(str, start);
			findWidestRow();
			end_action = true;
		}
	}

	if (end_action) {
		endAction();
		return;
	}


	synchronized (this) {
		// Insert text
		lines.ensureCapacity(lines.size() + num_lines);
		int pos1 = 0,   // position of start of line in inserted text
			pos,        // position end of line in inserted text
			row; // current row of insertion

		boolean move_endstuff = false;
		for (row = start_row; (pos = str.indexOf('\n', pos1)) != -1; row++) {
			String s = str.substring(pos1, pos);
			if (TRACE) {
				System.out.print("\tadding line at row = " + row);
			}

			if (row == start_row) {
				//
				// First Row case
				//
				String s1 = null;
				if ((start - first) < getLine(row).num_char) {
					//
					// Set flag to move the existing text past the
					// newline to a new line
					//
					move_endstuff = true;
				}

				TextLine line = getLine(row);
				if (move_endstuff) {
					s1 = getText(row).substring(start - first);
					line.deleteToEnd(start - first);
				}

				// insert the first line into the current row
				if (s != null && s.length() > 0) {
					line.insert(s, start - first);
				}

				if (move_endstuff) {
					insertLine(new TextLine(this, row + 1, s1), row + 1);
				}
				else {
					// add newline
					insertLine(new TextLine(this, row, null), row + 1);
				}

				if (TRACE) {
					System.out.println(", string = \"" + s + "\"; move_endstuff = " + move_endstuff);
				}
			}
			else {
				//
				// No existing text after the insertion point; so just
				// tack the new stuff on the en
				//
				if (TRACE) {
					System.out.println("., string = \"" + s + "\"; case 2");
				}
				if (s != null && s.length() > 0 && move_endstuff == false) {
					getLine(row).insert(s, 0);
					// add a new line
					insertLine(new TextLine(this, row, null), row + 1);
				}
				else if (move_endstuff) {
					// add a new line
					insertLine(new TextLine(this, row, s), row);
				}
				else {
					// add a new line
					insertLine(new TextLine(this, row, null), row + 1);
				}
			}
			pos1 = pos + 1;
		}

		if (row < lines.size()) {
			if (TRACE) {
				System.out.println("\tadding text = \"" + str.substring(pos1) + "\"");
			}
			getLine(row).insert(str.substring(pos1), 0);
		}
		updateLineTable(start_row);
		findWidestRow();
	}

	if (TRACE) {
		System.out.println("\tCurrent table:");
		// dump lines to see what the current internal state is
		for (int i = 0; i < lines.size(); i++) {
			TextLine line = (TextLine)lines.elementAt(i);
			System.out.println("\t\t" + line);
		}
	}

	// Repaint the component from the start position to the bottom of the
	// component since in the text area case and change chould change every
	// thing unde rthe current position
	if (do_repaint && isDisplayable()) {
		Point p = positionToPoint(start, null);
		if (alignment == LEFT) {
			// do the smart thing and redraw from the start line to
			// the bottom
			paintImmediately(0, p.y, size().width, size().height - p.y);
		}
		else {
			// do the dumb thing and repaint everything
			paintImmediately(0, 0, size().width, size().height);
		}
	}

	endAction();
}

/** Updates the lines from the start to the last row. */
void updateLineTable(int row) {
	synchronized (lines) {
		updateLineTable(row, lines.size() - 1);
	}
}

/** Updates the lines between 2 rows. */
void updateLineTable(int start_row, int end_row) {
	int start_pos = 0;

	synchronized (lines) {
		if (start_row > 0) {
			TextLine line = (TextLine) lines.elementAt(start_row-1);
			start_pos = line.start_pos + line.num_char + 1;
		}

		for (int row = start_row; row <= end_row; row++) {
			TextLine line = (TextLine) lines.elementAt(row);
			line.row = row;
			line.setStartPos(start_pos);
			start_pos += line.num_char + 1;
		}
	}
}

/** Removes the lines in row range [start, end] */
void removeLines(int start, int end) {
	synchronized (lines) {
		for (int i= Math.min(lines.size()-1, end); i >= start; i--)
			lines.removeElementAt(i);
	}
}

/** Inserts a line prior to 'row'. */
void insertLine(TextLine line, int row) {
	synchronized (lines) {
		if (row <= lines.size()) {
			lines.insertElementAt(line, row);
		}
		else {
			lines.addElement(line);
		}
		updateLineTable(row);
	}
}

/** Gets the x co-ordinate for a character position, ignoring scrolling. */
public int positionToX(int pos) {
	if (!isDisplayable()) return 0;
	getDrawingArea(rect);
	int offset = 0;
	TextLine line = getLine(positionToRow(pos));

	if (line == null) return 0;

	switch (alignment) {
	case BWTEnum.RIGHT:
		return rect.x + rect.width - line.getWidthToEnd(pos);
	case BWTEnum.CENTER:
		offset = (rect.width - line.getWidth()) / 2;
	}
	return rect.x + line.getWidth(pos) + offset;
}

/** Gets the y co-ordinate for a character position, ignoring scrolling. */
public int positionToY(int pos) {
	if (!isDisplayable()) return 0;
	getDrawingArea(rect);
	return rect.y + getRowPosition(positionToRow(pos));
}

/**
 * Scrolls the text if necessary to ensure that the position is visible.
 * @param pos the number of characters from the beginning of the text buffer.
 */
public void showPosition(int pos) {
	if (!isDisplayable() || bottom_row == BWTEnum.NOVALUE) return;
	getDrawingArea(rect);
	int x = positionToX(pos), origin = horiz_origin;

	if (x < horiz_origin + rect.x)
		origin = x - rect.x - 2;
	else if (x > horiz_origin + rect.x + rect.width)
		origin = x - (rect.x + rect.width) + 5;

	if (origin != horiz_origin) {
		if (scrolled_window != null)
			scrolled_window.scrollHorizontal(origin);
		else {
			horiz_origin = origin;
			repaint();
		}
	}

	int row = positionToRow(pos);
	if (row < top_row)
		origin = getRowPosition(row);
	else if (row > bottom_row)
		origin = vert_origin + getRowPosition(row+1) - getRowPosition(bottom_row);
	else
		return;

	if (scrolled_window != null)
		scrolled_window.scrollVertical(origin);
	else {
		vert_origin = origin;
		repaint();
	}
}

/** Gets the height of a row. */
protected int getRowHeight(int row) { return fm.getHeight(); }

/** Gets the y position of the row, neglecting shadows and scrolling. */
protected int getRowPosition(int row) {
	row = Math.max(0, Math.min(row, lines.size()-1));
	return row * fm.getHeight();
}

/** Finds the row specified by the point.
 * @return BWTEnum.NOTFOUND if the text area has not been added to a container
 */
public int yToRow(int y) {
	int row = 0;
	int num_lines = 0;

	if (!isDisplayable())
		return BWTEnum.NOTFOUND;

	synchronized (lines) {
		num_lines = lines.size();

		getDrawingArea(draw_rect);
		int new_y = y + vert_origin - draw_rect.y;

		if (y > draw_rect.y + draw_rect.height) {
			for (row=bottom_row; row <= num_lines; row++) {
				if (new_y < getRowPosition(row))
					break;
			}
			row--;
		}

		else if (y < draw_rect.y) {
			for (row=top_row; row >= 0; row--)
				if (new_y > getRowPosition(row))
					break;
			if (row < 0) row = 0;
		}

		else if (top_row == bottom_row)
			return top_row;

		else {
			for (row=top_row+1; row <= Math.min(num_lines, bottom_row+1); row++) {
				if (new_y < getRowPosition(row))
					break;
				if (row == bottom_row && row == num_lines-1)
					return row;
			}
			row--;
		}
	}
	return Math.min(Math.max(0, row), num_lines-1);
}

/* JDK110_START */
protected void paintComponent(Graphics gc, int horizontal_offset,
							  String text, Rectangle rect,
							  int alignment, FontMetrics fm, boolean enabled,
							  Color foreground, Color background,
							  Color selected_foreground,
							  Color selected_background,
							  int select_start, int select_end) {}

	//
	// support function for the above initialize() method to be implemented
	// in the sub-class
	//
public void setCursorPosition(jclass.cell.InitialEvent ev,
								 jclass.cell.CellInfo info, String s) {}

	//
	// define preferredSize for jclass.cell.CellRenderer interface
	//

public Dimension getPreferredSize(jclass.cell.CellInfo cellInfo, Object o) {
	// CellEditor/Renderer not currently supported in TextArea
	return null;
}

	//
	// Defines traversal keys for that we use that the cell editor should not
	// override
	//

KeyModifier [] key_modifiers = {
	new KeyModifier(KeyEvent.VK_RIGHT, KeyModifier.ALL),
	new KeyModifier(KeyEvent.VK_LEFT,  KeyModifier.ALL),
	new KeyModifier(KeyEvent.VK_DOWN, KeyModifier.ALL),
	new KeyModifier(KeyEvent.VK_UP,  KeyModifier.ALL),
	new KeyModifier(KeyEvent.VK_ENTER,  KeyModifier.ALL)
};

public KeyModifier[] getReservedKeys() {
	return key_modifiers;
}
/* JDK110_END */

/**
 * Draws the text
 */
protected synchronized void paintComponent(Graphics gc) {
	int pos = 0;
	int top_r = 0, bottom_r = 0;
	Color old_color = null;

		if (lines.size() == 0)
			return;
		top_r = top_row = Math.max(0, Math.min(top_row, countRows()-1));
		if (vert_origin == BWTEnum.NOVALUE)
			vert_origin = getRowPosition(top_row);
		top_char = getFirstPosition(top_row);
		pos = top_char;
		bottom_r = bottom_row = Math.max(top_row, Math.min(top_row+visible_rows-1, countRows()-1));

		if (!isEnabled()) {
			old_color = gc.getColor();
			gc.setColor(Color.lightGray.darker().darker());
		}

	for (int row=top_r; row <= bottom_r; row++) {
		TextLine line = getLine(row);
		line.setStartPos(pos);
		pos += line.num_char + 1;
		line.paint(gc);
	}

	if (!isEnabled())
		gc.setColor(old_color);
}

/** Gets the bounding rectangle of a row, or null if the row is invalid.
 * @param adjust if true, the co-ordinates are adjusted by the amount
 * that the field has been scrolled.
 */
public Rectangle getBounds(int row, boolean adjust) {
	synchronized (lines) {
		if (row < 0 || row >= lines.size()) return null;
		getDrawingArea(draw_rect);
		JCComponent.setBounds(draw_rect,
							  draw_rect.x - (adjust ? horiz_origin : 0),
							  draw_rect.y + getRowPosition(row) -
							  (adjust ? vert_origin : 0),
							  Math.max(draw_rect.width, preferredWidth()),
							  getRowHeight(row));
		return draw_rect;
	}
}

/** Draws the text between 2 points. */
protected void repaintPositions(Point p1, Point p2) {
	if (p1.equals(p2)) return;
	p1.y -= vert_origin;
	p2.y -= vert_origin;
	if (p1.y == p2.y) {
		super.repaintPositions(p1, p2);
		return;
	}
	int row1 = yToRow(p1.y), row2 = yToRow(p2.y);
	int start = 0, end = 0, top_r = 0, bottom_r = 0;

	synchronized (this) {
		start = Math.min(row1, row2);
		end = Math.max(row1, row2);
		if (end < top_row || start > bottom_row) return;
		top_r = top_row;
		bottom_r = bottom_row;
	}

	for (int row = Math.max(top_r, start);
		 row <= Math.min(bottom_r, end); row++) {
		Rectangle rect = getBounds(row, true);
		repaint(rect.x, rect.y, rect.width, rect.height);
	}
}

/**
 * Returns the number of characters in a row.
 */
protected int getNumChar(int row) {
	int num_char = 0;
	synchronized (lines) {
		num_char = (row >= 0 && row < lines.size()) ?
			((TextLine) lines.elementAt(row)).num_char : 0;
	}
	return num_char;
}

/**
 * Returns the width of a row (pixels).
 */
protected int getWidth(int row) {
	int width = 0;
	synchronized (lines) {
		width = ((TextLine) lines.elementAt(row)).width;
	}
	return width;
}

/** Gets the number of visible rows. */
public int getVisibleRows() { return visible_rows; }

/** Gets the text for a row, or null if the row is invalid. */
public String getText(int row) {
	TextLine line = null;
	synchronized (lines) {
		if (row < 0 || row >= lines.size()) return null;
		line = (TextLine) lines.elementAt(row);
	}
	return new String(line.text, 0, line.num_char);
}

/** Gets the line for the row. */
protected TextLine getLine(int row) {
	TextLine line;
	synchronized (lines) {
		line = (row >= 0 && row < lines.size()) ? (TextLine)lines.elementAt(row) : null;
	}
	return line;
}

/** Gets the text position of the first char in the row. */
protected int getFirstPosition(int row) {
	int pos = 0;
	synchronized (lines) {
		for (int i = 0; i < row; i++) {
			TextLine line = (TextLine) lines.elementAt(i);
			line.setStartPos(pos);
			pos += line.num_char + 1;		// add newline
		}
	}
	return pos;
}

/** Gets the row that contains the text position. */
protected int positionToRow(int pos) {
	int total = 0, row = 0;
	int position = 0;
	if (pos >= top_char) {
		total = top_char;
		row = top_row;
	}
	synchronized (lines) {
		for (; row < lines.size(); row++) {
			if (pos < total) return row-1;
			total += getNumChar(row) + 1;		// add newline
		}
		position = lines.size()-1;
	}
	return position;
}

/** Gets the width of the widest row (pixels). */
protected int getWidestRow() {
	if (widest_row == BWTEnum.NOVALUE)
		findWidestRow();
	if (widest_row == BWTEnum.NOVALUE) return 0;
	TextLine line = getLine(widest_row);
	return (line != null) ? line.width : 0;
}

/** Checks whether the specified row is the widest. */
protected void checkWidestRow(int row) {
	TextLine l1, l2;
	if (row == widest_row)
		findWidestRow();
	else if (widest_row == BWTEnum.NOVALUE)
		widest_row = row;
	else if ((l1 = getLine(row)) != null && (l2 = getLine(row)) != null
			 && l1.width > l2.width)
		widest_row = row;
	else
		findWidestRow();
}

/** Finds the width of the widest row (pixels). */
protected void findWidestRow() {
	int widest = 0;

	synchronized (lines) {
		for (int i=0; i < lines.size(); i++) {
			int w = ((TextLine) lines.elementAt(i)).width;
			if (w > widest) {
				widest_row = i;
				widest = w;
			}
		}
	}

}

protected int countRows() { return lines.size(); }
protected int getCurrentRow() { return positionToRow(cursor_pos); }
protected int getCurrentFirstPosition() {
	return getFirstPosition(getCurrentRow());
}

/**
 * Maps a point to a text position.
 */
public int pointToPosition(int x, int y) {
	int row = yToRow(y);
	TextLine line = getLine(row);
	if (row == BWTEnum.NOTFOUND || line == null) return 0;
	return line.xToPosition(x);
}

/** Scrolls to the end of the current line. */
protected void scrollLineEnd(Event ev) {
	TextLine line = getLine(getCurrentRow());
	if (line != null) {
		selectCursorPos(ev, getFirstPosition(getCurrentRow()) + line.num_char);
	}
}

/** Scrolls to the beginning of the current line. */
protected void scrollLineBegin(Event ev) {
	selectCursorPos(ev, getFirstPosition(getCurrentRow()));
}

/** Sets the cursor to the specified position and selects the text if the
 * Shift key was pressed.
 */
void selectCursorPos(Event ev, int pos) {
	startAction(ev);
	if (ev.shiftDown()) {
		int start = select_start, end = select_end;
		if (cursor_pos < end) {
			start = Math.min(num_char, pos);
			setCursorPosition(start);
		}
		else {
			end = Math.min(num_char, pos);
			setCursorPosition(end);
		}
		select(start, end, 0);
	}
	else {
		select(pos);
	}
	endAction();
}

/** Moves the cursor by a specified number of lines.
 * @param lines if +ve, moves cursor down; if -ve, moves cursor up
 */
private void moveCursor(Event ev, int num_lines, boolean adjust_toprow) {
	int cursor_row = getCurrentRow();
	int new_cursor_row = Math.min(lines.size()-1,
								  Math.max(0, cursor_row+num_lines));
	if (new_cursor_row == cursor_row) return;
	int old_toprow = top_row;

	// Move cursor to the next line with the same horizontal offset
	TextLine next_line = getLine(new_cursor_row);
	next_line.setStartPos(getFirstPosition(new_cursor_row));
	int offset = Math.min(next_line.num_char,
						  cursor_pos - getLine(cursor_row).start_pos);
	selectCursorPos(ev, next_line.start_pos + offset);
	if (adjust_toprow)
		setTopRow(old_toprow + num_lines);
}

/** Moves the cursor to the next line.
 * If the Shift key is pressed, the selection is extended.
 */
protected void moveNextLine(Event ev) { moveCursor(ev, 1, false); }

/** Moves the cursor to the previous line.
 * If the Shift key is pressed, the selection is extended.
 */
protected void movePreviousLine(Event ev) { moveCursor(ev, -1, false); }

/** Scrolls down vertically by a page. */
protected void scrollNextPage(Event ev) { moveCursor(ev, visible_rows-1, true); }

/** Scrolls up vertically by a page. */
protected void scrollPreviousPage(Event ev) { moveCursor(ev, -visible_rows+1, true); }

/** Inserts a newline.
 * @return false if the insertion was disallowed
 */
protected boolean insertNewLine(Event ev) {
	if (!editable || !multiline) return false;
	startAction(ev);
	insert("\n", cursor_pos);
	endAction();
	changed = true;
	return true;
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
PAGE-UP     scrollPreviousPage
PAGE-DOWN   scrollNextPage
UP          movePreviousLine
DOWN        moveNextLine
RIGHT       moveForwardChar
LEFT        moveBackwardChar
BS          deletePreviousChar
DELETE      deleteCurrentChar
ENTER       insertNewLine
any other   insertChar
</pre>
*/
public boolean
keyDown(Event ev, int key) {
	boolean no_error = true, handled = true;

	if (key == Event.UP) movePreviousLine(ev);
	else if (key == Event.DOWN) moveNextLine(ev);
	else if (key == Event.PGUP) scrollPreviousPage(ev);
	else if (key == Event.PGDN) scrollNextPage(ev);
	else if (key == BWTEnum.ENTER) no_error = insertNewLine(ev);
	else
		handled = false;

	if (!no_error)
		beep();
	return handled ? true : super.keyDown(ev, key);
}

/***********************************
 * JCScrollableInterface methods
 ***********************************/

/** Gets the horizontal origin. */
public int getHorizOrigin() { return horiz_origin; }

/** Sets the horizontal origin prior to scrolling. */
public void setHorizOrigin(int v) { horiz_origin = v; }

/** Gets the vertical origin. */
public int getVertOrigin() { return vert_origin; }

/** Sets the top row prior to scrolling. */
public void setVertOrigin(int value) {
	vert_origin = Math.max(0, value);
	synchronized (lines) {
		for (top_row = 0; top_row < lines.size(); top_row++) {
			if (getRowPosition(top_row) + getRowHeight(top_row) > value)
				break;
		}
	}
}

}
