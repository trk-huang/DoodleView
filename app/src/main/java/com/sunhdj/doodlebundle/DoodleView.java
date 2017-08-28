package com.sunhdj.doodlebundle;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.Window;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by huangdaju on 17/8/18.
 */

public class DoodleView extends SurfaceView implements SurfaceHolder.Callback {

    private SurfaceHolder mSurfaceHolder = null;

    private ActionStrategy mActionStrategy;
    private int currentColor = Color.BLACK;
    private int currentSize = 5;
    private Paint mPaint;
    private List<ActionStrategy> mActionStrategyList;
    private Type type = Type.PATH;
    private Bitmap mBitmap;
    private Context mContext;
    private int contentTop;

    public DoodleView(Context context) {
        super(context);
    }

    public DoodleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context.getApplicationContext();
        initView();
    }

    public DoodleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    int titleBarHeight = 0;
    private void initView() {
        mSurfaceHolder = this.getHolder();
        mSurfaceHolder.addCallback(this);
        this.setFocusable(true);
        mPaint = new Paint();
        mPaint.setColor(Color.WHITE);
        mPaint.setStrokeWidth(currentSize);

    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        Canvas canvas = holder.lockCanvas();
        canvas.drawColor(currentColor);
        mSurfaceHolder.unlockCanvasAndPost(canvas);
        mActionStrategyList = new ArrayList<>();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }


    public void setCurrentColor(int currentColor) {
        this.currentColor = currentColor;
    }

    public void setCurrentSize(int currentSize) {
        this.currentSize = currentSize;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public void setContentTop(int contentTop) {
        this.contentTop = contentTop;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        float touchX = event.getRawX();
        float touchY = event.getRawY() - contentTop;

        switch (action) {
            case MotionEvent.ACTION_CANCEL:
                return false;
            case MotionEvent.ACTION_DOWN:
                setCurrentAction(touchX, touchY);
                break;
            case MotionEvent.ACTION_UP:
                mActionStrategyList.add(mActionStrategy);
                mActionStrategy = null;
                break;
            case MotionEvent.ACTION_MOVE:
                Canvas canvas = mSurfaceHolder.lockCanvas();
                canvas.drawColor(Color.WHITE);
                for (ActionStrategy actionStrategy : mActionStrategyList) {
                    actionStrategy.draw(canvas);
                }
                mActionStrategy.move(touchX, touchY);
                mActionStrategy.draw(canvas);
                mSurfaceHolder.unlockCanvasAndPost(canvas);
                break;
        }
        return super.onTouchEvent(event);
    }

    public String saveBitmap() {
//        String path = Environment.getExternalStorageDirectory().getAbsolutePath() +  + System.currentTimeMillis() + ".png";
        String path = Utils.savePath(mContext);
//


        savePicByPng(this.getBitmap(), path);
        return path;
    }

    private void savePicByPng(Bitmap ba, String filePath) {
        FileOutputStream fileoutputStream = null;
        try {
            fileoutputStream = new FileOutputStream(filePath);
            if (null != fileoutputStream) {
                ba.compress(Bitmap.CompressFormat.PNG, 90, fileoutputStream);
                fileoutputStream.flush();
                fileoutputStream.close();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Bitmap getBitmap() {
        mBitmap = Bitmap.createBitmap(getWidth(), getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(mBitmap);
        doDraw(canvas);
        return mBitmap;
    }

    private void doDraw(Canvas canvas) {
        canvas.drawColor(Color.TRANSPARENT);
        for (ActionStrategy actionStrategy : mActionStrategyList) {
            actionStrategy.draw(canvas);
        }
        canvas.drawBitmap(mBitmap, 0, 0, mPaint);
    }

    private void setCurrentAction(float touchX, float touchY) {
        mActionStrategy = ActionFactory.choiceAction(Type.PATH, touchX, touchY, currentColor, currentSize);
    }

    public void reset() {
        if (mActionStrategyList != null && mActionStrategyList.size() > 0) {
            mActionStrategyList.clear();
            Canvas ca = mSurfaceHolder.lockCanvas();
            ca.drawColor(Color.WHITE);
            for (ActionStrategy actionStrategy : mActionStrategyList) {
                actionStrategy.draw(ca);
            }
            mSurfaceHolder.unlockCanvasAndPost(ca);
        }
    }
}
