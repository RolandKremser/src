/*
    All_Unlimited-Grunddefinition-DefExport.java

    NOTE: This file is a generated file.
          Do not modify it by hand!
*/

package All_Unlimited.Grunddefinition;

// add your custom import statements here
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Date;
import java.util.Vector;
import java.util.zip.ZipEntry;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;

import jclass.bwt.JCOutlinerFolderNode;
import All_Unlimited.Allgemein.*;
import All_Unlimited.Allgemein.Eingabe.FileEingabe;
import All_Unlimited.Allgemein.Eingabe.Text;
import All_Unlimited.Hauptmaske.AClient;
import All_Unlimited.Hauptmaske.ShowAbfrage;
import All_Unlimited.Hauptmaske.Abfrage;
import All_Unlimited.Allgemein.Eingabe.AUOutliner;
import jclass.bwt.JCOutlinerNode;
import jclass.bwt.JCOutlinerNodeStyle;

public class DefExport extends java.lang.Object
{
public DefExport(Vector<Integer> rVecDaten, JFrame FrmVU, Global glob,int riProg,boolean rbNormal,boolean rbAll,boolean rbWeb)//,boolean rbFremd)
{
	FrmVU.setCursor(new Cursor(Cursor.WAIT_CURSOR));
	g=glob;
	if (g.getVon()==null || g.getBis()==null)
	{
		FrmVU.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		new Message(Message.WARNING_MESSAGE,FrmVU,g).showDialog("ZR_fehlt");
		return;
	}
	VecDaten=rVecDaten;
        iProg=riProg;
        bNormal=rbNormal;
        bAll=rbAll;
        bWeb=rbWeb;
        //bNurModul=rbNurModul;
        //bFremd=rbFremd;
	TabBegriff = new Tabellenspeicher(g,new String[]{"AIC","Node","Start","Art"});
        bOk=true;
        //bTest2=Static.bTest;
        //Static.bTest=false;
	Ermitteln();

	Build(FrmVU);
	FrmVU.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
}

private void checkMB(Vector<Integer> Vec,String s)
{
        int iBG=g.TabBegriffgruppen.getAic(s);
        for(int i=0;i<VecMB.size();i++)
        {
          Integer I = VecMB.elementAt(i);
          int iPos=g.TabBegriffe.getPos("AIC",I);
          if(!Vec.contains(I) && iBG==g.TabBegriffe.getI(iPos,"Gruppe"))
            Vec.addElement(I);
        }
}

@SuppressWarnings("unchecked")
public void Ermitteln()
{
        //Module ermitteln
        SQL Qry=new SQL(g);
        String sDaten=g.in("aic_begriff",VecDaten);
        if (iProg==0)
        {
          Vector VecKunde = SQL.getVector("select aic_begriff from begriff b where " + sDaten + " and b.aic_begriffgruppe=" + g.TabBegriffgruppen.getAic("Kunde"), g);
          g.progInfo("VecKunde=" + VecKunde);
          VecModule = SQL.getVector("select beg_aic_begriff from begriff_zuordnung where " + g.in("aic_begriff", VecKunde), g);
          VecAbfStamm=null;
          sProg="x";
        }
        else
        {
          VecModule = new Vector<Integer>();
          sProg=g.TabCodes.getKennung(iProg);
          VecAbfStamm=SQL.getVector("select aic_begriff from PROGRAMM_ZUORDNUNG where aic_code="+iProg+" order by reihenfolge", g);
        }
        if (bAll || bWeb)
          SQL.addVector(VecModule,"select aic_begriff from begriff b where "+(iProg>0 ? "Prog="+iProg:sDaten)+" and b.aic_begriffgruppe="+g.TabBegriffgruppen.getAic("Modul")+
        		  (bAll!=bWeb ? " and web"+(bWeb ? "=1":" is null"):""),g);
        g.progInfo("VecModule="+VecModule);
        SQL.addVector(VecMB,"select beg_aic_begriff from begriff_zuordnung z join begriff on z.beg_aic_begriff=begriff.aic_begriff and ende is null where "+g.in("z.aic_begriff",VecModule),g);
        g.progInfo("VecMB="+VecMB);
        VecSprache = g.ApplPort() ? SQL.getVector("select aic_sprache from sprache",g):
            SQL.getVector("select aic_fremd from modul where aic_tabellenname="+g.TabTabellenname.getAic("SPRACHE")+" and "+g.in("aic_begriff",VecModule),g);
        if (!VecSprache.contains(1))  // Deutsch immer exportieren
          VecSprache.addElement(1);
        g.progInfo("VecSprache="+VecSprache);
        VecAbfrage = new Vector<Integer>();
        if (iProg>0 && (bAll || bWeb))
        {
        	//Vector VecTT = SQL.getVector("select aic_code from code where aic_begriffgruppe="+g.TabBegriffgruppen.getAic("Tabellentyp"),g);
        	//g.fixtestError("VecTT="+VecTT);
        	String sWeb=bAll!=bWeb ? " and web"+(bWeb ? "=1":" is null"):"";
        	Static.addVectorI(VecAbfrage,SQL.getVector("select b.aic_begriff from begriff b join abfrage a on a.aic_begriff=b.aic_begriff where jeder=1"+sWeb+" and (b.Prog="+iProg+(bWeb ? " or b.Prog is null":"")+")",g)); // Jeder-Abfragen
        	g.fixtestError("VecAbfrage1="+VecAbfrage);
        	Static.addVectorI(VecForm,SQL.getVector("select aic_begriff from begriff where jeder=1 and Prog="+iProg+" and aic_begriffgruppe="+g.TabBegriffgruppen.getAic("Frame")+sWeb,g)); // Jeder-Formulare
        	g.fixtestError("VecForm1="+VecForm);
        	SQL.addVector(VecModell,"select aic_begriff from begriff where Prog="+iProg+" and aic_begriffgruppe="+g.TabBegriffgruppen.getAic("Modell")+" and jeder=1"+sWeb,g); // Jeder-Modelle
        	if (bWeb)
        		SQL.addVector(VecDruck,"select aic_begriff from begriff where Prog="+iProg+" and aic_begriffgruppe="+g.TabBegriffgruppen.getAic("Druck")+" and web=1",g); // Web-Drucke
        }
        //Stamm-Abfragen dazu
        if (VecAbfStamm != null)
          Static.addVectorI(VecAbfrage,VecAbfStamm);
//        SQL.addVector(VecAbfrage,"select distinct abfrage.aic_begriff from status join abfrage on status.abfrage=abfrage.aic_abfrage",g);
        g.fixtestError("VecAbfrage2="+VecAbfrage);
	//Druck ermitteln
        checkMB(VecDruck,"Druck");
	SQL.addVector(VecDruck,"select aic_begriff from begriff b where "+sDaten+" and b.aic_begriffgruppe="+g.TabBegriffgruppen.getAic("Druck"),g);
        SQL.addVector(VecModell,"select beg_aic_begriff from begriff_zuordnung z join begriff b on z.beg_aic_begriff=b.aic_begriff and "+g.in("z.aic_begriff",VecDruck)+
                " and b.aic_begriffgruppe="+g.TabBegriffgruppen.getAic("Modell"),g);
        SQL.addVector(VecAbfrage,"select beg_aic_begriff from begriff_zuordnung z join begriff b on z.beg_aic_begriff=b.aic_begriff and "+g.in("z.aic_begriff",VecDruck)+
                " and b.aic_begriffgruppe="+g.TabBegriffgruppen.getAic("Abfrage"),g);
        SQL.addVector(VecAbfrage,"select a.aic_begriff from druck_zuordnung dz join abschnitt a on dz.aic_abschnitt=a.aic_abschnitt where "+g.in("dz.aic_begriff",VecDruck),g);
        g.progInfo("VecDruck="+VecDruck);
        for(int i=0;i<VecDruck.size();i++)
        {
          Integer iBegriff = VecDruck.elementAt(i);
          int iPos=g.TabBegriffe.getPos("AIC",iBegriff);
          checkKennung(g.TabBegriffe.getS(iPos,"kennung"),iBegriff,"DRUCK-HIERARCHIE");
          Vector<String> VecVisible = new Vector<String>();
          VecVisible.addElement(g.TabBegriffe.getS(iPos,"kennung"));

          TabBegriff.addInhalt("AIC",iBegriff);
          TabBegriff.addInhalt("Node",new JCOutlinerFolderNode((Object)VecVisible,(JCOutlinerFolderNode)OutDruck.getRootNode()));
          TabBegriff.addInhalt("Start",null);
          TabBegriff.addInhalt("Art","Druck");
        }
        if (VecAbfStamm != null)
         for(int i=0;i<VecAbfStamm.size();i++)
         {
           Integer iBegriff = VecAbfStamm.elementAt(i);
           int iPos = g.TabBegriffe.getPos("AIC", iBegriff);
           Vector<String> VecVisible = new Vector<String>();
           VecVisible.addElement(g.TabBegriffe.getS(iPos, "kennung"));
           JCOutlinerFolderNode Nod=new JCOutlinerFolderNode((Object)VecVisible, (JCOutlinerFolderNode)OutStamm.getRootNode());
           Nod.setUserData(iBegriff);
         }
        VecHS=null;
	//Applikation ermitteln
        if (bNormal)
        {
          checkMB(VecApplication, "Applikation");
          SQL.addVector(VecApplication, "select aic_begriff,b.kennung from begriff b where " + sDaten + " and b.aic_begriffgruppe=" + g.TabBegriffgruppen.getAic("Applikation"), g);
          g.progInfo("VecApplication=" + VecApplication);
          if (VecModule.size()>0)
            VecBew=SQL.getVector("select aic_fremd from modul where aic_tabellenname="+g.TabTabellenname.getAic("BEWEGUNGSTYP")+" and "+g.in("aic_begriff",VecModule),g);
          if (VecApplication.size()>0 || VecModule.size()>0)
          {
            if (VecModule.size()>0)
            {
              VecHS = SQL.getVector("select aic_fremd from modul where aic_tabellenname=" + g.TabTabellenname.getAic("HAUPTSCHIRM") + " and " + g.in("aic_begriff", VecModule), g);
              SQL.addVector(VecHS,"select h.aic_hauptschirm from begriff_zuordnung z join hauptschirm h on z.beg_aic_begriff=h.selbst where h.kennung is not null and" + g.in("z.aic_begriff", VecModule),g);
            }
            else
              VecHS=SQL.getVector("select aic_hauptschirm from hauptschirm where aic_benutzer is null and kennung is not null and kennung<>'*' and "+g.in("aic_begriff",VecApplication),g);
          }
          g.fixtestError("VecHS="+VecHS);
          if (VecHS != null)
          {
            SQL.addVector(VecAbfrage,"select distinct abfrage.aic_begriff from hauptschirm join abfrage on hauptschirm.aic_abfrage=abfrage.aic_abfrage where " + g.in("aic_hauptschirm", VecHS),g);
            SQL.addVector(VecAbfrage,"select distinct abfrage.aic_begriff from ansicht join abfrage on ansicht.aic_abfrage=abfrage.aic_abfrage where " + g.in("aic_hauptschirm", VecHS),g);
          }
          for (int i = 0; i < VecApplication.size(); i++)
          {
            Integer iBegriff = (Integer)VecApplication.elementAt(i);
            int iPos=g.TabBegriffe.getPos("AIC", iBegriff);
            checkKennung(g.TabBegriffe.getS(iPos,"kennung"), iBegriff, "APPLIKATION-HIERARCHIE");
            Vector VecVisible = new Vector();
            VecVisible.addElement(g.TabBegriffe.getS(iPos,"kennung"));

            TabBegriff.addInhalt("AIC", iBegriff);
            TabBegriff.addInhalt("Node", new JCOutlinerFolderNode((Object)VecVisible, (JCOutlinerFolderNode)OutApplication.getRootNode()));
            TabBegriff.addInhalt("Start", null);
            TabBegriff.addInhalt("Art", "Druck"); //??? Applikation
          }
        }

	//Formular Hierarchie erstellen
	// Vec.removeAllElements();
        checkMB(VecForm,"Frame");
        if(bNormal)
          SQL.addVector(VecForm,"select aic_begriff from begriff"+g.join("code","begriff")+" where code.kennung='System'", g);
        SQL.addVector(VecForm,"select aic_begriff from begriff b join begriffgruppe bg on b.aic_begriffgruppe=bg.aic_begriffgruppe where "+sDaten+" and bg.kennung='Frame' order by bg.kennung",g);
        Tabellenspeicher Tab=new Tabellenspeicher(g,"select b1.aic_begriff von,b2.aic_begriff nach from begriff b1 join formular f on b1.aic_begriff=f.aic_begriff join darstellung2 d on f.aic_formular=d.aic_formular join begriff b2 on d.aic_begriff=b2.aic_begriff join begriffgruppe bg on b2.aic_begriffgruppe=bg.aic_begriffgruppe where bg.kennung='Frame' order by von",true);
        for(int i=0;i<VecForm.size();i++)
        {
          Integer iBegriff = VecForm.elementAt(i);
            if(!TabBegriff.posInhalt("AIC",iBegriff))
            {
                    Vec.removeAllElements();
                    iStart = iBegriff;
                    insert(Tab,(JCOutlinerFolderNode)OutFormular.getRootNode(),iBegriff.intValue(),"Frame");
            }

        }
        for(int i=0;i<VecForm.size();i++)
        {
          if(TabBegriff.posInhalt("AIC",VecForm.elementAt(i)))
            ((Vector)((JCOutlinerFolderNode)TabBegriff.getInhalt("Node")).getLabel()).setElementAt(Boolean.TRUE,1);
        }

		/*for(Qry.moveFirst();!Qry.eof();Qry.moveNext())
		{
			if(TabBegriff.posInhalt("AIC",Qry.getI("AIC_Begriff")))
				((Vector)((JCOutlinerFolderNode)TabBegriff.getInhalt("Node")).getLabel()).setElementAt(Boolean.TRUE,1);
		}
		Qry.close();

                if(!bSchnell) {
                  Vector Vec2 = SQL.getVector("select aic_begriff from begriff join code where code.kennung='System'", g);
                  for(int i = 0; i < Vec2.size(); i++)
                  {
                    int iBegriff = ((Integer)Vec2.elementAt(i)).intValue();
                    if(!TabBegriff.posInhalt("AIC", iBegriff))
                    {
                      Vec.removeAllElements();
                      iStart = new Integer(iBegriff);
                      insert(Tab, (JCOutlinerFolderNode)OutFormular.getRootNode(), iBegriff, "Frame");
                    }

                  }
                }*/
        VecFormular = new Vector<Integer>(TabBegriff.getVecSpalteI("AIC"));

        /*

        // ermittelt Applikationen des Startformulars
	if(VecFormular.size()>0)
		SQL.addVector(VecApplication,"select distinct b.aic_begriff from formular f join darstellung d on f.aic_formular=d.aic_formular join begriff b on d.aic_begriff=b.aic_begriff join begriffgruppe bg on b.aic_begriffgruppe=bg.aic_begriffgruppe where bg.kennung='Applikation' and f.aic_begriff "+Static.SQL_in(VecFormular),g);

        // ermittelt Formulare der Applikationen
	if(VecApplication.size()>0)
	{
		SQL.addVector(VecFormular,"select z.aic_begriff from begriffgruppe g join begriff b on g.aic_begriffgruppe=b.aic_begriffgruppe"+
			" join begriff_zuordnung z on b.aic_begriff=z.aic_begriff where g.kennung='Frame' and z.beg_aic_begriff "+Static.SQL_in(VecApplication),g);

		Tabellenspeicher Tab=new Tabellenspeicher(g,"select b1.aic_begriff von,b2.aic_begriff nach from begriff b1 join formular f on b1.aic_begriff=f.aic_begriff join darstellung d on f.aic_formular=d.aic_formular join begriff b2 on d.aic_begriff=b2.aic_begriff join begriffgruppe bg on b2.aic_begriffgruppe=bg.aic_begriffgruppe where bg.kennung='Frame' order by von",true);
		for(int i=0;i<VecFormular.size();i++)
		{
			if(!TabBegriff.posInhalt("AIC",(Integer)VecFormular.elementAt(i)))
			{
				Vec.removeAllElements();
				iStart = (Integer)VecFormular.elementAt(i);
				insert(Tab,(JCOutlinerFolderNode)OutFormular.getRootNode(),((Integer)VecFormular.elementAt(i)).intValue(),"Frame");
			}

		}
		VecFormular = new Vector(TabBegriff.getVecSpalte("AIC"));
	}
        */

	if(VecFormular.size()>0)
		SQL.addVector(VecPlanung,"select distinct b.aic_begriff from formular f join darstellung2 d on f.aic_formular=d.aic_formular join begriff b on d.aic_begriff=b.aic_begriff join planung p on b.aic_begriff=p.aic_begriff where "+g.in("f.aic_begriff",VecFormular),g);
        if(VecFormular.size()>0)
              SQL.addVector(VecMenge,"select distinct b.aic_begriff from formular f join darstellung2 d on f.aic_formular=d.aic_formular join begriff b on d.aic_begriff=b.aic_begriff join begriffgruppe g on b.aic_begriffgruppe=g.aic_begriffgruppe where g.kennung='Formularmenge' and "+g.in("f.aic_begriff",VecFormular),g);

        if(VecPlanung.size()>0)
	{
		SQL.addVector(VecAbfrage,"select distinct abfrage.aic_begriff from planung,abfrage where planung.aic_abfrage=abfrage.aic_abfrage and "+g.in("planung.aic_begriff",VecPlanung),g);
		SQL.addVector(VecAbfrage,"select distinct abfrage.aic_begriff from planung,abfrage where planung.abf_aic_abfrage=abfrage.aic_abfrage and "+g.in("planung.aic_begriff",VecPlanung),g);
                SQL.addVector(VecAbfrage,"select distinct abfrage.aic_begriff from planung,abfrage where planung.abf2_aic_abfrage=abfrage.aic_abfrage and "+g.in("planung.aic_begriff",VecPlanung),g);
	}

	//Modell Hierarchie erstellen
	int iAIC_Modell_Begriffgruppe = g.TabBegriffgruppen.getAic("Modell");
	int iAIC_Abfrage_Begriffgruppe = g.TabBegriffgruppen.getAic("Abfrage");
        checkMB(VecModell,"Modell");
        VecMD=VecModell.size()>0 ? SQL.getSVector("select Kennung from Begriff where"+g.in("aic_begriff",VecModell)+" and "+g.bit("bits",Global.cstDefImport),g):null;
        checkMB(VecAbfrage,"Abfrage");
	SQL.addVector(VecModell,"select aic_begriff from begriff b where "+sDaten+" and b.aic_begriffgruppe="+iAIC_Modell_Begriffgruppe,g);
	int iModellSize = VecModell.size();
	if(VecFormular.size()>0)// && !bNurModul)  // Modelle in Formularen
		SQL.addVector(VecModell,"select distinct b.aic_begriff from formular f join darstellung2 d on f.aic_formular=d.aic_formular join begriff b on d.aic_begriff=b.aic_begriff where b.aic_begriffgruppe="+iAIC_Modell_Begriffgruppe+" and "+g.in("f.aic_begriff",VecFormular),g);
        //if (!bNurModul)
        //{
          SQL.addVector(VecModell,"select distinct m.aic_begriff from formular f join darstellung2 d on f.aic_formular=d.aic_formular join begriff b on d.aic_begriff=b.aic_begriff join abfrage a on b.aic_begriff=a.aic_begriff join modell m on a.aic_modell=m.aic_modell where b.aic_begriffgruppe="+iAIC_Abfrage_Begriffgruppe+" and "+g.in("f.aic_begriff",VecFormular),g);
          SQL.addVector(VecModell,"select distinct m.aic_begriff from begriff b join abfrage a on b.aic_begriff=a.aic_begriff join modell m on a.aic_modell=m.aic_modell or a.mod_aic_modell=m.aic_modell where b."+sDaten+" and b.aic_begriffgruppe="+iAIC_Abfrage_Begriffgruppe,g);
          SQL.addVector(VecModell,"select distinct m.aic_begriff from formular f join modell m on f.aic_modell=m.aic_modell and "+g.in("f.aic_begriff",VecFormular),g);
        //}
	SQL.addVector(VecAbfrage,"select aic_begriff from begriff b where "+sDaten+" and b.aic_begriffgruppe="+iAIC_Abfrage_Begriffgruppe,g);
	int iAbfrageSize = VecAbfrage.size();
	if(VecFormular.size()>0)
        {
          SQL.addVector(VecAbfrage,"select distinct a.aic_begriff from formular f join abfrage a on f.aic_abfrage=a.aic_abfrage where " + g.in("f.aic_begriff", VecFormular), g);
          SQL.addVector(VecAbfrage,"select distinct b.aic_begriff from formular f join darstellung2 d on f.aic_formular=d.aic_formular join begriff b on d.aic_begriff=b.aic_begriff"+
                        " where b.aic_begriffgruppe=" + iAIC_Abfrage_Begriffgruppe + " and " + g.in("f.aic_begriff", VecFormular), g);
        }
	int iOldModellSize =0;
	int iOldAbfrageSize =0;
	do
	{
		iOldModellSize = VecModell.size();
		iOldAbfrageSize = VecAbfrage.size();
		if(VecModell.size()>0) 
		{	// Abfragen laut Spalten bei Modellen
			SQL.addVector(VecAbfrage,"select distinct a.aic_begriff from modell m,befehl2 b join spalte s on b.aic_spalte=s.aic_spalte join abfrage a on s.aic_abfrage=a.aic_abfrage where m.aic_modell=b.aic_modell and "+g.in("m.aic_begriff",VecModell),g);
			SQL.addVector(VecAbfrage,"select distinct a.aic_begriff from abfrage a join modell m on a.aic_abfrage=m.aic_abfrage where "+g.in("m.aic_begriff",VecModell),g); // Abfrage des Modells
			SQL.addVector(VecAbfrage,"select distinct a.aic_begriff from abfrage a join modell m on a.aic_abfrage=m.Farbe where "+g.in("m.aic_begriff",VecModell),g); // Farb-Abfrage des Modells
		}
		if(VecAbfrage.size()>0) // Modelle und Filter der Abfragen
        {
          SQL.addVector(VecModell,"select distinct m.aic_begriff from abfrage a join modell m on a.aic_modell=m.aic_modell or a.mod_aic_modell=m.aic_modell where "+g.in("a.aic_begriff",VecAbfrage),g);
          SQL.addVector(VecAbfrage,"select distinct a2.aic_begriff from abfrage a1 join spalte s on a1.aic_abfrage=s.aic_abfrage,abfrage a2 where s.filter=a2.aic_abfrage and "+g.in("a1.aic_begriff",VecAbfrage),g);
          Tabellenspeicher TabNeu=new Tabellenspeicher(g,"select s.beg_aic_begriff,s.Abfrage_Begriff,s.Modell_Begriff from abfrage a join spalte s on a.aic_abfrage=s.aic_abfrage where "+g.in("a.aic_begriff",VecAbfrage),true);
          Vector<Integer> VecNeu = new Vector<Integer>();
          Static.addVectorI(VecNeu,TabNeu.getVecSpalteI("beg_aic_begriff"));
          Static.addVectorI(VecNeu,TabNeu.getVecSpalteI("Abfrage_Begriff"));
          Static.addVectorI(VecNeu,TabNeu.getVecSpalteI("Modell_Begriff"));
          
          Vector<Integer> VecNAbf=SQL.getVector("select aic_begriff from begriff where aic_begriffgruppe="+iAIC_Abfrage_Begriffgruppe+" and "+g.in("aic_begriff",VecNeu), g);
          Vector<Integer> VecNMod=SQL.getVector("select aic_begriff from begriff where aic_begriffgruppe="+iAIC_Modell_Begriffgruppe+" and "+g.in("aic_begriff",VecNeu), g);
          Vector<Integer> VecNFom=SQL.getVector("select aic_begriff from begriff where aic_begriffgruppe="+g.TabBegriffgruppen.getAic("Frame")+" and "+g.in("aic_begriff",VecNeu), g);
//          g.fixtestError("VecNeu="+VecNeu+", Abf="+VecNAbf+", Mod="+VecNMod+", Fom="+VecNFom);
          if (VecNAbf.size()>0) Static.addVectorI(VecAbfrage,VecNAbf);
          if (VecNMod.size()>0) Static.addVectorI(VecModell,VecNMod);
          if (VecNFom.size()>0) Static.addVectorI(VecFormular,VecNFom);
        }
	}
	while(iOldModellSize<VecModell.size() || iOldAbfrageSize<VecAbfrage.size());

        Tab=new Tabellenspeicher(g,"select distinct b1.aic_begriff von,b2.aic_begriff nach from begriff b1 join modell m1 on b1.aic_begriff=m1.aic_begriff,befehl2,modell m2 join begriff b2 on m2.aic_begriff=b2.aic_begriff where m1.aic_modell=befehl2.aic_modell and m2.aic_modell=befehl2.mod_aic_modell order by von",true);
	for(int i=0;i<VecModell.size();i++)
	{
		if(!TabBegriff.posInhalt("AIC",VecModell.elementAt(i)))
		{
			Vec.removeAllElements();
			iStart = (Integer)VecModell.elementAt(i);
			insert(Tab,(JCOutlinerFolderNode)OutModell.getRootNode(),iStart.intValue(),"Modell");
		}

	}

	for(int i=0;i<iModellSize;i++)
	{
		if(TabBegriff.posInhalt("AIC",VecModell.elementAt(i)))
			((Vector)((JCOutlinerFolderNode)TabBegriff.getInhalt("Node")).getLabel()).setElementAt(Boolean.TRUE,1);
	}

	for(TabBegriff.posInhalt("Art","Modell");!TabBegriff.out();TabBegriff.moveNext())
		if(!VecModell.contains(TabBegriff.getInt("AIC")))
			VecModell.addElement(TabBegriff.getInt("AIC"));

	if(VecModell.size()>0) // Abfragen aus Spalten der Befehle
		SQL.addVector(VecAbfrage,"select distinct a.aic_begriff from modell m,befehl2 b join spalte s on b.aic_spalte=s.aic_spalte join abfrage a on s.aic_abfrage=a.aic_abfrage where m.aic_modell=b.aic_modell and "+g.in("m.aic_begriff",VecModell),g);
		

	//SQL.addVector(VecModul,"select aic_begriff,b.kennung from versionsupdate vu,begriff b where ((vu.bits&"+cstNur+")<1 or vu.bits is null) and aic_version="+iAIC_Version+" and aic_begriff=aic_fremd and b.aic_begriffgruppe="+iAIC_Modul_Begriffgruppe,g);


	// 1.)
	g.debugInfo("1.)");
	VecBegriff = new Vector<Integer>((Vector)TabBegriff.getVecSpalte("AIC"));

	Static.addVectorI(VecBegriff,VecModule);
        //Modul//
        if (bNormal)
          SQL.addVector(VecModul,"select aic_begriff from begriff b where b.aic_begriffgruppe="+g.TabBegriffgruppen.getAic("Modul"),g);


	// fügt zu VecBegriff: Message, Button, Checkbox und Show dazu
        if (bNormal)
          SQL.addVector(VecBegriff,"select aic_begriff from begriff where aic_begriffgruppe in("+g.TabBegriffgruppen.getAic("Message")+","+g.TabBegriffgruppen.getAic("Button")+","+g.TabBegriffgruppen.getAic("Web")+
          		","+g.TabBegriffgruppen.getAic("Checkbox")+","+g.TabBegriffgruppen.getAic("Radiobutton")+","+g.TabBegriffgruppen.getAic("Show")+","+g.TabBegriffgruppen.getAic("Dialog")+","+g.TabBegriffgruppen.getAic("Titel")+(bWeb ? ","+g.TabBegriffgruppen.getAic("Aufgabe"):"")+
          		","+g.TabBegriffgruppen.getAic("Label")+","+g.TabBegriffgruppen.getAic("Format")+","+g.TabBegriffgruppen.getAic("Vergleich")+","+g.TabBegriffgruppen.getAic("Fehlermeldung")+","+g.TabBegriffgruppen.getAic("Grafik")/*+","+g.TabBegriffgruppen.getAic("PaneFX")*/+")",g);
	//SQL.addVector(VecBegriff,"select aic_begriff from versionsupdate vu,begriff b where ((vu.bits&"+cstNur+")<1 or vu.bits is null) and aic_begriff=aic_fremd and aic_version="+iAIC_Version,g);

	//if(VecModell.size()>0)	// 21.11.2002 ausgeklammert von K2
	//	SQL.addVector(VecBegriff,"select aic_begriff from begriff_zuordnung where beg_aic_begriff "+Static.SQL_in(VecModell),g);

	//if(VecFormular.size()>0)
		//SQL.addVector(VecBegriff,"select beg_aic_begriff from begriff_zuordnung where aic_begriff "+Static.SQL_in(VecFormular),g);

	g.progInfo("VecFormular="+VecFormular);
	g.progInfo("VecModell="+VecModell);
	g.progInfo("VecAbfrage="+VecAbfrage);
	//g.progInfo("VecDruck="+VecDruck);
	g.progInfo("VecBegriff="+VecBegriff);
	//if (true) return;
	/*
	for(TabBegriff.moveFirst();!TabBegriff.eof();TabBegriff.moveNext())
	{
		if(TabBegriff.getS("Art").equals("Frame"))
			VecFormular.addElement(TabBegriff.getInt("AIC"));
		else if(TabBegriff.getS("Art").equals("Modell"))
			VecModell.addElement(TabBegriff.getInt("AIC"));
		else if(TabBegriff.getS("Art").equals("Abfrage"))
			VecAbfrage.addElement(TabBegriff.getInt("AIC"));
	}*/

	Tabellenspeicher TabB = new Tabellenspeicher(g,"select distinct b.aic_begriff,b.kennung,bg.kennung gruppe from formular f join darstellung2 d on f.aic_formular=d.aic_formular join begriff b on d.aic_begriff=b.aic_begriff join begriffgruppe bg on b.aic_begriffgruppe=bg.aic_begriffgruppe where "+g.in("f.aic_begriff",VecBegriff)+" order by gruppe",true);
	if(g.Debug())
		TabB.showGrid("TabB",Frm);
	Static.addVectorI(VecBegriff,TabB.getVecSpalteI("aic_begriff"));

	//for(TabB.posInhalt("gruppe","Applikation");!TabB.out() && !TabB.eof() && TabB.getS("gruppe").equals("Applikation");TabB.moveNext())
		//VecApplication.addElement(TabB.getInhalt("aic_begriff"));
	for(TabB.posInhalt("gruppe","Gruppe");!TabB.out() && !TabB.eof() && TabB.getS("gruppe").equals("Gruppe");TabB.moveNext())
		VecGruppe.addElement(TabB.getI("aic_begriff"));

	// 2.)
	g.debugInfo("2.)"+VecBegriff.size());
	if(VecBegriff.size()>0 && bNormal)
	{
		//g.testInfo("VecBegriff: "+VecBegriff);
		SQL.addVector(VecStt,"select distinct aic_stammtyp from begriff where "+g.in("aic_begriff",VecBegriff),g);
		//g.testInfo("VecStt: "+VecStt);
	}

	// 3.)
	g.debugInfo("3.)"+VecStt.size());
	if(VecBegriff.size()>0 && bNormal)
		SQL.addVector(VecBew,"select distinct aic_bewegungstyp from begriff where "+g.in("aic_begriff",VecBegriff),g);

	// 4.)
	g.debugInfo("4.)"+VecBew.size());
	if(VecApplication.size()>0 && bNormal)
		SQL.addVector(VecStt,"select distinct aic_stammtyp from applikation_zuordnung where "+g.in("aic_begriff",VecApplication),g);

	// 5.)
	g.debugInfo("5.)"+VecStt.size());
	if(VecGruppe.size()>0 && bNormal)
		SQL.addVector(VecEig,"select distinct aic_eigenschaft from gruppe_zuordnung where "+g.in("aic_begriff",VecGruppe),g);

        if(VecFormular.size()>0 && bNormal)
		SQL.addVector(VecEig,"select distinct aic_eigenschaft from formular where "+g.in("aic_begriff",VecFormular),g);

	long lTime=Static.get_ms();
	int iAnz=0;
	int iAnzAbf = 0;
	int iAnzEig = 0;
	int iAnzStt = 0;
	int iAnzBew = 0;
	if (bNormal)
	do
	{
		if(VecAbfrage.size()>iAnzAbf)
		{
			iAnzAbf=VecAbfrage.size();
			SQL.addVector(VecAbfrage,"select distinct a2.aic_begriff from abfrage a1 join spalte s on a1.aic_abfrage=s.aic_abfrage,abfrage a2 where s.filter=a2.aic_abfrage and "+g.in("a1.aic_begriff",VecAbfrage),g);
			SQL.addVector(VecEig,"select distinct aic_eigenschaft from fixeigenschaft f join bedingung b on f.aic_bedingung=b.aic_bedingung join abfrage a on b.aic_abfrage=a.aic_abfrage where "+g.in("a.aic_begriff",VecAbfrage),g);
			SQL.addVector(VecEig,"select distinct aic_eigenschaft from fixeigenschaft f join spalte s on f.aic_spalte=s.aic_spalte join abfrage a on s.aic_abfrage=a.aic_abfrage where "+g.in("a.aic_begriff",VecAbfrage),g);
			SQL.addVector(VecBew,"select distinct aic_bewegungstyp from begriff where "+g.in("aic_begriff",VecAbfrage),g);
			Static.addVectorI(VecBegriff,VecAbfrage);
			SQL.addVector(VecStt,"select distinct aic_stammtyp from begriff where "+g.in("aic_begriff",VecBegriff),g);
		}

		if(VecBew.size()>iAnzBew) // Eigenschaften der Bewegung
		{
			iAnzBew=VecBew.size();
			SQL.addVector(VecEig,"select distinct aic_eigenschaft from bew_zuordnung where aic_bewegungstyp "+Static.SQL_in(VecBew),g);
		}

		if(VecStt.size()>iAnzStt)// Eigenschaften der Stammtypen, übergeordnete Stammtypen, Eigneschaft für Hierarchie
		{
			iAnzStt=VecStt.size();
			SQL.addVector(VecEig,"select distinct aic_eigenschaft from stammtyp_zuordnung where aic_stammtyp "+Static.SQL_in(VecStt),g);
			SQL.addVector(VecStt,"select distinct sta_aic_stammtyp from stammtyp where sta_aic_stammtyp is not null and aic_stammtyp "+Static.SQL_in(VecStt),g);
			SQL.addVector(VecEig,"select distinct aic_eigenschaft from stammtyp where aic_stammtyp "+Static.SQL_in(VecStt),g);
		}

		if(VecEig.size()>iAnzEig)
		{
			iAnzEig=VecEig.size();
			SQL.addVector(VecEig,"select distinct eig_aic_eigenschaft from eigenschaft_zuordnung where "+g.in("aic_eigenschaft",VecEig),g);
			SQL.addVector(VecAbfrage,"select distinct a.aic_begriff from eigenschaft e join abfrage a on e.aic_abfrage=a.aic_abfrage where "+g.in("aic_eigenschaft",VecEig),g);
			SQL.addVector(VecEig,"select eig_aic_eigenschaft from eigenschaft where "+g.in("aic_eigenschaft",VecEig),g);
            SQL.addVector(VecStt,"select distinct aic_stammtyp from eigenschaft where "+g.in("aic_eigenschaft",VecEig),g);
            SQL.addVector(VecBew,"select distinct aic_bewegungstyp from eigenschaft where "+g.in("aic_eigenschaft",VecEig),g);
		}
	iAnz++;
	}
        while(VecAbfrage.size()>iAnzAbf||VecStt.size()>iAnzStt||VecEig.size()>iAnzEig||VecBew.size()>iAnzBew);
	else
	  Static.addVectorI(VecBegriff,VecAbfrage);
        g.progInfo("VecEig="+VecEig);
	g.progInfo("VecStt="+VecStt);
	g.progInfo("VecBew="+VecBew);
        g.testInfo("Schleife: "+iAnz+"x: "+(Static.get_ms()-lTime)+" ms");


        Tabellenspeicher TabLeer=new Tabellenspeicher(g,"select aic_begriff,defbezeichnung,begriff.kennung,bg.kennung Gruppe from begriff join begriffgruppe bg on begriff.aic_begriffgruppe=bg.aic_begriffgruppe"+
                                                      " where begriff.kennung is null and "+g.in("aic_begriff",VecBegriff),true);
        if (!TabLeer.isEmpty())
        {
          bOk = false;
          new Message(Message.WARNING_MESSAGE+Message.SHOW_TAB,Frm,g).showDialog("ohne_Kennung",TabLeer);
          //TabLeer.showGrid("ohne Kennung",Frm,true);
        }
	//Abfragen ermitteln

        //SQL Qry = new SQL(g);

	if(VecAbfrage.size()>0 && Qry.open("SELECT autorefresh,spalten,m.kennung Modell,a.aic_begriff,b.kennung,b.bits,abits2,Anzahl,b.web,b.prog FROM begriff m join modell on m.aic_begriff=modell.aic_begriff right outer join Abfrage a on modell.aic_modell=a.aic_modell join Begriff b on a.aic_begriff=b.aic_begriff WHERE "+g.in("b.aic_begriff",VecAbfrage)))
	{
		for(;!Qry.eof();Qry.moveNext())
		{
			checkKennung(Qry.getS("kennung"),Qry.getInt("aic_begriff"),"ABFRAGE-HIERARCHIE");
			Vector VecVisible = new Vector();
			VecVisible.addElement(Qry.getS("kennung"));
			VecVisible.addElement(Qry.getL("bits"));
			VecVisible.addElement(Qry.getL("Abits2"));
			VecVisible.addElement(Qry.getInt("autorefresh"));
			VecVisible.addElement(Qry.getInt("spalten"));
			VecVisible.addElement(Qry.getInt("Anzahl"));
			VecVisible.addElement(Boolean.FALSE);
			VecVisible.addElement(Qry.getB("web"));
			VecVisible.addElement(Qry.getI("Prog")>0 ? g.TabCodes.getBezeichnungS(Qry.getI("Prog")):Static.sLeer);

			JCOutlinerFolderNode Node=new JCOutlinerFolderNode((Object)VecVisible,(JCOutlinerFolderNode)OutAbfrage.getRootNode());
			JCOutlinerNodeStyle Sty=g.getStyle(null);
      	  	Sty.setForeground(Qry.getB("web") ? g.ColWeb:Color.BLACK);
      	  	Node.setStyle(Sty);
			TabBegriff.addInhalt("AIC",Qry.getInt("aic_begriff"));
			TabBegriff.addInhalt("Node",Node);
			TabBegriff.addInhalt("Start",null);
			TabBegriff.addInhalt("Art","Abfrage");
		}
		Qry.close();
	}

	// am 19.10. nur vorläufig ausgeblendet, da sonst Fehler
	for(int i=0;i<iAbfrageSize;i++)
		if(TabBegriff.posInhalt("AIC",VecAbfrage.elementAt(i)))
			((Vector)((JCOutlinerFolderNode)TabBegriff.getInhalt("Node")).getLabel()).setElementAt(Boolean.TRUE,6);

	OutFormular.folderChanged(OutFormular.getRootNode());
	OutModell.folderChanged(OutModell.getRootNode());
	OutAbfrage.folderChanged(OutAbfrage.getRootNode());
	OutDruck.folderChanged(OutDruck.getRootNode());
        OutApplication.folderChanged(OutApplication.getRootNode());
        OutStamm.folderChanged(OutStamm.getRootNode());
	if(g.Debug())
		TabBegriff.showGrid("TabBegriff",Frm);
}

private void Speichern(String sFile,String sVer,boolean bProg)
{
	SQL Qry = new SQL(g);
	iError=0;

	//Speichern in Zip-File
        if (bProg)
          sFile=(sFile.endsWith(File.separator)? sFile:sFile+File.separator)+sProg+"_"+sVer+
              (g.ApplPort() ? "":new java.text.SimpleDateFormat("_yyyy-MM-dd").format(new java.util.Date()))+".up";
        g.fixtestInfo("sFile="+sFile);
        AUZip Zip=null;
        try
        {
          Zip = new AUZip(sFile, false);

          Zip.putNextEntry(new ZipEntry("Version.up"));
          Zip.SaveInteger(new Integer(Version.getVer()));
          Zip.SaveInteger(new Integer(cstVersion));
          Zip.SaveBoolean(CbxTest.isSelected());
          Zip.SaveBoolean(bNormal);
          Zip.SaveBoolean(CbxClean.isSelected());
          Zip.SaveBoolean(CbxMC.isSelected());
          Zip.SaveString(sProg);
          Zip.SaveString(sVer);
          Zip.SaveString2(new DateWOD(g.getVon()).Format("yyyy-MM-dd"));
          Zip.SaveString2(new DateWOD(g.getBis()).Format("yyyy-MM-dd"));
          Zip.SaveBoolean(bWeb);
          Zip.SaveString2(g.getDB());
          Zip.closeEntry();
        }
        catch(Exception e)
        {
            new Message(Message.WARNING_MESSAGE, null, g).showDialog("DefExport-Filefehler");
            return;
        }
     if (!CbxNurStamm.isSelected())
     {
		Zip.putNextEntry(new ZipEntry("Formular.up"));
		Zip.SaveOutliner(OutFormular);
		Zip.closeEntry();
	
		Zip.putNextEntry(new ZipEntry("Modell.up"));
		Zip.SaveOutliner(OutModell);
		Zip.closeEntry();
	
		Zip.putNextEntry(new ZipEntry("Abfrage.up"));
		Zip.SaveOutliner(OutAbfrage);
		Zip.closeEntry();
	
		Zip.putNextEntry(new ZipEntry("Druck.up"));
		 Zip.SaveOutliner(OutDruck);
		Zip.closeEntry();
	
		//g.printInfo("Begriff");
		Zip.putNextEntry(new ZipEntry("Begriff.up"));
        if (CbxKunden.isSelected())
        {
          SQL.addVector(VecBegriff, "select aic_begriff from begriff where aic_begriffgruppe=" + g.TabBegriffgruppen.getAic("Kunde"), g);
          SQL.addVector(VecBegriff,"select aic_begriff from begriff where aic_begriffgruppe="+g.TabBegriffgruppen.getAic("Modul"),g);
        }
        
	if(VecBegriff.size()>0)
	{
	  Tabellenspeicher Tab=new Tabellenspeicher(g,"SELECT "+g.ifnull("c.aic_code","null","(select kennung from begriffgruppe where aic_begriffgruppe=c.aic_begriffgruppe)")+" code_bg,c.Kennung code,b.kennung begriff,DefBezeichnung,"+
			  "(select kennung from code where aic_code=prog) Prog2,"+
			  "hotkey,fixe_Sprache,jeder,combo,homepage,kennzeichen,alias,bildname,pos,tod,web,b.bits,bg.kennung begriffgruppe,(case when ein is null or ein<importzeit then importzeit else ein end) edit FROM Code c"+
			  " RIGHT OUTER JOIN Begriff b ON c.aic_code=b.aic_code LEFT OUTER JOIN Logging l ON b.AIC_Logging=l.AIC_Logging, Begriffgruppe bg"+
			  " WHERE b.aic_begriffgruppe=bg.aic_begriffgruppe AND "+g.in("b.AIC_Begriff",VecBegriff)+" ORDER BY bg.kennung",true);
	  if (g.Debug())
	    Tab.showGrid("Begriffe",Frm);
		String sVor = "";
		for(;!Tab.eof();Tab.moveNext())
		{
			if(!sVor.equals(Tab.getS("begriffgruppe")))
			{
				sVor=Tab.getS("begriffgruppe");
				Zip.SaveString("*!*");
				Zip.SaveString(sVor);
			}
			Zip.SaveString(Tab.getS("begriff"));
			Zip.SaveString(Tab.getS("code"));
			Zip.SaveString(Tab.getS("code_bg"));
			Zip.SaveBoolean(Tab.getB("fixe_Sprache"));
			Zip.SaveBoolean(Tab.getB("jeder"));
			Zip.SaveBoolean(Tab.getB("combo"));
            Zip.SaveBoolean(Tab.getB("tod"));
            Zip.SaveBoolean(Tab.getB("web"));
            Zip.SaveString(Tab.getS("prog2"));
			Zip.SaveLong(Tab.getL("bits"));
            Zip.SaveString(Tab.getS("DefBezeichnung"));
            Zip.SaveString(Tab.getS("Hotkey"));
            Zip.SaveString(Tab.getS("homepage"));
            Zip.SaveString(Tab.getS("Kennzeichen"));
            Zip.SaveString(Tab.getS("Alias"));
            Zip.SaveString(Tab.getS("Bildname"));
            Zip.SaveInteger(Tab.getI("Pos"));
			java.util.Date DEdit=(java.util.Date)Tab.getTimestamp("edit");
//			if (DEdit==null && sVor.equals("Abfrage"))
//				Static.printError("DefExport: Abfrage "+Tab.getS("DefBezeichnung")+" hat keine Änderungszeit");
			//DEdit=DEdit==null?new java.util.Date(100,0,1):DEdit;
			Zip.SaveString(DEdit==null?new DateWOD(2000,1,1).Format("yyyy-MM-dd HH:mm:ss"):new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(DEdit));
		}
		//Qry.close();
	}
	Zip.closeEntry();
  
	//g.printInfo("Nur_Begriff.up");
        /*
	SQL.addVector(VecNurBegriff,"select aic_fremd from versionsupdate vu join tabellenname t on vu.aic_tabellenname=t.aic_tabellenname where aic_version="+iAIC_Version+" and (bits&"+cstNur+")>0 and kennung='Begriff'",g);
	Zip.putNextEntry(new ZipEntry("Nur_Begriff.up"));
	if(VecNurBegriff.size()>0 && Qry.open("select s.kennung schrift, b.kennung begriff, bg.kennung begriffgruppe,DefBezeichnung,jeder,combo,fixe_sprache,bits from schrift s right outer join begriff b on s.aic_schrift=b.aic_schrift join begriffgruppe bg on b.aic_begriffgruppe=bg.aic_begriffgruppe WHERE b.AIC_Begriff "+Static.SQL_in(VecNurBegriff)+" ORDER BY bg.kennung"))
	{
		String sVor = "";
		for(;!Qry.eof();Qry.moveNext())
		{
			if(!sVor.equals(Qry.getS("begriffgruppe")))
			{
				sVor=Qry.getS("begriffgruppe");
				Zip.SaveString("*!*");
				Zip.SaveString(sVor);
			}
			Zip.SaveString(Qry.getS("begriff"));
			Zip.SaveBoolean(Qry.getB("fixe_Sprache"));
			Zip.SaveBoolean(Qry.getB("jeder"));
			Zip.SaveBoolean(Qry.getB("combo"));
			Zip.SaveInteger(Qry.getInt("bits"));
			Zip.SaveString(Qry.getS("schrift"));
                        Zip.SaveString(Qry.getS("DefBezeichnung"));
		}
		Qry.close();
	}
	//g.printInfo("Nur_Eigenschaft.up");
	SQL.addVector(VecNurEig,"select aic_fremd from versionsupdate vu join tabellenname t on vu.aic_tabellenname=t.aic_tabellenname where aic_version="+iAIC_Version+" and (bits&"+cstNur+")>0 and kennung='Eigenschaft'",g);
	Zip.putNextEntry(new ZipEntry("Nur_Eigenschaft.up"));
	if(VecNurEig.size()>0 && Qry.open("select e.kennung,b.kennung datentyp,feldlaenge,format,min,max,e.bits from eigenschaft e join begriff b on e.aic_begriff=b.aic_begriff WHERE AIC_Eigenschaft "+Static.SQL_in(VecNurEig)))
	{
		String sVor = "";
		for(;!Qry.eof();Qry.moveNext())
		{
			Zip.SaveString(Qry.getS("Kennung"));
			Zip.SaveInteger(Qry.getInt("Feldlaenge"));
			Zip.SaveString(Qry.getS("Format"));
			Zip.SaveInteger(Qry.getInt("min"));
			Zip.SaveInteger(Qry.getInt("max"));
			Zip.SaveString(Qry.getS("datentyp"));
			Zip.SaveInteger(Qry.getInt("bits"));
		}
		Qry.close();
	}
	//g.printInfo("Nur_Stammtyp.up");
	SQL.addVector(VecNurStt,"select aic_fremd from versionsupdate vu join tabellenname t on vu.aic_tabellenname=t.aic_tabellenname where aic_version="+iAIC_Version+" and (bits&"+cstNur+")>0 and kennung='Stammtyp'",g);
	Zip.putNextEntry(new ZipEntry("Nur_Stammtyp.up"));
	if(VecNurStt.size()>0 && Qry.open("select kennung,translate,ende,benutzer,einheit,lizenz,copy from stammtyp WHERE AIC_Stammtyp "+Static.SQL_in(VecNurStt)))
	{
		String sVor = "";
		for(;!Qry.eof();Qry.moveNext())
		{
			Zip.SaveString(Qry.getS("Kennung"));
			Zip.SaveBoolean(Qry.getB("translate"));
			Zip.SaveBoolean(Qry.getB("ende"));
			Zip.SaveBoolean(Qry.getB("Benutzer"));
			Zip.SaveBoolean(Qry.getB("Einheit"));
			Zip.SaveBoolean(Qry.getB("Lizenz"));
			Zip.SaveBoolean(Qry.getB("Copy"));
		}
		Qry.close();
	}*/

	//g.printInfo("Bewegungstyp");
	Zip.putNextEntry(new ZipEntry("Bewegungstyp.up"));
        String sSQL="SELECT KENNUNG,BEWBITS,Tod";
        for (int i=1;i<10;i++)
          sSQL+=",(select kennung from eigenschaft where aic_eigenschaft=aic_eig"+i+") ANR"+i;
        for (int i=2;i<4;i++)
          sSQL+=",(select kennung from eigenschaft where aic_eigenschaft=Datum_Eig"+i+") LDATE"+i;
        for (int i=1;i<3;i++)
          sSQL+=",(select kennung from eigenschaft where aic_eigenschaft=Bool_Eig"+i+") Bool"+i;
        sSQL+=",(select kennung from eigenschaft where aic_eigenschaft=VonBis_Eig) VB";
        sSQL+=" FROM BEWEGUNGSTYP WHERE AIC_Bewegungstyp "+Static.SQL_in(VecBew);
	if(VecBew.size()>0 && Qry.open(sSQL))
	{
		for(;!Qry.eof();Qry.moveNext())
		{
			Zip.SaveString(Qry.getS("Kennung"));
                        Zip.SaveInteger(Qry.getInt("BEWBITS"));
                        for (int i=1;i<10;i++)
                          Zip.SaveString(Qry.getS("ANR"+i));
                        for (int i=2;i<4;i++)
                          Zip.SaveString(Qry.getS("LDATE"+i));
                        for (int i=1;i<3;i++)
                          Zip.SaveString(Qry.getS("Bool"+i));
                        Zip.SaveString(Qry.getS("VB"));
                        Zip.SaveBoolean(Qry.getB("tod"));
		}
		Qry.close();
	}
	Zip.closeEntry();

	//g.printInfo("Stammtyp");
	Zip.putNextEntry(new ZipEntry("Stammtyp.up"));
	if(/*VecStt.size()>0 && */bNormal && Qry.open("SELECT Kennung,SttBits,Tod,(select kennung from eigenschaft where aic_eigenschaft=ANR_Eig) ANR,WebIcon,WebIconOffen,WebFarbe FROM Stammtyp"))// WHERE AIC_Stammtyp"+Static.SQL_in(VecStt)))
	{
		for(;!Qry.eof();Qry.moveNext())
		{
			Zip.SaveString(Qry.getS("Kennung"));
			Zip.SaveInteger(Qry.getInt("SttBits"));
            Zip.SaveString(Qry.getS("ANR"));
            Zip.SaveString(Qry.getS("WebIcon"));
            Zip.SaveString(Qry.getS("WebIconOffen"));
            Zip.SaveString(Qry.getS("WebFarbe"));
            Zip.SaveBoolean(Qry.getB("tod"));
		}
		Qry.close();
	}
	Zip.closeEntry();

        Zip.putNextEntry(new ZipEntry("Rolle.up"));
        if(VecStt.size()>0 || !bNormal)
        {
          Vector VecRolle=null;
          if (!bNormal)
            VecRolle=SQL.getVector("select distinct aic_rolle from begriff where "+g.in("aic_begriff",VecBegriff),g);
           Qry.open("SELECT * FROM Rolle"+(bNormal?"":" where "+g.in("aic_rolle",VecRolle)));
                for(;!Qry.eof();Qry.moveNext())
                {
                        Zip.SaveString(Qry.getS("Kennung"));
                        Zip.SaveBoolean(Qry.getB("tod"));
                }
                Qry.close();
        }
        Zip.closeEntry();

	//g.printInfo("Eigenschaft");
	Zip.putNextEntry(new ZipEntry("Eigenschaft.up"));
	if(bNormal && VecEig.size()>0 && Qry.open("SELECT e.Kennung,Feldlaenge,Format,min,max,e.bits,b.kennung begriff,e.Tod"+
                                                  ",(select kennung from begriff join abfrage on begriff.aic_begriff=abfrage.aic_begriff where aic_abfrage=e.aic_abfrage) abfrage"+
                                                  " FROM Eigenschaft e JOIN Begriff b on e.aic_begriff=b.aic_begriff WHERE "+g.in("e.AIC_Eigenschaft",VecEig)))
	{
		for(;!Qry.eof();Qry.moveNext())
		{
			Zip.SaveString(Qry.getS("Kennung"));
			Zip.SaveInteger(Qry.getInt("Feldlaenge"));
			Zip.SaveString(Qry.getS("Format"));
			Zip.SaveInteger(Qry.getInt("min"));
			Zip.SaveInteger(Qry.getInt("max"));
			Zip.SaveInteger(Qry.getInt("bits"));
			Zip.SaveString(Qry.getS("begriff"));
			Zip.SaveString(Qry.getS("Abfrage"));
                        Zip.SaveBoolean(Qry.getB("tod"));
		}
		Qry.close();
                Zip.closeEntry();
                Zip.putNextEntry(new ZipEntry("Auswahl.up"));
                Tabellenspeicher TabA=new Tabellenspeicher(g,"select nr,a.kennung,e.kennung Eig from auswahl a join eigenschaft e on a.aic_eigenschaft=e.aic_eigenschaft WHERE "+
                    g.in("a.AIC_Eigenschaft",VecEig)+" order by e.kennung,nr",true);
                for (TabA.moveFirst(); !TabA.out(); TabA.moveNext())
                {
                  Zip.SaveString(TabA.getS("Eig"));
                  Zip.SaveString(TabA.getS("Kennung"));
                  Zip.SaveInteger(TabA.getInt("Nr"));
                }
	}
	Zip.closeEntry();

	g.printInfo("Hauptschirm-Einstellungen");
        if (VecHS != null && VecHS.size()>0)
        {
          Zip.putNextEntry(new ZipEntry("Hauptschirm.up"));
          Tabellenspeicher TabHS=new Tabellenspeicher(g,"SELECT aic_hauptschirm,(select kennung from begriff where aic_begriff=hauptschirm.aic_begriff) Applikation,aic_begriff,Kennung,bits"+
                       ",(select kennung from begriff join abfrage on begriff.aic_begriff=abfrage.aic_begriff where aic_abfrage=hauptschirm.aic_abfrage) abfrage"+
                  	   ",(select defbezeichnung from begriff where selbst=aic_begriff) defbez"+
                  	   ",(select code.kennung from code join begriff on prog=code.aic_code where selbst=aic_begriff) prog"+
                       ",(select kennung from stammtyp where aic_stammtyp=hauptschirm.aic_stammtyp) Stt"+
                       " from hauptschirm where " + g.in("AIC_Hauptschirm", VecHS)+" order by aic_begriff",true);
          for (TabHS.moveFirst(); !TabHS.out(); TabHS.moveNext())
          {
            Zip.SaveString(TabHS.getS("Applikation"));
            Zip.SaveString(TabHS.getS("Kennung"));
            Zip.SaveString(TabHS.getS("defbez"));
            Zip.SaveString(TabHS.getS("prog"));
            Zip.SaveInteger(TabHS.getInt("bits"));
            Zip.SaveString(TabHS.getS("Abfrage"));
            Zip.SaveString(TabHS.isNull("Stt") ? "-":TabHS.getS("Stt"));
          }
          Zip.closeEntry();
          Tabellenspeicher Tab = new Tabellenspeicher(g,new String[]{"AIC","Node"});
          AUOutliner OutHS;
          for (TabHS.moveFirst(); !TabHS.out(); TabHS.moveNext())
          {
            Qry.open("select *,(select kennung from begriff join abfrage on begriff.aic_begriff=abfrage.aic_begriff where aic_abfrage=ansicht.aic_abfrage) abfrage from ansicht where aic_hauptschirm="+TabHS.getI("aic_hauptschirm")+" order by aic_ansicht");
            Tab.clearAll();
            OutHS = new AUOutliner();
            //OutHS.setColumnButtons(new String[]{"Abfrage","int1","int2","int3","int4"});
            //OutHS.setNumColumns(5);
            for(;!Qry.eof();Qry.moveNext())
            {
              Vector<String> VecVisible = new Vector<String>();
              VecVisible.addElement(Qry.getS("abfrage"));
              VecVisible.addElement(g.TabStammtypen.getKennung(Qry.getI("int1")));
              VecVisible.addElement(Qry.getI("int2")>0 ? "S"+g.TabStammtypen.getKennung(Qry.getI("int2")):Qry.getI("int4")<2 ? "R"+g.TabRollen.getKennung(-Qry.getI("int2")):"B"+g.TabErfassungstypen.getKennung(-Qry.getI("int2")));
              VecVisible.addElement(g.TabEigenschaften.getKennung(Qry.getI("int3")));
              VecVisible.addElement(Qry.getS("int4"));
              JCOutlinerFolderNode Node = null;
              if(Qry.getI("ans_aic_ansicht")==0 || !Tab.posInhalt("AIC",Qry.getI("ans_aic_ansicht")))
              {
                Node = new JCOutlinerFolderNode((Object)VecVisible);
                OutHS.setRootNode(Node);
              }
              else
                Node = new JCOutlinerFolderNode((Object)VecVisible,(JCOutlinerFolderNode)Tab.getInhalt("Node"));
              Tab.addInhalt("AIC",Qry.getInt("AIC_Ansicht"));
              Tab.addInhalt("Node",Node);
            }
            Qry.close();
            /*OutHS.folderChanged(OutHS.getRootNode());
            JFrame Frm = new JFrame(TabHS.getS("Kennung"));
            Frm.getContentPane().add(OutHS);
            Frm.pack();
            Frm.setVisible(true);*/
            Zip.putNextEntry(new ZipEntry("Hauptschirm_"+TabHS.getS("Kennung")+".up"));
            Zip.SaveOutliner(OutHS);
            Zip.closeEntry();
          }
        }
	//g.printInfo("Planung");
	Zip.putNextEntry(new ZipEntry("Planung.up"));
	if(VecPlanung.size()>0 && Qry.open("select kennung,(select kennung from begriff join abfrage on begriff.aic_begriff=abfrage.aic_begriff where aic_abfrage=planung.aic_abfrage) Abf1,(select kennung from begriff join abfrage on begriff.aic_begriff=abfrage.aic_begriff where aic_abfrage=abf_aic_abfrage) Abf2,(select kennung from begriff join abfrage on begriff.aic_begriff=abfrage.aic_begriff where aic_abfrage=abf2_aic_abfrage) Abf3,spaltenbreite from planung join begriff on planung.aic_begriff=begriff.aic_begriff where "+g.in("planung.aic_begriff",VecPlanung)))
	{
		for(;!Qry.eof();Qry.moveNext())
		{
			Zip.SaveString(Qry.getS("Kennung"));
			Zip.SaveString(Qry.getS("Abf1"));
			Zip.SaveString(Qry.getS("Abf2"));
                        Zip.SaveString(Qry.getS("Abf3"));
			Zip.SaveInteger(Qry.getInt("spaltenbreite"));
		}
		Qry.close();
	}
	Zip.closeEntry();
    if(VecFormular.size()>0)
    {
        SaveZuordnung(Zip, Qry, "Form2FXML", "Form", "FXML", null, false, false,"select b.kennung Form, FXML from Begriff b"+g.join("Formular","b","Begriff")+" where FXML is not null and "+g.in("b.aic_begriff",VecFormular)+" order by FXML");

        Zip.putNextEntry(new ZipEntry("Formular_Abfrage.up"));
        if(Qry.open("select b1.kennung Formular,b2.kennung Abfrage from begriff b1 join formular f on b1.aic_begriff=f.aic_begriff join abfrage a on f.aic_abfrage=a.aic_abfrage join begriff b2 on a.aic_begriff=b2.aic_begriff where "+g.in("b1.aic_begriff",VecFormular)))
        {
                for(;!Qry.eof();Qry.moveNext())
                {
                        Zip.SaveString(Qry.getS("Formular"));
                        Zip.SaveString(Qry.getS("Abfrage"));
                }
                Qry.close();
        }
        Zip.closeEntry();
        
        Zip.putNextEntry(new ZipEntry("Formular_Modell.up"));
        if(Qry.open("select b1.kennung Formular,b2.kennung Modell from begriff b1 join formular f on b1.aic_begriff=f.aic_begriff"+
                                            " join modell m on f.aic_modell=m.aic_modell join begriff b2 on m.aic_begriff=b2.aic_begriff where "+g.in("b1.aic_begriff",VecFormular)))
        {
                for(;!Qry.eof();Qry.moveNext())
                {
                        Zip.SaveString(Qry.getS("Formular"));
                        Zip.SaveString(Qry.getS("Modell"));
                }
                Qry.close();
        }
        Zip.closeEntry();
        int iCDF=g.getCodeAic("Zuordnungsart", "DetailForm");
        Zip.putNextEntry(new ZipEntry("Formular_Begriffe.up"));
        if(Qry.open("select b1.kennung Formular,b2.aic_begriff,b2.kennung Beg,z.Reihe from begriff b1 join Beg2_Z z on b1.aic_begriff=z.aic_begriff"+
        				" join begriff b2 on z.aic_fremd=b2.aic_begriff and aic_tabellenname="+g.iTabBegriff+" and z.Art="+iCDF+" where "+g.in("b1.aic_begriff",VecFormular)))
        {
            for(;!Qry.eof();Qry.moveNext())
            {
                    Zip.SaveString(Qry.getS("Formular"));
                    Zip.SaveString(g.getBG(Qry.getI("aic_begriff")));
                    Zip.SaveString(Qry.getS("Beg"));
                    Zip.SaveInteger(Qry.getInt("Reihe"));
            }
            Qry.close();
        }
        Zip.closeEntry();
        
        Zip.putNextEntry(new ZipEntry("Formular_Stt.up"));
        if(Qry.open("select b1.kennung Formular,stt.kennung Stt,z.Reihe from begriff b1 join Beg2_Z z on b1.aic_begriff=z.aic_begriff"+
        				" join Stammtyp stt on z.aic_fremd=stt.aic_stammtyp and aic_tabellenname="+g.iTabStt+" and z.Art="+iCDF+" where "+g.in("b1.aic_begriff",VecFormular)))
        {
            for(;!Qry.eof();Qry.moveNext())
            {
                    Zip.SaveString(Qry.getS("Formular"));
                    Zip.SaveString(Qry.getS("Stt"));
                    Zip.SaveInteger(Qry.getInt("Reihe"));
            }
            Qry.close();
        }
        Zip.closeEntry();
    }
        if(VecDruck.size()>0)
          SaveZuordnung(Zip,Qry,"Druck_Zuordnung2","begriff","druck",null,true,false,"select bm.kennung druck, b.kennung begriff, bgb.kennung begriffgruppe from begriffgruppe bgm"+
                        " join begriff bm on bgm.aic_begriffgruppe=bm.aic_begriffgruppe,begriff_zuordnung bz, begriff b join begriffgruppe bgb on b.aic_begriffgruppe=bgb.aic_begriffgruppe"+
                        " where bm.aic_begriff=bz.aic_begriff and b.aic_begriff=bz.beg_aic_begriff and bgm.kennung='Druck' and bm.aic_begriff "+Static.SQL_in(VecDruck)+" order by bm.kennung");

        /*Zip.putNextEntry(new ZipEntry("Druck_Modell.up"));
        if(VecDruck.size()>0 && Qry.open("select b1.kennung Druck,b2.kennung Modell from begriff b1 join begriff_zuordnung z on b1.aic_begriff=z.aic_begriff"+
                                            " join begriff b2 on z.beg_aic_begriff=b2.aic_begriff where "+g.in("b1.aic_begriff",VecDruck)))
        {
                for(;!Qry.eof();Qry.moveNext())
                {
                        Zip.SaveString(Qry.getS("Druck"));
                        Zip.SaveString(Qry.getS("Modell"));
                }
                Qry.close();
        }
        Zip.closeEntry();*/
	//g.printInfo("Sprache");
        if (VecSprache.size()>0)
        {
        	Vector<Integer> VecSp2=new Vector<Integer>(VecSprache);
//        	if (!VecSp2.contains(new Integer(2)))  // Engisch immer dazu exportieren (für JavaFX)
//        		VecSp2.addElement(new Integer(2));
          Zip.putNextEntry(new ZipEntry("Sprache.up"));
          if (Qry.open("SELECT aic_sprache,Kennung,iso639,(select kennung from sprache where aic_sprache=sp.spr_aic_sprache) K2 FROM Sprache sp where aic_sprache" + Static.SQL_in(VecSp2)))
          {
            for (; !Qry.eof(); Qry.moveNext())
            {
              Zip.SaveString(Qry.getS("Kennung"));
              Zip.SaveString(Qry.getS("iso639"));
              Zip.SaveString(Qry.isNull("k2") ? "-":Qry.getS("k2"));
              //VecSprache.addElement(Qry.getInt("aic_sprache"));
            }
            Qry.close();
          }
          Zip.closeEntry();
        }
        //g.printInfo("Land");
        /*Zip.putNextEntry(new ZipEntry("Land.up"));
        if(Qry.open("SELECT aic_land,Kennung,iso3166 FROM Land "))
        {
                for(;!Qry.eof();Qry.moveNext())
                {
                        Zip.SaveString(Qry.getS("Kennung"));
                        Zip.SaveString(Qry.getS("iso3166"));
                }
                Qry.close();
        }
        Zip.closeEntry();*/

	//g.printInfo("Farbe");
	Zip.putNextEntry(new ZipEntry("Farbe.up"));
	if(bNormal && Qry.open("SELECT Kennung,red,green,blue FROM Farbe"))// JOIN Begriff b on f.aic_farbe=b.aic_farbe WHERE b.aic_begriff "+Static.SQL_in(VecBegriff)))
	{
		for(;!Qry.eof();Qry.moveNext())
		{
			Zip.SaveString(Qry.getS("Kennung"));
			Zip.SaveInteger(Qry.getInt("red"));
			Zip.SaveInteger(Qry.getInt("green"));
			Zip.SaveInteger(Qry.getInt("blue"));
		}
		Qry.close();
	}
	Zip.closeEntry();

        //g.printInfo("abschlusstyp");
        Zip.putNextEntry(new ZipEntry("Abschlusstyp.up"));
        Vector<Integer> VecAbschlusstyp=new Vector<Integer>();
        if(bProg && bNormal && Qry.open("SELECT Kennung,aic_stammtyp,bits,aic_abschlusstyp FROM abschlusstyp where prog="+iProg))
        {
                for(;!Qry.eof();Qry.moveNext())
                {
                        Zip.SaveString(Qry.getS("Kennung"));
                        int iPosS=Qry.isNull("aic_stammtyp") ? -1:g.TabStammtypen.getPos("AIC",Qry.getI("aic_stammtyp"));
                        Zip.SaveString(iPosS<0 ? "":g.TabStammtypen.getS(iPosS,"Kennung"));
                        Zip.SaveInteger(Qry.getInt("bits"));
                        VecAbschlusstyp.addElement(Qry.getI("aic_abschlusstyp"));
                }
                Qry.close();
        }
        Zip.closeEntry();

        Zip.putNextEntry(new ZipEntry("Abschlussdef.up"));
        if(VecAbschlusstyp.size()>0 && Qry.open("select t.kennung,d.aic_bewegungstyp,d.aic_eigenschaft from abschlusstyp t join abschlussdefinition d on t.aic_abschlusstyp=d.aic_abschlusstyp where t.prog="+iProg))
        {
                for(;!Qry.eof();Qry.moveNext())
                {
                        Zip.SaveString(Qry.getS("Kennung"));
                        Zip.SaveString(g.TabErfassungstypen.getKennung(Qry.getI("aic_bewegungstyp")));
                        Zip.SaveString(g.TabEigenschaften.getKennung(Qry.getI("aic_eigenschaft")));
                }
                Qry.close();
        }
        Zip.closeEntry();
        
        if (bWeb)
        {
	      g.fixtestError("Status für Aufgabe");
	      Vector<Integer> VecAufgabe=SQL.getVector("select aic_begriff from begriff where aic_begriffgruppe="+g.TabBegriffgruppen.getAic("Aufgabe")+" and (prog="+iProg+" or prog is null)", g);
	      g.fixtestError("VecAufgabe="+VecAufgabe);
	      Vector<Integer> VecStatus=VecAufgabe.size()==0 ? null:SQL.getVector("select aic_status from status_zuordnung where aic_begriff"+Static.SQL_in(VecAufgabe), g);
	      g.fixtestError("VecStatus="+VecStatus);
	      if (VecStatus!=null && VecStatus.size()>0)
	      {
	        Zip.putNextEntry(new ZipEntry("Status.up"));
//	        Vector<Integer> VecStatus=new Vector<Integer>();
	        if(bNormal && Qry.open("SELECT Aic_Status,Kennung,bits,Bildname,(select kennung from code where aic_code=Status.aic_code) Dar"+
	        		",(select kennung from stammview2 where aic_stamm=status.aic_stamm) Stammkennung"+
	        		",(select stt.kennung from stammtyp stt join stammview2 v on stt.aic_stammtyp=v.aic_stammtyp where aic_stamm=status.aic_stamm) SttKennung"+
	        		",(select kennung from hauptschirm where aic_hauptschirm=status.hauptschirm) HS"+
	        		",(select kennung from begriff join abfrage on begriff.aic_begriff=abfrage.aic_begriff where aic_abfrage=Abfrage) filter FROM status where aic_status"+Static.SQL_in(VecStatus)))
	        {
	                for(;!Qry.eof();Qry.moveNext())
	                {
	                        Zip.SaveString(Qry.getS("Kennung"));
	                        Zip.SaveString(Qry.getS("Filter"));
	                        Zip.SaveString(Qry.getS("HS"));
	                        Zip.SaveString(Qry.getS("Dar"));
	                        Zip.SaveInteger(Qry.getI("bits"));
//	                        Zip.SaveInteger(Qry.getInt("Reihenfolge"));
	                        Zip.SaveString(Qry.getS("Bildname"));
	                        Zip.SaveString(Qry.getS("Stammkennung"));
	                        Zip.SaveString(Qry.getS("Sttkennung"));
//	                        VecStatus.addElement(Qry.getI("Aic_Status"));
	                }
	                Qry.close();
	        }
	        Zip.closeEntry();

        //Status-Zuordnung
	        Zip.putNextEntry(new ZipEntry("StatusZ.up"));
	        if(VecStatus.size()>0 && Qry.open("select s.kennung status,b.kennung beg,bg.kennung bg,z.pos from status s join status_zuordnung z on s.aic_status=z.aic_status"+
	        		" join begriff b on z.aic_begriff=b.aic_begriff join begriffgruppe bg on b.aic_begriffgruppe=bg.aic_begriffgruppe where s.aic_status "+Static.SQL_in(VecStatus)))
	        {
	                for(;!Qry.eof();Qry.moveNext())
	                {
	                        Zip.SaveString(Qry.getS("status"));
	                        Zip.SaveString(Qry.getS("beg"));
	                        Zip.SaveString(Qry.getS("bg"));
	                        Zip.SaveInteger(Qry.getI("pos"));
	                }
	                Qry.close();
	        }
	        Zip.closeEntry();
	        //Abschlusstyp-Zuordnung
	        Zip.putNextEntry(new ZipEntry("BAT.up"));
	        if(VecStatus.size()>0 && Qry.open("select a.kennung at,b.kennung beg,bg.kennung bg from abschlusstyp a join begriff b on a.aic_abschlusstyp=b.aic_abschlusstyp join begriffgruppe bg on b.aic_begriffgruppe=bg.aic_begriffgruppe"))
	        {
	                for(;!Qry.eof();Qry.moveNext())
	                {
	                        Zip.SaveString(Qry.getS("at"));
	                        Zip.SaveString(Qry.getS("beg"));
	                        Zip.SaveString(Qry.getS("bg"));
	                }
	                Qry.close();
	        }
	        Zip.closeEntry();
	      }
	    }
	//g.printInfo("Abschnitt");
        //Vector VecRaster=new Vector();
	Zip.putNextEntry(new ZipEntry("Abschnitt.up"));
	if(VecDruck.size()>0 && Qry.open("select kennung, bits,aic_raster, a.aic_abschnitt,(select kennung from begriff where aic_begriff=a.aic_begriff) begriff"+
                                         ",(select kennung from Raster where aic_raster=a.aic_raster) Raster,zahl,Kennzeichen"+
                                         " from druck_zuordnung dz join abschnitt a on dz.aic_abschnitt=a.aic_abschnitt where "+g.in("dz.aic_begriff",VecDruck)))
	{
		for(;!Qry.eof();Qry.moveNext())
		{
			VecAbschnitt.addElement(Qry.getInt("aic_abschnitt"));
			Zip.SaveString(Qry.getS("kennung"));
			Zip.SaveInteger(Qry.getInt("bits"));
			Zip.SaveString(Qry.getS("begriff"));
			Zip.SaveString(Qry.getS("Raster"));
                        Zip.SaveInteger(Qry.getInt("Zahl"));
                        Zip.SaveString(Qry.getS("Kennzeichen"));
                        //int iRaster=Qry.getI("aic_Raster");
                        //if (iRaster>0 && !VecRaster.contains(new Integer(iRaster)))
                        //  VecRaster.addElement(new Integer(iRaster));
		}
		Qry.close();
	}

        Zip.putNextEntry(new ZipEntry("Raster.up"));
        if(bNormal &&  Qry.open("select kennung, bits, (select kennung from schrift where aic_schrift=a.aic_schrift) schrift1"+
        ",(select kennung from schrift where aic_schrift=a.sch_aic_schrift) schrift2"+
        ",(select kennung from schrift where aic_schrift=a.sch2_aic_schrift) schrift3"+
        ",(select kennung from schrift where aic_schrift=a.sch3_aic_schrift) schrift4"+
        ",(select kennung from schrift where aic_schrift=a.sch4_aic_schrift) schrift5"+
        ",(select kennung from schrift where aic_schrift=a.sch5_aic_schrift) schrift6"+
        ",(select kennung from schrift where aic_schrift=a.sch6_aic_schrift) schrift7"+
        ",Tod from raster a"))// where aic_raster "+Static.SQL_in(VecRaster)))
        {
                for(;!Qry.eof();Qry.moveNext())
                {
                        Zip.SaveString(Qry.getS("kennung"));
                        Zip.SaveInteger(Qry.getInt("bits"));
                        Zip.SaveString(Qry.getS("schrift1"));
                        Zip.SaveString(Qry.getS("schrift2"));
                        Zip.SaveString(Qry.getS("schrift3"));
                        Zip.SaveString(Qry.getS("schrift4"));
                        Zip.SaveString(Qry.getS("schrift5"));
                        Zip.SaveString(Qry.getS("schrift6"));
                        Zip.SaveString(Qry.getS("schrift7"));
                        Zip.SaveBoolean(Qry.getB("tod"));
                }
                Qry.close();
        }

	//g.printInfo("Schrift");
	Zip.putNextEntry(new ZipEntry("Schrift.up"));
	if(bNormal && Qry.open("select distinct s.kennung schrift,sa.kennung schriftart,"+g.SIZE()+",bold,italic from schrift s join schriftart sa on s.aic_schriftart=sa.aic_schriftart"))// WHERE s.aic_schrift "+Static.SQL_in(VecSchrift)))
		{
			for(;!Qry.eof();Qry.moveNext())
			{
				Zip.SaveString(Qry.getS("schrift"));
				Zip.SaveString(Qry.getS("schriftart"));
				Zip.SaveInteger(Qry.getInt("size"));
				Zip.SaveBoolean(Qry.getB("bold"));
				Zip.SaveBoolean(Qry.getB("italic"));
			}
			Qry.close();
		}
	Zip.closeEntry();

	int iF=sFile.lastIndexOf(File.separator);
	sDirBild=sFile.substring(0,iF+1)+"Images";
	g.fixtestError("Pfad für Images: "+sDirBild);
	//SaveBildBezeichnungMemo(zos,Qry,"Daten_Bild","Filename",null);
	//SaveBildBezeichnungMemo(zos,Qry,"Bezeichnung","Bezeichnung",null);
	//SaveBildBezeichnungMemo(zos,Qry,"Daten_Memo","Memo","Titel");

	if(VecModul.size()>0)
		SaveZuordnung(Zip,Qry,"Modul_Zuordnung","begriff","modul",null,true,false,"select bm.kennung modul, b.kennung begriff, bgb.kennung begriffgruppe from begriffgruppe bgm join begriff bm on bgm.aic_begriffgruppe=bm.aic_begriffgruppe,begriff_zuordnung bz, begriff b join begriffgruppe bgb on b.aic_begriffgruppe=bgb.aic_begriffgruppe where bm.aic_begriff=bz.aic_begriff and b.aic_begriff=bz.beg_aic_begriff and bgm.kennung='Modul' and bm.aic_begriff "+Static.SQL_in(VecModul)+" order by bm.kennung");
        if (CbxKunden.isSelected())
          SaveZuordnung(Zip,Qry,"Kunde_Zuordnung","begriff","Kunde",null,true,false,"select bm.kennung Kunde, b.kennung begriff, bgb.kennung begriffgruppe from begriffgruppe bgm join begriff bm on bgm.aic_begriffgruppe=bm.aic_begriffgruppe,begriff_zuordnung bz, begriff b join begriffgruppe bgb on b.aic_begriffgruppe=bgb.aic_begriffgruppe where bm.aic_begriff=bz.aic_begriff and b.aic_begriff=bz.beg_aic_begriff and bgm.kennung='Kunde' order by bm.kennung");
        if(VecBew.size()>0)
		SaveZuordnung(Zip,Qry,"Bew_Zuordnung","bewegungstyp","eigenschaft","reihenfolge",false,false,"select b.kennung bewegungstyp, e.kennung eigenschaft, reihenfolge from bewegungstyp b join bew_zuordnung bz on b.aic_bewegungstyp=bz.aic_bewegungstyp join eigenschaft e on bz.aic_eigenschaft=e.aic_eigenschaft where b.aic_bewegungstyp "+Static.SQL_in(VecBew)+" and b.tod is null order by b.kennung,reihenfolge");
	if(VecStt.size()>0)
        {
		  SaveZuordnung(Zip,Qry,"Stt_Zuordnung","stammtyp","eigenschaft","reihenfolge",false,false,"select stt.kennung stammtyp, e.kennung eigenschaft, reihenfolge from Stammtyp stt join Stammtyp_Zuordnung sttz on stt.aic_stammtyp=sttz.aic_stammtyp join eigenschaft e on sttz.aic_eigenschaft=e.aic_eigenschaft where stt.aic_Stammtyp "+Static.SQL_in(VecStt)+" and stt.tod is null order by stt.kennung,reihenfolge");
          SaveZuordnung(Zip,Qry,"Rol_Zuordnung","Rolle","eigenschaft",null,false,false,"select stt.kennung Rolle, e.kennung eigenschaft from Rolle stt join Rolle_Zuordnung sttz on stt.aic_Rolle=sttz.aic_Rolle join eigenschaft e on sttz.aic_eigenschaft=e.aic_eigenschaft where stt.tod is null and stt.aic_Stammtyp "+Static.SQL_in(VecStt)+" and stt.tod is null order by stt.kennung");
        }
        if(bNormal && VecGruppe.size()>0)
		SaveZuordnung(Zip,Qry,"Gruppe_Zuordnung","begriff","eigenschaft","reihenfolge",false,false,"select b.kennung begriff, e.kennung eigenschaft, reihenfolge from Begriff b join Gruppe_Zuordnung gz on b.aic_begriff=gz.aic_begriff join eigenschaft e on gz.aic_eigenschaft=e.aic_eigenschaft where b.aic_Begriff "+Static.SQL_in(VecGruppe)+" order by b.kennung,reihenfolge");
	if(bNormal && VecApplication.size()>0)
		SaveZuordnung(Zip,Qry,"Appl_Zuordnung","begriff","stammtyp","reihenfolge",false,false,"select b.kennung begriff, stt.kennung stammtyp, reihenfolge from Begriff b join Applikation_Zuordnung az on b.aic_begriff=az.aic_begriff join stammtyp stt on az.aic_stammtyp=stt.aic_stammtyp where b.aic_Begriff "+Static.SQL_in(VecApplication)+" order by b.kennung,reihenfolge");
	if(bNormal && VecEig.size()>0)
	{
		SaveZuordnung(Zip,Qry,"Eig_Zuordnung","e1","e2","reihenfolge",false,false,"select v.kennung e1, b.kennung e2, reihenfolge from eigenschaft v, eigenschaft_zuordnung z, eigenschaft b where v.aic_eigenschaft=z.aic_eigenschaft and b.aic_eigenschaft=z.eig_aic_eigenschaft and "+g.in("v.aic_eigenschaft",VecEig)+" order by v.kennung,reihenfolge");
		if (iProg>0)
			SaveZuordnung(Zip,Qry,"Prog2Eig","prog","Eig","reihenfolge",false,false,"select e.kennung eig,code.kennung prog,reihenfolge from eigenschaft e join prog2_zuordnung z on e.aic_eigenschaft=z.aic_eigenschaft join code on z.aic_code=code.aic_code where z.aic_code="+iProg+" order by code.kennung,reihenfolge");
	}
	if (bNormal)
          SaveZuordnung(Zip,Qry,"Stt2Stt","stt2","stt1",null,false,false,"select st1.kennung stt1, st2.kennung stt2 from stammtyp st1, stammtyp st2 where st1.aic_stammtyp=st2.sta_aic_stammtyp"/*" and st2.aic_stammtyp "+Static.SQL_in(VecStt)*/+" order by st1.kennung");
    if(bNormal && VecStt.size()>0)
	{
		SaveZuordnung(Zip,Qry,"Stt2Eig","stammtyp","eigenschaft",null,false,false,"select stt.kennung stammtyp, e.kennung eigenschaft from stammtyp stt, eigenschaft e where stt.aic_eigenschaft=e.aic_eigenschaft and stt.aic_stammtyp "+Static.SQL_in(VecStt)+" order by e.kennung");
		SaveZuordnung(Zip,Qry,"Stt2Status","stammtyp","status",null,false,false,"select stt.kennung stammtyp, e.kennung status from stammtyp stt, status e where stt.aic_status=e.aic_status and stt.aic_stammtyp "+Static.SQL_in(VecStt)+" order by e.kennung");
		if (bWeb)
			SaveZuordnung(Zip,Qry,"Stt2Aufgabe","stammtyp","aufgabe",null,false,false,"select stt.kennung stammtyp, b.kennung aufgabe from stammtyp stt, begriff b where stt.aufgabe=b.aic_begriff and stt.aic_stammtyp "+Static.SQL_in(VecStt)+" order by b.kennung");
		
		SaveZuordnung(Zip,Qry,"Rol2Rol","stt2","stt1",null,false,false,"select st1.kennung stt1, st2.kennung stt2 from Rolle st1, Rolle st2 where st1.aic_Rolle=st2.Rol_aic_Rolle order by st1.kennung");
        SaveZuordnung(Zip,Qry,"Rol2Stt","Rol","stammtyp",null,false,false,"select e.kennung Rol, stt.kennung stammtyp from Rolle e join stammtyp stt on e.aic_stammtyp=stt.aic_stammtyp order by stt.kennung");
        if (iProg>0)
        	SaveZuordnung(Zip,Qry,"Prog2Stt","prog","Stt","reihenfolge",false,false,"select stt.kennung stt,code.kennung prog,reihenfolge from stammtyp stt join prog_stt_zuordnung z on stt.aic_stammtyp=z.aic_stammtyp join code on z.aic_code=code.aic_code where z.aic_code="+iProg+" order by code.kennung,reihenfolge");
	}
	if(bNormal && VecEig.size()>0)
	{
		SaveZuordnung(Zip,Qry,"Eig2Stt","eigenschaft","stammtyp",null,false,false,"select stt.kennung stammtyp, e.kennung eigenschaft from stammtyp stt, eigenschaft e where stt.aic_stammtyp=e.aic_stammtyp and "+g.in("e.aic_eigenschaft",VecEig)+" order by stt.kennung");
                SaveZuordnung(Zip,Qry,"Eig2Bew","eigenschaft","bewegungstyp",null,false,false,"select bew.kennung bewegungstyp, e.kennung eigenschaft from bewegungstyp bew, eigenschaft e where bew.aic_bewegungstyp=e.aic_bewegungstyp and "+g.in("e.aic_eigenschaft",VecEig)+" order by bew.kennung");
                SaveZuordnung(Zip,Qry,"Eig2Rolle","eigenschaft","Rolle",null,false,false,"select R.kennung Rolle, e.kennung eigenschaft from Rolle R, eigenschaft e where R.aic_Rolle=e.aic_Rolle and "+g.in("e.aic_eigenschaft",VecEig)+" order by R.kennung");
                SaveZuordnung(Zip,Qry,"Eig2Eig","eigenschaft","eig2",null,false,false,"select e2.kennung eig2, e.kennung eigenschaft from eigenschaft e2, eigenschaft e where e2.aic_eigenschaft=e.eig_aic_eigenschaft and "+g.in("e.aic_eigenschaft",VecEig)+" order by e2.kennung");
                SaveZuordnung(Zip,Qry,"Eig2Stamm","eigenschaft","stamm",null,true,false,"select e.kennung eigenschaft, st.kennung stamm, stt.kennung begriffgruppe from eigenschaft e join stammview st on e.aic_stamm=st.aic_stamm join stammtyp stt on st.aic_stammtyp=stt.aic_stammtyp where st.kennung is not null and "+g.in("e.aic_eigenschaft",VecEig)+" order by st.kennung");
	}
        if(VecBegriff.size()>0)
	{
		SaveZuordnung(Zip,Qry,"Beg2Bew","begriff","bewegungstyp",null,true,false,"select bg.kennung begriffgruppe,b.kennung begriff, bew.kennung bewegungstyp from begriffgruppe bg join begriff b on bg.aic_begriffgruppe=b.aic_begriffgruppe join bewegungstyp bew on b.aic_bewegungstyp=bew.aic_bewegungstyp where "+g.in("b.aic_begriff",VecBegriff)+" order by bew.kennung");
		//SaveZuordnung(Zip,Qry,"Beg2Hp","begriff","homepage",null,true,false,"select bg.kennung begriffgruppe,b.kennung begriff, h.kennung homepage from begriffgruppe bg join begriff b on bg.aic_begriffgruppe=b.aic_begriffgruppe join homepage h on b.aic_homepage=h.aic_homepage where "+g.in("b.aic_begriff",VecBegriff)+" order by h.kennung");
		SaveZuordnung(Zip,Qry,"Beg2Stt","begriff","stammtyp",null,true,false,"select bg.kennung begriffgruppe,b.kennung begriff, stt.kennung stammtyp from begriffgruppe bg join begriff b on bg.aic_begriffgruppe=b.aic_begriffgruppe join stammtyp stt on b.aic_stammtyp=stt.aic_stammtyp where "+g.in("b.aic_begriff",VecBegriff)+" order by stt.kennung");
		//SaveZuordnung(Zip,Qry,"Beg2Eig","begriff","eigenschaft",null,true,false,"select bg.kennung begriffgruppe,b.kennung begriff, e.kennung eigenschaft from begriffgruppe bg join begriff b on bg.aic_begriffgruppe=b.aic_begriffgruppe join eigenschaft e on b.aic_begriff=e.aic_begriff where "+g.in("b.aic_begriff",VecBegriff)+" order by e.kennung");
		if (bNormal)
                  SaveZuordnung(Zip,Qry,"Beg2Schrift","begriff","schrift",null,true,false,"select bg.kennung begriffgruppe,b.kennung begriff,s.kennung schrift from begriffgruppe bg join begriff b on bg.aic_begriffgruppe=b.aic_begriffgruppe join schrift s on b.aic_schrift=s.aic_schrift where "+g.in("b.aic_begriff",VecBegriff)+" order by s.kennung");
		SaveZuordnung(Zip,Qry,"Beg2Code","begriff","code",null,true,true,"select b.kennung begriff,c.kennung code,gbg.kennung begriffgruppe,cbg.kennung codegruppe from begriffgruppe gbg join begriff b on gbg.aic_begriffgruppe=b.aic_begriffgruppe join code c on b.aic_code=c.aic_code join begriffgruppe cbg on c.aic_begriffgruppe=cbg.aic_begriffgruppe where "+g.in("b.aic_begriff",VecBegriff)+" order by c.kennung ");
                SaveZuordnung(Zip,Qry,"Beg2Rolle","begriff","Rolle",null,true,false,"select bg.kennung begriffgruppe,b.kennung begriff, r.kennung Rolle from begriffgruppe bg join begriff b on bg.aic_begriffgruppe=b.aic_begriffgruppe join Rolle r on b.aic_rolle=r.aic_rolle where "+g.in("b.aic_begriff",VecBegriff)+" order by r.kennung");
	}
	//if(VecSchrift.size()>0)
        if (bNormal)
        {
          SaveZuordnung(Zip, Qry, "Schrift2Farbe", "schrift", "farbe", null, false, false,
                        "select s.kennung schrift, f.kennung farbe from schrift s join farbe f on s.aic_farbe=f.aic_farbe order by f.kennung");
          SaveZuordnung(Zip, Qry, "Schrift2Farbe2", "schrift", "farbe", null, false, false,
                        "select s.kennung schrift, f.kennung farbe from schrift s join farbe f on s.far_aic_farbe=f.aic_farbe order by f.kennung");
        }
	if(VecAbfrage.size()>0)
        {
          SaveZuordnung(Zip, Qry, "Abf2Mod", "abfrage", "modell", null, false, false,"select a.kennung abfrage,m.kennung modell from begriff a join abfrage on a.aic_begriff=abfrage.aic_begriff"+
                        " join modell on abfrage.aic_modell=modell.aic_modell join begriff m on modell.aic_begriff=m.aic_begriff where " +g.in("a.aic_begriff", VecAbfrage) + " order by m.kennung");
          SaveZuordnung(Zip, Qry, "Abf2Mod2", "abfrage", "modell", null, false, false,"select a.kennung abfrage,m.kennung modell from begriff a join abfrage on a.aic_begriff=abfrage.aic_begriff"+
                        " join modell on abfrage.mod_aic_modell=modell.aic_modell join begriff m on modell.aic_begriff=m.aic_begriff where " +g.in("a.aic_begriff", VecAbfrage) + " order by m.kennung");
          SaveZuordnung(Zip, Qry, "Abf2Stamm","abfrage","stammKennung",null,false,false,"select b.kennung abfrage,s.kennung stammKennung from begriff b join abfrage a on b.aic_begriff=a.aic_begriff"+
                        " join stammview2 s on s.aic_stamm=webstamm where" +g.in("b.aic_begriff", VecAbfrage) + " order by s.kennung");
        }
	if (VecModell.size()>0)
	{
        SaveZuordnung(Zip, Qry, "Mod2Abf", "abfrage", "modell", null, false, false,"select m.kennung modell,a.kennung abfrage from begriff a join abfrage on a.aic_begriff=abfrage.aic_begriff"+
                " join modell on Modell.aic_abfrage=Abfrage.aic_Abfrage join begriff m on modell.aic_begriff=m.aic_begriff where " +g.in("m.aic_begriff", VecModell) + " order by a.kennung");
        SaveZuordnung(Zip, Qry, "Mod2Abf2", "abfrage", "modell", null, false, false,"select m.kennung modell,a.kennung abfrage from begriff a join abfrage on a.aic_begriff=abfrage.aic_begriff"+
                " join modell on Modell.farbe=Abfrage.aic_Abfrage join begriff m on modell.aic_begriff=m.aic_begriff where " +g.in("m.aic_begriff", VecModell) + " order by a.kennung");
	}
	if(VecApplication.size()>0)
        {
		SaveZuordnung(Zip,Qry,"Beg_AIC_Beg_Zuordnung","zuord","gruppe","reihenfolge",true,true,
		"select b1.kennung zuord, g1.kennung codegruppe, b2.kennung gruppe, g2.kennung begriffgruppe, reihenfolge from begriffgruppe g1 join begriff b1 on g1.aic_begriffgruppe=b1.aic_begriffgruppe"+
		" join begriff_zuordnung z on b1.aic_begriff=z.aic_begriff join begriff b2 on b2.aic_begriff=z.beg_aic_begriff join begriffgruppe g2 on b2.aic_begriffgruppe=g2.aic_begriffgruppe"+
		" where g1.kennung='Frame' and b2.aic_begriff "+Static.SQL_in(VecApplication)+" order by b2.kennung");

                SaveZuordnung(Zip,Qry,"Appl2_Zuordnung","zuord","gruppe","reihenfolge",true,true,
		"select b1.kennung zuord, g1.kennung codegruppe, b2.kennung gruppe, g2.kennung begriffgruppe, reihenfolge from begriffgruppe g1 join begriff b2 on g1.aic_begriffgruppe=b2.aic_begriffgruppe"+
                " join begriff_zuordnung z on b2.aic_begriff=z.aic_begriff join begriff b1 on b1.aic_begriff=z.beg_aic_begriff join begriffgruppe g2 on b2.aic_begriffgruppe=g2.aic_begriffgruppe"+
                " where g1.kennung='Applikation' and b2.aic_begriff "+Static.SQL_in(VecApplication)+" order by b2.kennung,reihenfolge");
		//"select b1.kennung zuord, g1.kennung codegruppe, b2.kennung gruppe, g2.kennung begriffgruppe, reihenfolge from begriffgruppe g1 join begriff b1 on g1.aic_begriffgruppe=b1.aic_begriffgruppe, begriff_zuordnung z, begriff b2 join begriffgruppe g2 on b2.aic_begriffgruppe=g2.aic_begriffgruppe where b1.aic_begriff=z.aic_begriff and b2.aic_begriff=z.beg_aic_begriff and b2.aic_begriff "+Static.SQL_in(VecApplication)+" order by b2.kennung");
        }
	if(VecFormular.size()>0)
        {
          SaveZuordnung(Zip,Qry,"Form2Stamm","Form","stamm",null,true,false,"select b.kennung Form, st.kennung stamm, stt.kennung begriffgruppe from begriff b join formular e on b.aic_begriff=e.aic_begriff join stammview st on e.aic_stamm=st.aic_stamm join stammtyp stt on st.aic_stammtyp=stt.aic_stammtyp where st.kennung is not null and "+g.in("e.aic_begriff",VecFormular)+" order by st.kennung");

          SaveZuordnung(Zip, Qry, "Form2Eig", "Form", "Eig", null, false, false,"select b.kennung Form, e.kennung Eig from Begriff b"+g.join("Formular","b","Begriff")+g.join("Eigenschaft","e","Formular","Eigenschaft")+" where "+g.in("b.aic_begriff",VecFormular)+" order by e.kennung");
          
          SaveZuordnung(Zip,Qry,"Beg2AIC_Beg_Zuordnung","zuord","gruppe","reihenfolge",true,true,
			"select b1.aic_begriff,b1.kennung zuord, g1.kennung codegruppe, b2.kennung gruppe, g2.kennung begriffgruppe, reihenfolge"+
 			" from begriffgruppe g1 join begriff b1 on g1.aic_begriffgruppe=b1.aic_begriffgruppe, begriff_zuordnung z, begriff b2 join begriffgruppe g2 on b2.aic_begriffgruppe=g2.aic_begriffgruppe"+
 			" where b1.aic_begriff=z.aic_begriff and b2.aic_begriff=z.beg_aic_begriff and "+g.in("b1.aic_begriff",VecFormular)+
			(VecApplication.size()>0?" and b2.aic_begriff not"+Static.SQL_in(VecApplication):"")+" order by b1.kennung");
          if (VecMenge.size()>0)
          {
            SaveZuordnung(Zip,Qry,"Beg3AIC_Beg_Zuordnung","zuord","gruppe","reihenfolge",true,true,
            "select b1.kennung zuord, g1.kennung codegruppe, b2.kennung gruppe, g2.kennung begriffgruppe, reihenfolge from begriffgruppe g1 join begriff b1 on g1.aic_begriffgruppe=b1.aic_begriffgruppe"+
            " join begriff_zuordnung z on b1.aic_begriff=z.beg_aic_begriff join begriff b2 on b2.aic_begriff=z.aic_begriff join begriffgruppe g2 on b2.aic_begriffgruppe=g2.aic_begriffgruppe"+
            " where g1.kennung in ('Frame','Applikation','Modell','Druck') and b2.aic_begriff "+Static.SQL_in(VecMenge)+" order by b2.kennung");
          }
        }
	if(VecDruck.size()>0)
		SaveZuordnung(Zip,Qry,"Druck_Zuordnung","absch","beg","reihenfolge",false,false,"select b.kennung beg, a.kennung absch, reihenfolge from begriff b join druck_zuordnung dz on b.aic_begriff=dz.aic_begriff join abschnitt a on dz.aic_abschnitt=a.aic_abschnitt where b.aic_begriff "+Static.SQL_in(VecDruck)+" order by beg,reihenfolge");

	//g.printInfo("VecFormular: "+VecFormular);
	for(int i=0;i<VecFormular.size();i++)
	{
		int iBegAic=Sort.geti(VecFormular,i);
		if(Qry.open("select d.aic_darstellung,d.dar_aic_darstellung,bg.kennung begriffgruppe,b.kennung begriff,reihenfolge,x,y,w,h,Align,HGap,VGap,d.Pos,i1,i2 "+
					"from formular f join darstellung2 d on f.aic_formular=d.aic_formular left outer join begriff b on d.aic_begriff=b.aic_begriff left outer join begriffgruppe bg on b.aic_begriffgruppe=bg.aic_begriffgruppe "+
					"where f.aic_begriff="+iBegAic+" order by "+g.order("d.dar_aic_darstellung")+",reihenfolge"))
		{
			Tabellenspeicher Tab = new Tabellenspeicher(g,new String[]{"AIC","Node"});
			OutDarstellung = new AUOutliner();
			int iZeile0=0;
			for(;!Qry.eof();Qry.moveNext())
			{
				Vector<String> VecVisible = new Vector<String>();
				VecVisible.addElement(Qry.getS("begriff"));
				VecVisible.addElement(Qry.getS("begriffgruppe"));
				VecVisible.addElement(Qry.getS("reihenfolge"));
				VecVisible.addElement(Qry.getS("x"));
				VecVisible.addElement(Qry.getS("y"));
				VecVisible.addElement(Qry.getS("w"));
				VecVisible.addElement(Qry.getS("h"));
				VecVisible.addElement(g.TabCodes.getKennung(Qry.getI("Align")));
				VecVisible.addElement(Qry.getS("HGap"));
				VecVisible.addElement(Qry.getS("VGap"));
				VecVisible.addElement(Qry.getS("Pos"));
				VecVisible.addElement(Qry.getS("i1"));
				VecVisible.addElement(Qry.getS("i2"));

				JCOutlinerFolderNode Node = null;
				boolean bOk=true;
				if(Qry.getI("dar_aic_darstellung")==0)
				{
					Node = new JCOutlinerFolderNode((Object)VecVisible);
					OutDarstellung.setRootNode(Node);
					iZeile0=Qry.getInt("AIC_Darstellung");
				}
				else if (!Tab.posInhalt("AIC",Qry.getI("dar_aic_darstellung")))
				{
					bOk=false;
					Static.printError("Formular "+g.getBegBez(iBegAic)+": Zeile "+(Qry.getI("AIC_Darstellung")+1-iZeile0)+" liegt unter ausgeblendeter Zeile");//g.TabBegriffe.getS(iPos,"DefBezeichnung"));
				}
				else
					Node = new JCOutlinerFolderNode((Object)VecVisible,(JCOutlinerFolderNode)Tab.getInhalt("Node"));
				if (bOk)
				{
				  Tab.addInhalt("AIC",Qry.getInt("AIC_Darstellung"));
				  Tab.addInhalt("Node",Node);
				}
			}
			Qry.close();
            int iPos=OutDarstellung.getTree().length==0 ? -1:g.TabBegriffe.getPos("AIC",iBegAic);
			if(iPos>=0)
			{
				//g.printInfo("Frame_"+g.TabBegriffe.getS("Kennung"));
				Zip.putNextEntry(new ZipEntry("Frame_"+g.TabBegriffe.getS(iPos,"Kennung")+".up"));
				Zip.SaveOutliner(OutDarstellung);
				Zip.closeEntry();
			}
		}
	}

	//g.printInfo("VecModell: "+VecModell);
	for(int i=0;i<VecModell.size();i++)
	{
		int iAIC_Modell=0;
		String sPeriode="";
		int iMaxB=0;
		//boolean bAnzeigen=false;
		if(Qry.open("select aic_modell,st.kennung,Max_B from stammview st right outer join modell m on st.aic_stamm=m.aic_stamm left outer join begriff b on m.aic_begriff=b.aic_begriff where b.aic_begriff="+VecModell.elementAt(i)))
		{
			iAIC_Modell=Qry.getI("aic_modell");
			sPeriode=Qry.getS("kennung");
			iMaxB=Qry.getI("Max_B");
			Qry.close();
		}
		if(iAIC_Modell>0 && Qry.open("select hide,Eingabe,var,mbits,aic_befehl,bef_aic_befehl,bef2_aic_befehl,code.kennung code,begriff.kennung begriff,spalte.nummer spalte,"+
					"(select kennung from begriff join modell on begriff.aic_begriff=modell.aic_begriff where modell.aic_modell=befehl2.mod_aic_modell) modell, begriffgruppe.kennung begriffgruppe "+
					"from begriff join abfrage on begriff.aic_begriff=abfrage.aic_begriff join spalte on abfrage.aic_abfrage=spalte.aic_abfrage right outer join befehl2 on spalte.aic_spalte=befehl2.aic_spalte"+
                                        " left outer join code on befehl2.aic_code=code.aic_code left outer join begriffgruppe on code.aic_begriffgruppe=begriffgruppe.aic_begriffgruppe where befehl2.aic_modell="+iAIC_Modell+
                                        " order by aic_befehl,"+g.order("bef_aic_befehl")+","+g.order("bef2_aic_befehl")))
		{
			Tabellenspeicher Tab = new Tabellenspeicher(g,new String[]{"JaNein","AIC","Node"});
			Tab.sGruppe="JaNein";
			Tab.sAIC="AIC";
			Vector<Comparable> VecVisible = new Vector<Comparable>();
			VecVisible.addElement("Periode");
			VecVisible.addElement(sPeriode);
			VecVisible.addElement(iMaxB);
			AUOutliner OutBefehl = new AUOutliner(new JCOutlinerFolderNode(VecVisible));
			String[] s1 = new String[]{"BG","Code","Modell","Spalte","Begriff","hide","aic","eingabe","var","Bits"};
			OutBefehl.setColumnButtons(s1);
			OutBefehl.setNumColumns(s1.length);
			for(;!Qry.eof();Qry.moveNext())
			{
				VecVisible = new Vector<Comparable>();
				VecVisible.addElement(Qry.getS("begriffgruppe").equals("Bedingung")&&(Qry.getI("mbits")&1/*Calc.SCHLEIFE*/)>0?"Schleife":Qry.getS("begriffgruppe"));
				VecVisible.addElement(Qry.getS("code"));
				VecVisible.addElement(Qry.getS("modell"));
				VecVisible.addElement(Qry.getS("spalte"));
				VecVisible.addElement(Qry.getS("begriff"));
				VecVisible.addElement(Qry.getB("hide")?"T":"F");
                VecVisible.addElement(Qry.getInt("aic_befehl"));
                VecVisible.addElement(Static.changeK1(Qry.getS("eingabe")));
                VecVisible.addElement(Qry.getS("var"));
                VecVisible.addElement(Qry.getInt("mbits"));
                JCOutlinerFolderNode NodeParent = null;

				if(Qry.getI("bef_aic_befehl")>0)
				{
					if(Tab.posInhalt((Object)"Ja",(Object)Qry.getInt("bef_aic_befehl")))
						NodeParent=(JCOutlinerFolderNode)Tab.getInhalt("Node");
				}
				else if(Qry.getI("bef2_aic_befehl")>0)
				{
					if(Tab.posInhalt((Object)"Nein",(Object)Qry.getInt("bef2_aic_befehl")))
						NodeParent=(JCOutlinerFolderNode)Tab.getInhalt("Node");
				}
				else
					NodeParent=(JCOutlinerFolderNode)OutBefehl.getRootNode();

				JCOutlinerFolderNode Node = new JCOutlinerFolderNode((Object)VecVisible,NodeParent);

				Tab.bInsert=true;
				if(Qry.getS("begriffgruppe").equals("Bedingung"))
				{
					JCOutlinerFolderNode NodeJa = new JCOutlinerFolderNode("Ja",Node);
					JCOutlinerFolderNode NodeNein = new JCOutlinerFolderNode("Nein",Node);

					Tab.posInhalt("JaNein","Ja");
					Tab.putInhalt("JaNein","Ja",true);
					Tab.putInhalt("AIC",Qry.getInt("aic_befehl"),true);
					Tab.putInhalt("Node",NodeJa,true);

					Tab.posInhalt("JaNein","Nein");
					Tab.putInhalt("JaNein","Nein",true);
					Tab.putInhalt("AIC",Qry.getInt("aic_befehl"),true);
					Tab.putInhalt("Node",NodeNein,true);
				}
				else
				{
					Tab.posInhalt("JaNein","Ja");
					Tab.putInhalt("JaNein","Ja",true);
					Tab.putInhalt("AIC",Qry.getInt("aic_befehl"),true);
					Tab.putInhalt("Node",Node,true);
				}
			}
			Qry.close();

			/*
			JFrame Frm = new JFrame();
			Frm.getContentPane().add(OutBefehl);
			Frm.show();*/
                        int iPos=g.TabBegriffe.getPos("AIC",(Integer)VecModell.elementAt(i));
			if(iPos>=0)
			{
				//g.printInfo("Modell_"+g.TabBegriffe.getS("Kennung"));
				Zip.putNextEntry(new ZipEntry("Modell_"+g.TabBegriffe.getS(iPos,"Kennung")+".up"));
				Zip.SaveOutliner(OutBefehl);
				Zip.closeEntry();
			}
		}
	}

	//g.printInfo("VecAbfrage: "+VecAbfrage);

	if(VecAbfrage.size()>0)
	{
		if (Qry.open("SELECT st.kennung stamm, stt.kennung stammtyp,(select kennung from code where aic_code=s.aic_code) code_kennung1,(select kennung from code where aic_code=s.cod_aic_code) code_kennung2,(select kennung from code where aic_code=s.cod2_aic_code) code_kennung3,(select kennung from code where aic_code=s.cod3_aic_code) code_kennung4"+
			",bits2,bits3,bits4,min,max,x,y,w,h,Stil,ToggleSight,Icon,Farbe,s.bits,b.kennung abfrage,s.aic_stamm,s.sta_aic_stamm,s.aic_spalte,s.kennung,s.soritiert sortiert,s.laenge,s.weblaenge,s.nummer,s.reihenfolge,s.faktor,a.aic_begriff"+
			",s.beg_aic_begriff sub1,s.Abfrage_begriff sub2,s.Modell_begriff sub3,s.spa_aic_spalte"+
            ",(select kennung from begriff join abfrage on begriff.aic_begriff=abfrage.aic_begriff where aic_abfrage=s.filter) filter"+
            ",(select kennung from farbe where aic_farbe=s.aic_farbe) farbe2"+
            ",(select kennung from stammtyp where aic_stammtyp=s.aic_stammtyp) Hierarchie"+
            ",(select kennung from begriff where aic_begriff=s.aic_begriff) Format"+
            " FROM Stammtyp stt join stammview st on stt.aic_stammtyp=st.aic_stammtyp right outer join Spalte s on st.aic_stamm=s.aic_stamm JOIN Abfrage a on s.aic_abfrage=a.aic_abfrage JOIN Begriff b on a.aic_begriff=b.aic_begriff WHERE "+g.in("b.AIC_Begriff",VecAbfrage)+" order by s.AIC_Abfrage,s.nummer"))
	{
		Zip.putNextEntry(new ZipEntry("Spalte.up"));
		String sAbfrage = "";
		for(;!Qry.eof();Qry.moveNext())
		{
			if(!sAbfrage.equals(Qry.getS("abfrage")))
			{
				sAbfrage = Qry.getS("abfrage");
				Zip.SaveString("*!*");
				Zip.SaveString(sAbfrage);
			}
			Zip.SaveString(Qry.getS("kennung"));
			Zip.SaveInteger(Qry.getInt("aic_spalte"));
			Zip.SaveInteger(Qry.getInt("sortiert"));
			Zip.SaveInteger(Qry.getInt("laenge"));
			Zip.SaveInteger(Qry.getInt("weblaenge"));
			Zip.SaveInteger(Qry.getInt("reihenfolge"));
			Zip.SaveInteger(Qry.getInt("nummer"));
			Zip.SaveInteger(Qry.getInt("bits"));
			Zip.SaveString(Qry.getS("format"));
			Zip.SaveDouble(Qry.getF("faktor"));
			Zip.SaveString(Qry.getS("filter"));
            Zip.SaveString(Qry.getS("farbe2"));
			Zip.SaveString(Qry.getS("code_kennung1"));
			Zip.SaveString(Qry.getS("code_kennung2"));
			Zip.SaveString(Qry.getS("code_kennung3"));
            Zip.SaveString(Qry.getS("code_kennung4"));
            Zip.SaveInteger(Qry.getInt("bits2"));
            Zip.SaveInteger(Qry.getInt("bits3"));
            Zip.SaveInteger(Qry.getInt("bits4"));
            Zip.SaveDouble(Qry.getF("min"));
            Zip.SaveDouble(Qry.getF("max"));
            Zip.SaveInteger(Qry.getInt("x"));
            Zip.SaveInteger(Qry.getInt("y"));
            Zip.SaveInteger(Qry.getInt("w"));
            Zip.SaveInteger(Qry.getInt("h"));
            Zip.SaveString(Qry.getS("Stil"));
            Zip.SaveString(Qry.getS("ToggleSight"));
            Zip.SaveString(Qry.getS("Icon"));
            Zip.SaveString(Qry.getS("Farbe"));
            //TODO Begriffgruppe für sub1-sub3 dazu
            Zip.SaveString(g.getBG(Qry.getI("sub1")));
            Zip.SaveString(Qry.getI("sub1")>0 ? g.TabBegriffe.getKennung(Qry.getI("sub1")):Static.sLeer);
            Zip.SaveString(g.getBG(Qry.getI("sub2")));
            Zip.SaveString(Qry.getI("sub2")>0 ? g.TabBegriffe.getKennung(Qry.getI("sub2")):Static.sLeer);
            Zip.SaveString(g.getBG(Qry.getI("sub3")));
            Zip.SaveString(Qry.getI("sub3")>0 ? g.TabBegriffe.getKennung(Qry.getI("sub3")):Static.sLeer);
			Zip.SaveInteger(Qry.getI("spa_aic_spalte"));
			if(Qry.getI("aic_stamm")==0)
				Zip.SaveString("");
			else
				SaveKennung(Zip,"Abfrage.Spalte("+sAbfrage+","+Qry.getInt("nummer")+")",Qry.getI("aic_begriff"),Qry.getI("aic_stamm"),Qry.getS("stamm"));
			Zip.SaveString(Qry.getS("stammtyp"));
            Zip.SaveString(Qry.getS("Hierarchie"));
            if(Qry.getI("sta_aic_stamm")==0)
            {
              Zip.SaveString("");
              Zip.SaveString("");
            }
            else
            {
              String sStamm=SQL.getString(g,"select kennung from stammview where aic_stamm=?",Qry.getS("sta_aic_stamm"));
              String sStt=SQL.getString(g,"select stt.kennung from stammtyp stt join stamm on stt.aic_stammtyp=stamm.aic_stammtyp and aic_stamm=?",Qry.getS("sta_aic_stamm"));
              g.fixtestInfo("Spalte mit Rel "+sStt+"."+sStamm);
              Zip.SaveString(sStt);
              SaveKennung(Zip,"Abfrage.Spalte("+sAbfrage+","+Qry.getInt("nummer")+")",Qry.getI("aic_begriff"),Qry.getI("sta_aic_stamm"),sStamm);
            }
			if(!VecSpalte.contains(Qry.getInt("aic_spalte")))
				VecSpalte.addElement(Qry.getInt("aic_spalte"));
		}
		Qry.close();
		Zip.closeEntry();
	}
	
          Zip.putNextEntry(new ZipEntry("Spalte_Zuordnung.up"));
          if (Qry.open("select z.aic_spalte,z.reihenfolge,(select kennung from stammview where aic_stamm=z.aic_stamm) st"+
                       ",(select kennung from stammtyp where aic_stammtyp=z.aic_stammtyp) Stt"+
                       ",(select kennung from eigenschaft where aic_eigenschaft=z.aic_eigenschaft) Eig,Titel"+
                       " from abfrage a join spalte s on a.aic_abfrage=s.aic_abfrage join spalte_zuordnung z on s.aic_spalte=z.aic_spalte where " +
                       g.in("a.aic_begriff", VecAbfrage) + " order by a.aic_abfrage,s.aic_spalte,z.reihenfolge"))
          {
            for (; !Qry.eof(); Qry.moveNext())
            {
              Zip.SaveInteger(Qry.getInt("aic_spalte"));
              Zip.SaveInteger(Qry.getInt("reihenfolge"));
              Zip.SaveString(Qry.getS("st"));
              Zip.SaveString(Qry.getS("Stt"));
              Zip.SaveString(Qry.getS("Eig"));
              Zip.SaveString(Qry.getS("Titel"));
            }
            Qry.close();
          }
          /*if (Qry.open("select z.aic_spalte,z.reihenfolge,stt.kennung Stt,Titel from abfrage a join spalte s on a.aic_abfrage=s.aic_abfrage join spalte_zuordnung z on s.aic_spalte=z.aic_spalte join stammtyp stt on z.aic_stammtyp=stt.aic_stammtyp where " +
                       g.in("a.aic_begriff", VecAbfrage) + " order by a.aic_abfrage,s.aic_spalte,z.reihenfolge"))
          {
            for (Qry.moveFirst(); !Qry.eof(); Qry.moveNext())
            {
              Zip.SaveInteger(Qry.getInt("aic_spalte"));
              Zip.SaveInteger(Qry.getInt("reihenfolge"));
              Zip.SaveString("_nur_Stt_");
              Zip.SaveString(Qry.getS("Stt"));
              Zip.SaveString(Qry.getS("Titel"));
            }
            Qry.close();
          }*/
          Zip.closeEntry();
          
    if(Qry.open("select aic_begriff,beg_aic_begriff,art from begriff_zuordnung where"+g.in("aic_begriff",VecAbfrage)))
	{
		Zip.putNextEntry(new ZipEntry("SubAbfragen.up"));
		for(;!Qry.eof();Qry.moveNext())
		{
			Zip.SaveString(g.TabBegriffe.getKennung(Qry.getI("aic_begriff")));
			Zip.SaveString(g.TabBegriffe.getKennung(Qry.getI("beg_aic_begriff")));
			Zip.SaveString(g.TabCodes.getKennung(Qry.getI("art")));
		}
		Qry.close();
		Zip.closeEntry();
	}
      
	if(Qry.open("select z.aic_spalte,z.pos,code.kennung,z.spa_aic_spalte,z.wert,z.eingabe from abfrage a join spalte s on a.aic_abfrage=s.aic_abfrage"+
 		" join spalte_berechnung z on s.aic_spalte=z.aic_spalte join code on z.aic_code=code.aic_code where "+g.in("a.aic_begriff",VecAbfrage)+" order by a.aic_abfrage,s.aic_spalte,z.pos"))
	{
		Zip.putNextEntry(new ZipEntry("Spalte_Berechnung.up"));
		for(;!Qry.eof();Qry.moveNext())
		{
			Zip.SaveInteger(Qry.getInt("aic_spalte"));
			Zip.SaveInteger(Qry.getInt("pos"));
			Zip.SaveString(Qry.getS("kennung"));
			Zip.SaveInteger(Qry.getInt("spa_aic_spalte"));
			Zip.SaveDouble(new Double(Qry.getF("wert")));
			Zip.SaveString(Qry.getS("Eingabe"));
		}
		Qry.close();
		Zip.closeEntry();
	}

	if(Qry.open("select s.aic_spalte,richtung,e.kennung eigenschaft, (select kennung from bewegungstyp where aic_bewegungstyp=f.aic_bewegungstyp) bewegungstyp from abfrage a join spalte s on a.aic_abfrage=s.aic_abfrage join fixeigenschaft f on s.aic_spalte=f.aic_spalte join eigenschaft e on f.aic_eigenschaft=e.aic_eigenschaft where "+g.in("a.aic_begriff",VecAbfrage)+" order by a.aic_abfrage,nummer,aic_fixEigenschaft"))
	{
		Zip.putNextEntry(new ZipEntry("Fix_Spalte.up"));
		for(;!Qry.eof();Qry.moveNext())
		{
			Zip.SaveInteger(Qry.getInt("aic_spalte"));
			Zip.SaveBoolean(Qry.getB("richtung"));
			Zip.SaveString(Qry.getS("eigenschaft"));
			Zip.SaveString(Qry.getS("bewegungstyp"));
		}
		Qry.close();
		Zip.closeEntry();
	}

		Tabellenspeicher TabFix = new Tabellenspeicher(g,"select bed.aic_bedingung,richtung,e.kennung eigenschaft, (select kennung from bewegungstyp where aic_bewegungstyp=f.aic_bewegungstyp) bewegungstyp, b.kennung datentyp from abfrage a join bedingung bed on a.aic_abfrage=bed.aic_abfrage join fixeigenschaft f on bed.aic_bedingung=f.aic_bedingung join eigenschaft e on f.aic_eigenschaft=e.aic_eigenschaft join begriff b on e.aic_begriff=b.aic_begriff where "+g.in("a.aic_begriff",VecAbfrage)+" order by bed.aic_bedingung,aic_fixEigenschaft",true);
		Tabellenspeicher TabBedingung = new Tabellenspeicher(g,"select aic_bedingung,bed_aic_bedingung,b.kennung abfrage,vergleichswert,bbits,b2.kennung VO,null Tabelle,null Gruppe,null Bezeichnung,b.aic_begriff,null aic "+
															   "from begriff b join abfrage a on b.aic_begriff=a.aic_begriff join bedingung bed on a.aic_abfrage=bed.aic_abfrage join begriff b2 on bed.aic_begriff=b2.aic_begriff where "+g.in("a.aic_begriff",VecAbfrage)+
															   " order by abfrage,"+g.order("bed_aic_bedingung"),true);

		Zip.putNextEntry(new ZipEntry("Fix_Bedingung.up"));

		int iAlt = 0;
		String sTabelle = "";
		String sGruppe = "";
		for(TabFix.moveFirst();!TabFix.eof();TabFix.moveNext())
		{

			Zip.SaveInteger(TabFix.getInt("aic_bedingung"));
			Zip.SaveBoolean(TabFix.getB("richtung"));
			Zip.SaveString(TabFix.getS("eigenschaft"));
			Zip.SaveString(TabFix.getS("bewegungstyp"));

			int iAIC_Bed = TabFix.getI("aic_bedingung");

			TabFix.push();
			TabFix.moveNext();
			boolean bBed = TabFix.eof() || TabFix.getI("aic_bedingung")!=iAIC_Bed;
			TabFix.pop();

			if(bBed)
			{
				String sDatentyp = TabFix.getS("Datentyp");
				if(sDatentyp.equals("Benutzer"))
				{
					sTabelle="Benutzer";
					sGruppe="Mandant";
				}
				else if(sDatentyp.equals("Mandant"))
				{
					sTabelle="Mandant";
					sGruppe="";
				}
				else if(sDatentyp.equals("Gruppe") || sDatentyp.equals("Hierarchie") || sDatentyp.equals("BewStamm") || sDatentyp.equals("BewHierarchie") || sDatentyp.equals("SysAic") || sDatentyp.equals("Firma"))
				{
					sTabelle="Stamm";
					sGruppe="Stammtyp";
				}
				else if(sDatentyp.equals("Anlage") || sDatentyp.equals("BewModell") || sDatentyp.equals("BewDruck") || sDatentyp.equals("BewFormular"))
				{
					sTabelle="Code";
					sGruppe="Begriffgruppe";
				}
                else if(sDatentyp.endsWith("Bool3"))
                {
                        sTabelle="Auswahl";
                        sGruppe="Eigenschaft";
                }
				else
				{
					sTabelle="";
					sGruppe="";
				}
				
                if(iAlt!=TabFix.getI("aic_bedingung") && !sTabelle.equals("") && TabBedingung.posInhalt("aic_bedingung",TabFix.getI("aic_bedingung"))
                    && !TabBedingung.getS("VO").equals("is null") && !TabBedingung.getS("VO").equals("is not null"))
                {
                	String sVGW=TabBedingung.getS("vergleichswert");
                	if (!sVGW.equals("*Joker*") && !sVGW.equals("*JokerBew*") && !sVGW.startsWith("*JokerStt") && !sVGW.startsWith("*JokerVec")
                    && !sVGW.equals("*ich*") && !sVGW.equals("*meinStamm*") && !sVGW.startsWith("*meine") && !sVGW.startsWith("*UseVec")
                    && !sVGW.equals("*Checkbox*") && !sVGW.equals("*Radiobutton*") && !sVGW.startsWith("*Qry") && !sVGW.startsWith("*Joker von")
                    && !sVGW.equals("*aktMandant*") && !sVGW.startsWith("*Reserve1") && !sVGW.equals("*Reserve2*") && !sVGW.equals("now()") && !sVGW.startsWith("$") && !sVGW.startsWith("@"))
					{
						int iAIC=TabBedingung.getI("vergleichswert");
						if(iAIC>0)
						{
							TabBedingung.setInhalt("tabelle",sTabelle);
							boolean bStamm = sTabelle.equals("Stamm");
							if (bStamm)
								TabBedingung.setInhalt("Aic", iAIC);
							//String sTab=bStamm ? "Stamm_Protokoll":sTabelle;
							//String sKen="kennung"+(bStamm ? "2":"");
							sVGW = SQL.getString(g,"SELECT kennung"/*+(bStamm ? "2":"")*/+" FROM "+(bStamm ? "Stammview2":sTabelle)+" WHERE AIC_"+sTabelle+" = "+iAIC);
	                                                //if (sVergleichswert==null)
	                                                //  sVergleichswert="";
	                        g.progInfo("Vergleichswert für "+sTabelle+","+iAIC+":"+sVGW);
							TabBedingung.setInhalt("vergleichswert",sVGW);
							TabBedingung.setInhalt("gruppe",!sGruppe.equals("")?SQL.getString(g,"SELECT b.kennung FROM "+sTabelle+" a JOIN "+sGruppe+" b on a.aic_"+sGruppe+"=b.aic_"+sGruppe+" WHERE a.AIC_"+sTabelle+" = "+iAIC):null);
							if(sVGW==null || sVGW.equals(""))
								TabBedingung.setInhalt("Bezeichnung",SQL.getString(g,sTabelle.equals("Stamm")?"select bezeichnung from stammview2 where aic_stamm="+iAIC:
								"select bezeichnung from bezeichnung b join tabellenname t on b.aic_tabellenname=t.aic_tabellenname where b.aic_sprache="+g.getSprache()+" and t.kennung='"+sTabelle+"' and aic_fremd="+iAIC));
						}
					}
                }
				iAlt = TabFix.getI("aic_bedingung");
			}
		}
		Zip.closeEntry();

		Zip.putNextEntry(new ZipEntry("Bedingung.up"));
		String sAbfrage = "";
                //TabBedingung.showGrid("TabBedingung");
		for(TabBedingung.moveFirst();!TabBedingung.eof();TabBedingung.moveNext())
		{
			if(!sAbfrage.equals(TabBedingung.getS("abfrage")))
			{
				sAbfrage = TabBedingung.getS("abfrage");
				Zip.SaveString("*!*");
				Zip.SaveString(sAbfrage);
			}
			//g.fixtestError("Bedingung bei "+sAbfrage+" mit "+TabBedingung.getS("Bezeichnung"));
			if(TabBedingung.getS("Tabelle").equals(""))
				Zip.SaveString(TabBedingung.getS("vergleichswert"));
			else
				SaveKennung(Zip,"Abfrage.Bedingung("+sAbfrage+","+TabBedingung.getS("Tabelle")+","+TabBedingung.getS("Bezeichnung")+")",TabBedingung.getI("aic_begriff"),TabBedingung.getI("aic"),TabBedingung.getS("vergleichswert"));
			Zip.SaveInteger(TabBedingung.getInt("bbits"));
                        Zip.SaveInteger(TabBedingung.getInt("aic_bedingung"));
			Zip.SaveInteger(TabBedingung.getInt("bed_aic_bedingung"));
			Zip.SaveString(TabBedingung.getS("VO"));
			Zip.SaveString(TabBedingung.getS("Tabelle"));
			Zip.SaveString(TabBedingung.getS("Gruppe"));
		}
		Zip.closeEntry();
	}
        if (bNormal)
        {
          Zip.putNextEntry(new ZipEntry("Modul_Zuordnung2.up"));
          Tabellenspeicher Tab = new Tabellenspeicher(g, "select * from modul order by aic_begriff", true);
          Tabellenspeicher TabSprache = new Tabellenspeicher(g, "select aic_sprache aic,kennung from Sprache", true);
          Tabellenspeicher TabHS = new Tabellenspeicher(g, "select aic_hauptschirm aic,kennung from Hauptschirm where kennung is not null", true);
          for(Tab.moveFirst(); !Tab.eof(); Tab.moveNext())
          {
            int iPosB=g.TabBegriffe.getPos("aic", Tab.getI("Aic_Begriff"));
            String sModul = iPosB>=0 ? g.TabBegriffe.getS(iPosB,"Kennung") : null;
            int iPosT=g.TabTabellenname.getPos("aic", Tab.getI("Aic_Tabellenname"));
            String sTab = iPosT>=0 ? g.TabTabellenname.getS(iPosT,"Kennung") : null;
            Tabellenspeicher TabK = sTab == null ? null : sTab.equals("EIGENSCHAFT") ? g.TabEigenschaften : sTab.equals("STAMMTYP") ?
                g.TabStammtypen :sTab.equals("BEWEGUNGSTYP") ? g.TabErfassungstypen : sTab.equals("SPRACHE") ? TabSprache:sTab.equals("HAUPTSCHIRM") ? TabHS:null;
            int iPos=TabK==null ? -1:TabK.getPos("aic",Tab.getI("aic_fremd"));
            if(TabK != null && sModul != null && !sModul.equals("") && iPos>=0 && !TabK.getS(iPos,"Kennung").equals(""))
            {
              Zip.SaveString(sModul);
              Zip.SaveString(sTab);
              Zip.SaveString(TabK.getS(iPos,"Kennung"));
            }
            else
            {
              g.fixtestError("Modul_Zuordnung2-Fehler bei " + sModul + ", " + sTab + ", " + (TabK == null ? "null" : iPos==-1 ? ""+Tab.getI("aic_fremd"):TabK.getS(iPos,"Kennung")));
            }
          }
          Zip.closeEntry();
        }
	//g.printInfo("VecSpalte: "+VecSpalte);
        if (VecModule.size()>0 && Qry.open("select kennung,importzeit from begriff where aic_begriff"+Static.SQL_in(VecModule)))
        {
          Zip.putNextEntry(new ZipEntry("Module.up"));
          for(;!Qry.eof();Qry.moveNext())
          {
            Date dtNow=new Date();
            Zip.SaveString(Qry.getS("kennung"));
            Zip.SaveString(new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Qry.isNull("Importzeit")?dtNow:Qry.getTS("Importzeit")));
          }
          Zip.closeEntry();
        }
	}
        if (!CbxkeineStamm.isSelected() && VecAbfStamm!=null && VecAbfStamm.size()>0)
        {
          /*Zip.putNextEntry(new ZipEntry("Stamm_Abfragen.up"));
          //Tabellenspeicher Tab = new Tabellenspeicher(g,"select aic_begriff,kennung,aic_stammtyp,aic_bewegungstyp"
          for(int i=0;i<VecAbfStamm.size();i++)
          {
            int iPosB=g.TabBegriffe.getPos("aic", Sort.geti(VecAbfStamm,i));
            if (iPosB>=0)
              Zip.SaveString(g.TabBegriffe.getS(iPosB,"Kennung"));
          }
          Zip.closeEntry();*/
          Vector Vec = OutStamm.getRootNode().getChildren();
          if (Vec != null && Vec.size()>0)
           for(int i=0;i<Vec.size();i++)
           {
            int iBeg=((Integer)((JCOutlinerNode)Vec.elementAt(i)).getUserData()).intValue();
            int iPosB = g.TabBegriffe.getPos("aic", iBeg);//Sort.geti(VecAbfStamm, i));
            if (iPosB >= 0)
            {
              String sKennung=g.TabBegriffe.getS(iPosB,"Kennung");
              ShowAbfrage Abf = new ShowAbfrage(g, g.TabBegriffe.getI(iPosB,"aic"), Abfrage.cstExport);
              Tabellenspeicher TabDaten= Abf.getDaten(g.TabBegriffe.getI(iPosB,"stt"),0,null,null);
              //TabDaten.showGrid("Stamm_Abf_"+sKennung);
              if (TabDaten!=null && TabDaten.size()>0)
              {
                AUOutliner GidGesamt = new AUOutliner();
                ShowAbfrage.initOutliner(g, GidGesamt);
                Abf.bForExport = true;
                Abf.TabToOutliner(GidGesamt, TabDaten, null);
                GidGesamt.folderChanged(GidGesamt.getRootNode());
                Zip.putNextEntry(new ZipEntry("Stamm_Abf_" + sKennung + ".up"));
                Zip.SaveOutliner(GidGesamt);
                Zip.closeEntry();
              }
            }
          }
        }
      if (!CbxNurStamm.isSelected())
      {
        if (VecMD != null && VecMD.size()>0)
        {
          Zip.putNextEntry(new ZipEntry("Modelle_DefImport.up"));
          //Tabellenspeicher Tab = new Tabellenspeicher(g,"select aic_begriff,kennung,aic_stammtyp,aic_bewegungstyp"
          for (int i = 0; i < VecMD.size(); i++)
          {
            //int iPosB = g.TabBegriffe.getPos("aic", Sort.geti(VecAbfStamm, i));
            //if (iPosB >= 0)
              Zip.SaveString(Sort.gets(VecMD,i));
          }
          Zip.closeEntry();
        }

        SaveBildBezeichnungMemo(Zip,Qry,"Daten_Bild","Filename",null);
        SaveBildBezeichnungMemo(Zip,Qry,"Bezeichnung","Bezeichnung",null);
        SaveBildBezeichnungMemo(Zip,Qry,"Daten_Memo","Memo","Titel");
        SaveBildBezeichnungMemo(Zip,Qry,"Tooltip","Memo2",null);
      }
        /*Tabellenspeicher TabBilder=new Tabellenspeicher(g,"select stt.kennung stt,s.kennung,filename from daten_bild"+SQL.join("tabellenname","t","daten_bild","tabellenname")+
          " join stammview2 s on aic_fremd=aic_stamm join stammtyp stt on stt.aic_stammtyp=s.aic_stammtyp where t.kennung='Stamm' and s.kennung is not null order by stt.kennung",true);
        if (!TabBilder.isEmpty())
        {
          Zip.putNextEntry(new ZipEntry("Stamm_Bild.up"));
          for(TabBilder.moveFirst();!TabBilder.eof();TabBilder.moveNext())
          {
            Zip.SaveString(TabBilder.getS("stt"));
            Zip.SaveString(TabBilder.getS("kennung"));
            Zip.SaveString(TabBilder.getS("filename"));
          }
          Zip.closeEntry();
        }

        if (bFremd)
        {
          Tabellenspeicher TabMemos = new Tabellenspeicher(g,
              "select stt.kennung stt,s.kennung,memo,titel,(select kennung from sprache where aic_sprache=dm.aic_sprache) sm from daten_memo dm" +
              SQL.join("tabellenname", "t", "dm", "tabellenname") +
              " join stammview2 s on aic_fremd=aic_stamm join stammtyp stt on stt.aic_stammtyp=s.aic_stammtyp where t.kennung='Stamm' and s.kennung is not null order by stt.kennung", true);
          if (!TabMemos.isEmpty())
          {
            Zip.putNextEntry(new ZipEntry("Stamm_Memo.up"));
            for (TabMemos.moveFirst(); !TabMemos.eof(); TabMemos.moveNext())
            {
              Zip.SaveString(TabMemos.getS("stt"));
              Zip.SaveString(TabMemos.getS("kennung"));
              Zip.SaveString(TabMemos.getS("sm"));
              Zip.SaveString(TabMemos.getM("Memo"));
              Zip.SaveString(TabMemos.getS("titel"));
            }
            Zip.closeEntry();
          }
        }*/
	/*if(CbxVersionsupdate.isSelected())
	{
		Zip.putNextEntry(new ZipEntry("Versionsupdate.up"));
		if(Qry.open("select v.kennung version,erstellt,exportiert,t.kennung tabelle,aic_fremd,vu.bits from tabellenname t join versionsupdate vu on t.aic_tabellenname=vu.aic_tabellenname join version v on vu.aic_version=v.aic_version where v.aic_version="+iAIC_Version+" order by tabelle")&&!Qry.eof())
		{
			Zip.SaveString(Qry.getS("version"));
			java.util.Date DEdit=(java.util.Date)Qry.getTS("erstellt");
			Zip.SaveString(DEdit==null?new DateWOD(2000,1,1).Format("yyyy-MM-dd HH:mm:ss"):new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(DEdit));
			DEdit=(java.util.Date)Qry.getTS("exportiert");
			Zip.SaveString(DEdit==null?"null":new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(DEdit));

			String sTabelle="";
			for(;!Qry.eof();Qry.moveNext())
			{
				if(!sTabelle.equals(Qry.getS("Tabelle")))
				{
					sTabelle = Qry.getS("Tabelle").toUpperCase();
					Zip.SaveString("*!*");
					Zip.SaveString(sTabelle);
				}

				if(sTabelle.equals("BEGRIFF"))
				{
					if(g.TabBegriffe.posInhalt("Aic",Qry.getI("aic_fremd"))&&g.TabBegriffgruppen.posInhalt("Aic",g.TabBegriffe.getI("Gruppe")))
					{
                                          Zip.SaveString(g.TabBegriffe.getS("Kennung"));
                                          Zip.SaveString(g.TabBegriffgruppen.getS("Kennung"));
					}
                                        else
                                          Static.printError("DefExport-Versionsupdate.up: BEGRIFF "+Qry.getI("aic_fremd")+" nicht gefunden!");
				}
				else if(sTabelle.equals("EIGENSCHAFT"))
				{
					if(g.TabEigenschaften.posInhalt("Aic",Qry.getI("aic_fremd")))
                                          Zip.SaveString(g.TabEigenschaften.getS("Kennung"));
                                        else
                                          Static.printError("DefExport-Versionsupdate.up: EIGENSCHAFT "+Qry.getI("aic_fremd")+" nicht gefunden!");
				}
				else if(sTabelle.equals("STAMMTYP"))
				{
					if(g.TabStammtypen.posInhalt("Aic",Qry.getI("aic_fremd")))
						Zip.SaveString(g.TabStammtypen.getS("Kennung"));
                                        else
                                          Static.printError("DefExport-Versionsupdate.up: STAMMTYP "+Qry.getI("aic_fremd")+" nicht gefunden!");
				}
                                else
                                  Static.printError("DefExport-Versionsupdate.up: Tabelle "+sTabelle+" nicht gültig!");
                                Zip.SaveInteger(new Integer(Qry.getI("bits")));
			}
			Qry.close();
		}
		Zip.closeEntry();
	}*/

