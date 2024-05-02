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
// RCSID -- $RCSfile: ListSelection.java $ $Revision: 2.11 $ $Date: 2000/11/09 20:11:26 $ $Locker: $  Sitraka Inc.

package jclass.bwt;
import jclass.util.*;
import java.awt.*;

/** Methods to process List selections. */
class ListSelection {

final static int BEGIN = 0;
final static int END = 1;

/**
 * Posts a JCListEvent event.
 */
private static boolean
postEvent(JCListComponent list, int stage, Event event, int row) {
	JCListInterface caller = (JCListInterface) list.scrolled_window;
	if (caller == null) caller = list;
	if (list.scrolled_window != null && event != null)
		event.target = caller;
	int old_id = (event != null) ? event.id : 0;
	boolean selected = (row < list.selected.length) ? list.selected[row] : false;
	int stateChange = selected ? JCItemEvent.DESELECTED : JCItemEvent.SELECTED;

	if (stage == END)
		stateChange = JCItemEvent.SELECTED;

	if (stage == END && event != null)
		event.id = selected ? Event.LIST_SELECT : Event.LIST_DESELECT;

	if (list.itemListeners.size() == 0) {
		return true;
	}

	// Send DESELECTED JCItemEvent to listeners
	else if (stage == END && !selected) {
		JCItemEvent item_ev =
			new JCItemEvent(caller, JCItemEvent.ITEM_STATE_CHANGED,
							list.getItem(row), JCItemEvent.DESELECTED);
		for (int i=0; i < list.itemListeners.size(); i++)
			((JCItemListener)list.itemListeners.elementAt(i)).itemStateChanged(item_ev);
		return true;
	}

	if (event != null)
		event.id = old_id;
	JCListEvent list_ev =
		new JCListEvent(caller, event, row, list.selection_type, stateChange);
	for (int i=0; i < list.itemListeners.size(); i++) {
		JCItemListener l = (JCItemListener) list.itemListeners.elementAt(i);
		if (stage == END)
			l.itemStateChanged(list_ev);
		if (l instanceof JCListListener) {
			if (stage == BEGIN) {
				((JCListListener)l).listItemSelectBegin(list_ev);
				if (!list_ev.doit)
					return false;
			}
			else
				((JCListListener)l).listItemSelectEnd(list_ev);
		}
	}
	return true;
}

/**
 * Select/deselect the range between start and end.
 * This does not set the last_selected flag.
 */
private static void
selectRange(JCListComponent list, int first, int last, boolean select) {
	int[] paint_rows = new int[Math.abs(last-first)+1];
	int num_paint_rows = 0;
	synchronized (list) {
		for (int i = Math.min(first, last),
			 end = Math.max(first, last); i <= end; i++) {
			if (list.selected[i] != select) {
				list.selected[i] = select;
				//	if (!select)
				//		postEvent(list, END, null, i);
				// Record row to be painted later outside monitor.
				paint_rows[num_paint_rows] = i;
				num_paint_rows++;
			}
		}
	}
	// Paint rows outside monitor to avoid deadlock.
	for (int i=0; i<num_paint_rows; i++) {
		list.paintRow(paint_rows[i]);
	}
}

/**
 * Restores the range between start and end.
 */
private static void
restoreRange(JCListComponent list, int first, int last, boolean dostart) {
	int start = Math.min(first, last), end = Math.max(first, last),
		tmp = list.start_item;
	int[] paint_rows = new int[end-start+1];
	int num_paint_rows = 0;
	synchronized (list) {
		for (int i=start; i <= end; i++) {
			if (i != tmp || dostart) {
				list.selected[i] = list.last_selected[i];
				// Record row to be painted later outside monitor.
				paint_rows[num_paint_rows] = i;
				num_paint_rows++;
			}
		}
	}
	// Paint rows outside monitor to avoid deadlock.
	for (int i=0; i<num_paint_rows; i++) {
		list.paintRow(paint_rows[i]);
	}
}

/**
 * This does all the necessary processing for movement in
 * multipleSelection mode.  This code handles all the various cases
 * for relationships between the start, end and current item, restoring
 * as needed, and selecting the appropriate range.  This is called in
 * both the mouse and keyboard cases.
 */
private static void
arrangeRange(JCListComponent list, int item) {
    int start = list.start_item, end = list.end_item, i = item;
    boolean set = list.selected[start];

    if (start < end) {
        if (i > end) {
            selectRange(list, end, i, set);
 		}
		else if (i < end && i >= start) {
			if (!set)
				restoreRange(list, i+1, end, false);
			else
				selectRange(list, i+1, end, false);
		}
		else if (i <= start) {
			if (!set)
				restoreRange(list, start, end, false);
			else
				selectRange(list, start, end, false);
			selectRange(list, i, start, set);
		}
	}

	else if (start > end) {
		if (i <= end) {
			selectRange(list, i, end, set);
		}
		else if (i > end && i <= start) {
			if (!set)
				restoreRange(list, end, i-1, false);
			else
				selectRange(list, end, i-1, false);
		}
		else if (i >= start) {
			if (!set)
				restoreRange(list, end, start, false);
			else
				selectRange(list, end, start, false);
			selectRange(list,start, i, set);
		}
	}

	else
		selectRange(list, start, i, set);
}

/**
 * mouseDown handler
 */
static boolean
beginSelect(JCListComponent list, Event event) {
	if (event.clickCount > 1)
		return false;

    if (list.countItems() == 0) return false;
    int item = list.last_row = list.eventToRow(event, true);
	if (item == BWTEnum.NOTFOUND) return false;

	// Disallow deselection of focus row if AutoSelect is set
	if (list.auto_select && list.selected[item] && item == list.focus_row)
		return true;

	list.selection_type = JCListEvent.INITIAL;
    if (list.multiple_select) {
		if (event.shiftDown())
			list.selection_type = JCListEvent.MODIFICATION;
		else if (event.controlDown())
			list.selection_type = JCListEvent.ADDITION;
	}

	if (!postEvent(list, BEGIN, event, item))
		return false;

    list.did_selection = false;

	/*
	 * Unselect the previous selection if needed.
	 */
    boolean sel = item < list.selected.length ? list.selected[item] : false;
    if (!list.appending) {
		for (int i = 0; i < list.selected.length && i < list.countItems(); i++) {
			if (list.selected[i]) {
		 //		if (postEvent(list, BEGIN, event, i)) {
					list.selected[i] = false;
					list.paintRow(i);
		 //		}
			}
        }
    }

    if (list.multiple_select) {
		if (event.shiftDown())
            sel = list.selected[list.start_item];
        else if (event.controlDown())
			list.selected[item] = !list.selected[item];
		else {
//			list.selected[item] = !sel;
			list.last_selected[item] = list.selected[item];
			list.selected[item] = !list.selected[item];
		}
    }

    else if (item < list.selected.length) {
		list.selected[item] = !sel;
	}

    list.paintRow(item);
    list.last_selected_item = item;
    list.old_end_item = list.end_item;
    list.end_item = item;

    if (!list.multiple_select || !event.shiftDown()) {
		list.old_start_item = list.start_item;
		list.start_item = item;

		if (list.multiple_select && !list.kbd_select)
			clickRow(list, event);
		return true;
	}

	/*
	 * If appending, select the
	 * new range. Look and see if the old range must be unselected
	 * (the cases where the selection endpoint goes from one side of the
	 * start to the other.)
	 */
	int start = list.start_item, end = list.old_end_item;
	if (start < end) {
		if (item > end)
			selectRange(list, end+1, item, sel);
		else if (item < end && item >= start)
			restoreRange(list, item+1, end, false);
		else if (item < start) {
			if (sel)
				selectRange(list, start+1, end, false);
			else
				restoreRange(list, start+1, end, false);
			selectRange(list, item, start, sel);
		}
	}

	else if (start > end) {
		if (item < end)
			selectRange(list, item, end+1, sel);
		else if (item > end && item <= start)
			restoreRange(list, end, item-1, false);
		else if (item > start) {
			if (sel)
				selectRange(list, end, start-1, false);
			else
				restoreRange(list, end, start-1, false);
			selectRange(list, start, item, sel);
		}
	}

	else if (start == end)
		selectRange(list, start, item, sel);

	clickRow(list, event);
	return true;
}

/**
 * mouseUp handler
 */
static boolean
endSelect(JCListComponent list, Event event) {
    if (list.countItems() == 0) return false;
    int item = list.last_row = list.eventToRow(event, false);

	list.old_start_item = list.start_item;
	list.old_end_item = list.end_item;
    if (list.hasFocus()) {
        if (list.multiple_select) {
            list.drawHighlight(list.focus_row, false);
            list.focus_row = item;
            list.drawHighlight(list.focus_row, true);
        }
        else {
            list.drawHighlight(list.focus_row, false);
            list.focus_row = list.last_selected_item;
            list.drawHighlight(list.focus_row, true);
        }
    }
    else
         list.focus_row = item;

	if (!((event.shiftDown() || event.controlDown())
		  && list.multiple_select) && !list.did_selection) {
        if (event.clickCount > 1)
			list.doubleClickAction(event, item);
        else
            clickRow(list, event);
    }
    else if (event.clickCount > 1)
		list.doubleClickAction(event, item);

    list.drawHighlight(list.focus_row, true);
    list.appending = false;
	return true;
}

static int last_x = BWTEnum.NOVALUE, last_y;

/**
 * mouseDrag handler
 */
static boolean
mouseDrag(JCListComponent list, Event ev) {
	if (!list.multiple_select) return false;
	if (Math.abs(ev.x - last_x) + Math.abs(ev.y - last_y) < 20) return false;
	last_x = ev.x; last_y = ev.y;
    list.did_selection = false;
	int item = list.last_row = list.eventToRow(ev, false);
	if (item < 0) return false;
	list.makeVisible(item);
	list.selection_type = JCListEvent.MODIFICATION;
	if (!postEvent(list, BEGIN, ev, item))
		return false;
	kbdNewItem(list, item, ev);
	return true;
}

/**
 * Invoked for all selection actions other than double-click.
 * This posts an event.
 */
private static void clickRow(JCListComponent list, Event event) {
    int item = list.last_selected_item;
    list.did_selection = true;
    try {
        postEvent(list, END, event, item);
    }
    catch (ArrayIndexOutOfBoundsException e) {
        // Item has disappeared move on.
    }

}

/**
 * Called when a new item is selected via the keyboard
 * in multipleSelection mode.
 * This does the deselection of previous items.
 */
private static void
kbdNewItem(JCListComponent list, int item, Event ev) {
    if (list.last_selected_item == item) return;
	if (!list.multiple_select) return;

	if (!list.did_selection) {		
		arrangeRange(list, item);
		list.last_selected_item = list.end_item = item;
		clickRow(list, ev);
		return;
	}

	list.did_selection = true;
	arrangeRange(list, item);
	list.last_selected_item = list.end_item = item;
	if (!list.did_selection)
		clickRow(list, ev);
}

/**
 * Called when a new item is selected via the
 * keyboard in multipleSelection mode.  This does the deselection of
 * previous items.
 */
private static void
kbdExtendedItem(JCListComponent list, int item, Event event) {
    if (list.last_selected_item == item) return;

	if (event.shiftDown()) {
		arrangeRange(list, item);
		list.end_item = list.last_selected_item = item;
		clickRow(list, event);
	}
}

/** Selects the row which currently has focus. */
static void
selectFocusRow(JCListComponent list, Event ev) {
	boolean m = list.multiple_select;
	list.multiple_select = false;
	select(list, list.focus_row, true, ev);
	list.multiple_select = m;
}

/**
 * upArrow handler
 */
private static boolean
prevRow(JCListComponent list, Event event) {
    if (list.countItems() == 0) return false;
    int item = list.focus_row - 1;
    if (item < 0) return false;
	if (!postEvent(list, BEGIN, event, item))
		return false;
    list.makeVisible(item);
    int olditem = list.focus_row;
    list.drawHighlight(olditem, false);
    list.focus_row = item;
    list.drawHighlight(list.focus_row, true);
	if (list.auto_select)
		selectFocusRow(list, event);
	else if (list.multiple_select)
		kbdExtendedItem(list, item, event);
	return true;
}

/**
 * downArrow handler
 */
private static boolean
nextRow(JCListComponent list, Event event) {
    int item = list.focus_row + 1;
    if (item >= list.countItems()) return false;
    list.makeVisible(item);
	if (!postEvent(list, BEGIN, event, item))
		return false;
    list.drawHighlight(list.focus_row, false);
    list.focus_row = item;
    list.drawHighlight(list.focus_row, true);
	if (list.auto_select)
		selectFocusRow(list, event);
	else if (list.multiple_select)
		kbdExtendedItem(list, item, event);
	return true;
}

/**
 * downArrow handler - no SHIFT or CTRL
 */
static boolean
normalNextRow(JCListComponent list, Event event) {
    if (!list.hasFocus()) return false;
    list.appending = false;
    list.selection_type = JCListEvent.INITIAL;
    return nextRow(list, event);
}

/**
 * SHIFT-downArrow handler
 */
static boolean
shiftNextRow(JCListComponent list, Event event) {
	if (!list.multiple_select) return false;
    if (!list.hasFocus()) return false;
    list.appending = true;
	event.modifiers |= Event.SHIFT_MASK;
    list.selection_type = JCListEvent.MODIFICATION;
    boolean status = nextRow(list, event);
    list.appending = false;
	return status;
}

/**
 * CTRL-downArrow handler
 */
static boolean
ctrlNextRow(JCListComponent list, Event event) {
    if (!list.hasFocus()) return false;
    list.appending = true;
	event.modifiers |= Event.CTRL_MASK;
    list.selection_type = JCListEvent.ADDITION;
    boolean status = nextRow(list, event);
    list.appending = false;
	return status;
}

/**
 * SHIFT-CTRL-downArrow handler
 */
static boolean
ctrlShiftNextRow(JCListComponent list, Event event) {
	if (!list.multiple_select) return false;
    if (!list.hasFocus()) return false;
    list.appending = true;
	event.modifiers |= Event.CTRL_MASK | Event.SHIFT_MASK;
    list.selection_type = JCListEvent.MODIFICATION;
    boolean status = nextRow(list, event);
    list.appending = false;
	return status;
}

/**
 * upArrow handler - no SHIFT or CTRL
 */
static boolean
normalPrevRow(JCListComponent list, Event event) {
    if (!list.hasFocus()) return false;
    list.appending = false;
    event.modifiers &= ~(Event.SHIFT_MASK | Event.CTRL_MASK | Event.ALT_MASK);
    list.selection_type = JCListEvent.INITIAL;
    return prevRow(list, event);
}

/**
 * SHIFT-upArrow handler
 */
static boolean
shiftPrevRow(JCListComponent list, Event event) {
	if (!list.multiple_select) return false;
    if (!list.hasFocus()) return false;
    list.appending = true;
    event.modifiers |= Event.SHIFT_MASK;
    list.selection_type = JCListEvent.MODIFICATION;
    boolean status = prevRow(list, event);
    list.appending = false;
	return status;
}

/**
 * CTRL-upArrow handler
 */
static boolean
ctrlPrevRow(JCListComponent list, Event event) {
    if (!list.hasFocus()) return false;
    list.appending = true;
    event.modifiers |= Event.CTRL_MASK;
    list.selection_type = JCListEvent.ADDITION;
    boolean status = prevRow(list, event);
    list.appending = false;
	return status;
}

/**
 * SHIFT-CTRL-upArrow handler
 */
static boolean
ctrlShiftPrevRow(JCListComponent list, Event event) {
	if (!list.multiple_select) return false;
    if (!list.hasFocus()) return false;
    list.appending = true;
    event.modifiers |= (Event.SHIFT_MASK | Event.CTRL_MASK);
    list.selection_type = JCListEvent.MODIFICATION;
    boolean status = prevRow(list, event);
    list.appending = false;
	return status;
}

/**
 * SHIFT-mouseDown handler
 */
static boolean
beginExtend(JCListComponent list, Event event) {
    if (!list.multiple_select) return false;
    list.appending = true;
	event.modifiers |= Event.SHIFT_MASK;
    return beginSelect(list, event);
}

/**
 * SHIFT-mouseUp handler
 */
static boolean
endExtend(JCListComponent list, Event event) {
    if (!list.multiple_select) return false;
    list.appending = false;
    return endSelect(list, event);
}

/**
 * CTRL-mouseDown handler
 */
static boolean
beginToggle(JCListComponent list, Event event) {
    if (!list.multiple_select) return false;
    list.appending = true;
    event.modifiers |= Event.CTRL_MASK;
    list.old_start_item = list.start_item;
    list.old_end_item = list.end_item;

	/*
	 * Since items are being added to a selection, save the state of
	 * the last selected range. This allows the rubberbanding and
	 * shift-select functionality to work correctly.
	 */
    int i = Math.min(list.old_start_item, list.old_end_item);
    int j = Math.max(list.old_start_item, list.old_end_item);
    if (i != 0 || j != 0)
        for (; i <= j; i++)
            list.last_selected[i] = list.selected[i];

    return beginSelect(list, event);
}

/**
 * CTRL-mouseUp handler
 */
static boolean
endToggle(JCListComponent list, Event event) {
    if (!list.multiple_select) return false;
    list.appending = false;
    return endSelect(list, event);
}

/*
 * Keyboard selection
 */
static boolean
kbdSelect(JCListComponent list, Event event) {
	if (list.focus_row < 0) return false;
	Rectangle rect = list.getBounds(list.focus_row, true);
	event.x = rect.x + 1;
	event.y = rect.y + 1;

    if (list.multiple_select && (event.shiftDown() || event.controlDown()))
		list.appending = true;
	list.kbd_select = true;
	boolean s = beginSelect(list, event);
	if (s)
		clickRow(list, event);
	list.kbd_select = false;
	return s;
}

/**
 * programmatically selects a row.
 */
static void
select(JCListComponent list, int row, boolean notify, Event event) {
	int[] paint_rows = new int[0];
	int num_paint_rows = 0;
	synchronized (list) {
		if (list.selected.length < list.countItems())
			list.updateSelectedList();

		if (!list.multiple_select) {
			list.selection_type = JCListEvent.INITIAL;
			paint_rows = new int[list.selected.length];
			for (int i = 0; i < list.countItems(); i++)
				if (list.selected[i]) {
					list.selected[i] = list.last_selected[i] = false;
					//postEvent(list, END, event, i);
					// Record row to be painted later outside monitor.
					paint_rows[num_paint_rows] = i;
					num_paint_rows++;
				}
		} else {
			list.selection_type = JCListEvent.MODIFICATION;
		}
	}
	// Paint rows outside monitor to avoid deadlock.
	for (int i=0; i<num_paint_rows; i++) {
		list.paintRow(paint_rows[i]);
	}
	synchronized (list) {
		list.selected[row] = list.last_selected[row] = true;
		list.last_selected_item = row;
	}
	list.paintRow(row);

	if (notify)
		clickRow(list, event);

	/*
	 * Update the selection parameters so that a programmatic
	 * selection looks the same as a user selection.
	 */
	int draw_highlight_false_row = 0;
	int draw_highlight_true_row = 0;
	boolean draw_highlight = false;
	synchronized (list) {
		for (int i = list.countItems() - 1; i >= 0; i--) {
			if (list.selected[i]) {
				int start, end = i;
				while (i != 0 && list.selected[i])
					i--;
				if (i == 0 && list.selected[i])
					start = i;
				else
					start = i + 1;
				list.old_end_item = list.end_item;
				list.end_item = end;
				list.old_start_item = list.start_item;
				list.start_item = start;
				list.last_selected_item = end;
				draw_highlight_false_row = list.focus_row;
				list.focus_row = end;
				draw_highlight_true_row = list.focus_row;
				draw_highlight = true;
			}
		}

		if (!draw_highlight) {
			/*
			 * If this point reached, there are no selected items
			 */
			list.old_end_item = list.end_item;
			list.end_item = 0;
			list.old_start_item = list.start_item;
			list.start_item = 0;
			list.last_selected_item = 0;
		}
	}

	if (draw_highlight) {
		// Draw highlight outside monitor to avoid deadlock
		list.drawHighlight(draw_highlight_false_row, false);
		list.drawHighlight(draw_highlight_true_row, true);
	}
}

}
