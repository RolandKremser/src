/*
    All_Unlimited-Allgemein-Sort.java

    NOTE: This file is a generated file.
          Do not modify it by hand!
*/

package All_Unlimited.Allgemein;

// add your custom import statements here
import java.util.*;

//import sun.awt.image.ToolkitImage;
import jclass.util.JCSortInterface;
import All_Unlimited.Allgemein.Anzeige.AddUp;
import All_Unlimited.Allgemein.Anzeige.BewEig;
import All_Unlimited.Allgemein.Anzeige.Combo;
import All_Unlimited.Allgemein.Anzeige.Farbe;
import All_Unlimited.Allgemein.Anzeige.HierarchieAnzeige;
import All_Unlimited.Allgemein.Anzeige.JaNein;
import All_Unlimited.Allgemein.Anzeige.Mass;
import All_Unlimited.Allgemein.Anzeige.VonBis;
import All_Unlimited.Allgemein.Anzeige.Waehrung;
import All_Unlimited.Allgemein.Anzeige.Zahl1;
import All_Unlimited.Allgemein.Anzeige.Zeit;

public class Sort extends java.lang.Object
{
    // add your data members here
	public static Object ObjOld=null;
	
	public static double getf(Object Obj)
	{
    // if (Obj != null)
    //       System.out.println("Sort.getf: Object "+Obj.getClass().getName()+" / "+Obj);
		try
		{
			double d= Obj == null || Obj.equals("") || Obj.equals("-") ? 0.0 : Obj instanceof Date ? new DateWOD((Date)Obj).getAbsSeconds()://((Date)Obj).getTime()/1000.0-lGMT:
				Obj instanceof VonBis ? ((VonBis)Obj).getSeconds():
				Obj instanceof Waehrung ? ((Waehrung)Obj).getValue():
				Obj instanceof Mass ? ((Mass)Obj).getValue():
				Obj instanceof Zahl1 ? ((Zahl1)Obj).getValue():
				Obj instanceof Zeit ? ((Zeit)Obj).getValue():
                                Obj instanceof AddUp ? ((AddUp)Obj).getValue():
				Obj instanceof DateWOD ? ((DateWOD)Obj).getAbsSeconds():
				Obj instanceof Double ? ((Double)Obj).doubleValue() :
				Obj instanceof Farbe ? ((Farbe)Obj).intValue() :
				Obj instanceof Integer ? ((Integer)Obj).doubleValue() :
					Obj instanceof Combo ? new Double(((Combo)Obj).getAic()).doubleValue() :
				Obj instanceof Long ? ((Long)Obj).doubleValue() :
                                Obj instanceof Boolean ? Boolean.TRUE.equals(Obj) ? 1:0 :
                                Obj instanceof JaNein ? ((JaNein)Obj).intValue():
                                Obj instanceof Favorit ? ((Favorit)Obj).is() ? 1:0:
				Obj instanceof String ? /*Static.Leer(Obj) ? 0:Obj.equals("x") ? 1:*/new Double(convertKomma((String)Obj)).doubleValue():dFehler(Obj);
                        // System.out.println("Sort.getf:"+Obj+(Obj==null ? "null":Obj.getClass().getName())+"="+d);
                        return d;
		}
		catch(java.lang.NumberFormatException e)
		{
				Static.printError("Sort.getf: <"+Obj+"> - "+e);
                                //if (g.Prog() || g.TestPC())
                                if (Transact.bTest)
                                  new Exception().printStackTrace();
                                //double d=-1;
                                //System.out.println("->"+d);
				return -1; // -1.0 ist manchmal +1
		}
	}

        public static DateWOD getDW(Object Obj)
        {
          return Obj == null ? null:Obj instanceof DateWOD ? (DateWOD)Obj:Obj instanceof Date ? new DateWOD((Date)Obj):new DateWOD((int)dFehler(Obj));
        }

        public static String convertKomma(String s)
        {
          //System.out.println("convertKomma1:"+s);
          if (s.equals(""))
            return "0";
          int iK=s.lastIndexOf(',');
          int iP=s.lastIndexOf('.');
          if (iK>iP)
          {
            if (s.indexOf('.')>-1)
              s=s.replace('.',' ');
            //System.out.println(iK+","+iP+":"+s);
            s=s.replace(',','.');
            //System.out.println(iK+","+iP+":"+s);
          }
          else if (iK>-1)
            s=s.replaceAll(",","");
          s=s.replaceAll(" ","");
          //System.out.println("convertKomma2:"+s);
          return s;
        }

