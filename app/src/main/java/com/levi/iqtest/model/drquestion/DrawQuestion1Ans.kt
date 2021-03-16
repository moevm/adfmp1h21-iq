package com.levi.iqtest.model.drquestion

import android.graphics.Canvas
import android.graphics.ColorFilter
import android.graphics.Paint
import android.graphics.PixelFormat
import android.graphics.drawable.Drawable

class DrawQuestion1Ans(val color1: Paint, val color2: Paint) : Drawable()  {
    private val paintStroke = Paint(Paint.ANTI_ALIAS_FLAG)
    init{
        paintStroke.setARGB(255, 0, 0, 0)
        paintStroke.setStyle(Paint.Style.STROKE)
        paintStroke.setStrokeWidth(3f)
    }
    override fun draw(canvas: Canvas) {

        val SIZE: Float = bounds.width().toFloat() / 3f
        val radius: Float = (SIZE-20) / 2f

        canvas.drawCircle(SIZE/2.toFloat(), SIZE/2.toFloat(), radius, color1)
        canvas.drawCircle(SIZE/2.toFloat(), SIZE/2.toFloat(), radius, paintStroke)
        canvas.drawCircle(SIZE/2.toFloat(), SIZE/2.toFloat(), radius / 2, color2)
        canvas.drawCircle(SIZE/2.toFloat(), SIZE/2.toFloat(), radius / 2, paintStroke)
    }

    override fun setAlpha(alpha: Int) {
        TODO("Not yet implemented")
    }

    override fun setColorFilter(colorFilter: ColorFilter?) {
        TODO("Not yet implemented")
    }

    override fun getOpacity(): Int =
        // Must be PixelFormat.UNKNOWN, TRANSLUCENT, TRANSPARENT, or OPAQUE
        PixelFormat.OPAQUE
}