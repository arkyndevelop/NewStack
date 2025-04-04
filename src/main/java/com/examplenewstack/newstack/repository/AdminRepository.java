package com.examplenewstack.newstack.repository;

import com.examplenewstack.newstack.model.usersinfo.adminMaster.AdminMaster;
import org.springframework.data.repository.CrudRepository;

public interface AdminRepository extends CrudRepository <AdminMaster, Long> {
}
