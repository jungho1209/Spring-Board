package com.example.springboard.global.property;

import com.example.springboard.global.jwt.JwtProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@EnableConfigurationProperties(value = {JwtProperty.class})
@Configuration
public class EnableConfigurationPropertiesConfig {
}
