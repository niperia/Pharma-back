package ma.emsi.dto.product;

import lombok.NonNull;
import org.antlr.v4.runtime.misc.NotNull;
import org.springframework.web.multipart.MultipartFile;

public record ProductRequest(@NotNull
                               int category, @NonNull String name,@NonNull String description,@NonNull MultipartFile picture
        ,@NonNull String tags
        ,@NonNull Double discountValue
        ,@NonNull int quantity
        ,@NonNull Double price,@NonNull int brand
) {






}
