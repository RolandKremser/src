/*
    All_Unlimited-Allgemein-Eingabe-ComboSort.java

    NOTE: This file is a generated file.
          Do not modify it by hand!
*/

package All_Unlimited.Allgemein.Eingabe;

// add your custom import statements here
//import java.util.*;
//import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Vector;
import java.awt.*;
import javax.swing.*;

import jclass.util.JCSortable;
import All_Unlimited.Allgemein.Count;
import All_Unlimited.Allgemein.Global;
import All_Unlimited.Allgemein.SQL;
import All_Unlimited.Allgemein.Static;
import All_Unlimited.Allgemein.Tabellenspeicher;
import All_Unlimited.Allgemein.Transact;
import All_Unlimited.Allgemein.Anzeige.Combo;

import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class ComboSort extends javax.swing.JComboBox
{
  /**
	 *
	 */
	private static final long serialVersionUID = -5973629625820489L;

private javax.swing.plaf.ComboBoxUI CboDefault=null;//Static.bDefaultLook && Static.Java8() ? new com.sun.java.swing.plaf.windows.WindowsComboBoxUI():null;

  public void fillDefTable(String sTabname, boolean rbKein)
    {
      long lClock = Static.get_ms();
		if (sTabname.equalsIgnoreCase("Stammtyp"))
			fillCbo(g.TabStammtypen,rbKein);
		else if (sTabname.equalsIgnoreCase("Erfassungstyp") || sTabname.equalsIgnoreCase("BEWEGUNGSTYP"))
			fillCbo(g.TabErfassungstypen,rbKein);
		//else if (sTabname.equalsIgnoreCase("Eigenschaft"))
		//	fillCbo(g.TabEigenschaften,rbKein);
		else if (sTabname.equalsIgnoreCase("Begriffgruppe"))
			fillCbo(g.TabBegriffgruppen,rbKein);
		else if (sTabname.equalsIgnoreCase("Abfrage"))
			fillCbo("select aic_abfrage,kennung,defBezeichnung Bezeichnung from abfrage join begriff on abfrage.aic_begriff=begriff.aic_begriff where "+g.bit("bits",0x10000000/* cstFilter */),sTabname,rbKein,false);
		//else if (sTabname.equalsIgnoreCase("Modell"))
		//	fillCbo("select aic_Modell,kennung,defBezeichnung Bezeichnung from Modell join begriff on Modell.aic_begriff=begriff.aic_begriff and tod is null",sTabname,rbKein,false);
		else if (sTabname.equalsIgnoreCase("Protokoll"))
			fillCbo("select aic_protokoll,timestamp bezeichnung,kennung='' from protokoll",sTabname,rbKein,false);
		else if (sTabname.equalsIgnoreCase("Mandant"))
			fillCboMandant(rbKein,sTabname.equals("MANDANT"));
        else if (sTabname.equalsIgnoreCase("Farbe"))
			fillCbo("select aic_farbe,kennung"+g.AU_Bezeichnung(sTabname)+" from Farbe where kennung not like '.%'",sTabname,rbKein,false);
        else if (sTabname.equalsIgnoreCase("BENUTZERGRUPPE"))
          fillCbo("SELECT aic_BENUTZERGRUPPE,kennung," + g.AU_BezeichnungF("BENUTZERGRUPPE") + " Bezeichnung FROM BENUTZERGRUPPE"+
                  (g.getMandant()==1 ? "":g.getReadMandanten(true,"BENUTZERGRUPPE")+" or "+g.bit("bits",Global.cstJeder)), sTabname, rbKein, false);
        else if (sTabname.equalsIgnoreCase("BENUTZER"))
        	fillCbo("select aic_benutzer,kennung," + g.AU_BezeichnungF("BENUTZER") + " Bezeichnung FROM benutzer"+
        			(g.getReadMandanten(true,"BENUTZER"))+" and geloescht is null and"+g.bitis("bits", Global.cstTimerBenutzer+Global.cstAServerUser+Global.cstTerminalUser+Global.cstImport, 0)
        			+(g.Prog()?"":" AND"+g.bits("Bits",3)+"<>1 ")+(g.Def()?"":"AND"+g.bits("Bits",3)+"<>2 ")+(g.SuperUser()?"":"AND"+g.bits("Bits",3)+"<>3 "), sTabname, rbKein, false);
        else if (sTabname.equalsIgnoreCase("ZUORDNUNG"))
          fillCbo("SELECT aic_ZUORDNUNG,kennung" + g.AU_Bezeichnung(sTabname) + " FROM " + sTabname+(rbKein?"":" where kennung<>'Druck'"), sTabname, rbKein, false);
        else
		/*else if (g.TabTabellenname.posInhalt("Kennung",sTabname))
		{
			//boolean bVirtuell=((Boolean)g.TabTabellenname.getInhalt("Virtuell")).booleanValue();
			//if(bVirtuell)
			//	fillCbo("SELECT AIC_Fremd AS Kennung,Bezeichnung FROM Bezeichnung JOIN Tabellenname WHERE Tabellenname.Kennung='"+sTabname+"'",true,sTabname,rbKein,false);
			//else
				fillCbo("SELECT *"+g.AU_Bezeichnung(sTabname,"")+" FROM "+sTabname,false,sTabname,rbKein,false);
		}
		else
		{*/
                {
                  fillCbo("SELECT aic_" + sTabname + ",kennung" + g.AU_BezeichnungFo(sTabname) + "Bezeichnung FROM " + sTabname, sTabname, rbKein, false);
                }
		//}
                if (g.TestPC())
                    g.clockInfo(" ------------------------- fillDefTable "+sTabname,lClock);
    }
  
  	public void fillStammTable(int iStt,boolean rbKein)
  	{
  		fillStammTable(iStt,null,rbKein);
  	}
    public void fillStammTable(int iStt, Vector<Integer> Vec,boolean rbKein)
    {
		//java.util.Vector Vec=g.SQL_CboStt(iStt);
		fillCbo("sElect aic_stamm,bezeichnung kennung"+g.AU_Bezeichnung2("Stamm","p2")+
				" from stammview p2 where aic_rolle is null and aic_stammtyp="+iStt+(Vec==null || Vec.size()==0 ? "":" and aic_stamm not"+Static.SQL_in(Vec))+g.getMandanten(iStt),"Stamm",rbKein,false);
		//fillCbo("Select aic_stamm,bezeichnung as kennung"+g.AU_Bezeichnung("Stamm","stammview")+
		//		" from stammview where aic_stammtyp ="+iStt+g.getReadMandanten(false),"Stamm",rbKein,false);

		setEditable(50);
		bString=false;
    }

    public void fillStammRolle(int iRolle,int iFirma, boolean rbKein)
    {
		//java.util.Vector Vec=g.SQL_CboStt(iStt);
		fillCbo("sElect aic_stamm,bezeichnung kennung"+g.AU_Bezeichnung2("Stamm","p2")+
				" from stammview p2 where aic_rolle="+iRolle+(iFirma>0 ? " and firma="+iFirma:"")+g.getReadMandanten(),"Stamm",rbKein,false);
		//fillCbo("Select aic_stamm,bezeichnung as kennung"+g.AU_Bezeichnung("Stamm","stammview")+
		//		" from stammview where aic_stammtyp ="+iStt+g.getReadMandanten(false),"Stamm",rbKein,false);

		setEditable(50);
		bString=false;
    }

    public void fillStammTable(int iBegriff,int iStt, boolean rbKein)
    {
      fillStammTable(iBegriff,iStt,0,rbKein);
    }

    public void fillStammTable(int iBegriff,int iStt,int iFirma, boolean rbKein)
    {
      Vector<Integer> VecStamm=null;
      if (iBegriff !=0)
          VecStamm=g.getVecStamm(iBegriff,iStt);
      //g.testInfo("fillStammTable:"+iBegriff+":"+VecStamm);
      Tabellenspeicher Tab= new Tabellenspeicher(g,"sElect aic_stamm aic,bezeichnung kennung"+g.AU_Bezeichnung2("Stamm","p2")+
				" from stammview p2 where aic_rolle is null and aic_stammtyp="+iStt+(iFirma>0 ? " and firma="+iFirma:"")+g.getMandanten(iStt),true);
      Tab.clearWithVec("aic", VecStamm);
      //Tab.showGrid("fillStammTable"+iStt);
      fillCbo(Tab,rbKein);
      setEditable(50);
      bString=false;
    }

    public void fillStammBitTable(int iBit, boolean rbKein)	// für Benutzer und Einheit
    {
		fillCbo("Select aic_stamm,"+g.concat(g.ifnull("austritt","''","'-'"),"bezeichnung")+" kennung"+g.AU_Bezeichnung2("Stamm","stammview2")+
				" from Stammtyp join Stammview2 on stammtyp.aic_Stammtyp=Stammview2.aic_Stammtyp where aic_rolle is null and "+g.bit("SttBits",iBit),"Stamm",rbKein,false);
    }

    /*public void fillStammTable(String sStt,String sEigenschaft, boolean rbKein) // nur für BDE von SoftTerm
    {
		int iStt=SQL.getInteger(g,"select aic_stammtyp from stammtyp where kennung='"+sStt+"'");
		int iEig=SQL.getInteger(g,"select aic_eigenschaft from eigenschaft where kennung='"+sEigenschaft+"'");
		fillCbo("Select aic_stamm,bezeichnung,(select spalte_stringx from daten_stringx join poolview on daten_stringx.aic_daten=poolview.aic_daten where aic_eigenschaft="+
			iEig+" and aic_stamm=p2.aic_stamm) Kennung from stammview p2 where aic_stammtyp="+iStt,"Stamm",rbKein,false);
		setEditable(50);
		bString=false;
    }*/

    private void fillCboMandant(boolean rbKein,boolean bAlle)
    {
      //fillCbo("select aic_mandant,kennung"+g.AU_Bezeichnung(sTabname)+" from Mandant"+g.getWriteMandanten(true),sTabname,rbKein,false);
      //g.fixtestInfo("fillCboMandant "+bAlle);
      Clear();
      for(int iPos=0;iPos<g.TabMandanten.size();iPos++)
        if (bAlle || g.isWriteMandant(g.TabMandanten.getI(iPos,"aic_mandant")))
          VecItem.addElement(new Combo(g.TabMandanten.getS(iPos,"Bezeichnung"),g.TabMandanten.getI(iPos,"aic_mandant"), g.TabMandanten.getS(iPos,"Kennung"),0,bAlle && g.TabMandanten.getB(iPos,"Tod") ? Color.RED:null));
      setKein(rbKein);
      Sort();
    }

	public void fillCbo(String sQry,String sTabname,boolean rbKein,boolean bFaktor)
	{
          long lClock = Static.get_ms();
		Clear();
		SQL Qry = new SQL(g);
		Qry.open(sQry);
                boolean bSprache=sTabname.equalsIgnoreCase("Sprache");
		while(!Qry.eof())
		{
			//String sKennung = Static.beiLeer(Qry.getS("Kennung"),Qry.getS("aic_"+sTabname)); 1.4.2004 ausgeklammert
                        String sKennung = Qry.getS("Kennung");
                        if (!bSprache || g.existsSprache(Qry.getI("aic_"+sTabname)))
                          VecItem.addElement(new Combo(Qry.getS("Bezeichnung"),Qry.getI("aic_"+sTabname), sKennung.trim(),bFaktor ? Qry.getF("Faktor") : 0 ));
			Qry.moveNext();
		}
		Qry.free();
		//g.addTemp(".fillCbo "+sTabname);
		setKein(rbKein);
		Sort();
          lClockFill+=Static.get_ms()-lClock;
          lClockAnz++;
	}

        public void fillCbo(Tabellenspeicher Tab,boolean rbKein)
        {
          fillCbo(Tab,rbKein,"Aic","Bezeichnung","Kennung");
        }
        
        public void fillCbo(Tabellenspeicher Tab,boolean rbKein,String sAic)
        {
          fillCbo(Tab,rbKein,sAic,"Bezeichnung","Kennung");
        }

	public void fillCbo(Tabellenspeicher Tab,boolean rbKein,String sAic,String sB,String sKen)
	{
        Clear();		//neu
		int iPos=0;
		while(iPos<Tab.size())//!Tab.eof())
		{
            String sBez=Tab.exists("DefBez") ? g.Def() ? Static.beiLeer(Tab.getS(iPos,"DefBez"),Tab.getS(iPos,sB)):Static.beiLeer(Tab.getS(iPos,sB),Tab.getS(iPos,"DefBez")):Tab.getS(iPos,sB);
			if (Tab != g.TabStammtypen || g.existsSttS(iPos))
				addItem2(sBez,Tab.getI(iPos,sAic),sKen==null ? Static.sLeer:Tab.getS(iPos,sKen),0,/*iSortiertNach==Farbe*/Tab.exists("Tod") && Tab.getB(iPos,"Tod") ? Global.ColTod:null);
			//if (/*iSortiertNach==Farbe)// &&*/ Tab.exists("Tod"))
			//	g.fixtestInfo("Farbe bei "+sBez+":"+(Tab.getB(iPos,"Tod") ? g.ColTod:null)+"/"+iSortiertNach);
			iPos++;
		}
		setKein(rbKein);//neu
		Sort();
	}
	
        public void fillStt(java.util.Vector Vec,int riStt)
        {
          setKein(false);
          Clear();
          bSorted=false;
          for(int i=0;i<Vec.size();i++)
          {
            int iStt=((Integer)Vec.elementAt(i)).intValue();
            if (iStt>0)
            {
              int iPos=g.TabStammtypen.getPos("Aic",iStt);
              if (iPos>=0)
                VecItem.addElement(new Combo(g.TabStammtypen.getS(iPos,"Bezeichnung"), g.TabStammtypen.getI(iPos,"Aic"),g.TabStammtypen.getS(iPos,"Kennung"),0));
            }
            else
            {
              int iPos=g.TabErfassungstypen.getPos("Aic",-iStt);
              if (iPos>=0)
                VecItem.addElement(new Combo("*"+g.TabErfassungstypen.getS(iPos,"Bezeichnung"), -g.TabErfassungstypen.getI(iPos,"Aic"),g.TabErfassungstypen.getS(iPos,"Kennung"),0));

            }
          }
          Sort();
          setSelectedAIC(riStt);
        }

        public static boolean einStt(Global g,int iVon,int iBis)
        {
          int iPos=g.TabStammtypen.getPos("Aic",new Integer(iVon));
          return iPos>=0 && g.TabStammtypen.getI(iPos,"Darunter")==iBis;
        }

	public void fillHierachieBis(int iVon,int iBis,boolean rbKein)
        {
		Clear();
		while(iVon != 0 && iBis != iVon)
		{
			int iPos=g.TabStammtypen.getPos("Aic",new Integer(iVon));
			iVon = g.TabStammtypen.getI(iPos,"Darunter");
			if (((g.TabStammtypen.getI(iPos,"bits")&Global.cstEnde)==0 || iBis==0) && g.existsSttS(iPos))
				VecItem.addElement(new Combo(g.TabStammtypen.getS(iPos,"Bezeichnung"), g.TabStammtypen.getI(iPos,"Aic"),g.TabStammtypen.getS(iPos,"Kennung"),0));
		}
		//g.addTemp("H");
		setKein(rbKein);
		Sort();
        }
        public void fillRolle(int iStt,boolean rbKein)
        {
          Clear();
          //for(g.TabRollen.moveFirst();!g.TabRollen.eof();g.TabRollen.moveNext())
          for(int iPos=0;iPos<g.TabRollen.size();iPos++)
            if (iStt==-1 || g.TabRollen.getI(iPos,"Stt")==iStt)
              VecItem.addElement(new Combo(g.TabRollen.getS(iPos,"Bezeichnung"), g.TabRollen.getI(iPos,"Aic"),g.TabRollen.getS(iPos,"Kennung"),0));
          //g.addTemp("H");
          g.progInfo("fillRolle "+iStt+" -> "+VecItem.size()+" Stk");
          setKein(rbKein);
          Sort();
        }
	public void fillMass(int iStamm,boolean rbKein)
	{
		fillCbo("select stammview.aic_stamm,p2.spalte_double faktor,bezeichnung kennung"+g.AU_Bezeichnung2("Stamm","stammview")+" from poolview p1,poolview p2 join stammview on stammview.aic_stamm=p2.aic_stamm where p1.aic_stamm="+iStamm+" and p2.aic_eigenschaft="+g.iEigFaktor+
 				" and p2.sta_aic_stamm=p1.sta_aic_stamm","Stamm",rbKein,true);
	}
	public void fillBegriffTable(String sGruppe,boolean rbKein)
	{
          if (sGruppe.equals("Modell"))
            fillCbo("select begriff.aic_begriff,kennung"+g.AU_Begriff()+" Bezeichnung from begriff join modell on Modell.aic_begriff=begriff.aic_begriff where "+g.bit("bits",Global.cstEF),"Begriff",rbKein,false);
          else
          {
            int iPos=g.TabBegriffgruppen.getPos("Kennung", sGruppe);
            if (iPos>=0)
              fillBegriffTable(g.TabBegriffgruppen.getI(iPos,"AIC"), rbKein, true,false);
          }
	}

	public void fillBegriffTable(String[] sGruppe,boolean rbKein)
	{
          int iPos=g.TabBegriffgruppen.getPos("Kennung", sGruppe[0]);
          if (iPos>=0)
            fillBegriffTable(g.TabBegriffgruppen.getI(iPos,"AIC"),rbKein,true,false);
          for(int i=1;i<sGruppe.length;i++)
          {
            iPos=g.TabBegriffgruppen.getPos("Kennung",sGruppe[i]);
            if (iPos>=0)
              fillBegriffTable(g.TabBegriffgruppen.getI(iPos,"AIC"), rbKein, false,false);
          }
	}

  public void setFilter(Vector<Integer> Vec)
  {
    if (VecItemCopy==null)
      VecItemCopy=new Vector<Combo>(VecItem);
    for (int i=0;i<VecItem.size();)
      if (!Vec.contains(All_Unlimited.Allgemein.Sort.geti(VecItem,i)))
        VecItem.remove(i);
      else
        i++;
    Sort();
  }
	
	public void fillBegriffTable(int iGruppe,boolean rbKein,boolean bClear)
	{
		fillBegriffTable(iGruppe,rbKein,bClear,false);
	}

	public void fillBegriffTable(int iGruppe,boolean rbKein,boolean bClear,boolean bDefBez)
	{
		if(bClear)
                  Clear();
                int iPosBG=g.TabBegriffgruppen.getPos("AIC",iGruppe);
		Tabellenspeicher Tab = g.TabBegriffgruppen.getB(iPosBG,"Code") ? g.TabCodes:g.TabBegriffe;
		//Tab.posInhalt("Gruppe",iGruppe);
                int iPos=Tab.getPos("Gruppe",iGruppe);
		while (iPos>=0 && iPos<Tab.size() && Tab.getI(iPos,"Gruppe")==iGruppe)
		{
			VecItem.addElement(new Combo(Tab.getS(iPos,bDefBez ? "DefBezeichnung":"Bezeichnung"),Tab.getI(iPos,"Aic"),Tab.getS(iPos,"Kennung"),0));
			Tab.moveNext();
                        iPos++;
		}
		//g.addTemp("B");
		setKein(rbKein);
		Sort();
	}

        public void fillANR(int iBew)
        {
          Clear();
          //g.TabErfassungstypen.push();
          //g.TabEigenschaften.push();
          //g.TabStammtypen.push();
          int iPosB=g.TabErfassungstypen.getPos("aic",iBew);
          Vector<Integer> Vec=new Vector<Integer>();
          int iPosE=g.TabErfassungstypen.getI(iPosB,"Eig1")>0 ? g.TabEigenschaften.getPos("aic",g.TabErfassungstypen.getI(iPosB,"Eig1")):-1;
          if (iPosE>=0)
          {
            Vec.addElement(g.TabEigenschaften.getInt(iPosE,"aic_stammtyp"));
            for (int i=2;i<10;i++)
            {
              iPosE=g.TabErfassungstypen.getI(iPosB, "Eig" + i) > 0 ? g.TabEigenschaften.getPos("aic", g.TabErfassungstypen.getI(iPosB, "Eig" + i)):-1;
              if (iPosE>=0 && !Vec.contains(g.TabEigenschaften.getInt(iPosE,"aic_stammtyp")))
                Vec.addElement(g.TabEigenschaften.getInt(iPosE,"aic_stammtyp"));
            }
          }
          for (int i=0;i<Vec.size();i++)
          {
            int iPos=g.TabStammtypen.getPos("Aic", Vec.elementAt(i));
            if (iPos>=0)
              VecItem.addElement(new Combo(g.TabStammtypen.getS(iPos,"Bezeichnung"), g.TabStammtypen.getI(iPos,"Aic"), g.TabStammtypen.getS(iPos,"Kennung"), 0));
          }
          //g.TabStammtypen.pop();
          //g.TabEigenschaften.pop();
          //g.TabErfassungstypen.pop();
          Sort();
        }

        public void fillFormat(int iBit,boolean rbKein,String sTab,boolean bUser)
        {
          //g.fixInfo("fillFormat "+sTab+", iBit="+iBit+", User="+bUser);
          long lClock = Static.get_ms();
          int iBG=g.TabBegriffgruppen.getAic("Format");
          if (Static.bFormatSortiert)
          {
            iSortiertNach=Bezeichnung;
            Clear();
            for(int iPos=g.TabBegriffe.getPos("Gruppe", iBG); g.TabBegriffe.getI(iPos,"Gruppe") == iBG; iPos++) {
              if(/*g.TabBegriffe.getI(iPos,"Bits") == 0 ||*/ (g.TabBegriffe.getI(iPos,"Bits") & iBit) > 0  && (!bUser || (g.TabBegriffe.getI(iPos,"Bits") & 32/*cstUser*/) > 0))
                VecItem.addElement(new Combo(g.TabBegriffe.getS(iPos,"Bezeichnung"), g.TabBegriffe.getI(iPos,"Aic"), g.TabBegriffe.getS(iPos,"Kennung"), 0));
            }
            setKein(rbKein);
            Sort();
          }
          else
          {
            iSortiertNach = kein;
            String s = sTab.equals("Spalte") ? "select count(*) from Spalte where aic_begriff=begriff.aic_begriff" :
                "select count(*) from " + sTab + " where format=begriff.kennung";
            fillCbo("select * from (select aic_begriff,kennung,defbezeichnung Bezeichnung,(" + s + ") Anzahl from begriff" +
                    " where aic_begriffgruppe=" + iBG + (bUser?" and "+g.bit("Bits",32/*cstUser*/):"")+
                    " and "+ g.bits("Bits", iBit) + ">0) x order by Anzahl desc,Bezeichnung",
                    "begriff", rbKein, false);
          }
          g.clockInfo("fillFormat"+iBit,lClock);
        }

	public ComboSort(Global glob)
	{
          this(glob,Bezeichnung);
	}

	public ComboSort(Global glob,int iSortMethode)
	{
		super();
		if (iSortMethode==kein)
			bSorted = false;
		else
			iSortiertNach=iSortMethode;
		g=glob;
                if (Static.bDefaultLook)
                {
                  setBackground(java.awt.Color.WHITE);
                  if (CboDefault != null)
                	  setUI(CboDefault);
                  //setBorder(javax.swing.plaf.BorderUIResource.getEtchedBorderUIResource());
                }
                //setMinimumSize(new java.awt.Dimension(40,15));
                //setOpaque(true);
                int iFF=g.getFontFaktor();
                int iH=17*iFF/100;
                //g.fixtestError("Combo nun 150x"+iH);
                setPreferredSize(new java.awt.Dimension(iFF*(Static.bND ? 175:150)/100,iH));
                //setBounds(0,0,0,0);
                if (!Static.bRahmen)
                  setBorder(null);
                setMaximumRowCount(30);
                setFont(Transact.fontStandard);
                Count.add(Count.ComboSort);
                if (iSortiertNach==Farbe)
                {
                  setRenderer(new ColoredRenderer());
                  iSortiertNach=Bezeichnung;
                  addFocusListener(new FocusListener()
                  {
                    public void focusGained(FocusEvent fe)
                    {
                      g.progInfo("Cbo.focusGained3 ");
                    }

                    public void focusLost(FocusEvent fe)
                    {
                      g.progInfo("Cbo.focusLost3 ");
                      setBackground(Color.WHITE);
                      //CheckColor(true);
                    }
                  });
                }
                addItemListener(new ItemListener ()
                {
                        public void itemStateChanged(ItemEvent ev)
                        {
                                if(ev.getStateChange() == ItemEvent.SELECTED)
                                        CheckColor(true);
                        }
                });

	}
	public void fillWaehrung(boolean rbKein,boolean bAlle)
	{
		//fillCbo("select stammview2.aic_stamm,bezeichnung kennung"+g.AU_Bezeichnung2("Stamm","stammview2")+",spalte_double faktor from poolview2 join stammview2 on poolview2.aic_stamm=stammview2.aic_stamm where aic_eigenschaft="+g.iEigEurofaktor+
		//" and aic_stammtyp="+g.iSttWaehrung,"Stamm",rbKein,true);

            Clear();
            for(int iPos=0;iPos<g.TabWaehrung.size();iPos++)
            {
              //if (bAlle || !g.WW() || g.TabWaehrung.getB("WCalc"))
              {
                String sKennung = g.TabWaehrung.getS(iPos,"Kennung");
                VecItem.addElement(new Combo(g.TabWaehrung.getS(iPos,"Bezeichnung"),g.TabWaehrung.getI(iPos,"aic_stamm"), sKennung.trim(),g.TabWaehrung.getF(iPos,"Faktor")));
              }
            }
            setKein(rbKein);
            Sort();
	}
	/*public void fillDaten(int iEigenschaft,String sDatentyp,int iStamm)
	{
		fillDaten(iEigenschaft,sDatentyp,iStamm,0);
	}*/

      /*public void fillHS(int iBegriff)
      {
              fillCbo("SELECT aic_Hauptschirm,kennung"+g.AU_Bezeichnung("Hauptschirm")+",bits faktor FROM Hauptschirm where aic_begriff="+iBegriff+" and (aic_benutzer is null or aic_benutzer="+g.getBenutzer()+" or "+SQL.bit("bits",0x8000 )+")","Hauptschirm",true,true);
      }*/

	public void fillDaten(int iEigenschaft,int iTextLaenge)
	{
          long lClock = Static.get_ms();
          iEig=iEigenschaft;
          /*
          g.printInfo(3,"fillDaten:"+iEigenschaft+"/"+sDatentyp+"="+(Static.get_ms()-g.lClock));
          String s="from daten_"+sDatentyp+" d join poolview on d.aic_daten=poolview.aic_daten where aic_eigenschaft="+iEigenschaft+(iStamm==0?"":" and aic_stamm="+iStamm);
          if (iStamm>0 || SQL.getInteger(g,"select count(distinct d.aic_daten) "+s)<1000)
              {
		SQL Qry = new SQL(g);
		Qry.open("select distinct spalte_"+sDatentyp+" Bez,d.aic_daten aic "+s);
		while(!Qry.eof())
		{
			String sBez = Qry.getS("Bez");
			if (contain(Bezeichnung+Kennung,sBez)==-1)
				VecItem.addElement(new Combo(sBez, Qry.getI("aic"),"",0));
			Qry.moveNext();
		}
		Qry.free();
              }
              Sort();*/
              //if (iStamm==0)
                setEditable(iTextLaenge);
          lClockDaten+=Static.get_ms()-lClock;
          lClockAnz++;
	}
    public void setEditable(int iTextLaenge)
	{
		bString=true;
                //if (bEdit)

		setTextLength(iTextLaenge);
                addKeyListener(new KeyListener()
                {
                  public void keyReleased(KeyEvent ev)
                  {
                    int iKeyCode = ev.getKeyCode();
                    if (iKeyCode == KeyEvent.VK_DELETE)
                    {
                      int iOld = iOldAIC;
                      if (bKeinSoll)
                        setSelectedAIC(0);
                      iOldAIC = iOld;
                    }
                  }
                  public void keyPressed(KeyEvent ev) {}
                  public void keyTyped(KeyEvent ev) {}
                });
		getComboSortEditor().addKeyListener(new KeyListener()
		{
			public void keyReleased(KeyEvent ev)
			{
				int iKeyCode=ev.getKeyCode();
                                /*if (iKeyCode==KeyEvent.VK_ESCAPE)
                                {
                                  g.progInfo("Cbo-ESC pressed");
                                  return;
                                }*/
                                if(iKeyCode==KeyEvent.VK_DELETE)
                                {
                                  int iOld=iOldAIC;
                                  if (bKeinSoll)
                                    setSelectedAIC(0);
                                  iOldAIC=iOld;
                                  ((JTextField)ev.getSource()).selectAll();
                                }
				else if(iKeyCode!=KeyEvent.VK_BACK_SPACE || get(STAMM))
				{
					String sText=((javax.swing.JTextField)ev.getSource()).getText();
                                        //g.progInfo("setEditable:"+sText+"/"+getSelectedItem());
                                        if (sText.equals(""))
                                        {
                                          int iOld=iOldAIC;
                                          if (bKeinSoll)
                                            setSelectedAIC(0);
                                          iOldAIC=iOld;
                                          ((JTextField)ev.getSource()).selectAll();
                                          sLast=getSelectedItem().toString();
                                        }
					else if (getSelectedItem()!=null && !sText.equals(getSelectedItem().toString()))
					{
                                          String s1=getSelectedItem().toString();
                                          String s2=((JTextField)getComboSortEditor()).getText();
                                          if (s1.length()>s2.length() && s1.startsWith(s2) && get(STAMM))
                                          {
                                            ((JTextField)getComboSortEditor()).setText(s1);
                                            sLast=s1;
                                          }
                                          if (setSelectedStart(sText))
                                          {
                                            sLast=getSelectedItem().toString();
                                            ((JTextField)ev.getSource()).setSelectionStart(sText.length()-(iKeyCode==KeyEvent.VK_BACK_SPACE?1:0));
                                          }
                                          else if (get(STAMM))
                                          {
                                            Static.beep();
                                            ((JTextField)getComboSortEditor()).setText(sLast);
                                            ((JTextField)ev.getSource()).setSelectionStart(sText.length()-1);
                                            ev.consume();
                                          }
					}
				}
			}
			public void keyPressed(KeyEvent ev)
			{
			}
			public void keyTyped(KeyEvent ev)
			{
                            boolean bSelected = ((javax.swing.JTextField)ev.getSource()).getSelectedText() != null;
                            int iText = ((javax.swing.JTextField)ev.getSource()).getText().length();
                            if (!bSelected && iLaenge > 0 && ev.getKeyChar() != 8 && iText >= iLaenge)
                              ev.consume();
			}
		});
		getComboSortEditor().addFocusListener(new FocusListener()
		{
				public void focusGained(FocusEvent fe)
				{
					//g.progInfo("Cbo.focusGained "+iEig+"/"+get(LEER));
                                        if (get(LEER) && iEig>0)
                                        {
                                          String sText=((JTextField)fe.getSource()).getText();
                                          set(LEER,false);
                                          g.TabEigenschaften.posInhalt("AIC",iEig);
                                          String sDatentyp=g.TabEigenschaften.getS("Datentyp");
                                          g.progInfo("fillDaten:" + iEig + "/" + sDatentyp + "=" + (Static.get_ms() - g.lClock));
                                          String s = "from daten_" + sDatentyp + " d join poolview on d.aic_daten=poolview.aic_daten where aic_eigenschaft=" + iEig;/* +
                                              (iStamm == 0 ? "" : " and aic_stamm=" + iStamm);*/
                                          //if (SQL.getInteger(g, "select count(distinct d.aic_daten) " + s) < 1000)
                                          //{
                                            SQL Qry = new SQL(g);
                                            Qry.open(g.topA(50,true)+"spalte_" + sDatentyp + " Bez,d.aic_daten aic " + s+g.topE(50));
                                            while (!Qry.eof())
                                            {
                                              String sBez = Qry.getS("Bez");
                                              if (contain(Bezeichnung + Kennung, sBez) == -1)
                                                VecItem.addElement(new Combo(sBez, Qry.getI("aic"), "", 0));
                                              Qry.moveNext();
                                            }
                                            Qry.free();
                                          //}
                                          Sort();
                                          ((JTextField)fe.getSource()).setText(sText);
                                        }
                                        //((JTextField)fe.getSource()).setSelectionStart(0);
                                        //((JTextField)fe.getSource()).setSelectionEnd(((javax.swing.JTextField)fe.getSource()).getText().length());
                                        ((JTextField)fe.getSource()).selectAll();
                                        //g.progInfo("Cbo.focusGained fertig");
				}
				public void focusLost(FocusEvent fe)
				{
                                  //g.progInfo("Cbo.focusLost2 ");
                                  setEditable(false);
                                  //CheckColor(true);
                                  //g.progInfo("Cbo.focusLost "+iEig+"/"+get(LEER));
                                  //Static.sleep(100);
                                }
		});


                addFocusListener(new FocusListener()
                {
                                public void focusGained(FocusEvent fe)
                                {
                                  //g.progInfo("Cbo.focusGained2 ");
                                  if (bCombo2)
                                    setEditable(true);
                                  getComboSortEditor().requestFocus();
                                }
                                public void focusLost(FocusEvent fe)
                                {
                                  //g.progInfo("Cbo.focusLost ");
                                  //CheckColor(true);
                                }
		});
	}
	public void finalize()
	{
		Count.sub(Count.ComboSort);
	}

        private void CheckColor(boolean b)
        {
          //g.progInfo("CheckColor:"+(b && Modified()));
          Color col1=Static.ColEdt(b && Modified(),true);
          Color col2=getBackground();
          if (!col1.equals(col2))
            setBackground(col1);
        }

	public void Clear()
	{
		Clear(true);
	}
	public void fillSchrift()
	{
		String[] fonts=java.awt.GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
		int iLaenge=fonts.length;
		Vector<String> VecSA=new Vector();
		for(int i=0;i<iLaenge;i++)
		{
			VecItem.addElement(new Combo("",i,fonts[i],0.0));
			VecSA.addElement(fonts[i].toUpperCase());
		}
		Tabellenspeicher Tab=new Tabellenspeicher(g,"select schriftart.*"+g.AU_Bezeichnung("schriftart")+" from schriftart",true);
		for(Tab.moveFirst();!Tab.eof();Tab.moveNext())
		{
			String sKen=Tab.getS("Kennung").toUpperCase();
			if (!VecSA.contains(sKen))
			{
				g.fixtestError("füge Schrift "+sKen+" hinzu");
				VecSA.addElement(sKen);
				VecItem.addElement(new Combo(Static.beiLeer(Tab.getS("Bezeichnung"), Tab.getS("Kennung")),Tab.getI("aic_schriftart"),Tab.getS("Kennung"),0.0));
			}
		}
		Sort();
	}
	public void fillFarbe(boolean bKennung)
	{
		Tabellenspeicher Tab=new Tabellenspeicher(g,"select farbe.*"+g.AU_Bezeichnung("farbe")+" from farbe where kennung not like '.%'",true);
		for(Tab.moveFirst();!Tab.eof();Tab.moveNext())
			VecItem.addElement(new Combo(Tab.getS("Bezeichnung"),Tab.getI("Aic_farbe"),Tab.getS(bKennung?"Kennung":"Bezeichnung"),Tab.getI("red")*256*256+Tab.getI("green")*256+Tab.getI("blue")));
		setKein(true);
		Sort();
	}
	/*public void fillDruck(int iRolle,int iStt,int iDruck,Vector VecDrucke,boolean bExport)
	{
          //g.progInfo("filDruck:"+iStt+"/"+iDruck+"/"+VecDrucke);
          int iPosBG=g.TabBegriffgruppen.getPos("Kennung","Druck");
		if (iPosBG>=0)
		{
			Clear();
                        if (iRolle>0 && iStt!=g.RolleToStt(iRolle))
                          iRolle=0;
			//Tabellenspeicher Tab = g.TabBegriffe;
			int iPos=g.TabBegriffe.getPos("Gruppe",g.TabBegriffgruppen.getI(iPosBG,"AIC"));
			for(int iGruppe=g.TabBegriffe.getI(iPos,"Gruppe");iPos<g.TabBegriffe.size() && g.TabBegriffe.getI(iPos,"Gruppe")==iGruppe;iPos++)
			{
				if((iDruck==0 && (g.TabBegriffe.getI(iPos,"bits")&0x400)>0 && ((iRolle>0 ? g.TabBegriffe.getI(iPos,"Rolle")==iRolle:g.TabBegriffe.getI(iPos,"Stt")==iStt)
                                    && ((g.TabBegriffe.getI(iPos,"bits")&0x100)>0 || g.TabBegriffe.getI(iPos,"Erf")==0) || iStt<0 && g.TabBegriffe.getI(iPos,"Erf")==-iStt) || iDruck==g.TabBegriffe.getI(iPos,"Aic"))
                                   && g.BerechtigungS(iPos) && (VecDrucke==null || VecDrucke.contains(g.TabBegriffe.getInhalt("Aic",iPos))) && (!bExport || (g.TabBegriffe.getI(iPos,"bits")&0x400000)>0))
					VecItem.addElement(new Combo(g.TabBegriffe.getS(iPos,"Bezeichnung"),g.TabBegriffe.getI(iPos,"Aic"),g.TabBegriffe.getS(iPos,"Kennung"),g.TabBegriffe.getI(iPos,"bits")));
			}
			setKein(false);
			Sort();
		}
	}*/

        /*public boolean Privileg(int iPos)
        {
          if ((g.TabBegriffe.getL(iPos,"bits")&0x40000000000l)==0)
            return true;
          else if (g.getBenutzer()==SQL.getInteger(g,"select aic_benutzer from abfrage where aic_begriff="+g.TabBegriffe.getI(iPos,"Aic")))
            return true;
          else
            return g.checkBG(SQL.getInteger(g,"select aic_benutzergruppe from abfrage where aic_begriff="+g.TabBegriffe.getI(iPos,"Aic")));
        }*/

	public void fillAbfrage(int iStt)//,Vector VecAbfragen)
	{
          int iPosBG=g.TabBegriffgruppen.getPos("Kennung","Abfrage");
		if (iPosBG>=0)
		{
			Clear();
                        int iPos=g.TabBegriffe.getPos("Gruppe",g.TabBegriffgruppen.getI(iPosBG,"AIC"));
			for(int iGruppe=g.TabBegriffe.getI(iPos,"Gruppe");iPos<g.TabBegriffe.size() && g.TabBegriffe.getI(iPos,"Gruppe")==iGruppe;iPos++)
			{
				if((g.TabBegriffe.getI(iPos,"Stt")==iStt || iStt<0 && g.TabBegriffe.getI(iPos,"Erf")==-iStt) && (g.TabBegriffe.getL(iPos,"bits")&0x0060/*cstDruckbar,cstExportierbar*/)>0
                                   && !g.isTod(g.TabBegriffe.getInhalt("Aic",iPos)) && g.Privileg(iPos,-1,-1))
                                   //((g.TabBegriffe.getL(iPos,"bits")&0x4000 /*cstVersionsup*/)==0 || VecAbfragen==null || VecAbfragen.contains(g.TabBegriffe.getInhalt("Aic",iPos))) && Privileg(iPos))
					VecItem.addElement(new Combo(g.TabBegriffe.getS(iPos,"Bezeichnung"),g.TabBegriffe.getI(iPos,"Aic"),g.TabBegriffe.getS(iPos,"Kennung"),0/*g.TabBegriffe.getL(iPos,"bits")*/));
			}
			setKein(false);
			Sort();
		}
	}
	public void fillMass2(int iStamm,boolean rbKein)
	{
		fillCbo("select stammview.aic_stamm,p2.spalte_double faktor,bezeichnung kennung"+g.AU_Bezeichnung2("Stamm","stammview")+" from poolview p2 join stammview on stammview.aic_stamm=p2.aic_stamm where p2.aic_eigenschaft="+g.iEigFaktor+
 				" and p2.sta_aic_stamm="+iStamm,"Stamm",rbKein,true);
	}
    public void Clear(boolean bKein)
    {
		if(VecItem.size()>0)
			VecItem.removeAllElements();

		if(getItemCount()>0)
			removeAllItems();

		if(bKein)
			addKein();
    }

	public String toString()
	{
		return "ComboSort "+VecMom;
	}

	@SuppressWarnings("unchecked")
	public void Sort()
	{
          //long lClock = Static.get_ms();
          //bKeinIst=iOldAIC==0;
          //g.testInfo("Sort");
          if (VecMom==null || VecMom.size()!=VecItem.size() || !bKeinSoll && iOldAIC!=0)
          {
            VecMom=new jclass.util.JCVector();
            for (int i = 0; i < VecItem.size(); i++)
              if ((VecItem.elementAt(i)).use || iOldAIC==(VecItem.elementAt(i)).getAic())
                VecMom.addElement(VecItem.elementAt(i));
            if (bSorted && VecMom.size()>1)
            {
              //g.fixtestError("Sort:"+VecMom);
              VecMom.sort(iSortiertNach == Bezeichnung ? sortComboByBezeichnung : iSortiertNach == Kennung ? sortComboByKennung : sortComboByAIC);
            }
          }
		setModel(new javax.swing.DefaultComboBoxModel(VecMom));
		installAncestorListener();
                //setUI(new com.sun.java.swing.plaf.windows.WindowsComboBoxUI());
          //long lClock2 = Static.get_ms();
          //if (lClock2>lClock)
          //  g.progInfo("Sort="+(lClock2-lClock)+":"+VecMom);
          //g.printInfo(3,"Sort "+VecItem.size()+":"+(lClock2-lClock)+"/"+(lClock2-g.lClock));
	}

        public boolean isEmpty()
        {
          //g.fixInfo("isEmpty:"+VecItem.size()+"/"+bKeinSoll);
          return VecItem==null || VecItem.size()<(bKeinSoll ? 2:1);
        }

    public boolean isSorted() {
        return bSorted;
    }

    public void setSorted(boolean newValue) {
		if (bSorted != newValue)
		{
			bSorted = newValue;
			Sort();
		}
    }

    public void addItem(String sText, int iAIC, String sKennung)
    {
		addItem(sText, iAIC, sKennung, 0);
    }

    public void addItem2(String sText, int iAIC, String sKennung)
    {
      VecItem.addElement(new Combo(sText,iAIC,sKennung,0));
    }

    public void addItem2(String sText, int iAIC, String sKennung,double dFaktor)
    {
      VecItem.addElement(new Combo(sText,iAIC,sKennung,dFaktor));
    }

    public void addItem2(String sText, int iAIC, String sKennung,double dFaktor,Color Col)
    {
      if (Col != null)
    	  g.fixtestInfo("gefärbt bei "+sText+" ("+iAIC+")");
      VecItem.addElement(new Combo(sText,iAIC,sKennung,dFaktor,Col));
    }

    public void addItem(String sText, int iAIC, String sKennung,double dFaktor)
    {
		VecItem.addElement(new Combo(sText,iAIC,sKennung,dFaktor));
		Sort();
    }

    public void addItem(String sText, int iAIC, boolean rb)
    {
                VecItem.addElement(new Combo(sText,iAIC,rb));
                Sort();
    }

	public void setItem(int iAIC,String sText,String sKennung,double dFaktor)
	{
		//VecItem.setElementAt(VecItem.indexOf(getSelectedItem()),new Combo(sText,iAIC,sKennung,dFaktor));
		if (((Combo)getSelectedItem()).change(sText,iAIC,sKennung,dFaktor))
                  Sort();
		setSelectedAIC(iAIC);
	}

	public void removeItemAt(int iAIC)
	{
		int i = contains2(iAIC);
		if(i>=0 && i<VecItem.size())
		{
			VecItem.removeElementAt(i);
			Sort();
		}
		else
			g.printInfo(1,"ComboSort.removeItemAt("+iAIC+"): Item mit dem angegebenen Element konnte nicht gefunden werden!!!");
	}

	public void setKein(boolean rbKein)
	{
		//g.addTemp(", setKein:"+rbKein+" statt "+bKeinSoll);
		//g.progInfo("setKein:"+bKeinSoll+"->"+rbKein);
		if (bKeinSoll != rbKein || bKeinIst != rbKein)
		{
			bKeinSoll = rbKein;
			setKeinIst(bKeinSoll);
		}
	}

	private void setKeinIst(boolean rbKein)
	{
		//g.addTemp(","+VecItem.size()+"- setKeinIst:"+rbKein+" statt "+bKeinIst);
		//g.progInfo("setKeinIst:"+bKeinIst+"->"+rbKein);
		//if (bKeinIst != rbKein)
		//{
			if (rbKein)
				addKein();
			else if(VecItem.size()>1)
			{
				//removeItemAt(0);
                                Sort();
				bKeinIst = iOldAIC==0;
			}
		//}
	}

	private void addKein()
	{
          if (!checkItem(0,bKeinSoll))
            addItem(Static.sKein, 0,bKeinSoll);
          bKeinIst = true;
	}

    /**
     * getBezeichnung Method
     */

    public Combo getItem(int key)
    {
		int i=contain(Aic,new Integer(key));
		return i!=-1 ?(Combo)VecMom.elementAt(i):null;
    }

    public Combo getCombo()
    {
      return getItem(getSelectedAIC());
    }

    public String getBezeichnung(int key)
    {
		int i=contain(Aic,new Integer(key));
		return i>=0?((Combo)VecMom.elementAt(i)).toString():"Not exists !!!";
    }

    public String getBezeichnung2(int key)
    {
                int i=contain(Aic,new Integer(key));
                return i>=0?((Combo)VecMom.elementAt(i)).toString():"";
    }

    /*
    /**
     * getNumber Method
     */

   /* public int getNumber()
    {
        return(VecItem.size());
    }*/
    /**
     * getSelectedAIC Method
     */

    public int getSelectedAIC()
    {
		Object obj = getSelectedItem();
                if (obj instanceof String && obj.equals(""))
                {
                  //g.debugInfo("******************** getSelectedAIC=0");
                  return 0;
                }
		int i = getSelectedIndex();

		if(i==-1 && obj instanceof String)
		{
			i = contain(Bezeichnung,(String)obj);
			set(OK,i>=0);
			if(get(OK))
			{
				setSelectedIndex(i);
				obj = getSelectedItem();
			}
		}
		else
			set(OK,true);
                return obj!=null&& i>=0?((Combo)obj).getAic():bString?-1:0;
    }

    public String getSelectedKennung()
    {
		Object obj = getSelectedItem();
		return obj!=null? obj instanceof Combo?((Combo)getSelectedItem()).getKennung():(String)obj:"";
    }

	public String getSelectedBezeichnung()
    {
		Object obj = getSelectedItem();
		return obj!=null?obj instanceof Combo?((Combo)getSelectedItem()).toString():(String)obj:"";
    }

	public double getSelectedFaktor()
    {
		Object obj = getSelectedItem();
		return obj!=null && obj instanceof Combo?((Combo)getSelectedItem()).getFaktor():0.0;
    }

    /**
     * setSelectedAIC Method
     */

	private boolean setSelectedStart(String s)
	{
		if (s.equals(""))
			return false;
		int iAnz=getItemCount();
		int i;
		for (i=0;i<iAnz && !((Combo)getItemAt(i)).toString().toUpperCase().startsWith(s.toUpperCase());i++);
		if (i<iAnz)
		{
			//sVorher=(String)getItemAt(i);
			setSelectedIndex( i );
		}
                boolean bOk=i<iAnz;
                return bOk;
	}

	public int contain(int iArt, Object ObjWert)
	{
		int i=0;
		boolean bFound = false;
                if (VecMom != null)
                  for(;i<VecMom.size() && !bFound;i++)
                  {
			Combo Com = (Combo)VecMom.elementAt(i);
			bFound = bFound || (iArt==Bezeichnung && Com.getBezeichnung().equals((String)ObjWert));
			bFound = bFound || (iArt==Bezeichnung+Kennung && Com.toString().equals((String)ObjWert));
			bFound = bFound || (iArt==Kennung && Com.getKennung().equals((String)ObjWert));
			bFound = bFound || (iArt==Aic && Com.getAic()==((Integer)ObjWert).intValue());
                        //if (iArt==Kennung)
                        //  g.progInfo("          "+Com.getKennung()+"/"+ObjWert);
                  }

		if(bFound)
			i--;
		else
			i=-1;

		return i;
	}

        public boolean contains(int iAic)
        {
          for (int i=0; i < VecItem.size(); i++)
            if(VecItem.elementAt(i).getAic() == iAic)
              return true;
          return false;
        }

        private int contains2(int iAic)
        {
          boolean bFound = false;
          int i=0;
          for(;i<VecItem.size() && !bFound;i++)
            bFound = VecItem.elementAt(i).getAic()==iAic;
          return bFound?i-1:-1;
        }

        private boolean checkItem(int iAic,boolean rbKeinSoll)
        {
          boolean bFound = false;
          Combo C=null;
          for(int i=0;i<VecItem.size() && !bFound;i++)
          {
            C=VecItem.elementAt(i);
            bFound = C.getAic() == iAic;
          }
          if (bFound)
          {
            C.use=rbKeinSoll;
            Sort();
          }
          return bFound;
        }

    public boolean setSelectedAIC(int key)
    {
      return setSelectedAIC(key,null,null,true);
    }

    public boolean setSelectedAIC(int key,String sK,String sB,boolean bNeu)
    {
		//g.addTemp(", setSelectedAIC:"+key);
                //g.fixtestInfo("setSelectedAIC "+iOldAIC+"->"+key);
		if (bNeu)
                  iOldAIC=key;
                setKeinIst(key==0 || bKeinSoll);
		//if (key==0)
		//	setKeinIst(true);
		int i=contain(Aic,new Integer(key));
                if (i<0 && contains2(key)>=0)
                {
                  Sort();
                  i=contain(Aic,new Integer(key));
                }
		if(i>=0)
			setSelectedIndex(i);
		else
		{
			if (get(STAMM))
			{
                          if (sK==null)
                          {
                            SQL Qry = new SQL(g);
                            Qry.open("SELect aic_stamm,bezeichnung kennung" + g.AU_Bezeichnung2("Stamm", "p2") + " from stammview2 p2 where aic_stamm=" + key);
                            if (!Qry.eof())
                            {
                              String s=Static.beiLeer(Qry.getS("Bezeichnung"), Qry.getS("Kennung"));
                              //g.fixInfo("Bezeichnung="+Qry.getS("Bezeichnung")+", Kennung="+Qry.getS("Kennung")+", Aic="+Qry.getI("aic_stamm")+"->"+s);
                              int iTemp=iOldAIC;
                              iOldAIC=key;
                              addItem(s, key, false);
                              setSelectedIndex(contain(Aic, new Integer(key)));
                              iOldAIC=iTemp;
                              g.fixtestInfo("füge <"+s+"> Stammsatz " + key + " dazu ");//->"+getSelectedAIC());
                              //CheckColor(true);
                              //return true;
                            }
                            else
                            {
                              setKeinIst(true);
                              setSelectedIndex(contain(Aic, new Integer(0)));
                              g.progInfo("Stammsatz " + key + " nicht gefunden -> wurde auf 0 gesetzt!");
                            }
                            Qry.free();
                          }
                          else
                          {
                            addItem(Static.beiLeer(sB, sK), key, false);
                            setSelectedIndex(contain(Aic, new Integer(key)));
                          }
			}
			else
			{
				setKeinIst(true);
				setSelectedIndex(contain(Aic,new Integer(0)));
				g.printInfo(1,"Aic "+key+" wurde auf 0 gesetzt!");
			}
		}
                if (isEditable())
                  ((JTextField)getComboSortEditor()).select(0,0);
                CheckColor(true);
		return i>=0;
    }

    public void setFirst()
    {
      if (getSelectedAIC()==0 && VecMom.size()>1)
        setSelectedIndex(1);
    }

    /**
     * setSelectedKennung Method
     */

    public boolean setSelectedKennung(String sKennung)
    {
		int i=contain(Kennung,sKennung);
		if(i>=0)
		{
			setSelectedIndex(i);
			iOldAIC=getSelectedAIC();
                        //g.progInfo("setSelectedKennung:"+sKennung+"->"+iOldAIC);
		}
		else
			iOldAIC=-1;
                CheckColor(false);
		return i>=0;
    }

	public boolean setSelectedKennung2(String sKennung)
    {
		setKeinIst(bKeinSoll);
		int i=contain(Kennung,sKennung);
		if(i>=0)
		{
			setSelectedIndex(i);
			iOldAIC=getSelectedAIC();
		}
		else
		{
			setKeinIst(true);
			setSelectedIndex(contain(Aic,new Integer(0)));
			iOldAIC=0;
			g.progInfo("setSelectedKennung2:Kennung "+sKennung+" wurde auf <kein> gesetzt!");
		}
                CheckColor(false);
		return i>=0;
    }

	public void setSelectedItem(String s)
	{
          //g.fixInfo("setSelectedItem:"+s);
		sOldText=s;
                if ("".equals(s))
                {
                  //g.progInfo("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! setSelectedItem mit nichts !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
                  super.setSelectedItem("*");
                }
		super.setSelectedItem(s);
	}

	public void addItem(Object Obj)
	{
		Static.printError("ComboSort: addItem(Object) darf nicht verwendet werden!!!");
	}

        //public void removeItemAt(int i)

	/*public boolean isKein()
	{
		return(bKeinSoll);
	}*/

	private JCSortable sortComboByBezeichnung = new JCSortable()
	{
		public long compare(Object obj1, Object obj2)
		{
			long comparing = 0;
			if(obj1==null || obj2==null)
				comparing = obj1==null?JCSortable.LESS_THAN:JCSortable.GREATER_THAN;
			else if(obj1 instanceof Combo && obj2 instanceof Combo)
			{
                          if (((Combo)obj1).getAic()==0)
                            return JCSortable.LESS_THAN;
                          else if (((Combo)obj2).getAic()==0)
                            return JCSortable.GREATER_THAN;
                          else
                          {
                            int i = ((Combo)obj1).toString().toUpperCase().replace('Ä', 'A').replace('Ö', 'O').replace('Ü',
                                'U').compareTo(((Combo)obj2).toString().toUpperCase().replace('Ä', 'A').replace('Ö', 'O').replace('Ü', 'U'));
                            comparing = i > 0 ? JCSortable.GREATER_THAN : i == 0 ? JCSortable.EQUAL : JCSortable.LESS_THAN;
                          }
			}
			else
				Static.printError("ComboSort.sortComboByBezeichnung: Falscher Datentyp!");

			return comparing;
		}
	};

	private JCSortable sortComboByKennung = new JCSortable()
	{
		public long compare(Object obj1, Object obj2)
		{
			long comparing = 0;
			if(obj1==null || obj2==null)
				comparing = obj1==null?JCSortable.LESS_THAN:JCSortable.GREATER_THAN;
			else if(obj1 instanceof Combo && obj2 instanceof Combo)
			{
				int i=((Combo)obj1).getKennung().toUpperCase().replace('Ä','A').replace('Ö','O').replace('Ü','U').compareTo(((Combo)obj2).getKennung().toUpperCase().replace('Ä','A').replace('Ö','O').replace('Ü','U'));
				comparing = i>0?JCSortable.GREATER_THAN:i==0?JCSortable.EQUAL:JCSortable.LESS_THAN;
			}
			else
				Static.printError("ComboSort.sortComboByKennung: Falscher Datentyp!");

			return comparing;
		}
	};

	private JCSortable sortComboByAIC = new JCSortable()
	{
		public long compare(Object obj1, Object obj2)
		{
			long comparing = 0;
			if(obj1==null || obj2==null)
				comparing = obj1==null?JCSortable.LESS_THAN:JCSortable.GREATER_THAN;
			else if(obj1 instanceof Combo && obj2 instanceof Combo)
				comparing = ((Combo)obj1).getAic()>((Combo)obj2).getAic()?JCSortable.GREATER_THAN:JCSortable.LESS_THAN;
			else
				Static.printError("ComboSort.sortComboByAIC: Falscher Datentyp!");

			return comparing;
		}
	};

	public Vector<Combo> getItems()
	{
		return (new Vector<Combo>(VecMom));
	}

  public Vector<Combo> getAllItems()
  {
    return new Vector<Combo>(VecItemCopy==null ? VecItem:VecItemCopy);
  }

  public void refresh()
  {
    if (VecItemCopy!=null)
      VecItem=new Vector<Combo>(VecItemCopy);
    VecItemCopy=null;
    Sort();
  }

	public boolean isNull()
	{
		return bString?editor.getItem() instanceof String?((String)editor.getItem()).equals(""):((Combo)editor.getItem()).getBezeichnung().equals(""):getSelectedAIC()==0;
	}

        public boolean wasNull()
        {
          return iOldAIC==0;
        }

	public boolean Modified()
	{
          //g.progInfo("Modified:"+bString+"/"+sOldText+"/"+iOldAIC+"/"+getSelectedAIC());
          if (bString && sOldText==null)
            return false;
        //g.fixtestInfo("Modified:"+bString+"/"+sOldText+"/"+iOldAIC+"->"+getSelectedAIC());
		return bString?!sOldText.equals(editor.getItem() instanceof String?(String)editor.getItem():((Combo)editor.getItem()).getBezeichnung()):iOldAIC!=getSelectedAIC();
	}

	public int getOld()
	{
		return iOldAIC;
	}

	public String getOldKennung()
	{
		int i=contain(Aic,new Integer(iOldAIC));
		return i>=0?((Combo)VecMom.elementAt(i)).getKennung():"";
	}

	public void setAktAIC(int i)
	{
		iOldAIC=i;
                CheckColor(false);
	}

        public void Reset()
        {
          //return bString?!sOldText.equals(editor.getItem() instanceof String?(String)editor.getItem():((Combo)editor.getItem()).getBezeichnung()):iOldAIC!=getSelectedAIC();
          if (bString)
            sOldText=editor.getItem() instanceof String ? (String)editor.getItem():((Combo)editor.getItem()).getBezeichnung();
          else
            iOldAIC=getSelectedAIC();
          CheckColor(false);
        }

        public void Reset2()
        {
          setSelectedAIC(iOldAIC);
          CheckColor(false);
        }

	private void setTextLength(int i)
	{
		iLaenge = i;
	}

	public void initBoolean()
	{
		bString=false;
	}

	public java.awt.Component getComboSortEditor()
	{
		return editor.getEditorComponent();
	}

        public void setEnabled(boolean b)
        {
          super.setEnabled(b && get(EDIT));
          setFont(b ? Transact.fontStandard:Global.fontDisabled);
        }

        public void setEditable1(boolean b)
        {
          if (!b)
          {
            set(EDIT,b);
            //UIManager.getDefaults().put("ComboBox.disabledForeground", java.awt.Color.BLACK);
            super.setEnabled(b);
            //super.setEditable(b);
          }
        }

        public void setBackground(Color c)
        {
          super.setBackground(c);
          if (editor != null)
          {
            getComboSortEditor().setBackground(c);
          }
        }

        /*public void paint()
        {
          CheckColor(true);
        }*/

        /*public static void showClock(Transact t,String s)
        {
          if (t.Clock())
            t.fixInfo("(C)"+Static.rightString("Cbo "+s,20)+":"+Static.printZahl(lClockAnz,6)+":"+Static.printZahl(lClockFill,6)+" / "+Static.printZahl(lClockAU,6)+" / "+Static.printZahl(lClockDaten,6));
        }*/

        public void set(int i,boolean b)
        {
          if (b)
            iBits|=i;
          else if ((iBits&i)>0)
            iBits-=i;
        }

        public boolean get(int i)
        {
          return (iBits&i)>0;
        }

    /****************************************
     * data members
     ****************************************/


    // add your data members here
	public static final int kein=0;
	public static final int Bezeichnung=1;
	public static final int Kennung=2;
	public static final int Aic=4;
    public static final int Farbe=5;

	private int iSortiertNach=Bezeichnung;
	private boolean bSorted=true;
	private boolean bKeinIst=false;
	public boolean bKeinSoll=false;
  public boolean bCombo2=false;
	private Vector<Combo> VecItem = new Vector<Combo>();
  private Vector<Combo> VecItemCopy=null;
  private jclass.util.JCVector VecMom;
	private Global g;
	private int iOldAIC=-1;
	private String sOldText;
        private String sLast;
	private boolean bString=false;
	private int iLaenge = -1;
        //public boolean bEdit=true;
	//public boolean bStamm=false;
	//public boolean bOk=true;

        private int iEig=0;
        private int iBits=EDIT+OK+LEER;

        public static final int OK=    1;
        public static final int EDIT=  2;
        public static final int STAMM= 4;
        public static final int LEER=  8;

        public static long lClockAnz=0;
        public static long lClockFill=0;
        public static long lClockAU=0;
        public static long lClockDaten=0;
}

class ColoredRenderer extends DefaultListCellRenderer
 {
   private static final long serialVersionUID=123;

   public Component getListCellRendererComponent(JList list, Object value,int index, boolean isSelected,boolean hasFocus)
   {
     Component c = super.getListCellRendererComponent(list, value, index,isSelected,hasFocus);
     Combo combo=(Combo)value;
     if (combo==null)
       ;//System.out.println("value für ColoredRenderer ist null");
     else if (!isSelected && !hasFocus)// && value instanceof Combo)
       c.setForeground(combo.getColor());
     else if (isSelected)
       c.setForeground(combo.getColor() == Color.BLACK ? Color.WHITE : Color.YELLOW);
     c.setBackground(isSelected ? Color.BLACK:Color.WHITE);
     //if (!(value instanceof Combo))
     //  System.out.println(Static.print(value)+"/"+isSelected+"/"+hasFocus);
     //if (hasFocus)
     //  c.setBackground(Color.YELLOW);
     //System.out.println(value+"/"+index+"/"+isSelected+"/"+hasFocus);
     return c;
   }
 }





