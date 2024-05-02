package All_Unlimited.Allgemein;

import java.util.HashSet;

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
@SuppressWarnings("rawtypes")
public class AUVector extends HashSet
{
  /**
	 *
	 */
	private static final long serialVersionUID = 6337415586759159434L;

@SuppressWarnings("unchecked")
public AUVector(String[] s)
  {
    super();
    for(int i=0;i<s.length;i++)
      add(s[i]);
  }
}
