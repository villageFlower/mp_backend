package simon.mp.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import simon.mp.APIList;
import simon.mp.entity.Image;
import simon.mp.repo.ImageRepository;
import simon.mp.dataclass.AddImageResp;
import simon.mp.service.ImageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@CrossOrigin
@RestController

public class ImageController {
    @Autowired
    private ImageService imageService;
    private Image image;
    private ImageRepository imageRepository;
    private static final Logger logger = LoggerFactory.getLogger(ImageController.class);


    @CrossOrigin
    @PostMapping(APIList.UPLOAD_IMAGE)
    public ResponseEntity<AddImageResp> Register(@RequestParam("file") MultipartFile image) {
        try {
            AddImageResp result = imageService.save(image);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(result);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new AddImageResp());
        }

    }


}
