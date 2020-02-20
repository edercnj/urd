package com.br.kerberus.urd.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy(proxyTargetClass = true)
@ComponentScan(basePackages="com.br.kerberus.urd")
public class UrdConfig {
}
