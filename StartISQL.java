/**
 * <p>Überschrift: </p>
 *
 * <p>Beschreibung: </p>
 *
 * <p>Copyright: Copyright (c) 2004</p>
 *
 * <p>Organisation: DVH</p>
 *
 * @author Roland Kremser
 * @version 1.0
 */

import All_Unlimited.Allgemein.Global;
import All_Unlimited.Grunddefinition.ISQL;

public class StartISQL
{
  public static void main(String[] args)
  {
    String sConnect= args[0];
    Global g = new Global(sConnect,null);
    g.checkSpracheLand();
    ISQL.get(g,null,true);
  }
}
