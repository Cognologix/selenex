package com.cadent.test.utils;

import com.cadent.test.exception.AutomationRunTimeError;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;

import java.io.IOException;

public class JsonUtil extends ObjectMapper {

    private static final long     serialVersionUID             = 1L;
    private static final JsonUtil toPlainStringMapper          = new JsonUtil();
    private static final JsonUtil toObjectTypeMapper           = new JsonUtil();
    private static final JsonUtil toJsonIgnoreObjectTypeMapper = new JsonUtil();

    static {

        toPlainStringMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        toPlainStringMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        toPlainStringMapper.enableDefaultTyping(DefaultTyping.JAVA_LANG_OBJECT, JsonTypeInfo.As.PROPERTY);

        // toObjectTypeMapper
        toObjectTypeMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        // toJsonIgnoreObjectTypeMapper
        toJsonIgnoreObjectTypeMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        toJsonIgnoreObjectTypeMapper.disable(MapperFeature.USE_ANNOTATIONS);
    }

    public static String toString(Object value) {
        try {
            final JsonUtil mapper = new JsonUtil();
            mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
            mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
            return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(value);
        } catch (Exception ex) {
            throw new AutomationRunTimeError (ex);
        }
    }

    public static Object toObject(String value, Class<?> valueType) throws IOException {
        return toObjectTypeMapper.readValue(value, valueType);
    }

    static class Module extends SimpleModule {

        /**
         *
         */
        private static final long serialVersionUID = 1L;

        /**
         * Instantiates a new Module.
         */
        public Module() {
            /*
             * addDeserializer(String.class, new StdScalarDeserializer<String>(String.class) { private static
             * final long serialVersionUID = 1L;
             *
             * @Override public String deserialize(JsonParser jsonParser, DeserializationContext ctxt) throws
             * IOException, JsonProcessingException { return StringUtils.trim(jsonParser.getValueAsString());
             * } });
             */
        }
    }
}
