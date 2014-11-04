
package cn.com.hoonsoft.config;

public class URLConfig {

    // ///////////////////////////////////////服务器基本信息配置(start)/////////////////////////////////////////

	/***获取天气WebService**/
	public static final String WEB_SERVER_URL = "http://www.webxml.com.cn/WebServices/WeatherWebService.asmx";
	
	/***获取天气WebService命名空间**/
	public static final String NAME_SPACE = "http://WebXml.com.cn/";
	
    /** 网络协议 ***/
    public static final String HTTP_PROTOCOL = "http";

    /** 主机地址（IP或域名） ***/
    public static final String HOST_ADDRESS = " 192.168.1.7:8080/WeVideoMatch";

    /** Servlet访问URL ***/
    public static final String SERVLET_BASE_URL = "/cn/com/hoonsoft/servlet/ServletAction";

    /*** 主机过滤Host **/
    public static final String HOST = "http://www.daqianduan.com/";
    
    /**
     * 基础网址
     */
    public static final String BASIC_URL = HTTP_PROTOCOL + ":" + "//" + HOST_ADDRESS  + SERVLET_BASE_URL;

    // ///////////////////////////////////////服务器基本信息配置(end)/////////////////////////////////////////

    // ///////////////////////////////////////请求Handle配置(start)///////////////////////////////////////
    /***
     * 版本更新请求地址
     */
    public static final String CLIENT_LATEST_VERSION_URL = BASIC_URL
            + "cn.com.hoonsoft.yizhao.json.GainClientLatestVersion";

    /**
     * 文件下载Handle_URL
     */
    public static final String FILE_DOWNLOAD_URL = BASIC_URL + "?" + IConstant.HANDLE_CLASS_NAME
            + "="
            + "cn.com.hoonsoft.file.servlethandle.ServletDownloadHandle";

    /*** 获取所有编码Handle **/
    public static final String GAIN_MULTIPLE_CODE_HANDLE = "cn.com.hoonsoft.yizhao.json.GainMultipleCodeList";

    /***
     * 版本更新请求Handle
     */
    public static final String CLIENT_LATEST_VERSION_HANDLE = "cn.com.hoonsoft.yizhao.json.GainClientLatestVersion";

    /**
     * 文件下载Handle_URL
     */
    public static final String FILE_DOWNLOAD_HANDLE = "cn.com.hoonsoft.file.servlethandle.ServletDownloadHandle";

    /**
     * 个人注册 手机号码 phoneNum 验证码（暂时空缺） 密码 password 异常 该手机号码已被注册,请您更换新的手机号码 注册个人用户失败
     */
    public static final String PERSONAL_REGIST = "cn.com.hoonsoft.yizhao.phone.register.servlethandle.PersonalRegisterHandle";
    /**
     * 个人用户修改密码 个人用户OID OID 旧密码 oldPassword 新密码 newPassword 版本号 version
     */
    public static final String PERSONAL_CHANGEPASS = "cn.com.hoonsoft.yizhao.phone.home.personal.infoupdate.servlethandle.ResetPasswordHandle";

    /**
     * 修改密码（商家用户） URL： 参数： 商家用户OID OID 旧密码 oldPassword 新密码 newPassword 商家类型
     * merchantType（express、logistics、move、lorry、rentCar） 版本号 version
     */
    public static final String MERCHANT_CHANGEPASS = "cn.com.hoonsoft.yizhao.phone.home.merchant.infoupdate.servlethandle.ResetPasswordHandle";

    /**
     * 商家注册
     */
    public static final String MERCANT_REGIST = "cn.com.hoonsoft.yizhao.phone.register.servlethandle.MerchantRegisterHandle";
    /**
     * 用户登录 phoneNum password
     */
    public static final String USER_LOGIN = "cn.com.hoonsoft.yizhao.phone.login.servlethandle.UserLoginHandle";
    /**
     * 获取用户协议
     */
    public static final String GAIN_SERVICEAGREEMENT_HANDLE = "cn.com.hoonsoft.yizhao.phone.register.servlethandle.GainServiceAgreementHandle";
    /**
     * 找回密码 phoneNum
     */
    public static final String USER_FINDPASS = "cn.com.hoonsoft.yizhao.phone.retrieve.servlethandle.RetrievePasswordHandle";

    /**
     * 商家上传头像
     */
    public static final String MERCHANT_UPLOADPHOTO = "cn.com.hoonsoft.yizhao.phone.home.merchant.infoupdate.servlethandle.UploadPhotoHandle";
    /**
     * 个人用户上传头像
     */
    public static final String PERSONAL_UPLOADPHOTO = "cn.com.hoonsoft.yizhao.phone.home.personal.infoupdate.servlethandle.UploadPhotoHandle";
    /**
     * 个人用户基本资料修改
     */
    public static final String PERSONAL_MODIFY_BASIC_INFO = "cn.com.hoonsoft.yizhao.phone.home.personal.infoupdate.servlethandle.BasicInfoHandle";
    /**
     * 商家完善基本资料
     */
    public static final String MERCHANT_MODIFY_BASIC_INFO = "cn.com.hoonsoft.yizhao.phone.home.merchant.infoupdate.servlethandle.BasicInfoHandle";
    /**
     * 商家资料获取
     */
    public static final String MERCHANT_GAIN_INFO = "cn.com.hoonsoft.yizhao.phone.home.merchant.infoupdate.servlethandle.GainMerchantInfoHandle";

    /**
     * 个人资料获取
     */
    public static final String PERSONAL_GAIN_INFO = "cn.com.hoonsoft.yizhao.phone.home.personal.infoupdate.servlethandle.GainUserInfoHandle";
    public static final String SAVE_GPS_PERSONAL = "cn.com.hoonsoft.yizhao.phone.home.personal.infoupdate.servlethandle.SaveGPSInfoHandle";
    public static final String SAVE_GPS_MERCHANT = "cn.com.hoonsoft.yizhao.phone.home.merchant.infoupdate.servlethandle.SaveGPSInfoHandle";
    /**
     * 物流资料完善
     */
    public static final String LOGISTICS_INFO_UPDATE = "cn.com.hoonsoft.yizhao.phone.home.merchant.infoupdate.servlethandle.LogisticsInfoHandle";
    /**
     * 搬家资料完善
     */
    public static final String MOVE_INFO_UPDATE = "cn.com.hoonsoft.yizhao.phone.home.merchant.infoupdate.servlethandle.MoveInfoHandle";
    /**
     * 货运资料完善
     */
    public static final String LORRY_INFO_UPDATE = "cn.com.hoonsoft.yizhao.phone.home.merchant.infoupdate.servlethandle.LorryInfoHandle";
    /**
     * 租车完善资料
     */
    public static final String RENTCAR_INFO_UPDATE = "cn.com.hoonsoft.yizhao.phone.home.merchant.infoupdate.servlethandle.RentCarInfoHandle";
    /**
     * 快递完善资料
     */
    public static final String EXPRESS_INFO_UPDATE = "cn.com.hoonsoft.yizhao.phone.home.merchant.infoupdate.servlethandle.ExpressInfoHandle";

