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
// RCSID -- $RCSfile: JCComponent.java $ $Revision: 2.35 $ $Date: 2000/12/19 00:04:37 $ $Locker: $  Sitraka Inc.

package jclass.bwt;
import jclass.util.*;
import jclass.base.*;
import java.applet.*;
import java.awt.Component;
import java.awt.*;
import java.util.*;

import All_Unlimited.Allgemein.Static;

/* JDK110_START */
import java.awt.event.*;
/* JDK110_END */

/**
 * The abstract base class for all BWT components.<p>
 * <strong>Methods which may be supplied by a subclass</strong><br>
 * <pre>
 * <a href=#drawComponent>drawComponent</a> (mandatory)
 * <a href=#preferredWidth>preferredWidth</a>
 * <a href=#preferredHeight>preferredHeight</a>
 * <a href=#reshape>reshape</a>
 * </pre><p>
 *
 * <strong>Properties</strong><p>
 * <table border>
 * <tr><th>Name</th>    <th>Method</th></tr><tr><td>
 * Background          </td><td><a href=#setBackground>setBackground</a></td></tr><tr><td>
 * DoubleBuffer        </td><td><a href=#setDoubleBuffer>setDoubleBuffer</a></td></tr><tr><td>
 * Font                </td><td><a href=#setFont>setFont</a></td></tr><tr><td>
 * Foreground          </td><td><a href=#setForeground>setForeground</a></td></tr><tr><td>
 * HighlightColor      </td><td><a href=#setHighlightColor>setHighlightColor</a></td></tr><tr><td>
 * HighlightThickness  </td><td><a href=#setHighlightThickness>setHighlightThickness</a></td></tr><tr><td>
 * Insets              </td><td><a href=#setInsets>setInsets</a></td></tr><tr><td>
 * PreferredSize       </td><td><a href=#setPreferredSize>setPreferredSize</a></td></tr><tr><td>
 * ShadowThickness  </td><td><a href=jclass.base.BaseComponent#setShadowThickness>setShadowThickness</a></td></tr><tr><td>
 * Traversable         </td><td><a href=#setTraversable>setTraversable</a></td></tr><tr><td>
 * UserData            </td><td><a href=#setUserData>setUserData</a></td></tr>
 * </table><p>
 */
