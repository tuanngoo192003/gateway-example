package project.auth.application.presenter.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import project.core.application.model.request.BaseRequest;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class LoginRequest extends BaseRequest{

    @NotNull
    private String identifier;

    @NotNull
    private String password;
}
