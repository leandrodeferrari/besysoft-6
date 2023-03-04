package com.besysoft.bootcamp.service.impl;

import com.besysoft.bootcamp.domain.Genero;
import com.besysoft.bootcamp.dto.mapper.IGeneroMapper;
import com.besysoft.bootcamp.dto.request.GeneroInDto;
import com.besysoft.bootcamp.dto.response.GeneroOutDto;
import com.besysoft.bootcamp.repository.database.IGeneroRepository;
import com.besysoft.bootcamp.service.IGeneroService;
import com.besysoft.bootcamp.util.GeneroUtil;
import com.besysoft.bootcamp.util.ValidacionGeneralUtil;

import lombok.extern.slf4j.Slf4j;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@ConditionalOnProperty(prefix = "app", name = "type-data", havingValue = "database")
@Service
public class GeneroServiceBDImpl implements IGeneroService {

    private final IGeneroMapper generoMapper;
    private final IGeneroRepository generoRepository;

    public GeneroServiceBDImpl(IGeneroMapper generoMapper,
                               IGeneroRepository generoRepository) {
        this.generoMapper = generoMapper;
        this.generoRepository = generoRepository;
    }

    @Transactional(readOnly = true)
    @Override
    public List<GeneroOutDto> obtenerTodos() {
        return this.generoRepository.findAll()
                .stream()
                .map(this.generoMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = false)
    @Override
    public GeneroOutDto crear(GeneroInDto dto) {

        GeneroUtil.validarNombre(dto.getNombre());

        if(this.generoRepository.existsByNombre(dto.getNombre())){
            log.info("Ocurrio una validacion personalizada, en el metodo crear(): " + GeneroUtil.EXISTE);
            throw new IllegalArgumentException(GeneroUtil.EXISTE);
        }

        Genero genero = this.generoRepository.save(this.generoMapper.mapToEntity(dto));

        return this.generoMapper.mapToDto(genero);

    }

    @Transactional(readOnly = false)
    @Override
    public GeneroOutDto actualizar(Long id, GeneroInDto dto) {

        ValidacionGeneralUtil.validarId(id);
        GeneroUtil.validarNombre(dto.getNombre());

        if(this.generoRepository.existsByNombre(dto.getNombre())){
            log.info("Ocurrio una validacion personalizada, en el metodo actualizar(): " + GeneroUtil.EXISTE_POR_NOMBRE);
            throw new IllegalArgumentException(GeneroUtil.EXISTE_POR_NOMBRE);
        }

        if(!this.generoRepository.existsById(id)){
            log.info("Ocurrio una validacion personalizada, en el metodo actualizar(): " + GeneroUtil.NO_EXISTE_POR_ID);
            throw new IllegalArgumentException(GeneroUtil.NO_EXISTE_POR_ID);
        }

        Genero genero = this.generoMapper.mapToEntity(dto);
        genero.setId(id);

        return this.generoMapper.mapToDto(this.generoRepository.save(genero));

    }

    @Transactional(readOnly = true)
    @Override
    public  Optional<Genero> buscarPorNombre(String nombre) {
        return this.generoRepository.findByNombre(nombre);
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Genero> buscarPorId(Long id) {
        return this.generoRepository.findById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public boolean existePorNombre(String nombre) {
        return this.generoRepository.existsByNombre(nombre);
    }

    @Transactional(readOnly = true)
    @Override
    public boolean existePorId(Long id) {
        return this.generoRepository.existsById(id);
    }

}
