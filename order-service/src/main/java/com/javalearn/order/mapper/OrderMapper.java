package com.javalearn.order.mapper;

import com.javalearn.order.dto.OrderLineItemsDto;
import com.javalearn.order.model.OrderLineItems;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    OrderMapper ORDER_MAPPER = Mappers.getMapper(OrderMapper.class);

    OrderLineItems mapOrderLineItemsDtoToOrderLineItems(OrderLineItemsDto orderLineItemsDto);


}
