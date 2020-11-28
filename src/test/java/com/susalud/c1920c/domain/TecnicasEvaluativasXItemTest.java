package com.susalud.c1920c.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.susalud.c1920c.web.rest.TestUtil;

public class TecnicasEvaluativasXItemTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TecnicasEvaluativasXItem.class);
        TecnicasEvaluativasXItem tecnicasEvaluativasXItem1 = new TecnicasEvaluativasXItem();
        tecnicasEvaluativasXItem1.setId(1L);
        TecnicasEvaluativasXItem tecnicasEvaluativasXItem2 = new TecnicasEvaluativasXItem();
        tecnicasEvaluativasXItem2.setId(tecnicasEvaluativasXItem1.getId());
        assertThat(tecnicasEvaluativasXItem1).isEqualTo(tecnicasEvaluativasXItem2);
        tecnicasEvaluativasXItem2.setId(2L);
        assertThat(tecnicasEvaluativasXItem1).isNotEqualTo(tecnicasEvaluativasXItem2);
        tecnicasEvaluativasXItem1.setId(null);
        assertThat(tecnicasEvaluativasXItem1).isNotEqualTo(tecnicasEvaluativasXItem2);
    }
}
