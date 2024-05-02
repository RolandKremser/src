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
// RCSID -- $RCSfile: CheckboxConverter.java $ $Revision: 2.1 $ $Date: 2000/11/09 20:09:13 $ $Locker: $  Sitraka Inc.

package jclass.bwt;
import jclass.util.*;

/** Reads JCCheckbox parameters from an HTML file or external file. */
class CheckboxConverter {

public final static int[] indicator_values = {
	BWTEnum.INDICATOR_CHECK,
	BWTEnum.INDICATOR_CIRCLE,
	BWTEnum.INDICATOR_CROSS,
	BWTEnum.INDICATOR_DIAMOND,
	BWTEnum.INDICATOR_FILL,
	BWTEnum.INDICATOR_IMAGE,
};

public final static String[] indicator_strings = {
	"INDICATOR_CHECK",
	"INDICATOR_CIRCLE",
	"INDICATOR_CROSS",
	"INDICATOR_DIAMOND",
	"INDICATOR_FILL",
	"INDICATOR_IMAGE",
};

static void
getParams(JCCheckbox comp) {
	JCConverter conv = comp.conv;

	comp.indicator = conv.toEnum(comp.getParam("indicator"), "indicator", 
				 indicator_strings, indicator_values, comp.indicator);
	comp.image_list = conv.toImageList(comp, 
					   comp.getParam("indicatorImageList"), comp.image_list);
	comp.num_states = conv.toInt(comp.getParam("numStates"), comp.num_states);
	comp.select_color = conv.toColor(comp.getParam("selectColor"), 
									 comp.select_color);
	comp.unselect_color = conv.toColor(comp.getParam("unselectColor"), 
									   comp.unselect_color);

	String s = comp.getParam("state");
	if (s != null) {
		if (s.equalsIgnoreCase("on"))
			comp.setState(BWTEnum.ON);
		else if (s.equalsIgnoreCase("off"))
			comp.setState(BWTEnum.OFF);
		else
			comp.setState(conv.toInt(s, comp.state));
	}
}

static void checkIndicator(int v) {
	JCUtilConverter.checkEnum(v, "indicator", indicator_values);
}

}
