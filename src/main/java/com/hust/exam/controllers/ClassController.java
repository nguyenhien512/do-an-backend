package com.hust.exam.controllers;

import com.hust.exam.DTO.ClassDto;
import com.hust.exam.service.ClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/classes")
@CrossOrigin
public class ClassController {

        @Autowired
        ClassService classService;

        @GetMapping("/forTeacher")
        @PreAuthorize("hasAuthority('TEACHER')")
        public List<ClassDto> getClassesCreateByTeacher() {
                Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
                String currentUserName = null;
                if (!(authentication instanceof AnonymousAuthenticationToken)) {
                        currentUserName = authentication.getName();
                }
                return classService.findByCreateBy(currentUserName);
        }

        @GetMapping("/{id}")
        @PreAuthorize("hasAuthority('TEACHER')")
        public ResponseEntity<ClassDto> getById(@PathVariable("id") int id) {
                return new ResponseEntity<>(classService.findById(id), HttpStatus.OK);
        }

        @PostMapping("")
        @PreAuthorize("hasAuthority('TEACHER')")
        public ResponseEntity<ClassDto> createClass(@RequestBody ClassDto dto) {
                Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
                String currentUserName = null;
                if (!(authentication instanceof AnonymousAuthenticationToken)) {
                        currentUserName = authentication.getName();
                }
                return new ResponseEntity<>(classService.createClass(currentUserName,dto), HttpStatus.CREATED);
        }

        @PutMapping("/{id}/addStudent")
        @PreAuthorize("hasAuthority('TEACHER')")
        public ResponseEntity<ClassDto> addStudent(@PathVariable int id, @RequestParam String student) {
                return new ResponseEntity<>(classService.addStudent(id, student), HttpStatus.OK);
        }

        @PutMapping("/{id}/removeStudent")
        @PreAuthorize("hasAuthority('TEACHER')")
        public ResponseEntity<ClassDto> removeStudent(@PathVariable int id, @RequestParam String student) {
                return new ResponseEntity<>(classService.removeStudent(id, student), HttpStatus.OK);
        }

        @DeleteMapping("/{id}")
        @PreAuthorize("hasAuthority('TEACHER')")
        public ResponseEntity<?> deleteClass(@PathVariable("id") int id) {
                classService.deleteClass(id);
                return new ResponseEntity<>(HttpStatus.OK);
        }

}
