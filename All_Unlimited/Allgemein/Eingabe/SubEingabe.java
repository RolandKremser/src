package All_Unlimited.Allgemein.Eingabe;

import java.awt.BorderLayout;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import All_Unlimited.Allgemein.Formular;
import All_Unlimited.Allgemein.Global;
import All_Unlimited.Allgemein.Tabellenspeicher;
import All_Unlimited.Allgemein.Transact;
import All_Unlimited.Hauptmaske.Abfrage;

public class SubEingabe extends JPanel 
{
    private Global g;
    private Window PFom;
    private ComboSort CboArt;
    private JPanel PnlSub=new JPanel(new BorderLayout());
    private ComboSort Cbo;
    private FormularEingabe CboSubFom=null;
	private AbfrageEingabe CboSubAbf=null;
	private ModellEingabe CboSubMod=null;
	private DruckEingabe CboSubPnt=null;
	private ComboSort CboSubDia=null;
    // private JButton BtnList;
    // private JDialog Frm=null;
    // private AUOutliner Out = new AUOutliner(new JCOutlinerFolderNode(""));
    // private JCOutlinerNode NodeMom=null;
    // private int iBegriff=0;
    // private int iFF=100;
    // private int iAic=0;
    // private String sGruppe=null;

  public SubEingabe(Global rg)
  {
    g=rg;
    build();
  }

//   public void setDlg(Window rFom)
//   {
//     PFom=rFom;
//   }

