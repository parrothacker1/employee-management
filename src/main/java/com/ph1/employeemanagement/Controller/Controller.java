package com.ph1.employeemanagement.Controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import com.ph1.employeemanagement.Employee.Employee;
import com.ph1.employeemanagement.Employee.Empl;
import java.util.ArrayList;

@RestController
@RequestMapping(value="/api")
public class Controller {
    private Employee empl=new Employee("parrothacker1","","127.0.0.1:5432");
    @GetMapping(value="/")
    public ResponseEntity<ArrayList<Empl>> getlist() {
        ArrayList<Empl> arr=empl.list();
        return new ResponseEntity<>(arr,HttpStatus.OK);
    }
    @PostMapping(value="/")
    public ResponseEntity<Boolean> addempl(@RequestBody Empl employee) {
        Boolean val=empl.insert(employee);
        HttpStatus status;
        if (val) {
            status=HttpStatus.CREATED;
        } else {
            status=HttpStatus.NOT_MODIFIED;
        }
        return new ResponseEntity<>(val,status);
    }
    @PutMapping(value="/")
    public ResponseEntity<Boolean> updateempl(@RequestBody Empl employee) {
        Boolean val=empl.edit(employee);
        HttpStatus status;
        if (val) {
            status=HttpStatus.OK;
        } else {
            status=HttpStatus.NOT_MODIFIED;
        }
        return new ResponseEntity<>(val,status);
    }
    @DeleteMapping(value="/")
    public ResponseEntity<Boolean> deleteempl(@RequestBody Integer id) {
        Boolean val=empl.delete(id);
        return new ResponseEntity<>(val,HttpStatus.OK);
    }
}

