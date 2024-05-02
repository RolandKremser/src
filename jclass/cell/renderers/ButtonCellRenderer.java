/*     */ package jclass.cell.renderers;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.awt.Component;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.Font;
/*     */ import java.awt.FontMetrics;
/*     */ import java.awt.Graphics;
/*     */ import java.awt.Image;
/*     */ import java.awt.Insets;
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.Toolkit;
/*     */ import java.util.Vector;
/*     */ import jclass.cell.CellInfo;
/*     */ import jclass.cell.CellRenderer;
/*     */ import jclass.cell.Utilities;
/*     */ import jclass.util.JCString;
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
/*     */ public class ButtonCellRenderer
/*     */   implements CellRenderer
/*     */ {
/*  73 */   protected boolean enabled = true;
/*  74 */   protected int border_style = 9;
/*     */   protected Object arm_value;
/*  76 */   protected boolean armed = false;
/*     */   
/*     */   protected int highlight;
/*  79 */   protected int border = 2;
/*     */   
/*  81 */   protected int arm_offset = 1;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void draw(Graphics paramGraphics, CellInfo paramCellInfo, Object paramObject, boolean paramBoolean)
/*     */   {
/*  92 */     Object localObject = null;
/*     */     
/*     */ 
/*     */ 
/*  96 */     Rectangle localRectangle1 = paramCellInfo.getDrawingArea();
/*  97 */     Utilities.translateToWholeCell(paramGraphics, paramCellInfo, localRectangle1);
/*     */     
/*  99 */     paramCellInfo.getFontMetrics();
/*     */     
/* 101 */     paramGraphics.setFont(paramCellInfo.getFont());
/* 102 */     paramGraphics.setColor(paramBoolean ? paramCellInfo.getSelectedForeground() : paramCellInfo.getForeground());
/* 103 */     int i = paramCellInfo.getHorizontalAlignment();
/*     */     
/* 105 */     Utilities.drawBorder(paramGraphics, this.border_style, this.border, 
/* 106 */       this.highlight, this.highlight, 
/* 107 */       localRectangle1.width - 2 * this.highlight, 
/* 108 */       localRectangle1.height - 2 * this.highlight, 
/* 109 */       paramCellInfo.getBackground(), paramCellInfo.getForeground());
/*     */     
/* 111 */     if ((this.armed) && (this.arm_value != null)) {
/* 112 */       paramGraphics.translate(this.arm_offset, this.arm_offset);
/* 113 */       localObject = this.arm_value;
/*     */     }
/*     */     else {
/* 116 */       if (this.armed) {
/* 117 */         paramGraphics.translate(this.arm_offset, this.arm_offset);
/*     */       }
/* 119 */       localObject = paramObject;
/*     */     }
/*     */     
/* 122 */     if (this.armed) {
/* 123 */       paramGraphics.translate(-this.arm_offset, -this.arm_offset);
/*     */     }
/*     */     
/* 126 */     Insets localInsets1 = paramCellInfo.getBorderInsets();
/* 127 */     Insets localInsets2 = paramCellInfo.getMarginInsets();
/* 128 */     Rectangle localRectangle2 = paramCellInfo.getDrawingArea();
/*     */     
/*     */ 
/*     */ 
/* 132 */     localRectangle2.setLocation(localInsets1.right + localInsets2.right, 
/* 133 */       localInsets1.top + localInsets2.top);
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 140 */     if (!this.enabled) {
/* 141 */       Color localColor = paramGraphics.getColor();
/* 142 */       paramGraphics.translate(1, 1);
/* 143 */       paramGraphics.setColor(Color.white);
/* 144 */       draw_label(null, paramGraphics, localObject, i, paramCellInfo.getVerticalAlignment(), 
/* 145 */         localRectangle2);
/* 146 */       paramGraphics.translate(-1, -1);
/* 147 */       paramGraphics.setColor(paramCellInfo.getBackground().darker());
/* 148 */       draw_label(null, paramGraphics, localObject, i, paramCellInfo.getVerticalAlignment(), 
/* 149 */         localRectangle2);
/* 150 */       paramGraphics.setColor(localColor);
/*     */     }
/*     */     else {
/* 153 */       draw_label(null, paramGraphics, localObject, i, paramCellInfo.getVerticalAlignment(), 
/* 154 */         localRectangle2);
/*     */     }
/*     */     
/* 157 */     Utilities.restoreFromWholeCell(paramGraphics, paramCellInfo, localRectangle1);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Dimension getPreferredSize(CellInfo paramCellInfo, Object paramObject)
/*     */   {
/* 167 */     int i = getWidth(paramObject, null, paramCellInfo.getFont());
/* 168 */     int j = getHeight(paramObject, null, paramCellInfo.getFont());
/* 169 */     return new Dimension(i + paramCellInfo.getMarginInsets().right * 2 + 
/* 170 */       paramCellInfo.getBorderInsets().right * 2, 
/* 171 */       j + paramCellInfo.getMarginInsets().top * 2 + 
/* 172 */       paramCellInfo.getBorderInsets().top * 2);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static void draw_label(Component paramComponent, Graphics paramGraphics, Object paramObject, int paramInt1, int paramInt2, Rectangle paramRectangle)
/*     */   {
/* 182 */     if (paramObject == null) {
/* 183 */       return;
/*     */     }
/* 185 */     if ((paramObject instanceof JCString)) {
/* 186 */       ((JCString)paramObject).draw(paramComponent, paramGraphics, paramRectangle, paramInt1);
/* 187 */       return;
/*     */     }
/* 189 */     if ((paramObject instanceof Image)) {
/* 190 */       paramGraphics.drawImage((Image)paramObject, paramRectangle.x, paramRectangle.y, null);
/* 191 */       return;
/*     */     }
/*     */     
/* 194 */     String str1 = paramObject.toString();
			  String str2;
/* 195 */     if ((str1 == null) || (str1.length() == 0)) {
/* 196 */       return;
/*     */     }
/* 198 */     FontMetrics localFontMetrics = paramGraphics.getFontMetrics();
/* 199 */     Font localFont = paramGraphics.getFont();
/* 200 */     int i = localFontMetrics.getHeight();int j = 0;int k = i - localFontMetrics.getAscent();
/* 201 */     int m = paramRectangle.y + i - k;
/* 202 */     int n = paramInt2 == 0 ? 0 : getHeight(str1, paramComponent, localFont);
/*     */     
/* 204 */     if (paramInt2 == 2) {
/* 205 */       m += paramRectangle.height - n;
/* 206 */     } else if (paramInt2 == 1) {
/* 207 */       m += (paramRectangle.height - n) / 2;
/*     */     }
/*     */     
/* 210 */     if (str1.indexOf('\n') != -1) {
/* 211 */       int i1 = 0;
/*     */       int i2;
/* 213 */       for (; (i2 = str1.indexOf('\n', i1)) != -1; 
/* 214 */           m += i) {
/* 215 */         str2 = str1.substring(i1, i2);
/* 216 */         if (paramInt1 == 1) {
/* 217 */           j = (paramRectangle.width - stringWidth(localFontMetrics, localFont, str2)) / 2;
/* 218 */         } else if (paramInt1 == 2)
/* 219 */           j = paramRectangle.width - stringWidth(localFontMetrics, localFont, str2);
/* 220 */         paramGraphics.drawString(str2, paramRectangle.x + j, m);i1 = i2 + 1;
/*     */       }
/* 222 */       str2 = str1.substring(i1, str1.length());
/* 223 */       if (paramInt1 == 1) {
/* 224 */         j = (paramRectangle.width - stringWidth(localFontMetrics, localFont, str2)) / 2;
/* 225 */       } else if (paramInt1 == 2)
/* 226 */         j = paramRectangle.width - stringWidth(localFontMetrics, localFont, str2);
/* 227 */       paramGraphics.drawString(str2, paramRectangle.x + j, m);
/*     */     }
/*     */     else {
/* 230 */       if (paramInt1 == 1) {
/* 231 */         j = (paramRectangle.width - stringWidth(localFontMetrics, localFont, str1)) / 2;
/* 232 */       } else if (paramInt1 == 2)
/* 233 */         j = paramRectangle.width - stringWidth(localFontMetrics, localFont, str1);
/* 234 */       paramGraphics.drawString(str1, paramRectangle.x + j, m);
/*     */     }
/*     */   }
/*     */   
/*     */   public static int getWidth(Object paramObject, Component paramComponent)
/*     */   {
/* 240 */     return getWidth(paramObject, paramComponent, paramComponent.getFont());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public static int getWidth(Object paramObject, Component paramComponent, Font paramFont)
/*     */   {
/* 247 */     if (paramObject == null)
/* 248 */       return 0;
/* 249 */     if ((paramObject instanceof Image))
/* 250 */       return ((Image)paramObject).getWidth(null);
/* 251 */     if ((paramObject instanceof JCString))
/* 252 */       return ((JCString)paramObject).getWidth(paramComponent, paramFont);
/* 253 */     if ((paramObject instanceof Vector)) {
/* 254 */       int i = 0;
/* 255 */       for (int j = 0; j < ((Vector)paramObject).size(); j++)
/* 256 */         i += getWidth(((Vector)paramObject).elementAt(j), paramComponent);
/* 257 */       return i;
/*     */     }
/*     */     
/* 260 */     String str = paramObject.toString();
/* 261 */     if ((str == null) || (str.length() == 0)) {
/* 262 */       return 0;
/*     */     }
/* 264 */     if ((paramComponent == null) || (paramComponent.getToolkit() == null) || (paramFont == null)) return 0;
/* 265 */     FontMetrics localFontMetrics = paramComponent.getToolkit().getFontMetrics(paramFont);
/*     */     
/*     */ 
/* 268 */     if (str.indexOf('\n') != -1) {
/* 269 */       int k = 0;int n = 0;
/* 270 */       int m; for (; (m = str.indexOf('\n', k)) != -1; 
/* 271 */           k = m + 1) {
/* 272 */         n = Math.max(n, 
/* 273 */           stringWidth(localFontMetrics, paramFont, str.substring(k, m)));
/* 274 */         if (paramFont.isItalic())
/* 275 */           n += 5;
/*     */       }
/* 277 */       return Math.max(n, 
/* 278 */         stringWidth(localFontMetrics, paramFont, str.substring(k, str.length())));
/*     */     }
/*     */     
/* 281 */     return stringWidth(localFontMetrics, paramFont, str);
/*     */   }
/*     */   
/*     */   static int stringWidth(FontMetrics paramFontMetrics, Font paramFont, String paramString)
/*     */   {
/* 286 */     return paramFontMetrics.stringWidth(paramString) + (paramFont.isItalic() ? paramFont.getSize() / 3 + 1 : 0);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public static int getHeight(Object paramObject, Component paramComponent)
/*     */   {
/* 293 */     return getHeight(paramObject, paramComponent, paramComponent.getFont());
/*     */   }
/*     */   
/*     */   public static int getHeight(Object paramObject, Component paramComponent, Font paramFont)
/*     */   {
/* 298 */     if (paramObject == null)
/* 299 */       return 0;
/* 300 */     if ((paramObject instanceof Image))
/* 301 */       return ((Image)paramObject).getHeight(null);
/* 302 */     if ((paramObject instanceof JCString))
/* 303 */       return ((JCString)paramObject).getHeight(paramComponent, paramFont);
/* 304 */     if ((paramObject instanceof Vector)) {
/* 305 */       Vector localVector = (Vector)paramObject;
/* 306 */       int i = 0;
/* 307 */       for (int j = 0; j < localVector.size(); j++)
/* 308 */         i = Math.max(i, getHeight(localVector.elementAt(j), paramComponent, paramFont));
/* 309 */       return i;
/*     */     }
/*     */     
/* 312 */     if ((paramComponent == null) || (paramComponent.getToolkit() == null) || (paramFont == null)) return 0;
/* 313 */     return 
/* 314 */       paramComponent.getToolkit().getFontMetrics(paramFont).getHeight() * getNumLines(paramObject.toString());
/*     */   }
/*     */   
/*     */   private static int getNumLines(String paramString) {
/* 318 */     if ((paramString == null) || (paramString.length() == 0))
/* 319 */       return 0;
/* 320 */     int i = 1;
/* 321 */     for (int j = 0; j < paramString.length(); j++)
/* 322 */       if (paramString.charAt(j) == '\n') i++;
/* 323 */     return i;
/*     */   }
/*     */   
/*     */   public static int getNumLines(Object paramObject)
/*     */   {
/* 328 */     return getNumLines(toString(paramObject));
/*     */   }
/*     */   
/*     */   static String toString(Object paramObject)
/*     */   {
/* 333 */     if (paramObject == null)
/* 334 */       return null;
/* 335 */     if ((paramObject instanceof Image))
/* 336 */       return null;
/* 337 */     if ((paramObject instanceof JCString))
/* 338 */       return ((JCString)paramObject).getString();
/* 339 */     if ((paramObject instanceof Vector))
/*     */     {
/* 341 */       for (int i = 0; i < ((Vector)paramObject).size(); i++) { String str;
/* 342 */         if ((str = toString(((Vector)paramObject).elementAt(i))) != null)
/* 343 */           return str; }
/* 344 */       return null;
/*     */     }
/*     */     
/* 347 */     return paramObject.toString();
/*     */   }
/*     */ }


/* Location:              D:\frei\JAVA\decompile\jar\All.jar!\jclass\cell\renderers\ButtonCellRenderer.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */