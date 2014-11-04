package cn.com.hoonsoft.tool;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.jivesoftware.smack.Chat;
import org.jivesoftware.smack.ChatManagerListener;
import org.jivesoftware.smack.ConnectionConfiguration;
import org.jivesoftware.smack.MessageListener;
import org.jivesoftware.smack.Roster;
import org.jivesoftware.smack.RosterEntry;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.packet.Packet;
import org.jivesoftware.smack.packet.Presence;
import org.jivesoftware.smack.util.StringUtils;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

/**
 * XMPP工具类
 * @author 曾繁添
 * @version 1.0
 */
public class ToolXMPP {

	private static ToolXMPP mToolXMPP = null;
	private static XMPPConnection connection = null;
	/**聊天窗口管理map集合**/
	private Map<String, Chat> chatManage = new HashMap<String, Chat>();

	
	private ToolXMPP() {
		
	}
	
	/**
	 * 对外公开的实例化接口
	 * @return 
	 */
	public static ToolXMPP newInstance(String address,int port){
		try {
			if(mToolXMPP == null){
				mToolXMPP = new ToolXMPP();
				ConnectionConfiguration config = new ConnectionConfiguration(address,port);
				/** 是否启用调试 */
				config.setDebuggerEnabled(true);
				connection = new XMPPConnection(config);
			}
		} catch (Exception e) {
			System.out.println("创建XMMPP失败，原因："+e.getMessage());
			e.printStackTrace();
		}
		return mToolXMPP;
	}
	
	/**
	 * 获取Openfire连接
	 * @return
	 */
	public static XMPPConnection getConnection(){
		return connection;
	}
	
	/**
	 * 连接服务器
	 * @return
	 */
	public boolean connect() {
		try {
			if(null != connection){
				connection.connect();
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	/**
	 * 断开连接
	 */
    public void disConnect() {
    	if(null != connection){
    		connection.disconnect();
    	}
    }
    
	 /**
	 * 登录
	 * 
	 * @param username 登录帐号
	 * @param pswd 登录密码
	 * @return 是否登录成功
	 */
	public boolean login(String username, String pswd) {
		try {
			if (connection == null) return false;
			connection.login(username, pswd);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	/**
	 * 获取或创建聊天窗口
	 * @param friend 好友名
	 * @param listenter 聊天监听器
	 * @return
	 */
	public Chat getFriendChat(String friend, MessageListener listenter) {
		if(getConnection()==null)
			return null;
		
		/** 判断是否创建聊天窗口 */
		for (String fristr : chatManage.keySet()) {
			if (fristr.equals(friend)) {
				// 存在聊天窗口，则返回对应聊天窗口
				return chatManage.get(fristr);
			}
		}
		/** 创建聊天窗口 */
		Chat chat = getConnection().getChatManager().createChat(friend + "@"+getConnection().getServiceName(), listenter);
		
		/** 添加聊天窗口到chatManage */
		chatManage.put(friend, chat);
		
		return chat;
	}
	
	/**
	 * 创建聊天会话
	 * @param toUser 聊天对象id
	 * @param domain 域
	 */
	public Chat createChat(String toUser,String domain){
		Chat mChat = connection.getChatManager().createChat(toUser+domain,mMessageListener);
		return mChat;
	}
	
	/**
	 * 发送消息
	 * @param strMsg 消息内容
	 */
	public void sendMessage(Chat chat,String strMsg){
		try {
			//发送消息
			chat.sendMessage(strMsg);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
    /** 
     * 修改密码 
     * @param newPswd 新密码
     * @return 是否修改成功
     */  
    public static boolean changePassword(String newPswd)  
    {  
        try {  
            connection.getAccountManager().changePassword(newPswd);  
            return true;  
        } catch (Exception e) {  
            return false;  
        }  
    } 
	
	/**
	 * 设置用户状态
	 */
	public void setPresence(int code) {
		if (connection == null)
			return;
		Presence presence;
		switch (code) {
			case 0:
				presence = new Presence(Presence.Type.available);
				connection.sendPacket(presence);
				Log.v("state", "设置在线");
				break;
			case 1:
				presence = new Presence(Presence.Type.available);
				presence.setMode(Presence.Mode.chat);
				connection.sendPacket(presence);
				Log.v("state", "设置Q我吧");
				System.out.println(presence.toXML());
				break;
			case 2:
				presence = new Presence(Presence.Type.available);
				presence.setMode(Presence.Mode.dnd);
				connection.sendPacket(presence);
				Log.v("state", "设置忙碌");
				System.out.println(presence.toXML());
				break;
			case 3:
				presence = new Presence(Presence.Type.available);
				presence.setMode(Presence.Mode.away);
				connection.sendPacket(presence);
				Log.v("state", "设置离开");
				System.out.println(presence.toXML());
				break;
			case 4:
				Roster roster = connection.getRoster();
				Collection<RosterEntry> entries = roster.getEntries();
				for (RosterEntry entry : entries) {
					presence = new Presence(Presence.Type.unavailable);
					presence.setPacketID(Packet.ID_NOT_AVAILABLE);
					presence.setFrom(connection.getUser());
					presence.setTo(entry.getUser());
					connection.sendPacket(presence);
					System.out.println(presence.toXML());
				}
				// 向同一用户的其他客户端发送隐身状态
				presence = new Presence(Presence.Type.unavailable);
				presence.setPacketID(Packet.ID_NOT_AVAILABLE);
				presence.setFrom(connection.getUser());
				presence.setTo(StringUtils.parseBareAddress(connection.getUser()));
				connection.sendPacket(presence);
				Log.v("state", "设置隐身");
				break;
			case 5:
				presence = new Presence(Presence.Type.unavailable);
				connection.sendPacket(presence);
				Log.v("state", "设置离线");
				break;
			default:
				break;
			}
		}
    
    /** 
     * 删除当前用户 
     * @param connection 
     * @return 
     */  
    public boolean deleteAccount()  
    {  
        try {  
            connection.getAccountManager().deleteAccount();  
            return true;  
        } catch (Exception e) {  
            return false;  
        }  
    }  
	
	/**
	 * 退出
	 */
    public void exit() {
        // 状态
        Presence presence = new Presence(Presence.Type.unavailable);
        connection.sendPacket(presence);
        //断开连接
        disConnect();
    }
    
    /**
     * 消息监听器
     */
    public MessageListener mMessageListener = new MessageListener(){

		@Override
		public void processMessage(Chat arg0, Message msg) {
			Log.i("ToolXMPP", "from="+msg.getFrom() + "to="+msg.getTo() + "body="+msg.getBody() + "subject="+msg.getSubject());
			
			//登录用户
			StringUtils.parseName(getConnection().getUser());
			//发送消息用户
			msg.getFrom();
			//消息内容
			String body = msg.getBody();
			boolean left = body.substring(0, 1).equals("{");
			boolean right = body.substring(body.length()-1, body.length()).equals("}");
			if(left&&right){
				try {
					JSONObject obj = new JSONObject(body);
					String type = obj.getString("messageType");
					String chanId = obj.getString("chanId");
					String chanName = obj.getString("chanName");
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
    	}
    };
    
    /**
     * 单人聊天信息监听类
     * 
     */
    public ChatManagerListener mChatManagerListener = new ChatManagerListener(){

    	public void chatCreated(Chat chat, boolean arg1) 
    	{
    		chat.addMessageListener(mMessageListener);
    	}
    };
	
}
