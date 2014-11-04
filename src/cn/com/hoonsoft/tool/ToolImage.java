package cn.com.hoonsoft.tool;

import android.content.Context;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

public abstract class ToolImage {

	private static ImageLoader imageLoader;
	private static DisplayImageOptions displayImageOptions;
	private static DisplayImageOptions displayImageOptions1;
	private static DisplayImageOptions displayImageOptions2;
	private static DisplayImageOptions displayImageOptions3;
	private static DisplayImageOptions displayImageOptions4;
	private static DisplayImageOptions displayImageOptions5;

	public static void init(Context context, int stubImage,
			int imageForEmptyUri, int imageOnFail) {
		ImageLoaderConfiguration imageLoaderConfiguration = new ImageLoaderConfiguration.Builder(
				context)
		// 如果图片尺寸大于了这个参数，那么就会这按照这个参数对图片大小进行限制并缓存
		// .memoryCacheExtraOptions(480, 800) // default=device screen
		// dimensions
		// .discCacheExtraOptions(480, 800, CompressFormat.JPEG, 75)
		// .taskExecutor(AsyncTask.THREAD_POOL_EXECUTOR)
		// .taskExecutorForCachedImages(AsyncTask.THREAD_POOL_EXECUTOR)
		// .threadPoolSize(3) // default
		// .threadPriority(Thread.NORM_PRIORITY - 1) // default
		// .tasksProcessingOrder(QueueProcessingType.FIFO) // default
		// .denyCacheImageMultipleSizesInMemory()
		// .memoryCache(new LruMemoryCache(2 * 1024 * 1024))
		// .memoryCacheSize(2 * 1024 * 1024)
		// .discCache(
		// new
		// LimitedAgeDiscCache(Environment.getExternalStorageDirectory(),
		// 5 * 60))
		// .discCache(new
		// UnlimitedDiscCache(Environment.getExternalStorageDirectory()))
		// .discCacheSize(50 * 1024 * 1024)
		// .discCacheFileCount(50)
		// .discCacheFileNameGenerator( new HashCodeFileNameGenerator())
		// // default
		// .imageDownloader(new BaseImageDownloader(context)) // default
		// .imageDecoder(new BaseImageDecoder()) // default
		// .defaultDisplayImageOptions(DisplayImageOptions.createSimple())
		// // default
				.enableLogging().build();
		ImageLoader.getInstance().init(imageLoaderConfiguration);
		imageLoader = ImageLoader.getInstance();

		displayImageOptions = new DisplayImageOptions.Builder()
		// 设置图片在下载期间显示的图片
				.showStubImage(stubImage) // 在显示真正的图片前，会加载这个资源
				// 设置图片Uri为空或是错误的时候显示的图片
				.showImageForEmptyUri(imageForEmptyUri) // 空的Url时
				// 设置图片加载/解码过程中错误时候显示的图片
				.showImageOnFail(imageOnFail)
				// .resetViewBeforeLoading() //
				// .delayBeforeLoading(1000) // 延长1000ms 加载图片 （想不出来用在什么场景下）
				// 设置下载的图片是否缓存在内存中
				.cacheInMemory()
				// 设置下载的图片是否缓存在SD卡中
				// .cacheOnDisc()
				// .preProcessor(...)
				// .postProcessor(...)
				// .extraForDownloader(...) //可以向加载器携带一些参数
				// .imageScaleType(ImageScaleType.IN_SAMPLE_POWER_OF_2) //
				// default
				// .bitmapConfig(Bitmap.Config.ARGB_8888) // default
				// .decodingOptions(...)
				// .displayer(new SimpleBitmapDisplayer()) // default
				// .handler(new Handler()) // default
				.build();

		// //设置图片在下载前是否重置，复位
		// resetViewBeforeLoading()
		// //设置图片的解码类型
		// bitmapConfig(Bitmap.Config.RGB_565)
		// //设置图片的解码配置
		// decodingOptions(android.graphics.BitmapFactory.Options
		// decodingOptions)
		// //设置图片下载前的延迟
		// delayBeforeLoading(int delayInMillis)
		// //设置额外的内容给ImageDownloader
		// extraForDownloader(Object extra)
		// //设置图片加入缓存前，对bitmap进行设置
		// preProcessor(BitmapProcessor preProcessor)
		// //设置显示前的图片，显示后这个图片一直保留在缓存中
		// postProcessor(BitmapProcessor postProcessor)
		// //设置图片以如何的编码方式显示
		// imageScaleType(ImageScaleType imageScaleType)
	}

	public static void settingInit1(int stubImage, int imageForEmptyUri,
			int imageOnFail) {
		displayImageOptions1 = new DisplayImageOptions.Builder()
				.showStubImage(stubImage)
				.showImageForEmptyUri(imageForEmptyUri)
				.showImageOnFail(imageOnFail).cacheInMemory().build();

	}
	
	public static void settingInit2(int stubImage, int imageForEmptyUri,
			int imageOnFail) {
		displayImageOptions2 = new DisplayImageOptions.Builder()
				.showStubImage(stubImage)
				.showImageForEmptyUri(imageForEmptyUri)
				.showImageOnFail(imageOnFail).cacheInMemory().build();

	}
	
	public static void settingInit3(int stubImage, int imageForEmptyUri,
			int imageOnFail) {
		displayImageOptions3 = new DisplayImageOptions.Builder()
				.showStubImage(stubImage)
				.showImageForEmptyUri(imageForEmptyUri)
				.showImageOnFail(imageOnFail).cacheInMemory().build();

	}
	
	public static void settingInit4(int stubImage, int imageForEmptyUri,
			int imageOnFail) {
		displayImageOptions4 = new DisplayImageOptions.Builder()
				.showStubImage(stubImage)
				.showImageForEmptyUri(imageForEmptyUri)
				.showImageOnFail(imageOnFail).cacheInMemory().build();

	}
	
	public static void settingInit5(int stubImage, int imageForEmptyUri,
			int imageOnFail) {
		displayImageOptions5 = new DisplayImageOptions.Builder()
				.showStubImage(stubImage)
				.showImageForEmptyUri(imageForEmptyUri)
				.showImageOnFail(imageOnFail).cacheInMemory().build();

	}

	public static void displayImage(String url, ImageView imageView) {
		imageLoader.displayImage(url, imageView, displayImageOptions);
	}
	public static void displayImage1(String url, ImageView imageView) {
		imageLoader.displayImage(url, imageView, displayImageOptions1);
	}
	public static void displayImage2(String url, ImageView imageView) {
		imageLoader.displayImage(url, imageView, displayImageOptions2);
	}
	public static void displayImage3(String url, ImageView imageView) {
		imageLoader.displayImage(url, imageView, displayImageOptions3);
	}
	public static void displayImage4(String url, ImageView imageView) {
		imageLoader.displayImage(url, imageView, displayImageOptions4);
	}
	public static void displayImage5(String url, ImageView imageView) {
		imageLoader.displayImage(url, imageView, displayImageOptions5);
	}

	public static void clearCache() {
		imageLoader.clearMemoryCache();
	}

}
