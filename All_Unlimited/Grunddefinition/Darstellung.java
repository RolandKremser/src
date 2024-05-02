/*
    All_Unlimited-Grunddefinition-Darstellung.java

    NOTE: This file is a generated file.
          Do not modify it by hand!
*/

package All_Unlimited.Grunddefinition;

// add your custom import statements here
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
//import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import jclass.bwt.BWTEnum;
import jclass.bwt.JCItemEvent;
import jclass.bwt.JCItemListener;
//import jclass.bwt.JCOutliner;
import jclass.bwt.JCOutlinerFolderNode;
import jclass.bwt.JCOutlinerNode;
import jclass.bwt.JCOutlinerNodeStyle;
import All_Unlimited.Allgemein.AUVector;
import All_Unlimited.Allgemein.Formular;
import All_Unlimited.Allgemein.Global;
import All_Unlimited.Allgemein.GroupBox;
import All_Unlimited.Allgemein.Message;
import All_Unlimited.Allgemein.SQL;
import All_Unlimited.Allgemein.Sort;
import All_Unlimited.Allgemein.Static;
import All_Unlimited.Allgemein.Tabellenspeicher;
import All_Unlimited.Allgemein.Parameter;
import All_Unlimited.Allgemein.Eingabe.AUOutliner;
import All_Unlimited.Allgemein.Eingabe.AUCheckBox;
import All_Unlimited.Allgemein.Eingabe.AUTextArea;
import All_Unlimited.Allgemein.Eingabe.ComboSort;
import All_Unlimited.Allgemein.Eingabe.EF_Eingabe;
import All_Unlimited.Allgemein.Eingabe.SpinBox;
import All_Unlimited.Allgemein.Eingabe.Text;
import All_Unlimited.Allgemein.Eingabe.BildEingabe;
import All_Unlimited.Hauptmaske.Abfrage;
import All_Unlimited.Print.Druckdefinition;
import javax.swing.JSplitPane;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.JToolBar;
import javax.swing.border.EmptyBorder;
import java.awt.Insets;
import All_Unlimited.Allgemein.Eingabe.Schrift;
import javax.swing.JToggleButton;
import jclass.bwt.JCOutlinerComponent;
import javax.swing.JPopupMenu;
//import javax.swing.JFrame;

public class Darstellung extends JFrame
{
    /**
	 *
	 */
    private static final long serialVersionUID = 6572611317344004690L;

    public static Darstellung start1(int riAIC_Frame,long rlBits,Global glob)
    {
      if (This1 != null)
      {
        This1.iAIC_Begriff = riAIC_Frame;
        This1.lBits=rlBits;
      }
      return This1==null?new Darstellung(glob,riAIC_Frame,rlBits,true):This1;
    }

    public static Darstellung start2(int riAIC_Frame,long rlBits,Global glob)
    {
      if (This2 != null)
      {
        This2.iAIC_Begriff = riAIC_Frame;
        This2.lBits=rlBits;
      }
      return This2==null?new Darstellung(glob,riAIC_Frame,rlBits,false):This2;
    }

	public static void free()
	{
		if (This1!=null)
		{
			This1.g.testInfo("Darstellung.free");
			if (This1.bEdit)
				This1.entsperren();
			This1.dispose();
			This1=null;
		}
        if (This2!=null)
        {
                This2.g.testInfo("Darstellung2.free");
                if (This2.bEdit)
    				This2.entsperren();
                This2.dispose();
                This2=null;
        }
	}

        private JFrame getThis()
        {
          return iNr==1 ? This1:iNr==2 ? This2:null;
        }

	private Darstellung(Global glob,int riAIC_Frame,long rlBits,boolean b)
	{
		g=glob;
                iFF=g.getFontFaktor();
                iAIC_Begriff = riAIC_Frame;
                lBits=rlBits;
                //g.progInfo("!!!!! neue Darstellung !!!!!");
                if (b)
                  This1=this;
                else
                  This2=this;
		//g.putTabFormulare(0,0,this);

		Build(b?1:2);
	}

	public void show2()
	{
                bRefresh=false;
		bShow=true;
                TabGruppe=null;
                bEdit=false;
                int iPos=g.TabBegriffe.getPos("Aic",iAIC_Begriff);
		setTitle(/*g.getBezeichnung("Tabellenname","Darstellung")*/g.TabBegriffe.getBezeichnungS(iBegriff)+" - "+g.TabBegriffe.getS(iPos,"Bezeichnung"));
                bSpeichern=false;
		iAIC_Frame=Formular.getForm(g,iAIC_Begriff);

		Vector<Object> VecRootVisible = new Vector<Object>();
		Vector<Comparable> VecRootInvisible = new Vector<Comparable>();
                VecRootVisible.addElement(g.TabBegriffe.getInhalt("DefBezeichnung",iPos));
		g.printInfo("Formular="+iAIC_Frame);
		VecRootVisible.addElement("Frame");
		VecRootVisible.addElement(null);
		VecRootVisible.addElement(new Integer(10));
		VecRootVisible.addElement(new Integer(10));
		VecRootVisible.addElement(new Integer(999));
		VecRootVisible.addElement(new Integer(666));
		VecRootInvisible.addElement(iAIC_Begriff);
		VecRootInvisible.addElement("Root");
		VecRootInvisible.addElement(new Integer(1));
		VecRootInvisible.addElement(null);
		OutBuild.getRootNode().setLabel(VecRootVisible);
		OutBuild.getRootNode().setUserData(VecRootInvisible);
		Image ImgGruppe=g.getImage("Begriffgruppe","Frame");
		if (ImgGruppe != null)
		{
			JCOutlinerNodeStyle StyFolder = new JCOutlinerNodeStyle();
			StyFolder.setFolderClosedIcon(ImgGruppe);
			StyFolder.setFolderOpenIcon(ImgGruppe);
			StyFolder.setFont(new Font("TimesRoman",0,16));
			OutBuild.getRootNode().setStyle(StyFolder);
		}
		//g.progInfo("*1");
		//g.progInfo("*2");
                if (Static.bAutoEdit)
                  Edit();
                else
                  Laden();
		//Show_Parameter_Eingabe();
		setSize(getSize().width+1, getSize().height);
		setSize(getSize().width-1, getSize().height);

		//g.progInfo("*3");
		super.setVisible(true);
                Show_Parameter_Eingabe();
		bShow=false;
		//bNeu=false;
		EnableButtons();
                bRefresh=true;
	}

	private void Build(int riNr)
	{
          iNr=riNr;
		//bStammtyp = g.TabBegriffe.getI("STT")!=0;
                String sTitel="Darstellung"+(iNr==1?"":""+iNr);
                Para=new Parameter(g,sTitel);
                iBegriff=g.getBegriffAicS("Button",sTitel);
                CbxDarstellen = g.getCheckbox("Auto-Show",false);
                CbxGenau = g.getCheckbox("genauer",false);
                //g.progInfo("iBegriff="+iBegriff);
                setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
                FrmShow = new JDialog(this,"");
		FrmShow.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
                FrmShow.addWindowListener(new WindowListener()
                {
                  public void windowClosed(WindowEvent e) {}
                  public void windowOpened(WindowEvent e) {}
                  public void windowClosing(WindowEvent e) {
                    CbxDarstellen.setSelected(false);
                  }
                  public void windowIconified(WindowEvent e) {}
                  public void windowDeiconified(WindowEvent e) {}
                  public void windowActivated(WindowEvent e) {}
                  public void windowDeactivated(WindowEvent e) {}
                });

		OutBuild.setBackground(Color.white);
		OutBuild.setCopy(true);

		//OutRootPanel.setLabel(sPanel);
		//OutRootButton.setLabel(sButton);

		TabRegion=new Tabellenspeicher(g,"SELECT AIC_Begriff,Begriff.Kennung,DefBezeichnung Bezeichnung,null Sort FROM Begriff"+g.join("Begriffgruppe","Begriff")+" WHERE Begriffgruppe.Kennung='region'",true);
		TabRegion.checkBezeichnung();
                TabRegion.setInhalt(TabRegion.getPos("Kennung","North"),"Sort",1);
                TabRegion.setInhalt(TabRegion.getPos("Kennung","West"),"Sort",3);
                TabRegion.setInhalt(TabRegion.getPos("Kennung","Center"),"Sort",3);
                TabRegion.setInhalt(TabRegion.getPos("Kennung","East"),"Sort",4);
                TabRegion.setInhalt(TabRegion.getPos("Kennung","South"),"Sort",5);
                TabRegion.sort("Sort",true);
                //TabRegion.showGrid("TabRegion");
		TabAlign=new Tabellenspeicher(g,"SELECT Code.Kennung"+g.AU_Bezeichnung("Code")+" FROM Code"+g.join("Begriffgruppe","Code")+" WHERE Begriffgruppe.Kennung='Alignment'",true);
		TabAlign.checkBezeichnung();

		String[] s = new String[] {g.getShow("Bezeichnung"),g.getShow("Kennung"),g.getShow("Anzahl"),g.getShow("Change"),g.getBegriffS("Checkbox","Jeder")};
		String[] s2 = new String[] {g.getShow("Bezeichnung"),g.getShow("Kennung"),g.getShow("Anzahl"),g.getShow("Change"),g.getBegriffS("Checkbox","Jeder"),g.getBegriffS("Checkbox","Web")};
		String[] s3 = new String[] {g.getShow("Bezeichnung"),g.getShow("Kennung"),g.getShow("Anzahl"),g.getShow("Change"),g.getBegriffS("Checkbox","Jeder"),g.getBegriffS("Checkbox","Web"),g.getShow("Ber")};
                //String[] s2 = new String[] {g.getBegriff("Show","Bezeichnung"),g.getBegriff("Show","Kennung"),g.getBegriff("Show","Anzahl"),g.getBezeichnung("Tabellenname","STAMMTYP")};
		OutBuild.setColumnButtons(s);
		OutBuild.setNumColumns(s.length);
		OutBuild.setColumnLabelSortMethod(Sort.sortMethod);
		OutBuild.setColumnWidth(0,300);
		OutBuild.setAllowMultipleSelections(true);
                OutPanel.setColumnButtons(s);
		OutPanel.setNumColumns(s.length);
		OutPanel.setColumnLabelSortMethod(Sort.sortMethod);
		OutPanel.setColumnWidth(0,300);
                OutPanel.setColumnWidth(1,100);
		OutButton.setColumnButtons(s);
		OutButton.setNumColumns(s.length);
		OutButton.setColumnLabelSortMethod(Sort.sortMethod);
		OutButton.setColumnWidth(0,300);
                OutButton.setColumnWidth(1,100);
                OutWeb.setColumnButtons(s);
                OutWeb.setNumColumns(s.length);
                OutWeb.setColumnLabelSortMethod(Sort.sortMethod);
                OutWeb.setColumnWidth(0,300);
                OutWeb.setColumnWidth(1,100);
		OutGruppe.setColumnButtons(s);
		OutGruppe.setNumColumns(s.length);
		OutGruppe.setColumnLabelSortMethod(Sort.sortMethod);
		OutGruppe.setColumnWidth(0,300);
                OutGruppe.setColumnWidth(1,100);
		OutFrei.setColumnButtons(s);
		OutFrei.setNumColumns(s.length);
		OutFrei.setColumnLabelSortMethod(Sort.sortMethod);
		OutFrei.setColumnWidth(0,300);
                OutFrei.setColumnWidth(1,100);
		OutLabel.setColumnButtons(s);
		OutLabel.setNumColumns(s.length);
		OutLabel.setColumnLabelSortMethod(Sort.sortMethod);
		OutLabel.setColumnWidth(0,300);
                OutLabel.setColumnWidth(1,100);
		OutBild.setColumnButtons(s);
		OutBild.setNumColumns(s.length);
		OutBild.setColumnLabelSortMethod(Sort.sortMethod);
		OutBild.setColumnWidth(0,300);
                OutBild.setColumnWidth(1,100);
		OutTitel.setColumnButtons(s3);
		OutTitel.setNumColumns(s3.length);
		OutTitel.setColumnLabelSortMethod(Sort.sortMethod);
		OutTitel.setColumnWidth(0,300);
                OutTitel.setColumnWidth(1,100);
		OutApplikation.setColumnButtons(s);
		OutApplikation.setNumColumns(s.length);
		OutApplikation.setColumnLabelSortMethod(Sort.sortMethod);
		OutApplikation.setColumnWidth(0,300);
                OutApplikation.setColumnWidth(1,100);
		OutFormular.setColumnButtons(s2);
		OutFormular.setNumColumns(s2.length);
		OutFormular.setColumnLabelSortMethod(Sort.sortMethod);
		OutFormular.setColumnWidth(0,300);
                OutFormular.setColumnWidth(1,100);
		OutCheckbox.setColumnButtons(s);
		OutCheckbox.setNumColumns(s.length);
		OutCheckbox.setColumnLabelSortMethod(Sort.sortMethod);
		OutCheckbox.setColumnWidth(0,300);
                OutCheckbox.setColumnWidth(1,100);
		OutAbfrage.setColumnButtons(s2);
		OutAbfrage.setNumColumns(s2.length);
		OutAbfrage.setColumnLabelSortMethod(Sort.sortMethod);
		OutAbfrage.setColumnWidth(0,300);
                OutAbfrage.setColumnWidth(1,100);
		OutModell.setColumnButtons(s2);
		OutModell.setNumColumns(s2.length);
		OutModell.setColumnLabelSortMethod(Sort.sortMethod);
		OutModell.setColumnWidth(0,300);
                OutModell.setColumnWidth(1,100);
		OutRadiobutton.setColumnButtons(s);
		OutRadiobutton.setNumColumns(s.length);
		OutRadiobutton.setColumnLabelSortMethod(Sort.sortMethod);
		OutRadiobutton.setColumnWidth(0,300);
                OutRadiobutton.setColumnWidth(1,100);
		OutGrafik.setColumnButtons(s);
		OutGrafik.setNumColumns(s.length);
		OutGrafik.setColumnLabelSortMethod(Sort.sortMethod);
		OutGrafik.setColumnWidth(0,300);
                OutGrafik.setColumnWidth(1,100);
		OutPlanung.setColumnButtons(s);
		OutPlanung.setNumColumns(s.length);
		OutPlanung.setColumnLabelSortMethod(Sort.sortMethod);
		OutPlanung.setColumnWidth(0,300);
                OutPlanung.setColumnWidth(1,100);
		OutDruck.setColumnButtons(s);
		OutDruck.setNumColumns(s.length);
		OutDruck.setColumnLabelSortMethod(Sort.sortMethod);
		OutDruck.setColumnWidth(0,300);
                OutDruck.setColumnWidth(1,100);
                OutMenge.setColumnButtons(s);
		OutMenge.setNumColumns(s.length);
		OutMenge.setColumnLabelSortMethod(Sort.sortMethod);
		OutMenge.setColumnWidth(0,300);
                OutMenge.setColumnWidth(1,100);
                Refresh(true);
		CboX = new ComboSort(g);
		//CboSchrift = new ComboSort(g);
		Fill_Combo_X();
                CboX.bKeinSoll=true;
		//Fill_Combo_Schrift();


		s = new String[]{g.getBegriffS("Show","Bezeichnung"),g.getBegriffS("Show","Kennung"),g.getBegriffS("Show","Position"),"X","Y","W","H"};
		OutBuild.setColumnButtons(s);
		OutBuild.setNumColumns(s.length);
		OutBuild.setColumnWidth(0,220);
		OutBuild.setColumnWidth(1,120);
                OutBuild.setColumnWidth(2,60);
		OutBuild.setColumnWidth(3,60);
                OutBuild.setColumnWidth(4,40);
                OutBuild.setColumnWidth(5,40);
                OutBuild.setColumnWidth(6,40);
                OutBuild.setColumnAlignment(3,BWTEnum.TOPRIGHT);
                OutBuild.setColumnAlignment(4,BWTEnum.TOPRIGHT);
                OutBuild.setColumnAlignment(5,BWTEnum.TOPRIGHT);
                OutBuild.setColumnAlignment(6,BWTEnum.TOPRIGHT);
		OutBuild.setColumnLabelSort(false);

                BtnEdit = g.getButton("editieren");
		JButton BtnDrucken = g.getButton("Druck");
                //BtnDruck = g.getButton("Druck");
		//BtnOk = g.getButton("Ok");
		//BtnAbbruch = g.getButton("Abbruch");
                BtnRuecksetzen=g.getButton("Ruecksetzen");
                BtnSpeichern = g.getButton("Speichern");
                JButton BtnBeenden = g.getButton("Beenden");
		//BtnUebernehmen = g.getButton("Uebernehmen");
		JButton BtnRefresh = g.getButton("Refresh");
		//BtnDefinition = g.getButton("Definition");
		JButton BtnAbfrage = g.getButton("Definition");//g.getFrameS("Abfrage");
                JButton BtnShow = g.getButton("show Frame");
		BtnHinzufuegen = g.getButton(">>");
                BtnHinzu2 = g.getButton(">");
		BtnEntfernen = g.getButton("<<");
                BtnDel2 = g.getButton("<");
		BtnSeperator = g.getButton("Seperator");
		BtnRauf = g.getButton("Pfeil oben");
		BtnRunter = g.getButton("Pfeil unten");
		BtnErsetzen = g.getButton("Ersetzen");

                BtnNeu = g.getButton("Neu");
                BtnParameter = g.getButton("Parameter");
                BtnDel = g.getButton("Loeschen");

                PnlPfeil.add(BtnEdit);
                PnlPfeil.add(BtnHinzufuegen);
		PnlPfeil.add(BtnHinzu2);
                PnlPfeil.add(BtnDel2);
                PnlPfeil.add(BtnEntfernen);
                PnlPfeil.add(BtnErsetzen);
		PnlPfeil.add(BtnSeperator);
		PnlPfeil.add(BtnRauf);
		PnlPfeil.add(BtnRunter);

                JPanel PnlPara = new JPanel(new BorderLayout(2,2));
                JPanel PnlParaBtn = new JPanel(new GridLayout(1,0,2,2));
                JPanel PnlParaCbx = new JPanel(new GridLayout(1,0,2,2));
		JPanel PnlParaGrund = new JPanel(new GridLayout(1,2,2,2));
		JPanel PnlPara1 = new JPanel(new BorderLayout());
		JPanel PnlPara2 = new JPanel(new BorderLayout());
                JPanel PnlPara3 = new JPanel(new BorderLayout());
                JPanel PnlPara4 = new JPanel(new BorderLayout());
		//JPanel PnlParaEdt2 = new JPanel(new GridLayout(0,1,2,2));
		//JPanel PnlParaLbl1 = new JPanel(new GridLayout(0,1,2,2));
		//JPanel PnlParaLbl2 = new JPanel(new GridLayout(0,1,2,2));
		//JPanel PnlParaUR = new JPanel(new GridLayout(1,2,2,2));
		//JPanel PnlParaOA = new JPanel(new GridLayout(1,2,2,2));
		JPanel PnlWest = new JPanel(new BorderLayout(2,2));

		//PnlParaLbl1.add(new JLabel());
		//PnlParaLbl2.add(new JLabel());
		/*PnlParaLbl1.add(new JLabel("X: ",SwingConstants.RIGHT));
		PnlParaLbl2.add(new JLabel("Y: ",SwingConstants.RIGHT));
		PnlParaLbl1.add(new JLabel("W: ",SwingConstants.RIGHT));
		PnlParaLbl2.add(new JLabel("H: ",SwingConstants.RIGHT));*/

		PnlParaCbx.add(CbxDarstellen);
		PnlParaCbx.add(CbxGenau);
		PnlParaEdt1.add(CboX);
                EdtX.setFont(g.fontTitel);
                EdtY.setFont(g.fontTitel);
                EdtW.setFont(g.fontTitel);
                EdtH.setFont(g.fontTitel);
		//PnlParaEdt2.add(EdtY);
		//PnlParaEdt1.add(EdtW);
		//PnlParaEdt2.add(EdtH);

                //BtnRuecksetzen.setPreferredSize(new Dimension(100,30));
		//PnlParaBtn.add(BtnUebernehmen);
		//PnlParaBtn.add(BtnRuecksetzen);
                //PnlParaOA.add(BtnDrucken);
                //PnlParaOA.add(BtnDruck);
                //BtnSpeichern.setPreferredSize(new Dimension(100,30));
                PnlParaBtn.add(BtnRuecksetzen);
                PnlParaBtn.add(BtnDrucken);
                PnlParaBtn.add(BtnSpeichern);
                PnlParaBtn.add(BtnBeenden);

		PnlPara1.add("West",new JLabel("X: ",SwingConstants.RIGHT));
		PnlPara1.add("Center",PnlParaEdt1);
		//PnlParaLeft.add("South",PnlParaUR);
                PnlPara2.add("West",new JLabel("Y: ",SwingConstants.RIGHT));
		PnlPara2.add("Center",EdtY);
		PnlPara3.add("West",new JLabel("W: ",SwingConstants.RIGHT));
		PnlPara3.add("Center",EdtW);
                PnlPara4.add("West",new JLabel("H: ",SwingConstants.RIGHT));
		PnlPara4.add("Center",EdtH);
		//PnlParaRight.add("South",PnlParaOA);

		PnlParaGrund.add(PnlPara1);
		PnlParaGrund.add(PnlPara2);
                PnlParaGrund.add(new JLabel());
                PnlParaGrund.add(PnlPara3);
                PnlParaGrund.add(PnlPara4);
                PnlParaGrund.setPreferredSize(new java.awt.Dimension(100,24));
                TxtMemoElement = new AUTextArea(g,0);
                TxtMemoElement.Edt.setEditable(false);
                splitPaneM = new JSplitPane(JSplitPane.VERTICAL_SPLIT,true,PnlTab,TxtMemoElement);
                splitPaneM.setOneTouchExpandable(true);
                splitPaneM.setResizeWeight(0.9);
		PnlWest.add("Center",splitPaneM);
                JPanel PnlSouth=new JPanel(new GridLayout());
                PnlSouth.add(BtnRefresh);
                PnlSouth.add(BtnShow);
                PnlSouth.add(BtnParameter);
                PnlSouth.add(BtnNeu);
                PnlSouth.add(BtnDel);
                PnlSouth.add(BtnAbfrage);
                PnlWest.add("South",PnlSouth);

		//PnlParaEdt1.remove(1);
		//PnlParaEdt1.add(CboX,1);

		//JCScrolledWindow PnlSP = new JCScrolledWindow();

		//PnlSP.add(OutBuild);

		//OutBuild.setScrollbarDisplay(JCOutliner.DISPLAY_VERTICAL_ONLY);

		PnlAufbau.add("Center",OutBuild);
		PnlAufbau.add("West",PnlPfeil);
                PnlPara.add("Center",PnlParaGrund);
                PnlPara.add("South",PnlParaBtn);
                PnlPara.add("West",PnlParaCbx);
		PnlAufbau.add("South",PnlPara);
                PnlWest.setMinimumSize(new java.awt.Dimension(100,100));
                PnlAufbau.setMinimumSize(new java.awt.Dimension(100,100));
		getContentPane().setLayout(new BorderLayout());
                splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,true,PnlWest,PnlAufbau);
                splitPane.setOneTouchExpandable(true);
                //splitPane.setDividerLocation(-2);
                splitPane.setResizeWeight(0.5);
                /*PropertyChangeListener propertyChangeListener =
                    new PropertyChangeListener() {
                  public void propertyChange(PropertyChangeEvent changeEvent)
                  {
                    if (changeEvent.getPropertyName().equals(JSplitPane.LAST_DIVIDER_LOCATION_PROPERTY))
                    {
                      int current = ((JSplitPane)changeEvent.getSource()).getDividerLocation();
                      System.out.println("Current: " + current);
                      //Integer last = (Integer)changeEvent.getNewValue();
                      //System.out.println("Last: " + last);
                      //Integer priorLast = (Integer)changeEvent.getOldValue();
                      //System.out.println("Prior last: " + priorLast);
                    }
                  }
                };
                splitPane.addPropertyChangeListener(propertyChangeListener);*/

