/*
    All_Unlimited-Hauptmaske-Zeitraum.java

    NOTE: This file is a generated file.
          Do not modify it by hand!
*/

package All_Unlimited.Hauptmaske;

// add your custom import statements here
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Vector;

//import javax.swing.AbstractAction;
//import javax.swing.Action;
import javax.swing.JButton;
//import javax.swing.JCheckBox;
//import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import All_Unlimited.Allgemein.DateWOD;
import All_Unlimited.Allgemein.Global;
import All_Unlimited.Allgemein.Message;
import All_Unlimited.Allgemein.Static;
import All_Unlimited.Allgemein.Tabellenspeicher;
import All_Unlimited.Allgemein.Eingabe.ComboSort;
import All_Unlimited.Allgemein.Eingabe.Datum;
import All_Unlimited.Allgemein.Eingabe.SpinBox;
import All_Unlimited.Allgemein.Eingabe.SpinBoxJahr;
import All_Unlimited.Allgemein.Eingabe.SpinBoxList;
import All_Unlimited.Allgemein.Formular;
import java.awt.Dimension;
import javax.swing.border.EmptyBorder;
import java.awt.Insets;
import javax.swing.JScrollPane;
//import javax.swing.SwingConstants;

public class Zeitraum extends All_Unlimited.Allgemein.Formular
{

