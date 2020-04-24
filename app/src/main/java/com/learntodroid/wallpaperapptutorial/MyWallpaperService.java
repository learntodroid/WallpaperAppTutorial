package com.learntodroid.wallpaperapptutorial;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Handler;
import android.service.wallpaper.WallpaperService;
import android.view.MotionEvent;
import android.view.SurfaceHolder;

public class MyWallpaperService extends WallpaperService {
    @Override
    public Engine onCreateEngine() {
        return new MyWallpaperEngine();
    }

    class MyWallpaperEngine extends Engine {
        private final Handler handler;
        private final Runnable drawRunner;
        private int width, height;
        private boolean visible, touchEnabled;

        private Bitmap dvdLogoBitmap;
        private Vector2f velocity, position;
        private int speedLevel;

        public MyWallpaperEngine() {
            visible = true;
            handler = new Handler();
            drawRunner = new Runnable() {
                @Override
                public void run() {
                    draw();
                }
            };

            touchEnabled = true;

            dvdLogoBitmap = BitmapFactory.decodeResource(getApplicationContext().getResources(), R.raw.small_dvd_logo);
            position = new Vector2f(width / 2, height / 2);
            velocity = new Vector2f(10, 10);
            speedLevel = 1;

            handler.post(drawRunner);
        }

        @Override
        public void onVisibilityChanged(boolean visible) {
            this.visible = visible;
            if (visible) {
                handler.post(drawRunner);
            } else {
                handler.removeCallbacks(drawRunner);
            }
        }

        @Override
        public void onSurfaceChanged(SurfaceHolder holder, int format, int width, int height) {
            super.onSurfaceChanged(holder, format, width, height);
            this.width = width;
            this.height = height;
        }

        @Override
        public void onSurfaceDestroyed(SurfaceHolder holder) {
            super.onSurfaceDestroyed(holder);
            this.visible = false;
            handler.removeCallbacks(drawRunner);
        }

        @Override
        public void onTouchEvent(MotionEvent event) {
            super.onTouchEvent(event);
            if (touchEnabled) {
                if (event.getAction() == android.view.MotionEvent.ACTION_UP) {
                    applySpeed();
                }
            }
        }

        private void applySpeed() {
            if (speedLevel == 5) {
                speedLevel = 1;
            } else {
                speedLevel++;
            }
            velocity = new Vector2f(Math.signum(velocity.getX()) * speedLevel * 10f, Math.signum(velocity.getY()) * speedLevel * 10f);
        }

        private void handleCollisions() {
            if ((position.getX() + dvdLogoBitmap.getWidth()) > width) {
                velocity.negateX();
            }

            if (position.getX() < 0) {
                velocity.negateX();
            }

            if ((position.getY() + dvdLogoBitmap.getHeight()) > height) {
                velocity.negateY();
            }

            if (position.getY() < 0) {
                velocity.negateY();
            }
        }

        private void draw() {
            SurfaceHolder holder = getSurfaceHolder();
            Canvas canvas = null;
            try {
                canvas = holder.lockCanvas();
                if (canvas != null) {
                    handleCollisions();
                    position.addVector2f(velocity);
                    drawBitmap(canvas);
                }
            } finally {
                if (canvas != null) {
                    holder.unlockCanvasAndPost(canvas);
                }
            }
            handler.removeCallbacks(drawRunner);
            if (visible) {
                handler.postDelayed(drawRunner, 50);
            }
        }

        private void drawBitmap(Canvas canvas) {
            canvas.drawColor(Color.WHITE);
            canvas.drawBitmap(dvdLogoBitmap, position.getX(), position.getY(), null);
        }
    }
}
