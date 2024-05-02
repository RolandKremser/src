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
package jclass.bwt;
import java.awt.*;

/** A private class used by a JCTextArea to store each line of text.
 * @see JCTextArea
 */
public class JCTextLine {

JCTextComponent comp;

/** The characters in the line. */
char[] text = new char[0];

/** The number of characters in the line (may be less than text.length). */
int num_char;

/** The width of the characters in the line. */
int width;

public JCTextLine(JCTextComponent comp, String s) {
	this.comp = comp;
	setText(s);
}

public JCTextLine(JCTextComponent comp, char[] s, int offset, int count) {
	this(comp, new String(s, offset, count));
}

public void calcWidth() {
	width = comp.fm.charsWidth(text, 0, num_char);
}

public void append(String s) {
	insert(s, num_char);
}

public void deleteToEnd(int pos) {
	delete(pos, num_char);
}

public void delete(int start, int end) {
	if (end > start) {
		width -= comp.fm.charsWidth(text, start, end-start);
		System.arraycopy(text, end, text, start, num_char - end);
		num_char -= (end - start);
	}
}

public void insert(String s, int pos) {
	int strlen = (s != null) ? s.length() : 0;
	if (strlen == 0) return;
	if (num_char + strlen >= text.length) {
		char newtext[] = new char[Math.max(num_char*2, num_char+strlen)];
		System.arraycopy(text, 0, newtext, 0, num_char);
		text = newtext;
	}
	System.arraycopy(text, pos, text, pos+strlen, num_char - pos);
	s.getChars(0, strlen, text, pos);
	num_char += strlen;
	width += comp.fm.stringWidth(s);
}

public void replace(String s, int start, int end) {
	delete(start, end);
	insert(s, start);
}

public void setText(String s) {
	num_char = 0;
	insert(s, 0);
	calcWidth();
}

}
