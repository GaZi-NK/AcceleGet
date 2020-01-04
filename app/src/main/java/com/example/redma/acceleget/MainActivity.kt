package com.example.redma.acceleget

import android.content.Context
import android.graphics.Color
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(),SensorEventListener {

    //センサーの制度が変更されると呼び出される
    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
    }

    //センサーの値が変更されるたびに呼び出される
    override fun onSensorChanged(event: SensorEvent?) {
        if(event == null) return
        //受け取ったセンサーのタイプが加速度センサーであれば数値をtxt01に表示する
        if(event.sensor.type == Sensor.TYPE_ACCELEROMETER) {
            //[0]がX軸、[1]がY軸、[2]がZ軸
            txt01.text = "X軸:${event.values[0]}\nY軸:${event.values[1]}\nZ軸:${event.values[2]}"
        }
        if(event.values[0] >= 5) {
            txt01.setTextColor(Color.BLUE)
        }else{
            txt01.setTextColor(Color.YELLOW)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onResume() {
        super.onResume()
        //センサーマネージャーのインスタンスを取得⇒戻り値がObject型のためSensorManager型にキャストする
        val sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        //センサータイプを指定してセンサーオブジェクトを取得
        val accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
        //イベントリスナーを登録⇒引数(リスナーオブジェクト、センサーオブジェクト、センサーからデータを取得するレート)
        sensorManager.registerListener(this,accelerometer,SensorManager.SENSOR_DELAY_NORMAL)


    }

    override fun onPause() {
        super.onPause()
        //センサーマネージャーを取得
        val sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        //リスナーの登録を解除
        sensorManager.unregisterListener(this)
    }


}
