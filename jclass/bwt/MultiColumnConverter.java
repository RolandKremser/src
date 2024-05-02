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
// RCSID -- $RCSfile: MultiColumnConverter.java $ $Revision: 2.4 $ $Date: 2000/11/09 20:11:36 $ $Locker: $  Sitraka Inc.

package jclass.bwt;
import jclass.util.*;
import java.awt.Component;

/** Reads JCMultiColumnInterface parameters from an HTML file or external file. */
class MultiColumnConverter {

final static int[] column_resize_policy_values = {
BWTEnum.RESIZE_SINGLE, BWTEnum.RESIZE_NONE, BWTEnum.RESIZE_COLLAPSE
};

final static String[] column_resize_policy_strings = {
	"RESIZE_SINGLE", "RESIZE_NONE", "RESIZE_COLLAPSE"
};

/** Gets a paramater for this component. */
static String getParam(JCMultiColumnInterface multi, String param) {
	if (multi instanceof JCComponent) {
		JCComponent comp = (JCComponent) multi;
		return comp.conv.getParam(comp.getApplet(), comp, comp.getName(),
								  param);
	}
	else if (multi instanceof JCContainer) {
		JCContainer comp = (JCContainer) multi;
		return comp.conv.getParam(comp.applet, comp, comp.getName(), param);
	}
	return null;
}

/**
 * Converts a string to an array of alignments.
 * @param s the string which will be converted to integers
 * @param delim the delimiter separating the integers in the string.
 */
static int[]
toAlignmentList(JCConverter conv, String s, int[] def) {
	if (s == null) return null;
	JCStringTokenizer string = new JCStringTokenizer(s);
	int list[] = new int[string.countTokens(',')];
	for (int i=0; string.hasMoreTokens(); i++) {
		String token = string.nextToken(',').trim();
		int def_value = (def != null && i < def.length) ? def[i] : 0;
		list[i] = LabelConverter.toAlignment(conv, token, def_value);
	}
	return list;
}

static void
getParams(JCMultiColumnInterface comp, JCConverter conv) {
	String s = getParam(comp, "numColumns");
	if (s != null && s.equalsIgnoreCase("VARIABLE"))
		comp.setNumColumns(BWTEnum.VARIABLE);
	else if (s != null)
		comp.setNumColumns(conv.toInt(s, comp.getNumColumns()));

	s = getParam(comp, "columnResizePolicy");
	if (s != null)
		comp.setColumnResizePolicy(
				conv.toEnum(s, "ColumnResizePolicy", 
							column_resize_policy_strings, 
							column_resize_policy_values, 
							BWTEnum.RESIZE_SINGLE));

	s = getParam(comp, "columnLabels");
	if (s != null) 
		comp.setColumnLabels(conv.toVector((Component)comp, s, ',', true));

	s = getParam(comp, "columnButtons");
	if (s != null) 
		comp.setColumnButtons(conv.toVector((Component)comp, s, ',', true));

	int[] values = conv.toIntList(getParam(comp, "columnWidths"), ',', null);
	if (values != null) {
		for (int i=0; i < values.length; i++)
			comp.setColumnWidth(i, values[i]);
	}

	if (comp instanceof JCMultiColumnWindow) {
		JCMultiColumnWindow w = (JCMultiColumnWindow) comp;
		w.setColumnLabelSort(conv.toBoolean(getParam(comp, "ColumnLabelSort"),
											w.getColumnLabelSort()));
	}

	values = toAlignmentList(conv, getParam(comp, "ColumnAlignments"), 
							 comp.getColumnAlignments());
	comp.setColumnAlignments(values);

	values = conv.toIntList(getParam(comp, "columnLeftMargins"), ',', null);
	if (values != null) {
		for (int i=0; i < values.length; i++)
			comp.setColumnLeftMargin(i, values[i]);
	}

	values = conv.toIntList(getParam(comp, "columnRightMargins"), ',', null);
	if (values != null) {
		for (int i=0; i < values.length; i++)
			comp.setColumnRightMargin(i, values[i]);
	}
}

}
