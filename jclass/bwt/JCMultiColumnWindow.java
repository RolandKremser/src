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
// RCSID -- $RCSfile: JCMultiColumnWindow.java $ $Revision: 2.9 $ $Date: 2000/11/09 20:10:46 $ $Locker: $  Sitraka Inc.

package jclass.bwt;
import jclass.util.*;
import java.applet.*;
import java.awt.*;

/**
 * An abstract class which is the superclass for scrollable
 * multi-column components and their column labels.<p>
 *
 * <strong>Properties</strong><p>
 * <table border>
 * <tr><th>Name</th>    <th>Method</th></tr><tr><td>
 * Background          </td><td><a href=java.awt.Component.html#setBackground>setBackground</a></td></tr><tr><td>
 * ColumnAlignments    </td><td><a href=#setColumnAlignments>setColumnAlignments</a></td></tr><tr><td>
 * ColumnButtons       </td><td><a href=#setColumnButtons>setColumnButtons</a></td></tr><tr><td>
 * ColumnDisplayWidths </td><td><a href=#setColumnDisplayWidths>setColumnDisplayWidths</a></td></tr><tr><td>
 * ColumnLabels        </td><td><a href=#setColumnLabels>setColumnLabels</a></td></tr><tr><td>
 * ColumnLabelSort     </td><td><a href=#setColumnLabelSort>setColumnLabelSort</a></td></tr><tr><td>
 * ColumnLabelSortMethod </td><td><a href=#setColumnLabelSort>setColumnLabelSortMethod</a></td></tr><tr><td>
 * ColumnLeftMargin    </td><td><a href=#setColumnLeftMargin>setColumnLeftMargin</a></td></tr><tr><td>
 * ColumnResizePolicy  </td><td><a href=#setColumnResizePolicy>setColumnResizePolicy</a></td></tr><tr><td>
 * ColumnRightMargin   </td><td><a href=#setColumnRightMargin>setColumnRightMargin</a></td></tr><tr><td>
 * ColumnWidths        </td><td><a href=#setColumnWidths>setColumnWidths</a></td></tr><tr><td>
 * Font                </td><td><a href=jclass.bwt.JCContainer.html#setFont>setFont</a></td></tr><tr><td>
 * Foreground          </td><td><a href=java.awt.Component.html#setForeground>setForeground</a></td></tr><tr><td>
 * Insets              </td><td><a href=jclass.bwt.JCContainer.html#setInsets>setInsets</a></td></tr><tr><td>
 * NumColumns          </td><td><a href=#setNumColumns>setNumColumns</a></td></tr><tr><td>
 * PreferredSize       </td><td><a href=jclass.bwt.JCContainer.html#setPreferredSize>setPreferredSize</a></td></tr><tr><td>
 * UserData            </td><td><a href=jclass.bwt.JCContainer.html#setUserData>setUserData</a></td></tr>
 * </table>
 * @see JCOutliner
 * @see JCMultiColumnList
 */
