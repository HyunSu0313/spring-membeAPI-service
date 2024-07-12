package cj.cloudwave.member.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@Profile("prod")
public class ProdWebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("https://df1a808cqvuv2.cloudfront.net") // CloudFront Origin으로 변경
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders(
                        "Content-Type",
                        "X-Amz-Date",
                        "Authorization",
                        "X-Api-Key",
                        "X-Amz-Security-Token",
                        "Origin",            // Origin 헤더 추가
                        "Sec-Fetch-Mode",    // Sec-Fetch-Mode 헤더 추가
                        "Sec-Fetch-Site"     // Sec-Fetch-Site 헤더 추가
                )
                .allowCredentials(true);
    }
}
