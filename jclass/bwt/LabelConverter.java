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
// RCSID -- $RCSfile: LabelConverter.java $ $Revision: 2.4 $ $Date: 2000/11/09 20:11:25 $ $Locker: $  Sitraka Inc.

package jclass.bwt;
import jclass.util.*;

/** Reads JCLabel parameters from an HTML file or external file. */
class LabelConverter {

final static int[] alignment_values = {
	BWTEnum.TOPLEFT,
	BWTEnum.TOPLEFT,
	BWTEnum.TOPCENTER,
	BWTEnum.TOPCENTER,
	BWTEnum.TOPRIGHT,
	BWTEnum.TOPRIGHT,
	BWTEnum.MIDDLELEFT,
	BWTEnum.MIDDLECENTER,
	BWTEnum.MIDDLERIGHT,
	BWTEnum.BOTTOMLEFT,
	BWTEnum.BOTTOMCENTER,
	BWTEnum.BOTTOMRIGHT,
};

final static String[] alignment_strings = {
	"TOPLEFT",
	"LEFT",
	"TOPCENTER",
	"CENTER",
	"TOPRIGHT",
	"RIGHT",
	"MIDDLELEFT",
	"MIDDLECENTER",
	"MIDDLERIGHT",
	"BOTTOMLEFT",
	"BOTTOMCENTER",
	"BOTTOMRIGHT",
};

static final String[] shadowtype_strings = {
	"SHADOW_NONE",
	"SHADOW_ETCHED_IN",
	"SHADOW_ETCHED_OUT",
	"SHADOW_IN",
	"SHADOW_OUT",
	"SHADOW_PLAIN",
	"SHADOW_FRAME_IN",
	"SHADOW_FRAME_OUT",
	"CONTROL_SHADOW_IN",
	"CONTROL_SHADOW_OUT",
};

static final int[] shadowtype_values = {
	BWTEnum.SHADOW_NONE,
	BWTEnum.SHADOW_ETCHED_IN,
	BWTEnum.SHADOW_ETCHED_OUT,
	BWTEnum.SHADOW_IN,
	BWTEnum.SHADOW_OUT,
	BWTEnum.SHADOW_PLAIN,
	BWTEnum.SHADOW_FRAME_IN,
	BWTEnum.SHADOW_FRAME_OUT,
	BWTEnum.CONTROL_SHADOW_IN,
	BWTEnum.CONTROL_SHADOW_OUT,
};

static void getParams(JCLabel comp) {
	JCConverter conv = comp.conv;

	comp.label = conv.toJCString(comp, comp.getParam("label"), comp.label);
	comp.alignment = toAlignment(conv, comp.getParam("alignment"), 
								 comp.alignment);
	comp.setBorderStyle(conv.toEnum(comp.getParam("shadowType"), 
									"shadowType", shadowtype_strings,
									shadowtype_values, comp.getBorderStyle()));
}

/** Converts a string to an alignment. If the string cannot be converted,
 * an error message is written to the console.
 * @param s the string to be converted
 * @param def the default value, returned if the string could not be converted
 * @return either the converted value or the default 
 */
static int
toAlignment(JCConverter conv, String s, int def) {
	return conv.toEnum(s, "alignment", alignment_strings, alignment_values, def);
}

static void checkAlignment(int v) { 
	JCUtilConverter.checkEnum(v, "alignment", alignment_values);
}

}
