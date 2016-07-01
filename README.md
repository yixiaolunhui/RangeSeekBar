# A nice range drag bar
  

##Preview
![image](https://github.com/dalong982242260/RangeSeekBar/blob/master/gif/seekbar.gif?raw=true)  ![image](https://github.com/dalong982242260/RangeSeekBar/blob/master/gif/seekbar2.gif?raw=true)

##Attributes

|name|format|description|
|:---:|:---:|:---:|
| tickCount | integer |刻度的数量
| tickHeight | dimension |刻度的高度
| barWeight | dimension |刻度的宽度
| barColor | reference/color |刻度的颜色
| connectingLineWeight | dimension |连接线的高度
| connectingLineColor | reference/color |连接线的颜色
| thumbRadius | dimension |拖动块的半径
| thumbImageNormal | reference |拖动块的自定义默认图片
| thumbImagePressed | reference |拖动块的自定义按下拖动时图片
| thumbColorNormal | reference/color |拖动块的自定义默认颜色
| thumbColorPressed | reference/color |拖动块的自定义按下拖动时颜色

##use
       <com.dalong.rangeseekbar.RangeBar
                xmlns:custom="http://schemas.android.com/apk/res-auto"
                android:id="@+id/rangebar1"
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:layout_marginBottom="50dp"
                android:layout_marginTop="50dp"
                custom:tickCount="7" />

##gradle

        compile 'com.dalong:rangebar:1.0.0'