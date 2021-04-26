package cn.edu.scau.ticket.application.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @description:
 * @author: Tensh
 * @createDate: 2021/4/26
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@ConfigurationProperties(prefix = "spring.security")
public class ReleaseProperties {
    private String[] releasePath;
}
