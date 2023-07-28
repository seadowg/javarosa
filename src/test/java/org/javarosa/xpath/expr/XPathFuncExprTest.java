package org.javarosa.xpath.expr;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class XPathFuncExprTest {

    @Test
    public void isIdempotent_whenArgsContainsNonIdempotentFunc_returnsFalse() {
        // string(random())
        XPathFuncExpr expr = new XPathFuncExpr(new XPathQName("string"), new XPathExpression[] {
            new XPathFuncExpr(new XPathQName("random"), new XPathExpression[] {})
        });

        assertThat(expr.isIdempotent(), equalTo(false));
    }
}