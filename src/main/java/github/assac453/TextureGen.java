package github.assac453;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL30;
import org.lwjgl.stb.STBImage;
import java.io.IOException;
import java.nio.ByteBuffer;

import static org.lwjgl.opengl.GL11.*;

public class TextureGen {
    public static int textureGen(String pathToFile) throws IOException {
        int textureId = glGenTextures();
        glBindTexture(GL_TEXTURE_2D, textureId);
        // Установка параметров фильтрации текстуры
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL13.GL_CLAMP_TO_EDGE);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL13.GL_CLAMP_TO_EDGE);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_LINEAR);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_LINEAR);
        ByteBuffer imageBuffer;
        int[] width=new int[1], height=new int[1], channels=new int[1];
        try {
            // Загрузка изображения с помощью STBImage
            imageBuffer = STBImage.stbi_load(pathToFile, width,height,channels, 4);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
        // Загрузка данных текстуры в OpenGL
        GL11.glTexImage2D(GL11.GL_TEXTURE_2D, 0, GL11.GL_RGBA, width[0], height[0], 0, GL11.GL_RGBA, GL11.GL_UNSIGNED_BYTE, imageBuffer);
        GL30.glGenerateMipmap(GL11.GL_TEXTURE_2D);
        // Освобождение памяти, занятой изображением
        assert imageBuffer != null;
        STBImage.stbi_image_free(imageBuffer);
        return textureId;
    }
}
