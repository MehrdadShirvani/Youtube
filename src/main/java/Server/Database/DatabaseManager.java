package Server.Database;

import Shared.Models.*;
import jakarta.persistence.*;
import jakarta.persistence.criteria.*;

import java.util.ArrayList;
import java.util.List;

public class DatabaseManager {
    static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("mainPersistentUnit");
    //region Channels
    public static Channel addChannel(Channel channel) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        entityManager.persist(channel);
        transaction.commit();

        Channel savedChannel = entityManager.find(Channel.class, channel.getChannelId());

        entityManager.close();
        return savedChannel;
    }
    public static void editChannel(Channel updatedChannel) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        Channel mergedChannel = entityManager.merge(updatedChannel);

        transaction.commit();
        entityManager.close();
    }
    public static Channel getChannel(Long channelId)
    {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        Channel channel = entityManager.find(Channel.class, channelId);

        entityManager.close();
        return channel;
    }
    public static List<Channel> getChannels() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            List<Channel> channels = entityManager.createQuery(
                            "SELECT c FROM Channels c", Channel.class)
                    .getResultList();
            entityManager.getTransaction().commit();
            return channels;
        } finally {
            if (entityManager.isOpen()) {
                entityManager.close();
            }
        }
    }
    public static List<Channel> getSubscribedChannels(Long channelId)
    {
        return new ArrayList<>();
        //TODO
    }
    public static List<Channel> getSubscriberChannels(Long channelId) {
        return new ArrayList<>();
        //TODO
    }
    //endregion

    //region Reactions
    public static Reaction addReaction(Reaction reaction)
    {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        entityManager.persist(reaction);
        transaction.commit();

        Reaction savedReaction = entityManager.find(Reaction.class, reaction.getReactionId());

        entityManager.close();
        return savedReaction;
    }
    public static void editReaction(Reaction reaction)
    {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        Reaction mergedReaction = entityManager.merge(reaction);

        transaction.commit();
        entityManager.close();
    }
    public static Reaction getReaction(Long channelId, Long videoId)
    {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        TypedQuery<Reaction> query = entityManager.createQuery(
                "SELECT r FROM Reactions r WHERE r.channelId = :channelId AND r.videoId = :videoId", Reaction.class);
        query.setParameter("videoId", videoId);
        query.setParameter("channelId", channelId);

        try
        {
            return query.getSingleResult();
        }
        catch (NoResultException e)
        {
            return null;
        }
        finally {
            if (entityManager.isOpen()) {
                entityManager.close();
            }
        }
    }
    public static void deleteReaction(Long reactionId)
    {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        Reaction reaction = entityManager.find(Reaction.class, reactionId);

        if (reaction != null) {
            entityManager.remove(reaction);
        }

        transaction.commit();
        entityManager.close();
    }
    //endregion

    //region Comments
    public static Comment getComment(Long commentId)
    {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        Comment comment = entityManager.find(Comment.class, commentId);

        entityManager.close();
        return comment;
    }
    public static Comment addComment(Comment comment)
    {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        entityManager.persist(comment);
        transaction.commit();

        Comment savedComment = entityManager.find(Comment.class, comment.getCommentId());

        entityManager.close();
        return savedComment;
    }
    public static void editComment(Comment comment) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        Comment mergedComment = entityManager.merge(comment);

        transaction.commit();
        entityManager.close();
    }
    public static void deleteComment(Long commentId)
    {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        Comment comment = entityManager.find(Comment.class, commentId);

        if (comment != null) {
            entityManager.remove(comment);
        }

        transaction.commit();
        entityManager.close();
    }
    //endregion

    //region CommentReactions
    public static CommentReaction addCommentReaction(CommentReaction commentReaction) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        entityManager.persist(commentReaction);
        transaction.commit();

        CommentReaction savedCommentReaction = entityManager.find(CommentReaction.class, commentReaction.getCommentId());

        entityManager.close();
        return savedCommentReaction;
    }
    public static void editCommentReaction(CommentReaction commentReaction) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        CommentReaction mergedCommentReaction = entityManager.merge(commentReaction);

        transaction.commit();
        entityManager.close();
    }
    public static void deleteCommentReaction(Long commentReactionId) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        CommentReaction commentReaction = entityManager.find(CommentReaction.class, commentReactionId);

        if (commentReaction != null) {
            entityManager.remove(commentReaction);
        }

        transaction.commit();
        entityManager.close();
    }
    public static List<CommentReaction> getCommentReactionsOfComment(Long commentId)
    {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        TypedQuery<CommentReaction> query = entityManager.createQuery(
                "SELECT cr FROM CommentReactions cr WHERE cr.commentId = :commentId", CommentReaction.class);
        query.setParameter("commentId", commentId);
        return  query.getResultList();
    }
    public static CommentReaction getCommentReaction(Long channelId, Long commentId)
    {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        TypedQuery<CommentReaction> query = entityManager.createQuery(
                "SELECT cr FROM CommentReactions cr WHERE cr.commentId = :commentId", CommentReaction.class);
        query.setParameter("commentId", commentId);
        try
        {
            return query.getSingleResult();
        }catch (NoResultException e)
        {
            return null;
        }
    }
    //endregion

    //region Playlists
    public static Playlist addPlaylist(Playlist playlist)
    {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        entityManager.persist(playlist);
        transaction.commit();

        Playlist savedPlaylist = entityManager.find(Playlist.class, playlist.getPlaylistId());

        entityManager.close();
        return savedPlaylist;
    }
    public static void editPlaylist(Playlist playlist) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        Playlist mergePlaylist = entityManager.merge(playlist);

        transaction.commit();
        entityManager.close();
    }
    public static List<Video> getPlaylistVideos(Long playlistId)
    {
        return new ArrayList<>();
        //TODO
    }
    public static List<Channel> getPlaylistChannels(Long playlistId)
    {
        return new ArrayList<>();
        //TODO
    }
    //endregion

    //region Accounts
    public static Account getAccount(Long accountId)
    {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        Account account = entityManager.find(Account.class, accountId);

        entityManager.close();
        return account;
    }
    public static Account getAccount(String username, String password)
    {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        TypedQuery<Account> query = entityManager.createQuery(
                "SELECT a FROM Accounts a WHERE a.username = :username AND a.password = :password", Account.class);
        query.setParameter("username", username);
        query.setParameter("password", password);
        try
        {
            return query.getSingleResult();
        }catch (NoResultException e)
        {
            return null;
        }
    }
    public static Account addAccount(Account account)
    {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        entityManager.persist(account);
        transaction.commit();

        Account savedAccount = entityManager.find(Account.class, account.getAccountId());

        entityManager.close();
        return savedAccount;
    }
    public static void editAccount(Account account)
    {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        Account mergeAccount = entityManager.merge(account);

        transaction.commit();
        entityManager.close();
    }
    //endregion

    //region Videos
    public static Video addVideo(Video video) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        entityManager.persist(video);
        transaction.commit();

        Video savedVideo = entityManager.find(Video.class, video.getVideoId());

        entityManager.close();
        return savedVideo;
    }
    public static void editVideo(Video video)
    {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        Video mergedVideo = entityManager.merge(video);

        transaction.commit();
        entityManager.close();
    }
    public static void deleteVideo(Long videoId)
    {

        //Delete VideoViews
        //Delete Comments
        //Delete CommentReactions
        //Delete RepliedComment
        //Delete Video_Category
        //Delete Video_Playlist

    }

    public static List<Category> getVideoCategories(Long videoId) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaQuery<Category> cq = cb.createQuery(Category.class).distinct(true);
            Root<Category> categoryRoot = cq.from(Category.class);
            Join<Category, VideoCategory> videoViewJoin = categoryRoot.join("Video_Category", JoinType.INNER);

            cq.select(categoryRoot)
                    .where(cb.equal(videoViewJoin.get("videoId"), videoId));

            TypedQuery<Category> query = entityManager.createQuery(cq);
            return query.getResultList();
        } finally {
            if (entityManager.isOpen()) {
                entityManager.close();
            }
        }
    }
    public static VideoCategory addVideoCategory(Long videoId, int categoryId) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        TypedQuery<VideoCategory> query = entityManager.createQuery(
                "SELECT vc FROM VideoCategory vc WHERE vc.videoId = :videoId AND vc.categoryId = :categoryId", VideoCategory.class);
        query.setParameter("videoId", videoId);
        query.setParameter("categoryId", categoryId);
        VideoCategory videoCategory = new VideoCategory(videoId, categoryId);

        try
        {
            query.getSingleResult();
        }
        catch (NoResultException e){
            entityManager.persist(videoCategory);
            transaction.commit();
        }

        entityManager.close();
        return videoCategory;
    }

    public static List<VideoView> getVideoViewsOfVideo(Long videoId)
    {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            TypedQuery<VideoView> query = entityManager.createQuery("SELECT v FROM VideoViews v WHERE v.videoId = :videoId", VideoView.class);
            query.setParameter("videoId",videoId);
            return query.getResultList();
        } finally {
            if (entityManager.isOpen()) {
                entityManager.close();
            }
        }
    }
    public static List<Reaction> getVideoReactions(Long videoId)
    {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            TypedQuery<Reaction> query = entityManager.createQuery("SELECT r FROM Reactions r WHERE r.videoId = :videoId", Reaction.class);
            query.setParameter("videoId",videoId);
            return query.getResultList();
        } finally {
            if (entityManager.isOpen()) {
                entityManager.close();
            }
        }

    }
    public static List<Comment> getVideoComments(Long videoId)
    {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            TypedQuery<Comment> query = entityManager.createQuery("SELECT c FROM Comments c WHERE c.videoId = :videoId", Comment.class);
            query.setParameter("videoId",videoId);
            return query.getResultList();
        } finally {
            if (entityManager.isOpen()) {
                entityManager.close();
            }
        }
    }
    public static VideoView addVideoView(VideoView videoView)
    {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        entityManager.persist(videoView);
        transaction.commit();

        VideoView savedVideoView = entityManager.find(VideoView.class, videoView.getVideoViewId());

        entityManager.close();
        return savedVideoView;
    }
    public static List<Video> getWatchHistory(Long channelId) {

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaQuery<Video> cq = cb.createQuery(Video.class).distinct(true);
            Root<Video> videoRoot = cq.from(Video.class);
            Join<Video, VideoView> videoViewJoin = videoRoot.join("videoViews", JoinType.INNER);

            cq.select(videoRoot)
                    .where(cb.equal(videoViewJoin.get("channelId"), channelId))
                    .orderBy(cb.desc(videoViewJoin.get("ViewDateTime")));

            TypedQuery<Video> query = entityManager.createQuery(cq);
            query.setMaxResults(100);

            return query.getResultList();
        } finally {
            if (entityManager.isOpen()) {
                entityManager.close();
            }
        }
    }
    //endregion

    //region Categories
    public static List<Category> getCategories()
    {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            List<Category> categories = entityManager.createQuery(
                            "SELECT c FROM Categories c", Category.class)
                    .getResultList();
            entityManager.getTransaction().commit();
            return categories;
        } finally {
            if (entityManager.isOpen()) {
                entityManager.close();
            }
        }
    }
    //endregion
}