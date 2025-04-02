/*
 * Copyright 2012-2019 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.samples.petclinic.owner;

import jakarta.validation.Valid;
import org.springframework.samples.petclinic.type.PetTypeDto;
import org.springframework.samples.petclinic.type.PetTypeMapper;
import org.springframework.samples.petclinic.type.PetTypeRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Objects;

/**
 * @author Juergen Hoeller
 * @author Ken Krebs
 * @author Arjen Poutsma
 */
@Controller
@RequestMapping("/owners/{ownerId}")
class PetController {

	private static final String VIEWS_PETS_CREATE_OR_UPDATE_FORM = "pets/createOrUpdatePetForm";

	private final OwnerRepository owners;
	private final PetTypeRepository petTypes;
	private final PetTypeMapper petTypeMapper;
	private final OwnerMapper ownerMapper;

	public PetController(OwnerRepository owners, PetTypeRepository petTypes, PetTypeMapper petTypeMapper, OwnerMapper ownerMapper) {
		this.owners = owners;
		this.petTypes = petTypes;
		this.petTypeMapper = petTypeMapper;
		this.ownerMapper = ownerMapper;
	}

	@ModelAttribute("types")
	public Collection<PetTypeDto> populatePetTypes() {
		return this.petTypes.findAllByOrderByNameAsc().stream()
			.map(petTypeMapper::toPetTypeDto)
			.toList();
	}

	@ModelAttribute("owner")
	public OwnerDto findOwner(@PathVariable("ownerId") int ownerId) {
		return this.owners.findById(ownerId).map(ownerMapper::toOwnerDto)
			.orElseThrow(() -> new IllegalArgumentException("Owner ID not found: " + ownerId));
	}

	@ModelAttribute("pet")
	public PetDto findPet(@PathVariable("ownerId") int ownerId,
						  @PathVariable(name = "petId", required = false) Integer petId) {

		if (petId == null) {
			return new PetDto();
		}

		Owner owner = this.owners.findById(ownerId).orElseThrow(() -> new IllegalArgumentException("Owner ID not found: " + ownerId));
		Pet pet = owner.getPet(petId);
		if (pet != null) {
			return ownerMapper.toPetDto(pet);
		}
		return null;
	}

	@InitBinder("owner")
	public void initOwnerBinder(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}

	@InitBinder("pet")
	public void initPetBinder(WebDataBinder dataBinder) {
		dataBinder.setValidator(new PetValidator());
	}

	@GetMapping("/pets/new")
	public String initCreationForm(OwnerDto owner, ModelMap model) {
		PetDto pet = new PetDto();
		owner.addPet(pet);
		model.put("pet", pet);
		return VIEWS_PETS_CREATE_OR_UPDATE_FORM;
	}

	@PostMapping("/pets/new")
	public String processCreationForm(OwnerDto owner, @Valid PetDto pet, BindingResult result, ModelMap model,
									  RedirectAttributes redirectAttributes) {
		if (StringUtils.hasText(pet.getName()) && pet.getId() == null && owner.getPet(pet.getName(), true) != null) {
			result.rejectValue("name", "duplicate", "already exists");
		}

		LocalDate currentDate = LocalDate.now();
		if (pet.getBirthDate() != null && pet.getBirthDate().isAfter(currentDate)) {
			result.rejectValue("birthDate", "typeMismatch.birthDate");
		}

		owner.addPet(pet);
		if (result.hasErrors()) {
			model.put("pet", pet);
			return VIEWS_PETS_CREATE_OR_UPDATE_FORM;
		}

		this.owners.save(ownerMapper.toEntity(owner));
		redirectAttributes.addFlashAttribute("message", "New Pet has been Added");
		return "redirect:/owners/{ownerId}";
	}

	@GetMapping("/pets/{petId}/edit")
	public String initUpdateForm(OwnerDto owner, @PathVariable("petId") int petId, ModelMap model,
								 RedirectAttributes redirectAttributes) {
		PetDto pet = owner.getPet(petId);
		model.put("pet", pet);
		return VIEWS_PETS_CREATE_OR_UPDATE_FORM;
	}

	@PostMapping("/pets/{petId}/edit")
	public String processUpdateForm(@Valid PetDto pet, BindingResult result, OwnerDto owner, ModelMap model,
									RedirectAttributes redirectAttributes) {

		String petName = pet.getName();

		// checking if the pet name already exist for the owner
		if (StringUtils.hasText(petName)) {
			PetDto existingPet = owner.getPet(petName.toLowerCase(), false);
			if (existingPet != null && !Objects.equals(existingPet.getId(), pet.getId())) {
				result.rejectValue("name", "duplicate", "already exists");
			}
		}

		LocalDate currentDate = LocalDate.now();
		if (pet.getBirthDate() != null && pet.getBirthDate().isAfter(currentDate)) {
			result.rejectValue("birthDate", "typeMismatch.birthDate");
		}

		if (result.hasErrors()) {
			model.put("pet", pet);
			return VIEWS_PETS_CREATE_OR_UPDATE_FORM;
		}

		owner.addPet(pet);
		this.owners.save(ownerMapper.toEntity(owner));
		redirectAttributes.addFlashAttribute("message", "Pet details has been edited");
		return "redirect:/owners/{ownerId}";
	}

}
