package com.example.myutil.disruptor;

import lombok.Data;

//定义事件event通过Disruptor进行交换的数据类型。
@Data
public class LongEvent {

    private Long value;

}
