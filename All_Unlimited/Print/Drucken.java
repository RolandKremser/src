/*
    All_Unlimited-Print-Drucken.java

    NOTE: This file is a generated file.
          Do not modify it by hand!
*/

package All_Unlimited.Print;

public class Drucken
{
	/**
	 *
	 */
	private static final long serialVersionUID = 1218162646603798260L;
	// Druck
	public static final int cstPntUnterschrift=	   0x01;
	public static final int cstPntKeinDrucktitel=  0x02;
	public static final int cstPntTagesanzeige=	   0x04;
	public static final int cstPntKeinKopfFuss=	   0x08;
	public static final int cstPntStammLinks=	   0x10;
	public static final int cstPntKeinStammtitel=  0x20;
	public static final int cstPntKeineGruppierung=0x40;
	public static final int cstPntQuerformat=  	   0x80;
	public static final int cstPntPers=  	  	  0x100; // war cstPntAuchHS
	public static final int cstPntDrucktitelLinks=0x200;
    //public static final int cstPntNeuerDruck=	  0x400; // nicht mehr nötig, sollte überall gesetzt sein
    public static final int cstPntSchicht=        0x800; // war cstPntBewegung
    public static final int cstPntPeriode=	     0x1000;
    public static final int cstPntSeitenumbruch= 0x2000;
    public static final int cstPntDirekt=        0x4000;
    public static final int cstPntUseSync=       0x8000;

        public static final int cstPntZeitart=        0x70000;
        public static final int cstPntZaTag=          0x10000;
        public static final int cstPntZaWoche=        0x20000;
        public static final int cstPntZaMonat=        0x30000;
        public static final int cstPntZaQuartal=      0x40000;
        public static final int cstPntZaJahr=         0x50000;

        public static final int cstPntNewYear=        0x80000;
       //public static final int cstDruckerauswahl=  0x100000; // bit aus DruckFrage
        public static final int cstPntKnopf=  	     0x200000;
        public static final int cstPntExport=  	     0x400000;
        public static final int cstPntHtml=          0x800000;
        public static final int cstPntKeinProg=     0x1000000;
        //public static final int cstDruckerauswahl2= 0x2000000; // nicht mehr in Verwendung
        public static final int cstPntEMail       = 0x2000000;
        public static final int cstPntFarbe=        0x4000000; // bit Farbe aus DruckFrage
        public static final int cstPntKeinZRtitel=  0x8000000;
        public static final int cstPntPdfSave=     0x10000000; // war cstTranslate
        public static final int cstPntMenge=       0x20000000;
        public static final int cstPntBewZR=       0x40000000;
        
        public static final long cstPntNurSumme=  0x100000000l;
        public static final long cstPntKeineSumme=0x200000000l;
        public static final long cstPntED		 =0x400000000l;

	// *************
    // * Abschnitt *
    // *************
	public static final int cstUeberschrift=		0x0001;
	public static final int cstkeineHauptsumme=		0x0002; // war cstUnterschrift
	public static final int cstGruppenRed=		    0x0004; // keine doppelten Gruppennamen war cstTitelDarunter
	//public static final int cstErsteFett=			0x0008; // nicht mehr in Verwendung: 5
	//public static final int cstEigZeilen=			0x0010; // nicht mehr in Verwendung:42
	//public static final int cstKeinTitel=			0x0020; // nicht mehr in Verwendung:28
	public static final int cstGesamtsumme=			0x0040;

    public static final int cstRN=                  0x0480;
    public static final int cstLinks=			    0x0000;
	public static final int cstRechts1=			    0x0080;
    public static final int cstNurEMail=	        0x0100; // war cstKeinDrucktitel
	public static final int cstLtAic=			    0x0200; // Planung laut Aic statt Name // war cstTagesanzeige
    public static final int cstNeben=		        0x0400;
    public static final int cstRechts2=				0x0480;
    public static final int cstNormal=				0x0000;
	public static final int cstDiagramm=			0x0800; // nicht mehr in Verwendung
        //public static final int cstUeberschriftLinks=	        0x1000; // nicht mehr in Verwendung
	public static final int cstKeinAbstand=			0x2000;
        public static final int cstKeineSumme=                  0x4000;
        public static final int cstIstUeberschrift=             0x8000;
        public static final int cstFixRaster=               0x00010000;
        public static final int cstFixPos=                  0x00020000;
        public static final int cstStrecken=                0x00040000;
        public static final int cstNichtDrucken=            0x00080000;
        public static final int cstImmer=                   0x00100000; // war cstZeilenende
        public static final int cstBlocksatz=               0x00400000;
        public static final int cstLeere=                   0x00800000;
        public static final int cstBedST=                   0x01000000; // bedingter Spaltentitel (nur auf neuer Seite)
        public static final int cstTest=                    0x02000000;
        public static final int cstNeueSeite=               0x04000000;
        public static final int cstNurSummeA=               0x08000000;
        public static final int cstTestfill=                0x10000000;
        public static final int cstNurMitTitel=             0x20000000;
        public static final int cstColor=                   0x40000000;

