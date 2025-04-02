package org.springframework.samples.petclinic.vet;

import jakarta.validation.constraints.NotBlank;

import java.util.Objects;
import java.util.Set;

/**
 * DTO for {@link Vet}
 */
public class VetDto {
	private final Integer id;
	private final String firstName;
	@NotBlank
	private final String lastName;
	private final Set<Specialty> specialties;

	public VetDto(Integer id, String firstName, String lastName, Set<Specialty> specialties) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.specialties = specialties;
	}

	public Integer getId() {
		return id;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		VetDto entity = (VetDto) o;
		return Objects.equals(this.id, entity.id) &&
			Objects.equals(this.firstName, entity.firstName) &&
			Objects.equals(this.lastName, entity.lastName);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, firstName, lastName);
	}

	@Override
	public String toString() {
		return getClass().getSimpleName() + "(" +
			"id = " + id + ", " +
			"firstName = " + firstName + ", " +
			"lastName = " + lastName + ")";
	}
}
