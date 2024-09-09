package ma.emsi.dto.category;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;
import ma.emsi.Model.Category;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.ArrayList;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoryResponse {
    @SneakyThrows
    public CategoryResponse(Category category){
        this.id = category.getId();
        this.name=category.getName();
        this.slug=category.getSlug();
        this.createdAt = category.getCreatedAt();
        this.updatedAt=category.getUpdatedAt();
    }
    private Integer id;

    private String name;

    private String slug;


    private Date createdAt;


    private Date updatedAt;

}
