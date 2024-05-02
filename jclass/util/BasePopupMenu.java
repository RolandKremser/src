/*    */ package jclass.util;
/*    */ 
/*    */ import java.awt.Menu;
/*    */ import java.awt.MenuItem;
/*    */ import java.awt.PopupMenu;
/*    */ import java.awt.event.ActionListener;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public abstract class BasePopupMenu
/*    */   extends PopupMenu
/*    */ {
/*    */   public BasePopupMenu(ActionListener paramActionListener)
/*    */   {
/* 38 */     String[] arrayOfString = getCommands();
/*    */     
/* 40 */     for (int i = 0; i < arrayOfString.length; i++) {
/* 41 */       addItem(arrayOfString[i], paramActionListener);
/*    */     }
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   protected abstract String[] getCommands();
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   public void addItem(String paramString, ActionListener paramActionListener)
/*    */   {
/* 56 */     MenuItem localMenuItem = new MenuItem(paramString);
/* 57 */     localMenuItem.setActionCommand(paramString);
/* 58 */     localMenuItem.addActionListener(paramActionListener);
/* 59 */     add(localMenuItem);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public boolean enableMenuItem(String paramString, boolean paramBoolean)
/*    */   {
/* 71 */     return enableMenuItem(this, paramString, paramBoolean);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public static boolean enableMenuItem(Menu paramMenu, String paramString, boolean paramBoolean)
/*    */   {
/* 84 */     int i = paramMenu.getItemCount();
/* 85 */     for (int j = 0; j < i; j++) {
/* 86 */       MenuItem localMenuItem = paramMenu.getItem(j);
/* 87 */       String str = localMenuItem.getActionCommand();
/* 88 */       if (str.equals(paramString)) {
/* 89 */         localMenuItem.setEnabled(paramBoolean);
/* 90 */         return true;
/*    */       }
/* 92 */       if (((localMenuItem instanceof Menu)) && (enableMenuItem((Menu)localMenuItem, paramString, paramBoolean)))
/* 93 */         return true;
/*    */     }
/* 95 */     return false;
/*    */   }
/*    */ }

