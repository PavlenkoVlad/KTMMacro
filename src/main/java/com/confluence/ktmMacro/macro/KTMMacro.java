package com.confluence.ktmMacro.macro;

import com.atlassian.confluence.content.render.xhtml.ConversionContext;
import com.atlassian.confluence.macro.Macro;
import com.atlassian.confluence.macro.MacroExecutionException;

import java.util.Map;

public class KTMMacro implements Macro {

    @Override
    public String execute(Map<String, String> map, String s, ConversionContext conversionContext) throws MacroExecutionException {
        return "<p>" + s + "</p>";
    }

    @Override
    public Macro.BodyType getBodyType() {
        return Macro.BodyType.PLAIN_TEXT;
    }

    @Override
    public Macro.OutputType getOutputType() {
        return Macro.OutputType.INLINE;
    }

}
