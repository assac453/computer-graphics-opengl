package github.assac453.blocks;

import github.assac453.Object;
import github.assac453.service.GenerateColor;

import java.util.Random;

public class Dern {
    private enum Enum_Side{
        LEFT_AND_RIGHT,
        FACE_AND_BACK,
        UP_AND_DOWN
    }
    public static Object dirt() {
        Object anchor = new Object(GenerateColor.Generate(0,0,0));
        Object upSide = generateSide(Enum_Side.FACE_AND_BACK);
        anchor.AddChild(upSide.Translatef(-15,0,-15,false));
        return anchor;
    }
    private static Object generateSide(Dern.Enum_Side what_side){
        int[][] colors = new int[16][3];
        colors[0][0] = 143;        colors[0][1] = 189;        colors[0][2] = 91;
        colors[1][0] = 117;        colors[1][1] = 180;        colors[1][2] = 75;
        colors[2][0] = 112;        colors[2][1] = 171;        colors[2][2] = 65;
        colors[3][0] = 114;        colors[3][1] = 171;        colors[3][2] = 68;
        colors[4][0] = 114;        colors[4][1] = 169;        colors[4][2] = 67;
        colors[5][0] = 105;        colors[5][1] = 156;        colors[5][2] = 61;
        colors[6][0] = 102;        colors[6][1] = 165;        colors[6][2] = 60;
        colors[7][0] = 103;        colors[7][1] = 169;        colors[7][2] = 61;
        colors[8][0] = 112;        colors[8][1] = 167;        colors[8][2] = 65;
        colors[9][0] = 103;        colors[9][1] = 154;        colors[9][2] = 61;
        colors[10][0] = 100;        colors[10][1] = 144;        colors[10][2] = 57;
        colors[11][0] = 126;        colors[11][1] = 189;        colors[11][2] = 72;
        colors[12][0] = 127;        colors[12][1] = 92;        colors[12][2] = 66;
        colors[13][0] = 114;        colors[13][1] = 171;        colors[13][2] = 61;
        colors[14][0] = 117;        colors[14][1] = 165;        colors[14][2] = 91;
        colors[15][0] = 103;        colors[15][1] = 169;        colors[15][2] = 60;

        Object side = new Object(GenerateColor.Generate(53, 50, 58));
        Random r = new Random();
        for (int j = 0; j < 16; j++) {
            for (int i = 0; i < 16; i++) {
                int index = r.nextInt(16);
                switch (what_side){
                    case LEFT_AND_RIGHT -> {
                        side.AddChild(new Object(GenerateColor.Generate(colors[index][0], colors[index][1], colors[index][2])).Translatef( 0, i * 2, j * 2, false));
                    }
                    case UP_AND_DOWN -> {
                        side.AddChild(new Object(GenerateColor.Generate(colors[index][0], colors[index][1], colors[index][2])).Translatef( i * 2, 0, j * 2, false));
                    }
                    case FACE_AND_BACK -> {
                        side.AddChild(new Object(GenerateColor.Generate(colors[index][0], colors[index][1], colors[index][2])).Translatef( i * 2, j * 2, 0, false));
                    }
                }
            }
        }
        return side;
    }
}
