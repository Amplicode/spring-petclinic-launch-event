package org.springframework.samples.petclinic.owner;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface OwnerMapper {
	Owner toEntity(OwnerDto ownerDto);

	OwnerDto toOwnerDto(Owner owner);

	PetDto toPetDto(Pet pet);
}
