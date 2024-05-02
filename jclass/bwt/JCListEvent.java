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
// RCSID -- $RCSfile: JCListEvent.java $ $Revision: 2.2 $ $Date: 2000/11/09 20:10:41 $ $Locker: $  Sitraka Inc.
package jclass.bwt;
import java.awt.Event;

/** JCListEvent is the selection event posted by a JCList or JCListComponent.
 * @see JCListAdapter
 * @see JCListListener
 * @see JCList#addItemListener
 */
public class JCListEvent extends JCItemEvent {

/** Selection types. */
public final static int INITIAL = 0;
public final static int MODIFICATION = 1;
public final static int ADDITION = 2;

Event event;	// source event
int row;		// last item selected

/** Selection type:<p>
 * <ul>
 * <li>INITIAL Initial selection
 * <li>MODIFICATION Modification of an existing selection
 * <li>ADDITION Additional noncontiguous selection
 * </ul>
 */
int type;
boolean doit = true;

/** Gets the last item selected. */
public int getRow() { return row; }

/** Gets the selection type:<p>
 * <pre>
 * INITIAL            Initial selection
 * MODIFICATION       Modification of an existing selection
 * ADDITION           Additional noncontiguous selection
 * </pre>
 */
public int getType() { return type; }

/** Gets the current AllowSelection value.
 * @see #setAllowSelection
 */
public boolean getAllowSelection() { return doit; }

/** Determines whether the selection should be allowed (default: true). */
public void setAllowSelection(boolean v) { doit = v; }

/** Gets the originating event (null if selection was not interactive). */
public Event getSourceEvent() { return event; }

/**
 * Constructs an object with the specified source, row and selection type.
 * @param source the originating list
 * @param id the event type
 * @param item the item where the event occurred
 * @param row the item's row number
 * @param type the selection type: INITIAL, MODIFICATION or ADDITION
 * @param stateChange the state change type which caused the event: SELECTED or DESELECTED
 * @see #getType
 */
public JCListEvent(JCListInterface list, 
				   Event ev, int row, int type, int stateChange) {
	super(list, ITEM_STATE_CHANGED, list.getItem(row), stateChange);
	event = ev;
	this.row = row; this.type = type;
}

}

