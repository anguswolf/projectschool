package com.exercise.projectschool.controller.impl;

import com.exercise.projectschool.controller.StudentController;
import com.exercise.projectschool.dto.StudentDTO;
import com.exercise.projectschool.entity.StudentEntity;
import com.exercise.projectschool.model.Student;
import com.exercise.projectschool.service.StudentService;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path= "/student")
@Tag(name = "Student Management")
public class StudentControllerImpl implements StudentController {
    private final StudentService studentService;


    @GetMapping(path = "/{serialNumber}")
    @Hidden
    public ResponseEntity<List<StudentEntity>> getStudent(@PathVariable String serialNumber) {
        return studentService.getStudentBySerialNumber(serialNumber);
    }

    @Operation(
            description = "Get Student by SerialNumber with JPA",
            summary = "Return List of Student by SerialNumber with JPA",
            responses = {
                    @ApiResponse(
                            description = "Find student by SerialNumber: (SerialNumber)",
                            responseCode = "200",
                            content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = StudentDTO.class),
                            examples = @ExampleObject(value = """
                                        {
                                          "id": 1,
                                          "name": "John Doe",
                                          "city": "New York",
                                          "capital": "Washington D.C.",
                                          "age": "25",
                                          "school": "XYZ High School",
                                          "serialNumber": "S123",
                                          "classRoom": "2A",
                                          "teachers": [
                                            {
                                              "id": 1,
                                              "name": "Jane Smith",
                                              "email": "jane@example.com",
                                              "matter": "Math",
                                              "school": "XYZ High School",
                                              "serialNumber": "T123",
                                              "classRoom": "2A",
                                              "students": []
                                            }
                                          ]
                                        }
                                    """)
                    )
                    ),
                    @ApiResponse(
                            description = "Not found student by SerialNumber: (serialNumber)",
                            responseCode = "404",
                            content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = StudentDTO.class),
                                    examples = @ExampleObject(value = "[]")
                    )
                    )
            }
    )
    @GetMapping(path = "/data/jpa/{serialNumber}")
    public ResponseEntity<StudentDTO> getStudentWithJpa(@PathVariable String serialNumber) {
        return studentService.getStudentBySerialNumberWithJpa(serialNumber);
    }

    @GetMapping(path= "/all")
    public ResponseEntity<List<StudentEntity>> getAllStudents() {
        return studentService.getAllStudents();
    }

    @GetMapping(path= "/all/data/jpa")
    public ResponseEntity<List<StudentDTO>> getAllStudentsWithJpa() {
        return studentService.getAllStudentsWithJpa();
    }

    @PostMapping(path= "/add")
    public ResponseEntity<Void> addStudents(@RequestBody List<Student> students) {
       return studentService.addStudents(students);
    }

    @PostMapping(path = "add/student/associated/teacher")
    public ResponseEntity<Void> addStudentAssociatedTeacher(@RequestBody StudentEntity student) {
        return studentService.addStudentAssociatedTeacher(student);
    }

    @DeleteMapping(path = "/delete/{serialNumber}")
    public ResponseEntity<Void> deleteStudent(@PathVariable String serialNumber) {
        return studentService.deleteStudentBySerialNumber(serialNumber);
    }

    @DeleteMapping(path = "/delete/student/associated/teacher/{serialNumber}")
    public ResponseEntity<Void> deleteStudentAssociatedTeacher(@PathVariable String serialNumber){
        return studentService.deleteStudentAssociatedTeacher(serialNumber );
    }

    @PutMapping(path = "/update")
    public ResponseEntity<Void> updateStudent(@RequestBody Student student) {
        return studentService.updateStudent(student);
    }

    @DeleteMapping(path = "/delete/all")
    public ResponseEntity<Void> deleteAllStudents() {
        return studentService.deleteAllStudents();
    }

}
