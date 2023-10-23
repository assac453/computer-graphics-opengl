package github.assac453;

import github.assac453.blocks.*;
import github.assac453.entities.*;
import github.assac453.service.GenerateColor;
import github.assac453.service.WorkMode;
import org.joml.Matrix4f;
import org.lwjgl.Version;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWFramebufferSizeCallback;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;
import org.lwjgl.system.MemoryStack;

import java.io.IOException;
import java.nio.IntBuffer;
import java.util.Objects;


import static org.lwjgl.glfw.Callbacks.glfwFreeCallbacks;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryStack.stackPush;
import static org.lwjgl.system.MemoryUtil.NULL;
public class Window {
    private final GameMaster gameMaster;
    // The window handle
    private long window;
    private WorkMode mode;
    float time = 1;

    private static int width;
    private static int height;
    private final String title;
    public static int getWidth(){return width;}
    public static int getHeight(){return height;}
    private float FOV;
    public void setFov(float fov){this.FOV =fov; }
    public Window(int width, int height, String title){
        gameMaster = new GameMaster();
        this.width = width;
        this.height = height;
        this.title = title;
    }
    public void run() throws IOException {
        System.out.println("Hello LWJGL " + Version.getVersion() + "!");

        init();
        loop();

        // Free the window callbacks and destroy the window
        glfwFreeCallbacks(window);
        glfwDestroyWindow(window);

        // Terminate GLFW and free the error callback
        glfwTerminate();
        Objects.requireNonNull(glfwSetErrorCallback(null)).free();
    }
    public void setMode(WorkMode mode){
        this.mode = mode;
    }
    private void init() {
        // Setup an error callback. The default implementation
        // will print the error message in System.err.
        GLFWErrorCallback.createPrint(System.err).set();

        // Initialize GLFW. Most GLFW functions will not work before doing this.
        if ( !glfwInit() )
            throw new IllegalStateException("Unable to initialize GLFW");

        // Configure GLFW
        glfwDefaultWindowHints(); // optional, the current window hints are already the default
        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE); // the window will stay hidden after creation
        glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE); // the window will be resizable

        // Create the window
        window = glfwCreateWindow(width, height, title, NULL, NULL);
        if ( window == NULL )
            throw new RuntimeException("Failed to create the GLFW window");

        // Setup a key callback. It will be called every time a key is pressed, repeated or released.
        glfwSetKeyCallback(window, (window, key, scancode, action, mods) -> {
            if ( key == GLFW_KEY_ESCAPE && action == GLFW_RELEASE )
                glfwSetWindowShouldClose(window, true); // We will detect this in the rendering loop
        });
        glfwSetFramebufferSizeCallback(window, new GLFWFramebufferSizeCallback() {
            @Override
            public void invoke(long window, int width, int height) {

                int maxSize = Math.max(width, height);
                int offsetX = (width - maxSize) / 2;
                int offsetY = (height - maxSize) / 2;
                glViewport(offsetX, offsetY, maxSize, maxSize);

                glPushMatrix();
                glLoadIdentity();
                glMatrixMode(GL_PROJECTION);
                Matrix4f perspective = new Matrix4f();
                perspective.setPerspective(60f,(float)width/height,1,1000);
                glMatrixMode(GL_MODELVIEW);
                glPopMatrix();
            }
        });
        // Get the thread stack and push a new frame
        try ( MemoryStack stack = stackPush() ) {
            IntBuffer pWidth = stack.mallocInt(1); // int*
            IntBuffer pHeight = stack.mallocInt(1); // int*

            // Get the window size passed to glfwCreateWindow
            glfwGetWindowSize(window, pWidth, pHeight);

            // Get the resolution of the primary monitor
            GLFWVidMode vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());

            // Center the window
            assert vidmode != null;
            glfwSetWindowPos(
                    window,
                    (vidmode.width() - pWidth.get(0)) / 2,
                    (vidmode.height() - pHeight.get(0)) / 2
            );
        } // the stack frame is popped automatically

        // Make the OpenGL context current
        glfwMakeContextCurrent(window);
        // Enable v-sync
        glfwSwapInterval(1);

        // Make the window visible
        glfwShowWindow(window);
    }
    private void setupScene(){
        switch (mode) {
            case ORTHOGRAPHIC -> {
                glEnable(GL_DEPTH_TEST);
                glMatrixMode(GL_PROJECTION);
                glLoadIdentity();
                var f = (width / height);
                glOrtho(-1 * f, 1 * f, -1, 1, 1.0, 1000.0);
                glMatrixMode(GL_MODELVIEW);
                glLoadIdentity();
            }
            case PERSPECTIVE_MOD_1 -> {
                glEnable(GL_DEPTH_TEST);
                glMatrixMode(GL_PROJECTION);
                glLoadIdentity();
                var f = (width / height);
                glFrustum(-1 * f, 1 * f, -1, 1, 1.0, 1000.0);
                glMatrixMode(GL_MODELVIEW);
                glLoadIdentity();
            }
            case PERSPECTIVE_MOD_2 -> {
                glEnable(GL_DEPTH_TEST);
                float fieldOfView = this.FOV;
                float aspect = (float) this.width / (float) this.height;
                float zNear = 0.1f;
                float zFar = 1000.0f;
                Matrix4f matrixProjection = new Matrix4f();
                matrixProjection.setPerspective(fieldOfView, aspect, zNear, zFar);
                float[] floatBufferForMatrixProjection = new float[16];
                matrixProjection.get(floatBufferForMatrixProjection);
                glMatrixMode(GL_PROJECTION);
                glLoadIdentity();
                glLoadMatrixf(floatBufferForMatrixProjection);
                glMatrixMode(GL_MODELVIEW);
                glLoadIdentity();
            }
        }

    }
    private void enableLight(){
        glLightModelf(GL_LIGHT_MODEL_TWO_SIDE,1);
        glEnable(GL_NORMALIZE);
        glEnable(GL_COLOR_MATERIAL);

        glEnable(GL_DEPTH_TEST);
        glEnable(GL_LIGHTING);

        glClearColor((float)141/255, (float)176/255, (float)255/255,0);
        glClearDepth(1);
        glDepthFunc(GL_LEQUAL);
    }
    private void loop() throws IOException {
        GL.createCapabilities();
        glMaterialfv(GL_FRONT_FACE,GL_DIFFUSE,new float[]{0f,0.8f,0.8f,1f});
        setupScene();
        enableLight();
        Object creeper = Creeper.creeper();
        creeper.Scalef(0.5f,0.5f,0.5f,false);
        Object plane = new Object(GenerateColor.Generate(62,117,59)).Translatef(0,0,-25,false);
        for (int i = 0; i < 25; i++) {
            for (int j = 0; j < 30; j++) {
                plane.AddChild(new Object(GenerateColor.Generate(0.5f,0.5f,0.5f)).Translatef(i*2,0,j*2,false).setTextureOfObject(TextureGen.textureGen("C:\\Users\\aleksey\\Pictures\\dirt.png")));
            }
        }
        plane.Translatef(-10,-2.5f,-10,false);
        plane.Scalef(0.5f,0.5f,0.5f,false);
        Object cube = new Object(GenerateColor.Generate(0.0f, 1.0f, 0.3f)).Translatef(-10,0,-20,false).setTextureOfObject(TextureGen.textureGen("C:\\Users\\aleksey\\Pictures\\diamond.png"));
        cube.AddChild(new Object(GenerateColor.Generate(0.5f,0.5f,0.5f)).Translatef(-2,0,0,false).setTextureOfObject(TextureGen.textureGen("C:\\Users\\aleksey\\Pictures\\diamond.png")));
        cube.AddChild(new Object(GenerateColor.Generate(99,63, 32)).Translatef(0,2,0,false).setTextureOfObject(TextureGen.textureGen("C:\\Users\\aleksey\\Pictures\\oak.png"))); //коричневый

        Object test = new Object(GenerateColor.Generate(0.5f,0.5f,0.5f));
        test.Translatef(0,0,-3,false);
        test.setTextureOfObject(TextureGen.textureGen("C:\\Users\\aleksey\\Pictures\\lesopilka.png"));

        creeper.Translatef(0,0,-200,false);
        creeper.Translatef(-100,0,0,false);
        creeper.Rotatef(30 / (180.0f / (float)Math.PI),0,1,0,false);

        Object sun = new Object(GenerateColor.Generate(0.5f,0.5f,0.5f));
        sun.Translatef(0,12,0,false);
        sun.Rotatef(30,1,0,0,false);
        sun.setTextureOfObject(TextureGen.textureGen("C:\\Users\\aleksey\\Pictures\\sun.png"));
        sun.Scalef(3,3,3,false);
        sun.flat = true;

        Object steve = Steve.steve();
        steve.Translatef(0,0,-20,false);
        gameMaster.AddObject(steve);

        gameMaster.AddObject(creeper);

        Object house = House.house();
        house.Translatef(-5,-3,-40,false);
        house.Rotatef(145 / (180.0f / (float)Math.PI),0,1,0,false);

        gameMaster.AddObject(house);

        Object tree = Tree.tree();
        tree.Scalef(0.25f,0.25f,0.25f,false);
        tree.Translatef(35,-5,-50,false);
        tree.AddChild(Tree.tree().Translatef(-10,0,0,false));
        tree.AddChild(Tree.tree().Translatef(-10,0,10,false));
        tree.AddChild(Tree.tree().Translatef(-10,0,20,false));
        tree.AddChild(Tree.tree().Translatef(-10,0,30,false));
        gameMaster.AddObject(tree);

        Object torch = Torch.torch();
        torch.Translatef(0,10,-55,false);
        torch.Scalef(0.25f,0.25f,0.25f,false);
        gameMaster.AddObject(torch);

        Object torch_light = new Object(GenerateColor.Generate(0,0,0));
        torch_light.setLight(true);
        torch_light.lightNum=LIGHT_NUM.LIGHT4;
        torch_light.setPosition(new float[]{0,0,0,1});
        torch_light.setDiffuse(new float[]{1,1,1,1});
        torch_light.setAmbient(new float[]{0f,0f,0f,1f});
        torch_light.setAmbient(new float[]{0f,0f,0f,1f});
        torch_light.setSpecular(new float[]{0.25f,0.25f,0.25f,1f});
        torch_light.setSpot_direction(new float[]{0,-1,0,1});
        torch_light.setCut_off(50f);
        torch_light.setExponent(1f);
        torch_light.Translatef(0,5,0,false);
        torch.AddChild(torch_light);

        gameMaster.AddObject(cube);

        gameMaster.AddObject(plane);

        Object light0 = new Object(GenerateColor.Generate(0,0,0)); //прожектор из крипера
        light0.setLight(true);
        light0.lightNum=LIGHT_NUM.LIGHT0;
        light0.setPosition(new float[]{0,10,0,1});
        light0.setDiffuse(new float[]{1,1,1,1});
        light0.setAmbient(new float[]{0f,0f,0f,1f});
        light0.setSpecular(new float[]{0.25f,0.25f,0.25f,1f});
        light0.setSpot_direction(new float[]{0,0,1,1});
        light0.setCut_off(30f);
        light0.setExponent(1f);
        light0.Translatef(0,0,0,false);
        steve.AddChild(light0);

        Object anchorLight1 = new Object(GenerateColor.Generate(0.5f,0.5f,0.5f)); //куб на котором вращается свет
        anchorLight1.Translatef(0,0,-15,false);
        anchorLight1.setTextureOfObject(TextureGen.textureGen("C:\\Users\\aleksey\\Pictures\\rock-texture_(2).jpg"));
        gameMaster.AddObject(anchorLight1);

        Object light1 = new Object(GenerateColor.Generate(0,0,0));//солнце которое вращается
        light1.setLight(true);
        light1.lightNum = LIGHT_NUM.LIGHT1;
        light1.setPosition(new float[]{0,0,0,1});
        light1.setDiffuse(new float[]{1,1,1,1});
        light1.Translatef(0,10,0,false);
        anchorLight1.AddChild(light1);

        anchorLight1.AddChild(sun);

        Object light2 = new Object(GenerateColor.Generate(0,0,0)); //направленный источник
        light2.setLight(true);
        light2.lightNum = LIGHT_NUM.LIGHT2;
        light2.setPosition(new float[]{0,10,1,0});
        light2.setDiffuse(new float[]{1,1,1,1});

        gameMaster.AddObject(light2);

        double timer=0;
        float koef=5;
        double timer_creeper_walk=0;
        while ( !glfwWindowShouldClose(window) ) {
            timer_creeper_walk+=1;
            System.out.println(timer_creeper_walk);
            if (timer_creeper_walk<200 && timer_creeper_walk>0){
                creeper.Translatef(0,0,1f,false);
            }
            if (timer_creeper_walk>=200) {
                timer_creeper_walk = -200;
            }
            if (timer_creeper_walk<0){
                creeper.Translatef(0,0,-1f,false);
            }
            if (timer>1800 &&timer<4800) {
                torch_light.isLightON(true);
            }
            if (timer>4800){timer=0;torch_light.isLightON(false);}
            timer++;
            time+=0.01;
            if (time>2){koef*=-1;time=0;}
            for (int i=1;i<=4;i++){
                if (i%2==0) {
                    creeper.GetChildById(i).Rotatef(0.001f*koef,1,0,0,false);
                }
                else {
                    creeper.GetChildById(i).Rotatef(0.001f*(-koef),1,0,0,false);
                }
            }
            glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT); // clear the framebuffer
            anchorLight1.Rotatef(0.001f,0,0,1,false);
            gameMaster.DrawObjects();

            if (glfwGetKey(window,GLFW_KEY_UP)==GLFW_PRESS){
                gameMaster.GetObjectById(0).Translatef(0,0,1.1f,false);
            }
            if (glfwGetKey(window,GLFW_KEY_DOWN)==GLFW_PRESS){
                gameMaster.GetObjectById(0).Translatef(0,0,-1.1f,false);
            }
            if (glfwGetKey(window,GLFW_KEY_A)==GLFW_PRESS){
                gameMaster.GetObjectById(0).Rotatef(-0.01f,0,1,0,false);
            }
            if (glfwGetKey(window,GLFW_KEY_D)==GLFW_PRESS){
                gameMaster.GetObjectById(0).Rotatef(0.01f,0,1,0,false);
            }
            if (glfwGetKey(window,GLFW_KEY_S)==GLFW_PRESS){
                gameMaster.GetObjectById(0).Translatef(0,-1.1f,0f,false);
            }
            if (glfwGetKey(window,GLFW_KEY_W)==GLFW_PRESS){
                gameMaster.GetObjectById(0).Translatef(0,1.1f,0f,false);
            }
            if (glfwGetKey(window,GLFW_KEY_RIGHT)==GLFW_PRESS){
                light0.Translatef(0,0.1f,0,false);
            }
            if (glfwGetKey(window,GLFW_KEY_LEFT)==GLFW_PRESS){
                light0.Translatef(0,-0.1f,0,false);
            }
            if (glfwGetKey(window, GLFW_KEY_0)==GLFW_PRESS){
                glDisable(GL_LIGHTING);
            }
            if (glfwGetKey(window, GLFW_KEY_9)==GLFW_PRESS){
                glEnable(GL_LIGHTING);
            }
            if (glfwGetKey(window, GLFW_KEY_1)==GLFW_PRESS){
                light1.isLightON(true);
            }
            if (glfwGetKey(window, GLFW_KEY_2)==GLFW_PRESS){
                light1.isLightON(false);
            }
            if (glfwGetKey(window, GLFW_KEY_3)==GLFW_PRESS){
                light2.isLightON(true);
            }
            if (glfwGetKey(window, GLFW_KEY_4)==GLFW_PRESS){
                light2.isLightON(false);
            }
            if (glfwGetKey(window, GLFW_KEY_5)==GLFW_PRESS){
                light0.isLightON(true);
            }
            if (glfwGetKey(window, GLFW_KEY_6)==GLFW_PRESS){
                light0.isLightON(false);
            }
            if (glfwGetKey(window, GLFW_KEY_7)==GLFW_PRESS){
                torch_light.isLightON(true);
            }
            if (glfwGetKey(window, GLFW_KEY_8)==GLFW_PRESS){
                torch_light.isLightON(false);
            }

            glfwSwapBuffers(window); // swap the color buffers
            glfwPollEvents();
        }
        glDisable(GL_LIGHTING);
    }
}
