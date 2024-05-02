package All_Unlimited.Allgemein.Eingabe;

// add your custom import statements here
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Vector;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRootPane;
//import javax.swing.event.PopupMenuListener;
//import javax.swing.event.PopupMenuEvent;

import javax.swing.JToggleButton;
import javax.swing.JToolBar;

import jclass.bwt.JCItemEvent;
//import jclass.bwt.JCOutliner;
import jclass.bwt.JCOutlinerEvent;
import jclass.bwt.JCOutlinerFolderNode;
import jclass.bwt.JCOutlinerListener;
import jclass.bwt.JCOutlinerNode;
import jclass.bwt.JCOutlinerNodeStyle;
import All_Unlimited.Allgemein.Favorit;
import All_Unlimited.Allgemein.Formular;
import All_Unlimited.Allgemein.Global;
import All_Unlimited.Allgemein.Message;
import All_Unlimited.Allgemein.SQL;
import All_Unlimited.Allgemein.Sort;
import All_Unlimited.Allgemein.Static;
import All_Unlimited.Allgemein.Tabellenspeicher;
import All_Unlimited.Allgemein.Transact;
import All_Unlimited.Hauptmaske.Abfrage;
import All_Unlimited.Hauptmaske.ShowAbfrage;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import java.awt.Insets;
//import javax.swing.JToolBar;

/**
 * <p>Beschreibung: Combobox mit Liste zur Auswahl</p>
 * <p>Erzeugt: (c) 20.01.2001</p>
 * <p>Organisation: DVH </p>
 * @author Gerhard Peter und Roland Kremser
 */