	Zip.close();
        if (bSaveKennung)
        {
          new File(sFile).delete();
          new Message(Message.WARNING_MESSAGE, null, g).showDialog("DefExport-Fehler");
        }
	//Class c = OutTest.getNextNode(OutTest.getRootNode()).getLabel().getClass();
	//g.debugInfo(""+c+" / "+c.getName()+" / "+c.isArray()+" / "+c.getPackage());

	Qry.free();
	//g.printInfo("Fertig!!!");
	//g.printInfo(""+VecStt);
}

private void Build(JFrame FomP)
{
	BtnTabellen = g.getButton("Tabellen");
	BtnOk = g.getButton("Ok");
        BtnOk.setEnabled(bOk);
	BtnAbbruch = g.getButton("Abbruch");

	//CbxSchnellimport = g.getCheckbox("Schnellimport");
        CbxKunden = g.getCheckbox("alle Kunden");
	CbxTest = g.getCheckbox("TestE");
        CbxClean= g.getCheckbox("clean all");
        CbxMC= g.getCheckbox("clean modul");
        CbxkeineStamm=g.getCheckbox("keine Stamm",g.ApplPort());
        CbxNurStamm=g.getCheckbox("nur Stamm");
        //CbxBefehlsmemos= g.getCheckbox("Befehlsmemos");
	//CbxFremdSprache = g.getCheckbox("FremdSprache");
	//CbxVersionsupdate = g.getCheckbox("Versionsupdate");

	TxtFile = new FileEingabe(g,iProg==0);
        TxtVer = new Text("",30,Text.FILE);
        //TxtVer.setEnabled(iProg>0);
        Parameter Para=new Parameter(g,"DefExport");
        TxtFile.setValue(Para.getParameter("Option",false,true));
        if (Para.bGefunden)
        {
            CbxKunden.setSelected((Para.int1&1)>0);
            CbxTest.setSelected((Para.int1&2)>0);
            CbxClean.setSelected((Para.int1&4)>0);
            CbxMC.setSelected((Para.int1&8)>0);
            CbxkeineStamm.setSelected((Para.int1&16)>0);
            //CbxNurStamm.setSelected((Para.int1&32)>0);
        }
	else if(g.Prog())
          TxtFile.setValue("C:\\AU_Update.zip");
        Para.free();

	String[] s = new String[]{"Formular-Kennung","zwingend"};
	OutFormular.setColumnButtons(s);
	OutFormular.setNumColumns(s.length);
        OutFormular.setRootVisible(false);
        s = new String[]{"Modell-Kennung","zwingend"};
	OutModell.setColumnButtons(s);
	OutModell.setNumColumns(s.length);
        OutModell.setRootVisible(false);
	s = new String[]{"Abfrage-Kennung","bits","bits2","autorefresh","spalten","Anzahl","zwingend","web","prog"};
	OutAbfrage.setColumnButtons(s);
	OutAbfrage.setNumColumns(s.length);
        OutAbfrage.setRootVisible(false);
	OutDruck.setColumnButtons(new String[]{"Druck-Kennung"});
	OutDruck.setNumColumns(1);
        OutDruck.setRootVisible(false);
        OutApplication.setColumnButtons(new String[]{"Application-Kennung"});
	OutApplication.setNumColumns(1);
        OutApplication.setRootVisible(false);
        OutStamm.setColumnButtons(new String[]{"Stamm-Kennung"});
	OutStamm.setNumColumns(1);
        OutStamm.setRootVisible(false);
	Frm = new JFrame("DefExport"+(iProg<=0 ? "":" - "+sProg+", "+g.getVonBis("dd.MM.yyyy",true)));
	Frm.getContentPane().setLayout(new BorderLayout(2,2));
	JPanel Pnl = new JPanel(new GridLayout(1,0,2,2));
	Pnl.add(OutFormular);
	Pnl.add(OutModell);
	Pnl.add(OutAbfrage);
	Pnl.add(OutDruck);
        JPanel PnlR = new JPanel(new GridLayout(0,1,2,2));
        PnlR.add(OutApplication);
        PnlR.add(OutStamm);
        Pnl.add(PnlR);
	Frm.getContentPane().add("Center",Pnl);
	JPanel Pnl2 = new JPanel(new GridLayout(1,0,2,2));
	Pnl2.add(BtnTabellen);
	Pnl2.add(TxtFile);
        if (iProg>0)
          Pnl2.add(TxtVer);
	Pnl2.add(BtnOk);
	Pnl2.add(BtnAbbruch);
	Pnl = new JPanel(new BorderLayout(2,2));
	Pnl.add("South",Pnl2);
	Pnl2 = new JPanel(new FlowLayout());
	//Pnl2.add(CbxSchnellimport);
        Pnl2.add(CbxKunden);
	Pnl2.add(CbxTest);
        Pnl2.add(CbxClean);
        Pnl2.add(CbxMC);
        Pnl2.add(CbxkeineStamm);
        Pnl2.add(CbxNurStamm);
        //Pnl2.add(CbxBefehlsmemos);
        if (VecModule.size()>0 && iProg==0 && bNormal)
          CbxMC.setSelected(!g.ApplPort());
        else
        {
          CbxClean.setSelected(false);
          CbxMC.setSelected(false);
          CbxClean.setEnabled(false);
          CbxMC.setEnabled(false);
        }
	//Pnl2.add(CbxFremdSprache);
	//Pnl2.add(CbxVersionsupdate);
	Pnl.add("West",Pnl2);
	Frm.getContentPane().add("South",Pnl);
	Frm.setSize(1200,800);
	Frm.setVisible(true);
	Static.centerComponent(Frm,FomP);

	BtnOk.addActionListener(new ActionListener()
	{
		public void actionPerformed(ActionEvent e)
		{
			int iLogging = SQL.getInteger(g,"select count(*) from begriff where aic_logging="+g.getLog()+" AND "+g.in("AIC_Begriff",VecBegriff));

			if(iLogging>0)
				new Message(Message.WARNING_MESSAGE,Frm,g).showDialog("Restart");
			else
			{
				boolean bTest=CbxTest.isSelected();
                                if (!bTest)
                                  AClient.send_AServer("deaktiv",g);
				if (bTest || Reinigung.Ausgeloggt(g,Frm,2))
				{
                                  int iPU=g.getPU();//SQL.getInteger(g,"select p.aic_parameter from hauptgruppe h join parameter p on h.aic_hauptgruppe=p.aic_hauptgruppe join nebengruppe n on n.aic_nebengruppe=p.aic_nebengruppe where h.kennung='Datenbank' and n.kennung='Update'");
                                  if (!bTest && iPU>0)
                                    g.exec("Update parameter set "+g.int1()+"=1 where aic_parameter=" + iPU);
					String sR=bTest ? "":Reinigung.SpaltenCheck(g,false);
					if(Reinigung.bFehler)
					{
						new Message(Message.WARNING_MESSAGE,Frm,g).showDialog("Fehlerliste",new String[]{sR});
					}
					else
					{
						Frm.setCursor(new Cursor(Cursor.WAIT_CURSOR));
						long lClock=Static.get_ms();
                                                //bNormal=!CbxSchnellimport.isSelected();
                                                //bFremd=CbxFremdSprache.isSelected();
						Speichern(TxtFile.getValue(),TxtVer.getText(),iProg>0);
						g.clockInfo("Exportdauer",lClock);
                                                Frm.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                                                //Static.bTest=bTest2;
                                                closeFrm();
					}
                                  if (!bTest && iPU>0)
                                  {
                                    g.exec("Update parameter set " + g.int1() + "=0 where aic_parameter=" + iPU);
                                    AClient.send_AServer("aktiv",g);
                                  }
				}
			}
		}
	});

	BtnAbbruch.addActionListener(new ActionListener()
	{
		public void actionPerformed(ActionEvent e)
		{
			//Frm.setVisible(false);
                        //Static.bTest=bTest2;
                        closeFrm();
		}
	});

	BtnTabellen.addActionListener(new ActionListener()
	{
		public void actionPerformed(ActionEvent e)
		{
			new Tabellenspeicher(g,"select aic_eigenschaft,kennung"+g.AU_Bezeichnung("eigenschaft","")+" from eigenschaft where aic_eigenschaft"+Static.SQL_in(VecEig),true).showGrid("Eigenschaft",Frm);
			new Tabellenspeicher(g,"select aic_stammtyp,kennung"+g.AU_Bezeichnung("stammtyp","")+" from stammtyp where aic_stammtyp"+Static.SQL_in(VecStt),true).showGrid("Stammtyp",Frm);
			new Tabellenspeicher(g,"select aic_bewegungstyp,kennung"+g.AU_Bezeichnung("bewegungstyp","")+" from bewegungstyp where aic_bewegungstyp"+Static.SQL_in(VecBew),true).showGrid("Bewegungstyp",Frm);
		}
	});

}

