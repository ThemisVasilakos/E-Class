package net.themis.eclass.service.impl;

import net.themis.eclass.exception.FileStorageException;
import net.themis.eclass.model.Course;
import net.themis.eclass.model.File;
import net.themis.eclass.repository.CourseRepository;
import net.themis.eclass.repository.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;

@Service
public class FileServiceImpl {

    @Autowired
    private FileRepository fileRepository;

    @Autowired
    private CourseRepository courseRepository;

    public File storeFile(Long courseId,MultipartFile file) {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        try {
            if (fileName.contains("..")) {
                throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
            }

            File dbFile = new File(fileName, file.getContentType(), file.getBytes());

            fileRepository.save(dbFile);

            Course course = courseRepository.findById(courseId).get();
            course.addFile(dbFile);
            courseRepository.save(course);

            return dbFile;
        } catch (IOException ex) {
            throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
        }
    }

    public File getFile(String fileId) throws FileNotFoundException {
        return fileRepository.findById(fileId).get();
               // .orElseThrow(() -> new FileNotFoundException("File not found with id " + fileId));
    }
}
