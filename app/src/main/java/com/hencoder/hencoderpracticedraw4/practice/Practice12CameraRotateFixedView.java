package com.hencoder.hencoderpracticedraw4.practice;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Camera;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Point;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.hencoder.hencoderpracticedraw4.R;

public class Practice12CameraRotateFixedView extends View {
    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    Bitmap bitmap;
    Point point1 = new Point(200, 200);
    Point point2 = new Point(600, 200);
    Camera camera = new Camera();

    public Practice12CameraRotateFixedView(Context context) {
        super(context);
    }

    public Practice12CameraRotateFixedView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Practice12CameraRotateFixedView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    {
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.maps);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int bitmapWidth = bitmap.getWidth();
        int bitmapHeight = bitmap.getHeight();
        int center1X = point1.x + bitmapWidth / 2;
        int center1Y = point1.y + bitmapHeight / 2;
        int center2X = point2.x + bitmapWidth / 2;
        int center2Y = point2.y + bitmapHeight / 2;

        canvas.save();
        camera.save(); // 保存 Camera 的状态
        camera.rotateX(30); // 旋转 Camera 的三维空间
        canvas.translate(center1X, center1Y); // 旋转之后把投影移动回来
        camera.applyToCanvas(canvas); // 把旋转投影到 Canvas
        canvas.translate(-center1X, -center1Y); // 旋转之前把绘制内容移动到轴心（原点）
        camera.restore(); // 恢复 Camera 的状态
        canvas.drawBitmap(bitmap, point1.x, point1.y, paint);
        canvas.restore();


        canvas.save();
        camera.save(); // 保存 Camera 的状态
        camera.rotateY(30); // 旋转 Camera 的三维空间
        canvas.translate(center2X, center2Y); // 旋转之后把投影移动回来
        camera.applyToCanvas(canvas); // 把旋转投影到 Canvas
        canvas.translate(-center2X, -center2Y); // 旋转之前把绘制内容移动到轴心（原点）
        camera.restore(); // 恢复 Camera 的状态
        canvas.drawBitmap(bitmap, point2.x, point2.y, paint);
        canvas.restore();

    }
}
