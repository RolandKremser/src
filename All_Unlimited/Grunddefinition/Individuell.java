package All_Unlimited.Grunddefinition;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import All_Unlimited.Allgemein.Formular;
import All_Unlimited.Allgemein.Global;
import All_Unlimited.Allgemein.SQL;
import All_Unlimited.Allgemein.Sort;
import All_Unlimited.Allgemein.Static;
import All_Unlimited.Allgemein.Tabellenspeicher;
import All_Unlimited.Allgemein.Eingabe.AUOutliner;
//import All_Unlimited.Allgemein.Eingabe.ComboSort;
import All_Unlimited.Allgemein.Eingabe.Text;
import jclass.bwt.JCItemEvent;
import jclass.bwt.JCOutlinerEvent;
import jclass.bwt.JCOutlinerFolderNode;
import jclass.bwt.JCOutlinerListener;
import jclass.bwt.JCOutlinerNode;

public class Individuell extends Formular {
	
	private Global g;
	private static Individuell Self=null;
	private AUOutliner Out= new AUOutliner(new JCOutlinerFolderNode(""));
//    private ComboSort CboSprache2;
    private Text EdtSuche = null;
    private Text EdtFremd = null;
    private JLabel LblM=null;
    private JLabel LblS=null;
    private JButton BtnSave;
	
	public static Individuell get(Global g,String s)
	  {
	    if (Self==null)
	      new Individuell(g);
	    else
	      Self.show2();
	    if (!Static.Leer(s))
	    {
	    	Self.EdtSuche.setText(s);
	    	Self.suche(false);
	    }
	    return Self;
	  }

	  public static void free()
	  {
	    if (Self != null)
	    {
	    	Self.g.winInfo("Individuell.free");
	    	Self.thisFrame.dispose();     
	    	Self=null;
	    }
	  }

	  private Individuell(Global rg)
	  {
	    super("Individuell", null, rg);
	    Self=this;
	    g=rg;
	    g.winInfo("Individuell.create");
	    Build();
	    show2();
	  }
	  
	  private void addIf(String sFrei,Component C)
	  {
		  JPanel Pnl=getFrei(sFrei);
		  if (Pnl != null)
			  Pnl.add(C);
	  }
	  
	  private void Build()
	    {
	      Out.setRootVisible(false);
//	      if (g.Def())
	        Out.setColumnButtons(new String[] {"Gruppe","Kennung","DefBezeichnung","Bezeichnung","Individuell","seit","Web","Aic"});
//	      else
//	        Out.setColumnButtons(new String[] {"Tabelle","Gruppe","Kennung","DefBezeichnung","Bezeichnung","FremdSprache","Bild"});
	      Out.setNumColumns(g.Def() ? 18:7);
	      Out.setColumnLabelSortMethod(Sort.sortMethod);
	      Out.setAllowMultipleSelections(true);
	      getFrei("Outliner").add(Out);
	      Out.addItemListener(new JCOutlinerListener()
	      {
	        public void itemStateChanged(JCItemEvent e) {}
	        public void outlinerFolderStateChangeBegin(JCOutlinerEvent e) {}
	        public void outlinerFolderStateChangeEnd(JCOutlinerEvent e) {}
	        public void outlinerNodeSelectBegin(JCOutlinerEvent e) {}

	        public void outlinerNodeSelectEnd(JCOutlinerEvent e)
	        {
	          JCOutlinerNode Nod=Out.getSelectedNode();
	          if (Nod != null && Nod.getLabel() instanceof Vector)
	          {
	            Vector Vec = (Vector)Nod.getLabel();
	            EdtFremd.setText(Sort.gets(Vec, 4));
	            EdtFremd.setEnabled(true);
	            BtnSave.setEnabled(true);
	          }
	        }
	      });
	      EdtSuche = new Text("",255);
	      getFrei("TxtStdSprache").add(EdtSuche);
	      EdtFremd = new Text("",255);
	      EdtFremd.setEnabled(false);
	      getFrei("TxtFremdSprache").add(EdtFremd);
	      LblM=new JLabel(g.getMandant(0,null));
	      addIf("Mandant",LblM);
	      LblS=new JLabel(g.getBezeichnung("Sprache",g.getSprache()));
	      addIf("Sprache",LblS);
//	      CboSprache2 = new ComboSort(g);
//	      CboSprache2.fillDefTable("SPRACHE",false);
//	      getFrei("Combo Sprache2").add(CboSprache2);
	      ActionListener AL=new ActionListener()
	      {
	          public void actionPerformed(ActionEvent ev)
	          {
	            String s = ev.getActionCommand();
	            if (s.equals("suche"))
	            	suche(false);
	            else if (s.equals("Save"))
	            	Save();
	            else if (s.equals("Alles"))
	            	suche(true);
	            else if (s.equals("Beenden"))
	            	thisJFrame().setVisible(false);
	            else
	            	Static.printError("Individuell: "+s+" wird nicht unterstützt");
	          }
	      };
	      EdtSuche.setActionCommand("suche");
	      EdtSuche.addActionListener(AL);
	      g.BtnAdd(getButton("Suche"),"suche",AL);
	      g.BtnAdd(getButton("alleI"),"Alles",AL);
	      BtnSave=getButton("Speichern");
	      BtnSave.setEnabled(false);
	      g.BtnAdd(BtnSave,"Save",AL);
	      g.BtnAdd(getButton("Beenden"),"Beenden",AL);	
	    }
	  
