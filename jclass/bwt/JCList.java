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
// RCSID -- $RCSfile: JCList.java $ $Revision: 2.14 $ $Date: 2000/11/09 20:10:37 $ $Locker: $  Sitraka Inc.

package jclass.bwt;
import jclass.util.*;
import java.awt.*;
import java.applet.*;
/* JDK110_START */
import java.awt.event.*;
/* JDK110_END */

/**
 * A scrollable list of text and/or image items.<p>
 * <strong>Behavior</strong><br>
 * <ul>
 * <li>If an item is clicked (or the space key is hit) with no other key pressed,
 * the item is selected if it was previously unselected,
 * or unselected if it was previously selected. The previously selected items
 * are unselected.
 * <li>If an item is clicked (or the space key is hit) with the SHIFT key down, the current selection
 * is extended from the current anchor to the item if AllowMultipleSelections
 * is true. If the anchor item is selected, the selection will be selected;
 * if the anchor item is unselected, the selection will be unselected.
 * <li>If an item is clicked (or the space key is hit) with the CONTROL key down, the current selection
 * is toggled if AllowMultipleSelections is true, and the item becomes the new anchor.
 * If the mouse is dragged across items, they are selected if
 * AllowMultipleSelections is true.
 * <li>Hitting the up or down arrow keys moves the focus item, which is shown
 * as a 2-pixel rectangle drawn around the item. The list will scroll if
 * necessary to display the focus item.
 * <li>Typing a character will cause the first item which starts with the
 * character to be made visible and selected. If no item can be found, no action
 * is take
 * </ul><p>
 *
 * <strong>Properties</strong><p>
 * <table border>
 * <tr><th>Name</th>    <th>Method</th></tr><tr><td>
 * AllowMultipleSelections   </td><td><a href=#setAllowMultipleSelections>setAllowMultipleSelections</a></td></tr><tr><td>
 * AutoSelect                </td><td><a href=#setAutoSelect>setAutoSelect</a></td></tr><tr><td>
 * Background                </td><td><a href=java.awt.Component.html#setBackground>setBackground</a></td></tr><tr><td>
 * Batched                </td><td><a href=#setBatched>setBatched</a></td></tr><tr><td>
 * Font                      </td><td><a href=jclass.bwt.JCContainer.html#setFont>setFont</a></td></tr><tr><td>
 * Foreground                </td><td><a href=java.awt.Component.html#setForeground>setForeground</a></td></tr><tr><td>
 * Insets                    </td><td><a href=jclass.bwt.JCContainer.html#setInsets>setInsets</a></td></tr><tr><td>
 * Items                     </td><td><a href=#setItems>setItems</a></td></tr><tr><td>
 * PreferredSize             </td><td><a href=jclass.bwt.JCContainer.html#setPreferredSize>setPreferredSize</a></td></tr><tr><td>
 * RowHeight                 </td><td><a href=#setRowHeight>setRowHeight</a></td></tr><tr><td>
 * ScrollbarDisplay          </td><td><a href=jclass.bwt.JCScrolledWindow.html#setScrollbarDisplay>setScrollbarDisplay</a></td></tr><tr><td>
 * ScrollbarOffset           </td><td><a href=jclass.bwt.JCScrolledWindow.html#setScrollbarOffset>setScrollbarOffset</a></td></tr><tr><td>
 * SelectedBackground        </td><td><a href=#setSelectedBackground>setSelectedBackground</a></td></tr><tr><td>
 * SelectedForeground        </td><td><a href=#setSelectedForeground>setSelectedForeground</a></td></tr><tr><td>
 * Spacing                   </td><td><a href=#setSpacing>setSpacing</a></td></tr><tr><td>
 * UserData                  </td><td><a href=jclass.bwt.JCContainer.html#setUserData>setUserData</a></td></tr><tr><td>
 * UserDataList              </td><td><a href=#setUserDataList>setUserDataList</a></td></tr><tr><td>
 * VisibleRows               </td><td><a href=#setVisibleRows>setVisibleRows</a></td></tr>
 * </table><p>
 *
 * <strong>Events</strong><p>
 * <table border><tr>
 * <th>Class</th> <th>Listener</th>   <th>Description</th></tr><tr><td>
 * JCActionEvent    </td><td><a href=#addActionListener>addActionListener</a></td> <td>Posted when an item is double-clicked, or the ENTER key is hit</td></tr><tr><td>
 * JCListEvent    </td><td><a href=#addItemListener>addItemListener</a></td> <td>Posted when an item is clicked or the space key is hit</td></tr>
 * </table>
 */
