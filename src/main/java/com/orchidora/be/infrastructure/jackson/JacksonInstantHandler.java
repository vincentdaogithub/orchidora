package com.orchidora.be.infrastructure.jackson;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;
import java.time.Instant;

@JsonComponent
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class JacksonInstantHandler {

    public static final class InstantSerializer extends JsonSerializer<Instant> {

        @Override
        public void serialize(Instant instant, JsonGenerator jsonGenerator, SerializerProvider serializerProvider)
                throws IOException {
            if (instant == null) {
                jsonGenerator.writeNull();
            } else {
                jsonGenerator.writeString(Long.toString(instant.toEpochMilli()));
            }
        }
    }
}
