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
// RCSID -- $RCSfile: JCProgressMeter.java $ $Revision: 2.8 $ $Date: 2000/11/09 20:10:56 $ $Locker: $  Sitraka Inc.

package jclass.bwt;
import jclass.util.*;
import jclass.base.*;
import java.awt.*;
import java.applet.*;

/**
 * A progress meter can be used by an application to indicate the progress of 
 * a lengthy operation. It consists of a rectangle that is gradually filled, 
 * from left to right, as an operation progresses. An optional label can be
 * used to display the meter's current value or an application-defined string.
 * <p>
 * <strong>Properties</strong><p>
 * <table border>
 * <tr><th>Name</th>    <th>Method</th></tr><tr><td>
 * AutoLabel               </td><td><a href=#setAutoLabel>setAutoLabel</a></td></tr><tr><td>
 * Background          </td><td><a href=jclass.bwt.JCComponent.html#setBackground>setBackground</a></td></tr><tr><td>
 * BarColor          </td><td><a href=#setBarColor>setBarColor</a></td></tr><tr><td>
 * BarCount          </td><td><a href=#setBarCount>setBarCount</a></td></tr><tr><td>
 * BarSpacing          </td><td><a href=#setBarSpacing>setBarSpacing</a></td></tr><tr><td>
 * Font                </td><td><a href=jclass.bwt.JCComponent.html#setFont>setFont</a></td></tr><tr><td>
 * Foreground          </td><td><a href=jclass.bwt.JCComponent.html#setForeground>setForeground</a></td></tr><tr><td>
 * Label               </td><td><a href=#setLabel>setLabel</a></td></tr><tr><td>
 * LabelPosition              </td><td><a href=#setLabelPosition>setLabelPosition</a></td></tr><tr><td>
 * LabelWidth               </td><td><a href=#setLabelWidth>setLabelWidth</a></td></tr><tr><td>
 * Maximum             </td><td><a href=#setMaximum>setMaximum</a></td></tr><tr><td>
 * Minimum             </td><td><a href=#setMinimum>setMinimum</a></td></tr><tr><td>
 * PreferredSize       </td><td><a href=jclass.bwt.JCComponent.html#setPreferredSize>setPreferredSize</a></td></tr><tr><td>
 * ShowLabel               </td><td><a href=#setShowLabel>setShowLabel</a></td></tr><tr><td>
 * Value               </td><td><a href=#setValue>setValue</a></td></tr><tr><td>
 * UserData            </td><td><a href=jclass.bwt.JCComponent.html#setUserData>setUserData</a></td></tr>
 * </table>
 */
