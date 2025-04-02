package org.springframework.samples.petclinic.type;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/rest/petTypes")
public class PetTypeController {

	private final PetTypeRepository petTypeRepository;

	private final PetTypeMapper petTypeMapper;

	private final ObjectMapper objectMapper;

	public PetTypeController(PetTypeRepository petTypeRepository,
							 PetTypeMapper petTypeMapper,
							 ObjectMapper objectMapper) {
		this.petTypeRepository = petTypeRepository;
		this.petTypeMapper = petTypeMapper;
		this.objectMapper = objectMapper;
	}

	@GetMapping
	public PagedModel<PetTypeDto> getAll(Pageable pageable) {
		Page<PetType> petTypes = petTypeRepository.findAll(pageable);
		Page<PetTypeDto> petTypeDtoPage = petTypes.map(petTypeMapper::toPetTypeDto);
		return new PagedModel<>(petTypeDtoPage);
	}

	@GetMapping("/{id}")
	public PetTypeDto getOne(@PathVariable Integer id) {
		Optional<PetType> petTypeOptional = petTypeRepository.findById(id);
		return petTypeMapper.toPetTypeDto(petTypeOptional.orElseThrow(() ->
			new ResponseStatusException(HttpStatus.NOT_FOUND, "Entity with id `%s` not found".formatted(id))));
	}

	@GetMapping("/by-ids")
	public List<PetTypeDto> getMany(@RequestParam List<Integer> ids) {
		List<PetType> petTypes = petTypeRepository.findAllById(ids);
		return petTypes.stream()
			.map(petTypeMapper::toPetTypeDto)
			.toList();
	}

	@PostMapping
	public PetTypeDto create(@RequestBody PetTypeDto dto) {
		PetType petType = petTypeMapper.toEntity(dto);
		PetType resultPetType = petTypeRepository.save(petType);
		return petTypeMapper.toPetTypeDto(resultPetType);
	}

	@PatchMapping("/{id}")
	public PetTypeDto patch(@PathVariable Integer id, @RequestBody JsonNode patchNode) throws IOException {
		PetType petType = petTypeRepository.findById(id).orElseThrow(() ->
			new ResponseStatusException(HttpStatus.NOT_FOUND, "Entity with id `%s` not found".formatted(id)));

		PetTypeDto petTypeDto = petTypeMapper.toPetTypeDto(petType);
		objectMapper.readerForUpdating(petTypeDto).readValue(patchNode);
		petTypeMapper.updateWithNull(petTypeDto, petType);

		PetType resultPetType = petTypeRepository.save(petType);
		return petTypeMapper.toPetTypeDto(resultPetType);
	}

	@PatchMapping
	public List<Integer> patchMany(@RequestParam List<Integer> ids, @RequestBody JsonNode patchNode) throws IOException {
		Collection<PetType> petTypes = petTypeRepository.findAllById(ids);

		for (PetType petType : petTypes) {
			PetTypeDto petTypeDto = petTypeMapper.toPetTypeDto(petType);
			objectMapper.readerForUpdating(petTypeDto).readValue(patchNode);
			petTypeMapper.updateWithNull(petTypeDto, petType);
		}

		List<PetType> resultPetTypes = petTypeRepository.saveAll(petTypes);
		return resultPetTypes.stream()
			.map(PetType::getId)
			.toList();
	}

	@DeleteMapping("/{id}")
	public PetTypeDto delete(@PathVariable Integer id) {
		PetType petType = petTypeRepository.findById(id).orElse(null);
		if (petType != null) {
			petTypeRepository.delete(petType);
		}
		return petTypeMapper.toPetTypeDto(petType);
	}

	@DeleteMapping
	public void deleteMany(@RequestParam List<Integer> ids) {
		petTypeRepository.deleteAllById(ids);
	}
}
