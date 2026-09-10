package com.example.asm01.repository;

import com.example.asm01.model.StudentEnrollment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentEnrollmentRepository extends JpaRepository<StudentEnrollment, Long> {
}
