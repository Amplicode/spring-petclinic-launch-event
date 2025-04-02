package org.springframework.samples.petclinic.vet;

import org.springframework.data.repository.ListCrudRepository;

public interface SpecialtyRepository extends ListCrudRepository<Specialty, Integer> {
}