    /**
     * 获得绑定车辆列表信息
     */
    public static final String GAINBUNDCARINFO = "cn.com.hoonsoft.yizhao.phone.home.merchant.infoupdate.servlethandle.GainBindInfoHandle";
    public static final String OID = "OID";
    public static final String VERSION = "version";
    public static final int MAXBOUNDCARSIZE = 10;
    /**
     * 绑定一辆车
     */
    public static final String BOUND_A_CAR = "cn.com.hoonsoft.yizhao.phone.home.merchant.infoupdate.servlethandle.BindLorryHandle";
    /**
     * 解除绑定一辆车
     */
    public static final String UN_BOUND_A_CAR = "cn.com.hoonsoft.yizhao.phone.home.merchant.infoupdate.servlethandle.UnbindLorryHandle";
    /**
     * 发布物流需求
     */
    public static final String PUBLISH_LOGISTIC = "cn.com.hoonsoft.yizhao.phone.home.personal.releaseRequirement.servlethandle.LogisticsRequireHandle";
    /**
     * 发布货车需求
     */
    public static final String PUBLISH_LORRY = "cn.com.hoonsoft.yizhao.phone.home.personal.releaseRequirement.servlethandle.LorryRequireHandle";
    /**
     * 发布搬家需求
     */
    public static final String PUBLISH_MOVE = "cn.com.hoonsoft.yizhao.phone.home.personal.releaseRequirement.servlethandle.MoveRequireHandle";
    /**
     * 发布租车需求
     */
    public static final String PUBLISH_RENTCAR = "cn.com.hoonsoft.yizhao.phone.home.personal.releaseRequirement.servlethandle.RentCarRequireHandle";
    /**
     * 得到报价
     */
    public static final String REQUESTQUOTATE = "cn.com.hoonsoft.yizhao.phone.home.personal.requirementManage.servlethandle.GainOfferInfoHandle";
    /**
     * 保存评论
     */
    public static final String SAVECOMMENTS = "cn.com.hoonsoft.yizhao.phone.home.personal.requirementManage.servlethandle.SaveCommentInfoHandle";
    /**
     * 已发布的物流需求
     */
    public static final String PUBLISHEDLOGISTIC = "cn.com.hoonsoft.yizhao.phone.home.personal.requirementManage.servlethandle.ReleasedLogisticsRequireHandle";
    /**
     * 已报价的物流需求
     */
    public static final String REQUESTEDQUOTATIONLOGISTIC = "cn.com.hoonsoft.yizhao.phone.home.personal.requirementManage.servlethandle.OfferedLogisticsRequireHandle";
    /**
     * 已完成的物流需求
     */
    public static final String FINISHEDLOGISTICREQUIRE = "cn.com.hoonsoft.yizhao.phone.home.personal.requirementManage.servlethandle.FinishedLogisticsRequireHandle";
    /**
     * 物流需求详情
     */
    public static final String GAINLOGISTICSREQUIRE = "cn.com.hoonsoft.yizhao.phone.home.personal.requirementManage.servlethandle.GainLogisticsRequireHandle";
    /**
     * 放弃物流需求
     */
    public static final String ABANDONLOGISTICSREQUIRE = "cn.com.hoonsoft.yizhao.phone.home.personal.requirementManage.servlethandle.AbandonLogisticsRequireHandle";
    /**
     * 完成需求
     */
    public static final String FINISHLOGISTICSREQUIRE = "cn.com.hoonsoft.yizhao.phone.home.personal.requirementManage.servlethandle.FinishLogisticsRequireHandle";
    /**
     * 刷新需求
     */
    public static final String REFRESHLOGISTICSREQUIRE = "cn.com.hoonsoft.yizhao.phone.home.personal.requirementManage.servlethandle.RefreshLogisticsRequireHandle";
    /**
     * 删除需求
     */
    public static final String DELETELOGISTICSREQUIRE = "cn.com.hoonsoft.yizhao.phone.home.personal.requirementManage.servlethandle.DeleteLogisticsRequireHandle";
    /**
     * 交易需求
     */
    public static final String TRADELOGISTICS = "cn.com.hoonsoft.yizhao.phone.home.personal.requirementManage.servlethandle.TradeLogisticsHandle";
    /**
     * 得到物流的交易信息
     */
    public static final String GAINLOGISTICSTRADEINFO = "cn.com.hoonsoft.yizhao.phone.home.personal.requirementManage.servlethandle.GainLogisticsTradeInfoHandle";

