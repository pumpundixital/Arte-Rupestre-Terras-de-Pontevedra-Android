package org.terrasdepontevedra.petra.domain.model.walk;

import java.io.Serializable;
import java.util.List;


public class ItineraryDto implements Serializable {


    /**
     * id : 1074
     * date : 2018-09-10T10:27:30
     * date_gmt : 2018-09-10T10:27:30
     * guid : {"rendered":"http://app.terrasdepontevedra.org/itinerario/itinerario-1/"}
     * modified : 2018-09-11T14:43:24
     * modified_gmt : 2018-09-11T14:43:24
     * slug : itinerario-1
     * status : publish
     * type : itinerario
     * link : http://app.terrasdepontevedra.org/gl/itinerario/itinerario-1/
     * title : {"rendered":"GL Itinerario 1"}
     * content : {"rendered":"<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.<\/p>\n","protected":false}
     * excerpt : {"rendered":"<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam,<\/p>\n","protected":false}
     * author : 1
     * featured_media : 1075
     * template :
     * meta : []
     * tipo-itinerario : [145]
     * better_featured_image : {"id":1075,"alt_text":"","caption":"Calco Outeiro dos Cogoludos","description":"","media_type":"image","media_details":{"width":843,"height":596,"file":"2017/10/cogoludos03.jpg","sizes":{"thumbnail":{"file":"cogoludos03-550x550.jpg","width":550,"height":550,"mime-type":"image/jpeg","source_url":"http://app.terrasdepontevedra.org/wp-content/uploads/2017/10/cogoludos03-550x550.jpg"},"medium":{"file":"cogoludos03-300x212.jpg","width":300,"height":212,"mime-type":"image/jpeg","source_url":"http://app.terrasdepontevedra.org/wp-content/uploads/2017/10/cogoludos03-300x212.jpg"},"medium_large":{"file":"cogoludos03-768x543.jpg","width":768,"height":543,"mime-type":"image/jpeg","source_url":"http://app.terrasdepontevedra.org/wp-content/uploads/2017/10/cogoludos03-768x543.jpg"}},"image_meta":{"aperture":"0","credit":"","camera":"","caption":"","created_timestamp":"0","copyright":"","focal_length":"0","iso":"0","shutter_speed":"0","title":"","orientation":"0","keywords":[]}},"post":1074,"source_url":"http://app.terrasdepontevedra.org/wp-content/uploads/2017/10/cogoludos03.jpg"}
     * wpcf-ids-de-los-lugares : ["1067,1064"]
     * rest_api_enabler : {"wpcf-ids-de-los-lugares":"1067,1064"}
     * _links : {"self":[{"href":"http://app.terrasdepontevedra.org/gl/wp-json/wp/v2/itinerario/1074/"}],"collection":[{"href":"http://app.terrasdepontevedra.org/gl/wp-json/wp/v2/itinerario/"}],"about":[{"href":"http://app.terrasdepontevedra.org/gl/wp-json/wp/v2/types/itinerario/"}],"author":[{"embeddable":true,"href":"http://app.terrasdepontevedra.org/gl/wp-json/wp/v2/users/1/"}],"wp:featuredmedia":[{"embeddable":true,"href":"http://app.terrasdepontevedra.org/gl/wp-json/wp/v2/media/1075/"}],"wp:attachment":[{"href":"http://app.terrasdepontevedra.org/gl/wp-json/wp/v2/media/?parent=1074"}],"wp:term":[{"taxonomy":"tipo-itinerario","embeddable":true,"href":"http://app.terrasdepontevedra.org/gl/wp-json/wp/v2/tipo-itinerario/?post=1074"}],"curies":[{"name":"wp","href":"https://api.w.org/{rel}","templated":true}]}
     */

    private int id;
    private String date;
    private String date_gmt;
    private GuidBean guid;
    private String modified;
    private String modified_gmt;
    private String slug;
    private String status;
    private String type;
    private String link;
    private TitleBean title;
    private ContentBean content;
    private ExcerptBean excerpt;
    private int author;
    private int featured_media;
    private String template;
    private BetterFeaturedImageBean better_featured_image;
    private LinksBean _links;
    private List<?> meta;
    @com.google.gson.annotations.SerializedName("tipo-itinerario")
    private List<Integer> tipoitinerario;
    @com.google.gson.annotations.SerializedName("wpcf-ids-de-los-lugares")
    private List<String> wpcfidsdeloslugares;

