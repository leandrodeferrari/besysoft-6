package com.besysoft.bootcamp.service.impl;

import com.besysoft.bootcamp.domain.Genero;
import com.besysoft.bootcamp.dto.mapper.IGeneroMapper;
import com.besysoft.bootcamp.dto.request.GeneroInDto;
import com.besysoft.bootcamp.dto.response.GeneroOutDto;
import com.besysoft.bootcamp.repository.memory.IGeneroRepository;
import com.besysoft.bootcamp.service.IGeneroService;
import com.besysoft.bootcamp.util.GeneroUtil;
import com.besysoft.bootcamp.util.ValidacionGeneralUtil;

import lombok.extern.slf4j.Slf4j;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@ConditionalOnProperty(prefix = "app", name = "type-data", havingValue = "memory")
@Service
public class GeneroServiceMemoriaImpl implements IGeneroService {

    private final IGeneroMapper generoMapper;
    private final IGeneroRepository generoRepository;

    public GeneroServiceMemoriaImpl(IGeneroMapper generoMapper,
                                    IGeneroRepository generoRepository) {
        this.generoMapper = generoMapper;
        this.generoRepository = generoRepository;
    }

    @Override
    public List<GeneroOutDto> obtenerTodos() {
        return this.generoRepository.obtenerTodos()
                .stream()
                .map(this.generoMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public GeneroOutDto crear(GeneroInDto dto) {

        GeneroUtil.validarNombre(dto.getNombre());

        if(this.generoRepository.existePorNombre(dto.getNombre())){
            log.info("Ocurrio una validacion personalizada, en el metodo crear(): " + GeneroUtil.EXISTE);
            throw new IllegalArgumentException(GeneroUtil.EXISTE);
        }

        Genero genero = this.generoRepository.crear(this.generoMapper.mapToEntity(dto));

        return this.generoMapper.mapToDto(genero);

    }

    @Override
    public GeneroOutDto actualizar(Long id, GeneroInDto dto) {

        ValidacionGeneralUtil.validarId(id);
        GeneroUtil.validarNombre(dto.getNombre());

        if(this.generoRepository.existePorNombre(dto.getNombre())){
            log.info("Ocurrio una validacion personalizada, en el metodo actualizar(): " + GeneroUtil.EXISTE_POR_NOMBRE);
            throw new IllegalArgumentException(GeneroUtil.EXISTE_POR_NOMBRE);
        }

        if(!this.generoRepository.existePorId(id)){
            log.info("Ocurrio una validacion personalizada, en el metodo actualizar(): " + GeneroUtil.NO_EXISTE_POR_ID);
            throw new IllegalArgumentException(GeneroUtil.NO_EXISTE_POR_ID);
        }

        Genero genero = this.generoRepository.actualizar(id, this.generoMapper.mapToEntity(dto));

        return this.generoMapper.mapToDto(genero);

    }

    @Override
    public Optional<Genero> buscarPorNombre(String nombre) {
        return this.generoRepository.buscarPorNombre(nombre);
    }

    @Override
    public Optional<Genero> buscarPorId(Long id) {
        return this.generoRepository.buscarPorId(id);
    }

    @Override
    public boolean existePorNombre(String nombre) {
        return this.generoRepository.existePorNombre(nombre);
    }

    @Override
    public boolean existePorId(Long id) {
        return this.generoRepository.existePorId(id);
    }

}
