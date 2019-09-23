package com.jdlservice.accountservice.dao;

import com.jdlservice.accountservice.entity.LogTrx;
import org.springframework.data.repository.CrudRepository;

public interface LogDao extends CrudRepository<LogTrx, String> {
}
