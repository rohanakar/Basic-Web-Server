package rishabh.server.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rishabh.server.models.Student;
import rishabh.server.repositories.StudentRepository;

@RestController
@RequestMapping("/api/v1/students")
public class StudentController {
    
    @Autowired
    private StudentRepository studentRepository;
    
    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudent(@PathVariable( "id") Long id){
        
        Student student = studentRepository.findById(id).orElse(null);
        return ResponseEntity.ok(student);
    }
    
    @GetMapping("/")
    public ResponseEntity<String> sayHello(){
        
        return ResponseEntity.ok("HELLO_WORLD");
    }
    
    @PostMapping("")
    public ResponseEntity<Student> createStudent(@RequestBody Student student ){
        return ResponseEntity.ok(studentRepository.save(student));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Student> updateStudent(@RequestBody Student student , @PathVariable("id") Long id){
        Student studentFromDb = studentRepository.findById(id).orElse(null);
        studentFromDb.setName(student.getName());
        return ResponseEntity.ok(studentRepository.save(studentFromDb));
    }
}
