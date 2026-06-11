package br.com.licitaai.repository;

import br.com.licitaai.model.Edital;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EditalRepository
        extends JpaRepository<Edital, Long> {

}