package com.example.backend.config;

import com.logaritex.ai.api.AssistantApi;
import com.logaritex.ai.api.Data;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.crypto.encrypt.TextEncryptor;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfig {
    // 암호화에 사용될 salt 값
    @Value("${spring.salt.salt}")
    private String SALT;


    @Bean
    public TextEncryptor textEncryptor() {

        // 암호화에 사용될 암호화기 생성
        return Encryptors.text("password", SALT);
    }
    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
