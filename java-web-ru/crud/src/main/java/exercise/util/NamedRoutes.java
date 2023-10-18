package exercise.util;

public class NamedRoutes {

    public static String rootPath() {
        return "/";
    }

    // BEGIN
    public static String postsPath() {
        return "/posts";
    }

    public static String postsPath(int number) {
        return "/posts?page=" + String.valueOf(number);
    }

    public static String postPath(String id) {
        return "/posts/" + id;
    }

    public static String postPath(Long id) {
        return postPath(String.valueOf(id));
    }
    // END
}
