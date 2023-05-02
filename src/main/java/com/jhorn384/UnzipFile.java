package com.jhorn384;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class UnzipFile {

    public static void unzipFile(File pathToUnzip, ZipInputStream zipIn) throws IOException {

        // a zip file uses an array of bytes to write the data 
        byte[] buffer = new byte[1024];
        ZipEntry zipEntry = zipIn.getNextEntry();
        
        // iterates through each ZipEntry
        while(zipEntry != null) {
            File newFile = newFile(pathToUnzip, zipEntry);
            // checks if entry is a directory, if it is, it is created using mkdirs() method.
            if (zipEntry.isDirectory()) {
                if (!newFile.isDirectory() && !newFile.mkdirs()) {
                    throw new IOException("Failed to create directory " + newFile);
                }
            } else {
                // Checks if the file's parent directory exists, which is necessary for archives created on Windows
                File parent = newFile.getParentFile();
                if (!parent.isDirectory() && !parent.mkdirs()) {
                    throw new IOException("Failed to create directory " + newFile);
                }

                // write file content
                FileOutputStream fos = new FileOutputStream(newFile);
                int length;
                while ((length = zipIn.read(buffer)) > 0) {
                    fos.write(buffer, 0, length);
                }
                fos.close();
            }
            zipEntry = zipIn.getNextEntry();
        }

        zipIn.closeEntry();
        zipIn.close();
    }

    // This method helps guard against writing files to the file system outside the target folder; which is called Zip Slip
    private static File newFile(File destDir, ZipEntry zipEntry) throws IOException {
        File destFile = new File(destDir, zipEntry.getName());

        String destDirPath = destDir.getCanonicalPath();
        String destFilePath = destFile.getCanonicalPath();

        if (!destFilePath.startsWith(destDirPath + File.separator)) {
            throw new IOException("Entry is outside of the target dir: " + zipEntry.getName());
        }

        return destFile;
    }
}
