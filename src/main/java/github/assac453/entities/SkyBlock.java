package github.assac453.entities;

import github.assac453.Object;
import github.assac453.blocks.Dern;
import github.assac453.blocks.DirtBlock;
import github.assac453.blocks.Listva;
import github.assac453.blocks.WoodBlock;
import github.assac453.service.GenerateColor;

public class SkyBlock {
    public static Object skyBlock(){
        Object listva = new Object(GenerateColor.Generate(0,0,0)).Translatef(-64,64,-320+4*32,false);
        listva.Rotatef(90,1,0,0,false);
        listva.AddChild(Listva.listva());

        listva.AddChild(Listva.listva().Translatef(32,32,0,false));
        listva.AddChild(Listva.listva().Translatef(32,-32,0,false));
        listva.AddChild(Listva.listva().Translatef(-32,32,0,false));
        listva.AddChild(Listva.listva().Translatef(-32,-32,0,false));
        listva.AddChild(Listva.listva().Translatef(32,32,32,false));
        listva.AddChild(Listva.listva().Translatef(0,32,32,false));
        listva.AddChild(Listva.listva().Translatef(32,0,32,false));
        listva.AddChild(Listva.listva().Translatef(32,0,0,false));

        Object trava = new Object(GenerateColor.Generate(0,0,0)).Translatef(-64,-25,-330,false);
        trava.Rotatef(90,1,0,0,false);
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 4; j++) {
                trava.AddChild(Dern.dirt().Translatef(32*i,32*j,0,false));
            }
        }

        Object wood = new Object(GenerateColor.Generate(0,0,0)).Translatef(-64,64,-320,false);
        wood.Rotatef(90,1,0,0,false);
        for (int i = 0; i < 4; i++) {
            wood.AddChild(WoodBlock.Penek().Translatef(0,0,32*i,false));
        }
        wood.Translatef(0,32*2,32,false);

        Object mountain = new Object(GenerateColor.Generate(0,0,0)).Translatef(-64,-32,-320,false);
        mountain.Rotatef(90,1,0,0,false);

        for (int k = 0; k < 4; k++) {
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 4; j++) {
                    mountain.AddChild(DirtBlock.dirt().Translatef(32*i,32*j,32*k,false));
                }
            }
        }
        Object anchorMain = new Object(GenerateColor.Generate(0,0,0)).Translatef(0,0,3,false);
        anchorMain.AddChild(listva);
        anchorMain.AddChild(mountain);
        anchorMain.AddChild(wood);
        anchorMain.AddChild(trava);
        return anchorMain;
    }
}
