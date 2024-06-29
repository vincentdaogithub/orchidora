package com.orchidora.be.infrastructure.jackson;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StringDeserializer;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;

@JsonComponent
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class JacksonStringHandler {

    public static final class CustomStringDeserializer extends StringDeserializer {
        @Override
        public String deserialize(JsonParser p, DeserializationContext ctxt, String intoValue) throws IOException {
            final String value = super.deserialize(p, ctxt, intoValue);
            return StringUtils.trim(value);
        }
    }
}
