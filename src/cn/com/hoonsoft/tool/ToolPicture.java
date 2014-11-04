package cn.com.hoonsoft.tool;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Random;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.NinePatchDrawable;
import android.media.ExifInterface;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

/**
 * 图片工具类
 * @author 曾繁添
 * @version 1.0
 */
public class ToolPicture {
	
	/**
	 * 根据指定内容生成自定义宽高的二维码图片 
	 * @param content 需要生成二维码的内容
	 * @param width 二维码宽度
	 * @param height 二维码高度
	 * @throws WriterException 生成二维码异常
	 */
	public static Bitmap makeQRImage(String content, int width, int height)
			throws WriterException {
		// 判断URL合法性
		if (ToolString.isNoBlankAndNoNull(content))
			return null;

		Hashtable<EncodeHintType, String> hints = new Hashtable<EncodeHintType, String>();
		hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
		// 图像数据转换，使用了矩阵转换
		BitMatrix bitMatrix = new QRCodeWriter().encode(content,
				BarcodeFormat.QR_CODE, width, height, hints);
		int[] pixels = new int[width * height];
		// 按照二维码的算法，逐个生成二维码的图片，两个for循环是图片横列扫描的结果
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				if (bitMatrix.get(x, y))
					pixels[y * width + x] = 0xff000000;
				else
					pixels[y * width + x] = 0xffffffff;
			}
		}
		// 生成二维码图片的格式，使用ARGB_8888
		Bitmap bitmap = Bitmap.createBitmap(width, height,
				Bitmap.Config.ARGB_8888);
		bitmap.setPixels(pixels, 0, width, 0, 0, width, height);
		
		return bitmap;
	}
	
	/**
	 * 读取图片属性：旋转的角度
	 * 
	 * @param path 图片绝对路径
	 * @return degree 旋转的角度
	 * @throws IOException
	 */
	public static int gainPictureDegree(String path) throws Exception {
		int degree = 0;
		try {
			ExifInterface exifInterface = new ExifInterface(path);
			int orientation = exifInterface.getAttributeInt(ExifInterface.TAG_ORIENTATION,ExifInterface.ORIENTATION_NORMAL);
			switch (orientation) {
				case ExifInterface.ORIENTATION_ROTATE_90:
					degree = 90;
					break;
				case ExifInterface.ORIENTATION_ROTATE_180:
					degree = 180;
					break;
				case ExifInterface.ORIENTATION_ROTATE_270:
					degree = 270;
					break;
			}
		} catch (Exception e) {
			throw(e);
		}
		
		return degree;
	}
	
    /**
     * 旋转图片 
     * @param angle 角度
     * @param bitmap 源bitmap
     * @return Bitmap 旋转角度之后的bitmap
     */  
    public static Bitmap rotaingBitmap(int angle,Bitmap bitmap) {  
        //旋转图片 动作   
        Matrix matrix = new Matrix();;  
        matrix.postRotate(angle);  
        //重新构建Bitmap
        Bitmap resizedBitmap = Bitmap.createBitmap(bitmap,0,0,bitmap.getWidth(),bitmap.getHeight(),matrix, true);  
        return resizedBitmap;  
    }
    
    /**
     * Drawable转成Bitmap 
     * @param drawable
     * @return
     */
    public static Bitmap drawableToBitmap(Drawable drawable) {
        if (drawable instanceof BitmapDrawable) {
            return ((BitmapDrawable) drawable).getBitmap();
        } else if (drawable instanceof NinePatchDrawable) {
            Bitmap bitmap = Bitmap
                    .createBitmap(
                            drawable.getIntrinsicWidth(),
                            drawable.getIntrinsicHeight(),
                            drawable.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888
                                    : Bitmap.Config.RGB_565);
            Canvas canvas = new Canvas(bitmap);
            drawable.setBounds(0, 0, drawable.getIntrinsicWidth(),
                    drawable.getIntrinsicHeight());
            drawable.draw(canvas);
            return bitmap;
        } else {
            return null;
        }
    }
    
    /**
     * 从资源文件中获取图片
     * @param context 上下文
     * @param drawableId 资源文件id
     * @return
     */
    public static Bitmap gainBitmap(Context context,int drawableId){
        Bitmap bmp = BitmapFactory.decodeResource(context.getResources(), drawableId);
        return bmp;
    }
    
    /**
     * 灰白图片（去色）
     * @param bitmap 需要灰度的图片
     * @return 去色之后的图片
     */
    public static Bitmap toBlack(Bitmap bitmap) {
        Bitmap resultBMP = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(),
                Bitmap.Config.RGB_565);
        Canvas c = new Canvas(resultBMP);
        Paint paint = new Paint();
        ColorMatrix cm = new ColorMatrix();
        cm.setSaturation(0);
        ColorMatrixColorFilter f = new ColorMatrixColorFilter(cm);
        paint.setColorFilter(f);
        c.drawBitmap(bitmap, 0, 0, paint);
        return resultBMP;
    }
    
    /**
     * 将bitmap转成 byte数组
     * 
     * @param bitmap
     * @return
     */
    public static byte[] toBtyeArray(Bitmap bitmap) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
        return baos.toByteArray();
    }
    
    /**
     * 将byte数组转成 bitmap
     * 
     * @param b
     * @return
     */
    public static Bitmap bytesToBimap(byte[] b) {
        if (b.length != 0) {
            return BitmapFactory.decodeByteArray(b, 0, b.length);
        } else {
            return null;
        }
    }
    
    /**
     * 将Bitmap转换成指定大小
     * 
     * @param bitmap 需要改变大小的图片
     * @param width 宽
     * @param height 高
     * @return
     */
    public static Bitmap createBitmapBySize(Bitmap bitmap, int width, int height) {
        return Bitmap.createScaledBitmap(bitmap, width, height, true);
    }
    
    
    /**
     * 在图片右下角添加水印
     * @param srcBMP 原图
     * @param markBMP 水印图片
     * @return 合成水印后的图片
     */
    public static Bitmap composeWatermark(Bitmap srcBMP, Bitmap markBMP) {
        if (srcBMP == null) {
            return null;
        }
        
        // 创建一个新的和SRC长度宽度一样的位图
        Bitmap newb = Bitmap.createBitmap(srcBMP.getWidth(), srcBMP.getHeight(), Config.ARGB_8888);
        Canvas cv = new Canvas(newb);
        // 在 0，0坐标开始画入原图
        cv.drawBitmap(srcBMP, 0, 0, null);
        // 在原图的右下角画入水印
        cv.drawBitmap(markBMP, srcBMP.getWidth() - markBMP.getWidth() + 5, srcBMP.getHeight() - markBMP.getHeight() + 5, null);
        // 保存
        cv.save(Canvas.ALL_SAVE_FLAG);
        // 存储
        cv.restore();
        
        return newb;
    }
    
    /**
     * 将图片转成指定弧度（角度）的图片
     * 
     * @param bitmap 需要修改的图片
     * @param pixels 圆角的弧度
     * @return 圆角图片
     */
    public static Bitmap toRoundCorner(Bitmap bitmap, int pixels) {
        Bitmap output = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Config.ARGB_8888);
        //根据图片创建画布
        Canvas canvas = new Canvas(output);
        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        final RectF rectF = new RectF(rect);
        final float roundPx = pixels;
        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(0xff424242);
        canvas.drawRoundRect(rectF, roundPx, roundPx, paint);
        paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);
        return output;
    }
    
	/**
	 * 缩放图片
	 * 
	 * @param bmp 需要缩放的图片源
	 * @param newW 需要缩放成的图片宽度
	 * @param newH 需要缩放成的图片高度
	 * @return 缩放后的图片
	 */
	public static Bitmap zoom(Bitmap bmp, int newW, int newH) {
		
		// 获得图片的宽高
		int width = bmp.getWidth();
		int height = bmp.getHeight();
		
		// 计算缩放比例
		float scaleWidth = ((float) newW) / width;
		float scaleHeight = ((float) newH) / height;
		
		// 取得想要缩放的matrix参数
		Matrix matrix = new Matrix();
		matrix.postScale(scaleWidth, scaleHeight);
		
		// 得到新的图片
		Bitmap newbm = Bitmap.createBitmap(bmp, 0, 0, width, height, matrix,true);
		
		return newbm;
	}
    
	/**
	 * 获取验证码图片
	 * @param width 验证码宽度
	 * @param height 验证码高度
	 * @return 验证码Bitmap对象
	 */
	public synchronized static Bitmap makeValidateCode(int width, int height){
		return ValidateCodeGenerator.createBitmap(width, height);
	}
	
	/**
	 * 获取验证码值
	 * @return 验证码字符串
	 */
	public synchronized static String gainValidateCodeValue(){
		return ValidateCodeGenerator.getCode();
	}
    
    /**
     * 随机生成验证码内部类
     *
     */
    final static class ValidateCodeGenerator{
    	private static final char[] CHARS = {
    		'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
    		'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 
    		'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
    		'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 
    		'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'
    	};
    	
    	//default settings
    	private static final int DEFAULT_CODE_LENGTH = 4;
    	private static final int DEFAULT_FONT_SIZE = 20;
    	private static final int DEFAULT_LINE_NUMBER = 3;
    	private static final int BASE_PADDING_LEFT = 5, RANGE_PADDING_LEFT = 10, BASE_PADDING_TOP = 15, RANGE_PADDING_TOP = 10;
    	private static final int DEFAULT_WIDTH = 60, DEFAULT_HEIGHT = 30;
    	
    	//variables
    	private static String value;
    	private static int padding_left, padding_top;
    	private static Random random = new Random();
    	
    	public static Bitmap createBitmap(int width,int height) {
    		padding_left = 0;
    		//创建画布
    		Bitmap bp = Bitmap.createBitmap(width, height, Config.ARGB_8888); 
    		Canvas c = new Canvas(bp);

    		//随机生成验证码字符
    		StringBuilder buffer = new StringBuilder();
    		for (int i = 0; i < DEFAULT_CODE_LENGTH; i++) {
    			buffer.append(CHARS[random.nextInt(CHARS.length)]);
    		}
    		value = buffer.toString();
    		
    		//设置颜色
    		c.drawColor(Color.WHITE);
    		
    		//设置画笔大小
    		Paint paint = new Paint();
    		paint.setTextSize(DEFAULT_FONT_SIZE);
    		for (int i = 0; i < value.length(); i++) {
    			//随机样式
    			randomTextStyle(paint);
        		padding_left += BASE_PADDING_LEFT + random.nextInt(RANGE_PADDING_LEFT);
        		padding_top = BASE_PADDING_TOP + random.nextInt(RANGE_PADDING_TOP);
    			c.drawText(value.charAt(i) + "", padding_left, padding_top, paint);
    		}
    		for (int i = 0; i < DEFAULT_LINE_NUMBER; i++) {
    			drawLine(c, paint);
    		}
    		//保存  
    		c.save(Canvas.ALL_SAVE_FLAG);
    		c.restore();
    		
    		return bp;
    	}
    	
    	public static String getCode() {
    		return value;
    	}
    	
    	private static void randomTextStyle(Paint paint) {
    		int color = randomColor(1);
    		paint.setColor(color);
    		paint.setFakeBoldText(random.nextBoolean());//true为粗体，false为非粗体
    		float skewX = random.nextInt(11) / 10;
    		skewX = random.nextBoolean() ? skewX : -skewX;
    		paint.setTextSkewX(skewX); //float类型参数，负数表示右斜，整数左斜
    		paint.setUnderlineText(true); //true为下划线，false为非下划线
    		paint.setStrikeThruText(true); //true为删除线，false为非删除线
    	}
    	
    	private static void drawLine(Canvas canvas, Paint paint) {
    		int color = randomColor(1);
    		int startX = random.nextInt(DEFAULT_WIDTH);
    		int startY = random.nextInt(DEFAULT_HEIGHT);
    		int stopX = random.nextInt(DEFAULT_WIDTH);
    		int stopY = random.nextInt(DEFAULT_HEIGHT);
    		paint.setStrokeWidth(1);
    		paint.setColor(color);
    		canvas.drawLine(startX, startY, stopX, stopY, paint);
    	}
    	
    	private static int randomColor(int rate) {
    		int red = random.nextInt(256) / rate;
    		int green = random.nextInt(256) / rate;
    		int blue = random.nextInt(256) / rate;
    		return Color.rgb(red, green, blue);
    	}
    }
}
