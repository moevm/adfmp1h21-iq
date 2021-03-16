package com.levi.iqtest.model.drquestion

import android.graphics.Canvas
import android.graphics.ColorFilter
import android.graphics.Paint
import android.graphics.PixelFormat
import android.graphics.drawable.Drawable

class DrawQuestion2(val a:Array<IntArray>, val n:Int) : Drawable() {
    private val paintStroke = Paint(Paint.ANTI_ALIAS_FLAG)
    private val paintText = Paint(Paint.ANTI_ALIAS_FLAG)
    init{
        paintStroke.setARGB(255, 0, 0, 0)
        paintStroke.setStyle(Paint.Style.STROKE)
        paintStroke.setStrokeWidth(3f)

        paintText.setTextAlign(Paint.Align.CENTER)
        paintText.setTextSize(50f)
    }

    override fun draw(canvas: Canvas) {
        val SIZE: Float = bounds.width().toFloat() / n

        for (i in 0 until n) {
            for (j in 0 until n) {
                canvas.drawRect(i * SIZE, j * SIZE, (i + 1) * SIZE, (j + 1) * SIZE, paintStroke)
                if(a[i][j] == 0)
                    canvas.drawText("?", (i * SIZE + SIZE / 2).toFloat(), (j * SIZE + SIZE / 2).toFloat(), paintText)
                else
                    canvas.drawText(Integer.toString(a[i][j]), (i * SIZE + SIZE / 2).toFloat(), (j * SIZE + SIZE / 2).toFloat(), paintText)
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