    public static class GuidBean {
        /**
         * rendered : http://app.terrasdepontevedra.org/itinerario/itinerario-1/
         */

        private String rendered;

        public String getRendered() {
            return rendered;
        }

        public void setRendered(String rendered) {
            this.rendered = rendered;
        }
    }

    public static class TitleBean {
        /**
         * rendered : GL Itinerario 1
         */

        private String rendered;

        public String getRendered() {
            return rendered;
        }

        public void setRendered(String rendered) {
            this.rendered = rendered;
        }
    }

    public static class ContentBean {
        /**
         * rendered : <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.</p>
         * <p>
         * protected : false
         */

        private String rendered;
        @com.google.gson.annotations.SerializedName("protected")
        private boolean protectedX;

        public String getRendered() {
            return rendered;
        }

        public void setRendered(String rendered) {
            this.rendered = rendered;
        }

        public boolean isProtectedX() {
            return protectedX;
        }

        public void setProtectedX(boolean protectedX) {
            this.protectedX = protectedX;
        }
    }

    public static class ExcerptBean {
        /**
         * rendered : <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam,</p>
         * <p>
         * protected : false
         */

        private String rendered;
        @com.google.gson.annotations.SerializedName("protected")
        private boolean protectedX;

        public String getRendered() {
            return rendered;
        }

        public void setRendered(String rendered) {
            this.rendered = rendered;
        }

        public boolean isProtectedX() {
            return protectedX;
        }

        public void setProtectedX(boolean protectedX) {
            this.protectedX = protectedX;
        }
    }

    public static class BetterFeaturedImageBean {
        /**
         * id : 1075
         * alt_text :
         * caption : Calco Outeiro dos Cogoludos
         * description :
         * media_type : image
         * media_details : {"width":843,"height":596,"file":"2017/10/cogoludos03.jpg","sizes":{"thumbnail":{"file":"cogoludos03-550x550.jpg","width":550,"height":550,"mime-type":"image/jpeg","source_url":"http://app.terrasdepontevedra.org/wp-content/uploads/2017/10/cogoludos03-550x550.jpg"},"medium":{"file":"cogoludos03-300x212.jpg","width":300,"height":212,"mime-type":"image/jpeg","source_url":"http://app.terrasdepontevedra.org/wp-content/uploads/2017/10/cogoludos03-300x212.jpg"},"medium_large":{"file":"cogoludos03-768x543.jpg","width":768,"height":543,"mime-type":"image/jpeg","source_url":"http://app.terrasdepontevedra.org/wp-content/uploads/2017/10/cogoludos03-768x543.jpg"}},"image_meta":{"aperture":"0","credit":"","camera":"","caption":"","created_timestamp":"0","copyright":"","focal_length":"0","iso":"0","shutter_speed":"0","title":"","orientation":"0","keywords":[]}}
         * post : 1074
         * source_url : http://app.terrasdepontevedra.org/wp-content/uploads/2017/10/cogoludos03.jpg
         */

        private int id;
        private String alt_text;
        private String caption;
        private String description;
        private String media_type;
        private MediaDetailsBean media_details;
        private int post;
        private String source_url;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getAlt_text() {
            return alt_text;
        }

        public void setAlt_text(String alt_text) {
            this.alt_text = alt_text;
        }

        public String getCaption() {
            return caption;
        }

