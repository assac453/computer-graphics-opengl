package github.assac453.blocks;

import github.assac453.Object;
import github.assac453.service.GenerateColor;

import java.util.Random;

public class DirtBlock {
    private enum Enum_Side{
        LEFT_AND_RIGHT,
        FACE_AND_BACK,
        UP_AND_DOWN
    }
    public static Object dirt() {
        Object anchor = new Object(GenerateColor.Generate(0,0,0));
        Object leftSide = generateSide(Enum_Side.LEFT_AND_RIGHT);
        Object rightSide = generateSide(Enum_Side.LEFT_AND_RIGHT);
        Object faceSide = generateSide(Enum_Side.FACE_AND_BACK);
        Object backSide = generateSide(Enum_Side.FACE_AND_BACK);
        Object upSide = generateSide(Enum_Side.UP_AND_DOWN);
        Object downSide = generateSide(Enum_Side.UP_AND_DOWN);

        anchor.AddChild(leftSide.Translatef(-15,-15,-15,false));
        anchor.AddChild(rightSide.Translatef(15,-15,-15,false));
        anchor.AddChild(faceSide.Translatef(-15,-15,-15,false));
        anchor.AddChild(backSide.Translatef(-15,-15,15,false));
        anchor.AddChild(downSide.Translatef(-15,-15,-15,false));
        anchor.AddChild(upSide.Translatef(-15,15,-15,false));
        return anchor;
    }
    private static Object generateSide(Enum_Side what_side){
        int[][] colors = new int[16][3];
        colors[0][0] = 115;        colors[0][1] = 101;        colors[0][2] = 58;
        colors[1][0] = 115;        colors[1][1] = 80;        colors[1][2] = 58;
        colors[2][0] = 140;        colors[2][1] = 101;        colors[2][2] = 74;
        colors[3][0] = 140;        colors[3][1] = 101;        colors[3][2] = 74;
        colors[4][0] = 115;        colors[4][1] = 80;        colors[4][2] = 58;
        colors[5][0] = 115;        colors[5][1] = 80;        colors[5][2] = 58;
        colors[6][0] = 140;        colors[6][1] = 101;        colors[6][2] = 74;
        colors[7][0] = 140;        colors[7][1] = 101;        colors[7][2] = 74;
        colors[8][0] = 115;        colors[8][1] = 80;        colors[8][2] = 58;
        colors[9][0] = 76;        colors[9][1] = 50;        colors[9][2] = 34;
        colors[10][0] = 118;        colors[10][1] = 84;        colors[10][2] = 61;
        colors[11][0] = 89;        colors[11][1] = 63;        colors[11][2] = 45;
        colors[12][0] = 127;        colors[12][1] = 92;        colors[12][2] = 66;
        colors[13][0] = 95;        colors[13][1] = 68;        colors[13][2] = 48;
        colors[14][0] = 95;        colors[14][1] = 68;        colors[14][2] = 48;
        colors[15][0] = 53;        colors[15][1] = 34;        colors[15][2] = 23;

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
