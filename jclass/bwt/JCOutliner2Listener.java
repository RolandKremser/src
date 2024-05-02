/*****************************************************************************
 *
 * (c) Copyright 1998, SITRAKA INC.
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
 * (c) Copyright 1998 Sitraka Inc.  Unpublished - all
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
 * (c) Copyright 1998 Sitraka Inc.
 * ALL RIGHTS RESERVED
 *
 *****************************************************************************/
// RCSID -- $RCSfile: JCOutliner2Listener.java $ $Revision: 1.3 $ $Date: 2000/11/09 20:10:48 $ $Locker: $  Sitraka Inc.
package jclass.bwt;

/**
 * The JCOutliner listener interface for receiving node selection,
 * unselection and state change events.
 *
 * The main reason for this is to provide a mechnism for a) being able to stop
 * an unselection from occuring b) informing the user of selection changes on
 * a node by node basis (the itemStateChanged event supported by the super
 * class is only fired once no-matter how many nodes are selected and it is
 * fired _after_ the selection has occured). 
 * <p>
 * <b>Event Firing Order</b><p>
 * Selects a node, and posts a JCOutlinerEvent.
 * <B>Single Select Posting sequence</B><p>
 * 1) outlinerNodeSelectBegin (can abort by calling e.setAllowChange(false)<p>
 * 2) outlinerNodeUnselectBegin (on perviously selected node; cannot be aborted)<p>
 * 3) itemStateChanged (on previously selected node)<p>
 * 4) outlinerNodeUnselectEnd (on previously selected node)<p>
 * 5) itemStateChanged<p>
 * 6) outlinerNodeSelectEnd<p>
 * <B>Multiple Selection Mode</B>
 * 1) outlinerNodeSelectBegin (can avoid selecting specified node by calling e.setAllowChange(false) - one call for each node selected<p>
 * 2) outlinerNodeUnselectBegin (for all nodes to be unselected; calling e.setAllowChange(false) can avoid the node begin unselected)<p>
 * 3) itemStateChanged (once for all for unselected nodes, if any)<p>
 * 4) outlinerNodeUnselectEnd (once for each unselected node)<p>
 * 5) itemStateChanged (once for all newly selected nodes<p> 
 * 6) outlinerNodeSelectEnd (once for each selected node)<p>
 * @param node if null, the currently selected node is deselected
 * @param ev event which caused the selection
 * @see JCOutliner#addItemListener
 * @see JCOutlinerListener
 */
public interface JCOutliner2Listener extends JCOutlinerListener {

/**
 * Invoked before a node is unselected. If (and only is) the
 * "allowMultipleSelections" property is "true" the you may call
 * "e.setAllowChange(false)" to stop a node from being selected.  In the
 * Single selected mode you have to stop the new selection (and hence abort
 * any deselection that might occure as a result) from starting by putting
 * the same call in the outlinerNodeSelectBegin method.
 */    
public void outlinerNodeUnselectBegin(JCOutlinerEvent e);

/**
 * Invoked after a node is unselected. Any changes made to the event
 * are ignored. Gets called for each node selected.
 */    
public void outlinerNodeUnselectEnd(JCOutlinerEvent e);

}

