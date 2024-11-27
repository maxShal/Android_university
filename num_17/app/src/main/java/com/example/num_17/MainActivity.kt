package com.example.num_17

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.view.View

class Draw2D(context: Context?) : View(context) {
    private val paint: Paint = Paint()
    private val rect: Rect = Rect()
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        paint.apply {
            style = Paint.Style.FILL
            color = Color.WHITE
        }
        canvas.drawPaint(paint)

        paint.apply {
            isAntiAlias = true
            color = Color.YELLOW
        }
        canvas.drawCircle(width - 30F, 30F, 150F, paint)

        paint.color = Color.GREEN
        canvas.drawRect(0F, height - 200F, width.toFloat(), height.toFloat(), paint)

        paint.apply {
            color = Color.BLUE
            style = Paint.Style.FILL
            isAntiAlias = true
            textSize = 32F
        }
        canvas.drawText("Лужайка только для котов", 30F, height - 205F, paint)

        val x = width - 260F
        val y = 280F

        paint.apply {
            color = Color.GRAY
            style = Paint.Style.FILL
            textSize = 27F
        }

        val str2rotate = "Лучик солнца!"

        canvas.save()
        canvas.rotate(-45F, x + rect.exactCenterX(), y + rect.exactCenterY())
        canvas.drawText(str2rotate, x, y, paint)

        canvas.restore()


    }
}

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val draw2D = Draw2D(this)
        setContentView(draw2D)
    }
}