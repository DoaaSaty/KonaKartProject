package com.konakart.conf;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.konakart.conf.ApplicationConfig;

@Configuration
@ComponentScan
@Import(ApplicationConfig.class)
public class TestConfig {

}
