package online.xzjob.schoolwall.util;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import online.xzjob.schoolwall.dto.ScUserDTO;
import online.xzjob.schoolwall.dto.ScUserSetting;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
public class OperationResult<T> {
    private boolean success;
    private LocalDateTime timestamp;
    private String message;
    private T data;

    public OperationResult() {
        this.timestamp = LocalDateTime.now();
    }

    public OperationResult(boolean success, String message, T data) {
        this.success = success;
        this.timestamp = LocalDateTime.now();
        this.message = message;
        this.data = data;
    }

    public void setData(T data) {
        this.data = (T) data;
    }
}