public class AUComboList extends javax.swing.JPanel
{
/**
	 *
	 */
	private static final long serialVersionUID = -2399416822150045971L;
/**
 * Erzeugt AUComboList
*/
/*public AUComboList(int riFormular,int riEig,int riStt,Global glob,int riAbfrage,boolean rbFuellen,boolean rbKein)
{
  this(null,riFormular,riEig,riStt,glob,riAbfrage,rbFuellen,rbKein);
}*/

/*public AUComboList(JDialog rDlg,int riFormular,int riEig,int riStt,Global glob,int riAbfrage,boolean rbFuellen,boolean rbKein,boolean rbCbo)
{
  this(null,rDlg,riFormular,riEig,riStt,glob,riAbfrage,rbFuellen,rbKein,rbCbo);
}*/
public AUComboList clone()
{
  return new AUComboList(null,0,0,iEig,iStt,g,iAbfrageOld,bFuellen,bKein,bLeer,!bFav);
}

/*public AUComboList(JDialog rDlg,Global glob)
{
  g = glob;
  FrmThis = this;
  Dlg=rDlg;
  Cbo = new ComboSort(g,ComboSort.Bezeichnung);
  Cbo.set(ComboSort.STAMM,true);
  Cbo.setKein(bKein);
  if (Static.bStern)
  {
    BtnList = g.getButton("...");
    BtnList.setFocusable(false);
    BtnList.setBorder(null);
  }
  Build();
}*/

/*
   rDlg .. übergeordneter Dialog, null bei Formular
   riFormular .. Formular-Begriff für Stamm-Berechtigung
   riAbf .. Abfrage für induividuellen Filter
   riEig .. Eigenschaft für induividuellen Filter, Rolle u.a.
   riStt .. Stt für Stamm-Berechtigung, Titel, Combobox füllen
   riAbfrage .. Abfrage für Filter (Combobox füllen)
   rbFuellen .. füllen der COmbobox (Daten holen)
   rbKein .. <kein> vorhanden, nicht bei zwingend
   rbCbo .. leer lassen erlauben (immer true; nur bei Rbt, Btn false)
 */
public AUComboList(/*Vector rVecStamm,*/JDialog rDlg,int riFormular,int riAbf,int riEig,int riStt,Global glob,int riAbfrage,boolean rbFuellen,boolean rbKein,boolean rbCbo,boolean rbKeinFav)
{
	 g = glob;
         //VecStamm=rVecStamm;
//	 if (riEig>0)
//		 g.fixtestError("AUComboList mit "+g.TabEigenschaften.getBezeichnungS(riEig)+" ("+riEig+")");
	 bFav=!rbKeinFav && (g.TabStammtypen.getI_AIC("bits", riStt)&Global.cstSttFav)>0;
        if (riFormular !=0)
          VecStamm=g.getVecStamm(riFormular,riStt);
        
        if (VecStamm!=null)
        	g.fixtestInfo("AUComboList in "+g.getBegBez(riFormular)+": Stt="+g.TabStammtypen.getBezeichnungS(riStt)+": VecStamm="+VecStamm);
	//g.progInfo("AUComboList: Stt="+g.TabStammtypen.getBezeichnung(riStt)+", Abfrage="+riAbfrage+", füllen="+rbFuellen+", kein="+rbKein);
	FrmThis = this;
        Dlg=rDlg;
	iStt=riStt;
	bKein=rbKein;
        //bKein=true; // 4.3.2004 auf true
        iAbfrageOld=riAbfrage;
        iAbf=riAbf;
        iEig=riEig;
        int iPos=g.TabEigenschaften.getPos("AIC",iEig);
        if (iPos>=0)
        {
          iRolle = g.TabEigenschaften.getI(iPos,"Rolle");
          //g.progInfo(g.TabEigenschaften.getS("Bezeichnung")+": Rolle="+iRolle+", Stt="+iStt);
        }
        //g.progInfo("AUComboList: Stt="+g.TabStammtypen.getBezeichnung(riStt)+", Eig="+iEig+", Rolle="+iRolle+", Abfrage="+iAbfrageOld);
        iAbfrage= iAbf>0 && iEig>0 ? g.getFilter(iAbf,iEig,riAbfrage):riAbfrage;
	//iAbfrageOld=iAbfrage;
        bVorFilter=iAbfrage>0 && !rbFuellen;
	bFuellen=rbFuellen || bVorFilter;
	//bSuchen=true; //rbSuchen; // 4.11.2003 auf true

	Cbo = new ComboSort(g,riAbfrage==0?ComboSort.Bezeichnung:ComboSort.kein);
	Cbo.set(ComboSort.STAMM,true);
        if (Static.bStern)
        {
          BtnList = g.getButton("...");
          BtnList.setFocusable(false);
          BtnList.setBorder(null);
        }

	g.TabComboboxen.addInhalt("Stt",new Integer(iStt));
	g.TabComboboxen.addInhalt("Cbo",Cbo);

	Build();
        Cbo.setKein(bKein);
        if (Static.ComboLeer() && rbCbo || iStt==0)
          bLeer=true;
	else if (bFuellen)
          fillCbo(false);
        
      if (bLeer)
	Cbo.getComboSortEditor().addFocusListener(new FocusListener()
      {
                      public void focusGained(FocusEvent fe)
                      {
                        checkFill();
                        g.fixtestInfo("focusGained2");
                        //Cbo.requestFocus();
                      }
                      public void focusLost(FocusEvent fe)
                      {
                        g.fixtestInfo("focusLost2");
                        //g.progInfo("focusLost "+iEig);
                      }
      });

        if (bFuellen)
          Cbo.setEditable(50);
	Cbo.initBoolean();
	//Cbo.setPreferredSize(preferredSize);
	
}

public void checkFill()
{
  //g.progInfo("checkFill "+iEig);
  if (bLeer)
  {
    bLeer=false;
    if (bFuellen)
    {
      int iAic=Cbo.getSelectedAIC();
      fillCbo(false);
      Cbo.setSelectedAIC(iAic);
    }
  }
}

private static void setIconImage(Window W,Image Gif)
{
  if (W instanceof JFrame)
    ((JFrame)W).setIconImage(Gif);
  //else if (W instanceof JDialog)
  //  ((JDialog)W).setIconImage(Gif);
}

private static Container getContentPane(Window W)
{
  if (W instanceof JFrame)
    return ((JFrame)W).getContentPane();
  else if (W instanceof JDialog)
    return ((JDialog)W).getContentPane();
  else
    return null;
}

public static JRootPane getRootPane(Window W)
{
  if (W instanceof JFrame)
    return ((JFrame)W).getRootPane();
  else if (W instanceof JDialog)
    return ((JDialog)W).getRootPane();
  else
    return null;
}

public static void setTitle(Window W,String s)
{
  if (W instanceof JFrame)
  ((JFrame)W).setTitle(s);
  else if (W instanceof JDialog)
    ((JDialog)W).setTitle(s);
}

private void setSPara()
{
  if (iAbf>0 && iEig>0)
    g.setFPara(iAbf,iEig,CboSuche.getSelectedAIC()+(CbxVoll.isSelected()?"T":"F")+(CbxSucheCase.isSelected()?"T":"F"));//+(CbxSoundex.isSelected()?"T":"F"));
}

private void BuildFrame()
{
	//g.fixtestError("Cbo="+Cbo.getWidth()+"x"+Cbo.getHeight());
    
	int iPosS=g.TabStammtypen.getPos("AIC",iStt);
	sTitle = (g.Def() ? "AUComboList - ":"")+g.TabStammtypen.getS(iPosS,"Bezeichnung");
        if (Dlg==null)
          Frm = new JFrame(sTitle);
        else
          Frm = new JDialog(Dlg,sTitle,true);
	Image Gif=g.getSttGif(iStt);
	if (Gif != null)
          setIconImage(Frm,Gif);
	getContentPane(Frm).setLayout(new BorderLayout(2,2));
	//if (bSuchen)
	//{
          JPanel PnlNorth=new JPanel(new BorderLayout());
		JPanel Pnl2=new JPanel(new FlowLayout(FlowLayout.CENTER,2,0));
	 	CboSuche=new ComboSort(g,ComboSort.kein);
                //CboSuche.setBorder(new EmptyBorder(6,2,6,2));

                CboSuche.setFont(Transact.fontStandard);
		Pnl2.add(CboSuche);
		EdtSuche=new Text("",30);
                EdtSuche.setFont(Transact.fontStandard);
                CbxVoll=g.getCheckbox("Volltextsuche",false);
                //final JCheckBox CbxSucheAb=new JCheckBox("weitersuchen",false);
                CbxSucheCase=g.getCheckbox("Case",false);
                //CbxSuchen=g.getCheckbox("Suchen",false);
//                CbxSoundex=g.getCheckbox("Soundex",false);

            String sPara=iAbf>0 && iEig>0 ? g.getFPara(iAbf,iEig):"";
            if (sPara.length()>2)
            {
              iSPos=Integer.parseInt(sPara.substring(0,1));
              g.testInfo("Parameter bei"+iAbf+"/"+iEig+":"+sPara+"/"+iSPos);
              CbxVoll.setSelected(sPara.charAt(1)=='T');
              CbxSucheCase.setSelected(sPara.charAt(2)=='T');
//              CbxSoundex.setSelected(sPara.charAt(3)=='T');
            }

		EdtSuche.addKeyListener(new KeyListener()
		{
			public void keyPressed(KeyEvent e)
			{
                          //g.fixInfo("keyPressed");
                          //g.progInfo("keyPressed: KeyCode="+e.getKeyCode());
                          int iKey=e.getKeyCode();
                          //g.progInfo("keyPressed: KeyCode="+iKey);
                          if (iKey==KeyEvent.VK_UP)
                            //Out.selectNode(Out.getPreviousNode(Out.getSelectedNode()),null);
                            Static.makeVisible(Out,Out.getPreviousNode(Out.getSelectedNode()));
                          else if (iKey==KeyEvent.VK_DOWN)
                            //Out.selectNode(Out.getNextNode(Out.getSelectedNode()),null);
                            Static.makeVisible(Out,Out.getNextNode(Out.getSelectedNode()));
                          else
                            bSuche=true;
			}
			public void keyReleased(KeyEvent e)
			{
                          //g.fixInfo("keyReleased");
                          //if (CbxSuchen.isSelected())
                          if (bSuche)
                            suche(EdtSuche.getText(),!CbxVoll.isSelected(),CbxSucheCase.isSelected(),false);
                          bSuche=false;
			}
			public void keyTyped(KeyEvent e)
			{
                          char cKey=e.getKeyChar();
                          //int iKey1=cKey;
                          //g.progInfo("keyTyped: Key="+iKey1);
                          if (cKey==27)
                            Frm.setVisible(false);
                          else if (cKey=='*')
                          {
                            e.consume();
                            suche(EdtSuche.getText(), !CbxVoll.isSelected(), CbxSucheCase.isSelected(),true);
                          }
                          //g.progInfo("KeyCode="+e.getKeyCode());
				/*char cKey=e.getKeyChar();
				if(cKey==13 || cKey==10)
				{
					e.consume();
					suche(EdtSuche.getText(),(All_Unlimited.Allgemein.Anzeige.Combo)CboSuche.getSelectedItem(),
                                              !CbxVoll.isSelected(),CbxSucheAb.isSelected(),CbxSucheCase.isSelected());
                                        //g.fixInfo("CbxSucheCase.requestFocusInWindow:"+CbxSucheCase.requestFocusInWindow());
                                        //Out.requestFocusInWindow();
                                        //g.fixInfo("Out.requestFocusInWindow:"+Out.requestFocusInWindow());
                                        //Out.folderChanged(null);
					//String sSuchtext=EdtSuche.getText();
					//g.fixInfo("Suchtext="+sSuchtext);
				}*/
			}
		});
                //if (Static.bStern)
                //{
                  //JPanel PnlSuche=new JPanel(new BorderLayout(1,1));
                  Pnl2.add(EdtSuche);
                  JButton BtnSuche=g.getButton("S");
                  Pnl2.add(BtnSuche);
                  //Pnl2.add(PnlSuche);
                  BtnSuche.addActionListener(new ActionListener()
                  {
                    public void actionPerformed(ActionEvent e)
                    {
                      suche(EdtSuche.getText(), !CbxVoll.isSelected(), CbxSucheCase.isSelected(),true);
                      EdtSuche.requestFocus();
                    }
                  });
                //}
                //else
                //  Pnl2.add(EdtSuche);
                JPanel PnlCbx=new JPanel(new FlowLayout());
                PnlCbx.add(CbxVoll);
                //PnlCbx.add(CbxSucheAb);
                if (g.Oracle() || g.MS())
                  PnlCbx.add(CbxSucheCase);
//                if (!bFuellen || bVorFilter)
//                {
//                  //PnlCbx.add(CbxSuchen);
//                  PnlCbx.add(CbxSoundex);
//                }
            PnlNorth.add("North",Pnl2);
            //if (bFuellen)
              PnlNorth.add("Center",PnlCbx);
            JScrollPane Scroll=new JScrollPane(PnlNorth);
            Scroll.setBorder(new EmptyBorder(new Insets(2,2,0,2)));
            getContentPane(Frm).add("North",Scroll);
		//EdtSuche.requestFocus();
	//}
        getContentPane(Frm).add("West",new JLabel(""));
	getContentPane(Frm).add("Center",Out);
	
	ActionListener AL=new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			String s=e.getActionCommand();
			if (s.equals("ok"))
			{  setSPara();ok(); }
			else if (s.equals("cancel"))
				Frm.setVisible(false);
			else if (s.equals("refresh"))
				Refresh();
			else if (s.equals("edit"))
				ShowFavorit(false,true);//EditFavorit();
			else if (s.equals("all"))
				ShowFavorit(false,false);
			else if (s.equals("user"))
				ShowFavorit(true,false);
			else if (s.equals("show"))
				ShowFav();
			else if (s.equals("del"))
				DelFav();
		}
	};
	Global.setAction(BtnOk, "ok", AL);
	Global.setAction(BtnAbbruch, "cancel", AL);
	Global.setAction(BtnRefresh, "refresh", AL);
	JPanel Pnl = new JPanel(new FlowLayout(FlowLayout.RIGHT,2,2));
    JPanel PnlS=new JPanel(new BorderLayout());
	if (bFav)
	{
		BtnAlle=g.getTButton("Fav_alle","all",AL);
		BtnPers=g.getTButton("Favoriten","user",AL);
		BtnDef=g.getTButton("Fav_edit","edit",AL);
		JButton BtnShow=g.getButton("Fav_show", "show", AL);
		JButton BtnDel=g.getButton("Fav_del", "del", AL);
		BtnGrp=new ButtonGroup();
		BtnGrp.add(BtnAlle);
		BtnGrp.add(BtnPers);
		BtnGrp.add(BtnDef);
		JToolBar PnlFav=new JToolBar(JToolBar.HORIZONTAL);
		PnlFav.setOpaque(false);
		PnlFav.setFloatable(false);
		PnlFav.add(BtnAlle);
		PnlFav.add(BtnPers);
		PnlFav.add(BtnDef);
		//PnlFav.addSeparator();
		Pnl.add(BtnShow);
		Pnl.add(BtnDel);
		//PnlNorth.add("East",PnlFav);
	    PnlS.add("West",PnlFav);
	    if (bUser)
	    {
	    	BtnPers.setSelected(true);
	    	//ShowFavorit(true,false);
	    	//g.fixtestInfo("Favorit: set to User");
	    	//Out.repaint();
	    	//Frm.setVisible(true);
	    	//RefreshFavorit();
	    	//Out.addNotify();
	    	//Out.repaint();
	    	//Out.layout();
	    }
    	else
    		BtnAlle.setSelected(true);
	   Pnl.add(new JLabel("  "));
	}
		if (g.Abfrage() && Dlg==null)
		{
                  Pnl.add(BtnInit);
                  Pnl.add(BtnFilter);
		}
        Pnl.add(BtnRefresh);
                //Pnl.addSeparator();
       Pnl.add(new JLabel("  "));
		Pnl.add(BtnOk);
                //JButton BtnHelp=g.getButton2("help_cbo");
                //Pnl.add(BtnHelp);
		Pnl.add(BtnAbbruch);
        //JPanel PnlS = new JPanel(new FlowLayout(FlowLayout.RIGHT,2,2));
        //PnlS.add(new JLabel());
        //PnlS.add(Pnl);
		PnlS.add("East",Pnl);
	getContentPane(Frm).add("South",PnlS);
	int iFF=g.getFontFaktor();
	Frm.setSize(450*iFF/100,400*iFF/100);

        //g.progInfo("Out.addKeyListener");
        getRootPane(Frm).setDefaultButton(BtnOk);
        
        Out.addItemListener(new JCOutlinerListener()
		{
			public void outlinerFolderStateChangeBegin(JCOutlinerEvent ev)
			{
                          if (!bOL) ok();
                          //g.progInfo("outlinerFolderStateChangeBegin");
			}
			public void outlinerFolderStateChangeEnd(JCOutlinerEvent ev)
                        {
                          //g.progInfo("outlinerFolderStateChangeEnd");
                        }
			public void outlinerNodeSelectBegin(JCOutlinerEvent ev)
			{
                          //g.progInfo("outlinerNodeSelectBegin");
			}
			public void outlinerNodeSelectEnd(JCOutlinerEvent ev)
                        {
                          //g.progInfo("outlinerNodeSelectEnd");
                        }
			public void itemStateChanged(JCItemEvent ev)
			{
				/*if(ev.getStateChange() == ev.SELECTED)
				{
                                  g.progInfo("itemStateChanged");
				}*/
			}
		});
        
        Out.getOutliner().addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getButton()==3 || e.getButton()==1 && e.getModifiersEx()==Global.iRM)
				{
					if (bEdit)
						SaveFavorit();
				}
			}
		});

	BtnFilter.addActionListener(new ActionListener()
	{
		public void actionPerformed(ActionEvent e)
		{
			if (g.Def() || (Abf.iBits&Abfrage.cstNoChange)==0)
				All_Unlimited.Grunddefinition.DefAbfrage.get(g,Abf,iStt).show();
		}
	});

        BtnInit.addActionListener(new ActionListener()
        {
                public void actionPerformed(ActionEvent e)
                {
                        iAbfrage=iAbfrageOld;
                        g.setFilter(iAbf,iEig,iAbfrageOld,iAbfrage);
                        //g.progInfo("Abfrage="+FilterModified()+"/"+getFilter());
                        Abf=new ShowAbfrage(g,iAbfrage,Abfrage.cstAbfrage/*cstHS_Filter*/);
                        int iOld = Cbo.getOld();
                        int iAIC = Cbo.getSelectedAIC();
                        fillCbo(true);
                        Cbo.setSelectedAIC(iAIC);
                        Cbo.setAktAIC(iOld);
                        fillList(null,null,false);
                }
        });

}