private void closeFrm()
      {
        Parameter Para=new Parameter(g,"DefExport");
        String sFile=TxtFile.getValue();
        if (sFile.length()>250)
        {
          g.fixInfo("Datei gekürzt von "+sFile.length()+" auf 250 Zeichen");
          sFile = sFile.substring(0, 250);
        }
        Para.setParameter("Option", sFile, (CbxKunden.isSelected()?1:0)+(CbxTest.isSelected() ? 2 : 0) + (CbxClean.isSelected() ? 4 : 0) + (CbxMC.isSelected() ? 8 : 0),0,0,0,false,true);
        Para.free();
        Frm.dispose();
      }

private void insert(Tabellenspeicher Tab, JCOutlinerFolderNode NodeParent,int iAIC,String sArt)
{
	boolean b=true;
	if(TabBegriff.posInhalt("AIC",iAIC))
	{
		JCOutlinerFolderNode Node2 = (JCOutlinerFolderNode)TabBegriff.getInhalt("Node");
		b=Node2.getLevel()==1;
		if(b)
		{
			g.progInfo("Delete: "+Node2);
			Node2.getParent().removeChild(Node2);
			int iiStart = TabBegriff.getI("Start");
			g.debugInfo("Loesche iiStart:"+iiStart);
			for(TabBegriff.posInhalt("Start",iiStart);!TabBegriff.out() && TabBegriff.getI("Start")==iiStart;TabBegriff.clearInhalt());
		}
	}

	if(b)
	{
		Vector<Comparable> VecVisible = new Vector<Comparable>();
		int iPos=g.TabBegriffe.getPos("AIC",iAIC);
		checkKennung(g.TabBegriffe.getS(iPos,"Kennung"),g.TabBegriffe.getInt(iPos,"AIC"),sArt.toUpperCase()+"-HIERARCHIE");
		VecVisible.addElement(g.TabBegriffe.getS(iPos,"Kennung"));
		VecVisible.addElement(new Boolean(false));
		JCOutlinerFolderNode Node = new JCOutlinerFolderNode((Object)VecVisible,NodeParent);
		JCOutlinerNodeStyle Sty=g.getStyle(null);
  	  	Sty.setForeground(g.TabBegriffe.getB(iPos,"Web") ? g.ColWeb:Color.BLACK);
  	  	Node.setStyle(Sty);
		TabBegriff.addInhalt("AIC",g.TabBegriffe.getInt(iPos,"AIC"));
		TabBegriff.addInhalt("Node",Node);
		TabBegriff.addInhalt("Start",iStart);
		TabBegriff.addInhalt("Art",sArt);

		Vec.addElement(iAIC);
		if(!sArt.equals("Abfrage") && Tab != null)
		{
			Tab.push();
			for(Tab.posInhalt("von",iAIC);!Tab.eof() && !Tab.out() && iAIC==Tab.getI("von");Tab.moveNext())
				if(!Vec.contains(Tab.getI("nach")))
				{
					insert(Tab,Node,Tab.getI("nach"),sArt);
				}
			Tab.pop();
		}
	}
}