        public static double getf2(Object Obj)
        {
          try
		{
			return Obj == null || Obj.equals("") ? 0.0 : Obj instanceof Date ? new DateWOD((Date)Obj).getAbsSeconds()://((Date)Obj).getTime()/1000.0-lGMT:
				Obj instanceof VonBis ? ((VonBis)Obj).getDauer():
				Obj instanceof Waehrung ? ((Waehrung)Obj).getAbsValue():
				Obj instanceof Mass ? ((Mass)Obj).getAbsValue():
				Obj instanceof Zahl1 ? ((Zahl1)Obj).getValue():
                                Obj instanceof AddUp ? ((AddUp)Obj).getValue():
				Obj instanceof Zeit ? ((Zeit)Obj).getValue():
				Obj instanceof DateWOD ? ((DateWOD)Obj).getAbsSeconds():
				Obj instanceof Double ? ((Double)Obj).doubleValue() :
				Obj instanceof Integer ? ((Integer)Obj).doubleValue() :
				Obj instanceof Long ? ((Long)Obj).doubleValue() :
				Obj instanceof String ? new Double(convertKomma((String)Obj)).doubleValue():dFehler(Obj);
		}
		catch(java.lang.NumberFormatException e)
		{
				return -1; // -1.0 ist manchmal +1
		}
        }

	private static double dFehler(Object Obj)
	{
		Static.printError("Sort.dFehler: Object "+Obj.getClass().getName()+" / "+Obj+" wird nicht unterstützt!");
                //if (Transact.bTest)
                  new Exception().printStackTrace();
		return 0.0;
	}

        public static Integer getInt(Object Obj)
        {
          return Obj == null ? null:Obj instanceof Integer ? (Integer)Obj : Obj instanceof Long ? new Integer(((Long)Obj).intValue()) :
                    Obj instanceof Double ? new Integer(((Double)Obj).intValue()):Obj instanceof String ? new Integer((String)Obj):null;
        }

        public static Integer getInt(Object Vec,int iPos)
        {
            if (Vec==null || !(Vec instanceof Vector) || iPos>=((Vector)Vec).size())
              return Static.Int0;
            return getInt(((Vector)Vec).elementAt(iPos));
        }

        public static Vector<Integer> getIntVec(Object Vec)
        {
          Vector<Integer> VecN=new Vector<Integer>();
          int iAnz=((Vector)Vec).size();
          for (int i=0;i<iAnz;i++)
          {
            Integer Int=getInt(((Vector)Vec).elementAt(i));
            if (!VecN.contains(Int))
              VecN.addElement(Int);
          }
          return VecN;
        }

        public static int geti(Object Vec,int iPos)
        {
          if (Vec==null || !(Vec instanceof Vector) || iPos>=((Vector)Vec).size())
            return 0;
          return geti2(((Vector)Vec).elementAt(iPos));
        }

        public static String gets(Object Vec,int iPos)
        {
          if (Vec==null || ((Vector)Vec).size()<=iPos)
            return "";
          return gets(((Vector)Vec).elementAt(iPos));
        }

        public static void add(Vector<Integer> Vec,int iPos)
        {
          Vec.setElementAt(geti(Vec, iPos)+1,iPos);
        }
        public static int geti(Object Obj)
        {
          return Obj == null || Obj instanceof Vector || Obj.equals("") ? 0 : Obj instanceof Integer ? ((Integer)Obj).intValue() :
                    Obj instanceof Boolean ? Obj==Boolean.TRUE ? 1:0:Obj instanceof BewEig ? ((BewEig)Obj).Eig():Obj instanceof Farbe ? ((Farbe)Obj).intValue() :
                                Obj instanceof Long ? ((Long)Obj).intValue() : Obj instanceof Combo ? ((Combo)Obj).getAic() :Obj instanceof HierarchieAnzeige ? ((HierarchieAnzeige)Obj).getValueStamm():
                                Obj instanceof Double ? ((Double)Obj).intValue():new Integer(Obj instanceof String ? (String)Obj:Obj.toString()).intValue();

        }