        public void setCaption(String caption) {
            this.caption = caption;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getMedia_type() {
            return media_type;
        }

        public void setMedia_type(String media_type) {
            this.media_type = media_type;
        }

        public MediaDetailsBean getMedia_details() {
            return media_details;
        }

        public void setMedia_details(MediaDetailsBean media_details) {
            this.media_details = media_details;
        }

        public int getPost() {
            return post;
        }

        public void setPost(int post) {
            this.post = post;
        }

        public String getSource_url() {
            return source_url;
        }

        public void setSource_url(String source_url) {
            this.source_url = source_url;
        }

        public static class MediaDetailsBean {
            /**
             * width : 843
             * height : 596
             * file : 2017/10/cogoludos03.jpg
             * sizes : {"thumbnail":{"file":"cogoludos03-550x550.jpg","width":550,"height":550,"mime-type":"image/jpeg","source_url":"http://app.terrasdepontevedra.org/wp-content/uploads/2017/10/cogoludos03-550x550.jpg"},"medium":{"file":"cogoludos03-300x212.jpg","width":300,"height":212,"mime-type":"image/jpeg","source_url":"http://app.terrasdepontevedra.org/wp-content/uploads/2017/10/cogoludos03-300x212.jpg"},"medium_large":{"file":"cogoludos03-768x543.jpg","width":768,"height":543,"mime-type":"image/jpeg","source_url":"http://app.terrasdepontevedra.org/wp-content/uploads/2017/10/cogoludos03-768x543.jpg"}}
             * image_meta : {"aperture":"0","credit":"","camera":"","caption":"","created_timestamp":"0","copyright":"","focal_length":"0","iso":"0","shutter_speed":"0","title":"","orientation":"0","keywords":[]}
             */

            private int width;
            private int height;
            private String file;
            private SizesBean sizes;
            private ImageMetaBean image_meta;

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

            public String getFile() {
                return file;
            }

            public void setFile(String file) {
                this.file = file;
            }

            public SizesBean getSizes() {
                return sizes;
            }

            public void setSizes(SizesBean sizes) {
                this.sizes = sizes;
            }

            public ImageMetaBean getImage_meta() {
                return image_meta;
            }

            public void setImage_meta(ImageMetaBean image_meta) {
                this.image_meta = image_meta;
            }

            public static class SizesBean {
                /**
                 * thumbnail : {"file":"cogoludos03-550x550.jpg","width":550,"height":550,"mime-type":"image/jpeg","source_url":"http://app.terrasdepontevedra.org/wp-content/uploads/2017/10/cogoludos03-550x550.jpg"}
                 * medium : {"file":"cogoludos03-300x212.jpg","width":300,"height":212,"mime-type":"image/jpeg","source_url":"http://app.terrasdepontevedra.org/wp-content/uploads/2017/10/cogoludos03-300x212.jpg"}
                 * medium_large : {"file":"cogoludos03-768x543.jpg","width":768,"height":543,"mime-type":"image/jpeg","source_url":"http://app.terrasdepontevedra.org/wp-content/uploads/2017/10/cogoludos03-768x543.jpg"}
                 */

                private ThumbnailBean thumbnail;
                private MediumBean medium;
                private MediumLargeBean medium_large;

                public ThumbnailBean getThumbnail() {
                    return thumbnail;
                }

                public void setThumbnail(ThumbnailBean thumbnail) {
                    this.thumbnail = thumbnail;
                }

                public MediumBean getMedium() {
                    return medium;
                }

                public void setMedium(MediumBean medium) {
                    this.medium = medium;
                }

                public MediumLargeBean getMedium_large() {
                    return medium_large;
                }

                public void setMedium_large(MediumLargeBean medium_large) {
                    this.medium_large = medium_large;
                }

                public static class ThumbnailBean {
                    /**
                     * file : cogoludos03-550x550.jpg
                     * width : 550
                     * height : 550
                     * mime-type : image/jpeg
                     * source_url : http://app.terrasdepontevedra.org/wp-content/uploads/2017/10/cogoludos03-550x550.jpg
                     */

                    private String file;
                    private int width;
                    private int height;
                    @com.google.gson.annotations.SerializedName("mime-type")
                    private String mimetype;
                    private String source_url;

                    public String getFile() {
                        return file;
                    }

                    public void setFile(String file) {
                        this.file = file;
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

                    public String getMimetype() {
                        return mimetype;
                    }

                    public void setMimetype(String mimetype) {
                        this.mimetype = mimetype;
                    }

                    public String getSource_url() {
                        return source_url;
                    }

                    public void setSource_url(String source_url) {
                        this.source_url = source_url;
                    }
                }

                public static class MediumBean {
                    /**
                     * file : cogoludos03-300x212.jpg
                     * width : 300
                     * height : 212
                     * mime-type : image/jpeg
                     * source_url : http://app.terrasdepontevedra.org/wp-content/uploads/2017/10/cogoludos03-300x212.jpg
                     */

                    private String file;
                    private int width;
                    private int height;
                    @com.google.gson.annotations.SerializedName("mime-type")
                    private String mimetype;
                    private String source_url;

                    public String getFile() {
                        return file;
                    }

                    public void setFile(String file) {
                        this.file = file;
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

                    public String getMimetype() {
                        return mimetype;
                    }

                    public void setMimetype(String mimetype) {
                        this.mimetype = mimetype;
                    }

                    public String getSource_url() {
                        return source_url;
                    }

                    public void setSource_url(String source_url) {
                        this.source_url = source_url;
                    }
                }

                public static class MediumLargeBean {
                    /**
                     * file : cogoludos03-768x543.jpg
                     * width : 768
                     * height : 543
                     * mime-type : image/jpeg
                     * source_url : http://app.terrasdepontevedra.org/wp-content/uploads/2017/10/cogoludos03-768x543.jpg
                     */

                    private String file;
                    private int width;
                    private int height;
                    @com.google.gson.annotations.SerializedName("mime-type")
                    private String mimetype;
                    private String source_url;

                    public String getFile() {
                        return file;
                    }

                    public void setFile(String file) {
                        this.file = file;
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

                    public String getMimetype() {
                        return mimetype;
                    }

                    public void setMimetype(String mimetype) {
                        this.mimetype = mimetype;
                    }

                    public String getSource_url() {
                        return source_url;
                    }

                    public void setSource_url(String source_url) {
                        this.source_url = source_url;
                    }
                }
            }

            public static class ImageMetaBean {
                /**
                 * aperture : 0
                 * credit :
                 * camera :
                 * caption :
                 * created_timestamp : 0
                 * copyright :
                 * focal_length : 0
                 * iso : 0
                 * shutter_speed : 0
                 * title :
                 * orientation : 0
                 * keywords : []
                 */

                private String aperture;
                private String credit;
                private String camera;
                private String caption;
                private String created_timestamp;
                private String copyright;
                private String focal_length;
                private String iso;
                private String shutter_speed;
                private String title;
                private String orientation;
                private List<?> keywords;

                public String getAperture() {
                    return aperture;
                }

                public void setAperture(String aperture) {
                    this.aperture = aperture;
                }

                public String getCredit() {
                    return credit;
                }

                public void setCredit(String credit) {
                    this.credit = credit;
                }

                public String getCamera() {
                    return camera;
                }

                public void setCamera(String camera) {
                    this.camera = camera;
                }

                public String getCaption() {
                    return caption;
                }

                public void setCaption(String caption) {
                    this.caption = caption;
                }

                public String getCreated_timestamp() {
                    return created_timestamp;
                }

                public void setCreated_timestamp(String created_timestamp) {
                    this.created_timestamp = created_timestamp;
                }

                public String getCopyright() {
                    return copyright;
                }

                public void setCopyright(String copyright) {
                    this.copyright = copyright;
                }

                public String getFocal_length() {
                    return focal_length;
                }

                public void setFocal_length(String focal_length) {
                    this.focal_length = focal_length;
                }

                public String getIso() {
                    return iso;
                }

                public void setIso(String iso) {
                    this.iso = iso;
                }

                public String getShutter_speed() {
                    return shutter_speed;
                }

                public void setShutter_speed(String shutter_speed) {
                    this.shutter_speed = shutter_speed;
                }

                public String getTitle() {
                    return title;
                }

                public void setTitle(String title) {
                    this.title = title;
                }

                public String getOrientation() {
                    return orientation;
                }

                public void setOrientation(String orientation) {
                    this.orientation = orientation;
                }

                public List<?> getKeywords() {
                    return keywords;
                }

                public void setKeywords(List<?> keywords) {
                    this.keywords = keywords;
                }
            }
        }
    }

    public static class RestApiEnablerBean {
        /**
         * wpcf-ids-de-los-lugares : 1067,1064
         */

        @com.google.gson.annotations.SerializedName("wpcf-ids-de-los-lugares")
        private String wpcfidsdeloslugares;

        public String getWpcfidsdeloslugares() {
            return wpcfidsdeloslugares;
        }

        public void setWpcfidsdeloslugares(String wpcfidsdeloslugares) {
            this.wpcfidsdeloslugares = wpcfidsdeloslugares;
        }
    }

    // FIXME generate failure  field _$WpTerm0
// FIXME generate failure  field _$WpAttachment183
// FIXME generate failure  field _$WpFeaturedmedia173
    public static class LinksBean {
        private List<SelfBean> self;
        private List<CollectionBean> collection;
        private List<AboutBean> about;
        private List<AuthorBean> author;
        private List<CuriesBean> curies;

        public List<SelfBean> getSelf() {
            return self;
        }

        public void setSelf(List<SelfBean> self) {
            this.self = self;
        }

        public List<CollectionBean> getCollection() {
            return collection;
        }

        public void setCollection(List<CollectionBean> collection) {
            this.collection = collection;
        }

        public List<AboutBean> getAbout() {
            return about;
        }

        public void setAbout(List<AboutBean> about) {
            this.about = about;
        }

        public List<AuthorBean> getAuthor() {
            return author;
        }

        public void setAuthor(List<AuthorBean> author) {
            this.author = author;
        }

        public static class SelfBean {
            /**
             * href : http://app.terrasdepontevedra.org/gl/wp-json/wp/v2/itinerario/1074/
             */

            private String href;

            public String getHref() {
                return href;
            }

            public void setHref(String href) {
                this.href = href;
            }
        }

        public static class CollectionBean {
            /**
             * href : http://app.terrasdepontevedra.org/gl/wp-json/wp/v2/itinerario/
             */

            private String href;

            public String getHref() {
                return href;
            }

            public void setHref(String href) {
                this.href = href;
            }
        }

        public static class AboutBean {
            /**
             * href : http://app.terrasdepontevedra.org/gl/wp-json/wp/v2/types/itinerario/
             */

            private String href;

            public String getHref() {
                return href;
            }

            public void setHref(String href) {
                this.href = href;
            }
        }

        public static class AuthorBean {
            /**
             * embeddable : true
             * href : http://app.terrasdepontevedra.org/gl/wp-json/wp/v2/users/1/
             */

            private boolean embeddable;
            private String href;

            public boolean isEmbeddable() {
                return embeddable;
            }

            public void setEmbeddable(boolean embeddable) {
                this.embeddable = embeddable;
            }

            public String getHref() {
                return href;
            }

            public void setHref(String href) {
                this.href = href;
            }
        }


        public static class CuriesBean {
            /**
             * name : wp
             * href : https://api.w.org/{rel}
             * templated : true
             */

            private String name;
            private String href;
            private boolean templated;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getHref() {
                return href;
            }

            public void setHref(String href) {
                this.href = href;
            }

            public boolean isTemplated() {
                return templated;
            }

            public void setTemplated(boolean templated) {
                this.templated = templated;
            }
        }
    }

    public int getId() {
        return id;
    }

    public String getDate() {
        return date;
    }

    public String getDate_gmt() {
        return date_gmt;
    }

    public GuidBean getGuid() {
        return guid;
    }

    public String getModified() {
        return modified;
    }

    public String getModified_gmt() {
        return modified_gmt;
    }

    public String getSlug() {
        return slug;
    }

    public String getStatus() {
        return status;
    }

    public String getType() {
        return type;
    }

    public String getLink() {
        return link;
    }

    public TitleBean getTitle() {
        return title;
    }

    public ContentBean getContent() {
        return content;
    }

    public ExcerptBean getExcerpt() {
        return excerpt;
    }

    public int getAuthor() {
        return author;
    }

    public int getFeatured_media() {
        return featured_media;
    }

    public String getTemplate() {
        return template;
    }

    public BetterFeaturedImageBean getBetter_featured_image() {
        return better_featured_image;
    }


    public LinksBean get_links() {
        return _links;
    }

    public List<?> getMeta() {
        return meta;
    }

    public List<Integer> getTipoitinerario() {
        return tipoitinerario;
    }

    public List<String> getWpcfidsdeloslugares() {
        return wpcfidsdeloslugares;
    }
}