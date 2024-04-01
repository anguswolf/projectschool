package com.exercise.projectschool.controller.impl;

import com.exercise.projectschool.controller.TeacherController;
import com.exercise.projectschool.dto.StudentDTO;
import com.exercise.projectschool.dto.TeacherDTO;
import com.exercise.projectschool.entity.TeacherEntity;
import com.exercise.projectschool.model.Teacher;
import com.exercise.projectschool.service.TeacherService;
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
@RequestMapping(path= "/teacher")
@Tag(name = "Teacher Management")
public class TeacherControllerImpl implements TeacherController {

    private final TeacherService teacherService;

    @Hidden
    @GetMapping(path = "/{serialNumber}")
    public ResponseEntity<List<TeacherEntity>> getTeacherBySerialNumber(@PathVariable String serialNumber) {
        return teacherService.getTeacherBySerialNumber(serialNumber);
    }

    @Operation(
            description = "Get Teacher by SerialNumber with JPA",
            summary = "Return List of Teacher by SerialNumber with JPA",
            responses = {
                    @ApiResponse(
                            description = "Find Teacher by SerialNumber: (SerialNumber)",
                            responseCode = "200",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = StudentDTO.class),
                                    examples = @ExampleObject(value = """
                                                {
                                                    "id": 1,
                                                    "name": "Michael",
                                                    "email": "michael@gmail.com",
                                                    "matter": "math",
                                                    "school": "Colosseo",
                                                    "serialNumber": "12345",
                                                    "classRoom": "1B",
                                                    "students": []
                                                }
                                            """)
                            )
                    ),
                    @ApiResponse(
                            description = "Not found Teacher by SerialNumber: (serialNumber)",
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
    public ResponseEntity<TeacherDTO> getTeacherWithJpa(@PathVariable String serialNumber) {
        return teacherService.getTeacherBySerialNumberWithJpa(serialNumber);
    }
    @Hidden
    @GetMapping(path = "/all")
    public ResponseEntity<List<TeacherEntity>> getAllTeachers() {
        return teacherService.getAllTeachers();
    }

    @Operation(
            description = "Get All Teachers with JPA",
            summary = "Return List of Teachers by SerialNumber with JPA",
            responses = {
                    @ApiResponse(
                            description = "Find all Teachers",
                            responseCode = "200",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = StudentDTO.class),
                                    examples = @ExampleObject(value = """
                                                {
                                                    "id": 1,
                                                    "name": "Michael",
                                                    "email": "michael@gmail.com",
                                                    "matter": "math",
                                                    "school": "Colosseo",
                                                    "serialNumber": "12345",
                                                    "classRoom": "1B",
                                                    "students": []
                                                }
                                            """)
                            )
                    ),
                    @ApiResponse(
                            description = "Not found Teachers",
                            responseCode = "406",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = StudentDTO.class),
                                    examples = @ExampleObject(value = "[]")
                            )
                    )
            }
    )
    @GetMapping(path = "/all/data/jpa")
    public ResponseEntity<List<TeacherDTO>> getAllTeachersWithJpa() {
        return teacherService.getAllTeachersWithJpa();
    }

    @Operation(
            description = "Add Teacher",
            summary = "Add Teacher",
            responses = {
                    @ApiResponse(
                            description = "Add Teacher",
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
                            description = "Teacher already present in the DB",
                            responseCode = "406",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = StudentDTO.class),
                                    examples = @ExampleObject(value = "[]")
                            )
                    )
            }
    )
    @PostMapping(path = "/add")
    public ResponseEntity<Void> addTeacher(@RequestBody List<Teacher> teachers) {
        return teacherService.addTeachers(teachers);
    }

    @Operation(
            description = "Update Teacher",
            summary = "Update Teacher",
            responses = {
                    @ApiResponse(
                            description = "Updated Teacher",
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
                            description = "No teacher found to update",
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
    public ResponseEntity<Void> updateTeacher(@RequestBody Teacher teacher) {
        return teacherService.updateTeacher(teacher);
    }

    @Operation(
            description = "Delete Teacher by SerialNumber",
            summary = "Delete Teacher by SerialNumber",
            responses = {
                    @ApiResponse(
                            description = "Delete Teacher by SerialNumber",
                            responseCode = "200",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = StudentDTO.class),
                                    examples = @ExampleObject(value = "[]")
                            )
                    ),
                    @ApiResponse(
                            description = "No teacher to eliminate with same SerialNumber",
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
    public ResponseEntity<Void> deleteTeacherBySerialNumber(@PathVariable String serialNumber) {
        return teacherService.deleteTeacherBySerialNumber(serialNumber);
    }

    @Operation(
            description = "Delete all Teachers",
            summary = "Delete all Teachers",
            responses = {
                    @ApiResponse(
                            description = "Delete all Teachers",
                            responseCode = "200",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = StudentDTO.class),
                                    examples = @ExampleObject(value = "[]")
                            )
                    ),
                    @ApiResponse(
                            description = "No teachers to eliminate",
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
    public ResponseEntity<Void> deleteAllTeachers() {
        return teacherService.deleteAllTeachers();
    }
}
