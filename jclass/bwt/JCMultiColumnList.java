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
// RCSID -- $RCSfile: JCMultiColumnList.java $ $Revision: 2.9 $ $Date: 2000/11/09 20:10:44 $ $Locker: $  Sitraka Inc.

package jclass.bwt;
import jclass.util.*;
import java.awt.*;
import java.applet.*;
/* JDK110_START */
import java.awt.event.*;
/* JDK110_END */

/**
 * A JCScrolledWindow which contains a JCMultiColumnListComponent.<p>
 * <strong>Behavior</strong><br>
 * The column labels may be created as labels or buttons. Clicking on a
 * column's button will cause the column to be sorted.<p>
 * see <a href=jclass.bwt.JCList.html>JCList</a><p>
 *
 * <strong>Properties</strong><p>
 * <table border>
 * <tr><th>Name</th>    <th>Method</th></tr><tr><td>
 * AllowMultipleSelections   </td><td><a href=#setAllowMultipleSelections>setAllowMultipleSelections</a></td></tr><tr><td>
 * AutoSelect                </td><td><a href=#setAutoSelect>setAutoSelect</a></td></tr><tr><td>
 * Background                </td><td><a href=java.awt.Component.html#setBackground>setBackground</a></td></tr><tr><td>
 * Batched                	</td><td><a href=#setBatched>setBatched</a></td></tr><tr><td>
 * ColumnAlignments          </td><td><a href=jclass.bwt.JCMultiColumnWindow.html#setColumnAlignments>setColumnAlignments</a></td></tr><tr><td>
 * ColumnDisplayWidths 		</td><td><a href=jclass.bwt.JCMultiColumnWindow.html#setColumnDisplayWidths>setColumnDisplayWidths</a></td></tr><tr><td>
 * ColumnLabels              </td><td><a href=jclass.bwt.JCMultiColumnWindow.html#setColumnLabels>setColumnLabels</a></td></tr><tr><td>
 * ColumnLabelSort     		</td><td><a href=jclass.bwt.JCMultiColumnWindow.html#setColumnLabelSort>setColumnLabelSort</a></td></tr><tr><td>
 * ColumnLabelSortMethod 	</td><td><a href=jclass.bwt.JCMultiColumnWindow.html#setColumnLabelSort>setColumnLabelSortMethod</a></td></tr><tr><td>
 * ColumnLeftMargin          </td><td><a href=jclass.bwt.JCMultiColumnWindow.html#setColumnLeftMargin>setColumnLeftMargin</a></td></tr><tr><td>
 * ColumnResizePolicy  		</td><td><a href=jclass.bwt.JCMultiColumnWindow.html#setColumnResizePolicy>setColumnResizePolicy</a></td></tr><tr><td>
 * ColumnRightMargin         </td><td><a href=jclass.bwt.JCMultiColumnWindow.html#setColumnRightMargin>setColumnRightMargin</a></td></tr><tr><td>
 * ColumnWidths              </td><td><a href=jclass.bwt.JCMultiColumnWindow.html#setColumnWidths>setColumnWidths</a></td></tr><tr><td>
 * Font                      </td><td><a href=jclass.bwt.JCContainer.html#setFont>setFont</a></td></tr><tr><td>
 * Foreground                </td><td><a href=java.awt.Component.html#setForeground>setForeground</a></td></tr><tr><td>
 * Insets                    </td><td><a href=jclass.bwt.JCContainer.html#setInsets>setInsets</a></td></tr><tr><td>
 * Items                     </td><td><a href=#setItems>setItems</a> or <a href=#setItemsStrings>setItemsStrings</a></td></tr><tr><td>
 * NumColumns                </td><td><a href=jclass.bwt.JCMultiColumnWindow.html#setNumColumns>setNumColumns</a></td></tr><tr><td>
 * PreferredSize             </td><td><a href=jclass.bwt.JCContainer.html#setPreferredSize>setPreferredSize</a></td></tr><tr><td>
 * RowHeight                 </td><td><a href=#setRowHeight>setRowHeight</a></td></tr><tr><td>
 * ScrollbarDisplay          </td><td><a href=jclass.bwt.JCScrolledWindow.html#setScrollbarDisplay>setScrollbarDisplay</a></td></tr><tr><td>
 * ScrollbarOffset           </td><td><a href=jclass.bwt.JCScrolledWindow.html#setScrollbarOffset>setScrollbarOffset</a></td></tr><tr><td>
 * SelectedBackground        </td><td><a href=#setSelectedBackground>setSelectedBackground</a></td></tr><tr><td>
 * SelectedForeground        </td><td><a href=#setSelectedForeground>setSelectedForeground</a></td></tr><tr><td>
 * Spacing                   </td><td><a href=#setSpacing>setSpacing</a></td></tr><tr><td>
 * UserData                  </td><td><a href=jclass.bwt.JCContainer.html#setUserData>setUserData</a></td></tr>
 * UserDataList              </td><td><a href=#setUserDataList>setUserDataList</a></td></tr><tr><td>
 * VisibleRows               </td><td><a href=#setVisibleRows>setVisibleRows</a></td></tr>
 * </table><p>
 *
 * <strong>Events</strong><p>
 * <table border><tr>
 * <th>Class</th> <th>Listener</th>   <th>Description</th></tr><tr><td>
 * JCActionEvent    </td><td><a href=jclass.bwt.JCList.html#addActionListener>addActionListener</a></td> <td>Posted when an item is double-clicked, or the ENTER key is hit</td></tr><tr><td>
 * JCListEvent    </td><td><a href=jclass.bwt.JCList.html#addItemListener>addItemListener</a></td> <td>Posted when an item is clicked or the space key is hit</td></tr>
 * </table>
 */
