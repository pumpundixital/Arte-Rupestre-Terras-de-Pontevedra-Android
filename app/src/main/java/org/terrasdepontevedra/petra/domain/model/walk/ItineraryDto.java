package org.terrasdepontevedra.petra.domain.model.walk;

import java.io.Serializable;
import java.util.List;


public class ItineraryDto implements Serializable {

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


    public static class GuidBean {
        /**
         * rendered : https://vigoexperience.com/?post_type=itinerario&#038;p=582
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
         * rendered : Edificios de Michel Pacewicz
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
         * rendered : <p>Stephane Michel Jean Pacewicz Durand nació el 19 de octubre de 1843 en la localidad francesa de Chateau-Gontier (cerca del río Loira).<br />
         De ascendencia polaca, en 1860 inicia sus estudios de arquitectura formándose con el arquitecto parisino Paul Abadie durante 6 años.<br />
         En 1872 conoce a Benito Sanjurjo, que por aquel entonces residía en París estudiando arquitectura, con quien entabla amistad. Gracias a su amistad, Pacewicz entra en contacto con algunos miembros de la alta burguesía viguesa de la época (Manuel Bárcena, Paulino y Francisco Yáñez, Rosendo Silva,&#8230;) que se encontraban en París con motivo de la Exposición Universal de París de 1889.<br />
         Es probable que se quedaran fascinados por la arquitectura eclética parisina de la época ya que en 1897 con 61 años de edad, Pacewicz llega a Vigo con varios proyectos ya redactados.<br />
         Se instala en la ciudad en 1904 con su mujer Anne Marie Traber y comienza a trabajar utilizando la firma de otros arquitectos de la ciudad (como el mismo Jenaro de la Fuente) debido a que su firma no se reconocía como arquitecto francés.</p>

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
         * rendered : <p>Visita los edificios más importantes de Pacewicz, arquitecto parisino que definión la arquitectura burguesa de Vigo</p>

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
         * id : 581
         * alt_text :
         * caption : puerta del sol
         * description :
         * media_type : image
         * media_details : {"width":800,"height":533,"file":"2017/09/13311.jpg","sizes":{"thumbnail":{"file":"13311-500x265.jpg","width":500,"height":265,"mime-type":"image/jpeg","source_url":"https://vigoexperience.com/wp-content/uploads/2017/09/13311-500x265.jpg"},"medium":{"file":"13311-299x199.jpg","width":299,"height":199,"mime-type":"image/jpeg","source_url":"https://vigoexperience.com/wp-content/uploads/2017/09/13311-299x199.jpg"},"medium_large":{"file":"13311-768x512.jpg","width":768,"height":512,"mime-type":"image/jpeg","source_url":"https://vigoexperience.com/wp-content/uploads/2017/09/13311-768x512.jpg"},"widget":{"file":"13311-36x36.jpg","width":36,"height":36,"mime-type":"image/jpeg","source_url":"https://vigoexperience.com/wp-content/uploads/2017/09/13311-36x36.jpg"},"square":{"file":"13311-180x180.jpg","width":180,"height":180,"mime-type":"image/jpeg","source_url":"https://vigoexperience.com/wp-content/uploads/2017/09/13311-180x180.jpg"},"featured":{"file":"13311-800x430.jpg","width":800,"height":430,"mime-type":"image/jpeg","source_url":"https://vigoexperience.com/wp-content/uploads/2017/09/13311-800x430.jpg"},"portfolio":{"file":"13311-495x400.jpg","width":495,"height":400,"mime-type":"image/jpeg","source_url":"https://vigoexperience.com/wp-content/uploads/2017/09/13311-495x400.jpg"},"portfolio_small":{"file":"13311-260x185.jpg","width":260,"height":185,"mime-type":"image/jpeg","source_url":"https://vigoexperience.com/wp-content/uploads/2017/09/13311-260x185.jpg"},"magazine":{"file":"13311-710x375.jpg","width":710,"height":375,"mime-type":"image/jpeg","source_url":"https://vigoexperience.com/wp-content/uploads/2017/09/13311-710x375.jpg"},"masonry":{"file":"13311-705x470.jpg","width":705,"height":470,"mime-type":"image/jpeg","source_url":"https://vigoexperience.com/wp-content/uploads/2017/09/13311-705x470.jpg"},"entry_with_sidebar":{"file":"13311-800x321.jpg","width":800,"height":321,"mime-type":"image/jpeg","source_url":"https://vigoexperience.com/wp-content/uploads/2017/09/13311-800x321.jpg"},"entry_without_sidebar":{"file":"13311-800x423.jpg","width":800,"height":423,"mime-type":"image/jpeg","source_url":"https://vigoexperience.com/wp-content/uploads/2017/09/13311-800x423.jpg"},"shop_thumbnail":{"file":"13311-120x120.jpg","width":120,"height":120,"mime-type":"image/jpeg","source_url":"https://vigoexperience.com/wp-content/uploads/2017/09/13311-120x120.jpg"},"shop_catalog":{"file":"13311-450x450.jpg","width":450,"height":450,"mime-type":"image/jpeg","source_url":"https://vigoexperience.com/wp-content/uploads/2017/09/13311-450x450.jpg"},"shop_single":{"file":"13311-450x300.jpg","width":450,"height":300,"mime-type":"image/jpeg","source_url":"https://vigoexperience.com/wp-content/uploads/2017/09/13311-450x300.jpg"}},"image_meta":{"aperture":"0","credit":"","camera":"","caption":"","created_timestamp":"0","copyright":"","focal_length":"0","iso":"0","shutter_speed":"0","title":"","orientation":"0","keywords":[]}}
         * post : null
         * source_url : https://vigoexperience.com/wp-content/uploads/2017/09/13311.jpg
         */

