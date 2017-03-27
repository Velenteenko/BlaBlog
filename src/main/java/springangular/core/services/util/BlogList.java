package springangular.core.services.util;

import springangular.core.entry.Blog;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by E5520 on 19.03.2017.
 */
public class BlogList {

    private List<Blog> blogs = new ArrayList<Blog>();

    public List<Blog> getBlogs() {
        return blogs;
    }

    public void setBlogs(List<Blog> blogs) {
        this.blogs = blogs;
    }
}
