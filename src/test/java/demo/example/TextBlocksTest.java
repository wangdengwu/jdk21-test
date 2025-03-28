package demo.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TextBlocksTest {

    @Test
    void testNormalFormat() {
        TextBlocks textBlocks = new TextBlocks();
        String expected = """
                <html>
                   <head>
                       <title>
                           Hello
                       </title>
                   </head>
                   <body>
                       World
                   </body>
                </html>""";
        assertEquals(expected, textBlocks.normalFormat("Hello", "World"));
    }

    @Test
    void testTextBlockFormat() {
        TextBlocks textBlocks = new TextBlocks();
        String expected = """
               <html>
                   <head>
                       <title>
                           Hello
                       </title>
                   </head>
                   <body>
                       World
                   </body>
               </html>
               """;
        assertEquals(expected, textBlocks.textBlockFormat("Hello", "World"));
    }

    @Test
    void testStrTemplateFormat() {
        TextBlocks textBlocks = new TextBlocks();
        String expected = """
                <html>
                    <head>
                        <title>
                            Hello
                        </title>
                    </head>
                    <body>
                        World
                    </body>
                </html>
                """;
        assertEquals(expected, textBlocks.strTemplateFormat("Hello", "World"));
    }

    @Test
    void testSellDrinkTo() {
        TextBlocks textBlocks = new TextBlocks();
        assertEquals("Selling wine to John, he is 20 years old", textBlocks.sellDrinkTo("John", 20));
    }

    @Test
    void testSellDrinkTo_UnderAge() {
        TextBlocks textBlocks = new TextBlocks();
        Exception exception = assertThrows(IllegalArgumentException.class, () -> textBlocks.sellDrinkTo("John", 17));
        assertEquals("No drinking for those under 18", exception.getMessage());
    }
}
