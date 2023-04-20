package exercise;

import java.util.Map;
import java.util.List;
import java.util.stream.Collectors;

// BEGIN
public class PairedTag extends Tag {
    private final String TagsBody;
    private final List<Tag> internalTag;

    public PairedTag(String htmlTag, Map<String, String> attributes, String tagsBody, List<Tag> internalTag) {
        super(htmlTag, attributes);
        TagsBody = tagsBody;
        this.internalTag = internalTag;
    }

    public String getTagsBody() {
        return TagsBody;
    }

    public List<Tag> getInternalTag() {
        return internalTag;
    }

    @Override
    public String toString() {
        var str = new StringBuilder();
                for (var elements : getInternalTag()) {
                    str.append(elements.getString());
                }
        return getString() +
                getTagsBody() +
                str +
                "</" + getHtmlTag() + ">";
    }
}
// END
