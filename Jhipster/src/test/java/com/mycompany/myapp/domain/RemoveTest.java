package com.mycompany.myapp.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class RemoveTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Remove.class);
        Remove remove1 = new Remove();
        remove1.setId(1L);
        Remove remove2 = new Remove();
        remove2.setId(remove1.getId());
        assertThat(remove1).isEqualTo(remove2);
        remove2.setId(2L);
        assertThat(remove1).isNotEqualTo(remove2);
        remove1.setId(null);
        assertThat(remove1).isNotEqualTo(remove2);
    }
}
