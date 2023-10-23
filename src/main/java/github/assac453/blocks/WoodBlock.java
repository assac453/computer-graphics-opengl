package github.assac453.blocks;

import github.assac453.Object;
import github.assac453.service.GenerateColor;

import java.util.Random;

public class WoodBlock {
    private enum Enum_Side{
        LEFT_AND_RIGHT,
        FACE_AND_BACK,
        UP_AND_DOWN
    }

    public static Object Penek() {
        Object anchor = new Object(GenerateColor.Generate(0,0,0));
        Object leftSide = generateSide(WoodBlock.Enum_Side.LEFT_AND_RIGHT);
        Object rightSide = generateSide(WoodBlock.Enum_Side.LEFT_AND_RIGHT);
        Object faceSide = generateSide(WoodBlock.Enum_Side.FACE_AND_BACK);
        Object backSide = generateSide(WoodBlock.Enum_Side.FACE_AND_BACK);
        Object upSide = generateSide(WoodBlock.Enum_Side.UP_AND_DOWN);
        Object downSide = generateSide(WoodBlock.Enum_Side.UP_AND_DOWN);

        anchor.AddChild(leftSide.Translatef(-15,-15,-15,false));
        anchor.AddChild(rightSide.Translatef(15,-15,-15,false));
        anchor.AddChild(faceSide.Translatef(-15,-15,-15,false));
        anchor.AddChild(backSide.Translatef(-15,-15,15,false));
        anchor.AddChild(downSide.Translatef(-15,-15,-15,false));
        anchor.AddChild(upSide.Translatef(-15,15,-15,false));
        return anchor;
    }


    private static Object generateSide(WoodBlock.Enum_Side what_side){
        int[][] colors = new int[16][3];
        colors[0][0] = 69;        colors[0][1] = 56;        colors[0][2] = 37;
        colors[1][0] = 99;        colors[1][1] = 79;        colors[1][2] = 46;
        colors[2][0] = 99;        colors[2][1] = 79;        colors[2][2] = 46;
        colors[3][0] = 152;       colors[3][1] = 120;      colors[3][2] = 73;
        colors[4][0] = 69;        colors[4][1] = 56;        colors[4][2] = 37;
        colors[5][0] = 99;        colors[5][1] = 79;        colors[5][2] = 46;
        colors[6][0] = 152;       colors[6][1] = 120;       colors[6][2] = 73;
        colors[7][0] = 69;        colors[7][1] = 56;        colors[7][2] = 37;
        colors[8][0] = 99;        colors[8][1] = 79;        colors[8][2] = 46;
        colors[9][0] = 69;        colors[9][1] = 56;        colors[9][2] = 37;
        colors[10][0] = 99;        colors[10][1] = 79;        colors[10][2] = 46;
        colors[11][0] = 99;        colors[11][1] = 79;        colors[11][2] = 46;
        colors[12][0] = 99;       colors[12][1] = 79;       colors[12][2] = 46;
        colors[13][0] = 69;        colors[13][1] = 56;        colors[13][2] = 37;
        colors[14][0] = 99;        colors[14][1] = 79;        colors[14][2] = 46;
        colors[15][0] = 99;       colors[15][1] = 79;       colors[15][2] = 46;
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
