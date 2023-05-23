package com.kainv.rest;

import com.kainv.model.entity.personal_cabinet_domain.Role;
import com.kainv.model.repos.RoleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

import static com.kainv.util.RequestMappingPathV1.REGISTRATION;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Slf4j
@RestController
@RequestMapping(REGISTRATION)
public class RegistrationRestControllerV1 {
    private final RoleRepository roleRepository;

    @Autowired
    public RegistrationRestControllerV1(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    //    TODO: усовершенствовать
    @GetMapping
    public ResponseEntity<CollectionModel<EntityModel<Role>>> getRoles() {
        List<EntityModel<Role>> roles = roleRepository.findAll().stream()
                .map(role -> EntityModel.of(role,
                        linkTo(methodOn(RegistrationRestControllerV1.class).getRoles()).withRel("roles")))
                .collect(Collectors.toList());

        CollectionModel<EntityModel<Role>> collectionModel = CollectionModel.of(roles,
                linkTo(methodOn(RegistrationRestControllerV1.class).getRoles()).withSelfRel());

        return ResponseEntity.ok(collectionModel);
    }



//    @PostMapping("/director")
//    @ResponseBody
//    public ResponseEntity<EntityModel<?>> registrationDirector(@RequestBody AddDirectorDto addDirectorDto) {
//        Optional<DirectorDto> result = registrationServiceMediator.addDirector(addDirectorDto);
//
//        if (result.isPresent()) {
//            return ResponseEntity.ok(EntityModel.of("Director has been saved with school")
//                    .add(
//                            linkTo(methodOn(RegistrationRestControllerV1.class).registrationDirector(addDirectorDto)).withSelfRel()
//                    )
//            );
//        } else {
//            log.info("user {} has not been saved and school {} has not been saved", addDirectorDto.getAddUserDto().getEmail(), addDirectorDto.getAddSchoolDto().getName());
//            return ResponseEntity.notFound().build();
//        }
//    }
}