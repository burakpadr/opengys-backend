package com.padr.gys.domain.common.util;

import java.io.File;
import java.util.List;

public class FileUtil {
    
    public static void createDirectoryIfNotExist(String path) {
        File file = new File(path);

        if (!file.isDirectory())
            if (!file.mkdirs())
                throw new RuntimeException("Klasör oluşturulamadı");
        
    }

    public static String getFileExtension(String fileName) {
        String[] splittedFileName = fileName.split("\\.");

        if (splittedFileName.length != 2)
            throw new RuntimeException("Dosya ismi 'örnek.uzantı' formatında olmalıdır!");

        return fileName.split("\\.")[1];
    }

    public static void deleteFiles(List<String> filePaths) {
        filePaths.stream().forEach(filePath -> {
            File file = new File(filePath);

            if (!file.delete())
                throw new RuntimeException("Dosya/Klasör silinemedi");
        });
    }
}
