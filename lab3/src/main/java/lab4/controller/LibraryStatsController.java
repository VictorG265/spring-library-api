package lab4.controller;

import lab4.dto.LibraryStatsDTO;
import lab4.service.LibraryStatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/library")
public class LibraryStatsController {

    @Autowired
    private LibraryStatsService libraryStatsService;

    @GetMapping("/stats")
    public ResponseEntity<LibraryStatsDTO> getStats() {
        return ResponseEntity.ok(libraryStatsService.getLibraryStats());
    }
}