  public static Zeitraum get(Global rg)
{
	//rg.progInfo("!Zeitraum!.get");
        Zeitraum FomZR=Self;//(Zeitraum)rg.getSelf("Zeitraum");
        if (FomZR==null)
        {
          FomZR=new Zeitraum(rg);
          //rg.setSelf("Zeitraum",FomZR);
        }
	return FomZR;
}

/*public void show(Window w)
{
        //Static.centerComponent(thisFrame,w);
        show();
}*/

public void show()
{
  //g.progInfo("Zeitraum.show1:"+Static.sZeitart+ "/"+g.getVon()+"-"+g.getBis());
	String sZeitart=g.getZA(0);
  if(!CboZeitArt.getSelectedKennung().equals(sZeitart))
  {
    CboZeitArt.setSelectedKennung(sZeitart);
    showZeitArt();
  }

	//if (bLaden)
		Laden();
	//else
	//	bLaden=true;
  /*if(!CboZeitArt.getSelectedKennung().equals(Static.sZeitart))
  {
    CboZeitArt.setSelectedKennung(Static.sZeitart);
    showZeitArt();
  }*/
  //iArt=CboZeitArt.getSelectedAIC();
  //g.progInfo("Zeitraum.show:"+iArt);
  //g.progInfo("Zeitraum.show2:"+Static.sZeitart+ "/"+g.getVon()+"-"+g.getBis());
	super.show();
}

public static void free(Global g)
{
  //Zeitraum Self=(Zeitraum)g.getSelf("Zeitraum");
  if (Self!=null)
  {
          g.testInfo("Zeitraum.free");
          Self.thisFrame.dispose();
          Self=null;
  }
}

private Zeitraum(Global glob)
{
	super("Zeitraum",null,glob);
	//long lClock=Static.get_ms();
	Self=this;
	g=glob;
        //iNr++;
	//g.progInfo("!Zeitraum! "+iNr);
	DatBewVon = new Datum(g,"dd.MM.yyyy",(JFrame)this.thisFrame);
	DatBewBis = new Datum(g,"dd.MM.yyyy",(JFrame)this.thisFrame);
        DatBewVon.setFont(g.fontStandard);
        DatBewBis.setFont(g.fontStandard);

	//Kal = new Kalender((JFrame)thisFrame,g);
	CboZeitArt = new ComboSort(g);
	CboZeitArt.setSorted(false);
        CboZeitArt.setFont(g.fontStandard);
	//CboZeitArt.fillBegriffTable("Periode",false);

	//CboZeitArt.fillStammTable(g.TabStammtypen.posInhalt("Kennung","PERIODE")?g.TabStammtypen.getI("Aic"):0,false);
	CboZeitArt.fillCbo("Select aic_stamm,bezeichnung kennung"+g.AU_Bezeichnung2("Stamm","p2")+
			" from stammview2 p2 where aic_stammtyp="+g.iSttPeriode+" and kennung <>'offen'","Stamm",false,false);
		//fillCbo("Select aic_stamm,bezeichnung as kennung"+g.AU_Bezeichnung("Stamm","stammview")+
		//		" from stammview where aic_stammtyp ="+iStt+g.getReadMandanten(false),"Stamm",rbKein,false);

	//CboZeitArt.setEditable(50);
	//CboZeitArt.bString=false;


	//if (Static.iZeitart != 0)
	{
		CboZeitArt.setSelectedKennung(g.getZA(0));
		showZeitArt();
	}
	//g.clockInfo("ZR bis showZeitArt",lClock);
        // Woche
	//SbxBewWVon.setMinimum(1);
	//SbxBewWVon.setMaximum(53);
        SbxBewWVon.setEditable(false);
        SbxBewWVon.setFont(g.fontStandard);
	//SbxBewWBis.setMinimum(1);
	//SbxBewWBis.setMaximum(53);
        SbxBewWBis.setEditable(false);
        SbxBewWBis.setFont(g.fontStandard);
	//SbxBewWJVon.setMinimum(1800);
	//SbxBewWJVon.setMaximum(2400);
        SbxBewWJVon.setFont(g.fontStandard);
	//!SbxBewWJVon.setEditable(false);
	//SbxBewWJBis.setMinimum(1800);
	//SbxBewWJBis.setMaximum(2400);
        SbxBewWJBis.setFont(g.fontStandard);
	//!SbxBewWJBis.setEditable(false);
        // Monat
	//SbxBewMJVon.setMinimum(1800);
	//SbxBewMJVon.setMaximum(2400);
        SbxBewMJVon.setFont(g.fontStandard);
        CboBewVon.setPreferredSize(new Dimension(60,18));
	//!SbxBewMJVon.setEditable(false);
	//SbxBewMJBis.setMinimum(1800);
	//SbxBewMJBis.setMaximum(2400);
        SbxBewMJBis.setFont(g.fontStandard);
        CboBewBis.setPreferredSize(new Dimension(60,18));
	//!SbxBewMJBis.setEditable(false);
        //!CboBewVon = new ComboSort(g,ComboSort.Aic);
        CboBewVon.setFont(g.fontStandard);
	//!CboBewVon.fillBegriffTable("Monat",false);
	//!CboBewBis = new ComboSort(g,ComboSort.Aic);
        CboBewBis.setFont(g.fontStandard);
	//!CboBewBis.fillBegriffTable("Monat",false);
        // Jahr
	//SbxBewJVon.setMinimum(1800);
	//SbxBewJVon.setMaximum(2400);
        SbxBewJVon.setFont(g.fontStandard);
	//!SbxBewJVon.setEditable(false);
	//SbxBewJBis.setMinimum(1800);
	//SbxBewJBis.setMaximum(2400);
        SbxBewJBis.setFont(g.fontStandard);
	//!SbxBewJBis.setEditable(false);
        // Quartal
	//SbxBewQVon.setMinimum(1);
        SbxBewQVon.setFont(g.fontStandard);
	//SbxBewQBis.setMinimum(1);
        SbxBewQBis.setFont(g.fontStandard);
	//SbxBewQVon.setMaximum(4);
	//SbxBewQBis.setMaximum(4);
	//SbxBewQJVon.setMinimum(1800);
	//SbxBewQJVon.setMaximum(2400);
        SbxBewQJVon.setFont(g.fontStandard);
        SbxBewQVon.setEditable(false);
	//!SbxBewQJVon.setEditable(false);
	//SbxBewQJBis.setMinimum(1800);
	//SbxBewQJBis.setMaximum(2400);
        SbxBewQJBis.setFont(g.fontStandard);
        SbxBewQBis.setEditable(false);
	//!SbxBewQJBis.setEditable(false);
	//Laden();
	//g.clockInfo("ZR bis Laden",lClock);
	//bLaden = false;
	Build();
        //if (BtnOk != null)
        //  ((JFrame)thisFrame).getRootPane().setDefaultButton(BtnOk);
        //else if (BtnSpeichern != null)
        //  ((JFrame)thisFrame).getRootPane().setDefaultButton(BtnSpeichern);
        //if (BtnZRspeichern != null)
        //  ((JFrame)thisFrame).getRootPane().setDefaultButton(BtnZRspeichern);
	/*Static.addActionListener(BtnOk,new ActionListener()
	{
		public void actionPerformed(ActionEvent e)
		{
			//g.progInfo("! BtnOk");
			if(Setzen())
                          hide();
		}
	});

	Static.addActionListener(BtnAbbruch,new ActionListener()
	{
		public void actionPerformed(ActionEvent e)
		{
                  //g.progInfo("vor Laden");// notwendig zum schließen
                  //g.progInfo("BtnAbbruch:"+iArt);
                  CboZeitArt.setSelectedAIC(iArt);
                  Static.sZeitart=CboZeitArt.getSelectedKennung();
                  showZeitArt();
			//Laden();
                  //g.progInfo("nach Laden");
			hide();
                  //g.progInfo("nach hide");
		}
	});*/

        /*Action cancelKeyAction = new AbstractAction() {

			private static final long serialVersionUID = 2163078481306919562L;

		public void actionPerformed(ActionEvent e)
          {
            //Laden();
            hide();
          }
        };*/
        //Static.addActionListener(BtnBeenden,cancelKeyAction);
        //Static.Escape(BtnAbbruch != null ? BtnAbbruch:BtnBeenden,thisFrame,cancelKeyAction);
    String sZA=g.getZA(0);    
	if (sZA==null)
       g.setZA(0,"Tag");
	else if (sZA.equals("offen"))
	   g.setZA(0,"Monat");
	if (!Static.Gleich(sZA,g.getZA(0)))
		g.fixtestError("Zeitraum: ändere ZA von "+sZA+" auf "+g.getZA(0));
        //g.fixtestInfo("sZeitart="+g.sZeitart);
        getTButton(g.getZA(0)).setSelected(true);

	ActionListener AL=new ActionListener()
	{
		public void actionPerformed(ActionEvent ev)
		{
                  //Object Btn=ev.getSource();
                  String s=ev.getActionCommand();
                  if (s.equals("save"))
                    Setzen();
                  //if (s.equals("refresh"))
                  //  Laden();
                  if (s.equals("plus"))
                    setNextPeriod(true);
                  else if (s.equals("minus"))
                    setNextPeriod(false);
                  else if (s.equals("jetzt"))
                    setJetzt();
                  else if (s.equals("Tag") || s.equals("Woche") || s.equals("Monat") || s.equals("Quartal") || s.equals("Jahr"))
                  {
                	  //g.fixtestError("ändere ZA auf "+s);
                	  CboZeitArt.setSelectedKennung(s);
                  }
                  else if (s.equals("vonbis") || s.equals("Leiste"))
                  {
                    //CbxLeiste.setSelected(s.equals("Leiste"));
                    //g.progInfo("CbxLeiste="+CbxLeiste.isSelected()+"/"+s);
                    Static.bZR_Leiste=s.equals("Leiste");
                    checkLeiste();
                    //thisFrame.validate();
                  }
                  else if (s.equals("Init"))
                  {
                    g.TabPerioden = null;
                    BtnInfo.setText(" 0");
                    BtnInfo.setEnabled(false);
                  }
                  else if (s.equals("Und"))
                  {
                    addPeriode(g);
                    BtnInfo.setEnabled(true);
                    BtnInfo.setText(" " + g.TabPerioden.getAnzahlElemente(null));
                  }
                  else if (s.equals("Info"))
                    g.TabPerioden.showGrid("TabPerioden");
		}
	};
        //g.setAction(BtnSpeichern,"save",AL);
        g.setAction(BtnZRspeichern,"save",AL);
        //g.setAction(BtnRefresh,"refresh",AL);
        //Static.addActionListener(BtnZRrefresh,AL);
        g.setAction(getButton("Tb_ZRminus"),"minus",AL);
        g.setAction(getButton("Tb_ZRplus"),"plus",AL);
        g.setAction(getButton("Jetzt"),"jetzt",AL);
        g.setAction(getTButton("Tag"),"Tag",AL);
        g.setAction(getTButton("Woche"),"Woche",AL);
        g.setAction(getTButton("Monat"),"Monat",AL);
        g.setAction(getTButton("Quartal"),"Quartal",AL);
        g.setAction(getTButton("Jahr"),"Jahr",AL);
        g.setAction(getTButton("vonbis"),"vonbis",AL);
        g.setAction(getTButton("Leiste"),"Leiste",AL);

        getTButton("Quartal").addMouseListener(new MouseListener()
        {
            public void mousePressed(MouseEvent ev)
            {
            }
            public void mouseClicked(MouseEvent ev)
            {
              int iM=ev.getModifiers();
              g.defInfo2("mouseClicked:"+iM+"x"+ev.getClickCount());
              if(iM==4 && ev.getClickCount()==3)
                Formular.Rule_change(g,(JFrame)thisFrame);
            }
            public void mouseEntered(MouseEvent ev)
            {}
            public void mouseExited(MouseEvent ev)
            {}
            public void mouseReleased(MouseEvent ev)
            {
            }
        });

	CboZeitArt.addItemListener(new ItemListener()
	{
		public void itemStateChanged(ItemEvent e)
		{
			if(e.getStateChange()==ItemEvent.SELECTED && !CboZeitArt.getSelectedKennung().equals(g.getZA(0)))
			{
                //g.fixtestError("CboZeitArt.itemStateChanged:"+CboZeitArt.getSelectedKennung()+"/"+CboZeitArtOk);
				if (CboZeitArtOk)
				{
					//g.progInfo("!Zeitraum!.CboZeitArt.addItemListener");
					CboZeitArtOk=false;
					if (CboZeitArt.getSelectedKennung().equals("offen"))
					{
						DatBewVon.setDate(null);
						DatBewBis.setDate(null);
						CboZeitArt.setSelectedKennung("Tag");
					}
					else
						showZeitArt();
                        Zeitraum.PeriodeToVec(g, g.getZA(0), true);
                        setVonBis(g.getVon(),g.getBis());
					CboZeitArtOk=true;
				}
			}
		}
	});

        //if (Static.bAR)
          thisFrame.addWindowListener(new WindowListener()
          {
                public void windowClosed(WindowEvent e)
                {
                }
                public void windowOpened(WindowEvent e)
                {
                }
                public void windowClosing(WindowEvent e)
                {
                }
                public void windowIconified(WindowEvent e)
                {
                }
                public void windowDeiconified(WindowEvent e)
                {
                }
                public void windowActivated(WindowEvent e)
                {
                  if (Static.bRefreshStop)
                    Static.bRefreshStop=false;
                  else
                    Laden();
                }
                public void windowDeactivated(WindowEvent e)
                {
                }
          });
        g.setAction(BtnInit,"Init",AL);
        g.setAction(BtnUnd,"Und",AL);
        if (BtnInfo != null)
        {
          BtnInfo.setText(" 0");
          BtnInfo.setEnabled(false);
          g.setAction(BtnInfo,"Info",AL);
        }
	//g.clockInfo("ZR bis Ende",lClock);
}

public static void addPeriode(Global g)
{
  if (g.TabPerioden == null)
  {
    g.TabPerioden = new Tabellenspeicher(g, new String[] {"Titel", "von", "bis"});
    g.TabPerioden.setTitel("TabPerioden");
  }
  int iPos=g.TabPerioden.newLine(-1);
  g.TabPerioden.setInhalt(iPos,"Titel", g.getVonBis2());
  g.TabPerioden.setInhalt(iPos,"von", g.getVon());
  g.TabPerioden.setInhalt(iPos,"bis", g.getBis());
  //g.TabPerioden.showGrid("TabPerioden");
}

private void Build()
{
	//g.progInfo("!Zeitraum!.Build");
	//BtnOk = getButton("Ok");
        //BtnSpeichern = getButton("Speichern");
        BtnZRspeichern = getButton("ZRspeichern");
        //BtnZRspeichern.setBorder(null);
        //BtnZRspeichern.setOpaque(false);
	//if(BtnOk==null) BtnOk = new JButton();
	//BtnAbbruch = getButton("Abbruch");
	//if(BtnAbbruch==null) BtnAbbruch = new JButton();
	//BtnRefresh = getButton("Tb_Refresh");
        //BtnZRrefresh = getButton("ZRrefresh");
	//BtnBeenden = getButton("Beenden");

        BtnInit = getButton("Init");
        BtnUnd = getButton("Und2");
        BtnInfo = getButton("Info2");
        if (!g.Spezial())
        {
          if (BtnInit != null)
            BtnInit.setVisible(false);
          if (BtnUnd != null)
            BtnUnd.setVisible(false);
          if (BtnInfo != null)
            BtnInfo.setVisible(false);
        }
	//if(BtnBeenden==null) BtnBeenden = new JButton();
        /*JButton BtnXXX=getButton("+");
        if (BtnXXX != null)
        {
          BtnPlus=BtnXXX;
          BtnMinus=getButton("-");
        }
        else
        {*/
          //BtnPlus = getButton("Tb_ZRplus");
          //if(BtnPlus==null) BtnPlus = new JButton();
          //BtnMinus = getButton("Tb_ZRminus");
          //if(BtnMinus==null) BtnMinus = new JButton();
        //}
        //BtnJetzt = getButton("Jetzt");
	//if(BtnJetzt==null) BtnJetzt = new JButton();

        String sVon=g.getBegriffS("Label","von");
        String sBis=g.getBegriffS("Label","bis");

	PnlTag = new JPanel(new GridLayout(1,0,2,2));
	//JPanel Pnl = new JPanel(new BorderLayout(2,2));
        PnlTag.add(g.getLabelO(sVon+":"));
        PnlTag.add(DatBewVon);
        //PnlTag.add(new JLabel());
        PnlTag.add(g.getLabelO(sBis+":"));
	PnlTag.add(DatBewBis);
	PnlTag.add(new JLabel());

	PnlWoche = new JPanel(new GridLayout(1,0,2,2));
	/*Pnl = new JPanel(new BorderLayout(2,2));
	JPanel PnlVon = new JPanel(new GridLayout(1,0,2,2));
	JPanel PnlBis = new JPanel(new GridLayout(1,0,2,2));*/
        PnlWoche.add(g.getLabelO(sVon+":"));
	PnlWoche.add(SbxBewWVon);
	PnlWoche.add(SbxBewWJVon);
        PnlWoche.add(g.getLabelO(sBis+":"));
	PnlWoche.add(SbxBewWBis);
	PnlWoche.add(SbxBewWJBis);
        PnlWoche.add(new JLabel());
	/*Pnl.add("Center",PnlBis);
	Pnl.add("West",new JLabel("-"));
	PnlWoche.add(PnlVon);
	PnlWoche.add(Pnl);*/

	PnlQuartal = new JPanel(new GridLayout(1,0,2,2));
	/*Pnl = new JPanel(new BorderLayout(2,2));
	PnlVon = new JPanel(new GridLayout(1,0,2,2));
	PnlBis = new JPanel(new GridLayout(1,0,2,2));*/
        PnlQuartal.add(g.getLabelO(sVon+":"));
	PnlQuartal.add(SbxBewQVon);
	PnlQuartal.add(SbxBewQJVon);
        PnlQuartal.add(g.getLabelO(sBis+":"));
	PnlQuartal.add(SbxBewQBis);
	PnlQuartal.add(SbxBewQJBis);
        PnlQuartal.add(new JLabel());
	/*Pnl.add("Center",PnlBis);
	Pnl.add("West",new JLabel("-"));
	PnlQuartal.add(PnlVon);
	PnlQuartal.add(Pnl);*/

	/*PnlMonat = new JPanel(new GridLayout(1,0,2,2));
	Pnl = new JPanel(new BorderLayout(2,2));*/
	JPanel PnlVon = new JPanel(new BorderLayout(2,2));
	JPanel PnlBis = new JPanel(new BorderLayout(2,2));
        PnlMonat = new JPanel(new GridLayout(1,0,2,2));
         PnlVon.add("West",g.getLabelO("  "+sVon+":"));
         PnlVon.add("Center",CboBewVon);
         PnlVon.add("East",SbxBewMJVon);
        PnlMonat.add(PnlVon);
         PnlBis.add("West",g.getLabelO("  "+sBis+":"));
         PnlBis.add("Center",CboBewBis);
         PnlBis.add("East",SbxBewMJBis);
        PnlMonat.add(PnlBis);
        /*g.addLabel3(PnlMonat,sVon);
	PnlMonat.add(CboBewVon);
	PnlMonat.add(SbxBewMJVon);
        g.addLabel3(PnlMonat,sBis);
	PnlMonat.add(CboBewBis);
	PnlMonat.add(SbxBewMJBis);
        PnlMonat.add(new JLabel());*/

	/*Pnl.add("Center",PnlBis);
	Pnl.add("West",new JLabel("-"));
	PnlMonat.add(PnlVon);
	PnlMonat.add(Pnl);*/

	//PnlJahr  = new JPanel(new GridLayout(1,0,2,2));
        PnlJahr = new JPanel(new GridLayout(1,0,2,2));
	//Pnl = new JPanel(new BorderLayout(2,2));
	PnlJahr.add(g.getLabelO(sVon+":"));
        PnlJahr.add(SbxBewJVon);
        //PnlJahr.add(new JLabel());
        PnlJahr.add(g.getLabelO(sBis+":"));
	//Pnl.add("West",new JLabel("-"));
	PnlJahr.add(SbxBewJBis);
	PnlJahr.add(new JLabel());

	Pnl_Datum2 = getFrei("Datum2");
	//JPanel Pnl_Combo_Art = getFrei("Combo Art");


	//if(Pnl_Combo_Art!=null)
	//	Pnl_Combo_Art.add(CboZeitArt);

        /*CbxLeiste=getCheckbox("Leiste");
        if(CbxLeiste==null)
          CbxLeiste = new JCheckBox();
        //else
          CbxLeiste.addActionListener(new ActionListener()
          {
            public void actionPerformed(ActionEvent e)
            {
              checkLeiste();
            }
          });*/
        if (Static.bZR_Leiste)
        {
          //CbxLeiste.setSelected(true);
          if (BtnZRspeichern != null)
            BtnZRspeichern.setVisible(false);
        }
        if (Static.bZR_Leiste)
          getTButton("Leiste").setSelected(true);
        else
          getTButton("vonbis").setSelected(true);
	//showZeitArt();
}

