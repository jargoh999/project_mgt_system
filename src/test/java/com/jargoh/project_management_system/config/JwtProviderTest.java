package com.jargoh.project_management_system.config;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestTemplate;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class JwtProviderTest {

    @Test
    void  seeSomething(){
        var res=JwtProvider.getEmailFromToken("eyJhbGciOiJIUzUxMiJ9.eyJpYXQiOjE3MjYwNDY5MjQsImV4cCI6MTcyNjkxMDkyNCwiZW1haWwiOiJ0ZXN0QGVtYWlsIn0.NPns93DRPjB-mN3p8gM_Pltf9X3xI1wOuyHkHC4oGRiU6eDeeT3AGGnmcuwk6ydXbDpcB_Hg7jWBY6K22yjkqw");
        System.out.println(res);
    }

}