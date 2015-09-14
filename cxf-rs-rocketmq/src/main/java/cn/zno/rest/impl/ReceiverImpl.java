package cn.zno.rest.impl;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;

import cn.zno.pojo.ReceiverRequest;
import cn.zno.pojo.ReceiverResponse;
import cn.zno.rest.Receiver;
import cn.zno.rocketmq.MyProducer;

import com.alibaba.rocketmq.client.exception.MQBrokerException;
import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.client.producer.SendResult;
import com.alibaba.rocketmq.client.producer.SendStatus;
import com.alibaba.rocketmq.common.message.Message;
import com.alibaba.rocketmq.remoting.exception.RemotingException;

public class ReceiverImpl implements Receiver {

	@Autowired
	private MyProducer myProducer;

	@Override
	public ReceiverResponse rece(ReceiverRequest receiverRequest) {
		
		ReceiverResponse response = new ReceiverResponse();

		byte[] msgBody = JSONObject.fromBean(receiverRequest).toString().getBytes();
			
		Message msg = new Message("MyTopic", "MyTag", msgBody);
		
		SendResult sendResult = null;
		try {
			sendResult = myProducer.getDefaultMQProducer().send(msg);
		} catch (MQClientException | RemotingException | MQBrokerException
				| InterruptedException e) {
			e.printStackTrace();
		}
		// 当消息发送失败时如何处理
		if (sendResult == null || sendResult.getSendStatus() != SendStatus.SEND_OK) {
			response.setCode("1");
			response.setResult("消息发送失败！");
		}else{
			response.setCode("0");
			response.setResult("消息发送成功！");
		}
		return response;
	}

}
