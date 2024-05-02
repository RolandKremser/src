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
// RCSID -- $RCSfile: JCItemEvent.java $ $Revision: 2.1 $ $Date: 2000/11/09 20:10:34 $ $Locker: $  Sitraka Inc.
package jclass.bwt;
import java.awt.*;

/**
 * The item event posted by JCItemSelectable objects.
 * This event is generated when an item is selected, de-selected, expanded,
 * or contracted.
 * @see JCItemSelectable
 * @see ItemListener
 */
public class JCItemEvent extends 
/* JDK110_START */
java.awt.event.ItemEvent
/* JDK110_END */
/* JDK102_START 
JCAWTEvent
 JDK102_END */
{

/* JDK102_START 
public static final int ITEM_STATE_CHANGED	= Event.LIST_SELECT; 
public static final int SELECTED = 1;
public static final int DESELECTED	= 2;
 JDK102_END */

/* JDK102_START 
Object item;
int stateChange;
 JDK102_END */
/* JDK110_START */
private static ItemSelectable selectable_item = new ItemSelectableObject();
/* JDK110_END */

/**
 * Constructs an event with the specified source,
 * type, item, and item select state.
 * @item the item where the event occurred
 * @stateChange the state change type which caused the event
 */
public JCItemEvent(Object source, int id, Object item, int stateChange) {
/* JDK110_START */
	// EventObject does not accept a null target
	super(selectable_item, id, item, stateChange);
	this.source = source;
/* JDK110_END */
/* JDK102_START 
	super(source, id);
	this.item = item;
	this.stateChange = stateChange;
 JDK102_END */
}

/**
 * Returns the item where the event occurred.
 */
/* JDK102_START 
public Object getItem() { return item; }
 JDK102_END */
/* JDK110_START */
public Object getItem() { return super.getItem(); }
/* JDK110_END */


/**
 * Returns the state change type which generated the event: SELECTED or DESELECTED
 */
/* JDK102_START 
public int getStateChange() { return stateChange; }
 JDK102_END */
/* JDK110_START */
public int getStateChange() { return super.getStateChange(); }
/* JDK110_END */

}

/* JDK110_START */
class ItemSelectableObject implements ItemSelectable {
	public Object[] getSelectedObjects() { return null; }
	public void addItemListener(java.awt.event.ItemListener l) {}
	public void removeItemListener(java.awt.event.ItemListener l) {}
}
/* JDK110_END */
