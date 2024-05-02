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
// RCSID -- $RCSfile: TextComponentConverter.java $ $Revision: 2.1 $ $Date: 2000/11/09 20:11:54 $ $Locker: $  Sitraka Inc.

package jclass.bwt;
import jclass.util.*;

/** Reads JCTextComponent parameters from an HTML file or external file. */
class TextComponentConverter {

final static String[] alignment_strings = { "LEFT", "CENTER", "RIGHT" };
final static int[] alignment_values = { BWTEnum.LEFT, BWTEnum.CENTER, BWTEnum.RIGHT };

final static int[] case_values = { 
	BWTEnum.CASE_AS_IS, BWTEnum.CASE_LOWER, BWTEnum.CASE_UPPER
};
final static String[] case_strings = { 
	"CASE_AS_IS", "CASE_LOWER", "CASE_UPPER"
};

static void
getParams(JCTextComponent comp) {
	JCConverter conv = comp.conv;
	comp.alignment = conv.toEnum(comp.getParam("alignment"), "alignment", 
						 alignment_strings, alignment_values, comp.alignment);
	comp.string_case = conv.toEnum(comp.getParam("StringCase"), "StringCase", 
								 case_strings, case_values, comp.string_case);
	comp.columns = conv.toInt(comp.getParam("Columns"), comp.columns);
	String s = comp.getParam("Text");
	if (s != null) 
		comp.text = s.toCharArray();
	comp.max_length = conv.toInt(comp.getParam("MaximumLength"), comp.max_length);
	comp.cursor_pos = conv.toInt(comp.getParam("CursorPosition"), comp.cursor_pos);
	comp.display_cursor = conv.toBoolean(comp.getParam("ShowCursorPosition"), comp.display_cursor);
	comp.overstrike = conv.toBoolean(comp.getParam("Overstrike"), comp.overstrike);
	comp.editable = conv.toBoolean(comp.getParam("Editable"), comp.editable);
	comp.setSelectedBackground(conv.toColor(comp.getParam("SelectedBackground"),
											comp.getSelectedBackground()));
	comp.setSelectedForeground(conv.toColor(comp.getParam("SelectedForeground"),
											comp.getSelectedBackground()));
}

static void checkAlignment(int v) { 
	JCUtilConverter.checkEnum(v, "alignment", alignment_values);
}

static void checkStringCase(int v) { 
	JCUtilConverter.checkEnum(v, "StringCase", case_values);
}

}
