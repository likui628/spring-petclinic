package spring.petclinic.services.map;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import spring.petclinic.model.Owner;
import spring.petclinic.model.Pet;
import spring.petclinic.model.PetType;
import spring.petclinic.services.OwnerService;
import spring.petclinic.services.PetService;
import spring.petclinic.services.PetTypeService;

import java.util.List;
import java.util.Set;

@Service
@Profile({"default", "map"})
public class OwnerServiceMap extends AbstractMapService<Owner, Long> implements OwnerService {
    private PetService petService;
    private PetTypeService petTypeService;

    public OwnerServiceMap(PetService petService, PetTypeService petTypeService) {
        this.petService = petService;
        this.petTypeService = petTypeService;
    }

    @Override
    public Set<Owner> findAll() {
        return super.findAll();
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }

    @Override
    public void delete(Owner object) {
        super.delete(object);
    }

    @Override
    public Owner save(Owner object) {
        if (object == null) {
            return null;
        }
        object.getPets().forEach(pet -> {
            if (pet.getPetType() != null) {
                if (pet.getPetType().getId() == null) {
                    PetType savedPetType = petTypeService.save(pet.getPetType());
                    pet.setPetType(savedPetType);
                }
            } else {
                throw new RuntimeException("Pet Type is required");
            }
            if (pet.getId() == null) {
                Pet savedPet = this.petService.save(pet);
                pet.setId(savedPet.getId());
            }
        });
        return super.save(object);
    }

    @Override
    public Owner findById(Long id) {
        return super.findById(id);
    }

    @Override
    public Owner findByLastName(String lastName) {
        return null;
    }

    @Override
    public List<Owner> findAllByLastNameLike(String lastName) {
        // Not Implemented
        return null;
    }
}