private void SaveKennung(AUZip Zip,String sWo,int iBeg,int iStamm, String s)
{
	if(s==null || s.equals(""))
	{
		Static.printError("DefExport.SaveKennung(): Kennung in <"+sWo+"> ist leer!!!");
		//if(!bSaveKennung)
		{
			bSaveKennung = true;
			Tabellenspeicher TabLeer=new Tabellenspeicher(g,"select aic_begriff,defbezeichnung,begriff.kennung,bg.kennung Gruppe,(select bezeichnung from stammview2 where aic_rolle is null and aic_stamm="+iStamm+
					") Stamm from begriff join begriffgruppe bg on begriff.aic_begriffgruppe=bg.aic_begriffgruppe where aic_begriff="+iBeg,true);
			if (!TabLeer.isEmpty())
			{
				bOk = false;
				new Message(Message.WARNING_MESSAGE+Message.SHOW_TAB,Frm,g).showDialog("ohne_Kennung",TabLeer);
			}
			else
			  new Message(Message.WARNING_MESSAGE,null,g).showDialog("KennungLeer");
		}	
	}
	Zip.SaveString(s==null ? "null":s);
}

private void SaveZuordnung(AUZip Zip,SQL Qry,String sFile,String s2, String s1,String r,boolean bBegriffgruppe,boolean bCodegruppe,String sSQL)
{
	try
	{
		if (g.Debug())
			new Tabellenspeicher(g,sSQL,true).showGrid(sFile,Frm);
		
		g.printInfo(sFile);
		if(Qry.open(sSQL))
		{
			if(!Qry.eof())
			{
				Zip.putNextEntry(new ZipEntry(sFile+".up"));
				String sVor="";
				for(;!Qry.eof();Qry.moveNext())
				{
					if(!sVor.equals(Qry.getS(s1)))
					{
						sVor=Qry.getS(s1);
						Zip.SaveString("*!*");
						Zip.SaveString(sVor);
					}
					Zip.SaveString(Qry.getS(s2));
					if(bBegriffgruppe)
						Zip.SaveString(Qry.getS("Begriffgruppe"));
					if(bCodegruppe)
						Zip.SaveString(Qry.getS("Codegruppe"));
					if(r!=null)
						Zip.SaveInteger(Qry.getInt("reihenfolge"));
				}
				Zip.closeEntry();
			}
		}
	}
	catch(Exception e){if (iError<5) {iError++;Static.printError("DefExport.SaveZuordnung(): Exception - "+e);e.printStackTrace();}}
}

