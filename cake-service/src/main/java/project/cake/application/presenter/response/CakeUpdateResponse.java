package project.cake.application.presenter.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CakeUpdateResponse {
    private String id; 

    private String name;

    private String description;

    private Long price;

    private Boolean isAvailable;
}
