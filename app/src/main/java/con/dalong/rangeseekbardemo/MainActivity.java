package con.dalong.rangeseekbardemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import com.dalong.rangeseekbar.RangeBar;

import con.dalong.rangeseekbardemo.dialog.ColorPickerDialog;

public class MainActivity extends AppCompatActivity{

    private static final int DEFAULT_BAR_COLOR = 0xffcccccc;
    private static final int DEFAULT_CONNECTING_LINE_COLOR = 0xff33b5e5;
    private static final int HOLO_BLUE = 0xff33b5e5;

    // 设置初始值，这样图像将被绘制
    private static final int DEFAULT_THUMB_COLOR_NORMAL = -1;
    private static final int DEFAULT_THUMB_COLOR_PRESSED = -1;

    // 设置变量以保存每个属性的颜色
    private int mBarColor = DEFAULT_BAR_COLOR;
    private int mConnectingLineColor = DEFAULT_CONNECTING_LINE_COLOR;
    private int mThumbColorNormal = DEFAULT_THUMB_COLOR_NORMAL;
    private int mThumbColorPressed = DEFAULT_THUMB_COLOR_PRESSED;

    private RangeBar rangebar;

    //在旋转屏幕/重新启动活动时保存状态
    @Override
    protected void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putInt("BAR_COLOR", mBarColor);
        bundle.putInt("CONNECTING_LINE_COLOR", mConnectingLineColor);
        bundle.putInt("THUMB_COLOR_NORMAL", mThumbColorNormal);
        bundle.putInt("THUMB_COLOR_PRESSED", mThumbColorPressed);
    }

    // 在旋转屏幕/重新启动活动时恢复状态
    @Override
    protected void onRestoreInstanceState(Bundle bundle) {
        super.onRestoreInstanceState(bundle);
        mBarColor = bundle.getInt("BAR_COLOR");
        mConnectingLineColor = bundle.getInt("CONNECTING_LINE_COLOR");
        mThumbColorNormal = bundle.getInt("THUMB_COLOR_NORMAL");
        mThumbColorPressed = bundle.getInt("THUMB_COLOR_PRESSED");

        // 将文本颜色更改为适当的颜色
        colorChanged(Component.BAR_COLOR, mBarColor);
        colorChanged(Component.CONNECTING_LINE_COLOR, mConnectingLineColor);
        colorChanged(Component.THUMB_COLOR_NORMAL, mThumbColorNormal);
        colorChanged(Component.THUMB_COLOR_PRESSED, mThumbColorPressed);

        rangebar = (RangeBar) findViewById(R.id.rangebar1);

        final TextView leftIndexValue = (TextView) findViewById(R.id.leftIndexValue);
        final TextView rightIndexValue = (TextView) findViewById(R.id.rightIndexValue);
        leftIndexValue.setText("" + rangebar.getLeftIndex());
        rightIndexValue.setText("" + rangebar.getRightIndex());

        // 设置焦点到主布局，而不是索引文本字段
        findViewById(R.id.mylayout).requestFocus();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        //获取控件
        final Button barColor = (Button) findViewById(R.id.barColor);
        final Button connectingLineColor = (Button) findViewById(R.id.connectingLineColor);
        final Button thumbColorNormal = (Button) findViewById(R.id.thumbColorNormal);
        final Button thumbColorPressed = (Button) findViewById(R.id.thumbColorPressed);
        final Button resetThumbColors = (Button) findViewById(R.id.resetThumbColors);
        final Button refreshButton = (Button) findViewById(R.id.refresh);
        final EditText leftIndexValue = (EditText) findViewById(R.id.leftIndexValue);
        final EditText rightIndexValue = (EditText) findViewById(R.id.rightIndexValue);
        rangebar = (RangeBar) findViewById(R.id.rangebar1);


        // 设置颜色按钮的初始颜色
        barColor.setTextColor(DEFAULT_BAR_COLOR);
        connectingLineColor.setTextColor(DEFAULT_CONNECTING_LINE_COLOR);
        thumbColorNormal.setTextColor(HOLO_BLUE);
        thumbColorPressed.setTextColor(HOLO_BLUE);




        //设置索引的显示值
        rangebar.setOnRangeBarChangeListener(new RangeBar.OnRangeBarChangeListener() {
            @Override
            public void onIndexChangeListener(RangeBar rangeBar, int leftThumbIndex, int rightThumbIndex) {

                leftIndexValue.setText("" + leftThumbIndex);
                rightIndexValue.setText("" + rightThumbIndex);
            }
        });

        // 通过输入值来刷新设置拖动条的索引位置
        refreshButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String leftIndex = leftIndexValue.getText().toString();
                String rightIndex = rightIndexValue.getText().toString();
                try {
                    if (!leftIndex.isEmpty() && !rightIndex.isEmpty())
                    {
                        int leftIntIndex = Integer.parseInt(leftIndex);
                        int rightIntIndex = Integer.parseInt(rightIndex);
                        rangebar.setThumbIndices(leftIntIndex, rightIntIndex);
                    }
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                }
            }
        });

        //设置数量部分

        // 设置刻度数量
        final TextView tickCount = (TextView) findViewById(R.id.tickCount);
        SeekBar tickCountSeek = (SeekBar) findViewById(R.id.tickCountSeek);
        tickCountSeek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar tickCountSeek, int progress, boolean fromUser) {
                try {
                    rangebar.setTickCount(progress);
                } catch (IllegalArgumentException e) {
                }
                tickCount.setText("tickCount = " + progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        // 设置刻度高度
        final TextView tickHeight = (TextView) findViewById(R.id.tickHeight);
        SeekBar tickHeightSeek = (SeekBar) findViewById(R.id.tickHeightSeek);
        tickHeightSeek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar tickHeightSeek, int progress, boolean fromUser) {
                rangebar.setTickHeight(progress);
                tickHeight.setText("tickHeight = " + progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        // 设置bar的竖直刻度的宽度
        final TextView barWeight = (TextView) findViewById(R.id.barWeight);
        SeekBar barWeightSeek = (SeekBar) findViewById(R.id.barWeightSeek);
        barWeightSeek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar barWeightSeek, int progress, boolean fromUser) {
                rangebar.setBarWeight(progress);
                barWeight.setText("barWeight = " + progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        // 设置连接线的宽度
        final TextView connectingLineWeight = (TextView) findViewById(R.id.connectingLineWeight);
        SeekBar connectingLineWeightSeek = (SeekBar) findViewById(R.id.connectingLineWeightSeek);
        connectingLineWeightSeek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar connectingLineWeightSeek, int progress, boolean fromUser) {
                rangebar.setConnectingLineWeight(progress);
                connectingLineWeight.setText("connectingLineWeight = " + progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        // 设置圆圈的半径
        final TextView thumbRadius = (TextView) findViewById(R.id.thumbRadius);
        SeekBar thumbRadiusSeek = (SeekBar) findViewById(R.id.thumbRadiusSeek);
        thumbRadiusSeek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar thumbRadiusSeek, int progress, boolean fromUser) {
                if (progress == 0) {
                    rangebar.setThumbRadius(-1);
                    thumbRadius.setText("thumbRadius = N/A");
                }
                else {
                    rangebar.setThumbRadius(progress);
                    thumbRadius.setText("thumbRadius = " + progress);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        // 设置颜色部分

        // 设置分割线刻度线的颜色
        barColor.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                initColorPicker(Component.BAR_COLOR, mBarColor);
            }
        });

        // 设置连接线的颜色
        connectingLineColor.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                initColorPicker(Component.CONNECTING_LINE_COLOR, mConnectingLineColor);
            }
        });

        // 设置圆圈拖动条的颜色
        thumbColorNormal.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                initColorPicker(Component.THUMB_COLOR_NORMAL, mThumbColorNormal);
            }
        });

        // 设置圆圈拖动条在按下拖动的颜色
        thumbColorPressed.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                initColorPicker(Component.THUMB_COLOR_PRESSED, mThumbColorPressed);
            }
        });

        // 重置所有拖动条的颜色
        resetThumbColors.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                rangebar.setThumbColorNormal(-1);
                rangebar.setThumbColorPressed(-1);

                mThumbColorNormal = -1;
                mThumbColorPressed = -1;

                thumbColorNormal.setText("thumbColorNormal = N/A");
                thumbColorPressed.setText("thumbColorPressed = N/A");
                thumbColorNormal.setTextColor(HOLO_BLUE);
                thumbColorPressed.setTextColor(HOLO_BLUE);
            }
        });

    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addCategory(Intent.CATEGORY_HOME);
        startActivity(intent);
    }



    /**
     * 设置颜色
     * @param component
     * @param initialColor
     */
    private void initColorPicker(final Component component, int initialColor) {

        ColorPickerDialog colorPickerDialog = new ColorPickerDialog(this, initialColor, new ColorPickerDialog.OnColorSelectedListener() {
            @Override
            public void onColorSelected(int newColor) {
                colorChanged(component,newColor);
            }
        });
        colorPickerDialog.show();
    }

    /**
     * 设置颜色
     * @param component
     * @param newColor
     */
    public void colorChanged(Component component, int newColor) {

        String hexColor = String.format("#%06X", (0xFFFFFF & newColor));

        switch (component){
            case BAR_COLOR:
                mBarColor = newColor;
                rangebar.setBarColor(newColor);
                final TextView barColorText = (TextView) findViewById(R.id.barColor);
                barColorText.setText("barColor = " + hexColor);
                barColorText.setTextColor(newColor);
                break;

            case CONNECTING_LINE_COLOR:
                mConnectingLineColor = newColor;
                rangebar.setConnectingLineColor(newColor);
                final TextView connectingLineColorText = (TextView) findViewById(R.id.connectingLineColor);
                connectingLineColorText.setText("connectingLineColor = " + hexColor);
                connectingLineColorText.setTextColor(newColor);
                break;

            case THUMB_COLOR_NORMAL:
                mThumbColorNormal = newColor;
                rangebar.setThumbColorNormal(newColor);
                final TextView thumbColorNormalText = (TextView) findViewById(R.id.thumbColorNormal);

                if (newColor == -1) {
                    thumbColorNormalText.setText("thumbColorNormal = N/A");
                    thumbColorNormalText.setTextColor(HOLO_BLUE);
                }
                else {
                    thumbColorNormalText.setText("thumbColorNormal = " + hexColor);
                    thumbColorNormalText.setTextColor(newColor);
                }
                break;

            case THUMB_COLOR_PRESSED:
                mThumbColorPressed = newColor;
                rangebar.setThumbColorPressed(newColor);
                final TextView thumbColorPressedText = (TextView) findViewById(R.id.thumbColorPressed);

                if (newColor == -1) {
                    thumbColorPressedText.setText("thumbColorPressed = N/A");
                    thumbColorPressedText.setTextColor(HOLO_BLUE);
                }
                else {
                    thumbColorPressedText.setText("thumbColorPressed = " + hexColor);
                    thumbColorPressedText.setTextColor(newColor);
                }
        }
    }
}
