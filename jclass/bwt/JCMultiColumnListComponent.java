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
// RCSID -- $RCSfile: JCMultiColumnListComponent.java $ $Revision: 2.13 $ $Date: 2000/11/09 20:10:45 $ $Locker: $  Sitraka Inc.

package jclass.bwt;
import jclass.util.*;
import java.util.*;
import java.awt.*;
import java.applet.*;
/* SWING11_START 
import com.sun.java.swing.border.Border;
 SWING11_END */
/* SWING12_START */
import javax.swing.border.Border;
/* SWING12_END */

/**
 * A list of text and/or image items. To create a scrollable list, use a
 * JCMultiColumnList.
 * @see JCMultiColumnList
 */
public class JCMultiColumnListComponent extends JCListComponent
    implements JCMultiColumnInterface {

/** The object which maintains the multi-column data. */
protected JCMultiColumnData	data = new JCMultiColumnData(this);

boolean num_columns_set = false;	// Has setNumColumns() been called?
int largest_size = 0;				// Size of longest item
    
private static final String base = "multicolumnlist";
private static int nameCounter = 0;

/** Creates an empty list with no visible rows.
 *  No parameters are read from an HTML file.
 */
public JCMultiColumnListComponent() {
	this(null, null, null);
}

/** Creates a list with the specified items.
 * No parameters are read from an HTML file.
 * @see JCListComponent#setItems
 */
public JCMultiColumnListComponent(JCVector items) {
	this(items, null, null);
}

/** Creates a list which reads parameters from the applet's HTML file.
 * @param items the initial items.
 * Each element may be a String, JCString or any object (in which case the object's
 * toString() method is called to obtain a string).
 * @param applet the applet whose PARAM tags are to be read
 * @param name if this is not null, only parameters preceded by this name are read
 * @see JCListComponent#setItems
 * @see java.applet.Applet#getParameter
 * @see jclass.util.JCString
 */
public JCMultiColumnListComponent(JCVector items, Applet applet, String name) {
	super(items, applet, name);
	if (name == null)
		setName(base + nameCounter++);
	border = highlight = spacing = 1;
	insets.left = insets.right = 0;
	if (getClass().getName().equals("jclass.bwt.JCMultiColumnListComponent"))
		 getParameters(applet);
	if (items != null)
		setItems(items);
}

/**
 * Creates a list with the specified number of visible lines.
 * @param rows the number of items to show.
 * @param multipleSelections if true then multiple selections are allowed.
 */
public JCMultiColumnListComponent(int rows, boolean multipleSelections) {
	super(rows, multipleSelections);
}

/** Reads the parameter values from the HTML page using the component's applet.
 * The values will override those previously set.
 */
protected void getParameters() {
	super.getParameters();
	MultiColumnConverter.getParams(this, conv);
}

/** Overridden to prevent a border being drawn */
/* SWING_START */
public void setBorder(Border border) {
}
/* SWING_END */

/**
 * Sets the font of the component.
 * <strong>HTML param name/value:</strong> "Font"/font<p>
 * @see jclass.util.JCConverter#toFont
 */
public void setFont(Font f) {
	super.setFont(f);
	if (!isDisplayable()) return;
	calcColumnWidths();
	calcSize(false);
	if (scrolled_window == null)
		repaint();
}

/** Sets the list's values as an array of Strings. The value for each column is
 * separated by commas.
 */
public void setItems(String[] items) {
	JCVector v = new JCVector(items);
	for (int i=0; i < v.size(); i++) 
		v.setElementAt(i, conv.toVector(this, items[i], ',', true));
	setItems(v);
}

/** Gets the list's values as a list of Strings. The value for each column is
 * separated by commas.
 * @see #setItems
 */
public synchronized String[] getItemsStrings() {
	String[] list = new String[items.size()];
	for (int i=0; i < list.length; i++) 
		list[i] = JCUtilConverter.toString(items.elementAt(i));
	return list;
}

void calcColumnWidths() {
	if (batched) 
		needs_recalc = true;
	else 
		data.calcColumnWidths();
}

/** Gets the parent's header. */
JCHeader getHeader() {
	return (scrolled_window != null
			&& scrolled_window instanceof JCMultiColumnWindow) ?
		((JCMultiColumnWindow)scrolled_window).getHeader() : null;
}

JCMultiColumnWindow getScrolledWindow() {
	return (scrolled_window != null
			&& scrolled_window instanceof JCMultiColumnWindow) ?
		(JCMultiColumnWindow)scrolled_window : null;
}

/** If the list is a child of a JCMultiColumnList, 
 * sets a header for the window with the specified labels.
 * @see JCMultiColumnWindow#setColumnLabels
 */
public void setColumnLabels(JCVector labels) {
	if (getScrolledWindow() != null)
		getScrolledWindow().setColumnLabels(labels);
}

/** If the list is a child of a JCMultiColumnList, 
 * sets a header for the window with buttons with the specified labels.
 * @see JCMultiColumnWindow#setColumnButtons
 */
public void setColumnButtons(JCVector labels) {
	if (getScrolledWindow() != null)
		getScrolledWindow().setColumnButtons(labels);
}

/** If false (default), the component is repainted every time an item
 * is changed. If set to true, the component is only repainted (if necessary)
 * when the value is again set to false.
 */
public void setBatched(boolean v) {
	if (batched == v) return;
	batched = v;
	if (getHeader() != null)
		getHeader().batched = v;
	if (batched) return;
	if (needs_recalc) {
		calcColumnWidths();
		calcSize(true);
		if (getHeader() != null) 
			getHeader().recalc();
	}
	if (needs_recalc || needs_repaint)
		repaint();
	needs_recalc = needs_repaint = false;
}

/** Recalculates the list's internal sizes based on the row. 
 * @param new_row whether this is a new item
 */
protected void calcSize(int row, boolean new_row) {
	boolean local_batched;
	int local_largest_size;

	synchronized (this) {
		pref_width_internal = 0;
		calcColumnWidths();
		if (!num_columns_set && row>=0 && row<items.size()) {
			Object obj = items.elementAt(row);
			if (obj instanceof Vector && !BWTUtil.is_jcstring(obj))
				largest_size = Math.max(largest_size, ((Vector)obj).size());
		}
		local_largest_size = largest_size;
	}

	if (getNumColumns() < local_largest_size) {
		data.setNumColumns(local_largest_size);	
		if (getHeader() != null)
			getHeader().setNumColumns(local_largest_size);
	}

	synchronized (this) {
		local_batched = batched;
		if (batched) 
			needs_recalc = true;
	}

	if (!local_batched) {
		super.calcSize(row, new_row);
		if (isDisplayable()) {
			calcColumnWidths();
			calcSize(true);
			if (getHeader() != null) 
				getHeader().recalc();
		}
	}
}

/**
 * Parses a string and adds the specified item to the end of the list.
 * @param item the item to be added. A JCVector is created using the tokens 
 * in the string.
 * @param delim delimiter separating each token in the string
 * @see jclass.util.JCConverter#toVector
 */
public void addItem(String item, char delim) {
	addItem(conv.toVector(this, item, delim, true));
}

/**
 * Parses a string and adds the specified item to the end of the list.
 * @param applet the applet to be used for loading images contained in any
 * JCStrings in the vector
 * @param item the item to be added. A JCVector is created using the tokens 
 * in the string.
 * @param delim delimiter separating each token in the string
 * @see jclass.util.JCString
 */
public void addItem(Applet applet, String item, char delim) {
	addItem(conv.toVector(applet, item, delim, true));
}

/** Draws the value as a String, JCString or Image.
 * @param alignment one of:
 * BWTEnum.TOPLEFT, TOPCENTER, TOPRIGHT, MIDDLELEFT, MIDDLECENTER, MIDDLERIGHT, 
 * BOTTOMLEFT, BOTTOMCENTER and BOTTOMRIGHT
 * @param draw_rect rectangle within which to draw the value
 */
protected void
draw(Graphics gc, Object value, int alignment, Rectangle draw_rect) {
	data.draw(gc, value, draw_rect);
}

/** Draws or clears the highlight rectangle (called by paint).
 * @param on if true, draws the highlight rectangle; otherwise clears the rect
 * If false, draws a 1-pixel rectangle 
 */
protected void drawHighlight(Graphics gc, boolean on) {
	super.drawHighlight(gc, on);

	// If clearing, draw 1-pixel rect in highlight color
	if (on) return;
	gc.setColor(highlight_color != null ? highlight_color : Color.black);
	gc.drawRect(highlight-1, highlight-1, 
				size().width-2*highlight+1, size().height-2*highlight+1);
}

/** If NumColumns has not been set, it is set to the largest number of items 
 * in all items.
 */
public void addNotify() {
	super.addNotify();
	if (!num_columns_set) {
		for (int i=0, len = items.size(); i < len; i++) {
			Object obj = items.elementAt(i);
			if (obj instanceof Vector && !BWTUtil.is_jcstring(obj))
				largest_size = Math.max(largest_size, ((Vector)obj).size());
		}
		if (getNumColumns() < largest_size) {
			data.setNumColumns(largest_size);
			if (getHeader() != null)
				getHeader().data.setNumColumns(largest_size);
		}
	}
	calcColumnWidths();
	pref_width_internal = data.preferredWidth();
}

protected int preferredWidth() { 
	if (pref_width_internal == 0) {
		calcColumnWidths();
		if (getHeader() != null) 
			getHeader().recalc();
	}
	return (pref_width_internal = data.preferredWidth());
}

/**
 * Sorts the rows in the list in ascending order based on a specified column,
 * using the String equivalents of each value.
 * @param sort_if An optional interface whose compare method is called during 
 * sorting, to provide a mechanism for the application to specify whether one 
 * object is greater than another (similar to qsort's compare function). 
 * If this parameter is null, the column is sorted by numerical or string 
 * comparison as applicable.
 */
public void sortByColumn(int column, JCSortInterface sort_if) {
	sortByColumn(column, sort_if, JCqsort.ASCENDING);
}

/**
 * Sorts the rows in the list based on a specified column,
 * using the String equivalents of each value.
 * @param sort_if An optional interface whose compare method is called during
 * sorting, to provide a mechanism for the application to specify whether one
 * object is greater than another (similar to qsort's compare function).
 * If this parameter is null, the column is sorted by numerical or string
 * comparison as applicable.
 * @param direction sort direction: JCqsort.ASCENDING or DESCENDING
 */
public void sortByColumn(int column, JCSortInterface sort_if, int direction) {
	int rows[] = new int[0];
	int items_size = 0;
	synchronized (this) {
		items_size = items.size();
		int[] new_rows = (new JCqsort(items, sort_if)).sort(column, direction);
		if (new_rows == null) return;

		if (focus_row != -1) {
			// make sure the focus row gets moved as well
			focus_row = new_rows[focus_row];
		}

		// Create an inverse list
		rows = new int[new_rows.length];
		for (int i=0; i < new_rows.length; i++)
			rows[new_rows[i]] = i;

		if (userdata_list != null) {
			updateUserdataList();
			Object list[] = new Object[items_size];
			for (int i=0; i < items_size; i++) 
				list[i] = userdata_list[rows[i]];
			userdata_list = list;
		}

		if (selected != null && selected.length >= items_size) {
			boolean list[] = new boolean[items_size];
			for (int i=0; i < items_size; i++) {
				list[i] = selected[rows[i]];
				last_selected[i] = false;
			}
			selected = list;
		}
	}

	if (row_height_ext != BWTEnum.VARIABLE) {
		repaint();
		return;
	}

	synchronized (this) {
		if (row_heights.length == items_size) {
			int heights[] = new int[items_size];
			for (int i=0; i < items_size; i++) {
				heights[i] = row_heights[rows[i]];
			}
			row_heights = heights;

			for (int i=1; i < items_size; i++)
				row_pos[i] = row_pos[i-1] + row_heights[i-1];
		}
	}
		
	if (focus_row != -1) {
		// make sure the focus row is visible
		makeVisible(focus_row);
	}
	repaint();
}

/***********************************
 * JCMultiColumnInterface methods
 ***********************************/

/** Calculates the width of a column for all rows. */
public int calcWidth(int col) {
	int w = 0;
	for (int i=0; i < items.size(); i++) {
		Object obj = null, item = items.elementAt(i);
		if (item instanceof Vector && !BWTUtil.is_jcstring(obj)) {
			if (col < ((Vector)item).size())
				obj = ((Vector)item).elementAt(col);
		}
		else
			obj = item;
		w = Math.max(w, BWTUtil.getWidth(obj, this));
	}
	return w + getColumnLeftMargin(col) + getColumnRightMargin(col);
}

/** Gets a list of the column widths.
 * @see #setColumnWidths
 */
public int[] getColumnWidths() { return data.getColumnWidths(); }

/** Sets the list of column widths. If a column's value is set to 
 * BWTEnum.VARIABLE, the width is set to the widest value in the column.<p>
 * This value may be set from an HTML file using a PARAM tag with a "ColumnWidths"
 * name and a comma-separated list of ints.
 */
public void setColumnWidths(int[] widths) {
	data.setColumnWidths(widths);
	repaint();
}

/** Gets the current width of a column, or 0 if the column does not exist.
 */
public int getColumnWidth(int col) { return data.getColumnWidth(col); }

/** Sets the width of a column. If the value is set to 
 * BWTEnum.VARIABLE, the width is set to the widest value in the column.
 */
public void setColumnWidth(int col, int width) {
	data.setColumnWidth(col, width);
	if (getHeader() != null) 
		getHeader().setColumnWidth(col, width);
	updateParent();
	repaint();
}

/** Gets the current number of columns. */
public int getNumColumns() { return data.num_columns; }

/** Sets the current number of columns. 
 * If set to BWTEnum.VARIABLE (default), all columns are displayed.<p>
 * <strong>HTML param name/value:</strong> "NumColumns"/int
 */
public void setNumColumns(int v) {
	data.setNumColumns(v != BWTEnum.VARIABLE ? v : largest_size);
	num_columns_set = (v != BWTEnum.VARIABLE);
	calcColumnWidths();
	repaint();
}

/** Gets the column alignments. */
public int[] getColumnAlignments() { return data.getColumnAlignments(); }

/** Gets a column's alignment (default: BWTEnum.MIDDLELEFT). */
public int getColumnAlignment(int col) { return data.getColumnAlignment(col); }

/** Sets a column's alignment.
 * @param alignment one of:
 BWTEnum.TOPLEFT, TOPCENTER, TOPRIGHT, MIDDLELEFT, MIDDLECENTER, MIDDLERIGHT, BOTTOMLEFT, BOTTOMCENTER and BOTTOMRIGHT
 */
public void setColumnAlignment(int col, int align) {
	data.setColumnAlignment(col, align);
	repaint();
}

/** Sets the column's alignments.<p>
 * This value may be set from an HTML file using a PARAM tag with a "ColumnAlignments"
 * name and a comma-separated list of enums.
 * @param align one of:
 * BWTEnum.TOPLEFT, TOPCENTER, TOPRIGHT, MIDDLELEFT, MIDDLECENTER, MIDDLERIGHT, 
 * BOTTOMLEFT, BOTTOMCENTER and BOTTOMRIGHT
 */
public void setColumnAlignments(int[] align) {
	data.setColumnAlignments(align);
	repaint();
}

/** Gets the physical position of the left boundary of the column 
 * (accounting for horizontal scrolling).
 */
public int getColumnPosition(int col) {
	return data.getColumnPosition(col)
		+ border + 2*highlight + insets.left - horiz_origin;
}

/** Gets the column's left margin value (pixels) (default: 5). */
public int getColumnLeftMargin(int col) { 
	return data.getColumnLeftMargin(col); 
}

/** Sets the column's left margin value (pixels) (default: 5). 
 * The values for all columns may be set from an HTML file using a PARAM tag with a "ColumnLeftMargins"
 * name and a comma-separated list of ints.
*/
public void setColumnLeftMargin(int col, int value) {
	data.setColumnLeftMargin(col, value);
	calcColumnWidths();
	repaint();
}

public int getColumnRightMargin(int col) { return data.getColumnRightMargin(col); }

/** Sets the column's right margin value (pixels) (default: 5).<p>
 * The values for all columns may be set from an HTML file using a PARAM tag with a "ColumnRightMargins"
 * name and a comma-separated list of ints.
 */
public void setColumnRightMargin(int col, int value) {
	data.setColumnRightMargin(col, value);
	calcColumnWidths();
	repaint();
}

/** Gets the component's JCMultiColumnData instance. */
public JCMultiColumnData getMultiColumnData() { return data; }

/** Gets a list of the column display widths.
 * @see #setColumnDisplayWidths
 */
public int[] getColumnDisplayWidths() { return data.getColumnDisplayWidths(); }

/** Sets the list of column display widths.  */
public void setColumnDisplayWidths(int[] widths) {
	data.setColumnDisplayWidths(widths);
	repaint();
}

/** Gets the current display width of a column, or 0 if the column does not exist.
 */
public int getColumnDisplayWidth(int col) { return data.getColumnDisplayWidth(col); }

/** Sets the display width of a column.  */
public void setColumnDisplayWidth(int col, int width) {
	data.setColumnDisplayWidth(col, width);
	if (getHeader() != null) 
		getHeader().setColumnDisplayWidth(col, width);
	updateParent();
	repaint();
}

/** Gets column resize policy. 
 * 	@param policy one of: BWTEnum.RESIZE_SINGLE, RESIZE_NONE, RESIZE_COLLAPSE 
 */
public int getColumnResizePolicy() {
	return data.getColumnResizePolicy();
}

/** Sets column resize policy. 
 * 	@param policy one of: BWTEnum.RESIZE_SINGLE, RESIZE_NONE, RESIZE_COLLAPSE 
 */
public void setColumnResizePolicy(int policy) {
	data.setColumnResizePolicy(policy);
}

}