private void ShowFav()
{
	Vector<Integer> Vec=getFavoriten();
	Tabellenspeicher Tab=new Tabellenspeicher(g,"select aic_stamm,bezeichnung from stammview2 where aic_stamm"+Static.SQL_in(Vec),true);
	Tab.showGrid("Favoriten",Frm);
}

private void DelFav()
{
	Vector<Integer> Vec=getFavoriten();
	if (Vec.size()>0)
	{
		String s=Vec.size()+" Favoriten";
		int iMT=new Message(Message.YES_NO_OPTION,Dlg,g,30).showDialog("Loeschen", new String[] {s});
		if (iMT==Message.YES_OPTION)
			g.exec("delete from favorit where aic_benutzer="+g.getBenutzer()+" and aic_stamm"+Static.SQL_in(Vec));
		Favorit.clear();
	}
	//g.fixtestError("DelFav noch nicht programmiert");
}

private Vector<Integer> getFavoriten()
{
	//select f.* from favorit f join stamm s on f.aic_stamm=s.aic_stamm and s.aic_stammtyp=121
	//return SQL.getVector("select f.aic_stamm from favorit f join stamm s on f.aic_stamm=s.aic_stamm and s.aic_stammtyp="+iStt+" where aic_benutzer="+g.getBenutzer(), g);
	return Favorit.get(g,iStt,Favorit.FAV);
}

