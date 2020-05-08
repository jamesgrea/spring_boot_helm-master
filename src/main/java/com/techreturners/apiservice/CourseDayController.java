package com.techreturners.apiservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CourseDayController {

    @Autowired
    protected CourseDayService courseDayService = null;

    @GetMapping("/course-day")
	public CourseDay getCourseDay(@RequestParam(defaultValue = "1") String day) {
        return courseDayService.getDay(Integer.parseInt(day));
	}
}