private void SaveBildBezeichnungMemo(AUZip Zip,SQL Qry,String sTabelle, String sWas, String sWas2)
{
	g.printInfo(sTabelle);
	Zip.putNextEntry(new ZipEntry(sTabelle+".up"));
	//Vector VecSprache = SQL.getVector("select aic_sprache from sprache "+(bFremd ?"":"where standard=1"),g);
        boolean bBild=sTabelle.equals("Daten_Bild");
	for(int j=0;j<(bBild?3:VecSprache.size());j++)
	{
          int iSprache= bBild ? j+1 : Tabellenspeicher.geti(VecSprache.elementAt(j));
          String sRelTab=bBild ? "Zustand":"Sprache";
            // exportierte Begriffe
		if(VecBegriff.size()>0 && Qry.open("SELECT s.kennung "+sRelTab+", "+sWas+(sWas2!=null?","+sWas2:"")+",b.kennung begriff,bg.kennung begriffgruppe FROM "+sRelTab+" s JOIN "+sTabelle+" on s.aic_"+sRelTab+"="+sTabelle+".aic_"+sRelTab+" JOIN Tabellenname t on "+sTabelle+".aic_tabellenname=t.aic_tabellenname,begriff b JOIN begriffgruppe bg on b.aic_begriffgruppe=bg.aic_begriffgruppe WHERE t.kennung='Begriff' AND "+g.in("AIC_Fremd",VecBegriff)+" AND b.aic_begriff=aic_fremd AND s.aic_"+sRelTab+"="+iSprache))
		{
			if(!Qry.eof())
			{
				Zip.SaveString("*!*");
				Zip.SaveString("BEGRIFF");
			}
			for(;!Qry.eof();Qry.moveNext())
			{
				Zip.SaveString(Qry.getS(sWas));
				if(sWas2!=null)
					Zip.SaveString(Qry.getS(sWas2));
				Zip.SaveString(Qry.getS(sRelTab));
				Zip.SaveString(Qry.getS("begriff"));
				Zip.SaveString(Qry.getS("begriffgruppe"));
			}
		}
                // alle Befehls-Memos von aktiven Modellen
                if (/*CbxBefehlsmemos.isSelected() && */sWas.equals("Memo") && bNormal)
                {
                  int iTab=g.TabTabellenname.getAic("BEFEHL");
                  int iAnz=0;
                  if(Qry.open("SELECT s.kennung "+sRelTab+", "+sWas+(sWas2!=null?","+sWas2:"")+",aic_fremd FROM "+sRelTab+" s JOIN "+sTabelle+" on s.aic_"+sRelTab+"="+sTabelle+".aic_"+sRelTab+" and "+sTabelle+".aic_tabellenname="+iTab
                              +" join befehl2 on aic_fremd=aic_befehl WHERE s.aic_"+sRelTab+"="+iSprache) && !Qry.eof())
                  {
                      Zip.SaveString("*!*");
                      Zip.SaveString("BEFEHL");

                      for (; !Qry.eof(); Qry.moveNext())
                      {
                        iAnz++;
                        Zip.SaveString(Qry.getS(sWas));
                        if (sWas2 != null)
                          Zip.SaveString(Qry.getS(sWas2));
                        Zip.SaveString(Qry.getS(sRelTab));
                        Zip.SaveString(Qry.getS("aic_fremd"));
                      }
                      g.defInfo2("Befehls-Memos:" + iAnz);
                  }
                }
                if(bNormal)
                {
                  // Datentyp, Panel, Vergleich immer
                  if(Qry.open("SELECT s.kennung "+sRelTab+", "+sWas+(sWas2!=null?","+sWas2:"")+",b.kennung begriff,bg.kennung begriffgruppe FROM "+sRelTab+" s JOIN "+sTabelle+" on s.aic_"+sRelTab+"="+sTabelle+".aic_"+sRelTab+" JOIN Tabellenname t on "+sTabelle+".aic_tabellenname=t.aic_tabellenname,begriff b JOIN begriffgruppe bg on b.aic_begriffgruppe=bg.aic_begriffgruppe WHERE t.kennung='Begriff' AND (bg.kennung='Datentyp' OR bg.kennung='Panel' OR bg.kennung='Vergleich') AND b.aic_begriff=aic_fremd AND s.aic_"+sRelTab+"="+iSprache))
                  {
			if(!Qry.eof())
			{
				Zip.SaveString("*!*");
				Zip.SaveString("BEGRIFF");
			}
			for(;!Qry.eof();Qry.moveNext())
			{
				Zip.SaveString(Qry.getS(sWas));
				if(sWas2!=null)
					Zip.SaveString(Qry.getS(sWas2));
				Zip.SaveString(Qry.getS(sRelTab));
				Zip.SaveString(Qry.getS("begriff"));
				Zip.SaveString(Qry.getS("begriffgruppe"));
			}
                  }
                  if(Qry.open("SELECT s.kennung "+sRelTab+", "+sWas+(sWas2!=null?","+sWas2:"")+",c.kennung code,bg.kennung begriffgruppe FROM "+sRelTab+" s JOIN "+sTabelle+" on s.aic_"+sRelTab+"="+sTabelle+".aic_"+sRelTab+" JOIN Tabellenname t on "+sTabelle+".aic_tabellenname=t.aic_tabellenname,Code c JOIN begriffgruppe bg on c.aic_begriffgruppe=bg.aic_begriffgruppe WHERE t.kennung='CODE' AND c.aic_code=aic_fremd AND s.aic_"+sRelTab+"="+iSprache))
                  {
			if(!Qry.eof())
			{
				Zip.SaveString("*!*");
				Zip.SaveString("CODE");
			}
			for(;!Qry.eof();Qry.moveNext())
			{
				Zip.SaveString(Qry.getS(sWas));
				if(sWas2!=null)
					Zip.SaveString(Qry.getS(sWas2));
				Zip.SaveString(Qry.getS(sRelTab));
				Zip.SaveString(Qry.getS("code"));
				Zip.SaveString(Qry.getS("begriffgruppe"));
			}
                  }
                  // Stamm-Bezeichnungen:
                  //new Tabellenspeicher(g,"SELECT s.kennung Sprache, "+sWas+(sWas2!=null?","+sWas2:"")+",ss.kennung Stamm,stt.kennung Stammtyp FROM Sprache s JOIN "+sTabelle+" on s.aic_sprache="+sTabelle+".aic_sprache JOIN Tabellenname t on "+sTabelle+".aic_tabellenname=t.aic_tabellenname,Stamm ss JOIN Stammtyp stt on ss.aic_stammtyp=stt.aic_stammtyp WHERE t.kennung='Stamm' AND ss.aic_stamm=aic_fremd and ss.kennung is not null AND s.aic_sprache="+VecSprache.elementAt(j),true).showGrid(sWas+"/"+VecSprache.elementAt(j));
                  if(Qry.open("SELECT s.kennung "+sRelTab+", "+sTabelle+"."+sWas+(sWas2!=null?","+sWas2:"")+",ss.kennung Stamm,stt.kennung Stammtyp FROM "+sRelTab+" s JOIN "+sTabelle+" on s.aic_"+sRelTab+"="+sTabelle+".aic_"+sRelTab+" JOIN Tabellenname t on "+sTabelle+".aic_tabellenname=t.aic_tabellenname,Stammview ss JOIN Stammtyp stt on ss.aic_stammtyp=stt.aic_stammtyp WHERE t.kennung='Stamm' AND ss.aic_stamm=aic_fremd and ss.kennung is not null AND s.aic_"+sRelTab+"="+iSprache))
                  {
                        if(!Qry.eof())
                        {
                                Zip.SaveString("*!*");
                                Zip.SaveString("STAMM");
                        }
                        for(;!Qry.eof();Qry.moveNext())
                        {
                                Zip.SaveString(Qry.getS(sWas));
                                if(sWas2!=null)
                                        Zip.SaveString(Qry.getS(sWas2));
                                Zip.SaveString(Qry.getS(sRelTab));
                                Zip.SaveString(Qry.getS("Stamm"));
                                Zip.SaveString(Qry.getS("Stammtyp"));
                        }
                  }
                  if(Qry.open("SELECT s.kennung "+sRelTab+", "+sWas+(sWas2!=null?","+sWas2:"")+",c.kennung code FROM "+sRelTab+" s JOIN "+sTabelle+" on s.aic_"+sRelTab+"="+sTabelle+".aic_"+sRelTab+" JOIN Tabellenname t on "+sTabelle+".aic_tabellenname=t.aic_tabellenname,begriffgruppe c WHERE t.kennung='Begriffgruppe' AND c.aic_begriffgruppe=aic_fremd AND s.aic_"+sRelTab+"="+iSprache))
                  {
                        if(!Qry.eof())
                        {
                                Zip.SaveString("*!*");
                                Zip.SaveString("BEGRIFFGRUPPE");
                        }
                        for(;!Qry.eof();Qry.moveNext())
                        {
                                Zip.SaveString(Qry.getS(sWas));
                                if(sWas2!=null)
                                        Zip.SaveString(Qry.getS(sWas2));
                                Zip.SaveString(Qry.getS(sRelTab));
                                Zip.SaveString(Qry.getS("code"));
                        }
                  }
                  if(Qry.open("SELECT s.kennung "+sRelTab+", "+sWas+(sWas2!=null?","+sWas2:"")+",c.kennung code FROM "+sRelTab+" s JOIN "+sTabelle+" on s.aic_"+sRelTab+"="+sTabelle+".aic_"+sRelTab+" JOIN Tabellenname t on "+sTabelle+".aic_tabellenname=t.aic_tabellenname,tabellenname c WHERE t.kennung='Tabellenname' AND c.aic_tabellenname=aic_fremd AND s.aic_"+sRelTab+"="+iSprache))
                  {
                        if(!Qry.eof())
                        {
                                Zip.SaveString("*!*");
                                Zip.SaveString("TABELLENNAME");
                        }
                        for(;!Qry.eof();Qry.moveNext())
                        {
                                Zip.SaveString(Qry.getS(sWas));
                                if(sWas2!=null)
                                        Zip.SaveString(Qry.getS(sWas2));
                                Zip.SaveString(Qry.getS(sRelTab));
                                Zip.SaveString(Qry.getS("code"));
                        }
                  }
                }
                if(VecBew.size()>0 && Qry.open("SELECT s.kennung "+sRelTab+","+sWas+(sWas2!=null?","+sWas2:"")+",bew.kennung FROM "+sRelTab+" s JOIN "+sTabelle+" on s.aic_"+sRelTab+"="+sTabelle+".aic_"+sRelTab+" JOIN Tabellenname t on "+sTabelle+".aic_tabellenname=t.aic_tabellenname,bewegungstyp bew WHERE t.kennung='BEWEGUNGSTYP' AND AIC_Fremd "+Static.SQL_in(VecBew)+" AND bew.aic_bewegungstyp=aic_fremd AND s.aic_"+sRelTab+"="+iSprache))
		{
			if(!Qry.eof())
			{
				Zip.SaveString("*!*");
				Zip.SaveString("BEWEGUNGSTYP");

			}
			for(;!Qry.eof();Qry.moveNext())
			{
				Zip.SaveString(Qry.getS(sWas));
				if(sWas2!=null)
					Zip.SaveString(Qry.getS(sWas2));
				Zip.SaveString(Qry.getS(sRelTab));
				Zip.SaveString(Qry.getS("kennung"));
			}
		}
		if(VecStt.size()>0 && Qry.open("SELECT s.kennung "+sRelTab+","+sWas+(sWas2!=null?","+sWas2:"")+",stt.kennung FROM "+sRelTab+" s JOIN "+sTabelle+" on s.aic_"+sRelTab+"="+sTabelle+".aic_"+sRelTab+" JOIN Tabellenname t on "+sTabelle+".aic_tabellenname=t.aic_tabellenname,Stammtyp stt WHERE t.kennung='Stammtyp' AND AIC_Fremd "+Static.SQL_in(VecStt)+" AND stt.aic_Stammtyp=aic_fremd AND s.aic_"+sRelTab+"="+iSprache))
		{
			if(!Qry.eof())
			{
				Zip.SaveString("*!*");
				Zip.SaveString("STAMMTYP");

			}
			for(;!Qry.eof();Qry.moveNext())
			{
				Zip.SaveString(Qry.getS(sWas));
				if(sWas2!=null)
					Zip.SaveString(Qry.getS(sWas2));
				Zip.SaveString(Qry.getS(sRelTab));
				Zip.SaveString(Qry.getS("kennung"));
			}
		}
                if(VecStt.size()>0 && Qry.open("SELECT s.kennung "+sRelTab+","+sWas+(sWas2!=null?","+sWas2:"")+",stt.kennung FROM "+sRelTab+" s JOIN "+sTabelle+" on s.aic_"+sRelTab+"="+sTabelle+".aic_"+sRelTab+" JOIN Tabellenname t on "+sTabelle+".aic_tabellenname=t.aic_tabellenname,Rolle stt WHERE t.kennung='ROLLE' AND stt.aic_Rolle=aic_fremd and stt.Tod is null AND s.aic_"+sRelTab+"="+iSprache))
                {
                        if(!Qry.eof())
                        {
                                Zip.SaveString("*!*");
                                Zip.SaveString("ROLLE");

                        }
                        for(;!Qry.eof();Qry.moveNext())
                        {
                                Zip.SaveString(Qry.getS(sWas));
                                if(sWas2!=null)
                                        Zip.SaveString(Qry.getS(sWas2));
                                Zip.SaveString(Qry.getS(sRelTab));
                                Zip.SaveString(Qry.getS("kennung"));
                        }
                }
		if(VecEig.size()>0 && Qry.open("SELECT s.kennung "+sRelTab+","+sWas+(sWas2!=null?","+sWas2:"")+",eig.kennung FROM "+sRelTab+" s JOIN "+sTabelle+" on s.aic_"+sRelTab+"="+sTabelle+".aic_"+sRelTab+" JOIN Tabellenname t on "+
                                               sTabelle+".aic_tabellenname=t.aic_tabellenname,Eigenschaft eig WHERE t.kennung='Eigenschaft' AND "+g.in("AIC_Fremd",VecEig)+" AND eig.aic_Eigenschaft=aic_fremd AND s.aic_"+sRelTab+"="+iSprache))
		{
			if(!Qry.eof())
			{
				Zip.SaveString("*!*");
				Zip.SaveString("EIGENSCHAFT");

			}
			for(;!Qry.eof();Qry.moveNext())
			{
				Zip.SaveString(Qry.getS(sWas));
				if(sWas2!=null)
					Zip.SaveString(Qry.getS(sWas2));
				Zip.SaveString(Qry.getS(sRelTab));
				Zip.SaveString(Qry.getS("kennung"));
			}
		}
                if(VecEig.size()>0 && Qry.open("SELECT s.kennung "+sRelTab+", "+sWas+(sWas2!=null?","+sWas2:"")+",a.kennung auswahl,e.kennung eig FROM "+sRelTab+" s JOIN "+
                                               sTabelle+" on s.aic_"+sRelTab+"="+sTabelle+".aic_"+sRelTab+" and "+sTabelle+".aic_tabellenname="+g.TabTabellenname.getAic("AUSWAHL")+
                                               ",auswahl a JOIN eigenschaft e on a.aic_eigenschaft=e.aic_eigenschaft WHERE "+g.in("a.aic_eigenschaft",VecEig)+" AND a.aic_auswahl=aic_fremd AND s.aic_"+sRelTab+"="+iSprache))
                {
                        if(!Qry.eof())
                        {
                                Zip.SaveString("*!*");
                                Zip.SaveString("AUSWAHL");
                        }
                        for(;!Qry.eof();Qry.moveNext())
                        {
                                Zip.SaveString(Qry.getS(sWas));
                                if(sWas2!=null)
                                        Zip.SaveString(Qry.getS(sWas2));
                                Zip.SaveString(Qry.getS(sRelTab));
                                Zip.SaveString(Qry.getS("auswahl"));
                                Zip.SaveString(Qry.getS("eig"));
                        }
                }
                if (VecHS != null && VecHS.size()>0 && Qry.open("SELECT s.kennung "+sRelTab+","+sWas+(sWas2!=null?","+sWas2:"")+",f.kennung FROM "+sRelTab+" s JOIN "+sTabelle+
                    " on s.aic_"+sRelTab+"="+sTabelle+".aic_"+sRelTab+" JOIN Tabellenname t on "+sTabelle+".aic_tabellenname=t.aic_tabellenname,Hauptschirm f WHERE t.kennung='HAUPTSCHIRM' AND f.aic_Hauptschirm=aic_fremd AND s.aic_"+sRelTab+"="+iSprache+" and "+g.in("aic_hauptschirm",VecHS)))
                  {
                        if(!Qry.eof())
                        {
                                Zip.SaveString("*!*");
                                Zip.SaveString("HAUPTSCHIRM");

                        }
                        for(;!Qry.eof();Qry.moveNext())
                        {
                                Zip.SaveString(Qry.getS(sWas));
                                if(sWas2!=null)
                                        Zip.SaveString(Qry.getS(sWas2));
                                Zip.SaveString(Qry.getS(sRelTab));
                                Zip.SaveString(Qry.getS("kennung"));
                        }
                  }

		/*if(VecHomepage.size()>0 && Qry.open("SELECT s.kennung Sprache,"+sWas+(sWas2!=null?","+sWas2:"")+",h.kennung FROM Sprache s JOIN "+sTabelle+" on s.aic_sprache="+sTabelle+".aic_sprache JOIN Tabellenname t on "+sTabelle+".aic_tabellenname=t.aic_tabellenname,Homepage h WHERE t.kennung='Homepage' AND AIC_Fremd "+Static.SQL_in(VecHomepage)+" AND h.aic_Homepage=aic_fremd AND s.aic_sprache="+iSprache))
		{
			if(!Qry.eof())
			{
				Zip.SaveString("*!*");
				Zip.SaveString("HOMEPAGE");

			}
			for(;!Qry.eof();Qry.moveNext())
			{
				Zip.SaveString(Qry.getS(sWas));
				if(sWas2!=null)
					Zip.SaveString(Qry.getS(sWas2));
				Zip.SaveString(Qry.getS("Sprache"));
				Zip.SaveString(Qry.getS("kennung"));
			}
		}*/
                String [] sTabs= new String [] {"Farbe","Sprache","Zustand","Land","ABSCHLUSSTYP","Schrift","RASTER","Status","Zuordnung","Tabellenname"};
                for(int i=0;i<sTabs.length;i++)
                  if(bNormal && Qry.open("SELECT s.kennung "+sRelTab+","+sWas+(sWas2!=null?","+sWas2:"")+",f.kennung FROM "+sRelTab+" s JOIN "+sTabelle+" on s.aic_"+sRelTab+"="+sTabelle+".aic_"+sRelTab+" JOIN Tabellenname t on "+sTabelle+".aic_tabellenname=t.aic_tabellenname,"+sTabs[i]+" f WHERE t.kennung='"+sTabs[i]+"' AND f.aic_"+sTabs[i]+"=aic_fremd AND s.aic_"+sRelTab+"="+iSprache))
                  {
			if(!Qry.eof())
			{
				Zip.SaveString("*!*");
				Zip.SaveString(sTabs[i].toUpperCase());

			}
			for(;!Qry.eof();Qry.moveNext())
			{
				Zip.SaveString(Qry.getS(sWas));
				if(sWas2!=null)
					Zip.SaveString(Qry.getS(sWas2));
				Zip.SaveString(Qry.getS(sRelTab));
				Zip.SaveString(Qry.getS("kennung"));
			}
                  }
                /*if(bNormal && Qry.open("SELECT s.kennung Sprache,"+sWas+(sWas2!=null?","+sWas2:"")+",f.kennung FROM Sprache s JOIN "+sTabelle+" on s.aic_sprache="+sTabelle+".aic_sprache JOIN Tabellenname t on "+sTabelle+".aic_tabellenname=t.aic_tabellenname,ABSCHLUSSTYP f WHERE t.kennung='ABSCHLUSSTYP' AND f.aic_ABSCHLUSSTYP=aic_fremd AND s.aic_sprache="+VecSprache.elementAt(j)))
                {
                        if(!Qry.eof())
                        {
                                Zip.SaveString("*!*");
                                Zip.SaveString("ABSCHLUSSTYP");

                        }
                        for(;!Qry.eof();Qry.moveNext())
                        {
                                Zip.SaveString(Qry.getS(sWas));
                                if(sWas2!=null)
                                        Zip.SaveString(Qry.getS(sWas2));
                                Zip.SaveString(Qry.getS("Sprache"));
                                Zip.SaveString(Qry.getS("kennung"));
                        }
                }
		if(bNormal &&  Qry.open("SELECT s.kennung Sprache,"+sWas+(sWas2!=null?","+sWas2:"")+",schr.kennung FROM Sprache s JOIN "+sTabelle+" on s.aic_sprache="+sTabelle+".aic_sprache JOIN Tabellenname t on "+sTabelle+".aic_tabellenname=t.aic_tabellenname,Schrift schr WHERE t.kennung='Schrift'"+" AND schr.aic_Schrift=aic_fremd AND s.aic_sprache="+VecSprache.elementAt(j)))
		{
			if(!Qry.eof())
			{
				Zip.SaveString("*!*");
				Zip.SaveString("SCHRIFT");

			}
			for(;!Qry.eof();Qry.moveNext())
			{
				Zip.SaveString(Qry.getS(sWas));
				if(sWas2!=null)
					Zip.SaveString(Qry.getS(sWas2));
				Zip.SaveString(Qry.getS("Sprache"));
				Zip.SaveString(Qry.getS("kennung"));
			}
		}
                if(bNormal &&  Qry.open("SELECT s.kennung Sprache,"+sWas+(sWas2!=null?","+sWas2:"")+",Raster.kennung FROM Sprache s JOIN "+sTabelle+" on s.aic_sprache="+sTabelle+".aic_sprache JOIN Tabellenname t on "+sTabelle+".aic_tabellenname=t.aic_tabellenname,Raster WHERE t.kennung='RASTER'"+" AND Raster.aic_Raster=aic_fremd AND s.aic_sprache="+VecSprache.elementAt(j)))
		{
			if(!Qry.eof())
			{
				Zip.SaveString("*!*");
				Zip.SaveString("RASTER");

			}
			for(;!Qry.eof();Qry.moveNext())
			{
				Zip.SaveString(Qry.getS(sWas));
				if(sWas2!=null)
					Zip.SaveString(Qry.getS(sWas2));
				Zip.SaveString(Qry.getS("Sprache"));
				Zip.SaveString(Qry.getS("kennung"));
			}
		}*/
		if(VecAbschnitt.size()>0 && Qry.open("SELECT absch.aic_Abschnitt,s.kennung "+sRelTab+","+sWas+(sWas2!=null?","+sWas2:"")+",absch.kennung FROM "+sRelTab+" s JOIN "+sTabelle+" on s.aic_"+sRelTab+"="+sTabelle+".aic_"+sRelTab+" JOIN Tabellenname t on "+sTabelle+".aic_tabellenname=t.aic_tabellenname,Abschnitt absch WHERE t.kennung='ABSCHNITT' AND AIC_Fremd "+Static.SQL_in(VecAbschnitt)+" AND absch.aic_Abschnitt=aic_fremd AND s.aic_"+sRelTab+"="+iSprache))
		{
			if(!Qry.eof())
			{
				Zip.SaveString("*!*");
				Zip.SaveString("ABSCHNITT");

			}
			for(;!Qry.eof();Qry.moveNext())
			{
				Zip.SaveString(Qry.getS(sWas));
				if (bBild)
				{
					String sBild=Qry.getS(sWas);
					
					String sFile2=sDirBild+File.separator+sBild;
					File file=new File(sFile2);
					file.mkdirs();
					Image Img = SQL.getImageA(g, Qry.getI("aic_abschnitt"));
					try
					{
						ImageIO.write(Static.imageToBufferedImage(sBild,Img), sBild.substring(sBild.lastIndexOf('.')+1).toLowerCase(), file);
					}
					catch (Exception e) {
						Static.printError("Abschnitt-Bild "+sBild+" nicht konvertierbar");
//		    			g.printStackTrace(e);
		    		}
				}
				if(sWas2!=null)
					Zip.SaveString(Qry.getS(sWas2));
				Zip.SaveString(Qry.getS(sRelTab));
				Zip.SaveString(Qry.getS("kennung"));
			}
		}
		if(VecSpalte.size()>0 && Qry.open("SELECT s.kennung "+sRelTab+","+sWas+(sWas2!=null?","+sWas2:"")+",aic_fremd FROM "+sRelTab+" s JOIN "+sTabelle+" on s.aic_"+sRelTab+"="+sTabelle+".aic_"+sRelTab+" JOIN Tabellenname t on "+sTabelle+".aic_tabellenname=t.aic_tabellenname WHERE t.kennung='SPALTE' AND "+g.in("AIC_Fremd",VecSpalte)+" AND s.aic_"+sRelTab+"="+iSprache))
		{
			if(!Qry.eof())
			{
				Zip.SaveString("*!*");
				Zip.SaveString("SPALTE");

			}
			for(;!Qry.eof();Qry.moveNext())
			{
				Zip.SaveString(Qry.getS(sWas));
				if(sWas2!=null)
					Zip.SaveString(Qry.getS(sWas2));
				Zip.SaveString(Qry.getS(sRelTab));
				Zip.SaveString(Qry.getS("aic_fremd"));
			}
		}
	}
	Zip.closeEntry();
}