private void SaveFavorit()
{
	//g.fixtestInfo("Save for User");
	Vector<Integer> Vec=getSelectedAics();
	Vector<Integer> VecMom=getFavoriten();
	//g.fixtestInfo(Vec+" zusätzlich zu "+VecMom);
	SQL Qry=new SQL(g);
	int iProt=g.Protokoll("Eingabe", iAbf);
	for (int i=0;i<Vec.size();i++)
	{
		Integer Int=Vec.elementAt(i);
		if (VecMom.contains(Int))
			g.exec("delete from favorit where aic_stamm="+Int.intValue()+" and aic_benutzer="+g.getBenutzer());
		else
		{
			Qry.add("AIC_STAMM", Int.intValue());
			Qry.add("aic_benutzer", g.getBenutzer());
			Qry.add("AIC_PROTOKOLL", iProt);
			Qry.add("Status", 1);
			Qry.insert("FAVORIT", false);
		}
	}
	Qry.free();
	Favorit.clear();
	//g.fixtestInfo(" nach speichern "+getFavoriten());
	RefreshFavorit();
}

private void RefreshFavorit()
{
	JCOutlinerNodeStyle Style = new JCOutlinerNodeStyle();
    Style.setFont(g.getOutFont(false));//g.fontStandard);
    JCOutlinerNodeStyle StyFav=new JCOutlinerNodeStyle(Style);
    StyFav.setForeground(Global.ColFavorit);
	Vector<Integer> VecF=getFavoriten();
	Vector Vec=Out.getRootNode().getChildren();
	if (Vec != null)
	 for(int i=0;i<Vec.size();i++)
	 {
		JCOutlinerNode Nod=(JCOutlinerNode)Vec.elementAt(i);
	  if (TabKnoten.posInhalt("Knoten", Nod))
	  {
		  Vector VecL=(Vector)Nod.getLabel();
		  boolean bFav=VecF.contains(TabKnoten.getInt("Aic"));
		  String s=bFav ? "x":"";
		  Nod.setStyle(bFav ? StyFav:Style);
		  if (VecL.size()>iSpUser)
			  VecL.setElementAt(s,iSpUser);
		  else
			  VecL.addElement(s);
	  }
	 }
	Out.folderChanged(Out.getRootNode());
}

/*private void EditFavorit()
{
	//g.fixtestInfo("Edit for User");
	bEdit=true;
	bUser=false;
	BtnOk.setEnabled(false);
	BtnAbbruch.setEnabled(false);
	Out.setAllowMultipleSelections(true);
	Refresh();
	Out.setBackground(g.ColHS);//Color.LIGHT_GRAY.brighter());
	RefreshFavorit();
}*/

private void ShowFavorit(boolean b,boolean rbEdit)
{
	//g.fixtestInfo("Show for User:"+b);
	bEdit=rbEdit;
	bUser=b;
	BtnOk.setEnabled(!bEdit);
	BtnAbbruch.setEnabled(!bEdit);
	Out.setAllowMultipleSelections(bEdit);
	Refresh();
	Out.setBackground(bEdit ? Global.ColHS:Color.WHITE);
	RefreshFavorit();
}


public boolean isActive()
{
  return Frm!=null && Frm.isVisible();
}

public void hideFrm()
{
  if (isActive())
    Frm.setVisible(false);
}

public void Refresh()
{
  iAbfrage=Abf.iAbfrage;
  g.setFilter(iAbf,iEig,iAbfrageOld,iAbfrage);
  //g.fixtestInfo("Refresh Abfrage="+iAbfrage+"/"+getFilter());
  int iOld = Cbo.getOld();
  int iAIC = Cbo.getSelectedAIC();
  fillCbo(true);
  Cbo.setSelectedAIC(iAIC);
  Cbo.setAktAIC(iOld);
  fillList(null,null,false);
}

