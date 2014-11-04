package cn.com.hoonsoft.example.message;

import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;

/**
 * 
 * 推送通知时，自定义打开行为的演示activity。 请在控制台通知推送界面中，高级设置->通知后续行为->自定义打开行为，选中并且填写内容：
 * intent:#Intent;component=com.baidu.push.example/com.baidu.push.example.
 * CustomActivity;end 确认推送后，手机将会收到通知，点击通知后，将打开CustomActivity
 * 
 * 用服务端rest api或者sdk推送的朋友，可以通过设置推送方法的open_type和pkg_content参数来实现。具体见api文档
 * 参考：http:
 * //developer.baidu.com/wiki/index.php?title=docs/cplat/push/faq#.E4.B8.
 * BA.E4.BD.95.E9.80.9A.E8.BF.87Server_SDK.E6
 * .8E.A8.E9.80.81.E6.88.90.E5.8A.9F.EF.BC.8CAndroid.E7.AB.AF.E5.8D.B4.E6.94.B6.E4.B8.8D.E5.88.B0.E9.80.9A.E7.9F.A5.EF.BC.9
 * F
 */
public class CustomActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Resources resource = this.getResources();
        String pkgName = this.getPackageName();

        setContentView(resource.getIdentifier("demo_baidu_pulsh_custom_activity", "layout",
                pkgName));
    }
}