public class JCProgressMeter extends JCComponent {

public static final int STRING_LEFT = BWTEnum.STRING_LEFT;
public static final int STRING_RIGHT = BWTEnum.STRING_RIGHT;
public static final int STRING_TOP = BWTEnum.STRING_TOP;
public static final int STRING_BOTTOM = BWTEnum.STRING_BOTTOM;
public static final int STRING_CENTER = BWTEnum.STRING_CENTER;

int	value = 0;
int	min = 0, max = 100;
Color bar_color = Color.red;
int bar_count = 10;
int bar_spacing = 2;
int label_position = STRING_RIGHT;
String label;
boolean auto_label = true;
boolean show_label = true;
int label_width_ext, label_width;
double chunk_width;
long time0;

private static final String base = "progressmeter";
private static int nameCounter = 0;

/** Space between label and bar area, if label is not centered. */
protected int label_inset = 5;

/** Bar area's bounding box */
protected Rectangle bar_area = new Rectangle();

/** Bar's drawn bounding box. */
protected Rectangle bar_rect = new Rectangle();

/** Bar's height. */
protected int bar_height = 15;

/** Bar's top and bottom inset. */
protected int bar_vert_inset = 3;

/** Bar's left and right inset. */
protected int bar_horiz_inset = 2;

/** Bar's shadow thickness. */
protected int bar_shadow = 1;

/**
 * Constructs a meter with a range of [0, 100].
 * @see #setValues
 */
public JCProgressMeter() {
	this(null, null);
}

/** Creates a meter which reads parameters from the applet's HTML file.
 * @param applet the applet whose PARAM tags are to be read
 * @param name if this is not null, only parameters preceded by this name are read
 * @see java.applet.Applet#getParameter
 */
public JCProgressMeter(Applet applet, String name) {
	super(applet, name);
	if (name == null) 
		setName(base + nameCounter++);
	traversable = false;
	border = highlight = 0;
	insets = new Insets(5,5,5,5);
	setAutoLabel(true);
	if (getClass().getName().equals("jclass.bwt.JCProgressMeter"))
		 getParameters(applet);
	time0 = System.currentTimeMillis();
}

/**
 * Constructs a meter with a value, and minimum and max values.
 * @param value the meter's value
 * @param min the min value
 * @param max the max value
 */
public JCProgressMeter(int value, int min, int max) {
	this();
	setValues(value, min, max);
}

/** Reads the parameter values from the HTML page using the component's applet.
 * The values will override those previously set.
 */
protected void getParameters() {
	super.getParameters();
	ProgressMeterConverter.getParams(this);
}

/**
 * Gets the current value. 
 */
public int getValue() { return value; }

/**
 * Gets the current value as a percentage of the range. 
 */
public int getValuePercentage() { 
	// must cast to long since huge ints will cause wrapping since the
	// calculation may surpass MAX INT.
	long calc = 100*((long)value-(long)min)/((long)max-(long)min);
	return (max > min) ? new Long(calc).intValue() : 0;
}

/**
 * Sets the value.
 * @param value the new value. If this value is
 * below the current minimum or above the current maximum, it becomes the
 * new one of those values, respectively.
 * @see #getValue
 */
public synchronized void setValue(int v) {
	v = Math.max(min, Math.min(v, max));
	if (value != v) {
		value = v;
		repaint();
		getToolkit().sync();
	}
}

/**
 * Gets the minimum value.
 * @see #setValues
 */
public int getMinimum() { return min; }

/**
 * Sets the minimum value.
 * @see #setValues
 */
public void setMinimum(int v) { setValues(value, v, max); }

/**
 * Gets the maximum value.
 * @see #setValues
 */
public int getMaximum() { return max; }

/**
 * Sets the maximum value.
 * @see #setValues
 */
public void setMaximum(int v) { setValues(value, min, v); }

/**
 * Sets the meter's values.
 * @param value the meter's value
 * @param min the minimum value of the meter
 * @param max the maximum value of the meter
 */
public synchronized void setValues(int value, int min, int max) {
	this.min = min;
	this.max = max;
	setValue(value);
	time0 = System.currentTimeMillis();
}

/** Gets the BarColor value.
 * @see #setBarColor
 */
public Color getBarColor() { return bar_color; }

/** Sets the bar's color (default: red). */
public synchronized void setBarColor(Color v) { 
	bar_color = v;
	repaint();
}

/** Gets the BarCount value.
 * @see #setBarCount
 */
public int getBarCount() { return bar_count; }

/** Sets the number of discrete bars eventually displayed (default: 10). */
public synchronized void setBarCount(int v) { 
	bar_count = v;
	layout();
	repaint();
}

/** Gets the BarSpacing value.
 * @see #setBarSpacing
 */
public int getBarSpacing() { return bar_spacing; }

/** Sets the space (in pixels) between each bar, if BarCount is greater than 1
 * (default: 2).
 * @see #setBarCount
 */
public synchronized void setBarSpacing(int v) { 
	bar_spacing = v;
	layout();
	repaint();
}

/** Gets the Label value.
 * @see #setLabel
 */
public String getLabel() { return label; }

/** Sets the label (default: null). If set, this value overrides the AutoLabel
 * value.
 * @see #setAutoLabel
 * @see #setLabelPosition
 */
public synchronized void setLabel(String s) { 
	label = s;
	repaint();
}

/** Gets the AutoLabel value.
 * @see #setAutoLabel
 */
public boolean getAutoLabel() { return auto_label; }

/** If true (default), a label will be displayed showing the meter's value
 * as a percentage. If Label is not null, it will be displayed instead.
 * If set to true and LabelWidth has not been set, its value is set to 4.
 * @see #setLabel
 * @see #setLabelPosition
 * @see #setLabelWidth
 */
public synchronized void setAutoLabel(boolean v) { 
	auto_label = v;
	if (label_width_ext == 0) 
		label_width = v ? 4 : 10;
	repaint();
}

/** Gets the ShowLabel value.
 * @see #setShowLabel
 */
public boolean getShowLabel() { return show_label; }

/** If true (default), a label will be displayed.
 * @see #setAutoLabel
 * @see #setLabel
 * @see #setLabelPosition
 */
public synchronized void setShowLabel(boolean v) { 
	show_label = v;
	repaint();
}

/** Gets the LabelPosition value.
 * @see #setLabelPosition
 */
public int getLabelPosition() { return label_position; }

/** Sets the label's position, relative to the bar:
 * STRING_TOP, STRING_LEFT, STRING_RIGHT (default), STRING_BOTTOM or STRING_CENTER
 * @exception IllegalArgumentException If the value is invalid
 * @see #setLabel
 */
public synchronized void setLabelPosition(int v) { 
	ProgressMeterConverter.checkPosition(v);
	label_position = v;
	layout();
	repaint();
}

/** Gets the LabelWidth value.
 * @see #setLabelWidth
 */
public int getLabelWidth() { return label_width_ext; }

/** Sets the maximum number of characters which will be displayed, 
 * if ShowLabel is true (default: 4 if AutoLabel is true, 10 otherwise).
 * @see #setAutoLabel
 * @see #setLabel
 * @see #setShowLabel
 */
public synchronized void setLabelWidth(int v) { 
	label_width_ext = label_width = v;
	layout();
	repaint();
}

/** Gets the time elapsed (in ms) since the minimum value was last set. 
 * @see #setValues
 */
public long getTimeElapsed() { return System.currentTimeMillis() - time0; }

/** Calculates an estimate of the time (in ms) before which the value will be 100%.
 * @return BWTEnum.MAXINT if the value is equal to the minimum
 * @see #getTimeElapsed
 */
public long getTimeToCompletion() {
	if (value == min) return BWTEnum.MAXINT;
	long t = getTimeElapsed();
	return (long)((double)(max-min) / (double)(value-min)*t - t);
}

/** Converts an int to a zero-filled string. */
final private String str(int v) { return (v < 10) ? "0"+v : ""+v; }

/** Calculates an estimate of the time before which the value will be 100%.
 * @return time in format HH:MM:SS, or "?" if the value is equal to the minimum
 * @see #getTimeElapsed
 */
public String getTimeToCompletionString() {
	if (value == min) return "?";
	int t = (int) getTimeToCompletion();
	t = t/1000;
	int h = t / 3600;
	t -= h * 3600;
	int m = t / 60;
	t -= m * 60;
	return str(h)+":"+str(m)+":"+str(t);
}

protected int preferredWidth() {
	int w = 0;
	if (show_label
		&& (label_position == BWTEnum.STRING_LEFT 
			|| label_position == BWTEnum.STRING_RIGHT))
		w = label_width * getToolkit().getFontMetrics(getFont()).charWidth('N');
	return w + 200;
}

protected int preferredHeight() {
	int h = 0;
	if (show_label
		&& (label_position == BWTEnum.STRING_TOP 
			|| label_position == BWTEnum.STRING_BOTTOM))
		h = getToolkit().getFontMetrics(getFont()).getHeight();
	return h + bar_height + label_inset + 2*bar_vert_inset + 2*bar_shadow;
}

public void layout() {
	if (!isDisplayable()) return;
	needs_layout = false;
	bar_area = getDrawingArea();

	if (!show_label) {
	}
	else if (label_position == BWTEnum.STRING_TOP
			 || label_position == BWTEnum.STRING_BOTTOM) {
		int h = getToolkit().getFontMetrics(getFont()).getHeight() + label_inset;
		if (label_position == BWTEnum.STRING_TOP)
			bar_area.y += h;
		bar_area.height -= h;
	}		
	else if (label_position == BWTEnum.STRING_LEFT
			 || label_position == BWTEnum.STRING_RIGHT) {
		int w = label_inset + 
			label_width * getToolkit().getFontMetrics(getFont()).charWidth('N');
		if (label_position == BWTEnum.STRING_LEFT)
			bar_area.x += w;
		bar_area.width -= w;
	}		

	chunk_width = 0;
	if (bar_count > 0 && bar_spacing > 0) {
		int h_margin = bar_horiz_inset + bar_shadow;
		chunk_width = (double)(bar_area.width - 2*h_margin
							   - ((bar_count-1) * bar_spacing)) 
			/ (double)bar_count;
		if (chunk_width < 1) chunk_width = 0;
	}
}

/** Draws the shadow around the bar. */
protected void drawShadow(Graphics gc) {
/* SWING_START */
	if (getBorder() != null) {
		getBorder().paintBorder(this, gc, bar_area.x, bar_area.y, 
								  bar_area.width, bar_area.height);
	}
	else {
/* SWING_END */
		Border.draw(gc, Border.IN, bar_shadow,
					bar_area.x, bar_area.y,
					bar_area.width, bar_area.height,
					getBackground(), getForeground());
/* SWING_START */
	}
/* SWING_END */
}

/** Draws the bar. */
protected void drawBar(Graphics gc) {
	int h_margin = bar_horiz_inset + bar_shadow,
		v_margin = bar_vert_inset + bar_shadow;
	JCComponent.setBounds(bar_rect, bar_area.x + h_margin, 
						  bar_area.y + v_margin, bar_area.width - 2 * h_margin,
						  bar_area.height - 2*v_margin);
	int bar_width = bar_rect.width;
	bar_rect.width *= getValuePercentage()/100.;

	if (chunk_width == 0) {
		gc.fillRect(bar_rect.x, bar_rect.y, bar_rect.width, bar_rect.height);
		return;
	}

	int remaining = 0, num_chunks = 0, x = bar_rect.x;
	int[] chunk_x = new int[bar_count], chunk_w = new int[bar_count];
	synchronized(this) {
	for (int i=0; i < bar_count; i++, x += (int)chunk_width + bar_spacing) {
		chunk_x[i] = x;
		chunk_w[i] = (int)chunk_width;

		// calculate the space remaining at the end of the bar. 
		if (i == bar_count-1)
			remaining = bar_rect.x + bar_width - (x + (int)chunk_width);
		if (chunk_x[i] + chunk_w[i] - bar_rect.x <= bar_rect.width) 
			num_chunks++;
	}
	}

	/*
	 * Spread remaining pixels out to the existing chunks
	 */
	// Add 1 pixel to each block until it equals the remaining.
    // i.e if there is 5 remaining pixels, the width of the first 
    // 5 blocks will be increased by 1 to fill in the space. 
	if (remaining > 0) {
		for (int i=0; i < num_chunks; i++) {
			if (i < remaining) {
				chunk_x[i] += i;
				chunk_w[i] += 1;
			}
			else {
				chunk_x[i] += remaining;
			}
		}
	}
	for (int i=0; i < num_chunks; i++) {
		gc.fillRect(chunk_x[i], bar_rect.y, chunk_w[i], bar_rect.height);
	}
}

/** Draws the label. */
protected void drawLabel(Graphics gc, String label) {
	Rectangle rect = getDrawingArea();
	int align = BWTEnum.MIDDLECENTER;

	switch (label_position) {
	case BWTEnum.STRING_LEFT:
		align = BWTEnum.MIDDLERIGHT;
		rect.width = bar_area.x - label_inset - rect.x;
		break;
	case BWTEnum.STRING_RIGHT:
		align = BWTEnum.MIDDLELEFT;
		rect.x = bar_area.x + bar_area.width + label_inset;
		break;
	case BWTEnum.STRING_TOP:
		align = BWTEnum.BOTTOMCENTER;
		rect.height = bar_area.y - label_inset - rect.y;
		break;
	case BWTEnum.STRING_BOTTOM:
		align = BWTEnum.TOPCENTER;
		rect.y = bar_area.y + bar_area.height + label_inset;
		break;
	}
	BWTUtil.draw(this, gc, label, align, rect);

	// Redraw the part of the label which covers the bar in white text
	if (label_position != BWTEnum.STRING_CENTER) return;
	gc = gc.create();
	gc.clipRect(bar_rect.x, bar_area.y,
				bar_rect.width, bar_area.height);
	gc.setColor(getBackground());
	BWTUtil.draw(this, gc, label, align, rect);
}

/** Draws the shadow, bar and label. 
 * @see #drawShadow
 * @see #drawBar
 * @see #drawLabel
 */
protected void paintComponent(Graphics gc) {
	if (needs_layout) layout();
	drawShadow(gc);
	gc.setColor(bar_color);
	drawBar(gc);

	if (show_label) {
		String s = label;
		if (s == null && auto_label)
			s = "" + getValuePercentage() + "%";

		gc.setColor(getForeground());
		drawLabel(gc, s);
	}
}

}
