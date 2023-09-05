package com.oenastech.service;

import com.oenastech.model.Provider;
import com.oenastech.repositary.ProviderRepo;
import org.springframework.stereotype.Service;
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
public class ProviderService {

    private final ProviderRepo providerRepo;

    public ProviderService(ProviderRepo providerRepo) {
        this.providerRepo = providerRepo;
    }

    public Provider getReferenceById(Long id) {
       return providerRepo.getReferenceById(id);
    }
}