private String SttBew(int iPos)
{
  return g.TabBegriffe.getI(iPos,"Erf")>0 ? g.TabErfassungstypen.getBezeichnungS(g.TabBegriffe.getI(iPos,"Erf")):
      g.TabBegriffe.getI(iPos,"Stt")>0 ? g.TabStammtypen.getBezeichnungS(g.TabBegriffe.getI(iPos,"Stt")):Static.sKein;
}

private void checkKennung(String sKennung,Integer iAic,String sOrt)
{
  boolean bOk2=sKennung!=null && !sKennung.equals("");
  if(!bOk2)
  {
    bOk = false;
    int iPos=g.TabBegriffe.getPos("Aic",iAic);
    int iPosBG= iPos<0 ? -1:g.TabBegriffgruppen.getPos("Aic",g.TabBegriffe.getI(iPos,"Gruppe"));
    if (iPos>=0 && iPosBG>=0)
      Static.printError("Kennung von "+g.TabBegriffgruppen.getS(iPosBG,"Bezeichnung")+" "+g.TabBegriffe.getS(iPos,"Bezeichnung")+" ("+SttBew(iPos)+"/" + sOrt + ") ist leer!!!");
    Static.printError(" DIESER VERSIONSEXPORT IST FEHLERHAFT UND KANN NICHT VERWENDET WERDEN!!!");
  }
}

