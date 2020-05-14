package com.example.stest.analysis;

import com.example.stest.analysis.util.encryption.Encryption;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StestApplicationTests {

    @Test
    public void contextLoads() {
        System.out.println(Encryption.enAesCode("STCASD","AAAAAAAAAAAAAAAA"));
        Long time=System.currentTimeMillis();
        System.out.println(time);
        System.out.println(Encryption.enAesCode(String.valueOf(time),"AAAAAAAAAAAAAAAA"));
    }

}