public class JCMultiColumnList extends JCMultiColumnWindow
implements JCListInterface {

JCMultiColumnListComponent list;

private static final String base = "multicolumnlist";
private static int nameCounter = 0;

/** Creates an empty list. No parameters are read from an HTML file. */
public JCMultiColumnList() {
	this(null, null, null);
}

/** Creates a list with the specified items.
 * No parameters are read from an HTML file.
 * @see #setItems
 */
public JCMultiColumnList(JCVector items) {
	this(items, null, null);
}

/** Creates a list which reads parameters from the applet's HTML file.
 * @param items the initial items.
 * Each element may be a String, JCString, or any object (in which case the
 * object's toString() method is called to obtain a string)
 * @param applet the applet whose PARAM tags are to be read
 * @param name if this is not null, only parameters preceded by this name are read
 * @see #setItems
 * @see java.applet.Applet#getParameter
 * @see jclass.util.JCString
 */
public JCMultiColumnList(JCVector items, Applet applet, String name) {
	super(applet, name);
	if (name == null)
		setName(base + nameCounter++);
	setList(new JCMultiColumnListComponent(items, applet, getName()));
	if (getClass().getName().equals("jclass.bwt.JCMultiColumnList"))
		 getParameters(applet);
}

/**
 * Creates an empty list with the specified number of visible lines.
 * @param rows the number of items to show.
 * @param multipleSelections if true then multiple selections are allowed
 */
public JCMultiColumnList(int rows, boolean multipleSelections) {
	this(null, null, null);
	list.setVisibleRows(rows);
	list.setAllowMultipleSelections(multipleSelections);
}

/** Gets the internal list. */
public JCMultiColumnListComponent getList() { return list; }

/** Replaces the current internal list. */
public void setList(JCMultiColumnListComponent list) {
	this.list = list;
	list.scrolled_window = this;
	setViewport(list);
	if (!isDisplayable())
		validate();
/* JDK110_START */
	list.addKeyListener(this);
/* JDK110_END */
}

protected int preferredWidth() {
	if (list == null || list.countItems() == 0)
		return (header != null) ? 
			    JCComponent.getPreferredSize(header).width : 100;

	int w = JCComponent.getPreferredSize(list).width;
	if (list.visible_rows == 0 || list.visible_rows >= list.countItems())
		w += sb_size();
	else
		w += BWTEnum.SB_SIZE + sb_offset;
	return Math.max(headerWidth(), w);
}

/** Sets the work area height to be the list's total height. */
protected int getViewportHeight() {
	int last_row = list.countItems() - 1;
	if (last_row < 0)
		return super.getViewportHeight();
	return list.getRowPosition(last_row) + list.getRowHeight(last_row) +
		2*(list.getBorderThickness()+list.getHighlightThickness()) +
		list.getInsets().top + list.getInsets().bottom;
}

/***************************
 * JCListInterface methods
 ***************************/
/**
 * Adds the specified item listener to receive item events from this list.
 * If the listener implements JCListListener (an JCItemListener subclass),
 * it will be passed JCListEvents.
 * @see JCItemEvent
 * @see JCListEvent
 * @see JCListListener
 */ 
public void addItemListener(JCItemListener l) { list.addItemListener(l); }

/**
 * Removes the specified item listener so it no longer receives
 * item events from this list.
 */ 
public void removeItemListener(JCItemListener l) { list.removeItemListener(l); }

/**
 * Adds the specified action listener to receive action events
 * from this list. Action events occur when a list item is double-clicked.
 * @see JCActionEvent
 */
public void addActionListener(JCActionListener l) { list.addActionListener(l); }

/**
 * Removes the specified action listener so it no longer receives
 * action events from this list.
 * @see #addActionListener
 */ 
public void removeActionListener(JCActionListener l) { list.removeActionListener(l); }
 
/** Gets the Batched value.
 * @see #setBatched
 */
public boolean getBatched() { return list.getBatched(); }

/** If false (default), the component is repainted every time an item
 * is changed. If set to true, the component is only repainted (if necessary)
 * when the value is again set to false.
 */
public void setBatched(boolean v) { list.setBatched(v); }

/** Gets the list's values.
 * @see #setItems
 */
public JCVector getItems() { return list.getItems(); }

/** Sets the list's values as a Vector of objects.<p>
 * <strong>HTML param name/value:</strong> "Items"/string list
 * @param items each object may be a String, JCString, or any object
 * (in which case the object's toString() method is called to obtain a string)
 * @see jclass.util.JCString
 * @see jclass.util.JCConverter#toVector
 */
public void setItems(JCVector items) { list.setItems(items); }

/** Sets the list's values as a list of Strings. */
public void setItems(String[] items) { list.setItems(items); }

/** Gets the list's values as a list of Strings.
 * @see #setItems
 */
public synchronized String[] getItemsStrings() { return list.getItemsStrings(); }

/** Sets the list's values as a list of Strings. The value for each column is
 * separated by commas.
 */
public void setItemsStrings(String[] items) { list.setItems(items); }

/**
 * Returns the number of items in the list.
 * @see #getItem
 */
public int countItems() { return list.countItems(); }

/**
 * Gets the item associated with the specified index.
 * @param row the position of the item
 * @see #countItems
 */
public Object getItem(int row) { return list.getItem(row); }

/**
 * Adds the specified item to the end of the list.
 * @param item the item to be added
 */
public void addItem(Object item) { list.addItem(item); }

/**
 * Parses a string and adds the specified item to the end of the list.
 * @param item the item to be added; a Vector is created using the tokens
 * in the string
 * @param delim delimiter separating each token in the string
 */
public void addItem(String item, char delim) { list.addItem(item, delim); }

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
	list.addItem(applet, item, delim); 
}

