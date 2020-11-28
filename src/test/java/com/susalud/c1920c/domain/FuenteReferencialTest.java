package com.susalud.c1920c.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.susalud.c1920c.web.rest.TestUtil;

public class FuenteReferencialTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(FuenteReferencial.class);
        FuenteReferencial fuenteReferencial1 = new FuenteReferencial();
        fuenteReferencial1.setId(1L);
        FuenteReferencial fuenteReferencial2 = new FuenteReferencial();
        fuenteReferencial2.setId(fuenteReferencial1.getId());
        assertThat(fuenteReferencial1).isEqualTo(fuenteReferencial2);
        fuenteReferencial2.setId(2L);
        assertThat(fuenteReferencial1).isNotEqualTo(fuenteReferencial2);
        fuenteReferencial1.setId(null);
        assertThat(fuenteReferencial1).isNotEqualTo(fuenteReferencial2);
    }
}
