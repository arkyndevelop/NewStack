package com.examplenewstack.newstack.controller.user.clientController.api;

import com.examplenewstack.newstack.entity.dto.clientdto.ClientDTO;
import com.examplenewstack.newstack.exception.CustomException;
import com.examplenewstack.newstack.service.user.client.DeleteByIdService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clients")
public class DeleteClientByIdController {

    private final DeleteByIdService deleteByIdService;


    public DeleteClientByIdController(DeleteByIdService deleteByIdService) {
        this.deleteByIdService = deleteByIdService;
    }


    @DeleteMapping("/delete/{id}")
   public ResponseEntity<?> deleteById(@RequestBody ClientDTO clientDTO , @PathVariable Long id){
            try{

                deleteByIdService.deleteById(id);
                return ResponseEntity.ok().build();
            } catch (Exception e){

                throw new CustomException("Erro: clientes não cadastrados");
            }

    }
}