public class JCList extends JCScrolledWindow
implements JCListInterface, JCItemSelectable {

JCListComponent	list;
private static final String base = "list";
private static int nameCounter = 0;

/** Creates an empty 4-row list. No parameters are read from an HTML file. */
public JCList() {
	this(null, null, null);
}

/** Creates a 4-row list with the specified items.
 * No parameters are read from an HTML file.
 * @see #setItems
 */
public JCList(JCVector items) {
	this(items, null, null);
}

/** Creates a 4-row list with the specified items and name.
 * No parameters are read from an HTML file.
 */
public JCList(String[] items, String name) {
	this(new JCVector(items), null, name);
}

/** Creates a 4-row list which reads parameters from the applet's HTML file.
 * @param items the initial items;
 * each element may be a String, JCString, or any object (in which case the object's
 * toString() method is called to obtain a string)
 * @param applet the applet whose PARAM tags are to be read
 * @param name if this is not null, only parameters preceded by this name are read
 * @see #setItems
 * @see java.applet.Applet#getParameter
 * @see jclass.util.JCString
 */
public JCList(JCVector items, Applet applet, String name) {
	super(applet, name);
	if (name == null) setName(base + nameCounter++);
	setList(new JCListComponent(items, applet, getName()));
	if (getClass().getName().equals("jclass.bwt.JCList"))
		 getParameters(applet);
}

/**
 * Creates an empty list with the specified number of visible rows.
 * @param rows the number of items to show (default: 4).
 * If set to BWTEnum.VARIABLE, the list will attempt to show all its items.
 * @param multipleSelections if true, then multiple selections are allowed
 */
public JCList(int rows, boolean multipleSelections) {
	this(null, null, null);
	list.setVisibleRows(rows);
	list.setAllowMultipleSelections(multipleSelections);
}

/** Gets the internal list. */
public JCListComponent getList() { return list; }

/** Replaces the current internal list. */
public void setList(JCListComponent list) {
/* AWT_START 
	// We don't need to do this under SWING since JComponent tracks the current Applet
	list.setApplet(applet);
 AWT_END */
	this.list = list;
	viewport = list;
	list.scrolled_window = this;
	if (isDisplayable())
		validate();
	list.enable(isEnabled());
/* JDK110_START */
	list.addKeyListener(this);
/* JDK110_END */
}

protected int preferredWidth() {
	if (list == null)
		return -1;
	return JCComponent.getPreferredSize(list).width + BWTEnum.SB_SIZE +
		   sb_offset;
}

/** Sets the work area height to be the list's virtual height. */
protected int getViewportHeight() {
	int last_row = list.countItems() - 1;
	if (last_row < 0)
		return super.getViewportHeight();
	return list.getRowPosition(last_row) + list.getRowHeight(last_row) +
		2*(list.getBorderThickness() + list.getHighlightThickness()) +
		list.getInsets().top + list.getInsets().bottom;
}

/** Scrolls the list vertically. */
protected void scrollVertical(JCScrollableInterface scrollable,
							  JCAdjustmentEvent ev, int value) {
	super.scrollVertical(scrollable, ev, value);
	switch (keystroke) {
	case Event.PGUP:
		list.setFocus(list.bottom_row);
		break;
	case Event.PGDN:
		list.setFocus(list.top_row);
		break;
	case Event.HOME:
		list.setFocus(0);
		break;
	case Event.END:
		list.setFocus(list.items.size()-1);
	}
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

/**
 * Removes the first occurrence of an item from the list.
 * @exception IllegalArgumentException If the item doesn't exist.
 * @see #deleteItem
 */
public void remove(String item) { list.remove(item); }

/** Gets the list's values.
 * @see #setItems
 */
public synchronized JCVector getItems() { return list.getItems(); }

/** Sets the list's values as a Vector of objects.<p>
 * <strong>HTML param name/value:</strong> "Items"/string list<br>
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

/** Sets the list's values as a list of Strings. */
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
 * Adds or inserts an item to the list.
 * @param item the item to be added;
 * this may be a String, JCString, or any object (in which case the object's
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

/** Gets the Batched value.
 * @see #setBatched
 */
public boolean getBatched() { return list.getBatched(); }

/** If false (default), the component is repainted every time an item
 * is changed. If set to true, the component is only repainted (if necessary)
 * when the value is again set to false.
 */
public void setBatched(boolean v) { list.setBatched(v); }

/**
 * Removes all items from the list.
 * @see #remove
 * @see #deleteItems
 */
public void removeAll() { list.clear(); }

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

/** Finds the first row matching the specified item, or BWTEnum.NOTFOUND. */
public int find(Object item) { return list.find(item); }

/*
 * Gets the WrapAroundSearch value.
 * @see #setWrapAroundSearch
 * unexposed until necessary.
 *
 */
protected boolean getWrapAroundSearch() { return list.getWrapAroundSearch(); }

/*
 * Sets the WrapAroundSearch value.
 * If true (default), search by key entered will wrap around to the
 * beginning/end of the list to find the next/previous item.
 * unexposed until necessary.
 *
 */
protected void setWrapAroundSearch(boolean v) {
	list.setWrapAroundSearch(v);
}

/**
 * Gets the selected item on the list or BWTEnum.NOTFOUND
 * if no item is selected.
 * @see #select
 * @see #deselect
 * @see #isSelected
 */
public int getSelectedIndex() { return list.getSelectedIndex(); }

/**
 * Returns the selected indexes on the list, or null if no item is selected.
 * @see #select
 * @see #deselect
 * @see #isSelected
 */
public int[] getSelectedIndexes() { return list.getSelectedIndexes(); }

/**
 * Returns the selected item on the list, or null if no item is selected.
 * @see #select
 * @see #deselect
 * @see #isSelected
 */
public Object getSelectedItem() { return list.getSelectedItem(); }

/**
 * Returns the selected items on the list as Strings, or null if no item is selected.
 * @see #select
 * @see #deselect
 * @see #isSelected
 */
public Object[] getSelectedObjects() { return list.getSelectedObjects(); }

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
 * Selects the item at the specified row, and posts a JCListEvent
 * @param row the position of the item to select
 * @see #getSelectedItem
 * @see #deselect
 * @see #isSelected
 * @see #addItemListener
 */
public synchronized void select(int row) { list.select(row); }

/**
 * Selects the specified item.
 * @see #getSelectedItem
 * @see #getSelectedIndex
 */
public synchronized void select(Object item) { list.select(item); }

/**
 * Selects the specified item.
 * If allowsMultipleSelections is false, any previously selected rows
 * are deselected.
 * @param notify if true, a JCListEvent is posted.
 * @see #setAllowMultipleSelections
 * @see #getSelectedItem
 * @see #deselect
 * @see #isSelected
 * @see #addItemListener
 */
public synchronized void select(Object item, boolean notify) {
	list.select(item, notify);
}

/**
 * Selects the item at the specified row.
 * If allowsMultipleSelections is false, any previously selected rows
 * are deselected.
 * @param notify if true, a JCListEvent is posted.
 * @see #setAllowMultipleSelections
 * @see #getSelectedItem
 * @see #deselect
 * @see #isSelected
 * @see #addItemListener
 */
public synchronized void select(int row, boolean notify) {
	list.select(row, notify);
}

/**
 * Deselects the item at the specified row.
 * @param row the position of the item to deselect
 * @see #select
 * @see #getSelectedItem
 * @see #isSelected
 */
public synchronized void deselect(int row) { list.deselect(row); }

/** Deselects all items. No events are posted */
public synchronized void deselectAll() { list.deselectAll(); }

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

/** Sets the number of visible rows (default: 4).
 * If set to BWTEnum.VARIABLE, the list
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
public synchronized void setTopRow(int row) { list.setTopRow(row); }

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
 * Sets whether the list allows multiple selections.
 * <strong>HTML param name/value:</strong> "AllowMultipleSelections"/boolean
 * @see #allowsMultipleSelections
 */
public void setAllowMultipleSelections(boolean v) { list.setAllowMultipleSelections(v); }

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
 * Sets the font of the list.
 */
public void setFont(Font f) {
	// this method was overridden because we must recalculate the size of
    // the JCListComponent. If we don't the items in the list will overlap
    // if the font size is too big.
	super.setFont(f);
	list.calcSize(true);
}

/**
 *  Disables JCList
 */
public void disable() {
	deselectAll();
	super.disable();
}

/**
 * Add key listener to the ListComponent
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
 * Remove focus listener to the ListComponent
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
 * Add focus listener to the ListComponent
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
 * Remove focus listener to the ListComponent
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


