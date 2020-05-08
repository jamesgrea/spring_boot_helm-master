package com.techreturners.apiservice;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CourseDayServiceTest {

    @Autowired
    private CourseDayService courseDayService;

    @Test
    public void validDayTest() {
        CourseDay courseDay = courseDayService.getDay(1);
        assertEquals("DevOps - History and Background", courseDay.getDescription());
    }

    @Test
    public void invalidDayTest() {
        CourseDay courseDay = courseDayService.getDay(6);
        assertEquals("You know its only five days?", courseDay.getDescription());
    }

}