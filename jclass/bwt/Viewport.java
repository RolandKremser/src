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
// RCSID -- $RCSfile: Viewport.java $ $Revision: 2.10 $ $Date: 2000/11/09 20:11:55 $ $Locker: $  Sitraka Inc.

package jclass.bwt;
import java.applet.*;
import java.awt.*;

/** A private component used as a JCScrolledWindow's viewport.
 * @see JCScrolledWindow
 */
class Viewport extends
/* AWT_START 
Panel {
 AWT_END */
/* SWING11_START 
com.sun.java.swing.JComponent {
 SWING11_END */
/* SWING12_START */
javax.swing.JComponent {
/* SWING12_END */

Component child;
int horiz_origin, vert_origin;

Viewport() {
	super();
	setLayout(null);
}

/** 
 * Adds the specified component to this container.
 */
public Component add(Component comp) {
	if (child != null)
		remove(child);
	super.add((child = comp));
	return comp;
}

/* JDK110_START */
public Component add(String name, Component comp) {
	if (child != null)
		remove(child);
	super.add(name, (child = comp));
	return comp;
}

public Component add(Component comp, int index) {
	if (child != null)
		remove(child);
	super.add((child = comp), index);
	return comp;
}

public void add(Component comp, Object constraints) {
	if (child != null)
		remove(child);
	super.add((child = comp), constraints);
}

public void add(Component comp, Object constraints, int index) {
	if (child != null)
		remove(child);
	super.add((child = comp), constraints, index);
}
/* JDK110_END */

/**
 * Returns the preferred size of the child.
 */
/* AWT_START 
public Dimension preferredSize() {
 AWT_END */
/* SWING_START */
public Dimension getPreferredSize() {
/* SWING_END */

	return child != null ? JCComponent.getPreferredSize(child) : 
/* AWT_START 
		super.preferredSize();
 AWT_END */
/* SWING_START */
		super.getPreferredSize();
/* SWING_END */
}

/** If the child is smaller, it is set to the size of this Viewport. */
/* AWT_START 
public void reshape(int x, int y, int width, int height) {
 AWT_END */
/* SWING_START */
public void setBounds(int x, int y, int width, int height) {
/* SWING_END */

	synchronized (this) {
		if (x == location().x && y == location().y
			&& width == size().width && height == size().height)
			return;
	}

/* AWT_START 
	super.reshape(x, y, width, height);
 AWT_END */
/* SWING_START */
	super.setBounds(x, y, width, height);
/* SWING_END */

	if (child != null) 
		child.resize(Math.max(width, child.size().width),
					 Math.max(height, child.size().height));
}

public void resizeChild(int width, int height)
{
	if (child != null) 
		child.resize(width, height);
}

public void addNotify() {
	super.addNotify();
	if (child != null)
		child.resize(JCComponent.getPreferredSize(child).width, 
					 JCComponent.getPreferredSize(child).height);
}

public void scrollHorizontal(int v) {
	if (child != null) 
		child.move(-v, child.location().y);
}

public void scrollVertical(int v) {
	if (child != null) 
		child.move(child.location().x, -v);
}

}
