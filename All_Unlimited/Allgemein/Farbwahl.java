/*
    All_Unlimited-Allgemein-Farbe.java

    NOTE: This file is a generated file.
          Do not modify it by hand!
*/

package All_Unlimited.Allgemein;

// add your custom import statements here
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.colorchooser.AbstractColorChooserPanel;
import java.awt.FlowLayout;

public class Farbwahl extends javax.swing.JDialog
{
	/**
	 *
	 */
	private static final long serialVersionUID = -8958787193780457489L;
	public Farbwahl(JFrame frame, Global glob)
	{
		super(frame,glob.getBegriffS("Dialog","Farbwahl"),true);
		g=glob;
		ColorPanel = colorchooser.getChooserPanels()[1];
		BtnOk = g.getButton("Ok");
		BtnAbbruch = g.getButton("Abbruch");
		BtnVorschau = g.getButton("show");
		g.removeHover(BtnVorschau); 
		JPanel PnlCenter = new JPanel(new BorderLayout(2,2));
		JPanel Pnl2South = new JPanel(new FlowLayout(FlowLayout.RIGHT,2,2));

		getContentPane().setLayout(new BorderLayout(2,2));

		PnlCenter.add("Center",ColorPanel);
		PnlCenter.add("South",BtnVorschau);
		Pnl2South.add(BtnOk);
		Pnl2South.add(BtnAbbruch);
		getContentPane().add("Center",PnlCenter);
		getContentPane().add("South",Pnl2South);
		//setSize(350,300);
                pack();
		//setResizable(false);


		BtnOk.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ev)
			{
				color=colorchooser.getColor();
				setVisible(false);
			}
		});

		BtnAbbruch.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ev)
			{
				setVisible(false);
			}
		});

		BtnVorschau.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ev)
			{
                          //g.setBack(BtnVorschau,color);
                          //g.testInfo("BtnVorschau gedrückt");
				BtnVorschau.setBackground(colorchooser.getColor());
			}
		});
	}

	public Color showDialog(Color col)
	{
		color=col;
		colorchooser.setColor(color);
                g.setBack(BtnVorschau,color);
		//BtnVorschau.setBackground(color);
		setVisible(true);
		return(color);
	}

	// add your data members here
	Global g;
	AbstractColorChooserPanel ColorPanel;
	JColorChooser colorchooser = new JColorChooser();
	Color color;

	JButton BtnOk;
	JButton BtnAbbruch;
	JButton BtnVorschau;
}