        public static int geti2(Object Obj)
        {
          try
          {
            return geti(Obj);
          }
          catch(Exception e)
          {
            Transact.fixInfoS("geti2 von "+Obj+" nicht möglich");
            if (Static.bInfoExcept && !Static.Gleich(Obj, ObjOld))
            {
            	ObjOld=Obj;
            	Transact.printStackTraceS(new Exception());
            }
            return -1;
          }
        }

        public static String print(Object Obj)
        {
          if (Obj instanceof Vector)
          {
            int iAnz=((Vector)Obj).size();
            String s="<";
            for (int i=0;i<iAnz;i++)
              s+=(i>0?";":"")+print(((Vector)Obj).elementAt(i));
            s+=">";
            return s;
          }
          else
            return Static.print(Obj);
        }

	public static String gets(Object Obj)
	{
		return Obj==null || Obj instanceof java.awt.Image ? "" : Obj instanceof String ? (String)Obj: Obj instanceof Vector && ((Vector)Obj).size()>0 ? gets(((Vector)Obj).elementAt(0)):Obj.toString();
	}

        public static String gets2(Object Obj,char cTrenn)
        {
          return Obj==null ? "" :Obj instanceof Vector ? VecToString((Vector)Obj,cTrenn):gets(Obj);
        }

        public static Vector getVec(Object Obj)
        {
          if (Obj instanceof Vector)
            return (Vector)Obj;
          else
          {
            Vector<Object> Vec=new Vector<Object>();
            Vec.addElement(Obj);
            return Vec;
          }
        }

        private static String VecToString(Vector Vec,char cTrenn)
        {
          if (Vec!=null && Vec.size()>0)
          {
            String s=gets2(Vec.elementAt(0),cTrenn);
            for (int i=1;i<Vec.size();i++)
              s+=cTrenn+gets2(Vec.elementAt(i),cTrenn);
            return s;
          }
          else
           return "";
        }

