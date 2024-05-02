package All_Unlimited.Allgemein;

//import java.awt.Checkbox;
import java.util.Vector;

import javax.swing.JCheckBox;


public class Favorit extends JCheckBox {
	
	  // Favoriten-Art
	  public static final int noFAV= 0;
	  public static final int FAV  = 1;
	  public static final int FAV_BG=2;
	  
	  private static Tabellenspeicher Tab=null;
	  private static Tabellenspeicher TabBG=null;
	  
	  private int iArt=0;
	  private int iStamm=0;
	  private static int iUser=0;
	  private Global g=null;
//	  private int iAbf=0;

	  public Favorit(Global rg,int riStamm)
	  {
		  super("");
		  g=rg;
		  iStamm=riStamm;
		  iArt=is(g,iStamm);
		  //getStyleClass().add("Favorit");
		  if (iArt>0)
			  setSelected(true);
//		  setOnAction((event)-> change());
	  }
	  
//	  private void change()
//	  {
//		  boolean b=isSelected();
//		  //g.fixtestError("Change "+iStamm+" auf "+b);
//		  iArt=b ? 1:0;
//		  if (b)
//		  {
//			  SQL Qry=new SQL(g);
//			  int iProt=g.Protokoll("Eingabe", iAbf);
//			  Qry.add("AIC_STAMM", iStamm);
//			  Qry.add("aic_benutzer", g.getBenutzer());
//			  Qry.add("AIC_PROTOKOLL", iProt);
//			  Qry.add("Status", 1);
//			  Qry.insert("FAVORIT", false);
//			  Qry.free();
//		  }
//		  else
//			  g.exec("delete from favorit where aic_stamm="+iStamm+" and aic_benutzer="+g.getBenutzer());
//		  clear();
//	  }
	  
//	  public void setAbf(int riAbf)
//	  {
//		  iAbf=riAbf;
//	  }
	  
	  public String toString()
	  {
		  return iArt==1 ? "x":iArt==2 ? "G":"";
	  }

	  public boolean is()
	  {
		return iArt>0;
	  }
	  
	    public static Tabellenspeicher getTab(Global g)
	    {
	    	CheckFav(g);
	    	return Tab;
	    }
	    
	    private static void CheckFav(Global g)
	    {
	    	if (Tab==null || iUser!=g.getBenutzer())
	    	{
	    		Tab=new Tabellenspeicher(g,"select aic_stammtyp,f.aic_stamm,bezeichnung from favorit f join stammview2 s on f.aic_stamm=s.aic_stamm and aic_rolle is null and aic_benutzer="+g.getBenutzer(),true);
	    		TabBG=new Tabellenspeicher(g,"select aic_stammtyp,f.aic_stamm,bezeichnung from favorit f join stammview2 s on f.aic_stamm=s.aic_stamm and aic_rolle is null and "+g.getAllBG(),true);
	    		iUser=g.getBenutzer();
	    		g.fixInfoT("CheckFav "+iUser+"->"+Tab.getVecSpalteI("aic_stamm"));
	    	}
	    }
	    
	    public static int is(Global g,int iStamm)
	    {
	    	CheckFav(g);
	    	if (Tab.getPos("aic_stamm", iStamm)>=0)
	    		return FAV;
	    	if (TabBG.getPos("aic_stamm", iStamm)>=0)
	    		return FAV_BG;
	    	return noFAV;
	    }
	    
	    public static Vector<Integer> get(Global g,int iStt,int riArt)
	    {
	    	CheckFav(g);
	    	Vector<Integer> Vec=new Vector<Integer>();
	    	Tabellenspeicher Tabx=riArt==FAV ? Tab:TabBG;
	    	for (int i=0;i<Tabx.size();i++)
	    		if (Tabx.getI(i,"aic_stammtyp")==iStt)
	    			Vec.addElement(Tabx.getI(i,"aic_stamm"));
	    	return Vec;
	    }
	    
	    public static void clear()
	    {
			Tab=null;
			TabBG=null;
	    }

}
