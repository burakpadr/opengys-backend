package com.padr.gys.domain.common.util;

import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.query.StringQuery;

public class ElasticsearchUtil {

    public static StringQuery prepareStringQuery(String searchTerm, String fieldName, Pageable pageable) {
        String[] splittedSearchTerm = searchTerm.split("\\s+");

        StringBuilder mustQuery = new StringBuilder();

        for (int i = 0; i < splittedSearchTerm.length; i++) {
            if (i == splittedSearchTerm.length - 1)
                mustQuery.append(String.format("{\"prefix\": {\"%s\": \"%s\"}}", fieldName, splittedSearchTerm[i]));
            else
                mustQuery.append(String.format("{\"match\": {\"%s\": \"%s\"}},", fieldName, splittedSearchTerm[i]));
        }

        String queryString = String.format("{\"bool\": {\"must\": [%s]}}", mustQuery);

        StringQuery query = new StringQuery(queryString);
        query.setPageable(pageable);

        return query;
    }
}
