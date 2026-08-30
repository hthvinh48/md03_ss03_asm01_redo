package com.example.asm01.controller;

import com.example.asm01.model.Instructor;
import com.example.asm01.service.InstructorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/instructors")
public class InstructorController {
    private final InstructorService instructorService;

    public InstructorController(InstructorService instructorService) {
        this.instructorService = instructorService;
    }

    @GetMapping
    public ResponseEntity<List<Instructor>> findAll() {
        List<Instructor> instructors = instructorService.getAllInstructors();
        return ResponseEntity.ok(instructors);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Instructor> findById(@PathVariable long id) {
        Instructor instructor = instructorService.getInstructorById(id);

        if (instructor == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(instructor);
    }

    @PostMapping
    public ResponseEntity<Instructor> insert(@RequestBody Instructor instructor) {
        Instructor newInstructor = instructorService.createInstructor(instructor);
        return ResponseEntity.ok(newInstructor);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Instructor> update(@PathVariable long id, @RequestBody Instructor instructor) {
        Instructor updatedInstructor = instructorService.updateInstructor(id, instructor);

        if (updatedInstructor == null) return ResponseEntity.notFound().build();

        return ResponseEntity.ok(updatedInstructor);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Instructor> delete(@PathVariable long id) {
        Instructor instructor = instructorService.deleteInstructorById(id);

        if (instructor == null) return ResponseEntity.notFound().build();

        return ResponseEntity.ok(instructor);
    }
}
