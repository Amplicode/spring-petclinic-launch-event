package org.springframework.samples.petclinic.owner;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

/**
 * DTO for {@link Owner}
 */
public class OwnerDto {
    private Integer id;
    private String firstName;
    @NotBlank
    private String lastName;
    @NotBlank
    private String address;
    @NotBlank
    private String city;
    @Pattern(message = "Telephone must be a 10-digit number", regexp = "\\d{10}")
    @NotBlank
    private String telephone;
    private Set<PetDto> pets = new LinkedHashSet<>();

    public OwnerDto() {
    }

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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public Set<PetDto> getPets() {
        return pets;
    }

    public void setPets(Set<PetDto> pets) {
        this.pets = pets;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OwnerDto entity = (OwnerDto) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.firstName, entity.firstName) &&
                Objects.equals(this.lastName, entity.lastName) &&
                Objects.equals(this.address, entity.address) &&
                Objects.equals(this.city, entity.city) &&
                Objects.equals(this.telephone, entity.telephone) &&
                Objects.equals(this.pets, entity.pets);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, address, city, telephone, pets);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "firstName = " + firstName + ", " +
                "lastName = " + lastName + ", " +
                "address = " + address + ", " +
                "city = " + city + ", " +
                "telephone = " + telephone + ", " +
                "pets = " + pets + ")";
    }

    public void addPet(PetDto pet) {
        getPets().add(pet);
    }

    /**
     * Return the Pet with the given name, or null if none found for this Owner.
     *
     * @param name to test
     * @return a pet if pet name is already in use
     */
    public PetDto getPet(String name) {
        return getPet(name, false);
    }

    /**
     * Return the Pet with the given id, or null if none found for this Owner.
     *
     * @param id to test
     * @return a pet if pet id is already in use
     */
    public PetDto getPet(Integer id) {
        return getPets().stream()
                .filter(pet -> Objects.equals(id, pet.getId()))
                .findFirst()
                .orElse(null);
    }

    /**
     * Return the Pet with the given name, or null if none found for this Owner.
     *
     * @param name to test
     * @return a pet if pet name is already in use
     */
    public PetDto getPet(String name, boolean ignoreNew) {
        name = name.toLowerCase();
        for (PetDto pet : getPets()) {
            String compName = pet.getName();
            if (compName != null && compName.equalsIgnoreCase(name)) {
                if (!ignoreNew || pet.getId() != null) {
                    return pet;
                }
            }
        }
        return null;
    }
}
