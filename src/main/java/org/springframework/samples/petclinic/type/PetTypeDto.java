package org.springframework.samples.petclinic.type;

import java.util.Objects;

/**
 * DTO for {@link PetType}
 */
public class PetTypeDto {
	private Integer id;
	private String name;

	public PetTypeDto() {
	}

	public PetTypeDto(Integer id, String name) {
		this.id = id;
		this.name = name;
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

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		PetTypeDto entity = (PetTypeDto) o;
		return Objects.equals(this.id, entity.id) &&
			Objects.equals(this.name, entity.name);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, name);
	}

	@Override
	public String toString() {
		return getClass().getSimpleName() + "(" +
			"id = " + id + ", " +
			"name = " + name + ")";
	}
}
