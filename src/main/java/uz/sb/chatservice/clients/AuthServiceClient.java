package uz.sb.chatservice.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import uz.sb.chatservice.config.FeignConfig;
import uz.sb.chatservice.domain.entity.dto.response.UserResponse;


@FeignClient(name = "AUTH-SERVICE", configuration = FeignConfig.class)
public interface AuthServiceClient {

    @GetMapping("/api/auth/find-by-id/{id}")
    UserResponse findById(@PathVariable("id") Long id);
}
