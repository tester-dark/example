package org.om.acme.backend.service;

import org.om.acme.backend.model.Report;
import org.om.acme.backend.repository.ReportRepository;
import org.om.acme.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

@Service
public class ReportService {
    ReportRepository repository;
    UserService service;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public ReportService(ReportRepository repository, UserService service) {
        this.service = service;
        this.repository = repository;
    }

    public void createReport(Report report) {
        repository.save(report);
    }

    public List<Report> listReport(LocalDate date) {
        try {
            return jdbcTemplate.query("SELECT id_report, date, hour, id_user FROM reports WHERE date = " + String.valueOf(date),
                    (rs, rowNum) -> new Report(rs.getLong("id_report"),
                            Instant.ofEpochMilli((rs.getDate("date").getTime())).atZone(ZoneId.systemDefault()).toLocalDate(),
                            rs.getTime("hour"), service.readUser(rs.getLong("id_user"))));
        }catch (Exception ex){
            return new ArrayList<>();
        }
    }
}