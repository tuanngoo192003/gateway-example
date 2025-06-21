package project.cake.application.presenter.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import project.core.application.model.request.BaseRequest;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class CakeUpdateRequest extends BaseRequest {
    private String id;

    private String name;

    private String description;

    private Long price;

    private Boolean isAvailable;

    private Boolean isDeleted;
}
