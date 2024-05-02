/*     */ package jclass.cell.renderers;
/*     */ 
/*     */ import java.awt.Checkbox;
/*     */ import java.awt.Color;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.Graphics;
/*     */ import java.awt.Image;
/*     */ import jclass.cell.CellInfo;
/*     */ import jclass.cell.CellRenderer;
/*     */ import jclass.util.JCImageCreator;
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
/*     */ public class CheckboxCellRenderer
/*     */   implements CellRenderer
/*     */ {
/*  76 */   private static JCImageCreator creator = null;
/*  77 */   private static Image boximage = null;
/*  78 */   private static Image checkimage = null;
/*     */   
/*  80 */   private static final String[] box_pixels = {
/*  81 */     "ggggggggggggl", 
/*  82 */     "gbbbbbbbbbbl.", 
/*  83 */     "gb.........l.", 
/*  84 */     "gb.........l.", 
/*  85 */     "gb.........l.", 
/*  86 */     "gb.........l.", 
/*  87 */     "gb.........l.", 
/*  88 */     "gb.........l.", 
/*  89 */     "gb.........l.", 
/*  90 */     "gb.........l.", 
/*  91 */     "gb.........l.", 
/*  92 */     "gbllllllllll.", 
/*  93 */     "l............" };
/*     */   
/*     */ 
/*  96 */   private static final String[] check_pixels = {
/*  97 */     ".........", 
/*  98 */     ".......b.", 
/*  99 */     "......bb.", 
/* 100 */     ".b...bbb.", 
/* 101 */     ".bb.bbb..", 
/* 102 */     ".bbbbb...", 
/* 103 */     "..bbb....", 
/* 104 */     "...b.....", 
/* 105 */     "........." };
/*     */   
/*     */ 
/* 108 */   private static Checkbox comp = new Checkbox();
/*     */   
/*     */ 
/*     */ 
/*     */   public CheckboxCellRenderer()
/*     */   {
/* 114 */     if (creator == null) {
/* 115 */       creator = new JCImageCreator(comp);
/* 116 */       creator.setColor('g', Color.gray);
/* 117 */       creator.setColor('l', Color.lightGray);
/* 118 */       creator.setColor('b', Color.black);
/* 119 */       creator.setColor('.', Color.white);
/*     */       
/* 121 */       creator.setSize(box_pixels.length, box_pixels.length);
/* 122 */       boximage = creator.create(box_pixels);
/*     */       
/* 124 */       creator.setSize(check_pixels.length, check_pixels.length);
/* 125 */       checkimage = creator.create(check_pixels);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void draw(Graphics paramGraphics, CellInfo paramCellInfo, Object paramObject, boolean paramBoolean)
/*     */   {
/* 133 */     paramGraphics.setFont(paramCellInfo.getFont());
/* 134 */     paramGraphics.setColor(paramBoolean ? paramCellInfo.getSelectedForeground() : 
/* 135 */       paramCellInfo.getForeground());
/*     */     
/* 137 */     paramGraphics.drawImage(boximage, 1, 1, null);
/*     */     
/* 139 */     if (paramObject == null) {
/* 140 */       return;
/*     */     }
/*     */     try {
/* 143 */       if (paramObject != null) {
/* 144 */         boolean bool = ((Boolean)paramObject).booleanValue();
/* 145 */         if (bool) {
/* 146 */           paramGraphics.drawImage(checkimage, 3, 3, null);
/*     */         }
/*     */       }
/*     */     }
/*     */     catch (Exception localException) {}
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public Dimension getPreferredSize(CellInfo paramCellInfo, Object paramObject)
/*     */   {
/* 158 */     return new Dimension(box_pixels.length, box_pixels.length);
/*     */   }
/*     */ }


/* Location:              D:\frei\JAVA\decompile\jar\All.jar!\jclass\cell\renderers\CheckboxCellRenderer.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */