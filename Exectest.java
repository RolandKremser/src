
import java.awt.*;
import java.awt.event.*;
import java.io.*;

import javax.swing.*;

public class Exectest extends javax.swing.JFrame {

  /**
   * @param args
   */
  public static void main(String[] args) {
    // Automatisch erstellter Methoden-Stub
    new Exectest();
  }
  
  private JTextArea EdtText=new JTextArea();
  private JTextArea EdtResult=new JTextArea();
  
  private Exectest()
  {
    //JFrame Fom=new JFrame("Exec-Test");
    JButton BtnSave=new JButton("Save");
    BtnSave.addActionListener(new ActionListener()
    {
    	public void actionPerformed(ActionEvent ev)
    	{
    	  save();
    	}
    });
    JButton BtnExec=new JButton("Exec");
    BtnExec.addActionListener(new ActionListener()
    {
    	public void actionPerformed(ActionEvent ev)
    	{
    	  rt_exec();
    	}
    });
    JButton BtnBeenden=new JButton("Beenden");
    BtnBeenden.addActionListener(new ActionListener()
    {
    	public void actionPerformed(ActionEvent ev)
    	{
                System.exit(0);
    	}
    });
    JPanel PnlS=new JPanel(new GridLayout());
    PnlS.add(BtnSave);
    PnlS.add(BtnExec);
    PnlS.add(BtnBeenden);
    EdtText.setBackground(Color.pink.brighter());
    getContentPane().add("North",EdtText);
    getContentPane().add("Center",EdtResult);
    getContentPane().add("South",PnlS);
    setVisible(true);
    pack();
  }
  
  private void rt_exec()
  {
    Runtime rt=Runtime.getRuntime();
    try
    {
      java.io.InputStream ops=rt.exec(EdtText.getText()).getInputStream();
      BufferedReader reader = new BufferedReader( new InputStreamReader( ops ));
      String s="";
      String sZeile;
      int i=0;
      while((sZeile=reader.readLine())!=null)
      {
	i++;
	s+=VorNull(i)+":"+sZeile+"\n";
      }
      EdtResult.setText(s);
    }
    catch(Exception e)
    {
      EdtResult.setText(""+e);
    }
  }
  
  public static String VorNull(int i)
  {
  	return (i<10 ?"0":"")+i;
  }
  
  private void save()
  {
    EdtResult.setText("");
    File test=new File("c:\\test0512.sql");
    FileWriter fw=null;
    Runtime rt=Runtime.getRuntime();
    try {
      fw = new FileWriter(test);
      //fw.close(); 
    }
    catch (IOException ex) {EdtResult.setText(""+ex);}
    try{
      Process child = rt.exec("\"c:\\Program Files\\MySQL51\\bin\\mysqldump.exe\" --single-transaction -u root dvh");
  
      InputStream in = child.getInputStream();
      InputStreamReader xx = new InputStreamReader(in,"latin1");
      char[] chars=new char[512];
      while((xx.read(chars))>0)
      {
	fw.write(chars);
      }
      fw.close(); 
    }
    catch (IOException ex) { EdtResult.setText(EdtResult.getText()+"\n"+ex); }
    
    /*try {
      Process proc = rt.exec("\"c:\\Program Files\\MySQL51\\bin\\mysqldump.exe\" --single-transaction -u root dvh");
      InputStream in = proc.getInputStream();
      InputStreamReader read = new InputStreamReader(in,"latin1");
      BufferedReader br = new BufferedReader(read);
      BufferedWriter bw = new BufferedWriter(new FileWriter("c:\\testxx.sql",true));
      String line=null;
      StringBuffer buffer = new StringBuffer();
      while((line=br.readLine())!=null){
      buffer.append(line+"\n");
      }
      String toWrite = buffer.toString();
      bw.write(toWrite);
      bw.close();
      br.close();
      } catch (IOException e) {
      e.printStackTrace();
      } */
  }

}