    /**
     * 已发布货车需求列表
     */
    public static final String RELEASEDLORRYREQUIRE = "cn.com.hoonsoft.yizhao.phone.home.personal.requirementManage.servlethandle.ReleasedLorryRequireHandle";
    /**
     * 已报价货车需求列表
     */
    public static final String OFFEREDLORRYREQUIRE = "cn.com.hoonsoft.yizhao.phone.home.personal.requirementManage.servlethandle.OfferedLorryRequireHandle";
    /**
     * 已完成货车需求列表
     */
    public static final String FINISHEDLORRYREQUIRE = "cn.com.hoonsoft.yizhao.phone.home.personal.requirementManage.servlethandle.FinishedLorryRequireHandle";
    /**
     * 货车需求详情
     */
    public static final String GAINLORRYREQUIRE = "cn.com.hoonsoft.yizhao.phone.home.personal.requirementManage.servlethandle.GainLorryRequireHandle";
    /**
     * 放弃货车需求
     */
    public static final String ABANDONLORRYREQUIRE = "cn.com.hoonsoft.yizhao.phone.home.personal.requirementManage.servlethandle.AbandonLorryRequireHandle";
    /**
     * 完成货车需求
     */
    public static final String FINISHLORRYREQUIRE = "cn.com.hoonsoft.yizhao.phone.home.personal.requirementManage.servlethandle.FinishLorryRequireHandle";
    /**
     * 刷新货车需求
     */
    public static final String REFRESHLORRYREQUIRE = "cn.com.hoonsoft.yizhao.phone.home.personal.requirementManage.servlethandle.RefreshLorryRequireHandle";
    /**
     * 删除货车需求
     */
    public static final String DELETELORRYREQUIRE = "cn.com.hoonsoft.yizhao.phone.home.personal.requirementManage.servlethandle.DeleteLorryRequireHandle";
    /**
     * 交易货车需求
     */
    public static final String TRADELORRY = "cn.com.hoonsoft.yizhao.phone.home.personal.requirementManage.servlethandle.TradeLorryHandle";
    /**
     * 得到货车的交易信息
     */
    public static final String GAINLORRYTRADEINFO = "cn.com.hoonsoft.yizhao.phone.home.personal.requirementManage.servlethandle.GainLorryTradeInfoHandle";

    /**
     * 已发布搬家需求列表
     */
    public static final String RELEASEDMOVEREQUIRE = "cn.com.hoonsoft.yizhao.phone.home.personal.requirementManage.servlethandle.ReleasedMoveRequireHandle";
    /**
     * 已报价搬家需求列表
     */
    public static final String OFFEREDMOVEREQUIRE = "cn.com.hoonsoft.yizhao.phone.home.personal.requirementManage.servlethandle.OfferedMoveRequireHandle";
    /**
     * 已完成搬家需求列表
     */
    public static final String FINISHEDMOVEREQUIRE = "cn.com.hoonsoft.yizhao.phone.home.personal.requirementManage.servlethandle.FinishedMoveRequireHandle";
    /**
     * 搬家需求详情
     */
    public static final String GAINMOVEREQUIRE = "cn.com.hoonsoft.yizhao.phone.home.personal.requirementManage.servlethandle.GainMoveRequireHandle";
    /**
     * 放弃搬家需求
     */
    public static final String ABANDONMOVEREQUIRE = "cn.com.hoonsoft.yizhao.phone.home.personal.requirementManage.servlethandle.AbandonMoveRequireHandle";
    /**
     * 完成搬家需求
     */
    public static final String FINISHMOVEREQUIRE = "cn.com.hoonsoft.yizhao.phone.home.personal.requirementManage.servlethandle.FinishMoveRequireHandle";
    /**
     * 刷新搬家需求
     */
    public static final String REFRESHMOVEREQUIRE = "cn.com.hoonsoft.yizhao.phone.home.personal.requirementManage.servlethandle.RefreshMoveRequireHandle";
    /**
     * 删除搬家需求
     */
    public static final String DELETEMOVEREQUIRE = "cn.com.hoonsoft.yizhao.phone.home.personal.requirementManage.servlethandle.DeleteMoveRequireHandle";
    /**
     * 交易搬家需求
     */
    public static final String TRADEMOVE = "cn.com.hoonsoft.yizhao.phone.home.personal.requirementManage.servlethandle.TradeMoveHandle";
    /**
     * 得到搬家的交易信息
     */
    public static final String GAINMOVETRADEINFO = "cn.com.hoonsoft.yizhao.phone.home.personal.requirementManage.servlethandle.GainMoveTradeInfoHandle";

