package All_Unlimited.Allgemein;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Stack;

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
public class AUxml
{
  java.io.FileOutputStream fos=null;
  Stack<String> VecTitel=new  Stack<String>();
  int iNr=0;

  public AUxml(String sFilename,String  sZS) // xml create
  {
    try
    {
      fos = new java.io.FileOutputStream(sFilename, false);
    }
    catch (java.io.IOException ex)
    {
            Static.printError("AUxml ("+sFilename+"): IOException - "+ex);
    }
    writeln("<?xml version=\"1.0\" encoding=\""+(sZS==null ? "iso-8859-1":sZS)+"\" ?>");
  }

  private void writeln(String s)
  {
    write(s+"\r\n");
  }

  private void write(String s)
  {
    try
    {
      fos.write(s.getBytes(Static.CP));
    }
    catch (java.io.IOException ex)
    {
            Static.printError("AUxml.writeln ("+s+"): IOException - "+ex);
    }
  }

  public void addTitel(String sT) // xml addTitel
  {
    writeSpace();
    VecTitel.push(sT);
    writeln('<'+sT+'>');
  }


  public void addTitel2(String sT,String s) // xml addTitel2
  {
    writeSpace();
    VecTitel.push(sT);
    writeln('<'+sT+" art=\""+s+"\">");
  }

  public void addTitel3(String sT,String s) // reserve64
  {
    writeSpace();
    VecTitel.push(sT);
    writeln('<'+sT+" "+s+">");
  }

  public void xml_Zeile(String sT) // reserve65
  {
    writeSpace();
    VecTitel.push(sT);
    iNr++;
    writeln('<'+sT+" number=\""+iNr+"\" type=\"N\" subtype=\"W\">");
  }

  public void okTitel() // xml okTitel
  {
    if (VecTitel.size() > 0)
    {
      String s=VecTitel.pop();
      writeSpace();
      writeln("</"+s+ ">");
    }
  }

  private String UmlauteErsetzen(String s)
  {
    return s.replaceAll("&", "&#38;").replaceAll("Ä", "&#196;").replaceAll("Ö", "&#214;").replaceAll("Ü", "&#220;")
        .replaceAll("ä", "&#228;").replaceAll("ö", "&#246;").replaceAll("ü", "&#252;").replaceAll("ß", "&#223;");
  }

  public void xml_Text(String s) // reserve79
  {
    writeSpace();
    writeln(s);
  }

  public void xml_String(String sT,String s) // xml string
  {
    writeSpace();
    writeln('<'+sT+'>'+UmlauteErsetzen(s)+"</"+sT+'>');
  }

  public void xml_Set(String sT,String s) // reserve66
  {
    writeSpace();
    writeln('<'+sT+"=\""+s+"\" />");
  }

  public void xml_Date(String sT,String s,Date dt) // xml date
  {
    writeSpace();
    writeln('<'+sT+" type=\"datum\">"+new SimpleDateFormat(s).format(dt)+"</"+sT+">");
  }

  public void xml_Time(String sT,String s,Date dt) // xml time
  {
    writeSpace();
    writeln('<'+sT+" type=\"uhrzeit\">"+new SimpleDateFormat(s).format(dt)+"</"+sT+'>');
  }

  public void xml_Int(String sT,int i) // xml int
  {
    writeSpace();
    writeln('<'+sT+'>'+i+"</"+sT+'>');
  }

  public void xml_double(String sT,double d) // reserve67
  {
    writeSpace();
    String s=new DecimalFormat("0.00").format(d).replace(',', '.');
    writeln('<'+sT+">"+s+"</"+sT+'>');
  }

  public void KZ(String sT,double d) // xml KZ
  {
    writeSpace();
    String s=new DecimalFormat("0.00").format(d).replace(',', '.');
    writeln('<'+sT+" type=\"kz\">"+s+"</"+sT+'>');
  }

  private void writeSpace()
  {
    for (int i=0;i<VecTitel.size();i++)
      write("   ");
  }

  public void free() // xml close
  {
    try
    {
      fos.close();
    }
    catch (java.io.IOException ex)
    {
            Static.printError("AUxml.free: IOException - "+ex);
    }

  }

}
