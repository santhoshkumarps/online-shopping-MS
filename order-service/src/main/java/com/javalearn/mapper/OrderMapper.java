package com.javalearn.mapper;

import com.javalearn.dto.OrderLineItemsDto;
import com.javalearn.model.OrderLineItems;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    OrderMapper ORDER_MAPPER = Mappers.getMapper(OrderMapper.class);

    OrderLineItems mapOrderLineItemsDtoToOrderLineItems(OrderLineItemsDto orderLineItemsDto);


}
