package All_Unlimited.Print;

//import java.awt.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import All_Unlimited.Allgemein.Global;
import All_Unlimited.Allgemein.Static;
import All_Unlimited.Allgemein.Eingabe.ComboSort;
import All_Unlimited.Allgemein.Eingabe.Text;
import All_Unlimited.Allgemein.Eingabe.Zahl;


// add your custom import statements here

public class Vorschau2 extends All_Unlimited.Allgemein.Formular
{
  /**
   * erzeugt Vorschau für neuen Druck
   */
	public Vorschau2(Global rglobal,PrintManagerA rpm,String sForm)//,boolean bEdit,java.util.Vector vecObj)
	{
		super(sForm,null,rglobal);



		//System.out.println("Vec: "+vecObj.size());
		//iListener=new ItemListener();
		//int iStellen=2;
		global=rglobal;
		pm=rpm;

		//DefaultListModel lll=(DefaultListModel)vecList.elementAt(0);
		//System.out.println("Count I:"+lll.getSize());
        BtnFirst=getButton("<<")==null?new JButton():getButton("<<");
		BtnPrev=getButton("<")==null?new JButton():getButton("<");
		BtnNext=getButton(">")==null?new JButton():getButton(">");
		BtnLast=getButton(">>")==null?new JButton():getButton(">>");
		BtnPDF=getButton("PDF")==null?new JButton():getButton("PDF");
		JButton BtnEnde=getButton("Beenden")==null?new JButton():getButton("Beenden");
		JButton BtnAktSeite=getButton("Seite drucken")==null?new JButton():getButton("Seite drucken");
		JButton BtnDruck=getButton("alle Seiten drucken")==null?new JButton():getButton("alle Seiten drucken");
                ((JFrame)thisFrame).getRootPane().setDefaultButton(BtnDruck);
		//JButton BtnDA=getButton("Drucker Auswahl")==null?new JButton():getButton("Drucker Auswahl");


		//anz=pm.getNumberOfPages();
		anz=pm.countPages();
		//if(anz>100)
	    	//iStellen=4;
		//else if(anz>10)
		//	iStellen=3;
		seite=1;

		preview=new PreviewA(pm,global);
	    	//preview.setEditable(bEdit);
		JPanel PnlVorschau=getFrei("Vorschau");
		JPanel PnlCombo=getFrei("Combo Zoom")==null?new JPanel():getFrei("Combo Zoom");
		JPanel PnlLbl=getFrei("Label")==null?new JPanel():getFrei("Label");
		//JPanel PnlVon=getFrei("von")==null?new JPanel():getFrei("von");
		//JPanel PnlBis=getFrei("bis")==null?new JPanel():getFrei("bis");
		JPanel PnlAnz=getFrei("Anzahl")==null?new JPanel():getFrei("Anzahl");
                JPanel PnlSeiten=getFrei("Seiten");
		//final JPanel PnlLbl2=getFrei("Label2")==null?new JPanel():getFrei("Label2");
                LblAnz=new JLabel("1 / "+pm.countPages(),SwingConstants.CENTER);
		//JLabel LblStrich= new JLabel("-");
		//final Zahl TxfVon=new Zahl(1);
		//final Zahl TxfBis=new Zahl(anz);
                //TxfVon.setMax(new Integer(anz));
                //TxfBis.setMax(new Integer(anz));
		final Zahl TxfAnz=new Zahl(1);
                final ComboSort CboZoom=new ComboSort(global);
                final Text EdtSeiten=PnlSeiten==null ? null:new Text("",10);

		CboZoom.setSorted(false);
                CboZoom.setKein(true);
		//CboZoom.addItem("25%",1,"25%");
		CboZoom.addItem("50%",2,"50%");
		CboZoom.addItem("75%",3,"75%");
		CboZoom.addItem("100%",4,"100%");
                CboZoom.addItem("120%",5,"120%");
		CboZoom.addItem("150%",6,"150%");
		CboZoom.addItem("200%",7,"200%");
                CboZoom.addItem("400%",8,"400%");
                CboZoom.addItem("800%",9,"800%");
		//CboZoom.addItem("2Page",8,"2 Pages");
		//CboZoom.addItem("4Page",9,"4 Pages");
		//CboZoom.addItem("PageWidth",10,"Page-Width");
		//CboZoom.addItem("PageHeight",11,"Page-Height");



		//CboZoom.setSelectedKennung("150%");
		PnlVorschau.add(preview);
		PnlCombo.add(CboZoom);
		PnlLbl.add(LblAnz);
		//PnlVon.add(TxfVon);
		//PnlBis.add(TxfBis);
		PnlAnz.add(TxfAnz);
                if (EdtSeiten != null)
                {
                  EdtSeiten.setText(1+"-"+anz);
                  PnlSeiten.add(EdtSeiten);
                }
		//PnlLbl2.add(LblStrich);

		//pm.preview(preview);
		//TxfAnz.setText("1");
		//TxfVon.setText("1");
		//TxfBis.setText(""+anz);

                EnableButtons();

		show();

		CboZoom.addItemListener(
			new ItemListener(){
				public void itemStateChanged(ItemEvent ie)
				{
                                  if(ie.getStateChange() == ItemEvent.SELECTED)
                                  {
					String sSelected=CboZoom.getSelectedKennung();
					if(sSelected.equals("50%"))
                                          preview.setZoom(0.5);
					else if(sSelected.equals("75%"))
                                          preview.setZoom(0.75);
					else if(sSelected.equals("100%"))
                                          preview.setZoom(1);
                                        else if(sSelected.equals("120%"))
                                          preview.setZoom(1.2);
					else if(sSelected.equals("150%"))
                                          preview.setZoom(1.5);
					else if(sSelected.equals("200%"))
                                          preview.setZoom(2);
                                        else if(sSelected.equals("400%"))
                                          preview.setZoom(4);
                                        else if(sSelected.equals("800%"))
                                          preview.setZoom(8);
                                        //else if(sSelected.equals("2 Pages"))
                                        //  preview.setView(preview.VIEW_2PAGES);
                                        //global.progInfo("Zoomx "+sSelected);
                                        //Static.sleep(100);
                                        Static.repaint(thisFrame);
                                        //Static.sleep(100);
                                        //preview.repaint();
                                        //Static.sleep(100);
                                        thisFrame.repaint();
					/*	preview.setView(preview.VIEW_200);
					else if(sSelected.equals("1 Page"))
						preview.setView(preview.VIEW_1PAGE);
					else if(sSelected.equals("2 Pages"))
						preview.setView(preview.VIEW_2PAGES);
					else if(sSelected.equals("4 Pages"))
						preview.setView(preview.VIEW_4PAGES);
					else if(sSelected.equals("Page-Width"))
						preview.setView(preview.VIEW_WIDTH);
					else if(sSelected.equals("Page-Height"))
						preview.setView(preview.VIEW_HEIGHT);*/

				}
                              }
			}
			);
                CboZoom.setSelectedKennung("150%");

		/*BtnDA.addActionListener(
			new ActionListener(){
				public void actionPerformed(ActionEvent ae)
				{
					pm.printDialog();
					thisFrame.toFront();
				}
			}
			);*/

		BtnDruck.addActionListener(
			new ActionListener(){
				public void actionPerformed(ActionEvent ae)
				{
                                  //int iVon=TxfVon.intValue();
                                  //int iBis=TxfBis.intValue();
                                  pm.startPrint(TxfAnz.intValue(),/*EdtSeiten==null ? iVon+"-"+iBis:*/EdtSeiten.getText());
                                  if (/*EdtSeiten==null && iVon==1 && iBis==anz || EdtSeiten != null &&*/ EdtSeiten.getText().equals("1-"+anz))
                                  {
                                    saveFenster();
                                    thisFrame.dispose();
                                  }
                                  pm.printPDFs();
				}
			}
			);

		BtnAktSeite.addActionListener(
			new ActionListener(){
				public void actionPerformed(ActionEvent ae)
				{
                                  pm.startPrint(TxfAnz.intValue(),""+seite);
					/*int	iAnz=new Integer(TxfAnz.getText()).intValue();
					iAnz=iAnz<0?1:iAnz;
					thisFrame.setCursor(new java.awt.Cursor(java.awt.Cursor.WAIT_CURSOR));
					pm.print(iAnz,seite,seite);
					thisFrame.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));*/
				}
			}
		);



		BtnNext.addActionListener(
			new ActionListener(){
				public void actionPerformed(ActionEvent ae)
				{
					//preview.nextPage();
					seite++;
                                        preview.gotoPage(seite);
					EnableButtons();
				}
			}
			);

		BtnLast.addActionListener(
			new ActionListener(){
				public void actionPerformed(ActionEvent ae)
				{
					//preview.lastPage();
					seite=anz;
                                        preview.gotoPage(anz);
					EnableButtons();
				}
			}
			);

		BtnPrev.addActionListener(
			new ActionListener(){
				public void actionPerformed(ActionEvent ae)
				{
					//preview.previousPage();
					seite--;
                                        preview.gotoPage(seite);
					EnableButtons();
				}
			}
			);

		BtnFirst.addActionListener(
			new ActionListener(){
				public void actionPerformed(ActionEvent ae)
				{
					//preview.firstPage();
					seite=1;
                                        preview.gotoPage(1);
					EnableButtons();
				}
			}
			);
		
		BtnPDF.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent ae)
					{
						pm.openPDFs();
					}
				}
				);

		BtnEnde.addActionListener(
			new ActionListener(){
				public void actionPerformed(ActionEvent ae)
				{
					saveFenster();
					thisFrame.dispose();
				}
			}
                      );
                Action cancelKeyAction = new AbstractAction() {
                  /**
					 *
					 */
					private static final long serialVersionUID = -1440434767891773051L;

				public void actionPerformed(ActionEvent e)
                  {
                    saveFenster();
                    thisFrame.dispose();
                  }
                };
                Static.Escape(BtnEnde,thisFrame,cancelKeyAction);


		/*TxfAnz.addFocusListener(
			new FocusListener(){
				public void focusGained( java.awt.event.FocusEvent e)
				{}
                public void focusLost( java.awt.event.FocusEvent e)
				{
					try
					{
						new Integer(TxfAnz.getText()).intValue();
					}catch(Exception ee)
					{
						TxfAnz.setText("1");
					}
				}
			}
			);
		TxfVon.addFocusListener(
			new FocusListener(){
				public void focusGained( java.awt.event.FocusEvent e)
				{}
                public void focusLost( java.awt.event.FocusEvent e)
				{
					pruefe(TxfVon,TxfBis);
				}
			}
			);

		TxfBis.addFocusListener(
			new FocusListener(){
				public void focusGained( java.awt.event.FocusEvent e)
				{}
                public void focusLost( java.awt.event.FocusEvent e)
				{
					pruefe(TxfVon,TxfBis);
				}
			}
			);

        */



		//Listeners listener=new Listeners(pm,vecObj);
		//set set1=new set(listener,pm);
		//set1.start();

		//preview.addPropertyChangeListener(listener);
		//pm.addObserver(listener);


		//preview.setView(Preview.VIEW_150);





	}

        /*

	public void pruefe(JTextField TxfVon,JTextField TxfBis)
	{


		int iVon=0;
		int iBis=0;
		try
		{
			iVon=new Integer(TxfVon.getText()).intValue();
		}catch(Exception exc)
		{
			TxfVon.setText("1");
		}
		try
		{
		   	iBis=new Integer(TxfBis.getText()).intValue();
		}catch(Exception exc)
		{
			TxfBis.setText(""+anz);
		}
		if(iBis<1||iBis>anz)
			TxfBis.setText(""+anz);
		else if(iVon<1||iVon>anz)
		    TxfVon.setText("1");
		else if(iVon>iBis)
		{
			TxfVon.setText(""+iBis);
			TxfBis.setText(""+iVon);
	    }
	}

      */

     private void EnableButtons()
     {
       BtnLast.setEnabled(seite<anz);
       BtnNext.setEnabled(seite<anz);
       BtnFirst.setEnabled(seite>1);
       BtnPrev.setEnabled(seite>1);
       LblAnz.setText(seite+" / "+anz);
       int iPDF=pm.pdfCount();
       BtnPDF.setText(iPDF+" PDF");
       BtnPDF.setEnabled(iPDF>0);
     }

     private Global global;
     private PrintManagerA pm;
     private JButton BtnFirst;
     private JButton BtnPrev;
     private JButton BtnNext;
     private JButton BtnLast;
     private JButton BtnPDF;
     private JLabel LblAnz;

     private PreviewA preview;
     private int anz=0;
     private int seite=0;
}
