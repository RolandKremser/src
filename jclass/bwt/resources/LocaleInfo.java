package jclass.bwt.resources;

import java.util.ListResourceBundle;
import java.io.Serializable;

public class LocaleInfo extends ListResourceBundle implements Serializable {

public static final String CUT       = "Cut";
public static final String COPY      = "Copy";
public static final String PASTE     = "Paste";
public static final String DELETE    = "Delete";
public static final String SELECTALL = "SelectAll";
public static final String CUTKEY    = "CutKey";
public static final String COPYKEY   = "CopyKey";
public static final String PASTEKEY  = "PasteKey";
public static final String HOMEKEY   = "HomeKey";
public static final String ENDKEY    = "EndKey";
public static final String OVRSTKKEY = "OverStrikeKey";
public static final String DELETEKEY = "DeleteKey";
public static final String BSKEY     = "BackSpaceKey";

public Object[][] getContents() {
	return contents;
}

static final Object[][] contents = {
	{ CUT,       "Cut" },
	{ COPY,      "Copy" },
	{ PASTE,     "Paste" },
	{ DELETE,    "Delete" },
	{ SELECTALL, "SelectAll" },
	{ CUTKEY,    new Integer(24) }, // <ctrl>-x
	{ COPYKEY,   new Integer(3)  }, // <ctrl>-c
	{ PASTEKEY,  new Integer(22) }, // <ctrl>-v
	{ HOMEKEY,   new Integer(-1) }, // unspecified
	{ ENDKEY,    new Integer(-1) }, // unspecified
	{ OVRSTKKEY, new Integer(-1) }, // unspecified
	{ DELETEKEY, new Integer(-1) }, // unspecified
	{ BSKEY,     new Integer(-1) }  // unspecified
};

};
