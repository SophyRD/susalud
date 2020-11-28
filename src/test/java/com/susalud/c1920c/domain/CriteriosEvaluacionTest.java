package com.susalud.c1920c.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.susalud.c1920c.web.rest.TestUtil;

public class CriteriosEvaluacionTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CriteriosEvaluacion.class);
        CriteriosEvaluacion criteriosEvaluacion1 = new CriteriosEvaluacion();
        criteriosEvaluacion1.setId(1L);
        CriteriosEvaluacion criteriosEvaluacion2 = new CriteriosEvaluacion();
        criteriosEvaluacion2.setId(criteriosEvaluacion1.getId());
        assertThat(criteriosEvaluacion1).isEqualTo(criteriosEvaluacion2);
        criteriosEvaluacion2.setId(2L);
        assertThat(criteriosEvaluacion1).isNotEqualTo(criteriosEvaluacion2);
        criteriosEvaluacion1.setId(null);
        assertThat(criteriosEvaluacion1).isNotEqualTo(criteriosEvaluacion2);
    }
}