      private void checkLeiste()
      {
        g.progInfo("CbxLeiste start");
        if (BtnZRspeichern != null)
          BtnZRspeichern.setVisible(!Static.bZR_Leiste);
        //if (BtnSpeichern != null)
        //  BtnSpeichern.setEnabled(!Static.bZR_Leiste);
        //Static.bZR_Leiste=CbxLeiste.isSelected();
        if (!Static.bZR_Leiste)
          Laden();
        showZeitArt();
        g.progInfo("CbxLeiste ende");
      }

private void BuildWochentagsleiste()
{
  if (PnlWTL==null)
  {
    PnlWTL = new JPanel(new BorderLayout());
    JPanel PnlWT1 = new JPanel(new GridLayout());
    SbxWocheWTL = new SpinBox(1,1,53,1,g.ColBackground);
    SbxWocheWTL.setFont(g.fontStandard);
    SbxWocheWTL.setEditable(false);
    //SbxWocheWTL.setPreferredSize(50, 10);
    SbxWocheWTL.addChangeListener(new ChangeListener()
    {
      public void stateChanged(ChangeEvent ae)
      {
        if (bWTL)
        {
          g.testInfo("SbxWocheWTL.spinBoxChangeEnd");
          DateWOD dt = new DateWOD(g.getVon());
          dt.set(Calendar.WEEK_OF_YEAR, SbxWocheWTL.getIntValue());
          //dt.set(Calendar.YEAR,SbxJahrWTL.getIntValue());
          Timestamp tsVon=dt.toTimestamp();
          dt.tomorrow();
          setVonBis(tsVon,dt.toTimestamp());
          DateWOD dtVon0 = new DateWOD(g.getVon());
          setWeekday(dtVon0.get(Calendar.DAY_OF_WEEK) - Calendar.MONDAY);
          //setWTTT();
        }
      }
    });
    PnlWT1.add(SbxWocheWTL);

    SbxJahrWTL = new SpinBoxJahr(g.ColBackground,2000);//new SpinBox(2007,1900,2100,1);
    SbxJahrWTL.setFont(g.fontStandard);
    //!SbxJahrWTL.setEditable(false);
    //SbxJahrWTL.setPreferredSize(50, 10);
    SbxJahrWTL.addChangeListener(new ChangeListener()
    {
      public void stateChanged(ChangeEvent ae)
      {
        if (bWTL)
        {
          g.testInfo("SbxJahrWTL.spinBoxChangeEnd");
          DateWOD dt = new DateWOD(g.getVon());
          dt.setYear(SbxJahrWTL.getIntValue());
          Timestamp tsVon=dt.toTimestamp();
          dt.tomorrow();
          setVonBis(tsVon,dt.toTimestamp());
          DateWOD dtVon0 = new DateWOD(g.getVon());
          setWeekday(dtVon0.get(Calendar.DAY_OF_WEEK) - Calendar.MONDAY);
          //setWTTT();
        }
      }
    });
    PnlWT1.add(SbxJahrWTL);
    JPanel PnlWT2 = new JPanel(new GridLayout());
    String[] sAryWochentage =
        {
        "Mo", "Di", "Mi", "Do", "Fr", "Sa", "So"};
    for (int i = 0; i < sAryWochentage.length; i++)
    {
      String sWochentag = g.getBegriffS("Wochentag", sAryWochentage[i]);
      BtnWTL[i] = new JButton(sWochentag.length() > 2 ? sWochentag.substring(0, 2) : sWochentag);
      BtnWTL[i].setFont(g.fontStandard);
      BtnWTL[i].setActionCommand("" + i);
      BtnWTL[i].setMargin(g.inset);
      PnlWT2.add(BtnWTL[i]);
      BtnWTL[i].addActionListener(new ActionListener()
      {
        public void actionPerformed(ActionEvent e)
        {
          DateWOD dtVon = new DateWOD(g.getVon());
          int iWtOld = new Integer(e.getActionCommand()).intValue();
          dtVon.setYear(SbxJahrWTL.getIntValue());
          dtVon.set(Calendar.WEEK_OF_YEAR,SbxWocheWTL.getIntValue());
          dtVon.setWeekday(iWtOld);
          //g.progInfo("yyyy.ww="+SbxJahrWTL.getIntValue()+"."+SbxWocheWTL.getIntValue()+"."+iWtOld);
          Timestamp tsVon=dtVon.toTimestamp();
          dtVon.tomorrow();
          if ((e.getModifiers()& ActionEvent.SHIFT_MASK+ActionEvent.CTRL_MASK)==0)
          {
            setVonBis(tsVon, dtVon.toTimestamp());
            setWeekday(iWtOld);
          }
          else if (dtVon.toTimestamp().after(g.getVon()))
          {
            setVonBis(NoN,dtVon.toTimestamp());
            boolean bCol=false;
            boolean bCol2=true;
            for (int i = 0; i < 7; i++)
            {
              if (BtnWTL[i].getBackground() == g.ColSelect)
                bCol = true;
              BtnWTL[i].setBackground(bCol && bCol2 || BtnWTL[i].equals(e.getSource()) ? g.ColSelect : i==6 ? g.ColHoliday:g.ColBackground);
              if (BtnWTL[i].equals(e.getSource()))
                bCol2 = false;
            }
          }
        }
      });
    }
    PnlWTL.add("Center",PnlWT2);
    PnlWTL.add("East",PnlWT1);
  }
  DateWOD dtVon0=new DateWOD(g.getVon());
  int iWtOld=dtVon0.get(Calendar.DAY_OF_WEEK)-Calendar.MONDAY;
  setWeekday(iWtOld);
  int iWeek=dtVon0.get(Calendar.WEEK_OF_YEAR);
  g.testInfo(dtVon0+"= Woche="+iWeek+", Jahr="+dtVon0.getYear()+", Monat="+dtVon0.getMonth());
  bWTL=false;
  SbxWocheWTL.setIntValue(iWeek);
  SbxJahrWTL.setIntValue(dtVon0.getYear()+(dtVon0.getMonth()==12 && iWeek<2 ? 1:dtVon0.getMonth()==1 && iWeek>51 ? -1:0));
  setWTTT(iWtOld);
  dtVon0.tomorrow();
  setVonBis(NoN,dtVon0.toTimestamp());
  bWTL=true;
}

private void setWTTT(int iWtOld)
{
  DateWOD dt = new DateWOD(g.getVon());
  //int iJahr=dt.get(DateWOD.YEAR);
  //g.progInfo("setWTTT1:"+dt);
  //dt.setWeekday(6);
  dt.setYear(SbxJahrWTL.getIntValue());
  //g.progInfo("setWTTT2:"+dt);
  dt.set(Calendar.WEEK_OF_YEAR,SbxWocheWTL.getIntValue());
  //g.progInfo("setWTTT3:"+dt);
  dt.setTimeZero();
  //int iMom=dt.get(dt.DAY_OF_WEEK)-dt.MONDAY;
  dt.setWeekday(0);
  //g.progInfo("setWTTT4:"+dt);
  for (int i = 0; i < 7; i++)
  {
    String s=g.Feiertag(dt);
    g.setBack(BtnWTL[i],iWtOld == i ? g.ColSelect:!s.equals("") || i==6 ? g.ColHoliday:g.ColBackground);
    //BtnWTL[i].setBackground(iWtOld == i ? g.ColSelect:!s.equals("") || i==6 ? g.ColHoliday:g.ColBackground);
    //BtnWTL[i].setText("<html>"+dt.Format("EE")+"<p><small><sub>"+dt.Format("dd.MM")+"</sub></small></html>");
    BtnWTL[i].setText("<html>"+dt.Format("EE")+"<br>"+dt.Format("dd.MM")+"</html>");
    BtnWTL[i].setToolTipText(dt.Format("dd.MM.yyyy")+(s.equals("")?"":" ("+s+")"));
    dt.tomorrow();
  }
}

private void setWeekday(int iWtOld)
{
  //for (int i = 0; i < 7; i++)
  //  BtnWTL[i].setBackground(g.ColBackground);
  if (iWtOld<0) iWtOld+=7;
  else if (iWtOld>6) iWtOld-=7;
  //BtnWTL[iWtOld].setBackground(g.ColSelect);
  setWTTT(iWtOld);
}

private boolean Aktuell()
{
  Date dtNow=new Date();
  return !dtNow.before(g.getVon()) && dtNow.before(g.getBis());
}

private void BuildWochenleiste(boolean bAktuell)
{
  g.progInfo("BuildWochenleiste");
  DateWOD DW=bAktuell ? new DateWOD():new DateWOD(g.getVon());
  DW.set(DateWOD.DAY_OF_WEEK,DateWOD.MONDAY);
  DW.setTimeZero();
  DateWOD DW2=new DateWOD(DW.getTime());
  //if (bAktuell) g.setVon(DW.toTimestamp());
  DW2.nextWeek();
  setVonBis(bAktuell ? DW.toTimestamp():NoN,DW2.toTimestamp());
  //Zeitraum.PeriodeToVec(g,"Woche",true);
  //DW.prevWeek();
  DW.prevWeek();
  DW.prevWeek();
  DW.prevWeek();
  String sZeitart=g.getZA(0);
  if (PnlWochenL == null)
  {
    PnlWochenL = new JPanel(new GridLayout());
    for(int i=0;i<7;i++)
      {
        BtnWL[i] = new JButton(Static.FormatTS(DW.toTimestamp(),sZeitart));
        g.setBack(BtnWL[i],i==3 ? g.ColSelect:g.ColBackground);
        //BtnWL[i].setBackground(i==3 ? g.ColSelect:g.ColBackground);
        //BtnWL[i].setText("<html>"+Static.FormatTS(DW.toTimestamp())+"<p><small><sub>"+DW.Format("dd.MM.yyyy")+"-</sub></small></html>");
        BtnWL[i].setText("<html>"+Static.FormatTS(DW.toTimestamp(),sZeitart)+"<br>"+DW.Format("dd.MM.yyyy")+"</html>");
        int iJahr=DW.getYearW();
        BtnWL[i].setActionCommand("" + (iJahr*100+DW.get(Calendar.WEEK_OF_YEAR)));
        BtnWL[i].setToolTipText(g.getBegriffS("Show","ab")+" "+DW.Format("dd.MMMM"));
        BtnWL[i].setMargin(g.inset);
        BtnWL[i].setFont(g.fontStandard);
        BtnWL[i].setEnabled(iJahr>=2000 && iJahr<2100);
        PnlWochenL.add(BtnWL[i]);
        DW.nextWeek();
        BtnWL[i].addActionListener( new ActionListener()
        {
          public void actionPerformed( ActionEvent e)
          {

            g.progInfo("BtnWL:"+e.getModifiers()+"/"+new Date(e.getWhen()));
            DateWOD dt=new DateWOD(g.getVon());
            int iWeek=new Integer(e.getActionCommand()).intValue();
            dt.setYear(iWeek/100);
            dt.set(Calendar.WEEK_OF_YEAR,iWeek%100);
            dt.set(Calendar.DAY_OF_WEEK,Calendar.MONDAY);
            Timestamp tsVon=dt.toTimestamp();
            dt.nextWeek();
            if ((e.getModifiers()& ActionEvent.SHIFT_MASK+ActionEvent.CTRL_MASK)==0)
            {
              setVonBis(tsVon, dt.toTimestamp());
              //for (int i = 0; i < 7; i++)
              //  BtnWL[i].setBackground(i == 3 ? g.ColSelect : g.ColBackground);
            }
            else if (dt.toTimestamp().after(g.getVon()))
            {
                setVonBis(NoN,dt.toTimestamp());
                /*boolean bCol = true;
                for (int i = 3; i < 7; i++)
                {
                  BtnWL[i].setBackground(bCol ? g.ColSelect : g.ColBackground);
                  if (BtnWL[i].equals(e.getSource()))
                    bCol = false;
                }*/
            }
            g.setZA(0,"Woche");
            Zeitraum.PeriodeToVec(g,"Woche",true);
            long lVon=g.getVon().getTime();
            long lBis=g.getBis().getTime();
            DateWOD DW=new DateWOD(lBis);
            DW.prevWeek();
            DW.prevWeek();
            DW.prevWeek();
            DW.prevWeek();
            for(int i = 0; i < 7; i++) {
              long lAkt=DW.getTimeInMillis();
              //BtnWL[i].setText(Static.FormatTS(DW.toTimestamp()));
              int iJahr=DW.getYearW();
              BtnWL[i].setText("<html>"+Static.FormatTS(DW.toTimestamp(),"Woche")+"<br>"+DW.Format("dd.MM.yyyy")+"</html>");
              BtnWL[i].setBackground(lAkt<lVon || lAkt>=lBis ? g.ColBackground:g.ColSelect);
              BtnWL[i].setActionCommand("" + (iJahr * 100 + DW.get(Calendar.WEEK_OF_YEAR)));
              BtnWL[i].setToolTipText(g.getBegriffS("Show", "ab") + " " + DW.Format("dd.MMMM"));
              BtnWL[i].setEnabled(iJahr>=2000 && iJahr<2100);
              DW.nextWeek();
            }
          }
        });
      }
  }
  else
  {
    for(int i=0;i<7;i++)
    {
      //BtnWL[i].setText(Static.FormatTS(DW.toTimestamp()));
      BtnWL[i].setText("<html>"+Static.FormatTS(DW.toTimestamp(),sZeitart)+"<br>"+DW.Format("dd.MM.yyyy")+"</html>");
      BtnWL[i].setActionCommand("" + (DW.getYearW()*100+DW.get(Calendar.WEEK_OF_YEAR)));
      BtnWL[i].setToolTipText(g.getBegriffS("Show","ab")+" "+DW.Format("dd.MMMM"));
      BtnWL[i].setBackground(i==3 ? g.ColSelect:g.ColBackground);
      DW.nextWeek();
    }
  }
}

private void BuildMonatsleiste()
      {
        if (PnlMonatL==null)
        {
          PnlMonatL=new JPanel(new BorderLayout());
          SbxJahrML = new SpinBoxJahr(g.ColBackground,2000);//new SpinBox(2007,1900,2100,1);
          //!SbxJahrML.setEditable(false);
          SbxJahrML.setFont(g.fontStandard);
          //SbxJahrML.setPreferredSize(50,10);
          SbxJahrML.addChangeListener(new ChangeListener()
          {
            public void stateChanged(ChangeEvent ae)
            {
              DateWOD dt = new DateWOD(g.getVon());
              dt.setYear(SbxJahrML.getIntValue());
              DateWOD dt2 = new DateWOD(g.getBis());
              dt2.setYear(SbxJahrML.getIntValue());
              if (dt2.before(dt))
                dt2.nextYear();
              setVonBis(dt.toTimestamp(),dt2.toTimestamp());
              //g.setVon(dt.toTimestamp());
              //g.setMonth(dt.toTimestamp());
            }
          });

          JPanel PnlMonate=new JPanel(new GridLayout());
          for (int i = 0; i < 12; i++)
          {
            String sMonat = g.getBegriffS("Monat", "" + (i + 1));
            BtnML[i] = new JButton(sMonat.length()<3?sMonat:sMonat.substring(0, 3));
            g.setBack(BtnML[i],g.ColBackground);
            //BtnML[i].setBackground(g.ColBackground);
            BtnML[i].setActionCommand("" + i);
            BtnML[i].setMargin(g.inset);
            BtnML[i].setFont(g.fontStandard);
            PnlMonate.add(BtnML[i]);

            BtnML[i].addActionListener(new ActionListener()
            {
              public void actionPerformed(ActionEvent e)
              {
                int iBtn = new Integer(e.getActionCommand()).intValue();
                DateWOD dt = new DateWOD(g.getVon());
                dt.setMonth(iBtn + 1);
                //g.setVon(dt.toTimestamp());
                boolean bOk=true;
                boolean bBis=(e.getModifiers()& ActionEvent.SHIFT_MASK+ActionEvent.CTRL_MASK)>0;
                if (bBis)
                {
                  dt.nextMonth();
                  bOk=dt.toTimestamp().after(g.getVon());
                  if (bOk)
                    setVonBis(NoN,dt.toTimestamp());
                }
                else
                {
                  g.setMonth(dt.toTimestamp());
                  setVonBis(NoN,NoN);
                }
                boolean bCol = false;
                boolean bCol2 = true;
                if (bOk)
                  for (int i = 0; i < 12; i++)
                  {
                    if (bBis && BtnML[i].getBackground() == g.ColSelect)
                      bCol = true;
                    BtnML[i].setBackground(bCol && bCol2 || BtnML[i].equals(e.getSource()) ? g.ColSelect : g.ColBackground);
                    if (BtnML[i].equals(e.getSource()))
                      bCol2 = false;
                  }
                g.setZA(0,"Monat");
                Zeitraum.PeriodeToVec(g, "Monat", true);
              }
            });
          }
          PnlMonatL.add("Center",PnlMonate);
          PnlMonatL.add("East",SbxJahrML);
        }
        for (int i = 0; i < 12; i++)
          BtnML[i].setBackground(g.ColBackground);
        int iWtOld=new DateWOD(g.getVon()).getMonth()-1;
        BtnML[iWtOld].setBackground(g.ColSelect);
        SbxJahrML.setIntValue(new DateWOD(g.getVon()).getYear());
        g.setMonth(g.getVon());
      }

