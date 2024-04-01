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
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/student")
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
                            responseCode = "406",
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

    @Hidden
    @GetMapping(path = "/all")
    public ResponseEntity<List<StudentEntity>> getAllStudents() {
        return studentService.getAllStudents();
    }

    @Operation(
            description = "Get All Students with JPA",
            summary = "Return List of all Students by with JPA",
            responses = {
                    @ApiResponse(
                            description = "Find all students",
                            responseCode = "200",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = StudentDTO.class),
                                    examples = @ExampleObject(value = """
                                                [
                                                            {
                                                                "id": 1,
                                                                "name": "Michael",
                                                                "city": "Rome",
                                                                "capital": "Italy",
                                                                "age": "sesanta",
                                                                "school": "Colosseo",
                                                                "serialNumber": "76543",
                                                                "classRoom": "3B",
                                                                "teachers": []
                                                            },
                                                            {
                                                                "id": 2,
                                                                "name": "Augusto",
                                                                "city": "Rome",
                                                                "capital": "Italy",
                                                                "age": "sesanta",
                                                                "school": "Colosseo",
                                                                "serialNumber": "346345",
                                                                "classRoom": "3B",
                                                                "teachers": []
                                                            }
                                                ]
                                            """)
                            )
                    ),
                    @ApiResponse(
                            description = "Not found students",
                            responseCode = "406",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = StudentDTO.class),
                                    examples = @ExampleObject(value = "[]")
                            )
                    )
            }
    )
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping(path = "/all/data/jpa")
    public ResponseEntity<List<StudentDTO>> getAllStudentsWithJpa() {
        return studentService.getAllStudentsWithJpa();
    }

    @Operation(
            description = "Add Student",
            summary = "Add Student",
            responses = {
                    @ApiResponse(
                            description = "Add student",
                            responseCode = "200",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = StudentDTO.class),
                                    examples = @ExampleObject(value = """
                                                [
                                                    {
                                                        "name": "Augusto",
                                                        "city": "Milano",
                                                        "capital": "Italy",
                                                        "age": "sesanta",
                                                        "school": "Colosseo",
                                                        "serialNumber": "346345",
                                                        "classRoom":"3B"
                                                    }
                                                ]
                                            """)
                            )
                    ),
                    @ApiResponse(
                            description = "Student already present in the DB",
                            responseCode = "400",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = StudentDTO.class),
                                    examples = @ExampleObject(value = "[]")
                            )
                    )
            }
    )
    @PostMapping(path = "/add")
    public ResponseEntity<Void> addStudents(@RequestBody List<Student> students) {
        return studentService.addStudents(students);
    }

    @Operation(
            description = "Add student with an associate professor",
            summary = "Return List student with an associate professor",
            responses = {
                    @ApiResponse(
                            description = "Add student with an associate professor",
                            responseCode = "200",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = StudentDTO.class),
                                    examples = @ExampleObject(value = """
                                               {
                                                           "name": "Harry Potter",
                                                           "city": "Rome",
                                                           "capital": "italy",
                                                           "age": "sesanta",
                                                           "school": "Politecnico",
                                                           "serialNumber": "3333",
                                                           "classRoom":"4A",
                                                           "teacherEntityList" : [
                                                               {
                                                               "name": "Albus",
                                                               "email": "m@gmail.com",
                                                               "matter": "math",
                                                               "school": "Colosseo",
                                                               "serialNumber": "2222",
                                                               "classRoom":"4A"
                                                               },
                                                               {
                                                               "name": "Piton",
                                                               "email": "m@gmail.com",
                                                               "matter": "math",
                                                               "school": "Colosseo",
                                                               "serialNumber": "1111",
                                                               "classRoom":"4A"
                                                           }
                                                           ]
                                                       }
                                            """)
                            )
                    ),
                    @ApiResponse(
                            description = "Student with an associate professor already present in the DB",
                            responseCode = "400",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = StudentDTO.class),
                                    examples = @ExampleObject(value = "[]")
                            )
                    )
            }
    )
    @PostMapping(path = "add/student/associated/teacher")
    public ResponseEntity<Void> addStudentAssociatedTeacher(@RequestBody StudentEntity student) {
        return studentService.addStudentAssociatedTeacher(student);
    }

    @Operation(
            description = "Update Student",
            summary = "Update Student",
            responses = {
                    @ApiResponse(
                            description = "Updated Student",
                            responseCode = "200",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = StudentDTO.class),
                                    examples = @ExampleObject(value = """
                                               {
                                                    "name": "Augusto",
                                                    "city": "Roma",
                                                    "age": "sesanta",
                                                    "school": "Bicocca",
                                                    "serialNumber": "1235"
                                               }
                                            """)
                            )
                    ),
                    @ApiResponse(
                            description = "No students found to update",
                            responseCode = "406",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = StudentDTO.class),
                                    examples = @ExampleObject(value = "[]")
                            )
                    )
            }
    )
    @PutMapping(path = "/update")
    public ResponseEntity<Void> updateStudent(@RequestBody Student student) {
        return studentService.updateStudent(student);
    }

    @Operation(
            description = "Delete student by SerialNumber",
            summary = "Delete student by SerialNumber",
            responses = {
                    @ApiResponse(
                            description = "Delete student by SerialNumber",
                            responseCode = "200",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = StudentDTO.class),
                                    examples = @ExampleObject(value = "[]")
                            )
                    ),
                    @ApiResponse(
                            description = "No student to eliminate with same SerialNumber",
                            responseCode = "406",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = StudentDTO.class),
                                    examples = @ExampleObject(value = "[]")
                            )
                    )
            }
    )
    @DeleteMapping(path = "/delete/{serialNumber}")
    public ResponseEntity<Void> deleteStudent(@PathVariable String serialNumber) {
        return studentService.deleteStudentBySerialNumber(serialNumber);
    }

    @Operation(
            description = "Delete Student with an Associate Professor with the serialNumber",
            summary = "Delete Student with an Associate Professor with the serialNumber",
            responses = {
                    @ApiResponse(
                            description = "Delete Student with an Associate Professor with the serialNumber",
                            responseCode = "200",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = StudentDTO.class),
                                    examples = @ExampleObject(value = "[]")
                            )
                    ),
                    @ApiResponse(
                            description = "No student to eliminate with same SerialNumber",
                            responseCode = "400",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = StudentDTO.class),
                                    examples = @ExampleObject(value = "[]")
                            )
                    )
            }
    )
    @DeleteMapping(path = "/delete/student/associated/teacher/{serialNumber}")
    public ResponseEntity<Void> deleteStudentAssociatedTeacher(@PathVariable String serialNumber) {
        return studentService.deleteStudentAssociatedTeacher(serialNumber);
    }


    @Operation(
            description = "Delete all students",
            summary = "Delete all students",
            responses = {
                    @ApiResponse(
                            description = "Delete all students",
                            responseCode = "200",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = StudentDTO.class),
                                    examples = @ExampleObject(value = "[]")
                            )
                    ),
                    @ApiResponse(
                            description = "No students to eliminate",
                            responseCode = "406",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = StudentDTO.class),
                                    examples = @ExampleObject(value = "[]")
                            )
                    )
            }
    )
    @DeleteMapping(path = "/delete/all")
    public ResponseEntity<Void> deleteAllStudents() {
        return studentService.deleteAllStudents();
    }

}
