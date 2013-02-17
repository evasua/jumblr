import com.tumblr.jumblr.JumblrClient;
import com.tumblr.jumblr.types.Blog;
import com.tumblr.jumblr.types.Post;
import com.tumblr.jumblr.types.User;

public class App {

    public static void main(String[] args) {
        
        JumblrClient client = new JumblrClient(
            "consumer_key",
            "consumer_secret"
        );
        
        client.setToken(
            "oauth_token",
            "oauth_token_secret"
        );
        
        
        // Play with users
        User user = client.userInfo();
        System.out.printf("%s is following %d blogs!\n", user.getName(), user.getFollowing());
        
        Blog my = null;
        for (Blog blog : user.getBlogs()) {
            System.out.println(blog.getName());
            my = blog;
        }

        for (Post post : client.blogSubmissions(my.getName())) {
            System.out.println(post.getId());
        }
        
        for (Post post : client.blogQueuedPosts(my.getName())) {
            System.out.println(post.getId());
        }
        
        for (Post post : my.draftPosts()) {
            System.out.println(post.getId());
        }
        
        for (Post post : my.submissions()) {
            System.out.println(post.getId());
        }
        
        // Get the blogs I'm following
        Blog lb = null;
        for (Blog blog : client.userFollowing()) {
            System.out.println(blog.getName());
            lb = blog;
        }

        client.follow(lb.getName());
        lb.follow();
        
        
        // Play with a blog
        Blog blog = client.blogInfo("seejohnrun.tumblr.com");
        System.out.println(blog.getTitle());
        System.out.println(blog.avatar());
        
        // Get our followers
        for (User follower : blog.followers()) {
            System.out.println(follower.getName());
        }
        
        // Get our likes
        Post last = null;
        for (Post post : blog.likedPosts()) {
            System.out.println(post.getId());
            last = post;
        }
        
        for (Post post : client.userLikes()) {
            System.out.println(post.getReblogKey());
        }
        
        // Like that last post
        client.like(last.getId(), last.getReblogKey());
        last.like();
        
    }
    
}
