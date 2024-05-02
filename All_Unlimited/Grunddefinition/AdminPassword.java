/*
    All_Unlimited-Grunddefinition-AdminPassword.java

    NOTE: This file is a generated file.
          Do not modify it by hand!
*/

package All_Unlimited.Grunddefinition;

// add your custom import statements here
import java.awt.Color;
//import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JPanel;

import jclass.bwt.JCItemEvent;
//import jclass.bwt.JCOutliner;
import jclass.bwt.JCOutlinerEvent;
import jclass.bwt.JCOutlinerFolderNode;
import jclass.bwt.JCOutlinerListener;
import jclass.bwt.JCOutlinerNode;
import jclass.bwt.JCOutlinerNodeStyle;
import All_Unlimited.Allgemein.Anzeige.Zeit;
import All_Unlimited.Allgemein.Eingabe.AUOutliner;
import All_Unlimited.Allgemein.Global;
import All_Unlimited.Allgemein.Message;
import All_Unlimited.Allgemein.SQL;
import All_Unlimited.Allgemein.Static;
import All_Unlimited.Allgemein.Tabellenspeicher;
import All_Unlimited.Allgemein.Sort;
//import All_Unlimited.Allgemein.Transact;

public class AdminPassword extends All_Unlimited.Allgemein.Formular
{
  public static AdminPassword start(Global glob)
  {
    if (This == null) new AdminPassword(glob);
    This.show();
    return This;
  }

