package com.levi.iqtest.model.drquestion

import android.graphics.Canvas
import android.graphics.ColorFilter
import android.graphics.Paint
import android.graphics.PixelFormat
import android.graphics.drawable.Drawable
import kotlin.math.pow

class DrawQuestion5(val n:Int) : Drawable() {
    override fun draw(canvas: Canvas) {
        // Get the drawable's bounds
        val width: Int = bounds.width()
        val height: Int = bounds.height()

        val paint = Paint(Paint.ANTI_ALIAS_FLAG)
        paint.setARGB(255, 0, 0, 0)
        paint.setStrokeWidth(3f)

        for(i in 0 until n){
            var startX:Float = (0..width).random().toFloat()
            var startY:Float = (0..height).random().toFloat()
            var stopX:Float = (0..width).random().toFloat()
            var stopY:Float = (0..height).random().toFloat()
            while(Math.sqrt(((startX - stopX).toDouble()).pow(2) + ((startY - stopY).toDouble()).pow(2)) < width/4){
                    startX = (0..width).random().toFloat()
                    startY = (0..height).random().toFloat()
                    stopX = (0..width).random().toFloat()
                    stopY = (0..height).random().toFloat()
            }
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