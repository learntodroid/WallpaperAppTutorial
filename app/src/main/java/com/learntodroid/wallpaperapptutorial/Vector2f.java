package com.learntodroid.wallpaperapptutorial;

public class Vector2f {
    private float x, y;

    public Vector2f(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public void addVector2f(Vector2f vector2f) {
        this.x += vector2f.getX();
        this.y += vector2f.getY();
    }

    public void scaleVector2f(float scalar) {
        this.x = x * scalar;
        this.y = y * scalar;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public void negateX() {
        this.x = -x;
    }

    public void negateY() {
        this.y = -y;
    }
}
