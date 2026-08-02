package org.edu.citas.dao.service;

import org.edu.citas.DTO.IMontoDto;
import org.edu.citas.Models.MontosModel;
import org.edu.citas.Models.NotasModel;
import org.edu.citas.dao.IMontoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
public class MontoService {

    @Autowired
    private IMontoRepo iMontoRepository;

public List<IMontoDto> getMontosAnual(int anio) {
    return iMontoRepository.MontoAnual(anio);

}
    public MontosModel createMonto(MontosModel montosModel){
    montosModel.setCreado(LocalDate.now());
    return iMontoRepository.save(montosModel);
    }

public List<IMontoDto> getMontosMensual(int mes, int anio) {
    return iMontoRepository.MontoMensual(mes, anio);
}
public List<IMontoDto> getMontoPersonalAnual(int pacienteId, int anio) {
    return iMontoRepository.MontoPersonalAnual(pacienteId, anio);
}

public Long totalAnual( int anio) {
    return iMontoRepository.TotalAnaul( anio);
}

public Long totalMensual( int anio, int mes) {
    return iMontoRepository.TotalMensual( anio, mes);
}
public Long totalPaciente( int anio, int pacienteId) {
    return iMontoRepository.TotalPorPersona( anio, pacienteId);
}




}