/**
 * Adds or inserts an item to the list.
 * @param item the item to be added
 * This may be a String, JCString, or any object (in which case the object's
 * toString() method is called to obtain a string)
 * @param row the position at which to put the item;
 * if row is -1, or greater than the number of items in the
 * list, the item is added to the end
 * @see jclass.util.JCString
 */
public void addItem(Object item, int row) { list.addItem(item, row); }

/**
 * Replaces the item at the given row.
 * @param item the new value to replace the existing item
 * @param row the position of the item to replace
 */
public void replaceItem(Object item, int row) { list.replaceItem(item, row); }

/**
 * Clears the list.
 * @see #deleteItem
 * @see #deleteItems
 */
public void clear() { list.clear(); }

/**
 * Deletes an item from the list.
 */
public void deleteItem(int row) { list.deleteItem(row); }

/**
 * Delete multiple items from the list.
 */
public void deleteItems(int start, int end) { list.deleteItems(start, end); }

/**
 * Gets the selected item on the list or BWTEnum.NOTFOUND
 * if no item is selected.
 * @see #select
 * @see #deselect
 * @see #isSelected
 */
public int getSelectedIndex() { return list.getSelectedIndex(); }

/**
 * Returns the selected indexes on the list.
 * @see #select
 * @see #deselect
 * @see #isSelected
 */
