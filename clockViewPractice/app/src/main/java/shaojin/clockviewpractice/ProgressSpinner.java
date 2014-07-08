package shaojin.clockviewpractice;

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
    private float outerStart =180;
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
        canvas.drawCircle(cx, cy, radius, paint);



        paint.setColor(Color.WHITE);
        oval.set(cx-radius-2,cy-radius-2,cx+radius+2,cy+radius+2);
        canvas.drawArc(oval,innerStart,45,true,paint);
        radius = radius * 3 / 4;
        canvas.drawCircle(cx, cy, radius, paint);

        paint.setColor(Color.BLUE);
        radius = radius * 3 / 4;
        canvas.drawCircle(cx, cy, radius, paint);


        paint.setColor(Color.WHITE);
        oval.set(cx-radius-2,cy-radius-2,cx+radius+2,cy+radius+2);
        canvas.drawArc(oval,outerStart,60,true,paint);
        radius = radius / 3;
        canvas.drawCircle(cx, cy, radius, paint);

        innerStart=innerStart+10;
        outerStart=outerStart-10;
        postInvalidateDelayed(25);
    }
}
