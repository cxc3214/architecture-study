package net.imspring.study.weixin.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.imspring.study.weixin.bean.TextMsg;
import net.imspring.study.weixin.tool.BeanKit;
import net.imspring.study.weixin.tool.XmlKit;

import org.jsoup.Jsoup;
import org.jsoup.helper.StringUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

@Controller
@RequestMapping("/msg")
public class MsgController {
	@Value("${weixin.tulingurl:}")
	private String tulingUrl ;
	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public String vali(@RequestParam Map map) {
		// // 微信加密签名
		// String signature = request.getParameter("signature");
		// // 时间戳
		// String timestamp = request.getParameter("timestamp");
		// // 随机数
		// String nonce = request.getParameter("nonce");
		// 随机字符串
		return map.get("echostr").toString();
	}

	@RequestMapping(method = RequestMethod.POST)
	@ResponseBody
	public String doCore(HttpServletRequest request,HttpServletResponse response) {
		TextMsg textMsg = BeanKit.instantiate(TextMsg.class);
		try {
			Map<String,String> requestMap = XmlKit.parseXml(request);
			// 发送方帐号（open_id）
			String fromUserName = requestMap.get("FromUserName");
			// 公众帐号
			String toUserName = requestMap.get("ToUserName");
			// 消息类型
			String msgType = requestMap.get("MsgType");
			String msgId = requestMap.get("MsgId");
			//消息内容
			String content = requestMap.get("Content");
			
			// 默认回复此文本消息
			textMsg.setToUserName(fromUserName);
			textMsg.setFromUserName(toUserName);
			textMsg.setCreateTime(new Date().getTime());
			textMsg.setMsgType("text");
			textMsg.setContent(getTulingMsg(content));
			
//			String respMessage = XmlKit.textMessageToXml(textMsg);
//			PrintWriter out = response.getWriter();
//			out.print(respMessage);
//			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		String respMessage = XmlKit.textMessageToXml(textMsg);
		return respMessage;
	}

	private String getTulingMsg(String reqText) throws IOException{
		String restr ="";
		String msg  = Jsoup.connect(tulingUrl).data("info", reqText).get().select("body").html();
		if(!StringUtil.isBlank(msg)){
			JSONObject json = JSON.parseObject(msg);
			restr = json.getString("text");
		}
		return restr;
	}
}
