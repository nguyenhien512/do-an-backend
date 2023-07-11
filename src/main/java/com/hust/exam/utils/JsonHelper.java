package com.hust.exam.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hust.exam.DTO.TestSubmitDTO;

public class JsonHelper {

    private static ObjectMapper objectMapper = new ObjectMapper();

    public static String convertObjectToJson(Object object) throws JsonProcessingException {
        return objectMapper.writeValueAsString(object);
    }

    public static TestSubmitDTO convertJsonToTestSubmitDTO(String json) throws JsonProcessingException {
        return objectMapper.readValue(json, TestSubmitDTO.class);
    }
}
