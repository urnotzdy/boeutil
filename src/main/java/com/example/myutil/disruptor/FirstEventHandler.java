package com.example.myutil.disruptor;

import com.lmax.disruptor.EventHandler;

//定义事件消费者
public class FirstEventHandler implements EventHandler<LongEvent> {

    @Override
    public void onEvent(LongEvent event, long sequence, boolean endOfBatch) throws Exception {
        System.out.println("消费者first:"+event.getValue());
        event.setValue(event.getValue()+10);
    }
}
