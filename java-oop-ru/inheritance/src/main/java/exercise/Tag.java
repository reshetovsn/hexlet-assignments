package exercise;

import java.util.stream.Collectors;
import java.util.Map;

// BEGIN
public abstract class Tag {
    private final String htmlTag;
    private final Map<String, String> attributes;

    public Tag(String htmlTag, Map<String, String> attributes) {
        this.htmlTag = htmlTag;
        this.attributes = attributes;
    }

    public String getHtmlTag() {
        return htmlTag;
    }

    public String getString() {
        var str = new StringBuilder();
        str.append("<");
        str.append(getHtmlTag());
        for (Map.Entry<String, String> elements : attributes.entrySet()) {
            str.append(" ");
            str.append(elements.getKey());
            str.append("=");
            str.append("\"");
            str.append(elements.getValue());
            str.append("\"");
        }
        str.append(">");
        return str.toString();
    }
}
// END
