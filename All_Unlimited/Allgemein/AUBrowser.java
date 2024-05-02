package All_Unlimited.Allgemein;

import javax.swing.*;
import javax.swing.event.*;
import java.io.*;
import java.net.*;

/**
 * <p>Überschrift: All Unlimited</p>
 *
 * <p>Beschreibung: </p>
 *
 * <p>Copyright: Copyright (c) 2010</p>
 *
 * <p>Organisation: DVH</p>
 *
 * @author Roland Kremser
 * @version 5.10
 */
public class AUBrowser extends JEditorPane implements HyperlinkListener
{
  private static final long serialVersionUID = 2740918275781785106L;

  public AUBrowser(String sURL)
  {
    setEditable(false);
    addHyperlinkListener(this);
    try
    {
      setPage(new URL(sURL));
    }
    catch (IOException e) { e.printStackTrace(); }
  }

  public void hyperlinkUpdate(HyperlinkEvent event)
  {
    HyperlinkEvent.EventType typ=event.getEventType();
    if (typ==HyperlinkEvent.EventType.ACTIVATED)
    {
      try
      {
        setPage(event.getURL());
      }
      catch(IOException e) {}
    }
  }
}
