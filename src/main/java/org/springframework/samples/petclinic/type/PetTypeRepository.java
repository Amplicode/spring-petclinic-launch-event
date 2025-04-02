package org.springframework.samples.petclinic.type;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.ListCrudRepository;

import java.util.List;

public interface PetTypeRepository extends ListCrudRepository<PetType, Integer> {
	@Query("select t.* from types t order by t.name asc")
	List<PetType> findAllByOrderByNameAsc();
}
