package com.services;

import com.entities.CompanyAddressEntity;
import com.entities.CompanyEntity;
import com.models.AddressModel;
import com.models.CompanyModel;
import com.repositories.CompanyAddressesRepository;
import com.repositories.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompanyService {

    private CompanyRepository companyRepository;
    private CompanyAddressesRepository companyAddressesRepository;

    @Autowired
    public CompanyService(CompanyRepository companyRepository, CompanyAddressesRepository companyAddressesRepository) {
        this.companyRepository = companyRepository;
        this.companyAddressesRepository = companyAddressesRepository;
    }

    public void addCompany(CompanyModel company) {
        CompanyEntity companyEntity = new CompanyEntity();
        companyEntity.setName(company.getName());
        companyEntity.setId(company.getId());
        companyRepository.save(companyEntity);

        for (AddressModel addressModel: company.getAddreses()) {
            CompanyAddressEntity companyAddressEntity = new CompanyAddressEntity();
            companyAddressEntity.setCountry(addressModel.getCountry());
            companyAddressEntity.setCity(addressModel.getCity());
            companyAddressEntity.setStreet(addressModel.getStreet());
            companyAddressEntity.setCompany(companyEntity);
            companyAddressesRepository.save(companyAddressEntity);
        }
    }

}
