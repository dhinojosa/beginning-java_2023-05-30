package com.salesforce;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;



public class BoxTest {
    @Test
    void testCreationOfABox() {
        Box box = new Box(12.00, 14.00, 40.00);
        Assertions.assertThat(box.getWidth()).isEqualTo(12.00);
        Assertions.assertThat(box.getLength()).isEqualTo(14.00);
        Assertions.assertThat(box.getHeight()).isEqualTo(40.00);
    }

    @Test
    void testCreationOfABoxAndChangeTheValues() {
        Box box = new Box(12.00, 14.00, 40.00);
        box.setHeight(90.00);
        Assertions.assertThat(box.getWidth()).isEqualTo(12.00);
        Assertions.assertThat(box.getLength()).isEqualTo(14.00);
        Assertions.assertThat(box.getHeight()).isEqualTo(90.00);
    }
}
