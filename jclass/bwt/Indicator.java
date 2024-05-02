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
// RCSID -- $RCSfile: Indicator.java $ $Revision: 2.3 $ $Date: 2000/11/09 20:09:19 $ $Locker: $  Sitraka Inc.

package jclass.bwt;
import jclass.util.*;
import java.awt.*;
import jclass.base.*;

public class Indicator {

private final static String[] box_pixels = {
"ggggggggggggl",
"gbbbbbbbbbbl.",
"gb.........l.",
"gb.........l.",
"gb.........l.",
"gb.........l.",
"gb.........l.",
"gb.........l.",
"gb.........l.",
"gb.........l.",
"gb.........l.",
"gbllllllllll.",
"l............"
};
private static Image box_image;

private final static String[] cross_pixels = {
"bb...bb",
"bbb.bbb",
".bbbbb.",
"..bbb..",
".bbbbb.",
"bbb.bbb",
"bb...bb",
};
private static Image cross_image;

private final static String[] mixed_cross_pixels = {
"l.l.l.l.l",
".bb.l.bb.",
"lbbb.bbbl",
".lbbbbbl.",
"l..bbb.l.",
".lbbbbb.l",
"lbbb.bbb.",
".bb.l.bb.",
"l.l.l.l.l",
};
private static Image mixed_cross_image;

private final static String[] check_pixels = {
".........",
".......b.",
"......bb.",
".b...bbb.",
".bb.bbb..",
".bbbbb...",
"..bbb....",
"...b.....",
".........",
};
private static Image check_image;

private final static String[] mixed_check_pixels = {
"l.l.l.l..",
".l.l.l.bl",
"l.l.l.bb.",
".b.l.bbbl",
"lbb.bbbl.",
".bbbbbl.l",
"l.bbbl.l.",
".l.b.l.l.",
"l.l.l.l.l",
};
private static Image mixed_check_image;

private final static String[] circle_pixels = {
"    llll     ",
"  lbbbbbbl   ",
" lbl.....bl  ",
" bl..lll..l  ",
"lb..bbbbb..l.",
"lb.lbbbbbl.l.",
"lb.lbbbbbl.l.",
"lb.lbbbbbl.l.",
" b..bbbbb..l.",
" lb..lll..l. ",
"  ll.....l . ",
"    lllll..  ",
"    .....    "
};
private static Image circle_image;

private final static String[] circle_empty_pixels = {
"    llll     ",
"  lbbbbbbl   ",
" lbl.....bl  ",
" bl.......l  ",
"lb.........l.",
"lb.........l.",
"lb.........l.",
"lb.........l.",
" b.........l.",
" lb.......l. ",
"  ll.....l . ",
"    lllll..  ",
"    .....    "
};
private static Image circle_empty_image;

private final static String[] diamond_pixels = {
"      t      ",
"     ttt     ",
"    ttttt    ",
"   tttmttt   ",
"  tttmmmttt  ",
" tttmmmmmttt ",
"tttmmmmmmmbbb",
" bbbmmmmmbbb ",
"  bbbmmmbbb  ",
"   bbbmbbb   ",
"    bbbbb    ",
"     bbb     ",
"      b      ",
};

/** Draws the button's indicator. */
public static void
draw(JCCheckbox box, Graphics gc) {
    switch (box.indicator) {

    case BWTEnum.INDICATOR_FILL:
		drawFilledBox(box, gc);
        break;

    case BWTEnum.INDICATOR_CHECK:
		drawBox(box, gc);
		if (box.state == BWTEnum.ON)
            drawCheck(box, gc);
		else if (box.state != BWTEnum.OFF)
            drawMixedCheck(box, gc);
        break;

    case BWTEnum.INDICATOR_CIRCLE:
        if (box.state != BWTEnum.OFF)
            drawCircle(box, gc);
		else
			drawEmptyCircle(box, gc);
        break;

    case BWTEnum.INDICATOR_CROSS:
		drawBox(box, gc);
		if (box.state == BWTEnum.ON)
            drawCross(box, gc); 
		else if (box.state != BWTEnum.OFF)
            drawMixedCross(box, gc); 
        break;

    case BWTEnum.INDICATOR_DIAMOND:
		drawDiamond(box, gc); 
        break;

	case BWTEnum.INDICATOR_IMAGE:
		if (box.image_list != null 
				&& box.state < box.image_list.length
				&& box.image_list[box.state] != null) {
			gc.setColor(box.getBackground());
			gc.fillRect(box.ind_x, box.ind_y, box.ind_width, box.ind_height);
			gc.drawImage(box.image_list[box.state],
						 box.ind_x, box.ind_y, null);
		}
		break;
	}
}

private static Image createImage(Component comp, String[] pixels, Image im) {
	if (im != null) return im;
	JCImageCreator creator = new JCImageCreator(comp);
	creator.setColor('g', Color.gray);
	creator.setColor('l', Color.lightGray);
	creator.setColor('b', Color.black);
	creator.setColor('.', Color.white);
	creator.setSize(pixels.length, pixels.length);
	return creator.create(pixels);
}

private static void
drawImage(JCCheckbox box, Graphics gc, String[] pixels, Image image, int off) {
	image = createImage(box, pixels, image);
	gc.drawImage(image, box.ind_x+off, box.ind_y+off, null);
}

private static Color getFillColor(JCCheckbox box) {
	Color c = null;
    c = (box.state == BWTEnum.ON) ? box.select_color : box.unselect_color;
	return c != null ? c : box.getBackground();
}

/** Draws a box with the box image. */
private static void
drawBox(JCCheckbox box, Graphics gc) {
	drawImage(box, gc, box_pixels, box_image, 0);
}

/** Draws a diamond with the diamond image. */
private static void
drawDiamond(JCCheckbox box, Graphics gc) {
	Color bg = getFillColor(box);
	Color top = (box.state == BWTEnum.ON) ? 
		BWTUtil.darker(bg) : BWTUtil.brighter(bg);
	Color bottom = (box.state == BWTEnum.ON) ? 
		BWTUtil.brighter(bg) : BWTUtil.darker(bg);
	
	JCImageCreator diamond_creator = new JCImageCreator(box, 13, 13);
	diamond_creator.setColor('t', top);
	diamond_creator.setColor('m', bg);
	diamond_creator.setColor('b', bottom);
	gc.drawImage(diamond_creator.create(diamond_pixels), 
				 box.ind_x, box.ind_y, null);
}

/** Draws a cross with the cross image. */
private static void
drawCross(JCCheckbox box, Graphics gc) {
	drawImage(box, gc, cross_pixels, cross_image, 3);
}

/** Draws a cross with the mixed cross image. */
private static void
drawMixedCross(JCCheckbox box, Graphics gc) {
	drawImage(box, gc, mixed_cross_pixels, mixed_cross_image, 2);
}

/** Draws a circle with the circle image. */
private static void
drawCircle(JCCheckbox box, Graphics gc) {
	drawImage(box, gc, circle_pixels, circle_image, 0);
}

/** Draws an empty circle. */
private static void
drawEmptyCircle(JCCheckbox box, Graphics gc) {
	drawImage(box, gc, circle_empty_pixels, circle_empty_image, 0);
}

/** Draws a check with the check image. */
private static void
drawCheck(JCCheckbox box, Graphics gc) {
	drawImage(box, gc, check_pixels, check_image, 2);
}

/** Draws a check with the mixed check image. */
private static void
drawMixedCheck(JCCheckbox box, Graphics gc) {
	drawImage(box, gc, mixed_check_pixels, mixed_check_image, 2);
}

/** Draws a box with the selected or unselected color. */
private static void drawFilledBox(JCCheckbox box, Graphics gc)  {
	gc.setColor(getFillColor(box));
	gc.fillRect(box.ind_x, box.ind_y, box.ind_width-1, box.ind_height-1);
	int type = (box.state == BWTEnum.ON) ? 
	   Border.IN : Border.OUT;
	Border.draw(gc, type, 2,
				box.ind_x, box.ind_y, box.ind_width, box.ind_height, 
				gc.getColor(), null);
}

}
