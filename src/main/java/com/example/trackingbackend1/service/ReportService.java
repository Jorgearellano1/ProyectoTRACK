package com.example.trackingbackend1.service;

import com.example.trackingbackend1.model.Report;
import com.example.trackingbackend1.repository.ReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReportService {

    @Autowired
    private ReportRepository reportRepository;

    public List<Report> getAllReports() {
        return reportRepository.findAll();
    }

    public Optional<Report> getReportById(Long id) {
        return reportRepository.findById(id);
    }

    public Report saveReport(Report report) {
        return reportRepository.save(report);
    }

    public Report updateReport(Long id, Report reportDetails) {
        Optional<Report> existingReport = reportRepository.findById(id);
        if (existingReport.isPresent()) {
            Report report = existingReport.get();
            report.setTitle(reportDetails.getTitle());
            report.setDescription(reportDetails.getDescription());
            report.setAuthor(reportDetails.getAuthor());
            report.setDate(reportDetails.getDate());
            return reportRepository.save(report);
        } else {
            throw new RuntimeException("Reporte no encontrado");
        }
    }

    public void deleteReport(Long id) {
        reportRepository.deleteById(id);
    }
}
