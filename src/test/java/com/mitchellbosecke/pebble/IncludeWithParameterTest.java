package com.mitchellbosecke.pebble;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.mitchellbosecke.pebble.error.PebbleException;
import com.mitchellbosecke.pebble.template.PebbleTemplate;

/**
 * This class tests if includes with parameters work.
 *
 * @author Thomas Hunziker
 *
 */
public class IncludeWithParameterTest extends AbstractTest {

    /**
     * Test if parameters are processed correctly.
     */
    @Test
    public void testIncludeWithParameters() throws PebbleException, IOException {

        PebbleTemplate template = pebble.getTemplate("template.includeWithParameter1.peb");
        Map<String, Object> context = new HashMap<>();

        context.put("contextVariable", "some-context-variable");
        context.put("level", 1);
        Writer writer = new StringWriter();
        template.evaluate(writer, context);

        String expectedOutput = "simple:simple-value" + "contextVariable:some-context-variable" + "map.position:left"
                + "map.contextVariable:some-context-variable" + "level:2" + "level-main:1";

        assertEquals(expectedOutput, writer.toString());

    }

    @Test
    public void testIncludeWithParametersNotIsolated() throws PebbleException, IOException {

        PebbleTemplate template = pebble.getTemplate("template.includeWithParameterNotIsolated1.peb");
        Map<String, Object> context = new HashMap<>();

        Writer writer = new StringWriter();
        template.evaluate(writer, context);

        String expectedOutput = "bazbar";

        assertEquals(expectedOutput, writer.toString());

    }

}