// add your data members here
private Global g;
private String sDirBild=null;
private boolean bOk=true;
//private int i=0;
//private int iAIC_Version;
private Vector VecDaten;
private AUOutliner OutFormular = new AUOutliner(new JCOutlinerFolderNode("Formular"));
private AUOutliner OutModell = new AUOutliner(new JCOutlinerFolderNode("Modell"));
private AUOutliner OutAbfrage = new AUOutliner(new JCOutlinerFolderNode("Abfrage"));
private AUOutliner OutDruck = new AUOutliner(new JCOutlinerFolderNode("Druck"));
private AUOutliner OutApplication = new AUOutliner(new JCOutlinerFolderNode("Application"));
private AUOutliner OutStamm = new AUOutliner(new JCOutlinerFolderNode("Stamm"));
private AUOutliner OutDarstellung;
private Vector<Integer> Vec = new Vector<Integer>();
private Tabellenspeicher TabBegriff;
private Integer iStart;
private Vector<Integer> VecBegriff;
private Vector<Integer> VecStt = new Vector<Integer>();
private Vector<Integer> VecBew = new Vector<Integer>();
private Vector<Integer> VecApplication = new Vector<Integer>();
private Vector VecHS=null;
private Vector<Integer> VecGruppe = new Vector<Integer>();
private Vector<Integer> VecEig = new Vector<Integer>();
private Vector<Integer> VecFormular = new Vector<Integer>();
private Vector<Integer> VecModell = new Vector<Integer>();
private Vector<String> VecMD = new Vector<String>(); // Modelle die nach DefImport ausgeführt werden
private Vector<Integer> VecAbfrage = new Vector<Integer>();
//private Vector<Object> VecHomepage = new Vector<Object>();
//private Vector VecFarbe = new Vector();
//private Vector VecSchrift = new Vector();
private Vector<Integer> VecSprache = new Vector<Integer>();
private Vector<Integer> VecDruck = new Vector<Integer>();
private Vector<Object> VecAbschnitt = new Vector<Object>();
private Vector<Object> VecSpalte = new Vector<Object>();
private Vector<Integer> VecPlanung = new Vector<Integer>();
private Vector<Integer> VecMenge = new Vector<Integer>();
private Vector<Integer> VecModul = new Vector<Integer>();
//private Vector VecNurBegriff = new Vector();
//private Vector VecNurEig = new Vector();
//private Vector VecNurStt = new Vector();
private Vector<Integer> VecMB = new Vector<Integer>();
private Vector<Integer> VecForm = new Vector<Integer>();
private Vector<Integer> VecModule=null;
private Vector<Integer> VecAbfStamm=null;

