package All_Unlimited.Allgemein.Anzeige;

import All_Unlimited.Allgemein.Static;
/**
 * <p>Überschrift: </p>
 *
 * <p>Beschreibung: All Unlimited</p>
 *
 * <p>Copyright: Copyright (c) 31.05.2005</p>
 *
 * <p>Organisation: DVH</p>
 *
 * @author Roland Kremser
 * @version 4.5
 */



import java.util.Vector;

public class AddUp extends Object implements Comparable<AddUp>
{
  private long l=0;
  private Vector<Object> Vec=null;
  public boolean bEinheit=true;

  public AddUp(boolean rbEinheit)
  {
    bEinheit=rbEinheit;
  }

  public void add()
  {
    l++;
  }

  public long getValue()
  {
    //System.out.println("AddUp.getValue="+l);
    return Vec==null ? l:Vec.size();
  }
	
  @Override public int compareTo(AddUp mass)
	{
		return getValue()<mass.getValue() ? -1:getValue()>mass.getValue() ? +1:0;
	}
	
  public void add(AddUp obj)
  {
    l+=obj.getValue();
    //System.err.println("-> AddUp: Vec="+Vec+", l="+l+"->"+toString());
//    Vec=null;
  }

  public void add2(Object obj)
  {
    //System.err.println("AddUp.add2:"+Static.print(obj)+"/"+Vec);
    if (Vec==null)
    {
      Vec=new Vector<Object>();
      Vec.addElement(obj);
    }
    else if (!Vec.contains(obj))
      Vec.addElement(obj);
  }

  public String toString()
  {
    return (bEinheit ? Static.sCount+" ":"")+(Vec==null ? l:Vec.size());
  }

}
