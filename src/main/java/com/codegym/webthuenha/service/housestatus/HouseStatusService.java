package com.codegym.webthuenha.service.housestatus;


import com.codegym.webthuenha.model.HouseStatus;
import com.codegym.webthuenha.repository.IHouseStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class HouseStatusService implements IHouseStatusService {

    @Autowired
    IHouseStatusRepository houseStatusRepository;
    @Override
    public Iterable<HouseStatus> findAll() {
        return houseStatusRepository.findAll();
    }

    @Override
    public Optional<HouseStatus> findById(Long id) {
        return houseStatusRepository.findById(id);
    }

    @Override
    public HouseStatus save(HouseStatus houseStatus) {
      return   houseStatusRepository.save(houseStatus);
    }

    @Override
    public void delete(Long id) {
        houseStatusRepository.deleteById(id);
    }
}
