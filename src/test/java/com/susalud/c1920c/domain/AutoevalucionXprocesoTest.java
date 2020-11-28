package com.susalud.c1920c.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.susalud.c1920c.web.rest.TestUtil;

public class AutoevalucionXprocesoTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(AutoevalucionXproceso.class);
        AutoevalucionXproceso autoevalucionXproceso1 = new AutoevalucionXproceso();
        autoevalucionXproceso1.setId(1L);
        AutoevalucionXproceso autoevalucionXproceso2 = new AutoevalucionXproceso();
        autoevalucionXproceso2.setId(autoevalucionXproceso1.getId());
        assertThat(autoevalucionXproceso1).isEqualTo(autoevalucionXproceso2);
        autoevalucionXproceso2.setId(2L);
        assertThat(autoevalucionXproceso1).isNotEqualTo(autoevalucionXproceso2);
        autoevalucionXproceso1.setId(null);
        assertThat(autoevalucionXproceso1).isNotEqualTo(autoevalucionXproceso2);
    }
}
