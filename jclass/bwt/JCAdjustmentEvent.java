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
// RCSID -- $RCSfile: JCAdjustmentEvent.java $ $Revision: 2.1 $ $Date: 2000/11/09 20:09:22 $ $Locker: $  Sitraka Inc.
package jclass.bwt;
import java.awt.*;

/**
 * The adjustment event emitted by JCAdjustable objects.
 * @see JCAdjustable
 * @see JCAdjustmentListener
 */
public class JCAdjustmentEvent extends
/* JDK110_START */
java.awt.event.AdjustmentEvent
/* JDK110_END */
/* JDK102_START 
JCAWTEvent
 JDK102_END */
{

/**
 * The adjustment value changed event.
 */
public static final int ADJUSTMENT_VALUE_CHANGED	= Event.SCROLL_LINE_UP; // 601

/**
 * The unit increment adjustment type.
 */
public static final int UNIT_INCREMENT	= 1;

/**
 * The unit decrement adjustment type.
 */
public static final int UNIT_DECREMENT	= 2;

/**
 * The block decrement adjustment type.
 */
public static final int BLOCK_DECREMENT     = 3;

/**
 * The block increment adjustment type.
 */
public static final int BLOCK_INCREMENT     = 4;

/**
 * The absolute tracking adjustment type.
 */
public static final int TRACK	        = 5;

/* JDK102_START 
int value, adjustmentType;
 JDK102_END */
/* JDK110_START */
private static java.awt.Adjustable adj = new AdjustableObject();
/* JDK110_END */

/**
 * Constructs an event  with the specified source, type, and value.
 * @value the current value of the adjustment
 */
public JCAdjustmentEvent(Object source, int id, int type, int value) {
/* JDK110_START */
	super(adj, id, type, value);
	this.source = source;
/* JDK110_END */
/* JDK102_START 
	super(source, id);
	adjustmentType = type;
	this.value = value;
 JDK102_END */
}

/**
 * Returns the current value in the adjustment event.
 */
/* JDK102_START 
public int getValue() { return value; }
 JDK102_END */

/**
 * Returns the type of adjustment which caused the value changed event.
 * @see UNIT_INCREMENT
 * @see UNIT_DECREMENT
 * @see BLOCK_INCREMENT
 * @see BLOCK_DECREMENT
 * @see TRACK
 */
/* JDK102_START 
public int getAdjustmentType() { return adjustmentType; }
 JDK102_END */

}

/* JDK110_START */
class AdjustableObject implements java.awt.Adjustable {
    public int getOrientation() { return 0; }
    public void setMinimum(int min) {}
    public int getMinimum() { return 0; }
    public void setMaximum(int max) {}
    public int getMaximum() { return 0; }
    public void setUnitIncrement(int u) {}
    public int getUnitIncrement() { return 0; }
    public void setBlockIncrement(int b) {}
    public int getBlockIncrement() { return 0; }
    public void setVisibleAmount(int v) {}
    public int getVisibleAmount() { return 0; }
    public void setValue(int v) {}
    public int getValue() { return 0; }
    public void addAdjustmentListener(java.awt.event.AdjustmentListener l) {}
    public void removeAdjustmentListener(java.awt.event.AdjustmentListener l) {}
}
/* JDK110_END */
