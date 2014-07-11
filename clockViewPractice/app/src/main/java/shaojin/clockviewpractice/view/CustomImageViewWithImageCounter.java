package shaojin.clockviewpractice.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Created by shaojin on 10/07/14.
 */
public class CustomImageViewWithImageCounter extends ImageView {
    public CustomImageViewWithImageCounter(Context context) {
        super(context);
    }

    public CustomImageViewWithImageCounter(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomImageViewWithImageCounter(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint  = new Paint(Paint.LINEAR_TEXT_FLAG);
        paint.setColor(Color.GRAY);
        paint.setTextSize(50);
        System.out.println("Drawing text");
        canvas.drawText("Hello World in custom view", 100, 100, paint);
    }
}
