package github.assac453;


import github.assac453.service.WorkMode;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws InterruptedException, IOException {
        Window w = new Window(512,512, "KR");
        w.setMode(WorkMode.PERSPECTIVE_MOD_1);
        w.setFov(40f);
        w.run();
    }
}
