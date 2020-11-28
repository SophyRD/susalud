package com.susalud.c1920c.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.susalud.c1920c.web.rest.TestUtil;

public class TecnicasEvaluativasTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TecnicasEvaluativas.class);
        TecnicasEvaluativas tecnicasEvaluativas1 = new TecnicasEvaluativas();
        tecnicasEvaluativas1.setId(1L);
        TecnicasEvaluativas tecnicasEvaluativas2 = new TecnicasEvaluativas();
        tecnicasEvaluativas2.setId(tecnicasEvaluativas1.getId());
        assertThat(tecnicasEvaluativas1).isEqualTo(tecnicasEvaluativas2);
        tecnicasEvaluativas2.setId(2L);
        assertThat(tecnicasEvaluativas1).isNotEqualTo(tecnicasEvaluativas2);
        tecnicasEvaluativas1.setId(null);
        assertThat(tecnicasEvaluativas1).isNotEqualTo(tecnicasEvaluativas2);
    }
}
