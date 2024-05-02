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
// RCSID -- $RCSfile: ScrollbarThumb.java $ $Revision: 2.6 $ $Date: 2000/11/09 20:11:40 $ $Locker: $  Sitraka Inc.

package jclass.bwt;
import java.awt.*;

/** A private class which is used for a JCScrollbar's slider.
 * Methods are not synchronized because access from the parent is synchronized instead.
 * For performance, no peer is created for it.
 */
class ScrollbarThumb extends JCComponent {

Component sb;
boolean visible = true;

ScrollbarThumb(Component sb) {
	super();
	this.sb = sb;
	highlight = 0;
	border_style = jclass.base.Border.OUT;
}

protected void drawBorder(Graphics gc) {
	super.drawBorder(gc);
/* SWING_START */
	if (getBorder() == null) {
/* SWING_END */
		gc.setColor(Color.black);
		gc.drawRect(0, 0, size().width-1, size().height-1);
/* SWING_START */
	}
/* SWING_END */
}

/** Don't create the peer. */
public void addNotify() {}

/**
 * Shows the component. 
 */
public void show(boolean v) {
	if (visible != v) {
		visible = v;
		Rectangle r = bounds();
		sb.repaint(r.x, r.y, r.width, r.height);
	}
}

/** Checks if this Component is visible. */
public boolean isVisible() { return visible; }
	
/** Reshapes the component. */
/* AWT_START 
public void reshape(int x, int y, int width, int height) {
 AWT_END */
/* SWING_START */
public void setBounds(int x, int y, int width, int height) {
/* SWING_END */

	if (width < 0 || height < 0) return;
	boolean resized = (size().width != width || size().height != height);
	if (!resized && location().x == x && location().y == y) return;

	Rectangle old_bounds = bounds();
	do_repaint = false;

/* AWT_START 
	super.reshape(x, y, width, height);
 AWT_END */
/* SWING_START */
	super.setBounds(x, y, width, height);
/* SWING_END */

	do_repaint = true;
	Rectangle new_bounds = bounds();

	// Repaint parent areas
	if (!new_bounds.intersects(old_bounds)) {
		sb.repaint(old_bounds.x, old_bounds.y, old_bounds.width, old_bounds.height);
		repaint();
	}
	else {
		Rectangle union = new_bounds.union(old_bounds);
		sb.repaint(union.x, union.y, union.width, union.height);
	}
}

/**
 * Repaints part of the component by calling paint directly.
 */
public void repaint(int x, int y, int w, int h) {
	if (!isVisible() || w <= 0 || h <= 0) return;

	Point p = BWTUtil.translateToParent((Container) sb, this, x, y);
	sb.repaint(p.x, p.y, w, h);
}

}