    /**
     * 已发布租车需求列表
     */
    public static final String RELEASEDRENTCARREQUIRE = "cn.com.hoonsoft.yizhao.phone.home.personal.requirementManage.servlethandle.ReleasedRentCarRequireHandle";
    /**
     * 已报价租车需求列表
     */
    public static final String OFFEREDRENTCARREQUIRE = "cn.com.hoonsoft.yizhao.phone.home.personal.requirementManage.servlethandle.OfferedRentCarRequireHandle";
    /**
     * 已完成租车需求列表
     */
    public static final String FINISHEDRENTCARREQUIRE = "cn.com.hoonsoft.yizhao.phone.home.personal.requirementManage.servlethandle.FinishedRentCarRequireHandle";
    /**
     * 租车需求详情
     */
    public static final String GAINRENTCARREQUIRE = "cn.com.hoonsoft.yizhao.phone.home.personal.requirementManage.servlethandle.GainRentCarRequireHandle";
    /**
     * 放弃租车需求
     */
    public static final String ABANDONRENTCARREQUIRE = "cn.com.hoonsoft.yizhao.phone.home.personal.requirementManage.servlethandle.AbandonRentCarRequireHandle";
    /**
     * 完成租车需求
     */
    public static final String FINISHRENTCARREQUIRE = "cn.com.hoonsoft.yizhao.phone.home.personal.requirementManage.servlethandle.FinishRentCarRequireHandle";
    /**
     * 刷新租车需求
     */
    public static final String REFRESHRENTCARREQUIRE = "cn.com.hoonsoft.yizhao.phone.home.personal.requirementManage.servlethandle.RefreshRentCarRequireHandle";
    /**
     * 删除租车需求
     */
    public static final String DELETERENTCARREQUIRE = "cn.com.hoonsoft.yizhao.phone.home.personal.requirementManage.servlethandle.DeleteRentCarRequireHandle";
    /**
     * 交易租车需求
     */
    public static final String TRADERENTCAR = "cn.com.hoonsoft.yizhao.phone.home.personal.requirementManage.servlethandle.TradeRentCarHandle";
    /**
     * 得到租车的交易信息
     */
    public static final String GAINRENTCARTRADEINFO = "cn.com.hoonsoft.yizhao.phone.home.personal.requirementManage.servlethandle.GainRentCarTradeInfoHandle";
    /**
     * 用户反馈信息
     */
    public static final String SAVEFEEDBACK = "cn.com.hoonsoft.yizhao.phone.feedbackOpinion.servlethandle.SaveFeedBackHandle";
    /**
     * 得到站内消息(个人用户)
     */
    public static final String GAINMESSAGEPERSONAL = "cn.com.hoonsoft.yizhao.phone.home.personal.messageCenter.servlethandle.GainMessageInfoHandle";
    /**
     * 删除站内消息(个人用户)
     */
    public static final String DELETEMESSAGEPERSONAL = "cn.com.hoonsoft.yizhao.phone.home.personal.messageCenter.servlethandle.DeleteMessageHandle";
    /**
     * 得到站内消息(商家用户)
     */
    public static final String GAINMESSAGEMERCHANT = "cn.com.hoonsoft.yizhao.phone.home.merchant.messageCenter.servlethandle.GainMessageInfoHandle";
    /**
     * 删除站内消息(商家用户)
     */
    public static final String DELETEMESSAGEMERCHANT = "cn.com.hoonsoft.yizhao.phone.home.merchant.messageCenter.servlethandle.DeleteMessageHandle";
    /**
     * 得到最后一次报价
     */
    public static final String GAINLASTOFFER = "cn.com.hoonsoft.yizhao.phone.home.merchant.offerManage.servlethandle.GainLastOfferHandle";
    /**
     * 得到评论信息
     */
    public static final String GAINCOMMENTINFO = "cn.com.hoonsoft.yizhao.phone.home.merchant.offerManage.servlethandle.GainCommentInfoHandle";
    /**
     * 保存报价信息
     */
    public static final String SAVEOFFERINFO = "cn.com.hoonsoft.yizhao.phone.home.merchant.offerManage.servlethandle.SaveOfferInfoHandle";
    /**
     * 保存收藏信息
     */
    public static final String SAVECOLLECTINFO = "cn.com.hoonsoft.yizhao.phone.home.merchant.offerManage.servlethandle.SaveCollectInfoHandle";
    /**
     * 取消收藏信息
     */
    public static final String CANCELCOLLECT = "cn.com.hoonsoft.yizhao.phone.home.merchant.offerManage.servlethandle.CancelCollectHandle";

