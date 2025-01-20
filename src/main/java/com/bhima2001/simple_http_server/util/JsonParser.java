package com.bhima2001.simple_http_server.util;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;

public class JsonParser {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static JsonNode fromJson(String jsonData) throws IOException {
        return objectMapper.readTree(jsonData);
    }

    public static <T> T nodeToObject(JsonNode node, Class<T> className) throws JsonProcessingException{
        return objectMapper.treeToValue(node, className);
    }

    public static <T> JsonNode objectToNode(Class<T> object) {
        return objectMapper.valueToTree(object);
    }

    public static String stringify(JsonNode node, Boolean isPretty) throws JsonProcessingException {
        return generateJson(node, isPretty);
    }

    private static String generateJson(Object o, boolean pretty) throws JsonProcessingException {
        ObjectWriter objectWriter = objectMapper.writer();
        if (pretty) {
            objectWriter = objectWriter.with(SerializationFeature.INDENT_OUTPUT);
        }
        return objectWriter.writeValueAsString(o);
    }
}
