package com.jdlservice.accountservice.dao;

import com.jdlservice.accountservice.entity.Privilege;
import com.jdlservice.accountservice.entity.PrivilegeId;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrivilegeDao extends CrudRepository<Privilege, PrivilegeId> {
}
