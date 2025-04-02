package org.springframework.samples.petclinic.vet;

import java.util.Objects;

/**
 * DTO for {@link VetSpecialty}
 */
public class VetSpecialtyDto {
	private Integer id;
	private Integer speciality;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getSpeciality() {
		return speciality;
	}

	public void setSpeciality(Integer speciality) {
		this.speciality = speciality;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		VetSpecialtyDto entity = (VetSpecialtyDto) o;
		return Objects.equals(this.id, entity.id) &&
			Objects.equals(this.speciality, entity.speciality);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, speciality);
	}

	@Override
	public String toString() {
		return getClass().getSimpleName() + "(" +
			"id = " + id + ", " +
			"speciality = " + speciality + ")";
	}
}
