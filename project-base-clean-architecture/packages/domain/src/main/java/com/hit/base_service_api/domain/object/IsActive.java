package com.hit.base_service_api.domain.object;

import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializable;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;

import java.io.IOException;
import java.util.Arrays;

@SuppressWarnings("rawtypes")
@JsonDeserialize(using = DomainObjectIsActiveDeserializer.class)
public enum IsActive implements EnumBase, JsonSerializable {
  /*
   * 0 is not active, 1 is active
   */
  NOT_ACTIVE(0), ACTIVE(1);

  @JsonValue
  private Integer value;

  private IsActive(Integer value) {
    this.value = value;
  }

  public Integer value() {
    return value;
  }

  public static IsActive parseOf(Integer value) {
    return Arrays.stream(values()).filter(e -> e.getValue().equals(value)).findFirst().orElse(null);
  }

  @Override
  public Object getValue() {
    return value;
  }

  @Override
  public void serialize(JsonGenerator generator, SerializerProvider serializers)
      throws IOException {
    generator.writeNumber(value);
  }

  @Override
  public void serializeWithType(JsonGenerator generator, SerializerProvider serializers,
                                TypeSerializer typeSer) throws IOException {
    generator.writeNumber(value);
  }
}

class DomainObjectIsActiveDeserializer extends JsonDeserializer<IsActive> {
  @Override
  public IsActive deserialize(JsonParser parser, DeserializationContext context)
      throws IOException {
    return IsActive.parseOf(parser.getValueAsInt());
  }
}
