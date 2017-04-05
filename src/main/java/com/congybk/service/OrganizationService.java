package com.congybk.service;

import com.congybk.entity.Organization;

import java.util.List;

/**
 * @Author YNC on 01/04/2017.
 */
public interface OrganizationService {
    List<Organization> getAll();

    void delete(int id);

    Organization findById(int id);

    Organization update(Organization organization);

    Organization create(Organization organization);
}