    /**
     * 商家物流已发布需求
     */
    public static final String RELEASEDLOGISTICSREQUIREMERCHANT = "cn.com.hoonsoft.yizhao.phone.home.merchant.offerManage.servlethandle.ReleasedLogisticsRequireHandle";

    /**
     * 商家收藏物流需求列表
     */
    public static final String COLLECTEDLOGISTICSREQUIREMERCHANT = "cn.com.hoonsoft.yizhao.phone.home.merchant.offerManage.servlethandle.CollectedLogisticsRequireHandle";
    /**
     * 商家已报价需求列表
     */
    public static final String OFFEREDLOGISTICSREQUIREMERCHANT = "cn.com.hoonsoft.yizhao.phone.home.merchant.offerManage.servlethandle.OfferedLogisticsRequireHandle";
    /**
     * 已完成需求列表商家物流
     */
    public static final String FINISHEDLOGISTICSREQUIREMERCHANT = "cn.com.hoonsoft.yizhao.phone.home.merchant.offerManage.servlethandle.FinishedLogisticsRequireHandle";
    /**
     * 商家需求详情物流
     */
    public static final String GAINLOGISTICSREQUIREMERCHANT = "cn.com.hoonsoft.yizhao.phone.home.merchant.offerManage.servlethandle.GainLogisticsRequireHandle";

    /**
     * 商家货车已发布需求
     */
    public static final String RELEASEDLORRYREQUIREMERCHANT = "cn.com.hoonsoft.yizhao.phone.home.merchant.offerManage.servlethandle.ReleasedLorryRequireHandle";

    /**
     * 商家收藏货车需求列表
     */
    public static final String COLLECTEDLORRYREQUIREMERCHANT = "cn.com.hoonsoft.yizhao.phone.home.merchant.offerManage.servlethandle.CollectedLorryRequireHandle";
    /**
     * 商家货车已报价需求列表
     */
    public static final String OFFEREDLORRYREQUIREMERCHANT = "cn.com.hoonsoft.yizhao.phone.home.merchant.offerManage.servlethandle.OfferedLorryRequireHandle";
    /**
     * 已完成需求列表商家货车
     */
    public static final String FINISHEDLORRYREQUIREMERCHANT = "cn.com.hoonsoft.yizhao.phone.home.merchant.offerManage.servlethandle.FinishedLorryRequireHandle";
    /**
     * 商家需求详情货车
     */
    public static final String GAINLORRYREQUIREMERCHANT = "cn.com.hoonsoft.yizhao.phone.home.merchant.offerManage.servlethandle.GainLorryRequireHandle";
    /**
     * 商家已发布需求搬家
     */
    public static final String RELEASEDMOVEREQUIREMERCHANT = "cn.com.hoonsoft.yizhao.phone.home.merchant.offerManage.servlethandle.ReleasedMoveRequireHandle";
    /**
     * 商家收藏需求搬家
     */
    public static final String COLLECTEDMOVEREQUIREMERCHANT = "cn.com.hoonsoft.yizhao.phone.home.merchant.offerManage.servlethandle.CollectedMoveRequireHandle";
    /**
     * 商家已报价列表搬家
     */
    public static final String OFFEREDMOVEREQUIREMERCHANT = "cn.com.hoonsoft.yizhao.phone.home.merchant.offerManage.servlethandle.OfferedMoveRequireHandle";
    /**
     * 商家已完成列表搬家
     */
    public static final String FINISHEDMOVEREQUIREMERCHANT = "cn.com.hoonsoft.yizhao.phone.home.merchant.offerManage.servlethandle.FinishedMoveRequireHandle";
    /**
     * 商家详情搬家
     */
    public static final String GAINMOVEREQUIREMERCHANT = "cn.com.hoonsoft.yizhao.phone.home.merchant.offerManage.servlethandle.GainMoveRequireHandle";

