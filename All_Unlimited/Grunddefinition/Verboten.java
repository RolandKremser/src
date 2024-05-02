package All_Unlimited.Grunddefinition;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import jclass.bwt.JCOutlinerFolderNode;
import jclass.bwt.JCOutlinerNode;
import jclass.bwt.JCOutlinerNodeStyle;
import All_Unlimited.Allgemein.Formular;
import All_Unlimited.Allgemein.Global;
import All_Unlimited.Allgemein.Message;
import All_Unlimited.Allgemein.SQL;
import All_Unlimited.Allgemein.Sort;
import All_Unlimited.Allgemein.Static;
import All_Unlimited.Allgemein.Tabellenspeicher;
import All_Unlimited.Allgemein.Version;
import All_Unlimited.Allgemein.Eingabe.AUOutliner;
import All_Unlimited.Allgemein.Eingabe.SpinBox;
import All_Unlimited.Hauptmaske.ShowAbfrage;

public class Verboten extends Formular 
{
	private Global g;
	private static Verboten Self=null;
	private int iMandant=0;
	private JLabel LblMandant;
	private AUOutliner Out;
	private AUOutliner OutZ;
	private Tabellenspeicher Tab;
	private Tabellenspeicher TabV;
	//private Vector<Integer> VecV;
	private Vector<Integer> VecP; // hinzu
	private Vector<Integer> VecM; // weg
	private JRadioButton RadEig;
	private JRadioButton RadBew;
	private JRadioButton RadAustritt;
	private JRadioButton RadAnlage;
	private SpinBox SpnAnzahl=new SpinBox();
	private JButton BtnSave;
	private JButton BtnSetFrist;
	
	public static final int FRIST=1;
	
	public static Verboten get(Global rg)
	  {
	    return Self==null ? new Verboten(rg):Self;
	  }

	  public static void free()
	  {
	    if (Self!=null)
	    {
	      Self.g.winInfo("Verboten.free");
	      Self.thisFrame.dispose();
	      Self=null;
	    }
	  }

	  public void show()
	  {
		    Refresh();
	        LblMandant.setHorizontalAlignment(LblMandant.CENTER);
	        super.show();
	  }
	  
