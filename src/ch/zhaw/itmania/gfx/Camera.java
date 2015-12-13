package ch.zhaw.itmania.gfx;

import ch.zhaw.itmania.entities.Entity;

import java.util.ArrayList;

public class Camera {

    private ArrayList<CameraListener> cameraListeners = new ArrayList<CameraListener>();
    private float xOffset, yOffset;
    private Screen screen;

    public Camera(Screen screen, float xOffset, float yOffset) {
        this.xOffset = xOffset;
        this.yOffset = yOffset;
        this.screen = screen;
    }

    public void move(float xAmount, float yAmount) {
        xOffset += xAmount;
        yOffset += yAmount;

        for(CameraListener listener : cameraListeners) {
            listener.onCameraMove();
        }

        System.out.println("Moved Called");
    }

    public void addCameraListener(CameraListener event) {
        cameraListeners.add(event);
    }

    public void follow(Entity entity) {
        xOffset = entity.getXPosition() - screen.getWidth() / 2 + entity.getWidth() / 2;
        yOffset = entity.getYPosition() - screen.getHeight() / 2 + entity.getWidth() / 2;
    }

    public float getXOffset() {
        return xOffset;
    }

    public float getYOffset() {
        return yOffset;
    }
}
