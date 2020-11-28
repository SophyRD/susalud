package com.susalud.c1920c.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.susalud.c1920c.web.rest.TestUtil;

public class AutoevalucionTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Autoevalucion.class);
        Autoevalucion autoevalucion1 = new Autoevalucion();
        autoevalucion1.setId(1L);
        Autoevalucion autoevalucion2 = new Autoevalucion();
        autoevalucion2.setId(autoevalucion1.getId());
        assertThat(autoevalucion1).isEqualTo(autoevalucion2);
        autoevalucion2.setId(2L);
        assertThat(autoevalucion1).isNotEqualTo(autoevalucion2);
        autoevalucion1.setId(null);
        assertThat(autoevalucion1).isNotEqualTo(autoevalucion2);
    }
}
