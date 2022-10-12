package com.example.myutil.disruptor;

import com.lmax.disruptor.EventHandler;

//定义事件消费者
public class NextEventHandler implements EventHandler<LongEvent> {

    @Override
    public void onEvent(LongEvent event, long sequence, boolean endOfBatch) throws Exception {
        System.out.println("消费者next:"+event.getValue());
    }
}
