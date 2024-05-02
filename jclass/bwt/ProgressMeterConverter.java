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
// RCSID -- $RCSfile: ProgressMeterConverter.java $ $Revision: 2.1 $ $Date: 2000/11/09 20:11:38 $ $Locker: $  Sitraka Inc.

package jclass.bwt;
import jclass.util.*;

/** Reads JCLabel parameters from an HTML file or external file. */
class ProgressMeterConverter {

final static String[] pos_strings = {
	"STRING_LEFT", "STRING_RIGHT", "STRING_TOP",
	"STRING_BOTTOM", "STRING_CENTER"
};

final static int[] pos_values = {
	BWTEnum.STRING_LEFT, BWTEnum.STRING_RIGHT, BWTEnum.STRING_TOP,
	BWTEnum.STRING_BOTTOM, BWTEnum.STRING_CENTER
};

static void
getParams(JCProgressMeter comp) {
	JCConverter conv = comp.conv;

	comp.label = comp.getParam("label");
	comp.label_position = conv.toEnum(comp.getParam("labelPosition"), "position", 
							   pos_strings, pos_values, comp.label_position);
	comp.auto_label = conv.toBoolean(comp.getParam("autoLabel"), comp.auto_label);
	comp.show_label = conv.toBoolean(comp.getParam("showLabel"), comp.show_label);
	comp.value = conv.toInt(comp.getParam("value"), comp.value);
	comp.label_width = conv.toInt(comp.getParam("labelWidth"), comp.label_width);
	comp.min = conv.toInt(comp.getParam("minimum"), comp.min);
	comp.max = conv.toInt(comp.getParam("maximum"), comp.max);
	comp.bar_count = conv.toInt(comp.getParam("barCount"), comp.bar_count);
	comp.bar_spacing = conv.toInt(comp.getParam("barSpacing"), comp.bar_spacing);
	comp.bar_color = conv.toColor(comp.getParam("barColor"), comp.bar_color);
}

static void checkPosition(int v) { 
	JCUtilConverter.checkEnum(v, "position", pos_values);
}

}