	  private Verboten(Global rg)
	  {
		  super("Verboten",null,rg);
		    g=rg;
		    Self=this;
		    g.winInfo("Verboten.create");
		    LblMandant=new JLabel(g.getMandant(0, "Kennung"));
		    JPanel Pnl_Mandant = getFrei("Mandant");
		    if (Pnl_Mandant != null)
		    	Pnl_Mandant.add(LblMandant);
		    Out=new AUOutliner();
		    ShowAbfrage.initOutliner(g, Out);
		    Out.setAllowMultipleSelections(true);
		    OutZ=new AUOutliner();
		    ShowAbfrage.initOutliner(g, OutZ);
		    OutZ.setAllowMultipleSelections(true);
//		    Out.setRootNode(new JCOutlinerFolderNode(""));
//		    Out.setColumnLabelSortMethod(Sort.sortMethod);
//		    Out.setRootVisible(false);
//		    Out.setFont(g.fontBezeichnung);
		    String[] sAry=new String[] {"Bezeichnung","Kennung","Anzahl","Frist_Austritt","Frist_Anlage"};
		    String[] sAry2=new String[] {"Bezeichnung","Kennung"};
		    for (int i=0;i<sAry.length;i++)
		      sAry[i]=g.getBegriffS("Show", sAry[i]);
		    Out.setColumnButtons(new jclass.util.JCVector(sAry));
		    Out.setNumColumns(sAry.length);
		    OutZ.setColumnButtons(new jclass.util.JCVector(sAry2));
		    OutZ.setNumColumns(sAry.length);
		    if (!g.Def())
		    {
		    	Out.setColumnDisplayWidth(1, 0);
		    	OutZ.setColumnDisplayWidth(1, 0);
		    }
		    JPanel Pnl_Outliner = getFrei("Outliner");
		    if (Pnl_Outliner != null)
		      Pnl_Outliner.add(Out);
		    JPanel Pnl_OutlinerZ = getFrei("Outliner Verwendung");
		    if (Pnl_OutlinerZ != null)
		    	Pnl_OutlinerZ.add(OutZ);
		    RadEig=getRadiobutton("Eigenschaft");
		    RadBew=getRadiobutton("Bewegungstyp");
		    RadEig.setSelected(true);
		    RadAustritt=getRadiobutton("Frist_Austritt");
		    RadAnlage=getRadiobutton("Frist_Anlage");
		    if (RadAustritt != null)
		    	RadAustritt.setSelected(true);
		    JPanel PnlFrist = getFrei("Frist");
		    if (PnlFrist != null)
		    	PnlFrist.add(SpnAnzahl);
		    ActionListener AL = new ActionListener()
	        {
	          @SuppressWarnings("unchecked")
	          public void actionPerformed(ActionEvent e)
	          {
	            String s = e.getActionCommand();
	            g.progInfo("Action="+s);
	            if (s.equals("Refresh"))
	              Refresh();
	            else if (s.equals("hinzu"))
	            	hinzu();
	            else if (s.equals("weg"))
	            	weg();
	            else if (s.equals("Save"))
	            	Save();
	            else if (s.equals("setFrist"))
	            	setFrist();
	            else if (s.equals("End"))
	            	hide();
	          }
	        };
	        g.setAction(RadEig,"Refresh",AL);
	        g.setAction(RadBew,"Refresh",AL);
	        g.BtnAdd(getButton(">"),"hinzu",AL);
	        g.BtnAdd(getButton("<"),"weg",AL);
		    g.BtnAdd(getButton("Refresh"),"Refresh",AL);
		    BtnSave=g.BtnAdd(getButton("Speichern2"),"Save",AL);
		    BtnSetFrist=g.BtnAdd(getButton("set_Frist"),"setFrist",AL);
		    g.BtnAdd(getButton("Beenden"),"End",AL);
	  }
	  
	  private void Refresh()
	  {
		  iMandant=g.getMandant();
		  LblMandant.setText(g.getMandant(iMandant, "Bezeichnung"));	        
		  String sTab=RadEig.isSelected() ? "Eigenschaft":RadBew.isSelected() ? "Bewegungstyp":"???";
		  VecP=new Vector<Integer>();
		  VecM=new Vector<Integer>();
		  TabV=new Tabellenspeicher(g,"select * from verboten where aic_tabellenname="+g.TabTabellenname.getAic(sTab.toUpperCase())+" and aic_mandant="+iMandant, true);
		  //VecV=SQL.getVector("select aic_fremd from verboten where vbits&1=0 && aic_tabellenname="+g.TabTabellenname.getAic(sTab.toUpperCase())+" and aic_mandant="+iMandant, g);
		  //g.fixtestError("VecV="+VecV);
		  boolean bEig=sTab.equals("Eigenschaft");
		  String sCount=bEig ? "stammpool where aic_eigenschaft=eigenschaft.aic_eigenschaft and aic_stamm in (select aic_stamm from stammview2 where aic_stammtyp="+g.iSttANR+" and aic_mandant="+iMandant+"))":
			  "bew_pool where pro_aic_protokoll is null and aic_bewegungstyp=bewegungstyp.aic_bewegungstyp and aic_mandant="+iMandant+")";
		  String s="select aic_"+sTab+" aic,kennung"+g.AU_Bezeichnung(sTab)+",(select count(*) from "+sCount+" Anzahl from "+sTab+" where "+g.bit(bEig ? "Bits":"BewBits", bEig?Global.cstEigBrtg:Global.cstBewBrtg)+" order by Bezeichnung";
		  Tab=new Tabellenspeicher(g,s,true);
		  fill();
	  }
	  
	  private void EnableButtons()
	  {
		  boolean b=VecP.size()>0 || VecM.size()>0;
		  BtnSave.setEnabled(b);
		  RadEig.setEnabled(!b);
		  RadBew.setEnabled(!b);
	  }
	  
