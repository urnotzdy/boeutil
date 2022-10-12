package com.example.myutil.disruptor;

import com.lmax.disruptor.RingBuffer;

import java.nio.ByteBuffer;

//事件生产者
public class LongEventProducer {

    //是线程间交换数据的中转地
    public final RingBuffer<LongEvent> ringBuffer;

    public LongEventProducer(RingBuffer<LongEvent> ringBuffer) {
        this.ringBuffer = ringBuffer;
    }

    public void onData(ByteBuffer byteBuffer) {
        // 1.ringBuffer 事件队列 下一个槽
        long sequence = ringBuffer.next();
        Long data = null;
        try {
            //2.取出空的事件队列
            LongEvent longEvent = ringBuffer.get(sequence);
            data = byteBuffer.getLong(0);
            //3.获取事件队列传递的数据
            longEvent.setValue(data);
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        } finally {
//            System.out.println("生产者准备发送数据");
            //4.发布事件
            ringBuffer.publish(sequence);
        }
    }

}
