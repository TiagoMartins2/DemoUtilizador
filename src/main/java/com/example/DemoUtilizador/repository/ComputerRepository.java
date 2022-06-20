package com.example.DemoUtilizador.repository;

import com.example.DemoUtilizador.model.Computer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.yaml.snakeyaml.tokens.CommentToken;

import javax.xml.stream.events.Comment;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public interface ComputerRepository extends JpaRepository<Computer, Integer> {

    @Query(value = "SELECT * FROM COMPUTER", nativeQuery = true)
    public List<Computer> getRecentComputer();


    List<Computer> findByLocalDateAfter(LocalDateTime startDateTime);
}
