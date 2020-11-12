package springmvckarkas;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;

@Configuration
public class JacksonConfig implements WebMvcConfigurer {
    private Logger log = LoggerFactory.getLogger(getClass());

    // Настраиваем Jackson для задания вида, в котором выдавать Json
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        log.info("configuredMessageConverters");
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new Hibernate5Module());
        mapper.registerModule(new JavaTimeModule());
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false); // Задаем дату в читабельном виде
        mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.NONE); // Отключаем чтение через шз геттеры и сеттеры
        mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY); // Подключаем чтение напрямую из полей
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL); // Не выводить null поля

        converters.add(new MappingJackson2HttpMessageConverter(mapper)); // Задаем кодировку и медиатайп
        StringHttpMessageConverter stringHttpMessageConverter = new StringHttpMessageConverter();
        stringHttpMessageConverter.setSupportedMediaTypes(Arrays.asList(new MediaType("text", "plain", Charset.forName("UTF-8")),
                new MediaType("text", "html", Charset.forName("UTF-8"))));
        converters.add(stringHttpMessageConverter);
    }
}
