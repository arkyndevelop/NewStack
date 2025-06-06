package com.examplenewstack.newstack.config.aws; // O pacote do seu controlador

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException; // Para tratamento de erros mais robusto

import java.io.IOException;
import java.util.List;
import java.util.Map; // Para o retorno de Map do S3Service e do Controller

@RestController // Indica que esta classe é um controlador REST
@RequestMapping("/api/pictures") // Define o caminho base para todos os endpoints deste controlador
public class PictureController {

    private final S3Service s3Service;
    private final PictureRepository pictureRepository; // Injetar o repositório

    // Usar injeção via construtor (preferível a @Autowired em campos)
    public PictureController(S3Service s3Service, PictureRepository pictureRepository) {
        this.s3Service = s3Service;
        this.pictureRepository = pictureRepository;
    }

    /**
     * Endpoint para upload de uma nova imagem.
     * Recebe um arquivo MultipartFile, faz o upload para o S3 e salva as informações no banco de dados.
     *
     * @param file O arquivo de imagem a ser enviado.
     * @return ResponseEntity com a URL da imagem salva e o ID no banco, ou erro.
     */
    @PostMapping("/upload")
    public ResponseEntity<Map<String, String>> uploadPicture(@RequestParam("file") MultipartFile file) {
        // Validação inicial: verifica se o arquivo está vazio
        if (file.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Por favor, selecione um arquivo para upload.");
        }

        try {
            // 1. Fazer upload do arquivo para o S3
            // O S3Service agora retorna um Map com o nome gerado no S3 e a URL.
            Map<String, String> uploadResult = s3Service.uploadFile(file);
            String nomeNoS3 = uploadResult.get("nomeNoS3");
            String urlS3 = uploadResult.get("urlS3");

            // 2. Salvar as informações da imagem no banco de dados
            Picture picture = new Picture();
            picture.setNomeOriginal(file.getOriginalFilename());
            picture.setNomeNoS3(nomeNoS3); // Usar o nome gerado pelo S3Service
            picture.setUrlS3(urlS3);     // Usar a URL gerada pelo S3Service

            Picture savedPicture = pictureRepository.save(picture); // Salva no MySQL

            // Retorna a URL e o ID da imagem salva em um JSON
            return ResponseEntity.status(HttpStatus.CREATED).body(
                    Map.of("message", "Upload e salvamento da imagem bem-sucedidos!",
                            "id", savedPicture.getId().toString(),
                            "nomeOriginal", savedPicture.getNomeOriginal(),
                            "nomeNoS3", savedPicture.getNomeNoS3(),
                            "urlS3", savedPicture.getUrlS3())
            );

        } catch (IOException e) {
            // Erro na leitura/processamento do arquivo
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Falha na leitura ou processamento do arquivo: " + e.getMessage(), e);
        } catch (Exception e) {
            // Outros erros (S3, banco de dados, etc.)
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro durante o upload ou salvamento da imagem: " + e.getMessage(), e);
        }
    }

    /**
     * Endpoint para listar todas as imagens salvas no banco de dados.
     *
     * @return ResponseEntity com a lista de todas as Pictures.
     */
    @GetMapping
    public ResponseEntity<List<Picture>> getAllPictures() {
        List<Picture> pictures = pictureRepository.findAll();
        if (pictures.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build(); // 204 No Content se não houver imagens
        }
        return ResponseEntity.ok(pictures); // 200 OK com a lista
    }

    /**
     * Endpoint para buscar uma imagem por ID.
     *
     * @param id O ID da imagem no banco de dados.
     * @return ResponseEntity com a Picture encontrada, ou 404 Not Found.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Picture> getPictureById(@PathVariable Long id) {
        Picture picture = pictureRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Imagem não encontrada com o ID: " + id));
        return ResponseEntity.ok(picture);
    }

    /**
     * Endpoint para deletar uma imagem pelo ID (apenas do banco, não do S3).
     * ATENÇÃO: Para remover do S3, precisaríamos de uma lógica adicional no S3Service.
     *
     * @param id O ID da imagem a ser deletada.
     * @return ResponseEntity com mensagem de sucesso ou erro.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePicture(@PathVariable Long id) {
        if (!pictureRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Imagem não encontrada para deletar com o ID: " + id);
        }
        pictureRepository.deleteById(id);
        return ResponseEntity.noContent().build(); // 204 No Content para deleção bem-sucedida
    }
}