      private void BuildQuartalsleiste()
            {
              if (PnlQL==null)
              {
                PnlQL=new JPanel(new BorderLayout());
                SbxJahrQL = new SpinBoxJahr(g.ColBackground,2000);//new SpinBox(2007,1900,2100,1);
                //!SbxJahrQL.setEditable(false);
                SbxJahrQL.setFont(g.fontStandard);
                //SbxJahrQL.setPreferredSize(50,10);
                SbxJahrQL.addChangeListener(new ChangeListener()
                {
                  public void stateChanged(ChangeEvent ae)
                  {
                    DateWOD dt = new DateWOD(g.getVon());
                    dt.setYear(SbxJahrQL.getIntValue());
                    DateWOD dt2 = new DateWOD(g.getBis());
                    dt2.setYear(SbxJahrQL.getIntValue());
                    if (dt2.before(dt))
                      dt2.nextYear();
                    /*Timestamp tsVon=dt.toTimestamp();
                    dt.nextMonth();
                    dt.nextMonth();
                    dt.nextMonth();*/
                    setVonBis(dt.toTimestamp(),dt2.toTimestamp());
                    g.setZA(0,"Quartal");
                    Zeitraum.PeriodeToVec(g, "Quartal", true);
                  }
                });

                JPanel PnlQuartale=new JPanel(new GridLayout());
                for (int i = 0; i < 4; i++)
                {
                  BtnQL[i] = new JButton(g.getBegriffS("Show","Quartal")+" "+(i+1));
                  g.setBack(BtnQL[i],g.ColBackground);
                  //BtnQL[i].setBackground(g.ColBackground);
                  BtnQL[i].setActionCommand("" + i);
                  BtnQL[i].setMargin(g.inset);
                  BtnQL[i].setFont(g.fontStandard);
                  PnlQuartale.add(BtnQL[i]);

                  BtnQL[i].addActionListener(new ActionListener()
                  {
                    public void actionPerformed(ActionEvent e)
                    {
                      int iBtn = new Integer(e.getActionCommand()).intValue();
                      //for (int i = 0; i < 4; i++)
                      //  BtnQL[i].setBackground(g.ColBackground);
                      //((JButton)e.getSource()).setBackground(g.ColSelect);
                      DateWOD dt = new DateWOD(g.getVon());
                      dt.setMonth(iBtn*3 + 1);
                      Timestamp tsVon=dt.toTimestamp();
                      dt.nextMonth();
                      dt.nextMonth();
                      dt.nextMonth();
                      boolean bBis=(e.getModifiers()& ActionEvent.SHIFT_MASK+ActionEvent.CTRL_MASK)>0;
                      boolean bOk= dt.toTimestamp().after(g.getVon());
                      if (!bBis)
                        setVonBis(tsVon,dt.toTimestamp());
                      else if (bOk)
                        setVonBis(NoN,dt.toTimestamp());
                      boolean bCol=false;
                      boolean bCol2 = true;
                      if (bOk || !bBis)
                        for (int i = 0; i < 4; i++)
                        {
                          if (bBis && BtnQL[i].getBackground() == g.ColSelect)
                            bCol = true;
                          BtnQL[i].setBackground(bCol && bCol2 || BtnQL[i].equals(e.getSource()) ? g.ColSelect : g.ColBackground);
                          if (BtnQL[i].equals(e.getSource()))
                            bCol2 = false;
                        }
                      g.setZA(0,"Quartal");
                      Zeitraum.PeriodeToVec(g, "Quartal", true);
                    }
                  });
                }
                PnlQL.add("Center",PnlQuartale);
                PnlQL.add("East",SbxJahrQL);
              }
              /*for (int i = 0; i < 4; i++)
                BtnQL[i].setBackground(g.ColBackground);
              int iWtOld=(new DateWOD(g.getVon()).getMonth()-1)/3;
              BtnQL[iWtOld].setBackground(g.ColSelect);*/
              SbxJahrQL.setIntValue(new DateWOD(g.getVon()).getYear());
              int iWtOld=(new DateWOD(g.getVon()).getMonth()-1)/3;
              BtnQL[iWtOld].doClick();
            }

