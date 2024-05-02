package All_Unlimited.Grunddefinition;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

//import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
//import javax.swing.JTabbedPane;
import javax.swing.text.DefaultCaret;

import All_Unlimited.Allgemein.Global;
import All_Unlimited.Allgemein.Static;

public class JavaKonsole extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static JavaKonsole Self=null;
	private JFrame FomInfo=null;
	private int iBegInfo=0;
	private int iBegErr=0;
	
	public static JavaKonsole get(boolean bInfo)
	{
		return get(bInfo,null);
	}
	
	public static JavaKonsole get(boolean bInfo,Global g)
	{
		if (Self==null) Self=new JavaKonsole();
		Static.EdtErrorKonsole.setFont(Global.fontJConsole);
		if (g!=null && Self.iBegInfo==0)
		{
			Self.iBegInfo=g.getBegriffAicS("Dialog", "Java-Konsole-Info");
			Self.iBegErr=g.getBegriffAicS("Dialog", "Java-Konsole-Error");
			if (Self.iBegErr>0)
			{
				Self.setTitle(g.getBegBez(Self.iBegErr));
				g.getFenster(Self, Self.iBegErr, false, 0, 0, 300, 600, 100);
			}
			if (Self.iBegInfo>0)
			{
				Self.FomInfo.setTitle(g.getBegBez(Self.iBegInfo));	
				g.getFenster(Self.FomInfo, Self.iBegInfo, false, 300, 0, 400, 700, 100);
			}
//			g.fixtestError("iBegErr="+Self.iBegErr+", iBegInfo="+Self.iBegInfo);
		}
		Self.setVisible(true);
		if (bInfo)
		{
			Static.EdtInfoKonsole.setFont(Global.fontJConsole);
			Self.FomInfo.setVisible(true);
		}
		return Self;
	}
	
	public static void setFenster(Global g)
	{
		if (Self != null)
		{
			if (Self.iBegErr>0) g.setFenster(Self, Self.iBegErr, 100);
			if (Self.iBegInfo>0) g.setFenster(Self.FomInfo, Self.iBegInfo, 100);
		}
	}
	
	public static void free()
	{
		if (Self != null)
		{
			Self.FomInfo.dispose();
			Self.dispose();
			Self=null;
			Global.fixInfoS("JavaKonsole.free");
		}
	}
	
	private JavaKonsole()
	{
		//Self=this;
		FomInfo=new JFrame("Java-Konsole");
		FomInfo.setTitle("Java-Konsole-Info");
		Static.EdtInfoKonsole.setEditable(false);
		DefaultCaret caret = (DefaultCaret) Static.EdtInfoKonsole.getCaret();
		caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
		FomInfo.getContentPane().add("Center", new JScrollPane(Static.EdtInfoKonsole));
		JPanel PnlButton=new JPanel(new FlowLayout(FlowLayout.RIGHT,2,2));
		JButton BtnIDel=new JButton("löschen");
		JButton BtnICopy=new JButton("kopieren");
		JButton BtnIEnd=new JButton("schließen");
		ActionListener AL = new ActionListener()
        {
          public void actionPerformed(ActionEvent e)
          {
            String s = e.getActionCommand();
            if (s.equals("Idel"))
            	Static.StreamInfo.clear();
            else if (s.equals("Icopy"))
            {
            	Static.EdtInfoKonsole.selectAll();
            	Static.EdtInfoKonsole.copy();
            	Static.EdtInfoKonsole.setSelectionStart(Static.EdtInfoKonsole.getSelectionEnd());
            }
            else if (s.equals("Iend"))
            	FomInfo.setVisible(false);
            else if (s.equals("Edel"))
            	Static.StreamError.clear();
            else if (s.equals("Ecopy"))
            {
            	Static.EdtErrorKonsole.selectAll();
            	Static.EdtErrorKonsole.copy();
            	Static.EdtErrorKonsole.setSelectionStart(Static.EdtErrorKonsole.getSelectionEnd());
            }
            else if (s.equals("Eend"))
            	setVisible(false);
          }
        };
		Global.setAction(BtnIDel,"Idel",AL);
		Global.setAction(BtnICopy,"Icopy",AL);
		Global.setAction(BtnIEnd,"Iend",AL);
		PnlButton.add(BtnIDel);
		PnlButton.add(BtnICopy);
		PnlButton.add(BtnIEnd);
		FomInfo.getContentPane().add("South", PnlButton);
		FomInfo.setLocation(300, 0);
		FomInfo.setSize(400,700);
		
		// Error-Konsole
		setTitle("Java-Konsole-Error");
		Static.EdtErrorKonsole.setEditable(false);		
		getContentPane().add("Center", new JScrollPane(Static.EdtErrorKonsole));
		PnlButton=new JPanel(new FlowLayout(FlowLayout.RIGHT,2,2));
		JButton BtnEDel=new JButton("löschen");
		JButton BtnECopy=new JButton("kopieren");
		JButton BtnEEnd=new JButton("schließen");		
		Global.setAction(BtnEDel,"Edel",AL);
		Global.setAction(BtnECopy,"Ecopy",AL);
		Global.setAction(BtnEEnd,"Eend",AL);
		PnlButton.add(BtnEDel);
		PnlButton.add(BtnECopy);
		PnlButton.add(BtnEEnd);
		getContentPane().add("South", PnlButton);
		setSize(500,600);
	}

}
