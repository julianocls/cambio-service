package br.com.jcls.cambioservice.controller;

import br.com.jcls.cambioservice.model.Cambio;
import br.com.jcls.cambioservice.repository.CambioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.math.RoundingMode;

@RestController
@RequestMapping("cambio-service")
public class CambioController {

    @Autowired
    private Environment environment;

    @Autowired
    private CambioRepository cambioRepository;

    @GetMapping("/{amount}/{from}/{to}")
    public Cambio getCambio(
            @PathVariable("amount")BigDecimal amount,
            @PathVariable("from") String from,
            @PathVariable("to") String to
            ) {

        var cambio = cambioRepository.findByFromAndTo(from, to);
        if (cambio == null ) { throw new RuntimeException("Currency unsuported"); }

        var port = environment.getProperty("local.server.port");
        var conversionFactor = cambio.getConversionFactor();
        var convertedValue = conversionFactor.multiply(amount);
        cambio.setConvertedValue(convertedValue.setScale(2, RoundingMode.CEILING));
        cambio.setEnvironment(port);

        return cambio;
    }

}