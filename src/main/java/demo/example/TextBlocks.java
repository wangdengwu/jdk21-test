package demo.example;

import java.util.Iterator;

public class TextBlocks {
    @SuppressWarnings("all")
    public String normalFormat(String title, String body) {
        return "<html>" + "\n" +
                "   <head>" + "\n" +
                "       <title>" + "\n" +
                "           " + title + "\n" +
                "       </title>" + "\n" +
                "   </head>" + "\n" +
                "   <body>" + "\n" +
                "       " + body + "\n" +
                "   </body>" + "\n" +
                "</html>";
    }

    //@since JDK 15
    public String textBlockFormat(String title, String body) {
        return """
                <html>
                    <head>
                        <title>
                            %s
                        </title>
                    </head>
                    <body>
                        %s
                    </body>
                </html>
                """.formatted(title, body);
    }

    // @since JDK 21 preview
    @SuppressWarnings("all")
    public String strTemplateFormat(String title, String body) {
        return STR."""
                <html>
                    <head>
                        <title>
                            \{title}
                        </title>
                    </head>
                    <body>
                        \{body}
                    </body>
                </html>
                """;
    }

    // @since JDK 21 preview
    public String sellDrinkTo(String name, Integer age) {
        SellDrinkTo sellDrinkTo = new SellDrinkTo();
        return sellDrinkTo."""
                Selling wine to \{name}, he is \{age} years old""";
    }

}

@SuppressWarnings("all")
class SellDrinkTo implements StringTemplate.Processor<String, IllegalArgumentException> {
    @Override
    public String process(StringTemplate st) throws IllegalArgumentException {
        StringBuilder sb = new StringBuilder();
        Iterator<String> fragmentsIter = st.fragments().iterator();
        for (Object value : st.values()) {
            sb.append(fragmentsIter.next());

            if (value instanceof Number && ((Number) value).intValue() < 18) {
                throw new IllegalArgumentException("No drinking for those under 18");
            }

            sb.append(value);
        }

        sb.append(fragmentsIter.next());

        return sb.toString();
    }
}