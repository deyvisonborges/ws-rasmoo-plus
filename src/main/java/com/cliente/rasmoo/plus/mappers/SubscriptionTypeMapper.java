package com.cliente.rasmoo.plus.mappers;

import com.cliente.rasmoo.plus.dtos.SubscriptionTypeDto;
import com.cliente.rasmoo.plus.models.SubscriptionTypeModel;

public class SubscriptionTypeMapper {
    public static SubscriptionTypeModel fromDtoToEntity(SubscriptionTypeDto dto) {
        return SubscriptionTypeModel
            .builder()
            .id(dto.getId())
            .name(dto.getName())
            .accessMonth(dto.getAccessMonth())
            .price(dto.getPrice())
            .productKey(dto.getProductKey())
            .build();
    }

}
