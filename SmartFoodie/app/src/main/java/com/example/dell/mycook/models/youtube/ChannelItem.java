package com.example.dell.mycook.models.youtube;

import com.google.gson.annotations.SerializedName;

/**
 * Created by dell on 4/24/2017.
 */

public class ChannelItem {

    VideoId id;
    VideoSnippet snippet;

    public ChannelItem(VideoId id, VideoSnippet snippet) {
        this.id = id;
        this.snippet = snippet;
    }

    public VideoId getId() {
        return id;
    }

    public void setId(VideoId id) {
        this.id = id;
    }

    public VideoSnippet getSnippet() {
        return snippet;
    }

    public void setSnippet(VideoSnippet snippet) {
        this.snippet = snippet;
    }

    public class VideoId{
        String videoId;

        public VideoId(String videoId) {
            this.videoId = videoId;
        }

        public String getVideoId() {
            return videoId;
        }

        public void setVideoId(String videoId) {
            this.videoId = videoId;
        }
    }

    public class VideoSnippet{
        String title;
        String description;
        String publishedAt;
        Thumbnails thumbnails;

        public VideoSnippet(String title, String description, String publishedAt, Thumbnails thumbnails) {
            this.title = title;
            this.description = description;
            this.publishedAt = publishedAt;
            this.thumbnails = thumbnails;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getPublishedAt() {
            return publishedAt;
        }

        public void setPublishedAt(String publishedAt) {
            this.publishedAt = publishedAt;
        }

        public Thumbnails getThumbnails() {
            return thumbnails;
        }

        public void setThumbnails(Thumbnails thumbnails) {
            this.thumbnails = thumbnails;
        }

        public class Thumbnails{
            @SerializedName("default") Default defaultThumbnail;

            public Thumbnails(Default defaultThumbnail) {
                this.defaultThumbnail = defaultThumbnail;
            }

            public Default getDefaultThumbnail() {
                return defaultThumbnail;
            }

            public void setDefaultThumbnail(Default defaultThumbnail) {
                this.defaultThumbnail = defaultThumbnail;
            }

            public class Default{
                String url;
                int width;
                int height;

                public Default(){}

                public Default(String url, int width, int height) {
                    this.url = url;
                    this.width = width;
                    this.height = height;
                }

                public String getUrl() {
                    return url;
                }

                public void setUrl(String url) {
                    this.url = url;
                }

                public int getWidth() {
                    return width;
                }

                public void setWidth(int width) {
                    this.width = width;
                }

                public int getHeight() {
                    return height;
                }

                public void setHeight(int height) {
                    this.height = height;
                }
            }
        }
    }
}
