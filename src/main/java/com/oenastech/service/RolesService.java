package com.oenastech.service;

import com.oenastech.model.Role;
import com.oenastech.repositary.RolesRepo;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * <h2>An online store project <h2/>
 * <p> That displays products with different providers,
 *with a shopping cart and an order page.<p/>
 *
 *
 *
 *
 * @author Raeed Awer
 *
 *@since 1.1
 */
@Service
public class RolesService {



   private final RolesRepo rolesRepo;

    public RolesService(RolesRepo rolesRepo) {
        this.rolesRepo = rolesRepo;
    }

    @Transactional
    public Set<Role> addClientRoles(String... clientRoles){
        Set<Role> roles=new HashSet<>();
        chickRole(clientRoles);
       Arrays.stream(clientRoles).filter(role-> role.startsWith("ROLE_"))
               .forEach(role-> roles.add(rolesRepo.getRoleByName(role)));


     return roles ;
    }

    private void chickRole(String... rolesName) {
       Arrays.stream(rolesName).filter(role -> rolesRepo.getRoleByName(role)==null)
                .forEach(newRoles->{ Role role=new Role();
                role.setName(newRoles);
                rolesRepo.save(role);
                });



    }


}