        private int id;
        private String alt_text;
        private String caption;
        private String description;
        private String media_type;
        private MediaDetailsBean media_details;
        private Object post;
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

        public Object getPost() {
            return post;
        }

        public void setPost(Object post) {
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
             * width : 800
             * height : 533
             * file : 2017/09/13311.jpg
             * sizes : {"thumbnail":{"file":"13311-500x265.jpg","width":500,"height":265,"mime-type":"image/jpeg","source_url":"https://vigoexperience.com/wp-content/uploads/2017/09/13311-500x265.jpg"},"medium":{"file":"13311-299x199.jpg","width":299,"height":199,"mime-type":"image/jpeg","source_url":"https://vigoexperience.com/wp-content/uploads/2017/09/13311-299x199.jpg"},"medium_large":{"file":"13311-768x512.jpg","width":768,"height":512,"mime-type":"image/jpeg","source_url":"https://vigoexperience.com/wp-content/uploads/2017/09/13311-768x512.jpg"},"widget":{"file":"13311-36x36.jpg","width":36,"height":36,"mime-type":"image/jpeg","source_url":"https://vigoexperience.com/wp-content/uploads/2017/09/13311-36x36.jpg"},"square":{"file":"13311-180x180.jpg","width":180,"height":180,"mime-type":"image/jpeg","source_url":"https://vigoexperience.com/wp-content/uploads/2017/09/13311-180x180.jpg"},"featured":{"file":"13311-800x430.jpg","width":800,"height":430,"mime-type":"image/jpeg","source_url":"https://vigoexperience.com/wp-content/uploads/2017/09/13311-800x430.jpg"},"portfolio":{"file":"13311-495x400.jpg","width":495,"height":400,"mime-type":"image/jpeg","source_url":"https://vigoexperience.com/wp-content/uploads/2017/09/13311-495x400.jpg"},"portfolio_small":{"file":"13311-260x185.jpg","width":260,"height":185,"mime-type":"image/jpeg","source_url":"https://vigoexperience.com/wp-content/uploads/2017/09/13311-260x185.jpg"},"magazine":{"file":"13311-710x375.jpg","width":710,"height":375,"mime-type":"image/jpeg","source_url":"https://vigoexperience.com/wp-content/uploads/2017/09/13311-710x375.jpg"},"masonry":{"file":"13311-705x470.jpg","width":705,"height":470,"mime-type":"image/jpeg","source_url":"https://vigoexperience.com/wp-content/uploads/2017/09/13311-705x470.jpg"},"entry_with_sidebar":{"file":"13311-800x321.jpg","width":800,"height":321,"mime-type":"image/jpeg","source_url":"https://vigoexperience.com/wp-content/uploads/2017/09/13311-800x321.jpg"},"entry_without_sidebar":{"file":"13311-800x423.jpg","width":800,"height":423,"mime-type":"image/jpeg","source_url":"https://vigoexperience.com/wp-content/uploads/2017/09/13311-800x423.jpg"},"shop_thumbnail":{"file":"13311-120x120.jpg","width":120,"height":120,"mime-type":"image/jpeg","source_url":"https://vigoexperience.com/wp-content/uploads/2017/09/13311-120x120.jpg"},"shop_catalog":{"file":"13311-450x450.jpg","width":450,"height":450,"mime-type":"image/jpeg","source_url":"https://vigoexperience.com/wp-content/uploads/2017/09/13311-450x450.jpg"},"shop_single":{"file":"13311-450x300.jpg","width":450,"height":300,"mime-type":"image/jpeg","source_url":"https://vigoexperience.com/wp-content/uploads/2017/09/13311-450x300.jpg"}}
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
                 * thumbnail : {"file":"13311-500x265.jpg","width":500,"height":265,"mime-type":"image/jpeg","source_url":"https://vigoexperience.com/wp-content/uploads/2017/09/13311-500x265.jpg"}
                 * medium : {"file":"13311-299x199.jpg","width":299,"height":199,"mime-type":"image/jpeg","source_url":"https://vigoexperience.com/wp-content/uploads/2017/09/13311-299x199.jpg"}
                 * medium_large : {"file":"13311-768x512.jpg","width":768,"height":512,"mime-type":"image/jpeg","source_url":"https://vigoexperience.com/wp-content/uploads/2017/09/13311-768x512.jpg"}
                 * widget : {"file":"13311-36x36.jpg","width":36,"height":36,"mime-type":"image/jpeg","source_url":"https://vigoexperience.com/wp-content/uploads/2017/09/13311-36x36.jpg"}
                 * square : {"file":"13311-180x180.jpg","width":180,"height":180,"mime-type":"image/jpeg","source_url":"https://vigoexperience.com/wp-content/uploads/2017/09/13311-180x180.jpg"}
                 * featured : {"file":"13311-800x430.jpg","width":800,"height":430,"mime-type":"image/jpeg","source_url":"https://vigoexperience.com/wp-content/uploads/2017/09/13311-800x430.jpg"}
                 * portfolio : {"file":"13311-495x400.jpg","width":495,"height":400,"mime-type":"image/jpeg","source_url":"https://vigoexperience.com/wp-content/uploads/2017/09/13311-495x400.jpg"}
                 * portfolio_small : {"file":"13311-260x185.jpg","width":260,"height":185,"mime-type":"image/jpeg","source_url":"https://vigoexperience.com/wp-content/uploads/2017/09/13311-260x185.jpg"}
                 * magazine : {"file":"13311-710x375.jpg","width":710,"height":375,"mime-type":"image/jpeg","source_url":"https://vigoexperience.com/wp-content/uploads/2017/09/13311-710x375.jpg"}
                 * masonry : {"file":"13311-705x470.jpg","width":705,"height":470,"mime-type":"image/jpeg","source_url":"https://vigoexperience.com/wp-content/uploads/2017/09/13311-705x470.jpg"}
                 * entry_with_sidebar : {"file":"13311-800x321.jpg","width":800,"height":321,"mime-type":"image/jpeg","source_url":"https://vigoexperience.com/wp-content/uploads/2017/09/13311-800x321.jpg"}
                 * entry_without_sidebar : {"file":"13311-800x423.jpg","width":800,"height":423,"mime-type":"image/jpeg","source_url":"https://vigoexperience.com/wp-content/uploads/2017/09/13311-800x423.jpg"}
                 * shop_thumbnail : {"file":"13311-120x120.jpg","width":120,"height":120,"mime-type":"image/jpeg","source_url":"https://vigoexperience.com/wp-content/uploads/2017/09/13311-120x120.jpg"}
                 * shop_catalog : {"file":"13311-450x450.jpg","width":450,"height":450,"mime-type":"image/jpeg","source_url":"https://vigoexperience.com/wp-content/uploads/2017/09/13311-450x450.jpg"}
                 * shop_single : {"file":"13311-450x300.jpg","width":450,"height":300,"mime-type":"image/jpeg","source_url":"https://vigoexperience.com/wp-content/uploads/2017/09/13311-450x300.jpg"}
                 */

                private ThumbnailBean thumbnail;
                private MediumBean medium;
                private MediumLargeBean medium_large;
                private WidgetBean widget;
                private SquareBean square;
                private FeaturedBean featured;
                private PortfolioBean portfolio;
                private PortfolioSmallBean portfolio_small;
                private MagazineBean magazine;
                private MasonryBean masonry;
                private EntryWithSidebarBean entry_with_sidebar;
                private EntryWithoutSidebarBean entry_without_sidebar;
                private ShopThumbnailBean shop_thumbnail;
                private ShopCatalogBean shop_catalog;
                private ShopSingleBean shop_single;

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

                public WidgetBean getWidget() {
                    return widget;
                }

                public void setWidget(WidgetBean widget) {
                    this.widget = widget;
                }

                public SquareBean getSquare() {
                    return square;
                }

                public void setSquare(SquareBean square) {
                    this.square = square;
                }

                public FeaturedBean getFeatured() {
                    return featured;
                }

                public void setFeatured(FeaturedBean featured) {
                    this.featured = featured;
                }

                public PortfolioBean getPortfolio() {
                    return portfolio;
                }

                public void setPortfolio(PortfolioBean portfolio) {
                    this.portfolio = portfolio;
                }

                public PortfolioSmallBean getPortfolio_small() {
                    return portfolio_small;
                }

                public void setPortfolio_small(PortfolioSmallBean portfolio_small) {
                    this.portfolio_small = portfolio_small;
                }

                public MagazineBean getMagazine() {
                    return magazine;
                }

                public void setMagazine(MagazineBean magazine) {
                    this.magazine = magazine;
                }

                public MasonryBean getMasonry() {
                    return masonry;
                }

                public void setMasonry(MasonryBean masonry) {
                    this.masonry = masonry;
                }

                public EntryWithSidebarBean getEntry_with_sidebar() {
                    return entry_with_sidebar;
                }

                public void setEntry_with_sidebar(EntryWithSidebarBean entry_with_sidebar) {
                    this.entry_with_sidebar = entry_with_sidebar;
                }

                public EntryWithoutSidebarBean getEntry_without_sidebar() {
                    return entry_without_sidebar;
                }

                public void setEntry_without_sidebar(EntryWithoutSidebarBean entry_without_sidebar) {
                    this.entry_without_sidebar = entry_without_sidebar;
                }

                public ShopThumbnailBean getShop_thumbnail() {
                    return shop_thumbnail;
                }

                public void setShop_thumbnail(ShopThumbnailBean shop_thumbnail) {
                    this.shop_thumbnail = shop_thumbnail;
                }

                public ShopCatalogBean getShop_catalog() {
                    return shop_catalog;
                }

                public void setShop_catalog(ShopCatalogBean shop_catalog) {
                    this.shop_catalog = shop_catalog;
                }

                public ShopSingleBean getShop_single() {
                    return shop_single;
                }

                public void setShop_single(ShopSingleBean shop_single) {
                    this.shop_single = shop_single;
                }

                public static class ThumbnailBean {
                    /**
                     * file : 13311-500x265.jpg
                     * width : 500
                     * height : 265
                     * mime-type : image/jpeg
                     * source_url : https://vigoexperience.com/wp-content/uploads/2017/09/13311-500x265.jpg
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
                     * file : 13311-299x199.jpg
                     * width : 299
                     * height : 199
                     * mime-type : image/jpeg
                     * source_url : https://vigoexperience.com/wp-content/uploads/2017/09/13311-299x199.jpg
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
                     * file : 13311-768x512.jpg
                     * width : 768
                     * height : 512
                     * mime-type : image/jpeg
                     * source_url : https://vigoexperience.com/wp-content/uploads/2017/09/13311-768x512.jpg
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

                public static class WidgetBean {
                    /**
                     * file : 13311-36x36.jpg
                     * width : 36
                     * height : 36
                     * mime-type : image/jpeg
                     * source_url : https://vigoexperience.com/wp-content/uploads/2017/09/13311-36x36.jpg
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

                public static class SquareBean {
                    /**
                     * file : 13311-180x180.jpg
                     * width : 180
                     * height : 180
                     * mime-type : image/jpeg
                     * source_url : https://vigoexperience.com/wp-content/uploads/2017/09/13311-180x180.jpg
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

                public static class FeaturedBean {
                    /**
                     * file : 13311-800x430.jpg
                     * width : 800
                     * height : 430
                     * mime-type : image/jpeg
                     * source_url : https://vigoexperience.com/wp-content/uploads/2017/09/13311-800x430.jpg
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

                public static class PortfolioBean {
                    /**
                     * file : 13311-495x400.jpg
                     * width : 495
                     * height : 400
                     * mime-type : image/jpeg
                     * source_url : https://vigoexperience.com/wp-content/uploads/2017/09/13311-495x400.jpg
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

                public static class PortfolioSmallBean {
                    /**
                     * file : 13311-260x185.jpg
                     * width : 260
                     * height : 185
                     * mime-type : image/jpeg
                     * source_url : https://vigoexperience.com/wp-content/uploads/2017/09/13311-260x185.jpg
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

                public static class MagazineBean {
                    /**
                     * file : 13311-710x375.jpg
                     * width : 710
                     * height : 375
                     * mime-type : image/jpeg
                     * source_url : https://vigoexperience.com/wp-content/uploads/2017/09/13311-710x375.jpg
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

                public static class MasonryBean {
                    /**
                     * file : 13311-705x470.jpg
                     * width : 705
                     * height : 470
                     * mime-type : image/jpeg
                     * source_url : https://vigoexperience.com/wp-content/uploads/2017/09/13311-705x470.jpg
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

                public static class EntryWithSidebarBean {
                    /**
                     * file : 13311-800x321.jpg
                     * width : 800
                     * height : 321
                     * mime-type : image/jpeg
                     * source_url : https://vigoexperience.com/wp-content/uploads/2017/09/13311-800x321.jpg
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

                public static class EntryWithoutSidebarBean {
                    /**
                     * file : 13311-800x423.jpg
                     * width : 800
                     * height : 423
                     * mime-type : image/jpeg
                     * source_url : https://vigoexperience.com/wp-content/uploads/2017/09/13311-800x423.jpg
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

                public static class ShopThumbnailBean {
                    /**
                     * file : 13311-120x120.jpg
                     * width : 120
                     * height : 120
                     * mime-type : image/jpeg
                     * source_url : https://vigoexperience.com/wp-content/uploads/2017/09/13311-120x120.jpg
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

                public static class ShopCatalogBean {
                    /**
                     * file : 13311-450x450.jpg
                     * width : 450
                     * height : 450
                     * mime-type : image/jpeg
                     * source_url : https://vigoexperience.com/wp-content/uploads/2017/09/13311-450x450.jpg
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

                public static class ShopSingleBean {
                    /**
                     * file : 13311-450x300.jpg
                     * width : 450
                     * height : 300
                     * mime-type : image/jpeg
                     * source_url : https://vigoexperience.com/wp-content/uploads/2017/09/13311-450x300.jpg
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

    // FIXME generate failure  field _$WpTerm201
// FIXME generate failure  field _$WpAttachment41
// FIXME generate failure  field _$WpFeaturedmedia235
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
             * href : https://vigoexperience.com/wp-json/wp/v2/itinerario/582
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
             * href : https://vigoexperience.com/wp-json/wp/v2/itinerario
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
             * href : https://vigoexperience.com/wp-json/wp/v2/types/itinerario
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
             * href : https://vigoexperience.com/wp-json/wp/v2/users/2
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

}