public abstract class JCComponent extends TransientComponent {

public static final String version = jclass.bwt.JCVersion.getVersionString();
public static final String about = "JClass BWT by Sitraka (www.klg.com)";

static JCConverter conv = new JCConverter();

/** Creates a component. No parameters are read from an HTML file. */
public JCComponent() {
	this(null, null);
}

/** Creates a component which reads parameters from the applet's HTML file.
 * @param applet the applet whose PARAM tags are to be read
 * @param name if this is not null, only parameters preceded by this name are read
 * @see java.applet.Applet#getParameter
 */
public JCComponent(Applet applet, String name) {
	super();
	this.applet = applet;
	setName(name);
	if (getClass().getName().equals("jclass.bwt.JCComponent"))
		 getParameters(applet);
}

/** Gets the name of the component, as set in the constructor or by setName.
 * If no name has been set, a 0-length string will be returned.
 * @see #setName
 */
/* JDK102_START
public String getName() { return name != null ? name : ""; }
 JDK102_END */

/**
 * Sets the name of the component.
 */
/* JDK102_START
public void setName(String name) { this.name = name; }
 JDK102_END */

/** Returns vendor information. */
public String getAbout() { return about; }

/** Provided for beans property editors - has no effect. */
public void setAbout(String s) {}

/** Returns the current product version. */
public String getVersion() { return version; }

/** Provided for beans property editors - has no effect. */
public void setVersion(String s) {}

/** Gets a paramater for this component. */
String getParam(String param) {
	return conv.getParam(applet, this, getName(), param);
}

/** Reads the parameter values from the HTML page using the component's applet.
 * The values will override those previously set.
 * Subclasses may override this method to set their own values - in this case,
 * the method should first call super.getParameters().
 */
protected void getParameters() {
	ComponentConverter.getParams(this);
}

/** Reads the parameter values from the HTML page using the specified applet.
 * The values will override those previously set.
 * @see java.applet.Applet#getParameter
 */
public void getParameters(Applet applet) {
	getParameters(applet, null);
}

/** Reads the parameter values from the file.
 * The values will override those previously set.
 * @param applet if not null and in a browser, its documentBase() is used to construct a complete filename if necessary
 * @param file if an http protocol is not specified (if a ":" is not present), the current working directory is used
 */
public void getParameters(Applet applet, String file) {
	this.applet = applet;

	if (file == null)
		file = getParam("paramFile");
	if (file != null)
		JCUtilConverter.setParamSource(this, JCFile.read(applet, file));

	// Read HTML file if running in a browser, or file if it is not null
	if (file != null || getAppletContext() != null) {
		getParameters();
		if (isDisplayable()) {
			addNotify();
			repaint();
		}
	}
}

public void addNotify() {

	super.addNotify();
}

/** Sets the converter to be used for converting strings to internal values.
 * @see jclass.util.JCConverter
 */
public static void setConverter(JCConverter c) { conv = c; }

/** Gets the converter used for converting strings to internal values.
 * @see jclass.util.JCConverter
 */
public static JCConverter getConverter() { return conv; }

/**
 * Sets the font of the component.<p>
 * <strong>HTML param name/value:</strong> "Font"/font
 * @see jclass.util.JCConverter#toFont
 */
public void setFont(Font f) {
	super.setFont(f);
}

/**
 * Sets the background color of the component.<p>
 * <strong>HTML param name/value:</strong> "Background"/Color
 * @see jclass.util.JCConverter#toColor
 */
public void setBackground(Color c) {
	super.setBackground(c);
}

/**
 * Sets the foreground color of the component.<p>
 * <strong>HTML param name/value:</strong> "Foreground"/Color
 * @see jclass.util.JCConverter#toColor
 */
public void setForeground(Color c) {
	super.setForeground(c);
}

/** Gets the shadow's thickness.<p>
 * <strong>HTML param name/value:</strong> "ShadowThickness"/int
 * @see #setShadowThickness
 */
public int getShadowThickness() { return getBorderThickness(); }

/** Sets the thickness of the rectangle drawn when the component has focus (default: 2).<p>
 * <strong>HTML param name/value:</strong> "HighlightThickness"/int
 * @see jclass.util.JCConverter#toInt
 */
public void setHighlightThickness(int v) {
	super.setHighlightThickness(v);
}

/**
 * Sets the color of the rectangle drawn when the component has focus
 * (default: black).<p>
 * <strong>HTML param name/value:</strong> "HighlightColor"/Color
 * @see jclass.util.JCConverter#toColor
 */
public void setHighlightColor(Color v) {
	super.setHighlightColor(v);
}

/**
 * Sets the component's margins (default: 0).<p>
 * <strong>HTML param name/value:</strong> "Insets"/Insets
 * @see jclass.util.JCConverter#toInsets
*/
public void setInsets(Insets insets) {
	super.setInsets(insets);
}

/** Sets the cursor in the parent frame. */
public void setCursor(int cursor) {
	BWTUtil.setCursor(this, cursor);
}


/**
 * Returns the preferred size.
 */
/* JDK102_START
public Dimension getPreferredSize() {
  return preferredSize();
}
 JDK102_END */

/**
 * Controls whether double-buffering is used when displaying
 * and updating the component (default: true).<p>
 * <strong>HTML param name/value:</strong> "DoubleBuffer"/boolean
 * @see jclass.util.JCConverter#toBoolean
 */
public void setDoubleBuffer(boolean v) { super.setDoubleBuffer(v); }

/** If btn1 is pressed and the component is traversable, requestFocus is called.
 * @see jclass.base.TransientComponent#isFocusTraversable
 */
public boolean mouseDown(Event ev, int x, int y) {
	mouse_down_event_timestamp = ev.when;
	if (ev.when - popdown_event_timestamp < 50
					&& JCEnvironment.getOS() != JCEnvironment.OS_UNIX)
		return true;

	if (BWTUtil.getMouseButton(ev) == 1 && isFocusTraversable())
		requestFocus();
	return false;
}

/**
 * Requests the input focus.
 * gotFocus() will be called if this method is successful.
 * @see java.awt.Component#gotFocus
 */
public void requestFocus() {
	super.requestFocus();
	if (isFocusTraversable())
		handleEvent(new Event(this, Event.GOT_FOCUS, null));
}

/** If the tab key is hit, keyboard focus is transferred to the next container.<p>
 * If SHIFT-tab is hit, focus is transferred to the previous container.<p>
 * If UP or LEFT arrow is hit, focus is transferred to the next traversable
 * component with the same parent.<p>
 * If DOWN or RIGHT arrow is hit, focus is transferred to the previous traversable
 * component with the same parent.<p>
 */
public boolean keyDown(Event ev, int key) {
    if (ev.key == Event.UP || ev.key == Event.LEFT)
		Focus.previousFocus(this);
	else if (ev.key == Event.DOWN || ev.key == Event.RIGHT)
		Focus.nextFocus(this);
/* JDK102_START
	else if (ev.key == BWTEnum.TAB) {
		if (ev.shiftDown())
			Focus.previousFocus(getParent(), this);
		else
			Focus.nextFocus(this);
	}
 JDK102_END */
	return false;
}

/**
 * JComponent in the Swing library overrides the setBounds method.
 * This caused repaint problems because BWT code calls the
 * reshape method instead of setBounds to stay compatible with JDK 102.
 * To keep our code looking clean, we created this method to call the
 * correct method.
 */
public static void setBounds(Component comp, int x, int y, int width,
						   int height) {
/* SWING_START */
	comp.setBounds(x, y, width, height);
/* SWING_END */
/* AWT_START 
    comp.reshape(x, y, width, height);
 AWT_END */

}

/**
 * In JDK 1.1, the reshape method for the Rectangle class was deprecated
 * and replaced with setBounds. To keep out code from being cluttered with
 * 110, 102 comments, this method will call the correct method.
 */
public static void setBounds(Rectangle rect, int x, int y, int width,
							int height) {

/* SWING_START */
	rect.setBounds(x, y, width, height);
/* SWING_END */
/* AWT_START 
    rect.reshape(x, y, width, height);
 AWT_END */
}


/**
 * Utility function to provide JDK neutral getMinimumSize()
functionality
 */
public static Dimension getMinimumSize(Component c) {
/* AWT_START 
    return c.minimumSize();
 AWT_END */
/* SWING_START */
    return c.getMinimumSize();
/* SWING_END */
}

/**
 * Utility function to provide JDK neutral getPreferredSize() functionality
 */
public static Dimension getPreferredSize(Component c) {
/* AWT_START 
    return c.preferredSize();
 AWT_END */
/* SWING_START */
    //System.out.println("getPreferredSize");
    //return new Dimension(100,100);
    //return c.getSize();
//	if (Static.Java8())
	  return c.getPreferredSize();
//	else
// 	  return new Dimension(50,30);
//    try
//    {
//    	return c.getPreferredSize();
//    }
//    catch(Exception e)
//    {
//    	System.out.println("getPreferredSize fix auf 50x30");
//    	return new Dimension(50,30);
//    }
/* SWING_END */
}

/**
 * Utility function to provide JDK neutral getInsets() functionality
 */
public static Insets getInsets(Container c) {
/* AWT_START 
    return c.insets();
 AWT_END */
/* SWING_START */
    return c.getInsets();
/* SWING_END */
}

/**
 * Utility function to provide JDK neutral getSize() functionality
 */
public static Dimension getSize(Container c) {
/* AWT_START 
	return c.size();
 AWT_END */
/* SWING_START */
    return c.getSize();
/* SWING_END */
}

}
