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
// RCSID -- $RCSfile: JCAWTEvent.java $ $Revision: 2.1 $ $Date: 2000/11/09 20:09:25 $ $Locker: $  Sitraka Inc.
package jclass.bwt;

/** A class which provides JDK 1.1's AWTEvent methods. */
public class JCAWTEvent 
/* JDK110_START */
extends java.awt.AWTEvent
/* JDK110_END */
{

/* JDK102_START 

// java.util.EventObject
protected transient Object  source;
public Object getSource() { return source; }
public String toString() {
	return getClass().getName() + "[source=" + source + "]";
}

public final static long COMPONENT_EVENT_MASK = 0x01;
public final static long CONTAINER_EVENT_MASK = 0x02;
public final static long FOCUS_EVENT_MASK = 0x04;
public final static long KEY_EVENT_MASK = 0x08;
public final static long MOUSE_EVENT_MASK = 0x10;
public final static long MOUSE_MOTION_EVENT_MASK = 0x20;
public final static long WINDOW_EVENT_MASK = 0x40;
public final static long ACTION_EVENT_MASK = 0x80;
public final static long ADJUSTMENT_EVENT_MASK = 0x100;
public final static long ITEM_EVENT_MASK = 0x200;
public final static long TEXT_EVENT_MASK = 0x400;
public final static int RESERVED_ID_MAX = 1999;

protected int id;
 JDK102_END */

/**
 * Constructs an event with the specified source object and type.
 */
public JCAWTEvent(Object source, int id) {
/* JDK110_START */
	super(source, id);
/* JDK110_END */
/* JDK102_START 
	if (source == null)
	    throw new IllegalArgumentException("null source");
	this.source = source;
	this.id = id;
 JDK102_END */
}

/**
 * Returns the event type.
 */
/* JDK102_START 
public int getID() { return id; }
 JDK102_END */

}
