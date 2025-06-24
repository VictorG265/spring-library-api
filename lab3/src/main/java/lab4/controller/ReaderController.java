package lab4.controller;

import jakarta.annotation.PostConstruct;
import lab4.entity.Reader;
import lab4.service.ReaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "api/reader", produces = MediaType.APPLICATION_JSON_VALUE)
public class ReaderController extends AbstractController<Reader> {

    @Autowired
    private ReaderService service;

    private HttpHeaders headers;

    @PostConstruct
    private void init() {
        headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
    }

    @Override
    public ReaderService getService() {
        return service;
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<Reader> getReaderByEmail(@PathVariable String email) {
        Reader reader = service.readByEmail(email);
        return reader == null ? new ResponseEntity<>(HttpStatus.NOT_FOUND) :
                new ResponseEntity<>(reader, headers, HttpStatus.OK);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<List<Reader>> getReadersByName(@PathVariable String name) {
        List<Reader> readers = service.readByName(name);
        return readers.isEmpty() ? new ResponseEntity<>(HttpStatus.NOT_FOUND) :
                new ResponseEntity<>(readers, headers, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Reader> updateReader(@PathVariable Long id, @RequestBody Reader reader) {
        Reader updatedReader = service.update(id, reader);
        return ResponseEntity.ok(updatedReader);
    }

    @GetMapping("/range")
    public List <Reader> findInRange(
            @RequestParam Long a,
            @RequestParam Long b
    ){
        List <Reader> readerList = service.findByIdBetween(a, b);
        return ResponseEntity.ok(readerList).getBody();
    }
}
