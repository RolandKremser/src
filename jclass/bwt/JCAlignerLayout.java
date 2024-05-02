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
// RCSID -- $RCSfile: JCAlignerLayout.java $ $Revision: 2.8 $ $Date: 2000/11/09 20:09:23 $ $Locker: $  Sitraka Inc.

package jclass.bwt;

import jclass.util.JCAlignLayout;
import java.awt.*;
import java.util.*;

/** A layout that provides a simple way to lay out a vertically arranged
 * group of control components, each with an associated label (or other 
 * component) placed to its left.<p>
 *
 * <strong>Properties</strong><p>
 * <table border>
 * <tr><th>Name</th>    <th>Method</th></tr><tr><td>
 * LabelDefaultAlignment </td><td><a href=#setLabelDefaultAlignment>setLabelDefaultAlignment</a></td></tr><tr><td>
 * LabelVerticalAlignment </td><td><a href=#setLabelVerticalAlignment>setLabelVerticalAlignment</a></td></tr><tr><td>
 * ResizeHeight </td><td><a href=#setResizeHeight>setResizeHeight</a></td></tr><tr><td>
 * ResizeWidth </td><td><a href=#setResizeWidth>setResizeWidth</a></td></tr><tr><td>
 * </table>
 */
public class JCAlignerLayout extends JCAlignLayout {

int default_alignment = BWTEnum.DEFAULT;

/**
 * Creates an aligner layout with 2 columns, a variable number of rows,
 * and a gap of 5 pixels.
 */
public JCAlignerLayout() {
	super();
}

/**
 * Creates an aligner layout with the specified number of columns and gaps.
 * @param cols the number of columns (should be a multiple of 2)
 * @param hgap the horizontal gap variable
 * @param vgap the vertical gap variable
 * @exception IllegalArgumentException If the rows and columns are invalid.
 */
public JCAlignerLayout(int cols, int hgap, int vgap) {
	super(cols, hgap, vgap);
}

/** Gets the default label alignment.
 * @see #setLabelDefaultAlignment
 */
public int getLabelDefaultAlignment() { return default_alignment; }

/** Sets the default alignment for each label (if the component is a JCLabel):<br>
 * BWTEnum.TOPLEFT, TOPCENTER, TOPRIGHT, MIDDLELEFT, 
 * MIDDLECENTER, MIDDLERIGHT (default), BOTTOMLEFT, BOTTOMCENTER and BOTTOMRIGHT
 * @exception IllegalArgumentException If an invalid value is set
 */
public synchronized void setLabelDefaultAlignment(int v) {
	LabelConverter.checkAlignment(v);
	default_alignment = v;
}

/**
 * Sets the vertical position of a label relative to its control.
 * @param align BWTEnum.TOP, MIDDLE (default), or BOTTOM.
 * @exception IllegalArgumentException If an invalid value is set
 */
public void setLabelVerticalAlignment(Component child, int align) {
	LabelConverter.checkAlignment(align);
	super.setLabelVerticalAlignment(child, align);
}

/** Positions the component. 
 * @param pos the component's index in its parents child list
 * @param row,col component's position
 */
/* AWT_START 
protected void reshape(int pos, int row, int col, Component comp, 
					   int x, int y, int col_width, int row_height) {
 AWT_END */
/* SWING_START */
protected void setBounds(int pos, int row, int col, Component comp, 
					   int x, int y, int col_width, int row_height) {
/* SWING_END */

	int comp_w = col_width, comp_h = row_height;

	if (isLabel(col) || !getResizeHeight(comp)) {
		comp_h = JCComponent.getPreferredSize(comp).height;
	}

	// Set alignment if a JCLabel is in the label column
	if (isLabel(col) && comp.getClass().getName().equals("jclass.bwt.JCLabel")) {
		if (default_alignment == BWTEnum.DEFAULT)
			((JCLabel)comp).setAlignment(BWTEnum.MIDDLERIGHT);
		else
			((JCLabel)comp).setAlignment(default_alignment);
	}

	/* Resize a control to its parent's right edge if its resizeWidth value 
	 * is true, and it is in the last column
	 */
	if (!isLabel(col)) {
		if (col < col_widths.length-1) {
		}
		else if (getResizeWidth(comp)) {
			Container parent = comp.getParent();
			comp_w = parent.size().width - 
				     JCComponent.getInsets(parent).right - x;
		}
		else
			comp_w = JCComponent.getPreferredSize(comp).width;

		JCComponent.setBounds(comp, x, y, comp_w, comp_h);
		return;
	}

	int control_h = row_height;
	if (pos < comp.getParent().countComponents()-1) {
		Component control = comp.getParent().getComponents()[pos+1];
		if (control != null && !getResizeHeight(control))
			control_h = JCComponent.getPreferredSize(control).height;
	}
		
	switch (getLabelVerticalAlignment(comp)) {
	case BWTEnum.MIDDLE:
		y += (control_h - comp_h) / 2;
		break;
	case BWTEnum.BOTTOM:
		y += control_h - comp_h;
		break;
	}
	JCComponent.setBounds(comp, x, y, comp_w, comp_h);
}

}
