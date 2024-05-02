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
// RCSID -- $RCSfile: TabManagerConverter.java $ $Revision: 2.1 $ $Date: 2000/11/09 20:11:50 $ $Locker: $  Sitraka Inc.

package jclass.bwt;
import jclass.util.*;

/** Reads JCTabManager parameters from an HTML file or external file. */
class TabManagerConverter {

final static String[] shape_strings = { "RECTANGLE", "SLANTED" };
final static int[] shape_values = { BWTEnum.RECTANGLE, BWTEnum.SLANTED };

final static String[] side_strings = { "TOP", "BOTTOM" };
final static int[] side_values = { BWTEnum.TOP, BWTEnum.BOTTOM };

final static int[] color_values = { BWTEnum.COLOR_PAGE, BWTEnum.COLOR_INHERIT };
final static String[] color_strings = { "COLOR_PAGE", "COLOR_INHERIT" };

static void
getParams(JCTabManager comp) {
	JCConverter conv = comp.conv;
	comp.tab_alignment = LabelConverter.toAlignment(conv, comp.getParam("alignment"), 
								 comp.tab_alignment);
	comp.tab_resize = conv.toBoolean(comp.getParam("tabResize"), comp.tab_resize);
	comp.tab_current_font = conv.toFont(comp.getParam("CurrentTabFont"), comp.tab_current_font);
	comp.first_tab = conv.toInt(comp.getParam("FirstVisibleTab"), comp.first_tab);
	comp.tab_stretch = conv.toBoolean(comp.getParam("tabStretch"), comp.tab_stretch);
	comp.tab_spacing = conv.toInt(comp.getParam("TabSpacing"), comp.tab_spacing);
	comp.tab_shape = conv.toEnum(comp.getParam("TabShape"), "shape", 
								 shape_strings, shape_values, comp.tab_shape);
	comp.tab_labels = conv.toStringList(comp.getParam("TabLabels"));
	comp.tab_side = conv.toEnum(comp.getParam("TabSide"), "side", 
								side_strings, side_values, comp.tab_side);
	comp.tab_color_policy = conv.toEnum(comp.getParam("TabColorPolicy"), 
			"color_policy", color_strings, color_values, comp.tab_color_policy);
}

static void checkTabSide(int v) { 
	JCUtilConverter.checkEnum(v, "TabSide", side_values);
}

static void checkColorPolicy(int v) { 
	JCUtilConverter.checkEnum(v, "TabColorPolicy", color_values);
}

static void checkTabShape(int v) { 
	JCUtilConverter.checkEnum(v, "TabShape", shape_values);
}

}