  public static void free()
  {
    if (This != null)
    {
      This.g.winInfo("AdminPassword.free");
      This.thisFrame.dispose();
      This = null;
    }
  }

private AdminPassword(Global glob)
{
	super("AdminPassword",null,glob);
	g=glob;
	g.winInfo("AdminPassword.create");
	This=this;
	g.putTabFormulare(getBegriff(),0,this);

	Build();

        ActionListener AL=new ActionListener()
        {
          public void actionPerformed(ActionEvent ev)
          {
            String s = ev.getActionCommand();
            if (s.equals("Reset"))
              reset_Password();
            else if (s.equals("Exit"))
              hide();
          }
        };
        BtnNewPassword = g.BtnAdd(getButton("new password"),"Reset",AL);
        BtnExit = g.BtnAdd(getButton("Beenden"),"Exit",AL);

        Action cancelKeyAction = new AbstractAction()
          {
            /**
			 *
			 */
			private static final long serialVersionUID = 4652743104938488331L;

			public void actionPerformed(ActionEvent e) {
              hide();
            }
          };
        Static.Escape(BtnExit,thisFrame,cancelKeyAction);

	OutUser.addItemListener(new JCOutlinerListener ()
	{
		public void outlinerNodeSelectEnd(JCOutlinerEvent ev)
		{
			EnableButtons();
		}

		public void outlinerNodeSelectBegin(JCOutlinerEvent ev)
		{
		}

		public void outlinerFolderStateChangeEnd(JCOutlinerEvent ev)
		{
		}

		public void outlinerFolderStateChangeBegin(JCOutlinerEvent ev)
		{
		}

		public void itemStateChanged(JCItemEvent ev)
		{
		}
	});
}

public void show()
{
	fillOutliner();
	super.show();
}

private void Build()
{
	/*String[] s = new String[] {g.getBezeichnung("Tabellenname","BENUTZER"),g.getBegriff("Show","Kennung"),g.getBegriff("Show","Passwort"),g.getBegriff("Show","Art")};
	OutUser.setColumnButtons(s);
	OutUser.setNumColumns(s.length);
	OutUser.setColumnLabelSortMethod(Sort.sortMethod);
	OutUser.setBackground(Color.white);
	OutUser.setRootVisible(false);*/
        g.initOutliner(OutUser,new String[] {"Benutzer","Kennung","Passwort","Art","bis"});


	JPanel Pnl = getFrei("Outliner Benutzer");

	if(Pnl!=null)
		Pnl.add(OutUser);
}

private void fillOutliner()
{
	JCOutlinerNodeStyle NodeStyleMandant = g.getStyle(g.getGif(g.TabTabellenname,"MANDANT"));//g.TabTabellenname.posInhalt("Kennung","MANDANT") ? (Image)g.TabTabellenname.getInhalt("Bild"): null);
	JCOutlinerNodeStyle NodeStyleBenutzer = g.getStyle(g.getGif(g.TabTabellenname,"BENUTZER"));//g.TabTabellenname.posInhalt("Kennung","BENUTZER") ? (Image)g.TabTabellenname.getInhalt("Bild"): null);
	JCOutlinerNodeStyle NodeStyleBenutzerStart = g.getStyle(g.getGif(g.TabTabellenname,"BENUTZER"));//g.TabTabellenname.posInhalt("Kennung","BENUTZER") ? (Image)g.TabTabellenname.getInhalt("Bild"): null);
	JCOutlinerNodeStyle NodeStyleProgDefStart = g.getStyle(g.getGif(g.TabTabellenname,"BENUTZER"));//g.TabTabellenname.posInhalt("Kennung","BENUTZER") ? (Image)g.TabTabellenname.getInhalt("Bild"): null);
	NodeStyleBenutzerStart.setFont(Global.fontTitel);
	NodeStyleProgDefStart.setFont(Global.fontTitel);
	NodeStyleProgDefStart.setForeground(Color.red);

	((JCOutlinerFolderNode)OutUser.getRootNode()).removeChildren();

	SQL Qry = new SQL(g);
	int iM=g.getMandant();

	Tabellenspeicher TabMandant = new Tabellenspeicher(g,"select passwort,aic_mandant,kennung,Tod"+g.AU_Bezeichnung("mandant")+" from mandant "+(iM==1 ?g.getWriteMandanten(true):" where aic_mandant="+iM),true);
//	TabMandant.showGrid("TabMandant");
	if (TabMandant.size()>0)
	 if(Qry.open("select * from (select aic_benutzer,kennung,"+g.AU_BezeichnungF("benutzer")+" Bezeichnung,passwort,"+/*g.bits("bits",Global.cstBenutzerEbene)+*/" bits, use_bis, aic_mandant from benutzer where geloescht is null"+
			 (g.Def() ? "":" AND"+g.bitis("Benutzer.Bits", Global.cstTimerBenutzer+Global.cstAServerUser+Global.cstTerminalUser+Global.cstImport, 0))+
			 (g.Def()?"":" AND"+g.bits("Benutzer.Bits",3)+"<>1")+(g.Def()?"":" AND"+g.bits("Benutzer.Bits",3)+"<>2")+(g.SuperUser()?"":" AND"+g.bits("Benutzer.Bits",3)+"<>3")+
	   " and aic_mandant"+Static.SQL_in(TabMandant.getVecSpalteI("aic_mandant"))/*g.getWriteMandanten(false)*/+") x order by aic_mandant,Bezeichnung"))
	{
		int iAIC_Mandant=0;
		JCOutlinerFolderNode NodeMandant = null;
        //g.testInfo("Def="+g.Def()+"/"+Global.cstSuperUser+"/"+Global.cstNormalUser);
        int iAnz1=0;
        int iAnz0=0;
		for(;!Qry.eof();Qry.moveNext())
		{
			if(iAIC_Mandant!=Qry.getI("aic_mandant"))
			{
				iAIC_Mandant=Qry.getI("aic_mandant");
				if (TabMandant.posInhalt("aic_mandant",iAIC_Mandant) && !TabMandant.getB("Tod"))
                                {
                                  Vector<String> VecVisible = new Vector<String>();
                                  VecVisible.addElement(!TabMandant.getS("bezeichnung").equals("") ? TabMandant.getS("bezeichnung") : TabMandant.getS("kennung"));
                                  VecVisible.addElement(TabMandant.getS("kennung"));
                                  if (g.Def())
                                    VecVisible.addElement(g.getPassword(TabMandant.getS("passwort"), Global.PWR, 0));
                                  NodeMandant = new JCOutlinerFolderNode((Object)VecVisible, (JCOutlinerFolderNode)OutUser.getRootNode());
                                  NodeMandant.setStyle(NodeStyleMandant);                                
                                }
                                else
                                  NodeMandant=null;
			}
            if (NodeMandant != null)
            {
              int iBit = Qry.getI("bits") & g.cstBenutzerEbene;
              //g.testInfo("bits="+Qry.getI("bits")+"/"+g.cstBenutzerEbene+"->"+iBit);
              Vector<String> VecVisible = new Vector<String>();
              Vector<Integer> VecInvisible = new Vector<Integer>();
              VecVisible.addElement(Qry.getS("bezeichnung"));
              VecVisible.addElement(Qry.getS("kennung"));
              int iPW_Art=Qry.getI("bits") & Global.cstPW;
              String sPasswort = iPW_Art == Global.cstPW_LDAP ? "<LDAP>" : iPW_Art == Global.cstPW_AZURE ? "<Azure>": g.getPassword(Qry.getS("passwort"), iPW_Art == Global.cstPW_MD5 ? Global.PWH:Global.PWVH, Qry.getI("aic_benutzer"));
              VecVisible.addElement(sPasswort.equals(Static.sDefaultPW) || g.Def() && (iBit == Global.cstSuperUser || iBit == Global.cstUserManager || iBit == Global.cstNormalUser) ? sPasswort :"*****");
              VecVisible.addElement(iBit == 0 ? "" : g.getBegriffS("Radiobutton", iBit == Global.cstProg ? "Prog" : iBit == Global.cstDef ? "Def" : iBit == Global.cstVerw ? "Verwaltung" :iBit == Global.cstSuperUser ? "Superuser":"UserManager"));
              VecVisible.addElement(Qry.isNull("use_bis") ? null:""+new Zeit(Qry.getD("use_bis"),"dd.MM.yyyy HH:mm"));
              VecInvisible.addElement(Qry.getInt("aic_benutzer"));
              VecInvisible.addElement(new Integer(iBit));
              VecInvisible.addElement(Qry.getInt("bits"));
              JCOutlinerNode NodeUser = new JCOutlinerNode(VecVisible, NodeMandant);
              NodeUser.setUserData(VecInvisible);
              NodeUser.setStyle(sPasswort.equalsIgnoreCase(Static.sDefaultPW) ? iBit == Global.cstProg || iBit == Global.cstDef ? NodeStyleProgDefStart : NodeStyleBenutzerStart : NodeStyleBenutzer);
              iAnz1++;
            }
            else
              iAnz0++; 
		}
		Qry.close();
		g.fixInfoT("Benutzer gefunden:"+iAnz1+", nicht:"+iAnz0);
	}

	OutUser.folderChanged(OutUser.getRootNode());
}

private void EnableButtons()
{
	JCOutlinerNode Node = OutUser.getSelectedNode();
	if (Node==null || Node.getLevel()<2)
		BtnNewPassword.setEnabled(false);
	else
	{
		Vector Vec=(Vector)Node.getLabel();
		String sPW=Vec!=null && Vec instanceof Vector ? Sort.gets(Vec, 2):null;
		boolean bKR=sPW!=null && sPW.length()>1 && (sPW.equals(Static.sDefaultPW) || sPW.equals("<Azure>") || sPW.equals("<LDAP>")); // kein rücksetzen
		//g.fixtestInfo("PW="+sPW+", bStart="+bStart);
		int iBit = Node!=null && Node.getLevel()==2?((Integer)((Vector)Node.getUserData()).elementAt(1)).intValue():-1;
		BtnNewPassword.setEnabled(!bKR && iBit>=0 && (g.Def() || (g.UserManager() ? iBit!=Global.cstProg&&iBit!=Global.cstDef:iBit==Global.cstNormalUser)));
	}
}

private void reset_Password()
{
	JCOutlinerNode Node = OutUser.getSelectedNode();
	int iAIC_Benutzer = Sort.geti(Node.getUserData(),0);
	int iBits=Sort.geti(Node.getUserData(),2);
	int iPW_Art=iBits&Global.cstPW;
	int iPWArt=iPW_Art==Global.cstPW_MD5 ? Global.PWH:Global.PWVH;
	String sPW=g.PasswordConvert(Static.sDefaultPW,iPWArt,iAIC_Benutzer);
	g.exec("update benutzer set passwort='"+sPW+"',aic_logging="+g.getLog()+" where aic_benutzer="+iAIC_Benutzer);
	if ((iBits&Global.cstDB)>0)
		UserManager.changePW(g, SQL.getString(g,"select kennung from benutzer where aic_benutzer="+iAIC_Benutzer), sPW,null);
	g.SaveDefVerlauf(getBegriff(), "B"+iAIC_Benutzer+": resetPW");
	new Message(Message.INFORMATION_MESSAGE,null,g).showDialog("New Password",new String[]{Static.sDefaultPW});
	fillOutliner();
}

// add your data members here
private Global g;

private AUOutliner OutUser = new AUOutliner(new JCOutlinerFolderNode("Root"));

private JButton BtnNewPassword;
private JButton BtnExit;

private static AdminPassword This=null;
}

