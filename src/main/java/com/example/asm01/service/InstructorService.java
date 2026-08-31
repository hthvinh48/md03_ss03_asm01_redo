package com.example.asm01.service;

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

    public List<Instructor> getAllInstructors() {
        return instructorRepository.findAll();
    }

    public Instructor getInstructorById(long id) {
        return instructorRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Instructor with id " + id + " not found")
        );
    }

    public long findMaxId() {
        List<Instructor> instructors = this.getAllInstructors();
        return instructors.stream().mapToLong(Instructor::getId).max().orElse(0);
    }

    public Instructor createInstructor(Instructor instructor) {
        instructor.setId(findMaxId() + 1);
        return instructorRepository.create(instructor);
    }

    public Instructor updateInstructor(long id, Instructor instructor) {
        return instructorRepository.update(id, instructor);
    }

    public Instructor deleteInstructorById(long id) {
        return instructorRepository.deleteById(id);
    }
}
