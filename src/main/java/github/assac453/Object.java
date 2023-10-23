package github.assac453;
import org.joml.Matrix4f;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.List;

import static org.lwjgl.opengl.GL11.*;

enum LIGHT_NUM{
    LIGHT0,
    LIGHT1,
    LIGHT2,
    LIGHT3,
    LIGHT4,
    LIGHT5,
    LIGHT6,
    LIGHT7,
}
public class Object {
    private final List<Object> objects;
    private final Matrix4f modelViewMatrix;
    private FloatBuffer bufferVertecies;
    private FloatBuffer bufferColour;
    private IntBuffer bufferIndecies;
    private int textureOfObject = 0;
    private float[] colors = new float[]{};
    private boolean light = false;
    public LIGHT_NUM lightNum;
    public Matrix4f getModelViewMatrix() {
        return modelViewMatrix;
    }
    public boolean flat = false;
    private float[] position = new float[]{0,0,1,0};
    private float[] diffuse = new float[]{1,1,1,1};
    private float[] ambient = new float[]{0f,0f,0f,1f};
    private float[] specular = new float[]{1,1,1,1};
    private float[] spot_direction = new float[]{0,0,0,1};
    private float cut_off = 180;
    private float exponent = 0;
    private float[] normal;
    public float[] getPosition() {
        return position;
    }
    public void setPosition(float[] position) {
        this.position = position;
    }
    public float[] getDiffuse() {
        return diffuse;
    }
    public void setDiffuse(float[] diffuse) {
        this.diffuse = diffuse;
    }
    public float[] getAmbient() {
        return ambient;
    }
    public void setAmbient(float[] ambient) {
        this.ambient = ambient;
    }
    public float[] getSpecular() {
        return specular;
    }
    public void setSpecular(float[] specular) {
        this.specular = specular;
    }
    public float[] getSpot_direction() {
        return spot_direction;
    }
    public Object setTextureOfObject(int texture){
        this.textureOfObject = texture;
        return this;
    }
    public void setSpot_direction(float[] spot_direction) {
        this.spot_direction = spot_direction;
    }
    public float getCut_off() {
        return cut_off;
    }
    public void setCut_off(float cut_off) {
        this.cut_off = cut_off;
    }
    public float getExponent() {
        return exponent;
    }
    public void setExponent(float exponent) {
        this.exponent = exponent;
    }
    public boolean isLight(){return light;}
    public void setLight(boolean isLight){light=isLight;}

    private FloatBuffer getBufferVertecies(){
        return this.bufferVertecies;
    }
    public void setColour(float[] colour){
        this.colors= colour;
    }

