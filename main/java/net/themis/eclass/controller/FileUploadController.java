package net.themis.eclass.controller;

import net.themis.eclass.model.File;
import net.themis.eclass.payload.Response;
import net.themis.eclass.service.impl.FileServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/teacher/courses")
public class FileUploadController {
    @Autowired
    private FileServiceImpl fileService;

    @PostMapping("/{courseId}/uploadFile")
    public Response uploadFile(@PathVariable Long courseId, @RequestParam("file") MultipartFile file) {
        File fileName = fileService.storeFile(courseId,file);

        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/downloadFile/")
                .path(fileName.getFileName())
                .toUriString();

        return new Response(fileName.getFileName(), fileDownloadUri,
                file.getContentType(), file.getSize());
    }

    @PostMapping("/{courseId}/uploadMultipleFiles")
    public List< Response > uploadMultipleFiles(@PathVariable Long courseId,@RequestParam("files") MultipartFile[] files) {
        return Arrays.asList(files)
                .stream()
                .map(file -> uploadFile(courseId,file))
                .collect(Collectors.toList());
    }
}
