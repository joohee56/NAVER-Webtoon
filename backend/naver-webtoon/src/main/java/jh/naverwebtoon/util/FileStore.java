package jh.naverwebtoon.util;

import java.io.File;
import java.io.IOException;
import java.util.UUID;
import jh.naverwebtoon.db.domain.UploadImage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileStore {

    @Value("${file.dir}")
    private String fileDir;

    public String getFullPath(String fileName) {
        return fileDir + fileName;
    }

    public UploadImage storeFile(MultipartFile multipartFile) throws IOException {
        if (multipartFile.isEmpty()) {
            return null;
        }

        String originalFileName = multipartFile.getOriginalFilename();
        String storeFileName = createStoreFileName(originalFileName);
        multipartFile.transferTo(new File(getFullPath(storeFileName))); //이미지 경로 폴더에 저장
        return new UploadImage(originalFileName, storeFileName);
    }

    //저장할 고유 이름 생성
    private String createStoreFileName(String originalFileName) {
        String ext = extractExt(originalFileName); //확장자 추출
        String uuid = UUID.randomUUID().toString();  //고유한 이름
        return uuid + "." + ext;
    }

    private String extractExt(String originalFileName) {
        int idx = originalFileName.lastIndexOf(".");
        return originalFileName.substring(idx+1); //확장자 반환
    }


}
