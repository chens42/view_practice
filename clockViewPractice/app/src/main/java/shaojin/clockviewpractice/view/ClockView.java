package shaojin.clockviewpractice.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import java.util.Calendar;

import shaojin.clockviewpractice.R;

public class ClockView extends View {
    private double hour = 0;
    private double minute = 0;
    private double second = 0;
    private int cx;
    private int cy;
    private int radius;

    public ClockView(Context context) {
        super(context);
    }

    public ClockView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initViews(context, attrs);
    }

    public ClockView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initViews(context, attrs);
    }

    private void initViews(Context context, AttributeSet attrs) {
        TypedArray a = context.obtainStyledAttributes(attrs,
                R.styleable.ClockViewStyleable, 0, 0);
        Calendar c = Calendar.getInstance();

        try {
            hour = c.get(Calendar.HOUR);
            minute = c.get(Calendar.MINUTE);
            second = c.get(Calendar.SECOND);

        } finally {
            a.recycle();
        }
    }

    public void setHour(double hour) {
        this.hour = hour;
        if (this.hour == 12) {
            this.hour = 0;
        }
        postInvalidateDelayed(1000);

    }

    public void setMinute(double minute) {
        this.minute = minute;
        if (this.minute == 60.0) {
            setHour(hour + 1);
            this.minute = 0;
        } else {
            postInvalidateDelayed(1000);
        }
    }

    public void setSecond(double second) {
        this.second = second;
        if (this.second == 60.0) {
            setMinute(minute + 1);
            this.second = 0.0;
        } else {
            postInvalidateDelayed(1000);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint();                          //define paint and paint color
        paint.setAntiAlias(true);
        paint.setColor(Color.BLACK);

        cx = getWidth() / 2;
        cy = getHeight() / 2;
        radius = Math.min(cx, cy);


        canvas.drawCircle(cx, cy, radius, paint);
        paint.setColor(Color.WHITE);
        canvas.drawCircle(cx, cy, radius - 3, paint);
        paint.setColor(Color.BLACK);
        canvas.drawCircle(cx, cy, 5, paint);
        paint.setTextSize(radius / 10);
        for (int i = 1; i <= 12; i++) {
            int angle = i * 30;
            int xDifference = (int) ((radius - 20) * Math.sin(Math.toRadians(angle)));
            int yDifference = (int) ((radius - 20) * Math.cos(Math.toRadians(angle)));
            canvas.drawText(i + "", xDifference + cx - 10, cy - yDifference + 10, paint);
        }
        drawTime(canvas);
    }

    private void drawTime(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(Color.RED);
        paint.setAntiAlias(true);

        int length = radius * 3 / 4;
        double direction = Math.toRadians(second * 6);
        int xDifference = (int) (length * Math.sin(direction));
        int yDifference = (int) (length * Math.cos(direction));
        canvas.drawLine(cx, cy, xDifference + cx, cy - yDifference, paint);

        length = radius * 2 / 3;
        direction = Math.toRadians((minute + second / 60) * 6);
        xDifference = (int) (length * Math.sin(direction));
        yDifference = (int) (length * Math.cos(direction));
        canvas.drawLine(cx, cy, xDifference + cx, cy - yDifference, paint);


        length = radius / 3;
        direction = Math.toRadians((hour + ((minute + second / 60) / 60)) * 30);
        xDifference = (int) (length * Math.sin(direction));
        yDifference = (int) (length * Math.cos(direction));
        canvas.drawLine(cx, cy, xDifference + cx, cy - yDifference, paint);

        setSecond(second + 1);

    }
}
