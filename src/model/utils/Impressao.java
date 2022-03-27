package model.utils;

import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintException;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;

import com.lowagie.text.Document;  

public class Impressao {  

   private static PrintService impressora;  
 
   public Impressao() {  
 
       detectaImpressoras();  
 
   }  
  
   public void detectaImpressoras() {  
 
       try {  
 
           DocFlavor df = DocFlavor.SERVICE_FORMATTED.PRINTABLE;  
           PrintService[] ps = PrintServiceLookup.lookupPrintServices(df, null);  
           for (PrintService p: ps) {  
 
               System.out.println("Impressora encontrada: " + p.getName());  
 
               if (p.getName().contains("Text") || p.getName().contains("Generic"))  {  
 
                   System.out.println("Impressora Selecionada: " + p.getName());  
                  impressora = p;  
                   break;  
 
               }  
 
           }  
 
       } catch (Exception e) {  
 
           e.printStackTrace();  
 
       }  
 
   }  
 
   public synchronized boolean imprime(Document doc) {  
 
       if (impressora == null) {  
 
           String msg = "Nennhuma impressora foi encontrada. Instale uma impressora padrão e reinicie o programa.";  
           System.out.println(msg);  
 
       } else {  
 
           try {  
 
               DocPrintJob dpj = impressora.createPrintJob();  
               //InputStream stream = new ByteArrayInputStream(texto.getBytes());  
 
               //DocFlavor flavor = DocFlavor.INPUT_STREAM.AUTOSENSE;  
               
               //Doc doc = new SimpleDoc(stream, flavor, null);  
               dpj.print((Doc) doc, null);  
 
               return true;  
 
           } catch (PrintException e) {  
 
               e.printStackTrace();  
 
           }  
 
       }  
 
       return false;  
 
   }  
}