    /**
     * 商家已发布需求租车
     */
    public static final String RELEASEDRENTCARREQUIREMERCHANT = "cn.com.hoonsoft.yizhao.phone.home.merchant.offerManage.servlethandle.ReleasedRentCarRequireHandle";
    /**
     * 商家收藏需求租车
     */
    public static final String COLLECTEDRENTCARREQUIREMERCHANT = "cn.com.hoonsoft.yizhao.phone.home.merchant.offerManage.servlethandle.CollectedRentCarRequireHandle";
    /**
     * 商家已报价列表租车
     */
    public static final String OFFEREDRENTCARREQUIREMERCHANT = "cn.com.hoonsoft.yizhao.phone.home.merchant.offerManage.servlethandle.OfferedRentCarRequireHandle";
    /**
     * 商家已完成列表租车
     */
    public static final String FINISHEDRENTCARREQUIREMERCHANT = "cn.com.hoonsoft.yizhao.phone.home.merchant.offerManage.servlethandle.FinishedRentCarRequireHandle";
    /**
     * 商家详情租车
     */
    public static final String GAINRENTCARREQUIREMERCHANT = "cn.com.hoonsoft.yizhao.phone.home.merchant.offerManage.servlethandle.GainRentCarRequireHandle";
    public static final int LATESTNEWS = 1;
    public static final int COLLECTEDNEWS = 2;
    public static final int FINISHEDNEWS = 3;
    public static final int QUOTEDNEWS = 4;

    /* 游客-需求-已发布需求列表 */
    public static final String RELEASED_REQUIRE_TOURIST_LOIGSTICS = "cn.com.hoonsoft.yizhao.phone.home.sightseer.logistics.servlethandle.ReleasedRequireHandle";
    public static final String RELEASED_REQUIRE_TOURIST_LOIGSTICS_DETAILS = "cn.com.hoonsoft.yizhao.phone.home.sightseer.logistics.servlethandle.GainRequireHandle";
    public static final String RELEASED_REQUIRE_TOURIST_MOVE = "cn.com.hoonsoft.yizhao.phone.home.sightseer.move.servlethandle.ReleasedRequireHandle";
    public static final String RELEASED_REQUIRE_TOURIST_MOVE_DETAILS = "cn.com.hoonsoft.yizhao.phone.home.sightseer.move.servlethandle.GainRequireHandle";
    public static final String RELEASED_REQUIRE_TOURIST_LORRY = "cn.com.hoonsoft.yizhao.phone.home.sightseer.lorry.servlethandle.ReleasedRequireHandle";
    public static final String RELEASED_REQUIRE_TOURIST_LORRY_DETAILS = "cn.com.hoonsoft.yizhao.phone.home.sightseer.lorry.servlethandle.GainRequireHandle";
    public static final String RELEASED_REQUIRE_TOURIST_RENTCAR = "cn.com.hoonsoft.yizhao.phone.home.sightseer.RentCar.servlethandle.ReleasedRequireHandle";
    public static final String RELEASED_REQUIRE_TOURIST_RENTCAR_DETAILS = "cn.com.hoonsoft.yizhao.phone.home.sightseer.RentCar.servlethandle.GainRequireHandle";
    /* 游客-需求-已完成需求列表 */
    public static final String FINISHED_REQUIRE_TOURIST_LOGISTICS = "cn.com.hoonsoft.yizhao.phone.home.sightseer.logistics.servlethandle.FinishedRequireHandle";
    public static final String FINISHED_REQUIRE_TOURIST_MOVE = "cn.com.hoonsoft.yizhao.phone.home.sightseer.move.servlethandle.FinishedRequireHandle";
    public static final String FINISHED_REQUIRE_TOURIST_LORRY = "cn.com.hoonsoft.yizhao.phone.home.sightseer.lorry.servlethandle.FinishedRequireHandle";
    public static final String FINISHED_REQUIRE_TOURIST_RENTCAR = "cn.com.hoonsoft.yizhao.phone.home.sightseer.RentCar.servlethandle.FinishedRequireHandle";

