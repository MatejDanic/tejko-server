package com.tejko.api.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tejko.models.yamb.Yamb;

public interface YambRepository extends JpaRepository<Yamb, UUID> {

    public List<Yamb> findAllByUserId(UUID id);

}
