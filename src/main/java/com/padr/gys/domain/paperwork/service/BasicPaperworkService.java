package com.padr.gys.domain.paperwork.service;

import java.io.File;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.padr.gys.domain.common.util.FileUtil;
import com.padr.gys.domain.paperwork.port.BasicPaperworkServicePort;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
class BasicPaperworkService implements BasicPaperworkServicePort {

    private final FileUtil fileUtil;
    private final MessageSource messageSource;

    @Override
    public File upload(String filePath, MultipartFile multipartFile) {
        File file = new File(filePath);

        fileUtil.createDirectoryIfNotExist(file.getParentFile().getAbsolutePath());

        try {
            multipartFile.transferTo(file);
        } catch (Exception e) {
            String exceptionMessage = messageSource.getMessage("independent.could-not-be-created-file", null,
                    LocaleContextHolder.getLocale());

            throw new RuntimeException(exceptionMessage);
        }

        return file;
    }
}
