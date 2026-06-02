package phat.springframework.springsecurity.config;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import java.io.IOException;
import java.time.LocalDateTime;

public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response,
                       AccessDeniedException accessDeniedException) throws IOException, ServletException {

        // 1. Thêm Custom Header báo lỗi phân quyền
        response.setHeader("pbank-error-reason", "Authorization failed");

        // 2. Thiết lập HTTP Status 403 Forbidden và Content-Type JSON
        response.setStatus(HttpStatus.FORBIDDEN.value());
        response.setContentType("application/json;charset=UTF-8");

        // 3. Tạo Payload JSON lỗi chuẩn RESTful
        String jsonPayload = String.format(
                "{\"timestamp\": \"%s\", \"status\": %d, \"error\": \"%s\", \"message\": \"%s\", \"path\": \"%s\"}",
                LocalDateTime.now(),
                HttpStatus.FORBIDDEN.value(),
                HttpStatus.FORBIDDEN.getReasonPhrase(),
                (accessDeniedException != null && accessDeniedException.getMessage() != null)
                        ? accessDeniedException.getMessage() : "Access Denied",
                request.getRequestURI()
        );

        // 4. Ghi JSON vào Response Body
        response.getWriter().write(jsonPayload);
    }
}
