package com.cdacproject.stayfinder.pg_property_service.mapper;

import com.cdacproject.stayfinder.pg_property_service.dto.RoomDto;
import com.cdacproject.stayfinder.pg_property_service.dto.RoomResponseDto;
import com.cdacproject.stayfinder.pg_property_service.model.Room;
import org.mapstruct.*;

@Mapper(componentModel = "spring", uses = ImageUrlMapper.class)
public interface RoomMapper {

    @Mapping(target = "imageUrl", source = "imageUrl", qualifiedByName = "buildImageUrl")
    RoomResponseDto toResponseDto(Room room, @Context String gatewayUrl);

    Room toEntity(RoomDto dto);
}


