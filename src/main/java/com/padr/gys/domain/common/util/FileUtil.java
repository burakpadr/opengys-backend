package com.padr.gys.domain.common.util;

import java.io.File;

public class FileUtil {
    
    public static void createDirectoryIfNotExist(String path) {
        File file = new File(path);

        if (!file.mkdirs())
            throw new RuntimeException("Klasör oluşturulamadı");
        
    }
}