	  private void show2()
	    {
	      super.show();
	      EdtSuche.requestFocus();
	    }
	  
	  private void suche(boolean bAll)
	  {
//		  g.fixtestError("suche "+(bAll ? "alles":""));
		  String sSuche=g.S(EdtSuche.getText(),255);
		  Tabellenspeicher TabI=new Tabellenspeicher(g,"select aic_fremd aic,BEZEICHNUNG,aic_protokoll from INDIVIDUELL where aic_tabellenname="+Global.iTabBegriff+" and aic_sprache="+g.getSprache()+" and aic_mandant="+g.getMandant(),true);
		  Tabellenspeicher TabP=new Tabellenspeicher(g,"select aic_protokoll,timestamp from protokoll where aic_protokoll"+g.SQL_in(TabI.getVecSpalteI("Aic_protokoll")),true);
		  String sWhere=bAll ? "aic_begriff"+(TabI.size()==0 ? "=0":g.SQL_in(TabI.getVecSpalteI("Aic"))):"defbezeichnung like "+sSuche+" or kennung like "+sSuche;
		  Tabellenspeicher Tab=new Tabellenspeicher(g,"select aic_begriff,kennung,defbezeichnung"+g.AU_Bezeichnung("Begriff")+" from begriff where "+sWhere,true);
//		  Tab.showGrid("Ergebnis");		  
		  BtnSave.setEnabled(false);
		  EdtFremd.setEnabled(false);
		  JCOutlinerFolderNode NodP=(JCOutlinerFolderNode)Out.getRootNode();
	      NodP.removeChildren();
	      for (Tab.moveFirst();!Tab.out();Tab.moveNext())
	      {
	        Vector<Comparable> VecVisible = new Vector<Comparable>();
	        int iAic=Tab.getI("aic_begriff");
	        int iPos=g.TabBegriffe.getPos("Aic", iAic);
	        int iPosI=TabI.getPos("Aic",iAic);
	        int iPosP=iPosI<0 ? -1:TabP.getPos("aic_protokoll",TabI.getI(iPosI,"aic_protokoll"));
	        VecVisible.addElement(iPos<0 ? "??":g.TabBegriffgruppen.getBezeichnungS(g.TabBegriffe.getI(iPos,"Gruppe")));
	        VecVisible.addElement(Tab.getS("Kennung"));
	        VecVisible.addElement(Tab.getS("DefBezeichnung"));
	        VecVisible.addElement(Tab.getS("Bezeichnung"));
//	        VecVisible.addElement(iPos<0 ? "??":g.TabBegriffe.getS(iPos,"Bezeichnung"));
	        VecVisible.addElement(iPosI<0 ? "":TabI.getS(iPosI,"Bezeichnung")); // Fremdsprache
	        VecVisible.addElement(iPosP<0 ? "":TabP.getDate(iPosP, "timestamp"));
	        VecVisible.addElement(Static.JaNein2(iPos>=0 && g.TabBegriffe.getB(iPos,"Web")));
	        VecVisible.addElement(iAic);
	        JCOutlinerNode Nod=new JCOutlinerNode(VecVisible,NodP);
//	        if (Tab.getB("Tod"))
//	        	Nod.setStyle(StyTod);
	        Nod.setUserData(iAic);
	      }
	      Out.folderChanged(NodP);
	  }
	  
	  private void Save()
	  {
//		  g.fixtestError("save");
		  JCOutlinerNode Nod=Out.getSelectedNode();
		  int iAic=Nod!=null ? (int)Nod.getUserData():0;
		  int iSp=g.getSprache();//CboSprache2.getSelectedAIC();
		  g.exec("delete from individuell where aic_mandant="+g.getMandant()+" and aic_tabellenname="+Global.iTabBegriff+" and aic_sprache="+iSp+" and aic_fremd="+iAic);
		  SQL Qry=new SQL(g);
		  Qry.add("AIC_MANDANT",g.getMandant());
		  Qry.add("aic_tabellenname",Global.iTabBegriff);
		  Qry.add("aic_fremd",iAic);
		  Qry.add("AIC_SPRACHE",iSp);
		  Qry.add("AIC_Protokoll",g.Protokoll("Eingabe",getBegriff()));
		  Qry.add("Bezeichnung",EdtFremd.getText());	
		  Qry.insert("individuell", false);
		  suche(false);
		  g.TabIndi=null;
	  }

}
