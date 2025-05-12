    package com.examplenewstack.newstack.controller.userController.employeeController;


    import com.examplenewstack.newstack.repository.EmployeeRepository;
    import jakarta.transaction.Transactional;
    import org.springframework.http.HttpStatus;
    import org.springframework.http.ResponseEntity;
    import org.springframework.web.bind.annotation.DeleteMapping;
    import org.springframework.web.bind.annotation.PathVariable;
    import org.springframework.web.bind.annotation.RestController;

    @RestController
    public class DeleteEmployeeController {

        private final EmployeeRepository employeeRepository;

        public DeleteEmployeeController(EmployeeRepository employeeRepository) {
            this.employeeRepository = employeeRepository;
        }

        @DeleteMapping("/deleteEmployee/{CPF}")
        @Transactional
        public ResponseEntity<String> deleteByCPF(@PathVariable String CPF) {
            try {

                if (!employeeRepository.existsByCPF(CPF)) {
                    return ResponseEntity.status(HttpStatus.NOT_FOUND)
                            .body("Funcionário com CPF " + CPF + " não encontrado");
                }


                employeeRepository.deleteByCPF(CPF);


                if (employeeRepository.existsByCPF(CPF)) {
                    throw new RuntimeException("Falha ao deletar funcionário - CPF ainda existe");
                }

                return ResponseEntity.ok("Funcionário com CPF " + CPF + " deletado com sucesso");

            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body("Erro ao excluir funcionário: " + e.getMessage());
            }
        }
    }