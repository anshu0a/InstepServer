package com.instep.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.instep.entity.LoginHistory;

public interface LoginHistoryRepository extends JpaRepository<LoginHistory, Long> {

}
