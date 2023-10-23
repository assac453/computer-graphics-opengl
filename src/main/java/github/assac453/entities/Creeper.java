package github.assac453.entities;

import github.assac453.Object;
import github.assac453.TextureGen;
import github.assac453.service.GenerateColor;

import java.io.IOException;

public class Creeper {
    public static Object creeper() throws IOException {
        Object creeper = new Object(GenerateColor.Generate(0.5f,0.5f,0.5f));
        creeper.AddChild(new Object(GenerateColor.Generate(0.0f, 1.0f, 0.3f)).Scalef(8,12,4,false).setTextureOfObject(TextureGen.textureGen("C:\\Users\\aleksey\\Pictures\\creeper.jpg")));//body
        creeper.AddChild(new Object(GenerateColor.Generate(0.0f, 1.0f, 0.3f)).Scalef(4,6,4,false).Translatef(1,-3,-2,false).setTextureOfObject(TextureGen.textureGen("C:\\Users\\aleksey\\Pictures\\creeper.jpg")));//leg
        creeper.AddChild(new Object(GenerateColor.Generate(0.0f, 1.0f, 0.3f)).Scalef(4,6,4,false).Translatef(-1,-3,-2,false).setTextureOfObject(TextureGen.textureGen("C:\\Users\\aleksey\\Pictures\\creeper.jpg")));//leg
        creeper.AddChild(new Object(GenerateColor.Generate(0.0f, 1.0f, 0.3f)).Scalef(4,6,4,false).Translatef(-1,-3,2,false).setTextureOfObject(TextureGen.textureGen("C:\\Users\\aleksey\\Pictures\\creeper.jpg")));//leg
        creeper.AddChild(new Object(GenerateColor.Generate(0.0f, 1.0f, 0.3f)).Scalef(4,6,4,false).Translatef(1,-3,2,false).setTextureOfObject(TextureGen.textureGen("C:\\Users\\aleksey\\Pictures\\creeper.jpg")));//leg
        creeper.AddChild(new Object(GenerateColor.Generate(0.0f, 1.0f, 0.3f)).Scalef(8,8,8,false).Translatef(0,2.5f,0,false).setTextureOfObject(TextureGen.textureGen("C:\\Users\\aleksey\\Pictures\\creeper.jpg"))); //head

        Object face = new Object(GenerateColor.Generate(0.0f, 0.0f, 0.0f));
        face.Translatef(3,13.1f,7.1f,false);
        face.AddChild(new Object(GenerateColor.Generate(0.0f, 0.0f, 0.0f)).Translatef(0,2,0,false));
        face.AddChild(new Object(GenerateColor.Generate(0.0f, 0.0f, 0.0f)).Translatef(-2,2,0,false));
        face.AddChild(new Object(GenerateColor.Generate(0.0f, 0.0f, 0.0f)).Translatef(-4,2,0,false));
        face.AddChild(new Object(GenerateColor.Generate(0.0f, 0.0f, 0.0f)).Translatef(-4,4,0,false));
        face.AddChild(new Object(GenerateColor.Generate(0.0f, 0.0f, 0.0f)).Translatef(-2,4,0,false));
        face.AddChild(new Object(GenerateColor.Generate(0.0f, 0.0f, 0.0f)).Translatef(0,8,0,false));
        face.AddChild(new Object(GenerateColor.Generate(0.0f, 0.0f, 0.0f)).Translatef(2,8,0,false));
        face.AddChild(new Object(GenerateColor.Generate(0.0f, 0.0f, 0.0f)).Translatef(0,10,0,false));
        face.AddChild(new Object(GenerateColor.Generate(0.0f, 0.0f, 0.0f)).Translatef(2,10,0,false));

        face.AddChild(new Object(GenerateColor.Generate(0.0f, 0.0f, 0.0f)).Translatef(-8,8,0,false));
        face.AddChild(new Object(GenerateColor.Generate(0.0f, 0.0f, 0.0f)).Translatef(-6,8,0,false));
        face.AddChild(new Object(GenerateColor.Generate(0.0f, 0.0f, 0.0f)).Translatef(-8,10,0,false));
        face.AddChild(new Object(GenerateColor.Generate(0.0f, 0.0f, 0.0f)).Translatef(-6,10,0,false));

        face.AddChild(new Object(GenerateColor.Generate(0.0f, 0.0f, 0.0f)).Translatef(-6,0,0,false));
        face.AddChild(new Object(GenerateColor.Generate(0.0f, 0.0f, 0.0f)).Translatef(-6,2,0,false));
        creeper.AddChild(face);
        creeper.Translatef(0,0,-3,false);
        creeper.Scalef(0.04f,0.04f,0.04f,false);
        return creeper;
    }
}
