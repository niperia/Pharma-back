package ma.emsi.dto.Brand;

import lombok.*;
import ma.emsi.Model.Brand;
import ma.emsi.Model.Category;

import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BrandResponse {
    @SneakyThrows
    public BrandResponse(Brand brand){
        this.id = brand.getId();
        this.name=brand.getName();
        this.slug=brand.getSlug();
        this.createdAt = brand.getCreatedAt();
        this.updatedAt=brand.getUpdatedAt();
    }
    private Integer id;

    private String name;

    private String slug;


    private Date createdAt;


    private Date updatedAt;

}
