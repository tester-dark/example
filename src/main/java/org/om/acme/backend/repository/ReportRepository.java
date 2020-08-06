package org.om.acme.backend.repository;

import org.om.acme.backend.model.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ReportRepository extends JpaRepository<Report, Long> {
    @Query("select r from Report r where r.date = :date")
    List<Report> searchReports(@Param("date") LocalDate date);

    List<Report> getByDate(LocalDate date);
}