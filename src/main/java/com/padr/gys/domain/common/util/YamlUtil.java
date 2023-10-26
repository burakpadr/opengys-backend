package com.padr.gys.domain.common.util;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import org.yaml.snakeyaml.Yaml;

public class YamlUtil {
    
    public static Object yamlToJson(String filePath) {
        try {
            ClassLoader classloader = Thread.currentThread().getContextClassLoader();
            InputStream inputStream = classloader.getResourceAsStream(filePath);

            Reader fileReader = new InputStreamReader(inputStream);

            Yaml yaml = new Yaml();

            Object result = yaml.load(fileReader);

            fileReader.close();

            return result;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