		//getContentPane().add("West",PnlWest);
		getContentPane().add("Center",splitPane);

                g.getFenster(this,iBegriff,false,50,50,800,600,iFF);

		OutBuild.addItemListener(new JCItemListener()
		{
			public void itemStateChanged(JCItemEvent ev)
			{
                          boolean b=bRefresh;
                          bRefresh=false;
				if(ev.getStateChange()==ItemEvent.SELECTED)
					Show_Parameter_Eingabe();
				EnableButtons();
                          bRefresh=b;
			}
		});

                OutBuild.getOutliner().addMouseListener(new MouseListener()
	          {
	            public void mousePressed(MouseEvent ev) {}
	            public void mouseClicked(MouseEvent ev)
	            {
	                if (ev.getButton()==3 || ev.getButton()==1 && ev.getModifiersEx()==Global.iRM)
	                {
	                  if (TabAll.posInhalt("AIC", Sort.geti((Vector)OutBuild.getSelectedNode().getUserData(),0)))
                            popupD.show((Component)ev.getSource(), ev.getX(), ev.getY());
	                  /*{
	                      Static.makeVisible(null, ((JCOutlinerNode)TabAll.getInhalt("Node")));
	                      PnlTab.setSelectedIndex(TabAll.getI("Tab"));
	                  }*/
	                }
                        /*else if (ev.getButton()==1 && ev.getClickCount()==2 && bEdit)
                        {
                          //JCOutlinerNode OutNod = OutBuild.getSelectedNode();
                          //g.fixInfo("Kommentar setzen:"+OutNod.getLabel()+"/"+OutNod.getUserData());
                          if (Kommentar())
                            ;

                        }*/
	            }
	            public void mouseEntered(MouseEvent ev) {}
	            public void mouseExited(MouseEvent ev)  {}
	            public void mouseReleased(MouseEvent ev){}
	          });

                  JCItemListener ItemList=new JCItemListener()
                  {
                          public void itemStateChanged(JCItemEvent ev)
                          {
                            if (ev.getStateChange() == ItemEvent.SELECTED)
                            {
                              EnableButtons();
                              //int iPos=g.TabBegriffe.getPos("Aic",((AUOutliner)ev.getSource()).getSelect(0));
                              //if (iPos>=0)
                                TxtMemoElement.setText(g.getMemo(((AUOutliner)ev.getSource()).getSelect(0)));
                            }
                          }
                  };
		OutPanel.addItemListener(ItemList);
                OutButton.addItemListener(ItemList); // war ausgeblendet
                OutWeb.addItemListener(ItemList);
		OutAbfrage.addItemListener(ItemList);
		OutFormular.addItemListener(ItemList);
		OutDruck.addItemListener(ItemList);
                OutMenge.addItemListener(ItemList);

		PnlTab.addChangeListener(new ChangeListener ()
		{
			public void stateChanged(ChangeEvent ev)
			{
				EnableButtons();
			}
		});

                ActionListener AL=new ActionListener()
                {
                        public void actionPerformed(ActionEvent ev)
                        {
                          //JButton Btn=(JButton)ev.getSource();
                          String s = ev.getActionCommand();
                          if (s.equals("Beenden"))
                            Beenden();
                          else if (s.equals("Speichern"))
                            Save();
                          else if (s.equals("BegriffNeu"))
                            Parameter(true);
                          else if (s.equals("BegriffEdit"))
                            Parameter(false);
                          else if (s.equals("BegriffDel"))
                            Delete();
                          else if (s.equals("Edit"))
                            Edit();
                          else if (s.equals("Drucken"))
                            OutBuild.print();
                          else if (s.equals("Refresh"))
                          {
                            long lClock = Static.get_ms();
                            g.LoadBegriffMemo();
                            Refresh(false);
                            g.clockInfo("Refresh Darstellung", lClock);
                          }
                          else if (s.equals("Aufruf"))
                            Aufruf();
                          else if (s.equals("show"))
                            showUsed();
                          else if (s.equals("sync"))
                          {
                              Static.makeVisible(null, ((JCOutlinerNode)TabAll.getInhalt("Node")));
                              PnlTab.setSelectedIndex(TabAll.getI("Tab"));
                          }
                          else if (s.equals("hide"))
                            Kommentar(null);
                          else if (s.equals("Show_Frame"))
                            Show_Frame();
                          else if (s.startsWith("Refresh"))
                        	  RefreshOne(s.equals("Refresh web"));//g.fixtestError(s+" noch nicht unterstützt");ö
                          else
                          {
                            bSpeichern = true;
                            if (s.equals("Entfernen"))
                              BtnEntfernen_actionPerformed(ev);
                            else if (s.equals("Del2"))
                              BtnDel2_actionPerformed(ev);
                            else if (s.equals("Hinzu1"))
                              BtnHinzufuegen_actionPerformed(ev);
                            else if (s.equals("Hinzu2"))
                              BtnHinzu2_actionPerformed(ev);
                            else if (s.equals("Seperator"))
                              BtnSeperator_actionPerformed(null);
                            else if (s.equals("Ersetzen"))
                              BtnErsetzen_actionPerformed();
                            else if (s.equals("Rauf"))
                              Verschieben(true);
                            else if (s.equals("Runter"))
                              Verschieben(false);
                            Show_Parameter_Eingabe();
                          }
                        }
                };

                g.addMenuItem("sync",popupD,"sync").addActionListener(AL);
                g.addMenuItem("hide",popupD,"hide").addActionListener(AL);

                g.addMenuItem("show",popupB,"show").addActionListener(AL);
                g.addMenuItem("Definition",popupB,"Aufruf").addActionListener(AL);
                popupB.addSeparator();
                g.addMenuItem("Edit",popupB,"BegriffEdit").addActionListener(AL);
                g.addMenuItem("Neu",popupB,"BegriffNeu").addActionListener(AL);
                g.addMenuItem("Loeschen",popupB,"BegriffDel").addActionListener(AL);
                popupB.addSeparator();
//                int iPnlTabIndex = PnlTab.getSelectedIndex();
//                if(iPnlTabIndex==9)
                {
                  g.addMenuItem("Refresh",popupB,"Refresh").addActionListener(AL);
                  g.addMenuItem("Refresh web",popupB,"Refresh web").addActionListener(AL);
                }
                g.BtnAdd(BtnBeenden,"Beenden",AL);
                g.BtnAdd(BtnSpeichern,"Speichern",AL);
                g.BtnAdd(BtnNeu,"BegriffNeu",AL);
                g.BtnAdd(BtnParameter,"BegriffEdit",AL);
                g.BtnAdd(BtnDel,"BegriffDel",AL);

                g.BtnAdd(BtnEdit,"Edit",AL);
                g.BtnAdd(BtnDrucken,"Drucken",AL);
                g.BtnAdd(BtnRefresh,"Refresh",AL);
                g.BtnAdd(BtnAbfrage,"Aufruf",AL);
                g.BtnAdd(BtnShow,"show",AL);

                g.BtnAdd(BtnEntfernen,"Entfernen",AL);
                g.BtnAdd(BtnDel2,"Del2",AL);
                g.BtnAdd(BtnHinzufuegen,"Hinzu1",AL);
                g.BtnAdd(BtnHinzu2,"Hinzu2",AL);
                g.BtnAdd(BtnSeperator,"Seperator",AL);
                g.BtnAdd(BtnErsetzen,"Ersetzen",AL);
                g.BtnAdd(BtnRauf,"Rauf",AL);
                g.BtnAdd(BtnRunter,"Runter",AL);

		//BtnEntfernen.addActionListener(AL);
                //BtnDel2.addActionListener(AL);
		//BtnHinzufuegen.addActionListener(AL);
                //BtnHinzu2.addActionListener(AL);
		//BtnSeperator.addActionListener(AL);
		//BtnErsetzen.addActionListener(AL);
		//BtnRauf.addActionListener(AL);
		//BtnRunter.addActionListener(AL);
		//BtnSpeichern.addActionListener(AL);
                //BtnEdit.addActionListener(AL);
		//BtnDrucken.addActionListener(AL);
                //BtnNeu.addActionListener(AL);
                //BtnParameter.addActionListener(AL);
                //BtnDel.addActionListener(AL);

