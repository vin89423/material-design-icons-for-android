/* 
 * Copyright Vin Wong @ vinexs.com
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.vinexs.mdicon;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.text.TextPaint;
import android.util.DisplayMetrics;
import android.util.Log;

public class MaterialDrawable extends Drawable {

    private final Context context;

    protected TextPaint paint;

    protected int theme = -1;
    protected int color = Color.BLACK;
    protected int size = -1;
    protected int alpha = 255;
    
    protected String iconUnicode = "";

    public MaterialDrawable(Context context, String iconUnicode) {
        this.context = context;
        this.iconUnicode = iconUnicode;
        setProperties();
    }
    
    public MaterialDrawable(Context context, String iconUnicode, int color) {
        this.context = context;
        this.iconUnicode = iconUnicode;
        this.color = color;
        this.theme = 255;
        setProperties();
	}

	public MaterialDrawable(Context context, String iconUnicode, int theme, int color, int alpha) {
        this.context = context;
        this.iconUnicode = iconUnicode;
        this.theme = theme;
        this.color = color;
        this.alpha = alpha;
        setProperties();
	}

    public MaterialDrawable setMenuItemSize() {
    	DisplayMetrics dm = context.getResources().getDisplayMetrics();
    	this.size = (int) ( 24 * dm.density );
    	setBounds(0, 0, size, size);
        invalidateSelf();
        return this;
    }
    
    public void setProperties(){
    	paint = new TextPaint();
    	if (theme < 0) {
        	getThemeIconColor();
        }
    	try {
    		paint.setTypeface(MaterialIcon.getTypeface(context));
    	} catch (Exception e) {
 			e.printStackTrace();
 		}
    	paint.setStyle(Paint.Style.STROKE);
    	paint.setTextAlign(Paint.Align.CENTER);
    	paint.setUnderlineText(false);
    	paint.setColor(color);
    	paint.setAlpha(alpha);
    	paint.setAntiAlias(true);
    }
    
    public void getThemeIconColor() {
    	int color = 0x000000;
    	Resources.Theme theme = context.getTheme();
    	if (theme != null) {
    		TypedArray backgroundAttributes = theme.obtainStyledAttributes(new int[]{android.R.attr.colorBackground});
			if (backgroundAttributes != null) {
				color = backgroundAttributes.getColor(0, 0x000000);
				backgroundAttributes.recycle();
			}
    	}
    	Log.d( "Detect Theme", "Theme Color: "+ String.format("#%06X", (0xFFFFFF & color)) );
    	// Convert color to greyscale, check if < 128
    	if (color != 0xFFFFFF && 0.21 * Color.red(color) + 0.72 * Color.green(color) + 0.07 * Color.blue(color) < 128) {
			this.color = Color.parseColor("#FFFFFF");			
			this.alpha = (int) (255 * 0.8);
		} else {
			this.color = Color.parseColor("#333333");		
			this.alpha = (int) (255 * 0.6);
		}
    }    

    @Override
    public void draw(Canvas canvas) {
        paint.setTextSize(getBounds().height());
        Rect textBounds = new Rect();
        paint.getTextBounds(iconUnicode, 0, 1, textBounds);
        float textBottom = (getBounds().height() - textBounds.height()) / 2f + textBounds.height() - textBounds.bottom;
        canvas.drawText(iconUnicode, getBounds().width() / 2f, textBottom, paint);
    }

    @Override
    public void clearColorFilter() {
        paint.setColorFilter(null);
    }    
    
	/** ActionBar icon display wrong without this. */
	@Override
	public int getIntrinsicHeight() {
	    return size;
	}
	
	/** ActionBar icon display wrong without this. */
	@Override
	public int getIntrinsicWidth() {
	    return size;
	}

    @Override
    public int getOpacity() {
        return this.alpha;
    }

    @Override
    public void setAlpha(int alpha) {
        this.alpha = alpha;
        paint.setAlpha(alpha);
    }

    @Override
    public void setColorFilter(ColorFilter cf) {
        paint.setColorFilter(cf);
    }
    
}
