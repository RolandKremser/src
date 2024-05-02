/*     */ package jclass.cell.renderers;
/*     */ 
/*     */ import java.awt.Dimension;
/*     */ import java.awt.FontMetrics;
/*     */ import java.awt.Graphics;
/*     */ import java.awt.Rectangle;
/*     */ import java.util.StringTokenizer;
/*     */ import jclass.cell.CellInfo;
/*     */ import jclass.cell.CellRenderer;
/*     */ import jclass.util.JCWordWrap;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class WordWrapCellRenderer
/*     */   implements CellRenderer
/*     */ {
/*     */   public void draw(Graphics paramGraphics, CellInfo paramCellInfo, Object paramObject, boolean paramBoolean)
/*     */   {
/*  82 */     if (paramObject == null)
/*  83 */       return;
/*  84 */     String str1 = paramObject.toString();
/*     */     
/*  86 */     if ((str1 == null) || (str1.length() == 0)) {
/*  87 */       return;
/*     */     }
/*  89 */     FontMetrics localFontMetrics = paramCellInfo.getFontMetrics();
/*  90 */     Rectangle localRectangle = paramCellInfo.getDrawingArea();
/*  91 */     paramGraphics.setFont(paramCellInfo.getFont());
/*  92 */     paramGraphics.setColor(paramBoolean ? paramCellInfo.getSelectedForeground() : 
/*  93 */       paramCellInfo.getForeground());
/*     */     
/*  95 */     str1 = JCWordWrap.wrapText(JCWordWrap.replace(str1, "\n", " "), 
/*  96 */       localFontMetrics, localRectangle.width, "\n", paramCellInfo.getHorizontalAlignment() != 2);
/*     */     
/*  98 */     localFontMetrics.stringWidth(str1);
/*  99 */     str1.indexOf('\n');
/*     */     int i;
/* 101 */     int j; int k; if (str1.indexOf('\n') != -1) {
/* 102 */       i = localFontMetrics.getHeight();
/* 103 */       j = 0;k = i - 4;
/*     */       
/* 105 */       int m = paramCellInfo.getVerticalAlignment();
/* 106 */       int n = getHeight(localFontMetrics, str1);
/* 107 */       if (m == 1) {
/* 108 */         k += (localRectangle.height - n) / 2;
/* 109 */       } else if (m == 2) {
/* 110 */         k += localRectangle.height - n;
/*     */       }
/* 112 */       m = paramCellInfo.getHorizontalAlignment();
/*     */       
/* 114 */       StringTokenizer localStringTokenizer = new StringTokenizer(str1, "\n", false);
/*     */       
/* 116 */       while (localStringTokenizer.hasMoreTokens()) {
/* 117 */         String str2 = localStringTokenizer.nextToken().trim();
/* 118 */         if (m == 1) {
/* 119 */           j = (localRectangle.width - localFontMetrics.stringWidth(str2)) / 2;
/* 120 */         } else if (m == 2) {
/* 121 */           j = localRectangle.width - localFontMetrics.stringWidth(str2);
/*     */         }
/* 123 */         paramGraphics.drawString(str2, j, k);
/* 124 */         k += i;
/*     */       }
/*     */     }
/*     */     else {
/* 128 */       i = 0;j = localFontMetrics.getHeight() - 4;
/*     */       
/*     */ 
/* 131 */       k = paramCellInfo.getHorizontalAlignment();
/* 132 */       if (k == 2) {
/* 133 */         i += localRectangle.width - localFontMetrics.stringWidth(str1);
/* 134 */       } else if (k == 1) {
/* 135 */         i += (localRectangle.width - localFontMetrics.stringWidth(str1)) / 2;
/*     */       }
/*     */       
/* 138 */       k = paramCellInfo.getVerticalAlignment();
/* 139 */       if (k == 1) {
/* 140 */         j += (localRectangle.height - localFontMetrics.getHeight()) / 2;
/* 141 */       } else if (k == 2) {
/* 142 */         j += localRectangle.height - localFontMetrics.getHeight();
/*     */       }
/* 144 */       paramGraphics.drawString(str1, i, j);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public int getHeight(FontMetrics paramFontMetrics, String paramString)
/*     */   {
/* 152 */     if ((paramString == null) || (paramString.length() == 0))
/* 153 */       return 0;
/* 154 */     int i = 1;
/* 155 */     for (int j = 0; j < paramString.length(); j++)
/* 156 */       if (paramString.charAt(j) == '\n') i++;
/* 157 */     return paramFontMetrics.getHeight() * i;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public int getWidth(FontMetrics paramFontMetrics, String paramString)
/*     */   {
/* 164 */     if ((paramString == null) || (paramString.length() == 0)) {
/* 165 */       return 0;
/*     */     }
/*     */     
/* 168 */     if (paramString.indexOf('\n') != -1) {
/* 169 */       int i = 0;int k = 0;
/* 170 */       int j; for (; (j = paramString.indexOf('\n', i)) != -1; i = j + 1)
/* 171 */         k = Math.max(k, paramFontMetrics.stringWidth(paramString.substring(i, j)));
/* 172 */       return Math.max(k, paramFontMetrics.stringWidth(paramString.substring(i, paramString.length())));
/*     */     }
/*     */     
/* 175 */     return paramFontMetrics.stringWidth(paramString);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Dimension getPreferredSize(CellInfo paramCellInfo, Object paramObject)
/*     */   {
/* 185 */     if (paramObject == null) {
/* 186 */       return null;
/*     */     }
/* 188 */     String str = paramObject.toString();
/*     */     
/* 190 */     if ((str == null) || (str.length() == 0)) {
/* 191 */       return new Dimension(0, 0);
/*     */     }
/* 193 */     FontMetrics localFontMetrics = paramCellInfo.getFontMetrics();
/* 194 */     int i = paramCellInfo.getDrawingArea().width;
/*     */     
/* 196 */     str = JCWordWrap.wrapText(JCWordWrap.replace(str, "\n", " "), 
/* 197 */       localFontMetrics, i, "\n", paramCellInfo.getHorizontalAlignment() != 2);
/*     */     
/* 199 */     return new Dimension(getWidth(localFontMetrics, str), getHeight(localFontMetrics, str));
/*     */   }
/*     */ }


/* Location:              D:\frei\JAVA\decompile\jar\All.jar!\jclass\cell\renderers\WordWrapCellRenderer.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */