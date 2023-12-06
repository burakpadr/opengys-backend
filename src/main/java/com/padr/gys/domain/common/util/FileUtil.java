package com.padr.gys.domain.common.util;

import java.io.File;
import java.util.List;

import com.padr.gys.domain.common.constant.IndependentExceptionMessageConstant;

public class FileUtil {

    public static void createDirectoryIfNotExist(String path) {
        File file = new File(path);

        if (!file.isDirectory())
            if (!file.mkdirs())
                throw new RuntimeException(IndependentExceptionMessageConstant.COULD_NOT_BE_CREATED_FOLDER);

    }

    public static String getFileExtension(String fileName) {
        String[] splittedFileName = fileName.split("\\.");

        if (splittedFileName.length != 2)
            throw new RuntimeException(IndependentExceptionMessageConstant.WRONG_FILE_NAME_FORMAT);

        return fileName.split("\\.")[1];
    }

    public static void deleteFiles(List<String> filePaths) {
        filePaths.stream().forEach(filePath -> {
            File file = new File(filePath);

            if (!file.delete())
                throw new RuntimeException(IndependentExceptionMessageConstant.COULD_NOT_BE_DELETED_FILE_OR_FOLDER);
        });
    }
}
