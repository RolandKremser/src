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
// RCSID -- $RCSfile: JCTextInterface.java $ $Revision: 2.9 $ $Date: 2000/11/09 20:11:22 $ $Locker: $  Sitraka Inc.

package jclass.bwt;
import java.awt.*;

public interface JCTextInterface
{

/**
 * Sets the text of this component to the specified text.
 * @see #getText
 */
public void setText(String t);

/**
 * Returns the text contained in this component.
 * @see #setText
 */
public String getText();

/**
 * Returns the selected text contained in this component.
 * @see #setText
 */
public String getSelectedText();

/**
 * Returns the boolean indicating whether this component is editable or not.
 * @see #setEditable
 */
public boolean isEditable();

/**
 * Returns the boolean indicating whether this component is editable or not.
 * @see #setEditable
 */
public boolean getEditable();

/**
 * Determines whether this component is editable.
 * @see #isEditable
 */
public void setEditable(boolean t);

/**
 * Returns the selected text's start position.
 */
public int getSelectionStart();

/**
 * Returns the selected text's end position.
 */
public int getSelectionEnd();

/**
 * Sets the selected text's start position.
 */
public void setSelectionStart(int pos);

/**
 * Sets the selected text's end position.
 */
public void setSelectionEnd(int pos);

/**
 * Selects the text found between the specified start and end locations.
 */
public void select(int start, int end);

/**
 * Selects all the text in the component.
 */
public void selectAll();

/**
 * Inserts text at the specified position.
 * @see #setText
 * @see #replaceRange
 */
public void insert(String str, int pos);

/**
 * Appends text to the end.
 * @see #insert
 */
public void append(String str);

/**
 * Replaces text between two positions with the specified text.
 */
public void replaceRange(String str, int start, int end);

/**
 * Returns the number of columns.
 */
public int getColumns();

/**
 * Sets the number of columns.
 */
public void setColumns(int v);

/** Sets the position of the text:<br>
BWTEnum.LEFT (default), CENTER, or RIGHT.
 */
public void setAlignment(int v);

/** Sets the maximum number of characters which may be entered by the user
 * (default: BWTEnum.MAXINT). If the user attempts to enter more characters,
 * the values will be ignored and the bell will be sounded.
 * This value is ignored for values set programmatically.
 */
public void setMaximumLength(int v);
/** Sets the case of text entered by the user or set programmatically:
<pre>
    BWTEnum.CASE_AS_IS       No conversion (default)
    BWTEnum.CASE_LOWER       Convert to lower case
    BWTEnum.CASE_UPPER       Convert to upper case
</pre>
 */
public void setStringCase(int v);

/** Emits a beep. */
public void beep();

/** Returns true if the user has changed the value since the last 
 * programmatic setting.
 */
public boolean getChanged();

/**
 * Returns the minimum size needed for the specified number of columns.
 */
public Dimension getMinimumSize(int columns);

/** Gets the current SelectionList.
 * @see #setSelectionList
 */
public int[] getSelectionList();

/** Sets a list of values used for multi-click. As the mouse is clicked in
 * rapid succession, each click selects the next type in the list.
 * Valid BWTEnum values (in order of the default list):
 * <pre>
 * SELECT_POSITION     Selects the current pointer position
 * SELECT_WORD         Selects the current word
 * SELECT_LINE         Selects the current line
 * SELECT_PARAGRAPH    Selects the current paragraph
 * SELECT_ALL          Selects all the text
 * </pre>
 * Default list: { BWTEnum.SELECT_POSITION, SELECT_WORD, SELECT_LINE, SELECT_ALL }
 */
public void setSelectionList(int[] list);

/**
 * Gets the SelectedBackground value.
 * @see #setSelectedBackground
 */
public Color getSelectedBackground();

/**
 * Sets the background color of selected text (default: blue).<p>
 * <strong>HTML param name/value:</strong> "SelectedBackground"/color
 * @see jclass.util.JCConverter#toColor
 */
public void setSelectedBackground(Color v);

/**
 * Gets the SelectedForeground value.
 * @see #setSelectedForeground
 */
public Color getSelectedForeground();

/**
 * Sets the foreground color of selected text (default: background color).<p>
 * <strong>HTML param name/value:</strong> "SelectedForeground"/color
 * @see jclass.util.JCConverter#toColor
 */
public void setSelectedForeground(Color v);

/**
 * Maps a physical position to the corresponding character position.
 */
public int pointToPosition(int x, int y);

/**
 * Scrolls the text if necessary to ensure that the position is visible.
 * @param pos the number of characters from the beginning of the text buffer.
 */
public void showPosition(int pos);

/** Gets the position of the cursor.
 * @see #setCursorPosition
 */
public int getCursorPosition();

/**
 * Sets the position of the cursor.
 */
public void setCursorPosition(int pos);

/** If false (default), characters typed by the user are inserted into
 * the current text.
 */
public void setOverstrike(boolean v);

/** Gets the Overstrike value.
 * @see #setOverstrike
 */
public boolean getOverstrike();

/** Gets the position of the last character in the text.
 * This represents the position that text appended to the component would be
 * placed after.
 */
public int getLastPosition();

/** Gets the ShowCursorPosition value.
 * @see #setShowCursorPosition
 */
public boolean getShowCursorPosition();

/** 
 * If set to true (default), the cursor position will be indicated with
 * a vertical I-beam.
 */
public void setShowCursorPosition(boolean v);

/**
 * Adds the specified listener to receive text events.
 * @see JCTextEvent
 */ 
public void addTextListener(JCTextListener l);

/**
 * Adds the specified JCTextCursorEvent listener to receive cursor movement events.
 * @see JCTextCursorEvent
 */ 
public void addTextCursorListener(JCTextCursorListener l);

}