	public static JCSortInterface sortMethod = new JCSortInterface()	// für JCOutliner
		{
			public boolean compare(Object object1,Object object2)
			{
				boolean bGreater=false;
                                /*if(object1==null || object2==null)
                                   bGreater = object2==null;
                                else*/ if(object1.getClass()!=object2.getClass())
                                   bGreater = 0<=Static.className(object1).compareTo(Static.className(object2));
                                else if(object1 instanceof Integer)
                                        bGreater = ((Integer)object1).intValue() >= ((Integer)object2).intValue();
                                else if(object1 instanceof AddUp)
                                        bGreater = ((AddUp)object1).getValue() >= ((AddUp)object2).getValue();
                                else if(object1 instanceof Long)
					bGreater = ((Long)object1).longValue() >= ((Long)object2).longValue();
				else if(object1 instanceof Float)
					bGreater = ((Float)object1).floatValue() >= ((Float)object2).floatValue();
				else if(object1 instanceof Double)
					bGreater = ((Double)object1).doubleValue() >= ((Double)object2).doubleValue();
				else if(object1 instanceof Date)
					bGreater = ((Date)object1).getTime() >= ((Date)object2).getTime();
				else if(object1 instanceof DateWOD)
					bGreater = ((DateWOD)object1).getAbsSeconds() >= ((DateWOD)object2).getAbsSeconds();
				else if(object1 instanceof Waehrung)
					bGreater =  ((Waehrung)object1).getValue() >= ((Waehrung)object2).getValue();
				else if(object1 instanceof Mass)
					bGreater =  ((Mass)object1).getValue() >= ((Mass)object2).getValue();
				else if(object1 instanceof Zeit)
					bGreater =  ((Zeit)object1).getValue() >= ((Zeit)object2).getValue();
				else if(object1 instanceof Zahl1)
					bGreater =  ((Zahl1)object1).getValue() >= ((Zahl1)object2).getValue();
				else if(object1 instanceof VonBis)
					bGreater =  ((VonBis)object1).getDauer() >= ((VonBis)object2).getDauer();
				else
					bGreater = 0<=object1.toString().toUpperCase().replace('Ä','A').replace('Ö','O').replace('Ü','U').compareTo(object2.toString().toUpperCase().replace('Ä','A').replace('Ö','O').replace('Ü','U'));

				return(bGreater);
			}
		};


/*
	public static JCSortable sortVecMethod = new JCSortable()	// für Tabellenspeicher und ComboSort
		{
			public long compare(Object object1,Object object2)
			{
				long comparing=0;

				if(object1==null || object2==null)
					comparing = object1==null?JCSortable.LESS_THAN:JCSortable.GREATER_THAN;
				else if(object1 instanceof Integer)
				{
					if(((Integer)object1).intValue() > ((Integer)object2).intValue())
						comparing = JCSortable.GREATER_THAN;
					else if(((Integer)object1).intValue() == ((Integer)object2).intValue())
						comparing = JCSortable.EQUAL;
					else
						comparing = JCSortable.LESS_THAN;

				}
				else if(object1 instanceof Float)
				{
					if(((Float)object1).floatValue() > ((Float)object2).floatValue())
						comparing = JCSortable.GREATER_THAN;
					else if(((Float)object1).floatValue() == ((Float)object2).floatValue())
						comparing = JCSortable.EQUAL;
					else
						comparing = JCSortable.LESS_THAN;
				}
				else if(object1 instanceof Double || object1 instanceof Date || object1 instanceof DateWOD)
				{
					double d1=getf(object1);
					double d2=getf(object2);
					if(d1 > d2)
						comparing = JCSortable.GREATER_THAN;
					else if(d1 == d2)
						comparing = JCSortable.EQUAL;
					else
						comparing = JCSortable.LESS_THAN;
				}
				else if(object1 instanceof Zeit)
				{
					if(((Zeit)object1).isNull())
						comparing = JCSortable.GREATER_THAN;
					else if(((Zeit)object2).isNull())
						comparing = JCSortable.LESS_THAN;
					else if(((Zeit)object1).getValue()>((Zeit)object2).getValue())
						comparing = JCSortable.GREATER_THAN;
					else if(((Zeit)object1).getValue()==((Zeit)object2).getValue())
						comparing = JCSortable.EQUAL;
					else
						comparing = JCSortable.LESS_THAN;
				}
				else
				{
					int i=object1.toString().toUpperCase().replace('Ä','A').replace('Ö','O').replace('Ü','U').compareTo(object2.toString().toUpperCase().replace('Ä','A').replace('Ö','O').replace('Ü','U'));
					//System.out.println(object1+":"+object2+"="+i);
					comparing = i>0?JCSortable.GREATER_THAN:i==0?JCSortable.EQUAL:JCSortable.LESS_THAN;
				}

				return(comparing);
			}
		};
*/

  public static int[] sort(Vector Vec,boolean bASC)
  {
    List<Vergleich> list=new ArrayList<Vergleich>();
    for (int i=0;i<Vec.size();i++)
      Collections.addAll(list,new Vergleich(Vec.elementAt(i),i));
    if (bASC)
    {
      Collections.sort(list);
    }
    else
    {
      Comparator<Vergleich> comparator=Collections.<Vergleich>reverseOrder();
      Collections.sort(list,comparator);
    }
    int[] iRet=new int[Vec.size()];
    for (int i=0;i<Vec.size();i++)
      iRet[i]=list.get(i).iNr;
    return iRet;
  }
}

class Vergleich implements Comparable<Vergleich>
{
  public int iNr;
  private Object Obj;

  public Vergleich(Object Obj,int iNr)
  {
    this.Obj=Obj;
    this.iNr=iNr;
  }

  public int compareTo( Vergleich V)
  {
    if (Obj == null || V.Obj == null)
    {
      if (Obj == null && V.Obj != null)
        return -1;
      else if (V.Obj == null && Obj != null)
        return 1;
      else
        return 0;
    }
    else if (Obj instanceof String)
      return Obj.toString().toUpperCase().replace('Ä','A').replace('Ö','O').replace('Ü','U').compareTo(V.Obj.toString().toUpperCase().replace('Ä','A').replace('Ö','O').replace('Ü','U'));
    else
    {
      double d1=Sort.getf(Obj);
      double d2=Sort.getf(V.Obj);
      if( d1 < d2 )
        return -1;
      if( d1 > d2 )
        return 1;
      return 0;
    }
  }

  @Override
  public String toString()
  {
      return Obj.toString()+"("+iNr+")";
  }

}

