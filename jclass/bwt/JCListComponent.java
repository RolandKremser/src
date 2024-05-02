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
// RCSID -- $RCSfile: JCListComponent.java $ $Revision: 2.32 $ $Date: 2000/11/09 20:10:39 $ $Locker: $  Sitraka Inc.

package jclass.bwt;
import jclass.util.*;
import java.awt.*;
import java.applet.*;
/* SWING11_START 
import com.sun.java.swing.border.Border;
 SWING11_END */
/* SWING12_START */
import javax.swing.border.Border;
/* SWING12_END */

/**
 * A list of text and/or image items. To create a scrollable list, use JCList.
 * @see JCList
 */
public class JCListComponent extends JCComponent
implements JCListInterface, JCScrollableInterface, JCItemSelectable {

JCVector	items = new JCVector();
int			visible_rows = 0;
boolean[]	selected = new boolean[0], last_selected = new boolean[0];
Color		selected_bg, selected_fg;
boolean		multiple_select = false;
int			spacing = 0;
boolean		batched = false;
boolean		needs_recalc = false, needs_repaint = false;
int			pref_height_internal, pref_width_internal;
int			row_height_ext = BWTEnum.FONT_HEIGHT;
int			row_height;
int[]		row_heights, row_pos;
int			horiz_origin, vert_origin = BWTEnum.NOVALUE;		// amount scrolled
int			top_row = 0, bottom_row = BWTEnum.NOVALUE;
int			last_visible;
int			last_row = BWTEnum.NOVALUE;
boolean		auto_select = false;
Object[]	userdata_list;

// private
Rectangle draw_rect = new Rectangle();
Insets vis = new Insets(0,0,0,0);

// Selections
boolean		did_selection = false;
int			last_selected_item = 0;
int			start_item = 0;
int			old_start_item = 0;
int			end_item = 0;
int			old_end_item = 0;
boolean		appending = false;
int			focus_row = -1;
boolean		kbd_select = false;
int 		selection_type;

boolean		wrap_around_search = true;

/** List of action listeners */
protected JCVector  actionListeners = new JCVector(0);

/** List of JCItemEvent/JCListEvent listeners */
protected JCVector  itemListeners = new JCVector(0);

private static final String base = "list";
private static int nameCounter = 0;

// Default number of visible rows
final static int 	DEFAULT_VISIBLE_ROWS = 4;

/** Parent window. */
protected JCScrolledWindow scrolled_window = null;

/** Creates an empty 4-row list.
 *  No parameters are read from an HTML file.
 */
public JCListComponent() {
	this(null, null, null);
}

/** Creates a 4-row list with the specified items.
 * No parameters are read from an HTML file.
 * @see #setItems
 */
public JCListComponent(JCVector items) {
	this(items, null, null);
}

/** Creates a 4-row list which reads parameters from the applet's HTML file.
 * @param items the initial items
 * This may be a String, JCString or any object (in which case the object's
 * toString() method is called to obtain a string).
 * @param applet the applet whose PARAM tags are to be read
 * @param name if this is not null, only parameters preceded by this name are read
 * @see #setItems
 * @see java.applet.Applet#getParameter
 * @see jclass.util.JCString
 */
public JCListComponent(JCVector items, Applet applet, String name) {
	super(applet, name);
	if (name == null)
		setName(base + nameCounter++);
	insets = new Insets(5,5,5,5);
	if (getClass().getName().equals("jclass.bwt.JCListComponent"))
		 getParameters(applet);
	if (items != null)
		setItems(items);
}

/**
 * Creates a list with the specified number of visible lines.
 * @param rows the number of items to show (default: 4).
 * If set to BWTEnum.VARIABLE, the list will attempt to show all its items.
 * @param multipleSelections if true then multiple selections are allowed.
 */
public JCListComponent(int rows, boolean multipleSelections) {
	this(null, null, null);
	visible_rows = rows;
	multiple_select = multipleSelections;
}

/** Reads the parameter values from the HTML page using the component's applet.
 * The values will override those previously set.
 */
protected void getParameters() {
	super.getParameters();
	ListConverter.getParams(this);
}

/**
 * Adds the specified item listener to receive item events from this list.
 * If the listener implements JCListListener (an JCItemListener subclass),
 * it will be passed JCListEvents.
 * @see JCItemEvent
 * @see JCListEvent
 * @see JCListListener
 */
public void addItemListener(JCItemListener l) {
	itemListeners.addUnique(l);
}

/**
 * Removes the specified item listener so it no longer receives
 * item events from this list.
 */
public void removeItemListener(JCItemListener l) {
	itemListeners.removeElement(l);
}

/**
 * Adds the specified action listener to receive action events
 * from this list. Action events occur when a list item is double-clicked.
 * @see JCActionEvent
 */
public void addActionListener(JCActionListener l) {
	actionListeners.addUnique(l);
}

/**
 * Removes the specified action listener so it no longer receives
 * action events from this list.
 * @see #addActionListener
 */
public void removeActionListener(JCActionListener l) {
	actionListeners.removeElement(l);
}

/**
 * Sets the font of the component.
 * <strong>HTML param name/value:</strong> "Font"/font<p>
 * @see jclass.util.JCConverter#toFont
 */
public void setFont(Font f) {
	synchronized (this) {
		if (getFont() != null && getFont().equals(f)) return;
		super.setFont(f);
	}
	calcSize(true);
}

/** Gets the UserDataList value.
 * @see #setUserDataList
 */
public Object[] getUserDataList() { return userdata_list; }

/** Sets an application-defined list of objects that are maintained for each
 * item.
 */
public void setUserDataList(Object[] v) {
	userdata_list = v;
	updateUserdataList();
}

/** Gets the list's values.
 * @see #setItems
 */
public JCVector getItems() { return items; }

/** Returns the item at the specified row.
 * @see #setItems
 */
public Object getItem(int row) { return items.elementAt(row); }

/** Sets the list's values as an array of Strings. */
public void setItems(String[] items) {
	setItems(new JCVector(items));
}

/** Gets the list's values as a list of Strings.
 * @see #setItems
 */
public synchronized String[] getItemsStrings() {
	String[] list = new String[items.size()];
	for (int i=0; i < list.length; i++) {
		Object o = items.elementAt(i);
		list[i] = o != null ? o.toString() : "";
	}
	return list;
}

/** Sets the list's values as a Vector of objects.<p>
 * <strong>HTML param name/value:</strong> "Items"/string list<p>
 * @param items Each object may be a String, JCString or any object
 * (in which case the object's toString() method is called to obtain a string).
 * @see jclass.util.JCString
 * @see jclass.util.JCConverter#toVector
 */
public void setItems(JCVector items) {
	synchronized (this) {
		this.items = (items != null) ? items : new JCVector();
		for (int i=0; i < selected.length; i++)
			selected[i] = last_selected[i] = false;
		// lose row focus
		focus_row = -1;
	}
	calcSize(true);
   	repaint();
}

/** Gets the Batched value.
 * @see #setBatched
 */
public boolean getBatched() { return batched; }

/** If false (default), the component is repainted every time an item
 * is changed. If set to true, the component is only repainted (if necessary)
 * when the value is again set to false.
 */
public void setBatched(boolean v) {
	if (batched == v) return;
	batched = v;
	if (batched) return;
	if (needs_recalc)
		calcSize(true);
	if (needs_recalc || needs_repaint)
		repaint();
	needs_recalc = needs_repaint = false;
}

/** Gets the spacing between items.
 * @see #setSpacing
 */
public int getSpacing() { return spacing; }

/** Sets the spacing between items (default: 0).
 * This value increases the HighlightThickness value.<p>
 * <strong>HTML param name/value:</strong> "Spacing"/int
 * @see JCComponent#setHighlightThickness
 */
public void setSpacing(int v) {
	spacing = v;
	calcSize(true);
	repaint();
}

/*
 * Gets the WrapAroundSearch value.
 * @see #setWrapAroundSearch
 * Unexposed until necessary.
 */
protected boolean getWrapAroundSearch() { return wrap_around_search; }

/**
 * Sets the WrapAroundSearch value.
 * If true (default), search by key entered will wrap around to the
 * beginning/end of the list to find the next/previous item.
 * Unexposed until necessary.
 */
protected void setWrapAroundSearch(boolean v) {
	wrap_around_search = v;
}

/** Gets the number of items in the list.
 */
public int countItems() { return items.size(); }

/**
 * Adds or inserts an item to the list.
 * @param item the item to be added.
 * This may be a String, JCString or any object (in which case the object's
 * toString() method is called to obtain a string).
 * @param row the position at which to put the item.
 * If row is -1, or greater than the number of items in the
 * list, the item is added to the end.
 * @see jclass.util.JCString
 */
public void addItem(Object item, int row) {
	synchronized (this) {
		if (row < 0 || row >= items.size()) {
			row = items.size();
			items.addElement(item);
		}
		else
			items.insertElementAt(item, row);
	}
	calcSize(row, true);
	repaint();
}

/** Adds an item to the end of the list.
 * @param item the item to be added.
 * This may be a String, JCString or any object (in which case the object's
 * toString() method is called to obtain a string).
 */
public void addItem(Object item) {
	addItem(item, BWTEnum.MAXINT);
}

/**
 * Replaces the item at the specified row.
 */
public void replaceItem(Object item, int row) {
	synchronized (this) {
		if (row >= items.size()) return;

		items.removeElementAt(row);
		if (row < 0 || row >= items.size()) {
			row = items.size();
			items.addElement(item);
		}
		else
			items.insertElementAt(item, row);
	}
	calcSize(row, true);
	repaint();
}

/**
 * Clears all items in the list.
 * @see #deleteItems
 */
public void clear() {
	setItems((JCVector) null);
}


private void delete(int start_row, int end_row) {
	end_row = Math.min(items.size()-1, end_row);
	for (int i=end_row; i >= start_row; i--)
		items.removeElementAt(i);

	if (selected.length > start_row) {
		System.arraycopy(selected, end_row+1, selected,
						 start_row, selected.length - end_row - 1);
		System.arraycopy(last_selected, end_row+1, last_selected,
						 start_row, selected.length - end_row - 1);
	}
	if (userdata_list != null && userdata_list.length > start_row)
		System.arraycopy(userdata_list, end_row+1, userdata_list,
						 start_row, userdata_list.length - end_row - 1);
}

/**
 * Deletes multiple items from the list.
 * #see clear
 */
public void deleteItems(int start_row, int end_row) {
	synchronized (this) {
		delete(start_row, end_row);
	}

	if (focus_row != -1) {
		if (focus_row >= start_row && focus_row <= end_row) {
			// focus was in the deleted range so we loose it
			focus_row = -1;
		}
		if (focus_row > end_row) {
			// move focus row up by the amount of rows deleted
			focus_row -= (end_row - start_row);
			if (focus_row < -1) {
				focus_row = -1;
			}
		}
	}

	calcSize(true);
	repaint();
}

/**
 * Delete an item from the list.
 */
public void deleteItem(int row) { deleteItems(row, row); }

/**
 * Removes the first occurrence of an item from the list.
 * @exception IllegalArgumentException If the item doesn't exist
 */
public void remove(String item) {
	synchronized (this) {
		int index = items.indexOf(item);
		if (index < 0)
			throw new IllegalArgumentException("item "+item+" not found in JCList");
		else
			deleteItems(index, index);
	}
	calcSize(true);
	repaint();
}

/**
 * Gets the first selected row, or BWTEnum.NOTFOUND if no item is selected.
 * @see #select
 * @see #deselect
 * @see #isSelected
 */
public synchronized int getSelectedIndex() {
	for (int row = 0, num_items = items.size();
		 row < selected.length && row < num_items; row++) {
		if (selected[row]) {
			return row;
		}
	}
	return BWTEnum.NOTFOUND;
}

/** Gets the current top-most visible row.
 * @see #setTopRow
 */
public int getTopRow() { return top_row; }

/** Sets the top-most visible row.
 * @see #makeVisible
 */
public void setTopRow(int row) {
	top_row = row;
	vert_origin = BWTEnum.NOVALUE;
	repaint();
}

/**
 * Forces the item at the specified row to be visible.
 */
public void makeVisible(int row) {
	if (row < 0 || row >= items.size()) return;

	int value;
	last_visible = row;
	if (row <= top_row)
		value = getRowPosition(row);
	else if (row == items.size()-1)
		value = BWTEnum.MAXINT;
	else if (row >= bottom_row)
		value = vert_origin + getRowPosition(row+1) - getRowPosition(bottom_row);
	else
		return;
	if (scrolled_window != null)
		scrolled_window.scrollVertical(value);
}

/**
 * Gets the index of the item that was last made visible by makeVisible().
 * @see #makeVisible
 */
public int getVisibleIndex() { return last_visible; }

/**
 * Returns the selected item on the list, or null if no item is selected.
 * @see #select
 * @see #deselect
 * @see #isSelected
 */
public synchronized Object getSelectedItem() {
	int row = getSelectedIndex();
	return (row >= 0 && row < items.size()) ? items.elementAt(row) : null;
}

/**
 * Returns the selected items on the list as Strings, or null if no item is selected.
 * @see #select
 * @see #deselect
 * @see #isSelected
 */
public Object[] getSelectedObjects() {
	int[] sel_list = getSelectedIndexes();
	if (sel_list == null)
		return null;
	Object[] list = new Object[sel_list.length];
	for (int i=0; i < sel_list.length; i++)
		list[i] = items.elementAt(sel_list[i]);
	return list;
}

/**
 * Returns a list of the selected rows, or null if no items are selected.
 * @see #select
 * @see #deselect
 * @see #isSelected
 */
public synchronized int[] getSelectedIndexes() {
	int i, num_items, len=0;
	for (i = 0, num_items = items.size();
		 i < selected.length && i < num_items; i++)
		if (selected[i]) len++;

	if (len == 0) return null;
	int[] list = new int[len];
	for (i = 0, len = 0; i < selected.length && i < num_items; i++)
		if (selected[i]) list[len++] = i;

	return list;
}

/**
 * Deselects the item at the specified row, and posts an JCItemEvent.
 * @see #select
 * @see #getSelectedIndex
 * @see #isSelected
 * @see #addItemListener
 */
public void deselect(int row) {
	JCVector il = null;
	JCItemEvent ev = null;
	boolean paint_row = false;
	boolean deselect_all = false;

	synchronized (this) {
		if (row >= selected.length || !selected[row])
			return;
		if (multiple_select)
			deselect_all = true;
		else {
			selected[row] = last_selected[row] = false;
			paint_row = true;
		}

		JCListInterface target = this;
		if (scrolled_window != null) target = (JCListInterface) scrolled_window;

		ev = new JCItemEvent(target, Event.LIST_DESELECT,
									 items.elementAt(row), JCItemEvent.DESELECTED);
		il = (JCVector) itemListeners.clone();
	}
	if (deselect_all) deselectAll();
	if (paint_row) paintRow(row);

	if (il != null && il.size() > 0) {
		for (int i=0; i < il.size(); i++)
			((JCItemListener)il.elementAt(i)).itemStateChanged(ev);
	}
}

/** Deselects all items. No events are posted */
public void deselectAll() {
	int[] paint_rows = new int[0];
	int num_paint_rows = 0;
	int num_rows;
	synchronized (this) {
		num_rows = items.size();
		paint_rows = new int[num_rows];
		for (int i = 0; i < selected.length && i < num_rows; i++) {
			if (selected[i]) {
				selected[i] = last_selected[i] = false;
				// Record row to be painted later outside monitor.
				paint_rows[num_paint_rows] = i;
				num_paint_rows++;
			}
		}
	}
	// Paint rows outside monitor to avoid deadlock.
	for (int i=0; i<num_paint_rows; i++) {
		paintRow(paint_rows[i]);
	}
}

/**
 * Selects the item at the specified row, and posts a JCListEvent.
 * If allowsMultipleSelections is false, any previously selected rows
 * are deselected.
 * @see #setAllowMultipleSelections
 * @see #addItemListener
 */
public void select(int row) { select(row, true); }

/**
 * Selects the specified item.
 * @see #getSelectedItem
 * @see #getSelectedIndex
 */
public void select(Object item) { select(item, true); }

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
public void select(Object item, boolean notify) {
	int index = items.indexOf(item);
	if (index >= 0)
		ListSelection.select(this, index, notify, null);
}

/**
 * Selects the item at the specified row, and posts a JCListEvent.
 * If allowsMultipleSelections is false, any previously selected rows
 * are deselected.
 * @param notify if true, a JCListEvent is posted.
 * @see #setAllowMultipleSelections
 * @see #addItemListener
 */
public void select(int row, boolean notify) {
	ListSelection.select(this, row, notify, null);
}

/** Finds the first row matching the specified item, or BWTEnum.NOTFOUND. */
public int find(Object item) {
	int index = items.indexOf(item);
	return index >= 0 ? index : BWTEnum.NOTFOUND;
}

/** Returns true if row is inside the inset. */
static boolean inside(Insets r, int row) {
	return r != null
		&& Math.min(r.top, r.bottom) <= row
		&& row <= Math.max(r.top, r.bottom);
}

/**
 * Returns true if the item at the specified row has been selected.
 * @see #select
 * @see #deselect
 */
public synchronized boolean isSelected(int row) {
	return (row < selected.length
			&& row >= 0 && row < items.size()) ? selected[row] : false;
}

/**
 * Returns true if this list allows multiple selections.
 * @see #setAllowMultipleSelections
 */
public boolean allowsMultipleSelections() { return multiple_select; }

/**
 * Returns true if this list allows multiple selections.
 * @see #setAllowMultipleSelections
 */
public boolean getAllowMultipleSelections() { return multiple_select; }

/**
 * Sets whether the list allows multiple selections.
 * <strong>HTML param name/value:</strong> "AllowMultipleSelections"/boolean
 * @see #allowsMultipleSelections
 */
public void setAllowMultipleSelections(boolean v) {
	multiple_select = v;
}

/** Gets the bounding rectangle of a row, or null if the row is invalid.
 * @param adjust if true, the co-ordinates are adjusted by the amount
 * that the list has been scrolled
 * @see #makeVisible
 */
public Rectangle getBounds(int row, boolean adjust) {
	if (row < 0 || row >= items.size()) return null;
	getDrawingArea(draw_rect);
	JCComponent.setBounds(draw_rect, draw_rect.x - (adjust ? horiz_origin : 0),
			 draw_rect.y + getRowPosition(row) - (adjust ? vert_origin : 0),
			 Math.max(draw_rect.width, preferredWidth()),
			 getRowHeight(row));
	return draw_rect;
}

/** Gets the AutoSelect value.
 * @see #setAutoSelect
 */
public boolean getAutoSelect() { return auto_select; }

/** If true, the item which currently has focus is also selected (default: false).<p>
 * <strong>HTML param name/value:</strong> "AutoSelect"/boolean
 * @see #select
 */
public void setAutoSelect(boolean v) {
	auto_select = v;
	repaint();
}

/** Gets the height of the row calculated previously. */
protected int getRowHeight(int row) {
	if (row_height_ext != BWTEnum.VARIABLE)
		return row_height;
	return row < row_heights.length ? row_heights[row] : 0;
}

/** Calculates the width of the row. */
protected int calcRowWidth(int row) {
	return BWTUtil.getWidth(items.elementAt(row), this);
}

/** Gets the position of the row calculated previously. */
protected int getRowPosition(int row) {
	row = Math.max(0, Math.min(row, items.size()-1));
	if (row_height_ext != BWTEnum.VARIABLE)
		return row * row_height;
	if (row_pos == null) return 0;
	return row < row_pos.length ? row_pos[row] : 0;
}

/** Gets the RowHeight value.
 * @see #setRowHeight
 */
public int getRowHeight() { return row_height_ext; }

/** Sets the height of each row (pixels).<p>
 * <strong>HTML param name/value:</strong> "RowHeight"/an int, "VARIABLE", or "FONT_HEIGHT"<br>
 * @param h any int, or one of the following:<p>
 * If set to BWTEnum.VARIABLE, the height will be calculated individually
 * for each row.<p>
 * If set to BWTEnum.FONT_HEIGHT (default), the height will be set to the
 * height of the current font.
 * @exception IllegalArgumentException If an invalid value is set
 */
public void setRowHeight(int h) {
	if (!(h >= 0 || h == BWTEnum.FONT_HEIGHT || h == BWTEnum.VARIABLE))
		throw new IllegalArgumentException("invalid row height: " + h);
	row_height_ext = h;
	calcSize(true);
	repaint();
}

/** Checks whether point is inside the row. */
private boolean inside(int row, int x, int y) {
	getDrawingArea(draw_rect);
	y += vert_origin - draw_rect.y;
	x += horiz_origin;
	return x >= draw_rect.x
		&& x - draw_rect.x < draw_rect.width
		&& y >= getRowPosition(row)
		&& y-getRowPosition(row) < getRowHeight(row);
}

/** Gets the row specified by the event, or BWTEnum.NOTFOUND.
 * @param visible if true, the row must be visible
 */
public int eventToRow(Event ev, boolean visible) {
	if (!isDisplayable() || items.size() == 0)
		return BWTEnum.NOTFOUND;

	getVisibleRange(vis);

  // getBounds() can return null if the is deleted while eventToRow is being called.
  Rectangle bound = getBounds(vis.top,true);
  if(bound == null) {
      return BWTEnum.NOTFOUND;
  }
	if (bound.inside(ev.x, ev.y))
		return vis.top;

  // getBounds() can return null if the is deleted while eventToRow is being called.
  bound = getBounds(vis.bottom,true);
  if(bound == null){
      return BWTEnum.NOTFOUND;
  }
	if (bound.inside(ev.x, ev.y))
		return vis.bottom;

	getDrawingArea(draw_rect);
	if (visible && !draw_rect.inside(ev.x, ev.y+space()))
		return BWTEnum.NOTFOUND;

	int row, y = ev.y + vert_origin - draw_rect.y;

	if (ev.y > draw_rect.y + draw_rect.height) {
		for (row=vis.bottom; row < items.size(); row++)
			if (y < getRowPosition(row))
				return row-1;
		return BWTEnum.NOTFOUND;
	}

	else if (ev.y < draw_rect.y) {
		for (row=vis.top; row >= 0; row--)
			if (y > getRowPosition(row))
				return row;
		return BWTEnum.NOTFOUND;
	}

	for (row=vis.top+1; row <= Math.min(items.size(), vis.bottom+1); row++) {
		if (y < getRowPosition(row))
			return row-1;
	}

	if (vis.bottom == items.size()-1
		&& y < getRowPosition(vis.bottom) + getRowHeight(vis.bottom))
		return vis.bottom;

	return BWTEnum.NOTFOUND;
}

/**
 * Returns the number of visible lines.
 * @see #setVisibleRows
 */
public int getRows() { return visible_rows; }

/**
 * Returns the number of visible lines.
 * @see #setVisibleRows
 */
public int getVisibleRows() { return visible_rows; }

/** Sets the number of visible rows (default: 4).
 * If set to BWTEnum.VARIABLE, the list
 * will attempt to resize itself so that all its items are visible.
 * @see #setItems
 */
public void setVisibleRows(int v) {
	visible_rows = v;
	calcSize(true);
}

/** Returns the top and bottom-most visible rows.
 * @param range returned
 */
public void getVisibleRange(Insets range) {
	if (bottom_row < 0) calcBottomRow();
	range.top = top_row;
	range.bottom = bottom_row;
}

/**
 * Gets the list's SelectedBackground value.
 * @see #setSelectedBackground
 */
public Color getSelectedBackground() {
/* JDK110_START */
	if (selected_bg == null && use_system_colors)
		return SystemColor.textHighlight;
/* JDK110_END */
	return selected_bg != null ? selected_bg : getForeground();
}

/**
 * Sets the background color of selected rows (default: foreground color).<p>
 * <strong>HTML param name/value:</strong> "SelectedBackground"/color
 * @see jclass.util.JCConverter#toColor
 */
public void setSelectedBackground(Color v) {
	selected_bg = v;
	if (getSelectedIndex() >= 0)
		repaint();
}

/**
 * Gets the list's SelectedForeground value.
 * @see #setSelectedForeground
 */
public Color getSelectedForeground() {
/* JDK110_START */
	if (selected_fg == null && use_system_colors)
		return SystemColor.textHighlightText;
/* JDK110_END */
	return selected_fg != null ? selected_fg : getBackground();
}

/**
 * Sets the foreground color of selected rows (default: background color).<p>
 * <strong>HTML param name/value:</strong> "SelectedForeground"/color
 * @see jclass.util.JCConverter#toColor
 */
public void setSelectedForeground(Color v) {
	selected_fg = v;
	if (getSelectedIndex() >= 0)
		repaint();
}

/** Finds the previous item which starts with the specified character, or
 * BWTEnum.NOTFOUND if none could be found.
 * @param c		the character to search
 * @param end 	the ending item index. The search includes this item.
 */
public int findPrevItem(char c, int end) {
	c = Character.toUpperCase(c);
	end = Math.min(end, items.size());
	for (int i=end; i >= 0; i--)
		if (BWTUtil.startsWith(items.elementAt(i), c))
			return i;
	return BWTEnum.NOTFOUND;
}

/** Finds the next item which starts with the specified character, or
 * BWTEnum.NOTFOUND if none could be found.
 * @param c		the character to search
 * @param start the starting item index. The search includes this item.
 */
public int findNextItem(char c, int start) {
	c = Character.toUpperCase(c);
	for (int i=start; i < items.size(); i++)
		if (BWTUtil.startsWith(items.elementAt(i), c))
			return i;
	return BWTEnum.NOTFOUND;
}

/** Finds the first item which starts with the specified character, or
 * BWTEnum.NOTFOUND if none could be found.
 */
public int findItem(char c) {
	return findNextItem(c, 0);
}

/** Calculates the list's internal sizes. */
protected void calcSize(boolean update_parent) {
	if (!isDisplayable())
		return;

	synchronized(this) {
		if (batched) {
			needs_recalc = true;
			return;
		}

		// Update selected item list
		updateSelectedList();

		// Update userdata list
		updateUserdataList();

		int total_item_height = 0;
		row_height = 0;
		if (row_height_ext != BWTEnum.VARIABLE) {
			row_heights = row_pos = null;
			if (row_height_ext == BWTEnum.FONT_HEIGHT)
				row_height = getToolkit().getFontMetrics(getFont()).getHeight()
					+ space();
			else
				row_height = row_height_ext;
			total_item_height = items.size() * row_height;
		}

		else {
			if (row_heights == null || items.size() > row_heights.length) {
				row_heights = new int[items.size()];
				row_pos = new int[items.size()];
			}
			for (int i=0; i < items.size(); i++) {
				row_heights[i] =
					BWTUtil.getHeight(items.elementAt(i), this) + space();
				if (i > 0)
					row_pos[i] = row_pos[i-1] + row_heights[i-1];
				total_item_height += row_heights[i];
			}
		}

		// Calculate preferred height
		if (visible_rows > 0) {
			if (visible_rows < items.size())
				pref_height_internal = prevItemPos(visible_rows);
			else {
				int h = (total_item_height > 0) ? total_item_height : row_height;
				if (h == 0)
					h = getToolkit().getFontMetrics(getFont()).getHeight() + space();
				int avg = h / Math.max(1, items.size());
				int extra = Math.max(0, visible_rows - items.size() - 1);
				pref_height_internal = h + extra * avg;
			}
		}
		else {
			int h = row_height;
			if (h == 0)
				h = getToolkit().getFontMetrics(getFont()).getHeight() + space();
			pref_height_internal = h * DEFAULT_VISIBLE_ROWS;
			if (visible_rows == BWTEnum.VARIABLE)
				pref_height_internal = Math.max(total_item_height, pref_height_internal);
		}

		// Only calculated if requested by preferredWidth()
		pref_width_internal = 0;
	}


	if (update_parent)
		updateParent();
}

/** Enlarges the array if necessary to the number of items. */
synchronized int[] ensureCapacity(int[] array) {
	if (array != null && items.size() < array.length)
		;
	else if (array == null)
		array = new int[items.size()];
	else {
		int array_len = array.length;
		int[] old_array = array;
		array = new int[items.size()];
		System.arraycopy(old_array, 0, array, 0, array_len);
	}
	return array;
}

/** Recalculates the list's internal sizes based on the row.
 * @param new_row whether this is a new item
 */
protected void calcSize(int row, boolean new_row) {
	if (!isDisplayable())
		return;
	int old_len = 0;

	synchronized(this) {
		if (batched) {
			needs_recalc = true;
			return;
		}

		// Update selected item list
		old_len = selected.length;
		updateSelectedList();

		if (new_row) {
			if (old_len < selected.length && row < old_len) {
				System.arraycopy(selected, row, selected, row+1, old_len-row);
				System.arraycopy(last_selected, row, last_selected, row+1, old_len-row);
			}
			selected[row] = last_selected[row] = false;
		}

		// Update userdata list
		old_len = userdata_list != null ? userdata_list.length : 0;
		updateUserdataList();

		if (new_row && userdata_list != null) {
			if (old_len < userdata_list.length && row < old_len)
				System.arraycopy(userdata_list, row,
								 userdata_list, row+1, old_len-row);
			if (row >=0 && row<userdata_list.length) {
				userdata_list[row] = null;
			}
		}
	}

	// Do not put calcSize() in monitor to avoid deadlock.
	if (row_height_ext != BWTEnum.VARIABLE) {
		calcSize(true);
		return;
	}

	synchronized (this) {
		if (new_row) {
			old_len = row_heights.length;
			row_heights = ensureCapacity(row_heights);
			row_pos = ensureCapacity(row_pos);
			if (old_len < row_heights.length && row < old_len)
				System.arraycopy(row_heights, row, row_heights, row+1, old_len-row);
		}
		row_heights[row] = BWTUtil.getHeight(items.elementAt(row), this) + space();

		for (int i=1; i < items.size(); i++)
			row_pos[i] = row_pos[i-1] + row_heights[i-1];

		if (visible_rows == 0) {
			pref_height_internal = 0;
			for (int i=0; i < items.size(); i++)
				pref_height_internal += row_heights[i];
		}

		// Only calculated if requested by preferredWidth()
		pref_width_internal = 0;
	}	// synchronized()
	updateParent();
}

/** Updates the selected item list after an item has changed. */
protected synchronized void updateSelectedList() {
	selected = BWTUtil.copyList(selected, items.size(), false);
	last_selected = BWTUtil.copyList(last_selected, items.size(), false);
}

/** Updates the userdata list after an item has changed. */
protected synchronized void updateUserdataList() {
	if (userdata_list != null)
		userdata_list = BWTUtil.copyList(userdata_list, items.size(), null);
}

/** Calculates the widest row. */
protected int calcWidestRow() {
	int w = 0;
	for (int i=0; i < items.size(); i++)
		w = Math.max(w, BWTUtil.getWidth(items.elementAt(i), this));
	return w + 2*highlight;
}

/** Gets the position of the previous item. */
private int prevItemPos(int row) {
	return (row > 0) ? getRowPosition(row-1) + getRowHeight(row-1) : 0;
}

private int space() { return 2*highlight + spacing; }

/** Repaints the component if Batched is false.
 * @see #setBatched
 */
public void repaint() {
	if (batched)
		needs_repaint = true;
	else
		super.repaint();
}

/** Calculate the item sizes. */
public void addNotify() {
	super.addNotify();
	calcSize(false);
	/* JDK110_START */
	enableEvents(AWTEvent.MOUSE_MOTION_EVENT_MASK);
	/* JDK110_END */
}

/** Returns the width of the widest item. */
protected int preferredWidth() {
	if (pref_width_internal == 0)
		pref_width_internal = calcWidestRow();
	return pref_width_internal;
}

/** Returns the visible height, if it has been set, or the sum of all item heights. */
protected int preferredHeight() { return pref_height_internal; }

/** Draws a row. */
public void paintRow(int row) {
	if (!isShowing() || row < top_row || row > bottom_row) return;
	if (row < 0 || row >= items.size()) return;
	paintRow(row, null);
}

/** Draws the value as a String, JCString or Image.
 * @param alignment one of:
 BWTEnum.TOPLEFT, TOPCENTER, TOPRIGHT, MIDDLELEFT, MIDDLECENTER, MIDDLERIGHT, BOTTOMLEFT, BOTTOMCENTER and BOTTOMRIGHT
 * @param draw_rect rectangle within which to draw the value
 */
protected void
draw(Graphics gc, Object value, int alignment, Rectangle draw_rect) {
	BWTUtil.draw(this, gc, value, alignment, draw_rect);
}

/** Draws or clears the highlight rectangle around the row. */
protected void drawHighlight(int row, boolean on) {
	drawHighlight(null, row, on);
}

/** Draws or clears the highlight rectangle around the row. */
protected void drawHighlight(Graphics gc, int row, boolean on) {
	if (!isShowing() || row < 0 || row >= items.size()) return;
	getDrawingArea(draw_rect);
	int w = preferredWidth();
	if (w > draw_rect.width) w += 2*(border+highlight);
	JCComponent.setBounds(draw_rect, draw_rect.x - horiz_origin,
			 draw_rect.y + getRowPosition(row) - vert_origin,
			 Math.max(draw_rect.width, w),
			 getRowHeight(row));

	Rectangle rect = getDrawingArea();
	Color old_color = (gc != null) ? gc.getColor() : null;
	boolean dispose_gc = false;
	if (gc == null) {
		dispose_gc = true;
		gc = getGraphics();
		gc.clipRect(rect.x, rect.y, rect.width, rect.height);
	}

	Color c;
	if (on) {
		c = getHighlightColor();
	} else {
		// Use selected bg if the row is selected.
		// An item can be selected but not highlighted in multiple selection.
		c = isSelected(row) ? getSelectedBackground() : getBackground();
	} 
	if (c == null) c = getForeground();

	if (on && isSelected(row))
		gc.setColor(Color.yellow);
	else 
		gc.setColor(c);

	if (on)
		BWTUtil.drawDashedRect(gc, draw_rect.x, draw_rect.y,
							   draw_rect.width-1, draw_rect.height-1);
	else {
		gc.drawRect(draw_rect.x, draw_rect.y,
					draw_rect.width-1, draw_rect.height-1);
	}
	if (dispose_gc)
		gc.dispose();
	else if (old_color != null)
		gc.setColor(old_color);
}

Rectangle clip_rect = new Rectangle();

/** Draws a row. */
protected void paintRow(int row, Graphics gc) {
	getDrawingArea(clip_rect);
	JCComponent.setBounds(draw_rect, clip_rect.x - horiz_origin,
					  clip_rect.y + getRowPosition(row) - vert_origin,
					  clip_rect.width + horiz_origin, getRowHeight(row));
	boolean dispose_gc = false;

	if (gc == null) {
		dispose_gc = true;
		gc = getGraphics();
		gc.clipRect(clip_rect.x, clip_rect.y, clip_rect.width, clip_rect.height);
		if (row < selected.length && !selected[row]) {
			gc.setColor(getBackground());
			gc.fillRect(draw_rect.x, draw_rect.y,
						draw_rect.width, draw_rect.height);
		}
		if (!isEnabled())
			gc.setColor(Color.lightGray.darker().darker());
		else
			gc.setColor(getForeground());
		gc.setFont(getFont());
	}
	else if (!draw_rect.intersects(getPaintRect()))
		return;

	if (row < selected.length && selected[row]) {
		gc.setColor(getSelectedBackground());
		gc.fillRect(draw_rect.x, draw_rect.y,
					draw_rect.width, draw_rect.height);
		gc.setColor(getSelectedForeground());
	}
	if (isEnabled() && row == focus_row)
		drawHighlight(gc, row, true);

	draw_rect.translate(highlight, highlight);
	draw_rect.width -= 2 * highlight;
	draw_rect.height -= 2 * highlight;

  try {
      Object item = items.elementAt(row);
    	if(items != null) {
		      draw(gc, items.elementAt(row), BWTEnum.MIDDLELEFT, draw_rect);
      }
  }
  catch (ArrayIndexOutOfBoundsException e) {
      // the row has been deleted out from under us so just move on
  }

	if (dispose_gc)
		gc.dispose();
	else if (row < selected.length && selected[row])
		gc.setColor(getForeground());
}

void setFocus(int row) {
	if (row == focus_row) return;
	if (focus_row >= 0 && focus_row < items.size())
		drawHighlight(focus_row, false);
    focus_row = row;
    drawHighlight(focus_row, true);
	if (auto_select)
		ListSelection.selectFocusRow(this, null);
}

void calcBottomRow() {
	int origin = (vert_origin != BWTEnum.NOVALUE) ?
		vert_origin : getRowPosition(top_row);
	int row, h = origin + getDrawingAreaHeight();
	for (row=top_row+1; row < items.size(); row++) {
		if (getRowPosition(row) >= h)
			break;
	}
	bottom_row = row - 1;
}

/**
 * Draws items
 */
protected void paintComponent(Graphics gc) {
	if (items.size() == 0)
		return;

	top_row = Math.max(0, Math.min(top_row, items.size()-1));

	if (vert_origin == BWTEnum.NOVALUE)
		vert_origin = getRowPosition(top_row);
	calcBottomRow();

	Color old_color = null;
	if (!isEnabled()) {
		old_color = gc.getColor();
		gc.setColor(Color.lightGray.darker().darker());
	}
	for (int row=top_row; row <= bottom_row; row++)
		paintRow(row, gc);

	if (!isEnabled())
		gc.setColor(old_color);
}

/** Called when the user double-clicks an item. An JCActionEvent is posted.
 * @see #addActionListener
 */
protected boolean doubleClickAction(Event ev, int row) {
	if (row < 0 || row >= selected.length) return false;
	if (!selected[row])
		select(row, true);

	if (scrolled_window != null)
		ev.target = scrolled_window;

	JCActionEvent a = new JCActionEvent(ev.target, ev.id, null);
	for (int i=0; i < actionListeners.size(); i++)
		((JCActionListener)actionListeners.elementAt(i)).actionPerformed(a);

	return true;
}

/**
 * Extends the selection to a new row if AllowMultipleSelections is true.
 * @see #setAllowMultipleSelections
 */
public boolean mouseDrag(Event ev, int x, int y) {
	return ListSelection.mouseDrag(this, ev);
}

/**
 * If the event's clickCount is greater than 1 calls doubleClickAction.<p>
 * If the SHIFT key is pressed, extends the current selection.<p>
 * If the CONTROL key is pressed, toggles the current selection.<p>
 * Otherwise the click point is toggled.
 */
public boolean mouseDown(Event ev, int x, int y) {
    //bug 10274 ->
    if(this.isEnabled() == false)
        return true;
    //<-bug 10274

	super.mouseDown(ev, x, y);
	if (BWTUtil.getMouseButton(ev) != 1) return false;

	// Ignore if user just made a combobox selection (bug 2048)
	long dt = ev.when - JCComponent.popdown_event_timestamp;
	if (dt >= 0 && dt < 50)
		return true;

	if (ev.clickCount > 1) {
		int row = eventToRow(ev, true);
		if (row == last_row)
			return doubleClickAction(ev, row);
		ev.clickCount = 1;
	}

	if (multiple_select && ev.shiftDown())
		return ListSelection.beginExtend(this, ev);
	else if (multiple_select && ev.controlDown())
		return ListSelection.beginToggle(this, ev);
	else {
		return ListSelection.beginSelect(this, ev);
	}
}

/**
 * If the event's clickCount is greater than 1, no action is taken.<p>
 * If the SHIFT key is pressed, extension of the current selection is ended.<p>
 * If the CONTROL key is pressed, toggling of the current selection is ended.<p>
 * Otherwise the click point is toggled.
 */
public boolean mouseUp(Event ev, int x, int y) {
    //bug 10274 ->
    if(this.isEnabled() == false)
        return true;
    //<-bug 10274

	if (ev.clickCount > 1) {
		int row = eventToRow(ev, true);
		if (row == last_row)
			return true;
		ev.clickCount = 1;
	}

	if (multiple_select && ev.shiftDown())
		return ListSelection.endExtend(this, ev);
	else if (multiple_select && ev.controlDown())
		return ListSelection.endToggle(this, ev);
	else
		return ListSelection.endSelect(this, ev);
}


/**
 *	Only support single character search.
 *  Always wrap around even though there is
 *  an unexposed WrapAroundSearch property.
 * 	Note: Arrow key only scrolls through the list without searching.
 */
protected int getNextAutoSearchIndex(Event ev, int key) {
	int row = BWTEnum.NOTFOUND;
	int selected_index = getSelectedIndex();
	int num_items = items.size();
	char search_char = ' ';

	if (num_items < 1) return BWTEnum.NOTFOUND;

	Object o = getSelectedItem();
	if (o != null) {
		if (o instanceof String) {
			search_char = ((String)o).charAt(0);
		} else if (o instanceof JCString) {
			search_char = ((JCString)o).getString().charAt(0);
		}
	}

	// New search.  Start from the top.
	if (!String.valueOf(search_char).equalsIgnoreCase(String.valueOf((char) key))) {
		selected_index = BWTEnum.NOTFOUND;
		search_char = (char) key;
	}

	if (search_char == ' ') {
		// Go to the next item.
		row = Math.max(0, selected_index+1);
		if (getWrapAroundSearch() && row >= num_items) {
			row = 0;
		}
		return row;
	}


	if (selected_index == BWTEnum.NOTFOUND) {
		// If no selected item, search from the top
		row = this.findItem(search_char);
	} else {
		// Have selection, find next item.
		if (selected_index+1 < num_items) {
			row = this.findNextItem(search_char, selected_index+1);
		}
		if (getWrapAroundSearch() && row == BWTEnum.NOTFOUND) {
			// wrap around and start search at the beginning
			row = this.findItem(search_char);
		}
	}

	return row;
}



/**
 * Moves selection up or down if arrow key hit; posts an JCActionEvent if ENTER
 * is hit; otherwise selects item which starts with the character.
 */
public boolean keyDown(Event ev, int key) {
	if (ev.key == Event.DOWN) {
		if (ev.controlDown() && ev.shiftDown())
			return ListSelection.ctrlShiftNextRow(this, ev);
		else if (ev.controlDown())
			return ListSelection.ctrlNextRow(this, ev);
		else if (ev.shiftDown())
			return ListSelection.shiftNextRow(this, ev);
		else {
			return ListSelection.normalNextRow(this, ev);
		}
	}

	else if (ev.key == Event.UP) {
		if (ev.controlDown() && ev.shiftDown())
			return ListSelection.ctrlShiftPrevRow(this, ev);
		else if (ev.controlDown())
			return ListSelection.ctrlPrevRow(this, ev);
		else if (ev.shiftDown())
			return ListSelection.shiftPrevRow(this, ev);
		else {
			return ListSelection.normalPrevRow(this, ev);
		}
	}

	// Post JCActionEvent if ENTER hit.
	else if (ev.key == 10 && focus_row >= 0)
		return doubleClickAction(ev, focus_row);

	// Select current focus row if space hit.
	else if (ev.key == (int)' ') {
		return ListSelection.kbdSelect(this, ev);
	}

	// Select item which starts with this char
	else {
		// Search by character entered.
		if (wrap_around_search == true) {
			int row = getNextAutoSearchIndex(ev, key);
			if (row != BWTEnum.NOTFOUND) {
				deselectAll();
				makeVisible(row);
				ListSelection.select(this, row, true, ev);
				return true;
			}
		}
	}

	return super.keyDown(ev, key);
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

/** Sets the vertical origin prior to scrolling. */
public void setVertOrigin(int value) {
	vert_origin = value;
	for (top_row = 0; top_row < items.size(); top_row++)
		if (getRowPosition(top_row) + getRowHeight(top_row) > value)
			break;
}

}
