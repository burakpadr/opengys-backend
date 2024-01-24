package com.padr.gys.domain.common.util;

import java.io.File;
import java.util.List;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class FileUtil {

    private final MessageSource messageSource;

    public void createDirectoryIfNotExist(String path) {
        File file = new File(path);

        if (!file.isDirectory())
            if (!file.mkdirs())
                throw new RuntimeException(messageSource.getMessage("independent.could-not-be-created-folder", null,
                        LocaleContextHolder.getLocale()));

    }

    public String getFileExtension(String fileName) {
        String[] splittedFileName = fileName.split("\\.");

        if (splittedFileName.length != 2)
            throw new RuntimeException(messageSource.getMessage("independent.wrong-file-name-format", null,
                    LocaleContextHolder.getLocale()));

        return fileName.split("\\.")[1];
    }

    public void deleteFiles(List<String> filePaths) {
        filePaths.stream().forEach(filePath -> {
            File file = new File(filePath);

            if (!file.delete())
                throw new RuntimeException(messageSource.getMessage("independent.could-not-be-deleted-file-or-folder",
                        null, LocaleContextHolder.getLocale()));
        });
    }
}
