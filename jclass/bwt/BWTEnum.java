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
// RCSID -- $RCSfile: BWTEnum.java $ $Revision: 2.5 $ $Date: 2000/11/09 20:09:11 $ $Locker: $  Sitraka Inc.

package jclass.bwt;
import java.awt.*;
import jclass.base.*;

/** These are commonly used variables and constants in JClass BWT. Java does not incorporate the idea of enums -- BWTEnum takes care of this otherwise absent function.*/

public class BWTEnum {

public static final char BELL = 7;
public static final char BS = 8;
public static final char TAB = 9;
public static final char ENTER = 10;
public static final char DELETE = 127;
public static final char ESC = 27;

public static final int CASE_AS_IS = 0;
public static final int CASE_LOWER = 1;
public static final int CASE_UPPER = 2;

public static final int MAXINT = Integer.MAX_VALUE;
public static final int NOTFOUND = -999;
public static final int NOVALUE = BaseComponent.NOVALUE;
public static final int DEFAULT = -999;
public static final int VARIABLE = -998;
public static final int FONT_HEIGHT = -997;

public static final int COLOR_PAGE = 0;
public static final int COLOR_INHERIT = 1;

public static final int COMBOBOX_SIMPLE	= 0;
public static final int COMBOBOX_DROPDOWN = 1;
public static final int COMBOBOX_DROPDOWN_LIST = 2;

public static final int INDICATOR_FILL	= 0;
public static final int INDICATOR_CHECK	= 1;
public static final int INDICATOR_CIRCLE = 2;
public static final int INDICATOR_CROSS	= 3;
public static final int INDICATOR_DIAMOND = 4;
public static final int INDICATOR_IMAGE = 5;

public static final int FOLDER_CLOSED = 1;
public static final int FOLDER_OPEN_NONE = 2;
public static final int FOLDER_OPEN_FOLDERS = 3;
public static final int FOLDER_OPEN_ITEMS = 4;
public static final int FOLDER_OPEN_ALL = 5;

public static final int RECTANGLE = 0;
public static final int SLANTED = 1;

public static final int HORIZONTAL = 0;
public static final int VERTICAL = 1;

public static final int OFF = 0;
public static final int ON = 1;
public static final int INDETERMINATE = 2;

// Preferred height/width of a horizontal/vertical scrollbar
public static final int SB_SIZE = 16;

public static final int TOPLEFT = Label.LEFT;
public static final int LEFT = TOPLEFT;
public static final int TOPCENTER = Label.CENTER;
public static final int CENTER = TOPCENTER;
public static final int TOPRIGHT = Label.RIGHT;
public static final int RIGHT = TOPRIGHT;
public static final int MIDDLELEFT = 3;
public static final int MIDDLECENTER = 4;
public static final int MIDDLE = MIDDLECENTER;
public static final int MIDDLERIGHT = 5;
public static final int BOTTOMLEFT = 6;
public static final int BOTTOMCENTER = 7;
public static final int BOTTOMRIGHT = 8;

// Same as jclass.util.JCString
public static final int STRING_LEFT = 0;
public static final int STRING_RIGHT = 1;
public static final int STRING_TOP = 2;
public static final int STRING_BOTTOM = 3;
public static final int STRING_CENTER = 4;

public static final int DOWN = 9;
public static final int UP = 10;

public static final int TOP = 1;
public static final int BOTTOM = 5;

public static final int SPLIT = 3;

public static final int SHADOW_NONE = Border.NONE;
public static final int SHADOW_ETCHED_IN = Border.ETCHED_IN;
public static final int SHADOW_ETCHED_OUT = Border.ETCHED_OUT;
public static final int SHADOW_IN = Border.IN;
public static final int SHADOW_OUT = Border.OUT;
public static final int SHADOW_PLAIN = Border.PLAIN;
public static final int SHADOW_FRAME_IN = Border.FRAME_IN;
public static final int SHADOW_FRAME_OUT = Border.FRAME_OUT;
public static final int CONTROL_SHADOW_IN = Border.CONTROL_IN;
public static final int CONTROL_SHADOW_OUT = Border.CONTROL_OUT;

public static final int DISPLAY_AS_NEEDED = 0;
public static final int DISPLAY_ALWAYS = 1;
public static final int DISPLAY_NONE = 2;
public static final int DISPLAY_VERTICAL_ONLY = 3;
public static final int DISPLAY_HORIZONTAL_ONLY = 4;

public static final int SELECT_CHAR = 1;
public static final int SELECT_WORD = 2;
public static final int SELECT_LINE = 3;
public static final int SELECT_PARAGRAPH = 4;
public static final int SELECT_ALL = 5;

public static final int RESIZE_SINGLE = 0;
public static final int RESIZE_NONE = 1;
public static final int RESIZE_COLLAPSE = 2;

}

