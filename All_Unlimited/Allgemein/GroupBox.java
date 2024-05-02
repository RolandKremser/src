package All_Unlimited.Allgemein;

/*
Programm: GroupBox.java
Dieses Programm dient nur dazu, optisch einen Rahmen um Steuerelemente
ziehen. GroupBox ist eine Ableitung von JLabel und der Rahmen wird
mittels TiteldBorder um das Label ohne Text erzeugt. Natürlich verwalten
wir auch das obligate InfoLabel.
*/
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
//import javax.swing.event.*;
//import javax.swing.text.*;
//import javax.swing.border.*;
public class GroupBox extends JPanel
{
    /**
	 *
	 */
	private static final long serialVersionUID = -3832057085863419542L;

	//protected InfoLabel _oInfoLabel;
    public GroupBox()
    {
        super();
        //_oInfoLabel=new InfoLabel("");
    }

    public GroupBox(String s)
    {
        super();
        setText(s,null);
    }

    public void setText(String strText)
    {
      setText(strText,null);
    }

    public void setText(String strText,Font f)
    {
        //setBorder(strText==null ? BorderFactory.createEtchedBorder():BorderFactory.createTitledBorder(strText));
        javax.swing.border.TitledBorder tb=BorderFactory.createTitledBorder(strText==null ?"":strText);
        if (f != null)
          tb.setTitleFont(f);
        setBorder(tb);
        setLayout(new java.awt.GridLayout(1,0));
    }
    /*public InfoLabel InfoLabel()
    {
        return _oInfoLabel;
    }
    public void InfoLabel(InfoLabel oHyper)
    {
        _oInfoLabel=oHyper;
    }*/
}

