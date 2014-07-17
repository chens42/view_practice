package shaojin.clockviewpractice.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

public class ProgressSpinner extends View {
    private Paint outerPaint = new Paint();
    private Paint innerPaint = new Paint();
    private int cx;
    private int cy;
    private int radius;
    private float innerStart = 0;
    private float outerStart = -180;
    private RectF outerOval = new RectF();
    private RectF innerOval = new RectF();
    private Path outerPath = new Path();
    private Path innerPath = new Path();


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
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        cx = getWidth() / 2;
        cy = getHeight() / 2;
        radius = Math.min(cx, cy);

        outerPaint.setAntiAlias(true);
        outerPaint.setColor(Color.BLUE);
        outerPaint.setStyle(Paint.Style.STROKE);
        outerPaint.setStrokeWidth(10);

        innerPaint.setAntiAlias(true);
        innerPaint.setColor(Color.BLUE);
        innerPaint.setStyle(Paint.Style.STROKE);
        innerPaint.setStrokeWidth(20);

        outerOval.set(cx - radius + radius / 10, cy - radius + radius / 10, cx + radius - radius / 10, cy + radius - radius / 10);
        outerPath.addArc(outerOval, 0, 315);
        radius = radius / 2;
        innerOval.set(cx - radius + 5, cy - radius + 5, cx + radius - 5, cy + radius - 5);
        innerPath.addArc(innerOval, 0, 315);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.save();
        canvas.rotate(outerStart, cx, cy);
        canvas.drawPath(outerPath, outerPaint);
        canvas.restore();

        canvas.save();
        canvas.rotate(innerStart, cx, cy);
        canvas.drawPath(innerPath, innerPaint);
        canvas.restore();

        innerStart = innerStart + 20;
        outerStart = outerStart - 10;

        postInvalidateDelayed(25);
    }
}