  public void build()
  {
    setLayout(new BorderLayout(5,5));
    CboArt = new ComboSort(g);
    String[] sTypen= {"Frame","Abfrage","Modell","Druck","Grafik"};
    for (int i=0;i<sTypen.length;i++)
        CboArt.addItem(g.TabBegriffgruppen.getBezeichnungS(sTypen[i]), 1, sTypen[i]);
    CboArt.setKein(true);

    Cbo = new ComboSort(g);
    Cbo.setPreferredSize(new java.awt.Dimension(200,20));
    Cbo.setMaximumRowCount(20);
    Cbo.setFont(Transact.fontStandard);
    Cbo.setKein(true);
    // Cbo.addKeyListener(new KeyListener()
    //     {
    //             public void keyPressed(KeyEvent e)
    //             {
    //             }
    //             public void keyReleased(KeyEvent e)
    //             {
    //             }
    //             public void keyTyped(KeyEvent e)
    //             {
    //               if(e.getKeyChar()=='*')
    //               {
    //                 e.consume();
    //                 OpenList();
    //               }
    //             }
    //     });

    CboArt.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					checkPnl();
				}
			});

    add("West",CboArt);
    PnlSub.add(Cbo);
    add("Center",PnlSub);
  }

    private void checkPnl()
    {
        PnlSub.removeAll();
        String sSub=CboArt.getSelectedKennung();
        if (sSub.equals("Frame"))
        {
            if (CboSubFom==null)
            {
                CboSubFom=new FormularEingabe(g,PFom);
                CboSubFom.Cbo.fillCbo("select aic_begriff,kennung,defbezeichnung Bezeichnung from begriff where aic_begriffgruppe="+g.TabBegriffgruppen.getAic("Frame")+" and "+g.bit("bits", Formular.cstTabFom)+" and Tod is null", "begriff", true,false);
            }
            PnlSub.add("Center",CboSubFom);
        }
        else if (sSub.equals("Modell"))
        {   
            if (CboSubMod==null)
            {
                CboSubMod=new ModellEingabe(g,PFom);
                CboSubMod.fillCboM(-2);
            }
            PnlSub.add("Center",CboSubMod);
        }
        else if (sSub.equals("Abfrage"))
        {
            if (CboSubAbf==null)
            {
                CboSubAbf=new AbfrageEingabe(g,PFom,true,true);
                CboSubAbf.fillCbo("select b.aic_begriff,kennung,defbezeichnung Bezeichnung from begriff b join Abfrage a on b.aic_begriff=a.aic_begriff where "+g.bit("abits2", Abfrage.cstSubFom)+" and Tod is null", "begriff", true);
            }
            PnlSub.add("Center",CboSubAbf);
        }
        else if (sSub.equals("Druck"))
        {
            if (CboSubPnt==null)
            {
                CboSubPnt=new DruckEingabe(g,PFom);
                CboSubPnt.fillCbo(new Tabellenspeicher(g, "select aic_begriff aic,kennung,defbezeichnung defBez"+g.AU_Bezeichnung("begriff")+
                    ",(select ein from logging where aic_logging=begriff.aic_logging) Log from begriff where aic_begriffgruppe="+g.TabBegriffgruppen.getAic("Druck")+" and web=1 and Tod is null", true), true);
            }
            PnlSub.add("Center",CboSubPnt);
        }
        else if (sSub.equals("Grafik"))
        {
            if (CboSubDia==null)
            {
                CboSubDia=new ComboSort(g);
                CboSubDia.fillCbo("select aic_begriff,kennung,defbezeichnung Bezeichnung from begriff where aic_begriffgruppe="+g.TabBegriffgruppen.getAic("Grafik"), "begriff", true,false);
            }
            PnlSub.add("Center",CboSubDia);
        }
        else
            PnlSub.add(Cbo);
        if (PFom != null)
            PFom.repaint();

    }

    public void init()
    {
        CboSubFom=null;
        CboSubMod=null;
        CboSubAbf=null;
        CboSubPnt=null;
        CboSubDia=null;
    }

    // public void show()
    // {
    //     if (sGruppe != null)
    //         CboArt.setSelectedKennung(sGruppe);
    //     else
    //         return;
    //     checkPnl();
    //     if (sGruppe.equals("Frame"))
    //         CboSubFom.Cbo.setSelectedAIC(iAic);
    //     else if (sGruppe.equals("Modell"))
    //         CboSubMod.setSelectedAIC(iAic);
    //     else if (sGruppe.equals("Abfrage"))
    //         CboSubAbf.setSelectedAIC(iAic);
    //     else if (sGruppe.equals("Druck"))
    //         CboSubPnt.setSelectedAIC(iAic);
    //     else if (sGruppe.equals("Grafik"))
    //         CboSubDia.setSelectedAIC(iAic);
    //     sGruppe=null;
    //     iAic=0;
    // }

    public void setAic(int iAic,String sGruppe,Window rFom)
    {
        PFom=rFom;
        // iAic=riAic;
        // sGruppe=rsGruppe;
        // show();
        if (sGruppe != null)
            CboArt.setSelectedKennung(sGruppe);
        else
            CboArt.setSelectedAIC(0);
        checkPnl();
        if (sGruppe==null)
            return;
        if (sGruppe.equals("Frame"))
            CboSubFom.Cbo.setSelectedAIC(iAic);
        else if (sGruppe.equals("Modell"))
            CboSubMod.setSelectedAIC(iAic);
        else if (sGruppe.equals("Abfrage"))
            CboSubAbf.setSelectedAIC(iAic);
        else if (sGruppe.equals("Druck"))
            CboSubPnt.setSelectedAIC(iAic);
        else if (sGruppe.equals("Grafik"))
            CboSubDia.setSelectedAIC(iAic);
        // sGruppe=null;
        // iAic=0;
    }

    public int getAic()
	{
		if (CboArt.getSelectedAIC()==0)
			return 0;//iAic>0 ? iAic:0;
		else
		{
			String sSub=CboArt.getSelectedKennung();
			if (sSub.equals("Frame"))
				return CboSubFom.Cbo.getSelectedAIC();
			else if (sSub.equals("Modell"))
				return CboSubMod.getSelectedAIC();
			else if (sSub.equals("Abfrage"))
				return CboSubAbf.getSelectedAIC();
			else if (sSub.equals("Druck"))
				return CboSubPnt.getSelectedAIC();
			else if (sSub.equals("Grafik"))
				return CboSubDia.getSelectedAIC();
			else
				return -1;
		}
	}

}
