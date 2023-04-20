package exercise;

import java.util.Map;

// BEGIN
class SingleTag extends Tag {

    public SingleTag(String htmlTag, Map<String, String> attributes) {
        super(htmlTag, attributes);
    }

    public String toString() {
        return getString();
    }
}
// END
