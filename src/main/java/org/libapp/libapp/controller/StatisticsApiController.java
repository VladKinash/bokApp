package org.libapp.libapp.controller;

import com.opencsv.CSVWriter;
import org.libapp.libapp.dto.AuthorBookCountDTO;
import org.libapp.libapp.dto.AuthorBorrowCountDTO;
import org.libapp.libapp.dto.MonthlyBorrowingStatsDTO;
import org.libapp.libapp.dto.TopBorrowedBookDTO;
import org.libapp.libapp.service.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.StringWriter;
import java.util.List;

@RestController
@RequestMapping("/api/statistics")
public class StatisticsApiController {

    @Autowired
    private StatisticsService statisticsService;

    @GetMapping("/total-books")
    public long getTotalBooks() {
        return statisticsService.getTotalBookCount();
    }

    @GetMapping("/overdue-books")
    public long getOverdueBooks() {
        return statisticsService.getOverdueBookCount();
    }

    @GetMapping("/currently-borrowed")
    public long getCurrentlyBorrowedBooks() {
        return statisticsService.getCurrentlyBorrowedBookCount();
    }

    @GetMapping("/borrowings-per-month")
    public List<MonthlyBorrowingStatsDTO> getBorrowingsPerMonth() {
        return statisticsService.getBorrowingsPerMonth();
    }

    @GetMapping("/borrowings-per-month-last-year")
    public List<MonthlyBorrowingStatsDTO> getBorrowingsPerMonthForLastYear() {
        return statisticsService.getBorrowingsPerMonthForLastYear();
    }

    @GetMapping("/top-borrowed-books")
    public List<TopBorrowedBookDTO> getTopBorrowedBooks(@RequestParam(defaultValue = "10") int limit) {
        return statisticsService.getTopBorrowedBooks(limit);
    }

    @GetMapping("/total-authors")
    public long getTotalAuthors() {
        return statisticsService.getTotalAuthorCount();
    }

    @GetMapping("/authors-most-books")
    public List<AuthorBookCountDTO> getAuthorsWithMostBooks(@RequestParam(defaultValue = "10") int limit) {
        return statisticsService.getAuthorsWithMostBooks(limit);
    }

    @GetMapping("/most-borrowed-authors")
    public List<AuthorBorrowCountDTO> getMostBorrowedAuthors(@RequestParam(defaultValue = "10") int limit) {
        return statisticsService.getMostBorrowedAuthors(limit);
    }

    @GetMapping(value = "/export/total-books", produces = "text/csv")
    public ResponseEntity<String> exportTotalBooks() {
        long total = statisticsService.getTotalBookCount();
        String csv = generateSingleValueCsv("Total Books", total);
        return createCsvResponse(csv, "total_books.csv");
    }

    @GetMapping(value = "/export/overdue-books", produces = "text/csv")
    public ResponseEntity<String> exportOverdueBooks() {
        long overdue = statisticsService.getOverdueBookCount();
        String csv = generateSingleValueCsv("Overdue Books", overdue);
        return createCsvResponse(csv, "overdue_books.csv");
    }

    @GetMapping(value = "/export/currently-borrowed", produces = "text/csv")
    public ResponseEntity<String> exportCurrentlyBorrowed() {
        long borrowed = statisticsService.getCurrentlyBorrowedBookCount();
        String csv = generateSingleValueCsv("Currently Borrowed", borrowed);
        return createCsvResponse(csv, "currently_borrowed.csv");
    }

    @GetMapping(value = "/export/borrowings-per-month", produces = "text/csv")
    public ResponseEntity<String> exportBorrowingsPerMonth() {
        List<MonthlyBorrowingStatsDTO> data = statisticsService.getBorrowingsPerMonth();
        String csv = generateMonthlyStatsCsv(data);
        return createCsvResponse(csv, "borrowings_per_month.csv");
    }

    @GetMapping(value = "/export/borrowings-per-month-last-year", produces = "text/csv")
    public ResponseEntity<String> exportBorrowingsLastYear() {
        List<MonthlyBorrowingStatsDTO> data = statisticsService.getBorrowingsPerMonthForLastYear();
        String csv = generateMonthlyStatsCsv(data);
        return createCsvResponse(csv, "borrowings_last_year.csv");
    }

    @GetMapping(value = "/export/top-borrowed-books", produces = "text/csv")
    public ResponseEntity<String> exportTopBorrowedBooks(@RequestParam(defaultValue = "10") int limit) {
        List<TopBorrowedBookDTO> data = statisticsService.getTopBorrowedBooks(limit);
        String csv = generateTopBooksCsv(data);
        return createCsvResponse(csv, "top_borrowed_books.csv");
    }

