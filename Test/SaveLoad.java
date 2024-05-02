package Test;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.zip.ZipEntry;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import All_Unlimited.Allgemein.AUZip;
import All_Unlimited.Allgemein.Eingabe.AUCheckBox;
import All_Unlimited.Allgemein.Eingabe.Zahl;

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
public class SaveLoad extends JFrame
{
  /**
	 * 
	 */
	private static final long serialVersionUID = 6289208635317939215L;
JTextArea Edt=new JTextArea();
  AUCheckBox Cbx=new AUCheckBox();
  Zahl num=new Zahl(0);

  public SaveLoad()
  {
    num.setMax(new Integer(9999));
    JButton BtnSave=new JButton("Save");
    JButton BtnLoad=new JButton("Load");
    JPanel Pnl1=new JPanel(new GridLayout());
    JPanel Pnl=new JPanel(new GridLayout());
    Pnl.add(BtnSave);
    Pnl.add(BtnLoad);
    BtnSave.addActionListener(new ActionListener()
    {
        public void actionPerformed(ActionEvent ev)
        {
          Save();
        }
    });
    BtnLoad.addActionListener(new ActionListener()
    {
        public void actionPerformed(ActionEvent ev)
        {
          Load();
        }
    });
    Pnl1.add(Cbx);
    Pnl1.add(num);
    getContentPane().add("North",Pnl1);
    getContentPane().add("Center",Edt);
    getContentPane().add("South",Pnl);
    setSize(300,300);
    setVisible(true);
  }

  private void Load()
  {
    Edt.setText("");
    String sFile="N:\\Test\\Tschechisch\\Test.zip";
    AUZip Zip = new AUZip(sFile,true);
    Zip.getNextEntry();
    Edt.setText(Zip.ReadString());
    Cbx.setSelected(Zip.ReadBoolean().booleanValue());
    num.setValue(Zip.ReadInteger().intValue());
    Zip.close();
  }

  private void Save()
  {
    System.out.println(Edt.getText());
    String sFile="N:\\Test\\Tschechisch\\Test.zip";
    AUZip Zip = new AUZip(sFile,false);
    Zip.putNextEntry(new ZipEntry("test.up"));
    Zip.SaveString(Edt.getText());
    Zip.SaveBoolean(Cbx.isSelected());
    Zip.SaveInteger(num.getInteger());
    Zip.closeEntry();
    Zip.close();
  }

}
