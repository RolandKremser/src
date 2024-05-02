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
// RCSID -- $RCSfile: JCTabManagerEvent.java $ $Revision: 2.2 $ $Date: 2000/11/09 20:11:12 $ $Locker: $  Sitraka Inc.

package jclass.bwt;
import java.awt.*;

/** JCTabManagerEvent is posted by a JCTabManager when its page changes.
 * @see JCTabManager#addTabManagerListener
 * @see JCTabManagerAdapter
 * @see JCTabManagerListener
 */
public class JCTabManagerEvent extends JCAWTEvent {

/** The current page, null at initialization. */
Component	current_page;

/** The page that will be displayed. */
Component	next_page;

/** The current tab, null at initialization. */
JCTabButton current_tab;

/** The tab that will be displayed. */
JCTabButton next_tab;

/** Determines whether the page should change. */
boolean doit = true;

/** Gets the current page (null at initialization). */
public Component getCurrentPage() { return current_page; }

/** Gets the page that will be displayed. */
public Component getNextPage() { return next_page; }

/** Gets the current tab (null at initialization). */
public JCTabButton getCurrentTab() { return current_tab; }

/** Gets the tab that will be displayed. */
public JCTabButton getNextTab() { return next_tab; }

/** Sets the page that will be displayed. */
public void setNextPage(Component page) { next_page = page; }

/** Determines whether the page should change. */
public boolean getChangePage() { return doit; }

/** Determines whether the page should change (default: true). */
public void setChangePage(boolean v) { doit = v; }

public JCTabManagerEvent(Event ev, Component current, JCTabButton current_tab,
						 Component next, JCTabButton next_tab) {
	super(ev.target, ev.id);
	current_page = current; next_page = next;
	this.current_tab = current_tab; this.next_tab = next_tab;
}

}
