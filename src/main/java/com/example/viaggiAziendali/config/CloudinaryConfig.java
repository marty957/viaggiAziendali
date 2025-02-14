package com.example.viaggiAziendali.config;


import com.cloudinary.Cloudinary;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class CloudinaryConfig {

    @Bean
    public Cloudinary uploader(){
        Map<String,String> conf= new HashMap<>();
        conf.put("cloud_name","dtfqtuczz");
        conf.put("api_key","551487253114781");
        conf.put("api_secret","551487253114781");

        return new Cloudinary(conf);

    }
}
