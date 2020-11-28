package com.mycompany.myapp.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class VerificadorTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Verificador.class);
        Verificador verificador1 = new Verificador();
        verificador1.setId(1L);
        Verificador verificador2 = new Verificador();
        verificador2.setId(verificador1.getId());
        assertThat(verificador1).isEqualTo(verificador2);
        verificador2.setId(2L);
        assertThat(verificador1).isNotEqualTo(verificador2);
        verificador1.setId(null);
        assertThat(verificador1).isNotEqualTo(verificador2);
    }
}
