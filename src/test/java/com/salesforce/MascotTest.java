package com.salesforce;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class MascotTest {
    @Test
    void testCreation() {
        Mascot mascot = new Mascot("Tigers");
        assertThat(mascot).isNotNull();
        assertThat(mascot.name()).isNotNull();
    }
}
