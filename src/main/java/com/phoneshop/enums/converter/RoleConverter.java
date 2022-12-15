package com.phoneshop.enums.converter;

import java.util.stream.Stream;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import com.phoneshop.enums.Role;

@Converter(autoApply = true)
public class RoleConverter implements AttributeConverter<Role, String> {
	@Override
	public String convertToDatabaseColumn(Role attribute) {
		if (attribute == null) {
			return null;
		}
		return attribute.getCode();
	}

	@Override
	public Role convertToEntityAttribute(String dbData) {
		if (dbData == null) {
			return null;
		}

		return Stream.of(Role.values()).filter(c -> c.getCode().equals(dbData)).findFirst()
				.orElseThrow(IllegalArgumentException::new);
	}
}