		BtnRuecksetzen.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ev)
			{
                          int pane = new Message(Message.YES_NO_OPTION, null, g).showDialog("verwerfen", new String[] {});
                          if(pane == Message.YES_OPTION)
                          {
                            entsperren();
                            //iLast = 0;
                            bEdit = false;
                            Laden();
                            EnableButtons();
                          }
			}
		});


              CboX.addItemListener(new ItemListener ()
              {
                      public void itemStateChanged(ItemEvent ev)
                      {
                        //g.progInfo("CboX");
                              if(ev.getStateChange() == ItemEvent.SELECTED)// && CboX.Modified())
                                      Uebernehmen();
                      }
              });


                EdtX.addChangeListener(new ChangeListener()
		{
                  public void stateChanged(ChangeEvent ae)
                  {
                    //int i=EdtX.getIntValue();
                    //g.progInfo("EdtX:"+ae.toString()+"/"+i);
                    //if (EdtX.getValue().equals(Static.Int0)
                    //if (i<0)
                    //  EdtX.setValue(Static.Int0);
                    //else
                      Uebernehmen();
                  }
		});

		/*EdtX.addKeyListener(new KeyListener()
		{
			public void keyPressed(KeyEvent e)
			{
			}
			public void keyReleased(KeyEvent e)
			{
			}
			public void keyTyped(KeyEvent e)
			{
                          //g.progInfo("EdtX2");
                          Uebernehmen();
                          EdtX.requestFocus();
			}
		});*/

		EdtY.addChangeListener(new ChangeListener()
		{
			public void stateChanged(ChangeEvent ae)
			{
                          //int i=EdtY.getIntValue();
                          //g.progInfo("EdtY:"+i);
                          //if (i<0)
                          //  EdtY.setValue(Static.Int0);
                          //else
                            Uebernehmen();
			}
		});

		/*EdtY.addKeyListener(new KeyListener()
		{
			public void keyPressed(KeyEvent e)
			{
			}
			public void keyReleased(KeyEvent e)
			{
			}
			public void keyTyped(KeyEvent e)
			{
                          //g.progInfo("EdtY1");
				Uebernehmen();
                                EdtY.requestFocus();
			}
		});*/

                EdtW.addChangeListener(new ChangeListener()
                {
                        public void stateChanged(ChangeEvent ae)
                        {
                          //int i=EdtW.getIntValue();
                          //g.progInfo("EdtW:"+ae.toString()+"/"+i);
                          //if (i<0)
                          //  EdtW.setValue(Static.Int0);
                          //else
                            Uebernehmen();

                        }
                });

                /*EdtW.addKeyListener(new KeyListener()
                {
                        public void keyPressed(KeyEvent e)
                        {
                        }
                        public void keyReleased(KeyEvent e)
                        {
                        }
                        public void keyTyped(KeyEvent e)
                        {
                          //g.progInfo("EdtW2");
                          Uebernehmen();
                          EdtW.requestFocus();
                        }
                });*/

                EdtH.addChangeListener(new ChangeListener()
                {
                        public void stateChanged(ChangeEvent ae)
                        {
                          //int i=EdtH.getIntValue();
                          //g.progInfo("EdtH:"+ae.toString()+"/"+i);
                          //if (i<0)
                          //  EdtH.setValue(Static.Int0);
                          //else
                            Uebernehmen();
                        }
                });

                /*EdtH.addKeyListener(new KeyListener()
                {
                        public void keyPressed(KeyEvent e)
                        {
                        }
                        public void keyReleased(KeyEvent e)
                        {
                        }
                        public void keyTyped(KeyEvent e)
                        {
                          Uebernehmen();
                          EdtH.requestFocus();
                        }
                });*/

                /*BtnRefresh.addActionListener(new ActionListener()
                {
                        public void actionPerformed(ActionEvent ev)
                        {
                          long lClock = Static.get_ms();
                          g.LoadBegriffMemo();
                          Refresh(false);
                          g.clockInfo("Refresh Darstellung",lClock);
                        }
		});*/

                /*BtnShow.addActionListener(new ActionListener()
                {
                  public void actionPerformed(ActionEvent e)
                  {
                    Vector Vec=(Vector)(TabIndextoOut(PnlTab.getSelectedIndex())).getUserData();
                    if (Vec!=null)
                    {
                      int iAic = Tabellenspeicher.geti(Vec.elementAt(0));
                      String sBez = g.TabBegriffe.getBezeichnungS(iAic);
                      String sKennung = g.TabBegriffe.getKennung(iAic);
                      new Tabellenspeicher(g, "select distinct b.aic_begriff,b.defbezeichnung,b.kennung" + g.AU_Bezeichnung("Stammtyp", "b") + " Stammtyp,count(*) Anzahl from begriff b" +
                                           g.join("formular", "b", "begriff") + g.join("darstellung", "d", "formular", "formular") + " where d.aic_begriff=" + iAic+
                                           " group by b.aic_begriff,b.defbezeichnung,b.kennung,b.aic_stammtyp", true).showGrid(sBez+" ("+sKennung+")");
                    }
                  }
                });*/

                /*BtnAbfrage.addActionListener(new ActionListener()
                {
                        public void actionPerformed(ActionEvent e)
                        {
                          int iPnlTab = PnlTab.getSelectedIndex();
                          if (iPnlTab==9)
                            DefAbfrageAufrufen(OutAbfrage.getSelectedNode().getLevel()<3 ? OutBuild:OutAbfrage,true);
                          else if (iPnlTab==11)
                            DefModell.get(g, Sort.geti(OutModell.getSelectedNode().getUserData(),0)).show();
                          else
                            Zuordnung2.get(g);
                        }
                });*/

		/*BtnDefinition.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ev)
			{
				//BtnDefinition.setEnabled(false);

				if(g.TabFormulare.posInhalt("AIC",g.getBegriffAic("Button","Definition")))
				{
					Def=(Definition)g.TabFormulare.getInhalt("Formular");
					Def.setTabelle("BEGRIFF");
					Def.setVisible(true);
				}
				else
				{
					Def = new Definition(g,"BEGRIFF");
					g.putTabFormulare(g.getBegriffAic("Button","Definition"),0,Def);
				}

			}
		});*/

		/*addWindowListener(new WindowListener()
		{
			public void windowClosed(WindowEvent e)
			{
				FrmShow.dispose();
				//g.printInfo("Darstellung windowClosed");
			}
			public void windowOpened(WindowEvent e)
			{
				//g.printInfo("Darstellung windowOpened");
			}
			public void windowClosing(WindowEvent e)
			{
				FrmShow.dispose();
				//g.printInfo("Darstellung windowClosing");
			}
			public void windowIconified(WindowEvent e)
			{
				//g.printInfo("Darstellung windowIconified");
			}
			public void windowDeiconified(WindowEvent e)
			{
				//g.printInfo("Darstellung windowDeiconified");
			}
			public void windowActivated(WindowEvent e)
			{
				//g.printInfo("Darstellung windowActivated");
			}
			public void windowDeactivated(WindowEvent e)
			{
				//g.printInfo("Darstellung windowDeactivated");
			}
		});*/

                g.CbxAdd(CbxDarstellen,"Show_Frame",AL);
                g.CbxAdd(CbxGenau,"Show_Frame",AL);
		/*CbxDarstellen.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ev)
			{
				Show_Frame();
			}
		});
                CbxGenau.addActionListener(new ActionListener()
                {
                        public void actionPerformed(ActionEvent ev)
                        {
                                Show_Frame();
                        }
                });*/
                Para.getParameter("Option1",true,false);
                if(Para.bGefunden)
                {
                  CbxDarstellen.setSelected((Para.int1&1)>0);
                  CbxGenau.setSelected((Para.int1&2)>0);
                  splitPane.setDividerLocation(Para.int2);
                  splitPaneM.setDividerLocation(Para.int3);
                  if (Para.int4>0)
                  {
                    OutBuild.setColumnWidth(0, Para.int4/1024);
                    OutBuild.setColumnWidth(1, Para.int4%1024);
                  }
                }
		/*
		Show_Parameter_Eingabe();
		setSize(getSize().width+1, getSize().height);
		setSize(getSize().width-1, getSize().height);
		EnableButtons();
		*/

	}

        private void showUsed()
        {
            Vector Vec=(Vector)(TabIndextoOut(PnlTab.getSelectedIndex())).getUserData();
            if (Vec!=null)
              showUsed(g,Sort.geti(Vec,0),getThis());
        }

        public static void showUsed(Global g,int iAic,JFrame Frm)
        {
          String sBez = g.TabBegriffe.getBezeichnungS(iAic);
          String sKennung = g.TabBegriffe.getKennung(iAic);
          Tabellenspeicher Tab=new Tabellenspeicher(g, "select 'selbst' Art,b.aic_begriff aic,b.defbezeichnung,b.kennung" + g.AU_Bezeichnung("Stammtyp", "b") + " Stammtyp,1 Anzahl,web from begriff b where b.aic_begriff=" + iAic,true);
          new Tabellenspeicher(g, "select distinct 'Formular' Art,b.aic_begriff aic,b.defbezeichnung,b.kennung" + g.AU_Bezeichnung("Stammtyp", "b") + " Stammtyp,count(*) Anzahl,web from begriff b" +
                                   g.join("formular", "b", "begriff") + g.join("darstellung", "d", "formular", "formular") + " where d.aic_begriff=" + iAic+
                                   " group by b.aic_begriff,b.defbezeichnung,b.kennung,b.aic_stammtyp,b.web", true).addTo(Tab, true);
          new Tabellenspeicher(g, "select (select kennung from begriffgruppe where aic_begriffgruppe=b.aic_begriffgruppe) Art,b.aic_begriff aic,b.defbezeichnung,b.kennung,'' Stammtyp,1 Anzahl,web from begriff b" +
        		  	" join begriff_zuordnung z on b.aic_begriff=z.aic_begriff and beg_aic_begriff=" + iAic, true).addTo(Tab, true);
          new Tabellenspeicher(g, "select 'Status' Art,s.aic_status aic"+g.AU_Bezeichnung("Status","s")+" defbezeichnung,s.kennung,'' Stammtyp,1 Anzahl,1 web from status s" +
        		  " join status_zuordnung z on s.aic_status=z.aic_status and z.aic_begriff=" + iAic, true).addTo(Tab, true);
          Tab.showGrid(sBez+" ("+sKennung+")",Frm);
        }

        private void Aufruf()
        {
          int iPnlTab = PnlTab.getSelectedIndex();
          /*if (iPnlTab==7)
            g.testInfo("Formular: Ebene "+OutFormular.getSelectedNode().getLevel()+", Aic="+Sort.geti(OutFormular.getSelectedNode().getUserData(),0));
          else if (iPnlTab==9)
            g.testInfo("Abfrage: Ebene "+OutAbfrage.getSelectedNode().getLevel()+", Aic="+Sort.geti(OutAbfrage.getSelectedNode().getUserData(),0));
          else if (iPnlTab==11)
            g.testInfo("Modell: Ebene "+OutModell.getSelectedNode().getLevel()+", Aic="+Sort.geti(OutModell.getSelectedNode().getUserData(),0));*/
          if (iPnlTab==7 && OutFormular.getSelectedNode().getLevel()==3)
            Darstellung.start2(Sort.geti(OutFormular.getSelectedNode().getUserData(),0),0,g).show2();
          else if (iPnlTab==9 && OutAbfrage.getSelectedNode().getLevel()==3)
            DefAbfrageAufrufen(/*OutAbfrage.getSelectedNode().getLevel()<3 ? OutBuild:*/OutAbfrage,true);
          else if (iPnlTab==11 && OutModell.getSelectedNode().getLevel()==3)
            DefModell.get(g, Sort.geti(OutModell.getSelectedNode().getUserData(),0)).show();
          else if (iPnlTab==15)
          {
            int iAic=Sort.geti(OutDruck.getSelectedNode().getUserData(),0);
            int iStt = SQL.getInteger(g, "select " + g.isnull() + "-aic_bewegungstyp,aic_stammtyp) from begriff where aic_begriff=" + iAic);
            Druckdefinition.get(g, true, iAic, iStt);
          }
          else if (iPnlTab==2 || iPnlTab==16)
            Zuordnung2.get(g);
        }

        @SuppressWarnings("unchecked")
        private boolean Kommentar(jclass.util.JCVector NodeC)
        {
          boolean b=false;
          boolean bNull=NodeC==null;
          JCOutlinerNode[] Nodes=bNull ? OutBuild.getSelectedNodes():null;
          int iL=bNull ? Nodes.length:NodeC.size();
          for (int i=0;i<iL;i++)
          {
            JCOutlinerNode NodeSelected = bNull ? Nodes[i]:(JCOutlinerNode)NodeC.elementAt(i);//OutBuild.getSelectedNode();
            if (NodeSelected != null)
            {
              Vector<Object> VecInvisible = (Vector)NodeSelected.getUserData();
              if (OutBuild.getRootNode() == NodeSelected)// && NodeSelected.getState() != BWTEnum.FOLDER_OPEN_ALL)
                ((JCOutlinerFolderNode)NodeSelected).setState(BWTEnum.FOLDER_OPEN_ALL);
              else
              {
                b=true;
                Kom(NodeSelected,VecInvisible);
                jclass.util.JCVector NodeC2=NodeSelected.getChildren();
                if (NodeC2 != null)
                  Kommentar(NodeC2);
              }
            }
          }
          if (bNull && b)
          {
            Show_Frame();
            bSpeichern = true;
            EnableButtons();
          }
          return b;
        }

        private void Kom(JCOutlinerNode NodeSelected,Vector<Object> VecInvisible)
        {
          int iIS=VecInvisible.size();
                if (iIS<6)
                  for (int i2=iIS;i2<6;i2++)
                    VecInvisible.addElement(null);
                //int iBits=Sort.geti(VecInvisible,5);
                boolean bKommentar = VecInvisible.elementAt(5)==null;
                VecInvisible.setElementAt(bKommentar ? Boolean.TRUE:null, 5);
                //makeKommentarStyle(NodeSelected, bKommentar,false);
                JCOutlinerNodeStyle StyNode = new JCOutlinerNodeStyle(NodeSelected.getStyle()==null?(new JCOutlinerComponent()).getDefaultNodeStyle():NodeSelected.getStyle());
                StyNode.setForeground(bKommentar? g.ColRem:OutBuild.getForeground());
                StyNode.setFont(bKommentar? g.fontRem:g.fontOutliner);//new Font(g.fontModell.getName(),bKommentar?Font.ITALIC:g.fontStandard.getStyle(),g.fontStandard.getSize()));
                NodeSelected.setStyle(StyNode);
        }

        private void entsperren()
        {
          if (iSperre>0)
          {
            g.exec("update begriff set log_aic_logging=null where aic_begriff=" + iSperre);
            g.defInfo("Formular " + g.TabBegriffe.getBezeichnungS(iSperre) + " (" + iSperre + ") entsperrt");
            iSperre = 0;
          }
        }

        private void Edit()
        {
          entsperren();
          if (g.exec("update begriff set log_aic_logging=" + g.getLog() + " where log_aic_logging is null and aic_begriff=" + iAIC_Begriff) == 1)
          {
            iSperre=iAIC_Begriff;
            g.defInfo("Formular "+g.TabBegriffe.getBezeichnungS(iSperre)+" ("+iSperre+") gesperrt");
            bEdit = true;
            Laden();
            EnableButtons();
          }
          else
          {
            String sUser = SQL.getString(g, "select benutzer.kennung from benutzer join logging on benutzer.aic_benutzer=logging.aic_benutzer" +
                                         " join begriff on logging.aic_logging=begriff.log_aic_logging where aic_begriff=" + iAIC_Begriff);
            new Message(Message.WARNING_MESSAGE, null, g).showDialog("Formular_gesperrt", new String[]
                {sUser});
            //if (iLast !=iAIC_Begriff)
              Laden();
          }
        }
        
        private void RefreshOne(boolean rbWeb)
        {
        	bWeb=rbWeb;
        	int iPnlTabIndex = PnlTab.getSelectedIndex();
        	if (iPnlTabIndex==7)
        		Fill_Tab_Outliners(7,"Frame",OutFormular,true,false);
        	else if (iPnlTabIndex==9)
        		Fill_Tab_Outliners(9,"Abfrage",OutAbfrage,true,false);
        	bWeb=false;
        }

        private void Refresh(boolean bNeu)
        {
          TabAll=new Tabellenspeicher(g,new String[]{"Aic","Node","Tab"});
          Fill_Tab_Outliners(0,"Panel",OutPanel,false,bNeu);
          Fill_Tab_Outliners(1,"Button",OutButton,true,bNeu);
          Fill_Tab_Outliners(2,"Gruppe",OutGruppe,true,bNeu);
          Fill_Tab_Outliners(3,"frei",OutFrei,true,bNeu);
          Fill_Tab_Outliners(4,"Label",OutLabel,true,bNeu);
          Fill_Tab_Outliners(5,"Titel",OutTitel,false,bNeu);
          Fill_Tab_Outliners(6,"Applikation",OutApplikation,true,bNeu);
          Fill_Tab_Outliners(7,"Frame",OutFormular,true,bNeu);
          Fill_Tab_Outliners(8,"Checkbox",OutCheckbox,true,bNeu);
          Fill_Tab_Outliners(9,"Abfrage",OutAbfrage,true,bNeu);
          Fill_Tab_Outliners(10,"Bild",OutBild,true,bNeu);
          Fill_Tab_Outliners(11,"Modell",OutModell,true,bNeu);
          Fill_Tab_Outliners(12,"Radiobutton",OutRadiobutton,true,bNeu);
          Fill_Tab_Outliners(13,"Grafik",OutGrafik,false,bNeu);
          Fill_Tab_Outliners(14,"Planung",OutPlanung,true,bNeu);
          Fill_Tab_Outliners(15,"Druck",OutDruck,true,bNeu);
          Fill_Tab_Outliners(16,"Formularmenge",OutMenge,true,bNeu);
          Fill_Tab_Outliners(17,"Web",OutWeb,true,bNeu);
          //TabAll.showGrid("TabAll");
        }

        private JCOutlinerNode TabIndextoOut(int iPnlTabIndex)
        {
          if(iPnlTabIndex==0)
                    return OutPanel.getSelectedNode();
            else if(iPnlTabIndex==1)
                    return OutButton.getSelectedNode();
            else if(iPnlTabIndex==2)
                    return OutGruppe.getSelectedNode();
            else if(iPnlTabIndex==3)
                    return OutFrei.getSelectedNode();
            else if(iPnlTabIndex==4)
                    return OutLabel.getSelectedNode();
            else if(iPnlTabIndex==5)
                    return OutTitel.getSelectedNode();
            else if(iPnlTabIndex==6)
                    return OutApplikation.getSelectedNode();
            else if(iPnlTabIndex==7)
                    return OutFormular.getSelectedNode();
            else if(iPnlTabIndex==8)
                    return OutCheckbox.getSelectedNode();
            else if(iPnlTabIndex==9)
                    return OutAbfrage.getSelectedNode();
            else if(iPnlTabIndex==10)
                    return OutBild.getSelectedNode();
            else if(iPnlTabIndex==11)
                    return OutModell.getSelectedNode();
            else if(iPnlTabIndex==12)
                    return OutRadiobutton.getSelectedNode();
            else if(iPnlTabIndex==13)
                    return OutGrafik.getSelectedNode();
            else if(iPnlTabIndex==14)
                    return OutPlanung.getSelectedNode();
            else if(iPnlTabIndex==15)
                    return OutDruck.getSelectedNode();
            else if(iPnlTabIndex==16)
                    return OutMenge.getSelectedNode();
            else if(iPnlTabIndex==17)
                    return OutWeb.getSelectedNode();
            else
              return null;
        }

        private void Parameter(boolean bNeu)
        {
          int iPnlTabIndex = PnlTab.getSelectedIndex();
          if (iPnlTabIndex==0 || iPnlTabIndex==9 || iPnlTabIndex==11 || iPnlTabIndex==7)
            return;
          if (DlgParameter==null)
          {
            DlgParameter=new JDialog(this,true);

            JPanel PnlEingabe = new JPanel(new BorderLayout());
             JPanel PnlLabel = new JPanel(new GridLayout(0,1,2,2));
             JPanel PnlEdit = new JPanel(new GridLayout(0,1,2,2));
             g.addLabel(PnlLabel," "+g.getBegriffS("Show","DefBezeichnung"));
              TxtDefBezeichnung= new Text("",255);
              PnlEdit.add(TxtDefBezeichnung);
             g.addLabel(PnlLabel," "+g.getBegriffS("Show","Bezeichnung"));
              TxtBezeichnung= new Text("",255);
              PnlEdit.add(TxtBezeichnung);
             g.addLabel(PnlLabel," "+g.getBegriffS("Show","Kennung"));
              TxtKennung = new Text("",40,Text.KENNUNG);
              PnlEdit.add(TxtKennung);
             g.addLabel(PnlLabel," "+g.getBegriffS("Show","Homepage"));
              TxtHomepage= new Text("",255);
              PnlEdit.add(TxtHomepage);
             g.addLabel(PnlLabel," "+g.getBegriffS("Show","Schrift"));
              CboSchrift = new Schrift(g);
              PnlEdit.add(CboSchrift);
             g.addLabel(PnlLabel," "+g.getBegriffS("Show","Hotkey"));
              JPanel PnlSub=new JPanel(new GridLayout());
              TxtHotkey = new Text("",1,Text.KENNUNG);
              CbxCombo=g.getCheckbox("combo");
              CbxWeb=g.getCheckbox("Web");
              PnlSub.add(TxtHotkey);
              PnlSub.add(CbxWeb);
              PnlSub.add(CbxCombo);
              PnlEdit.add(PnlSub);
             g.addLabel(PnlLabel," "+g.getBegriffS("Show","Bild"));
              BtnBild = new BildEingabe(this,g);
              BtnBild.activateEvent();
              PnlEdit.add(BtnBild);
             PnlLabel.add(new JLabel());
              PnlSub=new JPanel(new GridLayout());
              CbxJeder=g.getCheckbox("Jeder");
              CbxBerechtigt=g.getCheckbox("berechtigt");
              CbxTod=g.getCheckbox("Tod");
              PnlSub.add(CbxJeder);
              PnlSub.add(CbxBerechtigt);
              PnlSub.add(CbxTod);
              PnlEdit.add(PnlSub);
             PnlEingabe.add("Center",PnlEdit);
             PnlEingabe.add("West",PnlLabel);

             JTabbedPane PnlMemo=new JTabbedPane();
             PnlMemo.add(g.getBegriffS("Show","Tooltip"),Tooltip);
             PnlMemo.add(g.getBegriffS("Show","Memo"),Memo);

            JPanel PnlS=new JPanel(new FlowLayout(FlowLayout.RIGHT,3,2));
             ActionListener ALP=new ActionListener()
             {
               public void actionPerformed(ActionEvent ev)
               {
                 String s=((JButton)ev.getSource()).getActionCommand();
                 //g.progInfo("ALP:"+s);
                 /*if (s.equals("Bild"))
                   BtnBild.LadeBild();
                 else*/
                 //if(iBegriffEdit==0 && SQL.exists(g,"select aic_begriff from begriff WHERE aic_begriffgruppe="+iBG_Edit+" and Kennung='"+TxtKennung.getText()+"'"))
                 if (s.equals("ok"))
                   if (Message.KennungWarnung(g,TxtKennung.getText(),iBegriffEdit,iBG_Edit,getThis()))
                     return;
                   else
                     SaveBegriff();
                 DlgParameter.setVisible(false);
               }
             };
             //BtnBild.setActionCommand("Bild");
             //BtnBild.addActionListener(ALP);
             JButton BtnOk = g.getButton("Ok","ok",ALP);
             JButton BtnAbbruch = g.getButton("Abbruch","cancel",ALP);
             PnlS.add(BtnOk);
             PnlS.add(BtnAbbruch);
            DlgParameter.getContentPane().add("North",PnlEingabe);
            DlgParameter.getContentPane().add("Center",PnlMemo);
            DlgParameter.getContentPane().add("South",PnlS);
            DlgParameter.pack();
            Static.centerComponent(DlgParameter,this);
          }

          JCOutlinerNode OutPBNod=TabIndextoOut(iPnlTabIndex);
          iBegriffEdit=Sort.geti(OutPBNod.getUserData(),0);
          int iPos=g.TabBegriffe.getPos("Aic",iBegriffEdit);
          //g.progInfo(iPos+":"+g.TabBegriffe.getB(iPos,"Combo")+"/"+g.TabBegriffe.getB(iPos,"Tod"));
          iBG_Edit=g.TabBegriffe.getI(iPos,"Gruppe");
          if (bNeu) iBegriffEdit=0;
          String sBG=g.TabBegriffgruppen.getKennung(iBG_Edit);
          if (sBG.equals("Panel") || sBG.equals("Abfrage") || sBG.equals("Modell"))
            return;
          DlgParameter.setTitle(g.getBegriffS("Show",bNeu ? "new":"edit")+" "+(bNeu?sBG:g.getBegBez2(iPos)+" ("+sBG+")"));
          String sDefBez=bNeu ? "":g.TabBegriffe.getS(iPos,"DefBezeichnung");
          TxtDefBezeichnung.setText(sDefBez);
          String sBez=bNeu ? "":g.TabBegriffe.getS(iPos,"Bezeichnung");
          TxtBezeichnung.setText(sDefBez.equals(sBez) ? "":sBez);
          String sWWW=bNeu ? "":g.TabBegriffe.getS(iPos,"URL");
          TxtHomepage.setText(sWWW);
          Tooltip.setText(bNeu ? "":g.TabBegriffe.getS(iPos,"Tooltip"));
          TxtKennung.setText(bNeu ? "":g.TabBegriffe.getS(iPos,"Kennung"));
          TxtKennung.setEnabled(bNeu);
          TxtHotkey.setText(bNeu ? "":g.TabBegriffe.getS(iPos,"HK"));
          CboSchrift.setSelectedAIC(bNeu ? 0 :g.TabBegriffe.getI(iPos,"Schrift"));
          CbxCombo.setSelected(bNeu ? false:g.TabBegriffe.getB(iPos,"Combo"));
          CbxWeb.setSelected(bNeu ? false:g.TabBegriffe.getB(iPos,"Web"));
          CbxTod.setSelected(bNeu ? false:g.TabBegriffe.getB(iPos,"Tod"));
          CbxBerechtigt.setVisible(sBG.equals("Titel"));
          if (bNeu)
          {
            BtnBild.Delete();
            Memo.setText("");
            CbxJeder.setSelected(false);
            CbxBerechtigt.setSelected(false);
          }
          else
          {
            BtnBild.setValue(g.LoadImage(g.TabBegriffe.getS(iPos, "BildFile")), g.TabBegriffe.getS(iPos, "BildFile"));
            Tabellenspeicher Tab=new Tabellenspeicher(g,"select jeder,bits,(SELECT memo FROM Daten_memo AUI WHERE aic_tabellenname="+g.iTabBegriff+" AND AUI.AIC_Fremd=Begriff.AIC_BEGRIFF AND AUI.AIC_Sprache="+g.getSprache()+") Memo from begriff where aic_begriff="+iBegriffEdit,true);
            CbxJeder.setSelected(Tab.getB("Jeder"));
            CbxBerechtigt.setSelected(sBG.equals("Titel") && (Tab.getI("bits")&1)>0);
            Memo.setText(Tab.getM("Memo"));
          }
          //g.progInfo("Parameter:"+bNeu+"/"+g.getBegBez2(iPos)+"/"+sBG);
          DlgParameter.setVisible(true);
        }

        @SuppressWarnings("unchecked")
        private void SaveBegriff()
        {
          g.progInfo("SaveBegriff");
          String sBG=g.TabBegriffgruppen.getKennung(iBG_Edit);
          SQL Qry=new SQL(g);
          Qry.add("AIC_Begriffgruppe",iBG_Edit);
          Qry.add("DefBezeichnung",TxtDefBezeichnung.getText());
          Qry.add("HotKey",TxtHotkey.getText());
          Qry.add("Homepage",TxtHomepage.getText());
          Qry.add0("combo",CbxCombo.isSelected() ? 1:0);
          Qry.add0("web",CbxWeb.isSelected() ? 1:0);
          Qry.add0("Tod",CbxTod.isSelected() ? 1:0);
          Qry.add0("Jeder",CbxJeder.isSelected() ? 1:0);
          if (sBG.equals("Titel"))
        	  Qry.add("bits", CbxBerechtigt.isSelected());
          Qry.add0("aic_schrift",CboSchrift.getSelectedAIC());
          Qry.add("aic_logging", g.getLog());
          boolean bNeu=iBegriffEdit==0;
          if (bNeu)
          {
            Qry.add("Kennung",TxtKennung.getText());
            iBegriffEdit = Qry.insert("Begriff", true);
          }
          else
          {
            Qry.update("Begriff", iBegriffEdit);
          }
          if (BtnBild.Modified())
            g.setImage(BtnBild.getFilename(),g.iTabBegriff,iBegriffEdit,Global.iSpSw);
          //if (TxtBezeichnung.getText().equals(""))
          //  TxtBezeichnung.setText(TxtDefBezeichnung.getText());
          if (TxtBezeichnung.Modified())
            g.setBezeichnung(TxtBezeichnung.getOld(),TxtBezeichnung.getText(),g.iTabBegriff,iBegriffEdit,0);
          TxtBezeichnung.setText(TxtBezeichnung.getText());
          if (Tooltip.Modified())
            g.setTooltip(Tooltip.getValue(),g.iTabBegriff,iBegriffEdit,0);
          if (Memo.Modified())
            g.setMemo(Memo.getValue(),"",null,g.iTabBegriff,iBegriffEdit,0);
          g.setJeder(iBegriffEdit,CbxJeder.isSelected()?1:0);
          int iPos=g.putTabBegriffe(iBG_Edit,iBegriffEdit,TxtKennung.getText(),TxtBezeichnung.getText(),TxtDefBezeichnung.getText(),BtnBild.getFilename(),CboSchrift.getSelectedAIC(),
                           TxtHomepage.getText(),-1,-1,-1,-1,-1,0,CbxCombo.isSelected(),CbxWeb.isSelected(),0,TxtHotkey.getText(),CbxTod.isSelected(),Tooltip.getValue(),null,null,null,null,bNeu);
	  int iPnlTabIndex = PnlTab.getSelectedIndex();
          JCOutlinerNode OutPBNod=TabIndextoOut(iPnlTabIndex);
          //g.progInfo("OutPBNod="+OutPBNod);
          if (bNeu)
          {
            Vector<Object> VecVisible = new Vector<Object>();
            VecVisible.addElement(g.TabBegriffe.getS(iPos,"DefBezeichnung"));
            VecVisible.addElement(g.TabBegriffe.getInhalt("Kennung",iPos));
            VecVisible.addElement(g.getAnzahl(g.TabBegriffe.getI(iPos,"AIC")));
            VecVisible.addElement(g.getLog(g.TabBegriffe.getI(iPos,"AIC")));
            VecVisible.addElement(g.getJeder(g.TabBegriffe.getI(iPos,"AIC"),true));
            VecVisible.addElement(Static.JaNein2(CbxWeb.isSelected()));
            if (CbxBerechtigt.isVisible())
            	VecVisible.addElement(Static.JaNein2(CbxBerechtigt.isSelected()));
            JCOutlinerNode OutNod = new JCOutlinerNode(VecVisible);
            Vector<Object> VecInvisible = new Vector<Object>();
            VecInvisible.addElement(g.TabBegriffe.getS(iPos,"AIC"));
            VecInvisible.addElement(g.TabBegriffe.getInhalt("Kennung",iPos));
            OutNod.setUserData(VecInvisible);
            OutPBNod.getParent().addNode(OutNod);
            Static.makeVisible(OutPBNod.getOutliner(),OutNod);
          }
          else
          {
            Vector<Object> VecVisible = (Vector<Object>)OutPBNod.getLabel();
            //for (int i=0;i<5;i++)
            //  g.progInfo(i+":"+VecVisible.elementAt(i));
            VecVisible.setElementAt(g.TabBegriffe.getS(iPos,"DefBezeichnung"),0);
            VecVisible.setElementAt(g.TabBegriffe.getInhalt("Kennung",iPos),1);
            VecVisible.setElementAt(g.getAnzahl(g.TabBegriffe.getI(iPos,"AIC")),2);
            VecVisible.setElementAt(g.getLog(g.TabBegriffe.getI(iPos,"AIC")),3);
            VecVisible.setElementAt(g.getJeder(g.TabBegriffe.getI(iPos,"AIC"),true),4);
            if (VecVisible.size()>5)
            	VecVisible.setElementAt(Static.JaNein2(CbxWeb.isSelected()),5);
            if (CbxBerechtigt.isVisible())
            	VecVisible.setElementAt(Static.JaNein2(CbxBerechtigt.isSelected()),6);
            OutPBNod.getOutliner().folderChanged(OutPBNod);
          }
          g.SaveDefVerlauf(iBegriffEdit, bNeu ? "neu":"geändert");
        }

        private void Delete()
        {
          int iPnlTabIndex = PnlTab.getSelectedIndex();
          JCOutlinerNode OutPBNod=TabIndextoOut(iPnlTabIndex);
          int iBeg=Sort.geti(OutPBNod.getUserData(),0);
          int iPos=g.TabBegriffe.getPos("Aic",iBeg);
          String sBG=g.TabBegriffgruppen.getKennung(g.TabBegriffe.getI(iPos,"Gruppe"));
          if (sBG.equals("Panel") || sBG.equals("Abfrage") || sBG.equals("Modell"))
            return;
          g.progInfo("Delete:"+g.getBegBez2(iPos)+"/"+sBG);
          if (iBeg>0 && new Message(Message.YES_NO_OPTION,null,g).showDialog("Loeschen",new String[] {""+g.getBegBez2(iPos)}) == Message.YES_OPTION)
          {
            g.exec("delete from defVerlauf where aic_begriff="+iBeg);
            g.exec("delete from Verlauf where aic_begriff="+iBeg);
            boolean bDelete=g.exec("delete from begriff where aic_begriff="+iBeg)>-2;
            if (bDelete)
            {
              int iB=g.exec("delete from Bezeichnung where aic_tabellenname="+g.iTabBegriff+" and aic_fremd="+iBeg);
              int iM=g.exec("delete from Daten_Memo where aic_tabellenname="+g.iTabBegriff+" and aic_fremd="+iBeg);
              int iI=g.exec("delete from Daten_Bild where aic_tabellenname="+g.iTabBegriff+" and aic_fremd="+iBeg);
              int iT=g.exec("delete from tooltip where aic_tabellenname="+g.iTabBegriff+" and aic_fremd="+iBeg);
              g.progInfo(iB+" x Bezeichnung, "+iM+"x Memo, "+iT+"x Tooltip, "+iI+"x Bild gelöscht");
              Vector VecParChildren = OutPBNod.getParent().getChildren();
              iPos= VecParChildren.indexOf(OutPBNod);
              OutPBNod.getOutliner().selectNode(iPos>0 ? (JCOutlinerNode)VecParChildren.elementAt(iPos-1):OutPBNod.getParent(),null);
              OutPBNod.getParent().removeChild(OutPBNod);
              OutPBNod.getOutliner().folderChanged(OutPBNod.getOutliner().getRootNode());
            }
          }
        }

	private void EnableButtons()
	{
		if(!bShow)
		{
			int iPnlTabIndex = PnlTab.getSelectedIndex();
			JCOutlinerNode OutBuildNod = OutBuild.getSelectedNode();
			JCOutlinerNode OutPBNod=TabIndextoOut(iPnlTabIndex);

                        String sBuildKennung = (String)(((Vector)OutBuildNod.getUserData()).elementAt(1));
                        //String sPBKennung = iPnlTabIndex==0 ? (String)(((Vector)OutPBNod.getUserData()).elementAt(1)):null;
                        BtnEdit.setEnabled(!bEdit);
			BtnEntfernen.setEnabled(bEdit && OutBuildNod!=OutBuild.getRootNode());
                        BtnDel2.setEnabled(bEdit && OutBuildNod!=OutBuild.getRootNode()/* && !sBuildKennung.equals("BorderLayout")*/);
			BtnHinzufuegen.setEnabled(bEdit);// && allow_Adding(OutBuildNod,OutPBNod,iPnlTabIndex,true));
                        BtnHinzu2.setEnabled(bEdit && OutBuildNod!=OutBuild.getRootNode() && allow_Adding(OutBuildNod.getParent(),OutPBNod,iPnlTabIndex,false));// && sPBKennung != null && !sPBKennung.equals("BorderLayout"));
			BtnSeperator.setEnabled(bEdit && allow_Seperator(OutBuildNod));

			BtnRauf.setEnabled(bEdit && Static.allow_up_down(OutBuildNod,true));
			BtnRunter.setEnabled(bEdit && Static.allow_up_down(OutBuildNod,false));
			BtnErsetzen.setEnabled(bEdit && OutBuildNod!=OutBuild.getRootNode());//allow_Replace(OutBuildNod,OutPBNod,iPnlTabIndex));

			//int iX = EdtX.getText().equals("")?0:new Integer(EdtX.getText()).intValue();
			//int iY = EdtY.getText().equals("")?0:new Integer(EdtY.getText()).intValue();
			//BtnUebernehmen.setEnabled(true);//!(iX==0 && iY==0));
                        BtnSpeichern.setEnabled(bEdit && bSpeichern);

			//Vector VecVisible =new Vector((Vector)OutBuildNod.getLabel());

			//EdtKennung.setEnabled(OutBuildNod!=OutBuild.getRootNode());

			//boolean bEdtX = VecVisible.elementAt(3)!=null;
                        CboX.setEnabled(bEdit);
			EdtX.setEnabled(bEdit/*bEdtX*/);
                        //EdtX.setMinimum(-1);
			//EdtX.getDecrementArrow().setEnabled(true/*bEdtX*/);
			//EdtX.getIncrementArrow().setEnabled(true/*bEdtX*/);
			//boolean bEdtY = VecVisible.elementAt(4)!=null;
			EdtY.setEnabled(bEdit/*bEdtY*/);
			//EdtY.getDecrementArrow().setEnabled(true/*bEdtY*/);
			//EdtY.getIncrementArrow().setEnabled(true/*bEdtY*/);
			//boolean bEdtW = VecVisible.elementAt(5)!=null;
			EdtW.setEnabled(bEdit/*bEdtW*/);
			//EdtW.getDecrementArrow().setEnabled(true/*bEdtW*/);
			//EdtW.getIncrementArrow().setEnabled(true/*bEdtW*/);
			//boolean bEdtH = VecVisible.elementAt(6)!=null;
			EdtH.setEnabled(bEdit/*bEdtH*/);
			//EdtH.getDecrementArrow().setEnabled(true/*bEdtH*/);
			//EdtH.getIncrementArrow().setEnabled(true/*bEdtH*/);
		}

	}

	/*private boolean allow_Replace(JCOutlinerNode OutBuildNod, JCOutlinerNode OutPBNod, int iPnlTabIndex)
	{
		String sPBKennung = (iPnlTabIndex==9||iPnlTabIndex==7?OutPBNod.getLevel()>2:OutPBNod.getLevel()>0) ? (String)(((Vector)OutPBNod.getUserData()).elementAt(1)):null;
		String sBuildKennung = (String)(((Vector)OutBuildNod.getUserData()).elementAt(1));

		//g.progInfo(iPnlTabIndex+":"+sPBKennung+":"+sBuildKennung);
		return (iPnlTabIndex!=0 && OutBuildNod.getState()==BWTEnum.NOVALUE) || (iPnlTabIndex==5&&sBuildKennung!=null&&sBuildKennung.equals("Titel")) ||
                       (sPBKennung!=null && (sPBKennung.equals("JCTable")||sPBKennung.equals("JCOutliner")||sPBKennung.equals("EFGroup")) && (sBuildKennung.equals("JCTable")||sBuildKennung.equals("JCOutliner")||sBuildKennung.equals("EFGroup"))) ||
                       (sPBKennung!=null && (sPBKennung.equals("TabControl")||sPBKennung.equals("GroupBox")) && (sBuildKennung.equals("TabControl")||sBuildKennung.equals("GroupBox")));
	}*/

	private boolean allow_Adding(JCOutlinerNode OutBuildNod, JCOutlinerNode OutPBNod, int iPnlTabIndex,boolean bEnde)
	{
		String sPBKennung = (iPnlTabIndex==9||iPnlTabIndex==7||iPnlTabIndex==11?OutPBNod.getLevel()>2:OutPBNod.getLevel()>0) ? (String)(((Vector)OutPBNod.getUserData()).elementAt(1)):null;
		String sBuildKennung = (String)(((Vector)OutBuildNod.getUserData()).elementAt(1));

		boolean bAllow = !sBuildKennung.equals("BorderLayout") 		// Direkt auf BorderLayout
			&& OutBuildNod.getState() != BWTEnum.NOVALUE            // auf Endstück
			&& sPBKennung!=null && bEnde || iPnlTabIndex==0 && !bEnde;	// keine Elemente

		if(bAllow && (sPBKennung.equals("BorderLayout") || sPBKennung.equals("FlowLayout") || sPBKennung.equals("GridLayout") || sPBKennung.startsWith("ToolBar")))
		{
			bAllow = bAllow && !sBuildKennung.equals("JCOutliner")
			 && !sBuildKennung.equals("JCTable")
			 && !sBuildKennung.equals("EFGroup")
			 && !sBuildKennung.equals("Grafik")
			 && !sBuildKennung.equals("GroupBox")
                         && !sBuildKennung.equals("GroupBox2")
                         && !sBuildKennung.equals("EmptyBorder")
			 && !sBuildKennung.equals("TabControl");
		}
		else if(bAllow && (sPBKennung.equals("GroupBox") || sPBKennung.equals("GroupBox2") || sPBKennung.equals("EmptyBorder") || sPBKennung.equals("JCOutliner") || sPBKennung.equals("TabControl") ||
		        sPBKennung.equals("JCTable") || sPBKennung.equals("EFGroup")) || iPnlTabIndex==1|| iPnlTabIndex==17 || iPnlTabIndex==3 || iPnlTabIndex==4
				|| iPnlTabIndex==6  || iPnlTabIndex==7 || iPnlTabIndex==8 || iPnlTabIndex==10 || iPnlTabIndex==11 || iPnlTabIndex==12 || iPnlTabIndex==13 || iPnlTabIndex==14 || iPnlTabIndex==15|| iPnlTabIndex==16)
		{
			bAllow = bAllow /*&& (!sBuildKennung.equals("JCOutliner") || iPnlTabIndex==11 || iPnlTabIndex==7 || iPnlTabIndex==15 || iPnlTabIndex==1)
			 && (!sBuildKennung.equals("JCTable") || iPnlTabIndex==11 || iPnlTabIndex==7)*/
			 && !sBuildKennung.equals("EFGroup")
			 && !sBuildKennung.equals("Root")
			 && !sBuildKennung.equals("GroupBox")
                         && !sBuildKennung.equals("GroupBox2")
                         && !sBuildKennung.equals("EmptyBorder")
			 && !sBuildKennung.equals("TabControl")
			 && !sBuildKennung.equals("Grafik");
		}
		else if(iPnlTabIndex==2) // Gruppe
		{
			bAllow = bAllow && !sBuildKennung.equals("Root")
			 && !sBuildKennung.equals("Grafik")
			 && !sBuildKennung.equals("JCTable")
			 && !sBuildKennung.equals("JCOutliner")
			 && !sBuildKennung.equals("EFGroup");

		}
		else if(iPnlTabIndex==9) //Abfrage
		{
			bAllow = bAllow && (sBuildKennung.equals("JCTable") || sBuildKennung.equals("JCOutliner") || sBuildKennung.equals("EFGroup") || sBuildKennung.equals("Grafik"));
		}
		/*else if(iPnlTabIndex==11) //Modell
		{
			bAllow = bAllow && sBuildKennung.equals("JCOutliner");
		}*/
		else if(iPnlTabIndex==5) // Titel
		{
			 bAllow = bAllow && (sBuildKennung.equals("GroupBox")
			 || sBuildKennung.equals("TabControl"));
		}

		if(OutBuildNod==OutBuild.getRootNode() || (iPnlTabIndex==5 && sBuildKennung.equals("GroupBox")))
		{
			Vector Vec = OutBuildNod.getChildren();
			bAllow = bAllow && (Vec==null || Vec.size()==0);
		}

		bAllow = HGB() ? true:bAllow;// && !sPBKennung.equals("ScrollPane");

		return(bAllow);
	}

        private boolean HGB()
        {
          return (lBits&Formular.cstHGB)>0;
        }

	private boolean allow_Seperator(JCOutlinerNode OutBuildNod)
	{
		String sBuildKennung = (String)(((Vector)OutBuildNod.getUserData()).elementAt(1));
		//g.progInfo("Level="+OutBuildNod.getLevel());
		String sBuildParentKennung = OutBuildNod.getLevel()>0 && OutBuildNod.getParent()!=null?(String)(((Vector)OutBuildNod.getParent().getUserData()).elementAt(1)):"";

		boolean bAllow = !sBuildKennung.equals("BorderLayout");
		bAllow = bAllow && !sBuildKennung.equals("");
		bAllow = bAllow && !sBuildKennung.equals("GroupBox");
                bAllow = bAllow && !sBuildKennung.equals("GroupBox2");
                bAllow = bAllow && !sBuildKennung.equals("EmptyBorder");
		bAllow = bAllow && !sBuildKennung.equals("JCOutliner");
		bAllow = bAllow && !sBuildKennung.equals("TabControl");
		bAllow = bAllow && !sBuildKennung.equals("Root");
		bAllow = bAllow && (OutBuild.getSelectedNode().getState() == BWTEnum.NOVALUE ? !sBuildParentKennung.equals("BorderLayout"):true);

		return(bAllow);
	}
