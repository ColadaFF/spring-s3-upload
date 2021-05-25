package com.example.uploadclient;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/files")
public class FileController {
    private final FileStorage fileStorage;

    public FileController(FileStorage fileStorage) {
        this.fileStorage = fileStorage;
    }


    @PostMapping("/{filename}")
    public ResponseEntity<Void> uploadFileHandler(
            @PathVariable String filename,
            @RequestParam("file") MultipartFile file
    ) throws IOException {
        fileStorage.uploadFile(filename, file.getInputStream(), file.getSize());
        return ResponseEntity.ok(null);
    }


    @GetMapping
    public List<String> getFiles() {
        return fileStorage.listFiles();
    }
}
