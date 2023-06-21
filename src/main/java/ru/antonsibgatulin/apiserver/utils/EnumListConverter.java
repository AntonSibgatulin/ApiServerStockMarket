package ru.antonsibgatulin.apiserver.utils;


import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import ru.antonsibgatulin.apiserver.data.task.TypeTask;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Converter
public class EnumListConverter implements AttributeConverter<List<TypeTask>, String> {
    private static final String SEPARATOR = ",";

    @Override
    public String convertToDatabaseColumn(List<TypeTask> attribute) {
        return attribute != null ? attribute.stream()
                .map(TypeTask::name)
                .collect(Collectors.joining(SEPARATOR)) : null;
    }

    @Override
    public List<TypeTask> convertToEntityAttribute(String dbData) {
        return dbData != null ? Arrays.stream(dbData.split(SEPARATOR))
                .map(TypeTask::valueOf)
                .collect(Collectors.toList()) : null;
    }
}