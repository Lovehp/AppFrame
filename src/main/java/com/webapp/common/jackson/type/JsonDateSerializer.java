package com.webapp.common.jackson.type;

import java.io.IOException;
import java.util.Date;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.SerializerProvider;
import org.springframework.stereotype.Component;

import com.webapp.util.Dates;

@Component
public class JsonDateSerializer extends JsonSerializer<Date>{

	@Override
	public void serialize(Date date,JsonGenerator gen,SerializerProvider provider) throws IOException, JsonProcessingException{
		gen.writeString(Dates.format(date,Dates.longFormat));
	}
}
