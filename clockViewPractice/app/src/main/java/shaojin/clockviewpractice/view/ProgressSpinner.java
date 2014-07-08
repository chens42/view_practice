package shaojin.clockviewpractice.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

public class ProgressSpinner extends View {
    private Paint paint = new Paint();
    private int cx;
    private int cy;
    private int radius;
    private float innerStart = 0;
    private float outerStart = -180;
    private RectF oval = new RectF();


    public ProgressSpinner(Context context) {
        super(context);
    }

    public ProgressSpinner(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ProgressSpinner(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        cx = getWidth() / 2;
        cy = getHeight() / 2;
        radius = Math.min(cx, cy);
        paint.setAntiAlias(true);
        paint.setColor(Color.BLUE);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(radius / 10);
        oval.set(cx - radius + radius / 10, cy - radius + radius / 10, cx + radius - radius / 10, cy + radius - radius / 10);
        canvas.drawArc(oval, outerStart, 315, false, paint);
        radius = radius / 2;
        paint.setStrokeWidth(20);
        oval.set(cx - radius + 5, cy - radius + 5, cx + radius - 5, cy + radius - 5);
        canvas.drawArc(oval, innerStart, 315, false, paint);
        innerStart = innerStart + 20;
        outerStart = outerStart - 5;
        postInvalidateDelayed(25);
    }
}
