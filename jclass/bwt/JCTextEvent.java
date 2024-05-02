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
// RCSID -- $RCSfile: JCTextEvent.java $ $Revision: 2.3 $ $Date: 2000/11/09 20:11:20 $ $Locker: $  Sitraka Inc.

package jclass.bwt;
import java.awt.*;

/** JCTextValueEvent is posted by a JCTextComponent when its value changes.
 * @see JCTextAdapter
 * @see JCTextListener
 * @see JCTextComponent#addTextListener
 */
public class JCTextEvent extends
/* JDK102_START 
JCAWTEvent
 JDK102_END */
/* JDK110_START */
java.awt.event.TextEvent
/* JDK110_END */
 {

/* JDK102_START 
public static final int TEXT_FIRST 	= 900;
public static final int TEXT_LAST 	= 900;
public static final int TEXT_VALUE_CHANGED	= TEXT_FIRST;
 JDK102_END */

int start;				// starting position of the text to modify
int end;				// ending position of the text to modify
String text;			// The string to be inserted
boolean doit = true;	// Whether the value change be allowed
boolean is_deletion;	// If text is being deleted

/** Gets the starting position of the text to modify. */
public int getStartPosition() { return start; }

/** Sets the starting position of the text to modify. */
public void setStartPosition(int v) { start = v; }

/** Gets the ending position of the text to modify. */
public int getEndPosition() { return end; }

/** Sets the ending position of the text to modify. */
public void setEndPosition(int v) { end = v; }

/** Gets the string to be inserted. */
public String getText() { return text; }

/** Sets the string to be inserted. */
public void setText(String v) { text = v; }

/** Returns true if text is being deleted. */
public boolean isDeletion() { return is_deletion; }

/** Determines whether the value change be allowed. */
public boolean getAllowChange() { return doit; }

/** Determines whether the value change be allowed (default: true). */
public void setAllowChange(boolean v) { doit = v; }

public JCTextEvent(JCTextComponent comp, int start, int end, String text) {
	super(comp, TEXT_VALUE_CHANGED);
	this.start = start;
	this.end = end;
	this.text = text;
}

public String paramString() {
	return "text="+text+" start="+start+" end="+end;
}

}

