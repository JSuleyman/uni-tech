package com.example.unitech.service;

import com.example.unitech.dto.request.CityCreateRequestDTO;
import com.example.unitech.mapper.CityMapper;
import com.example.unitech.repository.CityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CityCreateService {
    private final CityRepository repository;
    private final CityMapper mapper;

    public void create(CityCreateRequestDTO createRequestDTO) {

    }
}
