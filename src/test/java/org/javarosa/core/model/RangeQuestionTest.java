package org.javarosa.core.model;

import org.javarosa.test.Scenario;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.javarosa.core.test.AnswerDataMatchers.intAnswer;
import static org.javarosa.test.BindBuilderXFormsElement.bind;
import static org.javarosa.test.XFormsElement.*;
import static org.javarosa.test.XFormsElement.t;

public class RangeQuestionTest {
    @Test
    public void answerIsPreservedWhenRangeQuestionHasIncompleteChoices() throws Exception {
        Scenario scenario = Scenario.init("indefinite repeat", html(
            head(
                title("Indefinite repeat"),
                model(
                    mainInstance(t("data id=\"indefinite-repeat\"",
                        t("range")
                    )),

                    instance("ticks",
                        item(-2, "A"),
                        item(0, "B"),
                        item(2, "C")),

                    bind("/data/range").type("int")
                )),
            body(
                t("range ref=\"/data/range\" start=\"-2\" end=\"2\" step=\"1\"",
                    t("itemset nodeset=\"instance('ticks')/root/item\"",
                        t("label ref=\"label\""),
                        t("value ref=\"value\"")
                    )
                )
            )
        ));

        scenario.answer("/data/range", 1);
        scenario.choicesOf("/data/range");
        assertThat(scenario.answerOf("/data/range"), is(intAnswer(1)));
    }
}
