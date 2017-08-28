package com.sunhdj.doodlebundle;

import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.Window;

public class MainActivity extends AppCompatActivity {

    DoodleView mDoodleView;
    AlertDialog colorDialog;
    AlertDialog actionDialog;
    AlertDialog sizeDialog;
    int contentTop ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        mDoodleView = (DoodleView) this.findViewById(R.id.doodleView);
        mDoodleView.post(new Runnable() {
            @Override
            public void run() {
                contentTop = getWindow().findViewById(Window.ID_ANDROID_CONTENT).getTop();
                mDoodleView.setContentTop(contentTop);
            }
        });
    }

    @Override
    public void onAttachedToWindow() {

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return mDoodleView.onTouchEvent(event);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_action:
                showActionDialog();
                break;
            case R.id.menu_color:
                showColorDialog();
                break;
            case R.id.menu_reset:
                mDoodleView.reset();
                break;
            case R.id.menu_save:
                mDoodleView.saveBitmap();
                break;
            case R.id.menu_size:
                showSizeDialog();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void showActionDialog() {
        if (actionDialog == null) {
            actionDialog = new AlertDialog.Builder(this).setTitle("选择形状").setSingleChoiceItems(new String[]{"路径", "直线", "矩形", "圆形", "实心矩形", "实心圆"}, 0, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    switch (which) {
                        case 0:
                            mDoodleView.setType(Type.PATH);
                            break;
                        case 1:
                            mDoodleView.setType(Type.LINE);
                            break;
                        case 2:
                            mDoodleView.setType(Type.SQUARE);
                            break;
                        case 3:
                            mDoodleView.setType(Type.CIRCLE);
                            break;
                        case 4:
                            mDoodleView.setType(Type.FILL_SQUARE);
                            break;
                        case 5:
                            mDoodleView.setType(Type.FILL_CIRCLE);
                            break;
                    }
                }
            }).create();
        }
        actionDialog.show();
    }

    private void showColorDialog() {
        if (colorDialog == null) {
            colorDialog = new AlertDialog.Builder(this).setTitle("选择颜色").setSingleChoiceItems(new String[]{"红色", "蓝色", "黄色"}, 0, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    switch (which) {
                        case 0:
                            mDoodleView.setCurrentColor(Color.RED);
                            break;
                        case 1:
                            mDoodleView.setCurrentColor(Color.BLUE);
                            break;
                        case 2:
                            mDoodleView.setCurrentColor(Color.YELLOW);
                            break;
                    }
                }
            }).create();
        }
        colorDialog.show();
    }

    private void showSizeDialog() {
        if (sizeDialog == null) {
            sizeDialog = new AlertDialog.Builder(this).setTitle("选择颜色").setSingleChoiceItems(new String[]{"细", "中", "粗"}, 0, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    switch (which) {
                        case 0:
                            mDoodleView.setCurrentSize(5);
                            break;
                        case 1:
                            mDoodleView.setCurrentSize(15);
                            break;
                        case 2:
                            mDoodleView.setCurrentSize(30);
                            break;
                    }
                }
            }).create();
        }
        sizeDialog.show();
    }


}
