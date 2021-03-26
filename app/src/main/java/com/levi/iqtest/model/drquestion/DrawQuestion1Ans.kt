package com.levi.iqtest.model.drquestion

import android.graphics.Canvas
import android.graphics.ColorFilter
import android.graphics.Paint
import android.graphics.PixelFormat
import android.graphics.drawable.Drawable
import android.util.Log

class DrawQuestion1Ans(val color1: Paint, val color2: Paint) : Drawable()  {
    private val paintStroke = Paint(Paint.ANTI_ALIAS_FLAG)
    init{
        paintStroke.setARGB(255, 0, 0, 0)
        paintStroke.setStyle(Paint.Style.STROKE)
        paintStroke.setStrokeWidth(3f)
    }
    override fun toString(): String {
        return "DrawQuestion1Ans(color1=$color1, color2=$color2, paintStroke=$paintStroke, ${bounds.width()}, ${bounds.height()})"
    }

    override fun draw(canvas: Canvas) {

        val SIZE: Float = Math.min(bounds.width(), bounds.height()).toFloat()
//        Log.i("Debug", toString())
        val radius: Float = (SIZE-SIZE/6) / 2f

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