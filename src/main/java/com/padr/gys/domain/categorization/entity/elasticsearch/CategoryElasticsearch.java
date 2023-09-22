package com.padr.gys.domain.categorization.entity.elasticsearch;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import com.padr.gys.domain.categorization.entity.persistence.Category;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Document(indexName = "category")
public class CategoryElasticsearch {

    @Id
    private String id;

    @Field(type = FieldType.Long)
    private Long rowId;

    @Field(type = FieldType.Text)
    private String name;

    public static CategoryElasticsearch of(Category category) {
        return CategoryElasticsearch.builder()
                .rowId(category.getId())
                .name(category.getName())
                .build();
    }

    public void updateFrom(Category category) {
        this.name = category.getName(); 
    }
}
