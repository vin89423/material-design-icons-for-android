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
import android.view.WindowManager;

public class MaterialDrawable extends Drawable {

	protected Context context = null;
    protected TextPaint paint = null;
    
    protected Boolean isMenuIcon = true;

    protected int theme = -1;
    protected int alpha = 255;
    protected int color = Color.BLACK;
	
	protected String iconUnicode = "";
	
    public MaterialDrawable(Context context, String iconUnicode) {
        this.context = context;
        this.iconUnicode = iconUnicode;
    }

	public MaterialDrawable(Context context, String iconUnicode, int color) {
        this.context = context;
        this.iconUnicode = iconUnicode;
        this.color = color;
        this.theme = 255;
	}

	public MaterialDrawable(Context context, String iconUnicode, int theme, int color, int alpha) {
        this.context = context;
        this.iconUnicode = iconUnicode;
        this.theme = theme;
        this.color = color;
        this.alpha = alpha;
	}

    @Override
    public void draw(Canvas canvas) {
        Rect textBounds = new Rect();
        paint = new TextPaint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setAntiAlias(true);
        try {
			paint.setTypeface(MaterialIcon.getTypeface(context));
		} catch (Exception e) {
			e.printStackTrace();
		}
        if (theme < 0) {
        	getThemeIconColor();
        }
        paint.setColor(color);
        paint.setAlpha(alpha);
        paint.getTextBounds(iconUnicode, 0, 1, textBounds);
        int xPos = 0, yPos = 0;
        if (isMenuIcon) {
        	DisplayMetrics dm = new DisplayMetrics();
        	((WindowManager) context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay().getMetrics(dm);
        	int size = (int) ( 32 * dm.density);
        	paint.setTextSize( size );
        	setBounds(0, 0, size, size); 
            yPos = (int) (14.67f * context.getResources().getDisplayMetrics().density + 0.5f);
        } else {
        	paint.setTextSize( getBounds().height() );
            xPos = (canvas.getWidth() / 2);
            yPos = (int) ((canvas.getHeight() / 2) - ((paint.descent() + paint.ascent()) / 2));
        }
        canvas.drawText(iconUnicode, xPos, yPos, paint);
    }
    
    public MaterialDrawable isMenuItem( Boolean status ) {
    	isMenuIcon = status;
    	return this;
    }
    
    public void getThemeIconColor() {
    	int color = 0xFFFFFF;
		Resources.Theme theme = context.getTheme();
		if (theme != null) {
			TypedArray backgroundAttributes = theme.obtainStyledAttributes(new int[]{android.R.attr.colorBackground});
			if (backgroundAttributes != null) {
				color = backgroundAttributes.getColor(0, 0xFFFFFF);
				backgroundAttributes.recycle();
			}
		}
		// Convert to greyscale and check if < 128
		if (color != 0xFFFFFF && 0.21 * Color.red(color) + 0.72 * Color.green(color) + 0.07 * Color.blue(color) < 128) {
			this.color = Color.parseColor("#FFFFFF");			
			this.alpha = (int) (255 * 0.8);
		} else {
			this.color = Color.parseColor("#333333");		
			this.alpha = (int) (255 * 0.6);
		}
    }

	public int getColor() {
		return color;
	}

	@Override
	public int getOpacity() {
		return alpha;
	}

    @Override
    public void setAlpha(int alpha) {
        this.alpha = alpha;
        paint.setAlpha(alpha);
    }

    public MaterialDrawable setColor(int color) {
		this.color = color;
		return this;
	}

	@Override
	public void setColorFilter(ColorFilter cf) {
		paint.setColorFilter(cf);		
	}
}