        public static final int cstBedingung=               0x00008800;
        public static final int cstEMail_A=                 0x00020800; // E-Mail-Adressen der Empfänger
        public static final int cstEMail_D=                 0x00028000; // E-Mail-Daten für Versand
        public static final int cstEMail_S=                 0x00028800; // E-Mail-Signatur
        public static final int cstSchicht2=                0x00200000;
        public static final int cstHtmlDirekt=              0x00200800;
        public static final int cstSchichtTMZ=              0x00208000;
        public static final int cstPDF_Save=                0x00208800;
        public static final int cstPDF_Direkt=				0x00220000;
        public static final int cstArt=                     cstDiagramm+cstIstUeberschrift+cstFixPos+cstSchicht2;
        
        

	// DruckFrage
	public static final int cstSeitenumbruch=		0x0001; // 0x
    public static final int cstPAUSE=               0x0002; // 1 Pb: Pause berücksichtigen
    public static final int cstMEMO2=               0x0004; // 2 Pb: Memo in 2. Zeile
	public static final int cstNurSumme=			0x0008; // 3x
	public static final int cstAIO=			        0x0010; // 4 Pb: Alle Gruppen in eine Planung zusammenfassen
	public static final int cstSeitenvorschau=		0x0020; // 5x
	public static final int cstVB=		            0x0040; // 6 Pb: von-bis als Text anzeigen
	public static final int cstStammLinks=			0x0080; // 7x
	public static final int cstTMZ=                 0x0100; // 8 Pb: Tagesmusterzählung
	public static final int cstLetzteGruppeWeg=		0x0200; // 9x
	public static final int cstPAZ=                 0x0400; //10 Pb: Arbeitszeit
    public static final int cstIST=                 0x0800; //11 Pb: Ist-Zeit (inkl Urlaub, Krank,...)
    public static final int cstSOLL=                0x1000; //12 Pb: Soll-Zeit
    public static final int cstDIFF=                0x2000; //13 Pb: Differenz Ist-Soll
    //public static final int cstKeineSumme=        0x4000; //14x von Abschnitt
    public static final int cstGruppiert=           0x8000; //15x
    public static final int cstCboDruck=		   0x00000;
    public static final int cstCboAbfrage=		   0x10000;
    public static final int cstCboHS=		       0x20000;
    public static final int cstCboRest=		       0x30000;
    public static final int cstCboArt=		       0x30000; //16 & 17
    public static final int cstPPS=                0x40000; //18 Pb: prod. Planstunden
    public static final int cstPeriode=		       0x80000; //19x
    public static final int cstDruckerauswahl=	  0x100000; //20x
    public static final int cstAlleSpalten=	      0x200000; //21x
    public static final int cstSeiteProGruppe=    0x400000; //22x
    public static final int cstART_H=            0x1800000; //23 & 24
    public static final int cstH_DEZ=             0x800000; //23x
    public static final int cstH_MIN=            0x1000000; //24x
//    public static final int cstED=	             0x2000000; //25 eigener Druck // war cstDruckerauswahl2
    public static final int cstDFFarbe=          0x4000000; //26x
    public static final int cstPb=               0x8000000; //27n Planungsbits: bestimmt ob bit 8,10-13 verwendet wird
    public static final int cstPDF_Art=         0x30000000; //28 & 29 PDF-Show, EMail oder PDF-Save
    public static final int cstPDF=             0x10000000; // PDF wird als Datei angelegt
    public static final int cstEMail=           0x20000000; // als E-Mail versendet
    public static final int cstPDF_DB=          0x30000000; // PDF wird in Datenbank gespeichert
        // Raster
     // public static int cstSperrflaecheFett=         0x0001;//alt
     // public static int cstSperrflaecheLtLabel=      0x0002;//alt
        public static final int cstAllgemeinHorizontallinien=0x0004;
        public static final int cstAllgemeinVertikallinien=  0x0008;
        //public static int cstEinzelsummenHervorheben=  0x0010;//alt
        public static final int cstStandard=                 0x0020;
        public static final int cstRahmen=                   0x0040;
        public static final int cstDoppelt=                  0x0080;
        public static final int cstRahmen2=                  0x0100;
        public static final int cstLU3=                      0x0200;
        public static final int cstLO1=                      0x0400;
        public static final int cstLU1=                      0x0800;
        public static final int cstLO2=                      0x1000;
        public static final int cstLU2=                      0x2000;
        public static final int cstHG_Farbe=                 0x4000;
        public static final int cstSpaltenAbstand=           0x8000;
        public static final int cstKartei=                  0x10000;

        // Layout
        public static final int cstLayQuer = 0x0001;
        public static final int cstLayA3   = 0x0002;
        public static final int cstLayStd  = 0x0004;
        public static final int cstFarbe   = 0x0008;
        public static final int cstLayFK   = 0x0010;
	public static final int cstLayFF   = 0x0020;

        // Zeile
        public static final int cstLinie = 0x0001;
        public static final int cstKopf  = 0x0002;
        public static final int cstFuss  = 0x0004;
        public static final int cstKF = cstKopf+cstFuss;

        //public static boolean bAktiv=false;

}