            private void BuildJahresleiste(boolean bAktuell)
            {
              DateWOD DW=DateWOD.getNewYear(bAktuell ? new Date():g.getVon());
              //if (bAktuell) g.setVon(DW.toTimestamp());
              DateWOD DW2=new DateWOD(DW.getTime());
              //if (bAktuell)
                DW2.nextYear();
              //else
              //  DW2.tomorrow();
              //g.fixtestInfo("J3:"+g.getVon()+"-"+g.getBis());
              setVonBis(bAktuell ? DW.toTimestamp():NoN,DW2.toTimestamp());
              //g.fixtestInfo("J2:"+g.getVon()+"-"+g.getBis());
              //Zeitraum.PeriodeToVec(g,"Jahr",true);
              //DW.yesterday();
              DW.prevYear();
              DW.prevYear();
              DW.prevYear();
              if (PnlJahrL == null)
              {
                PnlJahrL = new JPanel(new GridLayout());
                for(int i=0;i<7;i++)
                  {
                    BtnJL[i] = new JButton(Static.FormatTS(DW.toTimestamp(),g.getZA(0)));
                    g.setBack(BtnJL[i],i==3 ? g.ColSelect:g.ColBackground);
                    //BtnJL[i].setBackground(i==3 ? g.ColSelect:g.ColBackground);
                    int iJahr=DW.getYear();
                    BtnJL[i].setActionCommand("" + iJahr);
                    BtnJL[i].setFont(g.fontStandard);
                    BtnJL[i].setEnabled(iJahr>=2000 && iJahr<2100);

                    //BtnJL[i].setToolTipText(g.getBegriff("Show","ab")+" "+DW.Format("dd.MMMM"));
                    BtnJL[i].setMargin(g.inset);
                    PnlJahrL.add(BtnJL[i]);
                    DW.nextYear();
                    BtnJL[i].addActionListener( new ActionListener()
                    {
                      public void actionPerformed( ActionEvent e)
                      {
                        DateWOD dt=new DateWOD(g.getVon());
                        int iYear=new Integer(e.getActionCommand()).intValue();
                        dt.setYear(iYear);
                        Timestamp tsVon=dt.toTimestamp();
                        dt.nextYear();
                        boolean bBis=(e.getModifiers()& ActionEvent.SHIFT_MASK+ActionEvent.CTRL_MASK)>0;
                        if (bBis)
                        {
                          if (dt.toTimestamp().after(g.getVon()))
                          {
                            setVonBis(NoN,dt.toTimestamp());
                            /*boolean bCol = true;
                            for (int i = 3; i < 7; i++)
                            {
                              BtnJL[i].setBackground(bCol ? g.ColSelect : g.ColBackground);
                              if (BtnJL[i].equals(e.getSource()))
                                bCol = false;
                            }*/
                          }
                        }
                        else
                        {
                          setVonBis(tsVon, dt.toTimestamp());
                          /*for(int i = 0; i < 7; i++) {
                            BtnJL[i].setText("" + (iYear+i-3));
                            BtnJL[i].setActionCommand("" + (iYear+i-3));
                            BtnJL[i].setBackground(i==3 ? g.ColSelect:g.ColBackground);
                          }*/
                        }
                        long lVon=g.getVon().getTime();
                        long lBis=g.getBis().getTime();
                        DateWOD DW=new DateWOD(lBis);
                        DW.prevYear();
                        DW.prevYear();
                        DW.prevYear();
                        DW.prevYear();
                        for(int i = 0; i < 7; i++) {
                          long lAkt = DW.getTimeInMillis();
                          int iJahr=DW.getYear();
                          BtnJL[i].setText(""+iJahr);
                          BtnJL[i].setActionCommand(""+iJahr);
                          BtnJL[i].setBackground(lAkt < lVon || lAkt >= lBis ? g.ColBackground : g.ColSelect);
                          BtnJL[i].setEnabled(iJahr>=2000 && iJahr<2100);
                          DW.nextYear();
                        }
                        g.setZA(0,"Jahr");
                        Zeitraum.PeriodeToVec(g,"Jahr",true);
                      }
                    });
                  }
              }
              else
              {
                for (int i = 0; i < 7; i++)
                {
                  BtnJL[i].setText("" + (DW.getYear()));
                  BtnJL[i].setActionCommand("" + (DW.getYear()));
                  BtnJL[i].setBackground(i==3 ? g.ColSelect:g.ColBackground);
                  DW.nextYear();
                }
              }
            }

private void showZeitArt()
{
  	//g.fixtestInfo("Zeitraum.showZeitArt");
	if(Pnl_Datum2!=null)
	{
		Pnl_Datum2.removeAll();
		Pnl_Datum2.setLayout(new BorderLayout());
		//Static.iZeitart = CboZeitArt.getSelectedAIC();
		String sArt = CboZeitArt.getSelectedKennung();
                boolean bLeiste=Static.bZR_Leiste;
		//g.fixtestError("showZeitArt "+g.getZA(0)+"->"+sArt);
		g.setZA(0,sArt);
                String sOrt="Center";//bLeiste ? "Center":"South";
		if(sArt.equals("Tag") || sArt.equals("offen"))
		{
                  if (bLeiste) BuildWochentagsleiste();
                  Pnl_Datum2.add(sOrt,bLeiste? PnlWTL:PnlTag);
                  DatBewVon.setDate(sArt.equals("offen") || g.getVon()==null?null:g.getVon());
                  DatBewBis.setDate(sArt.equals("offen") || g.getBis()==null?null:new Date(new DateWOD(g.getBis()).yesterday()));
		}
		else if(sArt.equals("Woche"))
                {
                  if (bLeiste)BuildWochenleiste(Aktuell());
                  DateWOD CalVon=new DateWOD(g.getVon());
                  DateWOD CalBis=new DateWOD(g.getBis());
                  CalBis.yesterday();
                  SbxBewWVon.setIntValue(CalVon.get(Calendar.WEEK_OF_YEAR));
                  SbxBewWBis.setIntValue(CalBis.get(Calendar.WEEK_OF_YEAR));
                  SbxBewWJVon.setIntValue(CalVon.getYearW());
                  SbxBewWJBis.setIntValue(CalBis.getYearW());
                  Pnl_Datum2.add(sOrt, bLeiste? PnlWochenL:PnlWoche);
                }
		else if(sArt.equals("Monat"))
                {
                  if (bLeiste) BuildMonatsleiste();
                  DateWOD CalVon=new DateWOD(g.getVon());
                  DateWOD CalBis=new DateWOD(g.getBis());
                  CalBis.yesterday();
                  CboBewVon.setSelectedKennung((CalVon.getMonth()));
                  CboBewBis.setSelectedKennung((CalBis.getMonth()));
                  SbxBewMJVon.setIntValue(CalVon.get(Calendar.YEAR));
                  SbxBewMJBis.setIntValue(CalBis.get(Calendar.YEAR));
                  Pnl_Datum2.add(sOrt, bLeiste? PnlMonatL:PnlMonat);
                }
		else if(sArt.equals("Quartal"))
                {
                  if (bLeiste) BuildQuartalsleiste();
                  DateWOD CalVon=new DateWOD(g.getVon());
                  DateWOD CalBis=new DateWOD(g.getBis());
                  CalBis.yesterday();
                  SbxBewQVon.setIntValue((CalVon.getMonth()-1)/3+1);
                  SbxBewQBis.setIntValue((CalBis.getMonth()-1)/3+1);
                  SbxBewQJVon.setIntValue(CalVon.getYear());
                  SbxBewQJBis.setIntValue(CalBis.getYear());
                  Pnl_Datum2.add(sOrt, bLeiste? PnlQL:PnlQuartal);
                }
		else if(sArt.equals("Jahr"))
                {
                  //g.fixtestInfo("J1:"+g.getVon()+"-"+g.getBis());
                  if (bLeiste) BuildJahresleiste(false);
                  //g.fixtestInfo("J2:"+g.getVon()+"-"+g.getBis());
                  DateWOD CalVon=new DateWOD(g.getVon());
                  DateWOD CalBis=new DateWOD(g.getBis());
                  CalBis.yesterday();
                  //g.fixtestInfo("Jahr:"+CalVon+"-"+CalBis);
                  SbxBewJVon.setIntValue(CalVon.getYear());
                  SbxBewJBis.setIntValue(CalBis.getYear());
                  Pnl_Datum2.add(sOrt, bLeiste? PnlJahrL:PnlJahr);
                }
                if (Rand1!=null)
                {
                  Pnl_Datum2.remove(Rand1);
                  Pnl_Datum2.remove(Rand2);
                  Rand1=null;
                }
                if (!bLeiste)
                {
                  Rand1 = new JScrollPane(new JLabel());
                  Rand1.setBorder(new EmptyBorder(/*bLeiste ? new Insets(0, 0, 0, 0) :*/ new Insets(3, 0, 3, 0)));
                  Pnl_Datum2.add("South", Rand1);
                  Rand2 = new JScrollPane(new JLabel());
                  Rand2.setBorder(new EmptyBorder(/*bLeiste ? new Insets(0, 0, 0, 0) :*/ new Insets(3, 0, 3, 0)));
                  Pnl_Datum2.add("North", Rand2);
                }
                //Pnl_Datum2.validate();
                thisFrame.validate();
                if(thisFrame.isShowing())
		{
			//bLaden = false;
                        Pnl_Datum2.repaint();
                        //g.progInfo("Pnl_Datum2.repaint");
                        //Static.repaint(Self.thisFrame);
                        //Dimension dim=Self.thisFrame.getSize();
                        //Self.thisFrame.setSize(10,10);
                        //Self.thisFrame.setSize(dim);
			//hide();
			//g.progInfo("!Zeitraum!.showZeitArt: show");
			//show();
		}
	}
	//g.progInfo("showZeitArt:"+DatBewVon.getDate()+" - "+DatBewBis.getDate());
}

private void Laden()
{
  //g.fixtestInfo("Zeitraum.Laden");
	DateWOD CalVon = new DateWOD();
	DateWOD CalBis = new DateWOD();
	if(g.getVon()!=null && g.getBis()!=null)
	{
		CalVon.setFirstDayOfWeek(Calendar.MONDAY);
		CalBis.setFirstDayOfWeek(Calendar.MONDAY);
		CalVon.setMinimalDaysInFirstWeek(4);
		CalBis.setMinimalDaysInFirstWeek(4);

		CalVon.setTime(g.getVon());
		CalBis.setTime(g.getBis());
	}
	CalBis.yesterday();
	DatBewVon.setDate(g.getVon()!=null?CalVon.getTime():null);
	DatBewBis.setDate(g.getBis()!=null?CalBis.getTime():null);

	int iW=CalVon.get(Calendar.WEEK_OF_YEAR);
	int iM=CalVon.get(Calendar.MONTH);
        SbxBewWVon.setIntValue(iW);
	SbxBewWBis.setIntValue(CalBis.get(Calendar.WEEK_OF_YEAR));
	CboBewVon.setSelectedKennung((iM+1));
	CboBewBis.setSelectedKennung((CalBis.get(Calendar.MONTH)+1));
	int iWJVon=CalVon.getYearW();//(Calendar.YEAR)-(iM==0 && iW>5?1:iM==11 && iW==1?-1:0);
        //g.testInfo("von="+g.getVon()+"->"+iW+"/"+iM+"/"+iWJVon);
	//g.progInfo("iWJVon="+iWJVon+", Week="+iW);
	SbxBewWJVon.setIntValue(iWJVon);
	iW=CalBis.get(Calendar.WEEK_OF_YEAR);
	iM=CalBis.get(Calendar.MONTH);
	//g.progInfo("Month="+iM+", WEEK_OF_YEAR="+iW);
	SbxBewWJBis.setIntValue(CalBis.getYearW());//(Calendar.YEAR)-(iM==0 && iW>6?1:iM==11 && iW==1?-1:0));
        //g.testInfo("bis="+g.getBis()+"->"+iW+"/"+iM+"/"+SbxBewWJBis.getValue());
	SbxBewMJVon.setIntValue(CalVon.get(Calendar.YEAR));
	SbxBewMJBis.setIntValue(CalBis.get(Calendar.YEAR));
	SbxBewQVon.setIntValue((CalVon.getMonth()-1)/3+1);
	SbxBewQBis.setIntValue((CalBis.getMonth()-1)/3+1);
	SbxBewQJVon.setIntValue(CalVon.get(Calendar.YEAR));
	SbxBewQJBis.setIntValue(CalBis.get(Calendar.YEAR));
	SbxBewJVon.setIntValue(CalVon.get(Calendar.YEAR));
	SbxBewJBis.setIntValue(CalBis.get(Calendar.YEAR));
	CalBis.tomorrow();
        //g.fixtestInfo("vor setVonBis(NoN,NoN)");
        setVonBis(NoN,NoN);
        //g.fixtestInfo("nach setVonBis(NoN,NoN)");
        showZeitArt();
        //g.fixtestInfo("nach showZeitArt");
}

private boolean Setzen()
{
	//g.progInfo("!Zeitraum!.Setzen");
	String sArt = CboZeitArt.getSelectedKennung();
	boolean bOk = sArt.equals("Tag") ? DatBewVon.isValid2() && DatBewBis.isValid2() && (g.Prog() || !DatBewVon.isNull() && !DatBewBis.isNull()):true;
	//g.testInfo("Zeitraum.Setzen:"+sArt+"/"+bOk);
	DateWOD CalVon = null;
	DateWOD CalBis = null;
	if(bOk)
	{
		//g.progInfo("Setzen: "+sArt+"->"+DatBewVon.getDate()+" - "+DatBewBis.getDate());
		if(!sArt.equals("offen") && (!sArt.equals("Tag") || DatBewVon.getDate()!=null || DatBewBis.getDate()!=null))
		{
			CalVon = new DateWOD();
			CalBis = new DateWOD();

			if(sArt.equals("Tag"))
			{
				if(DatBewVon.isNull())
					CalVon=null;
				else
					CalVon.setTime(DatBewVon.getDate());

				if(DatBewBis.isNull())
					CalBis=null;
				else
				{
					CalBis.setTime(DatBewBis.getDate());
					CalBis.tomorrow();
				}
			}
			else if(sArt.equals("Woche"))
			{
				CalVon.clear();
				CalBis.clear();
				CalVon.setFirstDayOfWeek(Calendar.MONDAY);
				CalVon.setMinimalDaysInFirstWeek(4);
				CalBis.setFirstDayOfWeek(Calendar.MONDAY);
				CalBis.setMinimalDaysInFirstWeek(4);

				CalVon.setYear(SbxBewWJVon.getIntValue());
				CalVon.set(Calendar.WEEK_OF_YEAR,SbxBewWVon.getIntValue());
				CalVon.set(Calendar.DAY_OF_WEEK,Calendar.MONDAY);
				CalVon.setHours(0);
				CalVon.setMinutes(0);
				CalVon.setSeconds(0);
				CalVon.setMilliSeconds(0);

				CalBis.setYear(SbxBewWJBis.getIntValue());
				CalBis.set(Calendar.WEEK_OF_YEAR,SbxBewWBis.getIntValue());
				CalBis.set(Calendar.DAY_OF_WEEK,Calendar.MONDAY);
				CalBis.setHours(0);
				CalBis.setMinutes(0);
				CalBis.setSeconds(0);
				CalBis.setMilliSeconds(0);
                                CalBis.nextWeek();
                                //g.testInfo("von="+CalVon+"->"+SbxBewWVon.getIntValue()+"/"+SbxBewWJVon.getIntValue());
                                //g.testInfo("bis="+CalBis+"->"+SbxBewWBis.getIntValue()+"/"+SbxBewWJBis.getIntValue());
			}
			else if(sArt.equals("Monat"))
			{
				CalVon.setDate(iStartTag);
				CalVon.setMonth(new Integer(CboBewVon.getSelectedKennung()).intValue()-(iStartTag>15?1:0));
				CalVon.setYear(SbxBewMJVon.getIntValue());
				CalVon.setHours(0);
				CalVon.setMinutes(0);
				CalVon.setSeconds(0);
				CalVon.setMilliSeconds(0);

				CalBis.setDate(iStartTag);
				CalBis.setMonth(new Integer(CboBewBis.getSelectedKennung()).intValue()+(iStartTag>15?0:1));
				CalBis.setYear(SbxBewMJBis.getIntValue());
				CalBis.setHours(0);
				CalBis.setMinutes(0);
				CalBis.setSeconds(0);
				CalBis.setMilliSeconds(0);
			}
			else if(sArt.equals("Quartal"))
			{
				CalVon.setDate(1);
				CalVon.setMonth((SbxBewQVon.getIntValue()-1)*3+1);
				CalVon.setYear(SbxBewQJVon.getIntValue());
				CalVon.setHours(0);
				CalVon.setMinutes(0);
				CalVon.setSeconds(0);
				CalVon.setMilliSeconds(0);

				CalBis.setDate(1);
				CalBis.setMonth(SbxBewQBis.getIntValue()*3+1);
				CalBis.setYear(SbxBewQJBis.getIntValue());
				CalBis.setHours(0);
				CalBis.setMinutes(0);
				CalBis.setSeconds(0);
				CalBis.setMilliSeconds(0);
			}
			else if(sArt.equals("Jahr"))
			{
				CalVon.setDate(1);
				CalVon.setMonth(1);
				CalVon.setYear(SbxBewJVon.getIntValue());
				CalVon.setHours(0);
				CalVon.setMinutes(0);
				CalVon.setSeconds(0);
				CalVon.setMilliSeconds(0);

				CalBis.setDate(1);
				CalBis.setMonth(1);
				CalBis.setYear(SbxBewJBis.getIntValue()+1);
				CalBis.setHours(0);
				CalBis.setMinutes(0);
				CalBis.setSeconds(0);
				CalBis.setMilliSeconds(0);
			}

			bOk = bOk && CalVon==null || CalBis==null || CalVon.getTime().getTime()<CalBis.getTime().getTime();
			//g.testInfo(CalVon+"-"+CalBis+":"+bOk);
		}
	}
	if(bOk)
	{
		setVonBis(CalVon!=null?CalVon.toTimestamp():null,CalBis!=null?CalBis.toTimestamp():null);
		//g.progInfo("Setzen Zeitraum:"+sArt+"/"+g.getVon()+" - "+g.getBis()+" -> "+g.getLDATE());
                //g.checkBereich();
		//Laden();
		PeriodeToVec(g,sArt);
		//g.debugInfo("Von:"+g.getVon()+" / Bis:"+g.getBis());
	}
	else
		new Message(Message.ERROR_MESSAGE,(JFrame)thisFrame,g,10).showDialog("Falsche Angaben");
	return bOk;
}

