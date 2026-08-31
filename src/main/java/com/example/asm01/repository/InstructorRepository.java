package com.example.asm01.repository;

import com.example.asm01.model.Instructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class InstructorRepository {
    private final List<Instructor> instructors = new ArrayList<>(List.of(
            new Instructor(1, "Cristiano Ronaldo", "cr7@gmail.com"),
            new Instructor(2, "David Beckham", "david@gmail.com")
    ));

    public List<Instructor> findAll() {
        return new ArrayList<>(instructors);
    }

    public Optional<Instructor> findById(long id) {
        return instructors.stream().filter(i -> i.getId() == id).findFirst();
    }

    public Instructor create(Instructor instructor) {
        instructors.add(instructor);
        return instructor;
    }

    public Instructor update(long id, Instructor instructor) {
        Instructor currentInstructor = findById(id).orElseThrow(() ->
                new RuntimeException("Instructor with id " + id + " not found")
        );

        currentInstructor.setName(instructor.getName());
        currentInstructor.setEmail(instructor.getEmail());
        return currentInstructor;
    }

    public Instructor deleteById(long id) {
        Instructor currentInstructor = findById(id).orElseThrow(() ->
                new RuntimeException("Instructor with id " + id + " not found")
        );

        instructors.remove(currentInstructor);
        return currentInstructor;
    }
}
