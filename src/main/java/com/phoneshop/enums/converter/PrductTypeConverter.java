package com.phoneshop.enums.converter;

import java.util.stream.Stream;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import com.phoneshop.enums.ProductType;

@Converter(autoApply = true)
public class PrductTypeConverter implements AttributeConverter<ProductType, String>{

	@Override
	public String convertToDatabaseColumn(ProductType attribute) {
        if (attribute == null) {
            return null;
        }
        return attribute.getCode();
	}

	@Override
	public ProductType convertToEntityAttribute(String dbData) {
		if (dbData == null) {
            return null;
        }

        return Stream.of(ProductType.values())
          .filter(c -> c.getCode().equals(dbData))
          .findFirst()
          .orElseThrow(IllegalArgumentException::new);
	}
	
}
