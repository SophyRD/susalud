package com.susalud.c1920c.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.susalud.c1920c.web.rest.TestUtil;

public class CriteriosEvaluacionXItemTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CriteriosEvaluacionXItem.class);
        CriteriosEvaluacionXItem criteriosEvaluacionXItem1 = new CriteriosEvaluacionXItem();
        criteriosEvaluacionXItem1.setId(1L);
        CriteriosEvaluacionXItem criteriosEvaluacionXItem2 = new CriteriosEvaluacionXItem();
        criteriosEvaluacionXItem2.setId(criteriosEvaluacionXItem1.getId());
        assertThat(criteriosEvaluacionXItem1).isEqualTo(criteriosEvaluacionXItem2);
        criteriosEvaluacionXItem2.setId(2L);
        assertThat(criteriosEvaluacionXItem1).isNotEqualTo(criteriosEvaluacionXItem2);
        criteriosEvaluacionXItem1.setId(null);
        assertThat(criteriosEvaluacionXItem1).isNotEqualTo(criteriosEvaluacionXItem2);
    }
}
