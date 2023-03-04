package com.besysoft.bootcamp.service.impl;

import com.besysoft.bootcamp.domain.Genero;
import com.besysoft.bootcamp.domain.PeliculaSerie;
import com.besysoft.bootcamp.dto.mapper.IPeliculaSerieMapper;
import com.besysoft.bootcamp.dto.request.PeliculaSerieInDto;
import com.besysoft.bootcamp.dto.response.PeliculaSerieOutDto;
import com.besysoft.bootcamp.repository.memory.IPeliculaSerieRepository;
import com.besysoft.bootcamp.service.IGeneroService;
import com.besysoft.bootcamp.service.IPeliculaSerieService;
import com.besysoft.bootcamp.util.FechaUtil;
import com.besysoft.bootcamp.util.PeliculaSerieUtil;
import com.besysoft.bootcamp.util.ValidacionGeneralUtil;

import lombok.extern.slf4j.Slf4j;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@ConditionalOnProperty(prefix = "app", name = "type-data", havingValue = "memory")
@Service
public class PeliculaSerieServiceMemoriaImpl implements IPeliculaSerieService {

    private final IGeneroService generoService;
    private final IPeliculaSerieMapper peliculaSerieMapper;
    private final IPeliculaSerieRepository peliculaSerieRepository;

    public PeliculaSerieServiceMemoriaImpl(IGeneroService generoService,
                                           IPeliculaSerieMapper peliculaSerieMapper,
                                           IPeliculaSerieRepository peliculaSerieRepository) {
        this.generoService = generoService;
        this.peliculaSerieMapper = peliculaSerieMapper;
        this.peliculaSerieRepository = peliculaSerieRepository;
    }

    @Override
    public List<PeliculaSerieOutDto> buscarPorFiltros(String titulo, String nombreGenero) {
        return this.peliculaSerieRepository.buscarPorFiltros(titulo, nombreGenero)
                .stream()
                .map(this.peliculaSerieMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<PeliculaSerieOutDto> buscarPorFechas(String desde, String hasta) {

        LocalDate fechaInicio = FechaUtil.formatear(desde);
        LocalDate fechaFinal = FechaUtil.formatear(hasta);
        FechaUtil.validarRango(fechaInicio, fechaFinal);

        return this.peliculaSerieRepository.buscarPorFechas(fechaInicio, fechaFinal)
                .stream()
                .map(this.peliculaSerieMapper::mapToDto)
                .collect(Collectors.toList());

    }

    @Override
    public List<PeliculaSerieOutDto> buscarPorCalificaciones(Byte desde, Byte hasta) {

        PeliculaSerieUtil.validarCalificacion(desde);
        PeliculaSerieUtil.validarCalificacion(hasta);
        ValidacionGeneralUtil.validarRangoDeNumeros(desde, hasta);

        return this.peliculaSerieRepository.buscarPorCalificaciones(desde, hasta)
                .stream()
                .map(this.peliculaSerieMapper::mapToDto)
                .collect(Collectors.toList());

    }

    @Override
    public PeliculaSerieOutDto crear(PeliculaSerieInDto dto) {

        PeliculaSerieUtil.validarDto(dto);

        if(this.peliculaSerieRepository.existePorTitulo(dto.getTitulo())){
            log.info("Ocurrio una validacion personalizada, en el metodo crear(): " + PeliculaSerieUtil.EXISTE);
            throw new IllegalArgumentException(PeliculaSerieUtil.EXISTE);
        }

        Optional<Genero> optionalGenero = this.generoService
                .buscarPorId(dto.getGeneroId());

        if(optionalGenero.isPresent()){

            PeliculaSerie peliculaSerie = this.peliculaSerieMapper.mapToEntity(dto);
            peliculaSerie.setGenero(optionalGenero.get());

            return this.peliculaSerieMapper.mapToDto(this.peliculaSerieRepository.crear(peliculaSerie));

        } else {
            log.info("Ocurrio una validacion personalizada, en el metodo crear(): " + PeliculaSerieUtil.NO_EXISTE_GENERO_POR_NOMBRE);
            throw new IllegalArgumentException(PeliculaSerieUtil.NO_EXISTE_GENERO_POR_NOMBRE);
        }

    }

    @Override
    public PeliculaSerieOutDto actualizar(Long id, PeliculaSerieInDto dto) {

        ValidacionGeneralUtil.validarId(id);
        PeliculaSerieUtil.validarDto(dto);

        if(!this.peliculaSerieRepository.existePorId(id)){
            log.info("Ocurrio una validacion personalizada, en el metodo actualizar(): " + PeliculaSerieUtil.NO_EXISTE_POR_ID);
            throw new IllegalArgumentException(PeliculaSerieUtil.NO_EXISTE_POR_ID);
        }

        if(this.peliculaSerieRepository.existePorTitulo(dto.getTitulo())){
            log.info("Ocurrio una validacion personalizada, en el metodo actualizar(): " + PeliculaSerieUtil.EXISTE_POR_TITULO);
            throw new IllegalArgumentException(PeliculaSerieUtil.EXISTE_POR_TITULO);
        }

        Optional<Genero> optionalGenero = this.generoService
                .buscarPorId(dto.getGeneroId());

        if(optionalGenero.isPresent()){

            PeliculaSerie peliculaSerie = this.peliculaSerieMapper.mapToEntity(dto);
            peliculaSerie.setGenero(optionalGenero.get());
            peliculaSerie.setId(id);

            return this.peliculaSerieMapper
                    .mapToDto(this.peliculaSerieRepository.actualizar(id, peliculaSerie));

        } else {
            log.info("Ocurrio una validacion personalizada, en el metodo actualizar(): " + PeliculaSerieUtil.NO_EXISTE_GENERO_POR_NOMBRE);
            throw new IllegalArgumentException(PeliculaSerieUtil.NO_EXISTE_GENERO_POR_NOMBRE);
        }

    }

}
