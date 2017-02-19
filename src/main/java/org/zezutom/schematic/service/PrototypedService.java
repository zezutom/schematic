package org.zezutom.schematic.service;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.lang.annotation.*;

/**
 * Creates a service as a prototype bean.
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Service
@Scope("prototype")
public @interface PrototypedService {
}
