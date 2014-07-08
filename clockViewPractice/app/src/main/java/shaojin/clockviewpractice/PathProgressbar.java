package shaojin.clockviewpractice;


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

//        path.addCircle(cx,cy,radius, Path.Direction.CW);
//                path.addArc(oval,0,145);
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
        oval.set(cx - radius + radius / 10, cy - radius + radius / 10, cx + radius - radius / 10, cy + radius - radius / 10);
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
        if (angle == 720) {
            angle = 0;
            colorIndicator = !colorIndicator;
        }
        oval.set(cx - radius + radius / 10, cy - radius + radius / 10, cx + radius - radius / 10, cy + radius - radius / 10);
        path.arcTo(oval, 0, angle);
        postInvalidateDelayed(10);
    }
}