public int getSelected()
{
  //if (Out!=null)
  //  g.fixInfo("getSelected="+Out.getSelectedNode().getLabel());
  if (Out==null || Out.getSelectedNode()==null)
    return 0;
  else
    return TabKnoten.posInhalt("Knoten",Out.getSelectedNode()) ? TabKnoten.getI("Aic"):0;
}

private void ok()
{
        //Cbo.setSelectedItem((Combo)Out.getSelectedNode().getLabel());
        TabKnoten.posInhalt("Knoten",Out.getSelectedNode());
        //int iOld=Cbo.getOld();
        Cbo.setSelectedAIC(TabKnoten.getI("Aic"),null,null,false);
        //Cbo.setAktAIC(iOld);
        bOk=true;
        Frm.setVisible(false);
        if (evTab != null)
          evTab.actionPerformed(null);
}

public void OpenList()
{
  OpenList(Dlg,false);
}

public void OpenList(JDialog rDlg,boolean rbOL)
{
  if (rDlg==null && DlgR==null && Dlg!=null)
    DlgR=Dlg;
  Dlg=rDlg;
  if (rbOL!=bOL && Frm!=null)
  {
    Frm.dispose();
    Frm = null;
    if (!rbOL)
      Dlg=DlgR;
    g.fixInfo("AUComboList-Formular geschlossen, schalte auf "+rbOL);
  }
  bOL=rbOL;
  checkFill();
  if (Frm==null)
  {
          BuildFrame();
          BtnOk.setEnabled(!bOL);
          BtnAbbruch.setEnabled(!bOL);
          if (!bFuellen)
                  fillCbo(false);
          fillList(null,null,false);
  }
  else if (bRefresh)
    fillList(null,null,false);
  else if(TabKnoten.posInhalt("Aic",Cbo.getSelectedAIC()))
    Static.makeVisible(Out,(JCOutlinerNode)TabKnoten.getInhalt("Knoten"));
  if (!bCenter)
  {
    try
    {
      Static.centerComponent(Frm, FrmThis);
    }
    catch (Exception e)
    {
      Static.centerComponent(Frm, null);
    }
    bCenter=true;
  }
  Static.bRefreshStop=true;
  bOk=false;
  Frm.setVisible(true);
  //if (Cbo.getSelectedAIC()==0)
  EdtSuche.setText("");
  EdtSuche.requestFocus();
  //else
  //  Out.requestFocus();
}

private void Build()
{
	//NodeAbfrage = Abfrage.InitNode(g,false);
	TabKnoten = new Tabellenspeicher(g,new String[]{"Aic","Knoten"});
        //Formular Fom=new Formular(g,null);
	BtnOk = g.getButton("Ok");
	BtnAbbruch = g.getButton("Abbruch");
        BtnInit = g.getButton("Init");
	BtnFilter = g.getButton("Abfrage");
	BtnRefresh = g.getButton("Refresh");

	Out.setBackground(Color.white);
	Out.setRootVisible(false);


	setLayout(new BorderLayout(0,0));
	add("Center",Cbo);
        if (Static.bStern)
        {
          add("East", BtnList);
          BtnList.addActionListener(new ActionListener()
          {
            public void actionPerformed(ActionEvent e)
            {
              OpenList();
            }
          });
        }
        Cbo.addKeyListener(new KeyListener()
        {
                public void keyPressed(KeyEvent e)
                {
                }
                public void keyReleased(KeyEvent e)
                {
                }
                public void keyTyped(KeyEvent e)
                {
                  if(e.getKeyChar()=='*')
                  {
                    e.consume();
                    OpenList();
                  }
                }
        });

	Cbo.addItemListener(new ItemListener()
	{
		public void itemStateChanged(ItemEvent e)
		{
			if(e.getStateChange()==ItemEvent.SELECTED && Frm!=null && Frm.isShowing())
			{
				if(TabKnoten.posInhalt("Aic",Cbo.getSelectedAIC()))
					Out.selectNode((JCOutlinerNode)TabKnoten.getInhalt("Knoten"),null);
			}
		}
	});

	Cbo.getComboSortEditor().addMouseListener(new MouseListener()
	      {
	        public void mousePressed(MouseEvent ev)
	        {}

	        public void mouseClicked(MouseEvent ev)
	        {
	          //if (ev.getModifiers() == MouseEvent.BUTTON3_MASK)
	          if (ev.getButton()==3 || ev.getButton()==1 && ev.getModifiersEx()==Global.iRM)
	          {
	            OpenList();
	          }
	        }

	        public void mouseEntered(MouseEvent ev)
	        {}

	        public void mouseExited(MouseEvent ev)
	        {}

	        public void mouseReleased(MouseEvent ev)
	        {}
	      });
}

private void suche(String s,boolean bStart,boolean bCase,boolean bExt)
{
  //(All_Unlimited.Allgemein.Anzeige.Combo)CboSuche.getSelectedItem();
	if (bExt && (!bFuellen || bVorFilter))
	{
		fillList(s,CboSuche.getSelectedBezeichnung(),bCase);
	}
	else
	{
          if (bExt)
            fill(s);
          //int iPos=CboSuche.getSelectedAIC()-1;
          if (Out.getSelectedNode() !=null && Out.getSelectedNode().getLevel()>0 && isPos(s,(Vector)Out.getSelectedNode().getLabel()))
            return;
		/*Vector Vec = Out.getRootNode().getChildren();
                if (Vec != null)
                  for(int i=0;i<Vec.size();i++)
                  {
                    JCOutlinerNode NodNeu=(JCOutlinerNode)Vec.elementAt(i);
                    if (isPos(s,(Vector)NodNeu.getLabel()))
			{
                          	Static.makeVisible(Out,NodNeu);
                                //iLast=i;
				return;
			}
                  }*/
          for (TabKnoten.moveFirst();!TabKnoten.out();TabKnoten.moveNext())
          {
            JCOutlinerNode NodNeu=(JCOutlinerNode)TabKnoten.getInhalt("Knoten");
            if (isPos(s,(Vector)NodNeu.getLabel()))
            {
                    Static.makeVisible(Out,NodNeu);
                    return;
            }
          }
	}
}

