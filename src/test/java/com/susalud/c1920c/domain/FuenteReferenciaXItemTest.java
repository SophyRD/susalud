package com.susalud.c1920c.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.susalud.c1920c.web.rest.TestUtil;

public class FuenteReferenciaXItemTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(FuenteReferenciaXItem.class);
        FuenteReferenciaXItem fuenteReferenciaXItem1 = new FuenteReferenciaXItem();
        fuenteReferenciaXItem1.setId(1L);
        FuenteReferenciaXItem fuenteReferenciaXItem2 = new FuenteReferenciaXItem();
        fuenteReferenciaXItem2.setId(fuenteReferenciaXItem1.getId());
        assertThat(fuenteReferenciaXItem1).isEqualTo(fuenteReferenciaXItem2);
        fuenteReferenciaXItem2.setId(2L);
        assertThat(fuenteReferenciaXItem1).isNotEqualTo(fuenteReferenciaXItem2);
        fuenteReferenciaXItem1.setId(null);
        assertThat(fuenteReferenciaXItem1).isNotEqualTo(fuenteReferenciaXItem2);
    }
}
