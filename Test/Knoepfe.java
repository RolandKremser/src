package Test;

import java.applet.Applet;
import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * <p>Überschrift: </p>
 *
 * <p>Beschreibung: </p>
 *
 * <p>Copyright: Copyright (c) 2004</p>
 *
 * <p>Organisation: DVH</p>
 *
 * @author Roland Kremser
 * @version 1.0
 */
public class Knoepfe extends Applet
{
  /**
	 * 
	 */
	private static final long serialVersionUID = 7896376653365822375L;
private Button BtnE=new Button("Enable");
  private Button BtnR=new Button("Readonly");
  private Button BtnZ=new Button("Zwingend");
  private Button BtnEnde=new Button("Beenden");
  private TextArea Edt=new TextArea();
  private All_Unlimited.Allgemein.Eingabe.AUCheckBox Cbx=new All_Unlimited.Allgemein.Eingabe.AUCheckBox("Cbx");
  private Frame f;

  public Knoepfe()
  {

  }

  public void start()
  {
    f=new Frame("Test");
    f.setLayout(new BorderLayout());
    Panel PnlS=new Panel(new GridLayout());
    PnlS.add(BtnE);
    PnlS.add(BtnR);
    PnlS.add(BtnZ);
    PnlS.add(BtnEnde);

    BtnE.addActionListener(new ActionListener()
    {
          public void actionPerformed(ActionEvent e)
          {
                  Edt.setEnabled(!Edt.isEnabled());
                  Cbx.setEnabled(!Cbx.isEnabled());
          }
    });
    BtnR.addActionListener(new ActionListener()
        {
              public void actionPerformed(ActionEvent e)
              {
                      Edt.setEditable(!Edt.isEditable());
                      //UIManager.getDefaults().put("ComboBox.disabledForeground", Color.GREEN);
                      //Cbx.setEditable(false);
                      //Cbx.enableInputMethods(false);
                      //Cbx.setEditable(!Edt.isEditable());
              }
    });
    BtnZ.addActionListener(new ActionListener()
    {
          public void actionPerformed(ActionEvent e)
          {
                  Edt.setBackground(Edt.getBackground().equals(Color.yellow)?Color.white:Color.yellow);
          }
    });
    BtnEnde.addActionListener(new ActionListener()
    {
          public void actionPerformed(ActionEvent e)
          {
                  f.dispose();
          }
    });

    f.add("South",PnlS);
    f.add("Center",Edt);
    f.add("North",Cbx);
    f.pack();
    f.setVisible(true);
  }

}
