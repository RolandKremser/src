package All_Unlimited.Allgemein.Eingabe;

import java.awt.BorderLayout;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JPanel;

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
public class Zahl2 extends JPanel
{
  /**
	 *
	 */
	private static final long serialVersionUID = -4774881859207975031L;
public Zahl Edt;

  public Zahl2(Object Obj,int riStellen)
  {
        Edt = new Zahl(Obj,riStellen);
        Build();
  }

  private void Build()
  {
        setLayout(new BorderLayout());
        add("Center",Edt);
        addFocusListener(new FocusListener()
        {
                        public void focusGained(FocusEvent fe)
                        {
                                Edt.requestFocus();
                        }
                        public void focusLost(FocusEvent fe)
                        {
                        }
        });
  }

public void Reset2()
{
  Edt.Reset2();
}


}
