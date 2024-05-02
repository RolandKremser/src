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
package jclass.bwt;
import java.awt.*;

class TextLine {

JCTextAreaComponent comp;

/** The characters in the line. */
char[] text = new char[0];

int row;
int start_pos;		// Starting position in text

/** The number of characters in the line (may be less than text.length). */
int num_char;

/** The width of the characters in the line. */
int width;

TextLine(JCTextAreaComponent comp, int row, String s) {
	this.comp = comp;
	this.row = row;
	setText(s);
}

TextLine(JCTextAreaComponent comp, int row, char[] s, int pos, int count) {
	this(comp, row, new String(s, pos, count));
	start_pos = pos;
}

synchronized void setStartPos(int v) { start_pos = v; }

void calcWidth() {
	width = comp.fm.charsWidth(text, 0, num_char);
}

synchronized void append(String s) {
	insert(s, num_char);
}

synchronized void deleteToEnd(int pos) {
	delete(pos, num_char);
}

synchronized void delete(int start, int end) {
	if (end > start) {
		width -= comp.fm.charsWidth(text, start, end-start);
		System.arraycopy(text, end, text, start, num_char - end);
		num_char -= (end - start);
	}
}

synchronized void insert(String s, int pos) {
	int strlen = (s != null) ? s.length() : 0;
	if (strlen == 0) return;
	if (num_char+strlen+pos >= text.length) {
		char newtext[] = new char[Math.max(num_char*2, num_char+pos+strlen+1)];
		System.arraycopy(text, 0, newtext, 0, num_char);
		text = newtext;
	}
	if (num_char > pos)
		System.arraycopy(text, pos, text, pos+strlen, num_char-pos);
	s.getChars(0, strlen, text, pos);
	num_char += strlen;
	width += comp.fm.stringWidth(s);
}

synchronized void replace(String s, int start, int end) {
	delete(start, end);
	insert(s, start);
}

synchronized void setText(String s) {
	num_char = 0;
	insert(s, 0);
	calcWidth();
}

/** Gets the total width. */
int getWidth() { return width; }

/** Gets the width to the position. */
int getWidth(int pos) {
	int w = Math.max(0, Math.min(num_char, pos-start_pos));
	return comp.fm.charsWidth(text, 0, w);
}

/** Gets the width from the position to the end of the line. */
int getWidthToEnd(int pos) {
	int start = pos - start_pos;
	return comp.fm.charsWidth(text, start, Math.max(0, num_char-start));
}

/**
 * Maps an x co-ordinate to a text position.
 */
int xToPosition(int x) {
	int widths[] = comp.fm.getWidths(), pos;
	Rectangle rect = comp.getDrawingArea();

	switch (comp.alignment) {
	case BWTEnum.CENTER:
		x -= rect.x + (rect.width - width) / 2;
		break;
	case BWTEnum.RIGHT:
		x -= rect.x + rect.width - width - comp.horiz_origin;
		break;
	default:
	    x -= rect.x - comp.horiz_origin;
	    break;
	}

	for (pos=0; pos < num_char; pos++) {
	    int w = widths[text[pos]];
	    if (x < w/2) 
			break;
	    x -= w;
	}
	return pos + start_pos;
}

synchronized void paint(Graphics gc) {
	Rectangle draw_rect = comp.getDrawingArea();
	JCComponent.setBounds(draw_rect, draw_rect.x - comp.horiz_origin, 
					draw_rect.y + comp.getRowPosition(row) - comp.vert_origin,
					draw_rect.width + comp.horiz_origin, comp.getRowHeight(row));
	if (!draw_rect.intersects(comp.getPaintRect()))
		return;
	FontMetrics fm = comp.fm;
	int h = draw_rect.height, line_space = h - fm.getAscent();
	int x = draw_rect.x, y = draw_rect.y + h - line_space;
	int select_start = comp.select_start - start_pos,
		select_end = comp.select_end - start_pos;

	switch (comp.alignment) {
	case BWTEnum.CENTER:
		x += (draw_rect.width - width) / 2;
		break;
	case BWTEnum.RIGHT:
		x += draw_rect.width - width;
	}

	if (select_start != select_end
			&& select_start < num_char && select_end >= 0) {
		select_start = Math.max(select_start, 0);
		select_end = Math.min(select_end, num_char);
		if (select_start > 0) {
			gc.drawChars(text, 0, select_start, x, y);
			x += fm.charsWidth(text, 0, select_start);
		}
		int select_w = fm.charsWidth(text, select_start, select_end-select_start);
		Color gc_color = gc.getColor();
		comp.setSelectedBg(gc);
		gc.fillRect(x, draw_rect.y, select_w, draw_rect.height);

		comp.setSelectedFg(gc);
		gc.drawChars(text, select_start, select_end-select_start, x, y);
		x += select_w;

		gc.setColor(gc_color);
		if (select_end < num_char) 
			gc.drawChars(text, select_end, num_char-select_end, x, y);
	}
	else
		gc.drawChars(text, 0, num_char, x, y);
}

public String toString() {
	return "row="+row+" start="+start_pos+" num_char="+num_char+" width="+width+" text="+new String(text, 0, num_char)+"|";
}

}
