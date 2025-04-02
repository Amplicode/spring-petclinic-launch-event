package org.springframework.samples.petclinic.type;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface PetTypeMapper {
	PetType toEntity(PetTypeDto petTypeDto);

	PetTypeDto toPetTypeDto(PetType petType);
}
