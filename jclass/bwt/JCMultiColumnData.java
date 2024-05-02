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
// RCSID -- $RCSfile: JCMultiColumnData.java $ $Revision: 2.7 $ $Date: 2000/11/09 20:10:42 $ $Locker: $  Sitraka Inc.

package jclass.bwt;
import java.awt.*;
import java.util.*;

/** A class which maintains the data stored for a multi-column component.
 * @see JCMultiColumnInterface
 * @see JCOutlinerComponent
 * @see JCMultiColumnListComponent
 */
public class JCMultiColumnData implements JCSerializable {

private static final boolean TRACE = false;

/** The component for which data is maintained. */
protected JCMultiColumnInterface multi;

/**
 * The component for which data is maintained, equal to a cast of the
 * multi field.
 */
protected JCComponent comp;

public int[]			column_display_widths = new int[0];
public int 				column_resize_policy = BWTEnum.RESIZE_SINGLE;
public int[]			column_widths = new int[0];
public int[]			column_widths_ext = new int[0];
public int				num_columns = 1;
public int[]			alignments = new int[0];
public int[]			left_margins = new int[0];
public int[]			right_margins = new int[0];

/** Default constructor - no component is associated with the data. */
public JCMultiColumnData() {}

/** Creates an instance and registers a component. */
public JCMultiColumnData(JCMultiColumnInterface multi) {
	this.multi = multi;
	if (multi instanceof JCComponent)
		comp = (JCComponent) multi;
}
	
/**
 * Adjusts the drawing rectangle for the value.
 * @param col value's column
 * @param row_rect rectangle within which to draw the row
 * @param rect rectangle within which to draw the value (read-write)
 * where the rect.width is the column width (which is tweaked) and the
 * rect.x is the start of the drawing area (which is tweaked) and where
 * rect.y and rect.height are already correct and not touched by this
 * routine.
 */
protected void adjustDrawingRect(int col, Rectangle row_rect, Rectangle rect) {
	int left = multi.getColumnLeftMargin(col);
	rect.x = multi.getColumnPosition(col) + left;
	rect.width -= left + multi.getColumnRightMargin(col);
}

Rectangle rect = new Rectangle();

/** Draws the value as a String, Vector, JCString, or Image.
 * @param draw_rect rectangle within which to draw the value;
 * if null, it will be calculated
 */
public synchronized void draw(Graphics gc, Object value, Rectangle draw_rect) {
	if (draw_rect == null) {
		if (comp != null)
			rect = comp.getDrawingArea();
		else if (multi instanceof Component)
			rect = ((Component)multi).bounds();
		else
			return;
	}
	else {
	    JCComponent.setBounds(rect, draw_rect.x, draw_rect.y,
					 draw_rect.width, draw_rect.height);
	}

	Color old_color = null;
	if (comp != null && !comp.isEnabled()) {
		old_color = gc.getColor();
		gc.setColor(Color.lightGray.darker().darker());
	}

	Vector vector = null;
	boolean single_datum;
	if (!(value instanceof Vector) || BWTUtil.is_jcstring(value)) {
		if (comp == null) return;
		single_datum = true;
		/*
		BWTUtil.draw(comp, gc, value, multi.getColumnAlignment(0), draw_rect);
		if (!comp.isEnabled()) 
			gc.setColor(old_color);
		return;
		*/
	}
	else {
		vector = (Vector) value;
		single_datum = false;
	}

	Rectangle clip_rect = null;
	if (comp instanceof JCComponent)
		clip_rect = ((JCComponent)comp).getPaintRect();
	if (clip_rect == null)
		clip_rect = gc.getClipRect();

	for (int col = 0;
		 // we are dealing with a single column
		 (single_datum == true && col < 1)
			 // We are dealing with multiple columns
			 || (vector != null && col < vector.size()); col++) {
		// don't draw more columns than available
		if (col >= num_columns)
			break;

		rect.width = multi.getColumnDisplayWidth(col);
		if (rect.width < 0) break;
		adjustDrawingRect(col, draw_rect, rect);

		// if the area we need to repaint does not intersect
		// drawing area for this column then we don't draw
		if (!clip_rect.intersects(rect)) {
			if (TRACE) {
				System.out.println("Skipping column " + col);
				System.out.println("\tcomparing " + clip_rect);
				System.out.println("\t       to " + rect);
			}
			continue;
		}
		else if (TRACE) {
				System.out.println("Drawing column " + col);
				System.out.println("\tcomparing " + clip_rect);
				System.out.println("\t       to " + rect);
		}

		Graphics new_gc = gc.create();
		new_gc.clipRect(rect.x, rect.y, rect.width, rect.height);
		BWTUtil.draw((Component)multi, new_gc,
					 single_datum ? value : ((Vector)value).elementAt(col),
					 multi.getColumnAlignment(col), rect);
		new_gc.dispose();
	}

	if (!comp.isEnabled() && old_color != null) 
		gc.setColor(old_color);
}

/**
 * Calculates the preferred width of the multicolumn component
 */
public int preferredWidth() {
	// Get total width of columns
	// getColumnPositions() is overloaded to return the preferredWidth if
	// the column count exceeds the number of columns
	int w = getColumnPosition(getNumColumns());
	return(w);
}

/**
 * Determines the widths of variable-sized columns; calls component's
 * calcWidth.
 * @see JCMultiColumnInterface#calcWidth
 */
public void calcColumnWidths() {
	if (!((Component)multi).isDisplayable()) return;
	column_widths_ext =
		BWTUtil.copyList(column_widths_ext, num_columns, BWTEnum.VARIABLE);
	column_widths = BWTUtil.copyList(column_widths, num_columns, BWTEnum.VARIABLE);
	System.arraycopy(column_widths_ext, 0, column_widths, 0, num_columns);
	
	for (int col=0; col < num_columns; col++) {
		if (column_widths_ext[col] == BWTEnum.VARIABLE) 
			column_widths[col] = multi.calcWidth(col);
	}
}

/** Gets a list of the column widths set by setColumnWidths.
 * To get the current width of a column, use getColumnWidth.
 * @see #setColumnWidths
 * @see #getColumnWidth
 */
public int[] getColumnWidths() { return column_widths_ext; }

/** Sets the list of column widths. If a column's value is set to
 * BWTEnum.VARIABLE, the width is set to the widest value in the column.
 */
public void setColumnWidths(int[] widths) {
	column_widths_ext = (widths != null) ? widths : new int[0];
	calcColumnWidths();
}

/** Gets the current width of a column, or 0 if the column does not exist.
 */
public int getColumnWidth(int col) {
	return col >= 0 && col < column_widths.length ? column_widths[col] : 0;
}

/** Sets the width of a column. If the value is set to
 * BWTEnum.VARIABLE, the width is set to the widest value in the column.
 * @see JCMultiColumnInterface#calcWidth
 */
public void setColumnWidth(int col, int width) {
	column_widths = BWTUtil.copyList(column_widths, col+1, BWTEnum.VARIABLE);
	column_widths_ext =
		BWTUtil.copyList(column_widths_ext, col+1, BWTEnum.VARIABLE);
	column_widths_ext[col] = column_widths[col] = width;
	if (width == BWTEnum.VARIABLE) 
		column_widths[col] = multi.calcWidth(col);
}

/** Sets the internal width of a column. If the value is set to
 * BWTEnum.VARIABLE, the width is set to the widest value in the column.
 * @see JCMultiColumnInterface#calcWidth
 */
void setColumnWidthInternal(int col, int width) {
	column_widths = BWTUtil.copyList(column_widths, col+1, BWTEnum.VARIABLE);
	column_widths_ext =
		BWTUtil.copyList(column_widths_ext, col+1, BWTEnum.VARIABLE);
	column_widths[col] = width;
	if (width == BWTEnum.VARIABLE)
		column_widths[col] = multi.calcWidth(col);
}

/** Gets the current number of columns. */
public int getNumColumns() { return num_columns; }

/** Sets the current number of columns (default: 1). */
public void setNumColumns(int v) {
	num_columns = v;
}

/** Gets a column's alignment (default: BWTEnum.MIDDLELEFT). */
public int getColumnAlignment(int col) {
	return col < alignments.length ? alignments[col] : BWTEnum.MIDDLELEFT;
}

/** Gets the column alignments. */
public int[] getColumnAlignments() { 
	alignments = BWTUtil.copyList(alignments, num_columns, BWTEnum.MIDDLELEFT);
	return alignments;
}

/** Sets a column's alignment.
 * @param alignment one of:
 BWTEnum.TOPLEFT, TOPCENTER, TOPRIGHT, MIDDLELEFT (default), MIDDLECENTER, MIDDLERIGHT, BOTTOMLEFT, BOTTOMCENTER and BOTTOMRIGHT
 */
public void setColumnAlignment(int col, int align) {
	alignments = BWTUtil.copyList(alignments, col+1, BWTEnum.MIDDLELEFT);
	alignments[col] = align;
}

/** Sets the column's alignments.
 * @param align one of:
 BWTEnum.TOPLEFT, TOPCENTER, TOPRIGHT, MIDDLELEFT, MIDDLECENTER, MIDDLERIGHT, BOTTOMLEFT, BOTTOMCENTER and BOTTOMRIGHT
 */
public void setColumnAlignments(int[] align) {
	alignments = (align != null) ? align : new int[0];
}

/**
 * Gets the position of the left boundary of the column.  If the column is
 * last_column + 1 then total width of the columns is returned
 */
public int getColumnPosition(int col) {
	int w = 0;
	for (int i = 0; i < getNumColumns() && i < col; i++) {
		if (getColumnDisplayWidth(i) != BWTEnum.NOVALUE)
			w += getColumnDisplayWidth(i);
	}
	
	return(w);
}

/** Gets the column's left margin value (pixels). */
public int getColumnLeftMargin(int col) {
	return col < left_margins.length ? left_margins[col] : 5;
}

/** Sets the column's left margin value (pixels) (default: 5). */
public void setColumnLeftMargin(int col, int value) {
	left_margins = BWTUtil.copyList(left_margins, col+1, 5);
	left_margins[col] = value;
}

/** Gets the column's right margin value (pixels). */
public int getColumnRightMargin(int col) {
	return col < right_margins.length ? right_margins[col] : 5;
}

/** Sets the column's right margin value (pixels) (default: 5). */
public void setColumnRightMargin(int col, int value) {
	right_margins = BWTUtil.copyList(right_margins, col+1, 5);
	right_margins[col] = value;
}

/** Gets a list of the column display widths.
 * @see #setColumnDisplayWidths
 */
public int[] getColumnDisplayWidths() { return column_display_widths; }

/** Sets the list of column display widths. */
public void setColumnDisplayWidths(int[] widths) {
	column_display_widths = (widths != null) ? widths : new int[0];
}

/** Gets the current display width of a column, or BWTEnum.NOVALUE if the column does not exist.
 */
public int getColumnDisplayWidth(int col) { 
	int width = (col >= 0 && col < column_widths.length) ? getColumnWidth(col) : BWTEnum.NOVALUE;

	if (col >= 0 && col < column_display_widths.length) { 
		if (column_display_widths[col] != BWTEnum.NOVALUE) {
			width = column_display_widths[col];
		}
	}

	return width;
}

/** Sets the display width of a column. */
public void setColumnDisplayWidth(int col, int width) {
	column_display_widths = BWTUtil.copyList(column_display_widths, col+1, BWTEnum.NOVALUE);
	column_display_widths[col] = (width != BWTEnum.NOVALUE) ?  width : getColumnWidth(col);
}

/** Gets column resize policy. 
 * 	@param policy one of: BWTEnum.RESIZE_SINGLE, RESIZE_NONE, RESIZE_COLLAPSE 
 */
public int getColumnResizePolicy() {
	return column_resize_policy;
}

/** Sets column resize policy. 
 * 	@param policy one of: BWTEnum.RESIZE_SINGLE, RESIZE_NONE, RESIZE_COLLAPSE 
 */
public void setColumnResizePolicy(int policy) {
	column_resize_policy = policy;
}

}
