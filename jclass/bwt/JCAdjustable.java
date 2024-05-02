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
// RCSID -- $RCSfile: JCAdjustable.java $ $Revision: 2.1 $ $Date: 2000/11/09 20:09:21 $ $Locker: $  Sitraka Inc.
package jclass.bwt;

/**
 * The interface for objects which have an adjustable numeric value
 * contained within a bounded range of values.
 */
public interface JCAdjustable {

public static final int HORIZONTAL = 0; 
public static final int VERTICAL = 1;    

/**
 * Gets the orientation of the adjustable object.
 */
public int getOrientation();

/**
 * Sets the minimum value of the adjustable object.
 */
public void setMinimum(int min);

/**
 * Gets the minimum value of the adjustable object.
 */
public int getMinimum();

/**
 * Sets the maximum value of the adjustable object.
 * @param max the maximum value
 */
public void setMaximum(int max);

/**
 * Gets the maximum value of the adjustable object.
 */
public int getMaximum();

/**
 * Sets the unit value increment for the adjustable object.
 */
public void setUnitIncrement(int u);

/**
 * Gets the unit value increment for the adjustable object.
 */
public int getUnitIncrement();

/**
 * Sets the block value increment for the adjustable object.
 */
public void setBlockIncrement(int b);

/**
 * Gets the block value increment for the adjustable object.
 */
public int getBlockIncrement();

/**
 * Sets the length of the proportionl indicator of the adjustable object.
 */
public void setVisibleAmount(int v);

/**
 * Gets the length of the proportional indicator.
 */
public int getVisibleAmount();

/**
 * Sets the current value of the adjustable object. This
 * value must be within the range defined by the minimum and
 * maximum values for this object.
 */
public void setValue(int v);

/**
 * Gets the current value of the adjustable object.
 */
public int getValue();

/**
 * Add a listener to receive adjustment events when the value of
 * the adjustable object changes.
 * @see JCAdjustmentEvent
 */
public void addAdjustmentListener(JCAdjustmentListener l);

/**
 * Removes an adjustment listener.
 * @see JCAdjustmentEvent
 */ 
public void removeAdjustmentListener(JCAdjustmentListener l);

}
