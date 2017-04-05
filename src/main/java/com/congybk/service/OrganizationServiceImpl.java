package com.congybk.service;

import com.congybk.entity.Organization;
import com.congybk.repository.OrganizatioinRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author YNC on 01/04/2017.
 */
@Service
public class OrganizationServiceImpl implements OrganizationService {
    @Autowired
    OrganizatioinRepository mOrganizationRepostitory;

    @Override
    public List<Organization> getAll() {
        return (List<Organization>) mOrganizationRepostitory.findAll();
    }

    @Override
    public void delete(int id) {
        mOrganizationRepostitory.delete(id);
    }

    @Override
    public Organization findById(int id) {
        return mOrganizationRepostitory.findOne(id);
    }

    @Override
    public Organization update(Organization organization) {
        Organization temp = mOrganizationRepostitory.findOne(organization.getId());
        temp.setName(organization.getName());
        return mOrganizationRepostitory.save(temp);
    }

    @Override
    public Organization create(Organization organization) {
        return mOrganizationRepostitory.save(organization);
    }


}