    public Object(float[] colors){
        this.objects = new ArrayList<>();
        this.colors = colors;
        this.modelViewMatrix = new Matrix4f();
    }
    public Object Translatef(float x, float y, float z, boolean isToChild){
        this.modelViewMatrix.translate(x,y,z);
        if (isToChild){
            for (Object child : objects) {
                child.Translatef(x, y, z, isToChild);
            }
        }
        return this;
    }
    public Object Rotatef(float angle, float x, float y, float z, boolean isToChild){
        this.modelViewMatrix.rotate(angle, x,y,z);
        if (isToChild){
            for (Object child: objects) {
                child.Rotatef(angle,x,y,z, isToChild);
            }
        }
        return this;
    }
    public Object Scalef(float x, float y, float z, boolean isToChild){
        this.modelViewMatrix.scale(x,y,z);
        if (isToChild) {
            for (Object child : objects) {
                child.Scalef(x, y, z, isToChild);
            }
        }
        return this;
    }
    public void isLightON(boolean on)
    {
        if (on){
            switch (this.lightNum){
                case LIGHT0 -> glEnable(GL_LIGHT0);
                case LIGHT1 -> glEnable(GL_LIGHT1);
                case LIGHT2 -> glEnable(GL_LIGHT2);
                case LIGHT3 -> glEnable(GL_LIGHT3);
                case LIGHT4 -> glEnable(GL_LIGHT4);
                case LIGHT5 -> glEnable(GL_LIGHT5);
                case LIGHT6 -> glEnable(GL_LIGHT6);
                case LIGHT7 -> glEnable(GL_LIGHT7);
            }
        }
        else {
            switch (lightNum){
                case LIGHT0 -> glDisable(GL_LIGHT0);
                case LIGHT1 -> glDisable(GL_LIGHT1);
                case LIGHT2 -> glDisable(GL_LIGHT2);
                case LIGHT3 -> glDisable(GL_LIGHT3);
                case LIGHT4 -> glDisable(GL_LIGHT4);
                case LIGHT5 -> glDisable(GL_LIGHT5);
                case LIGHT6 -> glDisable(GL_LIGHT6);
                case LIGHT7 -> glDisable(GL_LIGHT7);
            }
        }
    }
    public void draw(){
        float[] floatBufferForMatrixProjection = new float[16];
        this.modelViewMatrix.get(floatBufferForMatrixProjection);
        glMatrixMode(GL_MODELVIEW);
        glMultMatrixf(floatBufferForMatrixProjection);

        if (isLight()){
            switch (lightNum){
                case LIGHT0 -> {
                    glLightfv(GL_LIGHT0, GL_POSITION, position);
                    glLightfv(GL_LIGHT0, GL_DIFFUSE, diffuse);
                    glLightfv(GL_LIGHT0, GL_AMBIENT, ambient);
                    glLightfv(GL_LIGHT0,GL_SPECULAR,specular);
                    glLightfv(GL_LIGHT0, GL_SPOT_DIRECTION, spot_direction);
                    glLightf(GL_LIGHT0, GL_SPOT_CUTOFF, cut_off);
                    glLightf(GL_LIGHT0, GL_SPOT_EXPONENT, exponent);
                }
                case LIGHT1 -> {
                    glLightfv(GL_LIGHT1, GL_POSITION, position);
                    glLightfv(GL_LIGHT1, GL_DIFFUSE, diffuse);
                    glLightfv(GL_LIGHT1, GL_AMBIENT, ambient);
                    glLightfv(GL_LIGHT1,GL_SPECULAR,specular);
                    glLightfv(GL_LIGHT1, GL_SPOT_DIRECTION, spot_direction);
                     glLightf(GL_LIGHT1, GL_SPOT_CUTOFF, cut_off);
                     glLightf(GL_LIGHT1, GL_SPOT_EXPONENT, exponent);
                }
                case LIGHT2 -> {
                    glLightfv(GL_LIGHT2, GL_POSITION, position);
                    glLightfv(GL_LIGHT2, GL_DIFFUSE, diffuse);
                    glLightfv(GL_LIGHT2, GL_AMBIENT, ambient);
                    glLightfv(GL_LIGHT2,GL_SPECULAR,specular);
                    glLightfv(GL_LIGHT2, GL_SPOT_DIRECTION, spot_direction);
                     glLightf(GL_LIGHT2, GL_SPOT_CUTOFF, cut_off);
                     glLightf(GL_LIGHT2, GL_SPOT_EXPONENT, exponent);
                }
                case LIGHT3 -> {
                    glLightfv(GL_LIGHT3, GL_POSITION, position);
                    glLightfv(GL_LIGHT3, GL_DIFFUSE, diffuse);
                    glLightfv(GL_LIGHT3, GL_AMBIENT, ambient);
                    glLightfv(GL_LIGHT3,GL_SPECULAR,specular);
                    glLightfv(GL_LIGHT3, GL_SPOT_DIRECTION, spot_direction);
                     glLightf(GL_LIGHT3, GL_SPOT_CUTOFF, cut_off);
                     glLightf(GL_LIGHT3, GL_SPOT_EXPONENT, exponent);
                }
                case LIGHT4 -> {
                    glLightfv(GL_LIGHT4, GL_POSITION, position);
                    glLightfv(GL_LIGHT4, GL_DIFFUSE, diffuse);
                    glLightfv(GL_LIGHT4, GL_AMBIENT, ambient);
                    glLightfv(GL_LIGHT4,GL_SPECULAR,specular);
                    glLightfv(GL_LIGHT4, GL_SPOT_DIRECTION, spot_direction);
                     glLightf(GL_LIGHT4, GL_SPOT_CUTOFF, cut_off);
                     glLightf(GL_LIGHT4, GL_SPOT_EXPONENT, exponent);
                }
                case LIGHT5 -> {
                    glLightfv(GL_LIGHT5, GL_POSITION, position);
                    glLightfv(GL_LIGHT5, GL_DIFFUSE, diffuse);
                    glLightfv(GL_LIGHT5, GL_AMBIENT, ambient);
                    glLightfv(GL_LIGHT5,GL_SPECULAR,specular);
                    glLightfv(GL_LIGHT5, GL_SPOT_DIRECTION, spot_direction);
                     glLightf(GL_LIGHT5, GL_SPOT_CUTOFF, cut_off);
                     glLightf(GL_LIGHT5, GL_SPOT_EXPONENT, exponent);
                }
                case LIGHT6 -> {
                    glLightfv(GL_LIGHT6, GL_POSITION, position);
                    glLightfv(GL_LIGHT6, GL_DIFFUSE, diffuse);
                    glLightfv(GL_LIGHT6, GL_AMBIENT, ambient);
                    glLightfv(GL_LIGHT6,GL_SPECULAR,specular);
                    glLightfv(GL_LIGHT6, GL_SPOT_DIRECTION, spot_direction);
                     glLightf(GL_LIGHT6, GL_SPOT_CUTOFF, cut_off);
                     glLightf(GL_LIGHT6, GL_SPOT_EXPONENT, exponent);
                }
                case LIGHT7 -> {
                    glLightfv(GL_LIGHT7, GL_POSITION, position);
                    glLightfv(GL_LIGHT7, GL_DIFFUSE, diffuse);
                    glLightfv(GL_LIGHT7, GL_AMBIENT, ambient);
                    glLightfv(GL_LIGHT7,GL_SPECULAR,specular);
                    glLightfv(GL_LIGHT7, GL_SPOT_DIRECTION, spot_direction);
                     glLightf(GL_LIGHT7, GL_SPOT_CUTOFF, cut_off);
                     glLightf(GL_LIGHT7, GL_SPOT_EXPONENT, exponent);
                }
            }
        }else {
            drawCube(textureOfObject);
        }
        for (Object child: objects) {
            glPushMatrix();
            child.draw();
            glPopMatrix();
        }

    }