private JFrame Frm;
private FileEingabe TxtFile;
private Text TxtVer;
private JButton BtnTabellen;
private JButton BtnOk;
private JButton BtnAbbruch;

private boolean bSaveKennung = false;

//private JCheckBox CbxSchnellimport;
private JCheckBox CbxKunden;
private JCheckBox CbxTest;
private JCheckBox CbxClean;
private JCheckBox CbxMC;
private JCheckBox CbxkeineStamm;
private JCheckBox CbxNurStamm;
//private JCheckBox CbxBefehlsmemos;
//private JCheckBox CbxFremdSprache;
//private JCheckBox CbxVersionsupdate;
private int iProg=0;
private String sProg=null;
private boolean bNormal=true;
private boolean bAll=false;
private boolean bWeb=false;
//private boolean bNurModul=false;
//private boolean bFremd=false;
//private boolean bTest2;
private int iError=0;
//public static final int cstNur = 1;
                                         //51..Hauptschirme mit Stt/Bew-Info
public static final int cstVersion = 83; //52..mit Programm und Version
                                         //53..mit von/bis, Stammimport
                                         //54..LDATE23, ANRStt
                                         //55..BOOL12
                                         //56..Sta_aic_Stamm aus Spalte
                                         //57..Spalten-Erweiterung (bits3,x,y,w,h,Stil)
                                         //58..Begriff: + Kennzeichen, Alias
	                                     //59..Darstellung: Align, HGap,...; Abits2
                                         //60..Spalte: SubFom
                                         //61..Web-bit, Prog bei begriff, Abfrage-Anzahl
										 //62..LDATE_VB
										 //63..WebLaenge
										 //64..Mod2Abf
										 //65..Spaltenreinigung mit Leerzeichen aus Kennung ersetzen
                                         //66..Aufgaben-Status		
                                         //67..Status-Erweiterung (Stt, Bild, ..)
										 //68..Spalte: SubAbf, SubMod
										 //69..Pos, bits4	
										 //70..ToggleSight	
										 //71..Sub1-Sub3
										 //72..web
	                                     //73..Aufgaben-Status-Änderung
										 //74..Sub-Abfragen
										 //75..Stt-Web-Parameter
										 //76..Abfrage-Stamm, Status-HS
										 //77..webIconOffen
                                         //78..Beg2_Z inkl. Reihe
										 //79..Sprache zu Sprache
									     //80..Eingabe/Var getrennt
										 //81..Formular-Abfrage (Beg2_Z)
										 //82..Spalte_Berechnung: Eingabe dazu
										 //83..SubSpalte
			
}

