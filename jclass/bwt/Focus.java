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
// RCSID -- $RCSfile: Focus.java $ $Revision: 2.3 $ $Date: 2000/11/09 20:09:16 $ $Locker: $  Sitraka Inc.

package jclass.bwt;
import java.awt.*;
import java.util.*;

class Focus {

/** Returns true if the component is traversable. */
private static boolean isTraversable(Component comp) {
	if (comp instanceof JCComponent)
		return ((JCComponent)comp).isFocusTraversable();
	else if (comp instanceof Container) {
		Component[] child = ((Container)comp).getComponents();
		for (int i=0; i < child.length; i++)
			if (isTraversable(child[i])) 
				return true;
		return false;
	}
	else 
		return comp.isShowing() && comp.isEnabled();
}

/** Finds the component which has focus. */
static JCComponent findFocus(Container comp) {
	if (comp == null) return null;
	Component[] child = comp.getComponents();
	int i;
	for (i=0; i < child.length; i++) {
		if (child[i] instanceof Container) 
			return findFocus((Container)child[i]);
		else if (child[i] instanceof JCComponent
				 && ((JCComponent)child[i]).hasFocus())
			return (JCComponent) child[i];
	}
	return null;
}

/** Returns the first child which can accept keyboard focus. */
static Component getFirstChild(Container comp) {
	if (!comp.isVisible())
		return null;
	Component[] child = comp.getComponents();
	for (int i=0; i < child.length; i++) {
		if (child[i] instanceof Container) {
			Component c = getFirstChild((Container)child[i]);
			if (c != null) return c;
		}
		else
			if (isTraversable(child[i])) return child[i];
	}
	return null;
}

/** Returns the number of traversable children in a container.
 * @param list if not null, the component is added to it
 */
private final static int countChildren(Component node, Vector list) {
	if (node == null)
		return 0;
	if (!(node instanceof Container)) {
		if (isTraversable(node)) {
			if (list != null) 
				list.addElement(node);
			return 1;
		}
		return 0;
	}

	int rows = 1;
	Component[] child = ((Container)node).getComponents();
	for (int i=0; i < child.length; i++)
		rows += countChildren(child[i], list);
	return rows;
}

/** Returns the next traversable child of a different parent. */
private static Component getNextChild(Container frame, Component current) {
	Vector list = new Vector(countChildren(frame, null));
	countChildren(frame, list);
	int pos = list.indexOf(current);
	for (int i=pos+1; i < list.size(); i++) {
		Component comp = (Component) list.elementAt(i);
		if (comp.getParent() != current.getParent())
			return comp;
	}
	return null;
}

/** Returns the previous traversable child of a different parent. */
private static Component getPreviousChild(Container frame, Component current) {
	Vector list = new Vector(countChildren(frame, null));
	countChildren(frame, list);
	int pos = list.indexOf(current);
	for (int i=pos-1; i >= 0; i--) {
		Component comp = (Component) list.elementAt(i);
		if (comp.getParent() != current.getParent())
			return comp;
	}
	return null;
}

/** Returns the last traversable child. */
static Component 
getLastChild(Container comp) {
	Component[] child = comp.getComponents();
	int i;
	for (i = child.length-1; i >= 0; i--) {
		if (child[i] instanceof Container) 
			return getLastChild((Container)child[i]);
		else
			if (isTraversable(child[i])) break;
	}
	return i >= 0 ? child[i] : null;
}

/**
 * Moves the focus to the next traversable sibling, or the first if necessary.
 */
static void 
nextFocus(Component comp) {
	if (comp.getParent() == null) return;
	Component[] child = comp.getParent().getComponents();
	if (child.length == 1) return;
	int i, pos;
	for (pos=0; pos < child.length; pos++)
		if (child[pos] == comp) break;

	comp = null;
	for (i = pos+1; comp == null && i < child.length; i++)
		if (isTraversable(child[i])) comp = child[i];
	for (i = 0; comp == null && i < pos; i++)
		if (isTraversable(child[i])) comp = child[i];

	if (comp != null)
		comp.requestFocus();
}

/**
 * Moves the focus to the next traversable component of a different parent.
 */
static void 
nextFocus(Container parent, Component current) {
	Container frame = BWTUtil.getFrame(parent);
	Component next = getNextChild(frame, current);
	if (next == null)
		next = getFirstChild(frame);
	if (next != null)
		next.requestFocus();
}

/**
 * Moves the focus to the previous traversable component of a different parent.
 */
static void 
previousFocus(Container parent, Component current) {
	Container frame = BWTUtil.getFrame(parent);
	Component prev = getPreviousChild(frame, current);
	if (prev == null)
		prev = getLastChild(frame);
	if (prev != null)
		prev.requestFocus();
}

/**
 * Moves the focus to the previous traversable sibling, 
 * or the last if necessary.
 */
static void 
previousFocus(Component comp) {
	if (comp.getParent() == null) return;
	Component[] child = comp.getParent().getComponents();
	if (child.length == 1) return;
	int i, pos;
	for (pos=0; pos < child.length; pos++)
		if (child[pos] == comp) break;

	comp = null;
	for (i = pos-1; comp == null && i >= 0; i--)
		if (isTraversable(child[i])) comp = child[i];
	for (i = child.length-1; comp == null && i != pos; i--)
		if (isTraversable(child[i])) comp = child[i];
	if (comp != null)
		comp.requestFocus();
}

}
