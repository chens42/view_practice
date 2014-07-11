package shaojin.clockviewpractice.view;


import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import shaojin.clockviewpractice.R;

public class PathProgressbar extends View {

    private Path path = new Path();
    private Paint paint = new Paint();
    private boolean colorIndicator;
    private int cx;
    private int cy;
    private int radius;
    private int angle = 0;
    private RectF oval = new RectF();
    private int cx1;
    private int cx2;
    private boolean isRightCircle = true;
    private int angleSpeed=5;
    private int colorOne=Color.GRAY;
    private int colorTwo=Color.WHITE;



    public PathProgressbar(Context context) {
        super(context);
    }

    public PathProgressbar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context,attrs);
    }

    public PathProgressbar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context,attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        TypedArray a = context.obtainStyledAttributes(attrs,
                R.styleable.PathProgressBarStyleable, 0, 0);
        colorIndicator = true;
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);

        try{
            colorOne=a.getColor(R.styleable.PathProgressBarStyleable_contentColorOne,Color.GRAY);
            colorTwo=a.getColor(R.styleable.PathProgressBarStyleable_contentColorTwo,Color.WHITE);
            angleSpeed=a.getInt(R.styleable.PathProgressBarStyleable_angleSpeed,5);


        }finally {
            a.recycle();
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        cx = getWidth() / 2;
        cy = getHeight() / 2;
        radius = Math.min(cx / 2, cy / 2);
        paint.setStrokeWidth(radius / 3);
        if (colorIndicator) {
            paint.setColor(colorOne);
        } else {
            paint.setColor(colorTwo);
        }
        cx1 = getWidth() / 4;
        cx2 = 3 * cx1;
        radius = Math.min(cx1, cy);
        oval.set(cx1 - radius + radius / 10, cy - radius + radius / 10, cx1 + radius, cy + radius - radius / 10);
        canvas.drawArc(oval, 0, 360, false, paint);
        if(!isRightCircle){
            if (!colorIndicator) {
                paint.setColor(colorOne);
            } else {
                paint.setColor(colorTwo);
            }
        }
        oval.set(cx2 - radius, cy - radius + radius / 10, cx2 + radius - radius / 10, cy + radius - radius / 10);
        canvas.drawArc(oval, 0, 360, false, paint);

        if (!colorIndicator) {
            paint.setColor(colorOne);
        } else {
            paint.setColor(colorTwo);
        }
        canvas.drawPath(path, paint);
        update();
    }

    private void update() {
        path.reset();
        angle = angle + angleSpeed;
        if (isRightCircle) {
            oval.set(cx2 - radius, cy - radius + radius / 10, cx2 + radius - radius / 10, cy + radius - radius / 10);
            path.arcTo(oval, 180, angle);
        } else {
            oval.set(cx1 - radius + radius / 10, cy - radius + radius / 10, cx1 + radius, cy + radius - radius / 10);
            path.arcTo(oval, 0, 0 - angle);
        }
        if (angle == 360) {
            angle = 0;
            if (!isRightCircle) {
                colorIndicator = !colorIndicator;
            }
            isRightCircle = !isRightCircle;
        }
        postInvalidateDelayed(10);
    }
}
