import java.util.List;

public class URLCategory {
    String urlCategoryId;
    String name;
    List<URLPattern> urlPatterns;

    public String getUrlCategoryId() {
        return urlCategoryId;
    }

    public void setUrlCategoryId(String urlCategoryId) {
        this.urlCategoryId = urlCategoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<URLPattern> getUrlPatterns() {
        return urlPatterns;
    }

    public void setUrlPatterns(List<URLPattern> urlPatterns) {
        this.urlPatterns = urlPatterns;
    }



}
