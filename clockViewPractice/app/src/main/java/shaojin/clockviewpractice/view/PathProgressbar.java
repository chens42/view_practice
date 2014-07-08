package shaojin.clockviewpractice.view;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

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

    public PathProgressbar(Context context) {
        super(context);
        init();
    }

    public PathProgressbar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public PathProgressbar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        colorIndicator = true;
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        cx = getWidth() / 2;
        cy = getHeight() / 2;
        radius = Math.min(cx / 2, cy / 2);
        paint.setStrokeWidth(radius / 10);
        if (colorIndicator) {
            paint.setColor(Color.GRAY);
        } else {
            paint.setColor(Color.WHITE);
        }
        cx1 = getWidth() / 4;
        cx2 = 3 * cx1;
        radius = Math.min(cx1, cy);
        oval.set(cx1 - radius + radius / 10, cy - radius + radius / 10, cx1 + radius, cy + radius - radius / 10);
        canvas.drawArc(oval, 0, 360, false, paint);
        if(!isRightCircle){
            if (!colorIndicator) {
                paint.setColor(Color.GRAY);
            } else {
                paint.setColor(Color.WHITE);
            }
        }
        oval.set(cx2 - radius, cy - radius + radius / 10, cx2 + radius - radius / 10, cy + radius - radius / 10);
        canvas.drawArc(oval, 0, 360, false, paint);

        if (!colorIndicator) {
            paint.setColor(Color.GRAY);
        } else {
            paint.setColor(Color.WHITE);
        }
        canvas.drawPath(path, paint);
        update();
    }

    private void update() {
        path.reset();
        angle = angle + 5;
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