public int[] getSelectedIndexes() { return list.getSelectedIndexes(); }

/**
 * Returns the selected item on the list or null if no item is selected.
 * @see #select
 * @see #deselect
 * @see #isSelected
 */
public Object getSelectedItem() { return list.getSelectedItem(); }

/**
 * Returns the selected items on the list as Strings.
 * @see #select
 * @see #deselect
 * @see #isSelected
 */
public Object[] getSelectedObjects() { return list.getSelectedObjects(); }

/**
 * Selects the item at the specified row.
 * @param row the position of the item to select
 * @see #getSelectedItem
 * @see #deselect
 * @see #isSelected
 */
public void select(int row) { list.select(row); }

/** Gets the AutoSelect value.
 * @see #setAutoSelect
 */
public boolean getAutoSelect() { return list.getAutoSelect(); }

/** If true, the item which currently has focus is also selected (default: false).<p>
 * <strong>HTML param name/value:</strong> "AutoSelect"/boolean
 * @see #select
 */
public void setAutoSelect(boolean v) { list.setAutoSelect(v); }

/**
 * Deselects the item at the specified row.
 * @param row the position of the item to deselect
 * @see #select
 * @see #getSelectedItem
 * @see #isSelected
 */
public void deselect(int row) { list.deselect(row); }

/**
 * Returns true if the item at the specified row has been selected;
 * false otherwise.
 * @param row the item to be checked
 * @see #select
 * @see #deselect
 * @see #isSelected
 */
public boolean isSelected(int row) { return list.isSelected(row); }

/**
 * Gets the list's SelectedBackground value.
 * @see #setSelectedBackground
 */
public Color getSelectedBackground() { return list.getSelectedBackground(); }

/**
 * Sets the background color of selected rows (default: foreground color).<p>
 * <strong>HTML param name/value:</strong> "SelectedBackground"/color
 * @see jclass.util.JCConverter#toColor
 */
public void setSelectedBackground(Color v) { list.setSelectedBackground(v); }

/**
 * Gets the list's SelectedForeground value.
 * @see #setSelectedForeground
 */
public Color getSelectedForeground() { return list.getSelectedForeground(); }

/**
 * Sets the foreground color of selected rows (default: background color).<p>
 * <strong>HTML param name/value:</strong> "SelectedForeground"/color
 * @see jclass.util.JCConverter#toColor
 */
public void setSelectedForeground(Color v) { list.setSelectedForeground(v); }

/**
 * Returns the number of visible lines in this list.
 */
public int getRows() { return list.getRows(); }

/** Sets the number of visible rows. If set to 0 (default), the list
 * will attempt to resize itself so that all its items are visible.<p>
 * <strong>HTML param name/value:</strong> "VisibleRows"/int
 * @see #setItems
 */
public void setVisibleRows(int v) { list.setVisibleRows(v); }

/** Gets the current top-most visible row.
 * @see #setTopRow
 */
