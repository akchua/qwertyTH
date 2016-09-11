package com.thesis.monitor.serializer.json;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.thesis.monitor.database.entity.Account;

public class AccountSerializer extends JsonSerializer<Account> {

	@Override
	public void serialize(Account account, JsonGenerator jsonGenerator, SerializerProvider serializerProvider)
			throws IOException, JsonProcessingException {
		jsonGenerator.writeObject(account);
	}
}
