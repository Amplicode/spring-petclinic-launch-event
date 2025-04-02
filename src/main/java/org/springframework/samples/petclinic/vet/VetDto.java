package org.springframework.samples.petclinic.vet;

import jakarta.validation.constraints.NotBlank;

import java.util.Objects;
import java.util.Set;

/**
 * DTO for {@link Vet}
 */
public class VetDto {
	private Integer id;
	private String firstName;
	@NotBlank
	private String lastName;
	private Set<SpecialtyDto> specialities;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Set<SpecialtyDto> getSpecialities() {
		return specialities;
	}

	public void setSpecialities(Set<SpecialtyDto> specialtySpecialities) {
		this.specialities = specialtySpecialities;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		VetDto entity = (VetDto) o;
		return Objects.equals(this.id, entity.id) &&
			Objects.equals(this.firstName, entity.firstName) &&
			Objects.equals(this.lastName, entity.lastName) &&
			Objects.equals(this.specialities, entity.specialities);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, firstName, lastName, specialities);
	}

	@Override
	public String toString() {
		return getClass().getSimpleName() + "(" +
			"id = " + id + ", " +
			"firstName = " + firstName + ", " +
			"lastName = " + lastName + ", " +
			"specialtySpecialities = " + specialities + ")";
	}
}