private boolean isPos(String s,Vector Vec)
{
  int iPos=CboSuche.getSelectedAIC()-1;
  for (int i2=iPos;i2==iPos || iPos<0 && i2<Vec.size();i2++)
  {
    if (i2<0) i2++;
    String sMom = Sort.gets(Vec.elementAt(i2));
    if (!CbxSucheCase.isSelected())
    {
      sMom = sMom.toUpperCase();
      s = s.toUpperCase();
    }
    if (CbxVoll.isSelected() ? sMom.indexOf(s) > -1 : sMom.startsWith(s))
      return true;
  }
  return false;
}

/**
* neufüllen (für EingabeFormular)
 */
public void fillCbo(boolean bImmer)
{
  //g.fixtestInfo("fillCbo "+g.TabEigenschaften.getBezeichnungS(iEig)+"/"+bImmer);
	//if (iAbfrage>0 && !bImmer)
	if (Abf==null)
	{
		Abf=new ShowAbfrage(g,iAbfrage,Abfrage.cstAbfrage/*cstHS_Filter*/);
		Abf.iStt=iStt;
	}
    if (iRolle==0 || Abf.iRolle>0)
      iRolle=Abf.iRolle;
    //if (Abf.iModell>0)
    //	bFav=false;
    Vector<Integer> Vec=null;
    if (bFav && BtnGrp==null)
    {
    	Vec=getFavoriten();
    	bUser=Vec.size()>0;
    	/*if (Vec.size()>0)
    		BtnPers.setSelected(true);
    	else
    		BtnAlle.setSelected(true);*/
    }
	Vector VecEigenschaften = new Vector();
	TabSpalten = Abf.getAnzeigeSpalten(VecEigenschaften);
	String sFilter = bImmer || iAbfrage>0 ? Abf.Check(VecEigenschaften,Abf.NodBed.getChildren(),"",false/*Abf.iBew==0?"Aic":"aic_bew_pool"*/):"";
	bBed=!sFilter.equals("");
	//java.util.Vector Vec=g.SQL_CboStt(iStt);
        if (Abf.iModell>0)
        {
          g.checkModelle();
          int iPosM=g.TabModelle.getPos("aic_modell",Abf.iModell);
          g.fixtestInfo("AUComboList-"+g.TabEigenschaften.getBezeichnungS(iEig)+": Abfrage <"+Abf.sDefBez+"> wird mit Modell <"+g.TabModelle.getS(iPosM,"Bezeichnung")+"> gefüllt");
          TabDaten = Abf.getDaten(iStt, 0, VecStamm,null);
          if (TabDaten==null)
          {
            Cbo.Clear(bKein);
            Cbo.Sort();
            g.fixInfo("AUComboList: Abfrage <"+Abf.sDefBez+"> liefert keine Daten bei Modell <"+g.TabModelle.getS(iPosM,"Bezeichnung")+">");
          }
          else
          {
        	  if (VecStamm!=null)
        		  TabDaten.clearWithVec("aic_stamm",VecStamm);
        	  if (bFav && bUser && Vec==null)
              	Vec=getFavoriten();
        	  if (bUser && Vec!=null)
        		  TabDaten.clearWithVec("aic_stamm", Vec);
        	  Cbo.fillCbo(TabDaten, bKein,"aic_stamm",TabSpalten.getS(0,"Kennung"),null);
        	  //TabDaten.showGrid("TabDaten für AUComboList mit Modell "+g.TabModelle.getS(iPosM,"Bezeichnung"));
          }
            //Cbo.fillCbo("SElect aic_stamm,bezeichnung kennung" + g.AU_Bezeichnung2("Stamm", "p2") + " from stammview p2 where aic_rolle is null and" +
            //            g.in("aic_stamm", TabDaten.getVecSpalte("aic_stamm")), "Stamm", bKein, false);
          //g.fixInfo("gefüllt mit Modell "+Abf.iModell);
        }
        else
        {
          boolean bDel = Abf.bEntfernte || Abfrage.is(Abf.iBits, Abfrage.cstEntfernte);
          if (Abf.iBew == 0)
          {
            //g.TabStammtypen.push();
            int iPos = g.TabStammtypen.getPos("Aic", iStt);
            boolean bCopy = (g.TabStammtypen.getI(iPos, "bits") & Global.cstCopy) > 0;
            //g.TabStammtypen.pop();
            if (bFav && bUser && Vec==null)
            	Vec=getFavoriten();
            sSQL = "SeLect * from (SELECT " + (iAbfrage == 0 ? "AIC_Stamm" : Abf.ZusaetzlicheSpalten(VecEigenschaften, Formular.Stamm, bBed)) +
                ",kennung" + g.AU_Bezeichnung4() +
                " FROM Stammview"+(Abfrage.is(Abf.iBits, Abfrage.cstKeinStammZeitraum) ? "2":"")+" p2 WHERE AIC_Stammtyp=" + iStt + (iRolle > 0 ? " and aic_rolle=" + iRolle : " and aic_rolle is null") +(bUser ? " and"+g.in("aic_stamm", Vec):"")+
                (bCopy ? g.getCopyMandanten(false) : g.getReadMandanten()) + Abf.Ebenen();
            sSQL2 = TabSpalten.isEmpty() && bFuellen ? " order by Bezeichnung" : Abf.Sortiert();
          }
          else
          {
            sSQL = "SeLeCt * from (SELECT distinct v" + iEig + " aic,e" + iEig + " Kennung,e" + iEig + " Bezeichnung," +
                Abf.ZusaetzlicheSpalten(VecEigenschaften, Formular.Bewegung, bBed) + " from " +
                (bDel ? Abfrage.is(Abf.iBits, Abfrage.cstKeinZeitraum) ? "Bew_Pool" : "BewView3" : Abfrage.is(Abf.iBits, Abfrage.cstKeinZeitraum) ? "BewView2" : "BewView") +
                " p2 where p2.aic_bewegungstyp=" + Abf.iBew + Abf.Ebenen();
            sSQL2 = "";
          }
          //g.progInfo(sSQL);
          //new Tabellenspeicher(g,s,true).showGrid("Daten");
          //TabSpalten.showGrid("Spalten");
          if (bFuellen)
          {
            long lClock = Static.get_ms();
            TabDaten = new Tabellenspeicher(g, Abf.checkJoker(sSQL + sFilter + sSQL2), true);
            TabDaten.checkBezeichnung();
            TabDaten.clearWithVec("aic_Stamm", VecStamm);
            //if (Abf.iBew>0)
            //  TabDaten.showGrid("fillCbo");
            Cbo.fillCbo(TabDaten, bKein,"aic_stamm");
            ComboSort.lClockAU += Static.get_ms() - lClock;
            ComboSort.lClockAnz++;
          }
          if (bVorFilter)
            bBed = false;
          else
            sSQL += sFilter;
        }
	if (Frm!=null)
		setTitle(Frm,sTitle+(!sFilter.equals("")?" ["+g.getBegriffS("Show","Filter")+"]":""));
        bRefresh=true;
}