	  private int getFrist(int iAic,boolean bAustritt)
	  {
		  int iPos=TabV.getPos("aic_fremd", iAic);
		  if (iPos<0)
			  return 0;
		  else
			  return TabV.getI(iPos,bAustritt ? "Frist_Austritt":"Frist_Anlage");
	  }
	  
	  private boolean isVerboten(int iAic)
	  {
		  int iPos=TabV.getPos("aic_fremd", iAic);
		  if (iPos<0)
			  return false;
		  else
			  return (TabV.getI(iPos,"VBits")&FRIST)==0;
	  }
	  
	  private void fill()
	  {
		  EnableButtons();		  
		  JCOutlinerNodeStyle StyFolder = new JCOutlinerNodeStyle();
		  StyFolder.setFont(g.fontStandard);
		  JCOutlinerFolderNode NodeRoot = (JCOutlinerFolderNode)Out.getRootNode();
		  JCOutlinerFolderNode NodeRootZ = (JCOutlinerFolderNode)OutZ.getRootNode();		    
		  NodeRoot.removeChildren();
		  NodeRootZ.removeChildren();
		  boolean bEig=RadEig.isSelected();
		  for(Tab.moveFirst();!Tab.eof();Tab.moveNext())
		  {
	    	int iAic=Tab.getI("AIC");
	    	//g.fixtestInfo("fill:"+bEig+", Eig="+iAic+", Lizenz="+g.existsEigenschaft(iAic));
	    	JCOutlinerNode Nod=null;
	    	if (isVerboten(iAic) && !VecM.contains(iAic) || VecP.contains(iAic))
	    		Nod = new JCOutlinerNode(new jclass.util.JCVector(new Object[] {Tab.getInhalt("Bezeichnung"),Tab.getS("Kennung")}), NodeRootZ);
	    	else if (bEig && g.existsEigenschaft(iAic) || !bEig && g.existsBew(iAic))
	    		Nod = new JCOutlinerNode(new jclass.util.JCVector(new Object[] {Tab.getInhalt("Bezeichnung"),Tab.getS("Kennung"),Tab.getS("Anzahl"),getFrist(iAic,true),getFrist(iAic,false)}), NodeRoot);
	    	if (Nod!=null)
	    	{
	    	  Nod.setStyle(StyFolder);
	    	  Nod.setUserData(iAic);
	    	}
		  }
		  Out.folderChanged(NodeRoot);
		  OutZ.folderChanged(NodeRootZ);
	  }
	  
	  private void hinzu()
	  {
		  JCOutlinerNode[] Node = Out.getSelectedNodes();
		  for (int i=0;i<Node.length;i++)
		  {
			  int iAic=Sort.geti(Node[i].getUserData());
			  if (!VecP.contains(iAic))
				  VecP.addElement(iAic);
		  }
		  fill();
	  }
	  
	  private void weg()
	  {
		  JCOutlinerNode[] Node = OutZ.getSelectedNodes();
		  for (int i=0;i<Node.length;i++)
		  {
			  int iAic=Sort.geti(Node[i].getUserData());
			  if (VecP.contains(iAic))
				  VecP.removeElement(iAic);
			  else if (!VecM.contains(iAic))
				  VecM.addElement(iAic);
		  }
		  fill();
	  }
	  
