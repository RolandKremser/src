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
package jclass.bwt;
import jclass.util.*;
import java.awt.*;

/** An interface which defines the API for components which have a list-like
 * behavior.
 * @see JCList
 * @see JCMultiColumnList
 */
public interface JCListInterface extends JCItemSelectable {

/**
 * Returns the number of items in the list.
 * @see #getItem
 */
public int countItems();

/** Gets the list's values.
 * @see #setItems
 */
public JCVector getItems();

/** Sets the list's values. */
public void setItems(JCVector items);

/**
 * Gets the item associated with the specified row.
 * @param row the position of the item
 * @see #countItems
 */
public Object getItem(int row);

/**
 * Adds a item to the end of the list.
 * @param item the item to be added
 */
public void addItem(Object item);

/**
 * Adds the specified item to the end of the list.
 * @param item the item to be added
 * @param row the position at which to put in the item;
 * if row is -1, or greater than the number of items
 * in the list, the item is added to the end
 */
public void addItem(Object item, int row);

/**
 * Replaces the item at the given row.
 * @param item the new value to replace the existing item
 * @param row the position of the item to replace
 */
public void replaceItem(Object item, int row);

/**
 * Clears the list.
 * @see #deleteItem
 * @see #deleteItems
 */
public void clear();

/**
 * Deletes an item from the list.
 */
public void deleteItem(int position);

/**
 * Deletes multiple items from the list.
 */
public void deleteItems(int start, int end);

/**
 * Get the selected item on the list or BWTEnum.NOTFOUND
 * if no item is selected.
 * @see #select
 * @see #deselect
 * @see #isSelected
 */
public int getSelectedIndex();

/**
 * Returns the selected indexes on the list, or null if no item is selected.
 * @see #select
 * @see #deselect
 * @see #isSelected
 */
public int[] getSelectedIndexes();

/**
 * Returns the selected item on the list, or null if no item is selected.
 * @see #select
 * @see #deselect
 * @see #isSelected
 */
public Object getSelectedItem();

/**
 * Returns the selected items on the list, or null if no items are selected.
 * @see #select
 * @see #deselect
 * @see #isSelected
 */
public Object[] getSelectedObjects();

/**
 * Selects the item at the specified row.
 * @param row the position of the item to select
 * @see #getSelectedObjects
 * @see #deselect
 * @see #isSelected
 */
public void select(int row);

/** Gets the AutoSelect value.
 * @see #setAutoSelect
 */
public boolean getAutoSelect();

/** If true, the item which currently has focus is also selected.
 * @see #select
 */
public void setAutoSelect(boolean v);

/**
 * Deselects the item at the specified row.
 * @param row the position of the item to deselect
 * @see #select
 * @see #getSelectedItem
 * @see #isSelected
 */
public void deselect(int row);

/**
 * Returns true if the item at the specified row has been selected;
 * otherwise false.
 * @param row the item to be checked
 * @see #select
 * @see #deselect
 * @see #isSelected
 */
public boolean isSelected(int row);

/**
 * Gets the list's SelectedBackground value.
 * @see #setSelectedBackground
 */
public Color getSelectedBackground();

/**
 * Sets the background color of selected rows.
 */
public void setSelectedBackground(Color v);

/**
 * Gets the list's SelectedForeground value.
 * @see #setSelectedForeground
 */
public Color getSelectedForeground();

/**
 * Sets the foreground color of selected rows.
 */
public void setSelectedForeground(Color v);

/**
 * Returns the number of visible lines in the list.
 */
public int getRows();

/** Sets the number of visible rows. If set to 0 (default), the list
 * will attempt to resize itself so that all its items are visible.
 * @see #setItems
 */
public void setVisibleRows(int v);

/**
 * Returns true if this list allows multiple selections.
 * @see #setAllowMultipleSelections
 */
public boolean allowsMultipleSelections();

/**
 * Sets whether this list should allow multiple selections or not.
 * @param v the boolean to allow multiple selections
 * @see #allowsMultipleSelections
 */
public void setAllowMultipleSelections(boolean v);

/**
 * Gets the row of the item that was last made visible by the method
 * makeVisible.
 */
public int getVisibleIndex();

/**
 * Forces the item at the specified row to be visible.
 * @param row the position of the item
 * @see #getVisibleIndex
 */
public void makeVisible(int row);

/** Gets the current top-most visible row.
 * @see #setTopRow
 */
public int getTopRow();

/** Sets the top-most visible row.
 * @see #makeVisible
 */
public void setTopRow(int row);

/** Sets the height of each row (pixels).<p>
 * The value may be any int, or one of the following:<p>
 * If set to BWTEnum.VARIABLE, the height will be calculated individually
 * for each row.<p>
 * If set to BWTEnum.FONT_HEIGHT (default), the height will be set to the
 * height of the current font.
 */
public void setRowHeight(int h);

/** Gets the spacing between items.
 * @see #setSpacing
 */
public int getSpacing();

/** Sets the spacing between items.
 * This value increases the HighlightThickness value.
 * @see JCComponent#setHighlightThickness
 */
public void setSpacing(int v);

}
