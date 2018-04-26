package ua.com.mycompany.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.support.ConversionServiceFactory;
import org.springframework.core.convert.support.GenericConversionService;

import java.util.Set;

@Configuration
@ComponentScan("ua.com.mycompany.converter")
public class ConversionConfig {
    @Autowired
    public void registerConverters(Set<Converter<?, ?>> converters, GenericConversionService conversionService) {
        ConversionServiceFactory.registerConverters(converters, conversionService);
    }
}
