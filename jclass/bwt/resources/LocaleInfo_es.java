package jclass.bwt.resources;

import java.util.ListResourceBundle;
import java.io.Serializable;

public class LocaleInfo_es extends ListResourceBundle
    implements Serializable {

public Object[][] getContents() {
	return contents;
}

static final Object[][] contents = {
	{ LocaleInfo.CUT,       "Cortar" },
	{ LocaleInfo.COPY,      "Copiar" },
	{ LocaleInfo.PASTE,     "Pegar"  },
	{ LocaleInfo.DELETE,    "Eliminar" },
	{ LocaleInfo.SELECTALL, "Seleccionar Todo" },
	{ LocaleInfo.HOMEKEY,   new Integer(1)  }, // <ctrl>-a
	{ LocaleInfo.ENDKEY,    new Integer(5)  }, // <ctrl>-e
	{ LocaleInfo.OVRSTKKEY, new Integer(15) }, // <ctrl>-o
	{ LocaleInfo.DELETEKEY, new Integer(4)  }, // <ctrl>-d
	{ LocaleInfo.BSKEY,     new Integer(8)  }  // <ctrl>-h
};

};