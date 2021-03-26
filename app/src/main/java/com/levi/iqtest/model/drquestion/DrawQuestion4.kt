package com.levi.iqtest.model.drquestion

import android.graphics.Canvas
import android.graphics.ColorFilter
import android.graphics.Paint
import android.graphics.PixelFormat
import android.graphics.drawable.Drawable

class DrawQuestion4 (val a:IntArray, val n:Int, val question:Int) : Drawable() {
    private val paintStroke = Paint(Paint.ANTI_ALIAS_FLAG)
    private val paintText = Paint(Paint.ANTI_ALIAS_FLAG)
    init{
        paintStroke.setARGB(255, 0, 0, 0)
        paintStroke.setStyle(Paint.Style.STROKE)
        paintStroke.setStrokeWidth(3f)

        paintText.setTextAlign(Paint.Align.CENTER)
    }

    override fun draw(canvas: Canvas) {
        val SIZE: Float = bounds.width().toFloat() / n
        val offsetLeft = if(bounds.width()<bounds.height()) 0 else (bounds.width()-bounds.height())/2
        paintText.setTextSize(SIZE/3)

        for (i in 0 until n) {
            canvas.drawRect(i * SIZE, 0f, (i + 1) * SIZE, SIZE, paintStroke)
            if(i==question)
                canvas.drawText("?", (i * SIZE + SIZE/2).toFloat(), (2*SIZE/3).toFloat(), paintText)
            else
                canvas.drawText(Integer.toString(a[i]), (i * SIZE + SIZE/2).toFloat(), (2*SIZE/3).toFloat(), paintText)
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