public abstract class JCMultiColumnWindow extends JCScrolledWindow
implements JCMultiColumnInterface, JCActionListener {

public static final int ASCENDING = JCqsort.ASCENDING;
public static final int DESCENDING = JCqsort.DESCENDING;

JCSortInterface sort_method;
boolean column_label_sort = true;

/** The header's viewport. */
protected Viewport headerArea;

/** The column label component. */
protected JCHeader header;

/** The component scrolled by this window. */
protected JCMultiColumnInterface comp;

/** Creates an empty window. No parameters are read from an HTML file. */
public JCMultiColumnWindow() {
	super(null, null);
}

/** Creates a window which reads parameters from the applet's HTML file.
 * @param applet the applet whose PARAM tags are to be read
 * @param name if this is not null, only parameters preceded by this name are read
 * @see java.applet.Applet#getParameter
 */
public JCMultiColumnWindow(Applet applet, String name) {
	super(applet, name);
}

/** Gets the header for the window. */
public JCHeader getHeader() { 
	if (header == null) 
		setHeader(new JCHeader(null, applet, getName()));
	return header; 
}

/** Sets a header for the window.
 * @see JCMultiColumnList
 * @see JCOutliner
 */
public void setHeader(JCHeader header) {
	this.header = header;
	if (header == null) {
		if (headerArea != null)
			remove(headerArea);
		headerArea = null;
		return;
	}
	header.setColumnResizePolicy(getColumnResizePolicy());
	if (headerArea == null)
		headerArea = new Viewport();
	headerArea.add(header);
	header.setMultiColumnComponent((JCMultiColumnInterface)viewport);
	header.addActionListener(this);
	if (isDisplayable())
		addInternal(headerArea);
}

/** Gets the column label strings, or null if none have been set.
 * @see #setColumnButtons
 * @see #setColumnLabels
 * @see JCHeader#getLabels
 */
public String[] getColumnLabels() {
	String[] list = null;
	if (header == null) {
/* JDK110_START */
		// ColumnLabels property does not show at design-time if
		// null is returned.  Must use an array to fool IDEs like
		// BeanBox.
		if (java.beans.Beans.isDesignTime()) {
			list = new String[0];
		}
/* JDK110_END */
		return list;
	}
	JCComponent[] labels = header.getLabels();
	list = new String[labels.length];

	for (int i=0; i < labels.length; i++) {
		if (labels[i] instanceof JCLabel) {
			Object v = ((JCLabel)labels[i]).getLabel();
			list[i] = (v != null) ? v.toString() : "";
		}
		else
			list[i] = labels[i].getName();
	}
	return list;
}


/** Sets a header for the window with the specified labels.
 * It is used by bean to avoid Introspection conflict.
 * @see #setColumnLabels
 * @see #setColumnButtons
 * @see JCHeader#setLabels
 */
public void setColumnLabelsStrings(String[] labels) {
	setColumnLabels(new JCVector(labels));
}

/** Sets a header for the window with the specified labels.
 * @see #setColumnButtons
 * @see JCHeader#setLabels
 */
public void setColumnLabels(String[] labels) {
	setColumnLabels(new JCVector(labels));
}

/** Sets a header for the window with the specified labels.
 * @see JCHeader#setLabels
 */
public void setColumnLabels(JCVector labels) {
	if (header == null) {
		if (labels == null || labels.size() == 0) return;
		setHeader(new JCHeader(null, applet, getName()));
	}
	if (labels == null || labels.size() == 0) 
		setHeader(null);
	else
		header.setLabels(labels);
}


/** Sets a header for the window with buttons created from the specified labels.
 * It is used by bean to avoid Introspection conflict.
 * @see #setColumnLabels
 * @see #setColumnButtons
 * @see JCHeader#setButtons
 */
public void setColumnButtonsStrings(String[] labels) {
	setColumnButtons(new JCVector(labels));
}

/** Sets a header for the window with buttons created from the specified labels.
 * @see #setColumnLabels
 * @see JCHeader#setButtons
 */
public void setColumnButtons(String[] labels) {
	setColumnButtons(new JCVector(labels));
}

/** Sets a header for the window with buttons created from the specified labels.
 * @see JCHeader#setButtons
 */
public void setColumnButtons(JCVector labels) {
	if (header == null) {
		if (labels == null || labels.size() == 0) return;
		setHeader(new JCHeader(null, applet, getName()));
	}
	if (labels == null || labels.size() == 0) 
		setHeader(null);
	else
		header.setButtons(labels);
}


/** Sets the component which will be the viewport. */
protected void setViewport(JCMultiColumnInterface comp) {
	this.comp = comp;
	viewport = (Component) comp;
}

/** Sets the location and width of the header. */
protected int reshapeHeader(int x, int y, int w) {
	int h = headerHeight();
	if (headerArea != null) {
		JCComponent.setBounds(headerArea, x, y, w, h);
	}
		
	return h;
}

/** Returns the header's preferred height. */
protected int headerHeight() { 
	return (headerArea == null) ? 0 : 
		    JCComponent.getPreferredSize(headerArea).height;
}

/** Returns the header's preferred width. */
protected int headerWidth() { 
	return (headerArea == null) ? 0 : 
		    JCComponent.getPreferredSize(headerArea).width;
}

/** Moves the viewport up so that its top highlight is not visible. */
protected void reshapeViewport(int x, int y, int w, int h) {
	if (headerArea != null)
		y -= ((JCComponent) comp).getHighlightThickness();
	super.reshapeViewport(x, y, w, h);
}

/** Returns the sum of the viewport's and header's preferred height. */
protected int preferredHeight() {
	int h = super.preferredHeight();
	if (headerArea != null)
		h += JCComponent.getPreferredSize(headerArea).height;
	return h;
}

/** Returns the larger of the viewport's and header's preferred width. */
protected int getViewportWidth() {
	int w = super.getViewportWidth();
	if (headerArea != null)
		w = Math.max(w, JCComponent.getPreferredSize(headerArea).width);
	return w;
}

/**
 * Sets the font of the component.
 * This value may be set from an HTML file using a PARAM tag with a "Font"
 * name and a Font value.
 * @see jclass.util.JCConverter#toFont
 */
public void setFont(Font f) {
	Font old_font = getFont();
	if (old_font != null && old_font.equals(f)) return;
	super.setFont(f);
	if (!isDisplayable()) return;
	((Component)comp).setFont(f);
	if (header != null) 
		header.setFont(f);
	layout();
}

/** Gets the windows's ColumnLabelSort value.
 * @see #setColumnLabelSort
 */
public boolean getColumnLabelSort() { return column_label_sort; }

/** Specifies whether a column is sorted when its button is clicked (default: true).
 * If true, sortByColumn() will be called.<p>
 * <strong>HTML param name/value:</strong> "ColumnLabelSort"/boolean
 * @see #sortByColumn
 */
public void setColumnLabelSort(boolean v) { column_label_sort = v; }

/**
 * Sets the method to be called during sorting.
 * @param method an interface whose "compare" method is called during 
 * sorting, to provide a mechanism for the application to specify whether one 
 * object is greater than another (similar to qsort's compare function)
 * (default: null). If null, the column is sorted by numerical or string 
 * comparison as applicable.
 * @see #setColumnLabelSort
 * @see jclass.util.JCSortInterface
 */
public void setColumnLabelSortMethod(JCSortInterface method) {
	sort_method = method;
}

/** Gets the current ColumnLabelSortMethod value.
 * @see #setColumnLabelSortMethod
 */
public JCSortInterface getColumnLabelSortMethod() { return sort_method; }

/**
 * Sorts the rows in the list in ascending order based on a specified column,
 * using the String equivalents of each value.
 * @param sort_if An optional interface whose compare method is called during
 * sorting, to provide a mechanism for the application to specify whether one
 * object is greater than another (similar to qsort's compare function).
 * If this parameter is null, the column is sorted by numerical or string
 * comparison as applicable.
 */
public void sortByColumn(int column, JCSortInterface sort_if) {}

/**
 * Sorts the rows in the list based on a specified column,
 * using the String equivalents of each value.
 * @param sort_if An optional interface whose compare method is called during
 * sorting, to provide a mechanism for the application to specify whether one
 * object is greater than another (similar to qsort's compare function).
 * If this parameter is null, the column is sorted by numerical or string
 * comparison as applicable.
 * @param direction sort direction: ASCENDING or DESCENDING
 */
public void sortByColumn(int column, JCSortInterface sort_if, int direction) {}

public void addNotify() {
	super.addNotify();
	if (headerArea != null) {
		addInternal(headerArea);
		header.setMultiColumnComponent((JCMultiColumnInterface)viewport);
	}
}

/** Scrolls the window horizontally to the specified value.
 * Subclasses may override this method to provide custom behavior.
 */
protected void scrollHorizontal(JCAdjustmentEvent ev, int value) {
	if (headerArea != null)
		headerArea.scrollHorizontal(value);
	super.scrollHorizontal(ev, value);
}

/** Sorts a column if the user clicks on a column heading button.
 * @see #setColumnLabelSort
 */
public void actionPerformed(JCActionEvent ev) {
	if (header == null || !getColumnLabelSort()) return;
	int direction = JCqsort.ASCENDING;
	Component btn = (Component) ev.getSource();
	int col = header.getComponent(btn);
	if (col < 0) return;
	if (btn instanceof HeaderButton)
		direction = ((HeaderButton) btn).direction;
	sortByColumn(col, sort_method, direction);
	if (btn instanceof HeaderButton)
		((HeaderButton) btn).direction = (direction == JCqsort.ASCENDING) ? 
			JCqsort.DESCENDING : JCqsort.ASCENDING;
}

/***********************************
 * JCMultiColumnInterface methods
 ***********************************/

/** Gets a list of the column widths.
 * @see #setColumnWidths
 */
public int[] getColumnWidths() { return comp.getColumnWidths(); }

/** Sets the list of column widths. If a column's value is set to
 * BWTEnum.VARIABLE, the width is set to the widest value in the column.<p>
 * <strong>HTML param name/value:</strong> "ColumnWidths"/comma-separated list of ints
 */
public void setColumnWidths(int[] widths) {
	if (header != null)
		header.setColumnWidths(widths);
	comp.setColumnWidths(widths);
}

/** Gets the current width of a column, or 0 if the column does not exist.
 */
public int getColumnWidth(int col) { return comp.getColumnWidth(col); }

/** Sets the width of a column. If the value is set to
 * BWTEnum.VARIABLE, the width is set to the widest value in the column.
 * @exception IllegalArgumentException If the value is invalid
 */
public void setColumnWidth(int col, int width) {
	if (width < 0 && width != BWTEnum.VARIABLE)
		throw new IllegalArgumentException("invalid column width: "+width);
	if (header != null)
		header.setColumnWidth(col, width);
	comp.setColumnWidth(col, width);
}

/** Gets the current number of columns. */
public int getNumColumns() { return comp.getNumColumns(); }

/** Sets the current number of columns. 
 * If set to BWTEnum.VARIABLE (default), all columns are displayed.<p>
 * <strong>HTML param name/value:</strong> "NumColumns"/int
 * @exception IllegalArgumentException If the value is invalid
 */
public void setNumColumns(int v) {
	if (v < 0 && v != BWTEnum.VARIABLE)
		throw new IllegalArgumentException("invalid NumColumns: "+v);
	comp.setNumColumns(v);
	if (header != null) 
		header.setNumColumns(v);
	layout();
}

/** Gets the column alignments. */
public int[] getColumnAlignments() { return comp.getColumnAlignments(); }

/** Gets a column's alignment. */
public int getColumnAlignment(int col) { return comp.getColumnAlignment(col); }

/** Sets a column's alignment.
 * @param alignment one of:
 BWTEnum.TOPLEFT, TOPCENTER, TOPRIGHT, MIDDLELEFT, MIDDLECENTER, MIDDLERIGHT, BOTTOMLEFT, BOTTOMCENTER and BOTTOMRIGHT
 * @exception IllegalArgumentException If the value is invalid
 */
public void setColumnAlignment(int col, int align) {
	LabelConverter.checkAlignment(align);
	comp.setColumnAlignment(col, align);
}

/** Sets a column label's alignment.
 * @param alignment one of:
 BWTEnum.TOPLEFT, TOPCENTER, TOPRIGHT, MIDDLELEFT, MIDDLECENTER, MIDDLERIGHT, BOTTOMLEFT, BOTTOMCENTER and BOTTOMRIGHT
 * @exception IllegalArgumentException If the value is invalid
 */
public void setColumnLabelAlignment(int col, int align) {
	LabelConverter.checkAlignment(align);
	if (header != null)
		header.setColumnAlignment(col, align);
}

/** Sets the column's alignments.<p>
 * <strong>HTML param name/value:</strong> "ColumnAlignments"/comma-separated list of enums
 * @param align one of:
 BWTEnum.TOPLEFT, TOPCENTER, TOPRIGHT, MIDDLELEFT, MIDDLECENTER, MIDDLERIGHT, BOTTOMLEFT, BOTTOMCENTER and BOTTOMRIGHT
 */
public void setColumnAlignments(int[] align) {
	comp.setColumnAlignments(align);
}

/** Gets the physical position of the left boundary of the column
 * (accounting for horizontal scrolling).
 */
public int getColumnPosition(int col) {
	return comp.getColumnPosition(col);
}

/** Gets the column's left margin value (pixels). */
public int getColumnLeftMargin(int col) {
	return comp.getColumnLeftMargin(col);
}

/** Sets the column's left margin value (pixels) (default: 5).<p>
 * <strong>HTML param name/value:</strong> "ColumnLeftMargins"/comma-separated list of ints, one for each column
*/
public void setColumnLeftMargin(int col, int value) {
	comp.setColumnLeftMargin(col, value);
	if (header != null)
		header.setColumnLeftMargin(col, value);
	layout();
}

/** Gets the column's right margin value (pixels). */
public int getColumnRightMargin(int col) {
	return comp.getColumnRightMargin(col);
}

/** Sets the column's right margin value (pixels) (default: 5).<p>
 * <strong>HTML param name/value:</strong> "ColumnRightMargins"/comma-separated list of ints, one for each column
 */
public void setColumnRightMargin(int col, int value) {
	comp.setColumnRightMargin(col, value);
	if (header != null)
		header.setColumnRightMargin(col, value);
	layout();
}

/** Calculates the width of a column. */
public int calcWidth(int col) { return comp.calcWidth(col); }

/** Gets the component's JCMultiColumnData instance. */
public JCMultiColumnData getMultiColumnData() { 
	return comp.getMultiColumnData(); 
}

/** Gets a list of the column display widths.
 * @see #setColumnDisplayWidths
 */
public int[] getColumnDisplayWidths() { return comp.getColumnDisplayWidths(); }

/** Sets the list of column display widths. */
public void setColumnDisplayWidths(int[] widths) {
	if (header != null)
		header.setColumnDisplayWidths(widths);
	comp.setColumnDisplayWidths(widths);
}

/** Gets the current display width of a column, or 0 if the column does not exist.
 */
public int getColumnDisplayWidth(int col) { return comp.getColumnDisplayWidth(col); }

/** Sets the width of a column. If the value is set to
 * BWTEnum.VARIABLE, the width is set to the widest value in the column.
 * @exception IllegalArgumentException If the value is invalid
 */
public void setColumnDisplayWidth(int col, int width) {
	if (header != null)
		header.setColumnDisplayWidth(col, width);
	comp.setColumnDisplayWidth(col, width);
}

/** Gets column resize policy. 
 * 	@param policy one of: BWTEnum.RESIZE_SINGLE, RESIZE_NONE, RESIZE_COLLAPSE 
 */
public int getColumnResizePolicy() {
	return comp.getColumnResizePolicy();
}

/** Sets column resize policy. 
 * <p><strong>HTML param name/value:</strong> "ColumnResizePolicy"/int</p>
 * 	@param policy one of: BWTEnum.RESIZE_SINGLE, RESIZE_NONE, RESIZE_COLLAPSE 
 */
public void setColumnResizePolicy(int policy) {
	if (header != null)
		header.setColumnResizePolicy(policy);
	comp.setColumnResizePolicy(policy);
}

}
