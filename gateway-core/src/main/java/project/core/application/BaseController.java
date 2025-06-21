package project.core.application;

import java.lang.reflect.Field;

import org.apache.coyote.BadRequestException;
import org.springframework.http.ResponseEntity;

import jakarta.validation.constraints.NotNull;
import project.core.application.model.request.BaseRequest;
import project.core.application.model.response.BaseResponse;

public class BaseController {

    protected void validateRequest(BaseRequest request) throws BadRequestException{
        Class<?> clazz = request.getClass();
        Field[] fields = clazz.getDeclaredFields();

        for (Field field : fields) {
            field.setAccessible(true); // allow access to private fields

            // Check for @NotNull annotation
            if (field.isAnnotationPresent(NotNull.class)) {
                try {
                    Object value = field.get(request);
                    if (value == null) {
                        throw new IllegalArgumentException("Field '" + field.getName() + "' must not be null");
                    }
                } catch (IllegalAccessException e) {
                    throw new BadRequestException("Could not access field: " + field.getName(), e);
                }
            }

            // You can add support for other annotations like @Size, @Pattern, etc. here
        }
    }

    protected <T> ResponseEntity<BaseResponse<T>> doResponse(T data) {
        return ResponseEntity.ok(BaseResponse.success(data));
    }
}
