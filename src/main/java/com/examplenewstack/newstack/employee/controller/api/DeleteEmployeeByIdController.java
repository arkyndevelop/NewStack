    package com.examplenewstack.newstack.employee.controller.api;


    import com.examplenewstack.newstack.employee.service.DeleteEmployeeByIdService;
    import io.swagger.v3.oas.annotations.tags.Tag;
    import org.springframework.http.ResponseEntity;
    import org.springframework.web.bind.annotation.DeleteMapping;
    import org.springframework.web.bind.annotation.PathVariable;
    import org.springframework.web.bind.annotation.RequestMapping;
    import org.springframework.web.bind.annotation.RestController;


    @RestController
    @RequestMapping("/employee")
    @Tag(name = "Funcionário")
    public class DeleteEmployeeByIdController {

        private  final DeleteEmployeeByIdService employeeService;

        public DeleteEmployeeByIdController(DeleteEmployeeByIdService employeeService) {
            this.employeeService = employeeService;
        }

        @DeleteMapping("/deleteEmployee/{id}")

        public ResponseEntity<?> deleteByCPF( @PathVariable Long id) {


                employeeService.deleteEmployeeById(id);

                return ResponseEntity.ok().build();




        }
        }