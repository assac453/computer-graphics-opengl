package github.assac453.entities;

import github.assac453.Object;
import github.assac453.TextureGen;
import github.assac453.service.GenerateColor;

import java.io.IOException;

public class Torch {
    public static Object torch() throws IOException {
        Object anchor = new Object(GenerateColor.Generate(0,0,0));
        Object stvol = stvol();
        Object fire = fire();
        anchor.AddChild(stvol);
        anchor.AddChild(fire);
        anchor.Scalef(0.25f,0.25f,0.25f,false);
        return anchor;
    }
    private static Object fire() throws IOException {
        Object listva = new Object(GenerateColor.Generate(0,0,0));
        listva.AddChild(new Object(GenerateColor.Generate(0.5f,0.5f,0.5f)).setTextureOfObject(TextureGen.textureGen("C:\\Users\\aleksey\\Pictures\\torch_fire.png")).Translatef(0,10,0,false));
        return listva;
    }
    private  static Object stvol() throws IOException {
        Object stvol = new Object(GenerateColor.Generate(0,0,0));
        for (int i = 0; i < 5; i++) {
            stvol.AddChild(new Object(GenerateColor.Generate(0.5f,0.5f,0.5f)).setTextureOfObject(TextureGen.textureGen("C:\\Users\\aleksey\\Pictures\\torch.png")).Translatef(0,i*2,0,false));
        }
        return stvol;
    }
}
