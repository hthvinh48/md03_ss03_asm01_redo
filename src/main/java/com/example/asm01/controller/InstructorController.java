package com.example.asm01.controller;

import com.example.asm01.dto.InstructorDetail;
import com.example.asm01.model.Instructor;
import com.example.asm01.response.ApiResponse;
import com.example.asm01.service.InstructorService;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<ApiResponse<List<InstructorDetail>>> findAll() {
        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "Fetched instructor details successfully",
                        instructorService.getInstructorDetail()
                )
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Instructor>> findById(@PathVariable long id) {
        try {
            return ResponseEntity.ok(
                    new ApiResponse<>(
                            true,
                            "get data successfully",
                            instructorService.getInstructorById(id)
                    )
            );
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ApiResponse<>(
                            false,
                            e.getMessage(),
                            null
                    )
            );
        }
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Instructor>> insert(@RequestBody Instructor instructor) {
        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "create a new instuctor successfully",
                        instructorService.createInstructor(instructor)
                )
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Instructor>> update(@PathVariable long id, @RequestBody Instructor instructor) {
        try {
            return ResponseEntity.ok(
                    new ApiResponse<>(
                            true,
                            "update instructor successfully",
                            instructorService.updateInstructor(id, instructor)
                    )
            );
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ApiResponse<>(
                            false,
                            e.getMessage(),
                            null
                    )
            );
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Instructor>> delete(@PathVariable long id) {
        try {
            return ResponseEntity.ok(
                    new ApiResponse<>(
                            true,
                            "delete instructor successfully",
                            instructorService.deleteInstructorById(id)
                    )
            );
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ApiResponse<>(
                            false,
                            e.getMessage(),
                            null
                    )
            );
        }
    }
}
