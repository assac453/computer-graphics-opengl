package github.assac453.blocks;

import github.assac453.Object;
import github.assac453.service.GenerateColor;

import java.util.Random;

public class Listva {
    private enum Enum_Side{
        LEFT_AND_RIGHT,
        FACE_AND_BACK,
        UP_AND_DOWN
    }
    public static Object listva() {
        Object anchor = new Object(GenerateColor.Generate(0,0,0));
        Object leftSide = generateSide(Listva.Enum_Side.LEFT_AND_RIGHT);
        Object rightSide = generateSide(Listva.Enum_Side.LEFT_AND_RIGHT);
        Object faceSide = generateSide(Listva.Enum_Side.FACE_AND_BACK);
        Object backSide = generateSide(Listva.Enum_Side.FACE_AND_BACK);
        Object upSide = generateSide(Listva.Enum_Side.UP_AND_DOWN);
        Object downSide = generateSide(Listva.Enum_Side.UP_AND_DOWN);

        anchor.AddChild(leftSide.Translatef(-15,-15,-15,false));
        anchor.AddChild(rightSide.Translatef(15,-15,-15,false));
        anchor.AddChild(faceSide.Translatef(-15,-15,-15,false));
        anchor.AddChild(backSide.Translatef(-15,-15,15,false));
        anchor.AddChild(downSide.Translatef(-15,-15,-15,false));
        anchor.AddChild(upSide.Translatef(-15,15,-15,false));
        return anchor;
    }
    private static Object generateSide(Listva.Enum_Side what_side){
        int[][] colors = new int[16][3];
        colors[0][0] = 49;        colors[0][1] = 129;        colors[0][2] = 33;
        colors[1][0] = 49;        colors[1][1] = 129;        colors[1][2] = 33;
        colors[2][0] = 33;        colors[2][1] = 84;        colors[2][2] = 23;
        colors[3][0] = 33;        colors[3][1] = 84;        colors[3][2] = 23;
        colors[4][0] = 0;        colors[4][1] = 0;        colors[4][2] = 0;
        colors[5][0] = 0;        colors[5][1] = 0;        colors[5][2] = 0;
        colors[6][0] = 0;        colors[6][1] = 0;        colors[6][2] = 0;
        colors[7][0] = 28;        colors[7][1] = 74;        colors[7][2] = 19;
        colors[8][0] = 30;        colors[8][1] = 80;        colors[8][2] = 20;
        colors[9][0] = 30;        colors[9][1] = 80;        colors[9][2] = 20;
        colors[10][0] = 20;        colors[10][1] = 80;        colors[10][2] = 20;
        colors[11][0] = 28;        colors[11][1] = 74;        colors[11][2] = 19;
        colors[12][0] = 33;        colors[12][1] = 84;        colors[12][2] = 23;
        colors[13][0] = 28;        colors[13][1] = 80;        colors[13][2] = 33;
        colors[14][0] = 49;        colors[14][1] = 129;        colors[14][2] = 23;
        colors[15][0] = 33;        colors[15][1] = 84;        colors[15][2] = 20;

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
