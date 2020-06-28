package com.br.valhalla.urd.service;

import com.br.valhalla.urd.core.AspectLog;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class SoapLogService extends AspectLog implements LogService{

    private Logger log;

    @Override
    public Logger getLog() { return log; }

    @Override
    public void setLog(Logger log) {this.log = log;}
}
