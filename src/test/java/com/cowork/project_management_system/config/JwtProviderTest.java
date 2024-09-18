package com.cowork.project_management_system.config;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class JwtProviderTest {

    @Test
    void  seeSomething(){
        var res=JwtProvider.getEmailFromToken("eyJhbGciOiJIUzUxMiJ9.eyJpYXQiOjE3MjYwNDY5MjQsImV4cCI6MTcyNjkxMDkyNCwiZW1haWwiOiJ0ZXN0QGVtYWlsIn0.NPns93DRPjB-mN3p8gM_Pltf9X3xI1wOuyHkHC4oGRiU6eDeeT3AGGnmcuwk6ydXbDpcB_Hg7jWBY6K22yjkqw");
        System.out.println(res);
    }

}