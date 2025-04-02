package org.springframework.samples.petclinic.owner;

import org.springframework.samples.petclinic.type.PetType;

import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

/**
 * DTO for {@link Pet}
 */
public class PetDto {
	private Integer id;
	private String name;
	private LocalDate birthDate;
	private PetType type;
	private Set<VisitDto> visits = new LinkedHashSet<>();

	public PetDto() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDate getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}

	public PetType getType() {
		return type;
	}

	public void setType(PetType type) {
		this.type = type;
	}

	public Set<VisitDto> getVisits() {
		return visits;
	}

	public void setVisits(Set<VisitDto> visits) {
		this.visits = visits;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		PetDto entity = (PetDto) o;
		return Objects.equals(this.id, entity.id) &&
			Objects.equals(this.name, entity.name) &&
			Objects.equals(this.birthDate, entity.birthDate) &&
			Objects.equals(this.type, entity.type) &&
			Objects.equals(this.visits, entity.visits);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, name, birthDate, type, visits);
	}

	@Override
	public String toString() {
		return getClass().getSimpleName() + "(" +
			"id = " + id + ", " +
			"name = " + name + ", " +
			"birthDate = " + birthDate + ", " +
			"type = " + type + ", " +
			"visits = " + visits + ")";
	}
}