	  private void setFrist()
	  {
		  boolean bAustritt=RadAustritt.isSelected();
		  g.fixtestError("Frist laut "+(bAustritt ? "Austritt":"Anlage")+": "+SpnAnzahl.getValue());
		  JCOutlinerNode[] Node = Out.getSelectedNodes();
		  SQL Qry=new SQL(g);
		  int iProt=g.Protokoll("Frist", getBegriff());
		  int iTab=g.TabTabellenname.getAic(RadEig.isSelected()?"EIGENSCHAFT":"BEWEGUNGSTYP");
		  for (int i=0;i<Node.length;i++)
		  {
			  int iAic=Sort.geti(Node[i].getUserData());
			  Vector<Object> Vec=(Vector<Object>)Node[i].getLabel();
			  //TODO zurückschreiben
			  Vec.setElementAt(SpnAnzahl.getValue(), bAustritt ? 3:4);
			  //int iAic=SQL.getInteger(g, "select aic_verboten from verboten ", i2, sBind)
			  g.exec("delete from verboten where aic_mandant="+iMandant+" and aic_tabellenname="+iTab+" and aic_fremd="+iAic);
			  Qry.add("Aic_Mandant", iMandant);
			  Qry.add("Aic_tabellenname",iTab);		  
			  Qry.add("Aic_Fremd",iAic);
			  Qry.add("Aic_Protokoll",iProt);
			  Qry.add("VBits",FRIST);
			  Qry.add0("Frist_Austritt", Sort.geti(Vec, 3));
			  Qry.add0("Frist_Anlage", Sort.geti(Vec, 4));
			  //Qry.add("Frist_"+(bAustritt?"Austritt":"Anlage"),SpnAnzahl.getValue());
			  Qry.insert("Verboten", false);
		  }
		  Qry.free();
		  Out.folderChanged(Out.getRootNode());
		  //fill();
		  //Refresh();
	  }
	  
	  private void Save()
	  {
		  g.fixtestError("Save: hinzu:"+VecP+", weg:"+VecM);
		  Tabellenspeicher Tab2=null;
		  if (VecP.size()>0)
		  {
			  Tab2=new Tabellenspeicher(g, new String[] {"Bezeichnung","Anzahl"});
			  for (int i=0;i<VecP.size();i++)
			  {
				  int iAic=Sort.geti(VecP, i);		
				  int iPos=Tab.getPos("Aic",iAic);
				  Tab2.addInhalt("Bezeichnung",Tab.getS(iPos,"Bezeichnung"));
				  Tab2.addInhalt("Anzahl",Tab.getS(iPos,"Anzahl"));
			  }
		  }
		  if (VecP.size()==0 || new Message(Message.YES_NO_OPTION+Message.SHOW_TAB,thisJFrame(),g).showDialog("Loeschen3", Tab2)==Message.YES_OPTION)
		  {
			  boolean bEig=RadEig.isSelected();
			  int iTab=g.TabTabellenname.getAic(bEig?"EIGENSCHAFT":"BEWEGUNGSTYP");
			  SQL Qry=new SQL(g);
			  int iProt=g.Protokoll("Verboten", getBegriff());
			  for (int i=0;i<VecP.size();i++)
			  {
				  int iAic=Sort.geti(VecP, i);
				  if (bEig)
				  {
					  int iAnz=g.exec("delete from stammpool where aic_stamm in (select aic_stamm from stammview2 where aic_mandant="+iMandant+") and aic_eigenschaft="+iAic);
					  if (iAnz>0)
						  g.printError(iAnz+"x Eigenschaft "+g.TabEigenschaften.getBezeichnungS(iAic)+" endgültig von Datenbank entfernt");
				  }
				  else
				  {
					  int iAnz=g.exec("update bew_pool set pro_aic_protokoll="+iProt+" where pro_aic_protokoll is null and aic_bewegungstyp="+iAic+" and aic_mandant="+iMandant);
					  if (iAnz>0)
						  g.printError(iAnz+"x Bewegung "+g.TabErfassungstypen.getBezeichnungS(iAic)+" entfernt");
				  }
				  Qry.add("Aic_Mandant", iMandant);
				  Qry.add("Aic_tabellenname",iTab);		  
				  Qry.add("Aic_Fremd",iAic);
				  Qry.add("Aic_Protokoll",iProt);
				  Qry.add0("VBits",0);
				  Qry.insert("Verboten", false);
			  }
			  Qry.free();
			  if (VecM.size()>0)
				  g.exec("delete from verboten where aic_mandant="+iMandant+" and aic_tabellenname="+iTab+" and aic_fremd "+Static.SQL_in(VecM));
		  }
		  Refresh();
	  }

}
