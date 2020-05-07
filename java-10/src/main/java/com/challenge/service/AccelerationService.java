package com.challenge.service;

import com.challenge.entity.Acceleration;
import com.challenge.entity.Company;
import com.challenge.repository.AccelerationRepository;
import com.challenge.service.interfaces.AccelerationServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccelerationService implements AccelerationServiceInterface {
    AccelerationRepository accelerationRepository;

    @Autowired
    public AccelerationService(AccelerationRepository accelerationRepository){
        this.accelerationRepository = accelerationRepository;
    }

    @Override
    public Optional<Acceleration> findById(Long id) {
        return accelerationRepository.findById(id);
    }

    @Override
    public List<Acceleration> findByCompanyId(Long companyId) {
        return accelerationRepository.findByCompanyId(companyId);
    }

    @Override
    public Acceleration save(Acceleration object) {
        return accelerationRepository.save(object);
    }
}