@SuppressWarnings("unchecked")
private void fillList(String s,String s2,boolean bCase)
{
  g.progInfo("AUComboList.fillList:"+s+"/"+s2);
  if (Frm != null)
    Frm.setCursor(new Cursor(Cursor.WAIT_CURSOR));
	//Vector VecItems = Cbo.getItems();
	//JCOutlinerNodeStyle Style = g.getSttStyle(iStt);

	Vector<String> VecGidSpalten;
	boolean bKeineSpalte=/*TabSpalten == null || */TabSpalten.isEmpty();
	if (bKeineSpalte)
	{
		//sSortiert=" order by bezeichnung";
		VecGidSpalten=new Vector<String>();
		VecGidSpalten.addElement(g.getShow("Bezeichnung"));
		g.progInfo("Bezeichnung hinzugefügt");
	}
	else
	{
		VecGidSpalten = Abf.getBezeichnung();
		//Out.setColumnAlignments(Abf.getAusrichtung());
		
	}
	if (bFav)
	{
		iSpUser=VecGidSpalten.size();
		VecGidSpalten.addElement(g.getShow("Favorit"));
	}
	g.fixtestInfo("Spalten:"+VecGidSpalten);
	//Out.setColumnButtons(VecGidSpalten);
	//Out.setNumColumns(VecGidSpalten.size());
	Abf.setOutlinerButtons(0,TabSpalten,Out,VecGidSpalten);

	if (bFuellen || s != null)
	{
		if (sSQL!=null && (bUser || bEdit || !Static.Leer(s)))
                {
                  String sSpalte=(bKeineSpalte?"Bezeichnung":TabSpalten.posInhalt("Bezeichnung",s2)?"e"+TabSpalten.getS("Kennung2"):"Bezeichnung");
                  g.progInfo("fillList: Spalte="+sSpalte);
                  if (!bCase)
                  {
                    if (!Static.Leer(s)) s=s.toUpperCase();
                    sSpalte="upper("+sSpalte+")";
                  }
                  if (sSQL==null)
                	  TabDaten=null;
                  else
                  {
	                  String sTest=sSQL+(Static.Leer(s) ? "":(bBed?" and ":" where ")+(/*CbxSoundex!=null && CbxSoundex.isSelected() ? "soundex("+sSpalte+")=soundex('"+s+"')":*/sSpalte+" like '"+(CbxVoll.isSelected()?"%":"")+s+"%'"))+sSQL2;
//	                  g.fixtestError("sTest="+sTest);
//	                  g.fixtestError("sSQL2="+Static.print(sSQL2));
	                  if (sSQL2.indexOf("order by")==-1)
	                    sTest+=" order by "+sSpalte;
	                  g.progInfo(sTest);
	                  TabDaten=new Tabellenspeicher(g,Abf.checkJoker(sTest),true);
                  }
                }

		fill(null);
	}
	if (/*bSuchen &&*/  s == null && CboSuche != null)
	{
		CboSuche.Clear();
                CboSuche.setKein(true);
		if (bKeineSpalte)
			CboSuche.addItem(g.getBegriffS("Show","Bezeichnung"),/*bFuellen?0:*/1,"");
		else
			for(int i=0;i<VecGidSpalten.size();i++)
				CboSuche.addItem(""+VecGidSpalten.elementAt(i),i+(/*bFuellen?0:*/1),"");
		CboSuche.setSelectedAIC(0);//iSPos);
	}
	//if (g.Prog())
	//	TabSpalten.showGrid();
    bRefresh=false;
    if (Frm != null)
      Frm.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
}

  public void getAll(JCOutlinerNode Nod)
  {
    //if (Vec==null)
    //  Vec=new Vector<JCOutlinerNode>();
    Vector Vec2=Nod.getChildren();
    if (Vec2 != null)
      for (int i=0;i<Vec2.size();i++)
      {
        JCOutlinerNode NodNew=(JCOutlinerNode)Vec2.elementAt(i);
        int iAic=Sort.geti(NodNew.getUserData());
        if (iAic>0)
        {
          TabKnoten.addInhalt("Aic", iAic);
          TabKnoten.addInhalt("Knoten", NodNew);
        }
        getAll(NodNew);
      }
  }


  private void fill(String s)
  {
    JCOutlinerFolderNode NodeRoot = (JCOutlinerFolderNode)Out.getRootNode();
    NodeRoot.removeChildren();
    JCOutlinerNodeStyle Style = new JCOutlinerNodeStyle();
    Style.setFont(g.getOutFont(false));//g.fontStandard);
    JCOutlinerNodeStyle StyFav=null;
    Vector<Integer> VecF=null;
    if (bFav)
    {
    	StyFav=new JCOutlinerNodeStyle(Style);
    	StyFav.setForeground(Global.ColFavorit);
    	VecF=getFavoriten();
    }
    TabKnoten.clearAll();
    boolean bUse=true;

    if ((Abf.iBits&Abfrage.cstTTO)>0)
    {
      //TabSpalten.showGrid("TabSpalten");
      //TabDaten.showGrid("TabDaten");
      //int iAnz=Out.getNumColumns();
      //ShowAbfrage.initOutliner(g,Out);
      Abf.TabToOutliner(Out, TabDaten, null, null, 1);
      getAll(Out.getRootNode());
      //TabKnoten.showGrid("TabKnoten");
    }
    else if (TabDaten != null)
     for(TabDaten.moveFirst();!TabDaten.eof();TabDaten.moveNext())
     {
            Vector<Object> VecSpalten = new Vector<Object>();
            if (TabSpalten==null || TabSpalten.isEmpty())
                            VecSpalten.addElement(TabDaten.getS("Bezeichnung"));
            if (TabSpalten != null)
                    for(TabSpalten.moveFirst();!TabSpalten.eof();TabSpalten.moveNext())
                            VecSpalten.addElement(Abf.TabToScreen(g,TabSpalten.getS("Kennung"),TabDaten,TabSpalten,false));
        if (s!=null && !s.equals(""))
          bUse=isPos(s,VecSpalten);
        if (bUse)
        {
          JCOutlinerFolderNode Node = new JCOutlinerFolderNode((Object)VecSpalten, NodeRoot);
          if (StyFav != null && VecF.contains(TabDaten.getI("aic_stamm")))
        	  Node.setStyle(StyFav);
          else if (Style != null)
            Node.setStyle(Style);

          TabKnoten.addInhalt("Aic", TabDaten.getI("aic_stamm"));
          TabKnoten.addInhalt("Knoten", Node);
        }
     }
    if(TabKnoten.posInhalt("Aic",Cbo.getSelectedAIC()))
            Static.makeVisible(Out,(JCOutlinerNode)TabKnoten.getInhalt("Knoten"));
    else
            Out.folderChanged(Out.getRootNode());
  }

