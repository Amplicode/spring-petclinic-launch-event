package org.springframework.samples.petclinic.vet;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/rest/vets")
public class VetController {

    private final VetRepository vetRepository;

    private final VetMapper vetMapper;

    public VetController(VetRepository vetRepository,
                         VetMapper vetMapper) {
        this.vetRepository = vetRepository;
        this.vetMapper = vetMapper;
    }

    @GetMapping
    public List<VetDto> getAll() {
        List<Vet> vets = vetRepository.findAll();
        List<VetDto> vetDtos = vets.stream()
                .map(vetMapper::toVetDto)
                .toList();
        return vetDtos;
    }

    @GetMapping("/{id}")
    public VetDto getOne(@PathVariable Integer id) {
        Optional<Vet> vetOptional = vetRepository.findById(id);
        return vetMapper.toVetDto(vetOptional.orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Entity with id `%s` not found".formatted(id))));
    }
}
