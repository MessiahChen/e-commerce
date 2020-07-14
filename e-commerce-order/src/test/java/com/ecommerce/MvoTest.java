package com.ecommerce;

import com.ecommerce.pojo.SaoSalesOrder;
import com.ecommerce.service.OrderService;
import com.ecommerce.vo.SaoSalesOrderVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @ProjectName: e-commerce
 * @Package: com.ecommerce.test
 * @ClassName: com.ecommerce.MvoTest
 * @Description: 测试mvo
 * @Author: 邱晓淋
 * @CreateDate: 2020/7/14 9:33
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {OrderApplication.class})
public class MvoTest {
    @Autowired
    private OrderService orderService;

    @Test
    public void testGetSaoByManId(){
        List<SaoSalesOrderVO> saoSalesOrderVOS = orderService.getSaoByManId(11);
        for(SaoSalesOrderVO saoSalesOrderVO : saoSalesOrderVOS){
            System.out.println(saoSalesOrderVO.toString());
        }
    }
}
