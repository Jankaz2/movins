package kazmierczak.jan.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResponseDto<T> {
    private T data;

    /**
     *
     * @param t we want to response
     * @param <T> type of response
     * @return response
     */
    public static <T> ResponseDto<T> toResponse(T t) {
        return ResponseDto
                .<T>builder()
                .data(t)
                .build();
    }
}
