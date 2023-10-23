package github.assac453.blocks;

import github.assac453.Object;
import github.assac453.TextureGen;
import github.assac453.service.GenerateColor;

import java.io.IOException;
import java.util.Random;

public class House {
    public static Object house() throws IOException {

        Object anchor = new Object(GenerateColor.Generate(0,0,0));
        Object floor = getFloor();
        Object walls = getWalls();
        anchor.AddChild(floor);
        anchor.AddChild(walls);
        anchor.Scalef(0.35f,0.35f,0.35f,false);
        return anchor;
    }
    private static Object getWalls() throws IOException {
        Object walls = new Object(GenerateColor.Generate(0,0,0));
        Object faceWall = new Object(GenerateColor.Generate(0,0,0));
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (i>0 && i<4 && j>0 && j< 6){
                    continue;
                }
                faceWall.AddChild(new Object(GenerateColor.Generate(0.5f,0.5f,0.5f)).Translatef(i*2,j*2,0,false).setTextureOfObject(TextureGen.textureGen("C:\\Users\\aleksey\\Pictures\\wood.png")));
            }
        }
        faceWall.Translatef(0,2,0,false);

        Object windowedWall = new Object(GenerateColor.Generate(0,0,0));
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (i>0 && i<9 && j>3 && j< 6){
                    continue;
                }
                windowedWall.AddChild(new Object(GenerateColor.Generate(0.5f,0.5f,0.5f)).Translatef(i*2,j*2,0,false).setTextureOfObject(TextureGen.textureGen("C:\\Users\\aleksey\\Pictures\\wood.png")));
            }
        }
        windowedWall.Rotatef(90 / (180.0f / (float)Math.PI),0,1,0,false);
        windowedWall.Scalef(-1,1,1,false);

        Object backWall = new Object(GenerateColor.Generate(0,0,0));
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                backWall.AddChild(new Object(GenerateColor.Generate(0.5f,0.5f,0.5f)).Translatef(i*2,j*2,0,false).setTextureOfObject(TextureGen.textureGen("C:\\Users\\aleksey\\Pictures\\wood.png")));
            }
        }
        backWall.Rotatef(90 / (180.0f / (float)Math.PI),0,1,0,false);
        backWall.Translatef(0,0,20,false);
        backWall.Translatef(-20,0,0,false);

        Object lastWall = new Object(GenerateColor.Generate(0,0,0));
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                lastWall.AddChild(new Object(GenerateColor.Generate(0.5f,0.5f,0.5f)).Translatef(i*2,j*2,0,false).setTextureOfObject(TextureGen.textureGen("C:\\Users\\aleksey\\Pictures\\diamond.png")));
            }
        }
        lastWall.Translatef(0,0,20,false);
        walls.AddChild(lastWall);
        walls.AddChild(backWall);
        walls.AddChild(faceWall);
        walls.AddChild(windowedWall);
        return walls;
    }
    private static Object getFloor() throws IOException {
        Object floor = new Object(GenerateColor.Generate(0,0,0));
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                floor.AddChild(new Object(GenerateColor.Generate(0.5f,0.5f,0.5f)).setTextureOfObject(TextureGen.textureGen("C:\\Users\\aleksey\\Pictures\\wood.png")).Translatef(j*2,0,i*2,false));
            }
        }
        return floor;
    }
}
