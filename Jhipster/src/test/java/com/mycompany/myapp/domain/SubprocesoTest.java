package com.mycompany.myapp.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class SubprocesoTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Subproceso.class);
        Subproceso subproceso1 = new Subproceso();
        subproceso1.setId(1L);
        Subproceso subproceso2 = new Subproceso();
        subproceso2.setId(subproceso1.getId());
        assertThat(subproceso1).isEqualTo(subproceso2);
        subproceso2.setId(2L);
        assertThat(subproceso1).isNotEqualTo(subproceso2);
        subproceso1.setId(null);
        assertThat(subproceso1).isNotEqualTo(subproceso2);
    }
}
