package net.themis.eclass.repository;

import net.themis.eclass.model.File;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepository extends JpaRepository<File,String> {
}
