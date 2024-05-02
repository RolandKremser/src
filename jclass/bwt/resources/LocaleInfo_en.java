package jclass.bwt.resources;

import java.util.ListResourceBundle;
import java.io.Serializable;

public class LocaleInfo_en extends ListResourceBundle
    implements Serializable {

public Object[][] getContents() {
	return contents;
}

static final Object[][] contents = {
	{ LocaleInfo.HOMEKEY,   new Integer(1)  }, // a
	{ LocaleInfo.ENDKEY,    new Integer(5)  }, // e
	{ LocaleInfo.OVRSTKKEY, new Integer(15) }, // o
	{ LocaleInfo.DELETEKEY, new Integer(4)  }, // d
	{ LocaleInfo.BSKEY,     new Integer(8)  }  // h
};

};