public ComboSort getComboBox()
{
	return Cbo;
}

public int getAIC()
{
  return Cbo.getSelectedAIC();
}

public boolean isNull()
{
	return Cbo.isNull();
}

public boolean wasNull()
{
  return Cbo.wasNull();
}


public boolean Modified()
{
	boolean b=Cbo.Modified();
	if (b)
		g.fixtestInfo("Modified "+g.TabEigenschaften.getBezeichnungS(iEig)+":"+Cbo.getOld()+" -> "+Cbo.getSelectedAIC());
	return b;
}

public boolean isValid2()
{
	int i=Cbo.getSelectedAIC();
	if (i<1 && !Cbo.get(ComboSort.OK))
	{
		//g.progInfo("isValid: AIC="+i+", ok="+Cbo.bOk+", Text="+Cbo.getSelectedItem());
		SQL Qry=new SQL(g);
		Qry.open("Select aic_stamm,kennung"+g.AU_Bezeichnung4()+" from stammview2 p2 where aic_stammtyp="+iStt+" and kennung like '"+Cbo.getSelectedItem()+"%'");
		if (!Qry.eof())
		{
			Cbo.addItem(Qry.getS("Bezeichnung"),Qry.getI("aic_stamm"),Qry.getS("kennung"),0);
			Cbo.setSelectedAIC(Qry.getI("aic_stamm"));
			Cbo.setAktAIC(-2);
			g.progInfo("********* hinzugefügt mit isValid: "+Qry.getS("kennung")+", Aic="+Cbo.getSelectedAIC()+", Text="+Cbo.getSelectedItem());
		}
		else
		{
			g.progInfo("**************************** nicht gefunden");
			return false;
		}
		Qry.free();
	}

	return true;
}

/*public boolean FilterModified()
{
	return iAbfrage != iAbfrageOld;
}*/

public int getFilter()
{
	//iAbfrageOld = iAbfrage;
	return iAbfrage;
}

public void setEnabled(boolean b)
{
	Cbo.setEnabled(b);
        //if (!b)
        //  Cbo.setFirst();
        if (Static.bStern)
          BtnList.setEnabled(b && Cbo.get(ComboSort.EDIT));
}

public void setEditable(boolean b)
{
  Cbo.setEditable1(b);
  if (Static.bStern)
    BtnList.setEnabled(b);
}

public void setFont(Font font)
{
	if (Cbo !=null)
	{
		if (font==null)
			font=Transact.fontStandard;
		Cbo.setFont(font);
                if (Static.bStern)
                  BtnList.setFont(font);
	}
}

/*public void setBackground(Color c)
{
  if (Cbo !=null)
  {
    Cbo.setBackground(c);
    if (Static.bStern)
      BtnList.setBackground(c);
  }
}*/

public Tabellenspeicher getTabDaten() // für Planung zum Popup-füllen
{
        return TabDaten;
}

public void setMulti(boolean b,ActionListener ev)
      {
        //bMulti=b;
        evTab=ev;
        Out.setAllowMultipleSelections(b);
      }

public Vector<Integer> getSelectedAics()
      {
        Vector<Integer> Vec=new Vector<Integer>();
        JCOutlinerNode[] Nodes=Out.getSelectedNodes();
        for (int i=0;i<Nodes.length;i++)
          if (TabKnoten.posInhalt("Knoten", Nodes[i]))
            Vec.addElement(TabKnoten.getInt("Aic"));
        return Vec;
      }

public void setStt(int riStt,int iFilter)
{
  if (iFilter!=iAbfrage)
  {
    iAbfrage=iFilter;
    iStt=0;
  }
  if (iStt!=riStt)
  {
    iStt=riStt;
    fillCbo(true);
  }
}

public int getStt()
{
  return iStt;
}

// add your data members here
private Global g;
private ComboSort Cbo;
private ComboSort CboSuche=null;
private JToggleButton BtnAlle;
private JToggleButton BtnPers;
private JToggleButton BtnDef;
private ButtonGroup BtnGrp;
private JButton BtnList;
private JButton BtnOk;
private JButton BtnInit;
private JButton BtnAbbruch;
private JButton BtnFilter;
private JButton BtnRefresh;
private JCheckBox CbxVoll;
//private JCheckBox CbxSuchen;
//private JCheckBox CbxSoundex;
private JCheckBox CbxSucheCase;
private Window Frm=null;
private AUComboList FrmThis;
private Text EdtSuche=null;
private AUOutliner Out = new AUOutliner(new JCOutlinerFolderNode(""));
private boolean bCenter = false;
private boolean bKein = false;
private boolean bLeer = false;
private boolean bFav = false;
private Tabellenspeicher TabKnoten;
private int iStt;
private int iAbfrage; // Filter
private int iAbfrageOld;
private int iAbf; // Übergeordnete Abfrage
private int iEig;
private int iRolle=0;
//private int iLast=-1;
//private JCOutlinerFolderNode NodeAbfrage;
private String sTitle;
private ShowAbfrage Abf=null;
private Tabellenspeicher TabSpalten=null;
private Tabellenspeicher TabDaten=null;
private boolean bFuellen;
private boolean bVorFilter;
private boolean bRefresh=true;
private boolean bBed=false;
//private boolean bSuchen;
private String sSQL=null;
private String sSQL2=null;
private JDialog Dlg;
private JDialog DlgR;
private Vector VecStamm=null;
private boolean bSuche=false;
private int iSPos=1;
//private int iIV=0;
//private boolean bMulti=false;
private boolean bOL=false; //Aufruf von Planung -> offen lassen
private ActionListener evTab=null;
public boolean bOk=false;
private boolean bEdit=false;
private boolean bUser=false;
private int iSpUser=1;
}

