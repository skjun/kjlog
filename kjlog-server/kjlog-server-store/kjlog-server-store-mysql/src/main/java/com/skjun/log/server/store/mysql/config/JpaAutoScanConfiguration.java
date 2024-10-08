package com.skjun.log.server.store.mysql.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories("com.skjun")
@EntityScan(basePackages = {"com.skjun"})
public class JpaAutoScanConfiguration {
}
