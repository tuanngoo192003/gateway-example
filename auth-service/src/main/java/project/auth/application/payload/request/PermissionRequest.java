package project.auth.application.payload.request;

import project.core.application.model.request.BaseRequest;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class PermissionRequest extends BaseRequest {

    @NotNull
    private String uri;

    @NotNull
    private String method;
}
