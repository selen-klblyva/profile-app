package az.edu.turing.profileapp.controller;


import lombok.SneakyThrows;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("/api/v1/files")
public class FileController {

    public static final String IMAGES_DIR="src/main/resources/images/";

    @SneakyThrows
    @PostMapping("/upload")
    public ResponseEntity<Void> uploadFile(@RequestBody String file) {
        Path path= Paths.get(IMAGES_DIR);
        Files.writeString(path,file);
        return ResponseEntity.accepted().build();
    }


}