public int getTopRow() { return list.getTopRow(); }

/** Sets the top-most visible row.
 * @see #makeVisible
 */
public void setTopRow(int row) { list.setTopRow(row); }

/**
 * Returns true if this list allows multiple selections.
 * @see #setAllowMultipleSelections
 */
public boolean allowsMultipleSelections() { return list.allowsMultipleSelections(); }

/**
 * Returns true if this list allows multiple selections.
 * @see #setAllowMultipleSelections
 */
public boolean getAllowMultipleSelections() { return list.allowsMultipleSelections(); }

/**
 * Sets whether the list allows multiple selections.<P>
 * <strong>HTML param name/value:</strong> "AllowMultipleSelections"/boolean
 * @see #allowsMultipleSelections
 */
public void setAllowMultipleSelections(boolean v) {
	list.setAllowMultipleSelections(v);
}

/**
 * Gets the row of the item that was last made visible by the method
 * makeVisible.
 */
public int getVisibleIndex() { return list.getVisibleIndex(); }

/**
 * Forces the item at the specified row to be visible.
 * @param row the position of the item
 * @see #getVisibleIndex
 */
public void makeVisible(int row) { list.makeVisible(row); }

/** Gets the height of each row (pixels).
 * @see #setRowHeight
 */
public int getRowHeight() { return list.getRowHeight(); }

/** Sets the height of each row (pixels).<p>
 * <strong>HTML param name/value:</strong> "RowHeight"/an int, "VARIABLE", or "FONT_HEIGHT"<br>
 * @param h any int, or one of the following:<p>
 * If set to BWTEnum.VARIABLE, the height will be calculated individually
 * for each row.<p>
 * If set to BWTEnum.FONT_HEIGHT (default), the height will be set to the
 * height of the current font.
 * @exception IllegalArgumentException If an invalid value is set
 */
public void setRowHeight(int h) { list.setRowHeight(h); }

/** Gets the spacing between items.
 * @see #setSpacing
 */
public int getSpacing() { return list.getSpacing(); }

/** Sets the spacing between items (default: 0).
 * This value increases the HighlightThickness value.<p>
 * <strong>HTML param name/value:</strong> "Spacing"/int
 * @see JCComponent#setHighlightThickness
 */
public void setSpacing(int v) { list.setSpacing(v); }

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
	list.sortByColumn(column, sort_if);
}

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
public void sortByColumn(int column, JCSortInterface sort_if, int direction) {
	list.sortByColumn(column, sort_if, direction);
}

/** Draws a row. */
public void paintRow(int row) { list.paintRow(row); }

/** Gets the UserDataList value.
 * @see #setUserDataList
 */
public Object[] getUserDataList() { return list.userdata_list; }

/** Sets an application-defined list of objects that are maintained for each
 * item.
 */
public void setUserDataList(Object[] v) { list.setUserDataList(v); }

/**
 * Add key listener to the MultiColumnListComponent
 * @see #removeKeyListener
 */
/* JDK110_START */
public void addKeyListener(KeyListener l) {
	if (list != null) {
		list.addKeyListener(l);
	}
}
/* JDK110_END */

/**
 * Remove focus listener to the MultiColumnListComponent
 * @see #addKeyListener
 */
/* JDK110_START */
public void removeKeyListener(KeyListener l) {
	if (list != null) {
		list.removeKeyListener(l);
	}
}
/* JDK110_END */

/**
 * Add focus listener to the MultiColumnListComponent
 * @see #removeFocusListener
 */
/* JDK110_START */
public void addFocusListener(FocusListener l) {
	if (list != null) {
		list.addFocusListener(l);
	}
}
/* JDK110_END */

/**
 * Remove focus listener to the MultiColumnListComponent
 * @see #addFocusListener
 */
/* JDK110_START */
public void removeFocusListener(FocusListener l) {
	if (list != null) {
		list.removeFocusListener(l);
	}
}
/* JDK110_END */

}
