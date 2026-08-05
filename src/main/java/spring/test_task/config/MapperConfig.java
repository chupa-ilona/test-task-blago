package spring.test_task.config;

import org.mapstruct.InjectionStrategy;

@org.mapstruct.MapperConfig(
        componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        nullValueCheckStrategy = org.mapstruct.NullValueCheckStrategy.ALWAYS,
        implementationPackage = "spring.test_task.mapper.impl"
)
public class MapperConfig {
}