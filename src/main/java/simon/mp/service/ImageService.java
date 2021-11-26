package simon.mp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;
import simon.mp.entity.Image;
import simon.mp.repo.ImageRepository;
import simon.mp.reponse.AddImageResp;
import simon.mp.util.FileUtil;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;
import java.util.stream.Stream;

@Service
public class ImageService {
    @Autowired
    private ImageRepository imageRepository;
    private final Path root = Paths.get("data/images");
    private FileUtil fileUtil;


    public void init() {
        if (Files.isDirectory(root)) return;
        try {
            Files.createDirectories(root);
        } catch (IOException e) {
            throw new RuntimeException("Could not initialize folder for upload!");
        }
    }

    public AddImageResp save(MultipartFile file) {
        //generate uuid for the image file name
        init();
        String fileName = UUID.randomUUID().toString();
        String oldFileName = file.getOriginalFilename();
        String ext = oldFileName.substring(oldFileName.lastIndexOf(".") + 1);
        try {
            Files.copy(file.getInputStream(), this.root.resolve(fileName + "." + ext));
        } catch (Exception e) {
            throw new RuntimeException("Could not store the file. Error: " + e.getMessage());
        }
        Image image = new Image();
        image.setUuid(fileName);
        image.setImage_size(file.getSize());
        image.setFile_path("images" + "/" + fileName  + "." + ext);

        try {
            Image result = imageRepository.save(image);
            AddImageResp resp = new AddImageResp();
            resp.id = result.getId();
            resp.url = "https://mp-resource.simonyc.tech/" + result.getFile_path();
            return  resp;
        } catch (Exception e) {
            throw new RuntimeException("Could not store the file. Error: " + e.getMessage());
        }
    }

    public Resource load(String filename) {
        try {
            Path file = root.resolve(filename);
            Resource resource = new UrlResource(file.toUri());

            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new RuntimeException("Could not read the file!");
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("Error: " + e.getMessage());
        }
    }

    public void deleteAll() {
        FileSystemUtils.deleteRecursively(root.toFile());
    }


    public Stream<Path> loadAll() {
        try {
            return Files.walk(this.root, 1).filter(path -> !path.equals(this.root)).map(this.root::relativize);
        } catch (IOException e) {
            throw new RuntimeException("Could not load the files!");
        }
    }
}
