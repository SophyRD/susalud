package com.susalud.c1920c.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.susalud.c1920c.web.rest.TestUtil;

public class MacroprocesoTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Macroproceso.class);
        Macroproceso macroproceso1 = new Macroproceso();
        macroproceso1.setId(1L);
        Macroproceso macroproceso2 = new Macroproceso();
        macroproceso2.setId(macroproceso1.getId());
        assertThat(macroproceso1).isEqualTo(macroproceso2);
        macroproceso2.setId(2L);
        assertThat(macroproceso1).isNotEqualTo(macroproceso2);
        macroproceso1.setId(null);
        assertThat(macroproceso1).isNotEqualTo(macroproceso2);
    }
}
