package org.springframework.samples.petclinic.vet;

import org.springframework.data.jdbc.core.mapping.AggregateReference;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.samples.petclinic.model.BaseEntity;

@Table("vet_specialties")
public class VetSpecialty extends BaseEntity {
	@Column("specialty_id")
	private AggregateReference<Specialty, Integer> speciality;

	public AggregateReference<Specialty, Integer> getSpeciality() {
		return speciality;
	}

	public void setSpeciality(AggregateReference<Specialty, Integer> speciality) {
		this.speciality = speciality;
	}
}
