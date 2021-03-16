package com.levi.iqtest.model.drquestion

import android.graphics.Canvas
import android.graphics.ColorFilter
import android.graphics.Paint
import android.graphics.PixelFormat
import android.graphics.drawable.Drawable

class DrawQuestion5(val n:Int) : Drawable() {
    override fun draw(canvas: Canvas) {
        // Get the drawable's bounds
        val width: Int = bounds.width()
        val height: Int = bounds.height()

        val paint = Paint(Paint.ANTI_ALIAS_FLAG)
        paint.setARGB(255, 0, 0, 0)
        paint.setStrokeWidth(3f)

        for(i in 0 until n){
            val startX:Float = (0..width).random().toFloat()
            val startY:Float = (0..height).random().toFloat()
            val stopX:Float = (0..width).random().toFloat()
            val stopY:Float = (0..height).random().toFloat()
            canvas.drawLine(startX, startY, stopX, stopY, paint)
        }
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