package Shared.Models;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "channel_playlist")
@IdClass(ChannelPlaylist.ChannelPlaylistId.class)
public class ChannelPlaylist {

    @Id
    @Column(name = "ChannelId", nullable = false)
    private Long channelId;

    @Id
    @Column(name = "PlaylistId", nullable = false)
    private Long playlistId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ChannelId", referencedColumnName = "ChannelId", insertable = false, updatable = false)
    private Channel channel;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PlaylistId", referencedColumnName = "PlaylistId", insertable = false, updatable = false)
    private Playlist playlist;

    public Long getChannelId() {
        return channelId;
    }

    public void setChannelId(Long channelId) {
        this.channelId = channelId;
    }

    public Long getPlaylistId() {
        return playlistId;
    }

    public void setPlaylistId(Long playlistId) {
        this.playlistId = playlistId;
    }

    public Channel getChannel() {
        return channel;
    }

    public Playlist getPlaylist() {
        return playlist;
    }

    public ChannelPlaylist()
    {

    }
    public ChannelPlaylist(Long channelId, Long playlistId)
    {

        this.channelId = channelId;
        this.playlistId = playlistId;
    }

    public static class ChannelPlaylistId implements Serializable {
        private Long channelId;
        private Long playlistId;
    }
}