package autograph;

import java.io.File;

public class Utils {
   public final static String jpg = "jpg";
   public final static String gif = "gif";
   public final static String png = "png";
   public final static String bmp = "bmp";
   public final static String xml = "xml";
   public final static String txt = "txt";
   public final static String ag = "ag";

   /*
    * Get the extension of a file.
    */  
   public static String getExtension(File f) {
       String ext = null;
       String s = f.getName();
       int i = s.lastIndexOf('.');

       if (i > 0 &&  i < s.length() - 1) {
           ext = s.substring(i+1).toLowerCase();
       }
       return ext;
   }
}
