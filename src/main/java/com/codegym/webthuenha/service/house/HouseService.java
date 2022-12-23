package com.codegym.webthuenha.service.house;

import com.codegym.webthuenha.model.House;
import com.codegym.webthuenha.repository.IHouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;


@Service
public class HouseService implements IHouseService{
    @Autowired
    IHouseRepository houseRepository;
    @Override
    public Iterable<House> findAll() {
        return houseRepository.findAll();
    }

    @Override
    public Optional<House> findById(Long id) {
        return houseRepository.findById(id);
    }

    @Override
    public House save(House house) {
        return houseRepository.save( house);
    }

    @Override
    public void delete(Long id) {
        houseRepository.deleteById(id);
    }

    @Override
    public Iterable<House> get5HouseByRent() {
        return houseRepository.get5HouseByRent();
    }

    @Override
    public Iterable<House> searchAllHouse(int bath, int bed, String address, Date startTime, Date endTime, Long maxRent, Long minRent) {
        String searchAddress = ('%' + address + '%');

        return houseRepository.searchAllHouse(bath, bed, searchAddress, startTime, endTime, maxRent, minRent);
    }


    public Iterable<House> findByUserId(Long id) {
        return houseRepository.findHouseByUserId(id);
    }

    @Override
    public Iterable<House> findAllPage9(Long start) {
        return houseRepository.findAllPage9(start*9);
    }

    @Override
    public Iterable<House> findHouseByAll(String bedrooms,
                                          String bathrooms,
                                          String address,
                                          long rentMin, long rentMax,
                                          String endTime, String startTime) {
        if (bedrooms == null || bedrooms == ""){
            bedrooms = ".*";
        }
        if (bathrooms == null || bathrooms == ""){
            bathrooms = ".*";
        }
        if (address == null || address == ""){
            address = ".*";
        }
        return houseRepository.findHouseByAll(bedrooms, bathrooms, address, rentMin, rentMax, endTime, startTime);
    }
}
