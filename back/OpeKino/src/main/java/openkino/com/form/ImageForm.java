package openkino.com.form;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;
import openkino.com.models.Image;
import openkino.com.view.Views;
import sun.misc.BASE64Decoder;

@Data
public class ImageForm {
    @JsonView(Views.Public.class)
    private Long id;
    @JsonView(Views.Public.class)
    private String file;

    public Image toImage(ImageForm imageMask) {
        Image image = new Image();
        try {
            String[] formatBase64 = imageMask.file.split(",", 2);
            String typeImg = formatBase64[0].replace("data:", "")
                    .replace(";base64", "");
            String imgBase64 = formatBase64[1];
            BASE64Decoder decoder = new BASE64Decoder();
            image.setImage_array(decoder.decodeBuffer(imgBase64));
            image.setType(typeImg);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return image;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }
}
