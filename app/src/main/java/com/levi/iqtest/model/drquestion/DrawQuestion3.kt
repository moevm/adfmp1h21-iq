package com.levi.iqtest.model.drquestion

import android.graphics.Canvas
import android.graphics.ColorFilter
import android.graphics.Paint
import android.graphics.PixelFormat
import android.graphics.drawable.Drawable

class DrawQuestion3(val a:Array<IntArray>, val n:Int) : Drawable() {
    private val paintStroke = Paint(Paint.ANTI_ALIAS_FLAG)
    private val paintText = Paint(Paint.ANTI_ALIAS_FLAG)
    init{
        paintStroke.setARGB(255, 0, 0, 0)
        paintStroke.setStyle(Paint.Style.STROKE)
        paintStroke.setStrokeWidth(3f)

        paintText.setTextAlign(Paint.Align.CENTER)
    }

    override fun draw(canvas: Canvas) {
        val SIZE: Float = Math.min(bounds.width(), bounds.height()).toFloat() / n
        val offsetLeft = if(bounds.width()<bounds.height()) 0 else (bounds.width()-bounds.height())/2
        paintText.setTextSize(SIZE/3)

        for (i in 0 until n) {
            for (j in 0 until n) {
                canvas.drawRect(i * SIZE+offsetLeft, j * SIZE, (i + 1) * SIZE+offsetLeft, (j + 1) * SIZE, paintStroke)
                if(a[i][j] == 0)
                    canvas.drawText("?", (i * SIZE + SIZE/2).toFloat()+offsetLeft, (j * SIZE + 2*SIZE/3).toFloat(), paintText)
                else
                    canvas.drawText(Integer.toString(a[i][j]), (i * SIZE + SIZE/2-10).toFloat()+offsetLeft, (j * SIZE + 2*SIZE/3).toFloat(), paintText)
            }
        }
    }


    override fun setAlpha(alpha: Int) {
        // This method is required
    }

    override fun setColorFilter(colorFilter: ColorFilter?) {
        // This method is required
    }

    override fun getOpacity(): Int =
        // Must be PixelFormat.UNKNOWN, TRANSLUCENT, TRANSPARENT, or OPAQUE
        PixelFormat.OPAQUE
}