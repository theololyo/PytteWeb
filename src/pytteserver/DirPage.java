package pytteserver;
import java.util.List;
/**
 * Concatinates a html-"document" containing directory listing if the given
 * request is a folder
 *
 * @author Markovic
 */
public final class DirPage {
    private final StringBuilder _strBldr;
    private final List<String> _linkList;
    private final String _dir;
    /**
     * Constructs a new html-"document"
     *
     * @param dir The current directory
     * @param linkList a list containing URIs for the files in this folder
     */
    public DirPage(String dir, List<String> linkList) {
        _strBldr = new StringBuilder();
        _linkList = linkList;
        _dir = dir;
        addLinks();
    }
    /**
     * adds all the given filepaths to an un-ordered list
     */
    public void addLinks() {
        openHtml();
        for (String str : _linkList) {
            _strBldr.append("<li> \r\n");
            _strBldr.append("<a href=");
            _strBldr.append('"' + str + '"' + ">" + str);
            _strBldr.append("</a></li>");
        }
        closeHtml();
    }
    /**
     * returns the generated html-"document"
     *
     * @return the genrated html-"document"
     */
    public String getHtml() {
        return _strBldr.toString();
    }
    private void openHtml() {
        _strBldr.append("<!DOCTYPE html> \r\n");
        _strBldr.append("<html lang=\"en\"> \r\n");
        _strBldr.append("<head><title>").append(_dir).append("</title></head> \r\n");
        _strBldr.append("<body> \r\n");
        _strBldr.append("<ul> \r\n");
    }
    private void closeHtml() {
        _strBldr.append("</ul> \r\n");
        _strBldr.append("</body> \r\n");
        _strBldr.append("</html>");
    }
}
