package com.oenastech.service;

import com.oenastech.dto.ClientDto;
import com.oenastech.model.Client;
import com.oenastech.repositary.ClientRepo;
import jakarta.transaction.Transactional;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

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
@Transactional
public class ClientService {

    private final PasswordEncoder passwordEncoder;
    private final ClientRepo clientRepo;

    private final RolesService rolesService;

    public ClientService(ClientRepo clientRepo, PasswordEncoder passwordEncoder, RolesService rolesService) {
        this.clientRepo = clientRepo;
        this.passwordEncoder = passwordEncoder;
        this.rolesService = rolesService;
    }
    @Transactional
    public void addClient(ClientDto clientDto) {

        Client client = clientDtoToClient(clientDto);

        clientRepo.save(client);
    }

    private Client clientDtoToClient(ClientDto clientDto) {

        Client client = new Client();

        client.setRoles(rolesService.addClientRoles("ROLE_user", "ROLE_admen"));
        client.setEmail(clientDto.getEmail());
        client.setFirstName(clientDto.getFirstName());
        client.setLastName(clientDto.getLastName());
        client.setShippingAddress(clientDto.getShippingAddress());
        client.setPassword(passwordEncoder.encode(clientDto.getPassword()));
        client.setActive(1);

        return client;
    }

    public Client findClientByEmail(String email) {
        return clientRepo.findClientByEmail(email);
    }

    public Client getCurrentClient() {

        UserDetails principal = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String email = principal.getUsername();
        System.out.println(email);
        Client client = findClientByEmail(email);
        System.out.println(client);
        return client;
    }
}
