package server.io.model;

import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class TitleEntityTests {

    @Test
    public void canInstantiate () {
        assertNotNull(new TitleEntity("ISBN", "Title"));
    }
}