/*
	private boolean allow_up_down(JCOutlinerNode OutBuildNod,boolean bRauf)
	{
		boolean bAllow = OutBuildNod!=(OutBuild.getRootNode());
		Vector VecKnoten = bAllow ? OutBuildNod.getParent().getChildren():null;
		if(bRauf)
			bAllow = bAllow && (VecKnoten.indexOf(OutBuildNod)>0);
		else
			bAllow = bAllow && VecKnoten.indexOf(OutBuildNod)<VecKnoten.size()-1;

		return(bAllow);
	}
*/

        @SuppressWarnings("unchecked")
        private void BtnDel2_actionPerformed(ActionEvent ev)
	{
          JCOutlinerNode[] Node = OutBuild.getSelectedNodes();
          JCOutlinerNode OutBuildNod = OutBuild.getSelectedNode();
          OutBuild.selectNode(OutBuildNod.getParent(),null);
          Vector Vec = (Vector)OutBuildNod.getParent().getUserData();
          String sKennungParent = (String)Vec.elementAt(1);
          Vector VecC=OutBuildNod.getChildren();
          String sBuildKennung = (String)(((Vector)OutBuildNod.getUserData()).elementAt(1));
          if(sBuildKennung.equals("BorderLayout"))
            for (int i=VecC.size()-1;i>=0;i--)
              if (!Sort.gets(((JCOutlinerNode)VecC.elementAt(i)).getUserData(),2).equals("Center"))
                VecC.remove(VecC.elementAt(i));
          if(sKennungParent.equals("BorderLayout"))
          {
            Vector VecVisible = (Vector)OutBuildNod.getLabel();
            Vector VecInvisible = (Vector)OutBuildNod.getUserData();
            if (VecC==null || VecC.size()==0)
            {
              VecVisible.setElementAt(new String(""),0);
              VecVisible.setElementAt(new String(""),1);
              VecVisible.setElementAt(null,3);
              VecVisible.setElementAt(null,4);
              VecVisible.setElementAt(null,5);
              VecVisible.setElementAt(null,6);
              VecInvisible.setElementAt(null,0);
              VecInvisible.setElementAt(new String(""),1);
              Vector VecParentChildren = new Vector(OutBuildNod.getParent().getChildren());
              JCOutlinerFolderNode NodNeu = new JCOutlinerFolderNode((Object)VecVisible,OutBuildNod.getParent());
              NodNeu.setUserData(VecInvisible);
              VecParentChildren.setElementAt(NodNeu,VecParentChildren.indexOf(OutBuildNod));
              OutBuildNod.getParent().setChildren(new jclass.util.JCVector(VecParentChildren));
              OutBuild.selectNode(NodNeu,null);
            }
            else if (VecC.size()==1)
            {
              int iPos = OutBuildNod.getParent().getChildren().indexOf(OutBuildNod);
              OutBuildNod.getParent().removeChild(OutBuildNod);
              JCOutlinerNode Nod=(JCOutlinerNode)VecC.elementAt(0);
              ((Vector)Nod.getLabel()).setElementAt(VecVisible.elementAt(2),2);
              ((Vector)Nod.getUserData()).setElementAt(VecInvisible.elementAt(2),2);
              OutBuildNod.getParent().addNode(Nod,iPos);
            }
          }
          else
          {
            if (Node.length==1)
            {
              int iPos = OutBuildNod.getParent().getChildren().indexOf(OutBuildNod);
              OutBuildNod.getParent().removeChild(OutBuildNod);
              if (VecC != null && VecC.size() > 0)
                for (int i = VecC.size() - 1; i >= 0; i--)
                  OutBuildNod.getParent().addNode((JCOutlinerNode)VecC.elementAt(i), iPos);
            }
            else
            {
              JCOutlinerFolderNode NodeParent = Node[0].getParent();
              OutBuild.selectNode(OutBuild.getPreviousNode(Node[0]), null);
              for (int i = 0; i < Node.length; i++)
                NodeParent.removeChild(Node[i]);
            }
            CheckReihenfolge(OutBuildNod.getParent());
          }

          //OutBuild.repaint();
          Show_Frame();
          OutBuild.folderChanged(OutBuild.getRootNode());
          EnableButtons();

        }

        @SuppressWarnings("unchecked")
		private void CheckReihenfolge(JCOutlinerFolderNode Out)
        {
          Vector VecChildren = Out.getChildren();
          for(int i=0;i<VecChildren.size();i++)
          {
                  ((Vector)((JCOutlinerNode)VecChildren.elementAt(i)).getLabel()).setElementAt(new Integer(i+1),2);
                  ((Vector)((JCOutlinerNode)VecChildren.elementAt(i)).getUserData()).setElementAt(new Integer(i+1),2);
          }
        }

	@SuppressWarnings("unchecked")
	private void BtnEntfernen_actionPerformed(ActionEvent ev)
	{
		JCOutlinerNode OutBuildNod = OutBuild.getSelectedNode();
		int pane=Message.YES_OPTION;
		if(OutBuildNod.getState()!=BWTEnum.NOVALUE)
                  pane = new Message(Message.YES_NO_OPTION,null,g).showDialog("Loeschen",new String[] {""+((Vector)OutBuild.getSelectedNode().getLabel()).elementAt(0)});
			//pane = JOptionPane.showConfirmDialog(this,"Wollen Sie wirklich den gewählten Eintrag löschen?","Sicherheitsabfrage",JOptionPane.YES_NO_OPTION);
		if(pane == Message.YES_OPTION)
		{
			Vector Vec = (Vector)OutBuildNod.getParent().getUserData();
			String sKennungParent = (String)Vec.elementAt(1);

			OutBuild.selectNode(OutBuildNod.getParent(),null);
			if(sKennungParent.equals("BorderLayout"))
			{
				Vector VecVisible = (Vector)OutBuildNod.getLabel();
				Vector VecInvisible = (Vector)OutBuildNod.getUserData();
				VecVisible.setElementAt(new String(""),0);
				VecVisible.setElementAt(new String(""),1);
				VecVisible.setElementAt(null,3);
				VecVisible.setElementAt(null,4);
				VecVisible.setElementAt(null,5);
				VecVisible.setElementAt(null,6);

				VecInvisible.setElementAt(null,0);
				VecInvisible.setElementAt(new String(""),1);

				Vector VecParentChildren = new Vector(OutBuildNod.getParent().getChildren());
				JCOutlinerFolderNode NodNeu = new JCOutlinerFolderNode((Object)VecVisible,OutBuildNod.getParent());
				NodNeu.setUserData(VecInvisible);
				VecParentChildren.setElementAt(NodNeu,VecParentChildren.indexOf(OutBuildNod));
				//OutBuildNod.setLabel(VecVisible);
				//OutBuildNod.setLabel(VecInvisible);
				OutBuildNod.getParent().setChildren(new jclass.util.JCVector(VecParentChildren));
				//((JCOutlinerFolderNode)OutBuildNod).removeChildren();
				OutBuild.selectNode(NodNeu,null);
			}
			else
			{
                          OutBuild.selectNode(OutBuildNod.getParent(),null);
				//Vector VecNodes = OutBuildNod.getParent().getChildren();
				//int iPos = VecNodes.indexOf(OutBuildNod)+1;
				//JCOutlinerNode NodNext = iPos<VecNodes.size()? (JCOutlinerNode)VecNodes.elementAt(iPos):null;
				OutBuildNod.getParent().removeChild(OutBuildNod);
				//if(NodNext!=null)
                                  CheckReihenfolge(OutBuildNod.getParent());
					//BtnSeperator_actionPerformed(NodNext);
			}

			//OutBuild.repaint();
			Show_Frame();
			OutBuild.folderChanged(OutBuild.getRootNode());
			EnableButtons();
		}
	}

        @SuppressWarnings("unchecked")
		private void BtnHinzu2_actionPerformed(ActionEvent ev)
        {
          JCOutlinerNode OutBuildNod=OutBuild.getSelectedNode();
          JCOutlinerFolderNode OutP=OutBuildNod.getParent();
          int iPnlTabIndex = PnlTab.getSelectedIndex();
          JCOutlinerNode OutPBNod=TabIndextoOut(iPnlTabIndex);
          Vector VecVisible = new Vector();
          Vector VecInvisible = new Vector();
          make_Visible_Invisible(VecVisible,VecInvisible,OutPBNod,OutBuildNod);
          OutBuild.selectNode(OutP,null);
          int iPos = OutP.getChildren().indexOf(OutBuildNod);
          VecVisible.setElementAt(((Vector)OutBuildNod.getLabel()).elementAt(2),2);
          VecInvisible.setElementAt(((Vector)OutBuildNod.getUserData()).elementAt(2),2);
          OutP.removeChild(OutBuildNod);
          JCOutlinerFolderNode OutNewNod=new JCOutlinerFolderNode((Object)VecVisible);
          OutNewNod.setStyle(OutPBNod.getStyle());
          OutNewNod.setUserData(VecInvisible);
          OutP.addNode(OutNewNod,iPos);
          OutNewNod.addNode(OutBuildNod);
          if(((String)((Vector)OutPBNod.getUserData()).elementAt(1)).equals("BorderLayout"))
          {
            for(TabRegion.moveFirst();!TabRegion.eof();TabRegion.moveNext())
            {
                    Vector<String> VecInv = new Vector<String>();
                    Vector<Object> VecVi = new Vector<Object>();

                    VecVi.addElement("");
                    VecVi.addElement("");
                    VecVi.addElement(TabRegion.getInhalt("Bezeichnung"));
                    VecVi.addElement(null);
                    VecVi.addElement(null);
                    VecVi.addElement(null);
                    VecVi.addElement(null);

                    VecInv.addElement(null);
                    VecInv.addElement("");
                    String s=TabRegion.getS("Kennung");
                    VecInv.addElement(s);
                    if (s.equals("Center"))
                    {
                      ((Vector)OutBuildNod.getLabel()).setElementAt(TabRegion.getInhalt("Bezeichnung"),2);
                      ((Vector)OutBuildNod.getUserData()).setElementAt(s,2);
                    }
                    else
                      new JCOutlinerFolderNode((Object)VecVi,OutNewNod).setUserData(VecInv);
            }

          }
          else
            CheckReihenfolge(OutNewNod);
          OutBuild.folderChanged(OutBuild.getRootNode());
          OutBuild.selectNode(OutNewNod,null);
          EnableButtons();
        }

	@SuppressWarnings("unchecked")
	private void BtnHinzufuegen_actionPerformed(ActionEvent ev)
	{
		JCOutlinerNode OutBuildNod=OutBuild.getSelectedNode();
                int iPnlTabIndex = PnlTab.getSelectedIndex();
                JCOutlinerNode OutPBNod=TabIndextoOut(iPnlTabIndex);

		Vector VecVisible = new Vector();
		Vector VecInvisible = new Vector();

		make_Visible_Invisible(VecVisible,VecInvisible,OutPBNod,OutBuildNod);
                //g.fixtestInfo("Hinzufügen:"+iPnlTabIndex);
		if(iPnlTabIndex==0 || iPnlTabIndex==5 || iPnlTabIndex==13 || iPnlTabIndex==14) // Panel, Titel, Grafik und Planung (kein Endstück)
		{
			JCOutlinerFolderNode OutNewNod=null;
			Vector VecPBUserData = (Vector)OutPBNod.getUserData();

			if(((String)((Vector)OutBuildNod.getUserData()).elementAt(1)).equals(""))
			{
				OutNewNod=(JCOutlinerFolderNode)OutBuildNod;
				OutNewNod.setLabel(VecVisible);
			}
			else
			{
				OutNewNod=new JCOutlinerFolderNode((Object)VecVisible,(JCOutlinerFolderNode)OutBuildNod);
			}
			OutNewNod.setStyle(OutPBNod.getStyle());
			OutNewNod.setUserData(VecInvisible);

			if(((String)VecPBUserData.elementAt(1)).equals("BorderLayout"))
			{
				TabRegion.moveFirst();
				while(!TabRegion.eof())
				{
					Vector VecInv = new Vector();
					Vector VecVi = new Vector();

					VecVi.addElement("");
					VecVi.addElement("");
					VecVi.addElement(TabRegion.getInhalt("Bezeichnung"));
					VecVi.addElement(null);
					VecVi.addElement(null);
					VecVi.addElement(null);
					VecVi.addElement(null);

					VecInv.addElement(null);
					VecInv.addElement("");
					VecInv.addElement(TabRegion.getInhalt("Kennung"));

					new JCOutlinerFolderNode((Object)VecVi,OutNewNod).setUserData(VecInv);
					TabRegion.moveNext();
				}
			}
		}
		else // Endstück: Button, Applikation, Formular, Label, Gruppe, Freifläche, Checkbox, Bild
		{
			JCOutlinerNode OutNewNod=null;
                        boolean bCR=false;
			if(((Vector)OutBuildNod.getUserData()).elementAt(0) == null)
			{
				OutBuild.selectNode(OutBuildNod.getParent(),null);
				Vector VecChildren = new Vector(OutBuildNod.getParent().getChildren());
				OutNewNod=new JCOutlinerNode((Object)VecVisible,OutBuildNod.getParent());

				VecChildren.setElementAt(OutNewNod,VecChildren.indexOf(OutBuildNod));
				OutBuildNod.getParent().setChildren(new jclass.util.JCVector(VecChildren));
			}
			else if (allow_Adding(OutBuildNod,OutPBNod,iPnlTabIndex,true))
			{
				OutNewNod=new JCOutlinerNode((Object)VecVisible,(JCOutlinerFolderNode)OutBuildNod);
			}
                        else
                        {
                          bCR=true;
                          OutNewNod = new JCOutlinerNode(VecVisible,OutBuildNod.getParent());
                          Vector VecChildren = OutBuildNod.getParent().getChildren();
                          VecChildren.removeElement(OutNewNod);
                          VecChildren.insertElementAt(OutNewNod,VecChildren.indexOf(OutBuildNod));

                        }

			OutNewNod.setStyle(OutPBNod.getStyle());
			OutNewNod.setUserData(VecInvisible);
			//OutBuild.selectNode(OutNewNod,null);
                        if (bCR)
                          CheckReihenfolge(OutBuildNod.getParent());
		}


		OutBuild.repaint();

		Show_Frame();

		OutBuild.folderChanged(OutBuild.getRootNode());

		EnableButtons();
	}

	@SuppressWarnings("unchecked")
	private void BtnErsetzen_actionPerformed()
	{
		JCOutlinerNode OutBuildAktNod = OutBuild.getSelectedNode();
		JCOutlinerFolderNode OutBuildNod=OutBuildAktNod.getParent();
                int iPnlTabIndex = PnlTab.getSelectedIndex();
                JCOutlinerNode OutPBNod = TabIndextoOut(iPnlTabIndex);
                String sPos=Sort.gets(OutBuildAktNod.getUserData(),2);
                if (iPnlTabIndex==0 && Sort.gets(OutPBNod.getLabel(),1).startsWith("SplitPane") &&
                    (sPos.equals("North") || sPos.equals("South") || sPos.equals("West") || sPos.equals("East")))
                {
                  JCOutlinerFolderNode NodP=OutBuildAktNod.getParent();
                  jclass.util.JCVector VecChildren = NodP.getChildren();
                  JCOutlinerNode NodC=null;
                  //int iP=0;
                  for(int i2=0; VecChildren!=null && VecChildren.size()>i2;i2++)
                  {
                    JCOutlinerNode Nod=(JCOutlinerNode)VecChildren.elementAt(i2);
                    if (Sort.gets(Nod.getUserData(),2).equals("Center"))
                    {
                      NodC = Nod;
                      //iP=i2;
                    }
                  }
                  //OutBuild.selectNode(NodP);
                  Vector VecVisible = new Vector();
                  Vector VecInvisible = new Vector();
                  make_Visible_Invisible(VecVisible, VecInvisible, OutPBNod, NodP);
                  VecVisible.setElementAt(((Vector)NodC.getLabel()).elementAt(2), 2);
                  VecInvisible.setElementAt(((Vector)NodC.getUserData()).elementAt(2), 2);
                  NodP.removeChild(OutBuildAktNod);
                  NodP.removeChild(NodC);
                  JCOutlinerFolderNode NodNeu=new JCOutlinerFolderNode((Object)VecVisible,NodP);
                  NodNeu.setUserData(VecInvisible);
                  NodNeu.setStyle(OutPBNod.getStyle());
                  //OutBuild.folderChanged(NodP);
                  JCOutlinerNode Nod1=OutBuildAktNod;
                  JCOutlinerNode Nod2=NodC;
                  //if (sPos.equals("North") || sPos.equals("West"))
                  if (sPos.equals("South") || sPos.equals("East"))
                  {
                    Nod2=OutBuildAktNod;
                    Nod1=NodC;
                  }
                  ((Vector)Nod1.getLabel()).setElementAt(new Integer(1),2);
                  ((Vector)Nod1.getUserData()).setElementAt(new Integer(1),2);
                  ((Vector)Nod2.getLabel()).setElementAt(new Integer(2),2);
                  ((Vector)Nod2.getUserData()).setElementAt(new Integer(2),2);
                  NodNeu.addNode(Nod1);
                  NodNeu.addNode(Nod2);
                  OutBuild.selectNode(NodNeu);
                  OutBuild.folderChanged(NodP);
                  //g.fixInfo("Center="+NodC+", Parent="+NodP);
                  //
                }
                else
                {
                  Vector VecVisible = new Vector();
                  Vector VecInvisible = new Vector();
                  make_Visible_Invisible(VecVisible, VecInvisible, OutPBNod, OutBuildNod);
                  VecVisible.setElementAt(((Vector)OutBuildAktNod.getLabel()).elementAt(2), 2);
                  VecInvisible.setElementAt(((Vector)OutBuildAktNod.getUserData()).elementAt(2), 2);
                  Vector VecOld=(Vector)OutBuildAktNod.getLabel();
                  for (int i=3;i<9;i++)
                	  if (i>=VecOld.size())
                		  ;
                	  else if (i>VecVisible.size()-1)
                		  VecVisible.addElement(VecOld.elementAt(i));
                	  else
                		  VecVisible.setElementAt(VecOld.elementAt(i), i);
                  OutBuildAktNod.setLabel(VecVisible);
                  OutBuildAktNod.setUserData(VecInvisible);
                  OutBuildAktNod.setStyle(OutPBNod.getStyle());
                }
		Show_Frame();
		OutBuild.folderChanged(OutBuild.getRootNode());
		EnableButtons();
	}

	@SuppressWarnings("unchecked")
	private void BtnSeperator_actionPerformed(JCOutlinerNode OutPar)
	{
		boolean bNormal = OutPar==null;
		JCOutlinerNode OutBuildNod = bNormal ? OutBuild.getSelectedNode():OutPar;
		Vector VecVisible = new Vector();
		Vector VecInvisible = new Vector();

		VecVisible.addElement("--------");
		VecVisible.addElement("Seperator");
		VecInvisible.addElement(null);
		VecInvisible.addElement("Seperator");
		for(int i=0;i<5;i++)
			VecVisible.addElement(null);

		if(/*!bNormal || */OutBuildNod.getState() == BWTEnum.NOVALUE )
		{
			//not Folder
			//g.progInfo("BtnSeperator_actionPerformed");
			Vector VecChildren = new Vector(OutBuildNod.getParent().getChildren());
			int iPos = VecChildren == null ? 1:VecChildren.indexOf(OutBuildNod)+1;
			VecVisible.setElementAt(new Integer(iPos),2);
			VecInvisible.addElement(new Integer(iPos));
			JCOutlinerNode Nod = new JCOutlinerNode(VecVisible,OutBuildNod.getParent());
			Nod.setUserData(VecInvisible);
			if (VecChildren != null)
			{
				VecChildren.insertElementAt(Nod,iPos-1);
                                //OutBuild.folderChanged(OutBuildNod.getParent());
                                //CheckReihenfolge(OutBuildNod.getParent());
				for(;iPos<VecChildren.size()+1;iPos++)
				{
					//g.progInfo("VecChildren="+VecChildren+", iPos="+iPos);
					//g.progInfo("Vector="+((JCOutlinerNode)VecChildren.elementAt(iPos-1)).getLabel());
					((Vector)((JCOutlinerNode)VecChildren.elementAt(iPos-1)).getLabel()).setElementAt(new Integer(iPos),2);
					((Vector)((JCOutlinerNode)VecChildren.elementAt(iPos-1)).getUserData()).setElementAt(new Integer(iPos),2);
				}
				OutBuildNod.getParent().setChildren(new jclass.util.JCVector(VecChildren));
			}
		}
		else
		{
			//Folder

			int iPos = OutBuildNod.getChildren() == null ? 1 : OutBuildNod.getChildren().size()+1;
			VecVisible.setElementAt(new Integer(iPos),2);
			VecInvisible.addElement(new Integer(iPos));
			JCOutlinerNode Nod = new JCOutlinerNode(VecVisible,(JCOutlinerFolderNode)OutBuildNod);
			Nod.setUserData(VecInvisible);

		}

		if (bNormal)
		{
			OutBuild.folderChanged(OutBuild.getRootNode());
		  	Show_Frame();
		  	EnableButtons();
		}
	}

	@SuppressWarnings("unchecked")
	private void Verschieben(boolean bOben)
	{
		JCOutlinerNode NodSelected = OutBuild.getSelectedNode();

		Vector<JCOutlinerNode> VecNodes=NodSelected.getParent().getChildren();
		int iPos=VecNodes.indexOf(NodSelected);
		JCOutlinerNode NodAndere=VecNodes.elementAt(iPos+(bOben?-1:1));

		VecNodes.setElementAt(NodAndere,iPos);
		VecNodes.setElementAt(NodSelected,iPos+(bOben?-1:1));

		Vector<Object> VecSelectedLabel = (Vector)NodSelected.getLabel();
		Vector VecAndereLabel = (Vector)NodAndere.getLabel();
		Object oSelectedOldReihenfolge = VecSelectedLabel.elementAt(2);
		VecSelectedLabel.setElementAt(VecAndereLabel.elementAt(2),2);
		VecAndereLabel.setElementAt(oSelectedOldReihenfolge,2);

		VecSelectedLabel = (Vector)NodSelected.getUserData();
		VecAndereLabel = (Vector)NodAndere.getUserData();
		oSelectedOldReihenfolge = VecSelectedLabel.elementAt(2);
		VecSelectedLabel.setElementAt(VecAndereLabel.elementAt(2),2);
		VecAndereLabel.setElementAt(oSelectedOldReihenfolge,2);

		OutBuild.folderChanged(OutBuild.getRootNode());
		Show_Frame();
		EnableButtons();
	}

	private void make_Visible_Invisible(Vector<Object> VecVisible, Vector<Object> VecInvisible, JCOutlinerNode OutPBNod, JCOutlinerNode OutBuildNod)
	{
		Vector VecPBUserData = (Vector) OutPBNod.getUserData();
		Vector VecPBLabel = (Vector) OutPBNod.getLabel();
		String sOutBuildKennung = ((String)((Vector)OutPBNod.getUserData()).elementAt(1));

		if( sOutBuildKennung.equals("FixLayout"))
		{
			VecVisible.addElement(VecPBLabel.elementAt(0)); 	//Bezeichung
			VecVisible.addElement(VecPBUserData.elementAt(1));	//Kennung
			VecVisible.addElement(null);						//Position
			VecVisible.addElement(null);	//x
			VecVisible.addElement(null);	//y
			VecVisible.addElement(null);	//w
			VecVisible.addElement(null);	//h

			VecInvisible.addElement(VecPBUserData.elementAt(0));//AIC
			VecInvisible.addElement(VecPBUserData.elementAt(1));//Datentyp
			VecInvisible.addElement(null);						//Position
		}
		else if( sOutBuildKennung.equals("FlowLayout") )
		{
			VecVisible.addElement(VecPBLabel.elementAt(0)); 	//Bezeichung
			VecVisible.addElement(VecPBUserData.elementAt(1));	//Kennung
			VecVisible.addElement(null);						//Position
			VecVisible.addElement(new String(TabAlign.getBezeichnung("CENTER")));			//Orientierung
			VecVisible.addElement(null);			//leer
			VecVisible.addElement(new Integer(2));	//w
			VecVisible.addElement(new Integer(2));	//h

			VecInvisible.addElement(VecPBUserData.elementAt(0));//AIC
			VecInvisible.addElement(VecPBUserData.elementAt(1));//Datentyp
			VecInvisible.addElement(null);						//Position
			VecInvisible.addElement(null);						//Schriftart
			VecInvisible.addElement(new Integer(FlowLayout.CENTER));
		}
		else if(sOutBuildKennung.equals("GridLayout") || sOutBuildKennung.startsWith("ToolBar") || sOutBuildKennung.equals("ScrollPane") || sOutBuildKennung.equals("EmptyBorder") || sOutBuildKennung.startsWith("SplitPane"))
		{
			VecVisible.addElement(VecPBLabel.elementAt(0));		//Bezeichung
			VecVisible.addElement(VecPBUserData.elementAt(1));	//Kennung
			VecVisible.addElement(null);						//Position
			VecVisible.addElement(new Integer(sOutBuildKennung.startsWith("SplitPane") ? 5:sOutBuildKennung.startsWith("EmptyBorder")?0:1));	//x
			VecVisible.addElement(new Integer(0));	//y
			VecVisible.addElement(new Integer(2));	//w
			VecVisible.addElement(new Integer(2));	//h

			VecInvisible.addElement(VecPBUserData.elementAt(0));//AIC
			VecInvisible.addElement(VecPBUserData.elementAt(1));//Datentyp
			VecInvisible.addElement(null);						//Position
			VecInvisible.addElement(null);						//Schriftart
		}
		else if( sOutBuildKennung.equals("GroupBox") || sOutBuildKennung.equals("GroupBox2") || sOutBuildKennung.equals("EmptyBorder") || sOutBuildKennung.equals("JCOutliner") || sOutBuildKennung.equals("EFGroup") ||
				 sOutBuildKennung.equals("JCTable") || sOutBuildKennung.equals("TabControl") || PnlTab.getSelectedIndex()>0 )
		{
			if(PnlTab.getSelectedIndex()==9 || PnlTab.getSelectedIndex()==7)
			{
				//VecVisible.addElement(OutPBNod.getParent().getLabel()+"."+VecPBLabel.elementAt(0));
				int iPos=g.TabBegriffe.getPos("AIC",new Integer((String)VecPBUserData.elementAt(0)));
				char Char = g.TabBegriffe.getI(iPos,"Stt")==0 && g.TabBegriffe.getI(iPos,"Erf")==0?'A':g.TabBegriffe.getI(iPos,"Erf")==0?'S':'B';
				g.debugInfo(""+Char);
				if(Char=='A')
					VecVisible.addElement(VecPBLabel.elementAt(0));
				else if(Char=='S')
					VecVisible.addElement(g.getBezeichnungS("Stammtyp",g.TabBegriffe.getI(iPos,"Stt"))+"."+VecPBLabel.elementAt(0));
				else if(Char=='B')
					VecVisible.addElement(g.getBezeichnungS("Bewegungstyp",g.TabBegriffe.getI(iPos,"Erf"))+"."+VecPBLabel.elementAt(0));

			}
			else
				VecVisible.addElement(VecPBLabel.elementAt(0)); 	//Bezeichung
			VecVisible.addElement(VecPBUserData.elementAt(1));	//Kennung
			VecVisible.addElement(null);						//Position

			if( ((String)((Vector)OutBuildNod.getParent().getUserData()).elementAt(1)).equals("FixLayout") )
			{
				VecVisible.addElement(new Integer(0));	//x
				VecVisible.addElement(new Integer(0));	//y
				VecVisible.addElement(new Integer(60));	//w
				VecVisible.addElement(new Integer(60));	//h
			}
			else
			{
				if(PnlTab.getSelectedIndex()==4)
					VecVisible.addElement(new String(TabAlign.getBezeichnung("CENTER")));
				else
					VecVisible.addElement(PnlTab.getSelectedIndex()==12?new Integer(0):null);	//x
				VecVisible.addElement(null);	//y
				VecVisible.addElement(null);	//w
				VecVisible.addElement(null);	//h
			}

			VecInvisible.addElement(VecPBUserData.elementAt(0));//AIC
			if(PnlTab.getSelectedIndex()==0)
				VecInvisible.addElement(VecPBUserData.elementAt(1));//Datentyp
			else if(PnlTab.getSelectedIndex()==1)
				VecInvisible.addElement("Button");				//Datentyp
			else if(PnlTab.getSelectedIndex()==2)
				VecInvisible.addElement("Gruppe");				//Datentyp
			else if(PnlTab.getSelectedIndex()==3)
				VecInvisible.addElement("frei");				//Datentyp
			else if(PnlTab.getSelectedIndex()==4)
				VecInvisible.addElement("Label");				//Datentyp
			else if(PnlTab.getSelectedIndex()==5)
				VecInvisible.addElement("Titel");				//Datentyp
			else if(PnlTab.getSelectedIndex()==6)
				VecInvisible.addElement("Applikation");			//Datentyp
			else if(PnlTab.getSelectedIndex()==7)
				VecInvisible.addElement("Frame");				//Datentyp
			else if(PnlTab.getSelectedIndex()==8)
				VecInvisible.addElement("Checkbox");			//Datentyp
			else if(PnlTab.getSelectedIndex()==9)
				VecInvisible.addElement("Abfrage");				//Datentyp
			else if(PnlTab.getSelectedIndex()==10)
				VecInvisible.addElement("Bild");				//Datentyp
			else if(PnlTab.getSelectedIndex()==11)
				VecInvisible.addElement("Modell");				//Datentyp
			else if(PnlTab.getSelectedIndex()==12)
				VecInvisible.addElement("Radiobutton");			//Datentyp
			else if(PnlTab.getSelectedIndex()==13)
				VecInvisible.addElement("Grafik");				//Datentyp
			else if(PnlTab.getSelectedIndex()==14)
				VecInvisible.addElement("Planung");				//Datentyp
			else if(PnlTab.getSelectedIndex()==15)
				VecInvisible.addElement("Druck");				//Datentyp
                        else if(PnlTab.getSelectedIndex()==16)
				VecInvisible.addElement("Formularmenge");				//Datentyp
                        else if(PnlTab.getSelectedIndex()==17)
				VecInvisible.addElement("Web");				//Datentyp
			VecInvisible.addElement(null);						//Position
			VecInvisible.addElement(null);						//Schriftart

			if(PnlTab.getSelectedIndex()==4 || PnlTab.getSelectedIndex()==10 || PnlTab.getSelectedIndex()==1|| PnlTab.getSelectedIndex()==17 || PnlTab.getSelectedIndex()==7 || // Label, Bild, Button, Frame
                           PnlTab.getSelectedIndex()==6 || PnlTab.getSelectedIndex()==11 || PnlTab.getSelectedIndex()==15 || PnlTab.getSelectedIndex()==16) // Appl, Modell, Druck, Menge
				VecInvisible.addElement(new Integer(SwingConstants.CENTER));
			/*
			else if(PnlTab.getSelectedIndex()==1 || PnlTab.getSelectedIndex()==6 || PnlTab.getSelectedIndex()==8)//Button, Checkbox
				VecInvisible.addElement(g.getImage("Begriff",new Integer((String)VecPBUserData.elementAt(0)).intValue()));
			else if(PnlTab.getSelectedIndex()==7)
				VecInvisible.addElement(g.getImage("Begriff",new Integer((String)VecPBUserData.elementAt(0)).intValue()));*/

		}
		else if( sOutBuildKennung.equals("BorderLayout") )
		{
			VecVisible.addElement(VecPBLabel.elementAt(0)); 	//Bezeichung
			VecVisible.addElement(VecPBUserData.elementAt(1));	//Kennung
			VecVisible.addElement(null);						//Position
			VecVisible.addElement(null);			//x
			VecVisible.addElement(null);			//y
			VecVisible.addElement(new Integer(2));	//w
			VecVisible.addElement(new Integer(2));	//h

			VecInvisible.addElement(VecPBUserData.elementAt(0));//AIC
			VecInvisible.addElement(VecPBUserData.elementAt(1));//Datentyp
			VecInvisible.addElement(null);						//Position
			VecInvisible.addElement(null);
		}

		//////////////////
		//Positionierung//
		//////////////////
		if( ((Vector)OutBuildNod.getUserData()).elementAt(0) == null )
		{
			//g.progInfo("*");
			VecVisible.setElementAt(((Vector)OutBuildNod.getLabel()).elementAt(2),2);
			//g.progInfo("**");
			VecInvisible.setElementAt(((Vector)OutBuildNod.getUserData()).elementAt(2),2);
			//g.progInfo("***");
		}
		else
		{
			int i = 1;
			if(OutBuildNod.getChildren() != null)
				i = OutBuildNod.getChildren().size()+1;

			VecVisible.setElementAt(new Integer(i),2);
			VecInvisible.setElementAt(new Integer(i),2);
		}
	}

	private void Show_Frame(JCOutlinerFolderNode OutBuildNod)
	{
          iSF++;
          g.progInfo(iSF+"Show_Frame:"+OutBuildNod);
          TabGet=new Tabellenspeicher(g,new String[]{"Element","Node"});
          ML=new MouseListener()
          {
            public void mousePressed(MouseEvent ev) {}
            public void mouseClicked(MouseEvent ev)
            {
                //if(ev.getModifiers()==MouseEvent.BUTTON3_MASK)
                if (ev.getButton()==3 || ev.getButton()==1 && ev.getModifiersEx()==Global.iRM)
                {
                  g.testInfo(ev.getSource()+"/"+((Container)ev.getSource()).getParent());
                  TabGet.posInhalt("Element", ev.getSource());
                  Static.makeVisible(OutBuild, (JCOutlinerNode)TabGet.getInhalt("Node"));
                }
            }
            public void mouseEntered(MouseEvent ev) {}
            public void mouseExited(MouseEvent ev)  {}
            public void mouseReleased(MouseEvent ev){}
          };
		TabRadio=new Tabellenspeicher(g,new String[]{"ID","ButtonGroup"});

		if (CbxDarstellen.isSelected())
		{
                  long lClock = Static.get_ms();
			FrmShow.getContentPane().removeAll();
			Vector VecRoot = (Vector)OutBuildNod.getLabel();
			FrmShow.setTitle((String)VecRoot.elementAt(0));
                        int iX=((Integer)VecRoot.elementAt(5)).intValue();//+2;
                        int iY=((Integer)VecRoot.elementAt(6)).intValue();//+2;
			FrmShow.setSize(iX,iY);

			if(OutBuildNod.getChildren() != null && OutBuildNod.getChildren().size() > 0)
			{
				JCOutlinerNode OutNextNode = (JCOutlinerNode)OutBuildNod.getChildren().elementAt(0);
				FrmShow.getContentPane().add("Center",Show_Frame_Rekursion(OutNextNode));
			}
			//OutBuild.folderChanged(OutBuild.getRootNode());
                        //if (!FrmShow.isVisible())
                          FrmShow.setVisible(true);
                        //else
                        //  FrmShow.repaint(100);
                        //FrmShow.setSize(iX,iY);
                        //FrmShow.toBack();
                        //this.requestFocus();
                  g.clockInfo("Show_Frame",lClock);
		}
		else
			FrmShow.setVisible(false);
	}

        private int getI(Object Obj)
        {
          return Obj instanceof Integer ? ((Integer)Obj).intValue():0;
        }

	private Container Show_Frame_Rekursion(JCOutlinerNode OutNod)
	{
		Container Pnl=null;
		String sLayoutKennung = (String)((Vector)OutNod.getUserData()).elementAt(1);
                int iW = Tabellenspeicher.geti(((Vector)OutNod.getLabel()).elementAt(5));
                int iH = Tabellenspeicher.geti(((Vector)OutNod.getLabel()).elementAt(6));
		if(sLayoutKennung.equals("FlowLayout"))
		{
			int iX = ((Integer)((Vector)OutNod.getUserData()).elementAt(4)).intValue();
			//int iX = 0;
			//int iW = ((Integer)((Vector)OutNod.getLabel()).elementAt(5)).intValue();
			//int iH = ((Integer)((Vector)OutNod.getLabel()).elementAt(6)).intValue();
			Pnl=new JPanel(new FlowLayout(iX,iW,iH));
		}
                else if(sLayoutKennung.startsWith("ToolBar"))
		{
                  Pnl = new JToolBar(sLayoutKennung.equals("ToolBarH") ? JToolBar.HORIZONTAL:JToolBar.VERTICAL);
                  ((JToolBar)Pnl).setOpaque(false);
                  ((JToolBar)Pnl).setFloatable(false);
                  iReiheOld=0;
                }
		else if(sLayoutKennung.equals("GridLayout"))
		{
			int iX = ((Integer)((Vector)OutNod.getLabel()).elementAt(3)).intValue();
			int iY = ((Integer)((Vector)OutNod.getLabel()).elementAt(4)).intValue();
			//int iW = ((Integer)((Vector)OutNod.getLabel()).elementAt(5)).intValue();
			//int iH = ((Integer)((Vector)OutNod.getLabel()).elementAt(6)).intValue();
			Pnl=new JPanel(new GridLayout(iX,iY,iW,iH));
		}
                else if(sLayoutKennung.startsWith("SplitPane"))
                {
                  Pnl = new JSplitPane(sLayoutKennung.equals("SplitPaneH") ? JSplitPane.HORIZONTAL_SPLIT:JSplitPane.VERTICAL_SPLIT,true);
                  ((JSplitPane)Pnl).setOneTouchExpandable(true);
                  int iX = ((Integer)((Vector)OutNod.getLabel()).elementAt(3)).intValue();
                  ((JSplitPane)Pnl).setResizeWeight(iX>9 ? 0.99:iX/10.0);
                }
		else if(sLayoutKennung.equals("BorderLayout"))
		{
			//int iW = ((Integer)((Vector)OutNod.getLabel()).elementAt(5)).intValue();
			//int iH = ((Integer)((Vector)OutNod.getLabel()).elementAt(6)).intValue();
			Pnl=new JPanel(new BorderLayout(iW,iH));
		}
		else if(sLayoutKennung.equals("FixLayout"))
		{
			Pnl=new JPanel(null);
		}
		else if(sLayoutKennung.equals("GroupBox") || sLayoutKennung.equals("GroupBox2"))
		{
			Pnl=new GroupBox();
		}
                /*else if(sLayoutKennung.equals("ReservePane12"))
                {
                  Pnl=new JScrollPane();
                  int iX = ((Integer)((Vector)OutNod.getLabel()).elementAt(3)).intValue();
                  int iY = ((Integer)((Vector)OutNod.getLabel()).elementAt(4)).intValue();
                  ((JScrollPane)Pnl).setBorder(new EmptyBorder(new Insets(iX,iY,iW,iH)));
                }*/
		else if(sLayoutKennung.equals("TabControl"))
		{
			Pnl=new JTabbedPane();
		}
		else if(sLayoutKennung.equals("Titel"))
		{
			Pnl=new JPanel(new GridLayout());
		}
		else if(sLayoutKennung.equals("JCOutliner"))
		{
		  AUOutliner OutNew = new AUOutliner(new JCOutlinerFolderNode("JCOutliner"));
                        if (iW>0 || iH>0)
                          OutNew.setPreferredSize(new java.awt.Dimension(iW,iH));
			//OutNew.setRootVisible(false);
			Pnl = OutNew;
			TabGet.addInhalt("Element", OutNew.getOutliner());
                        TabGet.addInhalt("Node",OutNod);
                        OutNew.getOutliner().addMouseListener(ML);
		}
		else if(sLayoutKennung.equals("JCTable"))
		{
			JTable tab = new JTable(10,2);
			tab.setCellSelectionEnabled(false);
                        tab.setBackground(g.ColTable);
                        if (iW>0 || iH>0)
                          tab.setPreferredSize(new java.awt.Dimension(iW,iH));
			Pnl = tab;
		}
		else if(sLayoutKennung.equals("EFGroup"))
		{
			Pnl=new JPanel(new GridLayout());
                        if (iW>0 || iH>0)
                          ((JPanel)Pnl).setPreferredSize(new java.awt.Dimension(iW,iH));
		}
                else if(sLayoutKennung.equals("EmptyBorder"))
                {
                  Pnl = new JScrollPane();
                  int iX = ((Integer)((Vector)OutNod.getLabel()).elementAt(3)).intValue();
                  int iY = ((Integer)((Vector)OutNod.getLabel()).elementAt(4)).intValue();
                  ((JScrollPane)Pnl).setBorder(new EmptyBorder(new Insets(iH+iY,iW+iX,iH,iW)));
                }
                else if(sLayoutKennung.equals("ScrollPane"))
                  if (HGB())
                    Pnl=new JPanel(null);
                  else
                  {
                    Pnl = new JScrollPane();
                    ((JScrollPane)Pnl).setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
                  }

		if(OutNod.getChildren() != null)
			for(int i=0;i<OutNod.getChildren().size();i++)
			{
				JCOutlinerNode NodChild = (JCOutlinerNode)OutNod.getChildren().elementAt(i);
                                Vector VecI=(Vector)NodChild.getUserData();
				String sKennung2 = Sort.gets(VecI,1);//(String)((Vector)NodChild.getUserData()).elementAt(1);
                                String sBezeichnung = (String)((Vector)NodChild.getLabel()).elementAt(0);
                                iW = Tabellenspeicher.geti(((Vector)NodChild.getLabel()).elementAt(5));
                                iH = Tabellenspeicher.geti(((Vector)NodChild.getLabel()).elementAt(6));
				if(!sKennung2.equals("") && (VecI.size()<6 || VecI.elementAt(5)==null))
				{
					Component Pnl2;
					//if(sKennung2.equals("Button"))
                                        //  Pnl2 = g.getButton(new Integer((String)((Vector)NodChild.getUserData()).elementAt(0)).intValue(),getI(((Vector)NodChild.getUserData()).elementAt(4)));
                                        if(sKennung2.equals("Applikation") || sKennung2.equals("Formularmenge")
                                            || (sKennung2.equals("Button") || sKennung2.equals("Web") || sKennung2.equals("Frame") || sKennung2.equals("Modell") || sKennung2.equals("Druck")) && !sLayoutKennung.equals("JCOutliner"))
                                        {
                                          if (sLayoutKennung.startsWith("ToolBar"))
                                          {
                                            int iY=Sort.geti(NodChild.getLabel(),4);
                                            //g.progInfo("iY="+iY);
                                            if (iY>0)
                                            {
                                              ButtonGroup RadGroup;
                                              if(TabRadio.posInhalt("ID",iY))
                                                RadGroup = (ButtonGroup)TabRadio.getInhalt("ButtonGroup");
                                              else
                                              {
                                                RadGroup = new ButtonGroup();
                                                TabRadio.addInhalt("ID",iY);
                                                TabRadio.addInhalt("ButtonGroup",RadGroup);
                                              }
                                              Pnl2 = g.getTButton(g.TabBegriffe.getKennung(Sort.geti(NodChild.getUserData(), 0)),null);
                                              RadGroup.add((JToggleButton)Pnl2);
                                            }
                                            else
                                              Pnl2 = g.getButton2(g.TabBegriffe.getPos("Aic", Sort.geti(NodChild.getUserData(), 0)), null, null);
                                            //g.progInfo(g.TabBegriffe.getKennung(Sort.geti(NodChild.getUserData(), 0))+":"+Pnl2);
                                          }
                                          else
                                            Pnl2 = g.getButton(Sort.geti(NodChild.getUserData(),0)/*new Integer((String)((Vector)NodChild.getUserData()).elementAt(0)).intValue()*/,HGB()?SwingConstants.LEFT:getI(((Vector)NodChild.getUserData()).elementAt(4)));
                                          if (HGB())
                                          {
                                            //((JButton)Pnl2).setOpaque(false);
                                            ((JButton)Pnl2).setContentAreaFilled(false);
                                            ((JButton)Pnl2).setVerticalAlignment(SwingConstants.BOTTOM);
                                          }
                                          else if (iW>0 || iH>0)
                                            ((JComponent)Pnl2).setPreferredSize(new java.awt.Dimension(iW,iH));
                                          //g.progInfo(((JButton)Pnl2).getText()+":"+iW+"/"+iH);
                                        }
					else if(sKennung2.equals("Checkbox"))
						Pnl2 = g.getCheckbox((String)((Vector)NodChild.getLabel()).elementAt(1));
					else if(sKennung2.equals("Gruppe") || sKennung2.equals("Abfrage") || sKennung2.equals("Frame") || sKennung2.equals("Modell") || sKennung2.equals("Druck") || sKennung2.equals("Button") || sKennung2.equals("Web"))
					{
						Pnl2 = new JPanel(new GridLayout());
                                                if (iW>0 || iH>0 /*&& sKennung2.equals("Gruppe")*/)
                                                  ((JPanel)Pnl2).setPreferredSize(new java.awt.Dimension(iW,iH));
						if (sLayoutKennung.equals("JCOutliner"))
						{
						  JCOutlinerNode Nod=new JCOutlinerNode(sBezeichnung,(JCOutlinerFolderNode)((AUOutliner)Pnl).getRootNode());
						  Nod.setStyle(NodChild.getStyle());
						}
						else if(sLayoutKennung.equals("JCTable"))
						{
							((JTable)Pnl).setValueAt(sBezeichnung,0,0);
						}
						else
						{
                                                  if (CbxGenau.isSelected())
                                                  {
                                                    int iSpalten = Tabellenspeicher.geti(((Vector)NodChild.getLabel()).elementAt(3));
                                                    ((JPanel)Pnl2).setLayout(new GridLayout());
                                                    JPanel PnlCenter = new JPanel(new GridLayout(0,iSpalten>1? iSpalten:1,0,0));
                                                    PnlCenter.setBackground(Static.ColEF);
                                                    int iGruppe = Tabellenspeicher.geti(((Vector)NodChild.getUserData()).elementAt(0));
                                                    int iPos=g.TabBegriffe.getPos("Aic", iAIC_Begriff);
                                                    if (TabGruppe==null)
                                                      TabGruppe=new Tabellenspeicher(g,new String[]{"AIC","TAB"});
                                                    boolean bSaveRolle = false;
                                                    int iPosR=g.TabBegriffe.getI(iPos,"Rolle")>0 ? g.TabRollen.getPos("Aic", g.TabBegriffe.getI(iPos,"Rolle")):-1;
                                                    if (iPosR>=0)
                                                      bSaveRolle = g.TabRollen.getI(iPosR,"Stt") == g.TabBegriffe.getI(iPos,"Stt");
                                                    Tabellenspeicher Tab = null;
                                                    if (TabGruppe.posInhalt("AIC",iGruppe))
                                                      Tab=(Tabellenspeicher)TabGruppe.getInhalt("TAB");
                                                    else
                                                    {
                                                      //g.progInfo(iGruppe+":"+bSaveRolle+"/"+g.TabBegriffe.getI("Stt")+"/"+g.TabBegriffe.getI("Erf"));
                                                      String s=sKennung2.equals("Abfrage") ? "select e.aic_eigenschaft aic,g.reihenfolge reihe,g.bits from abfrage a join spalte g on a.aic_abfrage=g.aic_abfrage join fixeigenschaft e on g.aic_spalte=e.aic_spalte where a.aic_begriff=" + iGruppe :bSaveRolle ?
                                                        "select e.aic_eigenschaft aic,g.reihenfolge reihe,e.bits from Gruppe_zuordnung g join eigenschaft e on e.aic_eigenschaft=g.aic_eigenschaft join Rolle_zuordnung sz on sz.aic_eigenschaft=e.aic_eigenschaft where G.aic_begriff=" + iGruppe +
                                                        " and sz.aic_Rolle=" + g.TabBegriffe.getI(iPos,"Rolle") :g.TabBegriffe.getI(iPos,"Erf")==0 ?
                                                        "select e.aic_eigenschaft aic,g.reihenfolge reihe,e.bits from Gruppe_zuordnung g join eigenschaft e on e.aic_eigenschaft=g.aic_eigenschaft join stammtyp_zuordnung sz on sz.aic_eigenschaft=e.aic_eigenschaft where G.aic_begriff=" + iGruppe +
                                                        " and sz.aic_stammtyp=" + g.TabBegriffe.getI(iPos,"Stt") :
                                                        "select e.aic_eigenschaft aic,g.reihenfolge reihe,e.bits from Gruppe_zuordnung g join eigenschaft e on e.aic_eigenschaft=g.aic_eigenschaft join bew_zuordnung bz on bz.aic_eigenschaft=e.aic_eigenschaft where G.aic_begriff=" +
                                                        iGruppe + " and bz.aic_bewegungstyp=" + g.TabBegriffe.getI(iPos,"Erf");
                                                    //g.progInfo(s);
                                                    Tab = new Tabellenspeicher(g,s + " order by g.reihenfolge", true);
                                                    if (!sKennung2.equals("Abfrage"))
                                                      Tab.mul("bits",0x10000);
                                                    //Tab.showGrid(""+iGruppe);
                                                     TabGruppe.addInhalt("AIC",iGruppe);
                                                     TabGruppe.addInhalt("TAB",Tab);
                                                    }
                                                    boolean bMemo=false;
                                                    int iReihe=0;
                                                    for (Tab.moveFirst(); !Tab.eof(); Tab.moveNext())
                                                     if (g.existsEigenschaft(Tab.getI("aic")))
                                                     {
                                                      JPanel Pnl22 = new JPanel(new BorderLayout(0, 0));
                                                      if (!sKennung2.equals("Abfrage") && iSpalten>1)
                                                      {
                                                        for (int i2 = 0; i2 < (Tab.getI("Reihe") - iReihe - 1) % iSpalten; i2++)
                                                          PnlCenter.add(new JLabel());
                                                        iReihe = Tab.getI("Reihe");
                                                      }
                                                      int iPosE=g.TabEigenschaften.getPos("Aic", Tab.getI("aic"));
                                                      String sDatentyp=g.TabEigenschaften.getS(iPosE,"Datentyp");
                                                      String sBez=g.TabEigenschaften.getS(iPosE,"Bezeichnung");
                                                      JComponent Edt = sDatentyp.equals("Memo") || sDatentyp.equals("Text") ? new AUTextArea(g, 7):
                                                          sDatentyp.endsWith("Boolean") ? (JComponent)new AUCheckBox(sBez):new Text("", 20);
                                                      boolean bEdit=(Tab.getI("bits")&Abfrage.cstAnzeigen)==0 || (Tab.getI("bits")&Abfrage.cstEditierbar)>0;
                                                      boolean bZwingend=(Tab.getL("bits")&(Global.cstAlways*0x10000))>0;
                                                      EF_Eingabe EF = new EF_Eingabe(g, Edt, "",sBez, bEdit,bZwingend,g.TabEigenschaften.getS(iPosE,"Info"),sDatentyp);
                                                      //Pnl22.add(sDatentyp.equals("Memo") || sDatentyp.equals("Text") || sDatentyp.equals("Farbe") || sDatentyp.equals("Boolean") ? "Center" : sNorth, EF);
                                                      Pnl22.setBackground(Static.ColEF);
                                                      bMemo=Edt instanceof AUTextArea;
                                                      if (bMemo)
                                                        ((AUTextArea)Edt).setText("");
                                                      //Pnl22.add("North", EF);
                                                      if (sDatentyp.equals("Filler"))
                                                        Pnl22.add(new JLabel());
                                                      else
                                                        Pnl22.add(sDatentyp.equals("Memo") || sDatentyp.equals("Text") || sDatentyp.equals("Farbe") || sDatentyp.equals("Boolean") ? "Center" : "North", EF);
                                                      //((JPanel)PnlCenter).add(Pnl22);
                                                      boolean bGB=false;
                                                      if (bMemo)
                                                      {
                                                        bGB=Pnl instanceof GroupBox;
                                                        if (!bGB)
                                                          bGB=((Vector)OutNod.getParent().getUserData()).elementAt(1).equals("GroupBox");
                                                        if (!bGB)
                                                        {
                                                          String s1=""+((Vector)OutNod.getUserData()).elementAt(1);
                                                          String s2=""+((Vector)OutNod.getParent().getUserData()).elementAt(1);
                                                          bGB =s1.equals("EFGroup") && s2.equals("Titel");
                                                        }
                                                        //if (!bGB)
                                                        //  bGB=((Vector)OutNod.getParent().getParent().getLabel()).elementAt(1).equals("GroupBox");
                                                        //g.progInfo(sBez + ":" + OutNod.getUserData() + "/" + OutNod.getParent().getUserData() + "/" + OutNod.getParent().getParent().getUserData());
                                                      }
                                                      PnlCenter.add(bGB ? Edt:Pnl22);
                                                     }
                                                    //if (bMemo)
                                                      ((JPanel)Pnl2).add(PnlCenter);
                                                    /*else
                                                    {
                                                      JScrollPane PnlSP = new JScrollPane(PnlCenter);
                                                      PnlSP.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
                                                      ((JPanel)Pnl2).add(PnlSP);
                                                    }*/
                                                  }
                                                  else
                                                  {
                                                    Pnl2.setBackground(Color.yellow);
                                                    ((JPanel)Pnl2).add(new JLabel(sBezeichnung));
                                                  }
						}
					}
					else if(sKennung2.equals("frei"))
					{
						Pnl2 = new JPanel();
						Pnl2.setBackground(Color.cyan);
						((JPanel)Pnl2).add(new JLabel(sBezeichnung));
                                                if (iW>0 || iH>0)
                                                  ((JPanel)Pnl2).setPreferredSize(new java.awt.Dimension(iW,iH));
					}
					else if(sKennung2.equals("Grafik"))
					{
						Pnl2 = new JPanel();
						Pnl2.setBackground(Color.red);
                                                if (iW>0 || iH>0)
                                                  ((JPanel)Pnl2).setPreferredSize(new java.awt.Dimension(iW,iH));
						((JPanel)Pnl2).add(new JLabel(sBezeichnung));
					}
					else if(sKennung2.equals("Planung"))
					{
						Pnl2 = new JPanel();
						Pnl2.setBackground(g.ColPlanung);//Color.blue);
						((JPanel)Pnl2).add(new JLabel(sBezeichnung));
					}
					else if(sKennung2.equals("Radiobutton"))
					{
						ButtonGroup RadGroup;
						Integer iID=(Integer)((Vector)NodChild.getLabel()).elementAt(3);
						if(TabRadio.posInhalt("ID",iID))
							RadGroup = (ButtonGroup)TabRadio.getInhalt("ButtonGroup");
						else
						{
							RadGroup = new ButtonGroup();
							TabRadio.addInhalt("ID",iID);
							TabRadio.addInhalt("ButtonGroup",RadGroup);
						}
						//Pnl2 = new JPanel();
						JRadioButton Rad = new JRadioButton(sBezeichnung);
						RadGroup.add(Rad);
						//((JPanel)Pnl2).add(Rad);
                                                Pnl2=Rad;
					}
					else if(sKennung2.equals("Label"))
						Pnl2 = g.getLabel((String)((Vector)NodChild.getLabel()).elementAt(1),HGB()?SwingConstants.LEFT:getI(((Vector)NodChild.getUserData()).elementAt(4)));
					else if(sKennung2.equals("Bild"))
						Pnl2 = g.getBildLabel((String)((Vector)NodChild.getLabel()).elementAt(1),HGB()?SwingConstants.LEFT:getI(((Vector)NodChild.getUserData()).elementAt(4)));
					else if(sKennung2.equals("Seperator"))
						Pnl2 = new JLabel();
					else
					{
						Pnl2 = Show_Frame_Rekursion(NodChild);
					}
                                        TabGet.addInhalt("Element", Pnl2);
                                        TabGet.addInhalt("Node",NodChild);
                                        Pnl2.addMouseListener(ML);
					if (sLayoutKennung.equals("BorderLayout"))
					{
						Pnl.add((String)((Vector)NodChild.getUserData()).elementAt(2),Pnl2);
					}
					else if(sLayoutKennung.equals("TabControl"))
					{
						Pnl.add(sBezeichnung,Pnl2);
					}
					else if(sLayoutKennung.equals("GroupBox") || sLayoutKennung.equals("GroupBox2"))
					{
                                          ((GroupBox)Pnl).setText((sKennung2.equals("Titel") || sKennung2.equals("Gruppe")) && sLayoutKennung.equals("GroupBox") ? sBezeichnung:null);
                                          Pnl.add(Pnl2);
					}
                                        else if(sLayoutKennung.equals("EmptyBorder"))
                                          ((JScrollPane)Pnl).setViewportView(Pnl2);
                                        else if(sLayoutKennung.equals("ScrollPane"))
                                          if (HGB())
                                          {
                                            Pnl2.setBounds(Tabellenspeicher.geti(((Vector)NodChild.getLabel()).elementAt(3)),Tabellenspeicher.geti(((Vector)NodChild.getLabel()).elementAt(4)),iW,iH);
                                            Pnl.add(Pnl2);
                                          }
                                          else
                                            ((JScrollPane)Pnl).setViewportView(Pnl2);
					else if(!sLayoutKennung.equals("JCOutliner"))
					{
                                          if (sLayoutKennung.startsWith("SplitPane"))
                                            Pnl2.setMinimumSize(new java.awt.Dimension(100,50));
                                          else if (sLayoutKennung.startsWith("ToolBar"))
                                          {
                                            int iReihe=Sort.geti(NodChild.getUserData(),2);
                                            //g.progInfo("Reihe bei Toolbar:"+iReihe);
                                            if (iReihe>iReiheOld+1)
                                              ((JToolBar)Pnl).addSeparator();
                                            iReiheOld=iReihe;
                                          }
                                          Pnl.add(Pnl2);
					}

				}
			}

		return Pnl;
	}

	@SuppressWarnings("unchecked")
	private void Show_Parameter_Eingabe()
	{
		JCOutlinerNode OutBuildNod = OutBuild.getSelectedNode();
		Vector VecVisible =new Vector((Vector)OutBuildNod.getLabel());
		Vector VecInvisible = new Vector((Vector)OutBuildNod.getUserData());

		if(VecVisible.elementAt(4)!=null)
			EdtY.setValue( ((Integer)VecVisible.elementAt(4)));
		else
			EdtY.setValue(Static.Int0);

		if(VecVisible.elementAt(5)!=null)
			EdtW.setValue( ((Integer)VecVisible.elementAt(5)));
		else
			EdtW.setValue(Static.Int0);

		if(VecVisible.elementAt(6)!=null)
			EdtH.setValue( ((Integer)VecVisible.elementAt(6)));
		else
			EdtH.setValue(Static.Int0);

		String sDatentyp = (String)VecInvisible.elementAt(1);
		if(( sDatentyp.equals("FlowLayout") || VecA.contains(sDatentyp)) && !HGB())
		{
			PnlParaEdt1.removeAll();
			PnlParaEdt1.add(CboX);
			int iAlign = VecInvisible.elementAt(4) instanceof Integer ? ((Integer)VecInvisible.elementAt(4)).intValue():0;
			if(VecA.contains(sDatentyp))
			{
				if(iAlign==SwingConstants.CENTER)
					iAlign=FlowLayout.CENTER;
				else if(iAlign==SwingConstants.RIGHT)
					iAlign=FlowLayout.RIGHT;
				else if(iAlign==SwingConstants.LEFT)
					iAlign=FlowLayout.LEFT;
			}
                        //g.progInfo("CboX1="+CboX);
			CboX.setSelectedAIC(iAlign);
                        //g.progInfo("CboX2="+CboX);
			EdtX.setIntValue(iAlign);
		}
		else
		{
			PnlParaEdt1.removeAll();
			PnlParaEdt1.add(EdtX);
			if(VecVisible.elementAt(3)!=null)
				EdtX.setValue( ((Integer)VecVisible.elementAt(3)));
			else
				EdtX.setValue(Static.Int0);
		}
		repaint();
	}

	private void Fill_Tab_Outliners(int iNr,String sTab, AUOutliner Out ,boolean bEndStueck, boolean bNew)
	{
		//long lClock = Static.get_ms();
		Out.setBackground(Color.white);
                if (MLO==null)
                {
                  MLO=new MouseListener()
                  {
                    public void mousePressed(MouseEvent ev)
                    {}

                    public void mouseClicked(MouseEvent ev)
                    {
                      //if(ev.getModifiers()==MouseEvent.BUTTON3_MASK)
                      if (ev.getButton() == 3 || ev.getButton() == 1 && ev.getModifiersEx() == Global.iRM)
                        popupB.show((Component)ev.getSource(), ev.getX(), ev.getY());//popEig.show((Component)ev.getSource(), ev.getX(), ev.getY());
                      //else if (ev.getModifiers() == MouseEvent.BUTTON1_MASK && ev.getClickCount() == 2)
                      //   Parameter(false);
                    }

                    public void mouseEntered(MouseEvent ev)
                    {}

                    public void mouseExited(MouseEvent ev)
                    {}

                    public void mouseReleased(MouseEvent ev)
                    {}
                  };

                }
                if (Out.getRootVisible())
                {
                  Out.setRootVisible(false);
                  //if(!sTab.equals("Abfrage") && !sTab.equals("Frame") && !sTab.equals("Modell"))
                    Out.getOutliner().addMouseListener(MLO);
                }
		JCOutlinerFolderNode OutRootNod = (JCOutlinerFolderNode)Out.getRootNode();
		OutRootNod.removeChildren();
		Image ImgGruppe=null;

		String sPnl="";
		int iPosBG=g.TabBegriffgruppen.getPos("Kennung",sTab);
		int iAIC=g.TabBegriffgruppen.getI(iPosBG,"AIC");

		sPnl=g.TabBegriffgruppen.getS(iPosBG,"Bezeichnung");
		ImgGruppe = g.LoadImage(g.TabBegriffgruppen.getS(iPosBG,"Filename"));

		Image ImgErfassungstyp = g.getGif(g.TabTabellenname,"BEWEGUNGSTYP");//g.TabTabellenname.posInhalt("Kennung","BEWEGUNGSTYP") ? (Image)g.TabTabellenname.getInhalt("Bild"): null;
		Image ImgStammtyp = g.getGif(g.TabTabellenname,"STAMMTYP");//g.TabTabellenname.posInhalt("Kennung","STAMMTYP") ? (Image)g.TabTabellenname.getInhalt("Bild"): null;

		//String sSttKennung="";
		JCOutlinerFolderNode NodPar=null;
		Tabellenspeicher TabKnoten = new Tabellenspeicher(g,new String[]{"Art","Knoten"});
		boolean bTree=sTab.equals("Abfrage") || sTab.equals("Frame") || sTab.equals("Modell");
		for(int iPos=g.TabBegriffe.getPos("Gruppe",iAIC);iPos<g.TabBegriffe.size() && g.TabBegriffe.getI(iPos,"Gruppe")==iAIC;iPos++)
		{
			//if(!sTab.equals("Frame") || bStammtyp==(g.TabBegriffe.getI("STT")!=0))
			long lBBits=g.TabBegriffe.getL(iPos,"Bits");
            if(!g.TabBegriffe.getB(iPos,"Tod") /*&& (!sTab.equals("Frame") || (lBBits&Formular.cstJavaFX)==0)*/ && (!bWeb || g.TabBegriffe.getB(iPos,"Web")))
			{
            	Vector<Object> VecVisible = new Vector<Object>();
            	VecVisible.addElement(g.TabBegriffe.getS(iPos,"DefBezeichnung"));
            	VecVisible.addElement(g.TabBegriffe.getInhalt("Kennung",iPos));
                VecVisible.addElement(g.getAnzahl(g.TabBegriffe.getI(iPos,"AIC")));
                VecVisible.addElement(g.getLog(g.TabBegriffe.getI(iPos,"AIC")));
                VecVisible.addElement(g.getJeder(g.TabBegriffe.getI(iPos,"AIC"),true));
                if (bTree || sTab.equals("Titel"))
                	VecVisible.addElement(g.TabBegriffe.getB(iPos,"Web") ? "x":"");
                if (sTab.equals("Titel"))
                	VecVisible.addElement(Static.JaNein2((g.TabBegriffe.getI(iPos,"bits") & g.cstBer)>0));
                JCOutlinerNode OutNod ;
                if(bEndStueck)
                {
				  OutNod = new JCOutlinerNode(VecVisible);
				  if (ImgGruppe != null)
				  {
					JCOutlinerNodeStyle StyStd = new JCOutlinerNodeStyle();
					StyStd.setItemIcon(ImgGruppe);
					//StyStd.setFont(g.fontStandard);
					OutNod.setStyle(StyStd);
				  }
                }
                else
                {
				  OutNod = new JCOutlinerFolderNode(VecVisible);
				  Image ImgElement = sTab.equals("Panel") ? g.LoadImage(g.TabBegriffe.getS(iPos,"BildFile")) : ImgGruppe;
				  if (ImgElement != null)
				  {
					JCOutlinerNodeStyle StyFolder = new JCOutlinerNodeStyle();
					StyFolder.setFolderClosedIcon(ImgElement);
					StyFolder.setFolderOpenIcon(ImgElement);
					//StyFolder.setFont(g.fontStandard);
					OutNod.setStyle(StyFolder);
				  }
                }
			Vector<Object> VecInvisible = new Vector<Object>();
			VecInvisible.addElement(g.TabBegriffe.getS(iPos,"AIC"));
			VecInvisible.addElement(g.TabBegriffe.getInhalt("Kennung",iPos));
			OutNod.setUserData(VecInvisible);
			TabAll.addInhalt("Aic",g.TabBegriffe.getI(iPos,"AIC"));
			TabAll.addInhalt("Node",OutNod);
			TabAll.addInhalt("Tab",iNr);

			if(bTree)
			{
				char Char=' ';
				int iAICArt=0;

				if(g.TabBegriffe.getI(iPos,"Stt")==0 && g.TabBegriffe.getI(iPos,"Erf")==0)
				{
					Char='A';
					iAICArt=g.TabBegriffe.getI(iPos,"Typ");
				}
				else if(g.TabBegriffe.getI(iPos,"Erf")==0)
				{
					Char='S';
					iAICArt=g.TabBegriffe.getI(iPos,"Stt");
				}
				else
				{
					Char='B';
					iAICArt=g.TabBegriffe.getI(iPos,"Erf");
				}

				if(TabKnoten.posInhalt("Art",""+Char+iAICArt))
				{
					NodPar = (JCOutlinerFolderNode)TabKnoten.getInhalt("Knoten");
				}
				else
				{
					//g.TabBegriffe.push();
					JCOutlinerFolderNode NodArt = null;
					if(TabKnoten.posInhalt("Art",""+Char))
					{
						NodArt = (JCOutlinerFolderNode)TabKnoten.getInhalt("Knoten");
					}
					else
					{
						NodArt = new JCOutlinerFolderNode(Char=='A'?g.getBegriffS("Show","Allgemein"):Char=='S'?g.getBezeichnungS("Tabellenname","STAMMTYP"):g.getBezeichnungS("Tabellenname","BEWEGUNGSTYP"),OutRootNod);
						NodArt.setState(BWTEnum.FOLDER_CLOSED);
                                                if(Char =='S' && ImgStammtyp!=null)
							NodArt.setStyle(g.getStyle(ImgStammtyp));
						else if(Char =='B' && ImgErfassungstyp!=null)
							NodArt.setStyle(g.getStyle(ImgErfassungstyp));
						TabKnoten.addInhalt("Art",""+Char);
						TabKnoten.addInhalt("Knoten",NodArt);
					}

					NodPar = new JCOutlinerFolderNode(Char=='A'&&iAICArt==0?Static.sKein:Char=='A'?g.getBezeichnung("Code",iAICArt):Char=='S'?g.getBezeichnungS("Stammtyp",iAICArt):g.getBezeichnungS("Bewegungstyp",iAICArt),NodArt);
                                        NodPar.setState(BWTEnum.FOLDER_CLOSED);
                                        TabKnoten.addInhalt("Art",""+Char+iAICArt);
					TabKnoten.addInhalt("Knoten",NodPar);

                                        Image ImgElement = !Static.bDefBezeichnung ? null : Char == 'S' ? g.getSttGif(iAICArt) : Char == 'B' ? g.getBewGif(iAICArt) : null;
					//Image ImgElement = !Static.bDefBezeichnung ? null:Char=='S'?g.getSttGif(g.TabStammtypen.getI("Aic")):Char=='B'?g.getBewGif(g.TabErfassungstypen.getI("Aic")):null;
					if (ImgElement == null)
						ImgElement = Char=='S'?ImgStammtyp:Char=='B'?ImgErfassungstyp:null;

					if(ImgElement!=null)
					{/*
						JCOutlinerNodeStyle StyFolder = new JCOutlinerNodeStyle();
						StyFolder.setFolderClosedIcon(ImgElement);
						StyFolder.setFolderOpenIcon(ImgElement);*/
						//StyFolder.setFont(new Font("TimesRoman",0,14));
						NodPar.setStyle(g.getStyle(ImgElement));
					}

					//g.TabBegriffe.pop();
				}

				NodPar.addNode(OutNod);
			}
			else
				OutRootNod.addNode(OutNod);
			}
		}

		//
		Out.sortByColumn(0,Sort.sortMethod);
		Out.folderChanged(Out.getRootNode());


		if(bNew)
		{
			JPanel Pnl = new JPanel(new BorderLayout());
			Pnl.add("Center",Out);
			if (ImgGruppe != null)
				PnlTab.addTab("",new ImageIcon(ImgGruppe),Pnl);
			else
				PnlTab.addTab(sPnl,Pnl);
		}

		//g.printInfo(sTab+": "+(Static.get_ms()-lClock));
	}

	private void Fill_Combo_X()
	{
		int iAlign=0;
                //TabAlign.showGrid("Align");
		for(TabAlign.moveFirst();!TabAlign.eof();TabAlign.moveNext())
		{
			String sKennung=TabAlign.getS("Kennung");
			if(sKennung.equals("CENTER"))	iAlign=FlowLayout.CENTER;
			else if(sKennung.equals("RIGHT"))	iAlign=FlowLayout.RIGHT;
			else if(sKennung.equals("LEFT"))	iAlign=FlowLayout.LEFT;

			CboX.addItem(TabAlign.getS("Bezeichnung"),iAlign,sKennung);
		}
                //g.progInfo("Fill_Combo_X="+CboX);
	}

	@SuppressWarnings("unchecked")
	private void Uebernehmen()
	{
          if (!bRefresh)
            return;
          //g.progInfo("Uebernehmen");
          bSpeichern=true;

		JCOutlinerNode OutNod = OutBuild.getSelectedNode();
		Vector VecVisible = new Vector((Vector)OutNod.getLabel());
		Vector VecInvisible = new Vector((Vector)OutNod.getUserData());

		if(VecVisible.elementAt(4)!=null || EdtY.getIntValue()>0)
			VecVisible.setElementAt(EdtY.getValue(),4);
		//if(VecVisible.elementAt(5)!=null)
			VecVisible.setElementAt(EdtW.getValue(),5);
		//if(VecVisible.elementAt(6)!=null)
			VecVisible.setElementAt(EdtH.getValue(),6);


		String sDatentyp = (String)((Vector)OutNod.getUserData()).elementAt(1);
		if(( sDatentyp.equals("FlowLayout") || VecA.contains(sDatentyp)) && !HGB())
		{
			VecVisible.setElementAt(CboX.getSelectedItem(),3);
			int iAlign = CboX.getSelectedAIC();
			if(VecA.contains(sDatentyp))
			{
				if(iAlign==FlowLayout.CENTER)
					iAlign=SwingConstants.CENTER;
				else if(iAlign==FlowLayout.RIGHT)
					iAlign=SwingConstants.RIGHT;
				else if(iAlign==FlowLayout.LEFT)
					iAlign=SwingConstants.LEFT;
			}

			VecInvisible.setElementAt(new Integer(iAlign),4);
			OutNod.setUserData(VecInvisible);
		}
		else
		{
			if(VecVisible.elementAt(3)!=null || EdtX.getIntValue()>0)
				VecVisible.setElementAt(EdtX.getValue(),3);
		}

		/*if( sDatentyp.equals("Titel") || sDatentyp.equals("Label"))
		{
			if(CboSchrift.getSelectedAIC()!=0)
				VecInvisible.setElementAt(new Integer(CboSchrift.getSelectedAIC()),3);
			else
				VecInvisible.setElementAt(null,3);
		}*/

		OutNod.setLabel(VecVisible);

		OutBuild.repaint();
                Show_Frame();
                EnableButtons();
	}

        private void Show_Frame()
        {
          new Thread(new Runnable()
          {
            public void run()
            {
              try {Show_Frame((JCOutlinerFolderNode)OutBuild.getRootNode());} catch (Exception e) { e.printStackTrace(); }
            }}).start();
          /*Dimension Dim=FrmShow.getSize();
          Static.sleep(50);
          FrmShow.setSize(5,5);
          FrmShow.repaint();
          Static.sleep(50);
          FrmShow.setSize(Dim);
          FrmShow.repaint();*/
        }

        private void Beenden()
        {
          if (bEdit && bSpeichern)
           if(new Message(Message.YES_NO_OPTION,null,g).showDialog("Speichern") == Message.YES_OPTION)
             Save();
           //else
           //  iLast=0;
          if (bEdit)
            entsperren();
          g.setFenster(this,iBegriff,iFF);
          Para.setParameter("Option1","",(CbxDarstellen.isSelected()?1:0)+(CbxGenau.isSelected()?1:0),splitPane.getDividerLocation(),splitPaneM.getDividerLocation(),
                            OutBuild.getColumnWidth(0)*1024+OutBuild.getColumnWidth(1),true,false);
          setVisible(false);
          //FrmShow.setVisible(false);
        }

	private void Save()
	{
          long lClock=Static.get_ms();
          //g.progInfo("Save");
          bSpeichern=false;
          if (Static.cache())
            Static.clearCache();
          EnableButtons();
          g.start();
          try
          {
            g.exec("DELETE FROM Darstellung WHERE AIC_Formular=" + iAIC_Frame);
            iReihe=0;
            Save_Rekursion(OutBuild.getRootNode(), 0);
            g.exec("update Begriff set aic_logging=" + g.getLog() + " where aic_begriff=" + iAIC_Begriff);

            g.SaveDefVerlauf(iAIC_Begriff, "geändert");
            g.commit();
            int iPos=g.TabFormulare.getPos("Aic", iAIC_Begriff);
            if (iPos>=0)
            {
              Window win = ((Formular)g.TabFormulare.getInhalt("Formular",iPos)).thisFrame;
              if (!win.isShowing())
                win.dispose();

              g.TabFormulare.clearInhalt(iPos);
            }
          }
          catch(Exception e)
          {
            g.rollback();
            e.printStackTrace();
          }
          g.clockInfo("save Formular "+g.TabBegriffe.getBezeichnungS(iAIC_Begriff),lClock);
	}

	private void Save_Rekursion(JCOutlinerNode OutNod,int iAIC)
	{
		if(iAIC==0)
			Save_Rekursion(OutNod,Save_Element(OutNod,iAIC));
		else
		{
			Vector VecChildren = OutNod.getChildren();

			for(int i=0; VecChildren!=null && VecChildren.size()>i;i++)
			{
				JCOutlinerNode OutChild = (JCOutlinerNode)VecChildren.elementAt(i);
				if(((Vector)OutChild.getUserData()).elementAt(0)!=null)
					Save_Rekursion(OutChild,Save_Element(OutChild,iAIC));
			}
		}
	}

	@SuppressWarnings("unchecked")
	private int Save_Element(JCOutlinerNode OutNod,int iAIC)
	{
		SQL Qry = new SQL(g);
		Vector VecVisible = new Vector((Vector)OutNod.getLabel());
		Vector VecInvisible = new Vector((Vector)OutNod.getUserData());

		if(iAIC!=0)
		{
			Qry.add("AIC_Begriff",(String)VecInvisible.elementAt(0));
			Qry.add("Dar_AIC_Darstellung",iAIC);
			if(((String)((Vector)OutNod.getParent().getUserData()).elementAt(1)).equals("BorderLayout"))
			{
				TabRegion.posInhalt("Kennung",(String)VecInvisible.elementAt(2));
				Qry.add("Reihenfolge",Sort.geti(TabRegion.getInhalt("AIC_Begriff")));
			}
			else
				Qry.add("Reihenfolge",Static.bei(Sort.geti(VecInvisible,2),-1,iReihe++));
		}
		Qry.add("AIC_Formular",iAIC_Frame);

		String sDatentyp = (String)VecInvisible.elementAt(1);
		//if((sDatentyp.equals("Titel") || sDatentyp.equals("Label")) && VecInvisible.elementAt(3)!=null)
		//	Qry.add("AIC_Schrift",((Integer)VecInvisible.elementAt(3)).intValue());
		if((sDatentyp.equals("FlowLayout") || VecA.contains(sDatentyp)) && !HGB())
			Qry.add("x",getI(VecInvisible.elementAt(4)));
		else
			if(VecVisible.elementAt(3)!=null)
				Qry.add("x",((Integer)VecVisible.elementAt(3)).intValue());

		if(VecVisible.elementAt(4)!=null)
			Qry.add("y",((Integer)VecVisible.elementAt(4)).intValue());
		if(VecVisible.elementAt(5)!=null)
			Qry.add("w",((Integer)VecVisible.elementAt(5)).intValue());
		if(VecVisible.elementAt(6)!=null)
			Qry.add("h",((Integer)VecVisible.elementAt(6)).intValue());
                if (VecInvisible.size()>5 && VecInvisible.elementAt(5) != null)
                  Qry.add("hide",true);
		//Qry.add("Kennung",(String)VecVisible.elementAt(1));
                Vector Vec=OutNod.getChildren();
                int iret = Qry.insert("Darstellung",Vec != null && Vec.size()>0);
		Qry.free();

		return iret > 0 ? iret:-1;
	}

	@SuppressWarnings("unchecked")
	private void Laden()
	{
          //g.progInfo("Laden:"+iAIC_Begriff+"/"+iLast+"/"+iLastLog);
          //if (iLast==iAIC_Begriff && iLastLog==SQL.getInteger(g,"select aic_logging from begriff where aic_begriff="+iLast))
          //  return;
          //iLast=iAIC_Begriff;
          //iLastLog=SQL.getInteger(g,"select aic_logging from begriff where aic_begriff="+iLast);
          //g.progInfo("->"+iLast+"/"+iLastLog);
          ((JCOutlinerFolderNode)OutBuild.getRootNode()).removeChildren();
		Tabellenspeicher TabQry = new Tabellenspeicher(g,"SELECT * FROM Darstellung WHERE AIC_Formular="+iAIC_Frame+" ORDER BY "+g.order("Dar_AIC_Darstellung")+",Reihenfolge",true);
		if(!TabQry.eof() && TabQry.getInhalt("Dar_AIC_Darstellung")==null)
		{
			Vector<Object> VecVisible = (Vector)OutBuild.getRootNode().getLabel();
			VecVisible.setElementAt(TabQry.getInhalt("x"),3);
			VecVisible.setElementAt(TabQry.getInhalt("y"),4);
			VecVisible.setElementAt(TabQry.getInhalt("w"),5);
			VecVisible.setElementAt(TabQry.getInhalt("h"),6);

			Laden_Rekursion(TabQry.getI("AIC_Darstellung"),(JCOutlinerFolderNode)OutBuild.getRootNode(),TabQry);
			Show_Frame();

			OutBuild.folderChanged(OutBuild.getRootNode());
                        //OutBuild.repaint();
		}
	}

	private void Laden_Rekursion(int iAIC,JCOutlinerFolderNode OutNod,Tabellenspeicher TabQry)
	{
		TabQry.posInhalt("Dar_AIC_Darstellung",iAIC);
		while(!TabQry.eof() && TabQry.getI("Dar_AIC_Darstellung")==iAIC)
		{
                  int iPos=g.TabBegriffe.getPos("AIC",TabQry.getI("aic_begriff"));
			if(iPos<0)
                          Static.printError("Darstellung.Laden_Rekursion(): Fehler bei TabBegriffe!!!");
			int iPosBG=g.TabBegriffgruppen.getPos("AIC",g.TabBegriffe.getI(iPos,"Gruppe"));
			if (!g.TabBegriffgruppen.getS(iPosBG,"Kennung").equals("Panel") && !g.TabBegriffgruppen.getS(iPosBG,"Kennung").equals("Titel")
                            && !g.TabBegriffgruppen.getS(iPosBG,"Kennung").equals("Grafik")  && !g.TabBegriffgruppen.getS(iPosBG,"Kennung").equals("Planung"))
				Fuelle(OutNod,TabQry,iPosBG,iPos);
			else
			{
				TabQry.push();
				Laden_Rekursion(TabQry.getI("AIC_Darstellung"),(JCOutlinerFolderNode)Fuelle(OutNod,TabQry,iPosBG,iPos),TabQry);
				TabQry.pop();
			}
			TabQry.moveNext();
		}

		if(((String)((Vector)OutNod.getUserData()).elementAt(1)).equals("BorderLayout"))
		{
			Vector VecChildren = OutNod.getChildren();
			TabRegion.moveFirst();
			while(!TabRegion.eof())
			{
				boolean bVorhanden = false;
				for(int i=0; VecChildren!=null && VecChildren.size()>i;i++)
					bVorhanden = bVorhanden || TabRegion.getS("Kennung").equals((String)((Vector)((JCOutlinerNode)VecChildren.elementAt(i)).getUserData()).elementAt(2));

				if(!bVorhanden)
				{
					Vector<Object> VecInv = new Vector<Object>();
					Vector<Object> VecVi = new Vector<Object>();

					VecVi.addElement(new String(""));
					VecVi.addElement(new String(""));
					VecVi.addElement(TabRegion.getInhalt("Bezeichnung"));
					VecVi.addElement(null);
					VecVi.addElement(null);
					VecVi.addElement(null);
					VecVi.addElement(null);

					VecInv.addElement(null);
					VecInv.addElement(new String(""));
					VecInv.addElement(TabRegion.getInhalt("Kennung"));
					VecInv.addElement(null);

					JCOutlinerFolderNode NodNew=new JCOutlinerFolderNode((Object)VecVi,OutNod);
					NodNew.setUserData(VecInv);
				}
				TabRegion.moveNext();
			}
		}
	}

	public JCOutlinerNode Fuelle(JCOutlinerFolderNode OutNod, Tabellenspeicher TabQry,int iPosBG,int iPos)
	{
			//g.TabBegriffe.posInhalt("AIC",TabQry.getS("aic_begriff"));
			//g.TabBegriffgruppe.posInhalt("AIC",g.TabBegriffe.getS("Gruppe"));
		boolean bBorder = ((String)((Vector)OutNod.getUserData()).elementAt(1)).equals("BorderLayout");
		String sKennung = g.TabBegriffgruppen.getS(iPosBG,"Kennung");
		String sBegriff = g.TabBegriffe.getS(iPos,"Kennung");
		//boolean bPanel = .equals("Panel");
		//boolean bTitel = TabQry.getS("gruppe").equals("Titel");
		boolean bFlowLabel = sKennung.equals("Panel") && sBegriff.equals("FlowLayout") || VecA.contains(sKennung)  ;


		if(!bBorder)
		{
			int i=1;
			if(!TabQry.bof())
			{
				int iAIC = TabQry.getI("Dar_AIC_Darstellung");
				TabQry.movePrevious();
				if(TabQry.getI("Dar_AIC_Darstellung")==iAIC)
					i=TabQry.getI("Reihenfolge")+1;
				TabQry.moveNext();
			}
			for(;i<TabQry.getI("Reihenfolge");i++)
				  BtnSeperator_actionPerformed(OutNod);
		}


		Vector<Object> VecVisible = new Vector<Object>();
		Vector<Object> VecInvisible = new Vector<Object>();

		if(sKennung.equals("Abfrage") || sKennung.equals("Frame") || sKennung.equals("Druck"))
		{
			char Char = g.TabBegriffe.getI(iPos,"Stt")==0 && g.TabBegriffe.getI(iPos,"Erf")==0?'A':g.TabBegriffe.getI(iPos,"Erf")==0?'S':'B';
			if(Char=='A')
				VecVisible.addElement(g.TabBegriffe.getS(iPos,"DefBezeichnung"));
			else if(Char=='S')
				VecVisible.addElement(g.getBezeichnungS("Stammtyp",g.TabBegriffe.getI(iPos,"Stt"))+"."+g.TabBegriffe.getS(iPos,"DefBezeichnung"));
			else if(Char=='B')
				VecVisible.addElement(g.getBezeichnungS("Bewegungstyp",g.TabBegriffe.getI(iPos,"Erf"))+"."+g.TabBegriffe.getS(iPos,"DefBezeichnung"));
		}
		else
			VecVisible.addElement(g.TabBegriffe.getS(iPos,"DefBezeichnung"));
		VecVisible.addElement(sBegriff);
		VecVisible.addElement(new Integer(TabQry.getS("reihenfolge")));
		if(TabQry.getS("x").equals(""))
			VecVisible.addElement(null);
		else
		{
			if(bFlowLabel && !HGB())
			{
				int iAlign = TabQry.getI("x");
				if(VecA.contains(sKennung))
				{
					if(iAlign==SwingConstants.CENTER)
						iAlign=FlowLayout.CENTER;
					else if(iAlign==SwingConstants.RIGHT)
						iAlign=FlowLayout.RIGHT;
					else if(iAlign==SwingConstants.LEFT)
						iAlign=FlowLayout.LEFT;
				}
                                //g.progInfo("CboX3="+CboX);
				CboX.setSelectedAIC(iAlign);
                                //g.progInfo("CboX4="+CboX);
				VecVisible.addElement(CboX.getSelectedItem());
			}
			else
				VecVisible.addElement(TabQry.getInhalt("x"));
		}

		VecVisible.addElement(TabQry.getInhalt("y"));
		VecVisible.addElement(TabQry.getInhalt("w"));
		VecVisible.addElement(TabQry.getInhalt("h"));

		VecInvisible.addElement(TabQry.getS("AIC_Begriff")); // 0
		VecInvisible.addElement(sKennung.equals("Panel")? sBegriff : sKennung); // 1
                VecInvisible.addElement(TabQry.getInhalt("Reihenfolge")); // 2
		VecInvisible.addElement(null); // 3

		if(bFlowLabel)
			VecInvisible.addElement(TabQry.getInhalt("x")); // 4
			/*
		if(sKennung.equals("Bild") || sKennung.equals("Button") || sKennung.equals("Applikation") || sKennung.equals("Frame") || sKennung.equals("Checkbox"))
			VecInvisible.addElement(g.getImage("Begriff",TabQry.getI("AIC_Begriff")));*/

		if(bBorder)
		{
			TabRegion.posInhalt("AIC_Begriff",TabQry.getI("Reihenfolge"));
			VecVisible.setElementAt(TabRegion.getInhalt("Bezeichnung"),2);
			VecInvisible.setElementAt(TabRegion.getInhalt("Kennung"),2); // 4
		}

		JCOutlinerNode OutNewNod;

		if(!sKennung.equals("Panel") && !sKennung.equals("Titel") && !sKennung.equals("Grafik") && !sKennung.equals("Planung"))
		{
			OutNewNod = new JCOutlinerNode(VecVisible,OutNod);
			Image ImgGruppe=g.getImage("Begriffgruppe",sKennung);
			if (ImgGruppe != null)
			{
				JCOutlinerNodeStyle StyStd = new JCOutlinerNodeStyle();
				StyStd.setItemIcon(ImgGruppe);
//				StyStd.setFont(new Font("Arial",0,12));
				OutNewNod.setStyle(StyStd);
			}
		}
		else
		{
			OutNewNod = new JCOutlinerFolderNode((Object)VecVisible,OutNod);
			Image ImgGruppe=null;
			if(sKennung.equals("Panel"))
				ImgGruppe=g.getImage("Begriff",sBegriff);
			else
				ImgGruppe=g.getImage("Begriffgruppe",sKennung);
			if (ImgGruppe != null)
			{
				JCOutlinerNodeStyle StyFolder = new JCOutlinerNodeStyle();
				StyFolder.setFolderClosedIcon(ImgGruppe);
				StyFolder.setFolderOpenIcon(ImgGruppe);
//				StyFolder.setFont(new Font("TimesRoman",0,14));
				OutNewNod.setStyle(StyFolder);
			}
		}
                if (TabQry.getB("hide"))
                  Kom(OutNewNod,VecInvisible);

		OutNewNod.setUserData(VecInvisible);

		return (OutNewNod);
	}

	private void DefAbfrageAufrufen(AUOutliner Out,boolean b1)
	{
	  JCOutlinerNode Node = Out.getSelectedNodes()[0];
	  Vector VecInvisible = (Vector)Node.getUserData();
	  if (VecInvisible != null)
	  {
	    int iBegriff = Sort.geti(VecInvisible,0);
	    int iStt = SQL.getInteger(g, "select " + g.isnull() + "-aic_bewegungstyp,aic_stammtyp) from begriff where aic_begriff=" + iBegriff);
	    All_Unlimited.Grunddefinition.DefAbfrage.get(g, new All_Unlimited.Hauptmaske.ShowAbfrage(g, iBegriff, Abfrage.cstBegriff), iStt,null,false,b1 ? 0:1).show(false);
	  }
	}

	// add your data members here
	private Global g;
	private static Darstellung This1=null;
        private static Darstellung This2=null;
	private JTabbedPane PnlTab= new JTabbedPane();
	//private JCOutlinerFolderNode OutRootBuild= new JCOutlinerFolderNode("");
	private AUOutliner OutBuild= new AUOutliner(new JCOutlinerFolderNode(""));
	private AUOutliner OutPanel= new AUOutliner(new JCOutlinerFolderNode(""));
	private AUOutliner OutButton = new AUOutliner(new JCOutlinerFolderNode(""));
	private AUOutliner OutGruppe = new AUOutliner(new JCOutlinerFolderNode(""));
	private AUOutliner OutFrei = new AUOutliner(new JCOutlinerFolderNode(""));
	private AUOutliner OutLabel = new AUOutliner(new JCOutlinerFolderNode(""));
	private AUOutliner OutBild = new AUOutliner(new JCOutlinerFolderNode(""));
	private AUOutliner OutTitel = new AUOutliner(new JCOutlinerFolderNode(""));
	private AUOutliner OutApplikation = new AUOutliner(new JCOutlinerFolderNode(""));
	private AUOutliner OutFormular = new AUOutliner(new JCOutlinerFolderNode(""));
	private AUOutliner OutCheckbox = new AUOutliner(new JCOutlinerFolderNode(""));
	private AUOutliner OutAbfrage = new AUOutliner(new JCOutlinerFolderNode(""));
	private AUOutliner OutModell = new AUOutliner(new JCOutlinerFolderNode(""));
	private AUOutliner OutRadiobutton = new AUOutliner(new JCOutlinerFolderNode(""));
	private AUOutliner OutGrafik = new AUOutliner(new JCOutlinerFolderNode(""));
	private AUOutliner OutPlanung = new AUOutliner(new JCOutlinerFolderNode(""));
	private AUOutliner OutDruck = new AUOutliner(new JCOutlinerFolderNode(""));
        private AUOutliner OutMenge = new AUOutliner(new JCOutlinerFolderNode(""));
        private AUOutliner OutWeb = new AUOutliner(new JCOutlinerFolderNode(""));

	private JPanel PnlParaEdt1 = new JPanel(new GridLayout(0,1,2,2));
	private Tabellenspeicher TabRegion;
	private Tabellenspeicher TabAlign;
	private JPanel PnlAufbau = new JPanel(new BorderLayout());
	private JPanel PnlPfeil = new JPanel(new GridLayout(0,1));
	private JButton BtnEntfernen;
        private JButton BtnDel2;
	private JButton BtnHinzufuegen;
        private JButton BtnHinzu2;
	private JButton BtnSeperator;
	private JButton BtnRauf;
	private JButton BtnRunter;
	private JButton BtnErsetzen;
        private JButton BtnEdit;
        private JDialog FrmShow;

	//private JTextField EdtBezeichnung = new JTextField();
	//private JTextField EdtKennung = new JTextField();
	private AUCheckBox CbxDarstellen;
        private AUCheckBox CbxGenau;
	private SpinBox EdtX = new SpinBox();
	private SpinBox EdtY = new SpinBox();
	private SpinBox EdtW = new SpinBox();
	private SpinBox EdtH = new SpinBox();
	private ComboSort CboX;
	//private ComboSort CboSchrift;
        private JButton BtnRuecksetzen;
	private JButton BtnSpeichern;
        private boolean bSpeichern=false;
	//private JButton BtnBeenden;
	//private JButton BtnUebernehmen;
	//private JButton BtnRuecksetzen;
        //private JButton BtnShow;
	//private JButton BtnDefinition;
	//private JButton BtnAbfrage;
        //private JButton BtnRefresh;
	//private JButton BtnDrucken;

        private JDialog DlgParameter=null;
        private Text TxtDefBezeichnung;
        private Text TxtKennung;
        private Schrift CboSchrift;
        private Text TxtHotkey;
        private BildEingabe BtnBild;
        private Text TxtBezeichnung;
        private Text TxtHomepage;
        private AUTextArea Memo = new AUTextArea();
        private AUTextArea Tooltip = new AUTextArea();
        private AUCheckBox CbxJeder;
        private AUCheckBox CbxBerechtigt;
        private AUCheckBox CbxCombo;
        private AUCheckBox CbxWeb;
        private AUCheckBox CbxTod;
        private int iBG_Edit=0;
        private int iBegriffEdit=0;

        private JButton BtnNeu;
        private JButton BtnParameter;
        private JButton BtnDel;
        //private JButton BtnDruck;
	private int iAIC_Frame;

	//private boolean bStammtyp=false;
	private int iAIC_Begriff;

	//private Definition Def=null;

	private Tabellenspeicher TabRadio=null;
        private Tabellenspeicher TabGruppe=null;
	private boolean bShow=false;
	//private boolean bNeu=true;
        private int iBegriff=0;
        private long lBits=0;
        private int iSperre=0;
        //private int iLast=0;
        //private int iLastLog=0;
        private boolean bRefresh=false;
        private boolean bEdit=false;
        private AUVector VecA=new AUVector(new String[] {"Label","Bild","Button","Web","Modell","Applikation","Frame","Druck","Formularmenge"});
        private Tabellenspeicher TabGet;
        private Tabellenspeicher TabAll;
        private MouseListener ML;
        //private boolean bAnzahl=false;
        private int iSF=0;
        private AUTextArea TxtMemoElement;
        private Parameter Para;
        private JSplitPane splitPane;
        private JSplitPane splitPaneM;
        private int iReiheOld=0;
        private int iNr=0;
        private int iReihe;
        private JPopupMenu popupD= new JPopupMenu();
        private JPopupMenu popupB= new JPopupMenu();
        private MouseListener MLO=null;
        private int iFF=100;
        private boolean bWeb=false;
}