    public void AddChild(Object object){
        this.objects.add(object);
    }
    public Object GetChildById(int id){
        return this.objects.get(id);
    }
    public void RemoveChildById(int id){
        this.objects.remove(id);
    }

    private void drawCube(int texture){
        if (!flat) {
            glEnable(GL_TEXTURE_2D);
            glBindTexture(GL_TEXTURE_2D, texture);
            glBegin(GL_TRIANGLES);

            glNormal3f(0.0f, 1.0f, 0.0f);
            glColor3f(this.colors[0], this.colors[1], this.colors[2]);
            glTexCoord2f(0.875f, 0.5f);
            glVertex3f(-1.0f, 1.0f, -1.0f);
            glColor3f(this.colors[0], this.colors[1], this.colors[2]);
            glTexCoord2f(0.625f, 0.75f);
            glVertex3f(1.0f, 1.0f, 1.0f);
            glColor3f(this.colors[0], this.colors[1], this.colors[2]);
            glTexCoord2f(0.625f, 0.5f);
            glVertex3f(1.0f, 1.0f, -1.0f);

            glNormal3f(0.0f, 0.0f, 1.0f);
            glColor3f(this.colors[0], this.colors[1], this.colors[2]);
            glTexCoord2f(0.625f, 0.75f);
            glVertex3f(1.0f, 1.0f, 1.0f);
            glColor3f(this.colors[0], this.colors[1], this.colors[2]);
            glTexCoord2f(0.375f, 1.0f);
            glVertex3f(-1.0f, -1.0f, 1.0f);
            glColor3f(this.colors[0], this.colors[1], this.colors[2]);
            glTexCoord2f(0.375f, 0.75f);
            glVertex3f(1.0f, -1.0f, 1.0f);

            glNormal3f(-1.0f, 0.0f, 0.0f);
            glColor3f(this.colors[0], this.colors[1], this.colors[2]);
            glTexCoord2f(0.625f, 0.0f);
            glVertex3f(-1.0f, 1.0f, 1.0f);
            glColor3f(this.colors[0], this.colors[1], this.colors[2]);
            glTexCoord2f(0.375f, 0.25f);
            glVertex3f(-1.0f, -1.0f, -1.0f);
            glColor3f(this.colors[0], this.colors[1], this.colors[2]);
            glTexCoord2f(0.375f, 0.0f);
            glVertex3f(-1.0f, -1.0f, 1.0f);

            glNormal3f(0.0f, -1.0f, 0.0f);
            glColor3f(this.colors[0], this.colors[1], this.colors[2]);
            glTexCoord2f(0.375f, 0.5f);
            glVertex3f(1.0f, -1.0f, -1.0f);
            glColor3f(this.colors[0], this.colors[1], this.colors[2]);
            glTexCoord2f(0.125f, 0.75f);
            glVertex3f(-1.0f, -1.0f, 1.0f);
            glColor3f(this.colors[0], this.colors[1], this.colors[2]);
            glTexCoord2f(0.125f, 0.5f);
            glVertex3f(-1.0f, -1.0f, -1.0f);


            glNormal3f(1.0f, 0.0f, 0.0f);
            glColor3f(this.colors[0], this.colors[1], this.colors[2]);
            glTexCoord2f(0.625f, 0.5f);
            glVertex3f(1.0f, 1.0f, -1.0f);
            glColor3f(this.colors[0], this.colors[1], this.colors[2]);
            glTexCoord2f(0.375f, 0.75f);
            glVertex3f(1.0f, -1.0f, 1.0f);
            glColor3f(this.colors[0], this.colors[1], this.colors[2]);
            glTexCoord2f(0.375f, 0.5f);
            glVertex3f(1.0f, -1.0f, -1.0f);

            glNormal3f(0.0f, 0.0f, -1.0f);
            glColor3f(this.colors[0], this.colors[1], this.colors[2]);
            glTexCoord2f(0.625f, 0.25f);
            glVertex3f(-1.0f, 1.0f, -1.0f);
            glColor3f(this.colors[0], this.colors[1], this.colors[2]);
            glTexCoord2f(0.375f, 0.5f);
            glVertex3f(1.0f, -1.0f, -1.0f);
            glColor3f(this.colors[0], this.colors[1], this.colors[2]);
            glTexCoord2f(0.375f, 0.25f);
            glVertex3f(-1.0f, -1.0f, -1.0f);

            glNormal3f(0.0f, 1.0f, 0.0f);
            glColor3f(this.colors[0], this.colors[1], this.colors[2]);
            glTexCoord2f(0.875f, 0.5f);
            glVertex3f(-1.0f, 1.0f, -1.0f);
            glColor3f(this.colors[0], this.colors[1], this.colors[2]);
            glTexCoord2f(0.875f, 0.75f);
            glVertex3f(-1.0f, 1.0f, 1.0f);
            glColor3f(this.colors[0], this.colors[1], this.colors[2]);
            glTexCoord2f(0.625f, 0.75f);
            glVertex3f(1.0f, 1.0f, 1.0f);

            glNormal3f(0.0f, 0.0f, 1.0f);
            glColor3f(this.colors[0], this.colors[1], this.colors[2]);
            glTexCoord2f(0.625f, 0.5f);
            glVertex3f(1.0f, 1.0f, 1.0f);
            glColor3f(this.colors[0], this.colors[1], this.colors[2]);
            glTexCoord2f(0.625f, 1.0f);
            glVertex3f(-1.0f, 1.0f, 1.0f);
            glColor3f(this.colors[0], this.colors[1], this.colors[2]);
            glTexCoord2f(0.375f, 1.0f);
            glVertex3f(-1.0f, -1.0f, 1.0f);

            glNormal3f(-1.0f, 0.0f, 0.0f);
            glColor3f(this.colors[0], this.colors[1], this.colors[2]);
            glTexCoord2f(0.625f, 0.0f);
            glVertex3f(-1.0f, 1.0f, 1.0f);
            glColor3f(this.colors[0], this.colors[1], this.colors[2]);
            glTexCoord2f(0.625f, 0.25f);
            glVertex3f(-1.0f, 1.0f, -1.0f);
            glColor3f(this.colors[0], this.colors[1], this.colors[2]);
            glTexCoord2f(0.375f, 0.25f);
            glVertex3f(-1.0f, -1.0f, -1.0f);

            glNormal3f(0.0f, -1.0f, 0.0f);
            glColor3f(this.colors[0], this.colors[1], this.colors[2]);
            glTexCoord2f(0.375f, 0.5f);
            glVertex3f(1.0f, -1.0f, -1.0f);
            glColor3f(this.colors[0], this.colors[1], this.colors[2]);
            glTexCoord2f(0.375f, 0.75f);
            glVertex3f(1.0f, -1.0f, 1.0f);
            glColor3f(this.colors[0], this.colors[1], this.colors[2]);
            glTexCoord2f(0.125f, 0.75f);
            glVertex3f(-1.0f, -1.0f, 1.0f);

            glNormal3f(1.0f, 0.0f, 0.0f);
            glColor3f(this.colors[0], this.colors[1], this.colors[2]);
            glTexCoord2f(0.625f, 0.5f);
            glVertex3f(1.0f, 1.0f, -1.0f);
            glColor3f(this.colors[0], this.colors[1], this.colors[2]);
            glTexCoord2f(0.625f, 0.75f);
            glVertex3f(1.0f, 1.0f, 1.0f);
            glColor3f(this.colors[0], this.colors[1], this.colors[2]);
            glTexCoord2f(0.375f, 0.75f);
            glVertex3f(1.0f, -1.0f, 1.0f);

            glNormal3f(0.0f, 0.0f, -1.0f);
            glColor3f(this.colors[0], this.colors[1], this.colors[2]);
            glTexCoord2f(0.625f, 0.25f);
            glVertex3f(-1.0f, 1.0f, -1.0f);
            glColor3f(this.colors[0], this.colors[1], this.colors[2]);
            glTexCoord2f(0.625f, 0.5f);
            glVertex3f(1.0f, 1.0f, -1.0f);
            glColor3f(this.colors[0], this.colors[1], this.colors[2]);
            glTexCoord2f(0.375f, 0.5f);
            glVertex3f(1.0f, -1.0f, -1.0f);

            glEnd();
            glDisable(GL_TEXTURE_2D);
        }
        else{
            glBindTexture(GL_TEXTURE_2D, texture);
            glEnable(GL_TEXTURE_2D);
            glBegin(GL_TRIANGLE_STRIP);

            glTexCoord2f(0.0f, 0.0f);
            glNormal3f(0,0,1);
            glColor3f(this.colors[0], this.colors[1], this.colors[2]);
            glVertex3f(-1.0f, -1.0f, 0.01f);

            glTexCoord2f(1.0f, 0.0f);
            glNormal3f(0,0,1);
            glColor3f(this.colors[0], this.colors[1], this.colors[2]);
            glVertex3f(1.0f, -1.0f, 0.01f);

            glTexCoord2f(0.0f, 1.0f);
            glNormal3f(0,0,1);
            glColor3f(this.colors[0], this.colors[1], this.colors[2]);
            glVertex3f(-1.0f, 1.0f, 0.01f);

            glTexCoord2f(1.0f, 1.0f);
            glNormal3f(0,0,1);
            glColor3f(this.colors[0], this.colors[1], this.colors[2]);
            glVertex3f(1.0f, 1.0f, 0.01f);

            glEnd();
            glDisable(GL_TEXTURE_2D);
        }
    }
}
