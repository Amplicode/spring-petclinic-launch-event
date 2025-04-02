package org.springframework.samples.petclinic.vet;

import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jdbc.core.mapping.AggregateReference;

import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public abstract class VetMapper {
    @Autowired
    private SpecialtyRepository specialtyRepository;

    abstract Specialty toEntity(SpecialtyDto specialtyDto);

    abstract SpecialtyDto toSpecialtyDto(Specialty specialty);

    abstract Vet toEntity(VetDto vetDto);

    @Mapping(target = "specialities", expression = "java(specialtiesToSpecialtyDtos(vet.getSpecialties()))")
    abstract VetDto toVetDto(Vet vet);

    Set<SpecialtyDto> specialtiesToSpecialtyDtos(Set<VetSpecialty> specialties) {
        return specialties.stream()
                .map(VetSpecialty::getSpeciality)
                .map(AggregateReference::getId).filter(Objects::nonNull)
                .map(specialtyRepository::findById).filter(Optional::isPresent)
                .map(Optional::get)
                .map(this::toSpecialtyDto)
                .collect(Collectors.toSet());
    }

}
