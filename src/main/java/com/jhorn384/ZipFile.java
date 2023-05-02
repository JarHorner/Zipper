package com.jhorn384;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZipFile {
    
    public static void zipFile(File fileToZip, String fileName, ZipOutputStream zipOut) throws IOException {
        // Does nothing if the targeted file is hidden
        if (fileToZip.isHidden()) {
            return;
        }
        // checks if file is a proper directory to continue
        if (fileToZip.isDirectory()) {
            // ensures the entry ends with a '/'
            if (fileName.endsWith("/")) {
                zipOut.putNextEntry(new ZipEntry(fileName));
                zipOut.closeEntry();
            } else {
                zipOut.putNextEntry(new ZipEntry(fileName + "/"));
                zipOut.closeEntry();
            }
            // grabs all the files within the directory
            File[] children = fileToZip.listFiles();
            // through the use of recursion, calls this method to zip each child in the main
            // file
            for (File childFile : children) {
                zipFile(childFile, fileName + "/" + childFile.getName(), zipOut);
            }
            return;
        }
        // begins writing the new zip file 
        FileInputStream fis = new FileInputStream(fileToZip);
        ZipEntry zipEntry = new ZipEntry(fileName);
        zipOut.putNextEntry(zipEntry);

        // a zip file uses an array of bytes to write the data 
        byte[] data = new byte[1024];
        int length;
        while ((length = fis.read(data)) >= 0) {
            zipOut.write(data, 0, length);
        }

        zipOut.close();
        fis.close();
    }
}
