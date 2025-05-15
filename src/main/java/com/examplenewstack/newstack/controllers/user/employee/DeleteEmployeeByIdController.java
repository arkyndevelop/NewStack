    package com.examplenewstack.newstack.controllers.user.employee;


    import com.examplenewstack.newstack.exceptions.client.CustomException;
    import com.examplenewstack.newstack.service.user.employee.DeleteEmployeeByIdService;
    import org.springframework.http.ResponseEntity;
    import org.springframework.web.bind.annotation.DeleteMapping;
    import org.springframework.web.bind.annotation.PathVariable;
    import org.springframework.web.bind.annotation.RestController;

    @RestController
    public class DeleteEmployeeByIdController {

        private  final DeleteEmployeeByIdService employeeService;

        public DeleteEmployeeByIdController(DeleteEmployeeByIdService employeeService) {
            this.employeeService = employeeService;
        }

        @DeleteMapping("/deleteEmployee/{id}")

        public ResponseEntity<?> deleteByCPF( @PathVariable Long id) {
            try {


                employeeService.deleteEmployeeById(id);

                return ResponseEntity.ok().build();

            } catch (Exception e){

            throw new CustomException("Erro: Nenhum Empregado cadastrado!");

            }
        }
        }