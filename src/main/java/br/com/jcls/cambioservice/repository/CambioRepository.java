package br.com.jcls.cambioservice.repository;

import br.com.jcls.cambioservice.model.Cambio;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CambioRepository extends JpaRepository<Cambio, Long> {
    @Cacheable(cacheNames = "cambio-expire")
    Cambio findByFromAndTo(String from, String to);

    @Cacheable(value = "cambio", unless = "#a0=='Foundation'")
    Optional<Cambio> findFirstByTo(String to);
}
