package org.springframework.samples.petclinic.owner;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDate;

@Table("visits")
public class Visit {

	@Id
	@Column("id")
	private Integer id;

	@Column("visit_date")
	private LocalDate visitDate;

	@Column("description")
	private String description;

	@Column("pet_id")
	private Integer pet;

	public Integer getPet() {
		return pet;
	}

	public void setPet(Integer pet) {
		this.pet = pet;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDate getVisitDate() {
		return visitDate;
	}

	public void setVisitDate(LocalDate visitDate) {
		this.visitDate = visitDate;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
}
