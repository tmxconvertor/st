package com.st;

import org.apache.log4j.Logger;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;



@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TextProps.class})
public class SomeClassTest {

    @Autowired
    private
    SomeClass someClass;
    private Logger log = Logger.getLogger(SomeClassTest.class);

    @org.junit.Before
    public void setUp() {
        log.debug("setup");
    }

    @org.junit.Test
    public void setText() {
        System.out.println("class: "+someClass.text.getText());
    }

}