    public static final String NEARBYMERCHANT_PERSONAL = "cn.com.hoonsoft.yizhao.phone.home.personal.gainNearbyMerchant.servlethandle.GainNearbyMerchantHandle";
    public static final String NEARBYEXPRESS_PERSONAL = "cn.com.hoonsoft.yizhao.phone.home.personal.express.servlethandle.GainNearbyExpressHandle";
    public static final String MERCHANTRECOMMENED_PERSONAL = "cn.com.hoonsoft.yizhao.phone.home.personal.merchantRecommend.servlethandle.GainMerchantRecommendHandle";
    public static final String GAINMERCHNATRECOMMEND = "cn.com.hoonsoft.yizhao.phone.home.personal.merchantRecommend.servlethandle.GainMerchantRecommendHandle";
    public static final String PHOTOPERSONAL = "cn.com.hoonsoft.yizhao.phone.home.personal.infoupdate.servlethandle.UploadPhotoHandle";
    public static final String HOMEFRAGMENTAD = "cn.com.hoonsoft.yizhao.phone.home.advertisement.servlethandle.GainEffectiveAdvertisementHandle";

    /**
     * 聊天
     */
    public static final String GAINGROUPLIST = "cn.com.hoonsoft.yizhao.phone.chat.servlethandle.GainGroupListHandle";
    public static final String GAINNOTICELIST = "cn.com.hoonsoft.yizhao.phone.chat.servlethandle.GainNoticeListHandle";
    public static final String GAINROSTERLIST = "cn.com.hoonsoft.yizhao.phone.chat.servlethandle.GainRosterListHandle";
    public static final String SAVEGROUPINFO = "cn.com.hoonsoft.yizhao.phone.chat.servlethandle.SaveGroupInfoHandle";
    public static final String UPDATEGROUPINFO = "cn.com.hoonsoft.yizhao.phone.chat.servlethandle.UpdateGroupInfoHandle";
    public static final String SAVEROSTERAPPLYINFO = "cn.com.hoonsoft.yizhao.phone.chat.servlethandle.SaveRosterApplyInfoHandle";
    public static final String AUDITGPSAPPLY = "cn.com.hoonsoft.yizhao.phone.chat.servlethandle.AuditGPSApplyHandle";
    public static final String AUDITROSTERAPPLYINFO = "cn.com.hoonsoft.yizhao.phone.chat.servlethandle.AuditRosterApplyInfoHandle";
    public static final String DELETEGROUP = "cn.com.hoonsoft.yizhao.phone.chat.servlethandle.DeleteGroupHandle";
    public static final String DELETEROSTER = "cn.com.hoonsoft.yizhao.phone.chat.servlethandle.DeleteRosterHandle";
    public static final String SAVEGPSAPPLY = "cn.com.hoonsoft.yizhao.phone.chat.servlethandle.SaveGPSApplyHandle";
    public static final String UPDATENICKNAME = "cn.com.hoonsoft.yizhao.phone.chat.servlethandle.UpdateNickNameHandle";
    public static final String UPDATEROSTERGROUP = "cn.com.hoonsoft.yizhao.phone.chat.servlethandle.UpdateRosterGroupHandle";
    public static final String GAINROSTERPOSITION = "cn.com.hoonsoft.yizhao.phone.chat.servlethandle.GainRosterPositionHandle";
    // ///////////////////////////////////////请求Handle配置(end)/////////////////////////////////////////

}
