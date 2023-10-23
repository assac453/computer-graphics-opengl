package github.assac453.entities;

import github.assac453.Object;
import github.assac453.TextureGen;
import github.assac453.service.GenerateColor;

import java.io.IOException;

public class Steve {
    public static Object steve() throws IOException {
        Object anchor = new Object(GenerateColor.Generate(1f,0.5f,0.5f));
        anchor.Translatef(0,0,-5,false);
        anchor.Scalef(0.1f,0.1f,0.1f,false);
        Object head = confHead();
        head.Translatef(0,5,0,false);
        head.Scalef(2f,2f,2f,false);
        Object arms = confArms();
        Object body = confBody();
        Object subHead = confHead();
        Object legs = confLegs();
        Object subLegHead = confHead();

        subLegHead.Translatef(0,-5,0,false);
        legs.Translatef(0,-50,0,false);

        anchor.AddChild(body);
        anchor.AddChild(subHead);
        anchor.AddChild(legs);
        anchor.AddChild(subLegHead);

        anchor.AddChild(head);
        anchor.AddChild(arms);
        return anchor;
    }
    private static Object confBody() throws IOException {
        Object body = new Object(GenerateColor.Generate(1f,0f,1f));
        body.Scalef(0.1f,0.1f,0.1f,false);
        Object subBody = new Object(GenerateColor.Generate(0.5f,0.5f,0.5f));
        subBody.Scalef(10f,10f,10f,false);
        subBody.AddChild(new Object(GenerateColor.Generate(0.5f,0.5f,0.5f)).setTextureOfObject(TextureGen.textureGen("C:\\Users\\aleksey\\Pictures\\steve_body.png")).Translatef(-1,2,0,false)    );
        subBody.AddChild(new Object(GenerateColor.Generate(0.5f,0.5f,0.5f)).setTextureOfObject(TextureGen.textureGen("C:\\Users\\aleksey\\Pictures\\steve_body.png")).Translatef(1,2,0,false)    );
        subBody.AddChild(new Object(GenerateColor.Generate(0.5f,0.5f,0.5f)).setTextureOfObject(TextureGen.textureGen("C:\\Users\\aleksey\\Pictures\\steve_body.png")).Translatef(-1,0,0,false)   );
        subBody.AddChild(new Object(GenerateColor.Generate(0.5f,0.5f,0.5f)).setTextureOfObject(TextureGen.textureGen("C:\\Users\\aleksey\\Pictures\\steve_body.png")).Translatef(1,0,0,false)    );
        subBody.AddChild(new Object(GenerateColor.Generate(0.5f,0.5f,0.5f)).setTextureOfObject(TextureGen.textureGen("C:\\Users\\aleksey\\Pictures\\steve_body.png")).Translatef(-1,-2,0,false)  );
        subBody.AddChild(new Object(GenerateColor.Generate(0.5f,0.5f,0.5f)).setTextureOfObject(TextureGen.textureGen("C:\\Users\\aleksey\\Pictures\\steve_body.png")).Translatef(1,-2,0,false)   );
        body.AddChild(subBody);
        return body;
    }
    private static Object confLegs() throws IOException {
        Object legs = new Object(GenerateColor.Generate(1f,0f,1f));
        legs.Scalef(0.1f,0.1f,0.1f,false);
        Object subBody = new Object(GenerateColor.Generate(0.5f,0.5f,0.5f));
        subBody.Scalef(10f,10f,10f,false);
        subBody.AddChild(new Object(GenerateColor.Generate(0.5f,0.5f,0.5f)).setTextureOfObject(TextureGen.textureGen("C:\\Users\\aleksey\\Pictures\\steve_leg.png")).Translatef(-1,2,0,false)    );
        subBody.AddChild(new Object(GenerateColor.Generate(0.5f,0.5f,0.5f)).setTextureOfObject(TextureGen.textureGen("C:\\Users\\aleksey\\Pictures\\steve_leg.png")).Translatef(1,2,0,false)    );
        subBody.AddChild(new Object(GenerateColor.Generate(0.5f,0.5f,0.5f)).setTextureOfObject(TextureGen.textureGen("C:\\Users\\aleksey\\Pictures\\steve_leg.png")).Translatef(-1,0,0,false)   );
        subBody.AddChild(new Object(GenerateColor.Generate(0.5f,0.5f,0.5f)).setTextureOfObject(TextureGen.textureGen("C:\\Users\\aleksey\\Pictures\\steve_leg.png")).Translatef(1,0,0,false)    );
        subBody.AddChild(new Object(GenerateColor.Generate(0.5f,0.5f,0.5f)).setTextureOfObject(TextureGen.textureGen("C:\\Users\\aleksey\\Pictures\\steve_leg.png")).Translatef(-1,-2,0,false)  );
        subBody.AddChild(new Object(GenerateColor.Generate(0.5f,0.5f,0.5f)).setTextureOfObject(TextureGen.textureGen("C:\\Users\\aleksey\\Pictures\\steve_leg.png")).Translatef(1,-2,0,false)   );
        legs.AddChild(subBody);
        return legs;
    }
    private static Object confArms() throws IOException {
        Object arms = new Object(GenerateColor.Generate(0.5f,1f,1f));
        arms.Scalef(0.1f,0.1f,0.1f,false);
        Object leftArm = new Object(GenerateColor.Generate(0.5f,0.5f,0.5f));
        leftArm.Scalef(10f,10f,10f,false);
        leftArm.setTextureOfObject(TextureGen.textureGen("C:\\Users\\aleksey\\Pictures\\steve_arm.png"));
        leftArm.AddChild(new Object(GenerateColor.Generate(0.5f,0.5f,0.5f)).setTextureOfObject(TextureGen.textureGen("C:\\Users\\aleksey\\Pictures\\steve_arm.png")).Translatef(0,-2,0,false));
        leftArm.AddChild(new Object(GenerateColor.Generate(0.5f,0.5f,0.5f)).setTextureOfObject(TextureGen.textureGen("C:\\Users\\aleksey\\Pictures\\steve_body.png")).Translatef(0,2,0,false));
        leftArm.Translatef(-3,0,0,false);

        Object rightArm = new Object(GenerateColor.Generate(0.5f,0.5f,0.5f));
        rightArm.Scalef(10f,10f,10f,false);
        rightArm.setTextureOfObject(TextureGen.textureGen("C:\\Users\\aleksey\\Pictures\\steve_arm.png"));
        rightArm.AddChild(new Object(GenerateColor.Generate(0.5f,0.5f,0.5f)).setTextureOfObject(TextureGen.textureGen("C:\\Users\\aleksey\\Pictures\\steve_arm.png")).Translatef(0,-2,0,false));
        rightArm.AddChild(new Object(GenerateColor.Generate(0.5f,0.5f,0.5f)).setTextureOfObject(TextureGen.textureGen("C:\\Users\\aleksey\\Pictures\\steve_body.png")).Translatef(0,2,0,false));
        rightArm.Translatef(3,0,0,false);

        arms.AddChild(leftArm);
        arms.AddChild(rightArm);
        return arms;
    }
    private static Object confHead() throws IOException {
        Object head = new Object(GenerateColor.Generate(0.5f,1f,0.5f));

        Object face = new Object(GenerateColor.Generate(0.5f,0.5f,0.5f));
        face.Translatef(0,0,1,false);
        face.Rotatef(180/(180f/(float)(Math.PI)),0,0,1,false);
        face.flat=true;
        face.setTextureOfObject(TextureGen.textureGen("C:\\Users\\aleksey\\Pictures\\steve_head.png"));

        Object rightFace = new Object(GenerateColor.Generate(0.5f,0.5f,0.5f));
        rightFace.flat = true;
        rightFace.setTextureOfObject(TextureGen.textureGen("C:\\Users\\aleksey\\Pictures\\steve_head_left.png"));
        rightFace.Translatef(1,0,0,false);
        rightFace.Rotatef(180/(180f/(float)(Math.PI)),0,0,1,false);
        rightFace.Rotatef(-90/(180f/(float)(Math.PI)),0,1,0,false);

        Object leftFace = new Object(GenerateColor.Generate(0.5f,0.5f,0.5f));
        leftFace.flat = true;
        leftFace.setTextureOfObject(TextureGen.textureGen("C:\\Users\\aleksey\\Pictures\\steve_head_right.png"));
        leftFace.Translatef(-1,0,0,false);
        leftFace.Rotatef(180/(180f/(float)(Math.PI)),0,0,1,false);
        leftFace.Rotatef(90/(180f/(float)(Math.PI)),0,1,0,false);

        Object backFace = new Object(GenerateColor.Generate(0.5f,0.5f,0.5f));
        backFace.flat = true;
        backFace.setTextureOfObject(TextureGen.textureGen("C:\\Users\\aleksey\\Pictures\\steve_head_back.png"));
        backFace.Translatef(0,0,-1,false);
        backFace.Rotatef(180/(180f/(float)(Math.PI)),0,0,1,false);
        backFace.Rotatef(180/(180f/(float)(Math.PI)),0,1,0,false);

        Object upFace = new Object(GenerateColor.Generate(0.5f,0.5f,0.5f));
        upFace.flat = true;
        upFace.setTextureOfObject(TextureGen.textureGen("C:\\Users\\aleksey\\Pictures\\steve_head_up.png"));
        upFace.Translatef(0,1,0,false);
        upFace.Rotatef(180/(180f/(float)(Math.PI)),0,0,1,false);
        upFace.Rotatef(180/(180f/(float)(Math.PI)),0,1,0,false);
        upFace.Rotatef(90/(180f/(float)(Math.PI)),1,0,0,false);

        Object downFace = new Object(GenerateColor.Generate(0.5f,0.5f,0.5f));
        downFace.flat = true;
        downFace.setTextureOfObject(TextureGen.textureGen("C:\\Users\\aleksey\\Pictures\\steve_head_down.png"));
        downFace.Translatef(0,-1f,0,false);
        downFace.Rotatef(90/(180f/(float)(Math.PI)),1,0,0,false);
        downFace.Rotatef(180/(180f/(float)(Math.PI)),0,0,1,false);

        head.AddChild(face);
        head.AddChild(rightFace);
        head.AddChild(leftFace);
        head.AddChild(backFace);
        head.AddChild(upFace);
        head.AddChild(downFace);
        return head;
    }
}

