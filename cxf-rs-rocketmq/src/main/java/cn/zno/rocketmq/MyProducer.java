package cn.zno.rocketmq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.client.producer.DefaultMQProducer;

public class MyProducer {

	private final Logger logger = LoggerFactory.getLogger(MyProducer.class);

	private DefaultMQProducer defaultMQProducer;
	private String producerGroup;
	private String namesrvAddr;

	/**
	 * Spring bean init-method
	 */
	public void init() throws MQClientException {
		// ������Ϣ
		logger.info("DefaultMQProducer initialize!");
		logger.info(producerGroup);
		logger.info(namesrvAddr);

		// ��ʼ��
		defaultMQProducer = new DefaultMQProducer(producerGroup);
		defaultMQProducer.setNamesrvAddr(namesrvAddr);
		defaultMQProducer.setInstanceName(String.valueOf(System
				.currentTimeMillis()));

		defaultMQProducer.start();

		logger.info("DefaultMQProudcer start success!");

	}

	/**
	 * Spring bean destroy-method
	 */
	public void destroy() {
		defaultMQProducer.shutdown();
	}

	public DefaultMQProducer getDefaultMQProducer() {
		return defaultMQProducer;
	}

	// ---------------setter -----------------

	public void setProducerGroup(String producerGroup) {
		this.producerGroup = producerGroup;
	}

	public void setNamesrvAddr(String namesrvAddr) {
		this.namesrvAddr = namesrvAddr;
	}

}