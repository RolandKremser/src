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
// RCSID -- $RCSfile: ListConverter.java $ $Revision: 2.1 $ $Date: 2000/11/09 20:11:25 $ $Locker: $  Sitraka Inc.

package jclass.bwt;
import jclass.util.*;

/** Reads JCListComponent parameters from an HTML file or external file. */
class ListConverter {

static void
getParams(JCListComponent comp) {
	JCConverter conv = comp.conv;

	String s = comp.getParam("Items");
	JCVector v = conv.toVector(comp, s, ',', true, comp.getItems());

	if (s != null && v != null &&
			BWTUtil.instanceOf(comp, "JCMultiColumnListComponent")) {
		for (int i=0; i < v.size(); i++) {
			Object o = v.elementAt(i);
			if (o instanceof String) {
				JCVector v2 = conv.toVector(comp, (String)o, '|', true, null);
				if (v2 != null)
					v.setElementAt(v2, i);
			}
		}
	}

	comp.setItems(v);
	comp.setSelectedBackground(conv.toColor(comp.getParam("SelectedBackground"),
											comp.getSelectedBackground()));
	comp.setSelectedForeground(conv.toColor(comp.getParam("SelectedForeground"),
											comp.getSelectedBackground()));
	comp.setAllowMultipleSelections(conv.toBoolean(comp.getParam("AllowMultipleSelections"), comp.allowsMultipleSelections()));
	comp.setAutoSelect(conv.toBoolean(comp.getParam("AutoSelect"), comp.auto_select));
	comp.setVisibleRows(conv.toInt(comp.getParam("VisibleRows"), 
								   comp.getVisibleRows()));
	s = comp.getParam("RowHeight");
	if (s != null) {
		if (s.trim().equalsIgnoreCase("font_height"))
			comp.setRowHeight(BWTEnum.FONT_HEIGHT);
		else
			comp.setRowHeight(conv.toInt(s, comp.getRowHeight()));
	}
	comp.setSpacing(conv.toInt(comp.getParam("Spacing"), comp.getSpacing()));
}

}
