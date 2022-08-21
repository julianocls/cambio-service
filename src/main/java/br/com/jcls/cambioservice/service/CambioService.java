package br.com.jcls.cambioservice.service;

import br.com.jcls.cambioservice.model.Cambio;
import br.com.jcls.cambioservice.repository.CambioRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;


@Service
@Slf4j
public class CambioService {

    @Autowired
    private Environment environment;

    @Autowired
    private CambioRepository cambioRepository;

    public Cambio findByFromAndTo(String from, String to, BigDecimal amount) {

        var cambio = cambioRepository.findByFromAndTo(from, to);
        log.info("Consulta realizada em from: %s, to %s", from, to);

        if (cambio == null ) { throw new RuntimeException("Currency unsuported"); }

        var port = environment.getProperty("local.server.port");
        var conversionFactor = cambio.getConversionFactor();
        var convertedValue = conversionFactor.multiply(amount);
        cambio.setConvertedValue(convertedValue.setScale(2, RoundingMode.CEILING));
        cambio.setEnvironment(port);

        return cambio;
    }
}
