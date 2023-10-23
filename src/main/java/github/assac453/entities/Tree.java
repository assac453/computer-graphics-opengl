package github.assac453.entities;

import github.assac453.Object;
import github.assac453.TextureGen;
import github.assac453.service.GenerateColor;

import java.io.IOException;

public class Tree {
    public static Object tree() throws IOException {
        Object anchor = new Object(GenerateColor.Generate(0,0,0));
        Object stvol = stvol();
        Object listva = listva();
        anchor.AddChild(stvol);
        anchor.AddChild(listva);
        return anchor;
    }
    private static Object listva() throws IOException {
        Object listva = new Object(GenerateColor.Generate(0,0,0));
        listva.AddChild(new Object(GenerateColor.Generate(0.5f,0.5f,0.5f)).setTextureOfObject(TextureGen.textureGen("C:\\Users\\aleksey\\Pictures\\oak_dude.png")).Translatef(0,10,0,false));
        listva.AddChild(new Object(GenerateColor.Generate(0.5f,0.5f,0.5f)).setTextureOfObject(TextureGen.textureGen("C:\\Users\\aleksey\\Pictures\\oak_dude.png")).Translatef(2,10,2,false));
        listva.AddChild(new Object(GenerateColor.Generate(0.5f,0.5f,0.5f)).setTextureOfObject(TextureGen.textureGen("C:\\Users\\aleksey\\Pictures\\oak_dude.png")).Translatef(2,10,-2,false));
        listva.AddChild(new Object(GenerateColor.Generate(0.5f,0.5f,0.5f)).setTextureOfObject(TextureGen.textureGen("C:\\Users\\aleksey\\Pictures\\oak_dude.png")).Translatef(-2,10,2,false));
        listva.AddChild(new Object(GenerateColor.Generate(0.5f,0.5f,0.5f)).setTextureOfObject(TextureGen.textureGen("C:\\Users\\aleksey\\Pictures\\oak_dude.png")).Translatef(-2,10,-2,false));

        listva.AddChild(new Object(GenerateColor.Generate(0.5f,0.5f,0.5f)).setTextureOfObject(TextureGen.textureGen("C:\\Users\\aleksey\\Pictures\\oak_dude.png")).Translatef(4,10,4,false));
        listva.AddChild(new Object(GenerateColor.Generate(0.5f,0.5f,0.5f)).setTextureOfObject(TextureGen.textureGen("C:\\Users\\aleksey\\Pictures\\oak_dude.png")).Translatef(4,10,-4,false));
        listva.AddChild(new Object(GenerateColor.Generate(0.5f,0.5f,0.5f)).setTextureOfObject(TextureGen.textureGen("C:\\Users\\aleksey\\Pictures\\oak_dude.png")).Translatef(-4,10,4,false));
        listva.AddChild(new Object(GenerateColor.Generate(0.5f,0.5f,0.5f)).setTextureOfObject(TextureGen.textureGen("C:\\Users\\aleksey\\Pictures\\oak_dude.png")).Translatef(-4,10,-4,false));
        return listva;
    }
    private  static Object stvol() throws IOException {
        Object stvol = new Object(GenerateColor.Generate(0,0,0));
        for (int i = 0; i < 5; i++) {
            stvol.AddChild(new Object(GenerateColor.Generate(0.5f,0.5f,0.5f)).setTextureOfObject(TextureGen.textureGen("C:\\Users\\aleksey\\Pictures\\oak.png")).Translatef(0,i*2,0,false));
        }
        return stvol;
    }
}
