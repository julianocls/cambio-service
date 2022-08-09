package br.com.jcls.cambioservice.service;

import br.com.jcls.cambioservice.model.Cambio;
import br.com.jcls.cambioservice.repository.CambioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
public class CambioService {

    @Autowired
    private Environment environment;

    @Autowired
    private CambioRepository cambioRepository;

    public Cambio findByFromAndTo(String from, String to, BigDecimal amount) {

        var cambio = cambioRepository.findByFromAndTo(from, to);
        System.out.printf("Consulta realizada em from: "+from+", to:"+ to);

        if (cambio == null ) { throw new RuntimeException("Currency unsuported"); }

        var port = environment.getProperty("local.server.port");
        var conversionFactor = cambio.getConversionFactor();
        var convertedValue = conversionFactor.multiply(amount);
        cambio.setConvertedValue(convertedValue.setScale(2, RoundingMode.CEILING));
        cambio.setEnvironment(port);

        return cambio;
    }
}
