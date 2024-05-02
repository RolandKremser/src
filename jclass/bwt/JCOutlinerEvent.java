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
// RCSID -- $RCSfile: JCOutlinerEvent.java $ $Revision: 2.2 $ $Date: 2000/11/09 20:10:51 $ $Locker: $  Sitraka Inc.
package jclass.bwt;
import java.awt.Event;

/** JCOutlinerEvent is the selection or state change event posted by a 
 * JCOutliner or JCOutlinerComponent.
 * @see JCOutliner#addItemListener
 * @see JCOutlinerAdapter
 * @see JCOutlinerListener
 */
public class JCOutlinerEvent extends JCItemEvent {

/** 
 * The node new state change type.
 */
public static final int NEW_STATE = SELECTED + 100;

/** The node's current state
 * (set to BWTEnum.NOVALUE if the node is not a folder).
 */
int state;

/** The node's new state
 * (set to BWTEnum.NOVALUE if the node is not a folder).
 */
int new_state = BWTEnum.NOVALUE;

/** Determines whether the selection or state change should be allowed. */
boolean doit = true;

/** Gets the node whose state will be changed. */
public JCOutlinerNode getNode() { return (JCOutlinerNode) getItem(); }

/** Gets the node's current state
 * (set to BWTEnum.NOVALUE if the node is not a folder).
 */
public int getState() { return state; }

/** Gets the node's new state.
 * If the node is not a folder, the value will be BWTEnum.NOVALUE.
 */
public int getNewState() { return new_state; }

/** Sets the node's new state. This is ignored if the node is not a folder.
 */
public void setNewState(int v) { new_state = v; }

/** Gets the current AllowChange value.
 * @see #setAllowChange
 */
public boolean getAllowChange() { return doit; }

/** Determines whether the selection or state change should be allowed
 * (default: true).
 */
public void setAllowChange(boolean v) { doit = v; }

public JCOutlinerEvent(JCOutlinerInterface outliner, JCOutlinerNode node,
					   boolean selected) {
	super(outliner, ITEM_STATE_CHANGED, node, selected ? SELECTED : DESELECTED);
}

public JCOutlinerEvent(JCOutlinerInterface outliner, JCOutlinerNode node, 
					   int new_state) {
	super(outliner, ITEM_STATE_CHANGED, node, NEW_STATE);
	this.new_state = new_state;
}

}

