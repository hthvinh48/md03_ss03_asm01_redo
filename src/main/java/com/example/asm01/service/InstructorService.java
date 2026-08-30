package com.example.asm01.service;

import com.example.asm01.model.Course;
import com.example.asm01.model.Instructor;
import com.example.asm01.repository.InstructorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InstructorService {
    private final InstructorRepository instructorRepository;

    public InstructorService(InstructorRepository instructorRepository) {
        this.instructorRepository = instructorRepository;
    }

    public List<Instructor> findAll() {
        return instructorRepository.getInstructors();
    }

    public long findMaxId() {
        List<Instructor> instructors = this.findAll();
        return instructors.stream().mapToLong(Instructor::getId).max().orElse(1);
    }

    public Instructor insert(Instructor instructor) {
        instructor.setId(findMaxId() + 1);
        return instructorRepository.insert(instructor);
    }

    public Instructor update(long id, Instructor instructor) {
        return instructorRepository.update(id, instructor);
    }

    public Instructor delete(long id) {
        return instructorRepository.delete(id);
    }
}
