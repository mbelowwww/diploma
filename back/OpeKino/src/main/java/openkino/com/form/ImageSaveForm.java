package openkino.com.form;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;
import openkino.com.models.Image;
import openkino.com.view.Views;
import sun.misc.BASE64Decoder;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class ImageSaveForm {

    @JsonView(Views.Public.class)
    private Long filmId;

    @JsonView(Views.Public.class)
    private List<ImageForm> images;


    @Data
    public static class ImageForm {
        @JsonView(Views.Public.class)
        private String file;

        @JsonView(Views.Public.class)
        private Boolean flag;

        public Image toImage() {
            Image image = new Image();
            try {
                String[] formatBase64 = this.file.split(",", 2);
                String typeImg = formatBase64[0].replace("data:", "")
                        .replace(";base64", "");
                String imgBase64 = formatBase64[1];
                BASE64Decoder decoder = new BASE64Decoder();
                image.setImage_array(decoder.decodeBuffer(imgBase64));
                image.setType(typeImg);
                image.setCreatedWhen(LocalDateTime.now());
                image.setUpdatedWhen(LocalDateTime.now());
            } catch (Exception e) {
                e.printStackTrace();
            }
            image.setFlag(this.flag);
            return image;
        }

    }
}
