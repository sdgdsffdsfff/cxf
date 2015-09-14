package cn.zno.rocketmq;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.rocketmq.client.consumer.DefaultMQPushConsumer;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import com.alibaba.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.common.consumer.ConsumeFromWhere;
import com.alibaba.rocketmq.common.message.MessageExt;
import com.alibaba.rocketmq.common.protocol.heartbeat.MessageModel;

public class MyConsumer {

    private final Logger logger = LoggerFactory.getLogger(MyConsumer.class);

    private DefaultMQPushConsumer defaultMQPushConsumer;
    private String namesrvAddr;
    private String consumerGroup;

    /**
     * Spring bean init-method
     */
    public void init() throws InterruptedException, MQClientException {

        // ������Ϣ
        logger.info("DefaultMQPushConsumer initialize!");
        logger.info(consumerGroup);
        logger.info(namesrvAddr);

        // һ��Ӧ�ô���һ��Consumer����Ӧ����ά���˶��󣬿�������Ϊȫ�ֶ�����ߵ���<br>
        // ע�⣺ConsumerGroupName��Ҫ��Ӧ������֤Ψһ
        defaultMQPushConsumer = new DefaultMQPushConsumer(consumerGroup);
        defaultMQPushConsumer.setNamesrvAddr(namesrvAddr);
        defaultMQPushConsumer.setInstanceName(String.valueOf(System.currentTimeMillis()));

        // ����ָ��MyTopic��tags����MyTag

        defaultMQPushConsumer.subscribe("MyTopic", "MyTag");

        // ����Consumer��һ�������ǴӶ���ͷ����ʼ���ѻ��Ƕ���β����ʼ����<br>
        // ����ǵ�һ����������ô�����ϴ����ѵ�λ�ü�������
        defaultMQPushConsumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);

        // ����Ϊ��Ⱥ����(�����ڹ㲥����)
        defaultMQPushConsumer.setMessageModel(MessageModel.CLUSTERING);

        defaultMQPushConsumer.registerMessageListener(new MessageListenerConcurrently() {

            // Ĭ��msgs��ֻ��һ����Ϣ������ͨ������consumeMessageBatchMaxSize����������������Ϣ
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {

                MessageExt msg = msgs.get(0);
                if (msg.getTopic().equals("MyTopic")) {
                    // TODO ִ��Topic�������߼�
                    if (msg.getTags() != null && msg.getTags().equals("MyTag")) {
                        // TODO ִ��Tag������
                    	logger.info("================" + new String(msg.getBody()));
                    }
                }
                // ���û��return success ��consumer���������Ѹ���Ϣ��ֱ��return success
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });

        // Consumer������ʹ��֮ǰ����Ҫ����start��ʼ������ʼ��һ�μ���<br>
        defaultMQPushConsumer.start();

        logger.info("DefaultMQPushConsumer start success!");
    }

    /**
     * Spring bean destroy-method
     */
    public void destroy() {
        defaultMQPushConsumer.shutdown();
    }

    // ----------------- setter --------------------

    public void setNamesrvAddr(String namesrvAddr) {
        this.namesrvAddr = namesrvAddr;
    }

    public void setConsumerGroup(String consumerGroup) {
        this.consumerGroup = consumerGroup;
    }

}