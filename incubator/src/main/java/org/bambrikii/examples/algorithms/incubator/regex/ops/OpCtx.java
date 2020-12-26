package org.bambrikii.examples.algorithms.incubator.regex.ops;

import lombok.Getter;
import lombok.Setter;
import org.bambrikii.examples.algorithms.incubator.regex.AbstractCtx;

@Getter
@Setter
public class OpCtx extends AbstractCtx {
    private String pattern;

    @Override
    public String getStr() {
        return pattern;
    }
}