    @GetMapping(value = "/export/total-authors", produces = "text/csv")
    public ResponseEntity<String> exportTotalAuthors() {
        long totalAuthors = statisticsService.getTotalAuthorCount();
        String csv = generateSingleValueCsv("Total Authors", totalAuthors);
        return createCsvResponse(csv, "total_authors.csv");
    }

    @GetMapping(value = "/export/authors-most-books", produces = "text/csv")
    public ResponseEntity<String> exportAuthorsWithMostBooks(@RequestParam(defaultValue = "10") int limit) {
        List<AuthorBookCountDTO> data = statisticsService.getAuthorsWithMostBooks(limit);
        String csv = generateAuthorsWithMostBooksCsv(data);
        return createCsvResponse(csv, "authors_most_books.csv");
    }

    @GetMapping(value = "/export/most-borrowed-authors", produces = "text/csv")
    public ResponseEntity<String> exportMostBorrowedAuthors(@RequestParam(defaultValue = "10") int limit) {
        List<AuthorBorrowCountDTO> data = statisticsService.getMostBorrowedAuthors(limit);
        String csv = generateMostBorrowedAuthorsCsv(data);
        return createCsvResponse(csv, "most_borrowed_authors.csv");
    }

    private String generateSingleValueCsv(String header, long value) {
        StringWriter writer = new StringWriter();
        try (CSVWriter csvWriter = new CSVWriter(writer)) {
            csvWriter.writeNext(new String[]{header, "Count"});
            csvWriter.writeNext(new String[]{header, String.valueOf(value)});
        } catch (Exception e) {
            throw new RuntimeException("Error generating CSV", e);
        }
        return writer.toString();
    }

    private String generateMonthlyStatsCsv(List<MonthlyBorrowingStatsDTO> data) {
        StringWriter writer = new StringWriter();
        try (CSVWriter csvWriter = new CSVWriter(writer)) {
            csvWriter.writeNext(new String[]{"Year-Month", "Borrow Count"});
            for (MonthlyBorrowingStatsDTO dto : data) {
                csvWriter.writeNext(new String[]{dto.getYearMonth(), String.valueOf(dto.getCount())});
            }
        } catch (Exception e) {
            throw new RuntimeException("Error generating CSV", e);
        }
        return writer.toString();
    }

    private String generateTopBooksCsv(List<TopBorrowedBookDTO> data) {
        StringWriter writer = new StringWriter();
        try (CSVWriter csvWriter = new CSVWriter(writer)) {
            csvWriter.writeNext(new String[]{"Book ID", "Title", "Borrow Count"});
            for (TopBorrowedBookDTO dto : data) {
                csvWriter.writeNext(new String[]{
                        dto.getBookId().toString(),
                        dto.getTitle(),
                        dto.getBorrowCount().toString()
                });
            }
        } catch (Exception e) {
            throw new RuntimeException("Error generating CSV", e);
        }
        return writer.toString();
    }

    private String generateAuthorsWithMostBooksCsv(List<AuthorBookCountDTO> data) {
        StringWriter writer = new StringWriter();
        try (CSVWriter csvWriter = new CSVWriter(writer)) {
            csvWriter.writeNext(new String[]{"Author ID", "First Name", "Last Name", "Book Count"});
            for (AuthorBookCountDTO dto : data) {
                csvWriter.writeNext(new String[]{
                        dto.getAuthorId().toString(),
                        dto.getFirstName(),
                        dto.getLastName(),
                        dto.getBookCount().toString()
                });
            }
        } catch (Exception e) {
            throw new RuntimeException("Error generating CSV", e);
        }
        return writer.toString();
    }

    private String generateMostBorrowedAuthorsCsv(List<AuthorBorrowCountDTO> data) {
        StringWriter writer = new StringWriter();
        try (CSVWriter csvWriter = new CSVWriter(writer)) {
            csvWriter.writeNext(new String[]{"Author ID", "First Name", "Last Name", "Borrow Count"});
            for (AuthorBorrowCountDTO dto : data) {
                csvWriter.writeNext(new String[]{
                        dto.getAuthorId().toString(),
                        dto.getFirstName(),
                        dto.getLastName(),
                        dto.getBorrowCount().toString()
                });
            }
        } catch (Exception e) {
            throw new RuntimeException("Error generating CSV", e);
        }
        return writer.toString();
    }

    private ResponseEntity<String> createCsvResponse(String csv, String filename) {
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + filename + "\"")
                .contentType(MediaType.parseMediaType("text/csv"))
                .body(csv);
    }
}