      private void setVonBis(java.sql.Timestamp dtV,java.sql.Timestamp dtB)
      {
        //g.progInfo("setVonBis:"+dtV+"-"+dtB);
        if (dtV != NoN && dtB != NoN)
          g.setVonBis(dtV,dtB);
        else if (dtV != NoN)
          g.setVon(dtV);
        else if (dtB != NoN)
          g.setBis(dtB);
        setTitle((g.Def()?"Zeitraum - ":"")+g.getVonBis("dd.MM.yyyy",true));
      }

private void setJetzt()
{
     //String sArt = CboZeitArt.getSelectedKennung();
     DateWOD dt=new DateWOD();
     int iJahr=dt.getYear();

     // Tag
     DatBewVon.setDate(dt.toDate());
     DatBewBis.setDate(dt.toDate());
     // Woche
     SbxBewWVon.setIntValue(dt.get(GregorianCalendar.WEEK_OF_YEAR));
     SbxBewWBis.setIntValue(dt.get(GregorianCalendar.WEEK_OF_YEAR));
     SbxBewWJVon.setIntValue(iJahr);
     SbxBewWJBis.setIntValue(iJahr);
     // Monat
     SbxBewMJVon.setIntValue(iJahr);
     SbxBewMJBis.setIntValue(iJahr);
     CboBewVon.setSelectedKennung(dt.getMonth());
     CboBewBis.setSelectedKennung(dt.getMonth());
     // Quartal
     SbxBewQVon.setIntValue((dt.getMonth()+2)/3);
     SbxBewQBis.setIntValue((dt.getMonth()+2)/3);
     SbxBewQJVon.setIntValue(iJahr);
     SbxBewQJBis.setIntValue(iJahr);
     // Jahr
     SbxBewJVon.setIntValue(iJahr);
     SbxBewJBis.setIntValue(iJahr);

     String sArt = CboZeitArt.getSelectedKennung();
     boolean bLeiste=Static.bZR_Leiste;
     if (bLeiste)
     {
       if (sArt.equals("Tag"))
       {
         int iWtOld=dt.get(Calendar.DAY_OF_WEEK)-Calendar.MONDAY;
         SbxWocheWTL.setIntValue(dt.get(Calendar.WEEK_OF_YEAR));
         SbxJahrWTL.setIntValue(dt.getYearW());
         BtnWTL[iWtOld].doClick();
       }
       else if (sArt.equals("Woche"))
         BuildWochenleiste(true);
       else if (sArt.equals("Monat"))
       {
         //BuildMonatsleiste();
         int iWtOld=dt.getMonth()-1;
         SbxJahrML.setIntValue(iJahr);
         setVonBis(dt.toTimestamp(),NoN);
         BtnML[iWtOld].doClick();
       }
       else if (sArt.equals("Quartal"))
       {
         int iWtOld=(dt.getMonth()-1)/3;
         SbxJahrQL.setIntValue(iJahr);
         //g.setVon(dt.toTimestamp());
         BtnQL[iWtOld].doClick();
       }
       else if (sArt.equals("Jahr"))
         BuildJahresleiste(true);
     }
}

private void setNextPeriod(boolean bPlus)
{
	//g.progInfo("!Zeitraum!.setNextPeriod");
	String sArt = CboZeitArt.getSelectedKennung();
        boolean bLeiste=Static.bZR_Leiste;
        if (bLeiste)
        {
          if(sArt.equals("Tag"))
          {
            DateWOD dt = new DateWOD(g.getVon());
            if (bPlus)
            {
              dt.tomorrow();
              Timestamp tsVon=dt.toTimestamp();
              dt.tomorrow();
              setVonBis(tsVon,dt.toTimestamp());
            }
            else
            {
              Timestamp tsBis=dt.toTimestamp();
              dt.yesterday();
              setVonBis(dt.toTimestamp(),tsBis);
            }
            BuildWochentagsleiste();
          }
          else if(sArt.equals("Woche"))
          {
            BtnWL[bPlus?4:2].doClick();
          }
          else if(sArt.equals("Monat"))
          {
            int iWtOld=new DateWOD(g.getVon()).getMonth()-(bPlus ? 0:2);
            if (iWtOld>11)
            {
              iWtOld=0;
              DateWOD dt = new DateWOD(g.getVon());
              dt.setYear(dt.getYear()+1);
              SbxJahrML.setIntValue(dt.getYear());
              setVonBis(dt.toTimestamp(),NoN);
            }
            else if (iWtOld<0)
            {
              iWtOld=11;
              DateWOD dt = new DateWOD(g.getVon());
              dt.setYear(dt.getYear()-1);
              SbxJahrML.setIntValue(dt.getYear());
              setVonBis(dt.toTimestamp(),NoN);
            }
            BtnML[iWtOld].doClick();
          }
          else if(sArt.equals("Quartal"))
          {
            int iWtOld=(new DateWOD(g.getVon()).getMonth()-1)/3+(bPlus ? 1:-1);
            if (iWtOld>3)
            {
              iWtOld=0;
              DateWOD dt = new DateWOD(g.getVon());
              dt.setYear(dt.getYear()+1);
              SbxJahrQL.setIntValue(dt.getYear());
              //g.setVon(dt.toTimestamp());
            }
            else if (iWtOld<0)
            {
              iWtOld=3;
              DateWOD dt = new DateWOD(g.getVon());
              dt.setYear(dt.getYear()-1);
              SbxJahrQL.setIntValue(dt.getYear());
              //g.setVon(dt.toTimestamp());
            }
            BtnQL[iWtOld].doClick();
          }
          else if(sArt.equals("Jahr"))
          {
            BtnJL[bPlus?4:2].doClick();
          }
        }
	else if(sArt.equals("Tag"))
	{
		DateWOD dwv = new DateWOD(DatBewVon.getMillis());
		DateWOD dwb = new DateWOD(DatBewBis.getMillis());
		if(bPlus)
		{
			dwv.tomorrow();
			dwb.tomorrow();
		}
		else
		{
			dwv.yesterday();
			dwb.yesterday();
		}
		DatBewVon.setDate(dwv.toDate());
		DatBewBis.setDate(dwb.toDate());
	}
	else if(sArt.equals("Monat"))
	{
		int imv = new Integer(CboBewVon.getSelectedKennung()).intValue();
		int imb = new Integer(CboBewBis.getSelectedKennung()).intValue();

		if(bPlus)
		{
			imv++;
			imb++;
			if(imv>12)
			{
				CboBewVon.setSelectedKennung(1);
				SbxBewMJVon.setIntValue(SbxBewMJVon.getIntValue()+1);
			}
			else
				CboBewVon.setSelectedKennung((imv));

			if(imb>12)
			{
				CboBewBis.setSelectedKennung(1);
				SbxBewMJBis.setIntValue(SbxBewMJBis.getIntValue()+1);
			}
			else
				CboBewBis.setSelectedKennung((imb));
		}
		else
		{
			imv--;
			imb--;
			if(imv<1)
			{
				CboBewVon.setSelectedKennung(12);
				SbxBewMJVon.setIntValue(SbxBewMJVon.getIntValue()-1);
			}
			else
				CboBewVon.setSelectedKennung((imv));

			if(imb<1)
			{
				CboBewBis.setSelectedKennung(12);
				SbxBewMJBis.setIntValue(SbxBewMJBis.getIntValue()-1);
			}
			else
				CboBewBis.setSelectedKennung((imb));
		}
	}
	else if(sArt.equals("Jahr"))
	{
		SbxBewJVon.setIntValue(SbxBewJVon.getIntValue()+(bPlus?1:-1));
		SbxBewJBis.setIntValue(SbxBewJBis.getIntValue()+(bPlus?1:-1));
	}
	else if(sArt.equals("Woche"))
	{
		int iwv = SbxBewWVon.getIntValue();
		int iwb = SbxBewWBis.getIntValue();

		if(bPlus)
		{
			DateWOD dw = new DateWOD(SbxBewWJVon.getIntValue(), 1, 1);
			int iMaxWeeksVon = dw.getActualMaximum(GregorianCalendar.WEEK_OF_YEAR);
			dw.setYear(SbxBewWJBis.getIntValue());
			int iMaxWeeksBis = dw.getActualMaximum(GregorianCalendar.WEEK_OF_YEAR);

			iwv++;
			iwb++;
			if(iwv>iMaxWeeksVon)
			{
				SbxBewWVon.setIntValue(1);
				SbxBewWJVon.setIntValue(SbxBewWJVon.getIntValue()+1);
			}
			else
				SbxBewWVon.setIntValue(iwv);

			if(iwb>iMaxWeeksBis)
			{
				SbxBewWBis.setIntValue(1);
				SbxBewWJBis.setIntValue(SbxBewWJBis.getIntValue()+1);
			}
			else
				SbxBewWBis.setIntValue(iwb);
		}
		else
		{
			iwv--;
			iwb--;
			if(iwv<1)
			{
				SbxBewWJVon.setIntValue(SbxBewWJVon.getIntValue()-1);
				DateWOD dw = new DateWOD(SbxBewWJVon.getIntValue(), 1, 1);
				SbxBewWVon.setIntValue(dw.getActualMaximum(GregorianCalendar.WEEK_OF_YEAR));
			}
			else
				SbxBewWVon.setIntValue(iwv);

			if(iwb<1)
			{
				SbxBewWJBis.setIntValue(SbxBewWJBis.getIntValue()-1);
				DateWOD dw = new DateWOD(SbxBewWJBis.getIntValue(), 1, 1);
				SbxBewWBis.setIntValue(dw.getActualMaximum(GregorianCalendar.WEEK_OF_YEAR));
			}
			else
				SbxBewWBis.setIntValue(iwb);
		}
	}
	else if(sArt.equals("Quartal"))
	{
		int iqv = SbxBewQVon.getIntValue();
		int iqb = SbxBewQBis.getIntValue();

		if(bPlus)
		{
			iqv++;
			iqb++;

			if(iqv>4)
			{
				SbxBewQVon.setIntValue(1);
				SbxBewQJVon.setIntValue(SbxBewQJVon.getIntValue()+1);
			}
			else
				SbxBewQVon.setIntValue(iqv);

			if(iqb>4)
			{
				SbxBewQBis.setIntValue(1);
				SbxBewQJBis.setIntValue(SbxBewQJBis.getIntValue()+1);
			}
			else
				SbxBewQBis.setIntValue(iqb);
		}
		else
		{
			iqv--;
			iqb--;

			if(iqv<1)
			{
				SbxBewQVon.setIntValue(4);
				SbxBewQJVon.setIntValue(SbxBewQJVon.getIntValue()-1);
			}
			else
				SbxBewQVon.setIntValue(iqv);

			if(iqb<1)
			{
				SbxBewQBis.setIntValue(4);
				SbxBewQJBis.setIntValue(SbxBewQJBis.getIntValue()-1);
			}
			else
				SbxBewQBis.setIntValue(iqb);
		}
	}

}

public static void PeriodeToVec(Global g,String sPeriode)
{
	PeriodeToVec(g,sPeriode,false,false);
}

public static void PeriodeToVec(Global g,String sPeriode,boolean bGanz)
{
        PeriodeToVec(g,sPeriode,bGanz,false);
}

public static void PeriodeToVec(Global g,String sPeriode,boolean bGanz,boolean bSo)
{
  //g.fixtestError("PeriodeToVec:"+sPeriode+"/"+bGanz+"/"+bSo);
  /*if (sPeriode.equals("offen"))
    g.progInfo("offen:"+g.VecPerioden);
  else*/ if (sPeriode==null || sPeriode.equals("offen") || g.getVon()==null || g.getBis()==null)
		g.setVecPer(null);
	else
	{
		Vector<Timestamp> Vec=new Vector<Timestamp>();
		DateWOD dtVon= new DateWOD(g.getVon());
		long lBis=g.getBis().getTime();
		if (sPeriode.equals("Monat"))
			dtVon.setDate(iStartTag);
		else if (sPeriode.equals("Woche"))
		{
			dtVon.set(Calendar.DAY_OF_WEEK,Calendar.MONDAY);
		}
		else if (sPeriode.equals("Quartal"))
		{
			dtVon.setDate(1);
			dtVon.setMonth(((dtVon.getMonth()-1)/3)*3+1);
		}
		else if (sPeriode.equals("Jahr"))
		{
			dtVon.setDate(1);
			dtVon.setMonth(1);
		}
		Vec.addElement(bGanz ? dtVon.toTimestamp():g.getVon());
		if (bGanz)
			g.setVon(dtVon.toTimestamp());
		while(dtVon.getTimeInMilli()<lBis)
		{
			if (sPeriode.equals("Tag"))
				dtVon.tomorrow();
			else if (sPeriode.equals("Woche"))
			{
				//g.testInfo("vor:"+dtVon);
				dtVon.nextWeek();
				//g.testInfo("nach:"+dtVon);
			}
			else if (sPeriode.equals("Monat"))
				dtVon.nextMonth();
			else if (sPeriode.equals("Quartal"))
			{
				dtVon.nextMonth();
				dtVon.nextMonth();
				dtVon.nextMonth();
			}
			else if (sPeriode.equals("Jahr"))
				dtVon.nextYear();
			else
				dtVon.setTimeInMilli(lBis);
                        if (!bSo || dtVon.getTimeInMilli()<lBis+DateWOD.HOUR+1)
			  Vec.addElement(!bGanz && dtVon.getTimeInMilli()>=lBis?g.getBis():dtVon.toTimestamp());
           g.setVecPer(Vec);
		}
		if (bGanz)
			g.setBis(dtVon.toTimestamp());
	}
	//g.testInfo("ZR="+g.getVon()+"-"+g.getBis()+", VecPerioden="+g.VecPerioden);
}

public static Vector<Timestamp> PeriodeToVec2(Global g,int iVB,String sPeriode,boolean bGanz,boolean bSo)
{
	Timestamp dtVonTS=g.getVon(iVB);
	Timestamp dtBisTS=g.getBis(iVB);
	
	if (sPeriode==null || sPeriode.equals("offen") || dtVonTS==null || dtBisTS==null)
		return null;
	else
	{
		if (sPeriode.equals("x"))
			sPeriode=g.getZA(iVB);
		Vector<Timestamp> VecPer=new Vector<Timestamp>();
		DateWOD dtVon= new DateWOD(dtVonTS);
		long lBis=dtBisTS.getTime();
		if (sPeriode.equals("Monat"))
			dtVon.setDate(iStartTag);
		else if (sPeriode.equals("Woche"))
		{
			dtVon.set(Calendar.DAY_OF_WEEK,Calendar.MONDAY);
		}
		else if (sPeriode.equals("Quartal"))
		{
			dtVon.setDate(1);
			dtVon.setMonth(((dtVon.getMonth()-1)/3)*3+1);
		}
		else if (sPeriode.equals("Jahr"))
		{
			dtVon.setDate(1);
			dtVon.setMonth(1);
		}
		VecPer.addElement(bGanz ? dtVon.toTimestamp():dtVonTS);
		if (bGanz)
			dtVonTS=dtVon.toTimestamp();
		while(dtVon.getTimeInMilli()<lBis)
		{
			if (sPeriode.equals("Tag"))
				dtVon.tomorrow();
			else if (sPeriode.equals("Woche"))
			{
				//g.testInfo("vor:"+dtVon);
				dtVon.nextWeek();
				//g.testInfo("nach:"+dtVon);
			}
			else if (sPeriode.equals("Monat"))
				dtVon.nextMonth();
			else if (sPeriode.equals("Quartal"))
			{
				dtVon.nextMonth();
				dtVon.nextMonth();
				dtVon.nextMonth();
			}
			else if (sPeriode.equals("Jahr"))
				dtVon.nextYear();
			else
				dtVon.setTimeInMilli(lBis);
            if (!bSo || dtVon.getTimeInMilli()<lBis+DateWOD.HOUR+1)
			  VecPer.addElement(!bGanz && dtVon.getTimeInMilli()>=lBis?dtBisTS:dtVon.toTimestamp());
		}
		if (bGanz)
			dtBisTS=dtVon.toTimestamp();
		if (iVB<1)
		{
			g.setVecPer(VecPer);
			if (bGanz)
				g.setVonBis(dtVonTS, dtBisTS);
		}
		else
		{
			g.setVonBisL(dtVonTS, dtBisTS, sPeriode, iVB, null,VecPer);
		}
		return VecPer;
	}
}

//public static Vector<Timestamp> VecPerioden=null;
//public static Tabellenspeicher TabPerioden=null;
public static int iStartTag=1;

// add your data members here
//private static int iAic=0;
private Global g;
//public  JButton BtnOk=null;
//private JButton BtnAbbruch=null;
//public  JButton BtnUebernehmen;
//private JButton BtnRuecksetzen;
//private JButton BtnRefresh;
//private JButton BtnZRrefresh;
public JButton BtnZRspeichern=null;
//public JButton BtnSpeichern=null;
//private JButton BtnBeenden=null;
private JButton BtnInit=null;
private JButton BtnUnd=null;
private JButton BtnInfo=null;
//private JButton BtnPlus;
//private JButton BtnMinus;
//private JButton BtnJetzt;

private JPanel Pnl_Datum2;
private JPanel PnlTag;
private JPanel PnlWoche;
private JPanel PnlMonat;
private JPanel PnlQuartal;
private JPanel PnlJahr;

private Datum DatBewVon;
private Datum DatBewBis;
private SpinBox SbxBewWVon = new SpinBox(1,1,53,1,Color.WHITE);
private SpinBox SbxBewWBis = new SpinBox(1,1,53,1,Color.WHITE);
private SpinBoxList CboBewVon = new SpinBoxList(DateWOD.DFS.getMonths(),12,Color.WHITE);
private SpinBoxList CboBewBis = new SpinBoxList(DateWOD.DFS.getMonths(),12,Color.WHITE);
private SpinBoxJahr SbxBewWJVon = new SpinBoxJahr(Color.WHITE,1990);//new SpinBox(2007,1900,2100,1);
private SpinBoxJahr SbxBewWJBis = new SpinBoxJahr(Color.WHITE,1990);//new SpinBox(2007,1900,2100,1);
private SpinBoxJahr SbxBewMJVon = new SpinBoxJahr(Color.WHITE,1990);//new SpinBox(2007,1900,2100,1);
private SpinBoxJahr SbxBewMJBis = new SpinBoxJahr(Color.WHITE,1990);//new SpinBox(2007,1900,2100,1);
private SpinBoxJahr SbxBewJVon = new SpinBoxJahr(Color.WHITE,1990);//new SpinBox(2007,1900,2100,1);
private SpinBoxJahr SbxBewJBis = new SpinBoxJahr(Color.WHITE,1990);//new SpinBox(2007,1900,2100,1);
private SpinBox SbxBewQVon = new SpinBox(1,1,4,1,Color.WHITE);
private SpinBox SbxBewQBis = new SpinBox(1,1,4,1,Color.WHITE);
private SpinBoxJahr SbxBewQJVon = new SpinBoxJahr(Color.WHITE,1990);//new SpinBox(2007,1900,2100,1);
private SpinBoxJahr SbxBewQJBis = new SpinBoxJahr(Color.WHITE,1990);//new SpinBox(2007,1900,2100,1);

//private JCheckBox CbxLeiste=null;
private JPanel PnlWTL=null;
private SpinBox SbxWocheWTL=null;
private SpinBoxJahr SbxJahrWTL=null;
private JButton[] BtnWTL = new JButton[7];
private boolean bWTL=true;
private JPanel PnlWochenL=null;
private JButton[] BtnWL = new JButton[7];
private JPanel PnlMonatL=null;
private SpinBoxJahr SbxJahrML=null;
private JButton[] BtnML = new JButton[12];
private JPanel PnlQL=null;
private SpinBoxJahr SbxJahrQL=null;
private JButton[] BtnQL = new JButton[4];
private JPanel PnlJahrL=null;
private JButton[] BtnJL = new JButton[7];

//private Kalender Kal;

private ComboSort CboZeitArt;
private static Zeitraum Self=null;
//private boolean bLaden=true;
private boolean CboZeitArtOk=true;
//private int iArt;
private JScrollPane Rand1=null;
private JScrollPane Rand2=null;

private static final Timestamp NoN=new Timestamp(-3719782800000l);

  //private static int iNr=0;
}

