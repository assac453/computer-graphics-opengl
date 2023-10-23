package github.assac453.service;

public class GenerateColor {
    public static float[] Generate(float r, float g, float b){
        float[] result = new float[72];
        for(int i = 0; i < 72;i+=3){
            result[i] = r;            result[i+1]=g;            result[i+2]=b;
        }
        return result;
    }
    public static float[] Generate(int r, int g, int b){
        float[] result = new float[72];
        for(int i = 0; i < 72;i+=3){
            result[i] = (float)r/255;            result[i+1]=(float)g/255;            result[i+2]=(float)b/255;
        }
        return result;
    }

}
