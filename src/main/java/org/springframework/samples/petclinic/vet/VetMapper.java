package org.springframework.samples.petclinic.vet;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface VetMapper {
	Vet toEntity(VetDto vetDto);

	VetDto toVetDto(Vet vet);
}
