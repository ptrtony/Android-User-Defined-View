package com.sinata.androidlearnhencoder;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Title:
 * Description:
 * Copyright:Copyright(c)2021
 * Company:成都博智维讯信息技术股份有限公司
 *
 * @author jingqiang.cheng
 * @date 2021/12/7
 */
class Dequene {

    Deque  deque = new ArrayDeque<String>();

    public void demo(){
        while (!deque.isEmpty()){
            deque.removeFirst();
        }
    }
}
