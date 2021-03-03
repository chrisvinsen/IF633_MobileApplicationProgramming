package id.ac.umn.w06_28917;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.LinearInterpolator;

import androidx.annotation.Nullable;
import androidx.interpolator.view.animation.LinearOutSlowInInterpolator;

public class AnimationView extends View
{
    private static final int DURATION = 4000;
    private static final long DELAY = 1000;
    private static final int CONFIG_COLOR = 5;
    private float mX, mY, mRadius;
    private final Paint mPaint = new Paint();
    private AnimatorSet mAnimatorSet = new AnimatorSet();

    public AnimationView(Context context) {
        super(context);
    }

    public AnimationView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        ObjectAnimator grewUp = ObjectAnimator.ofFloat(this, "radius", 0, getWidth());
        grewUp.setDuration(DURATION);
        grewUp.setInterpolator(new LinearInterpolator());

        ObjectAnimator shrink = ObjectAnimator.ofFloat(this, "radius", getWidth(), 0);
        shrink.setDuration(DURATION);
        shrink.setInterpolator(new LinearOutSlowInInterpolator());
        shrink.setStartDelay(DELAY);

        ObjectAnimator reset = ObjectAnimator.ofFloat(this, "radius", 0, getWidth());
        reset.setStartDelay(DELAY);
        reset.setDuration(DURATION);
        reset.setRepeatCount(1);
        reset.setRepeatMode(ValueAnimator.REVERSE);
        mAnimatorSet.play(grewUp).before(shrink);;
        mAnimatorSet.play(reset).after(shrink);
    }

    public void setRadius(float radius) {
        mRadius = radius;
        mPaint.setColor(Color.GREEN + (int) radius / CONFIG_COLOR);
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawCircle(mX, mY, mRadius, mPaint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getActionMasked() == MotionEvent.ACTION_DOWN) {
            mX = event.getX();
            mY = event.getY();
            if (mAnimatorSet != null && mAnimatorSet.isRunning()) {
                mAnimatorSet.cancel();
            }
            mAnimatorSet.start();
        }
        return super.onTouchEvent(event);
    }
}
