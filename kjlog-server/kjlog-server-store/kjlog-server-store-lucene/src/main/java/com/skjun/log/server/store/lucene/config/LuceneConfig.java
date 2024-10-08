package com.skjun.log.server.store.lucene.config;


import com.skjun.log.server.store.lucene.handler.LuceneLogDataHandler;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Data
public class LuceneConfig {
    @Value("${lucene.store_path}")
    private String storePath;

//    private String f


}
