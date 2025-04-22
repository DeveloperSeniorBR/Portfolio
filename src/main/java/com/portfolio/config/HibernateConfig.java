package com.portfolio.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
public class HibernateConfig {
    // A configuração do Hibernate agora é feita automaticamente pelo Spring Boot
} 